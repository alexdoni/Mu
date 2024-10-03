package com.p008ld.sdk.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.internal.LDIdentifiers;
import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* compiled from: LDDeviceUtils.kt */
/* loaded from: classes2.dex */
public final class zze {
    public static final zze zza = new zze();

    private zze() {
    }

    public static /* synthetic */ String zza(zze zzeVar, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = "";
        }
        return zzeVar.zza(z, str);
    }

    public final String zza(boolean z, String str) {
        StringBuilder sb = new StringBuilder();
        String appGUID = LDIdentifiers.Companion.getAppGUID();
        String advertId = LDIdentifiers.Companion.getAdvertId();
        sb.append(appGUID);
        sb.append(",");
        sb.append(zzb());
        sb.append(",");
        sb.append(zzc());
        sb.append(",");
        sb.append(advertId);
        if (!z) {
            String str2 = str;
            if (!(str2 == null || str2.length() == 0)) {
                sb.append(",");
                sb.append(str);
            } else {
                LDUtil.toast(zzt.zza(LDSdk.getApp(), "ld_init_fail"));
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
        return sb2;
    }

    @JvmStatic
    public static final String zza() {
        String string = Settings.Secure.getString(LDSdk.getApp().getContentResolver(), "android_id");
        return (Intrinsics.areEqual("9774d56d682e549c", string) || string == null) ? "" : string;
    }

    @JvmStatic
    public static final String zzb() {
        try {
            String str = Build.MANUFACTURER;
            Intrinsics.checkNotNullExpressionValue(str, "{\n            Build.MANUFACTURER\n        }");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @JvmStatic
    public static final String zzc() {
        String str = Build.MODEL;
        if (str == null) {
            return "";
        }
        String str2 = str;
        int length = str2.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str2.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        String obj = str2.subSequence(i, length + 1).toString();
        if (obj == null) {
            return "";
        }
        String replace = new Regex("\\s*").replace(obj, "");
        return replace == null ? "" : replace;
    }

    @JvmStatic
    public static final String zzd() {
        Object m1882constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            Object systemService = LDSdk.getApp().getSystemService("phone");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
            m1882constructorimpl = Result.m1882constructorimpl(((TelephonyManager) systemService).getSimCountryIso());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1885exceptionOrNullimpl = Result.m1885exceptionOrNullimpl(m1882constructorimpl);
        if (m1885exceptionOrNullimpl != null) {
            LDLog.m573e(String.valueOf(m1885exceptionOrNullimpl.getMessage()));
        }
        if (Result.m1888isFailureimpl(m1882constructorimpl)) {
            m1882constructorimpl = "";
        }
        return (String) m1882constructorimpl;
    }

    @JvmStatic
    public static final String zze() {
        Object m1882constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            Object systemService = LDSdk.getApp().getSystemService("phone");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
            m1882constructorimpl = Result.m1882constructorimpl(((TelephonyManager) systemService).getSimOperator());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1885exceptionOrNullimpl = Result.m1885exceptionOrNullimpl(m1882constructorimpl);
        if (m1885exceptionOrNullimpl != null) {
            LDLog.m573e(String.valueOf(m1885exceptionOrNullimpl.getMessage()));
        }
        if (Result.m1888isFailureimpl(m1882constructorimpl)) {
            m1882constructorimpl = "";
        }
        return (String) m1882constructorimpl;
    }

    @JvmStatic
    public static final String zzf() {
        String RELEASE = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(RELEASE, "RELEASE");
        return RELEASE;
    }

    @JvmStatic
    public static final int zzg() {
        return Build.VERSION.SDK_INT;
    }

    @JvmStatic
    public static final int zzh() {
        Object systemService = LDSdk.getApp().getSystemService("window");
        WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        return point.x;
    }

    @JvmStatic
    public static final int zzi() {
        Object systemService = LDSdk.getApp().getSystemService("window");
        WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        return point.y;
    }

    @JvmStatic
    public static final String zzj() {
        String str;
        String str2;
        Locale locale = LDSdk.getApp().getResources().getConfiguration().locale;
        if (locale != null) {
            String language = locale.getLanguage();
            Intrinsics.checkNotNullExpressionValue(language, "locale.language");
            str = language.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase()");
            String country = locale.getCountry();
            Intrinsics.checkNotNullExpressionValue(country, "locale.country");
            str2 = country.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).toUpperCase()");
        } else {
            str = LanguageType.SERVER_EN;
            str2 = "US";
        }
        return str + '-' + str2;
    }

    @JvmStatic
    public static final String zzk() {
        String id = TimeZone.getDefault().getID();
        Intrinsics.checkNotNullExpressionValue(id, "getDefault().id");
        return id;
    }

    @JvmStatic
    public static final boolean zzl() {
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            return Intrinsics.areEqual("harmony", cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final String zzm() {
        String packageName = LDSdk.getApp().getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "getApp().packageName");
        return packageName;
    }

    @JvmStatic
    public static final String zzn() {
        return zza(LDSdk.getApp().getPackageName());
    }

    @JvmStatic
    public static final String zza(String str) {
        String str2 = str;
        String str3 = "";
        if (!(str2 == null || str2.length() == 0)) {
            try {
                PackageManager packageManager = LDSdk.getApp().getPackageManager();
                Intrinsics.checkNotNull(str);
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                if (packageInfo != null) {
                    str3 = packageInfo.versionName;
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            Intrinsics.checkNotNullExpressionValue(str3, "try {\n            val pm…\n            \"\"\n        }");
        }
        return str3;
    }

    @JvmStatic
    public static final String zzo() {
        return zzb(LDSdk.getApp().getPackageName());
    }

    @JvmStatic
    public static final String zzb(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return "";
        }
        try {
            PackageManager packageManager = LDSdk.getApp().getPackageManager();
            Intrinsics.checkNotNull(str);
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo == null) {
                return "";
            }
            String num = Integer.valueOf(packageInfo.versionCode).toString();
            return num == null ? "" : num;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
