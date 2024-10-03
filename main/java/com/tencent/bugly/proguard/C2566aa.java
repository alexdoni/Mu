package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Process;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.aa */
/* loaded from: classes3.dex */
public final class C2566aa {

    /* renamed from: W */
    private static final Map<String, String> f974W = new HashMap();

    /* renamed from: aq */
    private static C2566aa f975aq = null;

    /* renamed from: D */
    public boolean f979D;

    /* renamed from: E */
    public String f980E;

    /* renamed from: F */
    public String f981F;

    /* renamed from: G */
    public String f982G;

    /* renamed from: H */
    public String f983H;

    /* renamed from: J */
    public boolean f985J;

    /* renamed from: O */
    public final SharedPreferences f990O;

    /* renamed from: P */
    public final SharedPreferences f991P;

    /* renamed from: X */
    private final Context f998X;

    /* renamed from: Y */
    private String f999Y;

    /* renamed from: Z */
    private String f1000Z;

    /* renamed from: aa */
    private String f1002aa;

    /* renamed from: aj */
    private String f1011aj;

    /* renamed from: c */
    public String f1027c;

    /* renamed from: d */
    public final String f1028d;

    /* renamed from: e */
    public String f1029e;

    /* renamed from: k */
    public final String f1035k;

    /* renamed from: o */
    public String f1039o;

    /* renamed from: p */
    public int f1040p;

    /* renamed from: q */
    public String f1041q;

    /* renamed from: r */
    public String f1042r;

    /* renamed from: s */
    public String f1043s;

    /* renamed from: v */
    public List<String> f1046v;

    /* renamed from: f */
    public boolean f1030f = true;

    /* renamed from: g */
    public final String f1031g = "com.tencent.bugly";

    /* renamed from: h */
    public String f1032h = "4.1.9.3";

    /* renamed from: i */
    public final String f1033i = "";

    /* renamed from: j */
    @Deprecated
    public final String f1034j = "";

    /* renamed from: l */
    public String f1036l = "unknown";

    /* renamed from: ab */
    private String f1003ab = "unknown";

    /* renamed from: ac */
    private String f1004ac = "";

    /* renamed from: m */
    public long f1037m = 0;

    /* renamed from: ad */
    private String f1005ad = null;

    /* renamed from: ae */
    private long f1006ae = -1;

    /* renamed from: af */
    private long f1007af = -1;

    /* renamed from: ag */
    private long f1008ag = -1;

    /* renamed from: ah */
    private String f1009ah = null;

    /* renamed from: ai */
    private String f1010ai = null;

    /* renamed from: ak */
    private Map<String, PlugInBean> f1012ak = null;

    /* renamed from: n */
    public boolean f1038n = false;

    /* renamed from: al */
    private String f1013al = null;

    /* renamed from: am */
    private Boolean f1014am = null;

    /* renamed from: an */
    private String f1015an = null;

    /* renamed from: t */
    public String f1044t = null;

    /* renamed from: u */
    public String f1045u = null;

    /* renamed from: ao */
    private Map<String, PlugInBean> f1016ao = null;

    /* renamed from: ap */
    private Map<String, PlugInBean> f1017ap = null;

    /* renamed from: w */
    public int f1047w = -1;

    /* renamed from: x */
    public int f1048x = -1;

    /* renamed from: ar */
    private final Map<String, String> f1018ar = new HashMap();

    /* renamed from: as */
    private final Map<String, String> f1019as = new HashMap();

    /* renamed from: at */
    private final Map<String, String> f1020at = new HashMap();

    /* renamed from: y */
    public String f1049y = "unknown";

    /* renamed from: z */
    public long f1050z = 0;

    /* renamed from: A */
    public long f976A = 0;

    /* renamed from: B */
    public long f977B = 0;

    /* renamed from: C */
    public long f978C = 0;

    /* renamed from: I */
    public boolean f984I = false;

    /* renamed from: K */
    public HashMap<String, String> f986K = new HashMap<>();

    /* renamed from: L */
    public List<String> f987L = new ArrayList();

    /* renamed from: M */
    public boolean f988M = false;

    /* renamed from: N */
    public InterfaceC2629q f989N = null;

