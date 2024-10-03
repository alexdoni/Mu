package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.bo */
/* loaded from: classes3.dex */
public final class C2607bo extends AbstractC2625m {

    /* renamed from: A */
    static ArrayList<C2606bn> f1329A;

    /* renamed from: B */
    static Map<String, String> f1330B;

    /* renamed from: C */
    static Map<String, String> f1331C;

    /* renamed from: v */
    static Map<String, String> f1332v;

    /* renamed from: w */
    static C2605bm f1333w;

    /* renamed from: x */
    static C2604bl f1334x;

    /* renamed from: y */
    static ArrayList<C2604bl> f1335y;

    /* renamed from: z */
    static ArrayList<C2604bl> f1336z;

    /* renamed from: a */
    public String f1337a = "";

    /* renamed from: b */
    public long f1338b = 0;

    /* renamed from: c */
    public String f1339c = "";

    /* renamed from: d */
    public String f1340d = "";

    /* renamed from: e */
    public String f1341e = "";

    /* renamed from: f */
    public String f1342f = "";

    /* renamed from: g */
    public String f1343g = "";

    /* renamed from: h */
    public Map<String, String> f1344h = null;

    /* renamed from: i */
    public String f1345i = "";

    /* renamed from: j */
    public C2605bm f1346j = null;

    /* renamed from: k */
    public int f1347k = 0;

    /* renamed from: l */
    public String f1348l = "";

    /* renamed from: m */
    public String f1349m = "";

    /* renamed from: n */
    public C2604bl f1350n = null;

    /* renamed from: o */
    public ArrayList<C2604bl> f1351o = null;

    /* renamed from: p */
    public ArrayList<C2604bl> f1352p = null;

    /* renamed from: q */
    public ArrayList<C2606bn> f1353q = null;

    /* renamed from: r */
    public Map<String, String> f1354r = null;

    /* renamed from: s */
    public Map<String, String> f1355s = null;

    /* renamed from: t */
    public String f1356t = "";

    /* renamed from: u */
    public boolean f1357u = true;

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo994a(C2624l c2624l) {
        c2624l.m1077a(this.f1337a, 0);
        c2624l.m1074a(this.f1338b, 1);
        c2624l.m1077a(this.f1339c, 2);
        String str = this.f1340d;
        if (str != null) {
            c2624l.m1077a(str, 3);
        }
        String str2 = this.f1341e;
        if (str2 != null) {
            c2624l.m1077a(str2, 4);
        }
        String str3 = this.f1342f;
        if (str3 != null) {
            c2624l.m1077a(str3, 5);
        }
        String str4 = this.f1343g;
        if (str4 != null) {
            c2624l.m1077a(str4, 6);
        }
        Map<String, String> map = this.f1344h;
        if (map != null) {
            c2624l.m1079a((Map) map, 7);
        }
        String str5 = this.f1345i;
        if (str5 != null) {
            c2624l.m1077a(str5, 8);
        }
        C2605bm c2605bm = this.f1346j;
        if (c2605bm != null) {
            c2624l.m1075a((AbstractC2625m) c2605bm, 9);
        }
        c2624l.m1073a(this.f1347k, 10);
        String str6 = this.f1348l;
        if (str6 != null) {
            c2624l.m1077a(str6, 11);
        }
        String str7 = this.f1349m;
        if (str7 != null) {
            c2624l.m1077a(str7, 12);
        }
        C2604bl c2604bl = this.f1350n;
        if (c2604bl != null) {
            c2624l.m1075a((AbstractC2625m) c2604bl, 13);
        }
        ArrayList<C2604bl> arrayList = this.f1351o;
        if (arrayList != null) {
            c2624l.m1078a((Collection) arrayList, 14);
        }
        ArrayList<C2604bl> arrayList2 = this.f1352p;
        if (arrayList2 != null) {
            c2624l.m1078a((Collection) arrayList2, 15);
        }
        ArrayList<C2606bn> arrayList3 = this.f1353q;
        if (arrayList3 != null) {
            c2624l.m1078a((Collection) arrayList3, 16);
        }
        Map<String, String> map2 = this.f1354r;
        if (map2 != null) {
            c2624l.m1079a((Map) map2, 17);
        }
        Map<String, String> map3 = this.f1355s;
        if (map3 != null) {
            c2624l.m1079a((Map) map3, 18);
        }
        String str8 = this.f1356t;
        if (str8 != null) {
            c2624l.m1077a(str8, 19);
        }
        c2624l.m1081a(this.f1357u, 20);
    }

    static {
        HashMap hashMap = new HashMap();
        f1332v = hashMap;
        hashMap.put("", "");
        f1333w = new C2605bm();
        f1334x = new C2604bl();
        f1335y = new ArrayList<>();
        f1335y.add(new C2604bl());
        f1336z = new ArrayList<>();
        f1336z.add(new C2604bl());
        f1329A = new ArrayList<>();
        f1329A.add(new C2606bn());
        HashMap hashMap2 = new HashMap();
        f1330B = hashMap2;
        hashMap2.put("", "");
        HashMap hashMap3 = new HashMap();
        f1331C = hashMap3;
        hashMap3.put("", "");
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo993a(C2623k c2623k) {
        this.f1337a = c2623k.m1058b(0, true);
        this.f1338b = c2623k.m1051a(this.f1338b, 1, true);
        this.f1339c = c2623k.m1058b(2, true);
        this.f1340d = c2623k.m1058b(3, false);
        this.f1341e = c2623k.m1058b(4, false);
        this.f1342f = c2623k.m1058b(5, false);
        this.f1343g = c2623k.m1058b(6, false);
        this.f1344h = (Map) c2623k.m1053a((C2623k) f1332v, 7, false);
        this.f1345i = c2623k.m1058b(8, false);
        this.f1346j = (C2605bm) c2623k.m1052a((AbstractC2625m) f1333w, 9, false);
        this.f1347k = c2623k.m1049a(this.f1347k, 10, false);
        this.f1348l = c2623k.m1058b(11, false);
        this.f1349m = c2623k.m1058b(12, false);
        this.f1350n = (C2604bl) c2623k.m1052a((AbstractC2625m) f1334x, 13, false);
        this.f1351o = (ArrayList) c2623k.m1053a((C2623k) f1335y, 14, false);
        this.f1352p = (ArrayList) c2623k.m1053a((C2623k) f1336z, 15, false);
        this.f1353q = (ArrayList) c2623k.m1053a((C2623k) f1329A, 16, false);
        this.f1354r = (Map) c2623k.m1053a((C2623k) f1330B, 17, false);
        this.f1355s = (Map) c2623k.m1053a((C2623k) f1331C, 18, false);
        this.f1356t = c2623k.m1058b(19, false);
        this.f1357u = c2623k.m1057a(20, false);
    }
}
