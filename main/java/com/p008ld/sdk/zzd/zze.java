package com.p008ld.sdk.zzd;

import com.p008ld.sdk.bean.CoolingOffBean;
import com.p008ld.sdk.track.ITrack;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;

/* compiled from: LDConfig.kt */
/* loaded from: classes2.dex */
public final class zze {
    private final Function1<String, Unit> zza;
    private final Function0<Unit> zzb;
    private final Function1<CoolingOffBean, Unit> zzc;
    private final List<ITrack> zzd;

    public zze(zza builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.zza = builder.zza();
        this.zzb = builder.zzb();
        this.zzc = builder.zzc();
        this.zzd = Util.toImmutableList(builder.zzd());
    }

    public zze() {
        this(new zza());
    }

    public final Function1<String, Unit> zza() {
        return this.zza;
    }

    public final Function0<Unit> zzb() {
        return this.zzb;
    }

    public final Function1<CoolingOffBean, Unit> zzc() {
        return this.zzc;
    }

    public final List<ITrack> zzd() {
        return this.zzd;
    }

    /* compiled from: LDConfig.kt */
    /* loaded from: classes2.dex */
    public static final class zza {
        private Function1<? super String, Unit> zza;
        private Function0<Unit> zzb;
        private Function1<? super CoolingOffBean, Unit> zzc;
        private final List<ITrack> zzd = new ArrayList();

        public final Function1<String, Unit> zza() {
            return this.zza;
        }

        public final Function0<Unit> zzb() {
            return this.zzb;
        }

        public final Function1<CoolingOffBean, Unit> zzc() {
            return this.zzc;
        }

        public final List<ITrack> zzd() {
            return this.zzd;
        }

        public final zza zza(Function1<? super String, Unit> function1) {
            this.zza = function1;
            return this;
        }

        public final zza zza(Function0<Unit> function0) {
            this.zzb = function0;
            return this;
        }

        public final zza zzb(Function1<? super CoolingOffBean, Unit> function1) {
            this.zzc = function1;
            return this;
        }

        public final zza zza(ITrack track) {
            Intrinsics.checkNotNullParameter(track, "track");
            this.zzd.add(track);
            return this;
        }

        public final zze zze() {
            return new zze(this);
        }
    }
}