    /* renamed from: Q */
    public boolean f992Q = true;

    /* renamed from: R */
    public boolean f993R = true;

    /* renamed from: S */
    public boolean f994S = false;

    /* renamed from: au */
    private final Object f1021au = new Object();

    /* renamed from: T */
    public final Object f995T = new Object();

    /* renamed from: av */
    private final Object f1022av = new Object();

    /* renamed from: aw */
    private final Object f1023aw = new Object();

    /* renamed from: U */
    public final Object f996U = new Object();

    /* renamed from: V */
    public final Object f997V = new Object();

    /* renamed from: ax */
    private final Object f1024ax = new Object();

    /* renamed from: ay */
    private final List<Integer> f1025ay = new ArrayList();

    /* renamed from: a */
    public final long f1001a = System.currentTimeMillis();

    /* renamed from: b */
    public final byte f1026b = 1;

    @Deprecated
    /* renamed from: n */
    public static String m649n() {
        return "";
    }

    private C2566aa(Context context) {
        this.f1011aj = null;
        this.f1039o = null;
        this.f1041q = null;
        this.f1042r = null;
        this.f1043s = null;
        this.f1046v = null;
        this.f979D = false;
        this.f980E = null;
        this.f981F = null;
        this.f982G = null;
        this.f983H = "";
        this.f985J = false;
        this.f998X = C2581ap.m811a(context);
        PackageInfo m1184b = C2638z.m1184b(context);
        if (m1184b != null) {
            try {
                String str = m1184b.versionName;
                this.f1039o = str;
                this.f980E = str;
                this.f981F = Integer.toString(m1184b.versionCode);
            } catch (Throwable th) {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
            }
        }
        this.f1027c = C2638z.m1180a(context);
        this.f1028d = C2638z.m1179a(Process.myPid());
        this.f1041q = C2638z.m1185c(context);
        this.f1035k = "Android " + C2567ab.m688b() + ",level " + C2567ab.m689c();
        Map<String, String> m1186d = C2638z.m1186d(context);
        if (m1186d != null) {
            try {
                this.f1046v = C2638z.m1181a(m1186d);
                String str2 = m1186d.get("BUGLY_APPID");
                if (str2 != null) {
                    this.f1042r = str2;
                    m656b("APP_ID", str2);
                }
                String str3 = m1186d.get("BUGLY_APP_VERSION");
                if (str3 != null) {
                    this.f1039o = str3;
                }
                String str4 = m1186d.get("BUGLY_APP_CHANNEL");
                if (str4 != null) {
                    this.f1043s = str4;
                }
                String str5 = m1186d.get("BUGLY_ENABLE_DEBUG");
                if (str5 != null) {
                    this.f979D = str5.equalsIgnoreCase("true");
                }
                String str6 = m1186d.get("com.tencent.rdm.uuid");
                if (str6 != null) {
                    this.f982G = str6;
                }
                String str7 = m1186d.get("BUGLY_APP_BUILD_NO");
                if (!TextUtils.isEmpty(str7)) {
                    this.f1040p = Integer.parseInt(str7);
                }
                String str8 = m1186d.get("BUGLY_AREA");
                if (str8 != null) {
                    this.f983H = str8;
                }
            } catch (Throwable th2) {
                if (!C2577al.m782a(th2)) {
                    th2.printStackTrace();
                }
            }
        }
        try {
            if (!context.getDatabasePath("bugly_db_").exists()) {
                this.f985J = true;
                C2577al.m785c("App is first time to be installed on the device.", new Object[0]);
            }
        } catch (Throwable th3) {
            if (C2628p.f1469c) {
                th3.printStackTrace();
            }
        }
        this.f990O = C2581ap.m812a("BUGLY_COMMON_VALUES", context);
        this.f991P = C2581ap.m812a("BUGLY_RESERVED_VALUES", context);
        this.f1011aj = C2567ab.m686a(context);
        m643E();
        C2577al.m785c("com info create end", new Object[0]);
    }

