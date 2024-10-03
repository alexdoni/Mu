package com.oversea.ab_firstarea.dpresenter.impl;

import com.oversea.ab_firstarea.dm.Lxhw_RequestComAuto;
import com.oversea.ab_firstarea.dm.impl.Lxhw_RequestComAutoModelImpl;
import com.oversea.ab_firstarea.dpresenter.PresenterRequestComAuto;

/* loaded from: classes.dex */
public class Lxhw_RequestComAutoPresenterImpl implements PresenterRequestComAuto {
    private Lxhw_RequestComAuto model = new Lxhw_RequestComAutoModelImpl();

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterRequestComAuto
    public void doLogin(String str, String str2) {
        this.model.autoLogin(str, str2);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterRequestComAuto
    public void doUserBindInfo(String str) {
        this.model.userBindInfo(str);
    }
}
