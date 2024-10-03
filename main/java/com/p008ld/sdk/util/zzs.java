package com.p008ld.sdk.util;

import android.app.Activity;
import androidx.core.app.ActivityCompat;
import com.luck.picture.lib.permissions.PermissionConfig;

/* compiled from: PermissionUtils.java */
/* loaded from: classes2.dex */
public class zzs {
    private static String[] zza = {PermissionConfig.READ_EXTERNAL_STORAGE, PermissionConfig.WRITE_EXTERNAL_STORAGE};

    public static void zza(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{"android.permission.READ_PHONE_STATE"}, 88);
    }

    public static void zzb(Activity activity) {
        ActivityCompat.requestPermissions(activity, zza, 90);
    }
}