    /* renamed from: E */
    private void m643E() {
        try {
            for (Map.Entry<String, ?> entry : this.f991P.getAll().entrySet()) {
                C2577al.m785c("put reserved request data from sp, key:%s value:%s", entry.getKey(), entry.getValue());
                m647a(entry.getKey(), entry.getValue().toString(), false);
            }
            for (Map.Entry<String, String> entry2 : f974W.entrySet()) {
                C2577al.m785c("put reserved request data from cache, key:%s value:%s", entry2.getKey(), entry2.getValue());
                m647a(entry2.getKey(), entry2.getValue(), true);
            }
            f974W.clear();
        } catch (Throwable th) {
            C2577al.m784b(th);
        }
    }

    /* renamed from: a */
    public final boolean m654a() {
        boolean z = this.f1025ay.size() > 0;
        C2577al.m785c("isAppForeground:%s", Boolean.valueOf(z));
        return z;
    }

    /* renamed from: a */
    public final void m651a(int i, boolean z) {
        C2577al.m785c("setActivityForeState, hash:%s isFore:%s", Integer.valueOf(i), Boolean.valueOf(z));
        if (z) {
            this.f1025ay.add(Integer.valueOf(i));
        } else {
            this.f1025ay.remove(Integer.valueOf(i));
            this.f1025ay.remove((Object) 0);
        }
        InterfaceC2629q interfaceC2629q = this.f989N;
        if (interfaceC2629q != null) {
            interfaceC2629q.setNativeIsAppForeground(this.f1025ay.size() > 0);
        }
    }

    /* renamed from: a */
    public static synchronized C2566aa m646a(Context context) {
        C2566aa c2566aa;
        synchronized (C2566aa.class) {
            if (f975aq == null) {
                f975aq = new C2566aa(context);
            }
            c2566aa = f975aq;
        }
        return c2566aa;
    }

    /* renamed from: b */
    public static synchronized C2566aa m648b() {
        C2566aa c2566aa;
        synchronized (C2566aa.class) {
            c2566aa = f975aq;
        }
        return c2566aa;
    }

    /* renamed from: c */
    public final void m657c() {
        synchronized (this.f1021au) {
            this.f999Y = UUID.randomUUID().toString();
        }
    }

    /* renamed from: d */
    public final String m659d() {
        String str;
        synchronized (this.f1021au) {
            if (this.f999Y == null) {
                m657c();
            }
            str = this.f999Y;
        }
        return str;
    }

    /* renamed from: e */
    public final String m661e() {
        if (!C2581ap.m844b(this.f1029e)) {
            return this.f1029e;
        }
        return this.f1042r;
    }

    /* renamed from: f */
    public final String m663f() {
        String str;
        synchronized (this.f997V) {
            str = this.f1036l;
        }
        return str;
    }

    /* renamed from: g */
    public final String m665g() {
        String str = this.f1002aa;
        if (str != null) {
            return str;
        }
        String m851d = C2581ap.m851d("deviceId", null);
        this.f1002aa = m851d;
        if (m851d != null) {
            return m851d;
        }
        String m644F = m644F();
        this.f1002aa = m644F;
        if (TextUtils.isEmpty(m644F)) {
            this.f1002aa = m645G();
        }
        String str2 = this.f1002aa;
        if (str2 == null) {
            return "";
        }
        C2581ap.m848c("deviceId", str2);
        return this.f1002aa;
    }

    /* renamed from: F */
    private String m644F() {
        if (TextUtils.isEmpty(this.f1005ad)) {
            this.f1005ad = C2581ap.m851d("androidid", null);
        }
        return this.f1005ad;
    }

    /* renamed from: G */
    private static String m645G() {
        String uuid = UUID.randomUUID().toString();
        return !C2581ap.m844b(uuid) ? uuid.replaceAll("-", "") : uuid;
    }

    /* renamed from: a */
    public final void m652a(String str) {
        this.f1002aa = str;
        if (!TextUtils.isEmpty(str)) {
            C2581ap.m848c("deviceId", str);
        }
        synchronized (this.f1024ax) {
            this.f1019as.put("E8", str);
        }
    }

