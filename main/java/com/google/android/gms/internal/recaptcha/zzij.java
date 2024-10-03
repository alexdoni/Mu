package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzij implements zznf {
    final /* synthetic */ zzii zza;
    final /* synthetic */ zznf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzij(zzii zziiVar, zznf zznfVar) {
        this.zza = zziiVar;
        this.zzb = zznfVar;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
        sb.append("propagating=[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zznf
    public final zzop zza() throws Exception {
        zzii zzc = zziq.zzc(this.zza);
        try {
            return this.zzb.zza();
        } finally {
            zziq.zzc(zzc);
        }
    }
}
