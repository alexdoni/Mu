package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public class zzru {
    private static final zzqr zzb = zzqr.zza();
    protected volatile zzsn zza;
    private volatile zzpy zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzru)) {
            return false;
        }
        zzru zzruVar = (zzru) obj;
        zzsn zzsnVar = this.zza;
        zzsn zzsnVar2 = zzruVar.zza;
        if (zzsnVar == null && zzsnVar2 == null) {
            return zzb().equals(zzruVar.zzb());
        }
        if (zzsnVar != null && zzsnVar2 != null) {
            return zzsnVar.equals(zzsnVar2);
        }
        if (zzsnVar != null) {
            zzruVar.zzc(zzsnVar.zzO());
            return zzsnVar.equals(zzruVar.zza);
        }
        zzc(zzsnVar2.zzO());
        return this.zza.equals(zzsnVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzpw) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzt();
        }
        return 0;
    }

    public final zzpy zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            if (this.zza == null) {
                this.zzc = zzpy.zzb;
            } else {
                this.zzc = this.zza.zzN();
            }
            return this.zzc;
        }
    }

    protected final void zzc(zzsn zzsnVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzsnVar;
                    this.zzc = zzpy.zzb;
                } catch (zzrr unused) {
                    this.zza = zzsnVar;
                    this.zzc = zzpy.zzb;
                }
            }
        }
    }
}
