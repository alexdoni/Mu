package com.oversea.ab_firstarea.plugin.pay.samsung;

import android.app.Activity;
import com.oversea.ab_firstplatform.model.GoogleProductListData;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes.dex */
public class SamsungPayLibHelper {
    private static AbstractSamsungPayManager abstractSamsungPayManager;
    private static volatile SamsungPayLibHelper samsungPayLibHelper;
    private GoogleProductListData googleProductListData;

    public static SamsungPayLibHelper getInstance() {
        if (samsungPayLibHelper == null) {
            synchronized (SamsungPayLibHelper.class) {
                if (samsungPayLibHelper == null) {
                    samsungPayLibHelper = new SamsungPayLibHelper();
                    initClass();
                }
            }
        }
        return samsungPayLibHelper;
    }

    private static void initClass() {
        try {
            abstractSamsungPayManager = (AbstractSamsungPayManager) Class.forName("com.ff.sdk.ffoversea.samsung.SamsungPayManager").newInstance();
        } catch (Exception unused) {
        }
    }

    public void init(Activity activity) {
        AbstractSamsungPayManager abstractSamsungPayManager2 = abstractSamsungPayManager;
        if (abstractSamsungPayManager2 != null) {
            abstractSamsungPayManager2.init(activity);
        }
    }

    public void pay(Activity activity, String str, Lxhw_PayParams lxhw_PayParams) {
        if (abstractSamsungPayManager != null) {
            LLog.i_Control("abstractSamsungPayManager start pay");
            abstractSamsungPayManager.pay(activity, str, lxhw_PayParams);
        }
    }

    public void consume(String str) {
        AbstractSamsungPayManager abstractSamsungPayManager2 = abstractSamsungPayManager;
        if (abstractSamsungPayManager2 != null) {
            abstractSamsungPayManager2.consume(str);
        }
    }

    public void getProductListInfo() {
        AbstractSamsungPayManager abstractSamsungPayManager2 = abstractSamsungPayManager;
        if (abstractSamsungPayManager2 != null) {
            abstractSamsungPayManager2.getProductListInfo();
        }
    }

    public void queryPurchases() {
        AbstractSamsungPayManager abstractSamsungPayManager2 = abstractSamsungPayManager;
        if (abstractSamsungPayManager2 != null) {
            abstractSamsungPayManager2.queryPurchases();
        }
    }

    public void setProductListData(GoogleProductListData googleProductListData) {
        AbstractSamsungPayManager abstractSamsungPayManager2 = abstractSamsungPayManager;
        if (abstractSamsungPayManager2 != null) {
            this.googleProductListData = googleProductListData;
            abstractSamsungPayManager2.setProductListData(googleProductListData);
        }
    }

    public GoogleProductListData getProductListData() {
        return this.googleProductListData;
    }

    public GoogleProductListData.Product_list getProductInfo(String str) {
        AbstractSamsungPayManager abstractSamsungPayManager2 = abstractSamsungPayManager;
        if (abstractSamsungPayManager2 != null) {
            return (GoogleProductListData.Product_list) abstractSamsungPayManager2.getProductInfo(str);
        }
        return null;
    }

    public void onDestroy() {
        AbstractSamsungPayManager abstractSamsungPayManager2 = abstractSamsungPayManager;
        if (abstractSamsungPayManager2 != null) {
            abstractSamsungPayManager2.onDestroy();
        }
    }
}
