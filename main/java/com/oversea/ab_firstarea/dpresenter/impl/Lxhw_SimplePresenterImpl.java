package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dm.Lxhw_SimpleModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_SimpleModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnBaseListener;
import com.oversea.ab_firstarea.dpresenter.PresenterSimple;
import com.oversea.ab_firstarea.dview.Lxhw_BaseView;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_SimplePresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_SimpleModel<String>, Lxhw_BaseView<String>> implements OnBaseListener<String>, PresenterSimple<String> {
    private Lxhw_BaseView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_SimplePresenterImpl(Activity activity, Lxhw_BaseView lxhw_BaseView) {
        super(activity, lxhw_BaseView);
        this.view = lxhw_BaseView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_SimpleModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_SimpleModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, String str2) {
        Lxhw_BaseView lxhw_BaseView = this.view;
        if (lxhw_BaseView != null) {
            lxhw_BaseView.onReqSuccess(str, str2);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_BaseView lxhw_BaseView = this.view;
        if (lxhw_BaseView != null) {
            lxhw_BaseView.onReqFail(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterSimple
    public void doRequestComAddHead(String str, String str2, Map<String, Object> map) {
        ((Lxhw_SimpleModel) this.model).doRequestAddHead(str, str2, this, map);
    }
}
