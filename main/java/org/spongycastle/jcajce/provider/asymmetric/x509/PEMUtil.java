package org.spongycastle.jcajce.provider.asymmetric.x509;

import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.util.encoders.Base64;

/* loaded from: classes3.dex */
class PEMUtil {
    private final String _footer1;
    private final String _footer2;
    private final String _header1;
    private final String _header2;
    private final String _header3 = "-----BEGIN PKCS7-----";
    private final String _footer3 = "-----END PKCS7-----";

    /* JADX INFO: Access modifiers changed from: package-private */
    public PEMUtil(String str) {
        this._header1 = "-----BEGIN " + str + "-----";
        this._header2 = "-----BEGIN X509 " + str + "-----";
        this._footer1 = "-----END " + str + "-----";
        this._footer2 = "-----END X509 " + str + "-----";
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:
    
        if (r0.length() == 0) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String readLine(java.io.InputStream r6) throws java.io.IOException {
        /*
            r5 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L5:
            int r1 = r6.read()
            r2 = 10
            r3 = 13
            if (r1 == r3) goto L18
            if (r1 == r2) goto L18
            if (r1 < 0) goto L18
            char r1 = (char) r1
            r0.append(r1)
            goto L5
        L18:
            if (r1 < 0) goto L20
            int r4 = r0.length()
            if (r4 == 0) goto L5
        L20:
            if (r1 >= 0) goto L24
            r6 = 0
            return r6
        L24:
            if (r1 != r3) goto L38
            r1 = 1
            r6.mark(r1)
            int r3 = r6.read()
            if (r3 != r2) goto L33
            r6.mark(r1)
        L33:
            if (r3 <= 0) goto L38
            r6.reset()
        L38:
            java.lang.String r6 = r0.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jcajce.provider.asymmetric.x509.PEMUtil.readLine(java.io.InputStream):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Sequence readPEMObject(InputStream inputStream) throws IOException {
        String readLine;
        StringBuffer stringBuffer = new StringBuffer();
        do {
            readLine = readLine(inputStream);
            if (readLine == null || readLine.startsWith(this._header1) || readLine.startsWith(this._header2)) {
                break;
            }
        } while (!readLine.startsWith(this._header3));
        while (true) {
            String readLine2 = readLine(inputStream);
            if (readLine2 == null || readLine2.startsWith(this._footer1) || readLine2.startsWith(this._footer2) || readLine2.startsWith(this._footer3)) {
                break;
            }
            stringBuffer.append(readLine2);
        }
        if (stringBuffer.length() == 0) {
            return null;
        }
        try {
            return ASN1Sequence.getInstance(Base64.decode(stringBuffer.toString()));
        } catch (Exception unused) {
            throw new IOException("malformed PEM data encountered");
        }
    }
}
