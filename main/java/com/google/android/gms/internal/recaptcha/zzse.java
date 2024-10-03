package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzse implements zztb {
    private static final zzsl zza = new zzsc();
    private final zzsl zzb;

    public zzse() {
        zzsl zzslVar;
        zzsl[] zzslVarArr = new zzsl[2];
        zzslVarArr[0] = zzra.zza();
        try {
            zzslVar = (zzsl) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzslVar = zza;
        }
        zzslVarArr[1] = zzslVar;
        zzsd zzsdVar = new zzsd(zzslVarArr);
        zzrp.zzf(zzsdVar, "messageInfoFactory");
        this.zzb = zzsdVar;
    }

    private static boolean zzb(zzsk zzskVar) {
        return zzskVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.recaptcha.zztb
    public final <T> zzta<T> zza(Class<T> cls) {
        zztc.zzG(cls);
        zzsk zzb = this.zzb.zzb(cls);
        if (!zzb.zzb()) {
            if (zzrg.class.isAssignableFrom(cls)) {
                if (zzb(zzb)) {
                    return zzsq.zzj(cls, zzb, zzst.zzb(), zzsa.zze(), zztc.zzB(), zzqu.zzb(), zzsj.zzb());
                }
                return zzsq.zzj(cls, zzb, zzst.zzb(), zzsa.zze(), zztc.zzB(), null, zzsj.zzb());
            }
            if (zzb(zzb)) {
                return zzsq.zzj(cls, zzb, zzst.zza(), zzsa.zzd(), zztc.zzz(), zzqu.zza(), zzsj.zza());
            }
            return zzsq.zzj(cls, zzb, zzst.zza(), zzsa.zzd(), zztc.zzA(), null, zzsj.zza());
        }
        if (zzrg.class.isAssignableFrom(cls)) {
            return zzsr.zzj(zztc.zzB(), zzqu.zzb(), zzb.zza());
        }
        return zzsr.zzj(zztc.zzz(), zzqu.zza(), zzb.zza());
    }
}
