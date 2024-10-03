package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_SimpleModel;
import com.oversea.ab_firstarea.dpresenter.OnBaseListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_SimpleModelImpl implements Lxhw_SimpleModel<String> {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_SimpleModel
    public void doRequestAddHead(final String str, String str2, final OnBaseListener<String> onBaseListener, Map<String, Object> map) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams(str);
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                createCommonParams.put(entry.getKey(), entry.getValue());
            }
        }
        httpParamsCommon.addSign(createCommonParams, str);
        HttpRequestCenter.getInstance().doRequestCommonAddHead(str, createCommonParams, str2, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_SimpleModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    if (!TextUtils.isEmpty(str3)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str3);
                        if (baseBean.getCode() == 0) {
                            onBaseListener.onReqSuccess(str, str3);
                        } else {
                            onBaseListener.onReqFail(str, baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onBaseListener.onReqFail(str, baseBean);
            }
        });
    }
}
