package com.google.android.gms.internal.recaptcha;

import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import javax.annotation.CheckForNull;
import kotlin.UByte$$ExternalSyntheticBackport0;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zznc extends zznb {
    final AtomicReferenceFieldUpdater<zzne<?>, Set<Throwable>> zza;
    final AtomicIntegerFieldUpdater<zzne<?>> zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zznc(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
        super(null);
        this.zza = atomicReferenceFieldUpdater;
        this.zzb = atomicIntegerFieldUpdater;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zznb
    public final int zza(zzne<?> zzneVar) {
        return this.zzb.decrementAndGet(zzneVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zznb
    public final void zzb(zzne<?> zzneVar, @CheckForNull Set<Throwable> set, Set<Throwable> set2) {
        UByte$$ExternalSyntheticBackport0.m1412m(this.zza, zzneVar, (Object) null, set2);
    }
}
