package org.spongycastle.asn1;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class DEREnumerated extends ASN1Enumerated {
    DEREnumerated(byte[] bArr) {
        super(bArr);
    }

    public DEREnumerated(BigInteger bigInteger) {
        super(bigInteger);
    }

    public DEREnumerated(int i) {
        super(i);
    }
}
