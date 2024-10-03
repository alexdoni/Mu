package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzik implements zzjc {
    final /* synthetic */ zzii zza;
    final /* synthetic */ zzjc zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzik(zzii zziiVar, zzjc zzjcVar) {
        this.zza = zziiVar;
        this.zzb = zzjcVar;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
        sb.append("propagating=[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzjc
    public final Object zza(Object obj) {
        zzii zzc = zziq.zzc(this.zza);
        try {
            return this.zzb.zza(obj);
        } finally {
            zziq.zzc(zzc);
        }
    }
}
