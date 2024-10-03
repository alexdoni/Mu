package com.google.android.gms.internal.recaptcha;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.provider.Settings;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.luck.picture.lib.config.PictureMimeType;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzcq {
    private static final String zza = zzcn.zza(5);

    public static final zzpy zza(zzvj zzvjVar, Context context, zzci zzciVar) throws zzcj, zzcm {
        long j;
        zzvj zzvjVar2 = zzvj.MOBILE_SIGNALS_UNKNOWN;
        switch (zzvjVar.ordinal()) {
            case 1:
                Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                if (registerReceiver == null) {
                    throw new zzcm(1);
                }
                int intExtra = (registerReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1) * 100) / registerReceiver.getIntExtra("scale", -1);
                zzciVar.zzb(intExtra);
                return zzco.zza(intExtra);
            case 2:
                AudioManager audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
                if (audioManager == null) {
                    throw new zzcm(2);
                }
                return zzco.zza((audioManager.getStreamVolume(3) * 100) / audioManager.getStreamMaxVolume(3));
            case 3:
                ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                if (activityManager == null) {
                    throw new zzcm(3);
                }
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                activityManager.getMemoryInfo(memoryInfo);
                int i = (int) ((memoryInfo.availMem * 100) / memoryInfo.totalMem);
                zzciVar.zzd(i);
                return zzco.zza(i);
            case 4:
                PackageManagerWrapper packageManager = Wrappers.packageManager(context);
                String packageName = context.getPackageName();
                if (packageManager == null) {
                    throw new zzcm(4);
                }
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
                    if (Build.VERSION.SDK_INT >= 28) {
                        j = packageInfo.getLongVersionCode();
                    } else {
                        j = packageInfo.versionCode;
                    }
                    ByteBuffer order = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
                    order.putLong(j).rewind();
                    return zzpy.zzm(order);
                } catch (PackageManager.NameNotFoundException e) {
                    zzak.zza(zza, e);
                    throw new zzcm(4);
                }
            case 5:
                PackageManagerWrapper packageManager2 = Wrappers.packageManager(context);
                String packageName2 = context.getPackageName();
                if (packageManager2 == null) {
                    throw new zzcm(5);
                }
                try {
                    PackageInfo packageInfo2 = packageManager2.getPackageInfo(packageName2, 4096);
                    zzkm zzkmVar = new zzkm();
                    ByteBuffer order2 = ByteBuffer.allocate(packageInfo2.requestedPermissions.length * 3).order(ByteOrder.LITTLE_ENDIAN);
                    for (String str : packageInfo2.requestedPermissions) {
                        byte[] copyOf = Arrays.copyOf(zzlq.zza().zzc(str, Charset.forName("UTF-8")).zze(), 3);
                        order2.put(copyOf);
                        zzkmVar.zzc((zzkm) zzco.zzb(copyOf));
                    }
                    zzciVar.zzc(zzkmVar.zzd());
                    order2.rewind();
                    return zzpy.zzm(order2);
                } catch (PackageManager.NameNotFoundException e2) {
                    zzak.zza(zza, e2);
                    throw new zzcm(5);
                }
            case 6:
                return zzco.zza(zzb(context.getContentResolver()));
            case 7:
                if (!(context instanceof Activity)) {
                    throw new zzcm(7);
                }
                String name = ((Activity) context).getClass().getName();
                zzciVar.zza(name);
                return zzpy.zzp(name);
            default:
                throw new zzcj(zzvjVar.zza());
        }
    }

    private static int zzb(ContentResolver contentResolver) throws zzcm {
        try {
            return Settings.System.getInt(contentResolver, "screen_brightness");
        } catch (Settings.SettingNotFoundException e) {
            zzak.zza(zza, e);
            throw new zzcm(6);
        }
    }
}
