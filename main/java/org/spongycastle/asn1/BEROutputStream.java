package org.spongycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public class BEROutputStream extends DEROutputStream {
    public BEROutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void writeObject(Object obj) throws IOException {
        if (obj == null) {
            writeNull();
        } else if (obj instanceof ASN1Primitive) {
            ((ASN1Primitive) obj).encode(this);
        } else {
            if (obj instanceof ASN1Encodable) {
                ((ASN1Encodable) obj).toASN1Primitive().encode(this);
                return;
            }
            throw new IOException("object not BEREncodable");
        }
    }
}
