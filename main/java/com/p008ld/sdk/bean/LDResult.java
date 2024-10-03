package com.p008ld.sdk.bean;

import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: LDResult.kt */
/* loaded from: classes2.dex */
public class LDResult<T> implements IResult, Serializable {
    private final Integer code;
    private final T data;
    private final String message;
    private final String msg;

    public LDResult() {
        this(null, null, null, null, 15, null);
    }

    public LDResult(T t, Integer num, String str, String str2) {
        this.data = t;
        this.code = num;
        this.message = str;
        this.msg = str2;
    }

    public /* synthetic */ LDResult(Object obj, Integer num, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : obj, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2);
    }

    public T getData() {
        return this.data;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override // com.p008ld.sdk.bean.IResult
    public boolean isSuccess() {
        Integer code = getCode();
        return code != null && code.intValue() == 200;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("code: ");
        sb.append(getCode());
        sb.append(" , msg: ");
        String message = getMessage();
        if (message == null) {
            message = getMsg();
        }
        sb.append(message);
        return sb.toString();
    }
}
