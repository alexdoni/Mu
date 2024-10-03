package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C2566aa;
import com.tencent.bugly.proguard.C2567ab;
import com.tencent.bugly.proguard.C2568ac;
import com.tencent.bugly.proguard.C2576ak;
import com.tencent.bugly.proguard.C2577al;
import com.tencent.bugly.proguard.C2581ap;
import com.tencent.bugly.proguard.C2584as;
import com.tencent.bugly.proguard.C2585at;
import com.tencent.bugly.proguard.C2596bd;
import com.tencent.bugly.proguard.C2597be;
import com.tencent.bugly.proguard.InterfaceC2629q;
import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/* compiled from: BUGLY */
/* loaded from: classes3.dex */
public class NativeCrashHandler implements InterfaceC2629q {

    /* renamed from: a */
    static String f958a = null;

    /* renamed from: b */
    private static NativeCrashHandler f959b = null;

    /* renamed from: c */
    private static int f960c = 1;

    /* renamed from: n */
    private static boolean f961n = true;

    /* renamed from: d */
    private final Context f962d;

    /* renamed from: e */
    private final C2566aa f963e;

    /* renamed from: f */
    private final C2576ak f964f;

    /* renamed from: g */
    private NativeExceptionHandler f965g;

    /* renamed from: h */
    private final boolean f966h;

    /* renamed from: i */
    private boolean f967i = false;

    /* renamed from: j */
    private boolean f968j = false;

    /* renamed from: k */
    private boolean f969k = false;

    /* renamed from: l */
    private boolean f970l = false;

    /* renamed from: m */
    private C2584as f971m;

    private native String getProperties(String str);

    private native String getSoCpuAbi();

    protected native boolean appendNativeLog(String str, String str2, String str3);

    protected native boolean appendWholeNativeLog(String str);

    protected native String getNativeKeyValueList();

    protected native String getNativeLog();

    protected native boolean putNativeKeyValue(String str, String str2);

    protected native String regist(String str, boolean z, int i);

    protected native String removeNativeKeyValue(String str);

    protected native void setNativeInfo(int i, String str);

    protected native void testCrash();

    protected native String unregist();

    private NativeCrashHandler(Context context, C2566aa c2566aa, C2584as c2584as, C2576ak c2576ak, boolean z, String str) {
        this.f962d = C2581ap.m811a(context);
        if (C2581ap.m844b(f958a)) {
            try {
                if (C2581ap.m844b(str)) {
                    str = context.getDir("bugly", 0).getAbsolutePath();
                }
            } catch (Throwable unused) {
                str = "/data/data/" + C2566aa.m646a(context).f1027c + "/app_bugly";
            }
            f958a = str;
        }
        this.f971m = c2584as;
        this.f963e = c2566aa;
        this.f964f = c2576ak;
        this.f966h = z;
        this.f965g = new C2596bd(context, c2566aa, c2584as, C2568ac.m710a());
    }

