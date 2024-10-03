package com.google.androidgamesdk;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes2.dex */
public class SwappyDisplayManager implements DisplayManager.DisplayListener {
    private Activity mActivity;
    private long mCookie;
    private Display.Mode mCurrentMode;
    private C1108a mLooper;
    private WindowManager mWindowManager;
    private final String LOG_TAG = "SwappyDisplayManager";
    private final boolean DEBUG = false;
    private final long ONE_MS_IN_NS = 1000000;
    private final long ONE_S_IN_NS = 1000000000;

    /* renamed from: com.google.androidgamesdk.SwappyDisplayManager$a */
    /* loaded from: classes2.dex */
    private class C1108a extends Thread {

        /* renamed from: a */
        public Handler f541a;

        /* renamed from: c */
        private Lock f543c;

        /* renamed from: d */
        private Condition f544d;

        private C1108a() {
            ReentrantLock reentrantLock = new ReentrantLock();
            this.f543c = reentrantLock;
            this.f544d = reentrantLock.newCondition();
        }

        /* synthetic */ C1108a(SwappyDisplayManager swappyDisplayManager, byte b) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Log.i("SwappyDisplayManager", "Starting looper thread");
            this.f543c.lock();
            Looper.prepare();
            this.f541a = new Handler();
            this.f544d.signal();
            this.f543c.unlock();
            Looper.loop();
            Log.i("SwappyDisplayManager", "Terminating looper thread");
        }

        @Override // java.lang.Thread
        public final void start() {
            this.f543c.lock();
            super.start();
            try {
                this.f544d.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.f543c.unlock();
        }
    }

    public SwappyDisplayManager(long j, Activity activity) {
        String string;
        byte b = 0;
        try {
            ActivityInfo activityInfo = activity.getPackageManager().getActivityInfo(activity.getIntent().getComponent(), 128);
            if (activityInfo.metaData != null && (string = activityInfo.metaData.getString("android.app.lib_name")) != null) {
                System.loadLibrary(string);
            }
        } catch (Throwable th) {
            Log.e("SwappyDisplayManager", th.getMessage());
        }
        this.mCookie = j;
        this.mActivity = activity;
        WindowManager windowManager = (WindowManager) activity.getSystemService(WindowManager.class);
        this.mWindowManager = windowManager;
        Display defaultDisplay = windowManager.getDefaultDisplay();
        this.mCurrentMode = defaultDisplay.getMode();
        updateSupportedRefreshRates(defaultDisplay);
        DisplayManager displayManager = (DisplayManager) this.mActivity.getSystemService(DisplayManager.class);
        synchronized (this) {
            C1108a c1108a = new C1108a(this, b);
            this.mLooper = c1108a;
            c1108a.start();
            displayManager.registerDisplayListener(this, this.mLooper.f541a);
        }
    }

    private boolean callNativeCallback() {
        if (Build.VERSION.SDK_INT >= 29) {
            return Build.VERSION.SDK_INT == 29 && Build.VERSION.PREVIEW_SDK_INT == 0;
        }
        return true;
    }

    private boolean modeMatchesCurrentResolution(Display.Mode mode) {
        return mode.getPhysicalHeight() == this.mCurrentMode.getPhysicalHeight() && mode.getPhysicalWidth() == this.mCurrentMode.getPhysicalWidth();
    }

    private native void nOnRefreshRateChanged(long j, long j2, long j3, long j4);

    private native void nSetSupportedRefreshRates(long j, long[] jArr, int[] iArr);

    private void updateSupportedRefreshRates(Display display) {
        Display.Mode[] supportedModes = display.getSupportedModes();
        int i = 0;
        for (Display.Mode mode : supportedModes) {
            if (modeMatchesCurrentResolution(mode)) {
                i++;
            }
        }
        long[] jArr = new long[i];
        int[] iArr = new int[i];
        int i2 = 0;
        for (int i3 = 0; i3 < supportedModes.length; i3++) {
            if (modeMatchesCurrentResolution(supportedModes[i3])) {
                jArr[i2] = 1.0E9f / supportedModes[i3].getRefreshRate();
                iArr[i2] = supportedModes[i3].getModeId();
                i2++;
            }
        }
        nSetSupportedRefreshRates(this.mCookie, jArr, iArr);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i) {
        synchronized (this) {
            Display defaultDisplay = this.mWindowManager.getDefaultDisplay();
            float refreshRate = defaultDisplay.getRefreshRate();
            Display.Mode mode = defaultDisplay.getMode();
            boolean z = true;
            boolean z2 = (mode.getPhysicalWidth() != this.mCurrentMode.getPhysicalWidth()) | (mode.getPhysicalHeight() != this.mCurrentMode.getPhysicalHeight());
            if (refreshRate == this.mCurrentMode.getRefreshRate()) {
                z = false;
            }
            this.mCurrentMode = mode;
            if (z2) {
                updateSupportedRefreshRates(defaultDisplay);
            }
            if (callNativeCallback() && z) {
                long j = 1.0E9f / refreshRate;
                nOnRefreshRateChanged(this.mCookie, j, defaultDisplay.getAppVsyncOffsetNanos(), j - (this.mWindowManager.getDefaultDisplay().getPresentationDeadlineNanos() - 1000000));
            }
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i) {
    }

    public void setPreferredRefreshRate(final int i) {
        this.mActivity.runOnUiThread(new Runnable() { // from class: com.google.androidgamesdk.SwappyDisplayManager.1
            @Override // java.lang.Runnable
            public final void run() {
                Window window = SwappyDisplayManager.this.mActivity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.preferredDisplayModeId = i;
                window.setAttributes(attributes);
            }
        });
    }

    public void terminate() {
        this.mLooper.f541a.getLooper().quit();
    }
}
