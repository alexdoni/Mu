package com.google.android.gms.internal.recaptcha;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
abstract class zztr<T, B> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zza(T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzb(T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract B zzc(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zzd(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zze(T t, T t2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract B zzf();

    abstract T zzg(B b);

    abstract void zzh(B b, int i, int i2);

    abstract void zzi(B b, int i, long j);

    abstract void zzj(B b, int i, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzk(B b, int i, zzpy zzpyVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzl(B b, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzm(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzn(Object obj, B b);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzo(Object obj, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzp(T t, zzuj zzujVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzq(B b, zzsz zzszVar) throws IOException {
        int zzd = zzszVar.zzd();
        int i = zzd >>> 3;
        int i2 = zzd & 7;
        if (i2 == 0) {
            zzl(b, i, zzszVar.zzl());
            return true;
        }
        if (i2 == 1) {
            zzi(b, i, zzszVar.zzk());
            return true;
        }
        if (i2 == 2) {
            zzk(b, i, zzszVar.zzp());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                zzh(b, i, zzszVar.zzf());
                return true;
            }
            throw zzrr.zza();
        }
        B zzf = zzf();
        int i3 = 4 | (i << 3);
        while (zzszVar.zzc() != Integer.MAX_VALUE && zzq(zzf, zzszVar)) {
        }
        if (i3 != zzszVar.zzd()) {
            throw zzrr.zzb();
        }
        zzg(zzf);
        zzj(b, i, zzf);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zzr(zzsz zzszVar);
}
