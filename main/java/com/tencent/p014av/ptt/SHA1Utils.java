package com.tencent.p014av.ptt;

import com.google.common.base.Ascii;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* loaded from: classes3.dex */
public class SHA1Utils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String getFileSHA1(String str) {
        return messageDigestInter(str, McElieceCCA2KeyGenParameterSpec.SHA1);
    }

    public static String getFileMD5(String str) {
        return messageDigestInter(str, "MD5");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String messageDigestInter(java.lang.String r4, java.lang.String r5) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r5)     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L3e
            r5 = 65536(0x10000, float:9.18355E-41)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L3e
        Le:
            int r2 = r1.read(r5)     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L3e
            if (r2 <= 0) goto L19
            r3 = 0
            r4.update(r5, r3, r2)     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L3e
            goto Le
        L19:
            byte[] r4 = r4.digest()     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L3e
            java.lang.String r4 = toHexString(r4)     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L3e
            r1.close()     // Catch: java.lang.Exception -> L25
            goto L29
        L25:
            r5 = move-exception
            r5.printStackTrace()
        L29:
            return r4
        L2a:
            r4 = move-exception
            goto L30
        L2c:
            r4 = move-exception
            goto L40
        L2e:
            r4 = move-exception
            r1 = r0
        L30:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L3e
            if (r1 == 0) goto L3d
            r1.close()     // Catch: java.lang.Exception -> L39
            goto L3d
        L39:
            r4 = move-exception
            r4.printStackTrace()
        L3d:
            return r0
        L3e:
            r4 = move-exception
            r0 = r1
        L40:
            if (r0 == 0) goto L4a
            r0.close()     // Catch: java.lang.Exception -> L46
            goto L4a
        L46:
            r5 = move-exception
            r5.printStackTrace()
        L4a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.ptt.SHA1Utils.messageDigestInter(java.lang.String, java.lang.String):java.lang.String");
    }

    public static String toHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            char[] cArr = HEX_DIGITS;
            sb.append(cArr[(bArr[i] & 240) >>> 4]);
            sb.append(cArr[bArr[i] & Ascii.f555SI]);
        }
        return sb.toString();
    }
}
