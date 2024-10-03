package com.google.android.gms.internal.recaptcha;

import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzjr implements Iterable<String> {
    final /* synthetic */ CharSequence zza;
    final /* synthetic */ zzjt zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjr(zzjt zzjtVar, CharSequence charSequence) {
        this.zzb = zzjtVar;
        this.zza = charSequence;
    }

    @Override // java.lang.Iterable
    public final Iterator<String> iterator() {
        return zzjt.zze(this.zzb, this.zza);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        zzjd.zzb(sb, iterator(), ", ");
        sb.append(']');
        return sb.toString();
    }
}
