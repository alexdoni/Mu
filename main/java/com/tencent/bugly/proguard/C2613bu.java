package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.bu */
/* loaded from: classes3.dex */
public final class C2613bu extends AbstractC2625m {

    /* renamed from: i */
    static Map<String, String> f1413i;

    /* renamed from: a */
    public long f1414a = 0;

    /* renamed from: b */
    public byte f1415b = 0;

    /* renamed from: c */
    public String f1416c = "";

    /* renamed from: d */
    public String f1417d = "";

    /* renamed from: e */
    public String f1418e = "";

    /* renamed from: f */
    public Map<String, String> f1419f = null;

    /* renamed from: g */
    public String f1420g = "";

    /* renamed from: h */
    public boolean f1421h = true;

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo994a(C2624l c2624l) {
        c2624l.m1074a(this.f1414a, 0);
        c2624l.m1072a(this.f1415b, 1);
        String str = this.f1416c;
        if (str != null) {
            c2624l.m1077a(str, 2);
        }
        String str2 = this.f1417d;
        if (str2 != null) {
            c2624l.m1077a(str2, 3);
        }
        String str3 = this.f1418e;
        if (str3 != null) {
            c2624l.m1077a(str3, 4);
        }
        Map<String, String> map = this.f1419f;
        if (map != null) {
            c2624l.m1079a((Map) map, 5);
        }
        String str4 = this.f1420g;
        if (str4 != null) {
            c2624l.m1077a(str4, 6);
        }
        c2624l.m1081a(this.f1421h, 7);
    }

    static {
        HashMap hashMap = new HashMap();
        f1413i = hashMap;
        hashMap.put("", "");
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo993a(C2623k c2623k) {
        this.f1414a = c2623k.m1051a(this.f1414a, 0, true);
        this.f1415b = c2623k.m1048a(this.f1415b, 1, true);
        this.f1416c = c2623k.m1058b(2, false);
        this.f1417d = c2623k.m1058b(3, false);
        this.f1418e = c2623k.m1058b(4, false);
        this.f1419f = (Map) c2623k.m1053a((C2623k) f1413i, 5, false);
        this.f1420g = c2623k.m1058b(6, false);
        this.f1421h = c2623k.m1057a(7, false);
    }
}
