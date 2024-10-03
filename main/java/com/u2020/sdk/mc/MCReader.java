package com.u2020.sdk.mc;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import java.io.File;

/* loaded from: classes3.dex */
public class MCReader {

    /* renamed from: a */
    private static Channel f1557a = null;

    /* renamed from: b */
    private static String f1558b = "MCReader";

    /* renamed from: a */
    private static String m1187a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return null;
        }
        return applicationInfo.sourceDir;
    }

    public static Channel getChannel(Context context) {
        String str;
        if (f1557a == null) {
            try {
                String m1187a = m1187a(context.getApplicationContext());
                if (m1187a == null) {
                    Log.i(f1558b, "SourceDir is null");
                    return null;
                }
                File file = new File(m1187a);
                Channel m1201a = C2654d.m1201a(file);
                if (m1201a == null) {
                    m1201a = C2654d.m1206f(file);
                    str = "v1";
                } else {
                    str = "v2";
                }
                if (m1201a != null) {
                    Log.i(f1558b, str);
                }
                f1557a = m1201a;
            } catch (Exception unused) {
            }
        }
        return f1557a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0030, code lost:
    
        if (r2.getSize() <= 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0032, code lost:
    
        r5 = new java.io.BufferedReader(new java.io.InputStreamReader(r1.getInputStream(r2)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0040, code lost:
    
        r6 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0041, code lost:
    
        r2 = r5.readLine();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
    
        if (r2 == null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0047, code lost:
    
        if (r6 != null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0049, code lost:
    
        r6 = new java.lang.StringBuilder();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004f, code lost:
    
        r6.append(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0063, code lost:
    
        if (r1 == null) goto L30;
     */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getChannel(android.content.Context r5, java.lang.String r6) {
        /*
            r0 = 0
            android.content.Context r5 = r5.getApplicationContext()     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L61
            java.lang.String r5 = m1187a(r5)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L61
            java.util.zip.ZipFile r1 = new java.util.zip.ZipFile     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L61
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L61
            java.util.Enumeration r5 = r1.entries()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
        L12:
            boolean r2 = r5.hasMoreElements()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
            if (r2 == 0) goto L53
            java.lang.Object r2 = r5.nextElement()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
            java.util.zip.ZipEntry r2 = (java.util.zip.ZipEntry) r2     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
            java.lang.String r3 = r2.getName()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
            boolean r3 = r3.startsWith(r6)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
            if (r3 == 0) goto L12
            long r5 = r2.getSize()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
            r3 = 0
            int r5 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r5 <= 0) goto L53
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
            java.io.InputStream r2 = r1.getInputStream(r2)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
            r6.<init>(r2)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L58
            r6 = r0
        L41:
            java.lang.String r2 = r5.readLine()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L63
            if (r2 == 0) goto L65
            if (r6 != 0) goto L4f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L63
            r3.<init>()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L63
            r6 = r3
        L4f:
            r6.append(r2)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L63
            goto L41
        L53:
            r6 = r0
            goto L65
        L55:
            r5 = move-exception
            r0 = r1
            goto L5b
        L58:
            r6 = r0
            goto L63
        L5a:
            r5 = move-exception
        L5b:
            if (r0 == 0) goto L60
            r0.close()     // Catch: java.io.IOException -> L60
        L60:
            throw r5
        L61:
            r6 = r0
            r1 = r6
        L63:
            if (r1 == 0) goto L68
        L65:
            r1.close()     // Catch: java.io.IOException -> L68
        L68:
            if (r6 != 0) goto L6b
            goto L6f
        L6b:
            java.lang.String r0 = r6.toString()
        L6f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.u2020.sdk.mc.MCReader.getChannel(android.content.Context, java.lang.String):java.lang.String");
    }
}
