package com.google.android.gms.internal.recaptcha;

import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzky {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(Set<?> set) {
        Iterator<?> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i += next != null ? next.hashCode() : 0;
        }
        return i;
    }
}
