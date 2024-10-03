package com.oversea.ab_firstarea.plugin.pay.onstore;

import android.app.Activity;
import com.oversea.ab_firstplatform.model.GoogleProductListData;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;

/* loaded from: classes.dex */
public abstract class AbstractOneStorePayManager<T> {
    public void consume(Object obj) {
    }

    public T getProductInfo(String str) {
        return null;
    }

    public void getProductListInfo() {
    }

    public void init(Activity activity) {
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
