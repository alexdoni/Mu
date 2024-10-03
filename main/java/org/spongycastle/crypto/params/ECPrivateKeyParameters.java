package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class ECPrivateKeyParameters extends ECKeyParameters {

    /* renamed from: d */
    BigInteger f2487d;

    public ECPrivateKeyParameters(BigInteger bigInteger, ECDomainParameters eCDomainParameters) {
        super(true, eCDomainParameters);
        this.f2487d = bigInteger;
    }

    public BigInteger getD() {
        return this.f2487d;
    }
}
