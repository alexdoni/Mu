package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzch extends zzcl {
    private final zzkl<Integer, zzpy> zzb;
    private final zzvh zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzch(zzkl<Integer, zzpy> zzklVar, zzvh zzvhVar) {
        this.zzb = zzklVar;
        if (zzvhVar != null) {
            this.zzc = zzvhVar;
            return;
        }
        throw new NullPointerException("Null mobileDynamicChallengeSignalsResults");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzcl) {
            zzcl zzclVar = (zzcl) obj;
            if (this.zzb.equals(zzclVar.zza()) && this.zzc.equals(zzclVar.zzb())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzb.hashCode() ^ 1000003) * 1000003) ^ this.zzc.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzb);
        String valueOf2 = String.valueOf(this.zzc);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 78 + String.valueOf(valueOf2).length());
        sb.append("OutOfGuardsSignalResults{signalValues=");
        sb.append(valueOf);
        sb.append(", mobileDynamicChallengeSignalsResults=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzcl
    public final zzkl<Integer, zzpy> zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzcl
    public final zzvh zzb() {
        return this.zzc;
    }
}
