package org.spongycastle.jcajce.provider.asymmetric.ec;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.KeyEncoder;
import org.spongycastle.crypto.agreement.ECDHBasicAgreement;
import org.spongycastle.crypto.engines.AESEngine;
import org.spongycastle.crypto.engines.DESedeEngine;
import org.spongycastle.crypto.engines.IESEngine;
import org.spongycastle.crypto.generators.ECKeyPairGenerator;
import org.spongycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.spongycastle.crypto.generators.KDF2BytesGenerator;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyGenerationParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.IESWithCipherParameters;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.parsers.ECIESPublicKeyParser;
import org.spongycastle.crypto.util.DigestFactory;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.IESUtil;
import org.spongycastle.jcajce.provider.util.BadBlockException;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.interfaces.ECKey;
import org.spongycastle.jce.interfaces.IESKey;
import org.spongycastle.jce.spec.IESParameterSpec;
import org.spongycastle.util.Strings;

/* loaded from: classes3.dex */
public class IESCipher extends CipherSpi {
    private ByteArrayOutputStream buffer;
    private boolean dhaesMode;
    private IESEngine engine;
    private AlgorithmParameters engineParam;
    private IESParameterSpec engineSpec;
    private final JcaJceHelper helper;
    private int ivLength;
    private AsymmetricKeyParameter key;
    private AsymmetricKeyParameter otherKeyParameter;
    private SecureRandom random;
    private int state;

    public IESCipher(IESEngine iESEngine) {
        this.helper = new BCJcaJceHelper();
        this.state = -1;
        this.buffer = new ByteArrayOutputStream();
        this.engineParam = null;
        this.engineSpec = null;
        this.dhaesMode = false;
        this.otherKeyParameter = null;
        this.engine = iESEngine;
        this.ivLength = 0;
    }

