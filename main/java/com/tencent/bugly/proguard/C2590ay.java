package com.tencent.bugly.proguard;

import android.app.ActivityManager;
import android.content.Context;
import android.os.FileObserver;
import android.text.TextUtils;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ay */
/* loaded from: classes3.dex */
public final class C2590ay {

    /* renamed from: f */
    public static C2590ay f1257f;

    /* renamed from: b */
    public final ActivityManager f1259b;

    /* renamed from: c */
    final C2566aa f1260c;

    /* renamed from: d */
    final C2576ak f1261d;

    /* renamed from: e */
    String f1262e;

    /* renamed from: g */
    private final Context f1263g;

    /* renamed from: h */
    private final C2568ac f1264h;

    /* renamed from: i */
    private final C2584as f1265i;

    /* renamed from: k */
    private FileObserver f1267k;

    /* renamed from: m */
    private C2599bg f1269m;

    /* renamed from: n */
    private int f1270n;

    /* renamed from: a */
    public final AtomicBoolean f1258a = new AtomicBoolean(false);

    /* renamed from: j */
    private final Object f1266j = new Object();

    /* renamed from: l */
    private boolean f1268l = true;

    /* renamed from: o */
    private long f1271o = 0;

    /* renamed from: a */
    public static synchronized C2590ay m941a() {
        C2590ay c2590ay;
        synchronized (C2590ay.class) {
            c2590ay = f1257f;
        }
        return c2590ay;
    }

    public C2590ay(Context context, C2568ac c2568ac, C2566aa c2566aa, C2576ak c2576ak, C2584as c2584as) {
        Context m811a = C2581ap.m811a(context);
        this.f1263g = m811a;
        this.f1259b = (ActivityManager) m811a.getSystemService("activity");
        if (C2581ap.m844b(NativeCrashHandler.getDumpFilePath())) {
            this.f1262e = context.getDir("bugly", 0).getAbsolutePath();
        } else {
            this.f1262e = NativeCrashHandler.getDumpFilePath();
        }
        this.f1260c = c2566aa;
        this.f1261d = c2576ak;
        this.f1264h = c2568ac;
        this.f1265i = c2584as;
    }

