package com.tencent.bugly.crashreport;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.crashreport.crash.p015h5.H5JavaScriptInterface;
import com.tencent.bugly.proguard.C2566aa;
import com.tencent.bugly.proguard.C2568ac;
import com.tencent.bugly.proguard.C2576ak;
import com.tencent.bugly.proguard.C2577al;
import com.tencent.bugly.proguard.C2579an;
import com.tencent.bugly.proguard.C2581ap;
import com.tencent.bugly.proguard.C2582aq;
import com.tencent.bugly.proguard.C2585at;
import com.tencent.bugly.proguard.C2586au;
import com.tencent.bugly.proguard.C2595bc;
import com.tencent.bugly.proguard.C2628p;
import com.tencent.bugly.proguard.C2631s;
import com.tencent.bugly.proguard.C2635w;
import com.tencent.bugly.proguard.C2636x;
import com.tencent.bugly.proguard.C2637y;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: BUGLY */
/* loaded from: classes3.dex */
public class CrashReport {

    /* renamed from: a */
    private static Context f843a;

    /* compiled from: BUGLY */
    /* loaded from: classes3.dex */
    public static class CrashHandleCallback extends BuglyStrategy.C2551a {
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.CrashReport$a */
    /* loaded from: classes3.dex */
    public interface InterfaceC2554a {
        /* renamed from: a */
        String mo615a();

        /* renamed from: a */
        void mo616a(H5JavaScriptInterface h5JavaScriptInterface, String str);

        /* renamed from: a */
        void mo617a(String str);

        /* renamed from: b */
        void mo618b();

        /* renamed from: c */
        CharSequence mo619c();
    }

    public static void enableBugly(boolean z) {
        C2628p.f1467a = z;
    }

    public static void initCrashReport(Context context) {
        if (context == null) {
            return;
        }
        f843a = context;
        C2628p.m1091a(CrashModule.getInstance());
        C2628p.m1088a(context);
    }

    public static void initCrashReport(Context context, UserStrategy userStrategy) {
        if (context == null) {
            return;
        }
        f843a = context;
        C2628p.m1091a(CrashModule.getInstance());
        C2628p.m1089a(context, userStrategy);
    }

    public static void initCrashReport(Context context, String str, boolean z) {
        initCrashReport(context, str, z, null);
    }

    public static void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        if (context == null) {
            return;
        }
        f843a = context;
        C2628p.m1091a(CrashModule.getInstance());
        C2628p.m1090a(context, str, z, userStrategy);
    }

    public static Context getContext() {
        return f843a;
    }

    public static String getBuglyVersion(Context context) {
        if (context == null) {
            C2577al.m786d("Please call with context.", new Object[0]);
            return "unknown";
        }
        return C2566aa.m646a(context).f1032h;
    }

    public static void testJavaCrash() {
        int i;
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not test Java crash because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        C2566aa m648b = C2566aa.m648b();
        if (m648b != null && (i = m648b.f1048x) != 24096) {
            m648b.f1048x = 24096;
            C2577al.m781a("server scene tag %d changed to tag %d", Integer.valueOf(i), Integer.valueOf(m648b.f1048x));
        }
        throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
    }

    public static void testNativeCrash() {
        testNativeCrash(true, true, false);
    }

