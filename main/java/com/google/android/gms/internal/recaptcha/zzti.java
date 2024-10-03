package com.google.android.gms.internal.recaptcha;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzti implements Iterator<Map.Entry> {
    final /* synthetic */ zztk zza;
    private int zzb = -1;
    private boolean zzc;
    private Iterator<Map.Entry> zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzti(zztk zztkVar, zztd zztdVar) {
        this.zza = zztkVar;
    }

    private final Iterator<Map.Entry> zza() {
        Map map;
        if (this.zzd == null) {
            map = this.zza.zzc;
            this.zzd = map.entrySet().iterator();
        }
        return this.zzd;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        Map map;
        int i = this.zzb + 1;
        list = this.zza.zzb;
        if (i < list.size()) {
            return true;
        }
        map = this.zza.zzc;
        return !map.isEmpty() && zza().hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Map.Entry next() {
        List list;
        List list2;
        this.zzc = true;
        int i = this.zzb + 1;
        this.zzb = i;
        list = this.zza.zzb;
        if (i < list.size()) {
            list2 = this.zza.zzb;
            return (Map.Entry) list2.get(this.zzb);
        }
        return zza().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        List list;
        if (this.zzc) {
            this.zzc = false;
            this.zza.zzn();
            int i = this.zzb;
            list = this.zza.zzb;
            if (i < list.size()) {
                zztk zztkVar = this.zza;
                int i2 = this.zzb;
                this.zzb = i2 - 1;
                zztkVar.zzl(i2);
                return;
            }
            zza().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
