package com.mu;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mu.utility.MemoryUtility;
import com.mu.utility.MyOrientationDetector;
import com.mu.utility.SystemUtil;
import com.shenghe.common_lib.C2502R;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import org.json.JSONException;
import org.json.JSONObject;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes2.dex */
public abstract class MainActivityBase extends UnityPlayerActivity {
    public static String CALLBACK_BATTERY = "ACTION_BATTERY_CHANGED";
    public static String CALLBACK_BINDPHONE = "BindPhone";
    public static String CALLBACK_CHECKVERIFY = "OnAndroidCheckVerify";
    public static String CALLBACK_ClearAppMemory = "OnClearAppMemorySuc";
    public static String CALLBACK_DOWNLOADING = "DownLoading";
    public static String CALLBACK_GAMEOBJECT_NAME = "(Android_Callback)";
    public static String CALLBACK_INIT = "OnAndroidInitSuc";
    public static String CALLBACK_LOGIN = "OnAndroidLoginSuc";
    public static String CALLBACK_LOGOUT = "OnAndroidLogoutAccount";
    public static String CALLBACK_MOVESUCCESS = "CopyFileComplete";
    public static String CALLBACK_MOVING = "OnMovingFile";
    public static String CALLBACK_OPENQUITWND = "OpenGameQuitWnd";
    public static String CALLBACK_OnScreenOrientation = "OnScreenOrientation";
    public static String CALLBACK_PAY = "OnAndroidPaySuc";
    public static String CALLBACK_TOTALSIZE = "GetFileTotalSize";
    public static String CALLBACK_TRANSLATERESULT = "OnTranslateResult";
    public static String CALLBACK_VoicePermision = "OnVoicePermesionSuc";
    public static String TAG_LOG = "MY-LOG";
    public int Pid;
    private Handler handler;
    int intScale;
    OrientationEventListener mOrientationEventListener;
    MemoryUtility memoryUtility;
    private ProgressBar progressBar;

    /* renamed from: tm */
    TelephonyManager f738tm;
    private TextView tvPercent;
    private View view;
    int intLevel = -1;
    private ImageView bgView = null;
    AnimationDrawable animationDrawable = null;
    private ImageView srcView = null;
    private int progress = 0;
    private double total = 0.0d;
    boolean ishide = false;
    private BroadcastReceiver mBatInfoReveiver = new BroadcastReceiver() { // from class: com.mu.MainActivityBase.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                MainActivityBase.this.intLevel = intent.getIntExtra(FirebaseAnalytics.Param.LEVEL, 0);
                MainActivityBase.this.intScale = intent.getIntExtra("scale", 100);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("intLevel", MainActivityBase.this.intLevel);
                    jSONObject.put("intScale", MainActivityBase.this.intScale);
                    MainActivityBase.this.sendCallback(MainActivityBase.CALLBACK_BATTERY, jSONObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public abstract void AccountCancellation();

    public abstract void CallUserAgreement();

    public abstract String GetHotUpdateConfigUrl();

    public abstract String GetInstallResVersion();

    public abstract String GetLocalResVersion();

    public abstract String GetPid();

    public abstract String GetResUpdateUrl();

    public abstract String GetServerResVersion();

    public abstract int IsExternalNet();

    public abstract boolean IsNeedRuntimeUpdate();

    public abstract int IsSDKDevelopApk();

    public abstract boolean IsSdk();

    public abstract void ResUpdateSuccess(String str);

    public void ShowRotateLoading() {
    }

    public abstract void changeAccount();

    public abstract String getPlatformID();

    public abstract void initOnCreate(Bundle bundle);

    public abstract int isSpecialLoadSkin();

    public abstract void login();

    public abstract void login(String str);

    public abstract void logoutAccount();

    public abstract void logoutGame();

    public abstract void pay(String str);

    public abstract void submitExtendData(String str);

    static /* synthetic */ int access$008(MainActivityBase mainActivityBase) {
        int i = mainActivityBase.progress;
        mainActivityBase.progress = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ShowView();
        initSystem();
        initOnCreate(bundle);
    }

    public void sendOpenQuitWndCallback(String str) {
        sendCallback(CALLBACK_OPENQUITWND, str);
    }

    public void sendCallback(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        try {
            UnityPlayer.UnitySendMessage(CALLBACK_GAMEOBJECT_NAME, str, str2);
        } catch (Exception unused) {
            Log.d(TAG_LOG, "sendCallback " + str + " error : " + str2);
        }
    }

    public void ShowView() {
        this.view = View.inflate(this, C2502R.layout.progresslayout, null);
        this.mUnityPlayer.addView(this.view);
        this.progressBar = (ProgressBar) this.view.findViewById(C2502R.id.ProgressBar);
        this.tvPercent = (TextView) this.view.findViewById(C2502R.id.ProgressLabel);
        Handler handler = new Handler() { // from class: com.mu.MainActivityBase.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (!MainActivityBase.this.ishide && message.what == 1) {
                    if (MainActivityBase.this.progress < 100) {
                        MainActivityBase.this.progressBar.setProgress(MainActivityBase.this.progress);
                        MainActivityBase.this.tvPercent.setText(MainActivityBase.this.progress + "%");
                        MainActivityBase.access$008(MainActivityBase.this);
                        MainActivityBase.this.handler.sendEmptyMessageDelayed(1, 20L);
                        return;
                    }
                    MainActivityBase.this.progress = 1;
                    MainActivityBase.this.progressBar.setProgress(MainActivityBase.this.progress);
                    MainActivityBase.this.tvPercent.setText(MainActivityBase.this.progress + "%");
                    MainActivityBase.this.handler.sendEmptyMessageDelayed(1, 15L);
                }
            }
        };
        this.handler = handler;
        handler.sendEmptyMessage(1);
    }

