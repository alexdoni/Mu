package com.google.android.gms.internal.play_billing;

import java.util.Objects;

/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes2.dex */
final class zzp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, Object obj2) {
        if (obj == null) {
            Objects.toString(obj2);
            throw new NullPointerException("null key in entry: null=".concat(String.valueOf(obj2)));
        }
        if (obj2 != null) {
            return;
        }
        throw new NullPointerException("null value in entry: " + obj + "=null");
    }
}
