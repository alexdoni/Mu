package com.oversea.ab_firstarea.plugin.pay.google;

import android.app.Activity;
import android.content.Context;
import com.oversea.ab_firstplatform.model.GoogleProductListData;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;

/* loaded from: classes.dex */
public abstract class AbstractGPPayManager<T> {
    public void consume(String str, String str2) {
    }

    public T getProductInfo(String str) {
        return null;
    }

    public void getProductListInfo() {
    }

    public void init(Context context) {
    }

    public void onDestroy() {
    }

    public void pay(Activity activity, String str, Lxhw_PayParams lxhw_PayParams) {
    }

    public void queryPurchases() {
    }

    public void setProductListData(GoogleProductListData googleProductListData) {
    }
}
