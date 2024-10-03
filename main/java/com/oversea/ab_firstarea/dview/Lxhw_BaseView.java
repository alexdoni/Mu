package com.oversea.ab_firstarea.dview;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public interface Lxhw_BaseView<T> {
    void onReqFail(String str, BaseBean baseBean);

    void onReqSuccess(String str, T t);
}
