package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzgb {
    private final Map<Uri, zzfx<?>> zza = new HashMap();
    private final Map<Uri, zzfz<?>> zzb = new HashMap();
    private final Executor zzc;
    private final zzed zzd;
    private final zzng<Uri, String> zze;
    private final Map<String, zzhd> zzf;
    private final zzhh zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public zzgb(Executor executor, Executor executor2, zzed zzedVar, zzhh zzhhVar, @Nullable Map<String, zzhd> map, zzhk zzhkVar) {
        executor.getClass();
        this.zzc = executor;
        executor2.getClass();
        this.zzd = executor2;
        this.zzg = zzedVar;
        this.zzf = zzhhVar;
        zzjn.zze(!zzhhVar.isEmpty());
        this.zze = new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzga
            @Override // com.google.android.gms.internal.recaptcha.zzng
            public final zzop zza(Object obj) {
                return zzof.zzf("");
            }
        };
    }

    private final synchronized <T extends zzsn> zzfx<T> zzb(zzfz<T> zzfzVar) {
        zzfx<T> zzfxVar;
        Uri zza = zzfzVar.zza();
        zzfxVar = (zzfx) this.zza.get(zza);
        if (zzfxVar == null) {
            Uri zza2 = zzfzVar.zza();
            zzjn.zzg(zza2.isHierarchical(), "Uri must be hierarchical: %s", zza2);
            String zzc = zzju.zzc(zza2.getLastPathSegment());
            int lastIndexOf = zzc.lastIndexOf(46);
            boolean z = true;
            zzjn.zzg((lastIndexOf == -1 ? "" : zzc.substring(lastIndexOf + 1)).equals("pb"), "Uri extension must be .pb: %s", zza2);
            zzhd zzhdVar = this.zzf.get("singleproc");
            if (zzhdVar == null) {
                z = false;
            }
            zzjn.zzg(z, "No XDataStoreVariantFactory registered for ID %s", "singleproc");
            String zzc2 = zzju.zzc(zzfzVar.zza().getLastPathSegment());
            int lastIndexOf2 = zzc2.lastIndexOf(46);
            if (lastIndexOf2 != -1) {
                zzc2 = zzc2.substring(0, lastIndexOf2);
            }
            zzfxVar = new zzfx<>(zzhdVar.zzb(zzfzVar, zzc2, this.zzc, this.zzd, 1), this.zzg, zzof.zzk(zzof.zzf(zzfzVar.zza()), this.zze, zzow.zzb()), zzfzVar.zzf(), zzfzVar.zze(), null);
            zzkj<zzfr<T>> zzc3 = zzfzVar.zzc();
            if (!zzc3.isEmpty()) {
                zzfxVar.zzn(zzfw.zzb(zzc3, this.zzc));
            }
            this.zza.put(zza, zzfxVar);
            this.zzb.put(zza, zzfzVar);
        } else {
            zzjn.zzg(zzfzVar.equals(this.zzb.get(zza)), "Arguments must match previous call for Uri: %s", zza);
        }
        return zzfxVar;
    }

    public final <T extends zzsn> zzfx<T> zza(zzfz<T> zzfzVar) {
        return zzb(zzfzVar);
    }
}
