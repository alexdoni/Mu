package com.p008ld.sdk.zzc;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import java.io.File;

/* compiled from: ChannelReaderUtil.java */
/* loaded from: classes2.dex */
public class zza {
    private static String zza;

    public static String zza(Context context) {
        if (zza == null) {
            String zzb = zzb(context);
            if (zzb == null || zzb.equals("")) {
                zzb = zzc(context);
            }
            zza = zzb;
        }
        return zza;
    }

    public static String zzb(Context context) {
        String zza2 = com.p008ld.sdk.zzc.zzb.zza.zza(new File(zzd(context)));
        Log.i("ChannelReaderUtil", "getChannelByV2 , channel = " + zza2);
        return zza2;
    }

    public static String zzc(Context context) {
        String zzb = com.p008ld.sdk.zzc.zzb.zza.zzb(new File(zzd(context)));
        Log.i("ChannelReaderUtil", "getChannelByV1 , channel = " + zzb);
        return zzb;
    }

    private static String zzd(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                return null;
            }
            return applicationInfo.sourceDir;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
