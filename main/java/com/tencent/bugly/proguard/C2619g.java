package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.g */
/* loaded from: classes3.dex */
public final class C2619g extends AbstractC2625m {

    /* renamed from: k */
    static byte[] f1443k = null;

    /* renamed from: l */
    static Map<String, String> f1444l = null;

    /* renamed from: m */
    static final /* synthetic */ boolean f1445m = true;

    /* renamed from: g */
    public byte[] f1452g;

    /* renamed from: i */
    public Map<String, String> f1454i;

    /* renamed from: j */
    public Map<String, String> f1455j;

    /* renamed from: a */
    public short f1446a = 0;

    /* renamed from: b */
    public byte f1447b = 0;

    /* renamed from: c */
    public int f1448c = 0;

    /* renamed from: d */
    public int f1449d = 0;

    /* renamed from: e */
    public String f1450e = null;

    /* renamed from: f */
    public String f1451f = null;

    /* renamed from: h */
    public int f1453h = 0;

    public final boolean equals(Object obj) {
        C2619g c2619g = (C2619g) obj;
        return C2626n.m1083a(1, (int) c2619g.f1446a) && C2626n.m1083a(1, (int) c2619g.f1447b) && C2626n.m1083a(1, c2619g.f1448c) && C2626n.m1083a(1, c2619g.f1449d) && C2626n.m1085a((Object) 1, (Object) c2619g.f1450e) && C2626n.m1085a((Object) 1, (Object) c2619g.f1451f) && C2626n.m1085a((Object) 1, (Object) c2619g.f1452g) && C2626n.m1083a(1, c2619g.f1453h) && C2626n.m1085a((Object) 1, (Object) c2619g.f1454i) && C2626n.m1085a((Object) 1, (Object) c2619g.f1455j);
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f1445m) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo994a(C2624l c2624l) {
        c2624l.m1080a(this.f1446a, 1);
        c2624l.m1072a(this.f1447b, 2);
        c2624l.m1073a(this.f1448c, 3);
        c2624l.m1073a(this.f1449d, 4);
        c2624l.m1077a(this.f1450e, 5);
        c2624l.m1077a(this.f1451f, 6);
        c2624l.m1082a(this.f1452g, 7);
        c2624l.m1073a(this.f1453h, 8);
        c2624l.m1079a((Map) this.f1454i, 9);
        c2624l.m1079a((Map) this.f1455j, 10);
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo993a(C2623k c2623k) {
        try {
            this.f1446a = c2623k.m1055a(this.f1446a, 1, true);
            this.f1447b = c2623k.m1048a(this.f1447b, 2, true);
            this.f1448c = c2623k.m1049a(this.f1448c, 3, true);
            this.f1449d = c2623k.m1049a(this.f1449d, 4, true);
            this.f1450e = c2623k.m1058b(5, true);
            this.f1451f = c2623k.m1058b(6, true);
            if (f1443k == null) {
                f1443k = new byte[]{0};
            }
            this.f1452g = c2623k.m1059c(7, true);
            this.f1453h = c2623k.m1049a(this.f1453h, 8, true);
            if (f1444l == null) {
                HashMap hashMap = new HashMap();
                f1444l = hashMap;
                hashMap.put("", "");
            }
            this.f1454i = (Map) c2623k.m1053a((C2623k) f1444l, 9, true);
            if (f1444l == null) {
                HashMap hashMap2 = new HashMap();
                f1444l = hashMap2;
                hashMap2.put("", "");
            }
            this.f1455j = (Map) c2623k.m1053a((C2623k) f1444l, 10, true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("RequestPacket decode error " + C2618f.m1007a(this.f1452g));
            throw new RuntimeException(e);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo995a(StringBuilder sb, int i) {
        C2621i c2621i = new C2621i(sb, i);
        c2621i.m1026a(this.f1446a, "iVersion");
        c2621i.m1020a(this.f1447b, "cPacketType");
        c2621i.m1021a(this.f1448c, "iMessageType");
        c2621i.m1021a(this.f1449d, "iRequestId");
        c2621i.m1024a(this.f1450e, "sServantName");
        c2621i.m1024a(this.f1451f, "sFuncName");
        c2621i.m1028a(this.f1452g, "sBuffer");
        c2621i.m1021a(this.f1453h, "iTimeout");
        c2621i.m1025a((Map) this.f1454i, "context");
        c2621i.m1025a((Map) this.f1455j, "status");
    }
}
