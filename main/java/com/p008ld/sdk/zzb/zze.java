package com.p008ld.sdk.zzb;

import com.p008ld.sdk.internal.LDNative;
import com.p008ld.sdk.util.zzf;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LDFileCache.kt */
/* loaded from: classes2.dex */
public final class zze extends zzd {
    @Override // com.p008ld.sdk.zzb.zza
    public void zza(String key, String json) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(json, "json");
        if (StringsKt.isBlank(key) || StringsKt.isBlank(json)) {
            return;
        }
        zzf.zza(LDNative.INSTANCE.encrypt(key), json);
    }

    @Override // com.p008ld.sdk.zzb.zza
    public String zza(String key, long j) {
        Intrinsics.checkNotNullParameter(key, "key");
        return StringsKt.isBlank(key) ? "" : zzf.zza(LDNative.INSTANCE.encrypt(key), j);
    }

    @Override // com.p008ld.sdk.zzb.zza
    public void zza(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        zzf.zza(LDNative.INSTANCE.encrypt(key));
    }
}
