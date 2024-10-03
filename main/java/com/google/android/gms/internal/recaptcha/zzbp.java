package com.google.android.gms.internal.recaptcha;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.HttpStatusException;
import com.google.android.gms.recaptcha.RecaptchaAction;
import com.google.android.gms.recaptcha.RecaptchaHandle;
import com.google.android.gms.recaptcha.RecaptchaNetworkException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzbp {
    public static final /* synthetic */ int zza = 0;
    private static final ExecutorService zzb = zzbz.zza;
    private final Context zzc;
    private final zzcs zzd;
    private final zzbw zze;
    private final zzcf zzf;

    public zzbp(Context context) {
        zzcs zza2 = zzct.zza();
        zzbw zza3 = zzbw.zza(context);
        zzcf zza4 = zzcf.zza(context);
        this.zzc = context;
        this.zzd = zza2;
        this.zze = zza3;
        this.zzf = zza4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzd(zzac zzacVar, Status status) {
        try {
            zzacVar.zzc(status, null);
        } catch (RemoteException e) {
            zzak.zza("RecaptchaOPExecute", e);
        }
    }

    public final /* synthetic */ zzop zza(final RecaptchaHandle recaptchaHandle, String str, RecaptchaAction recaptchaAction, long j, Map map, zzcl zzclVar, final String str2) throws Exception {
        String recaptchaAction2 = recaptchaAction.toString();
        String verificationHistoryToken = recaptchaAction.getVerificationHistoryToken();
        zzbh zzbhVar = new zzbh(zzbz.zza(), zzb, zzvd.zze());
        zzuz zzd = zzvb.zzd();
        zzd.zzd(str);
        zzd.zzc(recaptchaAction2);
        zzd.zzt(j);
        zzd.zzs(verificationHistoryToken);
        zzd.zzr(str2);
        zzd.zza(map);
        zzd.zzq(true);
        zzd.zzb(zzclVar.zza());
        zzd.zze(zzclVar.zzb());
        return zzof.zzj(zzbhVar.zzb(zzd.zzk()), new zzjc() { // from class: com.google.android.gms.internal.recaptcha.zzbk
            @Override // com.google.android.gms.internal.recaptcha.zzjc
            public final Object zza(Object obj) {
                return zzbp.this.zzc(recaptchaHandle, str2, (zzvd) obj);
            }
        }, zzow.zzb());
    }

    public final /* synthetic */ zzop zzb(final RecaptchaAction recaptchaAction, zzcp zzcpVar, final RecaptchaHandle recaptchaHandle, zzdn zzdnVar) throws Exception {
        final String zzf = zzdnVar.zzf();
        zztp zza2 = this.zzd.zza();
        zzun.zzb(zza2);
        final long zza3 = zzml.zza(zzml.zzb(zza2.zze(), 1000L), zza2.zzd() / 1000000);
        List<String> zzg = zzdnVar.zzg();
        Bundle additionalArgs = recaptchaAction.getAdditionalArgs();
        final HashMap hashMap = new HashMap();
        for (String str : additionalArgs.keySet()) {
            if (zzg.contains(str)) {
                Object obj = additionalArgs.get(str);
                if (obj instanceof String) {
                    hashMap.put(str, (String) obj);
                } else {
                    throw new zzbi("Only string values are allowed as an additional arg in RecaptchaAction");
                }
            } else {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 60);
                sb.append("AdditionalArgs key[ \"");
                sb.append(str);
                sb.append("\" ] is not accepted by reCATPCHA server");
                throw new zzbi(sb.toString());
            }
        }
        final zzcl zza4 = zzcpVar.zza(this.zzc);
        return zzof.zzk(this.zzf.zzb(recaptchaHandle.getSiteKey(), recaptchaHandle.getClientPackageName()), new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzbm
            @Override // com.google.android.gms.internal.recaptcha.zzng
            public final zzop zza(Object obj2) {
                return zzbp.this.zza(recaptchaHandle, zzf, recaptchaAction, zza3, hashMap, zza4, (String) obj2);
            }
        }, zzb);
    }

    public final /* synthetic */ String zzc(RecaptchaHandle recaptchaHandle, String str, zzvd zzvdVar) {
        this.zzf.zzd(recaptchaHandle.getSiteKey(), recaptchaHandle.getClientPackageName(), str, "RecaptchaOPExecute");
        return zzvdVar.zzf();
    }

    public final void zze(zzac zzacVar, final RecaptchaHandle recaptchaHandle, final RecaptchaAction recaptchaAction, final zzcp zzcpVar) {
        zzop zzc = zzof.zzc(this.zze.zzc(recaptchaHandle.getSiteKey(), recaptchaHandle.getClientPackageName(), zzcpVar), Exception.class, zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzbn
            @Override // com.google.android.gms.internal.recaptcha.zzng
            public final zzop zza(Object obj) {
                Exception exc = (Exception) obj;
                int i = zzbp.zza;
                if ((exc instanceof RecaptchaNetworkException) || (exc instanceof HttpStatusException)) {
                    return zzof.zze(new zzbx(exc));
                }
                return zzof.zze(new zzby(exc));
            }
        }), zzow.zzb());
        zzng zzc2 = zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzbl
            @Override // com.google.android.gms.internal.recaptcha.zzng
            public final zzop zza(Object obj) {
                return zzbp.this.zzb(recaptchaAction, zzcpVar, recaptchaHandle, (zzdn) obj);
            }
        });
        ExecutorService executorService = zzb;
        zzof.zzm(zzof.zzk(zzc, zzc2, executorService), new zzbo(this, zzacVar), executorService);
    }
}
