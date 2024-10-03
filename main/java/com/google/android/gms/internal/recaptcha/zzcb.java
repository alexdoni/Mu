package com.google.android.gms.internal.recaptcha;

import android.content.Context;
import android.net.Uri;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzcb {
    public static final zzfx<zzdk> zza(Context context, zzgb zzgbVar) {
        zzek zza = zzel.zza(context);
        zza.zzb("recaptcha");
        zza.zzc("token.pb");
        Uri zza2 = zza.zza();
        zzfy zzi = zzfz.zzi();
        zzi.zze(zza2);
        zzi.zzc(zzdk.zzb());
        zzi.zzf(false);
        return zzgbVar.zza(zzi.zzg());
    }
}
