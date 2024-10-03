package com.p008ld.sdk.core.bean;

import android.text.TextUtils;

/* loaded from: classes2.dex */
public class LdGamePayInfo {
    public long commodityPrice;
    public String cpOrderId;
    public String cpUserId;
    public String currencyType;
    public String productId;
    public String tradeName;
    public String transparentParams;

    public boolean checkError() {
        return TextUtils.isEmpty(this.cpOrderId) || TextUtils.isEmpty(this.cpUserId) || TextUtils.isEmpty(this.productId) || TextUtils.isEmpty(this.tradeName) || TextUtils.isEmpty(this.currencyType) || this.commodityPrice == 0;
    }
}
