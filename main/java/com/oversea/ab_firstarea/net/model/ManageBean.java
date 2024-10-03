package com.oversea.ab_firstarea.net.model;

import android.app.Activity;

/* loaded from: classes.dex */
public class ManageBean {
    private static ManageBean instance;
    private final String TAG = getClass().toString();
    private ACloudStSBean aCloudStSBean;
    public Activity activity;
    private IssueTypeListBean issueTypeListBean;
    private KFBaseInfoBean kfBaseInfoBean;
    private LoginBean loginBean;

    public static ManageBean getInstance() {
        if (instance == null) {
            instance = new ManageBean();
        }
        return instance;
    }

    public LoginBean getLoginBean() {
        if (this.loginBean == null) {
            this.loginBean = new LoginBean();
        }
        return this.loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String getAccount() {
        return getLoginBean().getData() == null ? "" : getLoginBean().getData().getPlatform_account();
    }

    public void setAccount(String str) {
        if (getLoginBean().getData() != null) {
            getLoginBean().getData().setPlatform_account(str);
        }
    }

    public KFBaseInfoBean getKfBaseInfoBean() {
        if (this.kfBaseInfoBean == null) {
            this.kfBaseInfoBean = new KFBaseInfoBean();
        }
        return this.kfBaseInfoBean;
    }

    public void setKfBaseInfoBean(KFBaseInfoBean kFBaseInfoBean) {
        this.kfBaseInfoBean = kFBaseInfoBean;
    }

    public IssueTypeListBean getIssueTypeListBean() {
        return this.issueTypeListBean;
    }

    public void setIssueTypeListBean(IssueTypeListBean issueTypeListBean) {
        this.issueTypeListBean = issueTypeListBean;
    }

    public ACloudStSBean getaCloudStSBean() {
        if (this.aCloudStSBean == null) {
            this.aCloudStSBean = new ACloudStSBean();
        }
        return this.aCloudStSBean;
    }

    public void setaCloudStSBean(ACloudStSBean aCloudStSBean) {
        this.aCloudStSBean = aCloudStSBean;
    }
}
