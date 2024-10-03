package com.google.android.gms.recaptcha;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.internal.recaptcha.zzaq;
import com.google.android.gms.internal.recaptcha.zzbc;
import com.google.android.gms.internal.recaptcha.zzbz;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class Recaptcha {
    private static GoogleApiAvailabilityLight zza = GoogleApiAvailabilityLight.getInstance();
    private static int zzb = 1;

    private Recaptcha() {
    }

    public static RecaptchaClient getClient(Activity activity) {
        if (zza(activity)) {
            return zzaq.zza(activity);
        }
        return new zzbc(activity);
    }

    private static boolean zza(Context context) {
        ExecutorService executorService = zzbz.zza;
        int i = zzb;
        if (i == 0) {
            throw null;
        }
        if (i == 1) {
            int isGooglePlayServicesAvailable = zza.isGooglePlayServicesAvailable(context, 18223000);
            if (isGooglePlayServicesAvailable != 1 && isGooglePlayServicesAvailable != 9 && isGooglePlayServicesAvailable != 3) {
                zzb = 2;
            } else {
                zzb = 3;
            }
        }
        int i2 = zzb;
        if (i2 != 0) {
            return i2 == 3;
        }
        throw null;
    }

    public static RecaptchaClient getClient(Context context) {
        if (zza(context)) {
            return zzaq.zzb(context);
        }
        return new zzbc(context);
    }
}
