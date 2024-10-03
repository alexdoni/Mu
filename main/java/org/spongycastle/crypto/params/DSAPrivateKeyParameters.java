package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class DSAPrivateKeyParameters extends DSAKeyParameters {

    /* renamed from: x */
    private BigInteger f2482x;

    public DSAPrivateKeyParameters(BigInteger bigInteger, DSAParameters dSAParameters) {
        super(true, dSAParameters);
        this.f2482x = bigInteger;
    }

    public BigInteger getX() {
        return this.f2482x;
    }
}
