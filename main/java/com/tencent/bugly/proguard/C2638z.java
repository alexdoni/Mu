package com.tencent.bugly.proguard;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.z */
/* loaded from: classes3.dex */
public final class C2638z {

    /* renamed from: a */
    public static final String[] f1552a = "@buglyAllChannel@".split(",");

    /* renamed from: b */
    public static final String[] f1553b = "@buglyAllChannelPriority@".split(",");

    /* renamed from: a */
    public static String m1180a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageName();
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    /* renamed from: b */
    public static PackageInfo m1184b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(m1180a(context), 0);
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m1179a(int i) {
        FileReader fileReader;
        Throwable th;
        try {
            fileReader = new FileReader("/proc/" + i + "/cmdline");
        } catch (Throwable th2) {
            fileReader = null;
            th = th2;
        }
        try {
            char[] cArr = new char[512];
            fileReader.read(cArr);
            int i2 = 0;
            while (i2 < 512 && cArr[i2] != 0) {
                i2++;
            }
            String substring = new String(cArr).substring(0, i2);
            try {
                fileReader.close();
            } catch (Throwable unused) {
            }
            return substring;
        } catch (Throwable th3) {
            th = th3;
            try {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
                String valueOf = String.valueOf(i);
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Throwable unused2) {
                    }
                }
                return valueOf;
            } catch (Throwable th4) {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Throwable unused3) {
                    }
                }
                throw th4;
            }
        }
    }

    /* renamed from: c */
    public static String m1185c(Context context) {
        CharSequence applicationLabel;
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (packageManager != null && applicationInfo != null && (applicationLabel = packageManager.getApplicationLabel(applicationInfo)) != null) {
                return applicationLabel.toString();
            }
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m1182a() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Field declaredField = cls.getDeclaredField("sCurrentActivityThread");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(cls);
            Field declaredField2 = cls.getDeclaredField("mActivities");
            declaredField2.setAccessible(true);
            for (Map.Entry entry : ((Map) declaredField2.get(obj)).entrySet()) {
                Field declaredField3 = Class.forName("android.app.ActivityThread$ActivityClientRecord").getDeclaredField("activity");
                declaredField3.setAccessible(true);
                Activity activity = (Activity) declaredField3.get(entry.getValue());
                Field declaredField4 = Activity.class.getDeclaredField("mResumed");
                declaredField4.setAccessible(true);
                if (((Boolean) declaredField4.get(activity)).booleanValue()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            C2577al.m784b(th);
            return true;
        }
    }

    /* renamed from: a */
    public static boolean m1183a(ActivityManager activityManager) {
        if (activityManager == null) {
            C2577al.m785c("is proc running, ActivityManager is null", new Object[0]);
            return true;
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            C2577al.m785c("running proc info list is empty, my proc not running.", new Object[0]);
            return false;
        }
        int myPid = Process.myPid();
        Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
        while (it.hasNext()) {
            if (it.next().pid == myPid) {
                C2577al.m785c("my proc is running.", new Object[0]);
                return true;
            }
        }
        C2577al.m785c("proc not in running proc info list, my proc not running.", new Object[0]);
        return false;
    }

    /* renamed from: d */
    public static Map<String, String> m1186d(Context context) {
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            Object obj = applicationInfo.metaData.get("BUGLY_DISABLE");
            if (obj != null) {
                hashMap.put("BUGLY_DISABLE", obj.toString());
            }
            Object obj2 = applicationInfo.metaData.get("BUGLY_APPID");
            if (obj2 != null) {
                hashMap.put("BUGLY_APPID", obj2.toString());
            }
            Object obj3 = applicationInfo.metaData.get("BUGLY_APP_CHANNEL");
            if (obj3 != null) {
                hashMap.put("BUGLY_APP_CHANNEL", obj3.toString());
            }
            Object obj4 = applicationInfo.metaData.get("BUGLY_APP_VERSION");
            if (obj4 != null) {
                hashMap.put("BUGLY_APP_VERSION", obj4.toString());
            }
            Object obj5 = applicationInfo.metaData.get("BUGLY_ENABLE_DEBUG");
            if (obj5 != null) {
                hashMap.put("BUGLY_ENABLE_DEBUG", obj5.toString());
            }
            Object obj6 = applicationInfo.metaData.get("com.tencent.rdm.uuid");
            if (obj6 != null) {
                hashMap.put("com.tencent.rdm.uuid", obj6.toString());
            }
            Object obj7 = applicationInfo.metaData.get("BUGLY_APP_BUILD_NO");
            if (obj7 != null) {
                hashMap.put("BUGLY_APP_BUILD_NO", obj7.toString());
            }
            Object obj8 = applicationInfo.metaData.get("BUGLY_AREA");
            if (obj8 != null) {
                hashMap.put("BUGLY_AREA", obj8.toString());
            }
            return hashMap;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static List<String> m1181a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            String str = map.get("BUGLY_DISABLE");
            if (str != null && str.length() != 0) {
                String[] split = str.split(",");
                for (int i = 0; i < split.length; i++) {
                    split[i] = split[i].trim();
                }
                return Arrays.asList(split);
            }
            return null;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
