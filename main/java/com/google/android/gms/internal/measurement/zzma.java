package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
/* loaded from: classes2.dex */
abstract class zzma<T, B> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zza(T t);

    abstract B zza();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zza(T t, T t2);

    abstract void zza(B b, int i, int i2);

    abstract void zza(B b, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(B b, int i, zzhm zzhmVar);

    abstract void zza(B b, int i, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(T t, zzmw zzmwVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zza(zzlc zzlcVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzb(T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(B b, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(T t, zzmw zzmwVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(Object obj, B b);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract B zzc(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzc(Object obj, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zzd(Object obj);

    abstract T zze(B b);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzf(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(B b, zzlc zzlcVar) throws IOException {
        int zzd = zzlcVar.zzd();
        int i = zzd >>> 3;
        int i2 = zzd & 7;
        if (i2 == 0) {
            zzb(b, i, zzlcVar.zzl());
            return true;
        }
        if (i2 == 1) {
            zza((zzma<T, B>) b, i, zzlcVar.zzk());
            return true;
        }
        if (i2 == 2) {
            zza((zzma<T, B>) b, i, zzlcVar.zzp());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                zza((zzma<T, B>) b, i, zzlcVar.zzf());
                return true;
            }
            throw zzji.zza();
        }
        B zza = zza();
        int i3 = 4 | (i << 3);
        while (zzlcVar.zzc() != Integer.MAX_VALUE && zza((zzma<T, B>) zza, zzlcVar)) {
        }
        if (i3 != zzlcVar.zzd()) {
            throw zzji.zzb();
        }
        zza((zzma<T, B>) b, i, (int) zze(zza));
        return true;
    }
}
