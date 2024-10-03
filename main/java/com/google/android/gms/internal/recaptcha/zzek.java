package com.google.android.gms.internal.recaptcha;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzek {
    private final Context zza;
    private final String zzb;
    private final String zzc = "files";
    private String zzd = "common";
    private final Account zze = zzel.zza;
    private String zzf = "";
    private final zzkf<String> zzg = zzkj.zzj();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzek(Context context, zzej zzejVar) {
        zzfc.zza(context != null, "Context cannot be null", new Object[0]);
        this.zza = context;
        this.zzb = context.getPackageName();
    }

    public final Uri zza() {
        return new Uri.Builder().scheme("android").authority(this.zzb).path(String.format("/%s/%s/%s/%s", this.zzc, this.zzd, zzee.zzb(this.zze), this.zzf)).encodedFragment(zzfb.zza(this.zzg.zze())).build();
    }

    public final zzek zzb(String str) {
        zzel.zzb("recaptcha");
        this.zzd = "recaptcha";
        return this;
    }

    public final zzek zzc(String str) {
        int i = zzel.zzb;
        this.zzf = "token.pb";
        return this;
    }
}
