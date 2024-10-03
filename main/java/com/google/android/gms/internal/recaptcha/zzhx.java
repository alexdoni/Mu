package com.google.android.gms.internal.recaptcha;

import android.os.StrictMode;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzhx {
    private static final zzhx zza;
    private final UUID zzb;
    private final AtomicLong zzc;

    static {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            zza = new zzhx(UUID.randomUUID(), new SecureRandom().nextLong());
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    zzhx(UUID uuid, long j) {
        this.zzb = uuid;
        this.zzc = new AtomicLong((j ^ 25214903917L) & 281474976710655L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhx zzb() {
        return zza;
    }

    final long zza() {
        long j;
        long j2;
        do {
            j = this.zzc.get();
            j2 = (((int) (r4 >>> 16)) << 32) + ((int) (r2 >>> 16));
        } while (!this.zzc.compareAndSet(j, ((25214903917L * (((j * 25214903917L) + 11) & 281474976710655L)) + 11) & 281474976710655L));
        return j2;
    }

    public final UUID zzc() {
        long zza2 = zza();
        return new UUID((zza2 & (-61441)) ^ this.zzb.getMostSignificantBits(), (zza() >>> 2) ^ this.zzb.getLeastSignificantBits());
    }
}
