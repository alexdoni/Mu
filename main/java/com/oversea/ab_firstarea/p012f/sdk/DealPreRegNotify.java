package com.oversea.ab_firstarea.p012f.sdk;

import android.app.Activity;
import android.util.Log;
import com.oversea.ab_firstarea.dpresenter.PresenterSimple;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_SimplePresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_BaseView;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.orderSyn.GooglePreRegistrationSupplementOrder;
import com.oversea.ab_firstarea.orderSyn.PreRegistrationOrderInfo;
import com.oversea.ab_firstarea.plugin.pay.PayLibHelper;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import java.util.HashMap;

/* loaded from: classes.dex */
public class DealPreRegNotify {
    private final String TAG = "DealPreRegNotify";
    private Lxhw_BaseView<String> baseView;
    private String data_signature;
    private Activity mContext;
    private PresenterSimple presenter;
    private String purchaseToken;
    private String purchase_data;

    public DealPreRegNotify(Activity activity) {
        this.mContext = activity;
        initBaseView();
        this.presenter = new Lxhw_SimplePresenterImpl(activity, this.baseView);
    }

    public void initBaseView() {
        this.baseView = new Lxhw_BaseView<String>() { // from class: com.oversea.ab_firstarea.f.sdk.DealPreRegNotify.1
            @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
            public void onReqSuccess(String str, String str2) {
                Log.v("DealPreRegNotify", "onReqSuccess type=" + str);
                try {
                    if (ComConstants.SDK_PRE_REG_REWARD_NOTIFY.equals(str)) {
                        Log.v("DealPreRegNotify", "pre reg payNotify onReqSuccess consume result: " + str2);
                        PayLibHelper.getInstance().consume(DealPreRegNotify.this.data_signature, DealPreRegNotify.this.purchaseToken, null);
                        PreRegistrationOrderInfo preRegistrationOrderInfoBySignature = GooglePreRegistrationSupplementOrder.getInstance().getPreRegistrationOrderInfoBySignature(DealPreRegNotify.this.data_signature);
                        if (preRegistrationOrderInfoBySignature != null) {
                            preRegistrationOrderInfoBySignature.setNotifyTimes(preRegistrationOrderInfoBySignature.getNotifyTimes() + 1);
                            preRegistrationOrderInfoBySignature.setReceiveStatus(1);
                            GooglePreRegistrationSupplementOrder.getInstance().updateOrder(preRegistrationOrderInfoBySignature);
                        }
                    }
                } catch (Throwable unused) {
                }
            }

            @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
            public void onReqFail(String str, BaseBean baseBean) {
                PreRegistrationOrderInfo preRegistrationOrderInfoBySignature;
                Log.v("DealPreRegNotify", "onReqFail type =" + str + " code=" + baseBean.getCode() + " " + baseBean.getMessage());
                if (!ComConstants.SDK_PRE_REG_REWARD_NOTIFY.equals(str) || (preRegistrationOrderInfoBySignature = GooglePreRegistrationSupplementOrder.getInstance().getPreRegistrationOrderInfoBySignature(DealPreRegNotify.this.data_signature)) == null) {
                    return;
                }
                preRegistrationOrderInfoBySignature.setNotifyTimes(preRegistrationOrderInfoBySignature.getNotifyTimes() + 1);
                GooglePreRegistrationSupplementOrder.getInstance().updateOrder(preRegistrationOrderInfoBySignature);
            }
        };
    }

    public void payNotify(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        Log.v("DealPreRegNotify", "payNotify type=" + str);
        this.purchase_data = str2;
        this.data_signature = str3;
        this.purchaseToken = str4;
        HashMap hashMap = new HashMap();
        hashMap.put("google_inapp_purchase_data", str2);
        hashMap.put("google_inapp_data_signature", str3);
        hashMap.put("server_id", str7);
        hashMap.put("server_name", str8);
        hashMap.put("role_id", str5);
        hashMap.put("role_name", str6);
        this.presenter.doRequestComAddHead(str, AreaBaseService.GOOGLE_PRE_REGISTRATION_REWARD_URL, hashMap);
    }
}
