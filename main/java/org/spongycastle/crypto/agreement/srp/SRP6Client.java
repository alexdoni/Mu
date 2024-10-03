package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.SRP6GroupParameters;

/* loaded from: classes3.dex */
public class SRP6Client {

    /* renamed from: A */
    protected BigInteger f2100A;

    /* renamed from: B */
    protected BigInteger f2101B;
    protected BigInteger Key;

    /* renamed from: M1 */
    protected BigInteger f2102M1;

    /* renamed from: M2 */
    protected BigInteger f2103M2;

    /* renamed from: N */
    protected BigInteger f2104N;

    /* renamed from: S */
    protected BigInteger f2105S;

    /* renamed from: a */
    protected BigInteger f2106a;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f2107g;
    protected SecureRandom random;

    /* renamed from: u */
    protected BigInteger f2108u;

    /* renamed from: x */
    protected BigInteger f2109x;

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest, SecureRandom secureRandom) {
        this.f2104N = bigInteger;
        this.f2107g = bigInteger2;
        this.digest = digest;
        this.random = secureRandom;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), digest, secureRandom);
    }

    public BigInteger generateClientCredentials(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.f2109x = SRP6Util.calculateX(this.digest, this.f2104N, bArr, bArr2, bArr3);
        BigInteger selectPrivateValue = selectPrivateValue();
        this.f2106a = selectPrivateValue;
        BigInteger modPow = this.f2107g.modPow(selectPrivateValue, this.f2104N);
        this.f2100A = modPow;
        return modPow;
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        BigInteger validatePublicValue = SRP6Util.validatePublicValue(this.f2104N, bigInteger);
        this.f2101B = validatePublicValue;
        this.f2108u = SRP6Util.calculateU(this.digest, this.f2104N, this.f2100A, validatePublicValue);
        BigInteger calculateS = calculateS();
        this.f2105S = calculateS;
        return calculateS;
    }

    protected BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f2104N, this.f2107g, this.random);
    }

    private BigInteger calculateS() {
        BigInteger calculateK = SRP6Util.calculateK(this.digest, this.f2104N, this.f2107g);
        return this.f2101B.subtract(this.f2107g.modPow(this.f2109x, this.f2104N).multiply(calculateK).mod(this.f2104N)).mod(this.f2104N).modPow(this.f2108u.multiply(this.f2109x).add(this.f2106a), this.f2104N);
    }

    public BigInteger calculateClientEvidenceMessage() throws CryptoException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f2100A;
        if (bigInteger3 == null || (bigInteger = this.f2101B) == null || (bigInteger2 = this.f2105S) == null) {
            throw new CryptoException("Impossible to compute M1: some data are missing from the previous operations (A,B,S)");
        }
        BigInteger calculateM1 = SRP6Util.calculateM1(this.digest, this.f2104N, bigInteger3, bigInteger, bigInteger2);
        this.f2102M1 = calculateM1;
        return calculateM1;
    }

    public boolean verifyServerEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f2100A;
        if (bigInteger4 == null || (bigInteger2 = this.f2102M1) == null || (bigInteger3 = this.f2105S) == null) {
            throw new CryptoException("Impossible to compute and verify M2: some data are missing from the previous operations (A,M1,S)");
        }
        if (!SRP6Util.calculateM2(this.digest, this.f2104N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            return false;
        }
        this.f2103M2 = bigInteger;
        return true;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f2105S;
        if (bigInteger == null || this.f2102M1 == null || this.f2103M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        BigInteger calculateKey = SRP6Util.calculateKey(this.digest, this.f2104N, bigInteger);
        this.Key = calculateKey;
        return calculateKey;
    }
}
