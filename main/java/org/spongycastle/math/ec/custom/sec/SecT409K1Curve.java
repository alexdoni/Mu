package org.spongycastle.math.ec.custom.sec;

import com.xsdk.ab_firstbase.net.okhttp3.CallCode;
import java.math.BigInteger;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECMultiplier;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.WTauNafMultiplier;
import org.spongycastle.util.encoders.Hex;

/* loaded from: classes3.dex */
public class SecT409K1Curve extends ECCurve.AbstractF2m {
    private static final int SecT409K1_DEFAULT_COORDS = 6;
    protected SecT409K1Point infinity;

    @Override // org.spongycastle.math.ec.ECCurve
    public int getFieldSize() {
        return CallCode.HTTP_CONFLICT;
    }

    public int getK1() {
        return 87;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return CallCode.HTTP_CONFLICT;
    }

    @Override // org.spongycastle.math.ec.ECCurve.AbstractF2m
    public boolean isKoblitz() {
        return true;
    }

    public boolean isTrinomial() {
        return true;
    }

    @Override // org.spongycastle.math.ec.ECCurve
    public boolean supportsCoordinateSystem(int i) {
        return i == 6;
    }

    public SecT409K1Curve() {
        super(CallCode.HTTP_CONFLICT, 87, 0, 0);
        this.infinity = new SecT409K1Point(this, null, null);
        this.f2612a = fromBigInteger(BigInteger.valueOf(0L));
        this.f2613b = fromBigInteger(BigInteger.valueOf(1L));
        this.order = new BigInteger(1, Hex.decode("7FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFE5F83B2D4EA20400EC4557D5ED3E3E7CA5B4B5C83B8E01E5FCF"));
        this.cofactor = BigInteger.valueOf(4L);
        this.coord = 6;
    }

    @Override // org.spongycastle.math.ec.ECCurve
    protected ECCurve cloneCurve() {
        return new SecT409K1Curve();
    }

    @Override // org.spongycastle.math.ec.ECCurve
    protected ECMultiplier createDefaultMultiplier() {
        return new WTauNafMultiplier();
    }

    @Override // org.spongycastle.math.ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT409FieldElement(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.spongycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        return new SecT409K1Point(this, eCFieldElement, eCFieldElement2, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.spongycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        return new SecT409K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
    }

    @Override // org.spongycastle.math.ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }
}
