package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

/* loaded from: classes3.dex */
public class McElieceCCA2KeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2";
    private int fieldPoly;
    private boolean initialized = false;

    /* renamed from: m */
    private int f2752m;
    private McElieceCCA2KeyGenerationParameters mcElieceCCA2Params;

    /* renamed from: n */
    private int f2753n;
    private SecureRandom random;

    /* renamed from: t */
    private int f2754t;

    private void initializeDefault() {
        init(new McElieceCCA2KeyGenerationParameters(new SecureRandom(), new McElieceCCA2Parameters()));
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.mcElieceCCA2Params = (McElieceCCA2KeyGenerationParameters) keyGenerationParameters;
        this.random = new SecureRandom();
        this.f2752m = this.mcElieceCCA2Params.getParameters().getM();
        this.f2753n = this.mcElieceCCA2Params.getParameters().getN();
        this.f2754t = this.mcElieceCCA2Params.getParameters().getT();
        this.fieldPoly = this.mcElieceCCA2Params.getParameters().getFieldPoly();
        this.initialized = true;
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        GF2mField gF2mField = new GF2mField(this.f2752m, this.fieldPoly);
        PolynomialGF2mSmallM polynomialGF2mSmallM = new PolynomialGF2mSmallM(gF2mField, this.f2754t, 'I', this.random);
        GoppaCode.MaMaPe computeSystematicForm = GoppaCode.computeSystematicForm(GoppaCode.createCanonicalCheckMatrix(gF2mField, polynomialGF2mSmallM), this.random);
        GF2Matrix secondMatrix = computeSystematicForm.getSecondMatrix();
        Permutation permutation = computeSystematicForm.getPermutation();
        GF2Matrix gF2Matrix = (GF2Matrix) secondMatrix.computeTranspose();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new McElieceCCA2PublicKeyParameters(this.f2753n, this.f2754t, gF2Matrix, this.mcElieceCCA2Params.getParameters().getDigest()), (AsymmetricKeyParameter) new McElieceCCA2PrivateKeyParameters(this.f2753n, gF2Matrix.getNumRows(), gF2mField, polynomialGF2mSmallM, permutation, this.mcElieceCCA2Params.getParameters().getDigest()));
    }
}
