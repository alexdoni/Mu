package com.oversea.ab_firstarea.plugin.pay.aptoide;

import android.app.Activity;
import android.content.Intent;
import com.oversea.ab_firstplatform.model.GoogleProductListData;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes.dex */
public class AptoidePayLibHelper {
    private static AbstractAptoidePayManager abstractAptoidePayManager;
    private static volatile AptoidePayLibHelper aptoidePayLibHelper;
    private GoogleProductListData googleProductListData;

    public static AptoidePayLibHelper getInstance() {
        if (aptoidePayLibHelper == null) {
            synchronized (AptoidePayLibHelper.class) {
                if (aptoidePayLibHelper == null) {
                    aptoidePayLibHelper = new AptoidePayLibHelper();
                    initClass();
                }
            }
        }
        return aptoidePayLibHelper;
    }

    private static void initClass() {
        try {
            abstractAptoidePayManager = (AbstractAptoidePayManager) Class.forName("com.ff.sdk.ffoversea.aptoide.AptoidePayManager").newInstance();
        } catch (Exception unused) {
        }
    }

    public void init(Activity activity) {
        AbstractAptoidePayManager abstractAptoidePayManager2 = abstractAptoidePayManager;
        if (abstractAptoidePayManager2 != null) {
            abstractAptoidePayManager2.init(activity);
        }
    }

    public void pay(Activity activity, String str, Lxhw_PayParams lxhw_PayParams) {
        if (abstractAptoidePayManager != null) {
            LLog.i_Control("abstractAptoidePayManager start pay");
            abstractAptoidePayManager.pay(activity, str, lxhw_PayParams);
        }
    }

    public void consume(String str) {
        AbstractAptoidePayManager abstractAptoidePayManager2 = abstractAptoidePayManager;
        if (abstractAptoidePayManager2 != null) {
            abstractAptoidePayManager2.consume(str);
        }
    }

    public void getProductListInfo() {
        AbstractAptoidePayManager abstractAptoidePayManager2 = abstractAptoidePayManager;
        if (abstractAptoidePayManager2 != null) {
            abstractAptoidePayManager2.getProductListInfo();
        }
    }

    public void queryPurchases() {
        AbstractAptoidePayManager abstractAptoidePayManager2 = abstractAptoidePayManager;
        if (abstractAptoidePayManager2 != null) {
            abstractAptoidePayManager2.queryPurchases();
        }
    }

    public void setProductListData(GoogleProductListData googleProductListData) {
        AbstractAptoidePayManager abstractAptoidePayManager2 = abstractAptoidePayManager;
        if (abstractAptoidePayManager2 != null) {
            this.googleProductListData = googleProductListData;
            abstractAptoidePayManager2.setProductListData(googleProductListData);
        }
    }

    public GoogleProductListData getProductListData() {
        return this.googleProductListData;
    }

    public GoogleProductListData.Product_list getProductInfo(String str) {
        AbstractAptoidePayManager abstractAptoidePayManager2 = abstractAptoidePayManager;
        if (abstractAptoidePayManager2 != null) {
            return (GoogleProductListData.Product_list) abstractAptoidePayManager2.getProductInfo(str);
        }
        return null;
    }

    public void onDestroy() {
        AbstractAptoidePayManager abstractAptoidePayManager2 = abstractAptoidePayManager;
        if (abstractAptoidePayManager2 != null) {
            abstractAptoidePayManager2.onDestroy();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        AbstractAptoidePayManager abstractAptoidePayManager2 = abstractAptoidePayManager;
        if (abstractAptoidePayManager2 != null) {
            abstractAptoidePayManager2.onActivityResult(i, i2, intent);
        }
    }
}
