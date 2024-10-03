package com.google.android.gms.internal.recaptcha;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzkk<K, V> {
    Object[] zza = new Object[8];
    int zzb = 0;

    public final zzkk<K, V> zza(K k, V v) {
        int i = this.zzb + 1;
        int i2 = i + i;
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (i2 > length) {
            this.zza = Arrays.copyOf(objArr, zzkd.zzb(length, i2));
        }
        zzjz.zza(k, v);
        Object[] objArr2 = this.zza;
        int i3 = this.zzb;
        int i4 = i3 + i3;
        objArr2[i4] = k;
        objArr2[i4 + 1] = v;
        this.zzb = i3 + 1;
        return this;
    }

    public final zzkl<K, V> zzb() {
        return zzkw.zzg(this.zzb, this.zza);
    }
}
