package org.spongycastle.asn1.cmc;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERUTF8String;

/* loaded from: classes3.dex */
public class CMCStatusInfo extends ASN1Object {
    private final ASN1Sequence bodyList;
    private final CMCStatus cMCStatus;
    private final OtherInfo otherInfo;
    private final DERUTF8String statusString;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CMCStatusInfo(CMCStatus cMCStatus, ASN1Sequence aSN1Sequence, DERUTF8String dERUTF8String, OtherInfo otherInfo) {
        this.cMCStatus = cMCStatus;
        this.bodyList = aSN1Sequence;
        this.statusString = dERUTF8String;
        this.otherInfo = otherInfo;
    }

    private CMCStatusInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 2 || aSN1Sequence.size() > 4) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.cMCStatus = CMCStatus.getInstance(aSN1Sequence.getObjectAt(0));
        this.bodyList = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() > 3) {
            this.statusString = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(2));
            this.otherInfo = OtherInfo.getInstance(aSN1Sequence.getObjectAt(3));
        } else {
            if (aSN1Sequence.size() > 2) {
                if (aSN1Sequence.getObjectAt(2) instanceof DERUTF8String) {
                    this.statusString = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(2));
                    this.otherInfo = null;
                    return;
                } else {
                    this.statusString = null;
                    this.otherInfo = OtherInfo.getInstance(aSN1Sequence.getObjectAt(2));
                    return;
                }
            }
            this.statusString = null;
            this.otherInfo = null;
        }
    }

    public static CMCStatusInfo getInstance(Object obj) {
        if (obj instanceof CMCStatusInfo) {
            return (CMCStatusInfo) obj;
        }
        if (obj != null) {
            return new CMCStatusInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.cMCStatus);
        aSN1EncodableVector.add(this.bodyList);
        DERUTF8String dERUTF8String = this.statusString;
        if (dERUTF8String != null) {
            aSN1EncodableVector.add(dERUTF8String);
        }
        OtherInfo otherInfo = this.otherInfo;
        if (otherInfo != null) {
            aSN1EncodableVector.add(otherInfo);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public CMCStatus getCMCStatus() {
        return this.cMCStatus;
    }

    public BodyPartID[] getBodyList() {
        return Utils.toBodyPartIDArray(this.bodyList);
    }

    public DERUTF8String getStatusString() {
        return this.statusString;
    }

    public boolean hasOtherInfo() {
        return this.otherInfo != null;
    }

    public OtherInfo getOtherInfo() {
        return this.otherInfo;
    }

    /* loaded from: classes3.dex */
    public static class OtherInfo extends ASN1Object implements ASN1Choice {
        private final CMCFailInfo failInfo;
        private final PendInfo pendInfo;

        /* JADX INFO: Access modifiers changed from: private */
        public static OtherInfo getInstance(Object obj) {
            if (obj instanceof OtherInfo) {
                return (OtherInfo) obj;
            }
            if (obj instanceof ASN1Encodable) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1Integer) {
                    return new OtherInfo(CMCFailInfo.getInstance(aSN1Primitive));
                }
                if (aSN1Primitive instanceof ASN1Sequence) {
                    return new OtherInfo(PendInfo.getInstance(aSN1Primitive));
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance(): " + obj.getClass().getName());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public OtherInfo(CMCFailInfo cMCFailInfo) {
            this(cMCFailInfo, null);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public OtherInfo(PendInfo pendInfo) {
            this(null, pendInfo);
        }

        private OtherInfo(CMCFailInfo cMCFailInfo, PendInfo pendInfo) {
            this.failInfo = cMCFailInfo;
            this.pendInfo = pendInfo;
        }

        public boolean isFailInfo() {
            return this.failInfo != null;
        }

        @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
        public ASN1Primitive toASN1Primitive() {
            PendInfo pendInfo = this.pendInfo;
            if (pendInfo != null) {
                return pendInfo.toASN1Primitive();
            }
            return this.failInfo.toASN1Primitive();
        }
    }
}
