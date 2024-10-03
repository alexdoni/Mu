package com.oversea.ab_firstarea.orderSyn;

import android.text.TextUtils;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;

/* loaded from: classes.dex */
public class OrderBaseInfo {
    public long lastTimeNotify;
    private String platform_order;
    private String productId;
    private String purchaseToken;
    private String signature;
    private String signedData;
    private String uid;
    private String serverId = "";
    private String roleId = "";
    private int failTime = 0;
    public final int MAXFAILTIMECONSUME = 2;
    public final int MAXFAILTIME = 5;
    public String sLingqu = "";

    public OrderBaseInfo(String str, String str2, String str3, String str4, String str5) {
        this.uid = "";
        this.lastTimeNotify = 0L;
        this.platform_order = str;
        this.productId = str2;
        this.signedData = str3;
        this.signature = str4;
        this.purchaseToken = str5;
        this.uid = Lxhw_XUserInfo.getInstance().getSdkId() + "";
        this.lastTimeNotify = System.currentTimeMillis();
    }

    public String getUid() {
        if (TextUtils.isEmpty(this.uid)) {
            this.uid = "";
        }
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getServerId() {
        if (TextUtils.isEmpty(this.serverId)) {
            this.serverId = "";
        }
        return this.serverId;
    }

    public void setServerId(String str) {
        this.serverId = str;
    }

    public String getRoleId() {
        if (TextUtils.isEmpty(this.roleId)) {
            this.roleId = "";
        }
        return this.roleId;
    }

    public void setRoleId(String str) {
        this.roleId = str;
    }

    public String getProductId() {
        if (TextUtils.isEmpty(this.productId)) {
            this.productId = "";
        }
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getPlatform_order() {
        if (TextUtils.isEmpty(this.platform_order)) {
            this.platform_order = "";
        }
        return this.platform_order;
    }

    public void setPlatform_order(String str) {
        this.platform_order = str;
    }

    public String getSignedData() {
        if (TextUtils.isEmpty(this.signedData)) {
            this.signedData = "";
        }
        return this.signedData;
    }

    public void setSignedData(String str) {
        this.signedData = str;
    }

    public String getSignature() {
        if (TextUtils.isEmpty(this.signature)) {
            this.signature = "";
        }
        return this.signature;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public String getPurchaseToken() {
        if (TextUtils.isEmpty(this.purchaseToken)) {
            this.purchaseToken = "";
        }
        return this.purchaseToken;
    }

    public void setPurchaseToken(String str) {
        this.purchaseToken = str;
    }

    public String getsLingqu() {
        return this.sLingqu;
    }

    public void setsLingqu(String str) {
        this.sLingqu = str;
    }

    public int getFailTime() {
        return this.failTime;
    }

    public void setFailTime(int i) {
        this.failTime = i;
    }

    public long getLastTimeNotify() {
        return this.lastTimeNotify;
    }

    public void setLastTimeNotify(long j) {
        this.lastTimeNotify = j;
    }
}
