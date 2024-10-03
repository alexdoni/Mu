package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzii;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
abstract class zzht<T extends zzii> implements zzii {
    private final T zza;
    private final UUID zzb;
    private final String zzc;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        zziq.zzf(this);
    }

    public final String toString() {
        return zziq.zzd(this);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzii
    public final T zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzii
    public final String zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzii
    public final UUID zzc() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzht(String str, T t) {
        str.getClass();
        this.zzc = str;
        this.zza = t;
        this.zzb = t.zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzht(String str, UUID uuid) {
        str.getClass();
        this.zzc = str;
        this.zza = null;
        this.zzb = uuid;
    }
}
