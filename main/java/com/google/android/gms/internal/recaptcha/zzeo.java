package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import android.text.TextUtils;
import com.facebook.share.internal.ShareInternalUtility;
import java.io.File;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzeo {
    private static final zzeo zza = new zzeo();

    private zzeo() {
    }

    public static final File zza(Uri uri) throws zzeu {
        if (!uri.getScheme().equals(ShareInternalUtility.STAGING_PARAM)) {
            throw new zzeu("Scheme must be 'file'");
        }
        if (!TextUtils.isEmpty(uri.getQuery())) {
            throw new zzeu("Did not expect uri to have query");
        }
        if (TextUtils.isEmpty(uri.getAuthority())) {
            return new File(uri.getPath());
        }
        throw new zzeu("Did not expect uri to have authority");
    }
}
