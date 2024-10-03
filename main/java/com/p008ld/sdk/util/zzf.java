package com.p008ld.sdk.util;

import android.content.Context;
import android.util.Log;
import io.jsonwebtoken.JwtParser;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/* compiled from: LDFileCacheUtils.java */
/* loaded from: classes2.dex */
public class zzf {
    static int zza = 2097152;
    static int zzb = 1000;
    private static final Object zzc = new Object();
    private static File zzd;

    public static void zza(Context context) {
        zza(new File(zzj.zza(context), "ldsdk"));
    }

    public static void zza(File file) {
        if (!file.isDirectory() && !file.mkdir()) {
            throw new RuntimeException("Could not create ldsdk directory");
        }
        zzd = file;
    }

    private static File zzb(File file) {
        if (file != null && !file.exists()) {
            file.mkdir();
        }
        return file;
    }

    private static File zza(File file, String str) {
        final String str2 = "." + str;
        File[] listFiles = zzb(file).listFiles(new FilenameFilter() { // from class: com.ld.sdk.util.zzf$$ExternalSyntheticLambda1
            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str3) {
                boolean zza2;
                zza2 = zzf.zza(str2, file2, str3);
                return zza2;
            }
        });
        if (listFiles == null || listFiles.length == 0) {
            return null;
        }
        return listFiles[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean zza(String str, File file, String str2) {
        return str2.endsWith(str);
    }

    private static File zzb(String str) {
        return zza(zzd, str);
    }

    private static long zzc(File file) {
        String name = file.getName();
        try {
            return Long.parseLong(name.substring(0, name.indexOf(46)));
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    private static File zzb(File file, String str) {
        return new File(zzb(file), String.valueOf(new Date().getTime()) + JwtParser.SEPARATOR_CHAR + str);
    }

    public static void zza(File file, String str, String str2) {
        synchronized (zzc) {
            File zza2 = zza(file, str);
            if (zza2 != null) {
                zza2.delete();
            }
            try {
                zzg.zza(zzb(file, str), str2.getBytes("UTF-8"));
            } catch (IOException unused) {
            }
            File[] listFiles = zzb(file).listFiles();
            if (listFiles != null && listFiles.length != 0) {
                int length = listFiles.length;
                int i = 0;
                for (File file2 : listFiles) {
                    i = (int) (i + file2.length());
                }
                if (length > zzb || i > zza) {
                    Arrays.sort(listFiles, new Comparator() { // from class: com.ld.sdk.util.zzf$$ExternalSyntheticLambda0
                        @Override // java.util.Comparator
                        public final int compare(Object obj, Object obj2) {
                            int zza3;
                            zza3 = zzf.zza((File) obj, (File) obj2);
                            return zza3;
                        }
                    });
                    for (File file3 : listFiles) {
                        length--;
                        i = (int) (i - file3.length());
                        file3.delete();
                        if (length <= zzb && i <= zza) {
                            break;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int zza(File file, File file2) {
        int compare = Long.compare(file.lastModified(), file2.lastModified());
        return compare != 0 ? compare : file.getName().compareTo(file2.getName());
    }

    public static void zza(String str, String str2) {
        zza(zzd, str, str2);
    }

    public static void zza(String str) {
        synchronized (zzc) {
            File zzb2 = zzb(str);
            if (zzb2 != null) {
                zzb2.delete();
            }
        }
    }

    public static String zza(File file, String str, long j) {
        synchronized (zzc) {
            File zza2 = zza(file, str);
            if (zza2 == null) {
                return null;
            }
            Date date = new Date();
            if (j > 0) {
                if (zzc(zza2) < Math.max(0L, date.getTime() - j)) {
                    return null;
                }
            }
            zza2.setLastModified(date.getTime());
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(zza2, "r");
                byte[] bArr = new byte[(int) randomAccessFile.length()];
                randomAccessFile.readFully(bArr);
                randomAccessFile.close();
                return new String(bArr, "UTF-8");
            } catch (IOException e) {
                LDLog.m573e("error reading from cache" + Log.getStackTraceString(e));
                return null;
            }
        }
    }

    public static String zza(String str, long j) {
        return zza(zzd, str, j);
    }
}
