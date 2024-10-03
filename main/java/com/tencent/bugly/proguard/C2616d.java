package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.d */
/* loaded from: classes3.dex */
public class C2616d extends C2615c {

    /* renamed from: e */
    protected HashMap<String, byte[]> f1434e = null;

    /* renamed from: g */
    private HashMap<String, Object> f1436g = new HashMap<>();

    /* renamed from: f */
    C2623k f1435f = new C2623k();

    @Override // com.tencent.bugly.proguard.C2615c
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo997a(String str) {
        super.mo997a(str);
    }

    /* renamed from: b */
    public void mo1003b() {
        this.f1434e = new HashMap<>();
    }

    @Override // com.tencent.bugly.proguard.C2615c
    /* renamed from: a */
    public <T> void mo998a(String str, T t) {
        if (this.f1434e == null) {
            super.mo998a(str, (String) t);
            return;
        }
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        }
        C2624l c2624l = new C2624l();
        c2624l.m1071a(this.f1431c);
        c2624l.m1076a(t, 0);
        this.f1434e.put(str, C2626n.m1087a(c2624l.f1462a));
    }

    /* renamed from: b */
    public final <T> T m1002b(String str, T t) throws C2592b {
        HashMap<String, byte[]> hashMap = this.f1434e;
        if (hashMap != null) {
            if (!hashMap.containsKey(str)) {
                return null;
            }
            if (this.f1436g.containsKey(str)) {
                return (T) this.f1436g.get(str);
            }
            try {
                this.f1435f.m1056a(this.f1434e.get(str));
                this.f1435f.m1050a(this.f1431c);
                T t2 = (T) this.f1435f.m1053a((C2623k) t, 0, true);
                if (t2 != null) {
                    m1001c(str, t2);
                }
                return t2;
            } catch (Exception e) {
                throw new C2592b(e);
            }
        }
        if (!this.f1429a.containsKey(str)) {
            return null;
        }
        if (this.f1436g.containsKey(str)) {
            return (T) this.f1436g.get(str);
        }
        byte[] bArr = new byte[0];
        Iterator<Map.Entry<String, byte[]>> it = this.f1429a.get(str).entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<String, byte[]> next = it.next();
            next.getKey();
            bArr = next.getValue();
        }
        try {
            this.f1435f.m1056a(bArr);
            this.f1435f.m1050a(this.f1431c);
            T t3 = (T) this.f1435f.m1053a((C2623k) t, 0, true);
            m1001c(str, t3);
            return t3;
        } catch (Exception e2) {
            throw new C2592b(e2);
        }
    }

    /* renamed from: c */
    private void m1001c(String str, Object obj) {
        this.f1436g.put(str, obj);
    }

    @Override // com.tencent.bugly.proguard.C2615c
    /* renamed from: a */
    public byte[] mo1000a() {
        if (this.f1434e != null) {
            C2624l c2624l = new C2624l(0);
            c2624l.m1071a(this.f1431c);
            c2624l.m1079a((Map) this.f1434e, 0);
            return C2626n.m1087a(c2624l.f1462a);
        }
        return super.mo1000a();
    }

    @Override // com.tencent.bugly.proguard.C2615c
    /* renamed from: a */
    public void mo999a(byte[] bArr) {
        try {
            super.mo999a(bArr);
        } catch (Exception unused) {
            this.f1435f.m1056a(bArr);
            this.f1435f.m1050a(this.f1431c);
            HashMap hashMap = new HashMap(1);
            hashMap.put("", new byte[0]);
            this.f1434e = this.f1435f.m1054a((Map) hashMap, 0, false);
        }
    }
}
