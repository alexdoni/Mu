package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.SRP6GroupParameters;

/* loaded from: classes3.dex */
public class SRP6Server {

    /* renamed from: A */
    protected BigInteger f2110A;

    /* renamed from: B */
    protected BigInteger f2111B;
    protected BigInteger Key;

    /* renamed from: M1 */
    protected BigInteger f2112M1;

    /* renamed from: M2 */
    protected BigInteger f2113M2;

    /* renamed from: N */
    protected BigInteger f2114N;

    /* renamed from: S */
    protected BigInteger f2115S;

    /* renamed from: b */
    protected BigInteger f2116b;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f2117g;
    protected SecureRandom random;

    /* renamed from: u */
    protected BigInteger f2118u;

    /* renamed from: v */
    protected BigInteger f2119v;

    public void init(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest, SecureRandom secureRandom) {
        this.f2114N = bigInteger;
        this.f2117g = bigInteger2;
        this.f2119v = bigInteger3;
        this.random = secureRandom;
        this.digest = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, BigInteger bigInteger, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), bigInteger, digest, secureRandom);
    }

    public BigInteger generateServerCredentials() {
        BigInteger calculateK = SRP6Util.calculateK(this.digest, this.f2114N, this.f2117g);
        this.f2116b = selectPrivateValue();
        BigInteger mod = calculateK.multiply(this.f2119v).mod(this.f2114N).add(this.f2117g.modPow(this.f2116b, this.f2114N)).mod(this.f2114N);
        this.f2111B = mod;
        return mod;
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        BigInteger validatePublicValue = SRP6Util.validatePublicValue(this.f2114N, bigInteger);
        this.f2110A = validatePublicValue;
        this.f2118u = SRP6Util.calculateU(this.digest, this.f2114N, validatePublicValue, this.f2111B);
        BigInteger calculateS = calculateS();
        this.f2115S = calculateS;
        return calculateS;
    }

    protected BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f2114N, this.f2117g, this.random);
    }

    private BigInteger calculateS() {
        return this.f2119v.modPow(this.f2118u, this.f2114N).multiply(this.f2110A).mod(this.f2114N).modPow(this.f2116b, this.f2114N);
    }

    public boolean verifyClientEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f2110A;
        if (bigInteger4 == null || (bigInteger2 = this.f2111B) == null || (bigInteger3 = this.f2115S) == null) {
            throw new CryptoException("Impossible to compute and verify M1: some data are missing from the previous operations (A,B,S)");
        }
        if (!SRP6Util.calculateM1(this.digest, this.f2114N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            return false;
        }
        this.f2112M1 = bigInteger;
        return true;
    }

    public BigInteger calculateServerEvidenceMessage() throws CryptoException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f2110A;
        if (bigInteger3 == null || (bigInteger = this.f2112M1) == null || (bigInteger2 = this.f2115S) == null) {
            throw new CryptoException("Impossible to compute M2: some data are missing from the previous operations (A,M1,S)");
        }
        BigInteger calculateM2 = SRP6Util.calculateM2(this.digest, this.f2114N, bigInteger3, bigInteger, bigInteger2);
        this.f2113M2 = calculateM2;
        return calculateM2;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f2115S;
        if (bigInteger == null || this.f2112M1 == null || this.f2113M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        BigInteger calculateKey = SRP6Util.calculateKey(this.digest, this.f2114N, bigInteger);
        this.Key = calculateKey;
        return calculateKey;
    }
}