    public static synchronized NativeCrashHandler getInstance(Context context, C2566aa c2566aa, C2584as c2584as, C2568ac c2568ac, C2576ak c2576ak, boolean z, String str) {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            if (f959b == null) {
                f959b = new NativeCrashHandler(context, c2566aa, c2584as, c2576ak, z, str);
            }
            nativeCrashHandler = f959b;
        }
        return nativeCrashHandler;
    }

    public static synchronized NativeCrashHandler getInstance() {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            nativeCrashHandler = f959b;
        }
        return nativeCrashHandler;
    }

    public static synchronized String getDumpFilePath() {
        String str;
        synchronized (NativeCrashHandler.class) {
            str = f958a;
        }
        return str;
    }

    public static synchronized void setDumpFilePath(String str) {
        synchronized (NativeCrashHandler.class) {
            f958a = str;
        }
    }

    public static void setShouldHandleInJava(boolean z) {
        f961n = z;
        NativeCrashHandler nativeCrashHandler = f959b;
        if (nativeCrashHandler != null) {
            nativeCrashHandler.m630a(999, String.valueOf(z));
        }
    }

    public static boolean isShouldHandleInJava() {
        return f961n;
    }

    public String getRunningCpuAbi() {
        try {
            return getSoCpuAbi();
        } catch (Throwable unused) {
            C2577al.m786d("get so cpu abi failed，please upgrade bugly so version", new Object[0]);
            return "";
        }
    }

    /* renamed from: a */
    private synchronized void m628a(boolean z) {
        if (this.f969k) {
            C2577al.m786d("[Native] Native crash report has already registered.", new Object[0]);
            return;
        }
        if (this.f968j) {
            try {
                String regist = regist(f958a, z, f960c);
                if (regist != null) {
                    C2577al.m781a("[Native] Native Crash Report enable.", new Object[0]);
                    this.f963e.f1045u = regist;
                    String concat = "-".concat(this.f963e.f1045u);
                    if (!C2585at.f1189b && !this.f963e.f1032h.contains(concat)) {
                        C2566aa c2566aa = this.f963e;
                        c2566aa.f1032h = c2566aa.f1032h.concat("-").concat(this.f963e.f1045u);
                    }
                    C2577al.m781a("comInfo.sdkVersion %s", this.f963e.f1032h);
                    this.f969k = true;
                    String runningCpuAbi = getRunningCpuAbi();
                    if (!TextUtils.isEmpty(runningCpuAbi)) {
                        this.f963e.m662e(runningCpuAbi);
                    }
                    return;
                }
            } catch (Throwable unused) {
                C2577al.m785c("[Native] Failed to load Bugly SO file.", new Object[0]);
            }
        } else if (this.f967i) {
            try {
                Class[] clsArr = {String.class, String.class, Integer.TYPE, Integer.TYPE};
                Object[] objArr = new Object[4];
                objArr[0] = f958a;
                objArr[1] = C2567ab.m691d();
                objArr[2] = Integer.valueOf(z ? 1 : 5);
                objArr[3] = 1;
                String str = (String) C2581ap.m814a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler2", clsArr, objArr);
                if (str == null) {
                    Class[] clsArr2 = {String.class, String.class, Integer.TYPE};
                    C2566aa.m648b();
                    str = (String) C2581ap.m814a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler", clsArr2, new Object[]{f958a, C2567ab.m691d(), Integer.valueOf(C2566aa.m640B())});
                }
                if (str != null) {
                    this.f969k = true;
                    this.f963e.f1045u = str;
                    C2581ap.m814a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", new Class[]{Boolean.TYPE}, new Object[]{Boolean.TRUE});
                    C2581ap.m814a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "setLogMode", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(z ? 1 : 5)});
                    String runningCpuAbi2 = getRunningCpuAbi();
                    if (!TextUtils.isEmpty(runningCpuAbi2)) {
                        this.f963e.m662e(runningCpuAbi2);
                    }
                    return;
                }
            } catch (Throwable unused2) {
            }
        }
        this.f968j = false;
        this.f967i = false;
    }

    public synchronized void startNativeMonitor() {
        if (!this.f968j && !this.f967i) {
            boolean z = !C2581ap.m844b(this.f963e.f1044t);
            if (C2585at.f1189b) {
                boolean m632a = m632a(z ? this.f963e.f1044t : "Bugly_Native", z);
                this.f968j = m632a;
                if (!m632a && !z) {
                    this.f967i = m632a("NativeRQD", false);
                }
            } else {
                String str = "Bugly_Native";
                String str2 = this.f963e.f1044t;
                if (z) {
                    str = str2;
                } else {
                    this.f963e.getClass();
                }
                this.f968j = m632a(str, z);
            }
            if (this.f968j || this.f967i) {
                m628a(this.f966h);
                setNativeAppVersion(this.f963e.f1039o);
                setNativeAppChannel(this.f963e.f1043s);
                setNativeAppPackage(this.f963e.f1027c);
                setNativeUserId(this.f963e.m663f());
                setNativeIsAppForeground(this.f963e.m654a());
                setNativeLaunchTime(this.f963e.f1001a);
                return;
            }
            return;
        }
        m628a(this.f966h);
    }

    public void checkUploadRecordCrash() {
        this.f964f.m774a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.1
            @Override // java.lang.Runnable
            public final void run() {
                int i;
                if (C2581ap.m830a(NativeCrashHandler.this.f962d, "native_record_lock")) {
                    if (!NativeCrashHandler.f961n) {
                        NativeCrashHandler.this.m630a(999, JsonSerializer.False);
                    }
                    CrashDetailBean m965a = C2597be.m965a(NativeCrashHandler.this.f962d, NativeCrashHandler.f958a, NativeCrashHandler.this.f965g);
                    if (m965a != null) {
                        C2577al.m781a("[Native] Get crash from native record.", new Object[0]);
                        if (!NativeCrashHandler.this.f971m.m900a(m965a, true)) {
                            NativeCrashHandler.this.f971m.m902b(m965a, false);
                        }
                        C2597be.m973a(false, NativeCrashHandler.f958a);
                    }
                    final NativeCrashHandler nativeCrashHandler = NativeCrashHandler.this;
                    long m836b = C2581ap.m836b() - C2585at.f1196j;
                    long m836b2 = C2581ap.m836b() + 86400000;
                    File file = new File(NativeCrashHandler.f958a);
                    if (file.exists() && file.isDirectory()) {
                        try {
                            File[] listFiles = file.listFiles();
                            if (listFiles != null && listFiles.length != 0) {
                                Arrays.sort(listFiles, new Comparator<File>() { // from class: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.2
                                    @Override // java.util.Comparator
                                    public final /* synthetic */ int compare(File file2, File file3) {
                                        return Long.compare(file3.lastModified(), file2.lastModified());
                                    }
                                });
                                int length = listFiles.length;
                                long j = 0;
                                int i2 = 0;
                                int i3 = 0;
                                int i4 = 0;
                                while (i2 < length) {
                                    File file2 = listFiles[i2];
                                    long lastModified = file2.lastModified();
                                    j += file2.length();
                                    if (lastModified >= m836b && lastModified < m836b2 && j < C2585at.f1195i) {
                                        i = length;
                                        i2++;
                                        length = i;
                                    }
                                    i = length;
                                    C2577al.m781a("[Native] Delete record file: %s", file2.getAbsolutePath());
                                    i3++;
                                    if (file2.delete()) {
                                        i4++;
                                    }
                                    i2++;
                                    length = i;
                                }
                                C2577al.m785c("[Native] Number of record files overdue: %d, has deleted: %d", Integer.valueOf(i3), Integer.valueOf(i4));
                            }
                        } catch (Throwable th) {
                            C2577al.m782a(th);
                        }
                    }
                    C2581ap.m842b(NativeCrashHandler.this.f962d, "native_record_lock");
                    return;
                }
                C2577al.m781a("[Native] Failed to lock file for handling native crash record.", new Object[0]);
            }
        });
    }

    /* renamed from: a */
    private static boolean m632a(String str, boolean z) {
        boolean z2;
        try {
            C2577al.m781a("[Native] Trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                C2577al.m781a("[Native] Successfully loaded SO: %s", str);
                return true;
            } catch (Throwable th) {
                th = th;
                z2 = true;
                C2577al.m786d(th.getMessage(), new Object[0]);
                C2577al.m786d("[Native] Failed to load so: %s", str);
                return z2;
            }
        } catch (Throwable th2) {
            th = th2;
            z2 = false;
        }
    }

    /* renamed from: c */
    private synchronized void m637c() {
        if (!this.f969k) {
            C2577al.m786d("[Native] Native crash report has already unregistered.", new Object[0]);
            return;
        }
        try {
            if (unregist() != null) {
                C2577al.m781a("[Native] Successfully closed native crash report.", new Object[0]);
                this.f969k = false;
                return;
            }
        } catch (Throwable unused) {
            C2577al.m785c("[Native] Failed to close native crash report.", new Object[0]);
        }
        try {
            C2581ap.m814a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", new Class[]{Boolean.TYPE}, new Object[]{Boolean.FALSE});
            this.f969k = false;
            C2577al.m781a("[Native] Successfully closed native crash report.", new Object[0]);
        } catch (Throwable unused2) {
            C2577al.m785c("[Native] Failed to close native crash report.", new Object[0]);
            this.f968j = false;
            this.f967i = false;
        }
    }

    public void testNativeCrash() {
        if (!this.f968j) {
            C2577al.m786d("[Native] Bugly SO file has not been load.", new Object[0]);
        } else {
            testCrash();
        }
    }

    public void testNativeCrash(boolean z, boolean z2, boolean z3) {
        m630a(16, String.valueOf(z));
        m630a(17, String.valueOf(z2));
        m630a(18, String.valueOf(z3));
        testNativeCrash();
    }

    public void dumpAnrNativeStack() {
        m630a(19, "1");
    }

    public void resendSigquit() {
        m630a(20, "");
    }

    public void unBlockSigquit(boolean z) {
        if (z) {
            m630a(21, "true");
        } else {
            m630a(21, JsonSerializer.False);
        }
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.f965g;
    }

    public void removeEmptyNativeRecordFiles() {
        C2597be.m978c(f958a);
    }

    /* renamed from: b */
    private synchronized void m635b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            m637c();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.f970l;
    }

    /* renamed from: c */
    private synchronized void m638c(boolean z) {
        if (this.f970l != z) {
            C2577al.m781a("user change native %b", Boolean.valueOf(z));
            this.f970l = z;
        }
    }

    public synchronized void setUserOpened(boolean z) {
        m638c(z);
        boolean isUserOpened = isUserOpened();
        C2568ac m710a = C2568ac.m710a();
        if (m710a != null) {
            isUserOpened = isUserOpened && m710a.m719c().f873f;
        }
        if (isUserOpened != this.f969k) {
            C2577al.m781a("native changed to %b", Boolean.valueOf(isUserOpened));
            m635b(isUserOpened);
        }
    }

    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.f873f != this.f969k) {
                C2577al.m786d("server native changed to %b", Boolean.valueOf(strategyBean.f873f));
            }
        }
        boolean z = C2568ac.m710a().m719c().f873f && this.f970l;
        if (z != this.f969k) {
            C2577al.m781a("native changed to %b", Boolean.valueOf(z));
            m635b(z);
        }
    }

    @Override // com.tencent.bugly.proguard.InterfaceC2629q
    public boolean appendLogToNative(String str, String str2, String str3) {
        if ((this.f967i || this.f968j) && str != null && str2 != null && str3 != null) {
            try {
                if (this.f968j) {
                    return appendNativeLog(str, str2, str3);
                }
                Boolean bool = (Boolean) C2581ap.m814a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "appendNativeLog", new Class[]{String.class, String.class, String.class}, new Object[]{str, str2, str3});
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (UnsatisfiedLinkError unused) {
            } catch (Throwable th) {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override // com.tencent.bugly.proguard.InterfaceC2629q
    public String getLogFromNative() {
        if (!this.f967i && !this.f968j) {
            return null;
        }
        try {
            if (this.f968j) {
                return getNativeLog();
            }
            return (String) C2581ap.m814a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "getNativeLog", null, null);
        } catch (UnsatisfiedLinkError unused) {
            return null;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        if ((this.f967i || this.f968j) && str != null && str2 != null) {
            try {
                if (this.f968j) {
                    return putNativeKeyValue(str, str2);
                }
                Boolean bool = (Boolean) C2581ap.m814a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "putNativeKeyValue", new Class[]{String.class, String.class}, new Object[]{str, str2});
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (UnsatisfiedLinkError unused) {
            } catch (Throwable th) {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m630a(int i, String str) {
        if (!this.f968j) {
            return false;
        }
        try {
            setNativeInfo(i, str);
            return true;
        } catch (UnsatisfiedLinkError unused) {
            return false;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public boolean filterSigabrtSysLog() {
        return m630a(998, "true");
    }

    public boolean setNativeAppVersion(String str) {
        return m630a(10, str);
    }

    public boolean setNativeAppChannel(String str) {
        return m630a(12, str);
    }

    public boolean setNativeAppPackage(String str) {
        return m630a(13, str);
    }

    public boolean setNativeUserId(String str) {
        return m630a(11, str);
    }

    @Override // com.tencent.bugly.proguard.InterfaceC2629q
    public boolean setNativeIsAppForeground(boolean z) {
        return m630a(14, z ? "true" : JsonSerializer.False);
    }

    public boolean setNativeLaunchTime(long j) {
        try {
            return m630a(15, String.valueOf(j));
        } catch (NumberFormatException e) {
            if (C2577al.m782a(e)) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    public void enableCatchAnrTrace() {
        f960c |= 2;
    }

    public void disableCatchAnrTrace() {
        f960c = 1;
    }

    public boolean isEnableCatchAnrTrace() {
        return (f960c & 2) == 2;
    }

    public String getSystemProperty(String str) {
        return (this.f968j || this.f967i) ? getProperties(str) : "fail";
    }
}
