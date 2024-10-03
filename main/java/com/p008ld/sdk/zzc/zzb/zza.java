package com.p008ld.sdk.zzc.zzb;

import com.p008ld.sdk.zzc.zza.zzc;
import java.io.File;

/* compiled from: ChannelReader.java */
/* loaded from: classes2.dex */
public class zza {
    public static String zza(File file) {
        System.out.println("try to read channel info from apk : " + file.getAbsolutePath());
        return zzb.zza(file, -2012129793);
    }

    public static String zzb(File file) {
        try {
            return zzc.zza(file);
        } catch (Exception unused) {
            System.out.println("APK : " + file.getAbsolutePath() + " not have channel info from Zip Comment");
            return "";
        }
    }
}
