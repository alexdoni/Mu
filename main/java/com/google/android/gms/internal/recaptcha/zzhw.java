package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzii;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
abstract class zzhw<T extends zzii> extends zzht<T> {
    private final zzih zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhw(String str, T t, zzih zzihVar) {
        super(str, t);
        zzjn.zze(zzihVar.zzb());
        this.zza = zzihVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhw(String str, UUID uuid, zzih zzihVar) {
        super(str, uuid);
        zzjn.zze(zzihVar.zzb());
        this.zza = zzihVar;
    }
}
