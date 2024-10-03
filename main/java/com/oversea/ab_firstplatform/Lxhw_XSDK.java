package com.oversea.ab_firstplatform;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.oversea.ab_firstarea.utils.DebugModeManager;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.oversea.ab_firstplatform.init.Lxhw_SDKListener;
import com.oversea.ab_firstplatform.lifecycle.XActivityCallback;
import com.oversea.ab_firstplatform.plugin.bugly.BuglyLibUtil;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_FUser;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;
import com.xsdk.ab_firstbase.statisics.util.ContextHolder;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.languagelib.MultiLanguageUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class Lxhw_XSDK {
    private static Lxhw_XSDK mInstance;
    private Application application;
    private Activity context;
    private boolean isBackground;
    private Lxhw_SDKListener listener;
    private int countActivity = 0;
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private List<XActivityCallback> activityCallbacks = new ArrayList(1);

    public void onAppConfigurationChanged(Configuration configuration) {
    }

    static /* synthetic */ int access$108(Lxhw_XSDK lxhw_XSDK) {
        int i = lxhw_XSDK.countActivity;
        lxhw_XSDK.countActivity = i + 1;
        return i;
    }

    static /* synthetic */ int access$110(Lxhw_XSDK lxhw_XSDK) {
        int i = lxhw_XSDK.countActivity;
        lxhw_XSDK.countActivity = i - 1;
        return i;
    }

    private Lxhw_XSDK() {
    }

    public static Lxhw_XSDK getInstance() {
        if (mInstance == null) {
            mInstance = new Lxhw_XSDK();
        }
        return mInstance;
    }

    public void setSDKListener(Lxhw_SDKListener lxhw_SDKListener) {
        if (lxhw_SDKListener != null) {
            this.listener = lxhw_SDKListener;
        }
    }

    public void setActivityCallback(XActivityCallback xActivityCallback) {
        if (this.activityCallbacks.contains(xActivityCallback) || xActivityCallback == null) {
            return;
        }
        this.activityCallbacks.add(xActivityCallback);
    }

    public Application getApplication() {
        return this.application;
    }

    public void onAppCreate(Application application) {
        this.application = application;
        ContextHolder.init(application);
        initCla(application);
        MultiLanguageUtil.init(application);
        BuglyLibUtil.getInstance().init(application);
        application.registerActivityLifecycleCallbacks(new SDKActivityLifecycleListener());
    }

    public void onAppAttachBaseContext(Context context) {
        Lxhw_AppInfoDecorator.init(context);
    }

    public void init(Activity activity) {
        this.context = activity;
    }

    public void initCla(Context context) {
        Lxhw_FUser.getInstance().init();
        Lxhw_FUser.getInstance().initApplication(context);
    }

    public Activity getContext() {
        return this.context;
    }

    public void onResult(int i, String str) {
        Lxhw_SDKListener lxhw_SDKListener = this.listener;
        if (lxhw_SDKListener != null) {
            lxhw_SDKListener.onResult(i, str);
        } else {
            LLog.e_noControl("onResult listener null");
        }
    }

    public void onAuthResult(int i) {
        Lxhw_SDKListener lxhw_SDKListener = this.listener;
        if (lxhw_SDKListener != null) {
            lxhw_SDKListener.onAuthResult(i);
        } else {
            LLog.e_noControl("onAuthResult listener null");
        }
    }

    public void onShareResult(boolean z) {
        Lxhw_SDKListener lxhw_SDKListener = this.listener;
        if (lxhw_SDKListener != null) {
            lxhw_SDKListener.onShareResult(z);
        } else {
            LLog.e_noControl("onAuthResult listener null");
        }
    }

    public void onDianzanResult(boolean z) {
        Lxhw_SDKListener lxhw_SDKListener = this.listener;
        if (lxhw_SDKListener != null) {
            lxhw_SDKListener.onDianzanResult(z);
        } else {
            LLog.e_noControl("onAuthResult listener null");
        }
    }

    public void onADResult(boolean z, String str) {
        Lxhw_SDKListener lxhw_SDKListener = this.listener;
        if (lxhw_SDKListener != null) {
            lxhw_SDKListener.onADResult(z, str);
        } else {
            LLog.e_noControl("onADResult listener null");
        }
    }

    public void onGiftGetResult(int i, String str) {
        Lxhw_SDKListener lxhw_SDKListener = this.listener;
        if (lxhw_SDKListener != null) {
            lxhw_SDKListener.onGiftGetResult(i, str);
        } else {
            LLog.e_noControl("onADResult listener null");
        }
    }

    public void onBackServiceResult(Lxhw_XUserInfo lxhw_XUserInfo) {
        Lxhw_SDKListener lxhw_SDKListener = this.listener;
        if (lxhw_SDKListener != null) {
            lxhw_SDKListener.onBackServiceResult(lxhw_XUserInfo);
        } else {
            LLog.e_noControl("onADResult listener null");
        }
    }

    public void onPingFenResult(boolean z) {
        Lxhw_SDKListener lxhw_SDKListener = this.listener;
        if (lxhw_SDKListener != null) {
            lxhw_SDKListener.onPingFenResult(z);
        } else {
            LLog.e_noControl("onAuthResult listener null");
        }
    }

    public void onUploadHeadResult(boolean z, String str) {
        Lxhw_SDKListener lxhw_SDKListener = this.listener;
        if (lxhw_SDKListener != null) {
            lxhw_SDKListener.onUploadHeadResult(z, str);
        } else {
            LLog.e_noControl("onAuthResult listener null");
        }
    }

    public void onDownHeadResult(boolean z, String str) {
        Lxhw_SDKListener lxhw_SDKListener = this.listener;
        if (lxhw_SDKListener != null) {
            lxhw_SDKListener.onDownHeadResult(z, str);
        } else {
            LLog.e_noControl("onAuthResult listener null");
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onActivityResult(i, i2, intent);
            }
        }
    }

    public void onBackPressed() {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onBackPressed();
            }
        }
    }

    public void onCreate(Bundle bundle) {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onCreate(bundle);
            }
        }
    }

    public void onStart() {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onStart();
            }
        }
    }

    public void onPause() {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onPause();
            }
        }
    }

    public void onResume() {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onResume();
            }
            this.activityCallbacks.size();
        }
    }

    public void onNewIntent(Intent intent) {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onNewIntent(intent);
            }
        }
    }

    public void onStop() {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onStop();
            }
        }
    }

    public void onDestroy() {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onDestroy();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onConfigurationChanged(configuration);
            }
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onRequestPermissionsResult(i, strArr, iArr);
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onSaveInstanceState(bundle);
            }
        }
    }

    public void onRestart() {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onRestart();
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        List<XActivityCallback> list = this.activityCallbacks;
        if (list != null) {
            Iterator<XActivityCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onWindowFocusChanged(z);
            }
        }
    }

    /* loaded from: classes2.dex */
    private class SDKActivityLifecycleListener implements Application.ActivityLifecycleCallbacks {
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        private SDKActivityLifecycleListener() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            Lxhw_XSDK.access$108(Lxhw_XSDK.this);
            if (Lxhw_XSDK.this.countActivity == 1 && Lxhw_XSDK.this.isBackground) {
                Lxhw_XSDK.this.isBackground = false;
                LLog.e_noControl("onActivityStarted activity");
                DebugModeManager.getInstance().upload("custom_sdk_foreground", System.currentTimeMillis() + "");
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            Lxhw_XSDK.access$110(Lxhw_XSDK.this);
            if (Lxhw_XSDK.this.countActivity > 0 || Lxhw_XSDK.this.isBackground) {
                return;
            }
            Lxhw_XSDK.this.isBackground = true;
            LLog.e_noControl("onActivityStopped activity");
            DebugModeManager.getInstance().upload("custom_sdk_background", System.currentTimeMillis() + "");
        }
    }
}
