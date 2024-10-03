package com.p008ld.sdk.zzd.zzb;

import okhttp3.internal.platform.Platform;

/* compiled from: Logger.java */
/* loaded from: classes2.dex */
public interface zzc {
    public static final zzc zza = new zzc() { // from class: com.ld.sdk.zzd.zzb.zzc.1
        @Override // com.p008ld.sdk.zzd.zzb.zzc
        public void zza(int i, String str, String str2) {
            Platform.get().log(str2, i, null);
        }
    };

    void zza(int i, String str, String str2);
}
