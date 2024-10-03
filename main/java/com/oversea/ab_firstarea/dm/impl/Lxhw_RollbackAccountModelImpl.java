package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_RollbackAccountModel;
import com.oversea.ab_firstarea.dpresenter.OnRollbackAccountListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_RollbackAccountModelImpl implements Lxhw_RollbackAccountModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_RollbackAccountModel
    public void rollback(final OnRollbackAccountListener onRollbackAccountListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doRollbackAccount");
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.ROLLBACK_ACCOUNT_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doRollbackAccount", createCommonParams, AreaBaseService.ROLLBACK_ACCOUNT_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_RollbackAccountModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str) {
                try {
                    if (!TextUtils.isEmpty(str)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str);
                        if (baseBean.getCode() == 0) {
                            onRollbackAccountListener.onReqSuccess("", baseBean);
                        } else {
                            onRollbackAccountListener.onReqFail("", baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onRollbackAccountListener.onReqFail("", baseBean);
            }
        });
    }
}
