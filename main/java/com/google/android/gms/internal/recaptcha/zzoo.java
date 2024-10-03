package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzoo<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable zza = new zzon(null);
    private static final Runnable zzb = new zzon(null);

    private final void zzc(Thread thread) {
        Runnable runnable = get();
        zzom zzomVar = null;
        boolean z = false;
        int i = 0;
        while (true) {
            if (!(runnable instanceof zzom)) {
                if (runnable != zzb) {
                    break;
                }
            } else {
                zzomVar = (zzom) runnable;
            }
            i++;
            if (i > 1000) {
                Runnable runnable2 = zzb;
                if (runnable == runnable2 || compareAndSet(runnable, runnable2)) {
                    z = Thread.interrupted() || z;
                    LockSupport.park(zzomVar);
                }
            } else {
                Thread.yield();
            }
            runnable = get();
        }
        if (z) {
            thread.interrupt();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Thread currentThread = Thread.currentThread();
        T t = null;
        if (compareAndSet(null, currentThread)) {
            boolean z = !zzg();
            if (z) {
                try {
                    t = zza();
                } catch (Throwable th) {
                    if (!compareAndSet(currentThread, zza)) {
                        zzc(currentThread);
                    }
                    zzd(th);
                    return;
                }
            }
            if (!compareAndSet(currentThread, zza)) {
                zzc(currentThread);
            }
            if (z) {
                zze(t);
            }
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String str;
        Runnable runnable = get();
        if (runnable == zza) {
            str = "running=[DONE]";
        } else if (runnable instanceof zzom) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            String name = ((Thread) runnable).getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 21);
            sb.append("running=[RUNNING ON ");
            sb.append(name);
            sb.append("]");
            str = sb.toString();
        } else {
            str = "running=[NOT STARTED YET]";
        }
        String zzb2 = zzb();
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(zzb2).length());
        sb2.append(str);
        sb2.append(", ");
        sb2.append(zzb2);
        return sb2.toString();
    }

    abstract T zza() throws Exception;

    abstract String zzb();

    abstract void zzd(Throwable th);

    abstract void zze(T t);

    abstract boolean zzg();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzh() {
        Runnable runnable = get();
        if (runnable instanceof Thread) {
            zzom zzomVar = new zzom(this, null);
            super/*java.util.concurrent.locks.AbstractOwnableSynchronizer*/.setExclusiveOwnerThread(Thread.currentThread());
            if (compareAndSet(runnable, zzomVar)) {
                try {
                    Thread thread = (Thread) runnable;
                    thread.interrupt();
                    if (getAndSet(zza) == zzb) {
                        LockSupport.unpark(thread);
                    }
                } catch (Throwable th) {
                    if (getAndSet(zza) == zzb) {
                        LockSupport.unpark((Thread) runnable);
                    }
                    throw th;
                }
            }
        }
    }
}
