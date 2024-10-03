package com.google.android.gms.internal.recaptcha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzry extends zzsa {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzry() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzry(zzrx zzrxVar) {
        super(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <L> List<L> zzf(Object obj, long j, int i) {
        zzrv zzrvVar;
        List<L> arrayList;
        List<L> list = (List) zzub.zzf(obj, j);
        if (list.isEmpty()) {
            if (list instanceof zzrw) {
                arrayList = new zzrv(i);
            } else if (!(list instanceof zzsv) || !(list instanceof zzro)) {
                arrayList = new ArrayList<>(i);
            } else {
                arrayList = ((zzro) list).zzd(i);
            }
            zzub.zzs(obj, j, arrayList);
            return arrayList;
        }
        if (zza.isAssignableFrom(list.getClass())) {
            ArrayList arrayList2 = new ArrayList(list.size() + i);
            arrayList2.addAll(list);
            zzub.zzs(obj, j, arrayList2);
            zzrvVar = arrayList2;
        } else if (list instanceof zztw) {
            zzrv zzrvVar2 = new zzrv(list.size() + i);
            zzrvVar2.addAll(zzrvVar2.size(), (zztw) list);
            zzub.zzs(obj, j, zzrvVar2);
            zzrvVar = zzrvVar2;
        } else {
            if (!(list instanceof zzsv) || !(list instanceof zzro)) {
                return list;
            }
            zzro zzroVar = (zzro) list;
            if (zzroVar.zzc()) {
                return list;
            }
            zzro zzd = zzroVar.zzd(list.size() + i);
            zzub.zzs(obj, j, zzd);
            return zzd;
        }
        return zzrvVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzsa
    public final <L> List<L> zza(Object obj, long j) {
        return zzf(obj, j, 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzsa
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzub.zzf(obj, j);
        if (list instanceof zzrw) {
            unmodifiableList = ((zzrw) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if (!(list instanceof zzsv) || !(list instanceof zzro)) {
                unmodifiableList = Collections.unmodifiableList(list);
            } else {
                zzro zzroVar = (zzro) list;
                if (zzroVar.zzc()) {
                    zzroVar.zzb();
                    return;
                }
                return;
            }
        }
        zzub.zzs(obj, j, unmodifiableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzsa
    public final <E> void zzc(Object obj, Object obj2, long j) {
        List list = (List) zzub.zzf(obj2, j);
        List zzf = zzf(obj, j, list.size());
        int size = zzf.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            zzf.addAll(list);
        }
        if (size > 0) {
            list = zzf;
        }
        zzub.zzs(obj, j, list);
    }
}
