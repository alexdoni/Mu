package com.p008ld.sdk.core.bean;

import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public class ConfigBean {
    public Set<String> allowLoginMethod;
    public String couponUseUrl;
    public String facebookLoginUrl;
    public boolean forcedUpdating;
    public String gameIconUrl;
    public String gameName;
    public boolean hasNewVersion;
    public int isCoupon;
    public int isPay;
    public int isWork;
    public String lineLoginUrl;
    public String newPkgSize;
    public long newPkgSizeLong;
    public String newVersionCode;
    public String newVersionDesc;
    public String newVersionName;
    public String newVersionUrl;
    public String privacyPolicyUrl;
    public String productUrl;
    public List<ContactUsBean> sdkCustomerServiceConfigVoList;
    public String termsOfServiceUrl;
    public String thirdLoginH5Url;

    /* loaded from: classes2.dex */
    public static class ContactUsBean {
        public String iconUrl;
        public String url;
    }
}
