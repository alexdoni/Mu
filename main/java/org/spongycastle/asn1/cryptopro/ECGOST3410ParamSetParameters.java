package org.spongycastle.asn1.cryptopro;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

/* loaded from: classes3.dex */
public class ECGOST3410ParamSetParameters extends ASN1Object {

    /* renamed from: a */
    ASN1Integer f1995a;

    /* renamed from: b */
    ASN1Integer f1996b;

    /* renamed from: p */
    ASN1Integer f1997p;

    /* renamed from: q */
    ASN1Integer f1998q;

    /* renamed from: x */
    ASN1Integer f1999x;

    /* renamed from: y */
    ASN1Integer f2000y;

    public static ECGOST3410ParamSetParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static ECGOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof ECGOST3410ParamSetParameters)) {
            return (ECGOST3410ParamSetParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new ECGOST3410ParamSetParameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid GOST3410Parameter: " + obj.getClass().getName());
    }

    public ECGOST3410ParamSetParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int i, BigInteger bigInteger5) {
        this.f1995a = new ASN1Integer(bigInteger);
        this.f1996b = new ASN1Integer(bigInteger2);
        this.f1997p = new ASN1Integer(bigInteger3);
        this.f1998q = new ASN1Integer(bigInteger4);
        this.f1999x = new ASN1Integer(i);
        this.f2000y = new ASN1Integer(bigInteger5);
    }

    public ECGOST3410ParamSetParameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f1995a = (ASN1Integer) objects.nextElement();
        this.f1996b = (ASN1Integer) objects.nextElement();
        this.f1997p = (ASN1Integer) objects.nextElement();
        this.f1998q = (ASN1Integer) objects.nextElement();
        this.f1999x = (ASN1Integer) objects.nextElement();
        this.f2000y = (ASN1Integer) objects.nextElement();
    }

    public BigInteger getP() {
        return this.f1997p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f1998q.getPositiveValue();
    }

    public BigInteger getA() {
        return this.f1995a.getPositiveValue();
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f1995a);
        aSN1EncodableVector.add(this.f1996b);
        aSN1EncodableVector.add(this.f1997p);
        aSN1EncodableVector.add(this.f1998q);
        aSN1EncodableVector.add(this.f1999x);
        aSN1EncodableVector.add(this.f2000y);
        return new DERSequence(aSN1EncodableVector);
    }
}
