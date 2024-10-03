package com.tencent.bugly.proguard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.c */
/* loaded from: classes3.dex */
class C2615c {

    /* renamed from: a */
    protected HashMap<String, HashMap<String, byte[]>> f1429a = new HashMap<>();

    /* renamed from: b */
    protected HashMap<String, Object> f1430b = new HashMap<>();

    /* renamed from: e */
    private HashMap<String, Object> f1433e = new HashMap<>();

    /* renamed from: c */
    protected String f1431c = "GBK";

    /* renamed from: d */
    C2623k f1432d = new C2623k();

    /* renamed from: a */
    public void mo997a(String str) {
        this.f1431c = str;
    }

    /* renamed from: a */
    public <T> void mo998a(String str, T t) {
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
        byte[] m1087a = C2626n.m1087a(c2624l.f1462a);
        HashMap<String, byte[]> hashMap = new HashMap<>(1);
        ArrayList arrayList = new ArrayList(1);
        m996a((ArrayList<String>) arrayList, t);
        hashMap.put(C2565a.m639a(arrayList), m1087a);
        this.f1433e.remove(str);
        this.f1429a.put(str, hashMap);
    }

    /* renamed from: a */
    private static void m996a(ArrayList<String> arrayList, Object obj) {
        while (true) {
            if (obj.getClass().isArray()) {
                if (!obj.getClass().getComponentType().toString().equals("byte")) {
                    throw new IllegalArgumentException("only byte[] is supported");
                }
                if (Array.getLength(obj) > 0) {
                    arrayList.add("java.util.List");
                    obj = Array.get(obj, 0);
                } else {
                    arrayList.add("Array");
                    arrayList.add("?");
                    return;
                }
            } else {
                if (obj instanceof Array) {
                    throw new IllegalArgumentException("can not support Array, please use List");
                }
                if (obj instanceof List) {
                    arrayList.add("java.util.List");
                    List list = (List) obj;
                    if (list.size() > 0) {
                        obj = list.get(0);
                    } else {
                        arrayList.add("?");
                        return;
                    }
                } else if (obj instanceof Map) {
                    arrayList.add("java.util.Map");
                    Map map = (Map) obj;
                    if (map.size() > 0) {
                        Object next = map.keySet().iterator().next();
                        obj = map.get(next);
                        arrayList.add(next.getClass().getName());
                    } else {
                        arrayList.add("?");
                        arrayList.add("?");
                        return;
                    }
                } else {
                    arrayList.add(obj.getClass().getName());
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public byte[] mo1000a() {
        C2624l c2624l = new C2624l(0);
        c2624l.m1071a(this.f1431c);
        c2624l.m1079a((Map) this.f1429a, 0);
        return C2626n.m1087a(c2624l.f1462a);
    }

    /* renamed from: a */
    public void mo999a(byte[] bArr) {
        this.f1432d.m1056a(bArr);
        this.f1432d.m1050a(this.f1431c);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.f1429a = this.f1432d.m1054a((Map) hashMap, 0, false);
    }
}
