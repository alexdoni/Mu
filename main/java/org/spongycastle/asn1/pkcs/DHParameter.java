package org.spongycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

/* loaded from: classes3.dex */
public class DHParameter extends ASN1Object {

    /* renamed from: g */
    ASN1Integer f2021g;

    /* renamed from: l */
    ASN1Integer f2022l;

    /* renamed from: p */
    ASN1Integer f2023p;

    public DHParameter(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f2023p = new ASN1Integer(bigInteger);
        this.f2021g = new ASN1Integer(bigInteger2);
        if (i != 0) {
            this.f2022l = new ASN1Integer(i);
        } else {
            this.f2022l = null;
        }
    }

    public static DHParameter getInstance(Object obj) {
        if (obj instanceof DHParameter) {
            return (DHParameter) obj;
        }
        if (obj != null) {
            return new DHParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private DHParameter(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f2023p = ASN1Integer.getInstance(objects.nextElement());
        this.f2021g = ASN1Integer.getInstance(objects.nextElement());
        if (objects.hasMoreElements()) {
            this.f2022l = (ASN1Integer) objects.nextElement();
        } else {
            this.f2022l = null;
        }
    }

    public BigInteger getP() {
        return this.f2023p.getPositiveValue();
    }

    public BigInteger getG() {
        return this.f2021g.getPositiveValue();
    }

    public BigInteger getL() {
        ASN1Integer aSN1Integer = this.f2022l;
        if (aSN1Integer == null) {
            return null;
        }
        return aSN1Integer.getPositiveValue();
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f2023p);
        aSN1EncodableVector.add(this.f2021g);
        if (getL() != null) {
            aSN1EncodableVector.add(this.f2022l);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
