package com.google.android.gms.internal.recaptcha;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzdb {
    public static String zza(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.recaptcha.internal.VERIFICATION_HISTORY_FILE_KEY", 0);
        String zzc = zzc(str);
        return sharedPreferences.contains(zzc) ? sharedPreferences.getString(zzc, "") : "";
    }

    public static void zzb(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences("com.google.android.gms.recaptcha.internal.VERIFICATION_HISTORY_FILE_KEY", 0).edit();
        edit.putString(zzc(str2), str);
        edit.apply();
    }

    private static String zzc(String str) {
        String valueOf = String.valueOf(str);
        return valueOf.length() != 0 ? "verification_history_token_key:".concat(valueOf) : new String("verification_history_token_key:");
    }
}
