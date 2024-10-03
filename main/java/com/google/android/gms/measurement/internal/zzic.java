package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public abstract class zzic extends zzid {
    private boolean zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzic(zzhf zzhfVar) {
        super(zzhfVar);
        this.zzu.zzaa();
    }

    protected abstract boolean zzo();

    protected void zzz() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzab() {
        if (!zzae()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzac() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (zzo()) {
            return;
        }
        this.zzu.zzz();
        this.zza = true;
    }

    public final void zzad() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzz();
        this.zzu.zzz();
        this.zza = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzae() {
        return this.zza;
    }
}
