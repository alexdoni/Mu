package com.google.android.gms.internal.recaptcha;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzkh<E> extends zzkj<E> {
    private final transient zzkj<E> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkh(zzkj<E> zzkjVar) {
        this.zza = zzkjVar;
    }

    private final int zzr(int i) {
        return (this.zza.size() - 1) - i;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkj, com.google.android.gms.internal.recaptcha.zzke, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.contains(obj);
    }

    @Override // java.util.List
    public final E get(int i) {
        zzjn.zza(i, this.zza.size(), FirebaseAnalytics.Param.INDEX);
        return this.zza.get(zzr(i));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkj, java.util.List
    public final int indexOf(@CheckForNull Object obj) {
        int lastIndexOf = this.zza.lastIndexOf(obj);
        if (lastIndexOf >= 0) {
            return zzr(lastIndexOf);
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkj, java.util.List
    public final int lastIndexOf(@CheckForNull Object obj) {
        int indexOf = this.zza.indexOf(obj);
        if (indexOf >= 0) {
            return zzr(indexOf);
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkj, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzke
    public final boolean zzf() {
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkj
    public final zzkj<E> zzh() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkj
    /* renamed from: zzi */
    public final zzkj<E> subList(int i, int i2) {
        zzjn.zzh(i, i2, this.zza.size());
        zzkj<E> zzkjVar = this.zza;
        return zzkjVar.subList(zzkjVar.size() - i2, this.zza.size() - i).zzh();
    }
}
