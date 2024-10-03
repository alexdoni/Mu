package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzfa;
import com.google.android.gms.internal.play_billing.zzfb;
import com.google.android.gms.internal.play_billing.zzfe;
import com.google.android.gms.internal.play_billing.zzff;
import com.google.android.gms.internal.play_billing.zzfh;
import com.google.android.gms.internal.play_billing.zzfj;

/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes.dex */
public final /* synthetic */ class zzba {
    public static zzfb zza(int i, int i2, BillingResult billingResult) {
        zzfa zzu = zzfb.zzu();
        zzfh zzu2 = zzfj.zzu();
        zzu2.zzj(billingResult.getResponseCode());
        zzu2.zzi(billingResult.getDebugMessage());
        zzu2.zzk(i);
        zzu.zzi(zzu2);
        zzu.zzj(i2);
        return (zzfb) zzu.zzc();
    }

    public static zzff zzb(int i) {
        zzfe zzu = zzff.zzu();
        zzu.zzi(i);
        return (zzff) zzu.zzc();
    }
}
