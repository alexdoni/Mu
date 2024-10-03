package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.br */
/* loaded from: classes3.dex */
public final class C2610br extends AbstractC2625m {

    /* renamed from: i */
    static byte[] f1386i;

    /* renamed from: j */
    static Map<String, String> f1387j;

    /* renamed from: a */
    public byte f1388a = 0;

    /* renamed from: b */
    public int f1389b = 0;

    /* renamed from: c */
    public byte[] f1390c = null;

    /* renamed from: d */
    public String f1391d = "";

    /* renamed from: e */
    public long f1392e = 0;

    /* renamed from: f */
    public String f1393f = "";

    /* renamed from: g */
    public String f1394g = "";

    /* renamed from: h */
    public Map<String, String> f1395h = null;

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo994a(C2624l c2624l) {
        c2624l.m1072a(this.f1388a, 0);
        c2624l.m1073a(this.f1389b, 1);
        byte[] bArr = this.f1390c;
        if (bArr != null) {
            c2624l.m1082a(bArr, 2);
        }
        String str = this.f1391d;
        if (str != null) {
            c2624l.m1077a(str, 3);
        }
        c2624l.m1074a(this.f1392e, 4);
        String str2 = this.f1393f;
        if (str2 != null) {
            c2624l.m1077a(str2, 5);
        }
        String str3 = this.f1394g;
        if (str3 != null) {
            c2624l.m1077a(str3, 6);
        }
        Map<String, String> map = this.f1395h;
        if (map != null) {
            c2624l.m1079a((Map) map, 7);
        }
    }

    static {
        f1386i = r0;
        byte[] bArr = {0};
        HashMap hashMap = new HashMap();
        f1387j = hashMap;
        hashMap.put("", "");
    }

    @Override // com.tencent.bugly.proguard.AbstractC2625m
    /* renamed from: a */
    public final void mo993a(C2623k c2623k) {
        this.f1388a = c2623k.m1048a(this.f1388a, 0, true);
        this.f1389b = c2623k.m1049a(this.f1389b, 1, true);
        this.f1390c = c2623k.m1059c(2, false);
        this.f1391d = c2623k.m1058b(3, false);
        this.f1392e = c2623k.m1051a(this.f1392e, 4, false);
        this.f1393f = c2623k.m1058b(5, false);
        this.f1394g = c2623k.m1058b(6, false);
        this.f1395h = (Map) c2623k.m1053a((C2623k) f1387j, 7, false);
    }
}
