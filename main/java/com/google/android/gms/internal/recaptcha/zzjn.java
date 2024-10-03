package com.google.android.gms.internal.recaptcha;

import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzjn {
    public static int zzb(int i, int i2, String str) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(zzl(i, i2, FirebaseAnalytics.Param.INDEX));
        }
        return i;
    }

    public static <T> T zzc(@CheckForNull T t, @CheckForNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException((String) obj);
    }

    public static <T> T zzd(@CheckForNull T t, String str, @CheckForNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(zzju.zzb(str, obj));
    }

    public static void zze(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void zzf(boolean z, String str, char c) {
        if (!z) {
            throw new IllegalArgumentException(zzju.zzb(str, Character.valueOf(c)));
        }
    }

    public static void zzg(boolean z, String str, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(zzju.zzb(str, obj));
        }
    }

    public static void zzi(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void zzj(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException((String) obj);
        }
    }

    public static void zzk(boolean z, String str, int i) {
        if (!z) {
            throw new IllegalStateException(zzju.zzb(str, Integer.valueOf(i)));
        }
    }

    private static String zzl(int i, int i2, String str) {
        if (i < 0) {
            return zzju.zzb("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 >= 0) {
            return zzju.zzb("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
        StringBuilder sb = new StringBuilder(26);
        sb.append("negative size: ");
        sb.append(i2);
        throw new IllegalArgumentException(sb.toString());
    }

    public static void zzh(int i, int i2, int i3) {
        String zzl;
        if (i < 0 || i2 < i || i2 > i3) {
            if (i < 0 || i > i3) {
                zzl = zzl(i, i3, "start index");
            } else {
                zzl = (i2 < 0 || i2 > i3) ? zzl(i2, i3, "end index") : zzju.zzb("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            }
            throw new IndexOutOfBoundsException(zzl);
        }
    }

    public static int zza(int i, int i2, String str) {
        String zzb;
        if (i >= 0 && i < i2) {
            return i;
        }
        if (i < 0) {
            zzb = zzju.zzb("%s (%s) must not be negative", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i));
        } else {
            if (i2 < 0) {
                StringBuilder sb = new StringBuilder(26);
                sb.append("negative size: ");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
            }
            zzb = zzju.zzb("%s (%s) must be less than size (%s)", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i), Integer.valueOf(i2));
        }
        throw new IndexOutOfBoundsException(zzb);
    }
}
