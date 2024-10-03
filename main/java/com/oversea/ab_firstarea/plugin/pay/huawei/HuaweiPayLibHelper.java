package com.oversea.ab_firstarea.plugin.pay.huawei;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.oversea.ab_firstplatform.model.GoogleProductListData;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes.dex */
public class HuaweiPayLibHelper {
    private static volatile HuaweiPayLibHelper huaweiPayLibHelper;
    private static AbstractHwPayManager mAbstractHwPayManager;
    private GoogleProductListData huaweiProductListData;

    public static HuaweiPayLibHelper getInstance() {
        if (huaweiPayLibHelper == null) {
            synchronized (HuaweiPayLibHelper.class) {
                if (huaweiPayLibHelper == null) {
                    huaweiPayLibHelper = new HuaweiPayLibHelper();
                    initClass();
                }
            }
        }
        return huaweiPayLibHelper;
    }

    private static void initClass() {
        try {
            mAbstractHwPayManager = (AbstractHwPayManager) Class.forName("com.ff.sdk.ffoversea_huaweipay.HuaweiManager").newInstance();
        } catch (Exception unused) {
        }
    }

    public void init(Activity activity) {
        AbstractHwPayManager abstractHwPayManager = mAbstractHwPayManager;
        if (abstractHwPayManager != null) {
            abstractHwPayManager.init(activity);
        }
    }

    public void loadHuaweiAGCConfig(Context context) {
        AbstractHwPayManager abstractHwPayManager = mAbstractHwPayManager;
        if (abstractHwPayManager != null) {
            abstractHwPayManager.loadHuaweiAGCConfig(context);
        }
    }

    public void launchReviewFlow(Activity activity) {
        AbstractHwPayManager abstractHwPayManager = mAbstractHwPayManager;
        if (abstractHwPayManager != null) {
            abstractHwPayManager.launchReviewFlow(activity);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        AbstractHwPayManager abstractHwPayManager = mAbstractHwPayManager;
        if (abstractHwPayManager != null) {
            abstractHwPayManager.onActivityResult(i, i2, intent);
        }
    }

    public void pay(Activity activity, String str, Lxhw_PayParams lxhw_PayParams) {
        if (mAbstractHwPayManager != null) {
            LLog.i_Control("mAbstractHwPayManager start pay");
            mAbstractHwPayManager.pay(activity, str, lxhw_PayParams);
        }
    }

    public void getProductListInfo() {
        AbstractHwPayManager abstractHwPayManager = mAbstractHwPayManager;
        if (abstractHwPayManager != null) {
            abstractHwPayManager.getProductListInfo();
        }
    }

    public void queryPurchases() {
        AbstractHwPayManager abstractHwPayManager = mAbstractHwPayManager;
        if (abstractHwPayManager != null) {
            abstractHwPayManager.queryPurchases();
        }
    }

    public void setHuaweiProductListData(GoogleProductListData googleProductListData) {
        AbstractHwPayManager abstractHwPayManager = mAbstractHwPayManager;
        if (abstractHwPayManager != null) {
            this.huaweiProductListData = googleProductListData;
            abstractHwPayManager.setProductListData(googleProductListData);
        }
    }

    public GoogleProductListData getHuaweiProductListData() {
        return this.huaweiProductListData;
    }

    public GoogleProductListData.Product_list getProductInfo(String str) {
        AbstractHwPayManager abstractHwPayManager = mAbstractHwPayManager;
        if (abstractHwPayManager != null) {
            return (GoogleProductListData.Product_list) abstractHwPayManager.getProductInfo(str);
        }
        return null;
    }
}
