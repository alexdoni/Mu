package com.tencent.bugly.proguard;

import java.io.Serializable;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.m */
/* loaded from: classes3.dex */
public abstract class AbstractC2625m implements Serializable {
    /* renamed from: a */
    public abstract void mo993a(C2623k c2623k);

    /* renamed from: a */
    public abstract void mo994a(C2624l c2624l);

    /* renamed from: a */
    public abstract void mo995a(StringBuilder sb, int i);

    public String toString() {
        StringBuilder sb = new StringBuilder();
        mo995a(sb, 0);
        return sb.toString();
    }
}
