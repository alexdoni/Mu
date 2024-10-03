package com.oversea.ab_firstarea.plugin.pay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.oversea.ab_firstarea.haiwai.GooglePlayInAppReview;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.plugin.channel.Tsdk;
import com.oversea.ab_firstarea.plugin.pay.aptoide.AptoidePayLibHelper;
import com.oversea.ab_firstarea.plugin.pay.google.GooglePayLibHelper;
import com.oversea.ab_firstarea.plugin.pay.huawei.HuaweiPayLibHelper;
import com.oversea.ab_firstarea.plugin.pay.onstore.OneStorePayLibHelper;
import com.oversea.ab_firstarea.plugin.pay.samsung.SamsungPayLibHelper;
import com.oversea.ab_firstplatform.model.GoogleProductListData;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;

/* loaded from: classes.dex */
public class PayLibHelper {
    private static volatile PayLibHelper payLibHelper;
    private String payType;

    public static PayLibHelper getInstance() {
        if (payLibHelper == null) {
            synchronized (PayLibHelper.class) {
                if (payLibHelper == null) {
                    payLibHelper = new PayLibHelper();
                }
            }
        }
        return payLibHelper;
    }

    public void setPayType(Context context) {
        this.payType = ImageUtils.getMetaData(context, ComConstants.FF_PAY_TYPE);
    }

    public void init(Activity activity) {
        setPayType(activity);
        if (isHuawei()) {
            HuaweiPayLibHelper.getInstance().init(activity);
            return;
        }
        if (isOneStore()) {
            OneStorePayLibHelper.getInstance().init(activity);
            return;
        }
        if (isSamsung()) {
            SamsungPayLibHelper.getInstance().init(activity);
        } else {
            if (isAptoide()) {
                return;
            }
            GooglePayLibHelper.getInstance().init(activity);
            GooglePlayInAppReview.getInstance().init(activity);
        }
    }

    public void setProductListData(GoogleProductListData googleProductListData) {
        if (isHuawei()) {
            HuaweiPayLibHelper.getInstance().setHuaweiProductListData(googleProductListData);
            return;
        }
        if (isOneStore()) {
            OneStorePayLibHelper.getInstance().setOneStoreProductListData(googleProductListData);
            return;
        }
        if (isSamsung()) {
            SamsungPayLibHelper.getInstance().setProductListData(googleProductListData);
            return;
        }
        if (isAptoide()) {
            AptoidePayLibHelper.getInstance().setProductListData(googleProductListData);
        } else if (!Lxhw_AreaPlatform.getInstance().isFF()) {
            Tsdk.getInstance().setProductListData(googleProductListData);
        } else {
            GooglePayLibHelper.getInstance().setGoogleProductListData(googleProductListData);
        }
    }

    public GoogleProductListData getProductListData() {
        if (isHuawei()) {
            return HuaweiPayLibHelper.getInstance().getHuaweiProductListData();
        }
        if (isOneStore()) {
            return OneStorePayLibHelper.getInstance().getOneStoreProductListData();
        }
        if (isSamsung()) {
            return SamsungPayLibHelper.getInstance().getProductListData();
        }
        if (isAptoide()) {
            return AptoidePayLibHelper.getInstance().getProductListData();
        }
        return GooglePayLibHelper.getInstance().getGoogleProductListData();
    }

    public void getProductListInfo() {
        if (isHuawei()) {
            HuaweiPayLibHelper.getInstance().getProductListInfo();
            return;
        }
        if (isOneStore()) {
            OneStorePayLibHelper.getInstance().getProductListInfo();
            return;
        }
        if (isSamsung()) {
            SamsungPayLibHelper.getInstance().getProductListInfo();
        } else if (isAptoide()) {
            AptoidePayLibHelper.getInstance().getProductListInfo();
        } else {
            GooglePayLibHelper.getInstance().getProductListInfo();
        }
    }

    public void pay(Activity activity, String str, Lxhw_PayParams lxhw_PayParams) {
        if (isHuawei()) {
            HuaweiPayLibHelper.getInstance().pay(activity, str, lxhw_PayParams);
            return;
        }
        if (isOneStore()) {
            OneStorePayLibHelper.getInstance().pay(activity, str, lxhw_PayParams);
            return;
        }
        if (isSamsung()) {
            SamsungPayLibHelper.getInstance().pay(activity, str, lxhw_PayParams);
        } else if (isAptoide()) {
            AptoidePayLibHelper.getInstance().pay(activity, str, lxhw_PayParams);
        } else {
            GooglePayLibHelper.getInstance().pay(activity, str, lxhw_PayParams);
        }
    }

    public void consume(String str, String str2, Object obj) {
        if (isHuawei()) {
            return;
        }
        if (isOneStore()) {
            OneStorePayLibHelper.getInstance().consume(obj);
        } else if (isSamsung()) {
            SamsungPayLibHelper.getInstance().consume(str);
        } else {
            if (isAptoide()) {
                return;
            }
            GooglePayLibHelper.getInstance().consume(str, str2);
        }
    }

    public void queryPurchases() {
        if (isHuawei() || isAptoide()) {
            return;
        }
        if (isOneStore()) {
            OneStorePayLibHelper.getInstance().queryPurchases();
        } else if (isSamsung()) {
            SamsungPayLibHelper.getInstance().queryPurchases();
        } else {
            GooglePayLibHelper.getInstance().queryPurchases();
        }
    }

    public GoogleProductListData.Product_list getProductInfo(String str) {
        if (isHuawei()) {
            return HuaweiPayLibHelper.getInstance().getProductInfo(str);
        }
        if (isOneStore()) {
            return OneStorePayLibHelper.getInstance().getProductInfo(str);
        }
        if (isSamsung()) {
            return SamsungPayLibHelper.getInstance().getProductInfo(str);
        }
        if (isAptoide()) {
            return AptoidePayLibHelper.getInstance().getProductInfo(str);
        }
        if (!Lxhw_AreaPlatform.getInstance().isFF()) {
            return Tsdk.getInstance().getPlatformProductInfo(str);
        }
        return GooglePayLibHelper.getInstance().getProductInfo(str);
    }

    public void launchReviewFlow(Activity activity) {
        if (isHuawei()) {
            HuaweiPayLibHelper.getInstance().launchReviewFlow(activity);
            return;
        }
        if (isOneStore()) {
            OneStorePayLibHelper.getInstance().launchReviewFlow(activity);
        } else {
            if (isSamsung() || isAptoide()) {
                return;
            }
            GooglePayLibHelper.getInstance().launchReviewFlow(activity);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (isHuawei()) {
            HuaweiPayLibHelper.getInstance().onActivityResult(i, i2, intent);
        } else if (isAptoide()) {
            AptoidePayLibHelper.getInstance().onActivityResult(i, i2, intent);
        }
    }

    public void loadHuaweiAGCConfig(Context context) {
        setPayType(context);
        if (isHuawei()) {
            HuaweiPayLibHelper.getInstance().loadHuaweiAGCConfig(context);
        }
    }

    public void onDestroy() {
        if (isSamsung()) {
            SamsungPayLibHelper.getInstance().onDestroy();
        }
    }

    public boolean isHuawei() {
        return "huawei".equals(this.payType);
    }

    public boolean isOneStore() {
        return "onestore".equals(this.payType);
    }

    public boolean isSamsung() {
        return "samsung".equals(this.payType);
    }

    public boolean isAptoide() {
        return "aptoide".equals(this.payType);
    }
}
