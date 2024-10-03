package com.google.android.gms.internal.recaptcha;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzfb {
    private static final Pattern zza = Pattern.compile("(\\w+).*");

    @Nullable
    public static String zza(List<String> list) {
        if (list.isEmpty()) {
            return null;
        }
        zzlb listIterator = ((zzkj) list).listIterator(0);
        StringBuilder sb = new StringBuilder();
        zzjd.zzb(sb, listIterator, "+");
        String valueOf = String.valueOf(sb.toString());
        return valueOf.length() != 0 ? "transform=".concat(valueOf) : new String("transform=");
    }

    public static String zzb(String str) {
        Matcher matcher = zza.matcher(str);
        if (!matcher.matches()) {
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Invalid fragment spec: ".concat(valueOf) : new String("Invalid fragment spec: "));
        }
        return matcher.group(1);
    }
}