    /* renamed from: a */
    private CrashDetailBean m940a(C2589ax c2589ax) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.f893C = C2567ab.m697j();
            crashDetailBean.f894D = C2567ab.m693f();
            crashDetailBean.f895E = C2567ab.m699l();
            crashDetailBean.f896F = this.f1260c.m670k();
            crashDetailBean.f897G = this.f1260c.m669j();
            crashDetailBean.f898H = this.f1260c.m671l();
            crashDetailBean.f899I = C2567ab.m687b(this.f1263g);
            crashDetailBean.f900J = C2567ab.m694g();
            crashDetailBean.f901K = C2567ab.m695h();
            crashDetailBean.f919b = 3;
            crashDetailBean.f922e = this.f1260c.m665g();
            crashDetailBean.f923f = this.f1260c.f1039o;
            crashDetailBean.f924g = this.f1260c.m675q();
            crashDetailBean.f930m = this.f1260c.m663f();
            crashDetailBean.f931n = "ANR_EXCEPTION";
            crashDetailBean.f932o = c2589ax.f1255f;
            crashDetailBean.f934q = c2589ax.f1256g;
            crashDetailBean.f910T = new HashMap();
            crashDetailBean.f910T.put("BUGLY_CR_01", c2589ax.f1254e);
            int indexOf = crashDetailBean.f934q != null ? crashDetailBean.f934q.indexOf("\n") : -1;
            crashDetailBean.f933p = indexOf > 0 ? crashDetailBean.f934q.substring(0, indexOf) : "GET_FAIL";
            crashDetailBean.f935r = c2589ax.f1252c;
            if (crashDetailBean.f934q != null) {
                crashDetailBean.f938u = C2581ap.m846c(crashDetailBean.f934q.getBytes());
            }
            crashDetailBean.f943z = c2589ax.f1251b;
            crashDetailBean.f891A = c2589ax.f1250a;
            crashDetailBean.f892B = "main(1)";
            crashDetailBean.f902L = this.f1260c.m677s();
            crashDetailBean.f925h = this.f1260c.m674p();
            crashDetailBean.f926i = this.f1260c.m650A();
            crashDetailBean.f939v = c2589ax.f1253d;
            crashDetailBean.f906P = this.f1260c.f1045u;
            crashDetailBean.f907Q = this.f1260c.f1001a;
            crashDetailBean.f908R = this.f1260c.m654a();
            crashDetailBean.f911U = this.f1260c.m684z();
            crashDetailBean.f912V = this.f1260c.f1048x;
            crashDetailBean.f913W = this.f1260c.m678t();
            crashDetailBean.f914X = this.f1260c.m683y();
            crashDetailBean.f942y = C2580ao.m800a();
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    /* renamed from: a */
    private static boolean m945a(String str, String str2, String str3) {
        TraceFileHelper.C2561a readTargetDumpInfo = TraceFileHelper.readTargetDumpInfo(str3, str, true);
        if (readTargetDumpInfo == null || readTargetDumpInfo.f952d == null || readTargetDumpInfo.f952d.isEmpty()) {
            C2577al.m787e("not found trace dump for %s", str3);
            return false;
        }
        StringBuilder sb = new StringBuilder(1024);
        String[] strArr = readTargetDumpInfo.f952d.get("main");
        if (strArr != null && strArr.length >= 3) {
            sb.append("\"main\" tid=");
            sb.append(strArr[2]);
            sb.append(" :\n");
            sb.append(strArr[0]);
            sb.append("\n");
            sb.append(strArr[1]);
            sb.append("\n\n");
        }
        for (Map.Entry<String, String[]> entry : readTargetDumpInfo.f952d.entrySet()) {
            if (!entry.getKey().equals("main") && entry.getValue() != null && entry.getValue().length >= 3) {
                sb.append("\"");
                sb.append(entry.getKey());
                sb.append("\" tid=");
                sb.append(entry.getValue()[2]);
                sb.append(" :\n");
                sb.append(entry.getValue()[0]);
                sb.append("\n");
                sb.append(entry.getValue()[1]);
                sb.append("\n\n");
            }
        }
        return C2578am.m793a(str2, sb.toString(), sb.length() * 2);
    }

    /* renamed from: a */
    private static String m942a(List<C2593ba> list, long j) {
        if (list == null || list.isEmpty()) {
            return "main thread stack not enable";
        }
        StringBuilder sb = new StringBuilder(4096);
        sb.append("\n>>>>> 以下为anr过程中主线程堆栈记录，可根据堆栈出现次数推测在该堆栈阻塞的时间，出现次数越多对anr贡献越大，越可能是造成anr的原因 >>>>>\n\n>>>>> Thread Stack Traces Records Start >>>>>\n");
        for (int i = 0; i < list.size(); i++) {
            C2593ba c2593ba = list.get(i);
            sb.append("Thread name:");
            sb.append(c2593ba.f1278a);
            sb.append("\n");
            long j2 = c2593ba.f1279b - j;
            String str = j2 <= 0 ? "before " : "after ";
            sb.append("Got ");
            sb.append(str);
            sb.append("anr:");
            sb.append(Math.abs(j2));
            sb.append("ms\n");
            sb.append(c2593ba.f1280c);
            sb.append("\n");
            if (sb.length() * 2 >= 101376) {
                break;
            }
        }
        sb.append("\n<<<<< Thread Stack Traces Records End <<<<<\n");
        return sb.toString();
    }

    /* renamed from: a */
    public final boolean m956a(boolean z) {
        boolean compareAndSet = this.f1258a.compareAndSet(!z, z);
        C2577al.m785c("tryChangeAnrState to %s, success:%s", Boolean.valueOf(z), Boolean.valueOf(compareAndSet));
        return compareAndSet;
    }

