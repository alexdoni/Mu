package io.jsonwebtoken.impl.crypto;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.RuntimeEnvironment;
import io.jsonwebtoken.security.SignatureException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class RsaProvider extends SignatureProvider {
    private static final Map<SignatureAlgorithm, PSSParameterSpec> PSS_PARAMETER_SPECS = createPssParameterSpecs();

    static {
        RuntimeEnvironment.enableBouncyCastleIfPossible();
    }

    private static Map<SignatureAlgorithm, PSSParameterSpec> createPssParameterSpecs() {
        HashMap hashMap = new HashMap();
        MGF1ParameterSpec mGF1ParameterSpec = MGF1ParameterSpec.SHA256;
        hashMap.put(SignatureAlgorithm.PS256, new PSSParameterSpec(mGF1ParameterSpec.getDigestAlgorithm(), "MGF1", mGF1ParameterSpec, 32, 1));
        MGF1ParameterSpec mGF1ParameterSpec2 = MGF1ParameterSpec.SHA384;
        hashMap.put(SignatureAlgorithm.PS384, new PSSParameterSpec(mGF1ParameterSpec2.getDigestAlgorithm(), "MGF1", mGF1ParameterSpec2, 48, 1));
        MGF1ParameterSpec mGF1ParameterSpec3 = MGF1ParameterSpec.SHA512;
        hashMap.put(SignatureAlgorithm.PS512, new PSSParameterSpec(mGF1ParameterSpec3.getDigestAlgorithm(), "MGF1", mGF1ParameterSpec3, 64, 1));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RsaProvider(SignatureAlgorithm signatureAlgorithm, Key key) {
        super(signatureAlgorithm, key);
        Assert.isTrue(signatureAlgorithm.isRsa(), "SignatureAlgorithm must be an RSASSA or RSASSA-PSS algorithm.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.jsonwebtoken.impl.crypto.SignatureProvider
    public Signature createSignatureInstance() {
        Signature createSignatureInstance = super.createSignatureInstance();
        PSSParameterSpec pSSParameterSpec = PSS_PARAMETER_SPECS.get(this.alg);
        if (pSSParameterSpec != null) {
            setParameter(createSignatureInstance, pSSParameterSpec);
        }
        return createSignatureInstance;
    }

    protected void setParameter(Signature signature, PSSParameterSpec pSSParameterSpec) {
        try {
            doSetParameter(signature, pSSParameterSpec);
        } catch (InvalidAlgorithmParameterException e) {
            throw new SignatureException("Unsupported RSASSA-PSS parameter '" + pSSParameterSpec + "': " + e.getMessage(), e);
        }
    }

    protected void doSetParameter(Signature signature, PSSParameterSpec pSSParameterSpec) throws InvalidAlgorithmParameterException {
        signature.setParameter(pSSParameterSpec);
    }

    public static KeyPair generateKeyPair() {
        return generateKeyPair(4096);
    }

    public static KeyPair generateKeyPair(int i) {
        return generateKeyPair(i, DEFAULT_SECURE_RANDOM);
    }

    public static KeyPair generateKeyPair(SignatureAlgorithm signatureAlgorithm) {
        Assert.isTrue(signatureAlgorithm.isRsa(), "Only RSA algorithms are supported by this method.");
        int i = C29141.$SwitchMap$io$jsonwebtoken$SignatureAlgorithm[signatureAlgorithm.ordinal()];
        return generateKeyPair((i == 1 || i == 2) ? 2048 : (i == 3 || i == 4) ? 3072 : 4096, DEFAULT_SECURE_RANDOM);
    }

    /* renamed from: io.jsonwebtoken.impl.crypto.RsaProvider$1 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class C29141 {
        static final /* synthetic */ int[] $SwitchMap$io$jsonwebtoken$SignatureAlgorithm;

        static {
            int[] iArr = new int[SignatureAlgorithm.values().length];
            $SwitchMap$io$jsonwebtoken$SignatureAlgorithm = iArr;
            try {
                iArr[SignatureAlgorithm.RS256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$jsonwebtoken$SignatureAlgorithm[SignatureAlgorithm.PS256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$jsonwebtoken$SignatureAlgorithm[SignatureAlgorithm.RS384.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$jsonwebtoken$SignatureAlgorithm[SignatureAlgorithm.PS384.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static KeyPair generateKeyPair(int i, SecureRandom secureRandom) {
        return generateKeyPair("RSA", i, secureRandom);
    }

    protected static KeyPair generateKeyPair(String str, int i, SecureRandom secureRandom) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(str);
            keyPairGenerator.initialize(i, secureRandom);
            return keyPairGenerator.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Unable to obtain an RSA KeyPairGenerator: " + e.getMessage(), e);
        }
    }
}
