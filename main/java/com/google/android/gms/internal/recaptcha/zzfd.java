package com.google.android.gms.internal.recaptcha;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzfd implements zzec<File> {
    private static final AtomicInteger zza = new AtomicInteger();
    private boolean zzb = false;

    private zzfd() {
    }

    public static zzfd zzb() {
        return new zzfd();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzec
    public final /* bridge */ /* synthetic */ File zza(zzeb zzebVar) throws IOException {
        if (this.zzb) {
            if (zzebVar.zze()) {
                throw new zzew("Short circuit would skip transforms.");
            }
            return zzebVar.zzb().zzd(zzebVar.zza());
        }
        zzev zza2 = zzev.zza(zzfe.zzc(zzebVar));
        try {
            if (!(zza2.zzb() instanceof zzer)) {
                throw new IOException("Not convertible and fallback to pipe is disabled.");
            }
            File zza3 = ((zzer) zza2.zzb()).zza();
            zza2.close();
            return zza3;
        } catch (Throwable th) {
            try {
                zza2.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final zzfd zzc() {
        this.zzb = true;
        return this;
    }
}
