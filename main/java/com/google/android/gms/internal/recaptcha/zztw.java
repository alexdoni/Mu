package com.google.android.gms.internal.recaptcha;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zztw extends AbstractList<String> implements RandomAccess, zzrw {
    private final zzrw zza;

    public zztw(zzrw zzrwVar) {
        this.zza = zzrwVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzrv) this.zza).get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zztv(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new zztu(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrw
    public final zzrw zze() {
        return this;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrw
    public final Object zzf(int i) {
        return this.zza.zzf(i);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrw
    public final List<?> zzh() {
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrw
    public final void zzi(zzpy zzpyVar) {
        throw new UnsupportedOperationException();
    }
}
