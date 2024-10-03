package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import com.google.android.gms.internal.recaptcha.zzsn;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzfz<T extends zzsn> {
    public static <T extends zzsn> zzfy<T> zzi() {
        zzfn zzfnVar = new zzfn();
        zzfnVar.zzh(zzge.zza());
        zzfnVar.zzb(zzhg.zza());
        zzfnVar.zzd(false);
        zzfnVar.zza(false);
        zzfnVar.zzf(true);
        return zzfnVar;
    }

    public abstract Uri zza();

    public abstract zzfp<T> zzb();

    public abstract zzkj<zzfr<T>> zzc();

    public abstract T zzd();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zze();

    public abstract boolean zzf();

    public abstract boolean zzg();

    public abstract zzge zzh();
}
