package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzjd {
    static final CharSequence zza(@CheckForNull Object obj, String str) {
        obj.getClass();
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    public static final StringBuilder zzb(StringBuilder sb, Iterator<? extends Object> it, String str) {
        try {
            if (it.hasNext()) {
                sb.append(zza(it.next(), str));
                while (it.hasNext()) {
                    sb.append((CharSequence) str);
                    sb.append(zza(it.next(), str));
                }
            }
            return sb;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
