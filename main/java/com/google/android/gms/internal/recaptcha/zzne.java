package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzms;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzne<OutputT> extends zzms.zzi<OutputT> {
    private static final zznb zza;
    private static final Logger zzb = Logger.getLogger(zzne.class.getName());
    private volatile int remaining;

    @CheckForNull
    private volatile Set<Throwable> seenExceptions = null;

    static {
        zznb zzndVar;
        Throwable th;
        zzna zznaVar = null;
        try {
            zzndVar = new zznc(AtomicReferenceFieldUpdater.newUpdater(zzne.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(zzne.class, "remaining"));
            th = null;
        } catch (Throwable th2) {
            zzndVar = new zznd(zznaVar);
            th = th2;
        }
        zza = zzndVar;
        if (th != null) {
            zzb.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFutureState", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzne(int i) {
        this.remaining = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int zzB(zzne zzneVar) {
        int i = zzneVar.remaining - 1;
        zzneVar.remaining = i;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzC() {
        return zza.zza(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set<Throwable> zzF() {
        Set<Throwable> set = this.seenExceptions;
        if (set != null) {
            return set;
        }
        Set<Throwable> newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        zzx(newSetFromMap);
        zza.zzb(this, null, newSetFromMap);
        Set<Throwable> set2 = this.seenExceptions;
        set2.getClass();
        return set2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzG() {
        this.seenExceptions = null;
    }

    abstract void zzx(Set<Throwable> set);
}
