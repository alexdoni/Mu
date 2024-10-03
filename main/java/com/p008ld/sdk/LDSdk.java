package com.p008ld.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import com.liulishuo.filedownloader.FileDownloader;
import com.p008ld.sdk.bean.CoolingOffBean;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.download.zzd;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDLockOnGetVariable;
import com.p008ld.sdk.internal.LDValidate;
import com.p008ld.sdk.track.LDMiddleTrack;
import com.p008ld.sdk.track.LDTrackEvent;
import com.p008ld.sdk.track.LDTrackToken;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzo;
import com.p008ld.sdk.zzd.zza;
import com.p008ld.sdk.zzd.zze;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.OkHttpClient;

/* compiled from: LDSdk.kt */
/* loaded from: classes2.dex */
public final class LDSdk {
    private static final String LD_APP_ID_PROPERTY = "com.ld.APP_ID";
    private static final String LD_APP_KEY_PROPERTY = "com.ld.APP_KEY";
    private static final String LD_CHANNEL_ID_PROPERTY = "com.ld.CHANNEL_ID";
    private static final String LD_SUN_CHANNEL_ID_PROPERTY = "com.ld.SUN_CHANNEL_ID";
    private static final String LD_TEST_HOST_PROPERTY = "com.ld.TEST_HOST";
    private static volatile String appId;
    private static volatile String appKey;
    private static Context applicationContext;
    private static LDLockOnGetVariable<File> cacheDir;
    private static volatile String channelId;
    private static volatile Boolean debugLogEnabled;
    private static Executor executor;
    private static volatile String sunChannelId;
    public static final LDSdk INSTANCE = new LDSdk();
    private static final AtomicBoolean sdkInitialized = new AtomicBoolean(false);
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static boolean isAutoInit = true;
    private static volatile Boolean isTestHost = false;
    private static volatile Boolean floatViewEnabled = true;
    private static volatile Boolean loginViewBackPressEnable = false;

    @JvmStatic
    public static final String getSdkVersion() {
        return "2.2.0";
    }

    @JvmStatic
    public static final String getSdkVersionCode() {
        return "26";
    }

