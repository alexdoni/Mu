package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzsn;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzhi<T extends zzsn> extends zzhj<T> {
    private final T zza;
    private final zzqr zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhi(T t, zzqr zzqrVar) {
        this.zza = t;
        if (zzqrVar != null) {
            this.zzb = zzqrVar;
            return;
        }
        throw new NullPointerException("Null extensionRegistryLite");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzhj) {
            zzhj zzhjVar = (zzhj) obj;
            if (this.zza.equals(zzhjVar.zzc()) && this.zzb.equals(zzhjVar.zzb())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 54 + String.valueOf(valueOf2).length());
        sb.append("ProtoSerializer{defaultValue=");
        sb.append(valueOf);
        sb.append(", extensionRegistryLite=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzhj, com.google.android.gms.internal.recaptcha.zzgd
    public final /* bridge */ /* synthetic */ Object zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzhj
    public final zzqr zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzhj
    public final T zzc() {
        return this.zza;
    }
}
