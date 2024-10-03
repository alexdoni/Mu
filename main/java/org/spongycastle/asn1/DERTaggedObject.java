package org.spongycastle.asn1;

import java.io.IOException;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes3.dex */
public class DERTaggedObject extends ASN1TaggedObject {
    private static final byte[] ZERO_BYTES = new byte[0];

    public DERTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
    }

    public DERTaggedObject(int i, ASN1Encodable aSN1Encodable) {
        super(true, i, aSN1Encodable);
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
            int encodedLength = this.obj.toASN1Primitive().toDERObject().encodedLength();
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
        boolean z = this.empty;
        int i = CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256;
        if (!z) {
            ASN1Primitive dERObject = this.obj.toASN1Primitive().toDERObject();
            if (this.explicit) {
                aSN1OutputStream.writeTag(CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256, this.tagNo);
                aSN1OutputStream.writeLength(dERObject.encodedLength());
                aSN1OutputStream.writeObject(dERObject);
                return;
            } else {
                if (!dERObject.isConstructed()) {
                    i = 128;
                }
                aSN1OutputStream.writeTag(i, this.tagNo);
                aSN1OutputStream.writeImplicitObject(dERObject);
                return;
            }
        }
        aSN1OutputStream.writeEncoded(CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256, this.tagNo, ZERO_BYTES);
    }
}
