package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import com.facebook.share.internal.ShareInternalUtility;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzen {
    private final Uri.Builder zza = new Uri.Builder().scheme(ShareInternalUtility.STAGING_PARAM).authority("").path(RemoteSettings.FORWARD_SLASH_STRING);
    private final zzkf<String> zzb = zzkj.zzj();

    private zzen() {
    }

    public final Uri zza() {
        return this.zza.encodedFragment(zzfb.zza(this.zzb.zze())).build();
    }

    public final zzen zzb(File file) {
        this.zza.path(file.getAbsolutePath());
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzen(zzem zzemVar) {
    }
}
