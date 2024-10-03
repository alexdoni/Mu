package com.tencent.bugly.proguard;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ar */
/* loaded from: classes3.dex */
public final class C2583ar implements Comparable<C2583ar> {

    /* renamed from: a */
    public long f1164a = -1;

    /* renamed from: b */
    public long f1165b = -1;

    /* renamed from: c */
    public String f1166c = null;

    /* renamed from: d */
    public boolean f1167d = false;

    /* renamed from: e */
    public boolean f1168e = false;

    /* renamed from: f */
    public int f1169f = 0;

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(C2583ar c2583ar) {
        C2583ar c2583ar2 = c2583ar;
        if (c2583ar2 == null) {
            return 1;
        }
        long j = this.f1165b - c2583ar2.f1165b;
        if (j <= 0) {
            return j < 0 ? -1 : 0;
        }
        return 1;
    }
}
