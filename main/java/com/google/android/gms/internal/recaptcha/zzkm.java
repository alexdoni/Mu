package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzkm<E> extends zzkc<E> {
    public zzkm() {
        super(4);
    }

    public final zzkm<E> zzc(E e) {
        e.getClass();
        super.zza(e);
        return this;
    }

    public final zzkn<E> zzd() {
        zzkn<E> zzn;
        int i = this.zzb;
        if (i == 0) {
            return zzkx.zza;
        }
        if (i != 1) {
            zzn = zzkn.zzn(i, this.zza);
            this.zzb = zzn.size();
            this.zzc = true;
            return zzn;
        }
        Object obj = this.zza[0];
        obj.getClass();
        return new zzkz(obj);
    }
}
