package com.oversea.ab_firstarea.dpresenter;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public interface OnBaseListener<T> {
    void onReqFail(String str, BaseBean baseBean);

    void onReqSuccess(String str, T t);
}
