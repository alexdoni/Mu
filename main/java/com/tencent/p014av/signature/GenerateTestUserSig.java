package com.tencent.p014av.signature;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes3.dex */
public class GenerateTestUserSig {
    private static final int EXPIRETIME = 604800;
    public static final int SDKAPPID = 1400399197;
    private static String SECRETKEY = "";

    public static void setSecretkey(String str) {
        SECRETKEY = str;
    }

    public static String genTestUserSig(String str) {
        return GenTLSSignature(1400399197L, str, 604800L, null, SECRETKEY);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0073 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String GenTLSSignature(long r16, java.lang.String r18, long r19, byte[] r21, java.lang.String r22) {
        /*
            r1 = r21
            boolean r0 = android.text.TextUtils.isEmpty(r22)
            java.lang.String r2 = ""
            if (r0 == 0) goto Lb
            return r2
        Lb:
            long r3 = java.lang.System.currentTimeMillis()
            r5 = 1000(0x3e8, double:4.94E-321)
            long r10 = r3 / r5
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.String r0 = "TLS.ver"
            java.lang.String r4 = "2.0"
            r3.put(r0, r4)     // Catch: org.json.JSONException -> L42
            java.lang.String r0 = "TLS.identifier"
            r4 = r18
            r3.put(r0, r4)     // Catch: org.json.JSONException -> L3e
            java.lang.String r0 = "TLS.sdkappid"
            r5 = r16
            r3.put(r0, r5)     // Catch: org.json.JSONException -> L3c
            java.lang.String r0 = "TLS.expire"
            r12 = r19
            r3.put(r0, r12)     // Catch: org.json.JSONException -> L3a
            java.lang.String r0 = "TLS.time"
            r3.put(r0, r10)     // Catch: org.json.JSONException -> L3a
            goto L4c
        L3a:
            r0 = move-exception
            goto L49
        L3c:
            r0 = move-exception
            goto L47
        L3e:
            r0 = move-exception
            r5 = r16
            goto L47
        L42:
            r0 = move-exception
            r5 = r16
            r4 = r18
        L47:
            r12 = r19
        L49:
            r0.printStackTrace()
        L4c:
            if (r1 == 0) goto L5f
            r0 = 2
            java.lang.String r1 = android.util.Base64.encodeToString(r1, r0)
            java.lang.String r0 = "TLS.userbuf"
            r3.put(r0, r1)     // Catch: org.json.JSONException -> L59
            goto L5d
        L59:
            r0 = move-exception
            r0.printStackTrace()
        L5d:
            r15 = r1
            goto L61
        L5f:
            r0 = 0
            r15 = r0
        L61:
            r7 = r16
            r9 = r18
            r12 = r19
            r14 = r22
            java.lang.String r0 = hmacsha256(r7, r9, r10, r12, r14, r15)
            int r1 = r0.length()
            if (r1 != 0) goto L74
            return r2
        L74:
            java.lang.String r1 = "TLS.sig"
            r3.put(r1, r0)     // Catch: org.json.JSONException -> L7a
            goto L7e
        L7a:
            r0 = move-exception
            r0.printStackTrace()
        L7e:
            java.util.zip.Deflater r0 = new java.util.zip.Deflater
            r0.<init>()
            java.lang.String r1 = r3.toString()
            java.lang.String r2 = "UTF-8"
            java.nio.charset.Charset r2 = java.nio.charset.Charset.forName(r2)
            byte[] r1 = r1.getBytes(r2)
            r0.setInput(r1)
            r0.finish()
            r1 = 2048(0x800, float:2.87E-42)
            byte[] r1 = new byte[r1]
            int r2 = r0.deflate(r1)
            r0.end()
            java.lang.String r0 = new java.lang.String
            r3 = 0
            byte[] r1 = java.util.Arrays.copyOfRange(r1, r3, r2)
            byte[] r1 = base64EncodeUrl(r1)
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.signature.GenerateTestUserSig.GenTLSSignature(long, java.lang.String, long, byte[], java.lang.String):java.lang.String");
    }

    private static String hmacsha256(long j, String str, long j2, long j3, String str2, String str3) {
        String str4 = "TLS.identifier:" + str + "\nTLS.sdkappid:" + j + "\nTLS.time:" + j2 + "\nTLS.expire:" + j3 + "\n";
        if (str3 != null) {
            str4 = str4 + "TLS.userbuf:" + str3 + "\n";
        }
        try {
            byte[] bytes = str2.getBytes("UTF-8");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(bytes, "HmacSHA256"));
            return new String(Base64.encode(mac.doFinal(str4.getBytes("UTF-8")), 2));
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException unused) {
            return "";
        }
    }

    private static byte[] base64EncodeUrl(byte[] bArr) {
        byte[] bytes = new String(Base64.encode(bArr, 2)).getBytes();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            if (b == 43) {
                bytes[i] = 42;
            } else if (b == 47) {
                bytes[i] = 45;
            } else if (b == 61) {
                bytes[i] = 95;
            }
        }
        return bytes;
    }
}
