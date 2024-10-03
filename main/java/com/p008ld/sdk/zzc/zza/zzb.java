package com.p008ld.sdk.zzc.zza;

/* compiled from: Pair.java */
/* loaded from: classes2.dex */
public final class zzb<A, B> {
    private final A zza;
    private final B zzb;

    private zzb(A a2, B b) {
        this.zza = a2;
        this.zzb = b;
    }

    public static <A, B> zzb<A, B> zza(A a2, B b) {
        return new zzb<>(a2, b);
    }

    public A zza() {
        return this.zza;
    }

    public B zzb() {
        return this.zzb;
    }

    public int hashCode() {
        A a2 = this.zza;
        int hashCode = ((a2 == null ? 0 : a2.hashCode()) + 31) * 31;
        B b = this.zzb;
        return hashCode + (b != null ? b.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzb zzbVar = (zzb) obj;
        A a2 = this.zza;
        if (a2 == null) {
            if (zzbVar.zza != null) {
                return false;
            }
        } else if (!a2.equals(zzbVar.zza)) {
            return false;
        }
        B b = this.zzb;
        if (b == null) {
            if (zzbVar.zzb != null) {
                return false;
            }
        } else if (!b.equals(zzbVar.zzb)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "first = " + this.zza + " , second = " + this.zzb;
    }
}
