package com.tencent.bugly.proguard;

import android.app.ActivityManager;
import android.content.Context;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.bd */
/* loaded from: classes3.dex */
public final class C2596bd implements NativeExceptionHandler {

    /* renamed from: a */
    private final Context f1296a;

    /* renamed from: b */
    private final C2584as f1297b;

    /* renamed from: c */
    private final C2566aa f1298c;

    /* renamed from: d */
    private final C2568ac f1299d;

    public C2596bd(Context context, C2566aa c2566aa, C2584as c2584as, C2568ac c2568ac) {
        this.f1296a = context;
        this.f1297b = c2584as;
        this.f1298c = c2566aa;
        this.f1299d = c2568ac;
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, byte[] bArr, Map<String, String> map, boolean z, boolean z2) {
        int i;
        String str12;
        int indexOf;
        boolean m918i = C2585at.m904a().m918i();
        if (m918i) {
            C2577al.m787e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f919b = 1;
        crashDetailBean.f922e = this.f1298c.m665g();
        crashDetailBean.f923f = this.f1298c.f1039o;
        crashDetailBean.f924g = this.f1298c.m675q();
        crashDetailBean.f930m = this.f1298c.m663f();
        crashDetailBean.f931n = str3;
        crashDetailBean.f932o = m918i ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        crashDetailBean.f933p = str4;
        crashDetailBean.f934q = str5 != null ? str5 : "";
        crashDetailBean.f935r = j;
        crashDetailBean.f938u = C2581ap.m846c(crashDetailBean.f934q.getBytes());
        crashDetailBean.f891A = str;
        crashDetailBean.f892B = str2;
        crashDetailBean.f902L = this.f1298c.m677s();
        crashDetailBean.f925h = this.f1298c.m674p();
        crashDetailBean.f926i = this.f1298c.m650A();
        crashDetailBean.f939v = str8;
        String dumpFilePath = NativeCrashHandler.getInstance() != null ? NativeCrashHandler.getDumpFilePath() : null;
        String m971a = C2597be.m971a(dumpFilePath, str8);
        if (!C2581ap.m844b(m971a)) {
            crashDetailBean.f916Z = m971a;
        }
        crashDetailBean.f918aa = C2597be.m975b(dumpFilePath);
        crashDetailBean.f940w = C2597be.m970a(str9, C2585at.f1192f, C2585at.f1197k, C2585at.f1202p);
        crashDetailBean.f941x = C2597be.m970a(str10, C2585at.f1192f, null, true);
        crashDetailBean.f904N = str7;
        crashDetailBean.f905O = str6;
        crashDetailBean.f906P = str11;
        crashDetailBean.f896F = this.f1298c.m670k();
        crashDetailBean.f897G = this.f1298c.m669j();
        crashDetailBean.f898H = this.f1298c.m671l();
        crashDetailBean.f899I = C2567ab.m687b(this.f1296a);
        crashDetailBean.f900J = C2567ab.m694g();
        crashDetailBean.f901K = C2567ab.m695h();
        if (z) {
            crashDetailBean.f893C = C2567ab.m697j();
            crashDetailBean.f894D = C2567ab.m693f();
            crashDetailBean.f895E = C2567ab.m699l();
            crashDetailBean.f942y = C2580ao.m800a();
            crashDetailBean.f907Q = this.f1298c.f1001a;
            crashDetailBean.f908R = this.f1298c.m654a();
            crashDetailBean.f943z = C2581ap.m827a(this.f1298c.f992Q, C2585at.f1194h);
            int indexOf2 = crashDetailBean.f934q.indexOf("java:\n");
            if (indexOf2 > 0 && (i = indexOf2 + 6) < crashDetailBean.f934q.length()) {
                String str13 = crashDetailBean.f934q;
                String substring = str13.substring(i, str13.length() - 1);
                if (substring.length() > 0 && crashDetailBean.f943z.containsKey(crashDetailBean.f892B) && (indexOf = (str12 = crashDetailBean.f943z.get(crashDetailBean.f892B)).indexOf(substring)) > 0) {
                    String substring2 = str12.substring(indexOf);
                    crashDetailBean.f943z.put(crashDetailBean.f892B, substring2);
                    crashDetailBean.f934q = crashDetailBean.f934q.substring(0, i);
                    crashDetailBean.f934q += substring2;
                }
            }
            if (str == null) {
                crashDetailBean.f891A = this.f1298c.f1028d;
            }
            crashDetailBean.f911U = this.f1298c.m684z();
            crashDetailBean.f912V = this.f1298c.f1048x;
            crashDetailBean.f913W = this.f1298c.m678t();
            crashDetailBean.f914X = this.f1298c.m683y();
        } else {
            crashDetailBean.f893C = -1L;
            crashDetailBean.f894D = -1L;
            crashDetailBean.f895E = -1L;
            if (crashDetailBean.f940w == null) {
                crashDetailBean.f940w = "This crash occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.f907Q = -1L;
            crashDetailBean.f911U = -1;
            crashDetailBean.f912V = -1;
            crashDetailBean.f913W = map;
            crashDetailBean.f914X = this.f1298c.m683y();
            crashDetailBean.f943z = null;
            if (str == null) {
                crashDetailBean.f891A = "unknown(record)";
            }
            if (bArr != null) {
                crashDetailBean.f942y = bArr;
            }
        }
        return crashDetailBean;
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final boolean getAndUpdateAnrState() {
        if (C2590ay.m941a() == null) {
            return false;
        }
        C2590ay m941a = C2590ay.m941a();
        if (m941a.f1258a.get()) {
            C2577al.m785c("anr is processing, return", new Object[0]);
            return false;
        }
        ActivityManager activityManager = m941a.f1259b;
        if (!((C2638z.m1183a(activityManager) || C2591az.m959a(activityManager, 0L) == null) ? false : true)) {
            C2577al.m785c("proc is not in anr, wait next check", new Object[0]);
            return false;
        }
        if (m941a.m955a(System.currentTimeMillis())) {
            return false;
        }
        return m941a.m956a(true);
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        C2577al.m781a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x009c A[Catch: all -> 0x0228, TryCatch #2 {all -> 0x0228, blocks: (B:79:0x0016, B:4:0x0036, B:6:0x0049, B:8:0x0051, B:9:0x005d, B:11:0x0069, B:14:0x0070, B:15:0x007c, B:17:0x009c, B:18:0x00cf, B:20:0x00f2, B:21:0x00f9, B:24:0x0105, B:26:0x010d, B:34:0x0155, B:35:0x0159, B:37:0x0163, B:75:0x00ba, B:76:0x007a), top: B:78:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00f2 A[Catch: all -> 0x0228, TryCatch #2 {all -> 0x0228, blocks: (B:79:0x0016, B:4:0x0036, B:6:0x0049, B:8:0x0051, B:9:0x005d, B:11:0x0069, B:14:0x0070, B:15:0x007c, B:17:0x009c, B:18:0x00cf, B:20:0x00f2, B:21:0x00f9, B:24:0x0105, B:26:0x010d, B:34:0x0155, B:35:0x0159, B:37:0x0163, B:75:0x00ba, B:76:0x007a), top: B:78:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01a6 A[Catch: all -> 0x0224, TryCatch #1 {all -> 0x0224, blocks: (B:41:0x01a0, B:43:0x01a6, B:45:0x01af), top: B:40:0x01a0 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01af A[Catch: all -> 0x0224, TRY_LEAVE, TryCatch #1 {all -> 0x0224, blocks: (B:41:0x01a0, B:43:0x01a6, B:45:0x01af), top: B:40:0x01a0 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00ba A[Catch: all -> 0x0228, TryCatch #2 {all -> 0x0228, blocks: (B:79:0x0016, B:4:0x0036, B:6:0x0049, B:8:0x0051, B:9:0x005d, B:11:0x0069, B:14:0x0070, B:15:0x007c, B:17:0x009c, B:18:0x00cf, B:20:0x00f2, B:21:0x00f9, B:24:0x0105, B:26:0x010d, B:34:0x0155, B:35:0x0159, B:37:0x0163, B:75:0x00ba, B:76:0x007a), top: B:78:0x0016 }] */
    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void handleNativeException2(int r28, int r29, long r30, long r32, java.lang.String r34, java.lang.String r35, java.lang.String r36, java.lang.String r37, int r38, java.lang.String r39, int r40, int r41, int r42, java.lang.String r43, java.lang.String r44, java.lang.String[] r45) {
        /*
            Method dump skipped, instructions count: 564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2596bd.handleNativeException2(int, int, long, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, int, int, int, java.lang.String, java.lang.String, java.lang.String[]):void");
    }

    /* renamed from: a */
    private static Map<String, String> m964a(String[] strArr) {
        HashMap hashMap = new HashMap(strArr == null ? 1 : strArr.length);
        if (strArr != null) {
            for (int i = 0; i < strArr.length; i++) {
                String str = strArr[i];
                if (str != null) {
                    C2577al.m781a("Extra message[%d]: %s", Integer.valueOf(i), str);
                    String[] split = str.split("=");
                    if (split.length == 2) {
                        hashMap.put(split[0], split[1]);
                    } else {
                        C2577al.m786d("bad extraMsg %s", str);
                    }
                }
            }
        } else {
            C2577al.m785c("not found extraMsg", new Object[0]);
        }
        return hashMap;
    }
}
