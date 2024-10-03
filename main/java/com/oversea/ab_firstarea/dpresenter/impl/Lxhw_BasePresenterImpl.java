package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;

/* loaded from: classes.dex */
public abstract class Lxhw_BasePresenterImpl<M, V> {
    public Activity mActivity;
    public M model;
    public V view;

    public abstract void initModel();

    public abstract void onDestroy();

    public Lxhw_BasePresenterImpl(Activity activity, V v) {
        this.mActivity = activity;
        this.view = v;
        initModel();
    }
}
