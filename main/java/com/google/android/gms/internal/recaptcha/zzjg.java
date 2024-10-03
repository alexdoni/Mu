package com.google.android.gms.internal.recaptcha;

import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzjg {
    private final String zza;
    private final zzjf zzb;
    private zzjf zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzjg(String str, zzje zzjeVar) {
        zzjf zzjfVar = new zzjf(null);
        this.zzb = zzjfVar;
        this.zzc = zzjfVar;
        str.getClass();
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append('{');
        zzjf zzjfVar = this.zzb.zzb;
        String str = "";
        while (zzjfVar != null) {
            Object obj = zzjfVar.zza;
            sb.append(str);
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                sb.append((CharSequence) Arrays.deepToString(new Object[]{obj}), 1, r3.length() - 1);
            }
            zzjfVar = zzjfVar.zzb;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzjg zza(@CheckForNull Object obj) {
        zzjf zzjfVar = new zzjf(null);
        this.zzc.zzb = zzjfVar;
        this.zzc = zzjfVar;
        zzjfVar.zza = obj;
        return this;
    }
}
