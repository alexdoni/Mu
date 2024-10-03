package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.5.0 */
/* loaded from: classes2.dex */
public abstract class zzmo extends zzml {
    private boolean zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmo(zzmp zzmpVar) {
        super(zzmpVar);
        this.zzf.zzu();
    }

    protected abstract boolean zzc();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzak() {
        if (!zzam()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzal() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzc();
        this.zzf.zzt();
        this.zza = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzam() {
        return this.zza;
    }
}
