package com.tencent.bugly.proguard;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.bl */
/* loaded from: classes3.dex */
public final class C2604bl extends AbstractC2625m implements Cloneable {

    /* renamed from: a */
    public String f1317a = "";

    /* renamed from: b */
    public String f1318b = "";

    /* renamed from: c */
    public String f1319c = "";

    /* renamed from: d */
    public String f1320d = "";

    /* renamed from: e */
    public String f1321e = "";

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo995a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo994a(C2624l c2624l) {
        c2624l.m1077a(this.f1317a, 0);
        String str = this.f1318b;
        if (str != null) {
            c2624l.m1077a(str, 1);
        }
        String str2 = this.f1319c;
        if (str2 != null) {
            c2624l.m1077a(str2, 2);
        }
        String str3 = this.f1320d;
        if (str3 != null) {
            c2624l.m1077a(str3, 3);
        }
        String str4 = this.f1321e;
        if (str4 != null) {
            c2624l.m1077a(str4, 4);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo993a(C2623k c2623k) {
        this.f1317a = c2623k.m1058b(0, true);
        this.f1318b = c2623k.m1058b(1, false);
        this.f1319c = c2623k.m1058b(2, false);
        this.f1320d = c2623k.m1058b(3, false);
        this.f1321e = c2623k.m1058b(4, false);
    }
}