    /* renamed from: c */
    private synchronized void m946c() {
        if (m950e()) {
            C2577al.m786d("start when started!", new Object[0]);
            return;
        }
        FileObserver fileObserver = new FileObserver("/data/anr/") { // from class: com.tencent.bugly.proguard.ay.1
            @Override // android.os.FileObserver
            public final void onEvent(int i, String str) {
                if (str == null) {
                    return;
                }
                final String concat = "/data/anr/".concat(String.valueOf(str));
                C2577al.m786d("watching file %s", concat);
                if (!concat.contains("trace")) {
                    C2577al.m786d("not anr file %s", concat);
                } else {
                    C2590ay.this.f1261d.m774a(new Runnable() { // from class: com.tencent.bugly.proguard.ay.1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            C2590ay c2590ay = C2590ay.this;
                            String str2 = concat;
                            if (c2590ay.m956a(true)) {
                                try {
                                    C2577al.m785c("read trace first dump for create time!", new Object[0]);
                                    TraceFileHelper.C2561a readFirstDumpInfo = TraceFileHelper.readFirstDumpInfo(str2, false);
                                    long j = readFirstDumpInfo != null ? readFirstDumpInfo.f951c : -1L;
                                    if (j == -1) {
                                        C2577al.m786d("trace dump fail could not get time!", new Object[0]);
                                        j = System.currentTimeMillis();
                                    }
                                    if (c2590ay.m955a(j)) {
                                        return;
                                    }
                                    c2590ay.m954a(j, str2);
                                } catch (Throwable th) {
                                    if (!C2577al.m782a(th)) {
                                        th.printStackTrace();
                                    }
                                    C2577al.m787e("handle anr error %s", th.getClass().toString());
                                }
                            }
                        }
                    });
                }
            }
        };
        this.f1267k = fileObserver;
        try {
            fileObserver.startWatching();
            C2577al.m781a("start anr monitor!", new Object[0]);
            this.f1261d.m774a(new Runnable() { // from class: com.tencent.bugly.proguard.ay.2
                @Override // java.lang.Runnable
                public final void run() {
                    C2590ay.m943a(C2590ay.this);
                }
            });
        } catch (Throwable th) {
            this.f1267k = null;
            C2577al.m786d("start anr monitor failed!", new Object[0]);
            if (C2577al.m782a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: d */
    private synchronized void m948d() {
        if (!m950e()) {
            C2577al.m786d("close when closed!", new Object[0]);
            return;
        }
        try {
            this.f1267k.stopWatching();
            this.f1267k = null;
            C2577al.m786d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            C2577al.m786d("stop anr monitor failed!", new Object[0]);
            if (C2577al.m782a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: e */
    private synchronized boolean m950e() {
        return this.f1267k != null;
    }

    /* renamed from: c */
    private synchronized void m947c(boolean z) {
        if (z) {
            m952g();
        } else {
            m953h();
        }
    }

    /* renamed from: f */
    private synchronized boolean m951f() {
        return this.f1268l;
    }

    /* renamed from: d */
    private synchronized void m949d(boolean z) {
        if (this.f1268l != z) {
            C2577al.m781a("user change anr %b", Boolean.valueOf(z));
            this.f1268l = z;
        }
    }

    /* renamed from: b */
    public final void m958b(boolean z) {
        m949d(z);
        boolean m951f = m951f();
        C2568ac m710a = C2568ac.m710a();
        if (m710a != null) {
            m951f = m951f && m710a.m719c().f873f;
        }
        if (m951f != m950e()) {
            C2577al.m781a("anr changed to %b", Boolean.valueOf(m951f));
            m947c(m951f);
        }
    }

    /* renamed from: b */
    public final synchronized void m957b() {
        C2577al.m786d("customer decides whether to open or close.", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0069 A[Catch: all -> 0x0212, TryCatch #0 {all -> 0x0212, blocks: (B:3:0x0007, B:4:0x0017, B:10:0x0029, B:12:0x0049, B:14:0x004f, B:18:0x0059, B:21:0x0069, B:23:0x007a, B:27:0x0085, B:29:0x008e, B:30:0x0099, B:32:0x009d, B:33:0x00a2, B:35:0x00a6, B:36:0x00ab, B:38:0x00b9, B:39:0x00bd, B:42:0x00ee, B:44:0x010e, B:47:0x011b, B:49:0x0144, B:50:0x0172, B:51:0x0174, B:58:0x0184, B:59:0x0195, B:60:0x01a6, B:62:0x01b7, B:63:0x01c6, B:66:0x01e4, B:68:0x01ea, B:69:0x01ef, B:70:0x01f6, B:81:0x020e, B:83:0x01bf, B:88:0x019e, B:89:0x015c, B:90:0x019f, B:91:0x00e8, B:94:0x0091, B:96:0x0072, B:100:0x0211, B:72:0x01f7, B:74:0x01fb, B:75:0x0207, B:53:0x0175, B:55:0x0179, B:56:0x0181, B:6:0x0018, B:8:0x001c, B:9:0x0028), top: B:2:0x0007, inners: #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007a A[Catch: all -> 0x0212, TRY_LEAVE, TryCatch #0 {all -> 0x0212, blocks: (B:3:0x0007, B:4:0x0017, B:10:0x0029, B:12:0x0049, B:14:0x004f, B:18:0x0059, B:21:0x0069, B:23:0x007a, B:27:0x0085, B:29:0x008e, B:30:0x0099, B:32:0x009d, B:33:0x00a2, B:35:0x00a6, B:36:0x00ab, B:38:0x00b9, B:39:0x00bd, B:42:0x00ee, B:44:0x010e, B:47:0x011b, B:49:0x0144, B:50:0x0172, B:51:0x0174, B:58:0x0184, B:59:0x0195, B:60:0x01a6, B:62:0x01b7, B:63:0x01c6, B:66:0x01e4, B:68:0x01ea, B:69:0x01ef, B:70:0x01f6, B:81:0x020e, B:83:0x01bf, B:88:0x019e, B:89:0x015c, B:90:0x019f, B:91:0x00e8, B:94:0x0091, B:96:0x0072, B:100:0x0211, B:72:0x01f7, B:74:0x01fb, B:75:0x0207, B:53:0x0175, B:55:0x0179, B:56:0x0181, B:6:0x0018, B:8:0x001c, B:9:0x0028), top: B:2:0x0007, inners: #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0085 A[Catch: all -> 0x0212, TRY_ENTER, TryCatch #0 {all -> 0x0212, blocks: (B:3:0x0007, B:4:0x0017, B:10:0x0029, B:12:0x0049, B:14:0x004f, B:18:0x0059, B:21:0x0069, B:23:0x007a, B:27:0x0085, B:29:0x008e, B:30:0x0099, B:32:0x009d, B:33:0x00a2, B:35:0x00a6, B:36:0x00ab, B:38:0x00b9, B:39:0x00bd, B:42:0x00ee, B:44:0x010e, B:47:0x011b, B:49:0x0144, B:50:0x0172, B:51:0x0174, B:58:0x0184, B:59:0x0195, B:60:0x01a6, B:62:0x01b7, B:63:0x01c6, B:66:0x01e4, B:68:0x01ea, B:69:0x01ef, B:70:0x01f6, B:81:0x020e, B:83:0x01bf, B:88:0x019e, B:89:0x015c, B:90:0x019f, B:91:0x00e8, B:94:0x0091, B:96:0x0072, B:100:0x0211, B:72:0x01f7, B:74:0x01fb, B:75:0x0207, B:53:0x0175, B:55:0x0179, B:56:0x0181, B:6:0x0018, B:8:0x001c, B:9:0x0028), top: B:2:0x0007, inners: #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01b7 A[Catch: all -> 0x0212, TryCatch #0 {all -> 0x0212, blocks: (B:3:0x0007, B:4:0x0017, B:10:0x0029, B:12:0x0049, B:14:0x004f, B:18:0x0059, B:21:0x0069, B:23:0x007a, B:27:0x0085, B:29:0x008e, B:30:0x0099, B:32:0x009d, B:33:0x00a2, B:35:0x00a6, B:36:0x00ab, B:38:0x00b9, B:39:0x00bd, B:42:0x00ee, B:44:0x010e, B:47:0x011b, B:49:0x0144, B:50:0x0172, B:51:0x0174, B:58:0x0184, B:59:0x0195, B:60:0x01a6, B:62:0x01b7, B:63:0x01c6, B:66:0x01e4, B:68:0x01ea, B:69:0x01ef, B:70:0x01f6, B:81:0x020e, B:83:0x01bf, B:88:0x019e, B:89:0x015c, B:90:0x019f, B:91:0x00e8, B:94:0x0091, B:96:0x0072, B:100:0x0211, B:72:0x01f7, B:74:0x01fb, B:75:0x0207, B:53:0x0175, B:55:0x0179, B:56:0x0181, B:6:0x0018, B:8:0x001c, B:9:0x0028), top: B:2:0x0007, inners: #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01ea A[Catch: all -> 0x0212, TryCatch #0 {all -> 0x0212, blocks: (B:3:0x0007, B:4:0x0017, B:10:0x0029, B:12:0x0049, B:14:0x004f, B:18:0x0059, B:21:0x0069, B:23:0x007a, B:27:0x0085, B:29:0x008e, B:30:0x0099, B:32:0x009d, B:33:0x00a2, B:35:0x00a6, B:36:0x00ab, B:38:0x00b9, B:39:0x00bd, B:42:0x00ee, B:44:0x010e, B:47:0x011b, B:49:0x0144, B:50:0x0172, B:51:0x0174, B:58:0x0184, B:59:0x0195, B:60:0x01a6, B:62:0x01b7, B:63:0x01c6, B:66:0x01e4, B:68:0x01ea, B:69:0x01ef, B:70:0x01f6, B:81:0x020e, B:83:0x01bf, B:88:0x019e, B:89:0x015c, B:90:0x019f, B:91:0x00e8, B:94:0x0091, B:96:0x0072, B:100:0x0211, B:72:0x01f7, B:74:0x01fb, B:75:0x0207, B:53:0x0175, B:55:0x0179, B:56:0x0181, B:6:0x0018, B:8:0x001c, B:9:0x0028), top: B:2:0x0007, inners: #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01f7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01bf A[Catch: all -> 0x0212, TryCatch #0 {all -> 0x0212, blocks: (B:3:0x0007, B:4:0x0017, B:10:0x0029, B:12:0x0049, B:14:0x004f, B:18:0x0059, B:21:0x0069, B:23:0x007a, B:27:0x0085, B:29:0x008e, B:30:0x0099, B:32:0x009d, B:33:0x00a2, B:35:0x00a6, B:36:0x00ab, B:38:0x00b9, B:39:0x00bd, B:42:0x00ee, B:44:0x010e, B:47:0x011b, B:49:0x0144, B:50:0x0172, B:51:0x0174, B:58:0x0184, B:59:0x0195, B:60:0x01a6, B:62:0x01b7, B:63:0x01c6, B:66:0x01e4, B:68:0x01ea, B:69:0x01ef, B:70:0x01f6, B:81:0x020e, B:83:0x01bf, B:88:0x019e, B:89:0x015c, B:90:0x019f, B:91:0x00e8, B:94:0x0091, B:96:0x0072, B:100:0x0211, B:72:0x01f7, B:74:0x01fb, B:75:0x0207, B:53:0x0175, B:55:0x0179, B:56:0x0181, B:6:0x0018, B:8:0x001c, B:9:0x0028), top: B:2:0x0007, inners: #2, #3, #4 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m954a(long r19, java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 544
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2590ay.m954a(long, java.lang.String):void");
    }

    /* renamed from: g */
    private synchronized void m952g() {
        if (m950e()) {
            C2577al.m786d("start when started!", new Object[0]);
            return;
        }
        if (TextUtils.isEmpty(this.f1262e)) {
            return;
        }
        synchronized (this.f1266j) {
            C2599bg c2599bg = this.f1269m;
            if (c2599bg == null || !c2599bg.isAlive()) {
                C2599bg c2599bg2 = new C2599bg();
                this.f1269m = c2599bg2;
                boolean z = this.f1260c.f994S;
                c2599bg2.f1309b = z;
                C2577al.m785c("set record stack trace enable:".concat(String.valueOf(z)), new Object[0]);
                C2599bg c2599bg3 = this.f1269m;
                StringBuilder sb = new StringBuilder("Bugly-ThreadMonitor");
                int i = this.f1270n;
                this.f1270n = i + 1;
                sb.append(i);
                c2599bg3.setName(sb.toString());
                this.f1269m.m987b();
            }
        }
        FileObserver fileObserver = new FileObserver(this.f1262e) { // from class: com.tencent.bugly.proguard.ay.3
            @Override // android.os.FileObserver
            public final void onEvent(int i2, String str) {
                if (str == null) {
                    return;
                }
                C2577al.m786d("observe file, dir:%s fileName:%s", C2590ay.this.f1262e, str);
                if (!(str.startsWith("manual_bugly_trace_") && str.endsWith(".txt"))) {
                    C2577al.m785c("not manual trace file, ignore.", new Object[0]);
                    return;
                }
                if (!C2590ay.this.f1258a.get()) {
                    C2577al.m785c("proc is not in anr, just ignore", new Object[0]);
                    return;
                }
                if (C2590ay.this.f1260c.m654a()) {
                    C2577al.m785c("Found foreground anr, resend sigquit immediately.", new Object[0]);
                    NativeCrashHandler.getInstance().resendSigquit();
                    long m788a = C2578am.m788a(str, "manual_bugly_trace_", ".txt");
                    C2590ay.this.m954a(m788a, C2590ay.this.f1262e + RemoteSettings.FORWARD_SLASH_STRING + str);
                    C2577al.m785c("Finish handling one anr.", new Object[0]);
                    return;
                }
                C2577al.m785c("Found background anr, resend sigquit later.", new Object[0]);
                long m788a2 = C2578am.m788a(str, "manual_bugly_trace_", ".txt");
                C2590ay.this.m954a(m788a2, C2590ay.this.f1262e + RemoteSettings.FORWARD_SLASH_STRING + str);
                C2577al.m785c("Finish handling one anr, now resend sigquit.", new Object[0]);
                NativeCrashHandler.getInstance().resendSigquit();
            }
        };
        this.f1267k = fileObserver;
        try {
            fileObserver.startWatching();
            C2577al.m781a("startWatchingPrivateAnrDir! dumFilePath is %s", this.f1262e);
            this.f1261d.m774a(new Runnable() { // from class: com.tencent.bugly.proguard.ay.4
                @Override // java.lang.Runnable
                public final void run() {
                    C2590ay.m943a(C2590ay.this);
                }
            });
        } catch (Throwable th) {
            this.f1267k = null;
            C2577al.m786d("startWatchingPrivateAnrDir failed!", new Object[0]);
            if (C2577al.m782a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public final boolean m955a(long j) {
        if (Math.abs(j - this.f1271o) < 10000) {
            C2577al.m786d("should not process ANR too Fre in %dms", 10000);
            return true;
        }
        this.f1271o = j;
        return false;
    }

    /* renamed from: h */
    private synchronized void m953h() {
        if (!m950e()) {
            C2577al.m786d("close when closed!", new Object[0]);
            return;
        }
        synchronized (this.f1266j) {
            C2599bg c2599bg = this.f1269m;
            if (c2599bg != null) {
                c2599bg.m986a();
                this.f1269m = null;
            }
        }
        C2577al.m781a("stopWatchingPrivateAnrDir", new Object[0]);
        try {
            this.f1267k.stopWatching();
            this.f1267k = null;
            C2577al.m786d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            C2577al.m786d("stop anr monitor failed!", new Object[0]);
            if (C2577al.m782a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m943a(C2590ay c2590ay) {
        long currentTimeMillis = (C2585at.f1196j + System.currentTimeMillis()) - C2581ap.m836b();
        C2578am.m790a(c2590ay.f1262e, "bugly_trace_", ".txt", currentTimeMillis);
        C2578am.m790a(c2590ay.f1262e, "manual_bugly_trace_", ".txt", currentTimeMillis);
        C2578am.m790a(c2590ay.f1262e, "main_stack_record_", ".txt", currentTimeMillis);
        C2578am.m790a(c2590ay.f1262e, "main_stack_record_", ".txt.merged", currentTimeMillis);
    }
}
