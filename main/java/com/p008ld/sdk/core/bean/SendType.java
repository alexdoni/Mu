package com.p008ld.sdk.core.bean;

/* compiled from: SendType.kt */
/* loaded from: classes2.dex */
public enum SendType {
    REGISTER("reg"),
    UPDATE_PASSWORD("updatePwd"),
    SECURITY("security"),
    CHANGE_EMAIL("changeEmail"),
    BIND("bind");

    private final String value;

    SendType(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
