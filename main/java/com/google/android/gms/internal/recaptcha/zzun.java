package com.google.android.gms.internal.recaptcha;

import java.text.SimpleDateFormat;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzun {
    public static final zztp zza;
    public static final zztp zzb;
    public static final zztp zzc;
    private static final ThreadLocal<SimpleDateFormat> zzd;

    static {
        zzto zzf = zztp.zzf();
        zzf.zzb(-62135596800L);
        zzf.zza(0);
        zza = zzf.zzk();
        zzto zzf2 = zztp.zzf();
        zzf2.zzb(253402300799L);
        zzf2.zza(999999999);
        zzb = zzf2.zzk();
        zzto zzf3 = zztp.zzf();
        zzf3.zzb(0L);
        zzf3.zza(0);
        zzc = zzf3.zzk();
        zzd = new zzum();
    }

    public static zzqo zza(zztp zztpVar, zztp zztpVar2) {
        zzb(zztpVar);
        zzb(zztpVar2);
        long zzc2 = zzml.zzc(zztpVar2.zze(), zztpVar.zze());
        int zzd2 = zztpVar2.zzd();
        int zzd3 = zztpVar.zzd();
        long j = zzd2 - zzd3;
        int i = (int) j;
        if (j == i) {
            return zzuk.zzc(zzc2, i);
        }
        StringBuilder sb = new StringBuilder(51);
        sb.append("overflow: checkedSubtract(");
        sb.append(zzd2);
        sb.append(", ");
        sb.append(zzd3);
        sb.append(")");
        throw new ArithmeticException(sb.toString());
    }

    public static zztp zzb(zztp zztpVar) {
        long zze = zztpVar.zze();
        int zzd2 = zztpVar.zzd();
        if (zze < -62135596800L || zze > 253402300799L || zzd2 < 0 || zzd2 >= 1000000000) {
            throw new IllegalArgumentException(String.format("Timestamp is not valid. See proto definition for valid values. Seconds (%s) must be in range [-62,135,596,800, +253,402,300,799]. Nanos (%s) must be in range [0, +999,999,999].", Long.valueOf(zze), Integer.valueOf(zzd2)));
        }
        return zztpVar;
    }

    public static zztp zzc(long j) {
        return zzd(j / 1000, (int) ((j % 1000) * 1000000));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zztp zzd(long j, int i) {
        long j2 = i;
        if (j2 <= -1000000000 || j2 >= 1000000000) {
            j = zzml.zza(j, j2 / 1000000000);
            i = (int) (j2 % 1000000000);
        }
        if (i < 0) {
            i = (int) (i + 1000000000);
            j = zzml.zzc(j, 1L);
        }
        zzto zzf = zztp.zzf();
        zzf.zzb(j);
        zzf.zza(i);
        zztp zzk = zzf.zzk();
        zzb(zzk);
        return zzk;
    }
}