    private LDSdk() {
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004c A[Catch: all -> 0x00f1, TryCatch #0 {, blocks: (B:4:0x0003, B:9:0x0012, B:11:0x0022, B:12:0x0028, B:14:0x002f, B:15:0x0035, B:17:0x0040, B:22:0x004c, B:24:0x0052, B:29:0x005e, B:31:0x0064, B:36:0x0070, B:38:0x0076, B:41:0x007f, B:43:0x0092, B:44:0x0098, B:46:0x00c3, B:47:0x00ca, B:51:0x00d1, B:52:0x00d8, B:54:0x00d9, B:55:0x00e0, B:57:0x00e1, B:58:0x00e8, B:60:0x00e9, B:61:0x00f0), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005e A[Catch: all -> 0x00f1, TryCatch #0 {, blocks: (B:4:0x0003, B:9:0x0012, B:11:0x0022, B:12:0x0028, B:14:0x002f, B:15:0x0035, B:17:0x0040, B:22:0x004c, B:24:0x0052, B:29:0x005e, B:31:0x0064, B:36:0x0070, B:38:0x0076, B:41:0x007f, B:43:0x0092, B:44:0x0098, B:46:0x00c3, B:47:0x00ca, B:51:0x00d1, B:52:0x00d8, B:54:0x00d9, B:55:0x00e0, B:57:0x00e1, B:58:0x00e8, B:60:0x00e9, B:61:0x00f0), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0070 A[Catch: all -> 0x00f1, TryCatch #0 {, blocks: (B:4:0x0003, B:9:0x0012, B:11:0x0022, B:12:0x0028, B:14:0x002f, B:15:0x0035, B:17:0x0040, B:22:0x004c, B:24:0x0052, B:29:0x005e, B:31:0x0064, B:36:0x0070, B:38:0x0076, B:41:0x007f, B:43:0x0092, B:44:0x0098, B:46:0x00c3, B:47:0x00ca, B:51:0x00d1, B:52:0x00d8, B:54:0x00d9, B:55:0x00e0, B:57:0x00e1, B:58:0x00e8, B:60:0x00e9, B:61:0x00f0), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d9 A[Catch: all -> 0x00f1, TryCatch #0 {, blocks: (B:4:0x0003, B:9:0x0012, B:11:0x0022, B:12:0x0028, B:14:0x002f, B:15:0x0035, B:17:0x0040, B:22:0x004c, B:24:0x0052, B:29:0x005e, B:31:0x0064, B:36:0x0070, B:38:0x0076, B:41:0x007f, B:43:0x0092, B:44:0x0098, B:46:0x00c3, B:47:0x00ca, B:51:0x00d1, B:52:0x00d8, B:54:0x00d9, B:55:0x00e0, B:57:0x00e1, B:58:0x00e8, B:60:0x00e9, B:61:0x00f0), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e1 A[Catch: all -> 0x00f1, TryCatch #0 {, blocks: (B:4:0x0003, B:9:0x0012, B:11:0x0022, B:12:0x0028, B:14:0x002f, B:15:0x0035, B:17:0x0040, B:22:0x004c, B:24:0x0052, B:29:0x005e, B:31:0x0064, B:36:0x0070, B:38:0x0076, B:41:0x007f, B:43:0x0092, B:44:0x0098, B:46:0x00c3, B:47:0x00ca, B:51:0x00d1, B:52:0x00d8, B:54:0x00d9, B:55:0x00e0, B:57:0x00e1, B:58:0x00e8, B:60:0x00e9, B:61:0x00f0), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00e9 A[Catch: all -> 0x00f1, TryCatch #0 {, blocks: (B:4:0x0003, B:9:0x0012, B:11:0x0022, B:12:0x0028, B:14:0x002f, B:15:0x0035, B:17:0x0040, B:22:0x004c, B:24:0x0052, B:29:0x005e, B:31:0x0064, B:36:0x0070, B:38:0x0076, B:41:0x007f, B:43:0x0092, B:44:0x0098, B:46:0x00c3, B:47:0x00ca, B:51:0x00d1, B:52:0x00d8, B:54:0x00d9, B:55:0x00e0, B:57:0x00e1, B:58:0x00e8, B:60:0x00e9, B:61:0x00f0), top: B:3:0x0003 }] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final synchronized void sdkInitialized(android.content.Context r7) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.LDSdk.sdkInitialized(android.content.Context):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final File sdkInitialized$lambda$0() {
        Context context = applicationContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("applicationContext");
            context = null;
        }
        return context.getCacheDir();
    }

    private final void initFileDownloader(Application application) {
        FileDownloader.setupOnApplicationOnCreate(application).connectionCreator(new zzd.zza(new OkHttpClient.Builder().connectTimeout(com.p008ld.sdk.zza.zza.zzb(), TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS)));
        FileDownloader.setup(application);
    }

