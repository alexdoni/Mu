package com.fifun.ffoversea_bugly;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.oversea.ab_firstplatform.plugin.bugly.AbstractBuglyManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class BuglyManager extends AbstractBuglyManager {
    private static final String TAG = "BuglyManager";
    private static volatile BuglyManager instance;
    private boolean isInit;

    public static BuglyManager getInstance() {
        if (instance == null) {
            synchronized (BuglyManager.class) {
                if (instance == null) {
                    instance = new BuglyManager();
                }
            }
        }
        return instance;
    }

    @Override // com.oversea.ab_firstplatform.plugin.bugly.AbstractBuglyManager
    public void initCrashReport(Context context) {
        String stringFromMateData = Utils.getStringFromMateData(context, "BUGLY_APPID");
        if (TextUtils.isEmpty(stringFromMateData)) {
            LLog.e_noControl("bugly_appid is null!");
        } else {
            init(context, stringFromMateData, "", "");
        }
    }

    @Override // com.oversea.ab_firstplatform.plugin.bugly.AbstractBuglyManager
    public void init(Context context, String str) {
        init(context, str, "", "");
    }

    @Override // com.oversea.ab_firstplatform.plugin.bugly.AbstractBuglyManager
    public void setBuglyUserId(String str) {
        if (TextUtils.isEmpty(str) || !this.isInit) {
            return;
        }
        CrashReport.setUserId(str);
    }

    public void init(Context context, String str, String str2, String str3) {
        if (context == null) {
            LLog.e_Control("context is null");
            return;
        }
        CrashReport.UserStrategy userStrategy = new CrashReport.UserStrategy(context);
        String androidID = Utils.getAndroidID(context);
        userStrategy.setDeviceID(androidID);
        userStrategy.setDeviceModel(Utils.getPhoneModel());
        userStrategy.setAppReportDelay(5000L);
        if (!TextUtils.isEmpty(str3)) {
            userStrategy.setAppChannel(str3);
        }
        if (!TextUtils.isEmpty(str2)) {
            userStrategy.setAppVersion(str2);
        }
        String processName = getProcessName();
        userStrategy.setUploadProcess(TextUtils.isEmpty(processName) || processName.equals(context.getPackageName()));
        CrashReport.initCrashReport(context, str, false, userStrategy);
        CrashReport.putUserData(context, "AndroidId", androidID);
        CrashReport.setUserId(androidID);
        this.isInit = true;
    }

    private String getProcessName() {
        String processNameByActivityThread;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                processNameByActivityThread = Application.getProcessName();
            } else {
                processNameByActivityThread = getProcessNameByActivityThread();
            }
            return processNameByActivityThread;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getProcessNameByActivityThread() {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            return invoke instanceof String ? (String) invoke : "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }
}
