package com.tencent.bugly.proguard;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ae */
/* loaded from: classes3.dex */
public final class C2570ae {
    /* renamed from: a */
    public static C2613bu m726a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        C2613bu c2613bu = new C2613bu();
        c2613bu.f1414a = userInfoBean.f850e;
        c2613bu.f1418e = userInfoBean.f855j;
        c2613bu.f1417d = userInfoBean.f848c;
        c2613bu.f1416c = userInfoBean.f849d;
        c2613bu.f1421h = userInfoBean.f860o == 1;
        int i = userInfoBean.f847b;
        if (i == 1) {
            c2613bu.f1415b = (byte) 1;
        } else if (i == 2) {
            c2613bu.f1415b = (byte) 4;
        } else if (i == 3) {
            c2613bu.f1415b = (byte) 2;
        } else if (i == 4) {
            c2613bu.f1415b = (byte) 3;
        } else if (i == 8) {
            c2613bu.f1415b = (byte) 8;
        } else {
            if (userInfoBean.f847b < 10 || userInfoBean.f847b >= 20) {
                C2577al.m787e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.f847b));
                return null;
            }
            c2613bu.f1415b = (byte) userInfoBean.f847b;
        }
        c2613bu.f1419f = new HashMap();
        if (userInfoBean.f861p >= 0) {
            Map<String, String> map = c2613bu.f1419f;
            StringBuilder sb = new StringBuilder();
            sb.append(userInfoBean.f861p);
            map.put("C01", sb.toString());
        }
        if (userInfoBean.f862q >= 0) {
            Map<String, String> map2 = c2613bu.f1419f;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(userInfoBean.f862q);
            map2.put("C02", sb2.toString());
        }
        if (userInfoBean.f863r != null && userInfoBean.f863r.size() > 0) {
            for (Map.Entry<String, String> entry : userInfoBean.f863r.entrySet()) {
                c2613bu.f1419f.put("C03_" + entry.getKey(), entry.getValue());
            }
        }
        if (userInfoBean.f864s != null && userInfoBean.f864s.size() > 0) {
            for (Map.Entry<String, String> entry2 : userInfoBean.f864s.entrySet()) {
                c2613bu.f1419f.put("C04_" + entry2.getKey(), entry2.getValue());
            }
        }
        Map<String, String> map3 = c2613bu.f1419f;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(!userInfoBean.f857l);
        map3.put("A36", sb3.toString());
        Map<String, String> map4 = c2613bu.f1419f;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(userInfoBean.f852g);
        map4.put("F02", sb4.toString());
        Map<String, String> map5 = c2613bu.f1419f;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(userInfoBean.f853h);
        map5.put("F03", sb5.toString());
        c2613bu.f1419f.put("F04", userInfoBean.f855j);
        Map<String, String> map6 = c2613bu.f1419f;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(userInfoBean.f854i);
        map6.put("F05", sb6.toString());
        c2613bu.f1419f.put("F06", userInfoBean.f858m);
        Map<String, String> map7 = c2613bu.f1419f;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(userInfoBean.f856k);
        map7.put("F10", sb7.toString());
        C2577al.m785c("summary type %d vm:%d", Byte.valueOf(c2613bu.f1415b), Integer.valueOf(c2613bu.f1419f.size()));
        return c2613bu;
    }

    /* renamed from: a */
    public static <T extends AbstractC2625m> T m727a(byte[] bArr, Class<T> cls) {
        if (bArr != null && bArr.length > 0) {
            try {
                T newInstance = cls.newInstance();
                C2623k c2623k = new C2623k(bArr);
                c2623k.m1050a("utf-8");
                newInstance.mo993a(c2623k);
                return newInstance;
            } catch (Throwable th) {
                if (!C2577al.m784b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static C2609bq m724a(Context context, int i, byte[] bArr) {
        C2566aa m648b = C2566aa.m648b();
        StrategyBean m719c = C2568ac.m710a().m719c();
        if (m648b == null || m719c == null) {
            C2577al.m787e("Can not create request pkg for parameters is invalid.", new Object[0]);
            return null;
        }
        try {
            C2609bq c2609bq = new C2609bq();
            synchronized (m648b) {
                c2609bq.f1362a = m648b.f1026b;
                c2609bq.f1363b = m648b.m661e();
                c2609bq.f1364c = m648b.f1027c;
                c2609bq.f1365d = m648b.f1039o;
                c2609bq.f1366e = m648b.f1043s;
                c2609bq.f1367f = m648b.f1032h;
                c2609bq.f1368g = i;
                if (bArr == null) {
                    bArr = "".getBytes();
                }
                c2609bq.f1369h = bArr;
                c2609bq.f1370i = m648b.m667h();
                c2609bq.f1371j = m648b.f1035k;
                c2609bq.f1372k = new HashMap();
                c2609bq.f1373l = m648b.m659d();
                c2609bq.f1374m = m719c.f882o;
                c2609bq.f1376o = m648b.m665g();
                c2609bq.f1377p = C2567ab.m690c(context);
                c2609bq.f1378q = System.currentTimeMillis();
                c2609bq.f1380s = m648b.m668i();
                c2609bq.f1383v = m648b.m665g();
                c2609bq.f1384w = c2609bq.f1377p;
                m648b.getClass();
                c2609bq.f1375n = "com.tencent.bugly";
                c2609bq.f1372k.put("A26", m648b.m677s());
                Map<String, String> map = c2609bq.f1372k;
                StringBuilder sb = new StringBuilder();
                sb.append(C2566aa.m641C());
                map.put("A62", sb.toString());
                Map<String, String> map2 = c2609bq.f1372k;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(C2566aa.m642D());
                map2.put("A63", sb2.toString());
                Map<String, String> map3 = c2609bq.f1372k;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(m648b.f985J);
                map3.put("F11", sb3.toString());
                Map<String, String> map4 = c2609bq.f1372k;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(m648b.f984I);
                map4.put("F12", sb4.toString());
                c2609bq.f1372k.put("D3", m648b.f1041q);
                if (C2628p.f1468b != null) {
                    for (AbstractC2627o abstractC2627o : C2628p.f1468b) {
                        if (abstractC2627o.versionKey != null && abstractC2627o.version != null) {
                            c2609bq.f1372k.put(abstractC2627o.versionKey, abstractC2627o.version);
                        }
                    }
                }
                c2609bq.f1372k.put("G15", C2581ap.m851d("G15", ""));
                c2609bq.f1372k.put("G10", C2581ap.m851d("G10", ""));
                c2609bq.f1372k.put("D4", C2581ap.m851d("D4", "0"));
            }
            Map<String, String> m682x = m648b.m682x();
            if (m682x != null) {
                for (Map.Entry<String, String> entry : m682x.entrySet()) {
                    if (!TextUtils.isEmpty(entry.getValue())) {
                        c2609bq.f1372k.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            return c2609bq;
        } catch (Throwable th) {
            if (!C2577al.m784b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m729a(Object obj) {
        try {
            C2617e c2617e = new C2617e();
            c2617e.mo1003b();
            c2617e.mo997a("utf-8");
            c2617e.m1005c();
            c2617e.m1004b("RqdServer");
            c2617e.m1006c("sync");
            c2617e.mo998a("detail", (String) obj);
            return c2617e.mo1000a();
        } catch (Throwable th) {
            if (C2577al.m784b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static C2610br m725a(byte[] bArr) {
        if (bArr != null) {
            try {
                C2617e c2617e = new C2617e();
                c2617e.mo1003b();
                c2617e.mo997a("utf-8");
                c2617e.mo999a(bArr);
                Object b = c2617e.m1002b("detail", new C2610br());
                if (C2610br.class.isInstance(b)) {
                    return (C2610br) C2610br.class.cast(b);
                }
                return null;
            } catch (Throwable th) {
                if (!C2577al.m784b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static byte[] m728a(AbstractC2625m abstractC2625m) {
        try {
            C2624l c2624l = new C2624l();
            c2624l.m1071a("utf-8");
            abstractC2625m.mo994a(c2624l);
            byte[] bArr = new byte[c2624l.f1462a.position()];
            System.arraycopy(c2624l.f1462a.array(), 0, bArr, 0, c2624l.f1462a.position());
            return bArr;
        } catch (Throwable th) {
            if (C2577al.m784b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
