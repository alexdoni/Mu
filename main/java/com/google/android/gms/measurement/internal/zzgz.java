package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.C0923R;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzgz {
    private final Resources zza;
    private final String zzb;

    public final String zza(String str) {
        int identifier = this.zza.getIdentifier(str, TypedValues.Custom.S_STRING, this.zzb);
        if (identifier == 0) {
            return null;
        }
        try {
            return this.zza.getString(identifier);
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }

    public static String zza(Context context) {
        try {
            return context.getResources().getResourcePackageName(C0923R.string.common_google_play_services_unknown_issue);
        } catch (Resources.NotFoundException unused) {
            return context.getPackageName();
        }
    }

    public zzgz(Context context, String str) {
        Preconditions.checkNotNull(context);
        this.zza = context.getResources();
        if (!TextUtils.isEmpty(str)) {
            this.zzb = str;
        } else {
            this.zzb = zza(context);
        }
    }
}
