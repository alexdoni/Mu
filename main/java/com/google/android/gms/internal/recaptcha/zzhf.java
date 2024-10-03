package com.google.android.gms.internal.recaptcha;

import android.net.Uri;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzhf {
    public static Uri zza(Uri uri, String str) {
        Uri.Builder buildUpon = uri.buildUpon();
        String valueOf = String.valueOf(uri.getPath());
        return buildUpon.path(str.length() != 0 ? valueOf.concat(str) : new String(valueOf)).build();
    }
}
