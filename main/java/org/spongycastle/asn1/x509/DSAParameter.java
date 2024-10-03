package org.spongycastle.asn1.x509;

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
public class DSAParameter extends ASN1Object {

    /* renamed from: g */
    ASN1Integer f2055g;

    /* renamed from: p */
    ASN1Integer f2056p;

    /* renamed from: q */
    ASN1Integer f2057q;

    public static DSAParameter getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static DSAParameter getInstance(Object obj) {
        if (obj instanceof DSAParameter) {
            return (DSAParameter) obj;
        }
        if (obj != null) {
            return new DSAParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DSAParameter(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f2056p = new ASN1Integer(bigInteger);
        this.f2057q = new ASN1Integer(bigInteger2);
        this.f2055g = new ASN1Integer(bigInteger3);
    }

    private DSAParameter(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        this.f2056p = ASN1Integer.getInstance(objects.nextElement());
        this.f2057q = ASN1Integer.getInstance(objects.nextElement());
        this.f2055g = ASN1Integer.getInstance(objects.nextElement());
    }

    public BigInteger getP() {
        return this.f2056p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f2057q.getPositiveValue();
    }

    public BigInteger getG() {
        return this.f2055g.getPositiveValue();
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f2056p);
        aSN1EncodableVector.add(this.f2057q);
        aSN1EncodableVector.add(this.f2055g);
        return new DERSequence(aSN1EncodableVector);
    }
}
