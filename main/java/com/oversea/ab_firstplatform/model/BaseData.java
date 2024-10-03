package com.oversea.ab_firstplatform.model;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class BaseData implements Serializable {
    private static final long serialVersionUID = 1;
    private int code;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
