package com.unity3d.player;

import java.lang.reflect.Method;
import java.util.HashMap;

/* renamed from: com.unity3d.player.p */
/* loaded from: classes3.dex */
final class C2720p {

    /* renamed from: a */
    private HashMap f1849a = new HashMap();

    /* renamed from: b */
    private Class f1850b;

    /* renamed from: c */
    private Object f1851c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.unity3d.player.p$a */
    /* loaded from: classes3.dex */
    public class a {

        /* renamed from: a */
        public Class[] f1852a;

        /* renamed from: b */
        public Method f1853b = null;

        public a(Class[] clsArr) {
            this.f1852a = clsArr;
        }
    }

    public C2720p(Class cls, Object obj) {
        this.f1850b = cls;
        this.f1851c = obj;
    }

    /* renamed from: a */
    private void m1363a(String str, a aVar) {
        try {
            aVar.f1853b = this.f1850b.getMethod(str, aVar.f1852a);
        } catch (Exception e) {
            C2711g.Log(6, "Exception while trying to get method " + str + ". " + e.getLocalizedMessage());
            aVar.f1853b = null;
        }
    }

    /* renamed from: a */
    public final Object m1364a(String str, Object... objArr) {
        StringBuilder sb;
        Object obj = null;
        if (this.f1849a.containsKey(str)) {
            a aVar = (a) this.f1849a.get(str);
            if (aVar.f1853b == null) {
                m1363a(str, aVar);
            }
            if (aVar.f1853b != null) {
                try {
                    obj = objArr.length == 0 ? aVar.f1853b.invoke(this.f1851c, new Object[0]) : aVar.f1853b.invoke(this.f1851c, objArr);
                } catch (Exception e) {
                    C2711g.Log(6, "Error trying to call delegated method " + str + ". " + e.getLocalizedMessage());
                }
                return obj;
            }
            sb = new StringBuilder("Unable to create method: ");
        } else {
            sb = new StringBuilder("No definition for method ");
            sb.append(str);
            str = " can be found";
        }
        sb.append(str);
        C2711g.Log(6, sb.toString());
        return null;
    }

    /* renamed from: a */
    public final void m1365a(String str, Class[] clsArr) {
        this.f1849a.put(str, new a(clsArr));
    }
}
