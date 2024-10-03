package com.p008ld.sdk.bean;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDResult.kt */
/* loaded from: classes2.dex */
public final class InitBean {
    private final String sid;

    public static /* synthetic */ InitBean copy$default(InitBean initBean, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = initBean.sid;
        }
        return initBean.copy(str);
    }

    public final String component1() {
        return this.sid;
    }

    public final InitBean copy(String sid) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        return new InitBean(sid);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof InitBean) && Intrinsics.areEqual(this.sid, ((InitBean) obj).sid);
    }

    public int hashCode() {
        return this.sid.hashCode();
    }

    public String toString() {
        return "InitBean(sid=" + this.sid + ')';
    }

    public InitBean(String sid) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        this.sid = sid;
    }

    public final String getSid() {
        return this.sid;
    }
}
