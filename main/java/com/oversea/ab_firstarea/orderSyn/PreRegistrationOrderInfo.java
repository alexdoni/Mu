package com.oversea.ab_firstarea.orderSyn;

/* loaded from: classes.dex */
public class PreRegistrationOrderInfo extends OrderBaseInfo {
    private int maxNotifyTimes;
    private int notifyTimes;
    private int receiveStatus;
    private String roleName;
    private String serverName;

    public PreRegistrationOrderInfo(String str, String str2, String str3, String str4, String str5) {
        super(str, str2, str3, str4, str5);
        this.receiveStatus = 0;
        this.notifyTimes = 0;
        this.maxNotifyTimes = 3;
    }

    public PreRegistrationOrderInfo(String str, String str2, String str3, String str4) {
        super("", str, str2, str3, str4);
        this.receiveStatus = 0;
        this.notifyTimes = 0;
        this.maxNotifyTimes = 3;
    }

    public int getReceiveStatus() {
        return this.receiveStatus;
    }

    public void setReceiveStatus(int i) {
        this.receiveStatus = i;
    }

    public int getNotifyTimes() {
        return this.notifyTimes;
    }

    public void setNotifyTimes(int i) {
        this.notifyTimes = i;
    }

    public int getMaxNotifyTimes() {
        return this.maxNotifyTimes;
    }

    public void setMaxNotifyTimes(int i) {
        this.maxNotifyTimes = i;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String str) {
        this.serverName = str;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String str) {
        this.roleName = str;
    }
}
