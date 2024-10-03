package com.google.android.gms.internal.recaptcha;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzqc {
    public static final /* synthetic */ int zzd = 0;
    private static volatile int zze = 100;
    int zza;
    final int zzb = zze;
    zzqd zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzqc(zzpz zzpzVar) {
    }

    public static int zzF(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zzG(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzqc zzH(byte[] bArr, int i, int i2, boolean z) {
        zzqa zzqaVar = new zzqa(bArr, 0, 0, false, null);
        try {
            zzqaVar.zze(0);
            return zzqaVar;
        } catch (zzrr e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract void zzA(int i);

    public abstract boolean zzC() throws IOException;

    public abstract boolean zzD() throws IOException;

    public abstract boolean zzE(int i) throws IOException;

    public abstract double zzb() throws IOException;

    public abstract float zzc() throws IOException;

    public abstract int zzd();

    public abstract int zze(int i) throws zzrr;

    public abstract int zzf() throws IOException;

    public abstract int zzg() throws IOException;

    public abstract int zzh() throws IOException;

    public abstract int zzk() throws IOException;

    public abstract int zzl() throws IOException;

    public abstract int zzm() throws IOException;

    public abstract int zzn() throws IOException;

    public abstract long zzo() throws IOException;

    public abstract long zzp() throws IOException;

    public abstract long zzt() throws IOException;

    public abstract long zzu() throws IOException;

    public abstract long zzv() throws IOException;

    public abstract zzpy zzw() throws IOException;

    public abstract String zzx() throws IOException;

    public abstract String zzy() throws IOException;

    public abstract void zzz(int i) throws zzrr;
}
