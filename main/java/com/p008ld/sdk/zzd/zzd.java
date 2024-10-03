package com.p008ld.sdk.zzd;

import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.util.LDUtil;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: LDApiModel.kt */
/* loaded from: classes2.dex */
public abstract class zzd<T> {
    public static final zza zza = new zza(null);
    private ConcurrentHashMap<String, T> zzb = new ConcurrentHashMap<>();

    protected abstract Class<T> zza();

    /* compiled from: LDApiModel.kt */
    /* loaded from: classes2.dex */
    public static final class zza {
        public /* synthetic */ zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String zzd() {
            return "https://middledata.ldplayer.net";
        }

        private zza() {
        }

        public final String zza() {
            return LDSdk.isTestMode() ? "https://test-overseas-usercenter.ldmnq.com/" : "https://usersdk.ldmnq.com/";
        }

        public final String zzb() {
            return LDSdk.isTestMode() ? "https://test-overseas-paycenter.ldmnq.com/" : "https://paysdk.ldmnq.com/";
        }

        public final String zzc() {
            return LDSdk.isTestMode() ? "https://debug.ld-space.com" : "https://api.ld-space.com";
        }

        public final String zze() {
            if (LDSdk.isTestMode()) {
                return "https://pre-prod-store-" + LDUtil.INSTANCE.getAreaFromLanguage() + ".ldplayer.net";
            }
            return "https://store-" + LDUtil.INSTANCE.getAreaFromLanguage() + ".ldplayer.net";
        }
    }

    public final T zzc() {
        return zza(zza.zza());
    }

    public final T zzd() {
        return zza(zza.zza());
    }

    public final T zze() {
        return zza(zza.zzb());
    }

    public final T zzf() {
        return zza(zza.zzc());
    }

    public final T zzg() {
        return zza(zza.zzd());
    }

    public final T zzh() {
        return zza(zza.zze());
    }

    private final T zza(String str) {
        T t = this.zzb.get(str);
        if (t != null) {
            return t;
        }
        T t2 = (T) zzb.zza.zza().zza(str, zza());
        T t3 = (T) this.zzb.putIfAbsent(str, t2);
        return t3 == null ? t2 : t3;
    }
}
