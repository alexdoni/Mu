package com.unity3d.player;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.internal.security.CertificateUtil;
import com.unity3d.player.C2717m;
import com.unity3d.player.C2722r;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class UnityPlayer extends FrameLayout implements IUnityPlayerLifecycleEvents, InterfaceC2710f {
    private static final int ANR_TIMEOUT_SECONDS = 4;
    private static final int RUN_STATE_CHANGED_MSG_CODE = 2269;
    private static final String SPLASH_ENABLE_METADATA_NAME = "unity.splash-enable";
    private static final String SPLASH_MODE_METADATA_NAME = "unity.splash-mode";
    private static final String TANGO_ENABLE_METADATA_NAME = "unity.tango-enable";
    public static Activity currentActivity;
    private Context mContext;
    private SurfaceView mGlView;
    private int mInitialScreenOrientation;
    private boolean mIsFullscreen;
    private BroadcastReceiver mKillingIsMyBusiness;
    private boolean mMainDisplayOverride;
    private int mNaturalOrientation;
    private OrientationEventListener mOrientationListener;
    private boolean mProcessKillRequested;
    private boolean mQuitting;
    DialogC2716l mSoftInputDialog;
    private C2719o mState;
    private C2722r mVideoPlayerProxy;
    private GoogleARCoreApi m_ARCoreApi;
    private boolean m_AddPhoneCallListener;
    private AudioVolumeHandler m_AudioVolumeHandler;
    private Camera2Wrapper m_Camera2Wrapper;
    private ClipboardManager m_ClipboardManager;
    private final ConcurrentLinkedQueue m_Events;
    private C2699a m_FakeListener;
    private HFPStatus m_HFPStatus;
    C2703e m_MainThread;
    private NetworkConnectivity m_NetworkConnectivity;
    private C2714j m_PersistentUnitySurface;
    private C2701c m_PhoneCallListener;
    private C2717m m_SplashScreen;
    private TelephonyManager m_TelephonyManager;
    private IUnityPlayerLifecycleEvents m_UnityPlayerLifecycleEvents;
    private Uri m_launchUri;

    /* renamed from: com.unity3d.player.UnityPlayer$a */
    /* loaded from: classes3.dex */
    class C2699a implements SensorEventListener {
        C2699a() {
        }

        @Override // android.hardware.SensorEventListener
        public final void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public final void onSensorChanged(SensorEvent sensorEvent) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* renamed from: com.unity3d.player.UnityPlayer$b */
    /* loaded from: classes3.dex */
    public static final class EnumC2700b {

        /* renamed from: a */
        public static final int f1731a = 1;

        /* renamed from: b */
        public static final int f1732b = 2;

        /* renamed from: c */
        public static final int f1733c = 3;

        /* renamed from: d */
        private static final /* synthetic */ int[] f1734d = {1, 2, 3};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.unity3d.player.UnityPlayer$c */
    /* loaded from: classes3.dex */
    public class C2701c extends PhoneStateListener {
        private C2701c() {
        }

        /* synthetic */ C2701c(UnityPlayer unityPlayer, byte b) {
            this();
        }

        @Override // android.telephony.PhoneStateListener
        public final void onCallStateChanged(int i, String str) {
            UnityPlayer.this.nativeMuteMasterAudio(i == 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.unity3d.player.UnityPlayer$d */
    /* loaded from: classes3.dex */
    public enum EnumC2702d {
        PAUSE,
        RESUME,
        QUIT,
        SURFACE_LOST,
        SURFACE_ACQUIRED,
        FOCUS_LOST,
        FOCUS_GAINED,
        NEXT_FRAME,
        URL_ACTIVATED,
        ORIENTATION_ANGLE_CHANGE
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.unity3d.player.UnityPlayer$e */
    /* loaded from: classes3.dex */
    public class C2703e extends Thread {

        /* renamed from: a */
        Handler f1747a;

        /* renamed from: b */
        boolean f1748b;

        /* renamed from: c */
        boolean f1749c;

        /* renamed from: d */
        int f1750d;

        /* renamed from: e */
        int f1751e;

        /* renamed from: f */
        int f1752f;

        /* renamed from: g */
        int f1753g;

        /* renamed from: h */
        int f1754h;

        private C2703e() {
            this.f1748b = false;
            this.f1749c = false;
            this.f1750d = EnumC2700b.f1732b;
            this.f1751e = 0;
            this.f1754h = 5;
        }

        /* synthetic */ C2703e(UnityPlayer unityPlayer, byte b) {
            this();
        }

        /* renamed from: a */
        private void m1274a(EnumC2702d enumC2702d) {
            Handler handler = this.f1747a;
            if (handler != null) {
                Message.obtain(handler, UnityPlayer.RUN_STATE_CHANGED_MSG_CODE, enumC2702d).sendToTarget();
            }
        }

        /* renamed from: a */
        public final void m1275a() {
            m1274a(EnumC2702d.QUIT);
        }

        /* renamed from: a */
        public final void m1276a(int i, int i2) {
            this.f1752f = i;
            this.f1753g = i2;
            m1274a(EnumC2702d.ORIENTATION_ANGLE_CHANGE);
        }

        /* renamed from: a */
        public final void m1277a(Runnable runnable) {
            if (this.f1747a == null) {
                return;
            }
            m1274a(EnumC2702d.PAUSE);
            Message.obtain(this.f1747a, runnable).sendToTarget();
        }

        /* renamed from: b */
        public final void m1278b() {
            m1274a(EnumC2702d.RESUME);
        }

        /* renamed from: b */
        public final void m1279b(Runnable runnable) {
            if (this.f1747a == null) {
                return;
            }
            m1274a(EnumC2702d.SURFACE_LOST);
            Message.obtain(this.f1747a, runnable).sendToTarget();
        }

        /* renamed from: c */
        public final void m1280c() {
            m1274a(EnumC2702d.FOCUS_GAINED);
        }

        /* renamed from: c */
        public final void m1281c(Runnable runnable) {
            Handler handler = this.f1747a;
            if (handler == null) {
                return;
            }
            Message.obtain(handler, runnable).sendToTarget();
            m1274a(EnumC2702d.SURFACE_ACQUIRED);
        }

        /* renamed from: d */
        public final void m1282d() {
            m1274a(EnumC2702d.FOCUS_LOST);
        }

        /* renamed from: d */
        public final void m1283d(Runnable runnable) {
            Handler handler = this.f1747a;
            if (handler != null) {
                Message.obtain(handler, runnable).sendToTarget();
            }
        }

        /* renamed from: e */
        public final void m1284e() {
            m1274a(EnumC2702d.URL_ACTIVATED);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            setName("UnityMain");
            Looper.prepare();
            this.f1747a = new Handler(new Handler.Callback() { // from class: com.unity3d.player.UnityPlayer.e.1
                /* renamed from: a */
                private void m1285a() {
                    if (C2703e.this.f1750d == EnumC2700b.f1733c && C2703e.this.f1749c) {
                        UnityPlayer.this.nativeFocusChanged(true);
                        C2703e.this.f1750d = EnumC2700b.f1731a;
                    }
                }

                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    if (message.what != UnityPlayer.RUN_STATE_CHANGED_MSG_CODE) {
                        return false;
                    }
                    EnumC2702d enumC2702d = (EnumC2702d) message.obj;
                    if (enumC2702d == EnumC2702d.NEXT_FRAME) {
                        C2703e.this.f1751e--;
                        UnityPlayer.this.executeGLThreadJobs();
                        if (!C2703e.this.f1748b || !C2703e.this.f1749c) {
                            return true;
                        }
                        if (C2703e.this.f1754h >= 0) {
                            if (C2703e.this.f1754h == 0 && UnityPlayer.this.getSplashEnabled()) {
                                UnityPlayer.this.DisableStaticSplashScreen();
                            }
                            C2703e.this.f1754h--;
                        }
                        if (!UnityPlayer.this.isFinishing() && !UnityPlayer.this.nativeRender()) {
                            UnityPlayer.this.finish();
                        }
                    } else if (enumC2702d == EnumC2702d.QUIT) {
                        Looper.myLooper().quit();
                    } else if (enumC2702d == EnumC2702d.RESUME) {
                        C2703e.this.f1748b = true;
                    } else if (enumC2702d == EnumC2702d.PAUSE) {
                        C2703e.this.f1748b = false;
                    } else if (enumC2702d == EnumC2702d.SURFACE_LOST) {
                        C2703e.this.f1749c = false;
                    } else {
                        if (enumC2702d == EnumC2702d.SURFACE_ACQUIRED) {
                            C2703e.this.f1749c = true;
                        } else if (enumC2702d == EnumC2702d.FOCUS_LOST) {
                            if (C2703e.this.f1750d == EnumC2700b.f1731a) {
                                UnityPlayer.this.nativeFocusChanged(false);
                            }
                            C2703e.this.f1750d = EnumC2700b.f1732b;
                        } else if (enumC2702d == EnumC2702d.FOCUS_GAINED) {
                            C2703e.this.f1750d = EnumC2700b.f1733c;
                        } else if (enumC2702d == EnumC2702d.URL_ACTIVATED) {
                            UnityPlayer.this.nativeSetLaunchURL(UnityPlayer.this.getLaunchURL());
                        } else if (enumC2702d == EnumC2702d.ORIENTATION_ANGLE_CHANGE) {
                            UnityPlayer.this.nativeOrientationChanged(C2703e.this.f1752f, C2703e.this.f1753g);
                        }
                        m1285a();
                    }
                    if (C2703e.this.f1748b && C2703e.this.f1751e <= 0) {
                        Message.obtain(C2703e.this.f1747a, UnityPlayer.RUN_STATE_CHANGED_MSG_CODE, EnumC2702d.NEXT_FRAME).sendToTarget();
                        C2703e.this.f1751e++;
                    }
                    return true;
                }
            });
            Looper.loop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.unity3d.player.UnityPlayer$f */
    /* loaded from: classes3.dex */
    public abstract class AbstractRunnableC2704f implements Runnable {
        private AbstractRunnableC2704f() {
        }

        /* synthetic */ AbstractRunnableC2704f(UnityPlayer unityPlayer, byte b) {
            this();
        }

        /* renamed from: a */
        public abstract void mo1272a();

        @Override // java.lang.Runnable
        public final void run() {
            if (UnityPlayer.this.isFinishing()) {
                return;
            }
            mo1272a();
        }
    }

    static {
        new C2718n().m1351a();
    }

    public UnityPlayer(Context context) {
        this(context, null);
    }

    public UnityPlayer(Context context, IUnityPlayerLifecycleEvents iUnityPlayerLifecycleEvents) {
        super(context);
        this.mInitialScreenOrientation = -1;
        byte b = 0;
        this.mMainDisplayOverride = false;
        this.mIsFullscreen = true;
        this.mState = new C2719o();
        this.m_Events = new ConcurrentLinkedQueue();
        this.mKillingIsMyBusiness = null;
        this.mOrientationListener = null;
        this.m_MainThread = new C2703e(this, b);
        this.m_AddPhoneCallListener = false;
        this.m_PhoneCallListener = new C2701c(this, b);
        this.m_ARCoreApi = null;
        this.m_FakeListener = new C2699a();
        this.m_Camera2Wrapper = null;
        this.m_HFPStatus = null;
        this.m_AudioVolumeHandler = null;
        this.m_launchUri = null;
        this.m_NetworkConnectivity = null;
        this.mProcessKillRequested = true;
        this.mSoftInputDialog = null;
        this.m_UnityPlayerLifecycleEvents = iUnityPlayerLifecycleEvents;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            currentActivity = activity;
            this.mInitialScreenOrientation = activity.getRequestedOrientation();
            this.m_launchUri = currentActivity.getIntent().getData();
        }
        EarlyEnableFullScreenIfVrLaunched(currentActivity);
        this.mContext = context;
        this.mNaturalOrientation = getNaturalOrientation(getResources().getConfiguration().orientation);
        if (currentActivity != null && getSplashEnabled()) {
            C2717m c2717m = new C2717m(this.mContext, C2717m.a.m1350a()[getSplashMode()]);
            this.m_SplashScreen = c2717m;
            addView(c2717m);
        }
        String loadNative = loadNative(this.mContext.getApplicationInfo());
        if (currentActivity != null) {
            this.m_PersistentUnitySurface = new C2714j(this.mContext);
        }
        if (!C2719o.m1354c()) {
            C2711g.Log(6, "Your hardware does not support this application.");
            AlertDialog create = new AlertDialog.Builder(this.mContext).setTitle("Failure to initialize!").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.unity3d.player.UnityPlayer.1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    UnityPlayer.this.finish();
                }
            }).setMessage("Your hardware does not support this application.\n\n" + loadNative + "\n\n Press OK to quit.").create();
            create.setCancelable(false);
            create.show();
            return;
        }
        initJni(context);
        this.mState.m1357c(true);
        SurfaceView CreateGlView = CreateGlView();
        this.mGlView = CreateGlView;
        CreateGlView.setContentDescription(GetGlViewContentDescription(context));
        addView(this.mGlView);
        bringChildToFront(this.m_SplashScreen);
        this.mQuitting = false;
        hideStatusBar();
        this.m_TelephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        this.m_ClipboardManager = (ClipboardManager) this.mContext.getSystemService("clipboard");
        this.m_Camera2Wrapper = new Camera2Wrapper(this.mContext);
        this.m_HFPStatus = new HFPStatus(this.mContext);
        this.m_MainThread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SurfaceView CreateGlView() {
        SurfaceView surfaceView = new SurfaceView(this.mContext);
        surfaceView.setId(this.mContext.getResources().getIdentifier("unitySurfaceView", "id", this.mContext.getPackageName()));
        if (IsWindowTranslucent()) {
            surfaceView.getHolder().setFormat(-3);
            surfaceView.setZOrderOnTop(true);
        } else {
            surfaceView.getHolder().setFormat(-1);
        }
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() { // from class: com.unity3d.player.UnityPlayer.21
            @Override // android.view.SurfaceHolder.Callback
            public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                UnityPlayer.this.updateGLDisplay(0, surfaceHolder.getSurface());
                UnityPlayer.this.sendSurfaceChangedEvent();
            }

            @Override // android.view.SurfaceHolder.Callback
            public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                UnityPlayer.this.updateGLDisplay(0, surfaceHolder.getSurface());
                if (UnityPlayer.this.m_PersistentUnitySurface != null) {
                    UnityPlayer.this.m_PersistentUnitySurface.m1334a(UnityPlayer.this);
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                if (UnityPlayer.this.m_PersistentUnitySurface != null) {
                    UnityPlayer.this.m_PersistentUnitySurface.m1333a(UnityPlayer.this.mGlView);
                }
                UnityPlayer.this.updateGLDisplay(0, null);
            }
        });
        surfaceView.setFocusable(true);
        surfaceView.setFocusableInTouchMode(true);
        return surfaceView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void DisableStaticSplashScreen() {
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.UnityPlayer.20
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer unityPlayer = UnityPlayer.this;
                unityPlayer.removeView(unityPlayer.m_SplashScreen);
                UnityPlayer.this.m_SplashScreen = null;
            }
        });
    }

    private void EarlyEnableFullScreenIfVrLaunched(Activity activity) {
        View decorView;
        if (activity == null || !activity.getIntent().getBooleanExtra("android.intent.extra.VR_LAUNCH", false) || activity.getWindow() == null || (decorView = activity.getWindow().getDecorView()) == null) {
            return;
        }
        decorView.setSystemUiVisibility(7);
    }

    private String GetGlViewContentDescription(Context context) {
        return context.getResources().getString(context.getResources().getIdentifier("game_view_content_description", TypedValues.Custom.S_STRING, context.getPackageName()));
    }

    private boolean IsWindowTranslucent() {
        if (currentActivity == null) {
            return false;
        }
        TypedValue typedValue = new TypedValue();
        return currentActivity.getTheme().resolveAttribute(R.attr.windowIsTranslucent, typedValue, true) && typedValue.type == 18 && typedValue.data != 0;
    }

    public static void UnitySendMessage(String str, String str2, String str3) {
        if (C2719o.m1354c()) {
            try {
                nativeUnitySendMessage(str, str2, str3.getBytes("UTF-8"));
                return;
            } catch (UnsupportedEncodingException unused) {
                return;
            }
        }
        C2711g.Log(5, "Native libraries not loaded - dropping message for " + str + "." + str2);
    }

    private void checkResumePlayer() {
        Activity activity = currentActivity;
        if (this.mState.m1361e(activity != null ? MultiWindowSupport.getAllowResizableWindow(activity) : false)) {
            this.mState.m1358d(true);
            queueGLThreadEvent(new Runnable() { // from class: com.unity3d.player.UnityPlayer.3
                @Override // java.lang.Runnable
                public final void run() {
                    UnityPlayer.this.nativeResume();
                    UnityPlayer.this.runOnUiThread(new Runnable() { // from class: com.unity3d.player.UnityPlayer.3.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (UnityPlayer.this.m_PersistentUnitySurface != null) {
                                UnityPlayer.this.m_PersistentUnitySurface.m1335b(UnityPlayer.this);
                            }
                        }
                    });
                }
            });
            this.m_MainThread.m1278b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish() {
        Context context = this.mContext;
        if (!(context instanceof Activity) || ((Activity) context).isFinishing()) {
            return;
        }
        ((Activity) this.mContext).finish();
    }

    private ApplicationInfo getApplicationInfo() {
        return this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
    }

    private int getNaturalOrientation(int i) {
        int rotation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        if ((rotation == 0 || rotation == 2) && i == 2) {
            return 0;
        }
        return ((rotation == 1 || rotation == 3) && i == 1) ? 0 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getSplashEnabled() {
        try {
            return getApplicationInfo().metaData.getBoolean(SPLASH_ENABLE_METADATA_NAME);
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean getTangoEnabled() {
        try {
            return getApplicationInfo().metaData.getBoolean(TANGO_ENABLE_METADATA_NAME);
        } catch (Exception unused) {
            return false;
        }
    }

    private void hideStatusBar() {
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).getWindow().setFlags(1024, 1024);
        }
    }

    private final native void initJni(Context context);

    protected static boolean loadLibraryStatic(String str) {
        StringBuilder sb;
        try {
            System.loadLibrary(str);
            return true;
        } catch (Exception e) {
            sb = new StringBuilder("Unknown error ");
            sb.append(e);
            C2711g.Log(6, sb.toString());
            return false;
        } catch (UnsatisfiedLinkError unused) {
            sb = new StringBuilder("Unable to find ");
            sb.append(str);
            C2711g.Log(6, sb.toString());
            return false;
        }
    }

    private static String loadNative(ApplicationInfo applicationInfo) {
        String unsatisfiedLinkError;
        String str = applicationInfo.nativeLibraryDir + "/libmain.so";
        try {
            System.loadLibrary("main");
            if (NativeLoader.load(applicationInfo.nativeLibraryDir)) {
                C2719o.m1352a();
                return "";
            }
            C2711g.Log(6, "NativeLoader.load failure, Unity libraries were not loaded.");
            return "NativeLoader.load failure, Unity libraries were not loaded.";
        } catch (SecurityException e) {
            unsatisfiedLinkError = e.toString();
            return logLoadLibMainError(str, unsatisfiedLinkError);
        } catch (UnsatisfiedLinkError e2) {
            unsatisfiedLinkError = e2.toString();
            return logLoadLibMainError(str, unsatisfiedLinkError);
        }
    }

    private static String logLoadLibMainError(String str, String str2) {
        String str3 = "Failed to load 'libmain.so'\n\n" + str2;
        C2711g.Log(6, str3);
        return str3;
    }

    private final native void nativeApplicationUnload();

    private final native boolean nativeDone();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeFocusChanged(boolean z);

    private final native boolean nativeInjectEvent(InputEvent inputEvent);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean nativeIsAutorotationOn();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeLowMemory();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeMuteMasterAudio(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeOrientationChanged(int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean nativePause();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeRecreateGfxState(int i, Surface surface);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean nativeRender();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeReportKeyboardConfigChanged();

    private final native void nativeRestartActivityIndicator();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeResume();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSendSurfaceChangedEvent();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetInputArea(int i, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetInputSelection(int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetInputString(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetKeyboardIsVisible(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetLaunchURL(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputCanceled();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputClosed();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputLostFocus();

    private static native void nativeUnitySendMessage(String str, String str2, byte[] bArr);

    private void pauseUnity() {
        reportSoftInputStr(null, 1, true);
        if (this.mState.m1362f()) {
            if (C2719o.m1354c()) {
                final Semaphore semaphore = new Semaphore(0);
                this.m_MainThread.m1277a(isFinishing() ? new Runnable() { // from class: com.unity3d.player.UnityPlayer.25
                    @Override // java.lang.Runnable
                    public final void run() {
                        UnityPlayer.this.shutdown();
                        semaphore.release();
                    }
                } : new Runnable() { // from class: com.unity3d.player.UnityPlayer.26
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (!UnityPlayer.this.nativePause()) {
                            semaphore.release();
                            return;
                        }
                        UnityPlayer.this.mQuitting = true;
                        UnityPlayer.this.shutdown();
                        semaphore.release(2);
                    }
                });
                try {
                    if (!semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                        C2711g.Log(5, "Timeout while trying to pause the Unity Engine.");
                    }
                } catch (InterruptedException unused) {
                    C2711g.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
                }
                if (semaphore.drainPermits() > 0) {
                    destroy();
                }
            }
            this.mState.m1358d(false);
            this.mState.m1356b(true);
            if (this.m_AddPhoneCallListener) {
                this.m_TelephonyManager.listen(this.m_PhoneCallListener, 0);
            }
        }
    }

    private void queueGLThreadEvent(AbstractRunnableC2704f abstractRunnableC2704f) {
        if (isFinishing()) {
            return;
        }
        queueGLThreadEvent((Runnable) abstractRunnableC2704f);
    }

    private void queueGLThreadEvent(Runnable runnable) {
        if (C2719o.m1354c()) {
            if (Thread.currentThread() == this.m_MainThread) {
                runnable.run();
            } else {
                this.m_Events.add(runnable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSurfaceChangedEvent() {
        if (C2719o.m1354c() && this.mState.m1360e()) {
            this.m_MainThread.m1283d(new Runnable() { // from class: com.unity3d.player.UnityPlayer.22
                @Override // java.lang.Runnable
                public final void run() {
                    UnityPlayer.this.nativeSendSurfaceChangedEvent();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shutdown() {
        this.mProcessKillRequested = nativeDone();
        this.mState.m1357c(false);
    }

    private void swapViews(View view, View view2) {
        boolean z;
        if (this.mState.m1359d()) {
            z = false;
        } else {
            pause();
            z = true;
        }
        if (view != null) {
            ViewParent parent = view.getParent();
            if (!(parent instanceof UnityPlayer) || ((UnityPlayer) parent) != this) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(view);
                }
                addView(view);
                bringChildToFront(view);
                view.setVisibility(0);
            }
        }
        if (view2 != null && view2.getParent() == this) {
            view2.setVisibility(8);
            removeView(view2);
        }
        if (z) {
            resume();
        }
    }

    private static void unloadNative() {
        if (C2719o.m1354c()) {
            if (!NativeLoader.unload()) {
                throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
            }
            C2719o.m1353b();
        }
    }

    private boolean updateDisplayInternal(final int i, final Surface surface) {
        if (!C2719o.m1354c() || !this.mState.m1360e()) {
            return false;
        }
        final Semaphore semaphore = new Semaphore(0);
        Runnable runnable = new Runnable() { // from class: com.unity3d.player.UnityPlayer.23
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.nativeRecreateGfxState(i, surface);
                semaphore.release();
            }
        };
        if (i == 0) {
            C2703e c2703e = this.m_MainThread;
            if (surface == null) {
                c2703e.m1279b(runnable);
            } else {
                c2703e.m1281c(runnable);
            }
        } else {
            runnable.run();
        }
        if (surface != null || i != 0) {
            return true;
        }
        try {
            if (semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                return true;
            }
            C2711g.Log(5, "Timeout while trying detaching primary window.");
            return true;
        } catch (InterruptedException unused) {
            C2711g.Log(5, "UI thread got interrupted while trying to detach the primary window from the Unity Engine.");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateGLDisplay(int i, Surface surface) {
        if (this.mMainDisplayOverride) {
            return;
        }
        updateDisplayInternal(i, surface);
    }

    protected void addPhoneCallListener() {
        this.m_AddPhoneCallListener = true;
        this.m_TelephonyManager.listen(this.m_PhoneCallListener, 32);
    }

    @Override // com.unity3d.player.InterfaceC2710f
    public boolean addViewToPlayer(View view, boolean z) {
        swapViews(view, z ? this.mGlView : null);
        boolean z2 = true;
        boolean z3 = view.getParent() == this;
        boolean z4 = z && this.mGlView.getParent() == null;
        boolean z5 = this.mGlView.getParent() == this;
        if (!z3 || (!z4 && !z5)) {
            z2 = false;
        }
        if (!z2) {
            if (!z3) {
                C2711g.Log(6, "addViewToPlayer: Failure adding view to hierarchy");
            }
            if (!z4 && !z5) {
                C2711g.Log(6, "addViewToPlayer: Failure removing old view from hierarchy");
            }
        }
        return z2;
    }

    public void configurationChanged(Configuration configuration) {
        SurfaceView surfaceView = this.mGlView;
        if (surfaceView instanceof SurfaceView) {
            surfaceView.getHolder().setSizeFromLayout();
        }
        C2722r c2722r = this.mVideoPlayerProxy;
        if (c2722r != null) {
            c2722r.m1389c();
        }
        GoogleVrProxy m1233b = GoogleVrApi.m1233b();
        if (m1233b != null) {
            m1233b.m1248c();
        }
    }

    public void destroy() {
        if (GoogleVrApi.m1233b() != null) {
            GoogleVrApi.m1231a();
        }
        C2714j c2714j = this.m_PersistentUnitySurface;
        if (c2714j != null) {
            c2714j.m1332a();
            this.m_PersistentUnitySurface = null;
        }
        Camera2Wrapper camera2Wrapper = this.m_Camera2Wrapper;
        if (camera2Wrapper != null) {
            camera2Wrapper.m1228a();
            this.m_Camera2Wrapper = null;
        }
        HFPStatus hFPStatus = this.m_HFPStatus;
        if (hFPStatus != null) {
            hFPStatus.m1256a();
            this.m_HFPStatus = null;
        }
        NetworkConnectivity networkConnectivity = this.m_NetworkConnectivity;
        if (networkConnectivity != null) {
            networkConnectivity.m1259b();
            this.m_NetworkConnectivity = null;
        }
        this.mQuitting = true;
        if (!this.mState.m1359d()) {
            pause();
        }
        this.m_MainThread.m1275a();
        try {
            this.m_MainThread.join(4000L);
        } catch (InterruptedException unused) {
            this.m_MainThread.interrupt();
        }
        BroadcastReceiver broadcastReceiver = this.mKillingIsMyBusiness;
        if (broadcastReceiver != null) {
            this.mContext.unregisterReceiver(broadcastReceiver);
        }
        this.mKillingIsMyBusiness = null;
        if (C2719o.m1354c()) {
            removeAllViews();
        }
        if (this.mProcessKillRequested) {
            IUnityPlayerLifecycleEvents iUnityPlayerLifecycleEvents = this.m_UnityPlayerLifecycleEvents;
            if (iUnityPlayerLifecycleEvents != null) {
                iUnityPlayerLifecycleEvents.onUnityPlayerQuitted();
            } else {
                onUnityPlayerQuitted();
            }
            kill();
        }
        unloadNative();
    }

    protected void disableLogger() {
        C2711g.f1813a = true;
    }

    public boolean displayChanged(int i, Surface surface) {
        if (i == 0) {
            this.mMainDisplayOverride = surface != null;
            runOnUiThread(new Runnable() { // from class: com.unity3d.player.UnityPlayer.24
                @Override // java.lang.Runnable
                public final void run() {
                    if (UnityPlayer.this.mMainDisplayOverride) {
                        UnityPlayer unityPlayer = UnityPlayer.this;
                        unityPlayer.removeView(unityPlayer.mGlView);
                    } else {
                        UnityPlayer unityPlayer2 = UnityPlayer.this;
                        unityPlayer2.addView(unityPlayer2.mGlView);
                    }
                }
            });
        }
        return updateDisplayInternal(i, surface);
    }

    protected void executeGLThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.m_Events.poll();
            if (runnable == null) {
                return;
            } else {
                runnable.run();
            }
        }
    }

    protected String getClipboardText() {
        ClipData primaryClip = this.m_ClipboardManager.getPrimaryClip();
        return primaryClip != null ? primaryClip.getItemAt(0).coerceToText(this.mContext).toString() : "";
    }

    protected String getKeyboardLayout() {
        DialogC2716l dialogC2716l = this.mSoftInputDialog;
        if (dialogC2716l == null) {
            return null;
        }
        return dialogC2716l.m1345a();
    }

    protected String getLaunchURL() {
        Uri uri = this.m_launchUri;
        if (uri != null) {
            return uri.toString();
        }
        return null;
    }

    protected int getNetworkConnectivity() {
        if (!C2715k.f1821c) {
            return 0;
        }
        if (this.m_NetworkConnectivity == null) {
            this.m_NetworkConnectivity = new NetworkConnectivity(this.mContext);
        }
        return this.m_NetworkConnectivity.m1258a();
    }

    public String getNetworkProxySettings(String str) {
        String str2;
        String str3;
        if (!str.startsWith("http:")) {
            if (str.startsWith("https:")) {
                str2 = "https.proxyHost";
                str3 = "https.proxyPort";
            }
            return null;
        }
        str2 = "http.proxyHost";
        str3 = "http.proxyPort";
        String property = System.getProperties().getProperty(str2);
        if (property != null && !"".equals(property)) {
            StringBuilder sb = new StringBuilder(property);
            String property2 = System.getProperties().getProperty(str3);
            if (property2 != null && !"".equals(property2)) {
                sb.append(CertificateUtil.DELIMITER);
                sb.append(property2);
            }
            String property3 = System.getProperties().getProperty("http.nonProxyHosts");
            if (property3 != null && !"".equals(property3)) {
                sb.append('\n');
                sb.append(property3);
            }
            return sb.toString();
        }
        return null;
    }

    public Bundle getSettings() {
        return Bundle.EMPTY;
    }

    protected int getSplashMode() {
        try {
            return getApplicationInfo().metaData.getInt(SPLASH_MODE_METADATA_NAME);
        } catch (Exception unused) {
            return 0;
        }
    }

    public View getView() {
        return this;
    }

    protected void hideSoftInput() {
        postOnUiThread(new Runnable() { // from class: com.unity3d.player.UnityPlayer.5
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.reportSoftInputArea(new Rect());
                UnityPlayer.this.reportSoftInputIsVisible(false);
                if (UnityPlayer.this.mSoftInputDialog != null) {
                    UnityPlayer.this.mSoftInputDialog.dismiss();
                    UnityPlayer.this.mSoftInputDialog = null;
                    UnityPlayer.this.nativeReportKeyboardConfigChanged();
                }
            }
        });
    }

    public void init(int i, boolean z) {
    }

    protected boolean initializeGoogleAr() {
        if (this.m_ARCoreApi != null || currentActivity == null || !getTangoEnabled()) {
            return false;
        }
        GoogleARCoreApi googleARCoreApi = new GoogleARCoreApi();
        this.m_ARCoreApi = googleARCoreApi;
        googleARCoreApi.initializeARCore(currentActivity);
        if (this.mState.m1359d()) {
            return false;
        }
        this.m_ARCoreApi.resumeARCore();
        return false;
    }

    protected boolean initializeGoogleVr() {
        final GoogleVrProxy m1233b = GoogleVrApi.m1233b();
        if (m1233b == null) {
            GoogleVrApi.m1232a(this);
            m1233b = GoogleVrApi.m1233b();
            if (m1233b == null) {
                C2711g.Log(6, "Unable to create Google VR subsystem.");
                return false;
            }
        }
        final Semaphore semaphore = new Semaphore(0);
        final Runnable runnable = new Runnable() { // from class: com.unity3d.player.UnityPlayer.14
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.injectEvent(new KeyEvent(0, 4));
                UnityPlayer.this.injectEvent(new KeyEvent(1, 4));
            }
        };
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.UnityPlayer.15
            @Override // java.lang.Runnable
            public final void run() {
                if (!m1233b.m1246a(UnityPlayer.currentActivity, UnityPlayer.this.mContext, UnityPlayer.this.CreateGlView(), runnable)) {
                    C2711g.Log(6, "Unable to initialize Google VR subsystem.");
                }
                if (UnityPlayer.currentActivity != null) {
                    m1233b.m1244a(UnityPlayer.currentActivity.getIntent());
                }
                semaphore.release();
            }
        });
        try {
            if (semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                return m1233b.m1245a();
            }
            C2711g.Log(5, "Timeout while trying to initialize Google VR.");
            return false;
        } catch (InterruptedException e) {
            C2711g.Log(5, "UI thread was interrupted while initializing Google VR. " + e.getLocalizedMessage());
            return false;
        }
    }

    public boolean injectEvent(InputEvent inputEvent) {
        if (C2719o.m1354c()) {
            return nativeInjectEvent(inputEvent);
        }
        return false;
    }

    protected boolean isFinishing() {
        if (!this.mQuitting) {
            Context context = this.mContext;
            boolean z = (context instanceof Activity) && ((Activity) context).isFinishing();
            this.mQuitting = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    protected void kill() {
        Process.killProcess(Process.myPid());
    }

    protected boolean loadLibrary(String str) {
        return loadLibraryStatic(str);
    }

    public void lowMemory() {
        if (C2719o.m1354c()) {
            queueGLThreadEvent(new Runnable() { // from class: com.unity3d.player.UnityPlayer.2
                @Override // java.lang.Runnable
                public final void run() {
                    UnityPlayer.this.nativeLowMemory();
                }
            });
        }
    }

    public void newIntent(Intent intent) {
        this.m_launchUri = intent.getData();
        this.m_MainThread.m1284e();
    }

    protected void notifyOnUnityPlayerUnloaded() {
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.UnityPlayer.18
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.pause();
                UnityPlayer.this.windowFocusChanged(false);
                if (UnityPlayer.this.m_UnityPlayerLifecycleEvents != null) {
                    UnityPlayer.this.m_UnityPlayerLifecycleEvents.onUnityPlayerUnloaded();
                } else {
                    UnityPlayer.this.onUnityPlayerUnloaded();
                }
            }
        });
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    @Override // com.unity3d.player.IUnityPlayerLifecycleEvents
    public void onUnityPlayerQuitted() {
    }

    @Override // com.unity3d.player.IUnityPlayerLifecycleEvents
    public void onUnityPlayerUnloaded() {
    }

    public void pause() {
        GoogleARCoreApi googleARCoreApi = this.m_ARCoreApi;
        if (googleARCoreApi != null) {
            googleARCoreApi.pauseARCore();
        }
        C2722r c2722r = this.mVideoPlayerProxy;
        if (c2722r != null) {
            c2722r.m1386a();
        }
        GoogleVrProxy m1233b = GoogleVrApi.m1233b();
        if (m1233b != null) {
            m1233b.pauseGvrLayout();
        }
        AudioVolumeHandler audioVolumeHandler = this.m_AudioVolumeHandler;
        if (audioVolumeHandler != null) {
            audioVolumeHandler.m1226a();
            this.m_AudioVolumeHandler = null;
        }
        pauseUnity();
    }

    void postOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public void quit() {
        destroy();
    }

    @Override // com.unity3d.player.InterfaceC2710f
    public void removeViewFromPlayer(View view) {
        swapViews(this.mGlView, view);
        boolean z = view.getParent() == null;
        boolean z2 = this.mGlView.getParent() == this;
        if (z && z2) {
            return;
        }
        if (!z) {
            C2711g.Log(6, "removeViewFromPlayer: Failure removing view from hierarchy");
        }
        if (z2) {
            return;
        }
        C2711g.Log(6, "removeVireFromPlayer: Failure agging old view to hierarchy");
    }

    @Override // com.unity3d.player.InterfaceC2710f
    public void reportError(String str, String str2) {
        C2711g.Log(6, str + ": " + str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportSoftInputArea(final Rect rect) {
        queueGLThreadEvent(new AbstractRunnableC2704f() { // from class: com.unity3d.player.UnityPlayer.12
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(UnityPlayer.this, (byte) 0);
            }

            @Override // com.unity3d.player.UnityPlayer.AbstractRunnableC2704f
            /* renamed from: a */
            public final void mo1272a() {
                UnityPlayer.this.nativeSetInputArea(rect.left, rect.top, rect.right, rect.bottom);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportSoftInputIsVisible(final boolean z) {
        queueGLThreadEvent(new AbstractRunnableC2704f() { // from class: com.unity3d.player.UnityPlayer.13
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(UnityPlayer.this, (byte) 0);
            }

            @Override // com.unity3d.player.UnityPlayer.AbstractRunnableC2704f
            /* renamed from: a */
            public final void mo1272a() {
                UnityPlayer.this.nativeSetKeyboardIsVisible(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportSoftInputSelection(final int i, final int i2) {
        queueGLThreadEvent(new AbstractRunnableC2704f() { // from class: com.unity3d.player.UnityPlayer.11
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(UnityPlayer.this, (byte) 0);
            }

            @Override // com.unity3d.player.UnityPlayer.AbstractRunnableC2704f
            /* renamed from: a */
            public final void mo1272a() {
                UnityPlayer.this.nativeSetInputSelection(i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportSoftInputStr(final String str, final int i, final boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        queueGLThreadEvent(new AbstractRunnableC2704f() { // from class: com.unity3d.player.UnityPlayer.10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(UnityPlayer.this, (byte) 0);
            }

            @Override // com.unity3d.player.UnityPlayer.AbstractRunnableC2704f
            /* renamed from: a */
            public final void mo1272a() {
                if (z) {
                    UnityPlayer.this.nativeSoftInputCanceled();
                } else {
                    String str2 = str;
                    if (str2 != null) {
                        UnityPlayer.this.nativeSetInputString(str2);
                    }
                }
                if (i == 1) {
                    UnityPlayer.this.nativeSoftInputClosed();
                }
            }
        });
    }

    protected void requestUserAuthorization(String str) {
        if (!C2715k.f1820b || str == null || str.isEmpty() || currentActivity == null) {
            return;
        }
        C2715k.f1822d.mo1329a(currentActivity, str);
    }

    public void resume() {
        GoogleARCoreApi googleARCoreApi = this.m_ARCoreApi;
        if (googleARCoreApi != null) {
            googleARCoreApi.resumeARCore();
        }
        this.mState.m1356b(false);
        C2722r c2722r = this.mVideoPlayerProxy;
        if (c2722r != null) {
            c2722r.m1388b();
        }
        checkResumePlayer();
        if (C2719o.m1354c()) {
            nativeRestartActivityIndicator();
        }
        GoogleVrProxy m1233b = GoogleVrApi.m1233b();
        if (m1233b != null) {
            m1233b.m1247b();
        }
        this.m_AudioVolumeHandler = new AudioVolumeHandler(this.mContext);
    }

    void runOnAnonymousThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    void runOnUiThread(Runnable runnable) {
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
        } else {
            C2711g.Log(5, "Not running Unity from an Activity; ignored...");
        }
    }

    protected void setCharacterLimit(final int i) {
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.UnityPlayer.7
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.mSoftInputDialog != null) {
                    UnityPlayer.this.mSoftInputDialog.m1346a(i);
                }
            }
        });
    }

    protected void setClipboardText(String str) {
        this.m_ClipboardManager.setPrimaryClip(ClipData.newPlainText("Text", str));
    }

    protected void setHideInputField(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.UnityPlayer.8
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.mSoftInputDialog != null) {
                    UnityPlayer.this.mSoftInputDialog.m1349a(z);
                }
            }
        });
    }

    protected void setSelection(final int i, final int i2) {
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.UnityPlayer.9
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.mSoftInputDialog != null) {
                    UnityPlayer.this.mSoftInputDialog.m1347a(i, i2);
                }
            }
        });
    }

    protected void setSoftInputStr(final String str) {
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.UnityPlayer.6
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.mSoftInputDialog == null || str == null) {
                    return;
                }
                UnityPlayer.this.mSoftInputDialog.m1348a(str);
            }
        });
    }

    protected void showSoftInput(final String str, final int i, final boolean z, final boolean z2, final boolean z3, final boolean z4, final String str2, final int i2, final boolean z5, final boolean z6) {
        postOnUiThread(new Runnable() { // from class: com.unity3d.player.UnityPlayer.4
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.mSoftInputDialog = new DialogC2716l(UnityPlayer.this.mContext, this, str, i, z, z2, z3, str2, i2, z5, z6);
                UnityPlayer.this.mSoftInputDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.unity3d.player.UnityPlayer.4.1
                    @Override // android.content.DialogInterface.OnCancelListener
                    public final void onCancel(DialogInterface dialogInterface) {
                        UnityPlayer.this.nativeSoftInputLostFocus();
                        UnityPlayer.this.reportSoftInputStr(null, 1, false);
                    }
                });
                UnityPlayer.this.mSoftInputDialog.show();
                UnityPlayer.this.nativeReportKeyboardConfigChanged();
            }
        });
    }

    protected boolean showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        if (this.mVideoPlayerProxy == null) {
            this.mVideoPlayerProxy = new C2722r(this);
        }
        boolean m1387a = this.mVideoPlayerProxy.m1387a(this.mContext, str, i, i2, i3, z, i4, i5, new C2722r.a() { // from class: com.unity3d.player.UnityPlayer.16
            @Override // com.unity3d.player.C2722r.a
            /* renamed from: a */
            public final void mo1273a() {
                UnityPlayer.this.mVideoPlayerProxy = null;
            }
        });
        if (m1387a) {
            runOnUiThread(new Runnable() { // from class: com.unity3d.player.UnityPlayer.17
                @Override // java.lang.Runnable
                public final void run() {
                    if (UnityPlayer.this.nativeIsAutorotationOn() && (UnityPlayer.this.mContext instanceof Activity)) {
                        ((Activity) UnityPlayer.this.mContext).setRequestedOrientation(UnityPlayer.this.mInitialScreenOrientation);
                    }
                }
            });
        }
        return m1387a;
    }

    protected boolean skipPermissionsDialog() {
        if (!C2715k.f1820b || currentActivity == null) {
            return false;
        }
        return C2715k.f1822d.mo1330a(currentActivity);
    }

    public boolean startOrientationListener(int i) {
        String str;
        if (this.mOrientationListener != null) {
            str = "Orientation Listener already started.";
        } else {
            OrientationEventListener orientationEventListener = new OrientationEventListener(this.mContext, i) { // from class: com.unity3d.player.UnityPlayer.19
                @Override // android.view.OrientationEventListener
                public final void onOrientationChanged(int i2) {
                    UnityPlayer.this.m_MainThread.m1276a(UnityPlayer.this.mNaturalOrientation, i2);
                }
            };
            this.mOrientationListener = orientationEventListener;
            if (orientationEventListener.canDetectOrientation()) {
                this.mOrientationListener.enable();
                return true;
            }
            str = "Orientation Listener cannot detect orientation.";
        }
        C2711g.Log(5, str);
        return false;
    }

    public boolean stopOrientationListener() {
        OrientationEventListener orientationEventListener = this.mOrientationListener;
        if (orientationEventListener == null) {
            C2711g.Log(5, "Orientation Listener was not started.");
            return false;
        }
        orientationEventListener.disable();
        this.mOrientationListener = null;
        return true;
    }

    protected void toggleGyroscopeSensor(boolean z) {
        SensorManager sensorManager = (SensorManager) this.mContext.getSystemService("sensor");
        Sensor defaultSensor = sensorManager.getDefaultSensor(11);
        if (z) {
            sensorManager.registerListener(this.m_FakeListener, defaultSensor, 1);
        } else {
            sensorManager.unregisterListener(this.m_FakeListener);
        }
    }

    public void unload() {
        nativeApplicationUnload();
    }

    public void windowFocusChanged(boolean z) {
        this.mState.m1355a(z);
        if (this.mState.m1360e()) {
            DialogC2716l dialogC2716l = this.mSoftInputDialog;
            if (dialogC2716l == null || dialogC2716l.f1825a) {
                if (z) {
                    this.m_MainThread.m1280c();
                } else {
                    this.m_MainThread.m1282d();
                }
                checkResumePlayer();
            }
        }
    }
}
