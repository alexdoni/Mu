package com.oversea.ab_firstarea.plugin.pay.onstore;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.oversea.ab_firstplatform.model.GoogleProductListData;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes.dex */
public class OneStorePayLibHelper {
    private static AbstractOneStorePayManager abstractOneStorePayManager;
    private static volatile OneStorePayLibHelper oneStorePayLibHelper;
    private GoogleProductListData googleProductListData;

    public static OneStorePayLibHelper getInstance() {
        if (oneStorePayLibHelper == null) {
            synchronized (OneStorePayLibHelper.class) {
                if (oneStorePayLibHelper == null) {
                    oneStorePayLibHelper = new OneStorePayLibHelper();
                    initClass();
                }
            }
        }
        return oneStorePayLibHelper;
    }

    private static void initClass() {
        try {
            abstractOneStorePayManager = (AbstractOneStorePayManager) Class.forName("com.ff.sdk.ffoversea.onstore.OneStorePayManager").newInstance();
        } catch (Exception unused) {
        }
    }

    public void init(Activity activity) {
        AbstractOneStorePayManager abstractOneStorePayManager2 = abstractOneStorePayManager;
        if (abstractOneStorePayManager2 != null) {
            abstractOneStorePayManager2.init(activity);
        }
    }

    public void pay(Activity activity, String str, Lxhw_PayParams lxhw_PayParams) {
        if (abstractOneStorePayManager != null) {
            LLog.i_Control("abstractOneStorePayManager start pay");
            abstractOneStorePayManager.pay(activity, str, lxhw_PayParams);
        }
    }

    public void consume(Object obj) {
        AbstractOneStorePayManager abstractOneStorePayManager2 = abstractOneStorePayManager;
        if (abstractOneStorePayManager2 != null) {
            abstractOneStorePayManager2.consume(obj);
        }
    }

    public void getProductListInfo() {
        AbstractOneStorePayManager abstractOneStorePayManager2 = abstractOneStorePayManager;
        if (abstractOneStorePayManager2 != null) {
            abstractOneStorePayManager2.getProductListInfo();
        }
    }

    public void queryPurchases() {
        AbstractOneStorePayManager abstractOneStorePayManager2 = abstractOneStorePayManager;
        if (abstractOneStorePayManager2 != null) {
            abstractOneStorePayManager2.queryPurchases();
        }
    }

    public void setOneStoreProductListData(GoogleProductListData googleProductListData) {
        AbstractOneStorePayManager abstractOneStorePayManager2 = abstractOneStorePayManager;
        if (abstractOneStorePayManager2 != null) {
            this.googleProductListData = googleProductListData;
            abstractOneStorePayManager2.setProductListData(googleProductListData);
        }
    }

    public GoogleProductListData getOneStoreProductListData() {
        return this.googleProductListData;
    }

    public GoogleProductListData.Product_list getProductInfo(String str) {
        AbstractOneStorePayManager abstractOneStorePayManager2 = abstractOneStorePayManager;
        if (abstractOneStorePayManager2 != null) {
            return (GoogleProductListData.Product_list) abstractOneStorePayManager2.getProductInfo(str);
        }
        return null;
    }

    public void launchReviewFlow(Activity activity) {
        try {
            String metaData = ImageUtils.getMetaData(activity, "onestore_product_id");
            if (TextUtils.isEmpty(metaData)) {
                LLog.e_noControl("onestore_product_id is null");
                return;
            }
            String str = "onestore://common/product/" + metaData;
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            activity.startActivity(intent);
        } catch (Exception e) {
            LLog.e_Control(e.getMessage());
        }
    }
}
