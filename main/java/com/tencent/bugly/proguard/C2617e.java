package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.e */
/* loaded from: classes3.dex */
public final class C2617e extends C2616d {

    /* renamed from: h */
    static HashMap<String, byte[]> f1437h;

    /* renamed from: i */
    static HashMap<String, HashMap<String, byte[]>> f1438i;

    /* renamed from: g */
    protected C2619g f1439g;

    /* renamed from: j */
    private int f1440j;

    public C2617e() {
        C2619g c2619g = new C2619g();
        this.f1439g = c2619g;
        this.f1440j = 0;
        c2619g.f1446a = (short) 2;
    }

    @Override // com.tencent.bugly.proguard.C2616d, com.tencent.bugly.proguard.C2615c
    /* renamed from: a */
    public final <T> void mo998a(String str, T t) {
        if (str.startsWith(".")) {
            throw new IllegalArgumentException("put name can not startwith . , now is ".concat(String.valueOf(str)));
        }
        super.mo998a(str, (String) t);
    }

    @Override // com.tencent.bugly.proguard.C2616d
    /* renamed from: b */
    public final void mo1003b() {
        super.mo1003b();
        this.f1439g.f1446a = (short) 3;
    }

    @Override // com.tencent.bugly.proguard.C2616d, com.tencent.bugly.proguard.C2615c
    /* renamed from: a */
    public final byte[] mo1000a() {
        if (this.f1439g.f1446a == 2) {
            if (this.f1439g.f1450e.equals("")) {
                throw new IllegalArgumentException("servantName can not is null");
            }
            if (this.f1439g.f1451f.equals("")) {
                throw new IllegalArgumentException("funcName can not is null");
            }
        } else {
            if (this.f1439g.f1450e == null) {
                this.f1439g.f1450e = "";
            }
            if (this.f1439g.f1451f == null) {
                this.f1439g.f1451f = "";
            }
        }
        C2624l c2624l = new C2624l(0);
        c2624l.m1071a(this.f1431c);
        if (this.f1439g.f1446a == 2) {
            c2624l.m1079a((Map) this.f1429a, 0);
        } else {
            c2624l.m1079a((Map) this.f1434e, 0);
        }
        this.f1439g.f1452g = C2626n.m1087a(c2624l.f1462a);
        C2624l c2624l2 = new C2624l(0);
        c2624l2.m1071a(this.f1431c);
        this.f1439g.mo994a(c2624l2);
        byte[] m1087a = C2626n.m1087a(c2624l2.f1462a);
        int length = m1087a.length + 4;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.putInt(length).put(m1087a).flip();
        return allocate.array();
    }

    @Override // com.tencent.bugly.proguard.C2616d, com.tencent.bugly.proguard.C2615c
    /* renamed from: a */
    public final void mo999a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            C2623k c2623k = new C2623k(bArr, (byte) 0);
            c2623k.m1050a(this.f1431c);
            this.f1439g.mo993a(c2623k);
            if (this.f1439g.f1446a == 3) {
                C2623k c2623k2 = new C2623k(this.f1439g.f1452g);
                c2623k2.m1050a(this.f1431c);
                if (f1437h == null) {
                    HashMap<String, byte[]> hashMap = new HashMap<>();
                    f1437h = hashMap;
                    hashMap.put("", new byte[0]);
                }
                this.f1434e = c2623k2.m1054a((Map) f1437h, 0, false);
                return;
            }
            C2623k c2623k3 = new C2623k(this.f1439g.f1452g);
            c2623k3.m1050a(this.f1431c);
            if (f1438i == null) {
                f1438i = new HashMap<>();
                HashMap<String, byte[]> hashMap2 = new HashMap<>();
                hashMap2.put("", new byte[0]);
                f1438i.put("", hashMap2);
            }
            this.f1429a = c2623k3.m1054a((Map) f1438i, 0, false);
            this.f1430b = new HashMap<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public final void m1004b(String str) {
        this.f1439g.f1450e = str;
    }

    /* renamed from: c */
    public final void m1006c(String str) {
        this.f1439g.f1451f = str;
    }

    /* renamed from: c */
    public final void m1005c() {
        this.f1439g.f1449d = 1;
    }
}
