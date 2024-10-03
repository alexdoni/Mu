package com.google.android.gms.internal.recaptcha;

import android.util.Log;
import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzdv {
    private static final Method zza;
    private static final Method zzb;
    private static final Method zzc;
    private static final Method zzd;

    static {
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Class<?> cls;
        try {
            try {
                cls = Class.forName("android.os.SystemProperties");
                method = cls.getMethod("get", String.class, String.class);
            } catch (Exception e) {
                e = e;
                method = null;
                method2 = null;
            } catch (Throwable th) {
                th = th;
                method = null;
                method2 = null;
            }
            try {
                method2 = cls.getMethod("getInt", String.class, Integer.TYPE);
                try {
                    method4 = cls.getMethod("getLong", String.class, Long.TYPE);
                } catch (Exception e2) {
                    e = e2;
                    method4 = null;
                } catch (Throwable th2) {
                    th = th2;
                    method3 = null;
                    zza = method;
                    zzb = method2;
                    zzc = method3;
                    zzd = null;
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                method2 = null;
                method4 = method2;
                e.printStackTrace();
                zza = method;
                zzb = method2;
                zzc = method4;
                zzd = null;
            } catch (Throwable th3) {
                th = th3;
                method2 = null;
                method3 = method2;
                zza = method;
                zzb = method2;
                zzc = method3;
                zzd = null;
                throw th;
            }
            try {
                Method method5 = cls.getMethod("getBoolean", String.class, Boolean.TYPE);
                zza = method;
                zzb = method2;
                zzc = method4;
                zzd = method5;
            } catch (Exception e4) {
                e = e4;
                e.printStackTrace();
                zza = method;
                zzb = method2;
                zzc = method4;
                zzd = null;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static String zza(String str, @Nullable String str2) {
        try {
            return (String) zza.invoke(null, "tiktok_systrace", JsonSerializer.False);
        } catch (Exception e) {
            Log.e("SystemProperties", "get error", e);
            return JsonSerializer.False;
        }
    }
}
