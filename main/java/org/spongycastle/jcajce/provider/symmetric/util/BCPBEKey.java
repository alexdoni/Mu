package org.spongycastle.jcajce.provider.symmetric.util;

import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

/* loaded from: classes3.dex */
public class BCPBEKey implements PBEKey {
    String algorithm;
    int digest;
    int ivSize;
    int keySize;
    ASN1ObjectIdentifier oid;
    CipherParameters param;
    PBEKeySpec pbeKeySpec;
    boolean tryWrong = false;
    int type;

    @Override // java.security.Key
    public String getFormat() {
        return "RAW";
    }

    public BCPBEKey(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, int i2, int i3, int i4, PBEKeySpec pBEKeySpec, CipherParameters cipherParameters) {
        this.algorithm = str;
        this.oid = aSN1ObjectIdentifier;
        this.type = i;
        this.digest = i2;
        this.keySize = i3;
        this.ivSize = i4;
        this.pbeKeySpec = pBEKeySpec;
        this.param = cipherParameters;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        KeyParameter keyParameter;
        CipherParameters cipherParameters = this.param;
        if (cipherParameters != null) {
            if (cipherParameters instanceof ParametersWithIV) {
                keyParameter = (KeyParameter) ((ParametersWithIV) cipherParameters).getParameters();
            } else {
                keyParameter = (KeyParameter) cipherParameters;
            }
            return keyParameter.getKey();
        }
        int i = this.type;
        if (i == 2) {
            return PBEParametersGenerator.PKCS12PasswordToBytes(this.pbeKeySpec.getPassword());
        }
        if (i == 5) {
            return PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(this.pbeKeySpec.getPassword());
        }
        return PBEParametersGenerator.PKCS5PasswordToBytes(this.pbeKeySpec.getPassword());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getType() {
        return this.type;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDigest() {
        return this.digest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getKeySize() {
        return this.keySize;
    }

    public int getIvSize() {
        return this.ivSize;
    }

    public CipherParameters getParam() {
        return this.param;
    }

    @Override // javax.crypto.interfaces.PBEKey
    public char[] getPassword() {
        return this.pbeKeySpec.getPassword();
    }

    @Override // javax.crypto.interfaces.PBEKey
    public byte[] getSalt() {
        return this.pbeKeySpec.getSalt();
    }

    @Override // javax.crypto.interfaces.PBEKey
    public int getIterationCount() {
        return this.pbeKeySpec.getIterationCount();
    }

    public ASN1ObjectIdentifier getOID() {
        return this.oid;
    }

    public void setTryWrongPKCS12Zero(boolean z) {
        this.tryWrong = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTryWrongPKCS12() {
        return this.tryWrong;
    }
}
