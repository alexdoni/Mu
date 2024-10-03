package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzib extends zzht<zzib> implements zzhv {
    static final zzhu zza = new zzhu();
    private final zzhu zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzib() {
        super("", zzhx.zzb().zzc());
        this.zzb = zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzhv
    public final zzii zzd(String str, zzih zzihVar, boolean z) {
        if (z) {
            int i = zziq.zzb;
        }
        return new zzic(str, this, zzihVar, z);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzhv
    public final /* bridge */ /* synthetic */ Exception zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzii
    public final zzii zzf(String str, zzih zzihVar) {
        int i = zziq.zzb;
        return zzd(str, zzihVar, true);
    }
}
