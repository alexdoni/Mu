package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import com.facebook.internal.security.CertificateUtil;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import java.lang.Thread;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.av */
/* loaded from: classes3.dex */
public final class C2587av implements Thread.UncaughtExceptionHandler {

    /* renamed from: h */
    private static String f1240h;

    /* renamed from: i */
    private static final Object f1241i = new Object();

    /* renamed from: a */
    protected final Context f1242a;

    /* renamed from: b */
    protected final C2584as f1243b;

    /* renamed from: c */
    protected final C2568ac f1244c;

    /* renamed from: d */
    protected final C2566aa f1245d;

    /* renamed from: e */
    protected Thread.UncaughtExceptionHandler f1246e;

    /* renamed from: f */
    protected Thread.UncaughtExceptionHandler f1247f;

    /* renamed from: g */
    protected boolean f1248g = false;

    /* renamed from: j */
    private int f1249j;

    public C2587av(Context context, C2584as c2584as, C2568ac c2568ac, C2566aa c2566aa) {
        this.f1242a = context;
        this.f1243b = c2584as;
        this.f1244c = c2568ac;
        this.f1245d = c2566aa;
    }

    /* renamed from: a */
    public final synchronized void m933a() {
        if (this.f1249j >= 10) {
            C2577al.m781a("java crash handler over %d, no need set.", 10);
            return;
        }
        this.f1248g = true;
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            if (getClass().getName().equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                return;
            }
            if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                C2577al.m781a("backup system java handler: %s", defaultUncaughtExceptionHandler.toString());
                this.f1247f = defaultUncaughtExceptionHandler;
                this.f1246e = defaultUncaughtExceptionHandler;
            } else {
                C2577al.m781a("backup java handler: %s", defaultUncaughtExceptionHandler.toString());
                this.f1246e = defaultUncaughtExceptionHandler;
            }
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.f1249j++;
        C2577al.m781a("registered java monitor: %s", toString());
    }

    /* renamed from: b */
    public final synchronized void m936b() {
        this.f1248g = false;
        C2577al.m781a("close java monitor!", new Object[0]);
        if ("bugly".equals(Thread.getDefaultUncaughtExceptionHandler().getClass().getName())) {
            C2577al.m781a("Java monitor to unregister: %s", toString());
            Thread.setDefaultUncaughtExceptionHandler(this.f1246e);
            this.f1249j--;
        }
    }

    /* renamed from: c */
    private static void m932c() {
        C2577al.m787e("current process die", new Object[0]);
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0117 A[Catch: all -> 0x0148, TryCatch #0 {all -> 0x0148, blocks: (B:31:0x0104, B:22:0x010f, B:26:0x0117, B:28:0x0127, B:37:0x0129), top: B:30:0x0104 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0127 A[Catch: all -> 0x0148, TryCatch #0 {all -> 0x0148, blocks: (B:31:0x0104, B:22:0x010f, B:26:0x0117, B:28:0x0127, B:37:0x0129), top: B:30:0x0104 }] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.tencent.bugly.crashreport.crash.CrashDetailBean m931b(java.lang.Thread r6, java.lang.Throwable r7, boolean r8, java.lang.String r9, byte[] r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 343
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2587av.m931b(java.lang.Thread, java.lang.Throwable, boolean, java.lang.String, byte[], boolean):com.tencent.bugly.crashreport.crash.CrashDetailBean");
    }

    /* renamed from: a */
    private static void m928a(CrashDetailBean crashDetailBean, Throwable th, boolean z) {
        String m927a;
        String name = th.getClass().getName();
        String m926a = m926a(th);
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(th.getStackTrace().length);
        objArr[1] = Boolean.valueOf(th.getCause() != null);
        C2577al.m787e("stack frame :%d, has cause %b", objArr);
        String str = "";
        String stackTraceElement = th.getStackTrace().length > 0 ? th.getStackTrace()[0].toString() : "";
        Throwable th2 = th;
        while (th2 != null && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 != null && th2 != th) {
            crashDetailBean.f931n = th2.getClass().getName();
            crashDetailBean.f932o = m926a(th2);
            if (th2.getStackTrace().length > 0) {
                crashDetailBean.f933p = th2.getStackTrace()[0].toString();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(CertificateUtil.DELIMITER);
            sb.append(m926a);
            sb.append("\n");
            sb.append(stackTraceElement);
            sb.append("\n......\nCaused by:\n");
            sb.append(crashDetailBean.f931n);
            sb.append(CertificateUtil.DELIMITER);
            sb.append(crashDetailBean.f932o);
            sb.append("\n");
            m927a = m927a(th2, C2585at.f1194h);
            sb.append(m927a);
            crashDetailBean.f934q = sb.toString();
        } else {
            crashDetailBean.f931n = name;
            if (C2585at.m904a().m918i() && z) {
                C2577al.m787e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
                str = " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
            }
            crashDetailBean.f932o = m926a + str;
            crashDetailBean.f933p = stackTraceElement;
            m927a = m927a(th, C2585at.f1194h);
            crashDetailBean.f934q = m927a;
        }
        crashDetailBean.f938u = C2581ap.m846c(crashDetailBean.f934q.getBytes());
        crashDetailBean.f943z.put(crashDetailBean.f892B, m927a);
    }

    /* renamed from: a */
    private static boolean m930a(Thread thread) {
        synchronized (f1241i) {
            if (f1240h != null && thread.getName().equals(f1240h)) {
                return true;
            }
            f1240h = thread.getName();
            return false;
        }
    }

    /* renamed from: a */
    public final void m935a(Thread thread, Throwable th, boolean z, String str, byte[] bArr, boolean z2) {
        if (z) {
            C2577al.m787e("Java Crash Happen cause by %s(%d)", thread.getName(), Long.valueOf(thread.getId()));
            if (m930a(thread)) {
                C2577al.m781a("this class has handled this exception", new Object[0]);
                if (this.f1247f != null) {
                    C2577al.m781a("call system handler", new Object[0]);
                    this.f1247f.uncaughtException(thread, th);
                } else {
                    m932c();
                }
            }
        } else {
            C2577al.m787e("Java Catch Happen", new Object[0]);
        }
        try {
            if (!this.f1248g) {
                C2577al.m785c("Java crash handler is disable. Just return.", new Object[0]);
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f1246e;
                    if (uncaughtExceptionHandler != null && m929a(uncaughtExceptionHandler)) {
                        C2577al.m787e("sys default last handle start!", new Object[0]);
                        this.f1246e.uncaughtException(thread, th);
                        C2577al.m787e("sys default last handle end!", new Object[0]);
                        return;
                    } else if (this.f1247f != null) {
                        C2577al.m787e("system handle start!", new Object[0]);
                        this.f1247f.uncaughtException(thread, th);
                        C2577al.m787e("system handle end!", new Object[0]);
                        return;
                    } else {
                        C2577al.m787e("crashreport last handle start!", new Object[0]);
                        m932c();
                        C2577al.m787e("crashreport last handle end!", new Object[0]);
                        return;
                    }
                }
                return;
            }
            if (!this.f1244c.m718b()) {
                C2577al.m786d("no remote but still store!", new Object[0]);
            }
            if (!this.f1244c.m719c().f873f && this.f1244c.m718b()) {
                C2577al.m787e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                C2584as.m871a(z ? "JAVA_CRASH" : "JAVA_CATCH", C2581ap.m816a(), this.f1245d.f1028d, thread.getName(), C2581ap.m822a(th), null);
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.f1246e;
                    if (uncaughtExceptionHandler2 != null && m929a(uncaughtExceptionHandler2)) {
                        C2577al.m787e("sys default last handle start!", new Object[0]);
                        this.f1246e.uncaughtException(thread, th);
                        C2577al.m787e("sys default last handle end!", new Object[0]);
                        return;
                    } else if (this.f1247f != null) {
                        C2577al.m787e("system handle start!", new Object[0]);
                        this.f1247f.uncaughtException(thread, th);
                        C2577al.m787e("system handle end!", new Object[0]);
                        return;
                    } else {
                        C2577al.m787e("crashreport last handle start!", new Object[0]);
                        m932c();
                        C2577al.m787e("crashreport last handle end!", new Object[0]);
                        return;
                    }
                }
                return;
            }
            CrashDetailBean m931b = m931b(thread, th, z, str, bArr, z2);
            if (m931b == null) {
                C2577al.m787e("pkg crash datas fail!", new Object[0]);
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler3 = this.f1246e;
                    if (uncaughtExceptionHandler3 != null && m929a(uncaughtExceptionHandler3)) {
                        C2577al.m787e("sys default last handle start!", new Object[0]);
                        this.f1246e.uncaughtException(thread, th);
                        C2577al.m787e("sys default last handle end!", new Object[0]);
                        return;
                    } else if (this.f1247f != null) {
                        C2577al.m787e("system handle start!", new Object[0]);
                        this.f1247f.uncaughtException(thread, th);
                        C2577al.m787e("system handle end!", new Object[0]);
                        return;
                    } else {
                        C2577al.m787e("crashreport last handle start!", new Object[0]);
                        m932c();
                        C2577al.m787e("crashreport last handle end!", new Object[0]);
                        return;
                    }
                }
                return;
            }
            C2584as.m871a(z ? "JAVA_CRASH" : "JAVA_CATCH", C2581ap.m816a(), this.f1245d.f1028d, thread.getName(), C2581ap.m822a(th), m931b);
            if (!this.f1243b.m900a(m931b, z)) {
                this.f1243b.m902b(m931b, z);
            }
            if (z) {
                this.f1243b.m898a(m931b);
            }
            if (z) {
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler4 = this.f1246e;
                if (uncaughtExceptionHandler4 != null && m929a(uncaughtExceptionHandler4)) {
                    C2577al.m787e("sys default last handle start!", new Object[0]);
                    this.f1246e.uncaughtException(thread, th);
                    C2577al.m787e("sys default last handle end!", new Object[0]);
                } else if (this.f1247f != null) {
                    C2577al.m787e("system handle start!", new Object[0]);
                    this.f1247f.uncaughtException(thread, th);
                    C2577al.m787e("system handle end!", new Object[0]);
                } else {
                    C2577al.m787e("crashreport last handle start!", new Object[0]);
                    m932c();
                    C2577al.m787e("crashreport last handle end!", new Object[0]);
                }
            }
        } catch (Throwable th2) {
            try {
                if (!C2577al.m782a(th2)) {
                    th2.printStackTrace();
                }
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler5 = this.f1246e;
                    if (uncaughtExceptionHandler5 != null && m929a(uncaughtExceptionHandler5)) {
                        C2577al.m787e("sys default last handle start!", new Object[0]);
                        this.f1246e.uncaughtException(thread, th);
                        C2577al.m787e("sys default last handle end!", new Object[0]);
                    } else if (this.f1247f != null) {
                        C2577al.m787e("system handle start!", new Object[0]);
                        this.f1247f.uncaughtException(thread, th);
                        C2577al.m787e("system handle end!", new Object[0]);
                    } else {
                        C2577al.m787e("crashreport last handle start!", new Object[0]);
                        m932c();
                        C2577al.m787e("crashreport last handle end!", new Object[0]);
                    }
                }
            } catch (Throwable th3) {
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler6 = this.f1246e;
                    if (uncaughtExceptionHandler6 != null && m929a(uncaughtExceptionHandler6)) {
                        C2577al.m787e("sys default last handle start!", new Object[0]);
                        this.f1246e.uncaughtException(thread, th);
                        C2577al.m787e("sys default last handle end!", new Object[0]);
                    } else if (this.f1247f != null) {
                        C2577al.m787e("system handle start!", new Object[0]);
                        this.f1247f.uncaughtException(thread, th);
                        C2577al.m787e("system handle end!", new Object[0]);
                    } else {
                        C2577al.m787e("crashreport last handle start!", new Object[0]);
                        m932c();
                        C2577al.m787e("crashreport last handle end!", new Object[0]);
                    }
                }
                throw th3;
            }
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        synchronized (f1241i) {
            m935a(thread, th, true, null, null, this.f1245d.f992Q);
        }
    }

    /* renamed from: a */
    private static boolean m929a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (uncaughtExceptionHandler == null) {
            return true;
        }
        String name = uncaughtExceptionHandler.getClass().getName();
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            if (name.equals(className) && "uncaughtException".equals(methodName)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public final synchronized void m934a(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.f873f != this.f1248g) {
                C2577al.m781a("java changed to %b", Boolean.valueOf(strategyBean.f873f));
                if (strategyBean.f873f) {
                    m933a();
                    return;
                }
                m936b();
            }
        }
    }

    /* renamed from: a */
    private static String m927a(Throwable th, int i) {
        if (th == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            if (th.getStackTrace() != null) {
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    if (i > 0 && sb.length() >= i) {
                        sb.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                        return sb.toString();
                    }
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                }
            }
        } catch (Throwable th2) {
            C2577al.m787e("gen stack error %s", th2.toString());
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static String m926a(Throwable th) {
        String message = th.getMessage();
        if (message == null) {
            return "";
        }
        if (message.length() <= 1000) {
            return message;
        }
        return message.substring(0, 1000) + "\n[Message over limit size:1000, has been cutted!]";
    }
}
