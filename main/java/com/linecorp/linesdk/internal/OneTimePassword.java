package com.linecorp.linesdk.internal;

/* loaded from: classes2.dex */
public class OneTimePassword {

    /* renamed from: id */
    private final String f697id;
    private final String password;

    public String toString() {
        return "OneTimeIdAndPassword{id='#####', password='#####'}";
    }

    public OneTimePassword(String str, String str2) {
        this.f697id = str;
        this.password = str2;
    }

    public String getId() {
        return this.f697id;
    }

    public String getPassword() {
        return this.password;
    }
}
