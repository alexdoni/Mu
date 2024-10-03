package com.p008ld.sdk.zzc;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: ChannelUtil.java */
/* loaded from: classes2.dex */
public class zzb {
    private static String zza;
    private static boolean zzb;

    public static String zza(Context context) {
        if (!TextUtils.isEmpty(zza)) {
            return zza;
        }
        if (zzb) {
            return zza;
        }
        zzb = true;
        String zza2 = zza(context, "CHANNEL");
        zza = zza2;
        if (!TextUtils.isEmpty(zza2)) {
            return zza;
        }
        return zza;
    }

    private static String zza(Context context, String str) {
        String str2;
        String[] split;
        String str3 = "META-INF/" + str;
        ZipFile zipFile = null;
        try {
            try {
                ZipFile zipFile2 = new ZipFile(context.getApplicationInfo().sourceDir);
                try {
                    Enumeration<? extends ZipEntry> entries = zipFile2.entries();
                    try {
                        while (entries.hasMoreElements()) {
                            str2 = entries.nextElement().getName();
                            if (!str2.startsWith(str3)) {
                            }
                        }
                        zipFile2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    str2 = "";
                } catch (IOException e2) {
                    e = e2;
                    zipFile = zipFile2;
                    e.printStackTrace();
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    str2 = "";
                    split = str2.split("_");
                    return split == null ? "" : "";
                } catch (Throwable th) {
                    th = th;
                    zipFile = zipFile2;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e5) {
            e = e5;
        }
        split = str2.split("_");
        if (split == null && split.length >= 2) {
            return str2.substring(split[0].length() + 1);
        }
    }
}
