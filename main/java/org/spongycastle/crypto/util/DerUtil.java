package org.spongycastle.crypto.util;

import java.io.IOException;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
class DerUtil {
    DerUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1OctetString getOctetString(byte[] bArr) {
        if (bArr == null) {
            return new DEROctetString(new byte[0]);
        }
        return new DEROctetString(Arrays.clone(bArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] toByteArray(ASN1Primitive aSN1Primitive) {
        try {
            return aSN1Primitive.getEncoded();
        } catch (IOException e) {
            throw new IllegalStateException("Cannot get encoding: " + e.getMessage()) { // from class: org.spongycastle.crypto.util.DerUtil.1
                @Override // java.lang.Throwable
                public Throwable getCause() {
                    return e;
                }
            };
        }
    }
}
