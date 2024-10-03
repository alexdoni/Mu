package com.google.android.play.core.integrity;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
final class zzc extends IntegrityTokenRequest {
    private final String zza;
    private final Long zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzc(String str, Long l, zzb zzbVar) {
        this.zza = str;
        this.zzb = l;
    }

    @Override // com.google.android.play.core.integrity.IntegrityTokenRequest
    public final Long cloudProjectNumber() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        Long l;
        if (obj == this) {
            return true;
        }
        if (obj instanceof IntegrityTokenRequest) {
            IntegrityTokenRequest integrityTokenRequest = (IntegrityTokenRequest) obj;
            if (this.zza.equals(integrityTokenRequest.nonce()) && ((l = this.zzb) != null ? l.equals(integrityTokenRequest.cloudProjectNumber()) : integrityTokenRequest.cloudProjectNumber() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        Long l = this.zzb;
        return hashCode ^ (l == null ? 0 : l.hashCode());
    }

    @Override // com.google.android.play.core.integrity.IntegrityTokenRequest
    public final String nonce() {
        return this.zza;
    }

    public final String toString() {
        String str = this.zza;
        String valueOf = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(str.length() + 50 + String.valueOf(valueOf).length());
        sb.append("IntegrityTokenRequest{nonce=");
        sb.append(str);
        sb.append(", cloudProjectNumber=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
