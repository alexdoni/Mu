package org.spongycastle.pqc.crypto.ntru;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.ntru.NTRUSigningPrivateKeyParameters;
import org.spongycastle.pqc.math.ntru.euclid.BigIntEuclidean;
import org.spongycastle.pqc.math.ntru.polynomial.BigDecimalPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Resultant;

/* loaded from: classes3.dex */
public class NTRUSigningKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NTRUSigningKeyGenerationParameters params;

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.params = (NTRUSigningKeyGenerationParameters) keyGenerationParameters;
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters;
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList arrayList = new ArrayList();
        int i = this.params.f2814B;
        while (true) {
            nTRUSigningPublicKeyParameters = null;
            byte b = 0;
            if (i < 0) {
                break;
            }
            arrayList.add(newCachedThreadPool.submit(new BasisGenerationTask()));
            i--;
        }
        newCachedThreadPool.shutdown();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = this.params.f2814B; i2 >= 0; i2--) {
            Future future = (Future) arrayList.get(i2);
            try {
                arrayList2.add(future.get());
                if (i2 == this.params.f2814B) {
                    nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(((NTRUSigningPrivateKeyParameters.Basis) future.get()).f2831h, this.params.getSigningParameters());
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) nTRUSigningPublicKeyParameters, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters(arrayList2, nTRUSigningPublicKeyParameters));
    }

    public AsymmetricCipherKeyPair generateKeyPairSingleThread() {
        ArrayList arrayList = new ArrayList();
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters = null;
        for (int i = this.params.f2814B; i >= 0; i--) {
            NTRUSigningPrivateKeyParameters.Basis generateBoundedBasis = generateBoundedBasis();
            arrayList.add(generateBoundedBasis);
            if (i == 0) {
                nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(generateBoundedBasis.f2831h, this.params.getSigningParameters());
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) nTRUSigningPublicKeyParameters, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters(arrayList, nTRUSigningPublicKeyParameters));
    }

    private void minimizeFG(IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, IntegerPolynomial integerPolynomial4, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += i * 2 * ((integerPolynomial.coeffs[i3] * integerPolynomial.coeffs[i3]) + (integerPolynomial2.coeffs[i3] * integerPolynomial2.coeffs[i3]));
        }
        int i4 = i2 - 4;
        IntegerPolynomial integerPolynomial5 = (IntegerPolynomial) integerPolynomial.clone();
        IntegerPolynomial integerPolynomial6 = (IntegerPolynomial) integerPolynomial2.clone();
        int i5 = 0;
        int i6 = 0;
        while (i5 < i && i6 < i) {
            int i7 = 0;
            for (int i8 = 0; i8 < i; i8++) {
                i7 += i * 4 * ((integerPolynomial3.coeffs[i8] * integerPolynomial.coeffs[i8]) + (integerPolynomial4.coeffs[i8] * integerPolynomial2.coeffs[i8]));
            }
            int sumCoeffs = i7 - ((integerPolynomial3.sumCoeffs() + integerPolynomial4.sumCoeffs()) * 4);
            if (sumCoeffs > i4) {
                integerPolynomial3.sub(integerPolynomial5);
                integerPolynomial4.sub(integerPolynomial6);
            } else if (sumCoeffs < (-i4)) {
                integerPolynomial3.add(integerPolynomial5);
                integerPolynomial4.add(integerPolynomial6);
            } else {
                i6++;
                integerPolynomial5.rotate1();
                integerPolynomial6.rotate1();
            }
            i5++;
            i6 = 0;
            i6++;
            integerPolynomial5.rotate1();
            integerPolynomial6.rotate1();
        }
    }

    private FGBasis generateBasis() {
        boolean z;
        Polynomial generateRandom;
        Polynomial polynomial;
        IntegerPolynomial integerPolynomial;
        IntegerPolynomial invertFq;
        Resultant resultant;
        int i;
        IntegerPolynomial integerPolynomial2;
        int i2;
        IntegerPolynomial integerPolynomial3;
        int i3;
        Polynomial polynomial2;
        Polynomial generateRandom2;
        Polynomial polynomial3;
        IntegerPolynomial integerPolynomial4;
        Resultant resultant2;
        BigIntEuclidean calculate;
        BigIntPolynomial round;
        IntegerPolynomial mult;
        Polynomial polynomial4;
        int i4 = this.params.f2815N;
        int i5 = this.params.f2820q;
        int i6 = this.params.f2816d;
        int i7 = this.params.f2817d1;
        int i8 = this.params.f2818d2;
        int i9 = this.params.f2819d3;
        int i10 = this.params.basisType;
        int i11 = (i4 * 2) + 1;
        boolean z2 = this.params.primeCheck;
        while (true) {
            if (this.params.polyType == 0) {
                generateRandom = DenseTernaryPolynomial.generateRandom(i4, i6 + 1, i6, new SecureRandom());
                z = z2;
            } else {
                z = z2;
                generateRandom = ProductFormPolynomial.generateRandom(i4, i7, i8, i9 + 1, i9, new SecureRandom());
            }
            polynomial = generateRandom;
            integerPolynomial = polynomial.toIntegerPolynomial();
            if ((!z || !integerPolynomial.resultant(i11).res.equals(BigInteger.ZERO)) && (invertFq = integerPolynomial.invertFq(i5)) != null) {
                break;
            }
            z2 = z;
        }
        Resultant resultant3 = integerPolynomial.resultant();
        while (true) {
            if (this.params.polyType == 0) {
                generateRandom2 = DenseTernaryPolynomial.generateRandom(i4, i6 + 1, i6, new SecureRandom());
                resultant = resultant3;
                i = i7;
                i2 = i8;
                i3 = i9;
                integerPolynomial2 = invertFq;
                integerPolynomial3 = integerPolynomial;
                polynomial2 = polynomial;
            } else {
                int i12 = i7;
                resultant = resultant3;
                int i13 = i8;
                i = i7;
                integerPolynomial2 = invertFq;
                i2 = i8;
                integerPolynomial3 = integerPolynomial;
                int i14 = i9;
                i3 = i9;
                polynomial2 = polynomial;
                generateRandom2 = ProductFormPolynomial.generateRandom(i4, i12, i13, i9 + 1, i14, new SecureRandom());
            }
            polynomial3 = generateRandom2;
            integerPolynomial4 = polynomial3.toIntegerPolynomial();
            if (z && integerPolynomial4.resultant(i11).res.equals(BigInteger.ZERO)) {
                invertFq = integerPolynomial2;
                integerPolynomial = integerPolynomial3;
                polynomial = polynomial2;
                resultant3 = resultant;
                i8 = i2;
                i7 = i;
                i9 = i3;
            } else {
                if (integerPolynomial4.invertFq(i5) != null) {
                    resultant2 = integerPolynomial4.resultant();
                    calculate = BigIntEuclidean.calculate(resultant.res, resultant2.res);
                    if (calculate.gcd.equals(BigInteger.ONE)) {
                        break;
                    }
                }
                invertFq = integerPolynomial2;
                integerPolynomial = integerPolynomial3;
                polynomial = polynomial2;
                resultant3 = resultant;
                i8 = i2;
                i7 = i;
                i9 = i3;
            }
        }
        BigIntPolynomial bigIntPolynomial = (BigIntPolynomial) resultant.rho.clone();
        Resultant resultant4 = resultant;
        bigIntPolynomial.mult(calculate.f2873x.multiply(BigInteger.valueOf(i5)));
        BigIntPolynomial bigIntPolynomial2 = (BigIntPolynomial) resultant2.rho.clone();
        bigIntPolynomial2.mult(calculate.f2874y.multiply(BigInteger.valueOf(-i5)));
        int i15 = 0;
        if (this.params.keyGenAlg == 0) {
            int[] iArr = new int[i4];
            int[] iArr2 = new int[i4];
            iArr[0] = integerPolynomial3.coeffs[0];
            iArr2[0] = integerPolynomial4.coeffs[0];
            for (int i16 = 1; i16 < i4; i16++) {
                int i17 = i4 - i16;
                iArr[i16] = integerPolynomial3.coeffs[i17];
                iArr2[i16] = integerPolynomial4.coeffs[i17];
            }
            IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(iArr);
            IntegerPolynomial integerPolynomial6 = new IntegerPolynomial(iArr2);
            IntegerPolynomial mult2 = polynomial2.mult(integerPolynomial5);
            mult2.add(polynomial3.mult(integerPolynomial6));
            Resultant resultant5 = mult2.resultant();
            BigIntPolynomial mult3 = integerPolynomial5.mult(bigIntPolynomial2);
            mult3.add(integerPolynomial6.mult(bigIntPolynomial));
            round = mult3.mult(resultant5.rho);
            round.div(resultant5.res);
        } else {
            for (int i18 = 1; i18 < i4; i18 *= 10) {
                i15++;
            }
            BigDecimalPolynomial div = resultant4.rho.div(new BigDecimal(resultant4.res), bigIntPolynomial2.getMaxCoeffLength() + 1 + i15);
            BigDecimalPolynomial div2 = resultant2.rho.div(new BigDecimal(resultant2.res), bigIntPolynomial.getMaxCoeffLength() + 1 + i15);
            BigDecimalPolynomial mult4 = div.mult(bigIntPolynomial2);
            mult4.add(div2.mult(bigIntPolynomial));
            mult4.halve();
            round = mult4.round();
        }
        BigIntPolynomial bigIntPolynomial3 = (BigIntPolynomial) bigIntPolynomial2.clone();
        bigIntPolynomial3.sub(polynomial2.mult(round));
        BigIntPolynomial bigIntPolynomial4 = (BigIntPolynomial) bigIntPolynomial.clone();
        bigIntPolynomial4.sub(polynomial3.mult(round));
        IntegerPolynomial integerPolynomial7 = new IntegerPolynomial(bigIntPolynomial3);
        IntegerPolynomial integerPolynomial8 = new IntegerPolynomial(bigIntPolynomial4);
        minimizeFG(integerPolynomial3, integerPolynomial4, integerPolynomial7, integerPolynomial8, i4);
        if (i10 == 0) {
            mult = polynomial3.mult(integerPolynomial2, i5);
            polynomial4 = integerPolynomial7;
        } else {
            mult = integerPolynomial7.mult(integerPolynomial2, i5);
            polynomial4 = polynomial3;
        }
        mult.modPositive(i5);
        return new FGBasis(polynomial2, polynomial4, mult, integerPolynomial7, integerPolynomial8, this.params);
    }

    public NTRUSigningPrivateKeyParameters.Basis generateBoundedBasis() {
        FGBasis generateBasis;
        do {
            generateBasis = generateBasis();
        } while (!generateBasis.isNormOk());
        return generateBasis;
    }

    /* loaded from: classes3.dex */
    private class BasisGenerationTask implements Callable<NTRUSigningPrivateKeyParameters.Basis> {
        private BasisGenerationTask() {
        }

        @Override // java.util.concurrent.Callable
        public NTRUSigningPrivateKeyParameters.Basis call() throws Exception {
            return NTRUSigningKeyPairGenerator.this.generateBoundedBasis();
        }
    }

    /* loaded from: classes3.dex */
    public class FGBasis extends NTRUSigningPrivateKeyParameters.Basis {

        /* renamed from: F */
        public IntegerPolynomial f2821F;

        /* renamed from: G */
        public IntegerPolynomial f2822G;

        FGBasis(Polynomial polynomial, Polynomial polynomial2, IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
            super(polynomial, polynomial2, integerPolynomial, nTRUSigningKeyGenerationParameters);
            this.f2821F = integerPolynomial2;
            this.f2822G = integerPolynomial3;
        }

        boolean isNormOk() {
            double d = this.params.keyNormBoundSq;
            int i = this.params.f2820q;
            return ((double) this.f2821F.centeredNormSq(i)) < d && ((double) this.f2822G.centeredNormSq(i)) < d;
        }
    }
}
