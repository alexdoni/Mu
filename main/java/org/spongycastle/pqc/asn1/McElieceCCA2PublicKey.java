package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

/* loaded from: classes3.dex */
public class McElieceCCA2PublicKey extends ASN1Object {
    private final AlgorithmIdentifier digest;

    /* renamed from: g */
    private final GF2Matrix f2724g;

    /* renamed from: n */
    private final int f2725n;

    /* renamed from: t */
    private final int f2726t;

    public McElieceCCA2PublicKey(int i, int i2, GF2Matrix gF2Matrix, AlgorithmIdentifier algorithmIdentifier) {
        this.f2725n = i;
        this.f2726t = i2;
        this.f2724g = new GF2Matrix(gF2Matrix.getEncoded());
        this.digest = algorithmIdentifier;
    }

    private McElieceCCA2PublicKey(ASN1Sequence aSN1Sequence) {
        this.f2725n = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue().intValue();
        this.f2726t = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getValue().intValue();
        this.f2724g = new GF2Matrix(((ASN1OctetString) aSN1Sequence.getObjectAt(2)).getOctets());
        this.digest = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(3));
    }

    public int getN() {
        return this.f2725n;
    }

    public int getT() {
        return this.f2726t;
    }

    public GF2Matrix getG() {
        return this.f2724g;
    }

    public AlgorithmIdentifier getDigest() {
        return this.digest;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.f2725n));
        aSN1EncodableVector.add(new ASN1Integer(this.f2726t));
        aSN1EncodableVector.add(new DEROctetString(this.f2724g.getEncoded()));
        aSN1EncodableVector.add(this.digest);
        return new DERSequence(aSN1EncodableVector);
    }

    public static McElieceCCA2PublicKey getInstance(Object obj) {
        if (obj instanceof McElieceCCA2PublicKey) {
            return (McElieceCCA2PublicKey) obj;
        }
        if (obj != null) {
            return new McElieceCCA2PublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }
}
