package com.oversea.ab_firstarea.p012f.sdk;

import android.app.Activity;
import com.oversea.ab_firstarea.dpresenter.PresenterSimple;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_SimplePresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_BaseView;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.plugin.pay.PayLibHelper;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import java.util.HashMap;

/* loaded from: classes.dex */
public class DealPayNotify {
    private Lxhw_BaseView<String> baseView;
    private String data_signature;
    private Activity mContext;
    private Object object;
    private PresenterSimple presenter;
    private String purchaseToken;
    private final String TAG = "DealPayNotify";
    private int iTest = 0;

    public DealPayNotify(Activity activity) {
        this.mContext = activity;
        initBaseView();
        this.presenter = new Lxhw_SimplePresenterImpl(activity, this.baseView);
    }

    public int getiTest() {
        return this.iTest;
    }

    public void setiTest(int i) {
        this.iTest = i;
    }

    public void initBaseView() {
        this.baseView = new Lxhw_BaseView<String>() { // from class: com.oversea.ab_firstarea.f.sdk.DealPayNotify.1
            @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
            public void onReqSuccess(String str, String str2) {
                LLog.i_Control("DealPayNotify onReqSuccess type=" + str);
                try {
                    if (!ComConstants.SDK_PAY_NOTIFY.equals(str) && !ComConstants.SDK_PAY_CHAXUN_BUDAN_NOTIFY.equals(str)) {
                        if (ComConstants.SDK_PAY_LOCAL_BUDAN_NOTIFY.equals(str)) {
                            LLog.i_Control("DealPayNotify payNotify onReqSuccess local budan");
                        } else if (ComConstants.SDK_PAY_CHAXUN_BUDAN_NOORDER_NOTIFY.equals(str)) {
                            LLog.i_Control("DealPayNotifypayNotify onReqSuccess SDK_PAY_CHAXUN_BUDAN_NOORDER_NOTIFY budan =" + str2);
                        }
                    }
                    LLog.i_Control("DealPayNotify payNotify onReqSuccess consume");
                    PayLibHelper.getInstance().consume(DealPayNotify.this.data_signature, DealPayNotify.this.purchaseToken, DealPayNotify.this.object);
                } catch (Exception unused) {
                }
            }

            @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
            public void onReqFail(String str, BaseBean baseBean) {
                LLog.e_Control("DealPayNotify onReqFail type =" + str + " code=" + baseBean.getCode() + " " + baseBean.getMessage());
                if (ComConstants.SDK_PAY_NOTIFY.equals(str)) {
                    ComConstants.GETORDER_STATU = 4;
                    LLog.e_Control("DealPayNotify payNotify onReqFail add budan");
                    ToastUtils.toastShow(Lxhw_XSDK.getInstance().getContext(), "code=" + baseBean.getCode() + " " + baseBean.getMessage());
                } else if (ComConstants.SDK_PAY_CHAXUN_BUDAN_NOTIFY.equals(str)) {
                    PayLibHelper.getInstance().consume(DealPayNotify.this.data_signature, DealPayNotify.this.purchaseToken, DealPayNotify.this.object);
                } else if (!ComConstants.SDK_PAY_LOCAL_BUDAN_NOTIFY.equals(str)) {
                    ComConstants.SDK_PAY_CHAXUN_BUDAN_NOORDER_NOTIFY.equals(str);
                }
                Lxhw_AreaPlatform.getInstance().onTrackEventJsonObject(Lxhw_XSDK.getInstance().getContext(), ComConfig.CUSTOM_SDK_PAY_ERROR, ComConfig.CUSTOM_SDK_DELIVER_ERROR, baseBean.getCode(), baseBean.getMessage());
            }
        };
    }

    public void payNotify(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Object obj) {
        LLog.i_Control("payNotify type=" + str);
        this.object = obj;
        this.data_signature = str7;
        this.purchaseToken = str8;
        HashMap hashMap = new HashMap();
        hashMap.put("order_number", str2);
        hashMap.put("platform_uid", str3);
        hashMap.put("server_id", str4);
        hashMap.put("role_id", str5);
        hashMap.put("google_inapp_purchase_data", str6);
        hashMap.put("google_inapp_data_signature", str7);
        if (PayLibHelper.getInstance().isOneStore()) {
            hashMap.put("pay_way", "oneStore");
            hashMap.put("options", str6);
        }
        if (PayLibHelper.getInstance().isSamsung()) {
            hashMap.put("pay_way", "Samsung");
            hashMap.put("options", str6);
        }
        if (PayLibHelper.getInstance().isAptoide()) {
            hashMap.put("pay_way", "Aptoide");
            hashMap.put("options", str6);
        }
        if (getiTest() == 1) {
            hashMap.put("skip_cp_send", 1);
        } else {
            hashMap.put("skip_cp_send", 0);
        }
        this.presenter.doRequestComAddHead(str, AreaBaseService.PAYNOTIFY, hashMap);
    }

    public void payNotifyNoOrder(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        LLog.i_Control("payNotifyNoOrder type=" + str);
        this.data_signature = str6;
        this.purchaseToken = str7;
        HashMap hashMap = new HashMap();
        hashMap.put("platform_uid", str2);
        hashMap.put("server_id", str3);
        hashMap.put("role_id", str4);
        hashMap.put("google_inapp_purchase_data", str5);
        hashMap.put("google_inapp_data_signature", str6);
        if (getiTest() == 1) {
            hashMap.put("skip_cp_send", 1);
        } else {
            hashMap.put("skip_cp_send", 0);
        }
        this.presenter.doRequestComAddHead(str, AreaBaseService.PAYNOTIFYNOORDER, hashMap);
    }
}
