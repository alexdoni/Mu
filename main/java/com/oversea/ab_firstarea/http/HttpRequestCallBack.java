package com.oversea.ab_firstarea.http;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public interface HttpRequestCallBack {
    void httpRequestCallBackListener(String str);

    void httpRequestFail(BaseBean baseBean);
}
