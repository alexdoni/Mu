package com.google.android.gms.internal.recaptcha;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public class zzgz<T> {
    private final String zza;
    private final zzop<String> zzb;
    private final zzhc<T> zzc;
    private final zznu zzd;
    private final zzhs<Void> zze;
    private final zzia zzh;
    private final zzhh zzj;
    private final zzhs<?> zzf = new zzhs<>(new zzgw(this, null), zzow.zzb());
    private final Object zzg = new Object();
    private List<zzng<zzfq<T>, ?>> zzi = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public zzgz(zzhc zzhcVar, zzhc<T> zzhcVar2, zzhh zzhhVar, zzop<String> zzopVar, boolean z, boolean z2) {
        this.zzc = zzhcVar;
        this.zzj = zzhcVar2;
        this.zzb = zzhhVar;
        this.zza = zzhcVar.zzk();
        final zzgo zzgoVar = (zzgo) zzhcVar;
        this.zze = new zzhs<>(new zznf() { // from class: com.google.android.gms.internal.recaptcha.zzgf
            @Override // com.google.android.gms.internal.recaptcha.zznf
            public final zzop zza() {
                return zzgo.this.zzc();
            }
        }, zzow.zzb());
        if (z) {
            this.zzh = zzia.zzd();
        } else {
            this.zzh = zzia.zzc();
        }
        zzn(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzgq
            @Override // com.google.android.gms.internal.recaptcha.zzng
            public final zzop zza(Object obj) {
                return zzgz.this.zzf((zzfq) obj);
            }
        });
        if (zzopVar != null) {
            this.zzd = zznu.zzc();
        } else {
            this.zzd = null;
        }
    }

    public final zzop<T> zzd() {
        zzop<T> zzopVar;
        this.zzh.zza();
        final zzha zzhaVar = null;
        if (this.zzf.zzh()) {
            zzopVar = this.zzc.zzi(null);
        } else {
            zzia zziaVar = this.zzh;
            String valueOf = String.valueOf(this.zza);
            zzie zzb = zziaVar.zzb(valueOf.length() != 0 ? "Get ".concat(valueOf) : new String("Get "), 1);
            try {
                zzop<T> zzk = zzof.zzk(this.zzf.zzb(), zzim.zzc(new zzng(zzhaVar) { // from class: com.google.android.gms.internal.recaptcha.zzgr
                    @Override // com.google.android.gms.internal.recaptcha.zzng
                    public final zzop zza(Object obj) {
                        return zzgz.this.zze(null, obj);
                    }
                }), zzow.zzb());
                zzb.zza(zzk);
                zzb.close();
                zzopVar = zzk;
            } catch (Throwable th) {
                try {
                    zzb.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        zzof.zzh(this.zzb);
        return zzof.zzh(zzopVar);
    }

    public final /* synthetic */ zzop zze(zzha zzhaVar, Object obj) throws Exception {
        return this.zzc.zzi(null);
    }

    public final /* synthetic */ zzop zzf(zzfq zzfqVar) throws Exception {
        return this.zze.zzb();
    }

    public final /* synthetic */ zzop zzg(zzng zzngVar, Executor executor, Object obj) throws Exception {
        return this.zzc.zzj(zzngVar, executor, null);
    }

    public final /* synthetic */ zzop zzh(zzng zzngVar, Executor executor, Object obj) throws Exception {
        return this.zzc.zzj(zzngVar, executor, null);
    }

    public final zzop<Void> zzi(final zzng<? super T, T> zzngVar, final Executor executor) {
        zzop<Void> zzd;
        zzia zziaVar = this.zzh;
        String valueOf = String.valueOf(this.zza);
        zzie zzb = zziaVar.zzb(valueOf.length() != 0 ? "Update ".concat(valueOf) : new String("Update "), 1);
        try {
            final zzop<?> zzb2 = this.zzf.zzb();
            zznu zznuVar = this.zzd;
            if (zznuVar != null) {
                zzd = zznuVar.zzd(zzim.zzb(new zznf() { // from class: com.google.android.gms.internal.recaptcha.zzgp
                    @Override // com.google.android.gms.internal.recaptcha.zznf
                    public final zzop zza() {
                        final zzgz zzgzVar = zzgz.this;
                        zzop zzopVar = zzb2;
                        final zzng zzngVar2 = zzngVar;
                        final Executor executor2 = executor;
                        return zzof.zzk(zzopVar, zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzgt
                            @Override // com.google.android.gms.internal.recaptcha.zzng
                            public final zzop zza(Object obj) {
                                return zzgz.this.zzh(zzngVar2, executor2, obj);
                            }
                        }), zzow.zzb());
                    }
                }), zzow.zzb());
            } else {
                zzd = zzof.zzk(zzb2, zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzgs
                    @Override // com.google.android.gms.internal.recaptcha.zzng
                    public final zzop zza(Object obj) {
                        return zzgz.this.zzg(zzngVar, executor, obj);
                    }
                }), zzow.zzb());
            }
            zzof.zzh(this.zzb);
            zzb.zza(zzd);
            zzb.close();
            return zzd;
        } catch (Throwable th) {
            try {
                zzb.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final void zzn(zzng<zzfq<T>, ?> zzngVar) {
        synchronized (this.zzg) {
            this.zzi.add(zzngVar);
        }
    }
}
