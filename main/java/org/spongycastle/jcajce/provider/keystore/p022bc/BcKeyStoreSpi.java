package org.spongycastle.jcajce.provider.keystore.p022bc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.generators.PKCS12ParametersGenerator;
import org.spongycastle.crypto.io.DigestInputStream;
import org.spongycastle.crypto.io.DigestOutputStream;
import org.spongycastle.crypto.io.MacInputStream;
import org.spongycastle.crypto.io.MacOutputStream;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.interfaces.BCKeyStore;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.io.Streams;
import org.spongycastle.util.io.TeeOutputStream;

/* loaded from: classes3.dex */
public class BcKeyStoreSpi extends KeyStoreSpi implements BCKeyStore {
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    private static final String KEY_CIPHER = "PBEWithSHAAnd3-KeyTripleDES-CBC";
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    private static final int KEY_SALT_SIZE = 20;
    static final int KEY_SECRET = 2;
    private static final int MIN_ITERATIONS = 1024;
    static final int NULL = 0;
    static final int SEALED = 4;
    static final int SECRET = 3;
    private static final String STORE_CIPHER = "PBEWithSHAAndTwofish-CBC";
    private static final int STORE_SALT_SIZE = 20;
    private static final int STORE_VERSION = 2;
    protected int version;
    protected Hashtable table = new Hashtable();
    protected SecureRandom random = new SecureRandom();
    private final JcaJceHelper helper = new BCJcaJceHelper();

