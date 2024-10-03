package com.tencent.bugly;

import com.tencent.bugly.proguard.C2566aa;
import java.util.Map;

/* compiled from: BUGLY */
/* loaded from: classes3.dex */
public class BuglyStrategy {

    /* renamed from: c */
    private String f818c;

    /* renamed from: d */
    private String f819d;

    /* renamed from: e */
    private String f820e;

    /* renamed from: f */
    private long f821f;

    /* renamed from: g */
    private String f822g;

    /* renamed from: h */
    private String f823h;

    /* renamed from: i */
    private String f824i;

    /* renamed from: u */
    private C2551a f836u;

    /* renamed from: j */
    private boolean f825j = true;

    /* renamed from: k */
    private boolean f826k = true;

    /* renamed from: l */
    private boolean f827l = true;

    /* renamed from: m */
    private boolean f828m = false;

    /* renamed from: n */
    private boolean f829n = true;

    /* renamed from: o */
    private Class<?> f830o = null;

    /* renamed from: p */
    private boolean f831p = true;

    /* renamed from: q */
    private boolean f832q = true;

    /* renamed from: r */
    private boolean f833r = true;

    /* renamed from: s */
    private boolean f834s = true;

    /* renamed from: t */
    private boolean f835t = false;

    /* renamed from: a */
    protected int f816a = 31;

    /* renamed from: b */
    protected boolean f817b = false;

    /* renamed from: v */
    private boolean f837v = false;

    public synchronized BuglyStrategy setBuglyLogUpload(boolean z) {
        this.f831p = z;
        return this;
    }

    public synchronized BuglyStrategy setRecordUserInfoOnceADay(boolean z) {
        this.f835t = z;
        return this;
    }

    public synchronized BuglyStrategy setUploadProcess(boolean z) {
        this.f833r = z;
        return this;
    }

    public synchronized boolean isUploadProcess() {
        return this.f833r;
    }

    public synchronized boolean isBuglyLogUpload() {
        return this.f831p;
    }

    public synchronized boolean recordUserInfoOnceADay() {
        return this.f835t;
    }

    public boolean isReplaceOldChannel() {
        return this.f832q;
    }

    public void setReplaceOldChannel(boolean z) {
        this.f832q = z;
    }

    public synchronized String getAppVersion() {
        String str = this.f818c;
        if (str != null) {
            return str;
        }
        return C2566aa.m648b().f1039o;
    }

    public synchronized BuglyStrategy setAppVersion(String str) {
        this.f818c = str;
        return this;
    }

    public synchronized BuglyStrategy setUserInfoActivity(Class<?> cls) {
        this.f830o = cls;
        return this;
    }

    public synchronized Class<?> getUserInfoActivity() {
        return this.f830o;
    }

    public synchronized String getAppChannel() {
        String str = this.f819d;
        if (str != null) {
            return str;
        }
        return C2566aa.m648b().f1043s;
    }

    public synchronized BuglyStrategy setAppChannel(String str) {
        this.f819d = str;
        return this;
    }

    public synchronized String getAppPackageName() {
        String str = this.f820e;
        if (str != null) {
            return str;
        }
        return C2566aa.m648b().f1027c;
    }

    public synchronized BuglyStrategy setAppPackageName(String str) {
        this.f820e = str;
        return this;
    }

    public synchronized long getAppReportDelay() {
        return this.f821f;
    }

    public synchronized BuglyStrategy setAppReportDelay(long j) {
        this.f821f = j;
        return this;
    }

    public synchronized String getLibBuglySOFilePath() {
        return this.f822g;
    }

    public synchronized BuglyStrategy setLibBuglySOFilePath(String str) {
        this.f822g = str;
        return this;
    }

    public synchronized String getDeviceID() {
        return this.f823h;
    }

    public synchronized BuglyStrategy setDeviceID(String str) {
        this.f823h = str;
        return this;
    }

    public synchronized String getDeviceModel() {
        return this.f824i;
    }

    public synchronized BuglyStrategy setDeviceModel(String str) {
        this.f824i = str;
        return this;
    }

    public synchronized boolean isEnableNativeCrashMonitor() {
        return this.f825j;
    }

    public synchronized BuglyStrategy setEnableNativeCrashMonitor(boolean z) {
        this.f825j = z;
        return this;
    }

    public synchronized BuglyStrategy setEnableUserInfo(boolean z) {
        this.f829n = z;
        return this;
    }

    public synchronized boolean isEnableUserInfo() {
        return this.f829n;
    }

    public synchronized boolean isEnableCatchAnrTrace() {
        return this.f827l;
    }

    public void setEnableCatchAnrTrace(boolean z) {
        this.f827l = z;
    }

    public void setEnableRecordAnrMainStack(boolean z) {
        this.f828m = z;
    }

    public boolean isEnableRecordAnrMainStack() {
        return this.f828m;
    }

    public synchronized boolean isEnableANRCrashMonitor() {
        return this.f826k;
    }

    public synchronized BuglyStrategy setEnableANRCrashMonitor(boolean z) {
        this.f826k = z;
        return this;
    }

    public synchronized C2551a getCrashHandleCallback() {
        return this.f836u;
    }

    public synchronized BuglyStrategy setCrashHandleCallback(C2551a c2551a) {
        this.f836u = c2551a;
        return this;
    }

    public synchronized void setCallBackType(int i) {
        this.f816a = i;
    }

    public synchronized int getCallBackType() {
        return this.f816a;
    }

    public synchronized void setCloseErrorCallback(boolean z) {
        this.f817b = z;
    }

    public synchronized boolean getCloseErrorCallback() {
        return this.f817b;
    }

    public boolean isMerged() {
        return this.f837v;
    }

    @Deprecated
    public void setMerged(boolean z) {
        this.f837v = z;
    }

    public synchronized void setUploadSpotCrash(boolean z) {
        this.f834s = z;
    }

    public synchronized boolean isUploadSpotCrash() {
        return this.f834s;
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.BuglyStrategy$a */
    /* loaded from: classes3.dex */
    public static class C2551a {
        public static final int CRASHTYPE_ANR = 4;
        public static final int CRASHTYPE_BLOCK = 7;
        public static final int CRASHTYPE_COCOS2DX_JS = 5;
        public static final int CRASHTYPE_COCOS2DX_LUA = 6;
        public static final int CRASHTYPE_JAVA_CATCH = 1;
        public static final int CRASHTYPE_JAVA_CRASH = 0;
        public static final int CRASHTYPE_NATIVE = 2;
        public static final int CRASHTYPE_U3D = 3;
        public static final int MAX_USERDATA_KEY_LENGTH = 100;
        public static final int MAX_USERDATA_VALUE_LENGTH = 100000;

        public synchronized Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
            return null;
        }

        public synchronized byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3) {
            return null;
        }
    }
}
