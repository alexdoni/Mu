package com.google.android.gms.internal.measurement;

import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
/* loaded from: classes2.dex */
final class zzme implements ListIterator<String> {
    private ListIterator<String> zza;
    private final /* synthetic */ int zzb;
    private final /* synthetic */ zzmb zzc;

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zza.nextIndex();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zza.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.zza.next();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ String previous() {
        return this.zza.previous();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzme(zzmb zzmbVar, int i) {
        zzjp zzjpVar;
        this.zzc = zzmbVar;
        this.zzb = i;
        zzjpVar = zzmbVar.zza;
        this.zza = zzjpVar.listIterator(i);
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void add(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void set(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.zza.hasPrevious();
    }
}
