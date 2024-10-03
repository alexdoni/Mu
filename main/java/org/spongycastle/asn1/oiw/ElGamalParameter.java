package org.spongycastle.asn1.oiw;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

/* loaded from: classes3.dex */
public class ElGamalParameter extends ASN1Object {

    /* renamed from: g */
    ASN1Integer f2019g;

    /* renamed from: p */
    ASN1Integer f2020p;

    public ElGamalParameter(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f2020p = new ASN1Integer(bigInteger);
        this.f2019g = new ASN1Integer(bigInteger2);
    }

    private ElGamalParameter(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f2020p = (ASN1Integer) objects.nextElement();
        this.f2019g = (ASN1Integer) objects.nextElement();
    }

    public static ElGamalParameter getInstance(Object obj) {
        if (obj instanceof ElGamalParameter) {
            return (ElGamalParameter) obj;
        }
        if (obj != null) {
            return new ElGamalParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getP() {
        return this.f2020p.getPositiveValue();
    }

    public BigInteger getG() {
        return this.f2019g.getPositiveValue();
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f2020p);
        aSN1EncodableVector.add(this.f2019g);
        return new DERSequence(aSN1EncodableVector);
    }
}
