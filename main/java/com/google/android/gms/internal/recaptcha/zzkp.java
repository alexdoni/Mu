package com.google.android.gms.internal.recaptcha;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzkp extends zzla {
    boolean zza;
    final /* synthetic */ Object zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkp(Object obj) {
        this.zzb = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.zza;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.zza) {
            this.zza = true;
            return this.zzb;
        }
        throw new NoSuchElementException();
    }
}
