package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_ChangePsdDiaModel;
import com.oversea.ab_firstarea.dpresenter.OnChangePsdDiaListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.ChangePsdDiaBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_ChangePsdDiaModelImpl implements Lxhw_ChangePsdDiaModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_ChangePsdDiaModel
    public void doChangePassword(String str, String str2, final OnChangePsdDiaListener<ChangePsdDiaBean> onChangePsdDiaListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doChangePassword");
        createCommonParams.put("old_password", str);
        createCommonParams.put("new_password", str2);
        createCommonParams.put("confirm_password", str2);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.CHANGEPASSWORD_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doChangePassword", createCommonParams, AreaBaseService.CHANGEPASSWORD_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ChangePsdDiaModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    if (!TextUtils.isEmpty(str3)) {
                        ChangePsdDiaBean changePsdDiaBean = (ChangePsdDiaBean) JsonUtility.jsonToObject(ChangePsdDiaBean.class, str3);
                        if (changePsdDiaBean.getCode() == 0) {
                            onChangePsdDiaListener.onReqSuccess("", changePsdDiaBean);
                        } else {
                            onChangePsdDiaListener.onReqFail("", changePsdDiaBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onChangePsdDiaListener.onReqFail("", baseBean);
            }
        });
    }
}