    /* renamed from: h */
    public final synchronized String m667h() {
        String str = this.f1000Z;
        if (str != null) {
            return str;
        }
        String m851d = C2581ap.m851d("deviceModel", null);
        this.f1000Z = m851d;
        if (m851d != null) {
            C2577al.m785c("collect device model from sp:%s", m851d);
            return this.f1000Z;
        }
        if (!this.f1038n) {
            C2577al.m785c("not allow collect device model", new Object[0]);
            return "fail";
        }
        String m685a = C2567ab.m685a();
        this.f1000Z = m685a;
        C2577al.m785c("collect device model:%s", m685a);
        C2581ap.m848c("deviceModel", this.f1000Z);
        return this.f1000Z;
    }

    /* renamed from: b */
    public final void m655b(String str) {
        C2577al.m781a("change deviceModel，old:%s new:%s", this.f1000Z, str);
        this.f1000Z = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        C2581ap.m848c("deviceModel", str);
    }

    /* renamed from: c */
    public final synchronized void m658c(String str) {
        this.f1003ab = String.valueOf(str);
    }

    /* renamed from: i */
    public final synchronized String m668i() {
        return this.f1004ac;
    }

    /* renamed from: d */
    public final synchronized void m660d(String str) {
        this.f1004ac = String.valueOf(str);
    }

    /* renamed from: j */
    public final long m669j() {
        if (this.f1006ae <= 0) {
            this.f1006ae = C2567ab.m692e();
        }
        return this.f1006ae;
    }

    /* renamed from: k */
    public final long m670k() {
        if (this.f1007af <= 0) {
            this.f1007af = C2567ab.m696i();
        }
        return this.f1007af;
    }

    /* renamed from: l */
    public final long m671l() {
        if (this.f1008ag <= 0) {
            this.f1008ag = C2567ab.m698k();
        }
        return this.f1008ag;
    }

    /* renamed from: m */
    public final String m672m() {
        if (!TextUtils.isEmpty(this.f1010ai)) {
            C2577al.m785c("get cpu type from so:%s", this.f1010ai);
            return this.f1010ai;
        }
        if (TextUtils.isEmpty(this.f1011aj)) {
            return "unknown";
        }
        C2577al.m785c("get cpu type from lib dir:%s", this.f1011aj);
        return this.f1011aj;
    }

