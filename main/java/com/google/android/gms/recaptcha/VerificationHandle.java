package com.google.android.gms.recaptcha;

import com.google.android.gms.internal.recaptcha.zzcs;
import com.google.android.gms.internal.recaptcha.zzqo;
import com.google.android.gms.internal.recaptcha.zztp;
import com.google.android.gms.internal.recaptcha.zzuk;
import com.google.android.gms.internal.recaptcha.zzun;
import com.google.android.gms.internal.recaptcha.zzvo;
import com.google.android.gms.internal.recaptcha.zzvs;
import com.google.android.gms.internal.recaptcha.zzvt;
import com.google.android.gms.internal.recaptcha.zzvu;
import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class VerificationHandle implements Serializable {
    public static VerificationHandle zzd(zzvo zzvoVar, zzcs zzcsVar, String str) {
        return zzg(zzvoVar.zzi(), zzvoVar.zzf(), str, "", zzcsVar);
    }

    public static VerificationHandle zze(zzvs zzvsVar, zzcs zzcsVar, String str) {
        return zzg(zzvsVar.zzj(), zzvsVar.zzf(), str, "", zzcsVar);
    }

    public static VerificationHandle zzf(String str, zzcs zzcsVar, String str2) {
        zzvt zzf = zzvu.zzf();
        zzf.zzb(0L);
        zzf.zza(0);
        return zzg("", zzf.zzk(), str2, str, zzcsVar);
    }

    private static VerificationHandle zzg(String str, zzvu zzvuVar, String str2, String str3, zzcs zzcsVar) {
        return new zza(str, str2, zzvuVar.zze(), zzvuVar.zzd(), str3, zzcsVar, zzcsVar.zza(), zzuk.zzb(zzvuVar.zze() * 60));
    }

    public abstract int getCodeLength();

    public abstract String getOperationAbortedToken();

    public abstract String getSiteKey();

    public abstract long getTimeoutMinutes();

    public abstract String getVerificationToken();

    public boolean isValid() {
        zzqo zza = zzun.zza(zzc(), zza().zza());
        zzqo zzb = zzb();
        zzuk.zza(zza);
        zzuk.zza(zzb);
        int i = (zza.zze() > zzb.zze() ? 1 : (zza.zze() == zzb.zze() ? 0 : -1));
        if (i == 0) {
            int zzd = zza.zzd();
            int zzd2 = zzb.zzd();
            i = zzd == zzd2 ? 0 : zzd < zzd2 ? -1 : 1;
        }
        return i < 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract zzcs zza();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract zzqo zzb();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract zztp zzc();
}
