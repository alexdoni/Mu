package com.google.android.gms.internal.recaptcha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzgw implements zznf<Object> {
    final /* synthetic */ zzgz zza;
    private List<zzng<zzfq, ?>> zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgw(zzgz zzgzVar, zzgu zzguVar) {
        this.zza = zzgzVar;
    }

    @Override // com.google.android.gms.internal.recaptcha.zznf
    public final zzop<Object> zza() throws Exception {
        zzia zziaVar;
        String str;
        Object obj;
        List<zzng<zzfq, ?>> list;
        zziaVar = this.zza.zzh;
        str = this.zza.zza;
        String valueOf = String.valueOf(str);
        zzie zzb = zziaVar.zzb(valueOf.length() != 0 ? "Initialize ".concat(valueOf) : new String("Initialize "), 1);
        try {
            obj = this.zza.zzg;
            synchronized (obj) {
                if (this.zzb == null) {
                    list = this.zza.zzi;
                    this.zzb = list;
                    this.zza.zzi = Collections.emptyList();
                }
            }
            ArrayList arrayList = new ArrayList(this.zzb.size());
            zzgy zzgyVar = new zzgy(this.zza, null);
            Iterator<zzng<zzfq, ?>> it = this.zzb.iterator();
            while (it.hasNext()) {
                try {
                    arrayList.add(it.next().zza(zzgyVar));
                } catch (Exception e) {
                    arrayList.add(zzof.zze(e));
                }
            }
            zzop<Object> zza = zzof.zzb(arrayList).zza(new Callable() { // from class: com.google.android.gms.internal.recaptcha.zzgv
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    zzgw.this.zzb();
                    return null;
                }
            }, zzow.zzb());
            zzb.zza(zza);
            zzb.close();
            return zza;
        } catch (Throwable th) {
            try {
                zzb.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final /* synthetic */ Object zzb() throws Exception {
        Object obj;
        obj = this.zza.zzg;
        synchronized (obj) {
            this.zzb = null;
        }
        return null;
    }
}
