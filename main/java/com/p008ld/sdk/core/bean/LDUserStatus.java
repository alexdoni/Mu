package com.p008ld.sdk.core.bean;

/* compiled from: LDLoginResult.kt */
/* loaded from: classes2.dex */
public enum LDUserStatus {
    BAN(0, "封禁"),
    NORMAL(1, "正常"),
    LOGOFF(2, "注销"),
    IN_LOGOFF(3, "注销中");

    private final int code;
    private final String msg;

    LDUserStatus(int i, String str) {
        this.code = i;
        this.msg = str;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMsg() {
        return this.msg;
    }
}
