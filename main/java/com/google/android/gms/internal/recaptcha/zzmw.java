package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzmw<I, O, F, T> extends zznv<O> implements Runnable {
    public static final /* synthetic */ int zzc = 0;

    @CheckForNull
    zzop<? extends I> zza;

    @CheckForNull
    F zzb;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        zzop<? extends I> zzopVar = this.zza;
        F f = this.zzb;
        if ((isCancelled() | (zzopVar == null)) || (f == null)) {
            return;
        }
        this.zza = null;
        if (zzopVar.isCancelled()) {
            zzc(zzopVar);
            return;
        }
        try {
            try {
                Object zzd = zzd(f, zzof.zzl(zzopVar));
                this.zzb = null;
                zze((zzmw<I, O, F, T>) zzd);
            } catch (Throwable th) {
                try {
                    zzu(th);
                } finally {
                    this.zzb = null;
                }
            }
        } catch (Error e) {
            zzu(e);
        } catch (CancellationException unused) {
            cancel(false);
        } catch (RuntimeException e2) {
            zzu(e2);
        } catch (ExecutionException e3) {
            zzu(e3.getCause());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzms
    @CheckForNull
    public final String zza() {
        String str;
        zzop<? extends I> zzopVar = this.zza;
        F f = this.zzb;
        String zza = super.zza();
        if (zzopVar != null) {
            String valueOf = String.valueOf(zzopVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16);
            sb.append("inputFuture=[");
            sb.append(valueOf);
            sb.append("], ");
            str = sb.toString();
        } else {
            str = "";
        }
        if (f == null) {
            if (zza == null) {
                return null;
            }
            String valueOf2 = String.valueOf(str);
            return zza.length() != 0 ? valueOf2.concat(zza) : new String(valueOf2);
        }
        String valueOf3 = String.valueOf(f);
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 11 + String.valueOf(valueOf3).length());
        sb2.append(str);
        sb2.append("function=[");
        sb2.append(valueOf3);
        sb2.append("]");
        return sb2.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzms
    protected final void zzb() {
        zzr(this.zza);
        this.zza = null;
        this.zzb = null;
    }

    abstract T zzd(F f, I i) throws Exception;

    abstract void zze(T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmw(zzop<? extends I> zzopVar, F f) {
        zzopVar.getClass();
        this.zza = zzopVar;
        f.getClass();
        this.zzb = f;
    }
}
