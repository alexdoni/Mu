package com.google.android.gms.internal.recaptcha;

import androidx.collection.SimpleArrayMap;
import javax.annotation.CheckReturnValue;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
@CheckReturnValue
/* loaded from: classes2.dex */
public class zzih {
    private final SimpleArrayMap zza;
    private boolean zzb = false;

    public final String toString() {
        StringBuilder sb = new StringBuilder("SpanExtras<");
        for (zzih zzihVar = this; zzihVar != null; zzihVar = null) {
            for (int i = 0; i < zzihVar.zza.size(); i++) {
                sb.append(this.zza.valueAt(i));
                sb.append("], ");
            }
        }
        sb.append(">");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzih zza() {
        if (!this.zzb) {
            this.zzb = true;
            return this;
        }
        throw new IllegalStateException("Already frozen");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzb() {
        return this.zzb;
    }
}