    public BcKeyStoreSpi(int i) {
        this.version = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class StoreEntry {
        String alias;
        Certificate[] certChain;
        Date date;
        Object obj;
        int type;

        StoreEntry(String str, Certificate certificate) {
            this.date = new Date();
            this.type = 1;
            this.alias = str;
            this.obj = certificate;
            this.certChain = null;
        }

        StoreEntry(String str, byte[] bArr, Certificate[] certificateArr) {
            this.date = new Date();
            this.type = 3;
            this.alias = str;
            this.obj = bArr;
            this.certChain = certificateArr;
        }

        StoreEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws Exception {
            this.date = new Date();
            this.type = 4;
            this.alias = str;
            this.certChain = certificateArr;
            byte[] bArr = new byte[20];
            BcKeyStoreSpi.this.random.setSeed(System.currentTimeMillis());
            BcKeyStoreSpi.this.random.nextBytes(bArr);
            int nextInt = (BcKeyStoreSpi.this.random.nextInt() & 1023) + 1024;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(20);
            dataOutputStream.write(bArr);
            dataOutputStream.writeInt(nextInt);
            DataOutputStream dataOutputStream2 = new DataOutputStream(new CipherOutputStream(dataOutputStream, BcKeyStoreSpi.this.makePBECipher(BcKeyStoreSpi.KEY_CIPHER, 1, cArr, bArr, nextInt)));
            BcKeyStoreSpi.this.encodeKey(key, dataOutputStream2);
            dataOutputStream2.close();
            this.obj = byteArrayOutputStream.toByteArray();
        }

        StoreEntry(String str, Date date, int i, Object obj) {
            new Date();
            this.alias = str;
            this.date = date;
            this.type = i;
            this.obj = obj;
        }

        StoreEntry(String str, Date date, int i, Object obj, Certificate[] certificateArr) {
            new Date();
            this.alias = str;
            this.date = date;
            this.type = i;
            this.obj = obj;
            this.certChain = certificateArr;
        }

        int getType() {
            return this.type;
        }

        String getAlias() {
            return this.alias;
        }

        Object getObject() {
            return this.obj;
        }

        Object getObject(char[] cArr) throws NoSuchAlgorithmException, UnrecoverableKeyException {
            Key decodeKey;
            if (cArr == null || cArr.length == 0) {
                Object obj = this.obj;
                if (obj instanceof Key) {
                    return obj;
                }
            }
            if (this.type == 4) {
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[]) this.obj));
                try {
                    byte[] bArr = new byte[dataInputStream.readInt()];
                    dataInputStream.readFully(bArr);
                    try {
                        return BcKeyStoreSpi.this.decodeKey(new DataInputStream(new CipherInputStream(dataInputStream, BcKeyStoreSpi.this.makePBECipher(BcKeyStoreSpi.KEY_CIPHER, 2, cArr, bArr, dataInputStream.readInt()))));
                    } catch (Exception unused) {
                        DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream((byte[]) this.obj));
                        byte[] bArr2 = new byte[dataInputStream2.readInt()];
                        dataInputStream2.readFully(bArr2);
                        int readInt = dataInputStream2.readInt();
                        try {
                            decodeKey = BcKeyStoreSpi.this.decodeKey(new DataInputStream(new CipherInputStream(dataInputStream2, BcKeyStoreSpi.this.makePBECipher("BrokenPBEWithSHAAnd3-KeyTripleDES-CBC", 2, cArr, bArr2, readInt))));
                        } catch (Exception unused2) {
                            DataInputStream dataInputStream3 = new DataInputStream(new ByteArrayInputStream((byte[]) this.obj));
                            bArr2 = new byte[dataInputStream3.readInt()];
                            dataInputStream3.readFully(bArr2);
                            readInt = dataInputStream3.readInt();
                            decodeKey = BcKeyStoreSpi.this.decodeKey(new DataInputStream(new CipherInputStream(dataInputStream3, BcKeyStoreSpi.this.makePBECipher("OldPBEWithSHAAnd3-KeyTripleDES-CBC", 2, cArr, bArr2, readInt))));
                        }
                        byte[] bArr3 = bArr2;
                        int i = readInt;
                        if (decodeKey != null) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                            dataOutputStream.writeInt(bArr3.length);
                            dataOutputStream.write(bArr3);
                            dataOutputStream.writeInt(i);
                            DataOutputStream dataOutputStream2 = new DataOutputStream(new CipherOutputStream(dataOutputStream, BcKeyStoreSpi.this.makePBECipher(BcKeyStoreSpi.KEY_CIPHER, 1, cArr, bArr3, i)));
                            BcKeyStoreSpi.this.encodeKey(decodeKey, dataOutputStream2);
                            dataOutputStream2.close();
                            this.obj = byteArrayOutputStream.toByteArray();
                            return decodeKey;
                        }
                        throw new UnrecoverableKeyException("no match");
                    }
                } catch (Exception unused3) {
                    throw new UnrecoverableKeyException("no match");
                }
            }
            throw new RuntimeException("forget something!");
        }

        Certificate[] getCertificateChain() {
            return this.certChain;
        }

        Date getDate() {
            return this.date;
        }
    }

    private void encodeCertificate(Certificate certificate, DataOutputStream dataOutputStream) throws IOException {
        try {
            byte[] encoded = certificate.getEncoded();
            dataOutputStream.writeUTF(certificate.getType());
            dataOutputStream.writeInt(encoded.length);
            dataOutputStream.write(encoded);
        } catch (CertificateEncodingException e) {
            throw new IOException(e.toString());
        }
    }

    private Certificate decodeCertificate(DataInputStream dataInputStream) throws IOException {
        String readUTF = dataInputStream.readUTF();
        byte[] bArr = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(bArr);
        try {
            return this.helper.createCertificateFactory(readUTF).generateCertificate(new ByteArrayInputStream(bArr));
        } catch (NoSuchProviderException e) {
            throw new IOException(e.toString());
        } catch (CertificateException e2) {
            throw new IOException(e2.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void encodeKey(Key key, DataOutputStream dataOutputStream) throws IOException {
        byte[] encoded = key.getEncoded();
        if (key instanceof PrivateKey) {
            dataOutputStream.write(0);
        } else if (key instanceof PublicKey) {
            dataOutputStream.write(1);
        } else {
            dataOutputStream.write(2);
        }
        dataOutputStream.writeUTF(key.getFormat());
        dataOutputStream.writeUTF(key.getAlgorithm());
        dataOutputStream.writeInt(encoded.length);
        dataOutputStream.write(encoded);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Key decodeKey(DataInputStream dataInputStream) throws IOException {
        KeySpec pKCS8EncodedKeySpec;
        int read = dataInputStream.read();
        String readUTF = dataInputStream.readUTF();
        String readUTF2 = dataInputStream.readUTF();
        byte[] bArr = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(bArr);
        if (readUTF.equals("PKCS#8") || readUTF.equals("PKCS8")) {
            pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(bArr);
        } else if (readUTF.equals("X.509") || readUTF.equals("X509")) {
            pKCS8EncodedKeySpec = new X509EncodedKeySpec(bArr);
        } else {
            if (readUTF.equals("RAW")) {
                return new SecretKeySpec(bArr, readUTF2);
            }
            throw new IOException("Key format " + readUTF + " not recognised!");
        }
        try {
            if (read == 0) {
                return this.helper.createKeyFactory(readUTF2).generatePrivate(pKCS8EncodedKeySpec);
            }
            if (read == 1) {
                return this.helper.createKeyFactory(readUTF2).generatePublic(pKCS8EncodedKeySpec);
            }
            if (read == 2) {
                return this.helper.createSecretKeyFactory(readUTF2).generateSecret(pKCS8EncodedKeySpec);
            }
            throw new IOException("Key type " + read + " not recognised!");
        } catch (Exception e) {
            throw new IOException("Exception creating key: " + e.toString());
        }
    }

    protected Cipher makePBECipher(String str, int i, char[] cArr, byte[] bArr, int i2) throws IOException {
        try {
            PBEKeySpec pBEKeySpec = new PBEKeySpec(cArr);
            SecretKeyFactory createSecretKeyFactory = this.helper.createSecretKeyFactory(str);
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(bArr, i2);
            Cipher createCipher = this.helper.createCipher(str);
            createCipher.init(i, createSecretKeyFactory.generateSecret(pBEKeySpec), pBEParameterSpec);
            return createCipher;
        } catch (Exception e) {
            throw new IOException("Error initialising store of key store: " + e);
        }
    }

    @Override // org.spongycastle.jce.interfaces.BCKeyStore
    public void setRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    @Override // java.security.KeyStoreSpi
    public Enumeration engineAliases() {
        return this.table.keys();
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineContainsAlias(String str) {
        return this.table.get(str) != null;
    }

    @Override // java.security.KeyStoreSpi
    public void engineDeleteEntry(String str) throws KeyStoreException {
        if (this.table.get(str) == null) {
            return;
        }
        this.table.remove(str);
    }

    @Override // java.security.KeyStoreSpi
    public Certificate engineGetCertificate(String str) {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        if (storeEntry == null) {
            return null;
        }
        if (storeEntry.getType() == 1) {
            return (Certificate) storeEntry.getObject();
        }
        Certificate[] certificateChain = storeEntry.getCertificateChain();
        if (certificateChain != null) {
            return certificateChain[0];
        }
        return null;
    }

    @Override // java.security.KeyStoreSpi
    public String engineGetCertificateAlias(Certificate certificate) {
        Enumeration elements = this.table.elements();
        while (elements.hasMoreElements()) {
            StoreEntry storeEntry = (StoreEntry) elements.nextElement();
            if (storeEntry.getObject() instanceof Certificate) {
                if (((Certificate) storeEntry.getObject()).equals(certificate)) {
                    return storeEntry.getAlias();
                }
            } else {
                Certificate[] certificateChain = storeEntry.getCertificateChain();
                if (certificateChain != null && certificateChain[0].equals(certificate)) {
                    return storeEntry.getAlias();
                }
            }
        }
        return null;
    }

    @Override // java.security.KeyStoreSpi
    public Certificate[] engineGetCertificateChain(String str) {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        if (storeEntry != null) {
            return storeEntry.getCertificateChain();
        }
        return null;
    }

    @Override // java.security.KeyStoreSpi
    public Date engineGetCreationDate(String str) {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        if (storeEntry != null) {
            return storeEntry.getDate();
        }
        return null;
    }

    @Override // java.security.KeyStoreSpi
    public Key engineGetKey(String str, char[] cArr) throws NoSuchAlgorithmException, UnrecoverableKeyException {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        if (storeEntry == null || storeEntry.getType() == 1) {
            return null;
        }
        return (Key) storeEntry.getObject(cArr);
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineIsCertificateEntry(String str) {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        return storeEntry != null && storeEntry.getType() == 1;
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineIsKeyEntry(String str) {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        return (storeEntry == null || storeEntry.getType() == 1) ? false : true;
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetCertificateEntry(String str, Certificate certificate) throws KeyStoreException {
        StoreEntry storeEntry = (StoreEntry) this.table.get(str);
        if (storeEntry != null && storeEntry.getType() != 1) {
            throw new KeyStoreException("key store already has a key entry with alias " + str);
        }
        this.table.put(str, new StoreEntry(str, certificate));
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) throws KeyStoreException {
        this.table.put(str, new StoreEntry(str, bArr, certificateArr));
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws KeyStoreException {
        if ((key instanceof PrivateKey) && certificateArr == null) {
            throw new KeyStoreException("no certificate chain for private key");
        }
        try {
            this.table.put(str, new StoreEntry(str, key, cArr, certificateArr));
        } catch (Exception e) {
            throw new KeyStoreException(e.toString());
        }
    }

    @Override // java.security.KeyStoreSpi
    public int engineSize() {
        return this.table.size();
    }

    protected void loadStore(InputStream inputStream) throws IOException {
        Certificate[] certificateArr;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        for (int read = dataInputStream.read(); read > 0; read = dataInputStream.read()) {
            String readUTF = dataInputStream.readUTF();
            Date date = new Date(dataInputStream.readLong());
            int readInt = dataInputStream.readInt();
            if (readInt != 0) {
                Certificate[] certificateArr2 = new Certificate[readInt];
                for (int i = 0; i != readInt; i++) {
                    certificateArr2[i] = decodeCertificate(dataInputStream);
                }
                certificateArr = certificateArr2;
            } else {
                certificateArr = null;
            }
            if (read == 1) {
                this.table.put(readUTF, new StoreEntry(readUTF, date, 1, decodeCertificate(dataInputStream)));
            } else if (read == 2) {
                this.table.put(readUTF, new StoreEntry(readUTF, date, 2, decodeKey(dataInputStream), certificateArr));
            } else if (read == 3 || read == 4) {
                byte[] bArr = new byte[dataInputStream.readInt()];
                dataInputStream.readFully(bArr);
                this.table.put(readUTF, new StoreEntry(readUTF, date, read, bArr, certificateArr));
            } else {
                throw new RuntimeException("Unknown object type in store.");
            }
        }
    }

    protected void saveStore(OutputStream outputStream) throws IOException {
        Enumeration elements = this.table.elements();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        while (true) {
            if (elements.hasMoreElements()) {
                StoreEntry storeEntry = (StoreEntry) elements.nextElement();
                dataOutputStream.write(storeEntry.getType());
                dataOutputStream.writeUTF(storeEntry.getAlias());
                dataOutputStream.writeLong(storeEntry.getDate().getTime());
                Certificate[] certificateChain = storeEntry.getCertificateChain();
                if (certificateChain == null) {
                    dataOutputStream.writeInt(0);
                } else {
                    dataOutputStream.writeInt(certificateChain.length);
                    for (int i = 0; i != certificateChain.length; i++) {
                        encodeCertificate(certificateChain[i], dataOutputStream);
                    }
                }
                int type = storeEntry.getType();
                if (type == 1) {
                    encodeCertificate((Certificate) storeEntry.getObject(), dataOutputStream);
                } else if (type == 2) {
                    encodeKey((Key) storeEntry.getObject(), dataOutputStream);
                } else if (type == 3 || type == 4) {
                    byte[] bArr = (byte[]) storeEntry.getObject();
                    dataOutputStream.writeInt(bArr.length);
                    dataOutputStream.write(bArr);
                } else {
                    throw new RuntimeException("Unknown object type in store.");
                }
            } else {
                dataOutputStream.write(0);
                return;
            }
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineLoad(InputStream inputStream, char[] cArr) throws IOException {
        CipherParameters generateDerivedMacParameters;
        this.table.clear();
        if (inputStream == null) {
            return;
        }
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int readInt = dataInputStream.readInt();
        if (readInt != 2 && readInt != 0 && readInt != 1) {
            throw new IOException("Wrong version of key store.");
        }
        int readInt2 = dataInputStream.readInt();
        if (readInt2 <= 0) {
            throw new IOException("Invalid salt detected");
        }
        byte[] bArr = new byte[readInt2];
        dataInputStream.readFully(bArr);
        int readInt3 = dataInputStream.readInt();
        HMac hMac = new HMac(new SHA1Digest());
        if (cArr != null && cArr.length != 0) {
            byte[] PKCS12PasswordToBytes = PBEParametersGenerator.PKCS12PasswordToBytes(cArr);
            PKCS12ParametersGenerator pKCS12ParametersGenerator = new PKCS12ParametersGenerator(new SHA1Digest());
            pKCS12ParametersGenerator.init(PKCS12PasswordToBytes, bArr, readInt3);
            if (readInt != 2) {
                generateDerivedMacParameters = pKCS12ParametersGenerator.generateDerivedMacParameters(hMac.getMacSize());
            } else {
                generateDerivedMacParameters = pKCS12ParametersGenerator.generateDerivedMacParameters(hMac.getMacSize() * 8);
            }
            Arrays.fill(PKCS12PasswordToBytes, (byte) 0);
            hMac.init(generateDerivedMacParameters);
            loadStore(new MacInputStream(dataInputStream, hMac));
            byte[] bArr2 = new byte[hMac.getMacSize()];
            hMac.doFinal(bArr2, 0);
            byte[] bArr3 = new byte[hMac.getMacSize()];
            dataInputStream.readFully(bArr3);
            if (Arrays.constantTimeAreEqual(bArr2, bArr3)) {
                return;
            }
            this.table.clear();
            throw new IOException("KeyStore integrity check failed.");
        }
        loadStore(dataInputStream);
        dataInputStream.readFully(new byte[hMac.getMacSize()]);
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(OutputStream outputStream, char[] cArr) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        byte[] bArr = new byte[20];
        int nextInt = (this.random.nextInt() & 1023) + 1024;
        this.random.nextBytes(bArr);
        dataOutputStream.writeInt(this.version);
        dataOutputStream.writeInt(20);
        dataOutputStream.write(bArr);
        dataOutputStream.writeInt(nextInt);
        HMac hMac = new HMac(new SHA1Digest());
        MacOutputStream macOutputStream = new MacOutputStream(hMac);
        PKCS12ParametersGenerator pKCS12ParametersGenerator = new PKCS12ParametersGenerator(new SHA1Digest());
        byte[] PKCS12PasswordToBytes = PBEParametersGenerator.PKCS12PasswordToBytes(cArr);
        pKCS12ParametersGenerator.init(PKCS12PasswordToBytes, bArr, nextInt);
        if (this.version < 2) {
            hMac.init(pKCS12ParametersGenerator.generateDerivedMacParameters(hMac.getMacSize()));
        } else {
            hMac.init(pKCS12ParametersGenerator.generateDerivedMacParameters(hMac.getMacSize() * 8));
        }
        for (int i = 0; i != PKCS12PasswordToBytes.length; i++) {
            PKCS12PasswordToBytes[i] = 0;
        }
        saveStore(new TeeOutputStream(dataOutputStream, macOutputStream));
        byte[] bArr2 = new byte[hMac.getMacSize()];
        hMac.doFinal(bArr2, 0);
        dataOutputStream.write(bArr2);
        dataOutputStream.close();
    }

    /* loaded from: classes3.dex */
    public static class BouncyCastleStore extends BcKeyStoreSpi {
        public BouncyCastleStore() {
            super(1);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.p022bc.BcKeyStoreSpi, java.security.KeyStoreSpi
        public void engineLoad(InputStream inputStream, char[] cArr) throws IOException {
            this.table.clear();
            if (inputStream == null) {
                return;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int readInt = dataInputStream.readInt();
            if (readInt != 2 && readInt != 0 && readInt != 1) {
                throw new IOException("Wrong version of key store.");
            }
            int readInt2 = dataInputStream.readInt();
            byte[] bArr = new byte[readInt2];
            if (readInt2 != 20) {
                throw new IOException("Key store corrupted.");
            }
            dataInputStream.readFully(bArr);
            int readInt3 = dataInputStream.readInt();
            if (readInt3 < 0 || readInt3 > 65536) {
                throw new IOException("Key store corrupted.");
            }
            CipherInputStream cipherInputStream = new CipherInputStream(dataInputStream, makePBECipher(readInt == 0 ? "OldPBEWithSHAAndTwofish-CBC" : BcKeyStoreSpi.STORE_CIPHER, 2, cArr, bArr, readInt3));
            SHA1Digest sHA1Digest = new SHA1Digest();
            loadStore(new DigestInputStream(cipherInputStream, sHA1Digest));
            byte[] bArr2 = new byte[sHA1Digest.getDigestSize()];
            sHA1Digest.doFinal(bArr2, 0);
            byte[] bArr3 = new byte[sHA1Digest.getDigestSize()];
            Streams.readFully(cipherInputStream, bArr3);
            if (Arrays.constantTimeAreEqual(bArr2, bArr3)) {
                return;
            }
            this.table.clear();
            throw new IOException("KeyStore integrity check failed.");
        }

        @Override // org.spongycastle.jcajce.provider.keystore.p022bc.BcKeyStoreSpi, java.security.KeyStoreSpi
        public void engineStore(OutputStream outputStream, char[] cArr) throws IOException {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            byte[] bArr = new byte[20];
            int nextInt = (this.random.nextInt() & 1023) + 1024;
            this.random.nextBytes(bArr);
            dataOutputStream.writeInt(this.version);
            dataOutputStream.writeInt(20);
            dataOutputStream.write(bArr);
            dataOutputStream.writeInt(nextInt);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(dataOutputStream, makePBECipher(BcKeyStoreSpi.STORE_CIPHER, 1, cArr, bArr, nextInt));
            DigestOutputStream digestOutputStream = new DigestOutputStream(new SHA1Digest());
            saveStore(new TeeOutputStream(cipherOutputStream, digestOutputStream));
            cipherOutputStream.write(digestOutputStream.getDigest());
            cipherOutputStream.close();
        }
    }

    static Provider getBouncyCastleProvider() {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) != null) {
            return Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        }
        return new BouncyCastleProvider();
    }

    /* loaded from: classes3.dex */
    public static class Std extends BcKeyStoreSpi {
        public Std() {
            super(2);
        }
    }

    /* loaded from: classes3.dex */
    public static class Version1 extends BcKeyStoreSpi {
        public Version1() {
            super(1);
        }
    }
}
