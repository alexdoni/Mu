package com.google.android.gms.internal.recaptcha;

import android.os.Build;
import android.os.Trace;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.WeakHashMap;
import javax.annotation.CheckReturnValue;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zziq {
    public static final /* synthetic */ int zzb = 0;
    static final zzds zza = new zzds("tiktok_systrace");
    private static final WeakHashMap<Thread, zzis> zzc = new WeakHashMap<>();
    private static final ThreadLocal<zzis> zzd = new zzip();
    private static final Deque<Object> zze = new ArrayDeque();
    private static final Deque<zzii> zzf = new ArrayDeque();
    private static final Object zzg = new Object();
    private static final Runnable zzh = new Runnable() { // from class: com.google.android.gms.internal.recaptcha.zzin
        @Override // java.lang.Runnable
        public final void run() {
            zziq.zzg();
        }
    };
    private static final Runnable zzi = new Runnable() { // from class: com.google.android.gms.internal.recaptcha.zzio
        @Override // java.lang.Runnable
        public final void run() {
            zziq.zzh();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    /* loaded from: classes2.dex */
    public final class zza {
        public static boolean zza() {
            return Trace.isEnabled();
        }
    }

    static zzii zza() {
        return zzd.get().zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzii zzb() {
        zzii zza2 = zza();
        return zza2 == null ? new zzib() : zza2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzii zzc(zzii zziiVar) {
        return zzk(zzd.get(), zziiVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzd(zzii zziiVar) {
        if (zziiVar.zza() == null) {
            return zziiVar.zzb();
        }
        String zzd2 = zzd(zziiVar.zza());
        String zzb2 = zziiVar.zzb();
        StringBuilder sb = new StringBuilder(String.valueOf(zzd2).length() + 4 + String.valueOf(zzb2).length());
        sb.append(zzd2);
        sb.append(" -> ");
        sb.append(zzb2);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzg() {
        Object remove = zze.remove();
        if (remove == zzg) {
            zzf.pop();
        } else {
            zzf.push((zzii) remove);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh() {
        zzc(null);
        zze.clear();
        zzdw.zza().removeCallbacks(zzh);
        zzf.clear();
    }

    @CheckReturnValue
    public static zzie zzi(String str, int i) {
        return zzj(str, 1, zzig.zza, true);
    }

    @CheckReturnValue
    public static zzie zzj(String str, int i, zzih zzihVar, boolean z) {
        zzii zzf2;
        zzii zza2 = zza();
        if (zza2 == null) {
            zzf2 = new zzic(str, zzihVar, z);
        } else if (zza2 instanceof zzhv) {
            zzf2 = ((zzhv) zza2).zzd(str, zzihVar, z);
        } else {
            zzf2 = zza2.zzf(str, zzihVar);
        }
        zzc(zzf2);
        return new zzie(zzf2);
    }

    private static zzii zzk(zzis zzisVar, zzii zziiVar) {
        boolean zza2;
        zzii zziiVar2 = zzisVar.zzc;
        if (zziiVar2 == zziiVar) {
            return zziiVar;
        }
        if (zziiVar2 == null) {
            if (Build.VERSION.SDK_INT >= 29) {
                zza2 = zza.zza();
            } else {
                zza2 = zzdu.zza(zza);
            }
            zzisVar.zzb = zza2;
        }
        if (zzisVar.zzb) {
            zzo(zziiVar2, zziiVar);
        }
        zzisVar.zzc = zziiVar;
        zzir zzirVar = zzisVar.zza;
        return zziiVar2;
    }

    private static void zzl(String str) {
        if (str.length() > 127) {
            str = str.substring(0, 127);
        }
        Trace.beginSection(str);
    }

    private static void zzm(zzii zziiVar) {
        if (zziiVar.zza() != null) {
            zzm(zziiVar.zza());
        }
        zzl(zziiVar.zzb());
    }

    private static void zzn(zzii zziiVar) {
        Trace.endSection();
        if (zziiVar.zza() != null) {
            zzn(zziiVar.zza());
        }
    }

    private static void zzo(zzii zziiVar, zzii zziiVar2) {
        if (zziiVar != null) {
            if (zziiVar2 != null) {
                if (zziiVar.zza() == zziiVar2) {
                    Trace.endSection();
                    return;
                } else if (zziiVar == zziiVar2.zza()) {
                    zzl(zziiVar2.zzb());
                    return;
                }
            }
            zzn(zziiVar);
        }
        if (zziiVar2 != null) {
            zzm(zziiVar2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzf(zzii zziiVar) {
        zziiVar.getClass();
        zzis zzisVar = zzd.get();
        zzii zziiVar2 = zzisVar.zzc;
        String zzb2 = zziiVar2.zzb();
        String zzb3 = zziiVar.zzb();
        if (zziiVar != zziiVar2) {
            throw new IllegalStateException(zzju.zzb("Wrong trace, expected %s but got %s", zzb2, zzb3));
        }
        zzk(zzisVar, zziiVar2.zza());
    }
}
