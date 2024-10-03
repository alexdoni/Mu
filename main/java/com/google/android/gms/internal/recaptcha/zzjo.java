package com.google.android.gms.internal.recaptcha;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzjo<T> extends zzjj<T> {
    private final T zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjo(T t) {
        this.zza = t;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzjo) {
            return this.zza.equals(((zzjo) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
        sb.append("Optional.of(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzjj
    public final T zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzjj
    public final T zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzjj
    public final boolean zzc() {
        return true;
    }
}
