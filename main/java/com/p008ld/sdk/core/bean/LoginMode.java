package com.p008ld.sdk.core.bean;

/* compiled from: LoginMode.kt */
/* loaded from: classes2.dex */
public enum LoginMode {
    NONE("NONE"),
    AUTO("AUTO"),
    FACEBOOK("FACEBOOK"),
    GOOGLE("GOOGLE"),
    LINE("LINE"),
    PHONE("PHONE"),
    USERNAME("USERNAME"),
    EMAIL("EMAIL"),
    SID("SID");

    private final String value;

    LoginMode(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
