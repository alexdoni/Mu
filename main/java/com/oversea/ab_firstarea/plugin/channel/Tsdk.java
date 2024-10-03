package com.oversea.ab_firstarea.plugin.channel;

import com.oversea.ab_firstplatform.model.GoogleProductListData;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserExtraData;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes.dex */
public class Tsdk {
    private static AbstractTsdkManager abstractTsdkManager;
    private static volatile Tsdk tSdkHelper;
    private GoogleProductListData googleProductListData = null;

    public static Tsdk getInstance() {
        if (tSdkHelper == null) {
            synchronized (Tsdk.class) {
                if (tSdkHelper == null) {
                    tSdkHelper = new Tsdk();
                    initClass();
                }
            }
        }
        return tSdkHelper;
    }

    private static void initClass() {
        try {
            abstractTsdkManager = (AbstractTsdkManager) Class.forName("com.ff.sdk.ffoversea.c.b.Fsdk").newInstance();
        } catch (Exception unused) {
        }
    }

    public void init() {
        AbstractTsdkManager abstractTsdkManager2 = abstractTsdkManager;
        if (abstractTsdkManager2 != null) {
            abstractTsdkManager2.init();
        }
    }

    public void login() {
        AbstractTsdkManager abstractTsdkManager2 = abstractTsdkManager;
        if (abstractTsdkManager2 != null) {
            abstractTsdkManager2.login();
        }
    }

    public void logout() {
        AbstractTsdkManager abstractTsdkManager2 = abstractTsdkManager;
        if (abstractTsdkManager2 != null) {
            abstractTsdkManager2.logout();
        }
    }

    public void exit() {
        AbstractTsdkManager abstractTsdkManager2 = abstractTsdkManager;
        if (abstractTsdkManager2 != null) {
            abstractTsdkManager2.exit();
        }
    }

    public void pay(Lxhw_PayParams lxhw_PayParams) {
        if (abstractTsdkManager != null) {
            LLog.i_Control("abstractTsdkManager start pay");
            abstractTsdkManager.pay(lxhw_PayParams);
        }
    }

    public void submitGameInfo(Lxhw_UserExtraData lxhw_UserExtraData) {
        AbstractTsdkManager abstractTsdkManager2 = abstractTsdkManager;
        if (abstractTsdkManager2 != null) {
            abstractTsdkManager2.submitGameInfo(lxhw_UserExtraData);
        }
    }

    public void setProductListData(GoogleProductListData googleProductListData) {
        this.googleProductListData = googleProductListData;
    }

    public GoogleProductListData.Product_list getPlatformProductInfo(String str) {
        GoogleProductListData googleProductListData = this.googleProductListData;
        if (googleProductListData == null || googleProductListData.getData() == null || this.googleProductListData.getData().getProduct_list() == null || this.googleProductListData.getData().getProduct_list().size() <= 0) {
            return null;
        }
        for (GoogleProductListData.Product_list product_list : this.googleProductListData.getData().getProduct_list()) {
            if (product_list.getProduct_id().equals(str)) {
                return product_list;
            }
        }
        return null;
    }
}
