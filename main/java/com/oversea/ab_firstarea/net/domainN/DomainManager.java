package com.oversea.ab_firstarea.net.domainN;

import android.text.TextUtils;
import com.oversea.ab_firstarea.channel.PTypeUrl;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.xsdk.ab_firstbase.net.okhttp3.DomainCallback;
import com.xsdk.ab_firstbase.net.okhttp3.DomainUtils;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ContextHolder;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DomainManager {
    private static volatile DomainManager domainUtils;
    private int currentSurveyIndex;
    private int currentWebPayIndex;

    public static DomainManager getInstance() {
        if (domainUtils == null) {
            synchronized (DomainManager.class) {
                if (domainUtils == null) {
                    domainUtils = new DomainManager();
                }
            }
        }
        return domainUtils;
    }

    public void init() {
        initDomains();
        DomainUtils.getInstance().setCallback(new DomainCallback() { // from class: com.oversea.ab_firstarea.net.domainN.DomainManager.1
            @Override // com.xsdk.ab_firstbase.net.okhttp3.DomainCallback
            public List<String> getUrlList(String str) {
                return DomainManager.this.getUrlList(str);
            }

            @Override // com.xsdk.ab_firstbase.net.okhttp3.DomainCallback
            public void updateUrl(String str) {
                if (DomainManager.this.getApiSdkDomainList().contains(str)) {
                    AreaBaseService.setUrl(str);
                } else if (DomainManager.this.getApiPayDomainList().contains(str)) {
                    AreaBaseService.setPayUrl(str);
                } else if (DomainManager.this.getApiLogDomainList().contains(str)) {
                    AreaBaseService.setApiLogsUrl(str);
                }
            }
        });
    }

    public void initDomains() {
        AreaBaseService.setUrl(PTypeUrl.curApiSdkUrl1);
        AreaBaseService.setPayUrl(PTypeUrl.curGPUrl);
        AreaBaseService.setPlatformPayUrl(PTypeUrl.curWPUrl);
        AreaBaseService.setSurveyUrl(PTypeUrl.SURVEYURL);
        AreaBaseService.setApiLogsUrl(PTypeUrl.EVENT_REPORT_URL);
        HttpRequestCenter.getInstance().getDomainUrl();
        setBackDomainFromServer();
    }

    public void setBackDomainFromServer() {
        DomainBean domainBean;
        String stringKeyForValue = ImageUtils.getStringKeyForValue(ContextHolder.getContext(), Constants.SDK_DOWNLOAD_BACKDOMAIN);
        if (TextUtils.isEmpty(stringKeyForValue) || (domainBean = (DomainBean) JsonUtility.jsonToObject(DomainBean.class, stringKeyForValue)) == null || domainBean.getApi_sdk() == null) {
            return;
        }
        if (!getApiSdkDomainList().contains(domainBean.getApi_sdk())) {
            getApiSdkDomainList().add(domainBean.getApi_sdk());
        }
        if (!getApiPayDomainList().contains(domainBean.getApi_pay())) {
            getApiPayDomainList().add(domainBean.getApi_pay());
        }
        if (getApiLogDomainList().contains(domainBean.getApi_logs())) {
            return;
        }
        getApiLogDomainList().add(domainBean.getApi_logs());
    }

    public void changeWebPayDomain() {
        try {
            int i = this.currentWebPayIndex + 1;
            this.currentWebPayIndex = i;
            if (i >= getWebPayDomainList().size()) {
                this.currentWebPayIndex = 0;
            }
            AreaBaseService.setPlatformPayUrl(getWebPayDomainList().get(this.currentWebPayIndex));
        } catch (Exception unused) {
        }
    }

    public void changeSurveyDomain() {
        try {
            int i = this.currentSurveyIndex + 1;
            this.currentSurveyIndex = i;
            if (i >= getSurveyDomainList().size()) {
                this.currentSurveyIndex = 0;
            }
            AreaBaseService.setSurveyUrl(getSurveyDomainList().get(this.currentSurveyIndex));
        } catch (Exception unused) {
        }
    }

    public List<String> getApiSdkDomainList() {
        return PTypeUrl.apiSDKUrlList;
    }

    public List<String> getApiPayDomainList() {
        return PTypeUrl.apiPayUrlList;
    }

    public List<String> getWebPayDomainList() {
        return PTypeUrl.webPayUrlList;
    }

    public List<String> getSurveyDomainList() {
        return PTypeUrl.surveyUrlList;
    }

    public List<String> getApiLogDomainList() {
        return PTypeUrl.apiLogUrlList;
    }

    public List<String> getUrlList(String str) {
        List<String> apiLogDomainList;
        ArrayList arrayList = new ArrayList();
        try {
            URI uri = new URL(str).toURI();
            String host = uri.getHost();
            String str2 = uri.getScheme() + "://" + host;
            if (getApiSdkDomainList().contains(str2)) {
                apiLogDomainList = getApiSdkDomainList();
            } else if (getApiPayDomainList().contains(str2)) {
                apiLogDomainList = getApiPayDomainList();
            } else {
                if (!getApiLogDomainList().contains(str2)) {
                    return arrayList;
                }
                apiLogDomainList = getApiLogDomainList();
            }
            return apiLogDomainList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }
}
