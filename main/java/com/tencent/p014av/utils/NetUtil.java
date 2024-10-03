package com.tencent.p014av.utils;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class NetUtil {
    private static final boolean DEBUG = true;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0041, code lost:
    
        if (r2 != null) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0043, code lost:
    
        r2.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0053, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0050, code lost:
    
        if (r2 == null) goto L19;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0058  */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String post(java.lang.String r2, java.lang.String r3) {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            java.net.URLConnection r2 = r1.openConnection()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            java.lang.String r1 = "POST"
            r2.setRequestMethod(r1)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L54
            r1 = 5000(0x1388, float:7.006E-42)
            r2.setReadTimeout(r1)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L54
            r1 = 10000(0x2710, float:1.4013E-41)
            r2.setConnectTimeout(r1)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L54
            r1 = 1
            r2.setDoOutput(r1)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L54
            java.io.OutputStream r1 = r2.getOutputStream()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L54
            byte[] r3 = r3.getBytes()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L54
            r1.write(r3)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L54
            r1.flush()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L54
            r1.close()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L54
            int r3 = r2.getResponseCode()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L54
            r1 = 200(0xc8, float:2.8E-43)
            if (r3 != r1) goto L41
            java.io.InputStream r3 = r2.getInputStream()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L54
            java.lang.String r3 = getStringFromInputStream(r3)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L54
            r0 = r3
        L41:
            if (r2 == 0) goto L53
        L43:
            r2.disconnect()
            goto L53
        L47:
            r3 = move-exception
            goto L4d
        L49:
            r3 = move-exception
            goto L56
        L4b:
            r3 = move-exception
            r2 = r0
        L4d:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L54
            if (r2 == 0) goto L53
            goto L43
        L53:
            return r0
        L54:
            r3 = move-exception
            r0 = r2
        L56:
            if (r0 == 0) goto L5b
            r0.disconnect()
        L5b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.utils.NetUtil.post(java.lang.String, java.lang.String):java.lang.String");
    }

    private static String getStringFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                byteArrayOutputStream.close();
                return byteArrayOutputStream2;
            }
        }
    }

    public static void debugInfo(String str, String str2) {
        Log.d(str, str2);
    }
}
