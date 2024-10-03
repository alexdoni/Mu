package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzof extends zzoh {
    public static <V> zzod<V> zza(Iterable<? extends zzop<? extends V>> iterable) {
        return new zzod<>(false, zzkj.zzm(iterable), null);
    }

    public static <V> zzod<V> zzb(Iterable<? extends zzop<? extends V>> iterable) {
        return new zzod<>(true, zzkj.zzm(iterable), null);
    }

    public static <V, X extends Throwable> zzop<V> zzc(zzop<? extends V> zzopVar, Class<X> cls, zzng<? super X, ? extends V> zzngVar, Executor executor) {
        zzmq zzmqVar = new zzmq(zzopVar, cls, zzngVar);
        zzopVar.zzp(zzmqVar, zzow.zzd(executor, zzmqVar));
        return zzmqVar;
    }

    public static <V> zzop<V> zzd() {
        return new zzoi();
    }

    public static <V> zzop<V> zze(Throwable th) {
        return new zzoj(th);
    }

    public static <V> zzop<V> zzf(@NullableDecl V v) {
        if (v == null) {
            return (zzop<V>) zzok.zza;
        }
        return new zzok(v);
    }

    public static zzop<Void> zzg() {
        return zzok.zza;
    }

    public static <V> zzop<V> zzh(zzop<V> zzopVar) {
        if (zzopVar.isDone()) {
            return zzopVar;
        }
        zzoe zzoeVar = new zzoe(zzopVar);
        zzopVar.zzp(zzoeVar, zznn.INSTANCE);
        return zzoeVar;
    }

    public static <O> zzop<O> zzi(zznf<O> zznfVar, Executor executor) {
        zzpd zzpdVar = new zzpd(zznfVar);
        zzpdVar.run();
        return zzpdVar;
    }

    public static <I, O> zzop<O> zzj(zzop<I> zzopVar, zzjc<? super I, ? extends O> zzjcVar, Executor executor) {
        int i = zzmw.zzc;
        zzjcVar.getClass();
        zzmv zzmvVar = new zzmv(zzopVar, zzjcVar);
        zzopVar.zzp(zzmvVar, zzow.zzd(executor, zzmvVar));
        return zzmvVar;
    }

    public static <I, O> zzop<O> zzk(zzop<I> zzopVar, zzng<? super I, ? extends O> zzngVar, Executor executor) {
        int i = zzmw.zzc;
        executor.getClass();
        zzmu zzmuVar = new zzmu(zzopVar, zzngVar);
        zzopVar.zzp(zzmuVar, zzow.zzd(executor, zzmuVar));
        return zzmuVar;
    }

    public static <V> V zzl(Future<V> future) throws ExecutionException {
        V v;
        boolean z = false;
        if (!future.isDone()) {
            throw new IllegalStateException(zzju.zzb("Future was expected to be done: %s", future));
        }
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    public static <V> void zzm(zzop<V> zzopVar, zzoa<? super V> zzoaVar, Executor executor) {
        zzoaVar.getClass();
        zzopVar.zzp(new zzoc(zzopVar, zzoaVar), executor);
    }
}
