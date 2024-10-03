package com.android.billingclient.api;

import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes.dex */
public final class ProductDetails {
    private final String zza;
    private final JSONObject zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final String zzi;
    private final String zzj;
    private final String zzk;
    private final List zzl;
    private final List zzm;
    private final zzbj zzn;

    /* compiled from: com.android.billingclient:billing@@5.2.1 */
    /* loaded from: classes.dex */
    public static final class OneTimePurchaseOfferDetails {
        private final String zza;
        private final long zzb;
        private final String zzc;
        private final String zzd;
        private final String zze;
        private final com.google.android.gms.internal.play_billing.zzu zzf;

        OneTimePurchaseOfferDetails(JSONObject jSONObject) throws JSONException {
            this.zza = jSONObject.optString("formattedPrice");
            this.zzb = jSONObject.optLong("priceAmountMicros");
            this.zzc = jSONObject.optString("priceCurrencyCode");
            this.zzd = jSONObject.optString("offerIdToken");
            this.zze = jSONObject.optString("offerId");
            jSONObject.optInt("offerType");
            JSONArray optJSONArray = jSONObject.optJSONArray("offerTags");
            ArrayList arrayList = new ArrayList();
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.getString(i));
                }
            }
            this.zzf = com.google.android.gms.internal.play_billing.zzu.zzj(arrayList);
        }

        public String getFormattedPrice() {
            return this.zza;
        }

        public long getPriceAmountMicros() {
            return this.zzb;
        }

        public String getPriceCurrencyCode() {
            return this.zzc;
        }

        public final String zza() {
            return this.zzd;
        }
    }

    /* compiled from: com.android.billingclient:billing@@5.2.1 */
    /* loaded from: classes.dex */
    public static final class PricingPhase {
        private final int billingCycleCount;
        private final String billingPeriod;
        private final String formattedPrice;
        private final long priceAmountMicros;
        private final String priceCurrencyCode;
        private final int recurrenceMode;

        PricingPhase(JSONObject jSONObject) {
            this.billingPeriod = jSONObject.optString("billingPeriod");
            this.priceCurrencyCode = jSONObject.optString("priceCurrencyCode");
            this.formattedPrice = jSONObject.optString("formattedPrice");
            this.priceAmountMicros = jSONObject.optLong("priceAmountMicros");
            this.recurrenceMode = jSONObject.optInt("recurrenceMode");
            this.billingCycleCount = jSONObject.optInt("billingCycleCount");
        }

        public int getBillingCycleCount() {
            return this.billingCycleCount;
        }

        public String getBillingPeriod() {
            return this.billingPeriod;
        }

        public String getFormattedPrice() {
            return this.formattedPrice;
        }

        public long getPriceAmountMicros() {
            return this.priceAmountMicros;
        }

        public String getPriceCurrencyCode() {
            return this.priceCurrencyCode;
        }

        public int getRecurrenceMode() {
            return this.recurrenceMode;
        }
    }

    /* compiled from: com.android.billingclient:billing@@5.2.1 */
    /* loaded from: classes.dex */
    public static class PricingPhases {
        private final List<PricingPhase> pricingPhaseList;

        PricingPhases(JSONArray jSONArray) {
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        arrayList.add(new PricingPhase(optJSONObject));
                    }
                }
            }
            this.pricingPhaseList = arrayList;
        }

        public List<PricingPhase> getPricingPhaseList() {
            return this.pricingPhaseList;
        }
    }

    /* compiled from: com.android.billingclient:billing@@5.2.1 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface RecurrenceMode {
        public static final int FINITE_RECURRING = 2;
        public static final int INFINITE_RECURRING = 1;
        public static final int NON_RECURRING = 3;
    }

    /* compiled from: com.android.billingclient:billing@@5.2.1 */
    /* loaded from: classes.dex */
    public static final class SubscriptionOfferDetails {
        private final String zza;
        private final String zzb;
        private final String zzc;
        private final PricingPhases zzd;
        private final List zze;
        private final zzbi zzf;

        SubscriptionOfferDetails(JSONObject jSONObject) throws JSONException {
            this.zza = jSONObject.optString("basePlanId");
            String optString = jSONObject.optString("offerId");
            this.zzb = true == optString.isEmpty() ? null : optString;
            this.zzc = jSONObject.getString("offerIdToken");
            this.zzd = new PricingPhases(jSONObject.getJSONArray("pricingPhases"));
            JSONObject optJSONObject = jSONObject.optJSONObject("installmentPlanDetails");
            this.zzf = optJSONObject != null ? new zzbi(optJSONObject) : null;
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("offerTags");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.getString(i));
                }
            }
            this.zze = arrayList;
        }

        public String getBasePlanId() {
            return this.zza;
        }

        public String getOfferId() {
            return this.zzb;
        }

        public List<String> getOfferTags() {
            return this.zze;
        }

        public String getOfferToken() {
            return this.zzc;
        }

        public PricingPhases getPricingPhases() {
            return this.zzd;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProductDetails(String str) throws JSONException {
        this.zza = str;
        JSONObject jSONObject = new JSONObject(str);
        this.zzb = jSONObject;
        String optString = jSONObject.optString("productId");
        this.zzc = optString;
        String optString2 = jSONObject.optString(ShareConstants.MEDIA_TYPE);
        this.zzd = optString2;
        if (TextUtils.isEmpty(optString)) {
            throw new IllegalArgumentException("Product id cannot be empty.");
        }
        if (TextUtils.isEmpty(optString2)) {
            throw new IllegalArgumentException("Product type cannot be empty.");
        }
        this.zze = jSONObject.optString("title");
        this.zzf = jSONObject.optString("name");
        this.zzg = jSONObject.optString("description");
        this.zzi = jSONObject.optString("packageDisplayName");
        this.zzj = jSONObject.optString("iconUrl");
        this.zzh = jSONObject.optString("skuDetailsToken");
        this.zzk = jSONObject.optString("serializedDocid");
        JSONArray optJSONArray = jSONObject.optJSONArray("subscriptionOfferDetails");
        if (optJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(new SubscriptionOfferDetails(optJSONArray.getJSONObject(i)));
            }
            this.zzl = arrayList;
        } else {
            this.zzl = (optString2.equals("subs") || optString2.equals("play_pass_subs")) ? new ArrayList() : null;
        }
        JSONObject optJSONObject = this.zzb.optJSONObject("oneTimePurchaseOfferDetails");
        JSONArray optJSONArray2 = this.zzb.optJSONArray("oneTimePurchaseOfferDetailsList");
        ArrayList arrayList2 = new ArrayList();
        if (optJSONArray2 != null) {
            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                arrayList2.add(new OneTimePurchaseOfferDetails(optJSONArray2.getJSONObject(i2)));
            }
            this.zzm = arrayList2;
        } else if (optJSONObject != null) {
            arrayList2.add(new OneTimePurchaseOfferDetails(optJSONObject));
            this.zzm = arrayList2;
        } else {
            this.zzm = null;
        }
        JSONObject optJSONObject2 = this.zzb.optJSONObject("limitedQuantityInfo");
        if (optJSONObject2 != null) {
            this.zzn = new zzbj(optJSONObject2);
        } else {
            this.zzn = null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ProductDetails) {
            return TextUtils.equals(this.zza, ((ProductDetails) obj).zza);
        }
        return false;
    }

    public String getDescription() {
        return this.zzg;
    }

    public String getName() {
        return this.zzf;
    }

    public OneTimePurchaseOfferDetails getOneTimePurchaseOfferDetails() {
        List list = this.zzm;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (OneTimePurchaseOfferDetails) this.zzm.get(0);
    }

    public String getProductId() {
        return this.zzc;
    }

    public String getProductType() {
        return this.zzd;
    }

    public List<SubscriptionOfferDetails> getSubscriptionOfferDetails() {
        return this.zzl;
    }

    public String getTitle() {
        return this.zze;
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public String toString() {
        return "ProductDetails{jsonString='" + this.zza + "', parsedJson=" + this.zzb.toString() + ", productId='" + this.zzc + "', productType='" + this.zzd + "', title='" + this.zze + "', productDetailsToken='" + this.zzh + "', subscriptionOfferDetails=" + String.valueOf(this.zzl) + "}";
    }

    public final String zza() {
        return this.zzb.optString("packageName");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzb() {
        return this.zzh;
    }

    public String zzc() {
        return this.zzk;
    }
}
