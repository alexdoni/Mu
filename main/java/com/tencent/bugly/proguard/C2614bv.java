package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.bv */
/* loaded from: classes3.dex */
public final class C2614bv extends AbstractC2625m implements Cloneable {

    /* renamed from: f */
    static ArrayList<C2613bu> f1422f;

    /* renamed from: g */
    static Map<String, String> f1423g;

    /* renamed from: a */
    public byte f1424a = 0;

    /* renamed from: b */
    public String f1425b = "";

    /* renamed from: c */
    public String f1426c = "";

    /* renamed from: d */
    public ArrayList<C2613bu> f1427d = null;

    /* renamed from: e */
    public Map<String, String> f1428e = null;

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo995a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo994a(C2624l c2624l) {
        c2624l.m1072a(this.f1424a, 0);
        String str = this.f1425b;
        if (str != null) {
            c2624l.m1077a(str, 1);
        }
        String str2 = this.f1426c;
        if (str2 != null) {
            c2624l.m1077a(str2, 2);
        }
        ArrayList<C2613bu> arrayList = this.f1427d;
        if (arrayList != null) {
            c2624l.m1078a((Collection) arrayList, 3);
        }
        Map<String, String> map = this.f1428e;
        if (map != null) {
            c2624l.m1079a((Map) map, 4);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo993a(C2623k c2623k) {
        this.f1424a = c2623k.m1048a(this.f1424a, 0, true);
        this.f1425b = c2623k.m1058b(1, false);
        this.f1426c = c2623k.m1058b(2, false);
        if (f1422f == null) {
            f1422f = new ArrayList<>();
            f1422f.add(new C2613bu());
        }
        this.f1427d = (ArrayList) c2623k.m1053a((C2623k) f1422f, 3, false);
        if (f1423g == null) {
            HashMap hashMap = new HashMap();
            f1423g = hashMap;
            hashMap.put("", "");
        }
        this.f1428e = (Map) c2623k.m1053a((C2623k) f1423g, 4, false);
    }
}
