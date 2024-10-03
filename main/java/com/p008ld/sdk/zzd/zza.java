package com.p008ld.sdk.zzd;

import android.content.Context;
import android.os.Bundle;
import com.p008ld.sdk.internal.LDCallback;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.track.ITrack;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.zzm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: LDApi.kt */
/* loaded from: classes2.dex */
public final class zza {
    public static final C3263zza zza = new C3263zza(null);
    public static zze zzb;
    private static volatile zza zzc;

    public /* synthetic */ zza(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    public static final synchronized zza zza() {
        zza zza2;
        synchronized (zza.class) {
            zza2 = zza.zza();
        }
        return zza2;
    }

    private zza() {
    }

    /* compiled from: LDApi.kt */
    /* renamed from: com.ld.sdk.zzd.zza$zza */
    /* loaded from: classes2.dex */
    public static final class C3263zza {
        public /* synthetic */ C3263zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private C3263zza() {
        }

        @JvmStatic
        public final synchronized zza zza() {
            zza zzaVar;
            zzaVar = null;
            if (zza.zzc == null) {
                zza.zzc = new zza(null);
            }
            zza zzaVar2 = zza.zzc;
            if (zzaVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("INSTANCE");
            } else {
                zzaVar = zzaVar2;
            }
            return zzaVar;
        }

        @JvmStatic
        public final void zza(zze config) {
            Intrinsics.checkNotNullParameter(config, "config");
            C3263zza c3263zza = zza.zza;
            zza.zzb = config;
        }
    }

    private final List<ITrack> zzc() {
        List<ITrack> zzd;
        zze zzeVar = zzb;
        return (zzeVar == null || (zzd = zzeVar.zzd()) == null) ? new ArrayList() : zzd;
    }

    public final void zza(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            Iterator<T> it = zzc().iterator();
            while (it.hasNext()) {
                ((ITrack) it.next()).init(context);
            }
            Result.m1882constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m1882constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void zza(zza zzaVar, String str, String str2, Bundle bundle, LDCallback lDCallback, int i, Object obj) {
        if ((i & 4) != 0) {
            bundle = new Bundle();
        }
        if ((i & 8) != 0) {
            lDCallback = null;
        }
        zzaVar.zza(str, str2, bundle, lDCallback);
    }

    public final void zza(String tokenName, String tokenCode, Bundle bundle, LDCallback<Boolean> lDCallback) {
        Object m1882constructorimpl;
        Intrinsics.checkNotNullParameter(tokenName, "tokenName");
        Intrinsics.checkNotNullParameter(tokenCode, "tokenCode");
        try {
            Result.Companion companion = Result.INSTANCE;
            for (ITrack iTrack : zzc()) {
                boolean z = true;
                if (iTrack.isTrackTokenName()) {
                    if (!(tokenName.length() == 0)) {
                        iTrack.trackEvent(tokenName, bundle, lDCallback);
                    }
                }
                if (!iTrack.isTrackTokenName()) {
                    if (tokenCode.length() != 0) {
                        z = false;
                    }
                    if (!z) {
                        iTrack.trackEvent(tokenCode, bundle, lDCallback);
                    }
                }
            }
            m1882constructorimpl = Result.m1882constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1885exceptionOrNullimpl = Result.m1885exceptionOrNullimpl(m1882constructorimpl);
        if (m1885exceptionOrNullimpl != null) {
            LDLog.m573e("trackEvent fail: " + m1885exceptionOrNullimpl);
            zzm.zza(new zzb(lDCallback, m1885exceptionOrNullimpl));
        }
    }

    /* compiled from: LDApi.kt */
    /* loaded from: classes2.dex */
    public static final class zzb extends Lambda implements Function0<Unit> {
        final /* synthetic */ LDCallback<Boolean> zza;
        final /* synthetic */ Throwable zzb;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzb(LDCallback<Boolean> lDCallback, Throwable th) {
            super(0);
            this.zza = lDCallback;
            this.zzb = th;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* synthetic */ Unit invoke() {
            zza();
            return Unit.INSTANCE;
        }

        public final void zza() {
            LDCallback<Boolean> lDCallback = this.zza;
            if (lDCallback != null) {
                lDCallback.done(false, new LDException(this.zzb));
            }
        }
    }
}
