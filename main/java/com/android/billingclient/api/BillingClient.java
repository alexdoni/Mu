package com.android.billingclient.api;

import android.app.Activity;
import android.content.Context;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes.dex */
public abstract class BillingClient {

    /* compiled from: com.android.billingclient:billing@@5.2.1 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface BillingResponseCode {
        public static final int BILLING_UNAVAILABLE = 3;
        public static final int DEVELOPER_ERROR = 5;
        public static final int ERROR = 6;
        public static final int FEATURE_NOT_SUPPORTED = -2;
        public static final int ITEM_ALREADY_OWNED = 7;
        public static final int ITEM_NOT_OWNED = 8;
        public static final int ITEM_UNAVAILABLE = 4;

        /* renamed from: OK */
        public static final int f136OK = 0;
        public static final int SERVICE_DISCONNECTED = -1;
        public static final int SERVICE_TIMEOUT = -3;
        public static final int SERVICE_UNAVAILABLE = 2;
        public static final int USER_CANCELED = 1;
    }

    /* compiled from: com.android.billingclient:billing@@5.2.1 */
    /* loaded from: classes.dex */
    public static final class Builder {
        private volatile String zza;
        private volatile boolean zzb;
        private volatile boolean zzc;
        private final Context zzd;
        private volatile PurchasesUpdatedListener zze;
        private volatile zzbf zzf;
        private volatile AlternativeBillingListener zzg;

        /* synthetic */ Builder(Context context, zzp zzpVar) {
            this.zzd = context;
        }

        public BillingClient build() {
            if (this.zzd == null) {
                throw new IllegalArgumentException("Please provide a valid Context.");
            }
            if (this.zze == null) {
                throw new IllegalArgumentException("Please provide a valid listener for purchases updates.");
            }
            if (this.zzb) {
                if (this.zze != null || this.zzg == null) {
                    if (this.zze != null) {
                        return new BillingClientImpl(null, this.zzb, false, this.zzd, this.zze, this.zzg);
                    }
                    return new BillingClientImpl((String) null, this.zzb, this.zzd, (zzbf) null);
                }
                throw new IllegalArgumentException("Please provide a valid listener for Google Play Billing purchases updates when enabling Alternative Billing.");
            }
            throw new IllegalArgumentException("Support for pending purchases must be enabled. Enable this by calling 'enablePendingPurchases()' on BillingClientBuilder.");
        }

        public Builder enableAlternativeBilling(AlternativeBillingListener alternativeBillingListener) {
            this.zzg = alternativeBillingListener;
            return this;
        }

        public Builder enablePendingPurchases() {
            this.zzb = true;
            return this;
        }

        public Builder setListener(PurchasesUpdatedListener purchasesUpdatedListener) {
            this.zze = purchasesUpdatedListener;
            return this;
        }
    }

    /* compiled from: com.android.billingclient:billing@@5.2.1 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ConnectionState {
        public static final int CLOSED = 3;
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int DISCONNECTED = 0;
    }

    /* compiled from: com.android.billingclient:billing@@5.2.1 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FeatureType {
        public static final String IN_APP_MESSAGING = "bbb";
        public static final String PRICE_CHANGE_CONFIRMATION = "priceChangeConfirmation";
        public static final String PRODUCT_DETAILS = "fff";
        public static final String SUBSCRIPTIONS = "subscriptions";
        public static final String SUBSCRIPTIONS_UPDATE = "subscriptionsUpdate";
    }

    /* compiled from: com.android.billingclient:billing@@5.2.1 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ProductType {
        public static final String INAPP = "inapp";
        public static final String SUBS = "subs";
    }

    /* compiled from: com.android.billingclient:billing@@5.2.1 */
    @Retention(RetentionPolicy.SOURCE)
    @Deprecated
    /* loaded from: classes.dex */
    public @interface SkuType {
        public static final String INAPP = "inapp";
        public static final String SUBS = "subs";
    }

    public static Builder newBuilder(Context context) {
        return new Builder(context, null);
    }

    public abstract void acknowledgePurchase(AcknowledgePurchaseParams acknowledgePurchaseParams, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener);

    public abstract void consumeAsync(ConsumeParams consumeParams, ConsumeResponseListener consumeResponseListener);

    public abstract void endConnection();

    public abstract int getConnectionState();

    public abstract BillingResult isFeatureSupported(String str);

    public abstract boolean isReady();

    public abstract BillingResult launchBillingFlow(Activity activity, BillingFlowParams billingFlowParams);

    @Deprecated
    public abstract void launchPriceChangeConfirmationFlow(Activity activity, PriceChangeFlowParams priceChangeFlowParams, PriceChangeConfirmationListener priceChangeConfirmationListener);

    public abstract void queryProductDetailsAsync(QueryProductDetailsParams queryProductDetailsParams, ProductDetailsResponseListener productDetailsResponseListener);

    public abstract void queryPurchaseHistoryAsync(QueryPurchaseHistoryParams queryPurchaseHistoryParams, PurchaseHistoryResponseListener purchaseHistoryResponseListener);

    @Deprecated
    public abstract void queryPurchaseHistoryAsync(String str, PurchaseHistoryResponseListener purchaseHistoryResponseListener);

    public abstract void queryPurchasesAsync(QueryPurchasesParams queryPurchasesParams, PurchasesResponseListener purchasesResponseListener);

    @Deprecated
    public abstract void queryPurchasesAsync(String str, PurchasesResponseListener purchasesResponseListener);

    @Deprecated
    public abstract void querySkuDetailsAsync(SkuDetailsParams skuDetailsParams, SkuDetailsResponseListener skuDetailsResponseListener);

    public abstract BillingResult showInAppMessages(Activity activity, InAppMessageParams inAppMessageParams, InAppMessageResponseListener inAppMessageResponseListener);

    public abstract void startConnection(BillingClientStateListener billingClientStateListener);
}
