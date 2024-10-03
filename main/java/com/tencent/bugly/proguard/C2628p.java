package com.tencent.bugly.proguard;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.p */
/* loaded from: classes3.dex */
public final class C2628p {

    /* renamed from: a */
    public static boolean f1467a = true;

    /* renamed from: b */
    public static List<AbstractC2627o> f1468b = new ArrayList();

    /* renamed from: c */
    public static boolean f1469c;

    /* renamed from: d */
    private static C2635w f1470d;

    /* renamed from: e */
    private static boolean f1471e;

    /* renamed from: a */
    private static boolean m1092a(C2566aa c2566aa) {
        List<String> list = c2566aa.f1046v;
        c2566aa.getClass();
        return list != null && list.contains("bugly");
    }

    /* renamed from: a */
    public static synchronized void m1088a(Context context) {
        synchronized (C2628p.class) {
            m1089a(context, null);
        }
    }

    /* renamed from: a */
    public static synchronized void m1089a(Context context, BuglyStrategy buglyStrategy) {
        synchronized (C2628p.class) {
            if (f1471e) {
                C2577al.m786d("[init] initial Multi-times, ignore this.", new Object[0]);
                return;
            }
            if (context == null) {
                Log.w(C2577al.f1123b, "[init] context of init() is null, check it.");
                return;
            }
            C2566aa m646a = C2566aa.m646a(context);
            if (m1092a(m646a)) {
                f1467a = false;
                return;
            }
            String m661e = m646a.m661e();
            if (m661e == null) {
                Log.e(C2577al.f1123b, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
            } else {
                m1090a(context, m661e, m646a.f979D, buglyStrategy);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m1090a(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        byte[] bArr;
        synchronized (C2628p.class) {
            if (f1471e) {
                C2577al.m786d("[init] initial Multi-times, ignore this.", new Object[0]);
                return;
            }
            if (context == null) {
                Log.w(C2577al.f1123b, "[init] context is null, check it.");
                return;
            }
            if (str == null) {
                Log.e(C2577al.f1123b, "init arg 'crashReportAppID' should not be null!");
                return;
            }
            f1471e = true;
            if (z) {
                f1469c = true;
                C2577al.f1124c = true;
                C2577al.m786d("Bugly debug模式开启，请在发布时把isDebug关闭。 -- Running in debug model for 'isDebug' is enabled. Please disable it when you release.", new Object[0]);
                C2577al.m787e("--------------------------------------------------------------------------------------------", new Object[0]);
                C2577al.m786d("Bugly debug模式将有以下行为特性 -- The following list shows the behaviour of debug model: ", new Object[0]);
                C2577al.m786d("[1] 输出详细的Bugly SDK的Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
                C2577al.m786d("[2] 每一条Crash都会被立即上报 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
                C2577al.m786d("[3] 自定义日志将会在Logcat中输出 -- Custom log will be output to logcat.", new Object[0]);
                C2577al.m787e("--------------------------------------------------------------------------------------------", new Object[0]);
                C2577al.m783b("[init] Open debug mode of Bugly.", new Object[0]);
            }
            C2577al.m781a(" crash report start initializing...", new Object[0]);
            C2577al.m783b("[init] Bugly start initializing...", new Object[0]);
            C2577al.m781a("[init] Bugly complete version: v%s", "4.1.9.3");
            Context m811a = C2581ap.m811a(context);
            C2566aa m646a = C2566aa.m646a(m811a);
            m646a.m673o();
            C2580ao.m797a(m811a);
            f1470d = C2635w.m1155a(m811a, f1468b);
            C2574ai.m747a(m811a);
            C2568ac.m711a(m811a, f1468b);
            C2633u m1135a = C2633u.m1135a(m811a);
            if (m1092a(m646a)) {
                f1467a = false;
                return;
            }
            m646a.f1042r = str;
            m646a.m656b("APP_ID", str);
            C2577al.m781a("[param] Set APP ID:%s", str);
            if (buglyStrategy != null) {
                String appVersion = buglyStrategy.getAppVersion();
                if (!TextUtils.isEmpty(appVersion)) {
                    if (appVersion.length() > 100) {
                        String substring = appVersion.substring(0, 100);
                        C2577al.m786d("appVersion %s length is over limit %d substring to %s", appVersion, 100, substring);
                        appVersion = substring;
                    }
                    m646a.f1039o = appVersion;
                    C2577al.m781a("[param] Set App version: %s", buglyStrategy.getAppVersion());
                }
                try {
                    if (buglyStrategy.isReplaceOldChannel()) {
                        String appChannel = buglyStrategy.getAppChannel();
                        if (!TextUtils.isEmpty(appChannel)) {
                            if (appChannel.length() > 100) {
                                String substring2 = appChannel.substring(0, 100);
                                C2577al.m786d("appChannel %s length is over limit %d substring to %s", appChannel, 100, substring2);
                                appChannel = substring2;
                            }
                            f1470d.m1174a(556, "app_channel", appChannel.getBytes(), false);
                            m646a.f1043s = appChannel;
                        }
                    } else {
                        Map<String, byte[]> m1172a = f1470d.m1172a(556, (InterfaceC2634v) null);
                        if (m1172a != null && (bArr = m1172a.get("app_channel")) != null) {
                            m646a.f1043s = new String(bArr);
                        }
                    }
                    C2577al.m781a("[param] Set App channel: %s", m646a.f1043s);
                } catch (Exception e) {
                    if (f1469c) {
                        e.printStackTrace();
                    }
                }
                String appPackageName = buglyStrategy.getAppPackageName();
                if (!TextUtils.isEmpty(appPackageName)) {
                    if (appPackageName.length() > 100) {
                        String substring3 = appPackageName.substring(0, 100);
                        C2577al.m786d("appPackageName %s length is over limit %d substring to %s", appPackageName, 100, substring3);
                        appPackageName = substring3;
                    }
                    m646a.f1027c = appPackageName;
                    C2577al.m781a("[param] Set App package: %s", buglyStrategy.getAppPackageName());
                }
                String deviceID = buglyStrategy.getDeviceID();
                if (deviceID != null) {
                    if (deviceID.length() > 100) {
                        String substring4 = deviceID.substring(0, 100);
                        C2577al.m786d("deviceId %s length is over limit %d substring to %s", deviceID, 100, substring4);
                        deviceID = substring4;
                    }
                    m646a.m652a(deviceID);
                    C2577al.m781a("[param] Set device ID: %s", deviceID);
                }
                String deviceModel = buglyStrategy.getDeviceModel();
                if (deviceModel != null) {
                    m646a.m655b(deviceModel);
                    C2577al.m781a("[param] Set device model: %s", deviceModel);
                }
                m646a.f1030f = buglyStrategy.isUploadProcess();
                C2580ao.f1129b = buglyStrategy.isBuglyLogUpload();
            }
            for (int i = 0; i < f1468b.size(); i++) {
                try {
                    if (m1135a.m1148b(f1468b.get(i).f1466id)) {
                        f1468b.get(i).init(m811a, z, buglyStrategy);
                    }
                } catch (Throwable th) {
                    if (!C2577al.m782a(th)) {
                        th.printStackTrace();
                    }
                }
            }
            C2631s.m1117a(m811a, buglyStrategy);
            long appReportDelay = buglyStrategy != null ? buglyStrategy.getAppReportDelay() : 0L;
            final C2568ac m710a = C2568ac.m710a();
            m710a.f1058c.m775a(new Thread() { // from class: com.tencent.bugly.proguard.ac.1
                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    try {
                        Map<String, byte[]> m1172a2 = C2635w.m1154a().m1172a(C2568ac.f1054a, (InterfaceC2634v) null);
                        if (m1172a2 != null) {
                            byte[] bArr2 = m1172a2.get(DeviceRequestsHelper.DEVICE_INFO_DEVICE);
                            byte[] bArr3 = m1172a2.get("gateway");
                            if (bArr2 != null) {
                                C2566aa.m646a(C2568ac.this.f1062h).m660d(new String(bArr2));
                            }
                            if (bArr3 != null) {
                                C2566aa.m646a(C2568ac.this.f1062h).m658c(new String(bArr3));
                            }
                        }
                        C2568ac.this.f1061g = C2568ac.m714d();
                        if (C2568ac.this.f1061g != null) {
                            if (C2581ap.m844b(C2568ac.f1057i) || !C2581ap.m852d(C2568ac.f1057i)) {
                                C2568ac.this.f1061g.f884q = StrategyBean.f868a;
                                C2568ac.this.f1061g.f885r = StrategyBean.f869b;
                            } else {
                                C2568ac.this.f1061g.f884q = C2568ac.f1057i;
                                C2568ac.this.f1061g.f885r = C2568ac.f1057i;
                            }
                        }
                    } catch (Throwable th2) {
                        if (!C2577al.m782a(th2)) {
                            th2.printStackTrace();
                        }
                    }
                    C2568ac c2568ac = C2568ac.this;
                    c2568ac.m716a(c2568ac.f1061g, false);
                }
            }, appReportDelay);
            C2577al.m783b("[init] Bugly initialization finished.", new Object[0]);
        }
    }

    /* renamed from: a */
    public static synchronized void m1091a(AbstractC2627o abstractC2627o) {
        synchronized (C2628p.class) {
            if (!f1468b.contains(abstractC2627o)) {
                f1468b.add(abstractC2627o);
            }
        }
    }
}
