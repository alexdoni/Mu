package com.android.billingclient.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import com.google.android.gms.internal.play_billing.zzfb;
import java.util.List;
import org.json.JSONException;

/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes.dex */
public final class zzn extends BroadcastReceiver {
    final /* synthetic */ zzo zza;
    private final PurchasesUpdatedListener zzb;
    private final zzbf zzc;
    private final AlternativeBillingListener zzd;
    private boolean zze;
    private final zzbh zzf;

    public /* synthetic */ zzn(zzo zzoVar, zzbf zzbfVar, zzbh zzbhVar, zzm zzmVar) {
        this.zza = zzoVar;
        this.zzb = null;
        this.zzd = null;
        this.zzc = null;
        this.zzf = zzbhVar;
    }

    public static /* bridge */ /* synthetic */ zzbf zza(zzn zznVar) {
        zzbf zzbfVar = zznVar.zzc;
        return null;
    }

    private static final void zze(Bundle bundle, BillingResult billingResult, int i) {
        if (bundle.getByteArray("FAILURE_LOGGING_PAYLOAD") != null) {
            try {
                zzfb.zzw(bundle.getByteArray("FAILURE_LOGGING_PAYLOAD"), com.google.android.gms.internal.play_billing.zzbn.zza());
                return;
            } catch (Throwable unused) {
                com.google.android.gms.internal.play_billing.zzb.zzj("BillingBroadcastManager", "Failed parsing Api failure.");
                return;
            }
        }
        zzba.zza(23, i, billingResult);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            com.google.android.gms.internal.play_billing.zzb.zzj("BillingBroadcastManager", "Bundle is null.");
            zzba.zza(11, 1, zzbc.zzj);
            PurchasesUpdatedListener purchasesUpdatedListener = this.zzb;
            if (purchasesUpdatedListener != null) {
                purchasesUpdatedListener.onPurchasesUpdated(zzbc.zzj, null);
                return;
            }
            return;
        }
        BillingResult zzd = com.google.android.gms.internal.play_billing.zzb.zzd(intent, "BillingBroadcastManager");
        String action = intent.getAction();
        String string = extras.getString("INTENT_SOURCE");
        int i = 2;
        if (string != "LAUNCH_BILLING_FLOW" && (string == null || !string.equals("LAUNCH_BILLING_FLOW"))) {
            i = 1;
        }
        if (action.equals("com.android.vending.billing.PURCHASES_UPDATED")) {
            if (extras.getBoolean("IS_FIRST_PARTY_PURCHASE", false) || this.zzb == null) {
                com.google.android.gms.internal.play_billing.zzb.zzj("BillingBroadcastManager", "Received purchase and no valid listener registered.");
                zzba.zza(12, i, zzbc.zzj);
                return;
            }
            List<Purchase> zzh = com.google.android.gms.internal.play_billing.zzb.zzh(extras);
            if (zzd.getResponseCode() == 0) {
                zzba.zzb(i);
            } else {
                zze(extras, zzd, i);
            }
            this.zzb.onPurchasesUpdated(zzd, zzh);
            return;
        }
        if (action.equals("com.android.vending.billing.ALTERNATIVE_BILLING")) {
            if (zzd.getResponseCode() != 0) {
                zze(extras, zzd, i);
                this.zzb.onPurchasesUpdated(zzd, com.google.android.gms.internal.play_billing.zzu.zzk());
                return;
            }
            if (this.zzd == null) {
                com.google.android.gms.internal.play_billing.zzb.zzj("BillingBroadcastManager", "AlternativeBillingListener is null.");
                zzba.zza(15, i, zzbc.zzj);
                this.zzb.onPurchasesUpdated(zzbc.zzj, com.google.android.gms.internal.play_billing.zzu.zzk());
                return;
            }
            String string2 = extras.getString("ALTERNATIVE_BILLING_USER_CHOICE_DATA");
            if (string2 == null) {
                com.google.android.gms.internal.play_billing.zzb.zzj("BillingBroadcastManager", "Couldn't find alternative billing user choice data in bundle.");
                zzba.zza(16, i, zzbc.zzj);
                this.zzb.onPurchasesUpdated(zzbc.zzj, com.google.android.gms.internal.play_billing.zzu.zzk());
                return;
            }
            try {
                AlternativeChoiceDetails alternativeChoiceDetails = new AlternativeChoiceDetails(string2);
                zzba.zzb(i);
                this.zzd.userSelectedAlternativeBilling(alternativeChoiceDetails);
            } catch (JSONException unused) {
                com.google.android.gms.internal.play_billing.zzb.zzj("BillingBroadcastManager", String.format("Error when parsing invalid alternative choice data: [%s]", string2));
                zzba.zza(17, i, zzbc.zzj);
                this.zzb.onPurchasesUpdated(zzbc.zzj, com.google.android.gms.internal.play_billing.zzu.zzk());
            }
        }
    }

    public final void zzc(Context context, IntentFilter intentFilter) {
        zzn zznVar;
        zzn zznVar2;
        if (this.zze) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 33) {
            zznVar2 = this.zza.zzb;
            context.registerReceiver(zznVar2, intentFilter, 2);
        } else {
            zznVar = this.zza.zzb;
            context.registerReceiver(zznVar, intentFilter);
        }
        this.zze = true;
    }

    public final void zzd(Context context) {
        zzn zznVar;
        if (!this.zze) {
            com.google.android.gms.internal.play_billing.zzb.zzj("BillingBroadcastManager", "Receiver is not registered.");
            return;
        }
        zznVar = this.zza.zzb;
        context.unregisterReceiver(zznVar);
        this.zze = false;
    }

    public /* synthetic */ zzn(zzo zzoVar, PurchasesUpdatedListener purchasesUpdatedListener, AlternativeBillingListener alternativeBillingListener, zzbh zzbhVar, zzm zzmVar) {
        this.zza = zzoVar;
        this.zzb = purchasesUpdatedListener;
        this.zzf = zzbhVar;
        this.zzd = alternativeBillingListener;
        this.zzc = null;
    }
}
