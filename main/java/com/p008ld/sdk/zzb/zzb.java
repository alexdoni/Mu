package com.p008ld.sdk.zzb;

import java.lang.reflect.Type;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDCache.kt */
/* loaded from: classes2.dex */
public abstract class zzb implements com.p008ld.sdk.zzb.zza {
    public static final zza zza = new zza(null);
    private static volatile zzb zzb;

    public abstract <T> T zza(String str, long j, Class<T> cls);

    public abstract <T> T zza(String str, long j, Type type);

    public abstract <T> void zza(String str, T t);

    public abstract <T> void zza(String str, List<? extends T> list);

    /* compiled from: LDCache.kt */
    /* loaded from: classes2.dex */
    public static final class zza {
        public /* synthetic */ zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private zza() {
        }

        @JvmStatic
        public final zzb zza() {
            if (zzb.zzb == null) {
                synchronized (zzb.class) {
                    if (zzb.zzb == null || zzb.zza.zza() == null) {
                        zza zzaVar = zzb.zza;
                        zzb.zzb = new zze();
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            zzb zzbVar = zzb.zzb;
            Intrinsics.checkNotNull(zzbVar);
            return zzbVar;
        }
    }
}
