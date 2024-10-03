package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zznm<V> extends zzmz<Object, V> {

    @CheckForNull
    private zznl<?> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zznm(zzke<? extends zzop<?>> zzkeVar, boolean z, Executor executor, zznf<V> zznfVar) {
        super(zzkeVar, z, false);
        this.zza = new zznj(this, zznfVar, executor);
        zzz();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ zznl zzH(zznm zznmVar, zznl zznlVar) {
        zznmVar.zza = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzmz
    public final void zzA(int i) {
        super.zzA(i);
        if (i == 1) {
            this.zza = null;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzms
    protected final void zzq() {
        zznl<?> zznlVar = this.zza;
        if (zznlVar != null) {
            zznlVar.zzh();
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzmz
    final void zzy() {
        zznl<?> zznlVar = this.zza;
        if (zznlVar != null) {
            zznlVar.zzf();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zznm(zzke<? extends zzop<?>> zzkeVar, boolean z, Executor executor, Callable<V> callable) {
        super(zzkeVar, z, false);
        this.zza = new zznk(this, callable, executor);
        zzz();
    }
}