    public IESCipher(IESEngine iESEngine, int i) {
        this.helper = new BCJcaJceHelper();
        this.state = -1;
        this.buffer = new ByteArrayOutputStream();
        this.engineParam = null;
        this.engineSpec = null;
        this.dhaesMode = false;
        this.otherKeyParameter = null;
        this.engine = iESEngine;
        this.ivLength = i;
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetBlockSize() {
        if (this.engine.getCipher() != null) {
            return this.engine.getCipher().getBlockSize();
        }
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetKeySize(Key key) {
        if (key instanceof ECKey) {
            return ((ECKey) key).getParameters().getCurve().getFieldSize();
        }
        throw new IllegalArgumentException("not an EC key");
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineGetIV() {
        IESParameterSpec iESParameterSpec = this.engineSpec;
        if (iESParameterSpec != null) {
            return iESParameterSpec.getNonce();
        }
        return null;
    }

    @Override // javax.crypto.CipherSpi
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParam == null && this.engineSpec != null) {
            try {
                AlgorithmParameters createAlgorithmParameters = this.helper.createAlgorithmParameters("IES");
                this.engineParam = createAlgorithmParameters;
                createAlgorithmParameters.init(this.engineSpec);
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return this.engineParam;
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetMode(String str) throws NoSuchAlgorithmException {
        String upperCase = Strings.toUpperCase(str);
        if (upperCase.equals("NONE")) {
            this.dhaesMode = false;
        } else if (upperCase.equals("DHAES")) {
            this.dhaesMode = true;
        } else {
            throw new IllegalArgumentException("can't support mode " + str);
        }
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetOutputSize(int i) {
        int size;
        if (this.key == null) {
            throw new IllegalStateException("cipher not initialised");
        }
        int macSize = this.engine.getMac().getMacSize();
        int fieldSize = this.otherKeyParameter == null ? ((((ECKeyParameters) this.key).getParameters().getCurve().getFieldSize() + 7) / 8) * 2 : 0;
        if (this.engine.getCipher() != null) {
            int i2 = this.state;
            if (i2 == 1 || i2 == 3) {
                i = this.engine.getCipher().getOutputSize(i);
            } else if (i2 == 2 || i2 == 4) {
                i = this.engine.getCipher().getOutputSize((i - macSize) - fieldSize);
            } else {
                throw new IllegalStateException("cipher not initialised");
            }
        }
        int i3 = this.state;
        if (i3 == 1 || i3 == 3) {
            size = this.buffer.size() + macSize + 1 + fieldSize;
        } else if (i3 == 2 || i3 == 4) {
            size = (this.buffer.size() - macSize) - fieldSize;
        } else {
            throw new IllegalStateException("cipher not initialised");
        }
        return size + i;
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetPadding(String str) throws NoSuchPaddingException {
        String upperCase = Strings.toUpperCase(str);
        if (!upperCase.equals("NOPADDING") && !upperCase.equals("PKCS5PADDING") && !upperCase.equals("PKCS7PADDING")) {
            throw new NoSuchPaddingException("padding not available with IESCipher");
        }
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec parameterSpec;
        if (algorithmParameters != null) {
            try {
                parameterSpec = algorithmParameters.getParameterSpec(IESParameterSpec.class);
            } catch (Exception e) {
                throw new InvalidAlgorithmParameterException("cannot recognise parameters: " + e.toString());
            }
        } else {
            parameterSpec = null;
        }
        this.engineParam = algorithmParameters;
        engineInit(i, key, parameterSpec, secureRandom);
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException, InvalidKeyException {
        byte[] bArr = null;
        this.otherKeyParameter = null;
        if (algorithmParameterSpec == null) {
            int i2 = this.ivLength;
            if (i2 != 0 && i == 1) {
                bArr = new byte[i2];
                secureRandom.nextBytes(bArr);
            }
            this.engineSpec = IESUtil.guessParameterSpec(this.engine.getCipher(), bArr);
        } else if (algorithmParameterSpec instanceof IESParameterSpec) {
            this.engineSpec = (IESParameterSpec) algorithmParameterSpec;
        } else {
            throw new InvalidAlgorithmParameterException("must be passed IES parameters");
        }
        byte[] nonce = this.engineSpec.getNonce();
        int i3 = this.ivLength;
        if (i3 != 0 && (nonce == null || nonce.length != i3)) {
            throw new InvalidAlgorithmParameterException("NONCE in IES Parameters needs to be " + this.ivLength + " bytes long");
        }
        if (i == 1 || i == 3) {
            if (key instanceof PublicKey) {
                this.key = ECUtils.generatePublicKeyParameter((PublicKey) key);
            } else if (key instanceof IESKey) {
                IESKey iESKey = (IESKey) key;
                this.key = ECUtils.generatePublicKeyParameter(iESKey.getPublic());
                this.otherKeyParameter = ECUtil.generatePrivateKeyParameter(iESKey.getPrivate());
            } else {
                throw new InvalidKeyException("must be passed recipient's public EC key for encryption");
            }
        } else if (i == 2 || i == 4) {
            if (key instanceof PrivateKey) {
                this.key = ECUtil.generatePrivateKeyParameter((PrivateKey) key);
            } else if (key instanceof IESKey) {
                IESKey iESKey2 = (IESKey) key;
                this.otherKeyParameter = ECUtils.generatePublicKeyParameter(iESKey2.getPublic());
                this.key = ECUtil.generatePrivateKeyParameter(iESKey2.getPrivate());
            } else {
                throw new InvalidKeyException("must be passed recipient's private EC key for decryption");
            }
        } else {
            throw new InvalidKeyException("must be passed EC key");
        }
        this.random = secureRandom;
        this.state = i;
        this.buffer.reset();
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException("cannot handle supplied parameter spec: " + e.getMessage());
        }
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineUpdate(byte[] bArr, int i, int i2) {
        this.buffer.write(bArr, i, i2);
        return null;
    }

    @Override // javax.crypto.CipherSpi
    public int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.buffer.write(bArr, i, i2);
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineDoFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        if (i2 != 0) {
            this.buffer.write(bArr, i, i2);
        }
        byte[] byteArray = this.buffer.toByteArray();
        this.buffer.reset();
        CipherParameters iESWithCipherParameters = new IESWithCipherParameters(this.engineSpec.getDerivationV(), this.engineSpec.getEncodingV(), this.engineSpec.getMacKeySize(), this.engineSpec.getCipherKeySize());
        if (this.engineSpec.getNonce() != null) {
            iESWithCipherParameters = new ParametersWithIV(iESWithCipherParameters, this.engineSpec.getNonce());
        }
        ECDomainParameters parameters = ((ECKeyParameters) this.key).getParameters();
        AsymmetricKeyParameter asymmetricKeyParameter = this.otherKeyParameter;
        if (asymmetricKeyParameter != null) {
            try {
                int i3 = this.state;
                if (i3 != 1 && i3 != 3) {
                    this.engine.init(false, this.key, asymmetricKeyParameter, iESWithCipherParameters);
                    return this.engine.processBlock(byteArray, 0, byteArray.length);
                }
                this.engine.init(true, asymmetricKeyParameter, this.key, iESWithCipherParameters);
                return this.engine.processBlock(byteArray, 0, byteArray.length);
            } catch (Exception e) {
                throw new BadBlockException("unable to process block", e);
            }
        }
        int i4 = this.state;
        if (i4 != 1 && i4 != 3) {
            if (i4 == 2 || i4 == 4) {
                try {
                    this.engine.init(this.key, iESWithCipherParameters, new ECIESPublicKeyParser(parameters));
                    return this.engine.processBlock(byteArray, 0, byteArray.length);
                } catch (InvalidCipherTextException e2) {
                    throw new BadBlockException("unable to process block", e2);
                }
            }
            throw new IllegalStateException("cipher not initialised");
        }
        ECKeyPairGenerator eCKeyPairGenerator = new ECKeyPairGenerator();
        eCKeyPairGenerator.init(new ECKeyGenerationParameters(parameters, this.random));
        final boolean pointCompression = this.engineSpec.getPointCompression();
        try {
            this.engine.init(this.key, iESWithCipherParameters, new EphemeralKeyPairGenerator(eCKeyPairGenerator, new KeyEncoder() { // from class: org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher.1
                @Override // org.spongycastle.crypto.KeyEncoder
                public byte[] getEncoded(AsymmetricKeyParameter asymmetricKeyParameter2) {
                    return ((ECPublicKeyParameters) asymmetricKeyParameter2).getQ().getEncoded(pointCompression);
                }
            }));
            return this.engine.processBlock(byteArray, 0, byteArray.length);
        } catch (Exception e3) {
            throw new BadBlockException("unable to process block", e3);
        }
    }

    @Override // javax.crypto.CipherSpi
    public int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        byte[] engineDoFinal = engineDoFinal(bArr, i, i2);
        System.arraycopy(engineDoFinal, 0, bArr2, i3, engineDoFinal.length);
        return engineDoFinal.length;
    }

    /* loaded from: classes3.dex */
    public static class ECIES extends IESCipher {
        public ECIES() {
            super(new IESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA1()), new HMac(DigestFactory.createSHA1())));
        }
    }

    /* loaded from: classes3.dex */
    public static class ECIESwithCipher extends IESCipher {
        public ECIESwithCipher(BlockCipher blockCipher, int i) {
            super(new IESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA1()), new HMac(DigestFactory.createSHA1()), new PaddedBufferedBlockCipher(blockCipher)), i);
        }
    }

    /* loaded from: classes3.dex */
    public static class ECIESwithDESedeCBC extends ECIESwithCipher {
        public ECIESwithDESedeCBC() {
            super(new CBCBlockCipher(new DESedeEngine()), 8);
        }
    }

    /* loaded from: classes3.dex */
    public static class ECIESwithAESCBC extends ECIESwithCipher {
        public ECIESwithAESCBC() {
            super(new CBCBlockCipher(new AESEngine()), 16);
        }
    }
}
