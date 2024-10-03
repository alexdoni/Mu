package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_MobileConfirmModel;
import com.oversea.ab_firstarea.dpresenter.OnMobileConfirmListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.MobileConfirmBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_MobileConfirmModelImpl implements Lxhw_MobileConfirmModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_MobileConfirmModel
    public void doBindPhoneSendCode(String str, String str2, final OnMobileConfirmListener<MobileConfirmBean> onMobileConfirmListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doBindPhoneSendCode");
        createCommonParams.put("phone_prefix", str);
        createCommonParams.put("phone", str2);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.BINDPHONESENDCODE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doBindPhoneSendCode", createCommonParams, AreaBaseService.BINDPHONESENDCODE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_MobileConfirmModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    if (!TextUtils.isEmpty(str3)) {
                        MobileConfirmBean mobileConfirmBean = (MobileConfirmBean) JsonUtility.jsonToObject(MobileConfirmBean.class, str3);
                        if (mobileConfirmBean.getCode() == 0) {
                            onMobileConfirmListener.onReqCodeSuccess();
                        } else {
                            onMobileConfirmListener.onReqFail("", mobileConfirmBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onMobileConfirmListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_MobileConfirmModel
    public void doBindPhone(String str, String str2, String str3, final OnMobileConfirmListener<MobileConfirmBean> onMobileConfirmListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doBindPhone");
        createCommonParams.put("phone_prefix", str);
        createCommonParams.put("phone", str2);
        createCommonParams.put("code", str3);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.BINDPHONE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doBindPhone", createCommonParams, AreaBaseService.BINDPHONE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_MobileConfirmModelImpl.2
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str4) {
                try {
                    if (!TextUtils.isEmpty(str4)) {
                        MobileConfirmBean mobileConfirmBean = (MobileConfirmBean) JsonUtility.jsonToObject(MobileConfirmBean.class, str4);
                        if (mobileConfirmBean.getCode() == 0) {
                            onMobileConfirmListener.onReqSuccess("", mobileConfirmBean);
                        } else {
                            onMobileConfirmListener.onReqFail("", mobileConfirmBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onMobileConfirmListener.onReqFail("", baseBean);
            }
        });
    }
}
