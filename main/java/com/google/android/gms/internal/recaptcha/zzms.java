package com.google.android.gms.internal.recaptcha;

import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzms<V> extends zzpe implements zzop<V> {
    private static final boolean zzae;
    private static final Logger zzb;
    private static final zza zzc;
    private static final Object zzd;

    @NullableDecl
    private volatile zzd listeners;

    @NullableDecl
    private volatile Object value;

    @NullableDecl
    private volatile zzk waiters;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    /* loaded from: classes2.dex */
    public abstract class zza {
        /* synthetic */ zza(C09251 c09251) {
        }

        abstract void zza(zzk zzkVar, zzk zzkVar2);

        abstract void zzb(zzk zzkVar, Thread thread);

        abstract boolean zzc(zzms<?> zzmsVar, zzd zzdVar, zzd zzdVar2);

        abstract boolean zzd(zzms<?> zzmsVar, Object obj, Object obj2);

        abstract boolean zze(zzms<?> zzmsVar, zzk zzkVar, zzk zzkVar2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    /* loaded from: classes2.dex */
    public final class zzb {
        static final zzb zza;
        static final zzb zzb;
        final boolean zzc;

        @NullableDecl
        final Throwable zzd;

        static {
            if (zzms.zzae) {
                zzb = null;
                zza = null;
            } else {
                zzb = new zzb(false, null);
                zza = new zzb(true, null);
            }
        }

        zzb(boolean z, @NullableDecl Throwable th) {
            this.zzc = z;
            this.zzd = th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    /* loaded from: classes2.dex */
    public final class zzc {
        static final zzc zza = new zzc(new Throwable("Failure occurred while trying to finish a future.") { // from class: com.google.android.gms.internal.recaptcha.zzms.zzc.1
            {
                super("Failure occurred while trying to finish a future.");
            }

            @Override // java.lang.Throwable
            public final synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable zzb;

        zzc(Throwable th) {
            th.getClass();
            this.zzb = th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    /* loaded from: classes2.dex */
    public final class zzd {
        static final zzd zza = new zzd(null, null);

        @NullableDecl
        zzd next;
        final Runnable zzb;
        final Executor zzc;

        zzd(Runnable runnable, Executor executor) {
            this.zzb = runnable;
            this.zzc = executor;
        }
    }

    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    /* loaded from: classes2.dex */
    final class zze extends zza {
        final AtomicReferenceFieldUpdater<zzk, Thread> zza;
        final AtomicReferenceFieldUpdater<zzk, zzk> zzb;
        final AtomicReferenceFieldUpdater<zzms, zzk> zzc;
        final AtomicReferenceFieldUpdater<zzms, zzd> zzd;
        final AtomicReferenceFieldUpdater<zzms, Object> zze;

        zze(AtomicReferenceFieldUpdater<zzk, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<zzk, zzk> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<zzms, zzk> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<zzms, zzd> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<zzms, Object> atomicReferenceFieldUpdater5) {
            super(null);
            this.zza = atomicReferenceFieldUpdater;
            this.zzb = atomicReferenceFieldUpdater2;
            this.zzc = atomicReferenceFieldUpdater3;
            this.zzd = atomicReferenceFieldUpdater4;
            this.zze = atomicReferenceFieldUpdater5;
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final void zza(zzk zzkVar, zzk zzkVar2) {
            this.zzb.lazySet(zzkVar, zzkVar2);
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final void zzb(zzk zzkVar, Thread thread) {
            this.zza.lazySet(zzkVar, thread);
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final boolean zzc(zzms<?> zzmsVar, zzd zzdVar, zzd zzdVar2) {
            return UByte$$ExternalSyntheticBackport0.m1412m(this.zzd, zzmsVar, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final boolean zzd(zzms<?> zzmsVar, Object obj, Object obj2) {
            return UByte$$ExternalSyntheticBackport0.m1412m(this.zze, zzmsVar, obj, obj2);
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final boolean zze(zzms<?> zzmsVar, zzk zzkVar, zzk zzkVar2) {
            return UByte$$ExternalSyntheticBackport0.m1412m(this.zzc, zzmsVar, zzkVar, zzkVar2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    /* loaded from: classes2.dex */
    public final class zzf<V> implements Runnable {
        final zzms<V> zza;
        final zzop<? extends V> zzb;

        zzf(zzms<V> zzmsVar, zzop<? extends V> zzopVar) {
            this.zza = zzmsVar;
            this.zzb = zzopVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (((zzms) this.zza).value != this) {
                return;
            }
            if (zzms.zzc.zzd(this.zza, this, zzms.zzd(this.zzb))) {
                zzms.zzz(this.zza);
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    /* loaded from: classes2.dex */
    final class zzg extends zza {
        private zzg() {
            super(null);
        }

        /* synthetic */ zzg(C09251 c09251) {
            super(null);
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final void zza(zzk zzkVar, zzk zzkVar2) {
            zzkVar.next = zzkVar2;
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final void zzb(zzk zzkVar, Thread thread) {
            zzkVar.thread = thread;
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final boolean zzc(zzms<?> zzmsVar, zzd zzdVar, zzd zzdVar2) {
            synchronized (zzmsVar) {
                if (((zzms) zzmsVar).listeners != zzdVar) {
                    return false;
                }
                ((zzms) zzmsVar).listeners = zzdVar2;
                return true;
            }
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final boolean zzd(zzms<?> zzmsVar, Object obj, Object obj2) {
            synchronized (zzmsVar) {
                if (((zzms) zzmsVar).value != obj) {
                    return false;
                }
                ((zzms) zzmsVar).value = obj2;
                return true;
            }
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final boolean zze(zzms<?> zzmsVar, zzk zzkVar, zzk zzkVar2) {
            synchronized (zzmsVar) {
                if (((zzms) zzmsVar).waiters != zzkVar) {
                    return false;
                }
                ((zzms) zzmsVar).waiters = zzkVar2;
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    /* loaded from: classes2.dex */
    public interface zzh<V> extends zzop<V> {
    }

    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    /* loaded from: classes2.dex */
    abstract class zzi<V> extends zzms<V> implements zzh<V> {
    }

    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    /* loaded from: classes2.dex */
    final class zzj extends zza {
        static final Unsafe zza;
        static final long zzb;
        static final long zzc;
        static final long zzd;
        static final long zze;
        static final long zzf;

        static {
            Unsafe unsafe;
            try {
                try {
                    unsafe = Unsafe.getUnsafe();
                } catch (SecurityException unused) {
                    unsafe = (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.android.gms.internal.recaptcha.zzms.zzj.1
                        public static final Unsafe zza() throws Exception {
                            for (Field field : Unsafe.class.getDeclaredFields()) {
                                field.setAccessible(true);
                                Object obj = field.get(null);
                                if (Unsafe.class.isInstance(obj)) {
                                    return (Unsafe) Unsafe.class.cast(obj);
                                }
                            }
                            throw new NoSuchFieldError("the Unsafe");
                        }

                        @Override // java.security.PrivilegedExceptionAction
                        public final /* bridge */ /* synthetic */ Unsafe run() throws Exception {
                            return zza();
                        }
                    });
                }
                try {
                    zzc = unsafe.objectFieldOffset(zzms.class.getDeclaredField("waiters"));
                    zzb = unsafe.objectFieldOffset(zzms.class.getDeclaredField("listeners"));
                    zzd = unsafe.objectFieldOffset(zzms.class.getDeclaredField("value"));
                    zze = unsafe.objectFieldOffset(zzk.class.getDeclaredField("thread"));
                    zzf = unsafe.objectFieldOffset(zzk.class.getDeclaredField("next"));
                    zza = unsafe;
                } catch (Exception e) {
                    zzjw.zza(e);
                    throw new RuntimeException(e);
                }
            } catch (PrivilegedActionException e2) {
                throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
            }
        }

        private zzj() {
            super(null);
        }

        /* synthetic */ zzj(C09251 c09251) {
            super(null);
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final void zza(zzk zzkVar, zzk zzkVar2) {
            zza.putObject(zzkVar, zzf, zzkVar2);
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final void zzb(zzk zzkVar, Thread thread) {
            zza.putObject(zzkVar, zze, thread);
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final boolean zzc(zzms<?> zzmsVar, zzd zzdVar, zzd zzdVar2) {
            return UByte$$ExternalSyntheticBackport0.m1413m(zza, zzmsVar, zzb, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final boolean zzd(zzms<?> zzmsVar, Object obj, Object obj2) {
            return UByte$$ExternalSyntheticBackport0.m1413m(zza, zzmsVar, zzd, obj, obj2);
        }

        @Override // com.google.android.gms.internal.recaptcha.zzms.zza
        final boolean zze(zzms<?> zzmsVar, zzk zzkVar, zzk zzkVar2) {
            return UByte$$ExternalSyntheticBackport0.m1413m(zza, zzmsVar, zzc, zzkVar, zzkVar2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    /* loaded from: classes2.dex */
    public final class zzk {
        static final zzk zza = new zzk(false);

        @NullableDecl
        volatile zzk next;

        @NullableDecl
        volatile Thread thread;

        zzk() {
            zzms.zzc.zzb(this, Thread.currentThread());
        }

        zzk(boolean z) {
        }
    }

    static {
        boolean z;
        Throwable th;
        Throwable th2;
        zza zzgVar;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", JsonSerializer.False));
        } catch (SecurityException unused) {
            z = false;
        }
        zzae = z;
        zzb = Logger.getLogger(zzms.class.getName());
        C09251 c09251 = null;
        try {
            zzgVar = new zzj(c09251);
            th2 = null;
            th = null;
        } catch (Throwable th3) {
            try {
                th2 = th3;
                zzgVar = new zze(AtomicReferenceFieldUpdater.newUpdater(zzk.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(zzk.class, zzk.class, "next"), AtomicReferenceFieldUpdater.newUpdater(zzms.class, zzk.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(zzms.class, zzd.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(zzms.class, Object.class, "value"));
                th = null;
            } catch (Throwable th4) {
                th = th4;
                th2 = th3;
                zzgVar = new zzg(c09251);
            }
        }
        zzc = zzgVar;
        if (th != null) {
            Logger logger = zzb;
            logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
        zzd = new Object();
    }

    private static void zzA(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = zzb;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 57 + String.valueOf(valueOf2).length());
            sb.append("RuntimeException while executing runnable ");
            sb.append(valueOf);
            sb.append(" with executor ");
            sb.append(valueOf2);
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", sb.toString(), (Throwable) e);
        }
    }

    private final void zzB(zzk zzkVar) {
        zzkVar.thread = null;
        while (true) {
            zzk zzkVar2 = this.waiters;
            if (zzkVar2 != zzk.zza) {
                zzk zzkVar3 = null;
                while (zzkVar2 != null) {
                    zzk zzkVar4 = zzkVar2.next;
                    if (zzkVar2.thread != null) {
                        zzkVar3 = zzkVar2;
                    } else if (zzkVar3 != null) {
                        zzkVar3.next = zzkVar4;
                        if (zzkVar3.thread == null) {
                            break;
                        }
                    } else if (!zzc.zze(this, zzkVar2, zzkVar4)) {
                        break;
                    }
                    zzkVar2 = zzkVar4;
                }
                return;
            }
            return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final V zzC(Object obj) throws ExecutionException {
        if (obj instanceof zzb) {
            Throwable th = ((zzb) obj).zzd;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof zzc) {
            throw new ExecutionException(((zzc) obj).zzb);
        }
        if (obj == zzd) {
            return null;
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static Object zzd(zzop<?> zzopVar) {
        Throwable zzn;
        if (zzopVar instanceof zzh) {
            Object obj = ((zzms) zzopVar).value;
            if (!(obj instanceof zzb)) {
                return obj;
            }
            zzb zzbVar = (zzb) obj;
            if (!zzbVar.zzc) {
                return obj;
            }
            Throwable th = zzbVar.zzd;
            return th != null ? new zzb(false, th) : zzb.zzb;
        }
        if (!(zzopVar instanceof zzpe) || (zzn = ((zzpe) zzopVar).zzn()) == null) {
            boolean isCancelled = zzopVar.isCancelled();
            if ((!zzae) & isCancelled) {
                return zzb.zzb;
            }
            try {
                Object zze2 = zze(zzopVar);
                if (!isCancelled) {
                    return zze2 == null ? zzd : zze2;
                }
                String valueOf = String.valueOf(zzopVar);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 84);
                sb.append("get() did not throw CancellationException, despite reporting isCancelled() == true: ");
                sb.append(valueOf);
                return new zzb(false, new IllegalArgumentException(sb.toString()));
            } catch (CancellationException e) {
                if (!isCancelled) {
                    String valueOf2 = String.valueOf(zzopVar);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 77);
                    sb2.append("get() threw CancellationException, despite reporting isCancelled() == false: ");
                    sb2.append(valueOf2);
                    return new zzc(new IllegalArgumentException(sb2.toString(), e));
                }
                return new zzb(false, e);
            } catch (ExecutionException e2) {
                if (isCancelled) {
                    String valueOf3 = String.valueOf(zzopVar);
                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 84);
                    sb3.append("get() did not throw CancellationException, despite reporting isCancelled() == true: ");
                    sb3.append(valueOf3);
                    return new zzb(false, new IllegalArgumentException(sb3.toString(), e2));
                }
                return new zzc(e2.getCause());
            } catch (Throwable th2) {
                return new zzc(th2);
            }
        }
        return new zzc(zzn);
    }

    private static <V> V zze(Future<V> future) throws ExecutionException {
        V v;
        boolean z = false;
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

    private final void zzw(StringBuilder sb) {
        try {
            Object zze2 = zze(this);
            sb.append("SUCCESS, result=[");
            if (zze2 == null) {
                sb.append(JsonSerializer.Null);
            } else if (zze2 == this) {
                sb.append("this future");
            } else {
                sb.append(zze2.getClass().getName());
                sb.append("@");
                sb.append(Integer.toHexString(System.identityHashCode(zze2)));
            }
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e2) {
            sb.append("FAILURE, cause=[");
            sb.append(e2.getCause());
            sb.append("]");
        }
    }

    private final void zzx(StringBuilder sb) {
        String sb2;
        int length = sb.length();
        sb.append("PENDING");
        Object obj = this.value;
        if (obj instanceof zzf) {
            sb.append(", setFuture=[");
            zzy(sb, ((zzf) obj).zzb);
            sb.append("]");
        } else {
            try {
                sb2 = zzju.zza(zza());
            } catch (RuntimeException | StackOverflowError e) {
                String valueOf = String.valueOf(e.getClass());
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 38);
                sb3.append("Exception thrown from implementation: ");
                sb3.append(valueOf);
                sb2 = sb3.toString();
            }
            if (sb2 != null) {
                sb.append(", info=[");
                sb.append(sb2);
                sb.append("]");
            }
        }
        if (isDone()) {
            sb.delete(length, sb.length());
            zzw(sb);
        }
    }

    private final void zzy(StringBuilder sb, Object obj) {
        try {
            if (obj == this) {
                sb.append("this future");
            } else {
                sb.append(obj);
            }
        } catch (RuntimeException | StackOverflowError e) {
            sb.append("Exception thrown from implementation: ");
            sb.append(e.getClass());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzz(zzms<?> zzmsVar) {
        zzd zzdVar;
        zzd zzdVar2;
        zzd zzdVar3 = null;
        while (true) {
            zzk zzkVar = ((zzms) zzmsVar).waiters;
            if (zzc.zze(zzmsVar, zzkVar, zzk.zza)) {
                while (zzkVar != null) {
                    Thread thread = zzkVar.thread;
                    if (thread != null) {
                        zzkVar.thread = null;
                        LockSupport.unpark(thread);
                    }
                    zzkVar = zzkVar.next;
                }
                zzmsVar.zzb();
                do {
                    zzdVar = ((zzms) zzmsVar).listeners;
                } while (!zzc.zzc(zzmsVar, zzdVar, zzd.zza));
                while (true) {
                    zzdVar2 = zzdVar3;
                    zzdVar3 = zzdVar;
                    if (zzdVar3 == null) {
                        break;
                    }
                    zzdVar = zzdVar3.next;
                    zzdVar3.next = zzdVar2;
                }
                while (zzdVar2 != null) {
                    zzdVar3 = zzdVar2.next;
                    Runnable runnable = zzdVar2.zzb;
                    if (runnable instanceof zzf) {
                        zzf zzfVar = (zzf) runnable;
                        zzmsVar = zzfVar.zza;
                        if (((zzms) zzmsVar).value == zzfVar) {
                            if (zzc.zzd(zzmsVar, zzfVar, zzd(zzfVar.zzb))) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        zzA(runnable, zzdVar2.zzc);
                    }
                    zzdVar2 = zzdVar3;
                }
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:?, code lost:
    
        return true;
     */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean cancel(boolean r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.value
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L8
            r3 = r2
            goto L9
        L8:
            r3 = r1
        L9:
            boolean r4 = r0 instanceof com.google.android.gms.internal.recaptcha.zzms.zzf
            r3 = r3 | r4
            if (r3 == 0) goto L61
            boolean r3 = com.google.android.gms.internal.recaptcha.zzms.zzae
            if (r3 == 0) goto L1f
            com.google.android.gms.internal.recaptcha.zzms$zzb r3 = new com.google.android.gms.internal.recaptcha.zzms$zzb
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.String r5 = "Future.cancel() was called."
            r4.<init>(r5)
            r3.<init>(r8, r4)
            goto L26
        L1f:
            if (r8 == 0) goto L24
            com.google.android.gms.internal.recaptcha.zzms$zzb r3 = com.google.android.gms.internal.recaptcha.zzms.zzb.zza
            goto L26
        L24:
            com.google.android.gms.internal.recaptcha.zzms$zzb r3 = com.google.android.gms.internal.recaptcha.zzms.zzb.zzb
        L26:
            r4 = r7
            r5 = r1
        L28:
            com.google.android.gms.internal.recaptcha.zzms$zza r6 = com.google.android.gms.internal.recaptcha.zzms.zzc
            boolean r6 = r6.zzd(r4, r0, r3)
            if (r6 == 0) goto L5a
            if (r8 == 0) goto L35
            r4.zzq()
        L35:
            zzz(r4)
            boolean r4 = r0 instanceof com.google.android.gms.internal.recaptcha.zzms.zzf
            if (r4 == 0) goto L58
            com.google.android.gms.internal.recaptcha.zzms$zzf r0 = (com.google.android.gms.internal.recaptcha.zzms.zzf) r0
            com.google.android.gms.internal.recaptcha.zzop<? extends V> r0 = r0.zzb
            boolean r4 = r0 instanceof com.google.android.gms.internal.recaptcha.zzms.zzh
            if (r4 == 0) goto L55
            r4 = r0
            com.google.android.gms.internal.recaptcha.zzms r4 = (com.google.android.gms.internal.recaptcha.zzms) r4
            java.lang.Object r0 = r4.value
            if (r0 != 0) goto L4d
            r5 = r2
            goto L4e
        L4d:
            r5 = r1
        L4e:
            boolean r6 = r0 instanceof com.google.android.gms.internal.recaptcha.zzms.zzf
            r5 = r5 | r6
            if (r5 == 0) goto L58
            r5 = r2
            goto L28
        L55:
            r0.cancel(r8)
        L58:
            r1 = r2
            goto L61
        L5a:
            java.lang.Object r0 = r4.value
            boolean r6 = r0 instanceof com.google.android.gms.internal.recaptcha.zzms.zzf
            if (r6 != 0) goto L28
            r1 = r5
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.recaptcha.zzms.cancel(boolean):boolean");
    }

    @Override // java.util.concurrent.Future
    public final V get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) & (!(obj2 instanceof zzf))) {
                return (V) zzC(obj2);
            }
            zzk zzkVar = this.waiters;
            if (zzkVar != zzk.zza) {
                zzk zzkVar2 = new zzk();
                do {
                    zza zzaVar = zzc;
                    zzaVar.zza(zzkVar2, zzkVar);
                    if (zzaVar.zze(this, zzkVar, zzkVar2)) {
                        do {
                            LockSupport.park(this);
                            if (Thread.interrupted()) {
                                zzB(zzkVar2);
                                throw new InterruptedException();
                            }
                            obj = this.value;
                        } while (!((obj != null) & (!(obj instanceof zzf))));
                        return (V) zzC(obj);
                    }
                    zzkVar = this.waiters;
                } while (zzkVar != zzk.zza);
            }
            return (V) zzC(this.value);
        }
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.value instanceof zzb;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return (!(r0 instanceof zzf)) & (this.value != null);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append(getClass().getName());
        }
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[status=");
        if (this.value instanceof zzb) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            zzw(sb);
        } else {
            zzx(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @NullableDecl
    public String zza() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        long delay = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
        StringBuilder sb = new StringBuilder(41);
        sb.append("remaining delay=[");
        sb.append(delay);
        sb.append(" ms]");
        return sb.toString();
    }

    protected void zzb() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzpe
    @NullableDecl
    public final Throwable zzn() {
        if (!(this instanceof zzh)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof zzc) {
            return ((zzc) obj).zzb;
        }
        return null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzop
    public final void zzp(Runnable runnable, Executor executor) {
        zzd zzdVar;
        zzjn.zzc(runnable, "Runnable was null.");
        zzjn.zzc(executor, "Executor was null.");
        if (!isDone() && (zzdVar = this.listeners) != zzd.zza) {
            zzd zzdVar2 = new zzd(runnable, executor);
            do {
                zzdVar2.next = zzdVar;
                if (zzc.zzc(this, zzdVar, zzdVar2)) {
                    return;
                } else {
                    zzdVar = this.listeners;
                }
            } while (zzdVar != zzd.zza);
        }
        zzA(runnable, executor);
    }

    protected void zzq() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzr(@NullableDecl Future<?> future) {
        if ((future != null) && (this.value instanceof zzb)) {
            future.cancel(zzv());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean zzt(@NullableDecl V v) {
        if (v == null) {
            v = (V) zzd;
        }
        if (!zzc.zzd(this, null, v)) {
            return false;
        }
        zzz(this);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zzu(Throwable th) {
        th.getClass();
        if (!zzc.zzd(this, null, new zzc(th))) {
            return false;
        }
        zzz(this);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zzv() {
        Object obj = this.value;
        return (obj instanceof zzb) && ((zzb) obj).zzc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean zzc(zzop<? extends V> zzopVar) {
        zzc zzcVar;
        zzopVar.getClass();
        Object obj = this.value;
        if (obj == null) {
            if (zzopVar.isDone()) {
                if (!zzc.zzd(this, null, zzd(zzopVar))) {
                    return false;
                }
                zzz(this);
                return true;
            }
            zzf zzfVar = new zzf(this, zzopVar);
            if (zzc.zzd(this, null, zzfVar)) {
                try {
                    zzopVar.zzp(zzfVar, zznn.INSTANCE);
                } catch (Throwable th) {
                    try {
                        zzcVar = new zzc(th);
                    } catch (Throwable unused) {
                        zzcVar = zzc.zza;
                    }
                    zzc.zzd(this, zzfVar, zzcVar);
                }
                return true;
            }
            obj = this.value;
        }
        if (obj instanceof zzb) {
            zzopVar.cancel(((zzb) obj).zzc);
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public final V get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long nanos = timeUnit.toNanos(j);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            boolean z = true;
            if ((obj != null) & (!(obj instanceof zzf))) {
                return (V) zzC(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
            if (nanos >= 1000) {
                zzk zzkVar = this.waiters;
                if (zzkVar != zzk.zza) {
                    zzk zzkVar2 = new zzk();
                    do {
                        zza zzaVar = zzc;
                        zzaVar.zza(zzkVar2, zzkVar);
                        if (zzaVar.zze(this, zzkVar, zzkVar2)) {
                            do {
                                LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                                if (Thread.interrupted()) {
                                    zzB(zzkVar2);
                                    throw new InterruptedException();
                                }
                                Object obj2 = this.value;
                                if (!((obj2 != null) & (!(obj2 instanceof zzf)))) {
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    return (V) zzC(obj2);
                                }
                            } while (nanos >= 1000);
                            zzB(zzkVar2);
                        } else {
                            zzkVar = this.waiters;
                        }
                    } while (zzkVar != zzk.zza);
                }
                return (V) zzC(this.value);
            }
            while (nanos > 0) {
                Object obj3 = this.value;
                if (!((obj3 != null) & (!(obj3 instanceof zzf)))) {
                    if (!Thread.interrupted()) {
                        nanos = nanoTime - System.nanoTime();
                    } else {
                        throw new InterruptedException();
                    }
                } else {
                    return (V) zzC(obj3);
                }
            }
            String zzmsVar = toString();
            String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
            String lowerCase2 = timeUnit.toString().toLowerCase(Locale.ROOT);
            StringBuilder sb = new StringBuilder(String.valueOf(lowerCase2).length() + 28);
            sb.append("Waited ");
            sb.append(j);
            sb.append(" ");
            sb.append(lowerCase2);
            String sb2 = sb.toString();
            if (nanos + 1000 < 0) {
                String concat = String.valueOf(sb2).concat(" (plus ");
                long j2 = -nanos;
                long convert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
                long nanos2 = j2 - timeUnit.toNanos(convert);
                if (convert != 0 && nanos2 <= 1000) {
                    z = false;
                }
                if (convert > 0) {
                    String valueOf = String.valueOf(concat);
                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 21 + String.valueOf(lowerCase).length());
                    sb3.append(valueOf);
                    sb3.append(convert);
                    sb3.append(" ");
                    sb3.append(lowerCase);
                    String sb4 = sb3.toString();
                    if (z) {
                        sb4 = String.valueOf(sb4).concat(",");
                    }
                    concat = String.valueOf(sb4).concat(" ");
                }
                if (z) {
                    String valueOf2 = String.valueOf(concat);
                    StringBuilder sb5 = new StringBuilder(String.valueOf(valueOf2).length() + 33);
                    sb5.append(valueOf2);
                    sb5.append(nanos2);
                    sb5.append(" nanoseconds ");
                    concat = sb5.toString();
                }
                sb2 = String.valueOf(concat).concat("delay)");
            }
            if (isDone()) {
                throw new TimeoutException(String.valueOf(sb2).concat(" but future completed as timeout expired"));
            }
            StringBuilder sb6 = new StringBuilder(String.valueOf(sb2).length() + 5 + String.valueOf(zzmsVar).length());
            sb6.append(sb2);
            sb6.append(" for ");
            sb6.append(zzmsVar);
            throw new TimeoutException(sb6.toString());
        }
        throw new InterruptedException();
    }
}