    public static void testNativeCrash(boolean z, boolean z2, boolean z3) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not test native crash because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C2577al.m781a("start to create a native crash for test!", new Object[0]);
            C2585at.m904a().m910a(z, z2, z3);
        }
    }

    public static void testANRCrash() {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not test ANR crash because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C2577al.m781a("start to create a anr crash for test!", new Object[0]);
            C2585at.m904a().m917h();
        }
    }

    public static void postException(Thread thread, int i, String str, String str2, String str3, Map<String, String> map) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not post crash caught because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C2586au.m925a(thread, i, str, str2, str3, map);
        }
    }

    public static void postException(int i, String str, String str2, String str3, Map<String, String> map) {
        postException(Thread.currentThread(), i, str, str2, str3, map);
    }

    public static void postCatchedException(Throwable th) {
        postCatchedException(th, Thread.currentThread());
    }

    public static void postCatchedException(Throwable th, Thread thread) {
        postCatchedException(th, thread, false);
    }

    public static void postCatchedException(final Throwable th, final Thread thread, final boolean z) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not post crash caught because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        if (th == null) {
            C2577al.m786d("throwable is null, just return", new Object[0]);
            return;
        }
        if (thread == null) {
            thread = Thread.currentThread();
        }
        final C2585at m904a = C2585at.m904a();
        m904a.f1213w.m774a(new Runnable() { // from class: com.tencent.bugly.proguard.at.3

            /* renamed from: a */
            final /* synthetic */ boolean f1219a = false;

            /* renamed from: d */
            final /* synthetic */ String f1222d = null;

            /* renamed from: e */
            final /* synthetic */ byte[] f1223e = null;

            /* renamed from: f */
            final /* synthetic */ boolean f1224f = true;

            @Override // java.lang.Runnable
            public final void run() {
                try {
                    C2577al.m785c("post a throwable %b", Boolean.valueOf(this.f1219a));
                    C2585at.this.f1210t.m935a(thread, th, false, this.f1222d, this.f1223e, this.f1224f);
                    if (z) {
                        C2577al.m781a("clear user datas", new Object[0]);
                        C2566aa.m646a(C2585at.this.f1208c).m679u();
                    }
                } catch (Throwable th2) {
                    if (!C2577al.m784b(th2)) {
                        th2.printStackTrace();
                    }
                    C2577al.m787e("java catch error: %s", th.toString());
                }
            }
        });
    }

    public static void setAllThreadStackEnable(Context context, boolean z, boolean z2) {
        C2566aa m646a = C2566aa.m646a(context);
        m646a.f992Q = z;
        m646a.f993R = z2;
    }

    public static void closeNativeReport() {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not close native report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C2585at.m904a().m913d();
        }
    }

    public static void startCrashReport() {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not start crash report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C2585at.m904a().m911b();
        }
    }

    public static void closeCrashReport() {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not close crash report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C2585at.m904a().m912c();
        }
    }

    public static void closeBugly() {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not close bugly because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        if (f843a == null) {
            return;
        }
        C2582aq m854a = C2582aq.m854a();
        if (m854a != null) {
            m854a.m860b(f843a);
        }
        closeCrashReport();
        C2631s.m1116a(f843a);
        C2576ak m772a = C2576ak.m772a();
        if (m772a != null) {
            m772a.m776b();
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set tag caught because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.e(C2577al.f1123b, "setTag args context should not be null");
            return;
        }
        if (i <= 0) {
            C2577al.m786d("setTag args tagId should > 0", new Object[0]);
        }
        C2566aa m646a = C2566aa.m646a(context);
        synchronized (m646a.f996U) {
            int i2 = m646a.f1047w;
            if (i2 != i) {
                m646a.f1047w = i;
                C2577al.m781a("user scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(m646a.f1047w));
            }
        }
        C2577al.m783b("[param] set user scene tag: %d", Integer.valueOf(i));
    }

    public static int getUserSceneTagId(Context context) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not get user scene tag because bugly is disable.");
            return -1;
        }
        if (context == null) {
            Log.e(C2577al.f1123b, "getUserSceneTagId args context should not be null");
            return -1;
        }
        return C2566aa.m646a(context).m684z();
    }

    public static String getUserData(Context context, String str) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not get user data because bugly is disable.");
            return "unknown";
        }
        if (context == null) {
            Log.e(C2577al.f1123b, "getUserDataValue args context should not be null");
            return "unknown";
        }
        if (C2581ap.m844b(str)) {
            return null;
        }
        return C2566aa.m646a(context).m666g(str);
    }

    public static void putUserData(Context context, String str, String str2) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not put user data because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C2577al.f1123b, "putUserData args context should not be null");
            return;
        }
        if (str == null) {
            C2577al.m786d("putUserData args key should not be null or empty", new Object[0]);
            return;
        }
        if (str2 == null) {
            C2577al.m786d("putUserData args value should not be null", new Object[0]);
            return;
        }
        if (str2.length() > 200) {
            C2577al.m786d("user data value length over limit %d, it will be cutted!", 200);
            str2 = str2.substring(0, 200);
        }
        C2566aa m646a = C2566aa.m646a(context);
        if (m646a.m681w().contains(str)) {
            NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
            if (nativeCrashHandler != null) {
                nativeCrashHandler.putKeyValueToNative(str, str2);
            }
            C2566aa.m646a(context).m653a(str, str2);
            C2577al.m785c("replace KV %s %s", str, str2);
            return;
        }
        if (m646a.m680v() >= 50) {
            C2577al.m786d("user data size is over limit %d, it will be cutted!", 50);
            return;
        }
        if (str.length() > 50) {
            C2577al.m786d("user data key length over limit %d , will drop this new key %s", 50, str);
            str = str.substring(0, 50);
        }
        NativeCrashHandler nativeCrashHandler2 = NativeCrashHandler.getInstance();
        if (nativeCrashHandler2 != null) {
            nativeCrashHandler2.putKeyValueToNative(str, str2);
        }
        C2566aa.m646a(context).m653a(str, str2);
        C2577al.m783b("[param] set user data: %s - %s", str, str2);
    }

    public static String removeUserData(Context context, String str) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not remove user data because bugly is disable.");
            return "unknown";
        }
        if (context == null) {
            Log.e(C2577al.f1123b, "removeUserData args context should not be null");
            return "unknown";
        }
        if (C2581ap.m844b(str)) {
            return null;
        }
        C2577al.m783b("[param] remove user data: %s", str);
        return C2566aa.m646a(context).m664f(str);
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not get all keys of user data because bugly is disable.");
            return new HashSet();
        }
        if (context == null) {
            Log.e(C2577al.f1123b, "getAllUserDataKeys args context should not be null");
            return new HashSet();
        }
        return C2566aa.m646a(context).m681w();
    }

    public static int getUserDatasSize(Context context) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not get size of user data because bugly is disable.");
            return -1;
        }
        if (context == null) {
            Log.e(C2577al.f1123b, "getUserDatasSize args context should not be null");
            return -1;
        }
        return C2566aa.m646a(context).m680v();
    }

    public static String getAppID() {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not get App ID because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return C2566aa.m646a(f843a).m661e();
    }

    public static void setUserId(String str) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set user ID because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            setUserId(f843a, str);
        }
    }

    public static void setUserId(Context context, String str) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set user ID because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.e(C2577al.f1123b, "Context should not be null when bugly has not been initialed!");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            C2577al.m786d("userId should not be null", new Object[0]);
            return;
        }
        if (str.length() > 100) {
            String substring = str.substring(0, 100);
            C2577al.m786d("userId %s length is over limit %d substring to %s", str, 100, substring);
            str = substring;
        }
        if (str.equals(C2566aa.m646a(context).m663f())) {
            return;
        }
        C2566aa m646a = C2566aa.m646a(context);
        synchronized (m646a.f997V) {
            m646a.f1036l = String.valueOf(str == null ? "10000" : str);
        }
        C2577al.m783b("[user] set userId : %s", str);
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeUserId(str);
        }
        if (CrashModule.getInstance().hasInitialized()) {
            C2631s.m1114a();
        }
    }

    public static String getUserId() {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not get user ID because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return C2566aa.m646a(f843a).m663f();
    }

    public static void setDeviceId(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        C2566aa.m646a(context).m652a(str);
    }

    public static void setDeviceModel(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        C2566aa.m646a(context).m655b(str);
    }

    public static String getDeviceID(Context context) {
        return C2566aa.m646a(context).m665g();
    }

    public static String getAppVer() {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not get app version because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return C2566aa.m646a(f843a).f1039o;
    }

    public static String getAppChannel() {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not get App channel because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return C2566aa.m646a(f843a).f1043s;
    }

    public static void setContext(Context context) {
        f843a = context;
    }

    public static boolean isLastSessionCrash() {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
            return false;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return false;
        }
        C2585at m904a = C2585at.m904a();
        Boolean bool = m904a.f1205A;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = C2566aa.m648b().f1028d;
        List<C2637y> m1171a = C2635w.m1154a().m1171a(1);
        ArrayList arrayList = new ArrayList();
        if (m1171a != null && m1171a.size() > 0) {
            for (C2637y c2637y : m1171a) {
                if (str.equals(c2637y.f1547c)) {
                    m904a.f1205A = Boolean.TRUE;
                    arrayList.add(c2637y);
                }
            }
            if (arrayList.size() > 0) {
                C2635w.m1154a().m1173a(arrayList);
            }
            return true;
        }
        m904a.f1205A = Boolean.FALSE;
        return false;
    }

    public static void setSdkExtraData(Context context, String str, String str2) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not put SDK extra data because bugly is disable.");
            return;
        }
        if (context == null || C2581ap.m844b(str) || C2581ap.m844b(str2)) {
            return;
        }
        C2566aa m646a = C2566aa.m646a(context);
        if (str == null || str2 == null) {
            return;
        }
        synchronized (m646a.f995T) {
            m646a.f986K.put(str, str2);
        }
    }

    public static Map<String, String> getSdkExtraData() {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2577al.f1123b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return null;
        }
        return C2566aa.m646a(f843a).f986K;
    }

    public static Map<String, String> getSdkExtraData(Context context) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        }
        if (context == null) {
            C2577al.m786d("Context should not be null.", new Object[0]);
            return null;
        }
        return C2566aa.m646a(context).f986K;
    }

    private static void putSdkData(Context context, String str, String str2) {
        if (context == null || C2581ap.m844b(str) || C2581ap.m844b(str2)) {
            return;
        }
        String replace = str.replace("[a-zA-Z[0-9]]+", "");
        if (replace.length() > 100) {
            Log.w(C2577al.f1123b, String.format("putSdkData key length over limit %d, will be cutted.", 50));
            replace = replace.substring(0, 50);
        }
        if (str2.length() > 500) {
            Log.w(C2577al.f1123b, String.format("putSdkData value length over limit %d, will be cutted!", 200));
            str2 = str2.substring(0, 200);
        }
        C2566aa.m646a(context).m656b(replace, str2);
        C2577al.m783b(String.format("[param] putSdkData data: %s - %s", replace, str2), new Object[0]);
    }

    @Deprecated
    public static void setIsAppForeground(Context context, boolean z) {
        C2577al.m781a("App fore and back status are no longer supported", new Object[0]);
    }

    public static void setIsDevelopmentDevice(Context context, boolean z) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set 'isDevelopmentDevice' because bugly is disable.");
            return;
        }
        if (context == null) {
            C2577al.m786d("Context should not be null.", new Object[0]);
            return;
        }
        if (z) {
            C2577al.m785c("This is a development device.", new Object[0]);
        } else {
            C2577al.m785c("This is not a development device.", new Object[0]);
        }
        C2566aa.m646a(context).f984I = z;
    }

    public static void setSessionIntervalMills(long j) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set 'SessionIntervalMills' because bugly is disable.");
        } else {
            C2631s.m1115a(j);
        }
    }

    public static void setDumpFilePath(Context context, String str) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set App version because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C2577al.f1123b, "setTombPath args context should not be null");
        } else if (str == null) {
            Log.w(C2577al.f1123b, "tombstone path is null, will not set");
        } else {
            Log.i(C2577al.f1123b, "user set tombstone path: ".concat(String.valueOf(str)));
            NativeCrashHandler.setDumpFilePath(str);
        }
    }

    public static void setAppVersion(Context context, String str) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set App version because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C2577al.f1123b, "setAppVersion args context should not be null");
            return;
        }
        if (str == null) {
            Log.w(C2577al.f1123b, "App version is null, will not set");
            return;
        }
        C2566aa.m646a(context).f1039o = str;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeAppVersion(str);
        }
    }

    public static void setAppChannel(Context context, String str) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set App channel because Bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C2577al.f1123b, "setAppChannel args context should not be null");
            return;
        }
        if (str == null) {
            Log.w(C2577al.f1123b, "App channel is null, will not set");
            return;
        }
        C2566aa.m646a(context).f1043s = str;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeAppChannel(str);
        }
    }

    public static void setAppPackage(Context context, String str) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set App package because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C2577al.f1123b, "setAppPackage args context should not be null");
            return;
        }
        if (str == null) {
            Log.w(C2577al.f1123b, "App package is null, will not set");
            return;
        }
        C2566aa.m646a(context).f1027c = str;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeAppPackage(str);
        }
    }

    public static void setCrashFilter(String str) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set App package because bugly is disable.");
        } else {
            Log.i(C2577al.f1123b, "Set crash stack filter: ".concat(String.valueOf(str)));
            C2585at.f1203q = str;
        }
    }

    public static void setCrashRegularFilter(String str) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set App package because bugly is disable.");
        } else {
            Log.i(C2577al.f1123b, "Set crash stack filter: ".concat(String.valueOf(str)));
            C2585at.f1204r = str;
        }
    }

    public static void setHandleNativeCrashInJava(boolean z) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set App package because bugly is disable.");
        } else {
            Log.i(C2577al.f1123b, "Should handle native crash in Java profile after handled in native profile: ".concat(String.valueOf(z)));
            NativeCrashHandler.setShouldHandleInJava(z);
        }
    }

    public static void setBuglyDbName(String str) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set DB name because bugly is disable.");
        } else {
            Log.i(C2577al.f1123b, "Set Bugly DB name: ".concat(String.valueOf(str)));
            C2636x.f1541a = str;
        }
    }

    public static void enableObtainId(Context context, boolean z) {
        setCollectPrivacyInfo(context, z);
    }

    public static void setCollectPrivacyInfo(Context context, boolean z) {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set collect privacy info enable because bugly is disable.");
        } else if (context == null) {
            Log.w(C2577al.f1123b, "setCollectPrivacyInfo args context should not be null");
        } else {
            Log.i(C2577al.f1123b, "setCollectPrivacyInfo: ".concat(String.valueOf(z)));
            C2566aa.m646a(context).f1038n = z;
        }
    }

    public static void setServerUrl(String str) {
        if (C2581ap.m844b(str) || !C2581ap.m852d(str)) {
            Log.i(C2577al.f1123b, "URL is invalid.");
            return;
        }
        C2568ac.m712a(str);
        StrategyBean.f868a = str;
        StrategyBean.f869b = str;
    }

    public static void uploadUserInfo() {
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not upload user info because bugly is disable.");
        } else if (C2631s.f1488b == null) {
            Log.w(C2577al.f1123b, "Can not upload user info because bugly is not init.");
        } else {
            C2631s.f1488b.m1112b();
        }
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    public static boolean setJavascriptMonitor(final WebView webView, boolean z, boolean z2) {
        if (webView == null) {
            Log.w(C2577al.f1123b, "WebView is null.");
            return false;
        }
        webView.getSettings().setSavePassword(false);
        webView.getSettings().setAllowFileAccess(false);
        return setJavascriptMonitor(new InterfaceC2554a() { // from class: com.tencent.bugly.crashreport.CrashReport.1
            @Override // com.tencent.bugly.crashreport.CrashReport.InterfaceC2554a
            /* renamed from: a */
            public final String mo615a() {
                return webView.getUrl();
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.InterfaceC2554a
            /* renamed from: b */
            public final void mo618b() {
                WebSettings settings = webView.getSettings();
                if (settings.getJavaScriptEnabled()) {
                    return;
                }
                settings.setJavaScriptEnabled(true);
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.InterfaceC2554a
            /* renamed from: a */
            public final void mo617a(String str) {
                webView.loadUrl(str);
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.InterfaceC2554a
            /* renamed from: a */
            public final void mo616a(H5JavaScriptInterface h5JavaScriptInterface, String str) {
                webView.addJavascriptInterface(h5JavaScriptInterface, str);
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.InterfaceC2554a
            /* renamed from: c */
            public final CharSequence mo619c() {
                return webView.getContentDescription();
            }
        }, z, z2);
    }

    public static boolean setJavascriptMonitor(InterfaceC2554a interfaceC2554a, boolean z) {
        return setJavascriptMonitor(interfaceC2554a, z, false);
    }

    public static boolean setJavascriptMonitor(InterfaceC2554a interfaceC2554a, boolean z, boolean z2) {
        if (interfaceC2554a == null) {
            Log.w(C2577al.f1123b, "WebViewInterface is null.");
            return false;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            C2577al.m787e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        }
        C2577al.m781a("Set Javascript exception monitor of webview.", new Object[0]);
        if (!C2628p.f1467a) {
            Log.w(C2577al.f1123b, "Can not set JavaScript monitor because bugly is disable.");
            return false;
        }
        C2577al.m785c("URL of webview is %s", interfaceC2554a.mo615a());
        C2577al.m781a("Enable the javascript needed by webview monitor.", new Object[0]);
        interfaceC2554a.mo618b();
        H5JavaScriptInterface h5JavaScriptInterface = H5JavaScriptInterface.getInstance(interfaceC2554a);
        if (h5JavaScriptInterface != null) {
            C2577al.m781a("Add a secure javascript interface to the webview.", new Object[0]);
            interfaceC2554a.mo616a(h5JavaScriptInterface, "exceptionUploader");
        }
        if (z) {
            C2577al.m781a("Inject bugly.js(v%s) to the webview.", C2595bc.m963b());
            String m962a = C2595bc.m962a();
            if (m962a == null) {
                C2577al.m787e("Failed to inject Bugly.js.", C2595bc.m963b());
                return false;
            }
            interfaceC2554a.mo617a("javascript:".concat(String.valueOf(m962a)));
        }
        return true;
    }

    /* compiled from: BUGLY */
    /* loaded from: classes3.dex */
    public static class UserStrategy extends BuglyStrategy {

        /* renamed from: c */
        CrashHandleCallback f845c;

        public UserStrategy(Context context) {
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized void setCallBackType(int i) {
            this.f816a = i;
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized int getCallBackType() {
            return this.f816a;
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized void setCloseErrorCallback(boolean z) {
            this.f817b = z;
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized boolean getCloseErrorCallback() {
            return this.f817b;
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized CrashHandleCallback getCrashHandleCallback() {
            return this.f845c;
        }

        public synchronized void setCrashHandleCallback(CrashHandleCallback crashHandleCallback) {
            this.f845c = crashHandleCallback;
        }
    }

    public static void setHttpProxy(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            C2579an.f1127a = null;
        } else {
            C2579an.f1127a = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i));
        }
    }

    public static void setHttpProxy(InetAddress inetAddress, int i) {
        if (inetAddress == null) {
            C2579an.f1127a = null;
        } else {
            C2579an.f1127a = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(inetAddress, i));
        }
    }

    public static Proxy getHttpProxy() {
        return C2579an.f1127a;
    }
}
