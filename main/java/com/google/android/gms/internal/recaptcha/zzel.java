package com.google.android.gms.internal.recaptcha;

import android.accounts.Account;
import android.content.Context;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzel {
    public static final /* synthetic */ int zzb = 0;
    private static final Pattern zzc = Pattern.compile("[a-z]+(_[a-z]+)*");
    static final Account zza = zzee.zza;
    private static final Set<String> zzd = Collections.unmodifiableSet(new HashSet(Arrays.asList("default", "unused", "special", "reserved", "shared", "virtual", "managed")));
    private static final Set<String> zze = Collections.unmodifiableSet(new HashSet(Arrays.asList("files", "cache", "managed", "directboot-files", "directboot-cache", "external")));

    public static zzek zza(Context context) {
        return new zzek(context, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(String str) {
        zzfc.zza(zzc.matcher("recaptcha").matches(), "Module must match [a-z]+(_[a-z]+)*: %s", "recaptcha");
        zzfc.zza(!zzd.contains("recaptcha"), "Module name is reserved and cannot be used: %s", "recaptcha");
    }
}
