package com.google.android.play.integrity.internal;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
public final class zzx implements zzaa {
    private static final Object zza = new Object();
    private volatile zzaa zzb;
    private volatile Object zzc = zza;

    private zzx(zzaa zzaaVar) {
        this.zzb = zzaaVar;
    }

    @Override // com.google.android.play.integrity.internal.zzaa
    public final Object zza() {
        Object obj = this.zzc;
        Object obj2 = zza;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.zzc;
                if (obj == obj2) {
                    obj = this.zzb.zza();
                    Object obj3 = this.zzc;
                    if (obj3 != obj2 && obj3 != obj) {
                        String valueOf = String.valueOf(obj3);
                        String valueOf2 = String.valueOf(obj);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 118 + String.valueOf(valueOf2).length());
                        sb.append("Scoped provider was invoked recursively returning different results: ");
                        sb.append(valueOf);
                        sb.append(" & ");
                        sb.append(valueOf2);
                        sb.append(". This is likely due to a circular dependency.");
                        throw new IllegalStateException(sb.toString());
                    }
                    this.zzc = obj;
                    this.zzb = null;
                }
            }
        }
        return obj;
    }

    public static zzaa zzb(zzaa zzaaVar) {
        zzaaVar.getClass();
        return zzaaVar instanceof zzx ? zzaaVar : new zzx(zzaaVar);
    }
}
