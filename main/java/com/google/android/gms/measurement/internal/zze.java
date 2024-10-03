package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public abstract class zze extends zzf {
    private boolean zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(zzhf zzhfVar) {
        super(zzhfVar);
        this.zzu.zzaa();
    }

    protected void zzx() {
    }

    protected abstract boolean zzz();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzu() {
        if (!zzy()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzv() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (zzz()) {
            return;
        }
        this.zzu.zzz();
        this.zza = true;
    }

    public final void zzw() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzx();
        this.zzu.zzz();
        this.zza = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzy() {
        return this.zza;
    }
}
