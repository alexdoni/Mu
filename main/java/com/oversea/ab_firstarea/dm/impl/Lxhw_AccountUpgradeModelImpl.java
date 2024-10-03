package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_AccountUpgradeModel;
import com.oversea.ab_firstarea.dpresenter.OnAccountUpgradeListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_AccountUpgradeModelImpl implements Lxhw_AccountUpgradeModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_AccountUpgradeModel
    public void doUpgradeAccount(final String str, String str2, String str3, final OnAccountUpgradeListener<BaseBean> onAccountUpgradeListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doUpgradeAccount");
        createCommonParams.put("platform_account", str2);
        createCommonParams.put("new_password", str3);
        createCommonParams.put("confirm_password", str3);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.UPGRADEACCOUNT_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doUpgradeAccount", createCommonParams, AreaBaseService.UPGRADEACCOUNT_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_AccountUpgradeModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str4) {
                try {
                    if (!TextUtils.isEmpty(str4)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str4);
                        if (baseBean.getCode() == 0) {
                            onAccountUpgradeListener.onReqSuccess(str, baseBean);
                        } else {
                            onAccountUpgradeListener.onReqFail(str, baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onAccountUpgradeListener.onReqFail(str, baseBean);
            }
        });
    }
}
