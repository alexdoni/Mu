package com.oversea.ab_firstarea.plugin.pay.google;

import android.app.Activity;
import android.content.Context;
import com.oversea.ab_firstarea.haiwai.GooglePlayInAppReview;
import com.oversea.ab_firstplatform.model.GoogleProductListData;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes.dex */
public class GooglePayLibHelper {
    private static volatile GooglePayLibHelper googlePayLibHelper;
    private static AbstractGPPayManager mAbstractGpPPayManager;
    private GoogleProductListData googleProductListData;

    public static GooglePayLibHelper getInstance() {
        if (googlePayLibHelper == null) {
            synchronized (GooglePayLibHelper.class) {
                if (googlePayLibHelper == null) {
                    googlePayLibHelper = new GooglePayLibHelper();
                    initClass();
                }
            }
        }
        return googlePayLibHelper;
    }

    private static void initClass() {
        try {
            mAbstractGpPPayManager = (AbstractGPPayManager) Class.forName("com.ff.sdk.ffoversea_gppay.GooglePayManager").newInstance();
        } catch (Exception unused) {
        }
    }

    public void init(Context context) {
        AbstractGPPayManager abstractGPPayManager = mAbstractGpPPayManager;
        if (abstractGPPayManager != null) {
            abstractGPPayManager.init(context);
        }
    }

    public void pay(Activity activity, String str, Lxhw_PayParams lxhw_PayParams) {
        if (mAbstractGpPPayManager != null) {
            LLog.i_Control("mAbstractGpPPayManager start pay");
            mAbstractGpPPayManager.pay(activity, str, lxhw_PayParams);
        }
    }

    public void consume(String str, String str2) {
        AbstractGPPayManager abstractGPPayManager = mAbstractGpPPayManager;
        if (abstractGPPayManager != null) {
            abstractGPPayManager.consume(str, str2);
        }
    }

    public void getProductListInfo() {
        AbstractGPPayManager abstractGPPayManager = mAbstractGpPPayManager;
        if (abstractGPPayManager != null) {
            abstractGPPayManager.getProductListInfo();
        }
    }

    public void queryPurchases() {
        AbstractGPPayManager abstractGPPayManager = mAbstractGpPPayManager;
        if (abstractGPPayManager != null) {
            abstractGPPayManager.queryPurchases();
        }
    }

    public void setGoogleProductListData(GoogleProductListData googleProductListData) {
        AbstractGPPayManager abstractGPPayManager = mAbstractGpPPayManager;
        if (abstractGPPayManager != null) {
            this.googleProductListData = googleProductListData;
            abstractGPPayManager.setProductListData(googleProductListData);
        }
    }

    public GoogleProductListData getGoogleProductListData() {
        return this.googleProductListData;
    }

    public GoogleProductListData.Product_list getProductInfo(String str) {
        AbstractGPPayManager abstractGPPayManager = mAbstractGpPPayManager;
        if (abstractGPPayManager != null) {
            return (GoogleProductListData.Product_list) abstractGPPayManager.getProductInfo(str);
        }
        return null;
    }

    public void launchReviewFlow(Activity activity) {
        GooglePlayInAppReview.getInstance().launchReviewFlow(activity);
    }
}
