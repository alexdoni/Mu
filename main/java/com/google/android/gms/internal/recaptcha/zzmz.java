package com.google.android.gms.internal.recaptcha;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
abstract class zzmz<InputT, OutputT> extends zzne<OutputT> {
    private static final Logger zza = Logger.getLogger(zzmz.class.getName());

    @CheckForNull
    private zzke<? extends zzop<? extends InputT>> zzb;
    private final boolean zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmz(zzke<? extends zzop<? extends InputT>> zzkeVar, boolean z, boolean z2) {
        super(zzkeVar.size());
        zzkeVar.getClass();
        this.zzb = zzkeVar;
        this.zzc = z;
    }

    private static void zzI(Throwable th) {
        zza.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFuture", "log", true != (th instanceof Error) ? "Got more than one input Future failure. Logging failures after the first" : "Input Future failed with Error", th);
    }

    private static boolean zzJ(Set<Throwable> set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ zzke zzd(zzmz zzmzVar, zzke zzkeVar) {
        zzmzVar.zzb = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zze(zzmz zzmzVar, int i, Future future) {
        try {
            zzof.zzl(future);
        } catch (ExecutionException e) {
            zzmzVar.zzH(e.getCause());
        } catch (Throwable th) {
            zzmzVar.zzH(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzw(zzmz zzmzVar, zzke zzkeVar) {
        int zzC = zzmzVar.zzC();
        zzjn.zzj(zzC >= 0, "Less than 0 remaining futures");
        if (zzC == 0) {
            zzmzVar.zzG();
            zzmzVar.zzy();
            zzmzVar.zzA(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zzA(int i) {
        this.zzb = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzms
    @CheckForNull
    public final String zza() {
        zzke<? extends zzop<? extends InputT>> zzkeVar = this.zzb;
        if (zzkeVar != null) {
            String valueOf = String.valueOf(zzkeVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 8);
            sb.append("futures=");
            sb.append(valueOf);
            return sb.toString();
        }
        return super.zza();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzms
    protected final void zzb() {
        zzke<? extends zzop<? extends InputT>> zzkeVar = this.zzb;
        zzA(1);
        if ((zzkeVar != null) && isCancelled()) {
            boolean zzv = zzv();
            zzla<? extends zzop<? extends InputT>> it = zzkeVar.iterator();
            while (it.hasNext()) {
                it.next().cancel(zzv);
            }
        }
    }

    abstract void zzy();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzz() {
        zzke<? extends zzop<? extends InputT>> zzkeVar = this.zzb;
        zzkeVar.getClass();
        if (zzkeVar.isEmpty()) {
            zzy();
            return;
        }
        if (this.zzc) {
            zzla<? extends zzop<? extends InputT>> it = this.zzb.iterator();
            int i = 0;
            while (it.hasNext()) {
                zzop<? extends InputT> next = it.next();
                next.zzp(new zzmx(this, next, i), zznn.INSTANCE);
                i++;
            }
            return;
        }
        zzmy zzmyVar = new zzmy(this, null);
        zzla<? extends zzop<? extends InputT>> it2 = this.zzb.iterator();
        while (it2.hasNext()) {
            it2.next().zzp(zzmyVar, zznn.INSTANCE);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzne
    final void zzx(Set<Throwable> set) {
        set.getClass();
        if (isCancelled()) {
            return;
        }
        Throwable zzn = zzn();
        zzn.getClass();
        zzJ(set, zzn);
    }

    private final void zzH(Throwable th) {
        th.getClass();
        if (!this.zzc || zzu(th) || !zzJ(zzF(), th)) {
            if (th instanceof Error) {
                zzI(th);
                return;
            }
            return;
        }
        zzI(th);
    }
}
