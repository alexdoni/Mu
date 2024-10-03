package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzcg extends zzck {
    private final zzkj<zzvj> zzb;
    private final zzvf zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcg(zzkj<zzvj> zzkjVar, zzvf zzvfVar) {
        if (zzkjVar != null) {
            this.zzb = zzkjVar;
            if (zzvfVar != null) {
                this.zzc = zzvfVar;
                return;
            }
            throw new NullPointerException("Null mobileDynamicChallengeSignals");
        }
        throw new NullPointerException("Null requestedSignals");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzck) {
            zzck zzckVar = (zzck) obj;
            if (this.zzb.equals(zzckVar.zza()) && this.zzc.equals(zzckVar.zzb())) {
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
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 72 + String.valueOf(valueOf2).length());
        sb.append("OutOfGuardsSignalData{requestedSignals=");
        sb.append(valueOf);
        sb.append(", mobileDynamicChallengeSignals=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzck
    public final zzkj<zzvj> zza() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzck
    public final zzvf zzb() {
        return this.zzc;
    }
}
