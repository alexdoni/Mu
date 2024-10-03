package org.spongycastle.asn1.p020x9;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

/* loaded from: classes3.dex */
public class DomainParameters extends ASN1Object {

    /* renamed from: g */
    private final ASN1Integer f2075g;

    /* renamed from: j */
    private final ASN1Integer f2076j;

    /* renamed from: p */
    private final ASN1Integer f2077p;

    /* renamed from: q */
    private final ASN1Integer f2078q;
    private final ValidationParams validationParams;

    public static DomainParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static DomainParameters getInstance(Object obj) {
        if (obj instanceof DomainParameters) {
            return (DomainParameters) obj;
        }
        if (obj != null) {
            return new DomainParameters(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DomainParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, ValidationParams validationParams) {
        if (bigInteger == null) {
            throw new IllegalArgumentException("'p' cannot be null");
        }
        if (bigInteger2 == null) {
            throw new IllegalArgumentException("'g' cannot be null");
        }
        if (bigInteger3 == null) {
            throw new IllegalArgumentException("'q' cannot be null");
        }
        this.f2077p = new ASN1Integer(bigInteger);
        this.f2075g = new ASN1Integer(bigInteger2);
        this.f2078q = new ASN1Integer(bigInteger3);
        if (bigInteger4 != null) {
            this.f2076j = new ASN1Integer(bigInteger4);
        } else {
            this.f2076j = null;
        }
        this.validationParams = validationParams;
    }

    private DomainParameters(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 3 || aSN1Sequence.size() > 5) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        this.f2077p = ASN1Integer.getInstance(objects.nextElement());
        this.f2075g = ASN1Integer.getInstance(objects.nextElement());
        this.f2078q = ASN1Integer.getInstance(objects.nextElement());
        ASN1Encodable next = getNext(objects);
        if (next != null && (next instanceof ASN1Integer)) {
            this.f2076j = ASN1Integer.getInstance(next);
            next = getNext(objects);
        } else {
            this.f2076j = null;
        }
        if (next != null) {
            this.validationParams = ValidationParams.getInstance(next.toASN1Primitive());
        } else {
            this.validationParams = null;
        }
    }

    private static ASN1Encodable getNext(Enumeration enumeration) {
        if (enumeration.hasMoreElements()) {
            return (ASN1Encodable) enumeration.nextElement();
        }
        return null;
    }

    public BigInteger getP() {
        return this.f2077p.getPositiveValue();
    }

    public BigInteger getG() {
        return this.f2075g.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f2078q.getPositiveValue();
    }

    public BigInteger getJ() {
        ASN1Integer aSN1Integer = this.f2076j;
        if (aSN1Integer == null) {
            return null;
        }
        return aSN1Integer.getPositiveValue();
    }

    public ValidationParams getValidationParams() {
        return this.validationParams;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f2077p);
        aSN1EncodableVector.add(this.f2075g);
        aSN1EncodableVector.add(this.f2078q);
        ASN1Integer aSN1Integer = this.f2076j;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        ValidationParams validationParams = this.validationParams;
        if (validationParams != null) {
            aSN1EncodableVector.add(validationParams);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