    private final void initSdkConfig(Context context) {
        zza.C3263zza c3263zza = com.p008ld.sdk.zzd.zza.zza;
        zze.zza zzaVar = new zze.zza();
        zzaVar.zza(new LDMiddleTrack());
        zzaVar.zza(new zza(context));
        zzaVar.zzb(zzb.zza);
        zzaVar.zza(zzc.zza);
        c3263zza.zza(zzaVar.zze());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LDSdk.kt */
    /* loaded from: classes2.dex */
    public static final class zza extends Lambda implements Function1<String, Unit> {
        final /* synthetic */ Context zza;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zza(Context context) {
            super(1);
            this.zza = context;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* synthetic */ Unit invoke(String str) {
            zza(str);
            return Unit.INSTANCE;
        }

        public final void zza(String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            LDLog.m573e("LDSdk -> setAccountUnfreezingAction");
            com.p008ld.sdk.core.zza.zza.zza.zza().zzb((LDUser) null);
            Activity topActivity = LDUtil.getTopActivity();
            if (topActivity != null) {
                LDSdkManager.getInstance().showLoginView(topActivity, null);
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setData(Uri.parse(it));
            this.zza.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LDSdk.kt */
    /* loaded from: classes2.dex */
    public static final class zzb extends Lambda implements Function1<CoolingOffBean, Unit> {
        public static final zzb zza = new zzb();

        zzb() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* synthetic */ Unit invoke(CoolingOffBean coolingOffBean) {
            zza(coolingOffBean);
            return Unit.INSTANCE;
        }

        public final void zza(CoolingOffBean it) {
            Intrinsics.checkNotNullParameter(it, "it");
            LDLog.m573e("LDSdk ->setAccountInCoolingOffPeriodAction：" + it);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LDSdk.kt */
    /* loaded from: classes2.dex */
    public static final class zzc extends Lambda implements Function0<Unit> {
        public static final zzc zza = new zzc();

        zzc() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* synthetic */ Unit invoke() {
            zza();
            return Unit.INSTANCE;
        }

        public final void zza() {
            LDLog.m573e("LDSdk ->setTokenExpiredAction");
            com.p008ld.sdk.core.zza.zza.zza.zza().zzb((LDUser) null);
            Activity topActivity = LDUtil.getTopActivity();
            if (topActivity != null) {
                LDSdkManager.getInstance().showLoginView(topActivity, null);
            }
        }
    }

    @JvmStatic
    public static final boolean isInitialized() {
        return sdkInitialized.get();
    }

    @JvmStatic
    public static final Context getApp() {
        LDValidate.sdkInitialized();
        Context context = applicationContext;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("applicationContext");
        return null;
    }

    @JvmStatic
    public static final String getSid() {
        return com.p008ld.sdk.model.zza.zza.zza().zzc();
    }

    @JvmStatic
    public static final File getCacheDir() {
        LDValidate.sdkInitialized();
        LDLockOnGetVariable<File> lDLockOnGetVariable = cacheDir;
        if (lDLockOnGetVariable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cacheDir");
            lDLockOnGetVariable = null;
        }
        return lDLockOnGetVariable.getValue();
    }

    @JvmStatic
    public static final void setCacheDir(File cacheDir2) {
        Intrinsics.checkNotNullParameter(cacheDir2, "cacheDir");
        cacheDir = new LDLockOnGetVariable<>(cacheDir2);
    }

    @JvmStatic
    public static final File getLogFile() {
        LDValidate.sdkInitialized();
        zzo zza2 = zzo.zza.zza();
        if (zza2 != null) {
            return zza2.zza();
        }
        return null;
    }

    @JvmStatic
    public static final void deleteLogFile() {
        LDValidate.sdkInitialized();
        zzo zza2 = zzo.zza.zza();
        if (zza2 != null) {
            zza2.zzb();
        }
    }

    @JvmStatic
    public static final Executor getExecutor() {
        ReentrantLock reentrantLock = LOCK;
        reentrantLock.lock();
        try {
            if (executor == null) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            Executor executor2 = executor;
            if (executor2 != null) {
                return executor2;
            }
            throw new IllegalStateException("Required value was null.".toString());
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @JvmStatic
    public static final void setAutoInit(boolean z) {
        isAutoInit = z;
    }

    @JvmStatic
    public static final boolean getAutoInit() {
        return isAutoInit;
    }

    @JvmStatic
    public static final void setExecutor(Executor executor2) {
        Intrinsics.checkNotNullParameter(executor2, "executor");
        ReentrantLock reentrantLock = LOCK;
        reentrantLock.lock();
        try {
            executor = executor2;
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @JvmStatic
    public static final void trackEvent(LDTrackEvent event, LDException lDException) {
        Intrinsics.checkNotNullParameter(event, "event");
        Bundle bundle = new Bundle();
        bundle.putInt("eventResult", lDException == null ? 1 : 0);
        INSTANCE.trackEvent(new LDTrackToken(event.getKey(), ""), bundle);
    }

    @JvmStatic
    public static final void trackLoginEvent(LDTrackEvent event, LDException lDException, String str) {
        Intrinsics.checkNotNullParameter(event, "event");
        Bundle bundle = new Bundle();
        bundle.putInt("eventResult", lDException == null ? 1 : 0);
        bundle.putString("loginType", str);
        if (lDException != null) {
            bundle.putString("failureReason", lDException.toString());
        }
        INSTANCE.trackEvent(new LDTrackToken(event.getKey(), ""), bundle);
    }

    @JvmStatic
    public static final void trackPayEvent(LDTrackEvent event, LDException lDException, String str) {
        Intrinsics.checkNotNullParameter(event, "event");
        Bundle bundle = new Bundle();
        bundle.putInt("eventResult", lDException == null ? 1 : 0);
        bundle.putString("orderId", str);
        if (lDException != null) {
            bundle.putString("failureReason", lDException.toString());
        }
        INSTANCE.trackEvent(new LDTrackToken(event.getKey(), ""), bundle);
    }

    static /* synthetic */ void trackEvent$default(LDSdk lDSdk, LDTrackToken lDTrackToken, Bundle bundle, int i, Object obj) {
        if ((i & 2) != 0) {
            bundle = new Bundle();
        }
        lDSdk.trackEvent(lDTrackToken, bundle);
    }

    private final void trackEvent(LDTrackToken lDTrackToken, Bundle bundle) {
        com.p008ld.sdk.zzd.zza.zza(com.p008ld.sdk.zzd.zza.zza.zza(), lDTrackToken.getTokenName(), lDTrackToken.getTokenCode(), bundle, null, 8, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0052, code lost:
    
        if (r0 == null) goto L11;
     */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void debug(java.lang.Object r3) {
        /*
            java.lang.String r0 = "message"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            boolean r0 = getDebugLogEnable()
            if (r0 == 0) goto L68
            boolean r0 = r3 instanceof java.lang.Throwable
            if (r0 == 0) goto L16
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            java.lang.String r3 = kotlin.ExceptionsKt.stackTraceToString(r3)
            goto L65
        L16:
            java.lang.Throwable r0 = new java.lang.Throwable
            r0.<init>()
            java.lang.StackTraceElement[] r0 = r0.getStackTrace()
            java.lang.String r1 = "Throwable().stackTrace"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            r1 = 1
            java.lang.Object r0 = kotlin.collections.ArraysKt.getOrNull(r0, r1)
            java.lang.StackTraceElement r0 = (java.lang.StackTraceElement) r0
            if (r0 == 0) goto L54
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = " ("
            r1.<init>(r2)
            java.lang.String r2 = r0.getFileName()
            r1.append(r2)
            r2 = 58
            r1.append(r2)
            int r0 = r0.getLineNumber()
            r1.append(r0)
            r0 = 41
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            if (r0 != 0) goto L56
        L54:
            java.lang.String r0 = ""
        L56:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r0)
            java.lang.String r3 = r1.toString()
        L65:
            com.p008ld.sdk.util.LDLog.m572d(r3)
        L68:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.LDSdk.debug(java.lang.Object):void");
    }

    @JvmStatic
    public static final void setDebugLogEnabled(boolean z) {
        debugLogEnabled = Boolean.valueOf(z);
    }

    @JvmStatic
    public static final void setFloatViewEnabled(boolean z) {
        floatViewEnabled = Boolean.valueOf(z);
    }

    @JvmStatic
    public static final void setLoginViewBackPressEnabled(boolean z) {
        loginViewBackPressEnable = Boolean.valueOf(z);
    }

    @JvmStatic
    public static final String getAppId() {
        LDValidate.sdkInitialized();
        String str = appId;
        if (str != null) {
            return str;
        }
        throw new LDException("A valid AppId must be set in the AndroidManifest.xml");
    }

    @JvmStatic
    public static final String getAppKey() {
        LDValidate.sdkInitialized();
        String str = appKey;
        if (str != null) {
            return str;
        }
        throw new LDException("A valid AppKey must be set in the AndroidManifest.xml");
    }

    @JvmStatic
    public static final String getChannelId() {
        LDValidate.sdkInitialized();
        String str = channelId;
        if (str != null) {
            return str;
        }
        throw new LDException("A valid channelId must be set in the AndroidManifest.xml");
    }

    @JvmStatic
    public static final String getSunChannelId() {
        LDValidate.sdkInitialized();
        String str = sunChannelId;
        if (str != null) {
            return str;
        }
        throw new LDException("A valid sun channelId must be set in the AndroidManifest.xml");
    }

    @JvmStatic
    public static final boolean isTestMode() {
        LDValidate.sdkInitialized();
        Boolean bool = isTestHost;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @JvmStatic
    public static final boolean getDebugLogEnable() {
        Boolean bool = debugLogEnabled;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @JvmStatic
    public static final boolean getFloatViewEnable() {
        Boolean bool = floatViewEnabled;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    @JvmStatic
    public static final boolean getLoginViewBackPressEnable() {
        Boolean bool = loginViewBackPressEnable;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @JvmStatic
    public static final void loadDefaultsFromMetadata$sdk_base_release(Context context) {
        String valueOf;
        String valueOf2;
        String valueOf3;
        String valueOf4;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "try {\n            contex…         return\n        }");
            if (applicationInfo.metaData == null) {
                return;
            }
            if (appId == null) {
                Object obj = applicationInfo.metaData.get(LD_APP_ID_PROPERTY);
                if (obj instanceof String) {
                    valueOf4 = (String) obj;
                } else {
                    valueOf4 = String.valueOf(obj);
                }
                appId = valueOf4;
            }
            if (appKey == null) {
                Object obj2 = applicationInfo.metaData.get(LD_APP_KEY_PROPERTY);
                if (obj2 instanceof String) {
                    valueOf3 = (String) obj2;
                } else {
                    valueOf3 = String.valueOf(obj2);
                }
                appKey = valueOf3;
            }
            if (channelId == null) {
                Object obj3 = applicationInfo.metaData.get(LD_CHANNEL_ID_PROPERTY);
                if (obj3 instanceof String) {
                    valueOf2 = (String) obj3;
                } else {
                    valueOf2 = String.valueOf(obj3);
                }
                channelId = valueOf2;
            }
            if (sunChannelId == null) {
                Object obj4 = applicationInfo.metaData.get(LD_SUN_CHANNEL_ID_PROPERTY);
                if (obj4 instanceof String) {
                    valueOf = (String) obj4;
                } else {
                    valueOf = String.valueOf(obj4);
                }
                sunChannelId = valueOf;
            }
            isTestHost = Boolean.valueOf(applicationInfo.metaData.getBoolean(LD_TEST_HOST_PROPERTY));
            LDLog.m573e("loadDefaultsFromMetaData: appId: " + appId + " , appKey: " + appKey + " , channelId: " + channelId + " , sunChannelId:" + sunChannelId + " , isTestHost: " + isTestHost);
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0022 A[Catch: Exception -> 0x00d4, TryCatch #1 {Exception -> 0x00d4, blocks: (B:4:0x000f, B:6:0x0016, B:11:0x0022, B:12:0x0026, B:14:0x002b, B:19:0x0037, B:21:0x004a, B:22:0x0052, B:24:0x0058, B:30:0x006b, B:31:0x007b, B:33:0x0089, B:35:0x008d, B:39:0x009a, B:41:0x009e, B:45:0x00ab, B:60:0x0077, B:66:0x000b, B:3:0x0005), top: B:2:0x0005, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0037 A[Catch: Exception -> 0x00d4, TryCatch #1 {Exception -> 0x00d4, blocks: (B:4:0x000f, B:6:0x0016, B:11:0x0022, B:12:0x0026, B:14:0x002b, B:19:0x0037, B:21:0x004a, B:22:0x0052, B:24:0x0058, B:30:0x006b, B:31:0x007b, B:33:0x0089, B:35:0x008d, B:39:0x009a, B:41:0x009e, B:45:0x00ab, B:60:0x0077, B:66:0x000b, B:3:0x0005), top: B:2:0x0005, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void loadRealChannel$sdk_base_release(android.content.Context r4) {
        /*
            Method dump skipped, instructions count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.LDSdk.loadRealChannel$sdk_base_release(android.content.Context):void");
    }
}
