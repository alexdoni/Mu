package com.google.android.gms.internal.recaptcha;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzeg {
    private final Context zza;
    private final zzet zzb = new zzet();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzeg(Context context, zzef zzefVar) {
        zzfc.zza(context != null, "Context cannot be null", new Object[0]);
        this.zza = context.getApplicationContext();
    }

    public final zzeh zzb() {
        return new zzeh(this, null);
    }
}
