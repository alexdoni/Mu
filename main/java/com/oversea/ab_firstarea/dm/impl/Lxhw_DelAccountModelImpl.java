package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_DelAccountModel;
import com.oversea.ab_firstarea.dpresenter.OnDelAccountListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_DelAccountModelImpl implements Lxhw_DelAccountModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_DelAccountModel
    public void delAccount(final OnDelAccountListener onDelAccountListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doDelAccount");
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.DEL_ACCOUNT_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doDelAccount", createCommonParams, AreaBaseService.DEL_ACCOUNT_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_DelAccountModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str) {
                try {
                    if (!TextUtils.isEmpty(str)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str);
                        if (baseBean.getCode() == 0) {
                            onDelAccountListener.onReqSuccess("", baseBean);
                        } else {
                            onDelAccountListener.onReqFail("", baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onDelAccountListener.onReqFail("", baseBean);
            }
        });
    }
}
