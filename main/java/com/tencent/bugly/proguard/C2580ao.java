package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ao */
/* loaded from: classes3.dex */
public final class C2580ao {

    /* renamed from: a */
    public static boolean f1128a = true;

    /* renamed from: b */
    public static boolean f1129b = true;

    /* renamed from: c */
    private static SimpleDateFormat f1130c = null;

    /* renamed from: d */
    private static int f1131d = 30720;

    /* renamed from: e */
    private static StringBuilder f1132e = null;

    /* renamed from: f */
    private static StringBuilder f1133f = null;

    /* renamed from: g */
    private static boolean f1134g = false;

    /* renamed from: h */
    private static a f1135h = null;

    /* renamed from: i */
    private static String f1136i = null;

    /* renamed from: j */
    private static String f1137j = null;

    /* renamed from: k */
    private static Context f1138k = null;

    /* renamed from: l */
    private static String f1139l = null;

    /* renamed from: m */
    private static boolean f1140m = false;

    /* renamed from: n */
    private static boolean f1141n = false;

    /* renamed from: o */
    private static ExecutorService f1142o;

    /* renamed from: p */
    private static int f1143p;

    /* renamed from: q */
    private static final Object f1144q = new Object();

    static {
        try {
            f1130c = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable th) {
            C2577al.m784b(th.getCause());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static boolean m805d(String str, String str2, String str3) {
        try {
            C2566aa m648b = C2566aa.m648b();
            if (m648b == null || m648b.f989N == null) {
                return false;
            }
            return m648b.f989N.appendLogToNative(str, str2, str3);
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    private static String m801b() {
        try {
            C2566aa m648b = C2566aa.m648b();
            if (m648b == null || m648b.f989N == null) {
                return null;
            }
            return m648b.f989N.getLogFromNative();
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static synchronized void m797a(Context context) {
        synchronized (C2580ao.class) {
            if (f1140m || context == null || !f1129b) {
                return;
            }
            try {
                f1142o = Executors.newSingleThreadExecutor();
                f1133f = new StringBuilder(0);
                f1132e = new StringBuilder(0);
                f1138k = context;
                C2566aa m646a = C2566aa.m646a(context);
                f1136i = m646a.f1028d;
                m646a.getClass();
                f1137j = "";
                f1139l = f1138k.getFilesDir().getPath() + "/buglylog_" + f1136i + "_" + f1137j + ".txt";
                f1143p = Process.myPid();
            } catch (Throwable unused) {
            }
            f1140m = true;
        }
    }

    /* renamed from: a */
    public static void m796a(int i) {
        synchronized (f1144q) {
            f1131d = i;
            if (i < 0) {
                f1131d = 0;
            } else if (i > 30720) {
                f1131d = 30720;
            }
        }
    }

    /* renamed from: a */
    public static void m799a(String str, String str2, Throwable th) {
        if (th == null) {
            return;
        }
        String message = th.getMessage();
        if (message == null) {
            message = "";
        }
        m798a(str, str2, message + '\n' + C2581ap.m838b(th));
    }

    /* renamed from: a */
    public static synchronized void m798a(final String str, final String str2, final String str3) {
        synchronized (C2580ao.class) {
            if (f1140m && f1129b) {
                try {
                    if (f1141n) {
                        f1142o.execute(new Runnable() { // from class: com.tencent.bugly.proguard.ao.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                C2580ao.m805d(str, str2, str3);
                            }
                        });
                    } else {
                        f1142o.execute(new Runnable() { // from class: com.tencent.bugly.proguard.ao.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                C2580ao.m806e(str, str2, str3);
                            }
                        });
                    }
                } catch (Exception e) {
                    C2577al.m784b(e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public static synchronized void m806e(String str, String str2, String str3) {
        synchronized (C2580ao.class) {
            if (f1128a) {
                m807f(str, str2, str3);
            } else {
                m808g(str, str2, str3);
            }
        }
    }

    /* renamed from: f */
    private static synchronized void m807f(String str, String str2, String str3) {
        synchronized (C2580ao.class) {
            String m795a = m795a(str, str2, str3, Process.myTid());
            synchronized (f1144q) {
                try {
                    f1133f.append(m795a);
                    if (f1133f.length() >= f1131d) {
                        StringBuilder sb = f1133f;
                        f1133f = sb.delete(0, sb.indexOf("\u0001\r\n") + 1);
                    }
                } finally {
                }
            }
        }
    }

    /* renamed from: g */
    private static synchronized void m808g(String str, String str2, String str3) {
        synchronized (C2580ao.class) {
            String m795a = m795a(str, str2, str3, Process.myTid());
            synchronized (f1144q) {
                try {
                    f1133f.append(m795a);
                } catch (Throwable unused) {
                }
                if (f1133f.length() <= f1131d) {
                    return;
                }
                if (f1134g) {
                    return;
                }
                f1134g = true;
                a aVar = f1135h;
                if (aVar == null) {
                    f1135h = new a(f1139l);
                } else if (aVar.f1152b == null || f1135h.f1152b.length() + f1133f.length() > f1135h.f1153c) {
                    f1135h.m809a();
                }
                if (f1135h.m810a(f1133f.toString())) {
                    f1133f.setLength(0);
                    f1134g = false;
                }
            }
        }
    }

    /* renamed from: a */
    private static String m795a(String str, String str2, String str3, long j) {
        String date;
        f1132e.setLength(0);
        if (str3.length() > 30720) {
            str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
        }
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat = f1130c;
        if (simpleDateFormat != null) {
            date = simpleDateFormat.format(date2);
        } else {
            date = date2.toString();
        }
        StringBuilder sb = f1132e;
        sb.append(date);
        sb.append(" ");
        sb.append(f1143p);
        sb.append(" ");
        sb.append(j);
        sb.append(" ");
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        sb.append(": ");
        sb.append(str3);
        sb.append("\u0001\r\n");
        return f1132e.toString();
    }

    /* renamed from: a */
    public static byte[] m800a() {
        if (!f1128a) {
            return m804c();
        }
        if (f1129b) {
            return C2581ap.m834a(f1133f.toString(), "BuglyLog.txt");
        }
        return null;
    }

    /* renamed from: c */
    private static byte[] m804c() {
        if (!f1129b) {
            return null;
        }
        if (f1141n) {
            C2577al.m781a("[LogUtil] Get user log from native.", new Object[0]);
            String m801b = m801b();
            if (m801b != null) {
                C2577al.m781a("[LogUtil] Got user log from native: %d bytes", Integer.valueOf(m801b.length()));
                return C2581ap.m834a(m801b, "BuglyNativeLog.txt");
            }
        }
        StringBuilder sb = new StringBuilder();
        synchronized (f1144q) {
            a aVar = f1135h;
            if (aVar != null && aVar.f1151a && f1135h.f1152b != null && f1135h.f1152b.length() > 0) {
                sb.append(C2581ap.m819a(f1135h.f1152b, 30720, true));
            }
            StringBuilder sb2 = f1133f;
            if (sb2 != null && sb2.length() > 0) {
                sb.append(f1133f.toString());
            }
        }
        return C2581ap.m834a(sb.toString(), "BuglyLog.txt");
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.ao$a */
    /* loaded from: classes3.dex */
    public static class a {

        /* renamed from: a */
        boolean f1151a;

        /* renamed from: b */
        File f1152b;

        /* renamed from: c */
        long f1153c = 30720;

        /* renamed from: d */
        private String f1154d;

        /* renamed from: e */
        private long f1155e;

        public a(String str) {
            if (str == null || str.equals("")) {
                return;
            }
            this.f1154d = str;
            this.f1151a = m809a();
        }

        /* renamed from: a */
        final boolean m809a() {
            try {
                File file = new File(this.f1154d);
                this.f1152b = file;
                if (file.exists() && !this.f1152b.delete()) {
                    this.f1151a = false;
                    return false;
                }
                if (this.f1152b.createNewFile()) {
                    return true;
                }
                this.f1151a = false;
                return false;
            } catch (Throwable th) {
                C2577al.m782a(th);
                this.f1151a = false;
                return false;
            }
        }

        /* renamed from: a */
        public final boolean m810a(String str) {
            FileOutputStream fileOutputStream;
            if (!this.f1151a) {
                return false;
            }
            FileOutputStream fileOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(this.f1152b, true);
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileOutputStream.write(str.getBytes("UTF-8"));
                fileOutputStream.flush();
                fileOutputStream.close();
                this.f1155e += r10.length;
                this.f1151a = true;
                try {
                    fileOutputStream.close();
                } catch (IOException unused) {
                }
                return true;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                try {
                    C2577al.m782a(th);
                    this.f1151a = false;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return false;
                } catch (Throwable th3) {
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th3;
                }
            }
        }
    }
}
