package com.u2020.sdk.mc;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/* compiled from: PayloadReader.java */
/* renamed from: com.u2020.sdk.mc.f */
/* loaded from: classes3.dex */
final class C2656f {
    private C2656f() {
    }

    /* renamed from: a */
    public static byte[] m1211a(final File apkFile, final int id) {
        ByteBuffer byteBuffer;
        Map<Integer, ByteBuffer> m1210a = m1210a(apkFile);
        if (m1210a == null || (byteBuffer = m1210a.get(Integer.valueOf(id))) == null) {
            return null;
        }
        return m1212a(byteBuffer);
    }

    /* renamed from: b */
    public static String m1213b(final File apkFile, final int id) {
        byte[] m1211a = m1211a(apkFile, id);
        if (m1211a == null) {
            return null;
        }
        try {
            return new String(m1211a, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static byte[] m1212a(final ByteBuffer byteBuffer) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(array, byteBuffer.position() + arrayOffset, arrayOffset + byteBuffer.limit());
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003d, code lost:
    
        if (r1 == null) goto L26;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.Map<java.lang.Integer, java.nio.ByteBuffer> m1210a(final java.io.File r3) {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L27 java.io.IOException -> L36
            java.lang.String r2 = "r"
            r1.<init>(r3, r2)     // Catch: java.lang.Throwable -> L27 java.io.IOException -> L36
            java.nio.channels.FileChannel r3 = r1.getChannel()     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L25
            com.u2020.sdk.mc.e r2 = com.u2020.sdk.mc.C2652b.m1188a(r3)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L38
            java.lang.Object r2 = r2.m1208a()     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L38
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L38
            java.util.Map r0 = com.u2020.sdk.mc.C2652b.m1199b(r2)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L38
            if (r3 == 0) goto L3f
            r3.close()     // Catch: java.io.IOException -> L3f java.lang.Throwable -> L42
            goto L3f
        L20:
            r2 = move-exception
            goto L2b
        L22:
            r2 = move-exception
            r3 = r0
            goto L2b
        L25:
            r3 = r0
            goto L38
        L27:
            r3 = move-exception
            r2 = r3
            r3 = r0
            r1 = r3
        L2b:
            if (r3 == 0) goto L30
            r3.close()     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L42
        L30:
            if (r1 == 0) goto L35
            r1.close()     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L42
        L35:
            throw r2     // Catch: java.lang.Throwable -> L42
        L36:
            r3 = r0
            r1 = r3
        L38:
            if (r3 == 0) goto L3d
            r3.close()     // Catch: java.io.IOException -> L3d java.lang.Throwable -> L42
        L3d:
            if (r1 == 0) goto L42
        L3f:
            r1.close()     // Catch: java.lang.Throwable -> L42
        L42:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.u2020.sdk.mc.C2656f.m1210a(java.io.File):java.util.Map");
    }
}