    public void HideView() {
        this.mUnityPlayer.removeView(this.bgView);
        this.bgView = null;
        this.mUnityPlayer.removeView(this.srcView);
        this.srcView = null;
        this.mUnityPlayer.removeView(this.view);
        this.view = null;
        this.ishide = true;
    }

    public void initSystem() {
        try {
            getWindow().addFlags(128);
            registerReceiver(this.mBatInfoReveiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            this.f738tm = (TelephonyManager) getSystemService("phone");
            setFullScreen();
            MemoryUtility memoryUtility = new MemoryUtility((Activity) this);
            this.memoryUtility = memoryUtility;
            this.Pid = memoryUtility.GetProcessPid();
            OrientationEventListener orientationEventListener = new OrientationEventListener(this, 3) { // from class: com.mu.MainActivityBase.2
                @Override // android.view.OrientationEventListener
                public void onOrientationChanged(int i) {
                    if (i == -1) {
                        return;
                    }
                    int i2 = 0;
                    if (i <= 350 && i >= 10) {
                        if (i >= 70 && i <= 90) {
                            i2 = 90;
                        } else if (i > 170 && i < 190) {
                            i2 = CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
                        } else if (i >= 260 && i <= 275) {
                            i2 = 270;
                        }
                    }
                    if (MyOrientationDetector.unityOrientation != i2) {
                        MyOrientationDetector.unityOrientation = i2;
                        MainActivityBase.this.setOrientation(i2);
                    }
                }
            };
            this.mOrientationEventListener = orientationEventListener;
            if (orientationEventListener.canDetectOrientation()) {
                this.mOrientationEventListener.enable();
            } else {
                this.mOrientationEventListener.disable();
            }
        } catch (Exception unused) {
        }
    }

    private void copyToClipBoard(String str) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
        ClipData newPlainText = ClipData.newPlainText(null, str);
        clipboardManager.setPrimaryClip(newPlainText);
        Toast.makeText(this, "copy = " + newPlainText, 0).show();
    }

    public String getSystemLanguage() {
        return SystemUtil.getSystemLanguage();
    }

    public String getSystemVersion() {
        return SystemUtil.getSystemVersion();
    }

    public String getSystemModel() {
        return SystemUtil.getSystemModel();
    }

    public String getDeviceBrand() {
        return SystemUtil.getDeviceBrand();
    }

    public long systemAvaialbeMemorySize() {
        MemoryUtility memoryUtility = this.memoryUtility;
        if (memoryUtility == null) {
            return 0L;
        }
        return memoryUtility.getSystemAvaialbeMemorySize();
    }

    public long getProcessMemoryInfo() {
        MemoryUtility memoryUtility = this.memoryUtility;
        if (memoryUtility == null) {
            return 0L;
        }
        return memoryUtility.GetProcessMemoryInfo(this.Pid);
    }

    public long getMemoryThreshold() {
        MemoryUtility memoryUtility = this.memoryUtility;
        if (memoryUtility == null) {
            return 0L;
        }
        return memoryUtility.getMemoryThreshold();
    }

    public boolean isLowMemory() {
        MemoryUtility memoryUtility = this.memoryUtility;
        if (memoryUtility == null) {
            return false;
        }
        return memoryUtility.isLowMemory();
    }

    public void clearAppCache() {
        MemoryUtility memoryUtility = this.memoryUtility;
        if (memoryUtility == null) {
            return;
        }
        memoryUtility.clearAppCache(this);
    }

    public void clearAppMemory() {
        if (this.memoryUtility == null) {
            return;
        }
        this.memoryUtility.clearAppMemory(new Handler() { // from class: com.mu.MainActivityBase.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                MainActivityBase.this.sendCallback(MainActivityBase.CALLBACK_ClearAppMemory, "");
            }
        });
    }

    public long getMemoryLimitResidue() {
        MemoryUtility memoryUtility = this.memoryUtility;
        if (memoryUtility == null) {
            return 0L;
        }
        return memoryUtility.getMemoryLimitResidue();
    }

    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            setFullScreen();
        }
    }

    public void setFullScreen() {
        try {
            getWindow().getDecorView().setSystemUiVisibility(5894);
            Window window = getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.systemUiVisibility = 2050;
            window.setAttributes(attributes);
        } catch (Exception unused) {
        }
    }

    public void setOrientation(int i) {
        MyOrientationDetector.unityOrientation = i;
        sendCallback(CALLBACK_OnScreenOrientation, MyOrientationDetector.unityOrientation + "");
    }

    public boolean checkVoicePermission(boolean z) {
        if (ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0) {
            return true;
        }
        if (!z) {
            return false;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECORD_AUDIO"}, 321);
        return false;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 321) {
            sendCallback(CALLBACK_VoicePermision, "" + iArr[0]);
        }
    }
}
