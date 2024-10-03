package com.oversea.ab_firstplatform.plugin.bugly;

import android.content.Context;
import com.oversea.ab_firstarea.channel.ProjectType;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes2.dex */
public class BuglyLibUtil {
    private static volatile BuglyLibUtil buglyLibUtil;
    private static AbstractBuglyManager mAbstractBuglyManager;

    public static BuglyLibUtil getInstance() {
        if (buglyLibUtil == null) {
            synchronized (BuglyLibUtil.class) {
                if (buglyLibUtil == null) {
                    buglyLibUtil = new BuglyLibUtil();
                    init();
                }
            }
        }
        return buglyLibUtil;
    }

    private static void init() {
        try {
            if (ProjectType.ZIAN.equals(ProjectType.pType)) {
                LLog.i_noControl("ZIAN");
            } else {
                mAbstractBuglyManager = (AbstractBuglyManager) Class.forName("com.fifun.ffoversea_bugly.BuglyManager").newInstance();
            }
        } catch (Exception unused) {
        }
    }

    public void init(Context context) {
        AbstractBuglyManager abstractBuglyManager = mAbstractBuglyManager;
        if (abstractBuglyManager != null) {
            abstractBuglyManager.initCrashReport(context);
        }
    }

    public void setBuglyUserId(String str) {
        AbstractBuglyManager abstractBuglyManager = mAbstractBuglyManager;
        if (abstractBuglyManager == null || str == null) {
            return;
        }
        abstractBuglyManager.setBuglyUserId(str);
    }
}
