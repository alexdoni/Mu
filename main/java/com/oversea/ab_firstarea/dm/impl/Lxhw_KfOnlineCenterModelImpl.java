package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_KfOnlineCenterModel;
import com.oversea.ab_firstarea.dpresenter.OnKfOnlineCenterListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.IssueListBean;
import com.oversea.ab_firstarea.net.model.IssueTypeListBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_KfOnlineCenterModelImpl implements Lxhw_KfOnlineCenterModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_KfOnlineCenterModel
    public void requestIssueTypeList(final OnKfOnlineCenterListener<IssueListBean, IssueTypeListBean> onKfOnlineCenterListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("requestIssueTypeList");
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.CUSTOMERISSUETYPELIST_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("requestIssueTypeList", createCommonParams, AreaBaseService.CUSTOMERISSUETYPELIST_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_KfOnlineCenterModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str) {
                try {
                    if (!TextUtils.isEmpty(str)) {
                        IssueTypeListBean issueTypeListBean = (IssueTypeListBean) JsonUtility.jsonToObject(IssueTypeListBean.class, str);
                        if (issueTypeListBean.getCode() == 0) {
                            onKfOnlineCenterListener.onIssueListTypeSuccess(issueTypeListBean);
                        } else {
                            onKfOnlineCenterListener.onReqFail("", issueTypeListBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onKfOnlineCenterListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_KfOnlineCenterModel
    public void requestIssueList(final OnKfOnlineCenterListener<IssueListBean, IssueTypeListBean> onKfOnlineCenterListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("requestIssueList");
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.CUSTOMERISSUELIST_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("requestIssueList", createCommonParams, AreaBaseService.CUSTOMERISSUELIST_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_KfOnlineCenterModelImpl.2
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str) {
                try {
                    if (!TextUtils.isEmpty(str)) {
                        IssueListBean issueListBean = (IssueListBean) JsonUtility.jsonToObject(IssueListBean.class, str);
                        if (issueListBean.getCode() == 0) {
                            onKfOnlineCenterListener.onIssueListSuccess(issueListBean);
                        } else {
                            onKfOnlineCenterListener.onReqFail("", issueListBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onKfOnlineCenterListener.onReqFail("", baseBean);
            }
        });
    }
}
