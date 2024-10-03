package com.google.android.gms.internal.recaptcha;

import android.accounts.Account;
import com.facebook.internal.security.CertificateUtil;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzee {
    public static final Account zza = new Account("shared", "mobstore");

    public static Account zza(String str) {
        if ("shared".equals(str)) {
            return zza;
        }
        int indexOf = str.indexOf(58);
        zzfc.zza(indexOf >= 0, "Malformed account", new Object[0]);
        return new Account(str.substring(indexOf + 1), str.substring(0, indexOf));
    }

    public static String zzb(Account account) {
        zzfc.zza(account.type.indexOf(58) == -1, "Account type contains ':'.", new Object[0]);
        zzfc.zza(account.type.indexOf(47) == -1, "Account type contains '/'.", new Object[0]);
        zzfc.zza(account.name.indexOf(47) == -1, "Account name contains '/'.", new Object[0]);
        if (zza.equals(account)) {
            return "shared";
        }
        String str = account.type;
        String str2 = account.name;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(CertificateUtil.DELIMITER);
        sb.append(str2);
        return sb.toString();
    }
}
