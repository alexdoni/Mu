package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zznp implements zznf {
    final /* synthetic */ zzns zza;
    final /* synthetic */ zznf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zznp(zznu zznuVar, zzns zznsVar, zznf zznfVar) {
        this.zza = zznsVar;
        this.zzb = zznfVar;
    }

    public final String toString() {
        return this.zzb.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zznf
    public final zzop zza() throws Exception {
        if (!this.zza.compareAndSet(zznr.NOT_RUN, zznr.STARTED)) {
            return new zzoi();
        }
        return this.zzb.zza();
    }
}
