package org.spongycastle.asn1.cmc;

import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.crmf.CertTemplate;

/* loaded from: classes3.dex */
public class ModCertTemplate extends ASN1Object {
    private final BodyPartList certReferences;
    private final CertTemplate certTemplate;
    private final BodyPartPath pkiDataReference;
    private final boolean replace;

    public ModCertTemplate(BodyPartPath bodyPartPath, BodyPartList bodyPartList, boolean z, CertTemplate certTemplate) {
        this.pkiDataReference = bodyPartPath;
        this.certReferences = bodyPartList;
        this.replace = z;
        this.certTemplate = certTemplate;
    }

    private ModCertTemplate(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 4 && aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.pkiDataReference = BodyPartPath.getInstance(aSN1Sequence.getObjectAt(0));
        this.certReferences = BodyPartList.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() == 4) {
            this.replace = ASN1Boolean.getInstance(aSN1Sequence.getObjectAt(2)).isTrue();
            this.certTemplate = CertTemplate.getInstance(aSN1Sequence.getObjectAt(3));
        } else {
            this.replace = true;
            this.certTemplate = CertTemplate.getInstance(aSN1Sequence.getObjectAt(2));
        }
    }

    public static ModCertTemplate getInstance(Object obj) {
        if (obj instanceof ModCertTemplate) {
            return (ModCertTemplate) obj;
        }
        if (obj != null) {
            return new ModCertTemplate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BodyPartPath getPkiDataReference() {
        return this.pkiDataReference;
    }

    public BodyPartList getCertReferences() {
        return this.certReferences;
    }

    public boolean isReplacingFields() {
        return this.replace;
    }

    public CertTemplate getCertTemplate() {
        return this.certTemplate;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.pkiDataReference);
        aSN1EncodableVector.add(this.certReferences);
        boolean z = this.replace;
        if (!z) {
            aSN1EncodableVector.add(ASN1Boolean.getInstance(z));
        }
        aSN1EncodableVector.add(this.certTemplate);
        return new DERSequence(aSN1EncodableVector);
    }
}
