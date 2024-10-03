package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.spongycastle.asn1.cmc.BodyPartID;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzhs<T> {
    private final zzhp<T> zza;
    private final AtomicLong zzb = new AtomicLong(zzi(Integer.MIN_VALUE, Integer.MIN_VALUE));
    private final AtomicReference<zzhr<T>> zzc = new AtomicReference<>(null);
    private final AtomicReference<zzop<T>> zzd = new AtomicReference<>(null);
    private final Executor zze = zzow.zzc(zzow.zzb());
    private final zzpa<T> zzf;

    public zzhs(zznf<T> zznfVar, Executor executor) {
        zzpa<T> zzd = zzpa.zzd();
        this.zzf = zzd;
        zzhp<T> zzhpVar = new zzhp<>(zznfVar, executor);
        this.zza = zzhpVar;
        zzd.zzp(zzhpVar, zzow.zzb());
    }

    public static /* synthetic */ boolean zzg(zzhs zzhsVar) {
        long j;
        int i;
        int i2;
        boolean z;
        do {
            j = zzhsVar.zzb.get();
            i = (int) j;
            i2 = (int) (j >>> 32);
            if (i == Integer.MIN_VALUE) {
                StringBuilder sb = new StringBuilder(33);
                sb.append("Refcount is: ");
                sb.append(j);
                throw new AssertionError(sb.toString());
            }
            z = i == -2147483647;
            if (z) {
                i2++;
            }
        } while (!zzhsVar.zzb.compareAndSet(j, zzi(i2, i - 1)));
        return z;
    }

    private static long zzi(int i, int i2) {
        return (i2 & BodyPartID.bodyIdMax) | (i << 32);
    }

    /* renamed from: zzj */
    public final zzop<T> zzc(int i) {
        zzhr<T> zzhrVar;
        zznf zznfVar;
        Executor executor;
        int i2;
        if (((int) (this.zzb.get() >>> 32)) > i) {
            return zzof.zzd();
        }
        zzhr zzhrVar2 = new zzhr(i);
        do {
            zzhrVar = this.zzc.get();
            if (zzhrVar != null) {
                i2 = ((zzhr) zzhrVar).zza;
                if (i2 > i) {
                    return zzof.zzd();
                }
            }
        } while (!UByte$$ExternalSyntheticBackport0.m1410m(this.zzc, zzhrVar, zzhrVar2));
        if (((int) (this.zzb.get() >>> 32)) > i) {
            zzhrVar2.cancel(true);
            UByte$$ExternalSyntheticBackport0.m1410m(this.zzc, zzhrVar2, null);
            return zzhrVar2;
        }
        zznfVar = ((zzhp) this.zza).zza;
        executor = ((zzhp) this.zza).zzb;
        if (zznfVar == null || executor == null) {
            zzhrVar2.zzc(this.zzf);
        } else {
            zzhrVar2.zzc(zzof.zzi(zzim.zzb(zznfVar), executor));
        }
        return zzhrVar2;
    }

    public final zzop<T> zzb() {
        long j;
        final int i;
        zzop zzc;
        if (this.zzf.isDone()) {
            return this.zzf;
        }
        do {
            j = this.zzb.get();
            i = (int) (j >>> 32);
        } while (!this.zzb.compareAndSet(j, zzi(i, ((int) j) + 1)));
        final zzpa zzd = zzpa.zzd();
        zzop<T> andSet = this.zzd.getAndSet(zzd);
        if (andSet == null) {
            zzc = zzof.zzi(zzim.zzb(new zznf() { // from class: com.google.android.gms.internal.recaptcha.zzhl
                @Override // com.google.android.gms.internal.recaptcha.zznf
                public final zzop zza() {
                    return zzhs.this.zzc(i);
                }
            }), zzow.zzb());
        } else {
            zzc = zzof.zzc(andSet, Throwable.class, zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzhm
                @Override // com.google.android.gms.internal.recaptcha.zzng
                public final zzop zza(Object obj) {
                    return zzhs.this.zzd(i, (Throwable) obj);
                }
            }), this.zze);
        }
        zzd.zzc(zzc);
        final zzhq zzhqVar = new zzhq(this, i, null);
        zzd.zzp(new Runnable() { // from class: com.google.android.gms.internal.recaptcha.zzhn
            @Override // java.lang.Runnable
            public final void run() {
                zzhs.this.zzf(zzd, zzhqVar);
            }
        }, zzow.zzb());
        return zzhqVar;
    }

    public final /* synthetic */ zzop zzd(int i, Throwable th) throws Exception {
        return zzc(i);
    }

    public final /* synthetic */ void zzf(zzpa zzpaVar, zzhq zzhqVar) {
        try {
            this.zzf.zzt(zzof.zzl(zzpaVar));
            zzhqVar.zzc(this.zzf);
        } catch (Throwable unused) {
            zzhqVar.zzc(zzpaVar);
        }
    }

    public final boolean zzh() {
        return this.zzf.isDone();
    }
}
