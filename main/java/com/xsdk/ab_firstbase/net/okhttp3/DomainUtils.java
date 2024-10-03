package com.xsdk.ab_firstbase.net.okhttp3;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class DomainUtils {
    private static volatile DomainUtils domainUtils;
    private DomainCallback callback;
    private List<String> api_sdk_domain_list = new ArrayList();
    private List<String> api_pay_domain_list = new ArrayList();
    private List<String> pay_platform_domain_list = new ArrayList();
    private List<String> survey_domain_list = new ArrayList();
    private List<String> api_logs_domain_list = new ArrayList();

    public static DomainUtils getInstance() {
        if (domainUtils == null) {
            synchronized (DomainUtils.class) {
                if (domainUtils == null) {
                    domainUtils = new DomainUtils();
                }
            }
        }
        return domainUtils;
    }

    public void setCallback(DomainCallback domainCallback) {
        this.callback = domainCallback;
    }

    public List<String> getUrlList(String str) {
        DomainCallback domainCallback = this.callback;
        if (domainCallback != null) {
            return domainCallback.getUrlList(str);
        }
        return new ArrayList();
    }

    public void updateUrl(String str) {
        DomainCallback domainCallback = this.callback;
        if (domainCallback != null) {
            domainCallback.updateUrl(str);
        }
    }
}
