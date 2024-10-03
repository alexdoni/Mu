package com.google.android.gms.internal.recaptcha;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzkj<E> extends zzke<E> implements List<E>, RandomAccess {
    private static final zzlb<Object> zza = new zzkg(zzkr.zza, 0);

    public static <E> zzkf<E> zzj() {
        return new zzkf<>(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> zzkj<E> zzk(Object[] objArr) {
        return zzl(objArr, objArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> zzkj<E> zzl(Object[] objArr, int i) {
        if (i == 0) {
            return (zzkj<E>) zzkr.zza;
        }
        return new zzkr(objArr, i);
    }

    public static <E> zzkj<E> zzn(Collection<? extends E> collection) {
        if (collection instanceof zzke) {
            zzkj<E> zzd = ((zzke) collection).zzd();
            if (!zzd.zzf()) {
                return zzd;
            }
            Object[] array = zzd.toArray();
            return zzl(array, array.length);
        }
        Object[] array2 = collection.toArray();
        int length = array2.length;
        zzkq.zzb(array2, length);
        return zzl(array2, length);
    }

    public static <E> zzkj<E> zzo() {
        return (zzkj<E>) zzkr.zza;
    }

    public static <E> zzkj<E> zzp(E e) {
        Object[] objArr = {e};
        zzkq.zzb(objArr, 1);
        return zzl(objArr, 1);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzke, java.util.AbstractCollection, java.util.Collection
    public boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i = 0; i < size; i++) {
                        if (zzji.zza(get(i), list.get(i))) {
                        }
                    }
                    return true;
                }
                Iterator<E> it = iterator();
                Iterator<E> it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!it2.hasNext() || !zzji.zza(it.next(), it2.next())) {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzke, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* bridge */ /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    public int lastIndexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    @Deprecated
    public final E remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzke
    int zza(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i2] = get(i2);
        }
        return size;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzke
    @Deprecated
    public final zzkj<E> zzd() {
        return this;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzke
    /* renamed from: zze */
    public final zzla<E> iterator() {
        return listIterator(0);
    }

    public zzkj<E> zzh() {
        return size() <= 1 ? this : new zzkh(this);
    }

    @Override // java.util.List
    /* renamed from: zzi, reason: merged with bridge method [inline-methods] */
    public zzkj<E> subList(int i, int i2) {
        zzjn.zzh(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 != 0) {
            return new zzki(this, i, i3);
        }
        return (zzkj<E>) zzkr.zza;
    }

    @Override // java.util.List
    /* renamed from: zzq, reason: merged with bridge method [inline-methods] */
    public final zzlb<E> listIterator(int i) {
        zzjn.zzb(i, size(), FirebaseAnalytics.Param.INDEX);
        return isEmpty() ? (zzlb<E>) zza : new zzkg(this, i);
    }

    public static <E> zzkj<E> zzm(Iterable<? extends E> iterable) {
        iterable.getClass();
        if (iterable instanceof Collection) {
            return zzn((Collection) iterable);
        }
        Iterator<? extends E> it = iterable.iterator();
        if (!it.hasNext()) {
            return (zzkj<E>) zzkr.zza;
        }
        E next = it.next();
        if (!it.hasNext()) {
            return zzp(next);
        }
        zzkf zzkfVar = new zzkf(4);
        zzkfVar.zzc((zzkf) next);
        zzkfVar.zzd(it);
        return zzkfVar.zze();
    }
}
