package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzce implements zzoa<Void> {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ String zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzce(zzcf zzcfVar, String str, String str2, String str3, String str4) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoa
    public final void zza(Throwable th) {
        th.getMessage();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoa
    public final /* bridge */ /* synthetic */ void zzb(Void r3) {
        String.format("A session storage token (%s) is stored for key: %s:%s", this.zzb, this.zzc, this.zzd);
    }
}
