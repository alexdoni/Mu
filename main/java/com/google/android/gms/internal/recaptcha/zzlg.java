package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzlg extends zzlf {
    final zzlh zza;
    final /* synthetic */ zzli zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzlg(zzli zzliVar, int i) {
        this.zzb = zzliVar;
        this.zza = new zzlh(i);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlf, com.google.android.gms.internal.recaptcha.zzlo
    public final zzlo zza(byte[] bArr, int i, int i2) {
        this.zza.write(bArr, 0, i2);
        return this;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlo
    public final zzlm zzd() {
        return this.zzb.zzb(this.zza.zzb(), 0, this.zza.zza());
    }
}
