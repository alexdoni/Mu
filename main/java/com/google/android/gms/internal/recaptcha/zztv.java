package com.google.android.gms.internal.recaptcha;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zztv implements Iterator<String> {
    final Iterator<String> zza;
    final /* synthetic */ zztw zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zztv(zztw zztwVar) {
        zzrw zzrwVar;
        this.zzb = zztwVar;
        zzrwVar = zztwVar.zza;
        this.zza = zzrwVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ String next() {
        return this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
