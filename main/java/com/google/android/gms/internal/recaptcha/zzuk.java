package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzuk {
    public static final zzqo zza;
    public static final zzqo zzb;
    public static final zzqo zzc;

    static {
        zzqn zzf = zzqo.zzf();
        zzf.zzb(-315576000000L);
        zzf.zza(-999999999);
        zza = zzf.zzk();
        zzqn zzf2 = zzqo.zzf();
        zzf2.zzb(315576000000L);
        zzf2.zza(999999999);
        zzb = zzf2.zzk();
        zzqn zzf3 = zzqo.zzf();
        zzf3.zzb(0L);
        zzf3.zza(0);
        zzc = zzf3.zzk();
    }

    public static zzqo zza(zzqo zzqoVar) {
        long zze = zzqoVar.zze();
        int zzd = zzqoVar.zzd();
        if (zze >= -315576000000L && zze <= 315576000000L) {
            long j = zzd;
            if (j >= -999999999 && j < 1000000000 && ((zze >= 0 && zzd >= 0) || (zze <= 0 && zzd <= 0))) {
                return zzqoVar;
            }
        }
        throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", Long.valueOf(zze), Integer.valueOf(zzd)));
    }

    public static zzqo zzb(long j) {
        return zzc(j, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzqo zzc(long j, int i) {
        long j2 = i;
        if (j2 <= -1000000000 || j2 >= 1000000000) {
            j = zzml.zza(j, j2 / 1000000000);
            i = (int) (j2 % 1000000000);
        }
        if (j > 0 && i < 0) {
            i = (int) (i + 1000000000);
            j--;
        }
        if (j < 0 && i > 0) {
            i = (int) (i - 1000000000);
            j++;
        }
        zzqn zzf = zzqo.zzf();
        zzf.zzb(j);
        zzf.zza(i);
        zzqo zzk = zzf.zzk();
        zza(zzk);
        return zzk;
    }
}
