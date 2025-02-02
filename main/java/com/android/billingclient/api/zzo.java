package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes.dex */
public final class zzo {
    private final Context zza;
    private final zzn zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(Context context, zzbf zzbfVar, zzbh zzbhVar) {
        this.zza = context;
        this.zzb = new zzn(this, null, zzbhVar, 0 == true ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzbf zzb() {
        zzn.zza(this.zzb);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final PurchasesUpdatedListener zzc() {
        return zzn.zzb(this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd() {
        this.zzb.zzd(this.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze() {
        IntentFilter intentFilter = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        intentFilter.addAction("com.android.vending.billing.ALTERNATIVE_BILLING");
        this.zzb.zzc(this.zza, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(Context context, PurchasesUpdatedListener purchasesUpdatedListener, AlternativeBillingListener alternativeBillingListener, zzbh zzbhVar) {
        this.zza = context;
        this.zzb = new zzn(this, purchasesUpdatedListener, alternativeBillingListener, zzbhVar, null);
    }
}
