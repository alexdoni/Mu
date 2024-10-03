package com.google.android.gms.internal.recaptcha;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzkn<E> extends zzke<E> implements Set<E> {

    @CheckForNull
    private transient zzkj<E> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(int i) {
        int max = Math.max(i, 2);
        if (max >= 751619276) {
            if (max < 1073741824) {
                return 1073741824;
            }
            throw new IllegalArgumentException("collection too large");
        }
        int highestOneBit = Integer.highestOneBit(max - 1);
        do {
            highestOneBit += highestOneBit;
        } while (highestOneBit * 0.7d < max);
        return highestOneBit;
    }

    public static <E> zzkn<E> zzk(Collection<? extends E> collection) {
        Object[] array = collection.toArray();
        return zzn(array.length, array);
    }

    public static <E> zzkn<E> zzl() {
        return zzkx.zza;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzkn) && zzm() && ((zzkn) obj).zzm() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzky.zza(this);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzke
    public zzkj<E> zzd() {
        zzkj<E> zzkjVar = this.zza;
        if (zzkjVar != null) {
            return zzkjVar;
        }
        zzkj<E> zzi = zzi();
        this.zza = zzi;
        return zzi;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzke, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zze */
    public abstract zzla<E> iterator();

    zzkj<E> zzi() {
        return zzkj.zzk(toArray());
    }

    boolean zzm() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> zzkn<E> zzn(int i, Object... objArr) {
        if (i == 0) {
            return zzkx.zza;
        }
        if (i == 1) {
            Object obj = objArr[0];
            obj.getClass();
            return new zzkz(obj);
        }
        int zzh = zzh(i);
        Object[] objArr2 = new Object[zzh];
        int i2 = zzh - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object obj2 = objArr[i5];
            zzkq.zza(obj2, i5);
            int hashCode = obj2.hashCode();
            int zza = zzkb.zza(hashCode);
            while (true) {
                int i6 = zza & i2;
                Object obj3 = objArr2[i6];
                if (obj3 != null) {
                    if (obj3.equals(obj2)) {
                        break;
                    }
                    zza++;
                } else {
                    objArr[i4] = obj2;
                    objArr2[i6] = obj2;
                    i3 += hashCode;
                    i4++;
                    break;
                }
            }
        }
        Arrays.fill(objArr, i4, i, (Object) null);
        if (i4 == 1) {
            Object obj4 = objArr[0];
            obj4.getClass();
            return new zzkz(obj4, i3);
        }
        if (zzh(i4) < zzh / 2) {
            return zzn(i4, objArr);
        }
        int length = objArr.length;
        if (i4 < (length >> 1) + (length >> 2)) {
            objArr = Arrays.copyOf(objArr, i4);
        }
        return new zzkx(objArr, i3, objArr2, i2, i4);
    }
}
