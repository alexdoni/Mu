package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.bt */
/* loaded from: classes3.dex */
public final class C2612bt extends AbstractC2625m implements Cloneable {

    /* renamed from: m */
    static C2611bs f1398m = new C2611bs();

    /* renamed from: n */
    static Map<String, String> f1399n = null;

    /* renamed from: o */
    static final /* synthetic */ boolean f1400o = true;

    /* renamed from: a */
    public boolean f1401a = true;

    /* renamed from: b */
    public boolean f1402b = true;

    /* renamed from: c */
    public boolean f1403c = true;

    /* renamed from: d */
    public String f1404d = "";

    /* renamed from: e */
    public String f1405e = "";

    /* renamed from: f */
    public C2611bs f1406f = null;

    /* renamed from: g */
    public Map<String, String> f1407g = null;

    /* renamed from: h */
    public long f1408h = 0;

    /* renamed from: i */
    public String f1409i = "";

    /* renamed from: j */
    public String f1410j = "";

    /* renamed from: k */
    public int f1411k = 0;

    /* renamed from: l */
    public int f1412l = 0;

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        C2612bt c2612bt = (C2612bt) obj;
        return C2626n.m1086a(this.f1401a, c2612bt.f1401a) && C2626n.m1086a(this.f1402b, c2612bt.f1402b) && C2626n.m1086a(this.f1403c, c2612bt.f1403c) && C2626n.m1085a(this.f1404d, c2612bt.f1404d) && C2626n.m1085a(this.f1405e, c2612bt.f1405e) && C2626n.m1085a(this.f1406f, c2612bt.f1406f) && C2626n.m1085a(this.f1407g, c2612bt.f1407g) && C2626n.m1084a(this.f1408h, c2612bt.f1408h) && C2626n.m1085a(this.f1409i, c2612bt.f1409i) && C2626n.m1085a(this.f1410j, c2612bt.f1410j) && C2626n.m1083a(this.f1411k, c2612bt.f1411k) && C2626n.m1083a(this.f1412l, c2612bt.f1412l);
    }

    public final int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f1400o) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo994a(C2624l c2624l) {
        c2624l.m1081a(this.f1401a, 0);
        c2624l.m1081a(this.f1402b, 1);
        c2624l.m1081a(this.f1403c, 2);
        String str = this.f1404d;
        if (str != null) {
            c2624l.m1077a(str, 3);
        }
        String str2 = this.f1405e;
        if (str2 != null) {
            c2624l.m1077a(str2, 4);
        }
        C2611bs c2611bs = this.f1406f;
        if (c2611bs != null) {
            c2624l.m1075a((AbstractC2625m) c2611bs, 5);
        }
        Map<String, String> map = this.f1407g;
        if (map != null) {
            c2624l.m1079a((Map) map, 6);
        }
        c2624l.m1074a(this.f1408h, 7);
        String str3 = this.f1409i;
        if (str3 != null) {
            c2624l.m1077a(str3, 8);
        }
        String str4 = this.f1410j;
        if (str4 != null) {
            c2624l.m1077a(str4, 9);
        }
        c2624l.m1073a(this.f1411k, 10);
        c2624l.m1073a(this.f1412l, 11);
    }

    static {
        HashMap hashMap = new HashMap();
        f1399n = hashMap;
        hashMap.put("", "");
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo993a(C2623k c2623k) {
        this.f1401a = c2623k.m1057a(0, true);
        this.f1402b = c2623k.m1057a(1, true);
        this.f1403c = c2623k.m1057a(2, true);
        this.f1404d = c2623k.m1058b(3, false);
        this.f1405e = c2623k.m1058b(4, false);
        this.f1406f = (C2611bs) c2623k.m1052a((AbstractC2625m) f1398m, 5, false);
        this.f1407g = (Map) c2623k.m1053a((C2623k) f1399n, 6, false);
        this.f1408h = c2623k.m1051a(this.f1408h, 7, false);
        this.f1409i = c2623k.m1058b(8, false);
        this.f1410j = c2623k.m1058b(9, false);
        this.f1411k = c2623k.m1049a(this.f1411k, 10, false);
        this.f1412l = c2623k.m1049a(this.f1412l, 11, false);
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo995a(StringBuilder sb, int i) {
        C2621i c2621i = new C2621i(sb, i);
        c2621i.m1027a(this.f1401a, "enable");
        c2621i.m1027a(this.f1402b, "enableUserInfo");
        c2621i.m1027a(this.f1403c, "enableQuery");
        c2621i.m1024a(this.f1404d, "url");
        c2621i.m1024a(this.f1405e, "expUrl");
        c2621i.m1023a((AbstractC2625m) this.f1406f, "security");
        c2621i.m1025a((Map) this.f1407g, "valueMap");
        c2621i.m1022a(this.f1408h, "strategylastUpdateTime");
        c2621i.m1024a(this.f1409i, "httpsUrl");
        c2621i.m1024a(this.f1410j, "httpsExpUrl");
        c2621i.m1021a(this.f1411k, "eventRecordCount");
        c2621i.m1021a(this.f1412l, "eventTimeInterval");
    }
}
