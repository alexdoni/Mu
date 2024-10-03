package com.mu.update;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.mu.update.ConfigInfo;
import com.mu.utility.Encrypt.Encrypt;
import com.mu.utility.MuDebug;
import com.shenghe.common_lib.C2502R;

/* loaded from: classes2.dex */
public class HotUpdateMgr {
    private static HotUpdateMgr instance;
    int FaileCount;
    private Context mContext;
    Class<?> mGotoAcitity;
    private Handler mHandler;
    public UpdateManager mUpdateManager;
    public ConfigInfo.UpdateState mUpdateState;
    public VersionManager mVersionManager;
    public ConfigInfo.ActivityState mActivityState = ConfigInfo.ActivityState.first;
    public ConfigInfo.SplashState splashstate = ConfigInfo.SplashState.NONE;
    public boolean firstGame = true;
    public boolean CoverInstallation = false;
    public boolean versionLoaded = false;

    /* renamed from: p */
    private String f740p = "ヰ﹃なふУΑてΚРㄆけㄉンЁモЕまㄆけちむヌУЗκてㄕムぁニむК∏М";

    /* renamed from: l */
    private String f739l = "︸ㄞたちㄎㄆふなЕΦЦΠヴ";

    public static HotUpdateMgr getInstance() {
        if (instance == null) {
            instance = new HotUpdateMgr();
        }
        return instance;
    }

    public void Init(Context context, Class<?> cls, String str, boolean z) {
        this.mContext = context;
        this.mGotoAcitity = cls;
        setFullScene();
        Encrypt.Init();
        ConfigInfo.ApkName = this.mContext.getPackageName();
        if (checkDirectGoToUnity()) {
            return;
        }
        Context context2 = this.mContext;
        ((Activity) context2).setContentView(context2.getResources().getIdentifier("downloaddll", "layout", this.mContext.getPackageName()));
        SetHandler();
        UpdateWndMgr.getInstance().InitWnd(context, this.mHandler);
        UpdateWndMgr.getInstance().StartSplash();
        this.splashstate = ConfigInfo.SplashState.Start;
        if (ConfigInfo.FullURL.booleanValue() || z) {
            ConfigInfo.VersionURL = str;
        } else {
            ConfigInfo.VersionURL = GetVersionURLConfig(str);
        }
        ConfigInfo.VersionURL = Encrypt.DeCode(ConfigInfo.VersionURL);
        if (ConfigInfo.VersionURL == null) {
            UpdateWndMgr.getInstance().PropConfigFileNoExitError();
            return;
        }
        VersionManager versionManager = new VersionManager(context, this.mHandler);
        this.mVersionManager = versionManager;
        versionManager.LoadVersion();
    }

    public void setFullScene() {
        try {
            ((Activity) this.mContext).getWindow().addFlags(128);
            ((Activity) this.mContext).getWindow().getDecorView().setSystemUiVisibility(5894);
        } catch (Exception unused) {
        }
    }

    public boolean checkDirectGoToUnity() {
        try {
            if (this.mActivityState != ConfigInfo.ActivityState.gotoMain) {
                return false;
            }
            Context context = this.mContext;
            ((Activity) context).setContentView(context.getResources().getIdentifier("black", "layout", this.mContext.getPackageName()));
            new Handler().postDelayed(new Runnable() { // from class: com.mu.update.HotUpdateMgr.1
                @Override // java.lang.Runnable
                public void run() {
                    HotUpdateMgr.this.GoToUnity();
                }
            }, 500L);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    void GoToUnity() {
        UpdateWndMgr.getInstance().InitViewState_GOUNITY();
        this.mActivityState = ConfigInfo.ActivityState.gotoMain;
        this.mContext.startActivity(new Intent(this.mContext, this.mGotoAcitity));
        ((Activity) this.mContext).finish();
        ((Activity) this.mContext).overridePendingTransition(C2502R.anim.normal, C2502R.anim.normal);
    }

    public void ResetFaile() {
        this.FaileCount = 0;
    }

    boolean check_Failed() {
        if (this.FaileCount < 2) {
            return true;
        }
        if (this.mActivityState == ConfigInfo.ActivityState.first) {
            return false;
        }
        if (this.mUpdateState == ConfigInfo.UpdateState.LoadVersionFailed) {
            UpdateWndMgr.getInstance().PropVersionError();
        } else {
            UpdateWndMgr.getInstance().PropError();
        }
        return false;
    }

    public void CheckProcess() {
        if (this.versionLoaded) {
            if (this.mVersionManager.ApkParseVersion(ConfigInfo.PackageVersion, this.mVersionManager.netApkVersion.newVersion) != 0 && this.mVersionManager.ApkParseVersion(ConfigInfo.CurAPKVersion, this.mVersionManager.netApkVersion.newVersion) != 0 && this.mVersionManager.netApkVersion.canForceUpdate) {
                if (this.mActivityState == ConfigInfo.ActivityState.splashFinish) {
                    UpdateWndMgr.getInstance().ClearRotateLoading();
                    String str = this.mVersionManager.netApkVersion.resUrl + '/' + this.mVersionManager.netApkVersion.newApk + "#" + this.mVersionManager.netApkVersion.newVersion;
                    MuDebug.Log("msg ..... " + str);
                    UpdateManager updateManager = new UpdateManager(this.mContext);
                    this.mUpdateManager = updateManager;
                    updateManager.checkUpdate(str);
                    return;
                }
                return;
            }
            UpdateWndMgr.getInstance().ClearRotateLoading();
            if (this.mActivityState == ConfigInfo.ActivityState.splashFinish) {
                this.mVersionManager.WriteApkVersion();
                GoToUnity();
            }
        }
    }

    public void SetHandler() {
        this.mHandler = new Handler() { // from class: com.mu.update.HotUpdateMgr.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    UpdateWndMgr.getInstance().UpdateDownProcess(((message.arg1 * 1.0f) / message.arg2) * 100.0f);
                    return;
                }
                if (i == 2) {
                    HotUpdateMgr.this.mUpdateState = ConfigInfo.UpdateState.LoadVersionFinish;
                    HotUpdateMgr.this.mVersionManager.CloseVersionThread();
                    HotUpdateMgr.this.versionLoaded = true;
                    HotUpdateMgr.this.CheckProcess();
                    return;
                }
                if (i != 3) {
                    if (i != 12) {
                        return;
                    }
                    UpdateWndMgr.getInstance().ClearSplash();
                    HotUpdateMgr.this.CheckProcess();
                    HotUpdateMgr.this.check_Failed();
                    return;
                }
                HotUpdateMgr.this.mUpdateState = ConfigInfo.UpdateState.LoadVersionFailed;
                if (HotUpdateMgr.this.check_Failed()) {
                    HotUpdateMgr.this.FaileCount++;
                    HotUpdateMgr.this.mVersionManager.LoadVersion();
                }
            }
        };
    }

    public String GetVersionURLConfig(String str) {
        return (this.f740p + str + this.f739l).toString();
    }
}