    /* renamed from: e */
    public final void m662e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f1010ai = str.trim();
    }

    /* renamed from: o */
    public final String m673o() {
        try {
            Map<String, ?> all = this.f998X.getSharedPreferences("BuglySdkInfos", 0).getAll();
            if (!all.isEmpty()) {
                synchronized (this.f995T) {
                    for (Map.Entry<String, ?> entry : all.entrySet()) {
                        try {
                            this.f986K.put(entry.getKey(), entry.getValue().toString());
                        } catch (Throwable th) {
                            C2577al.m782a(th);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            C2577al.m782a(th2);
        }
        if (!this.f986K.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry2 : this.f986K.entrySet()) {
                sb.append("[");
                sb.append(entry2.getKey());
                sb.append(",");
                sb.append(entry2.getValue());
                sb.append("] ");
            }
            C2577al.m785c("SDK_INFO = %s", sb.toString());
            m656b("SDK_INFO", sb.toString());
            return sb.toString();
        }
        C2577al.m785c("SDK_INFO is empty", new Object[0]);
        return null;
    }

    /* renamed from: p */
    public final synchronized Map<String, PlugInBean> m674p() {
        Map<String, PlugInBean> map = this.f1012ak;
        if (map != null && map.size() > 0) {
            HashMap hashMap = new HashMap(this.f1012ak.size());
            hashMap.putAll(this.f1012ak);
            return hashMap;
        }
        return null;
    }

    /* renamed from: q */
    public final String m675q() {
        if (this.f1013al == null) {
            this.f1013al = C2567ab.m700m();
        }
        return this.f1013al;
    }

    /* renamed from: r */
    public final Boolean m676r() {
        if (this.f1014am == null) {
            this.f1014am = Boolean.valueOf(C2567ab.m704q());
        }
        return this.f1014am;
    }

    /* renamed from: s */
    public final String m677s() {
        if (this.f1015an == null) {
            String str = C2567ab.m701n();
            this.f1015an = str;
            C2577al.m781a("ROM ID: %s", str);
        }
        return this.f1015an;
    }

    /* renamed from: t */
    public final Map<String, String> m678t() {
        synchronized (this.f1022av) {
            if (this.f1018ar.size() <= 0) {
                return null;
            }
            return new HashMap(this.f1018ar);
        }
    }

    /* renamed from: f */
    public final String m664f(String str) {
        String remove;
        if (C2581ap.m844b(str)) {
            C2577al.m786d("key should not be empty %s", String.valueOf(str));
            return null;
        }
        synchronized (this.f1022av) {
            remove = this.f1018ar.remove(str);
        }
        return remove;
    }

    /* renamed from: u */
    public final void m679u() {
        synchronized (this.f1022av) {
            this.f1018ar.clear();
        }
    }

    /* renamed from: g */
    public final String m666g(String str) {
        String str2;
        if (C2581ap.m844b(str)) {
            C2577al.m786d("key should not be empty %s", String.valueOf(str));
            return null;
        }
        synchronized (this.f1022av) {
            str2 = this.f1018ar.get(str);
        }
        return str2;
    }

    /* renamed from: a */
    public final void m653a(String str, String str2) {
        if (C2581ap.m844b(str) || C2581ap.m844b(str2)) {
            C2577al.m786d("key&value should not be empty %s %s", String.valueOf(str), String.valueOf(str2));
            return;
        }
        synchronized (this.f1022av) {
            this.f1018ar.put(str, str2);
        }
    }

    /* renamed from: v */
    public final int m680v() {
        int size;
        synchronized (this.f1022av) {
            size = this.f1018ar.size();
        }
        return size;
    }

    /* renamed from: w */
    public final Set<String> m681w() {
        Set<String> keySet;
        synchronized (this.f1022av) {
            keySet = this.f1018ar.keySet();
        }
        return keySet;
    }

    /* renamed from: a */
    private void m647a(String str, String str2, boolean z) {
        if (C2581ap.m844b(str)) {
            C2577al.m786d("key should not be empty %s", str);
            return;
        }
        C2577al.m785c("putExtraRequestData key:%s value:%s save:%s", str, str2, Boolean.valueOf(z));
        synchronized (this.f1024ax) {
            if (TextUtils.isEmpty(str2)) {
                this.f1019as.remove(str);
                this.f991P.edit().remove(str).apply();
            } else {
                this.f1019as.put(str, str2);
                if (z) {
                    this.f991P.edit().putString(str, str2).apply();
                }
            }
        }
    }

    /* renamed from: x */
    public final Map<String, String> m682x() {
        synchronized (this.f1024ax) {
            if (this.f1019as.size() <= 0) {
                return null;
            }
            return new HashMap(this.f1019as);
        }
    }

    /* renamed from: b */
    public final void m656b(String str, String str2) {
        if (C2581ap.m844b(str) || C2581ap.m844b(str2)) {
            C2577al.m786d("server key&value should not be empty %s %s", String.valueOf(str), String.valueOf(str2));
            return;
        }
        synchronized (this.f1023aw) {
            this.f1020at.put(str, str2);
        }
    }

    /* renamed from: y */
    public final Map<String, String> m683y() {
        synchronized (this.f1023aw) {
            if (this.f1020at.size() <= 0) {
                return null;
            }
            return new HashMap(this.f1020at);
        }
    }

    /* renamed from: z */
    public final int m684z() {
        int i;
        synchronized (this.f996U) {
            i = this.f1047w;
        }
        return i;
    }

    /* renamed from: A */
    public final synchronized Map<String, PlugInBean> m650A() {
        Map<String, PlugInBean> map;
        map = this.f1016ao;
        Map<String, PlugInBean> map2 = this.f1017ap;
        if (map2 != null) {
            map.putAll(map2);
        }
        return map;
    }

    /* renamed from: B */
    public static int m640B() {
        return C2567ab.m689c();
    }

    @Deprecated
    /* renamed from: C */
    public static boolean m641C() {
        C2577al.m781a("Detect if the emulator is unavailable", new Object[0]);
        return false;
    }

    @Deprecated
    /* renamed from: D */
    public static boolean m642D() {
        C2577al.m781a("Detect if the device hook is unavailable", new Object[0]);
        return false;
    }
}
