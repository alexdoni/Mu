package com.google.android.gms.internal.recaptcha;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzsr<T> implements zzta<T> {
    private final zzsn zza;
    private final zztr<?, ?> zzb;
    private final boolean zzc;
    private final zzqs<?> zzd;

    private zzsr(zztr<?, ?> zztrVar, zzqs<?> zzqsVar, zzsn zzsnVar) {
        this.zzb = zztrVar;
        this.zzc = zzqsVar.zzh(zzsnVar);
        this.zzd = zzqsVar;
        this.zza = zzsnVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> zzsr<T> zzj(zztr<?, ?> zztrVar, zzqs<?> zzqsVar, zzsn zzsnVar) {
        return new zzsr<>(zztrVar, zzqsVar, zzsnVar);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final int zza(T t) {
        zztr<?, ?> zztrVar = this.zzb;
        int zzb = zztrVar.zzb(zztrVar.zzd(t));
        if (!this.zzc) {
            return zzb;
        }
        this.zzd.zza(t);
        throw null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final int zzb(T t) {
        int hashCode = this.zzb.zzd(t).hashCode();
        if (!this.zzc) {
            return hashCode;
        }
        this.zzd.zza(t);
        throw null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final T zzc() {
        return (T) this.zza.zzK().zzm();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final void zzd(T t) {
        this.zzb.zzm(t);
        this.zzd.zze(t);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final void zze(T t, T t2) {
        zztc.zzF(this.zzb, t, t2);
        if (this.zzc) {
            zztc.zzE(this.zzd, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final void zzf(T t, zzsz zzszVar, zzqr zzqrVar) throws IOException {
        boolean zzP;
        zztr<?, ?> zztrVar = this.zzb;
        zzqs<?> zzqsVar = this.zzd;
        Object zzc = zztrVar.zzc(t);
        zzqw<?> zzb = zzqsVar.zzb(t);
        while (zzszVar.zzc() != Integer.MAX_VALUE) {
            try {
                int zzd = zzszVar.zzd();
                if (zzd != 11) {
                    if ((zzd & 7) == 2) {
                        Object zzc2 = zzqsVar.zzc(zzqrVar, this.zza, zzd >>> 3);
                        if (zzc2 != null) {
                            zzqsVar.zzf(zzszVar, zzc2, zzqrVar, zzb);
                        } else {
                            zzP = zztrVar.zzq(zzc, zzszVar);
                        }
                    } else {
                        zzP = zzszVar.zzP();
                    }
                    if (!zzP) {
                        return;
                    }
                } else {
                    Object obj = null;
                    int i = 0;
                    zzpy zzpyVar = null;
                    while (zzszVar.zzc() != Integer.MAX_VALUE) {
                        int zzd2 = zzszVar.zzd();
                        if (zzd2 == 16) {
                            i = zzszVar.zzj();
                            obj = zzqsVar.zzc(zzqrVar, this.zza, i);
                        } else if (zzd2 == 26) {
                            if (obj != null) {
                                zzqsVar.zzf(zzszVar, obj, zzqrVar, zzb);
                            } else {
                                zzpyVar = zzszVar.zzp();
                            }
                        } else if (!zzszVar.zzP()) {
                            break;
                        }
                    }
                    if (zzszVar.zzd() != 12) {
                        throw zzrr.zzb();
                    }
                    if (zzpyVar != null) {
                        if (obj != null) {
                            zzqsVar.zzg(zzpyVar, obj, zzqrVar, zzb);
                        } else {
                            zztrVar.zzk(zzc, i, zzpyVar);
                        }
                    }
                }
            } finally {
                zztrVar.zzn(t, zzc);
            }
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final void zzg(T t, zzuj zzujVar) throws IOException {
        this.zzd.zza(t);
        throw null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final boolean zzh(T t, T t2) {
        if (!this.zzb.zzd(t).equals(this.zzb.zzd(t2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zza(t);
        this.zzd.zza(t2);
        throw null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzta
    public final boolean zzi(T t) {
        this.zzd.zza(t);
        throw null;
    }
}
