package com.google.android.gms.internal.recaptcha;

import java.util.AbstractList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzrn<F, T> extends AbstractList<T> {
    private final List<F> zza;
    private final zzrm<F, T> zzb;

    public zzrn(List<F> list, zzrm<F, T> zzrmVar) {
        this.zza = list;
        this.zzb = zzrmVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final T get(int i) {
        return (T) this.zzb.zza(this.zza.get(i));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }
}
