package com.facebook;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Base64;
import android.util.Log;
import com.facebook.GraphRequest;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.AppEventsManager;
import com.facebook.appevents.UserDataStore;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.appevents.internal.AppEventsLoggerUtility;
import com.facebook.appevents.ondeviceprocessing.OnDeviceProcessingManager;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.LockOnGetVariable;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.internal.instrument.InstrumentManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* compiled from: FacebookSdk.kt */
@Metadata(m1394d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0018\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0011\n\u0002\b\u0016\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004\u0091\u0001\u0092\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020?H\u0007J\b\u0010H\u001a\u00020FH\u0007J\b\u0010I\u001a\u00020FH\u0007J\b\u0010J\u001a\u00020*H\u0007J\b\u0010K\u001a\u00020&H\u0007J\b\u0010L\u001a\u00020\u0004H\u0007J\n\u0010M\u001a\u0004\u0018\u00010\u0004H\u0007J\u0014\u0010N\u001a\u0004\u0018\u00010\u00042\b\u0010O\u001a\u0004\u0018\u00010&H\u0007J\b\u0010P\u001a\u00020*H\u0007J\b\u0010Q\u001a\u00020*H\u0007J\n\u0010R\u001a\u0004\u0018\u00010-H\u0007J\b\u0010S\u001a\u00020\u0016H\u0007J\b\u0010T\u001a\u00020\u0004H\u0007J\b\u0010U\u001a\u00020*H\u0007J\b\u0010V\u001a\u00020*H\u0007J\b\u0010W\u001a\u000202H\u0007J\b\u0010X\u001a\u00020\u0004H\u0007J\b\u0010Y\u001a\u00020\u0004H\u0007J\b\u0010Z\u001a\u00020\u0004H\u0007J\b\u0010[\u001a\u00020\u0004H\u0007J\b\u0010\\\u001a\u00020\u0004H\u0007J\u0010\u0010]\u001a\u00020*2\u0006\u0010O\u001a\u00020&H\u0007J\u000e\u0010^\u001a\b\u0012\u0004\u0012\u00020?0_H\u0007J\b\u0010`\u001a\u00020*H\u0007J\b\u0010a\u001a\u00020bH\u0007J\b\u0010c\u001a\u00020\u0004H\u0007J\b\u0010d\u001a\u00020*H\u0007J\u0010\u0010e\u001a\u00020*2\u0006\u0010f\u001a\u00020\u0016H\u0007J\b\u0010;\u001a\u00020*H\u0007J\b\u0010g\u001a\u00020*H\u0007J\b\u0010<\u001a\u00020*H\u0007J\u0010\u0010h\u001a\u00020*2\u0006\u0010G\u001a\u00020?H\u0007J\u0017\u0010i\u001a\u00020F2\b\u0010O\u001a\u0004\u0018\u00010&H\u0001¢\u0006\u0002\bjJ\u0018\u0010k\u001a\u00020F2\u0006\u0010O\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0004H\u0003J\u0018\u0010l\u001a\u00020F2\u0006\u0010O\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0004H\u0007J\u0010\u0010m\u001a\u00020F2\u0006\u0010G\u001a\u00020?H\u0007J\u0010\u0010n\u001a\u00020F2\u0006\u0010%\u001a\u00020&H\u0007J\u001a\u0010n\u001a\u00020F2\u0006\u0010%\u001a\u00020&2\b\u0010o\u001a\u0004\u0018\u00010pH\u0007J\u0018\u0010n\u001a\u00020F2\u0006\u0010%\u001a\u00020&2\u0006\u0010.\u001a\u00020\u0016H\u0007J\"\u0010n\u001a\u00020F2\u0006\u0010%\u001a\u00020&2\u0006\u0010.\u001a\u00020\u00162\b\u0010o\u001a\u0004\u0018\u00010pH\u0007J\u0010\u0010q\u001a\u00020F2\u0006\u0010r\u001a\u00020*H\u0007J\u0010\u0010s\u001a\u00020F2\u0006\u0010'\u001a\u00020\u0004H\u0007J\u0012\u0010t\u001a\u00020F2\b\u0010(\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010u\u001a\u00020F2\u0006\u0010r\u001a\u00020*H\u0007J\u0010\u0010v\u001a\u00020F2\u0006\u0010r\u001a\u00020*H\u0007J\u0010\u0010w\u001a\u00020F2\u0006\u0010+\u001a\u00020-H\u0007J\u0012\u0010x\u001a\u00020F2\b\u0010y\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010z\u001a\u00020F2\u0006\u0010r\u001a\u00020*H\u0007J\u001d\u0010{\u001a\u00020F2\u000e\u0010|\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010}H\u0007¢\u0006\u0002\u0010~J/\u0010{\u001a\u00020F2\u000e\u0010|\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010}2\u0006\u0010\u007f\u001a\u00020\u00162\u0007\u0010\u0080\u0001\u001a\u00020\u0016H\u0007¢\u0006\u0003\u0010\u0081\u0001J\u0011\u0010\u0082\u0001\u001a\u00020F2\u0006\u00101\u001a\u000202H\u0007J\u0011\u0010\u0083\u0001\u001a\u00020F2\u0006\u00103\u001a\u00020\u0004H\u0007J\u0011\u0010\u0084\u0001\u001a\u00020F2\u0006\u00104\u001a\u00020\u0004H\u0007J\u0017\u0010\u0085\u0001\u001a\u00020F2\u0006\u00105\u001a\u000206H\u0001¢\u0006\u0003\b\u0086\u0001J\u0012\u0010\u0087\u0001\u001a\u00020F2\u0007\u0010\u0088\u0001\u001a\u00020*H\u0007J\u0012\u0010\u0089\u0001\u001a\u00020F2\u0007\u0010\u008a\u0001\u001a\u00020*H\u0007J\u001a\u0010\u008b\u0001\u001a\u00020F2\u0006\u0010O\u001a\u00020&2\u0007\u0010\u008c\u0001\u001a\u00020*H\u0007J\u0011\u0010\u008d\u0001\u001a\u00020F2\u0006\u0010r\u001a\u00020*H\u0007J\u0012\u0010\u008e\u0001\u001a\u00020F2\u0007\u0010\u008f\u0001\u001a\u00020bH\u0007J\t\u0010\u0090\u0001\u001a\u00020FH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\n \"*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010)\u001a\u00020*8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010/\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0004\n\u0002\u00100R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u00107\u001a\u00020*8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u00108\u001a\u00020*8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010=\u001a\u0012\u0012\u0004\u0012\u00020?0>j\b\u0012\u0004\u0012\u00020?`@X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020BX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020DX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0093\u0001"}, m1395d2 = {"Lcom/facebook/FacebookSdk;", "", "()V", "ADVERTISER_ID_COLLECTION_ENABLED_PROPERTY", "", "APPLICATION_ID_PROPERTY", "APPLICATION_NAME_PROPERTY", "APP_EVENT_PREFERENCES", "ATTRIBUTION_PREFERENCES", "AUTO_INIT_ENABLED_PROPERTY", "AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY", "CALLBACK_OFFSET_CHANGED_AFTER_INIT", "CALLBACK_OFFSET_NEGATIVE", "CALLBACK_OFFSET_PROPERTY", "CLIENT_TOKEN_PROPERTY", "CLOUDBRIDGE_SAVED_CREDENTIALS", "CODELESS_DEBUG_LOG_ENABLED_PROPERTY", "DATA_PROCESSING_OPTIONS_PREFERENCES", "DATA_PROCESSION_OPTIONS", "DATA_PROCESSION_OPTIONS_COUNTRY", "DATA_PROCESSION_OPTIONS_STATE", "DEFAULT_CALLBACK_REQUEST_CODE_OFFSET", "", "FACEBOOK_COM", "FB_GG", "GAMING", "INSTAGRAM", "INSTAGRAM_COM", "LOCK", "Ljava/util/concurrent/locks/ReentrantLock;", "MAX_REQUEST_CODE_RANGE", "MONITOR_ENABLED_PROPERTY", "PUBLISH_ACTIVITY_PATH", "TAG", "kotlin.jvm.PlatformType", "WEB_DIALOG_THEME", "appClientToken", "applicationContext", "Landroid/content/Context;", "applicationId", "applicationName", "bypassAppSwitch", "", "cacheDir", "Lcom/facebook/internal/LockOnGetVariable;", "Ljava/io/File;", "callbackRequestCodeOffset", "codelessDebugLogEnabled", "Ljava/lang/Boolean;", "executor", "Ljava/util/concurrent/Executor;", "facebookDomain", "graphApiVersion", "graphRequestCreator", "Lcom/facebook/FacebookSdk$GraphRequestCreator;", "hasCustomTabsPrefetching", "ignoreAppSwitchToLoggedOut", "instagramDomain", "isDebugEnabledField", "isFullyInitialized", "isLegacyTokenUpgradeSupported", "loggingBehaviors", "Ljava/util/HashSet;", "Lcom/facebook/LoggingBehavior;", "Lkotlin/collections/HashSet;", "onProgressThreshold", "Ljava/util/concurrent/atomic/AtomicLong;", "sdkInitialized", "Ljava/util/concurrent/atomic/AtomicBoolean;", "addLoggingBehavior", "", "behavior", "clearLoggingBehaviors", "fullyInitialize", "getAdvertiserIDCollectionEnabled", "getApplicationContext", "getApplicationId", "getApplicationName", "getApplicationSignature", "context", "getAutoInitEnabled", "getAutoLogAppEventsEnabled", "getCacheDir", "getCallbackRequestCodeOffset", "getClientToken", "getCodelessDebugLogEnabled", "getCodelessSetupEnabled", "getExecutor", "getFacebookDomain", "getFacebookGamingDomain", "getGraphApiVersion", "getGraphDomain", "getInstagramDomain", "getLimitEventAndDataUsage", "getLoggingBehaviors", "", "getMonitorEnabled", "getOnProgressThreshold", "", "getSdkVersion", "isDebugEnabled", "isFacebookRequestCode", "requestCode", "isInitialized", "isLoggingBehaviorEnabled", "loadDefaultsFromMetadata", "loadDefaultsFromMetadata$facebook_core_release", "publishInstallAndWaitForResponse", "publishInstallAsync", "removeLoggingBehavior", "sdkInitialize", "callback", "Lcom/facebook/FacebookSdk$InitializeCallback;", "setAdvertiserIDCollectionEnabled", "flag", "setApplicationId", "setApplicationName", "setAutoInitEnabled", "setAutoLogAppEventsEnabled", "setCacheDir", "setClientToken", "clientToken", "setCodelessDebugLogEnabled", "setDataProcessingOptions", "options", "", "([Ljava/lang/String;)V", UserDataStore.COUNTRY, ServerProtocol.DIALOG_PARAM_STATE, "([Ljava/lang/String;II)V", "setExecutor", "setFacebookDomain", "setGraphApiVersion", "setGraphRequestCreator", "setGraphRequestCreator$facebook_core_release", "setIsDebugEnabled", "enabled", "setLegacyTokenUpgradeSupported", "supported", "setLimitEventAndDataUsage", "limitEventUsage", "setMonitorEnabled", "setOnProgressThreshold", "threshold", "updateGraphDebugBehavior", "GraphRequestCreator", "InitializeCallback", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class FacebookSdk {
    public static final String ADVERTISER_ID_COLLECTION_ENABLED_PROPERTY = "com.facebook.sdk.AdvertiserIDCollectionEnabled";
    public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
    public static final String APPLICATION_NAME_PROPERTY = "com.facebook.sdk.ApplicationName";
    public static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
    private static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
    public static final String AUTO_INIT_ENABLED_PROPERTY = "com.facebook.sdk.AutoInitEnabled";
    public static final String AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY = "com.facebook.sdk.AutoLogAppEventsEnabled";
    public static final String CALLBACK_OFFSET_CHANGED_AFTER_INIT = "The callback request code offset can't be updated once the SDK is initialized. Call FacebookSdk.setCallbackRequestCodeOffset inside your Application.onCreate method";
    public static final String CALLBACK_OFFSET_NEGATIVE = "The callback request code offset can't be negative.";
    public static final String CALLBACK_OFFSET_PROPERTY = "com.facebook.sdk.CallbackOffset";
    public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.sdk.ClientToken";
    public static final String CLOUDBRIDGE_SAVED_CREDENTIALS = "com.facebook.sdk.CloudBridgeSavedCredentials";
    public static final String CODELESS_DEBUG_LOG_ENABLED_PROPERTY = "com.facebook.sdk.CodelessDebugLogEnabled";
    public static final String DATA_PROCESSING_OPTIONS_PREFERENCES = "com.facebook.sdk.DataProcessingOptions";
    public static final String DATA_PROCESSION_OPTIONS = "data_processing_options";
    public static final String DATA_PROCESSION_OPTIONS_COUNTRY = "data_processing_options_country";
    public static final String DATA_PROCESSION_OPTIONS_STATE = "data_processing_options_state";
    public static final String FACEBOOK_COM = "facebook.com";
    public static final String FB_GG = "fb.gg";
    public static final String GAMING = "gaming";
    public static final String INSTAGRAM = "instagram";
    public static final String INSTAGRAM_COM = "instagram.com";
    private static final int MAX_REQUEST_CODE_RANGE = 100;
    public static final String MONITOR_ENABLED_PROPERTY = "com.facebook.sdk.MonitorEnabled";
    private static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
    public static final String WEB_DIALOG_THEME = "com.facebook.sdk.WebDialogTheme";
    private static volatile String appClientToken;
    private static Context applicationContext;
    private static volatile String applicationId;
    private static volatile String applicationName;
    public static boolean bypassAppSwitch;
    private static LockOnGetVariable<File> cacheDir;
    private static volatile Boolean codelessDebugLogEnabled;
    private static Executor executor;
    private static volatile String facebookDomain;
    private static String graphApiVersion;
    private static GraphRequestCreator graphRequestCreator;
    public static boolean hasCustomTabsPrefetching;
    public static boolean ignoreAppSwitchToLoggedOut;
    private static volatile String instagramDomain;
    private static volatile boolean isDebugEnabledField;
    private static boolean isFullyInitialized;
    private static boolean isLegacyTokenUpgradeSupported;
    private static final AtomicBoolean sdkInitialized;
    public static final FacebookSdk INSTANCE = new FacebookSdk();
    private static final String TAG = FacebookSdk.class.getCanonicalName();
    private static final HashSet<LoggingBehavior> loggingBehaviors = SetsKt.hashSetOf(LoggingBehavior.DEVELOPER_ERRORS);
    private static AtomicLong onProgressThreshold = new AtomicLong(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
    private static final int DEFAULT_CALLBACK_REQUEST_CODE_OFFSET = 64206;
    private static int callbackRequestCodeOffset = DEFAULT_CALLBACK_REQUEST_CODE_OFFSET;
    private static final ReentrantLock LOCK = new ReentrantLock();

    /* compiled from: FacebookSdk.kt */
    @Metadata(m1394d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bá\u0080\u0001\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¨\u0006\f"}, m1395d2 = {"Lcom/facebook/FacebookSdk$GraphRequestCreator;", "", "createPostRequest", "Lcom/facebook/GraphRequest;", "accessToken", "Lcom/facebook/AccessToken;", "publishUrl", "", "publishParams", "Lorg/json/JSONObject;", "callback", "Lcom/facebook/GraphRequest$Callback;", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public interface GraphRequestCreator {
        GraphRequest createPostRequest(AccessToken accessToken, String publishUrl, JSONObject publishParams, GraphRequest.Callback callback);
    }

    /* compiled from: FacebookSdk.kt */
    @Metadata(m1394d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, m1395d2 = {"Lcom/facebook/FacebookSdk$InitializeCallback;", "", "onInitialized", "", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public interface InitializeCallback {
        void onInitialized();
    }

    @JvmStatic
    public static final String getFacebookGamingDomain() {
        return FB_GG;
    }

    @JvmStatic
    public static final String getSdkVersion() {
        return FacebookSdkVersion.BUILD;
    }

    private FacebookSdk() {
    }

    static {
        ServerProtocol serverProtocol = ServerProtocol.INSTANCE;
        graphApiVersion = ServerProtocol.getDefaultAPIVersion();
        sdkInitialized = new AtomicBoolean(false);
        instagramDomain = INSTAGRAM_COM;
        facebookDomain = FACEBOOK_COM;
        graphRequestCreator = new GraphRequestCreator() { // from class: com.facebook.FacebookSdk$$ExternalSyntheticLambda8
            @Override // com.facebook.FacebookSdk.GraphRequestCreator
            public final GraphRequest createPostRequest(AccessToken accessToken, String str, JSONObject jSONObject, GraphRequest.Callback callback) {
                GraphRequest m1622graphRequestCreator$lambda0;
                m1622graphRequestCreator$lambda0 = FacebookSdk.m1622graphRequestCreator$lambda0(accessToken, str, jSONObject, callback);
                return m1622graphRequestCreator$lambda0;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: graphRequestCreator$lambda-0, reason: not valid java name */
    public static final GraphRequest m1622graphRequestCreator$lambda0(AccessToken accessToken, String str, JSONObject jSONObject, GraphRequest.Callback callback) {
        return GraphRequest.INSTANCE.newPostRequest(accessToken, str, jSONObject, callback);
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
    public static final long getOnProgressThreshold() {
        Validate validate = Validate.INSTANCE;
        Validate.sdkInitialized();
        return onProgressThreshold.get();
    }

    @JvmStatic
    public static final void setOnProgressThreshold(long threshold) {
        onProgressThreshold.set(threshold);
    }

    @JvmStatic
    public static final boolean isDebugEnabled() {
        return isDebugEnabledField;
    }

    @JvmStatic
    public static final void setIsDebugEnabled(boolean enabled) {
        isDebugEnabledField = enabled;
    }

    @JvmStatic
    public static final boolean isLegacyTokenUpgradeSupported() {
        return isLegacyTokenUpgradeSupported;
    }

    @JvmStatic
    public static final void setLegacyTokenUpgradeSupported(boolean supported) {
        isLegacyTokenUpgradeSupported = supported;
    }

    @JvmStatic
    public static final String getGraphApiVersion() {
        Utility utility = Utility.INSTANCE;
        String str = TAG;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("getGraphApiVersion: %s", Arrays.copyOf(new Object[]{graphApiVersion}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
        Utility.logd(str, format);
        return graphApiVersion;
    }

    @JvmStatic
    public static final void setGraphApiVersion(String graphApiVersion2) {
        Intrinsics.checkNotNullParameter(graphApiVersion2, "graphApiVersion");
        Log.w(TAG, "WARNING: Calling setGraphApiVersion from non-DEBUG code.");
        Utility utility = Utility.INSTANCE;
        if (Utility.isNullOrEmpty(graphApiVersion2) || Intrinsics.areEqual(graphApiVersion, graphApiVersion2)) {
            return;
        }
        graphApiVersion = graphApiVersion2;
    }

    @JvmStatic
    public static final synchronized boolean isFullyInitialized() {
        boolean z;
        synchronized (FacebookSdk.class) {
            z = isFullyInitialized;
        }
        return z;
    }

    @JvmStatic
    public static final String getFacebookDomain() {
        return facebookDomain;
    }

    @JvmStatic
    public static final String getInstagramDomain() {
        return instagramDomain;
    }

    @JvmStatic
    public static final void setFacebookDomain(String facebookDomain2) {
        Intrinsics.checkNotNullParameter(facebookDomain2, "facebookDomain");
        Log.w(TAG, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
        facebookDomain = facebookDomain2;
    }

    @Deprecated(message = "")
    @JvmStatic
    public static final synchronized void sdkInitialize(Context applicationContext2, int callbackRequestCodeOffset2) {
        synchronized (FacebookSdk.class) {
            Intrinsics.checkNotNullParameter(applicationContext2, "applicationContext");
            sdkInitialize(applicationContext2, callbackRequestCodeOffset2, null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001f, code lost:
    
        com.facebook.FacebookSdk.callbackRequestCodeOffset = r3;
        sdkInitialize(r2, r4);
     */
    @kotlin.Deprecated(message = "")
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final synchronized void sdkInitialize(android.content.Context r2, int r3, com.facebook.FacebookSdk.InitializeCallback r4) {
        /*
            java.lang.Class<com.facebook.FacebookSdk> r0 = com.facebook.FacebookSdk.class
            monitor-enter(r0)
            java.lang.String r1 = "applicationContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)     // Catch: java.lang.Throwable -> L2e
            java.util.concurrent.atomic.AtomicBoolean r1 = com.facebook.FacebookSdk.sdkInitialized     // Catch: java.lang.Throwable -> L2e
            boolean r1 = r1.get()     // Catch: java.lang.Throwable -> L2e
            if (r1 == 0) goto L1d
            int r1 = com.facebook.FacebookSdk.callbackRequestCodeOffset     // Catch: java.lang.Throwable -> L2e
            if (r3 != r1) goto L15
            goto L1d
        L15:
            com.facebook.FacebookException r2 = new com.facebook.FacebookException     // Catch: java.lang.Throwable -> L2e
            java.lang.String r3 = "The callback request code offset can't be updated once the SDK is initialized. Call FacebookSdk.setCallbackRequestCodeOffset inside your Application.onCreate method"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2e
            throw r2     // Catch: java.lang.Throwable -> L2e
        L1d:
            if (r3 < 0) goto L26
            com.facebook.FacebookSdk.callbackRequestCodeOffset = r3     // Catch: java.lang.Throwable -> L2e
            sdkInitialize(r2, r4)     // Catch: java.lang.Throwable -> L2e
            monitor-exit(r0)
            return
        L26:
            com.facebook.FacebookException r2 = new com.facebook.FacebookException     // Catch: java.lang.Throwable -> L2e
            java.lang.String r3 = "The callback request code offset can't be negative."
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2e
            throw r2     // Catch: java.lang.Throwable -> L2e
        L2e:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookSdk.sdkInitialize(android.content.Context, int, com.facebook.FacebookSdk$InitializeCallback):void");
    }

    @Deprecated(message = "")
    @JvmStatic
    public static final synchronized void sdkInitialize(Context applicationContext2) {
        synchronized (FacebookSdk.class) {
            Intrinsics.checkNotNullParameter(applicationContext2, "applicationContext");
            sdkInitialize(applicationContext2, (InitializeCallback) null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004e A[Catch: all -> 0x011f, TryCatch #0 {, blocks: (B:4:0x0003, B:11:0x0013, B:12:0x0018, B:14:0x0038, B:16:0x0042, B:21:0x004e, B:23:0x0054, B:26:0x005d, B:28:0x0066, B:29:0x0069, B:31:0x006d, B:33:0x0071, B:35:0x0079, B:37:0x007f, B:38:0x0087, B:39:0x008c, B:40:0x008d, B:42:0x009d, B:45:0x00fd, B:46:0x0102, B:47:0x0103, B:48:0x0108, B:49:0x0109, B:50:0x0110, B:52:0x0111, B:53:0x0118, B:55:0x0119, B:56:0x011e), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0111 A[Catch: all -> 0x011f, TryCatch #0 {, blocks: (B:4:0x0003, B:11:0x0013, B:12:0x0018, B:14:0x0038, B:16:0x0042, B:21:0x004e, B:23:0x0054, B:26:0x005d, B:28:0x0066, B:29:0x0069, B:31:0x006d, B:33:0x0071, B:35:0x0079, B:37:0x007f, B:38:0x0087, B:39:0x008c, B:40:0x008d, B:42:0x009d, B:45:0x00fd, B:46:0x0102, B:47:0x0103, B:48:0x0108, B:49:0x0109, B:50:0x0110, B:52:0x0111, B:53:0x0118, B:55:0x0119, B:56:0x011e), top: B:3:0x0003 }] */
    @kotlin.Deprecated(message = "")
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final synchronized void sdkInitialize(android.content.Context r5, final com.facebook.FacebookSdk.InitializeCallback r6) {
        /*
            Method dump skipped, instructions count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookSdk.sdkInitialize(android.content.Context, com.facebook.FacebookSdk$InitializeCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sdkInitialize$lambda-3, reason: not valid java name */
    public static final File m1624sdkInitialize$lambda3() {
        Context context = applicationContext;
        if (context != null) {
            return context.getCacheDir();
        }
        Intrinsics.throwUninitializedPropertyAccessException("applicationContext");
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sdkInitialize$lambda-4, reason: not valid java name */
    public static final void m1625sdkInitialize$lambda4(boolean z) {
        if (z) {
            InstrumentManager instrumentManager = InstrumentManager.INSTANCE;
            InstrumentManager.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sdkInitialize$lambda-5, reason: not valid java name */
    public static final void m1626sdkInitialize$lambda5(boolean z) {
        if (z) {
            AppEventsManager appEventsManager = AppEventsManager.INSTANCE;
            AppEventsManager.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sdkInitialize$lambda-6, reason: not valid java name */
    public static final void m1627sdkInitialize$lambda6(boolean z) {
        if (z) {
            hasCustomTabsPrefetching = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sdkInitialize$lambda-7, reason: not valid java name */
    public static final void m1628sdkInitialize$lambda7(boolean z) {
        if (z) {
            ignoreAppSwitchToLoggedOut = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sdkInitialize$lambda-8, reason: not valid java name */
    public static final void m1629sdkInitialize$lambda8(boolean z) {
        if (z) {
            bypassAppSwitch = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sdkInitialize$lambda-9, reason: not valid java name */
    public static final Void m1630sdkInitialize$lambda9(InitializeCallback initializeCallback) {
        AccessTokenManager.INSTANCE.getInstance().loadCurrentAccessToken();
        ProfileManager.INSTANCE.getInstance().loadCurrentProfile();
        if (AccessToken.INSTANCE.isCurrentAccessTokenActive() && Profile.INSTANCE.getCurrentProfile() == null) {
            Profile.INSTANCE.fetchProfileForCurrentAccessToken();
        }
        if (initializeCallback != null) {
            initializeCallback.onInitialized();
        }
        AppEventsLogger.INSTANCE.initializeLib(getApplicationContext(), applicationId);
        UserSettingsManager userSettingsManager = UserSettingsManager.INSTANCE;
        UserSettingsManager.logIfAutoAppLinkEnabled();
        AppEventsLogger.Companion companion = AppEventsLogger.INSTANCE;
        Context applicationContext2 = getApplicationContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext().applicationContext");
        companion.newLogger(applicationContext2).flush();
        return null;
    }

    @JvmStatic
    public static final boolean isInitialized() {
        return sdkInitialized.get();
    }

    @JvmStatic
    public static final void fullyInitialize() {
        isFullyInitialized = true;
    }

    @JvmStatic
    public static final Set<LoggingBehavior> getLoggingBehaviors() {
        Set<LoggingBehavior> unmodifiableSet;
        HashSet<LoggingBehavior> hashSet = loggingBehaviors;
        synchronized (hashSet) {
            unmodifiableSet = Collections.unmodifiableSet(new HashSet(hashSet));
            Intrinsics.checkNotNullExpressionValue(unmodifiableSet, "unmodifiableSet(HashSet(loggingBehaviors))");
        }
        return unmodifiableSet;
    }

    @JvmStatic
    public static final void addLoggingBehavior(LoggingBehavior behavior) {
        Intrinsics.checkNotNullParameter(behavior, "behavior");
        HashSet<LoggingBehavior> hashSet = loggingBehaviors;
        synchronized (hashSet) {
            hashSet.add(behavior);
            INSTANCE.updateGraphDebugBehavior();
            Unit unit = Unit.INSTANCE;
        }
    }

    @JvmStatic
    public static final void removeLoggingBehavior(LoggingBehavior behavior) {
        Intrinsics.checkNotNullParameter(behavior, "behavior");
        HashSet<LoggingBehavior> hashSet = loggingBehaviors;
        synchronized (hashSet) {
            hashSet.remove(behavior);
        }
    }

    @JvmStatic
    public static final void clearLoggingBehaviors() {
        HashSet<LoggingBehavior> hashSet = loggingBehaviors;
        synchronized (hashSet) {
            hashSet.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    @JvmStatic
    public static final boolean isLoggingBehaviorEnabled(LoggingBehavior behavior) {
        boolean z;
        Intrinsics.checkNotNullParameter(behavior, "behavior");
        HashSet<LoggingBehavior> hashSet = loggingBehaviors;
        synchronized (hashSet) {
            if (isDebugEnabled()) {
                z = hashSet.contains(behavior);
            }
        }
        return z;
    }

    private final void updateGraphDebugBehavior() {
        HashSet<LoggingBehavior> hashSet = loggingBehaviors;
        if (!hashSet.contains(LoggingBehavior.GRAPH_API_DEBUG_INFO) || hashSet.contains(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            return;
        }
        hashSet.add(LoggingBehavior.GRAPH_API_DEBUG_WARNING);
    }

    @JvmStatic
    public static final String getGraphDomain() {
        AccessToken currentAccessToken = AccessToken.INSTANCE.getCurrentAccessToken();
        String graphDomain = currentAccessToken != null ? currentAccessToken.getGraphDomain() : null;
        Utility utility = Utility.INSTANCE;
        return Utility.getGraphDomainFromTokenDomain(graphDomain);
    }

    @JvmStatic
    public static final Context getApplicationContext() {
        Validate validate = Validate.INSTANCE;
        Validate.sdkInitialized();
        Context context = applicationContext;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("applicationContext");
        throw null;
    }

    @JvmStatic
    public static final void publishInstallAsync(Context context, final String applicationId2) {
        if (CrashShieldHandler.isObjectCrashing(FacebookSdk.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(applicationId2, "applicationId");
            final Context applicationContext2 = context.getApplicationContext();
            getExecutor().execute(new Runnable() { // from class: com.facebook.FacebookSdk$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    FacebookSdk.m1623publishInstallAsync$lambda15(applicationContext2, applicationId2);
                }
            });
            FeatureManager featureManager = FeatureManager.INSTANCE;
            if (FeatureManager.isEnabled(FeatureManager.Feature.OnDeviceEventProcessing)) {
                OnDeviceProcessingManager onDeviceProcessingManager = OnDeviceProcessingManager.INSTANCE;
                if (OnDeviceProcessingManager.isOnDeviceProcessingEnabled()) {
                    OnDeviceProcessingManager onDeviceProcessingManager2 = OnDeviceProcessingManager.INSTANCE;
                    OnDeviceProcessingManager.sendInstallEventAsync(applicationId2, ATTRIBUTION_PREFERENCES);
                }
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, FacebookSdk.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: publishInstallAsync$lambda-15, reason: not valid java name */
    public static final void m1623publishInstallAsync$lambda15(Context applicationContext2, String applicationId2) {
        Intrinsics.checkNotNullParameter(applicationId2, "$applicationId");
        FacebookSdk facebookSdk = INSTANCE;
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
        facebookSdk.publishInstallAndWaitForResponse(applicationContext2, applicationId2);
    }

    private final void publishInstallAndWaitForResponse(Context context, String applicationId2) {
        try {
            if (CrashShieldHandler.isObjectCrashing(this)) {
                return;
            }
            try {
                AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.INSTANCE.getAttributionIdentifiers(context);
                SharedPreferences sharedPreferences = context.getSharedPreferences(ATTRIBUTION_PREFERENCES, 0);
                String stringPlus = Intrinsics.stringPlus(applicationId2, "ping");
                long j = sharedPreferences.getLong(stringPlus, 0L);
                try {
                    AppEventsLoggerUtility appEventsLoggerUtility = AppEventsLoggerUtility.INSTANCE;
                    JSONObject jSONObjectForGraphAPICall = AppEventsLoggerUtility.getJSONObjectForGraphAPICall(AppEventsLoggerUtility.GraphAPIActivityType.MOBILE_INSTALL_EVENT, attributionIdentifiers, AppEventsLogger.INSTANCE.getAnonymousAppDeviceGUID(context), getLimitEventAndDataUsage(context), context);
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format(PUBLISH_ACTIVITY_PATH, Arrays.copyOf(new Object[]{applicationId2}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
                    GraphRequest createPostRequest = graphRequestCreator.createPostRequest(null, format, jSONObjectForGraphAPICall, null);
                    if (j == 0 && createPostRequest.executeAndWait().getError() == null) {
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putLong(stringPlus, System.currentTimeMillis());
                        edit.apply();
                    }
                } catch (JSONException e) {
                    throw new FacebookException("An error occurred while publishing install.", e);
                }
            } catch (Exception e2) {
                Utility utility = Utility.INSTANCE;
                Utility.logd("Facebook-publish", e2);
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    @JvmStatic
    public static final boolean getLimitEventAndDataUsage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Validate validate = Validate.INSTANCE;
        Validate.sdkInitialized();
        return context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).getBoolean("limitEventUsage", false);
    }

    @JvmStatic
    public static final void setLimitEventAndDataUsage(Context context, boolean limitEventUsage) {
        Intrinsics.checkNotNullParameter(context, "context");
        context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).edit().putBoolean("limitEventUsage", limitEventUsage).apply();
    }

    @JvmStatic
    public static final void loadDefaultsFromMetadata$facebook_core_release(Context context) {
        if (context == null) {
            return;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "try {\n          context.packageManager.getApplicationInfo(\n              context.packageName, PackageManager.GET_META_DATA)\n        } catch (e: PackageManager.NameNotFoundException) {\n          return\n        }");
            if (applicationInfo.metaData == null) {
                return;
            }
            if (applicationId == null) {
                Object obj = applicationInfo.metaData.get(APPLICATION_ID_PROPERTY);
                if (obj instanceof String) {
                    String str = (String) obj;
                    Locale ROOT = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                    String lowerCase = str.toLowerCase(ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                    if (StringsKt.startsWith$default(lowerCase, "fb", false, 2, (Object) null)) {
                        String substring = str.substring(2);
                        Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.String).substring(startIndex)");
                        applicationId = substring;
                    } else {
                        applicationId = str;
                    }
                } else if (obj instanceof Number) {
                    throw new FacebookException("App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.");
                }
            }
            if (applicationName == null) {
                applicationName = applicationInfo.metaData.getString(APPLICATION_NAME_PROPERTY);
            }
            if (appClientToken == null) {
                appClientToken = applicationInfo.metaData.getString(CLIENT_TOKEN_PROPERTY);
            }
            if (callbackRequestCodeOffset == DEFAULT_CALLBACK_REQUEST_CODE_OFFSET) {
                callbackRequestCodeOffset = applicationInfo.metaData.getInt(CALLBACK_OFFSET_PROPERTY, DEFAULT_CALLBACK_REQUEST_CODE_OFFSET);
            }
            if (codelessDebugLogEnabled == null) {
                codelessDebugLogEnabled = Boolean.valueOf(applicationInfo.metaData.getBoolean(CODELESS_DEBUG_LOG_ENABLED_PROPERTY, false));
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    @JvmStatic
    public static final String getApplicationSignature(Context context) {
        PackageManager packageManager;
        if (CrashShieldHandler.isObjectCrashing(FacebookSdk.class)) {
            return null;
        }
        try {
            Validate validate = Validate.INSTANCE;
            Validate.sdkInitialized();
            if (context == null || (packageManager = context.getPackageManager()) == null) {
                return null;
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 64);
                Signature[] signatureArr = packageInfo.signatures;
                if (signatureArr != null) {
                    if (!(signatureArr.length == 0)) {
                        MessageDigest messageDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1);
                        messageDigest.update(packageInfo.signatures[0].toByteArray());
                        return Base64.encodeToString(messageDigest.digest(), 9);
                    }
                }
            } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException unused) {
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, FacebookSdk.class);
            return null;
        }
    }

    @JvmStatic
    public static final String getApplicationId() {
        Validate validate = Validate.INSTANCE;
        Validate.sdkInitialized();
        String str = applicationId;
        if (str != null) {
            return str;
        }
        throw new FacebookException("A valid Facebook app id must be set in the AndroidManifest.xml or set by calling FacebookSdk.setApplicationId before initializing the sdk.");
    }

    @JvmStatic
    public static final void setApplicationId(String applicationId2) {
        Intrinsics.checkNotNullParameter(applicationId2, "applicationId");
        Validate validate = Validate.INSTANCE;
        Validate.notEmpty(applicationId2, "applicationId");
        applicationId = applicationId2;
    }

    @JvmStatic
    public static final String getApplicationName() {
        Validate validate = Validate.INSTANCE;
        Validate.sdkInitialized();
        return applicationName;
    }

    @JvmStatic
    public static final void setApplicationName(String applicationName2) {
        applicationName = applicationName2;
    }

    @JvmStatic
    public static final String getClientToken() {
        Validate validate = Validate.INSTANCE;
        Validate.sdkInitialized();
        String str = appClientToken;
        if (str != null) {
            return str;
        }
        throw new FacebookException("A valid Facebook client token must be set in the AndroidManifest.xml or set by calling FacebookSdk.setClientToken before initializing the sdk. Visit https://developers.facebook.com/docs/android/getting-started#add-app_id for more information.");
    }

    @JvmStatic
    public static final void setClientToken(String clientToken) {
        appClientToken = clientToken;
    }

    @JvmStatic
    public static final boolean getAutoInitEnabled() {
        UserSettingsManager userSettingsManager = UserSettingsManager.INSTANCE;
        return UserSettingsManager.getAutoInitEnabled();
    }

    @JvmStatic
    public static final void setAutoInitEnabled(boolean flag) {
        UserSettingsManager userSettingsManager = UserSettingsManager.INSTANCE;
        UserSettingsManager.setAutoInitEnabled(flag);
        if (flag) {
            fullyInitialize();
        }
    }

    @JvmStatic
    public static final boolean getAutoLogAppEventsEnabled() {
        UserSettingsManager userSettingsManager = UserSettingsManager.INSTANCE;
        return UserSettingsManager.getAutoLogAppEventsEnabled();
    }

    @JvmStatic
    public static final void setAutoLogAppEventsEnabled(boolean flag) {
        UserSettingsManager userSettingsManager = UserSettingsManager.INSTANCE;
        UserSettingsManager.setAutoLogAppEventsEnabled(flag);
        if (flag) {
            Application application = (Application) getApplicationContext();
            ActivityLifecycleTracker activityLifecycleTracker = ActivityLifecycleTracker.INSTANCE;
            ActivityLifecycleTracker.startTracking(application, getApplicationId());
        }
    }

    @JvmStatic
    public static final boolean getCodelessDebugLogEnabled() {
        Validate validate = Validate.INSTANCE;
        Validate.sdkInitialized();
        Boolean bool = codelessDebugLogEnabled;
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @JvmStatic
    public static final boolean getCodelessSetupEnabled() {
        UserSettingsManager userSettingsManager = UserSettingsManager.INSTANCE;
        return UserSettingsManager.getCodelessSetupEnabled();
    }

    @JvmStatic
    public static final boolean getAdvertiserIDCollectionEnabled() {
        UserSettingsManager userSettingsManager = UserSettingsManager.INSTANCE;
        return UserSettingsManager.getAdvertiserIDCollectionEnabled();
    }

    @JvmStatic
    public static final void setAdvertiserIDCollectionEnabled(boolean flag) {
        UserSettingsManager userSettingsManager = UserSettingsManager.INSTANCE;
        UserSettingsManager.setAdvertiserIDCollectionEnabled(flag);
    }

    @JvmStatic
    public static final void setCodelessDebugLogEnabled(boolean flag) {
        codelessDebugLogEnabled = Boolean.valueOf(flag);
    }

    @JvmStatic
    public static final boolean getMonitorEnabled() {
        UserSettingsManager userSettingsManager = UserSettingsManager.INSTANCE;
        return UserSettingsManager.getMonitorEnabled();
    }

    @JvmStatic
    public static final void setMonitorEnabled(boolean flag) {
        UserSettingsManager userSettingsManager = UserSettingsManager.INSTANCE;
        UserSettingsManager.setMonitorEnabled(flag);
    }

    @JvmStatic
    public static final void setDataProcessingOptions(String[] options) {
        if (CrashShieldHandler.isObjectCrashing(FacebookSdk.class)) {
            return;
        }
        try {
            setDataProcessingOptions(options, 0, 0);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, FacebookSdk.class);
        }
    }

    @JvmStatic
    public static final void setDataProcessingOptions(String[] options, int country, int state) {
        if (CrashShieldHandler.isObjectCrashing(FacebookSdk.class)) {
            return;
        }
        if (options == null) {
            try {
                options = new String[0];
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, FacebookSdk.class);
                return;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(DATA_PROCESSION_OPTIONS, new JSONArray((Collection) ArraysKt.toList(options)));
            jSONObject.put(DATA_PROCESSION_OPTIONS_COUNTRY, country);
            jSONObject.put(DATA_PROCESSION_OPTIONS_STATE, state);
            Context context = applicationContext;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("applicationContext");
                throw null;
            }
            context.getSharedPreferences(DATA_PROCESSING_OPTIONS_PREFERENCES, 0).edit().putString(DATA_PROCESSION_OPTIONS, jSONObject.toString()).apply();
        } catch (JSONException unused) {
        }
    }

    @JvmStatic
    public static final File getCacheDir() {
        Validate validate = Validate.INSTANCE;
        Validate.sdkInitialized();
        LockOnGetVariable<File> lockOnGetVariable = cacheDir;
        if (lockOnGetVariable != null) {
            return lockOnGetVariable.getValue();
        }
        Intrinsics.throwUninitializedPropertyAccessException("cacheDir");
        throw null;
    }

    @JvmStatic
    public static final void setCacheDir(File cacheDir2) {
        Intrinsics.checkNotNullParameter(cacheDir2, "cacheDir");
        cacheDir = new LockOnGetVariable<>(cacheDir2);
    }

    @JvmStatic
    public static final int getCallbackRequestCodeOffset() {
        Validate validate = Validate.INSTANCE;
        Validate.sdkInitialized();
        return callbackRequestCodeOffset;
    }

    @JvmStatic
    public static final boolean isFacebookRequestCode(int requestCode) {
        int i = callbackRequestCodeOffset;
        return requestCode >= i && requestCode < i + 100;
    }

    @JvmStatic
    public static final void setGraphRequestCreator$facebook_core_release(GraphRequestCreator graphRequestCreator2) {
        Intrinsics.checkNotNullParameter(graphRequestCreator2, "graphRequestCreator");
        graphRequestCreator = graphRequestCreator2;
    }
}
