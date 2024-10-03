package com.google.android.gms.internal.recaptcha;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzkz<E> extends zzkn<E> {
    final transient E zza;
    private transient int zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkz(E e) {
        e.getClass();
        this.zza = e;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzke, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.equals(obj);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkn, java.util.Collection, java.util.Set
    public final int hashCode() {
        int i = this.zzb;
        if (i != 0) {
            return i;
        }
        int hashCode = this.zza.hashCode();
        this.zzb = hashCode;
        return hashCode;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkn, com.google.android.gms.internal.recaptcha.zzke, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* bridge */ /* synthetic */ Iterator iterator() {
        return new zzkp(this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        String obj = this.zza.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 2);
        sb.append('[');
        sb.append(obj);
        sb.append(']');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzke
    public final int zza(Object[] objArr, int i) {
        objArr[0] = this.zza;
        return 1;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkn, com.google.android.gms.internal.recaptcha.zzke
    /* renamed from: zze */
    public final zzla<E> iterator() {
        return new zzkp(this.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzke
    public final boolean zzf() {
        throw null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkn
    final zzkj<E> zzi() {
        return zzkj.zzp(this.zza);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkn
    final boolean zzm() {
        return this.zzb != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkz(E e, int i) {
        this.zza = e;
        this.zzb = i;
    }
}
