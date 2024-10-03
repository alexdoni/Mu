package com.android.billingclient.api;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.android.billingclient:billing@@5.2.1 */
@Deprecated
/* loaded from: classes.dex */
public class SkuDetailsParams {
    private String zza;
    private List zzb;

    /* compiled from: com.android.billingclient:billing@@5.2.1 */
    /* loaded from: classes.dex */
    public static class Builder {
        private String zza;
        private List zzb;

        private Builder() {
        }

        /* synthetic */ Builder(zzbu zzbuVar) {
        }

        public SkuDetailsParams build() {
            String str = this.zza;
            if (str == null) {
                throw new IllegalArgumentException("SKU type must be set");
            }
            if (this.zzb != null) {
                SkuDetailsParams skuDetailsParams = new SkuDetailsParams();
                skuDetailsParams.zza = str;
                skuDetailsParams.zzb = this.zzb;
                return skuDetailsParams;
            }
            throw new IllegalArgumentException("SKU list or SkuWithOffer list must be set");
        }

        public Builder setSkusList(List<String> list) {
            this.zzb = new ArrayList(list);
            return this;
        }

        public Builder setType(String str) {
            this.zza = str;
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public String getSkuType() {
        return this.zza;
    }

    public List<String> getSkusList() {
        return this.zzb;
    }
}
