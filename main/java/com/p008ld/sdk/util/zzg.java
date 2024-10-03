package com.p008ld.sdk.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: LDFileUtils.java */
/* loaded from: classes2.dex */
public class zzg {
    private static int zza = 524288;

    /* compiled from: LDFileUtils.java */
    /* loaded from: classes2.dex */
    public interface zza {
        void zza(double d);
    }

    public static void zza(File file, byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = zza(file);
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            fileOutputStream.write(bArr);
            zzh.zza(fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            zzh.zza(fileOutputStream);
            throw th;
        }
    }

    public static FileOutputStream zza(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                throw new IOException("File '" + file + "' could not be created");
            }
        }
        return new FileOutputStream(file);
    }

    public static boolean zza(String str, InputStream inputStream) {
        return zza(zzj.zza(str), inputStream, false, null);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:6|7|8|9|(4:(6:11|(2:12|(1:14)(0))|16|17|18|20)(6:28|(2:29|(1:31)(0))|16|17|18|20)|17|18|20)|15|16) */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004c, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004d, code lost:
    
        r7.printStackTrace();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean zza(java.io.File r7, java.io.InputStream r8, boolean r9, com.ld.sdk.util.zzg.zza r10) {
        /*
            r0 = 0
            if (r8 == 0) goto L8c
            boolean r1 = com.p008ld.sdk.util.zzj.zzc(r7)
            if (r1 != 0) goto Lb
            goto L8c
        Lb:
            r1 = 0
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r3.<init>(r7, r9)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            int r7 = com.p008ld.sdk.util.zzg.zza     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r2.<init>(r3, r7)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r7 = -1
            if (r10 != 0) goto L29
            int r9 = com.p008ld.sdk.util.zzg.zza     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            byte[] r9 = new byte[r9]     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
        L1f:
            int r10 = r8.read(r9)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            if (r10 == r7) goto L48
            r2.write(r9, r0, r10)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            goto L1f
        L29:
            int r9 = r8.available()     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            double r3 = (double) r9     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            r5 = 0
            r10.zza(r5)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            int r9 = com.p008ld.sdk.util.zzg.zza     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            byte[] r9 = new byte[r9]     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            r1 = r0
        L38:
            int r5 = r8.read(r9)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            if (r5 == r7) goto L48
            r2.write(r9, r0, r5)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            int r1 = r1 + r5
            double r5 = (double) r1     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            double r5 = r5 / r3
            r10.zza(r5)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5d
            goto L38
        L48:
            r8.close()     // Catch: java.io.IOException -> L4c
            goto L50
        L4c:
            r7 = move-exception
            r7.printStackTrace()
        L50:
            r2.close()     // Catch: java.io.IOException -> L54
            goto L58
        L54:
            r7 = move-exception
            r7.printStackTrace()
        L58:
            r7 = 1
            return r7
        L5a:
            r7 = move-exception
            r1 = r2
            goto L79
        L5d:
            r7 = move-exception
            r1 = r2
            goto L63
        L60:
            r7 = move-exception
            goto L79
        L62:
            r7 = move-exception
        L63:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L60
            r8.close()     // Catch: java.io.IOException -> L6a
            goto L6e
        L6a:
            r7 = move-exception
            r7.printStackTrace()
        L6e:
            if (r1 == 0) goto L78
            r1.close()     // Catch: java.io.IOException -> L74
            goto L78
        L74:
            r7 = move-exception
            r7.printStackTrace()
        L78:
            return r0
        L79:
            r8.close()     // Catch: java.io.IOException -> L7d
            goto L81
        L7d:
            r8 = move-exception
            r8.printStackTrace()
        L81:
            if (r1 == 0) goto L8b
            r1.close()     // Catch: java.io.IOException -> L87
            goto L8b
        L87:
            r8 = move-exception
            r8.printStackTrace()
        L8b:
            throw r7
        L8c:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "create file <"
            r8.<init>(r9)
            r8.append(r7)
            java.lang.String r7 = "> failed."
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            java.lang.String r8 = "FileIOUtils"
            android.util.Log.e(r8, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.util.zzg.zza(java.io.File, java.io.InputStream, boolean, com.ld.sdk.util.zzg$zza):boolean");
    }
}
