package org.spongycastle.asn1.ua;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

/* loaded from: classes3.dex */
public class DSTU4145BinaryField extends ASN1Object {

    /* renamed from: j */
    private int f2026j;

    /* renamed from: k */
    private int f2027k;

    /* renamed from: l */
    private int f2028l;

    /* renamed from: m */
    private int f2029m;

    private DSTU4145BinaryField(ASN1Sequence aSN1Sequence) {
        this.f2029m = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getPositiveValue().intValue();
        if (aSN1Sequence.getObjectAt(1) instanceof ASN1Integer) {
            this.f2027k = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getPositiveValue().intValue();
        } else {
            if (aSN1Sequence.getObjectAt(1) instanceof ASN1Sequence) {
                ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
                this.f2027k = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(0)).getPositiveValue().intValue();
                this.f2026j = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(1)).getPositiveValue().intValue();
                this.f2028l = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(2)).getPositiveValue().intValue();
                return;
            }
            throw new IllegalArgumentException("object parse error");
        }
    }

    public static DSTU4145BinaryField getInstance(Object obj) {
        if (obj instanceof DSTU4145BinaryField) {
            return (DSTU4145BinaryField) obj;
        }
        if (obj != null) {
            return new DSTU4145BinaryField(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DSTU4145BinaryField(int i, int i2, int i3, int i4) {
        this.f2029m = i;
        this.f2027k = i2;
        this.f2026j = i3;
        this.f2028l = i4;
    }

    public int getM() {
        return this.f2029m;
    }

    public int getK1() {
        return this.f2027k;
    }

    public int getK2() {
        return this.f2026j;
    }

    public int getK3() {
        return this.f2028l;
    }

    public DSTU4145BinaryField(int i, int i2) {
        this(i, i2, 0, 0);
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.f2029m));
        if (this.f2026j == 0) {
            aSN1EncodableVector.add(new ASN1Integer(this.f2027k));
        } else {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.add(new ASN1Integer(this.f2027k));
            aSN1EncodableVector2.add(new ASN1Integer(this.f2026j));
            aSN1EncodableVector2.add(new ASN1Integer(this.f2028l));
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
