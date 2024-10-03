package com.google.android.gms.internal.recaptcha;

import android.content.Context;
import com.facebook.internal.security.CertificateUtil;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzbw {
    private final zzfx<zzdk> zza;
    private final zzcs zzb;
    private final zzcf zzc;

    protected zzbw(zzfx<zzdk> zzfxVar, zzcs zzcsVar, zzcf zzcfVar) {
        this.zza = zzfxVar;
        this.zzb = zzcsVar;
        this.zzc = zzcfVar;
    }

    public static zzbw zza(Context context) {
        return new zzbw(zzcb.zza(context, zzca.zza(context)), zzct.zza(), zzcf.zza(context));
    }

    public final /* synthetic */ zzdn zzb(String str, String str2, zzuv zzuvVar) {
        this.zzc.zzd(str, str2, zzuvVar.zzi(), "RecaptchaLTManager");
        String zzg = zzuvVar.zzg();
        List<String> zzj = zzuvVar.zzj();
        List<zzvj> zzk = zzuvVar.zzk();
        zzvf zzf = zzuvVar.zzf();
        zztp zza = this.zzb.zza();
        zzdm zza2 = zzdn.zza();
        zza2.zzd(zzg);
        zza2.zza(zzj);
        zza2.zzc(zza);
        zza2.zzb(zzk);
        zza2.zze(zzf);
        return zza2.zzk();
    }

    public final zzop<zzdn> zzc(final String str, final String str2, final zzcp zzcpVar) {
        final AtomicReference atomicReference = new AtomicReference(null);
        return zzof.zzj(this.zza.zzi(zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzbv
            @Override // com.google.android.gms.internal.recaptcha.zzng
            public final zzop zza(Object obj) {
                return zzbw.this.zzd(str, str2, zzcpVar, atomicReference, (zzdk) obj);
            }
        }), zzow.zzb()), new zzjc() { // from class: com.google.android.gms.internal.recaptcha.zzbu
            @Override // com.google.android.gms.internal.recaptcha.zzjc
            public final Object zza(Object obj) {
                zzdn zzdnVar = (zzdn) atomicReference.get();
                Object[] objArr = new Object[0];
                if (zzdnVar != null) {
                    return zzdnVar;
                }
                throw new zzjx(zzju.zzb("expected a non-null reference", objArr));
            }
        }, zzow.zzb());
    }

    public final /* synthetic */ zzop zzd(final String str, final String str2, final zzcp zzcpVar, final AtomicReference atomicReference, final zzdk zzdkVar) throws Exception {
        zzjj zzd;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(CertificateUtil.DELIMITER);
        sb.append(str2);
        final String sb2 = sb.toString();
        if (zzdkVar.zzg(sb2)) {
            zzdn zzdnVar = zzdkVar.zzf().get(sb2);
            zzd = (zzdnVar == null || zzun.zza(zzdnVar.zzd(), this.zzb.zza()).zze() > 86400) ? zzjj.zzd() : zzjj.zzf(zzdnVar);
        } else {
            zzd = zzjj.zzd();
        }
        if (!zzd.zzc()) {
            zzbh zzbhVar = new zzbh(zzbz.zzb(), zzbz.zza, zzuv.zze());
            zzup zzd2 = zzuq.zzd();
            zzuw zzd3 = zzux.zzd();
            zzd3.zzc(str);
            zzd3.zzb(str2);
            String valueOf = String.valueOf(zzt.zza());
            zzd3.zza(valueOf.length() != 0 ? ";".concat(valueOf) : new String(";"));
            zzd2.zza(zzd3.zzk());
            zzd2.zzb(true);
            return zzof.zzj(zzof.zzj(zzbhVar.zzb(zzd2.zzk()), new zzjc() { // from class: com.google.android.gms.internal.recaptcha.zzbs
                @Override // com.google.android.gms.internal.recaptcha.zzjc
                public final Object zza(Object obj) {
                    return zzbw.this.zzb(str, str2, (zzuv) obj);
                }
            }, zzow.zzb()), zzim.zza(new zzjc() { // from class: com.google.android.gms.internal.recaptcha.zzbt
                @Override // com.google.android.gms.internal.recaptcha.zzjc
                public final Object zza(Object obj) {
                    zzcp zzcpVar2 = zzcp.this;
                    AtomicReference atomicReference2 = atomicReference;
                    zzdk zzdkVar2 = zzdkVar;
                    String str3 = sb2;
                    zzdn zzdnVar2 = (zzdn) obj;
                    zzcpVar2.zzc(zzck.zzc(zzkj.zzn(zzdnVar2.zzi()), zzdnVar2.zze()));
                    atomicReference2.set(zzdnVar2);
                    zzdh zzv = zzdkVar2.zzv();
                    zzv.zzb(str3, zzdnVar2);
                    return zzv.zzk();
                }
            }), zzow.zzb());
        }
        zzcpVar.zzc(zzck.zzc(zzkj.zzn(((zzdn) zzd.zza()).zzi()), ((zzdn) zzd.zza()).zze()));
        atomicReference.set((zzdn) zzd.zza());
        return zzof.zzf(zzdkVar);
    }
}
