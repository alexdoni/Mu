package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzsn;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzfw<T extends zzsn> implements zzng<zzfq<T>, Void> {
    private final List<zzfr<T>> zza;
    private final Executor zzb;

    private zzfw(List<zzfr<T>> list, Executor executor) {
        this.zza = list;
        this.zzb = executor;
    }

    public static <T extends zzsn> zzfw<T> zzb(List<zzfr<T>> list, Executor executor) {
        return new zzfw<>(list, executor);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzng
    public final /* bridge */ /* synthetic */ zzop<Void> zza(Object obj) throws Exception {
        zzfq zzfqVar = (zzfq) obj;
        final int size = this.zza.size();
        final ArrayList arrayList = new ArrayList(size);
        zzlb listIterator = ((zzkj) this.zza).listIterator(0);
        while (listIterator.hasNext()) {
            arrayList.add(((zzfr) listIterator.next()).zzb());
        }
        return zzof.zzk(zzfqVar.zza(zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzfv
            @Override // com.google.android.gms.internal.recaptcha.zzng
            public final zzop zza(Object obj2) {
                return zzfw.this.zzd(arrayList, size, (zzsn) obj2);
            }
        }), zzow.zzb()), zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzfu
            @Override // com.google.android.gms.internal.recaptcha.zzng
            public final zzop zza(Object obj2) {
                return zzfw.this.zze(size, arrayList, obj2);
            }
        }), zzow.zzb());
    }

    public final /* synthetic */ zzop zzc(zzsn zzsnVar, int i, List list) throws Exception {
        zzop zzf = zzof.zzf(zzsnVar);
        for (int i2 = 0; i2 < i; i2++) {
            if (((Boolean) zzof.zzl((Future) list.get(i2))).booleanValue()) {
                final zzfr<T> zzfrVar = this.zza.get(i2);
                zzf = zzof.zzk(zzf, zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzft
                    @Override // com.google.android.gms.internal.recaptcha.zzng
                    public final zzop zza(Object obj) {
                        return zzfr.this.zzc();
                    }
                }), zzow.zzb());
            }
        }
        return zzf;
    }

    public final /* synthetic */ zzop zzd(final List list, final int i, final zzsn zzsnVar) throws Exception {
        return zzof.zza(list).zzb(zzim.zzb(new zznf() { // from class: com.google.android.gms.internal.recaptcha.zzfs
            @Override // com.google.android.gms.internal.recaptcha.zznf
            public final zzop zza() {
                return zzfw.this.zzc(zzsnVar, i, list);
            }
        }), this.zzb);
    }

    public final /* synthetic */ zzop zze(int i, List list, Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            if (((Boolean) zzof.zzl((Future) list.get(i2))).booleanValue()) {
                arrayList.add(this.zza.get(i2).zza());
            }
        }
        return zzof.zzb(arrayList).zza(zzni.zza(null), zzow.zzb());
    }
}
