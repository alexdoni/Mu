package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzic extends zzhw<zzii> implements zzhv {
    private final Exception zza;
    private final boolean zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzic(String str, zzhv zzhvVar, zzih zzihVar, boolean z) {
        super(str, zzhvVar, zzihVar);
        this.zza = zzhvVar.zze();
        this.zzb = z;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzhv
    public final zzii zzd(String str, zzih zzihVar, boolean z) {
        if (z && !this.zzb) {
            int i = zziq.zzb;
        }
        boolean z2 = true;
        if ((!z || this.zzb) && !this.zzb) {
            z2 = false;
        }
        return new zzic(str, this, zzihVar, z2);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzhv
    public final Exception zze() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzii
    public final zzii zzf(String str, zzih zzihVar) {
        return zzd(str, zzihVar, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzic(String str, zzih zzihVar, boolean z) {
        super(str, zzhx.zzb().zzc(), zzihVar);
        this.zza = zzib.zza;
        this.zzb = z;
    }
}
