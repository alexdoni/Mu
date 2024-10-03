package com.google.android.gms.internal.recaptcha;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzkf<E> extends zzkc<E> {
    public zzkf() {
        super(4);
    }

    public final zzkf<E> zzc(E e) {
        super.zza(e);
        return this;
    }

    public final zzkf<E> zzd(Iterator<? extends E> it) {
        while (it.hasNext()) {
            super.zza(it.next());
        }
        return this;
    }

    public final zzkj<E> zze() {
        this.zzc = true;
        return zzkj.zzl(this.zza, this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkf(int i) {
        super(4);
    }
}
