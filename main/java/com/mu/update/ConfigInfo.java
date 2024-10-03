package com.mu.update;

/* loaded from: classes2.dex */
public class ConfigInfo {
    public static String ApkName = "";
    public static String CurAPKVersion = "";
    public static String ErrorMsg = "";
    public static String PackageVersion = "";
    public static String ServerConfig = "";
    public static String VersionFilePath = "";
    public static String VersionURL = "";
    public static UpdateState mUpdateState;
    public static Boolean FullURL = false;
    public static String SDKGameID = "135";
    public static String pId = "1213";
    public static boolean openVerify = false;
    public static int externalNet = 1;
    public static boolean isDebugState = false;
    public static String opName = "ad_twmz";
    public static String versionConfig = "";

    /* loaded from: classes2.dex */
    public enum ActivityState {
        first,
        splashFinish,
        gotoMain
    }

    /* loaded from: classes2.dex */
    public enum SplashState {
        NONE,
        Start,
        End
    }

    /* loaded from: classes2.dex */
    public enum UpdateState {
        LoadVersion,
        LoadVersionFinish,
        LoadVersionFailed,
        apkInstallFailed
    }

    /* loaded from: classes2.dex */
    public class DownloadApkStatus {
        public static final int REQUEST_CODE_UNKNOWN_APP = 100;
        public static final int REQUEST_CODE_UNKNOWN_APP_CANCEL = 101;

        public DownloadApkStatus() {
        }
    }

    /* loaded from: classes2.dex */
    public class Msg {
        public static final int Error_Occured = 100;
        public static final int SPLASH_FINISH = 12;
        public static final int Toast_Message = 29;
        public static final int UPDATE_DOWNLOAD_PROCESS = 1;
        public static final int VERSION_DOWNLOAD_FAILED = 3;
        public static final int VERSION_DOWNLOAD_FINISH = 2;

        public Msg() {
        }
    }
}
