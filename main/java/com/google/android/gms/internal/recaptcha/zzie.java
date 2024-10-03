package com.google.android.gms.internal.recaptcha;

import java.io.Closeable;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzie implements Runnable, Closeable {
    private zzii zza;
    private zzii zzb;
    private final boolean zzc = zzdw.zzb();
    private boolean zzd;
    private boolean zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzie(zzii zziiVar) {
        this.zza = zziiVar;
        this.zzb = zziiVar;
    }

    private final void zzb() {
        this.zzd = true;
        if (this.zzc && !this.zze) {
            zzdw.zzb();
        }
        this.zza = null;
    }

    public final <V, T extends zzop<V>> T zza(T t) {
        if (!this.zzd) {
            if (!this.zze) {
                this.zze = true;
                t.zzp(this, zzow.zzb());
                return t;
            }
            throw new IllegalStateException("Signal is already attached to future");
        }
        throw new IllegalStateException("Span was already closed. Did you attach it to a future after calling Tracer.endSpan()?");
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzd || !this.zze) {
            zzdw.zza().post(new Runnable() { // from class: com.google.android.gms.internal.recaptcha.zzid
                @Override // java.lang.Runnable
                public final void run() {
                    throw new IllegalStateException("Span was closed by an invalid call to SpanEndSignal.run()");
                }
            });
        } else {
            zzb();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        zzii zziiVar = this.zzb;
        this.zzb = null;
        try {
            if (!this.zze) {
                if (this.zzd) {
                    throw new IllegalStateException("Span was already closed!");
                }
                zzb();
            }
        } finally {
            zziq.zzf(zziiVar);
        }
    }
}
