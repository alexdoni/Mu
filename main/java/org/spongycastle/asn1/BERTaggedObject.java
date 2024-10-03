package org.spongycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes3.dex */
public class BERTaggedObject extends ASN1TaggedObject {
    public BERTaggedObject(int i, ASN1Encodable aSN1Encodable) {
        super(true, i, aSN1Encodable);
    }

    public BERTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
    }

    public BERTaggedObject(int i) {
        super(false, i, new BERSequence());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        if (this.empty || this.explicit) {
            return true;
        }
        return this.obj.toASN1Primitive().toDERObject().isConstructed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public int encodedLength() throws IOException {
        int calculateTagLength;
        if (!this.empty) {
            int encodedLength = this.obj.toASN1Primitive().encodedLength();
            if (this.explicit) {
                calculateTagLength = StreamUtil.calculateTagLength(this.tagNo) + StreamUtil.calculateBodyLength(encodedLength);
            } else {
                encodedLength--;
                calculateTagLength = StreamUtil.calculateTagLength(this.tagNo);
            }
            return calculateTagLength + encodedLength;
        }
        return StreamUtil.calculateTagLength(this.tagNo) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1TaggedObject, org.spongycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        Enumeration objects;
        aSN1OutputStream.writeTag(CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256, this.tagNo);
        aSN1OutputStream.write(128);
        if (!this.empty) {
            if (!this.explicit) {
                if (this.obj instanceof ASN1OctetString) {
                    if (this.obj instanceof BEROctetString) {
                        objects = ((BEROctetString) this.obj).getObjects();
                    } else {
                        objects = new BEROctetString(((ASN1OctetString) this.obj).getOctets()).getObjects();
                    }
                } else if (this.obj instanceof ASN1Sequence) {
                    objects = ((ASN1Sequence) this.obj).getObjects();
                } else if (this.obj instanceof ASN1Set) {
                    objects = ((ASN1Set) this.obj).getObjects();
                } else {
                    throw new ASN1Exception("not implemented: " + this.obj.getClass().getName());
                }
                while (objects.hasMoreElements()) {
                    aSN1OutputStream.writeObject((ASN1Encodable) objects.nextElement());
                }
            } else {
                aSN1OutputStream.writeObject(this.obj);
            }
        }
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }
}
