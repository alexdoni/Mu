package com.p008ld.sdk.track;

/* compiled from: LDTrackEvent.kt */
/* loaded from: classes2.dex */
public enum LDTrackEvent {
    INIT_CP_SDK("init_cp_sdk", "初始化联运sdk"),
    DISPLAY_LOGIN_PAGE("display_login_page", "登录界面曝光"),
    LOGIN("login", "登录行为"),
    DISPLAY_PAY_PAGE("display_pay_page", "支付界面曝光"),
    CLICK_PAY("click_pay", "点击支付"),
    PAY_FINISHED_PAY_PAGE("pay_finished_pay_page", "支付界面支付完成"),
    DISPLAY_PAY_CENTER("display_pay_center", "充值中心曝光"),
    CLICK_PAY_CENTER("click_pay_center", "充值中心点击支付"),
    PAY_FINISHED_PAY_CENTER("pay_finished_pay_center", "充值中心支付完成"),
    CP_LOGIN_CP("cp_login_cp", "未接入 sdk 登录,cp 上报的登录行为");

    private final String desc;
    private final String key;

    LDTrackEvent(String str, String str2) {
        this.key = str;
        this.desc = str2;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getKey() {
        return this.key;
    }
}
