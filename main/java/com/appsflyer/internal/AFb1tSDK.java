package com.appsflyer.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AFLogger;
import com.appsflyer.AFVersionDeclaration;
import com.appsflyer.AppsFlyerConsent;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerInAppPurchaseValidatorListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.appsflyer.deeplink.DeepLinkListener;
import com.appsflyer.deeplink.DeepLinkResult;
import com.appsflyer.internal.AFd1iSDK;
import com.appsflyer.internal.AFd1xSDK;
import com.appsflyer.internal.AFe1fSDK;
import com.appsflyer.internal.AFf1lSDK;
import com.appsflyer.internal.AFg1zSDK;
import com.appsflyer.internal.components.network.http.ResponseNetwork;
import com.appsflyer.internal.platform_extension.PluginInfo;
import com.google.android.gms.common.GoogleApiAvailability;
import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;
import io.jsonwebtoken.JwtParser;
import java.net.URI;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes.dex */
public final class AFb1tSDK extends AppsFlyerLib {
    private static int $10 = 0;
    private static int $11 = 1;
    static final String AFInAppEventParameterName;
    static AppsFlyerInAppPurchaseValidatorListener AFKeystoreWrapper = null;
    private static char[] afDebugLog = null;
    private static char afErrorLog = 0;
    private static int afRDLog = 1;
    private static int afWarnLog;

    /* renamed from: e */
    private static AFb1tSDK f173e;
    public static final String valueOf;
    public static final String values;
    private Map<Long, String> AFLogger;
    private final AFd1lSDK afInfoLog;
    private AFf1cSDK afVerboseLog;
    private boolean force;

    /* renamed from: v */
    private Application f176v;

    /* renamed from: w */
    private SharedPreferences f177w;
    public volatile AppsFlyerConversionListener AFInAppEventType = null;
    private long unregisterClient = -1;
    private long registerClient = -1;

    /* renamed from: d */
    private long f174d = TimeUnit.SECONDS.toMillis(5);

    /* renamed from: i */
    private boolean f175i = false;

    static void AFKeystoreWrapper() {
        afDebugLog = new char[]{29480, 29493, 29489, 29497, 29483, 29447, 29502, 29501, 29484};
        afErrorLog = (char) 18113;
    }

    static /* synthetic */ void AFInAppEventParameterName(AFb1tSDK aFb1tSDK) {
        int i = afRDLog + 13;
        afWarnLog = i % 128;
        boolean z = i % 2 == 0;
        Object obj = null;
        aFb1tSDK.registerClient();
        if (!z) {
            throw null;
        }
        int i2 = afWarnLog + 9;
        afRDLog = i2 % 128;
        if ((i2 % 2 == 0 ? 'J' : ']') == ']') {
            return;
        }
        obj.hashCode();
        throw null;
    }

    static /* synthetic */ void AFInAppEventParameterName(AFb1tSDK aFb1tSDK, AFa1pSDK aFa1pSDK) {
        int i = afRDLog + 23;
        afWarnLog = i % 128;
        boolean z = i % 2 == 0;
        aFb1tSDK.AFKeystoreWrapper(aFa1pSDK);
        if (!z) {
            int i2 = 59 / 0;
        }
    }

    static /* synthetic */ boolean AFInAppEventParameterName(AFb1tSDK aFb1tSDK, boolean z) {
        int i = afWarnLog;
        int i2 = i + 105;
        afRDLog = i2 % 128;
        int i3 = i2 % 2;
        aFb1tSDK.f175i = z;
        int i4 = i + 65;
        afRDLog = i4 % 128;
        if ((i4 % 2 == 0 ? '^' : 'N') == 'N') {
            return z;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    static /* synthetic */ Application AFKeystoreWrapper(AFb1tSDK aFb1tSDK) {
        int i = afWarnLog + 15;
        int i2 = i % 128;
        afRDLog = i2;
        int i3 = i % 2;
        Application application = aFb1tSDK.f176v;
        int i4 = i2 + 1;
        afWarnLog = i4 % 128;
        if (!(i4 % 2 != 0)) {
            return application;
        }
        int i5 = 92 / 0;
        return application;
    }

    static /* synthetic */ long values(AFb1tSDK aFb1tSDK, long j) {
        int i = afRDLog;
        int i2 = i + 11;
        afWarnLog = i2 % 128;
        char c = i2 % 2 != 0 ? '0' : (char) 1;
        aFb1tSDK.registerClient = j;
        if (c != 1) {
            int i3 = 64 / 0;
        }
        int i4 = i + 65;
        afWarnLog = i4 % 128;
        if (!(i4 % 2 != 0)) {
            return j;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    static /* synthetic */ AFf1cSDK values(AFb1tSDK aFb1tSDK) {
        int i = afWarnLog + 81;
        afRDLog = i % 128;
        if (!(i % 2 == 0)) {
            return aFb1tSDK.unregisterClient();
        }
        aFb1tSDK.unregisterClient();
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    static {
        AFKeystoreWrapper();
        AFInAppEventParameterName = "286";
        values = "6.13";
        StringBuilder sb = new StringBuilder();
        sb.append("6.13");
        sb.append("/androidevent?buildnumber=6.13.1&app_id=");
        valueOf = sb.toString();
        Object obj = null;
        AFKeystoreWrapper = null;
        f173e = new AFb1tSDK();
        int i = afWarnLog + 87;
        afRDLog = i % 128;
        if ((i % 2 != 0 ? 'B' : (char) 2) == 'B') {
            return;
        }
        obj.hashCode();
        throw null;
    }

    public final AFd1mSDK values() {
        int i = afWarnLog + 35;
        int i2 = i % 128;
        afRDLog = i2;
        int i3 = i % 2;
        AFd1lSDK aFd1lSDK = this.afInfoLog;
        int i4 = i2 + 21;
        afWarnLog = i4 % 128;
        if (i4 % 2 == 0) {
            return aFd1lSDK;
        }
        int i5 = 10 / 0;
        return aFd1lSDK;
    }

    private synchronized AFf1cSDK unregisterClient() {
        AFf1cSDK aFf1cSDK;
        if (this.afVerboseLog == null) {
            this.afVerboseLog = new AFf1cSDK() { // from class: com.appsflyer.internal.AFb1tSDK$$ExternalSyntheticLambda2
                @Override // com.appsflyer.internal.AFf1cSDK
                public final void onRemoteConfigUpdateFinished(AFf1gSDK aFf1gSDK) {
                    AFb1tSDK.this.AFKeystoreWrapper(aFf1gSDK);
                }
            };
            int i = afWarnLog + 27;
            afRDLog = i % 128;
            int i2 = i % 2;
        }
        aFf1cSDK = this.afVerboseLog;
        int i3 = afWarnLog + 23;
        afRDLog = i3 % 128;
        if ((i3 % 2 == 0 ? JwtParser.SEPARATOR_CHAR : ':') != ':') {
            Object obj = null;
            obj.hashCode();
            throw null;
        }
        return aFf1cSDK;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void AFKeystoreWrapper(AFf1gSDK aFf1gSDK) {
        AFd1mSDK values2 = values();
        if (!(aFf1gSDK != AFf1gSDK.SUCCESS)) {
            int i = afRDLog + 79;
            afWarnLog = i % 128;
            int i2 = i % 2;
            values2.onAppOpenAttributionNative().AFInAppEventType();
        }
        if ((!values2.mo78i().values() ? 'D' : ']') == 'D') {
            int i3 = afWarnLog + 67;
            afRDLog = i3 % 128;
            int i4 = i3 % 2;
            values2.afLogForce().values();
            return;
        }
        values2.afLogForce().valueOf();
        int i5 = afWarnLog + 3;
        afRDLog = i5 % 128;
        int i6 = i5 % 2;
    }

    public AFb1tSDK() {
        AFVersionDeclaration.init();
        this.afInfoLog = new AFd1lSDK();
        values().afLogForce().valueOf();
        values().afLogForce().AFKeystoreWrapper();
        AFe1fSDK afInfoLog = values().afInfoLog();
        afInfoLog.valueOf.add(new AFa1vSDK(this, (byte) 0));
    }

    public static AFb1tSDK valueOf() {
        int i = afRDLog;
        int i2 = i + 39;
        afWarnLog = i2 % 128;
        Object obj = null;
        if ((i2 % 2 != 0 ? Typography.quote : ',') != ',') {
            obj.hashCode();
            throw null;
        }
        AFb1tSDK aFb1tSDK = f173e;
        int i3 = i + 105;
        afWarnLog = i3 % 128;
        if (i3 % 2 == 0) {
            return aFb1tSDK;
        }
        throw null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    @Deprecated
    public final void performOnAppAttribution(Context context, URI uri) {
        int i = afWarnLog + 81;
        afRDLog = i % 128;
        Object obj = null;
        if (i % 2 == 0) {
            obj.hashCode();
            throw null;
        }
        if ((uri == null) || uri.toString().isEmpty()) {
            AFc1uSDK afWarnLog2 = values().afWarnLog();
            StringBuilder sb = new StringBuilder("Link is \"");
            sb.append(uri);
            sb.append("\"");
            afWarnLog2.valueOf(sb.toString(), DeepLinkResult.Error.NETWORK);
            int i2 = afWarnLog + 35;
            afRDLog = i2 % 128;
            if (i2 % 2 != 0) {
                return;
            }
            obj.hashCode();
            throw null;
        }
        if (context != null) {
            AFInAppEventParameterName(context);
            values().afWarnLog().valueOf(context, AFc1pSDK.valueOf(values().AppsFlyer2dXConversionCallback()), Uri.parse(uri.toString()));
            return;
        }
        AFc1uSDK afWarnLog3 = values().afWarnLog();
        StringBuilder sb2 = new StringBuilder("Context is \"");
        sb2.append(context);
        sb2.append("\"");
        afWarnLog3.valueOf(sb2.toString(), DeepLinkResult.Error.NETWORK);
        int i3 = afRDLog + 11;
        afWarnLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    @Deprecated
    public final void setSharingFilter(String... strArr) {
        int i = afRDLog + 51;
        afWarnLog = i % 128;
        char c = i % 2 != 0 ? '\r' : (char) 16;
        setSharingFilterForPartners(strArr);
        if (c != 16) {
            int i2 = 8 / 0;
        }
        int i3 = afRDLog + 53;
        afWarnLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    @Deprecated
    public final void setSharingFilterForAllPartners() {
        int i = afWarnLog + 15;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? 'N' : '=') != 'N') {
            setSharingFilterForPartners(LanguageType.SERVER_FOLLOW_SYSTEM);
        } else {
            String[] strArr = new String[0];
            strArr[0] = LanguageType.SERVER_FOLLOW_SYSTEM;
            setSharingFilterForPartners(strArr);
        }
        int i2 = afWarnLog + 103;
        afRDLog = i2 % 128;
        if ((i2 % 2 == 0 ? 'N' : '_') != 'N') {
            return;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setSharingFilterForPartners(String... strArr) {
        values().afErrorLogForExcManagerOnly().AFInAppEventType = new AFd1ySDK(strArr);
        int i = afRDLog + 61;
        afWarnLog = i % 128;
        if ((i % 2 != 0 ? (char) 30 : '?') != '?') {
            int i2 = 84 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void appendParametersToDeepLinkingURL(String str, Map<String, String> map) {
        int i = afRDLog + 107;
        afWarnLog = i % 128;
        if (!(i % 2 != 0)) {
            AFc1uSDK afWarnLog2 = values().afWarnLog();
            afWarnLog2.AFInAppEventType = str;
            afWarnLog2.values = map;
            int i2 = afRDLog + 49;
            afWarnLog = i2 % 128;
            if ((i2 % 2 != 0 ? '[' : '/') == '/') {
                return;
            } else {
                throw null;
            }
        }
        AFc1uSDK afWarnLog3 = values().afWarnLog();
        afWarnLog3.AFInAppEventType = str;
        afWarnLog3.values = map;
        throw null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void subscribeForDeepLink(DeepLinkListener deepLinkListener) {
        int i = afWarnLog + 97;
        afRDLog = i % 128;
        int i2 = i % 2;
        subscribeForDeepLink(deepLinkListener, TimeUnit.SECONDS.toMillis(3L));
        int i3 = afRDLog + 101;
        afWarnLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void performOnDeepLinking(final Intent intent, Context context) {
        int i = afWarnLog;
        int i2 = i + 21;
        afRDLog = i2 % 128;
        if (i2 % 2 == 0) {
            throw null;
        }
        if (!(intent != null)) {
            values().afWarnLog().valueOf("performOnDeepLinking was called with null intent", DeepLinkResult.Error.DEVELOPER_ERROR);
            return;
        }
        if ((context == null ? ' ' : '0') == ' ') {
            int i3 = i + 121;
            afRDLog = i3 % 128;
            int i4 = i3 % 2;
            values().afWarnLog().valueOf("performOnDeepLinking was called with null context", DeepLinkResult.Error.DEVELOPER_ERROR);
            return;
        }
        final Context applicationContext = context.getApplicationContext();
        AFInAppEventParameterName(applicationContext);
        values().AFInAppEventParameterName().execute(new Runnable() { // from class: com.appsflyer.internal.AFb1tSDK$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AFb1tSDK.this.AFKeystoreWrapper(applicationContext, intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0068, code lost:
    
        if ((r2) != true) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006f, code lost:
    
        r6 = r6 + 49;
        com.appsflyer.internal.AFb1tSDK.afRDLog = r6 % 128;
        r6 = r6 % 2;
        r0.valueOf("No direct deep link", null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007c, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006d, code lost:
    
        if (r2 == false) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void AFKeystoreWrapper(android.content.Context r8, android.content.Intent r9) {
        /*
            r7 = this;
            r7.AFInAppEventParameterName(r8)
            com.appsflyer.internal.AFd1mSDK r0 = r7.values()
            com.appsflyer.internal.AFc1uSDK r0 = r0.afWarnLog()
            com.appsflyer.internal.AFd1mSDK r1 = r7.values()
            com.appsflyer.internal.AFd1tSDK r1 = r1.values()
            r2 = 18
            if (r9 == 0) goto L19
            r3 = r2
            goto L1b
        L19:
            r3 = 92
        L1b:
            r4 = 0
            if (r3 == r2) goto L1f
            goto L30
        L1f:
            java.lang.String r2 = "android.intent.action.VIEW"
            java.lang.String r3 = r9.getAction()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L30
            android.net.Uri r2 = r9.getData()
            goto L31
        L30:
            r2 = r4
        L31:
            r3 = 0
            r5 = 1
            if (r2 == 0) goto L37
            r6 = r3
            goto L38
        L37:
            r6 = r5
        L38:
            if (r6 == 0) goto L3b
            goto L47
        L3b:
            java.lang.String r2 = r2.toString()
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L47
            r2 = r5
            goto L48
        L47:
            r2 = r3
        L48:
            java.lang.String r6 = "ddl_sent"
            boolean r1 = r1.values(r6)
            if (r1 == 0) goto L52
            r1 = r3
            goto L53
        L52:
            r1 = r5
        L53:
            if (r1 == r5) goto L7d
            int r1 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r1 = r1 + 13
            int r6 = r1 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r6
            int r1 = r1 % 2
            if (r1 == 0) goto L6d
            r1 = 78
            int r1 = r1 / r3
            if (r2 != 0) goto L67
            goto L68
        L67:
            r3 = r5
        L68:
            if (r3 == r5) goto L7d
            goto L6f
        L6b:
            r8 = move-exception
            throw r8
        L6d:
            if (r2 != 0) goto L7d
        L6f:
            int r6 = r6 + 49
            int r8 = r6 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r8
            int r6 = r6 % 2
            java.lang.String r8 = "No direct deep link"
            r0.valueOf(r8, r4)
            return
        L7d:
            com.appsflyer.internal.AFd1mSDK r1 = r0.unregisterClient
            com.appsflyer.internal.AFc1oSDK r1 = r1.AppsFlyer2dXConversionCallback()
            com.appsflyer.internal.AFc1pSDK r1 = com.appsflyer.internal.AFc1pSDK.valueOf(r1)
            r0.AFKeystoreWrapper(r1, r9, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.AFKeystoreWrapper(android.content.Context, android.content.Intent):void");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void addPushNotificationDeepLinkPath(String... strArr) {
        List<String> asList = Arrays.asList(strArr);
        List<List<String>> list = values().afWarnLog().AFKeystoreWrapper;
        if (!list.contains(asList)) {
            int i = afRDLog + 7;
            afWarnLog = i % 128;
            int i2 = i % 2;
            list.add(asList);
            int i3 = afRDLog + 89;
            afWarnLog = i3 % 128;
            int i4 = i3 % 2;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setDisableAdvertisingIdentifiers(boolean z) {
        boolean z2;
        AFLogger.afDebugLog("setDisableAdvertisingIdentifiers: ".concat(String.valueOf(z)));
        if (!(!z)) {
            int i = afRDLog + 113;
            afWarnLog = i % 128;
            int i2 = i % 2;
            z2 = false;
        } else {
            z2 = true;
        }
        AFb1rSDK.AFInAppEventParameterName = Boolean.valueOf(z2);
        AFd1mSDK values2 = values();
        values2.afErrorLogForExcManagerOnly().registerClient = z;
        if (!(z)) {
            AFe1fSDK afInfoLog = values2.afInfoLog();
            afInfoLog.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(new AFf1vSDK(values())));
            return;
        }
        int i3 = afRDLog + 39;
        afWarnLog = i3 % 128;
        if (!(i3 % 2 != 0)) {
            values2.afErrorLogForExcManagerOnly().f189e = null;
        } else {
            values2.afErrorLogForExcManagerOnly().f189e = null;
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setDisableNetworkData(boolean z) {
        int i = afWarnLog + 51;
        afRDLog = i % 128;
        int i2 = i % 2;
        AFLogger.afDebugLog("setDisableNetworkData: ".concat(String.valueOf(z)));
        AFInAppEventType(AppsFlyerProperties.DISABLE_NETWORK_DATA, z);
        int i3 = afWarnLog + 109;
        afRDLog = i3 % 128;
        if (i3 % 2 == 0) {
            Object obj = null;
            obj.hashCode();
            throw null;
        }
    }

    public final void valueOf(Context context, Intent intent) {
        AFi1aSDK aFi1aSDK = new AFi1aSDK(intent);
        if (!(aFi1aSDK.AFKeystoreWrapper("appsflyer_preinstall") == null)) {
            int i = afWarnLog + 37;
            afRDLog = i % 128;
            int i2 = i % 2;
            registerClient(aFi1aSDK.AFKeystoreWrapper("appsflyer_preinstall"));
        }
        AFLogger.afInfoLog("****** onReceive called *******");
        AppsFlyerProperties.getInstance();
        String AFKeystoreWrapper2 = aFi1aSDK.AFKeystoreWrapper("referrer");
        AFLogger.afInfoLog("Play store referrer: ".concat(String.valueOf(AFKeystoreWrapper2)));
        if (AFKeystoreWrapper2 != null) {
            AFKeystoreWrapper(context).valueOf("referrer", AFKeystoreWrapper2);
            AppsFlyerProperties appsFlyerProperties = AppsFlyerProperties.getInstance();
            appsFlyerProperties.set("AF_REFERRER", AFKeystoreWrapper2);
            appsFlyerProperties.AFInAppEventType = AFKeystoreWrapper2;
            if ((AppsFlyerProperties.getInstance().AFInAppEventType() ? '\\' : (char) 0) != 0) {
                int i3 = afWarnLog + 47;
                afRDLog = i3 % 128;
                if ((i3 % 2 == 0 ? (char) 1 : '3') != 1) {
                    AFLogger.afInfoLog("onReceive: isLaunchCalled");
                    AFInAppEventParameterName(context, AFg1aSDK.onReceive);
                    AFKeystoreWrapper(AFKeystoreWrapper2);
                } else {
                    AFLogger.afInfoLog("onReceive: isLaunchCalled");
                    AFInAppEventParameterName(context, AFg1aSDK.onReceive);
                    AFKeystoreWrapper(AFKeystoreWrapper2);
                    int i4 = 87 / 0;
                }
            }
        }
    }

    private static void AFInAppEventParameterName(JSONObject jSONObject) {
        String str;
        ArrayList arrayList = new ArrayList();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                JSONArray jSONArray = new JSONArray((String) jSONObject.get(keys.next()));
                int i = 0;
                while (true) {
                    if (i < jSONArray.length()) {
                        arrayList.add(Long.valueOf(jSONArray.getLong(i)));
                        i++;
                    }
                }
            } catch (JSONException e) {
                AFLogger.afErrorLogForExcManagerOnly("error at timeStampArr", e);
            }
        }
        Collections.sort(arrayList);
        Iterator<String> keys2 = jSONObject.keys();
        loop2: while (true) {
            str = null;
            while (true) {
                if (!(!keys2.hasNext())) {
                    int i2 = afWarnLog + 55;
                    afRDLog = i2 % 128;
                    int i3 = i2 % 2;
                    if (str != null) {
                        break loop2;
                    }
                    String next = keys2.next();
                    try {
                        JSONArray jSONArray2 = new JSONArray((String) jSONObject.get(next));
                        int i4 = 0;
                        while (i4 < jSONArray2.length()) {
                            if (jSONArray2.getLong(i4) != ((Long) arrayList.get(0)).longValue() && jSONArray2.getLong(i4) != ((Long) arrayList.get(1)).longValue() && jSONArray2.getLong(i4) != ((Long) arrayList.get(arrayList.size() - 1)).longValue()) {
                                i4++;
                                str = next;
                            }
                        }
                    } catch (JSONException e2) {
                        AFLogger.afErrorLogForExcManagerOnly("error at manageExtraReferrers", e2);
                    }
                } else {
                    break loop2;
                }
            }
            int i5 = afRDLog + 117;
            afWarnLog = i5 % 128;
            int i6 = i5 % 2;
        }
        if ((str != null ? (char) 24 : 'H') != 24) {
            return;
        }
        jSONObject.remove(str);
        int i7 = afRDLog + 73;
        afWarnLog = i7 % 128;
        int i8 = i7 % 2;
    }

    public final void AFInAppEventType(Context context, String str) {
        JSONArray jSONArray;
        JSONObject jSONObject;
        int i = afWarnLog + 83;
        afRDLog = i % 128;
        Object obj = null;
        try {
            if (i % 2 == 0) {
                AFLogger.afDebugLog("received a new (extra) referrer: ".concat(String.valueOf(str)));
                System.currentTimeMillis();
                AFKeystoreWrapper(context).AFKeystoreWrapper("extraReferrers", (String) null);
                obj.hashCode();
                throw null;
            }
            AFLogger.afDebugLog("received a new (extra) referrer: ".concat(String.valueOf(str)));
            long currentTimeMillis = System.currentTimeMillis();
            String AFKeystoreWrapper2 = AFKeystoreWrapper(context).AFKeystoreWrapper("extraReferrers", (String) null);
            if (AFKeystoreWrapper2 == null) {
                jSONObject = new JSONObject();
                jSONArray = new JSONArray();
            } else {
                JSONObject jSONObject2 = new JSONObject(AFKeystoreWrapper2);
                jSONArray = jSONObject2.has(str) ? new JSONArray((String) jSONObject2.get(str)) : new JSONArray();
                jSONObject = jSONObject2;
            }
            if (jSONArray.length() < 5) {
                int i2 = afRDLog + 121;
                afWarnLog = i2 % 128;
                if (i2 % 2 != 0) {
                    jSONArray.put(currentTimeMillis);
                    obj.hashCode();
                    throw null;
                }
                jSONArray.put(currentTimeMillis);
                int i3 = afWarnLog + 115;
                afRDLog = i3 % 128;
                int i4 = i3 % 2;
            }
            if (!(((long) jSONObject.length()) < 4)) {
                int i5 = afRDLog + 43;
                afWarnLog = i5 % 128;
                int i6 = i5 % 2;
                AFInAppEventParameterName(jSONObject);
            }
            jSONObject.put(str, jSONArray.toString());
            AFKeystoreWrapper(context).valueOf("extraReferrers", jSONObject.toString());
        } catch (JSONException e) {
            AFLogger.afErrorLogForExcManagerOnly("error at addReferrer", e);
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("Couldn't save referrer - ");
            sb.append(str);
            sb.append(": ");
            AFLogger.afErrorLog(sb.toString(), th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void valueOf(AFd1mSDK aFd1mSDK) {
        int i = afRDLog + 115;
        afWarnLog = i % 128;
        int i2 = i % 2;
        aFd1mSDK.afRDLog().AFKeystoreWrapper();
        int i3 = afRDLog + 115;
        afWarnLog = i3 % 128;
        int i4 = i3 % 2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0057, code lost:
    
        r5.values().values("is_stop_tracking_used", true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0055, code lost:
    
        if ((r4 ? 0 : 21) != 21) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0031, code lost:
    
        if (r4 != false) goto L19;
     */
    @Override // com.appsflyer.AppsFlyerLib
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void stop(boolean r4, android.content.Context r5) {
        /*
            r3 = this;
            int r0 = com.appsflyer.internal.AFb1tSDK.afWarnLog
            int r0 = r0 + 47
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r1
            int r0 = r0 % 2
            r1 = 44
            if (r0 != 0) goto L11
            r0 = 20
            goto L12
        L11:
            r0 = r1
        L12:
            r2 = 0
            if (r0 == r1) goto L36
            r3.AFInAppEventParameterName(r5)
            com.appsflyer.internal.AFd1mSDK r5 = r3.values()
            com.appsflyer.internal.AFg1xSDK r0 = r5.force()
            r0.AFLogger = r4
            java.util.concurrent.ExecutorService r0 = r5.AFInAppEventParameterName()
            com.appsflyer.internal.AFb1tSDK$$ExternalSyntheticLambda5 r1 = new com.appsflyer.internal.AFb1tSDK$$ExternalSyntheticLambda5
            r1.<init>()
            r0.submit(r1)
            r0 = 40
            int r0 = r0 / r2
            if (r4 == 0) goto L61
            goto L57
        L34:
            r4 = move-exception
            throw r4
        L36:
            r3.AFInAppEventParameterName(r5)
            com.appsflyer.internal.AFd1mSDK r5 = r3.values()
            com.appsflyer.internal.AFg1xSDK r0 = r5.force()
            r0.AFLogger = r4
            java.util.concurrent.ExecutorService r0 = r5.AFInAppEventParameterName()
            com.appsflyer.internal.AFb1tSDK$$ExternalSyntheticLambda5 r1 = new com.appsflyer.internal.AFb1tSDK$$ExternalSyntheticLambda5
            r1.<init>()
            r0.submit(r1)
            r0 = 21
            if (r4 == 0) goto L54
            goto L55
        L54:
            r2 = r0
        L55:
            if (r2 == r0) goto L61
        L57:
            com.appsflyer.internal.AFd1tSDK r4 = r5.values()
            java.lang.String r5 = "is_stop_tracking_used"
            r0 = 1
            r4.values(r5, r0)
        L61:
            int r4 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r4 = r4 + 113
            int r5 = r4 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r5
            int r4 = r4 % 2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.stop(boolean, android.content.Context):void");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final String getSdkVersion() {
        int i = afWarnLog + 73;
        afRDLog = i % 128;
        int i2 = i % 2;
        values().mo78i().AFInAppEventParameterName("getSdkVersion", new String[0]);
        String m83e = AFd1sSDK.m83e();
        int i3 = afRDLog + 63;
        afWarnLog = i3 % 128;
        int i4 = i3 % 2;
        return m83e;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void enableTCFDataCollection(boolean z) {
        int i = afRDLog + 29;
        afWarnLog = i % 128;
        boolean z2 = i % 2 == 0;
        AFInAppEventType(AppsFlyerProperties.ENABLE_TCF_DATA_COLLECTION, Boolean.toString(z));
        if (z2) {
            return;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void onPause(Context context) {
        int i = afRDLog + 25;
        afWarnLog = i % 128;
        int i2 = i % 2;
        values().AFLogger$LogLevel().valueOf();
        int i3 = afRDLog + 113;
        afWarnLog = i3 % 128;
        if (!(i3 % 2 == 0)) {
            int i4 = 42 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void updateServerUninstallToken(Context context, String str) {
        AFInAppEventParameterName(context);
        AFg1oSDK aFg1oSDK = new AFg1oSDK(context);
        if (str == null || str.trim().isEmpty()) {
            AFLogger.INSTANCE.m103w(AFg1gSDK.UNINSTALL, "Firebase Token is either empty or null and was not registered.");
            return;
        }
        AFLogger.INSTANCE.m101i(AFg1gSDK.UNINSTALL, "Firebase Refreshed Token = ".concat(String.valueOf(str)));
        AFg1rSDK AFInAppEventParameterName2 = aFg1oSDK.AFInAppEventParameterName();
        if (AFInAppEventParameterName2 == null || !str.equals(AFInAppEventParameterName2.AFInAppEventParameterName)) {
            long currentTimeMillis = System.currentTimeMillis();
            boolean z = AFInAppEventParameterName2 == null || currentTimeMillis - AFInAppEventParameterName2.values > TimeUnit.SECONDS.toMillis(2L);
            AFg1rSDK aFg1rSDK = new AFg1rSDK(str, currentTimeMillis, !z);
            aFg1oSDK.values.valueOf("afUninstallToken", aFg1rSDK.AFInAppEventParameterName);
            aFg1oSDK.values.AFInAppEventParameterName("afUninstallToken_received_time", aFg1rSDK.values);
            aFg1oSDK.values.values("afUninstallToken_queued", aFg1rSDK.valueOf());
            if (z) {
                AFg1oSDK.AFInAppEventParameterName(str);
            }
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setDebugLog(boolean z) {
        AFLogger.LogLevel logLevel;
        if ((z ? 'W' : 'Y') != 'Y') {
            int i = afWarnLog + 59;
            afRDLog = i % 128;
            int i2 = i % 2;
            logLevel = AFLogger.LogLevel.DEBUG;
        } else {
            logLevel = AFLogger.LogLevel.NONE;
            int i3 = afRDLog + 115;
            afWarnLog = i3 % 128;
            int i4 = i3 % 2;
        }
        setLogLevel(logLevel);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setOaidData(String str) {
        int i = afRDLog + 41;
        afWarnLog = i % 128;
        int i2 = i % 2;
        values().mo78i().AFInAppEventParameterName("setOaidData", str);
        AFb1rSDK.AFKeystoreWrapper = str;
        int i3 = afWarnLog + 91;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
    }

    private static void AFInAppEventType(String str, String str2) {
        int i = afWarnLog + 55;
        afRDLog = i % 128;
        int i2 = i % 2;
        AppsFlyerProperties.getInstance().set(str, str2);
        int i3 = afRDLog + 77;
        afWarnLog = i3 % 128;
        if ((i3 % 2 != 0 ? '+' : 'T') != '+') {
            return;
        }
        int i4 = 32 / 0;
    }

    private static void AFInAppEventType(String str, boolean z) {
        int i = afWarnLog + 105;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? '\b' : (char) 28) == '\b') {
            AppsFlyerProperties.getInstance().set(str, z);
            Object obj = null;
            obj.hashCode();
            throw null;
        }
        AppsFlyerProperties.getInstance().set(str, z);
        int i2 = afRDLog + 31;
        afWarnLog = i2 % 128;
        if ((i2 % 2 != 0 ? 'J' : 'Y') != 'J') {
            return;
        }
        int i3 = 11 / 0;
    }

    private static String AFInAppEventType(String str) {
        int i = afWarnLog + 123;
        afRDLog = i % 128;
        int i2 = i % 2;
        String string = AppsFlyerProperties.getInstance().getString(str);
        int i3 = afRDLog + 83;
        afWarnLog = i3 % 128;
        if (i3 % 2 == 0) {
            return string;
        }
        throw null;
    }

    private static boolean AFInAppEventParameterName(String str) {
        int i = afRDLog + 75;
        afWarnLog = i % 128;
        return i % 2 == 0 ? AppsFlyerProperties.getInstance().getBoolean(str, false) : AppsFlyerProperties.getInstance().getBoolean(str, true);
    }

    public final boolean AFInAppEventParameterName() {
        int i = afWarnLog + 11;
        afRDLog = i % 128;
        int i2 = i % 2;
        if ((AFInAppEventParameterName(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID) ? '4' : 'c') == '4') {
            int i3 = afRDLog + 47;
            afWarnLog = i3 % 128;
            int i4 = i3 % 2;
            if (AFInAppEventType() == null) {
                int i5 = afRDLog + 75;
                afWarnLog = i5 % 128;
                return i5 % 2 == 0;
            }
        }
        return false;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void waitForCustomerUserId(boolean z) {
        int i = afRDLog + 93;
        afWarnLog = i % 128;
        int i2 = i % 2;
        AFLogger.afInfoLog("initAfterCustomerUserID: ".concat(String.valueOf(z)), true);
        AFInAppEventType(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, z);
        int i3 = afRDLog + 53;
        afWarnLog = i3 % 128;
        if ((i3 % 2 != 0 ? '2' : '9') == '9') {
        } else {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setCustomerIdAndLogSession(String str, Context context) {
        if (context != null) {
            int i = afRDLog + 99;
            afWarnLog = i % 128;
            int i2 = i % 2;
            if (AFInAppEventParameterName()) {
                setCustomerUserId(str);
                StringBuilder sb = new StringBuilder("CustomerUserId set: ");
                sb.append(str);
                sb.append(" - Initializing AppsFlyer Tacking");
                AFLogger.afInfoLog(sb.toString(), true);
                String referrer = AppsFlyerProperties.getInstance().getReferrer(values().values());
                AFInAppEventParameterName(context, AFg1aSDK.setCustomerIdAndLogSession);
                String str2 = values().force().registerClient;
                Object obj = null;
                if (referrer == null) {
                    int i3 = afRDLog + 19;
                    afWarnLog = i3 % 128;
                    if ((i3 % 2 != 0 ? 'K' : (char) 5) == 'K') {
                        throw null;
                    }
                    referrer = "";
                }
                if (!(context instanceof Activity ? false : true)) {
                    ((Activity) context).getIntent();
                    int i4 = afRDLog + 77;
                    afWarnLog = i4 % 128;
                    int i5 = i4 % 2;
                }
                valueOf(context, referrer);
                int i6 = afRDLog + 59;
                afWarnLog = i6 % 128;
                if (i6 % 2 == 0) {
                    return;
                }
                obj.hashCode();
                throw null;
            }
            setCustomerUserId(str);
            AFLogger.afInfoLog("waitForCustomerUserId is false; setting CustomerUserID: ".concat(String.valueOf(str)), true);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003d, code lost:
    
        if (r2 == '9') goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003f, code lost:
    
        com.appsflyer.AFLogger.afInfoLog("No out-of-store value set");
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0044, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0045, code lost:
    
        r0 = com.appsflyer.internal.AFb1tSDK.afRDLog + 31;
        com.appsflyer.internal.AFb1tSDK.afWarnLog = r0 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0051, code lost:
    
        if ((r0 % 2) == 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0053, code lost:
    
        r0 = org.spongycastle.pqc.math.linearalgebra.Matrix.MATRIX_TYPE_ZERO;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0057, code lost:
    
        if (r0 != 21) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0059, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005a, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0056, code lost:
    
        r0 = 21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0030, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x002e, code lost:
    
        if ((r0 != null ? 4 : 'A') != 'A') goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001c, code lost:
    
        if (r0 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0031, code lost:
    
        r4 = values(r4, "AF_STORE");
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0039, code lost:
    
        if (r4 == null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x003b, code lost:
    
        r2 = '9';
     */
    @Override // com.appsflyer.AppsFlyerLib
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String getOutOfStore(android.content.Context r4) {
        /*
            r3 = this;
            int r0 = com.appsflyer.internal.AFb1tSDK.afWarnLog
            int r0 = r0 + 99
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r1
            int r0 = r0 % 2
            java.lang.String r1 = "api_store_value"
            r2 = 65
            if (r0 != 0) goto L21
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r0 = r0.getString(r1)
            r1 = 41
            int r1 = r1 / 0
            if (r0 == 0) goto L31
            goto L30
        L1f:
            r4 = move-exception
            throw r4
        L21:
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r0 = r0.getString(r1)
            if (r0 == 0) goto L2d
            r1 = 4
            goto L2e
        L2d:
            r1 = r2
        L2e:
            if (r1 == r2) goto L31
        L30:
            return r0
        L31:
            java.lang.String r0 = "AF_STORE"
            java.lang.String r4 = r3.values(r4, r0)
            r0 = 57
            if (r4 == 0) goto L3c
            r2 = r0
        L3c:
            r1 = 0
            if (r2 == r0) goto L45
            java.lang.String r4 = "No out-of-store value set"
            com.appsflyer.AFLogger.afInfoLog(r4)
            return r1
        L45:
            int r0 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r0 = r0 + 31
            int r2 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r2
            int r0 = r0 % 2
            r2 = 21
            if (r0 == 0) goto L56
            r0 = 90
            goto L57
        L56:
            r0 = r2
        L57:
            if (r0 != r2) goto L5a
            return r4
        L5a:
            throw r1     // Catch: java.lang.Throwable -> L5b
        L5b:
            r4 = move-exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.getOutOfStore(android.content.Context):java.lang.String");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setOutOfStore(String str) {
        int i = afWarnLog;
        int i2 = i + 71;
        afRDLog = i2 % 128;
        int i3 = i2 % 2;
        if (!(str == null)) {
            int i4 = i + 33;
            afRDLog = i4 % 128;
            if (i4 % 2 == 0) {
            }
            String lowerCase = str.toLowerCase(Locale.getDefault());
            AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_STORE_FROM_API, lowerCase);
            AFLogger.afInfoLog("Store API set with value: ".concat(String.valueOf(lowerCase)), true);
            return;
        }
        AFLogger.afWarnLog("Cannot set setOutOfStore with null", true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0046, code lost:
    
        if ((r6.equals(com.appsflyer.AppsFlyerProperties.getInstance().getString(com.appsflyer.AppsFlyerProperties.ONELINK_ID))) != true) goto L13;
     */
    @Override // com.appsflyer.AppsFlyerLib
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setAppInviteOneLink(java.lang.String r6) {
        /*
            r5 = this;
            com.appsflyer.internal.AFd1mSDK r0 = r5.values()
            com.appsflyer.internal.AFb1bSDK r0 = r0.mo78i()
            r1 = 1
            java.lang.String[] r2 = new java.lang.String[r1]
            r3 = 0
            r2[r3] = r6
            java.lang.String r4 = "setAppInviteOneLink"
            r0.AFInAppEventParameterName(r4, r2)
            java.lang.String r0 = "setAppInviteOneLink = "
            java.lang.String r2 = java.lang.String.valueOf(r6)
            java.lang.String r0 = r0.concat(r2)
            com.appsflyer.AFLogger.afInfoLog(r0)
            r0 = 43
            if (r6 == 0) goto L27
            r2 = 89
            goto L28
        L27:
            r2 = r0
        L28:
            java.lang.String r4 = "oneLinkSlug"
            if (r2 == r0) goto L48
            int r0 = com.appsflyer.internal.AFb1tSDK.afWarnLog
            int r0 = r0 + 91
            int r2 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r2
            int r0 = r0 % 2
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r0 = r0.getString(r4)
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L45
            goto L46
        L45:
            r3 = r1
        L46:
            if (r3 == r1) goto L6d
        L48:
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r1 = "onelinkDomain"
            r0.remove(r1)
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r1 = "onelinkVersion"
            r0.remove(r1)
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r1 = "onelinkScheme"
            r0.remove(r1)
            int r0 = com.appsflyer.internal.AFb1tSDK.afWarnLog
            int r0 = r0 + 113
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r1
            int r0 = r0 % 2
        L6d:
            AFInAppEventType(r4, r6)
            int r6 = com.appsflyer.internal.AFb1tSDK.afWarnLog
            int r6 = r6 + 77
            int r0 = r6 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r0
            int r6 = r6 % 2
            if (r6 == 0) goto L7d
            return
        L7d:
            r6 = 0
            r6.hashCode()     // Catch: java.lang.Throwable -> L82
            throw r6     // Catch: java.lang.Throwable -> L82
        L82:
            r6 = move-exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.setAppInviteOneLink(java.lang.String):void");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setAdditionalData(Map<String, Object> map) {
        int i = afRDLog + 93;
        afWarnLog = i % 128;
        int i2 = i % 2;
        if (map != null) {
            values().mo78i().AFInAppEventParameterName("setAdditionalData", map.toString());
            AppsFlyerProperties.getInstance().setCustomData(new JSONObject(map).toString());
        }
        int i3 = afWarnLog + 25;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002a, code lost:
    
        if (r19.getIntent() == null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002c, code lost:
    
        r3 = values().mo78i();
        r9 = new java.lang.StringBuilder("activity_intent_");
        r9.append(r19.getIntent().toString());
        r3.AFInAppEventParameterName("sendPushNotificationData", r19.getLocalClassName(), r9.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0058, code lost:
    
        if (r19 == null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x005a, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x005d, code lost:
    
        if (r3 == true) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x005f, code lost:
    
        values().mo78i().AFInAppEventParameterName("sendPushNotificationData", "activity_null");
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0071, code lost:
    
        r3 = com.appsflyer.internal.AFb1tSDK.afWarnLog + 55;
        com.appsflyer.internal.AFb1tSDK.afRDLog = r3 % 128;
        r3 = r3 % 2;
        values().mo78i().AFInAppEventParameterName("sendPushNotificationData", r19.getLocalClassName(), "activity_intent_null");
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x005c, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0024, code lost:
    
        if (r19 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        if ((r19 != null) != true) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x01ba  */
    @Override // com.appsflyer.AppsFlyerLib
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void sendPushNotificationData(android.app.Activity r19) {
        /*
            Method dump skipped, instructions count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.sendPushNotificationData(android.app.Activity):void");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setUserEmails(String... strArr) {
        int i = afRDLog + 3;
        afWarnLog = i % 128;
        if ((i % 2 != 0 ? '+' : 'Q') != 'Q') {
            values().mo78i().AFInAppEventParameterName("setUserEmails", strArr);
            setUserEmails(AppsFlyerProperties.EmailsCryptType.NONE, strArr);
            int i2 = 1 / 0;
        } else {
            values().mo78i().AFInAppEventParameterName("setUserEmails", strArr);
            setUserEmails(AppsFlyerProperties.EmailsCryptType.NONE, strArr);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setUserEmails(AppsFlyerProperties.EmailsCryptType emailsCryptType, String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length + 1);
        arrayList.add(emailsCryptType.toString());
        arrayList.addAll(Arrays.asList(strArr));
        values().mo78i().AFInAppEventParameterName("setUserEmails", (String[]) arrayList.toArray(new String[strArr.length + 1]));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.EMAIL_CRYPT_TYPE, emailsCryptType.getValue());
        HashMap hashMap = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        int length = strArr.length;
        Object obj = null;
        int i = 0;
        String str = null;
        while (true) {
            if (i >= length) {
                break;
            }
            int i2 = afRDLog + 97;
            afWarnLog = i2 % 128;
            int i3 = i2 % 2;
            String str2 = strArr[i];
            if (C06954.values[emailsCryptType.ordinal()] != 2) {
                arrayList2.add(AFb1mSDK.valueOf(str2));
                str = "sha256_el_arr";
            } else {
                arrayList2.add(str2);
                str = "plain_el_arr";
            }
            i++;
            int i4 = afRDLog + 111;
            afWarnLog = i4 % 128;
            int i5 = i4 % 2;
        }
        hashMap.put(str, arrayList2);
        AppsFlyerProperties.getInstance().setUserEmails(new JSONObject(hashMap).toString());
        int i6 = afRDLog + 111;
        afWarnLog = i6 % 128;
        if ((i6 % 2 != 0 ? 'A' : (char) 22) == 22) {
            return;
        }
        obj.hashCode();
        throw null;
    }

    /* renamed from: com.appsflyer.internal.AFb1tSDK$4 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C06954 {
        static final /* synthetic */ int[] values;

        static {
            int[] iArr = new int[AppsFlyerProperties.EmailsCryptType.values().length];
            values = iArr;
            try {
                iArr[AppsFlyerProperties.EmailsCryptType.SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                values[AppsFlyerProperties.EmailsCryptType.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setCollectAndroidID(boolean z) {
        int i = afRDLog + 5;
        afWarnLog = i % 128;
        if ((i % 2 != 0 ? '\\' : ';') != '\\') {
            values().mo78i().AFInAppEventParameterName("setCollectAndroidID", String.valueOf(z));
        } else {
            AFb1bSDK mo78i = values().mo78i();
            String[] strArr = new String[1];
            strArr[1] = String.valueOf(z);
            mo78i.AFInAppEventParameterName("setCollectAndroidID", strArr);
        }
        AFInAppEventType(AppsFlyerProperties.COLLECT_ANDROID_ID, Boolean.toString(z));
        AFInAppEventType(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, Boolean.toString(z));
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setCollectIMEI(boolean z) {
        int i = afWarnLog + 35;
        afRDLog = i % 128;
        if (i % 2 != 0) {
            values().mo78i().AFInAppEventParameterName("setCollectIMEI", String.valueOf(z));
        } else {
            values().mo78i().AFInAppEventParameterName("setCollectIMEI", String.valueOf(z));
        }
        AFInAppEventType(AppsFlyerProperties.COLLECT_IMEI, Boolean.toString(z));
        AFInAppEventType(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, Boolean.toString(z));
        int i2 = afRDLog + 71;
        afWarnLog = i2 % 128;
        if ((i2 % 2 != 0 ? (char) 1 : '5') != 1) {
            return;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    @Deprecated
    public final void setCollectOaid(boolean z) {
        int i = afWarnLog + 107;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? (char) 0 : '(') != 0) {
            values().mo78i().AFInAppEventParameterName("setCollectOaid", String.valueOf(z));
        } else {
            AFb1bSDK mo78i = values().mo78i();
            String[] strArr = new String[0];
            strArr[1] = String.valueOf(z);
            mo78i.AFInAppEventParameterName("setCollectOaid", strArr);
        }
        AFInAppEventType(AppsFlyerProperties.COLLECT_OAID, Boolean.toString(z));
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final AppsFlyerLib init(String str, AppsFlyerConversionListener appsFlyerConversionListener, Context context) {
        long j;
        if (this.force) {
            return this;
        }
        this.force = true;
        values().force().registerClient = str;
        AFb1lSDK.valueOf(str);
        if ((context != null ? '*' : 'b') == 'b') {
            AFLogger.INSTANCE.m103w(AFg1gSDK.REFERRER, "context is null, Google Install Referrer will be not initialized");
        } else {
            int i = afRDLog + 37;
            afWarnLog = i % 128;
            if (!(i % 2 != 0)) {
                AFInAppEventParameterName(context);
                Application valueOf2 = AFb1uSDK.valueOf(context);
                if (valueOf2 == null) {
                    return this;
                }
                this.f176v = valueOf2;
                values().afRDLog().values();
                values().AFLogger().valueOf = System.currentTimeMillis();
                AFe1fSDK afInfoLog = values().afInfoLog();
                afInfoLog.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(new AFf1vSDK(values())));
                AFh1aSDK level = values().getLevel();
                level.values = Build.VERSION.SDK_INT >= 31 ? new AFi1uSDK(level.valueOf) : new AFi1vSDK(level.valueOf);
                values().onAppOpenAttributionNative().valueOf(new AFd1iSDK.AFa1ySDK() { // from class: com.appsflyer.internal.AFb1tSDK$$ExternalSyntheticLambda3
                    @Override // com.appsflyer.internal.AFd1iSDK.AFa1ySDK
                    public final void onConfigurationChanged(boolean z) {
                        AFb1tSDK.this.values(z);
                    }
                });
                values().mo76d().values(unregisterClient());
                AFi1iSDK mo79v = values().mo79v();
                Runnable runnable = new Runnable() { // from class: com.appsflyer.internal.AFb1tSDK$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        AFb1tSDK.this.AFLogger();
                    }
                };
                AFi1sSDK AFKeystoreWrapper2 = mo79v.AFKeystoreWrapper(runnable);
                Runnable AFInAppEventType = mo79v.AFInAppEventType(AFKeystoreWrapper2, runnable);
                mo79v.valueOf(AFKeystoreWrapper2);
                mo79v.valueOf(new AFi1hSDK(mo79v.AFInAppEventType.AFInAppEventType(), AFInAppEventType));
                mo79v.valueOf(new AFi1oSDK(AFInAppEventType, mo79v.AFInAppEventType));
                mo79v.valueOf(new AFi1gSDK(AFInAppEventType, mo79v.AFInAppEventType));
                mo79v.values(AFInAppEventType);
                if (!mo79v.valueOf()) {
                    Context context2 = mo79v.AFInAppEventType.mo80w().valueOf;
                    AFd1mSDK aFd1mSDK = mo79v.AFInAppEventType;
                    List<ResolveInfo> queryIntentContentProviders = context2.getPackageManager().queryIntentContentProviders(new Intent("com.appsflyer.referrer.INSTALL_PROVIDER"), 0);
                    if (queryIntentContentProviders != null && !queryIntentContentProviders.isEmpty()) {
                        ArrayList arrayList = new ArrayList();
                        Iterator<ResolveInfo> it = queryIntentContentProviders.iterator();
                        while (it.hasNext()) {
                            int i2 = afWarnLog + 89;
                            afRDLog = i2 % 128;
                            int i3 = i2 % 2;
                            ProviderInfo providerInfo = it.next().providerInfo;
                            if (providerInfo != null) {
                                arrayList.add(new AFi1lSDK(providerInfo, AFInAppEventType, aFd1mSDK));
                            } else {
                                AFLogger.INSTANCE.m103w(AFg1gSDK.PREINSTALL, "com.appsflyer.referrer.INSTALL_PROVIDER Action is set for non ContentProvider component");
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            mo79v.AFInAppEventParameterName.addAll(arrayList);
                            AFLogger aFLogger = AFLogger.INSTANCE;
                            AFg1gSDK aFg1gSDK = AFg1gSDK.PREINSTALL;
                            StringBuilder sb = new StringBuilder("Detected ");
                            sb.append(arrayList.size());
                            sb.append(" valid preinstall provider(s)");
                            aFLogger.m96d(aFg1gSDK, sb.toString());
                        }
                    }
                }
                AFi1jSDK[] AFKeystoreWrapper3 = mo79v.AFKeystoreWrapper();
                int length = AFKeystoreWrapper3.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        break;
                    }
                    int i5 = afRDLog + 125;
                    afWarnLog = i5 % 128;
                    int i6 = i5 % 2;
                    AFKeystoreWrapper3[i4].valueOf(mo79v.AFInAppEventType.mo80w().valueOf);
                    i4++;
                }
                final AFg1xSDK force = this.afInfoLog.force();
                AFd1sSDK AFInAppEventType2 = values().AFInAppEventType();
                force.values = System.currentTimeMillis();
                AFg1zSDK aFg1zSDK = force.valueOf;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(AFb1kSDK.AFInAppEventType(AFInAppEventType2.AFInAppEventParameterName, AFInAppEventType2.AFKeystoreWrapper));
                sb2.append(force.values);
                byte[] AFKeystoreWrapper4 = AFb1mSDK.AFKeystoreWrapper(sb2.toString());
                if (AFKeystoreWrapper4 != null) {
                    if (!(AFKeystoreWrapper4.length <= 0)) {
                        int i7 = afWarnLog + 73;
                        afRDLog = i7 % 128;
                        if (i7 % 2 != 0 ? AFKeystoreWrapper4.length > 8 : AFKeystoreWrapper4.length > 108) {
                            AFKeystoreWrapper4 = Arrays.copyOfRange(AFKeystoreWrapper4, 0, 8);
                        }
                        ByteBuffer allocate = ByteBuffer.allocate(8);
                        allocate.put(AFKeystoreWrapper4);
                        allocate.flip();
                        j = allocate.getLong();
                        force.AFInAppEventParameterName = aFg1zSDK.AFInAppEventParameterName(j, force.AFInAppEventType.valueOf, new AFg1zSDK.AFa1vSDK() { // from class: com.appsflyer.internal.AFg1xSDK.5
                            public C07205() {
                            }

                            @Override // com.appsflyer.internal.AFg1zSDK.AFa1vSDK
                            public final void AFInAppEventParameterName(String str2, String str3) {
                                AFg1xSDK.this.AFKeystoreWrapper = new ConcurrentHashMap();
                                AFg1xSDK.this.AFKeystoreWrapper.put("signedData", str2);
                                AFg1xSDK.this.AFKeystoreWrapper.put("signature", str3);
                                AFg1xSDK.this.AFInAppEventParameterName();
                                AFLogger.afInfoLog("Successfully retrieved Google LVL data.");
                            }

                            @Override // com.appsflyer.internal.AFg1zSDK.AFa1vSDK
                            public final void AFInAppEventType(String str2, Exception exc) {
                                AFg1xSDK.this.AFKeystoreWrapper = new ConcurrentHashMap();
                                String message = exc.getMessage();
                                if (message == null) {
                                    message = "unknown";
                                }
                                AFg1xSDK.this.AFInAppEventParameterName();
                                AFg1xSDK.this.AFKeystoreWrapper.put("error", message);
                                AFLogger.afErrorLog(str2, exc, true, true, false);
                            }
                        });
                    }
                }
                j = -1;
                force.AFInAppEventParameterName = aFg1zSDK.AFInAppEventParameterName(j, force.AFInAppEventType.valueOf, new AFg1zSDK.AFa1vSDK() { // from class: com.appsflyer.internal.AFg1xSDK.5
                    public C07205() {
                    }

                    @Override // com.appsflyer.internal.AFg1zSDK.AFa1vSDK
                    public final void AFInAppEventParameterName(String str2, String str3) {
                        AFg1xSDK.this.AFKeystoreWrapper = new ConcurrentHashMap();
                        AFg1xSDK.this.AFKeystoreWrapper.put("signedData", str2);
                        AFg1xSDK.this.AFKeystoreWrapper.put("signature", str3);
                        AFg1xSDK.this.AFInAppEventParameterName();
                        AFLogger.afInfoLog("Successfully retrieved Google LVL data.");
                    }

                    @Override // com.appsflyer.internal.AFg1zSDK.AFa1vSDK
                    public final void AFInAppEventType(String str2, Exception exc) {
                        AFg1xSDK.this.AFKeystoreWrapper = new ConcurrentHashMap();
                        String message = exc.getMessage();
                        if (message == null) {
                            message = "unknown";
                        }
                        AFg1xSDK.this.AFInAppEventParameterName();
                        AFg1xSDK.this.AFKeystoreWrapper.put("error", message);
                        AFLogger.afErrorLog(str2, exc, true, true, false);
                    }
                });
            } else {
                AFInAppEventParameterName(context);
                AFb1uSDK.valueOf(context);
                throw null;
            }
        }
        AFb1bSDK mo78i = values().mo78i();
        String[] strArr = new String[2];
        strArr[0] = str;
        strArr[1] = appsFlyerConversionListener == null ? JsonSerializer.Null : "conversionDataListener";
        mo78i.AFInAppEventParameterName("init", strArr);
        AFLogger.INSTANCE.force(AFg1gSDK.GENERAL, String.format("Initializing AppsFlyer SDK: (v%s.%s)", "6.13.1", AFInAppEventParameterName));
        this.AFInAppEventType = appsFlyerConversionListener;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void values(boolean z) {
        int i = afWarnLog;
        int i2 = i + 75;
        afRDLog = i2 % 128;
        int i3 = i2 % 2;
        if ((z ? (char) 23 : (char) 15) == 15) {
            values().afLogForce().AFInAppEventParameterName();
            int i4 = afRDLog + 33;
            afWarnLog = i4 % 128;
            int i5 = i4 % 2;
            return;
        }
        int i6 = i + 123;
        afRDLog = i6 % 128;
        if ((i6 % 2 == 0 ? ' ' : '-') != ' ') {
            values().afLogForce().AFInAppEventType();
            return;
        }
        values().afLogForce().AFInAppEventType();
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void AFLogger() {
        AFKeystoreWrapper(new AFh1wSDK());
        int i = afRDLog + 65;
        afWarnLog = i % 128;
        int i2 = i % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void enableFacebookDeferredApplinks(boolean z) {
        int i = afRDLog + 109;
        afWarnLog = i % 128;
        if (!(i % 2 == 0)) {
            values().afErrorLog().values(z);
            int i2 = 42 / 0;
        } else {
            values().afErrorLog().values(z);
        }
        int i3 = afWarnLog + 117;
        afRDLog = i3 % 128;
        if ((i3 % 2 == 0 ? '2' : 'Z') == 'Z') {
        } else {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void start(Context context) {
        int i = afWarnLog + 35;
        afRDLog = i % 128;
        int i2 = i % 2;
        start(context, null);
        int i3 = afWarnLog + 21;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void start(Context context, String str) {
        int i = afRDLog + 109;
        afWarnLog = i % 128;
        boolean z = i % 2 != 0;
        start(context, str, null);
        if (z) {
            throw null;
        }
        int i2 = afWarnLog + 45;
        afRDLog = i2 % 128;
        int i3 = i2 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void start(Context context, String str, final AppsFlyerRequestListener appsFlyerRequestListener) {
        if (values().AFLogger$LogLevel().AFKeystoreWrapper()) {
            return;
        }
        if (!this.force) {
            AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! The API call 'start()' must be called after the 'init(String, AppsFlyerConversionListener)' API method, which should be called on the Application's onCreate.");
            if (str == null) {
                if (appsFlyerRequestListener != null) {
                    int i = afRDLog + 49;
                    afWarnLog = i % 128;
                    if (i % 2 != 0) {
                        appsFlyerRequestListener.onError(37, "No dev key");
                        return;
                    } else {
                        appsFlyerRequestListener.onError(41, "No dev key");
                        return;
                    }
                }
                return;
            }
        }
        AFInAppEventParameterName(context);
        final AFg1cSDK AFLogger = values().AFLogger();
        AFLogger.values(AFa1qSDK.AFKeystoreWrapper(context));
        Object obj = null;
        if ((this.f176v == null ? (char) 17 : (char) 30) == 17) {
            Application valueOf2 = AFb1uSDK.valueOf(context);
            if (valueOf2 == null) {
                return;
            }
            int i2 = afWarnLog + 69;
            afRDLog = i2 % 128;
            if ((i2 % 2 == 0 ? (char) 17 : (char) 23) == 17) {
                this.f176v = valueOf2;
                obj.hashCode();
                throw null;
            }
            this.f176v = valueOf2;
        }
        values().mo78i().AFInAppEventParameterName("start", str);
        AFLogger aFLogger = AFLogger.INSTANCE;
        AFg1gSDK aFg1gSDK = AFg1gSDK.GENERAL;
        String str2 = AFInAppEventParameterName;
        aFLogger.m101i(aFg1gSDK, String.format("Starting AppsFlyer: (v%s.%s)", "6.13.1", str2));
        AFLogger aFLogger2 = AFLogger.INSTANCE;
        AFg1gSDK aFg1gSDK2 = AFg1gSDK.GENERAL;
        StringBuilder sb = new StringBuilder("Build Number: ");
        sb.append(str2);
        aFLogger2.m101i(aFg1gSDK2, sb.toString());
        AppsFlyerProperties.getInstance().loadProperties(values().values());
        if (TextUtils.isEmpty(str)) {
            if ((TextUtils.isEmpty(values().force().registerClient) ? (char) 1 : ')') != ')') {
                int i3 = afRDLog + 43;
                afWarnLog = i3 % 128;
                if ((i3 % 2 != 0 ? '(' : '\t') == '(') {
                    AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! You must provide AppsFlyer Dev-Key either in the 'init' API method (should be called on Application's onCreate),or in the start() API (should be called on Activity's onCreate).");
                    obj.hashCode();
                    throw null;
                }
                AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! You must provide AppsFlyer Dev-Key either in the 'init' API method (should be called on Application's onCreate),or in the start() API (should be called on Activity's onCreate).");
                if ((appsFlyerRequestListener != null ? ' ' : 'B') != 'B') {
                    appsFlyerRequestListener.onError(41, "No dev key");
                    return;
                }
                return;
            }
        } else {
            int i4 = afWarnLog + 1;
            afRDLog = i4 % 128;
            if ((i4 % 2 != 0 ? (char) 0 : (char) 1) != 0) {
                values().force().registerClient = str;
                AFb1lSDK.valueOf(str);
                int i5 = 23 / 0;
            } else {
                values().force().registerClient = str;
                AFb1lSDK.valueOf(str);
            }
        }
        values().mo76d().values(unregisterClient());
        registerClient();
        values(this.f176v.getBaseContext());
        values().afErrorLog().AFInAppEventType();
        this.afInfoLog.AFLogger$LogLevel().values(context, new AFd1xSDK.AFa1vSDK() { // from class: com.appsflyer.internal.AFb1tSDK.1
            @Override // com.appsflyer.internal.AFd1xSDK.AFa1vSDK
            public final void AFKeystoreWrapper(AFh1uSDK aFh1uSDK) {
                AFLogger.AFKeystoreWrapper();
                AFd1mSDK values2 = AFb1tSDK.this.values();
                values2.mo76d().values(AFb1tSDK.values(AFb1tSDK.this));
                AFb1tSDK.AFInAppEventParameterName(AFb1tSDK.this);
                int AFInAppEventParameterName2 = values2.AFInAppEventType().AFKeystoreWrapper.AFInAppEventParameterName("appsFlyerCount", 0);
                AFLogger.afInfoLog("onBecameForeground");
                if (AFInAppEventParameterName2 < 2) {
                    AFb1tSDK.this.values().registerClient().AFInAppEventParameterName();
                }
                AFh1sSDK aFh1sSDK = new AFh1sSDK();
                if (aFh1uSDK != null) {
                    AFb1tSDK.this.values().afWarnLog().AFKeystoreWrapper(AFc1pSDK.values(aFh1sSDK), aFh1uSDK.AFKeystoreWrapper, values2.mo80w().valueOf);
                }
                AFb1tSDK aFb1tSDK = AFb1tSDK.this;
                aFh1sSDK.AFInAppEventType = appsFlyerRequestListener;
                aFb1tSDK.AFInAppEventParameterName(aFh1sSDK, aFh1uSDK);
            }

            @Override // com.appsflyer.internal.AFd1xSDK.AFa1vSDK
            public final void valueOf() {
                Context context2 = AFb1tSDK.this.values().mo80w().valueOf;
                AFLogger.afInfoLog("onBecameBackground");
                AFg1cSDK aFg1cSDK = AFLogger;
                long currentTimeMillis = System.currentTimeMillis();
                if (aFg1cSDK.f255d != 0) {
                    long j = currentTimeMillis - aFg1cSDK.f255d;
                    if (j > 0 && j < 1000) {
                        j = 1000;
                    }
                    aFg1cSDK.f257i = TimeUnit.MILLISECONDS.toSeconds(j);
                    aFg1cSDK.AFInAppEventType.AFInAppEventParameterName("prev_session_dur", aFg1cSDK.f257i);
                } else {
                    AFLogger.afInfoLog("Metrics: fg ts is missing");
                }
                AFLogger.afInfoLog("callStatsBackground background call");
                AFb1tSDK.this.values().onAppOpenAttributionNative().AFInAppEventParameterName();
                AFb1bSDK mo78i = AFb1tSDK.this.values().mo78i();
                if (mo78i.mo67d()) {
                    mo78i.AFKeystoreWrapper();
                    if (context2 != null && !AppsFlyerLib.getInstance().isStopped()) {
                        mo78i.valueOf(context2.getPackageName(), context2.getPackageManager());
                    }
                    mo78i.valueOf();
                } else {
                    AFLogger.afDebugLog("RD status is OFF");
                }
                AFb1tSDK.this.values().registerClient().values();
                AFb1tSDK.this.values().AppsFlyer2dXConversionCallback().values();
            }
        });
    }

    private static void values(Context context) {
        int i = afRDLog + 53;
        afWarnLog = i % 128;
        int i2 = i % 2;
        try {
            if (!((context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.flags & 32768) == 0)) {
                int i3 = afWarnLog + 73;
                afRDLog = i3 % 128;
                int i4 = i3 % 2;
                if ((context.getResources().getIdentifier("appsflyer_backup_rules", "xml", context.getPackageName()) != 0 ? '.' : (char) 15) != '.') {
                    AFLogger.INSTANCE.mo59w(AFg1gSDK.GENERAL, "'allowBackup' is set to true; appsflyer_backup_rules.xml not detected.\nAppsFlyer shared preferences should be excluded from auto backup by adding: <exclude domain=\"sharedpref\" path=\"appsflyer-data\"/> to the Application's <full-backup-content> rules", true);
                    int i5 = afRDLog + 71;
                    afWarnLog = i5 % 128;
                    int i6 = i5 % 2;
                    return;
                }
                int i7 = afRDLog + 75;
                afWarnLog = i7 % 128;
                int i8 = i7 % 2;
                AFLogger.INSTANCE.mo57i(AFg1gSDK.GENERAL, "appsflyer_backup_rules.xml detected, using AppsFlyer defined backup rules for AppsFlyer SDK data", true);
                int i9 = afWarnLog + 87;
                afRDLog = i9 % 128;
                if (i9 % 2 == 0) {
                    int i10 = 5 / 0;
                }
            }
        } catch (Exception e) {
            AFLogger.INSTANCE.m99e(AFg1gSDK.GENERAL, "checkBackupRules Exception", e, false, false);
            AFLogger.INSTANCE.m102v(AFg1gSDK.GENERAL, "checkBackupRules Exception: ".concat(String.valueOf(e)));
        }
    }

    public static String AFInAppEventType() {
        int i = afWarnLog + 3;
        afRDLog = i % 128;
        int i2 = i % 2;
        String AFInAppEventType = AFInAppEventType(AppsFlyerProperties.APP_USER_ID);
        int i3 = afWarnLog + 113;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
        return AFInAppEventType;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setCustomerUserId(String str) {
        int i = afWarnLog + 111;
        afRDLog = i % 128;
        int i2 = i % 2;
        values().mo78i().AFInAppEventParameterName("setCustomerUserId", str);
        AFLogger.afInfoLog("setCustomerUserId = ".concat(String.valueOf(str)));
        AFInAppEventType(AppsFlyerProperties.APP_USER_ID, str);
        AFInAppEventType(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false);
        int i3 = afRDLog + 109;
        afWarnLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setAppId(String str) {
        int i = afWarnLog + 51;
        afRDLog = i % 128;
        int i2 = i % 2;
        values().mo78i().AFInAppEventParameterName("setAppId", str);
        AFInAppEventType(AppsFlyerProperties.APP_ID, str);
        int i3 = afRDLog + 77;
        afWarnLog = i3 % 128;
        if (i3 % 2 != 0) {
            int i4 = 83 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setExtension(String str) {
        int i = afWarnLog + 29;
        afRDLog = i % 128;
        int i2 = i % 2;
        values().mo78i().AFInAppEventParameterName("setExtension", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.EXTENSION, str);
        int i3 = afRDLog + 41;
        afWarnLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setIsUpdate(boolean z) {
        int i = afWarnLog + 95;
        afRDLog = i % 128;
        int i2 = i % 2;
        values().mo78i().AFInAppEventParameterName("setIsUpdate", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.IS_UPDATE, z);
        int i3 = afRDLog + 61;
        afWarnLog = i3 % 128;
        if ((i3 % 2 != 0 ? 'S' : '.') != '.') {
            int i4 = 27 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setCurrencyCode(String str) {
        int i = afWarnLog + 77;
        afRDLog = i % 128;
        int i2 = i % 2;
        values().mo78i().AFInAppEventParameterName("setCurrencyCode", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.CURRENCY_CODE, str);
        int i3 = afWarnLog + 73;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void logLocation(Context context, double d, double d2) {
        values().mo78i().AFInAppEventParameterName("logLocation", String.valueOf(d), String.valueOf(d2));
        HashMap hashMap = new HashMap();
        hashMap.put(AFInAppEventParameterName.LONGITUDE, Double.toString(d2));
        hashMap.put(AFInAppEventParameterName.LATITUDE, Double.toString(d));
        valueOf(context, AFInAppEventType.LOCATION_COORDINATES, hashMap);
        int i = afWarnLog + 9;
        afRDLog = i % 128;
        if (i % 2 == 0) {
            Object obj = null;
            obj.hashCode();
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void logSession(Context context) {
        int i = afWarnLog + 115;
        afRDLog = i % 128;
        int i2 = i % 2;
        values().mo78i().AFInAppEventParameterName("logSession", new String[0]);
        values().mo78i().AFInAppEventParameterName();
        AFInAppEventParameterName(context, AFg1aSDK.logSession);
        valueOf(context, null, null);
        int i3 = afRDLog + 7;
        afWarnLog = i3 % 128;
        int i4 = i3 % 2;
    }

    private void AFInAppEventParameterName(Context context, AFg1aSDK aFg1aSDK) {
        AFInAppEventParameterName(context);
        AFg1cSDK AFLogger = values().AFLogger();
        AFh1zSDK AFKeystoreWrapper2 = AFa1qSDK.AFKeystoreWrapper(context);
        if (AFLogger.values()) {
            int i = afWarnLog + 89;
            afRDLog = i % 128;
            int i2 = i % 2;
            AFLogger.AFInAppEventParameterName.put("api_name", aFg1aSDK.toString());
            AFLogger.values(AFKeystoreWrapper2);
            int i3 = afRDLog + 15;
            afWarnLog = i3 % 128;
            int i4 = i3 % 2;
        }
        AFLogger.AFKeystoreWrapper();
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void sendAdRevenue(Context context, Map<String, Object> map) {
        int AFInAppEventParameterName2 = AFInAppEventParameterName(AFKeystoreWrapper(context));
        HashMap hashMap = new HashMap();
        hashMap.put("ad_network", map);
        hashMap.put("adrevenue_counter", Integer.valueOf(AFInAppEventParameterName2));
        AFInAppEventParameterName(context, hashMap, new AFh1vSDK());
        int i = afRDLog + 73;
        afWarnLog = i % 128;
        if (!(i % 2 != 0)) {
            return;
        }
        int i2 = 64 / 0;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void sendAdImpression(Context context, Map<String, Object> map) {
        int valueOf2 = valueOf(AFKeystoreWrapper(context));
        HashMap hashMap = new HashMap();
        hashMap.put("ad_network", map);
        hashMap.put("adimpression_counter", Integer.valueOf(valueOf2));
        AFInAppEventParameterName(context, hashMap, new AFh1tSDK());
        int i = afRDLog + 37;
        afWarnLog = i % 128;
        int i2 = i % 2;
    }

    private void AFInAppEventParameterName(Context context, Map<String, Object> map, AFa1pSDK aFa1pSDK) {
        int i = afWarnLog + 101;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? '\b' : ' ') != ' ') {
            AFInAppEventParameterName(context);
            aFa1pSDK.AFInAppEventParameterName((Map<String, ?>) map);
            AFInAppEventParameterName(aFa1pSDK, unregisterClient(context));
            int i2 = 45 / 0;
        } else {
            AFInAppEventParameterName(context);
            aFa1pSDK.AFInAppEventParameterName((Map<String, ?>) map);
            AFInAppEventParameterName(aFa1pSDK, unregisterClient(context));
        }
        int i3 = afWarnLog + 105;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void logEvent(Context context, String str, Map<String, Object> map) {
        int i = afRDLog + 41;
        afWarnLog = i % 128;
        int i2 = i % 2;
        Object obj = null;
        logEvent(context, str, map, null);
        int i3 = afRDLog + 119;
        afWarnLog = i3 % 128;
        if ((i3 % 2 != 0 ? '=' : '4') == '4') {
            return;
        }
        obj.hashCode();
        throw null;
    }

    private AFh1uSDK unregisterClient(Context context) {
        int i = afWarnLog;
        int i2 = i + 65;
        afRDLog = i2 % 128;
        int i3 = i2 % 2;
        if (context instanceof Activity) {
            return new AFh1uSDK((Activity) context, values().AFVersionDeclaration());
        }
        int i4 = i + 119;
        afRDLog = i4 % 128;
        int i5 = i4 % 2;
        return null;
    }

    private void valueOf(Context context, String str, Map<String, Object> map) {
        AFh1pSDK aFh1pSDK = new AFh1pSDK();
        aFh1pSDK.AFLogger = str;
        aFh1pSDK.AFKeystoreWrapper = map;
        AFInAppEventParameterName(aFh1pSDK, unregisterClient(context));
        int i = afWarnLog + 107;
        afRDLog = i % 128;
        if (i % 2 != 0) {
            return;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    final void AFInAppEventParameterName(AFa1pSDK aFa1pSDK, AFh1uSDK aFh1uSDK) {
        int i = afRDLog + 75;
        afWarnLog = i % 128;
        int i2 = i % 2;
        valueOf(aFa1pSDK, aFh1uSDK);
        Object obj = null;
        if (values().force().registerClient == null) {
            int i3 = afRDLog + 87;
            afWarnLog = i3 % 128;
            if (i3 % 2 == 0) {
                AFLogger.afWarnLog("[LogEvent/Launch] AppsFlyer's SDK cannot send any event without providing DevKey.");
                AppsFlyerRequestListener appsFlyerRequestListener = aFa1pSDK.AFInAppEventType;
                if ((appsFlyerRequestListener != null ? 'Q' : '?') != 'Q') {
                    return;
                }
                appsFlyerRequestListener.onError(41, "No dev key");
                return;
            }
            AFLogger.afWarnLog("[LogEvent/Launch] AppsFlyer's SDK cannot send any event without providing DevKey.");
            AppsFlyerRequestListener appsFlyerRequestListener2 = aFa1pSDK.AFInAppEventType;
            obj.hashCode();
            throw null;
        }
        String referrer = AppsFlyerProperties.getInstance().getReferrer(values().values());
        if (referrer == null) {
            int i4 = afWarnLog + 1;
            afRDLog = i4 % 128;
            if (i4 % 2 == 0) {
                int i5 = 70 / 0;
            }
            referrer = "";
        }
        aFa1pSDK.f160d = referrer;
        values(aFa1pSDK);
        int i6 = afWarnLog + 123;
        afRDLog = i6 % 128;
        if (i6 % 2 != 0) {
            return;
        }
        obj.hashCode();
        throw null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void anonymizeUser(boolean z) {
        int i = afRDLog + 21;
        afWarnLog = i % 128;
        if ((i % 2 != 0 ? '^' : (char) 31) != 31) {
            AFb1bSDK mo78i = values().mo78i();
            String[] strArr = new String[0];
            strArr[1] = String.valueOf(z);
            mo78i.AFInAppEventParameterName("anonymizeUser", strArr);
        } else {
            values().mo78i().AFInAppEventParameterName("anonymizeUser", String.valueOf(z));
        }
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, z);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void registerConversionListener(Context context, AppsFlyerConversionListener appsFlyerConversionListener) {
        int i = afRDLog + 9;
        afWarnLog = i % 128;
        if ((i % 2 != 0 ? (char) 30 : '0') != 30) {
            values().mo78i().AFInAppEventParameterName("registerConversionListener", new String[0]);
        } else {
            values().mo78i().AFInAppEventParameterName("registerConversionListener", new String[0]);
        }
        AFKeystoreWrapper(appsFlyerConversionListener);
        int i2 = afRDLog + 103;
        afWarnLog = i2 % 128;
        int i3 = i2 % 2;
    }

    private void AFKeystoreWrapper(AppsFlyerConversionListener appsFlyerConversionListener) {
        if (appsFlyerConversionListener == null) {
            int i = afRDLog + 57;
            afWarnLog = i % 128;
            if (!(i % 2 == 0)) {
                int i2 = 26 / 0;
                return;
            }
            return;
        }
        this.AFInAppEventType = appsFlyerConversionListener;
        int i3 = afWarnLog + 25;
        afRDLog = i3 % 128;
        if ((i3 % 2 == 0 ? ']' : '\n') == '\n') {
        } else {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void unregisterConversionListener() {
        int i = afWarnLog + 65;
        afRDLog = i % 128;
        int i2 = i % 2;
        values().mo78i().AFInAppEventParameterName("unregisterConversionListener", new String[0]);
        this.AFInAppEventType = null;
        int i3 = afWarnLog + 105;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void registerValidatorListener(Context context, AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener) {
        values().mo78i().AFInAppEventParameterName("registerValidatorListener", new String[0]);
        AFLogger.afDebugLog("registerValidatorListener called");
        if ((appsFlyerInAppPurchaseValidatorListener == null ? '!' : 'C') == 'C') {
            AFKeystoreWrapper = appsFlyerInAppPurchaseValidatorListener;
            int i = afWarnLog + 111;
            afRDLog = i % 128;
            int i2 = i % 2;
            return;
        }
        int i3 = afWarnLog + 65;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
        AFLogger.afDebugLog("registerValidatorListener null listener");
        int i5 = afWarnLog + 19;
        afRDLog = i5 % 128;
        if (i5 % 2 != 0) {
            return;
        }
        int i6 = 36 / 0;
    }

    public static String valueOf(SimpleDateFormat simpleDateFormat, long j) {
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String format = simpleDateFormat.format(new Date(j));
        int i = afWarnLog + 9;
        afRDLog = i % 128;
        if (!(i % 2 == 0)) {
            return format;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    private void valueOf(Context context, String str) {
        AFh1sSDK aFh1sSDK = new AFh1sSDK();
        AFInAppEventParameterName(context);
        aFh1sSDK.AFLogger = null;
        aFh1sSDK.AFKeystoreWrapper = null;
        aFh1sSDK.f160d = str;
        aFh1sSDK.values = null;
        values(aFh1sSDK);
        int i = afRDLog + 83;
        afWarnLog = i % 128;
        int i2 = i % 2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0051, code lost:
    
        if (com.appsflyer.AppsFlyerProperties.getInstance().getBoolean(com.appsflyer.AppsFlyerProperties.LAUNCH_PROTECT_ENABLED, false) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0063, code lost:
    
        com.appsflyer.AFLogger.afInfoLog("Allowing multiple launches within a 5 second time window.");
        r0 = com.appsflyer.internal.AFb1tSDK.afRDLog + 43;
        com.appsflyer.internal.AFb1tSDK.afWarnLog = r0 % 128;
        r0 = r0 % 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0077, code lost:
    
        if (m71e() == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0079, code lost:
    
        r6 = r6.AFInAppEventType;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007b, code lost:
    
        if (r6 == null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007d, code lost:
    
        r6.onError(10, "Event timeout. Check 'minTimeBetweenSessions' param");
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0084, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0061, code lost:
    
        if ((com.appsflyer.AppsFlyerProperties.getInstance().getBoolean(com.appsflyer.AppsFlyerProperties.LAUNCH_PROTECT_ENABLED, true)) != true) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void values(com.appsflyer.internal.AFa1pSDK r6) {
        /*
            r5 = this;
            int r0 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r0 = r0 + 15
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r1
            int r0 = r0 % 2
            java.lang.String r0 = r6.AFLogger
            r1 = 98
            if (r0 != 0) goto L12
            r0 = r1
            goto L14
        L12:
            r0 = 36
        L14:
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L24
            int r0 = com.appsflyer.internal.AFb1tSDK.afWarnLog
            int r0 = r0 + 77
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r1
            int r0 = r0 % 2
            r0 = r2
            goto L25
        L24:
            r0 = r3
        L25:
            boolean r1 = r5.AFInAppEventParameterName()
            r4 = 48
            if (r1 == 0) goto L30
            r1 = 85
            goto L31
        L30:
            r1 = r4
        L31:
            if (r1 == r4) goto L39
            java.lang.String r6 = "CustomerUserId not set, reporting is disabled"
            com.appsflyer.AFLogger.afInfoLog(r6, r3)
            return
        L39:
            if (r0 == 0) goto L8b
            int r0 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r0 = r0 + 79
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r1
            int r0 = r0 % 2
            java.lang.String r1 = "launchProtectEnabled"
            if (r0 == 0) goto L54
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            boolean r0 = r0.getBoolean(r1, r2)
            if (r0 == 0) goto L63
            goto L73
        L54:
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            boolean r0 = r0.getBoolean(r1, r3)
            if (r0 == 0) goto L60
            r0 = r3
            goto L61
        L60:
            r0 = r2
        L61:
            if (r0 == r3) goto L73
        L63:
            java.lang.String r0 = "Allowing multiple launches within a 5 second time window."
            com.appsflyer.AFLogger.afInfoLog(r0)
            int r0 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r0 = r0 + 43
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r1
            int r0 = r0 % 2
            goto L85
        L73:
            boolean r0 = r5.m71e()
            if (r0 == 0) goto L85
            com.appsflyer.attribution.AppsFlyerRequestListener r6 = r6.AFInAppEventType
            if (r6 == 0) goto L84
            r0 = 10
            java.lang.String r1 = "Event timeout. Check 'minTimeBetweenSessions' param"
            r6.onError(r0, r1)
        L84:
            return
        L85:
            long r0 = java.lang.System.currentTimeMillis()
            r5.unregisterClient = r0
        L8b:
            com.appsflyer.internal.AFd1mSDK r0 = r5.values()
            java.util.concurrent.ScheduledExecutorService r0 = r0.valueOf()
            com.appsflyer.internal.AFb1tSDK$AFa1tSDK r1 = new com.appsflyer.internal.AFb1tSDK$AFa1tSDK
            r1.<init>(r5, r6, r2)
            r2 = 0
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS
            com.appsflyer.internal.AFj1zSDK.AFKeystoreWrapper(r0, r1, r2, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.values(com.appsflyer.internal.AFa1pSDK):void");
    }

    /* renamed from: e */
    private boolean m71e() {
        if (this.unregisterClient > 0) {
            long currentTimeMillis = System.currentTimeMillis() - this.unregisterClient;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS Z", Locale.US);
            String valueOf2 = valueOf(simpleDateFormat, this.unregisterClient);
            String valueOf3 = valueOf(simpleDateFormat, this.registerClient);
            if ((currentTimeMillis >= this.f174d) || isStopped()) {
                if (!isStopped()) {
                    AFLogger.afInfoLog(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nSending launch (+%s ms)", valueOf2, valueOf3, Long.valueOf(currentTimeMillis)));
                }
            } else {
                int i = afRDLog + 45;
                afWarnLog = i % 128;
                if (i % 2 != 0) {
                    AFLogger.afInfoLog(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nThis launch is blocked: %s ms < %s ms", valueOf2, valueOf3, Long.valueOf(this.f174d), Long.valueOf(currentTimeMillis)));
                    return false;
                }
                AFLogger.afInfoLog(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nThis launch is blocked: %s ms < %s ms", valueOf2, valueOf3, Long.valueOf(currentTimeMillis), Long.valueOf(this.f174d)));
                return true;
            }
        } else if (!isStopped()) {
            AFLogger.afInfoLog("Sending first launch for this session!");
        }
        int i2 = afWarnLog + 1;
        afRDLog = i2 % 128;
        int i3 = i2 % 2;
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0052, code lost:
    
        if ((r7 % 2) != 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0054, code lost:
    
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0057, code lost:
    
        if (r7 == true) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0065, code lost:
    
        if (values().mo79v().AFInAppEventType(r0) == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0079, code lost:
    
        com.appsflyer.internal.AFj1zSDK.AFKeystoreWrapper(values().valueOf(), new com.appsflyer.internal.AFb1tSDK.AFa1tSDK(r6, r0, r3), 5, java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0076, code lost:
    
        r1 = 48 / 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0077, code lost:
    
        if (values().mo79v().AFInAppEventType(r0) == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0056, code lost:
    
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0045, code lost:
    
        if ((r7.length() <= 5) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0038, code lost:
    
        if ((r7.length() > 3 ? '=' : '%') != '%') goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0048, code lost:
    
        r7 = com.appsflyer.internal.AFb1tSDK.afWarnLog + 83;
        com.appsflyer.internal.AFb1tSDK.afRDLog = r7 % 128;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void AFKeystoreWrapper(java.lang.String r7) {
        /*
            r6 = this;
            com.appsflyer.internal.AFh1oSDK r0 = new com.appsflyer.internal.AFh1oSDK
            r0.<init>()
            com.appsflyer.internal.AFd1mSDK r1 = r6.values()
            com.appsflyer.internal.AFd1sSDK r1 = r1.AFInAppEventType()
            com.appsflyer.internal.AFd1tSDK r1 = r1.AFKeystoreWrapper
            java.lang.String r2 = "appsFlyerCount"
            r3 = 0
            int r1 = r1.AFInAppEventParameterName(r2, r3)
            com.appsflyer.internal.AFa1pSDK r0 = r0.AFInAppEventType(r1)
            r0.f160d = r7
            if (r7 == 0) goto L90
            int r1 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r1 = r1 + 125
            int r2 = r1 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r2
            int r1 = r1 % 2
            r2 = 1
            if (r1 == 0) goto L3b
            int r7 = r7.length()
            r1 = 3
            r4 = 37
            if (r7 <= r1) goto L37
            r7 = 61
            goto L38
        L37:
            r7 = r4
        L38:
            if (r7 == r4) goto L90
            goto L48
        L3b:
            int r7 = r7.length()
            r1 = 5
            if (r7 <= r1) goto L44
            r7 = r3
            goto L45
        L44:
            r7 = r2
        L45:
            if (r7 == 0) goto L48
            goto L90
        L48:
            int r7 = com.appsflyer.internal.AFb1tSDK.afWarnLog
            int r7 = r7 + 83
            int r1 = r7 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r1
            int r7 = r7 % 2
            if (r7 != 0) goto L56
            r7 = r2
            goto L57
        L56:
            r7 = r3
        L57:
            if (r7 == r2) goto L68
            com.appsflyer.internal.AFd1mSDK r7 = r6.values()
            com.appsflyer.internal.AFi1iSDK r7 = r7.mo79v()
            boolean r7 = r7.AFInAppEventType(r0)
            if (r7 == 0) goto L90
            goto L79
        L68:
            com.appsflyer.internal.AFd1mSDK r7 = r6.values()
            com.appsflyer.internal.AFi1iSDK r7 = r7.mo79v()
            boolean r7 = r7.AFInAppEventType(r0)
            r1 = 48
            int r1 = r1 / r3
            if (r7 == 0) goto L90
        L79:
            com.appsflyer.internal.AFd1mSDK r7 = r6.values()
            java.util.concurrent.ScheduledExecutorService r7 = r7.valueOf()
            com.appsflyer.internal.AFb1tSDK$AFa1tSDK r1 = new com.appsflyer.internal.AFb1tSDK$AFa1tSDK
            r1.<init>(r6, r0, r3)
            r4 = 5
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            com.appsflyer.internal.AFj1zSDK.AFKeystoreWrapper(r7, r1, r4, r0)
            goto L90
        L8e:
            r7 = move-exception
            throw r7
        L90:
            int r7 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r7 = r7 + 109
            int r0 = r7 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r0
            int r7 = r7 % 2
            if (r7 == 0) goto La2
            r7 = 72
            int r7 = r7 / r3
            return
        La0:
            r7 = move-exception
            throw r7
        La2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.AFKeystoreWrapper(java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0180  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void AFKeystoreWrapper(com.appsflyer.internal.AFa1pSDK r15) {
        /*
            Method dump skipped, instructions count: 576
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.AFKeystoreWrapper(com.appsflyer.internal.AFa1pSDK):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x006b, code lost:
    
        if (r5.remove("android_id") != null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0076, code lost:
    
        r0 = com.appsflyer.internal.AFb1tSDK.afRDLog + 35;
        com.appsflyer.internal.AFb1tSDK.afWarnLog = r0 % 128;
        r0 = r0 % 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0080, code lost:
    
        com.appsflyer.AFLogger.afInfoLog("validateGaidAndIMEI :: removing: android_id");
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0074, code lost:
    
        if (r5.remove("android_id") != null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ab, code lost:
    
        if (r5.remove("imei") != null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b6, code lost:
    
        com.appsflyer.AFLogger.afInfoLog("validateGaidAndIMEI :: removing: imei");
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00b4, code lost:
    
        if (r5.remove("imei") != null) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void AFInAppEventParameterName(java.util.Map<java.lang.String, java.lang.Object> r5) {
        /*
            r4 = this;
            int r0 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r0 = r0 + 37
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r1
            int r0 = r0 % 2
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r1 = "collectAndroidIdForceByUser"
            r2 = 0
            boolean r0 = r0.getBoolean(r1, r2)
            r1 = 1
            if (r0 != 0) goto L1a
            r0 = r1
            goto L1b
        L1a:
            r0 = r2
        L1b:
            if (r0 == 0) goto L2c
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r3 = "collectIMEIForceByUser"
            boolean r0 = r0.getBoolean(r3, r2)
            if (r0 == 0) goto L2a
            goto L2c
        L2a:
            r0 = r2
            goto L2d
        L2c:
            r0 = r1
        L2d:
            if (r0 != 0) goto Lcc
            java.lang.String r0 = "advertiserId"
            java.lang.Object r0 = r5.get(r0)
            if (r0 == 0) goto L39
            r0 = r2
            goto L3a
        L39:
            r0 = r1
        L3a:
            if (r0 == r1) goto Lcc
            com.appsflyer.internal.AFd1mSDK r0 = r4.values()     // Catch: java.lang.Exception -> Lc6
            com.appsflyer.internal.AFd1qSDK r0 = r0.afErrorLogForExcManagerOnly()     // Catch: java.lang.Exception -> Lc6
            java.lang.String r0 = r0.values     // Catch: java.lang.Exception -> Lc6
            boolean r0 = com.appsflyer.internal.AFc1rSDK.AFInAppEventType(r0)     // Catch: java.lang.Exception -> Lc6
            r1 = 83
            r3 = 29
            if (r0 == 0) goto L52
            r0 = r1
            goto L53
        L52:
            r0 = r3
        L53:
            if (r0 == r1) goto L56
            goto L86
        L56:
            int r0 = com.appsflyer.internal.AFb1tSDK.afWarnLog
            int r0 = r0 + 17
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r1
            int r0 = r0 % 2
            java.lang.String r1 = "android_id"
            if (r0 != 0) goto L70
            java.lang.Object r0 = r5.remove(r1)     // Catch: java.lang.Exception -> Lc6
            r1 = 33
            int r1 = r1 / r2
            if (r0 == 0) goto L86
            goto L76
        L6e:
            r5 = move-exception
            throw r5
        L70:
            java.lang.Object r0 = r5.remove(r1)     // Catch: java.lang.Exception -> Lc6
            if (r0 == 0) goto L86
        L76:
            int r0 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r0 = r0 + 35
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r1
            int r0 = r0 % 2
            java.lang.String r0 = "validateGaidAndIMEI :: removing: android_id"
            com.appsflyer.AFLogger.afInfoLog(r0)     // Catch: java.lang.Exception -> Lc6
        L86:
            com.appsflyer.internal.AFd1mSDK r0 = r4.values()     // Catch: java.lang.Exception -> Lc6
            com.appsflyer.internal.AFg1xSDK r0 = r0.force()     // Catch: java.lang.Exception -> Lc6
            java.lang.String r0 = r0.unregisterClient     // Catch: java.lang.Exception -> Lc6
            boolean r0 = com.appsflyer.internal.AFc1rSDK.AFInAppEventType(r0)     // Catch: java.lang.Exception -> Lc6
            if (r0 == 0) goto Lbc
            int r0 = com.appsflyer.internal.AFb1tSDK.afWarnLog
            int r0 = r0 + 109
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r1
            int r0 = r0 % 2
            java.lang.String r1 = "imei"
            if (r0 != 0) goto Lb0
            java.lang.Object r5 = r5.remove(r1)     // Catch: java.lang.Exception -> Lc6
            r0 = 85
            int r0 = r0 / r2
            if (r5 == 0) goto Lbc
            goto Lb6
        Lae:
            r5 = move-exception
            throw r5
        Lb0:
            java.lang.Object r5 = r5.remove(r1)     // Catch: java.lang.Exception -> Lc6
            if (r5 == 0) goto Lbc
        Lb6:
            java.lang.String r5 = "validateGaidAndIMEI :: removing: imei"
            com.appsflyer.AFLogger.afInfoLog(r5)     // Catch: java.lang.Exception -> Lc6
        Lbc:
            int r5 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r5 = r5 + r3
            int r0 = r5 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r0
            int r5 = r5 % 2
            return
        Lc6:
            r5 = move-exception
            java.lang.String r0 = "failed to remove IMEI or AndroidID key from params; "
            com.appsflyer.AFLogger.afErrorLog(r0, r5)
        Lcc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.AFInAppEventParameterName(java.util.Map):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map<String, Object> valueOf(AFa1pSDK aFa1pSDK) {
        boolean z;
        String str;
        final Context context = values().mo80w().valueOf;
        AFd1tSDK AFKeystoreWrapper2 = AFKeystoreWrapper(context);
        AFg1qSDK unregisterClient = values().unregisterClient();
        boolean values2 = aFa1pSDK.values();
        Map<String, Object> map = aFa1pSDK.valueOf;
        long time = new Date().getTime();
        Object[] objArr = new Object[1];
        m68a((byte) (1 - TextUtils.lastIndexOf("", '0', 0, 0)), 12 - Color.blue(0), "\u0006\u0000\b\u0002\u0000\u0002\u0001\u0007\u0006\u0005\u0002\u0001", objArr);
        map.put(((String) objArr[0]).intern(), Long.toString(time));
        try {
            if (isStopped()) {
                AFLogger.afInfoLog("Reporting has been stopped");
            } else {
                StringBuilder sb = new StringBuilder("******* sendTrackingWithEvent: ");
                if (values2) {
                    int i = afWarnLog + 113;
                    afRDLog = i % 128;
                    int i2 = i % 2;
                    str = "Launch";
                } else {
                    str = aFa1pSDK.AFLogger;
                    int i3 = afWarnLog + 11;
                    afRDLog = i3 % 128;
                    int i4 = i3 % 2;
                }
                sb.append(str);
                AFLogger.afInfoLog(sb.toString());
            }
            m70e(context);
            unregisterClient.AFKeystoreWrapper(map, isPreInstalledApp(context), new Function0() { // from class: com.appsflyer.internal.AFb1tSDK$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    String registerClient;
                    registerClient = AFb1tSDK.this.registerClient(context);
                    return registerClient;
                }
            });
            int AFInAppEventType = AFInAppEventType(AFKeystoreWrapper2, values2);
            if ((aFa1pSDK.AFLogger != null ? '-' : 'M') != '-') {
                z = false;
            } else {
                int i5 = afWarnLog + 77;
                afRDLog = i5 % 128;
                int i6 = i5 % 2;
                z = true;
            }
            int AFKeystoreWrapper3 = AFKeystoreWrapper(AFKeystoreWrapper2, z);
            if (values2) {
                int i7 = afWarnLog + 57;
                afRDLog = i7 % 128;
                int i8 = i7 % 2;
                if ((AFInAppEventType == 1 ? '+' : (char) 5) == '+') {
                    AppsFlyerProperties.getInstance().AFKeystoreWrapper = true;
                }
            }
            unregisterClient.AFInAppEventParameterName(map, AFInAppEventType, AFKeystoreWrapper3);
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getLocalizedMessage(), th, true);
        }
        int i9 = afWarnLog + 23;
        afRDLog = i9 % 128;
        if (i9 % 2 != 0) {
            return map;
        }
        int i10 = 31 / 0;
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String registerClient(Context context) {
        int i = afWarnLog + 75;
        afRDLog = i % 128;
        int i2 = i % 2;
        String attributionId = getAttributionId(context);
        int i3 = afWarnLog + 5;
        afRDLog = i3 % 128;
        if (!(i3 % 2 == 0)) {
            return attributionId;
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0084 A[Catch: Exception -> 0x0096, TryCatch #0 {Exception -> 0x0096, blocks: (B:4:0x000e, B:9:0x0067, B:14:0x007e, B:16:0x0084, B:18:0x008c, B:24:0x0075, B:26:0x0054, B:29:0x0031), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0075 A[Catch: Exception -> 0x0096, TryCatch #0 {Exception -> 0x0096, blocks: (B:4:0x000e, B:9:0x0067, B:14:0x007e, B:16:0x0084, B:18:0x008c, B:24:0x0075, B:26:0x0054, B:29:0x0031), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0071  */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m70e(android.content.Context r3) {
        /*
            int r0 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r0 = r0 + 97
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r1
            int r0 = r0 % 2
            java.lang.String r1 = "android.permission.INTERNET"
            if (r0 == 0) goto L31
            android.content.pm.PackageManager r0 = r3.getPackageManager()     // Catch: java.lang.Exception -> L96
            java.lang.String r3 = r3.getPackageName()     // Catch: java.lang.Exception -> L96
            r2 = 205(0xcd, float:2.87E-43)
            android.content.pm.PackageInfo r3 = r0.getPackageInfo(r3, r2)     // Catch: java.lang.Exception -> L96
            java.lang.String[] r3 = r3.requestedPermissions     // Catch: java.lang.Exception -> L96
            java.util.List r3 = java.util.Arrays.asList(r3)     // Catch: java.lang.Exception -> L96
            boolean r0 = r3.contains(r1)     // Catch: java.lang.Exception -> L96
            r1 = 66
            if (r0 != 0) goto L2c
            r0 = r1
            goto L2e
        L2c:
            r0 = 72
        L2e:
            if (r0 == r1) goto L54
            goto L67
        L31:
            android.content.pm.PackageManager r0 = r3.getPackageManager()     // Catch: java.lang.Exception -> L96
            java.lang.String r3 = r3.getPackageName()     // Catch: java.lang.Exception -> L96
            r2 = 4096(0x1000, float:5.74E-42)
            android.content.pm.PackageInfo r3 = r0.getPackageInfo(r3, r2)     // Catch: java.lang.Exception -> L96
            java.lang.String[] r3 = r3.requestedPermissions     // Catch: java.lang.Exception -> L96
            java.util.List r3 = java.util.Arrays.asList(r3)     // Catch: java.lang.Exception -> L96
            boolean r0 = r3.contains(r1)     // Catch: java.lang.Exception -> L96
            r1 = 70
            if (r0 != 0) goto L4f
            r0 = r1
            goto L51
        L4f:
            r0 = 67
        L51:
            if (r0 == r1) goto L54
            goto L67
        L54:
            com.appsflyer.AFLogger r0 = com.appsflyer.AFLogger.INSTANCE     // Catch: java.lang.Exception -> L96
            com.appsflyer.internal.AFg1gSDK r1 = com.appsflyer.internal.AFg1gSDK.GENERAL     // Catch: java.lang.Exception -> L96
            java.lang.String r2 = "Permission android.permission.INTERNET is missing in the AndroidManifest.xml"
            r0.m103w(r1, r2)     // Catch: java.lang.Exception -> L96
            int r0 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r0 = r0 + 97
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r1
            int r0 = r0 % 2
        L67:
            java.lang.String r0 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r0 = r3.contains(r0)     // Catch: java.lang.Exception -> L96
            if (r0 != 0) goto L71
            r0 = 0
            goto L72
        L71:
            r0 = 1
        L72:
            if (r0 == 0) goto L75
            goto L7e
        L75:
            com.appsflyer.AFLogger r0 = com.appsflyer.AFLogger.INSTANCE     // Catch: java.lang.Exception -> L96
            com.appsflyer.internal.AFg1gSDK r1 = com.appsflyer.internal.AFg1gSDK.GENERAL     // Catch: java.lang.Exception -> L96
            java.lang.String r2 = "Permission android.permission.ACCESS_NETWORK_STATE is missing in the AndroidManifest.xml"
            r0.m103w(r1, r2)     // Catch: java.lang.Exception -> L96
        L7e:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L96
            r1 = 32
            if (r0 <= r1) goto L95
            java.lang.String r0 = "com.google.android.gms.permission.AD_ID"
            boolean r3 = r3.contains(r0)     // Catch: java.lang.Exception -> L96
            if (r3 != 0) goto L95
            com.appsflyer.AFLogger r3 = com.appsflyer.AFLogger.INSTANCE     // Catch: java.lang.Exception -> L96
            com.appsflyer.internal.AFg1gSDK r0 = com.appsflyer.internal.AFg1gSDK.GENERAL     // Catch: java.lang.Exception -> L96
            java.lang.String r1 = "Permission com.google.android.gms.permission.AD_ID is missing in the AndroidManifest.xml"
            r3.m103w(r0, r1)     // Catch: java.lang.Exception -> L96
        L95:
            return
        L96:
            r3 = move-exception
            com.appsflyer.AFLogger r0 = com.appsflyer.AFLogger.INSTANCE
            com.appsflyer.internal.AFg1gSDK r1 = com.appsflyer.internal.AFg1gSDK.GENERAL
            java.lang.String r2 = "Exception while validation permissions. "
            r0.m97e(r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.m70e(android.content.Context):void");
    }

    public static Map<String, Object> values(Map<String, Object> map) {
        int i = afWarnLog + 43;
        afRDLog = i % 128;
        int i2 = i % 2;
        if (map.containsKey("meta")) {
            int i3 = afRDLog + 43;
            afWarnLog = i3 % 128;
            char c = i3 % 2 == 0 ? '+' : '\'';
            Object obj = map.get("meta");
            if (c == '+') {
                return (Map) obj;
            }
            throw null;
        }
        HashMap hashMap = new HashMap();
        map.put("meta", hashMap);
        int i4 = afWarnLog + 39;
        afRDLog = i4 % 128;
        int i5 = i4 % 2;
        return hashMap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002a, code lost:
    
        if (r1 != null) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0039, code lost:
    
        if ((r1 != null) != false) goto L49;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String values(android.app.Activity r11) {
        /*
            java.lang.String r0 = "af"
            int r1 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r2 = r1 + 3
            int r3 = r2 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r3
            int r2 = r2 % 2
            r3 = 0
            if (r2 != 0) goto L92
            r2 = 36
            r4 = 1
            r5 = 0
            if (r11 == 0) goto L7c
            int r1 = r1 + 17
            int r6 = r1 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r6
            int r1 = r1 % 2
            r6 = 21
            if (r1 == 0) goto L23
            r1 = r6
            goto L24
        L23:
            r1 = r2
        L24:
            if (r1 == r6) goto L2d
            android.content.Intent r1 = r11.getIntent()
            if (r1 == 0) goto L7c
            goto L3b
        L2d:
            android.content.Intent r1 = r11.getIntent()
            r6 = 62
            int r6 = r6 / r5
            if (r1 == 0) goto L38
            r6 = r4
            goto L39
        L38:
            r6 = r5
        L39:
            if (r6 == 0) goto L7c
        L3b:
            android.os.Bundle r6 = r1.getExtras()     // Catch: java.lang.Throwable -> L6d
            if (r6 == 0) goto L7c
            java.lang.String r3 = r6.getString(r0)     // Catch: java.lang.Throwable -> L6d
            if (r3 == 0) goto L7c
            com.appsflyer.AFLogger r7 = com.appsflyer.AFLogger.INSTANCE     // Catch: java.lang.Throwable -> L6d
            com.appsflyer.internal.AFg1gSDK r8 = com.appsflyer.internal.AFg1gSDK.ENGAGEMENT     // Catch: java.lang.Throwable -> L6d
            java.lang.String r9 = "Push Notification received af payload = "
            java.lang.String r10 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r9 = r9.concat(r10)     // Catch: java.lang.Throwable -> L6d
            r7.m103w(r8, r9)     // Catch: java.lang.Throwable -> L6d
            r6.remove(r0)     // Catch: java.lang.Throwable -> L6d
            android.content.Intent r0 = r1.putExtras(r6)     // Catch: java.lang.Throwable -> L6d
            r11.setIntent(r0)     // Catch: java.lang.Throwable -> L6d
            int r11 = com.appsflyer.internal.AFb1tSDK.afWarnLog
            int r11 = r11 + 3
            int r0 = r11 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r0
            int r11 = r11 % 2
            goto L7c
        L6d:
            r11 = move-exception
            com.appsflyer.AFLogger r0 = com.appsflyer.AFLogger.INSTANCE
            com.appsflyer.internal.AFg1gSDK r1 = com.appsflyer.internal.AFg1gSDK.ENGAGEMENT
            java.lang.String r6 = r11.getMessage()
            r0.m97e(r1, r6, r11)
            goto L7c
        L7a:
            r11 = move-exception
            throw r11
        L7c:
            int r11 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r11 = r11 + 121
            int r0 = r11 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r0
            int r11 = r11 % 2
            if (r11 == 0) goto L8a
            r11 = r4
            goto L8b
        L8a:
            r11 = r5
        L8b:
            if (r11 == r4) goto L8e
            return r3
        L8e:
            int r2 = r2 / r5
            return r3
        L90:
            r11 = move-exception
            throw r11
        L92:
            throw r3     // Catch: java.lang.Throwable -> L93
        L93:
            r11 = move-exception
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.values(android.app.Activity):java.lang.String");
    }

    private static int AFInAppEventParameterName(AFd1tSDK aFd1tSDK) {
        int i = afWarnLog + 55;
        afRDLog = i % 128;
        if (i % 2 == 0) {
        }
        return values(aFd1tSDK, "appsFlyerAdRevenueCount", true);
    }

    private static int valueOf(AFd1tSDK aFd1tSDK) {
        int i = afWarnLog + 67;
        afRDLog = i % 128;
        return i % 2 != 0 ? values(aFd1tSDK, "appsFlyerAdImpressionCount", true) : values(aFd1tSDK, "appsFlyerAdImpressionCount", false);
    }

    public final void values(Context context, AFc1pSDK aFc1pSDK, Uri uri, Uri uri2) {
        AFInAppEventParameterName(context);
        if (!aFc1pSDK.values("af_deeplink")) {
            String valueOf2 = valueOf(uri.toString());
            AFc1uSDK afWarnLog2 = values().afWarnLog();
            if (afWarnLog2.AFInAppEventType != null) {
                int i = afWarnLog + 83;
                afRDLog = i % 128;
                if (i % 2 == 0) {
                    Map<String, String> map = afWarnLog2.values;
                    throw null;
                }
                if (afWarnLog2.values != null && valueOf2.contains(afWarnLog2.AFInAppEventType)) {
                    Uri.Builder buildUpon = Uri.parse(valueOf2).buildUpon();
                    Uri.Builder buildUpon2 = Uri.EMPTY.buildUpon();
                    for (Map.Entry<String, String> entry : afWarnLog2.values.entrySet()) {
                        int i2 = afRDLog + 121;
                        afWarnLog = i2 % 128;
                        int i3 = i2 % 2;
                        buildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
                        buildUpon2.appendQueryParameter(entry.getKey(), entry.getValue());
                    }
                    valueOf2 = buildUpon.build().toString();
                    String encodedQuery = buildUpon2.build().getEncodedQuery();
                    Intrinsics.checkNotNullParameter("appended_query_params", "");
                    aFc1pSDK.AFInAppEventParameterName.put("appended_query_params", encodedQuery);
                    AFc1oSDK aFc1oSDK = aFc1pSDK.AFInAppEventType;
                    if ((aFc1oSDK != null ? Typography.less : '#') != '#') {
                        aFc1oSDK.values(aFc1pSDK.AFInAppEventParameterName);
                    }
                }
            }
            Intrinsics.checkNotNullParameter("af_deeplink", "");
            aFc1pSDK.AFInAppEventParameterName.put("af_deeplink", valueOf2);
            AFc1oSDK aFc1oSDK2 = aFc1pSDK.AFInAppEventType;
            if (aFc1oSDK2 != null) {
                int i4 = afWarnLog + 45;
                afRDLog = i4 % 128;
                int i5 = i4 % 2;
                aFc1oSDK2.values(aFc1pSDK.AFInAppEventParameterName);
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("link", uri.toString());
        if (uri2 != null) {
            int i6 = afRDLog + 7;
            afWarnLog = i6 % 128;
            int i7 = i6 % 2;
            hashMap.put("original_link", uri2.toString());
        }
        AFb1uSDK.values(context, hashMap, uri);
        AFf1lSDK aFf1lSDK = new AFf1lSDK(values(), UUID.randomUUID(), uri);
        if ((aFf1lSDK.m90i() ? (char) 19 : 'a') != 'a') {
            Boolean bool = Boolean.TRUE;
            Intrinsics.checkNotNullParameter("isBrandedDomain", "");
            aFc1pSDK.AFInAppEventParameterName.put("isBrandedDomain", bool);
            AFc1oSDK aFc1oSDK3 = aFc1pSDK.AFInAppEventType;
            if (aFc1oSDK3 != null) {
                aFc1oSDK3.values(aFc1pSDK.AFInAppEventParameterName);
            }
        }
        if (!aFf1lSDK.m92w()) {
            values().afWarnLog().AFInAppEventType(hashMap);
            return;
        }
        aFf1lSDK.f219e = AFInAppEventType(hashMap);
        AFe1fSDK afInfoLog = values().afInfoLog();
        afInfoLog.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(aFf1lSDK));
    }

    private static String valueOf(String str) {
        if (str == null ? 2 : false) {
            return null;
        }
        if (!str.matches("fb\\d*?://authorize.*")) {
            return str;
        }
        if ((str.contains("access_token") ? (char) 25 : (char) 2) != 25) {
            return str;
        }
        String values2 = values(str);
        if (values2.length() == 0) {
            return str;
        }
        ArrayList arrayList = new ArrayList();
        if (values2.contains("&")) {
            arrayList = new ArrayList(Arrays.asList(values2.split("&")));
            int i = afRDLog + 125;
            afWarnLog = i % 128;
            int i2 = i % 2;
        } else {
            arrayList.add(values2);
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (str2.contains("access_token")) {
                int i3 = afRDLog + 77;
                afWarnLog = i3 % 128;
                char c = i3 % 2 != 0 ? '>' : 'O';
                it.remove();
                if (c == '>') {
                    throw null;
                }
            } else {
                if (sb.length() != 0) {
                    int i4 = afRDLog + 101;
                    afWarnLog = i4 % 128;
                    if (i4 % 2 != 0) {
                        sb.append("&");
                        throw null;
                    }
                    sb.append("&");
                } else if (!str2.startsWith("?")) {
                    int i5 = afWarnLog + 29;
                    afRDLog = i5 % 128;
                    if ((i5 % 2 == 0 ? Typography.dollar : '0') != '0') {
                        sb.append("?");
                        throw null;
                    }
                    sb.append("?");
                }
                sb.append(str2);
            }
        }
        return str.replace(values2, sb.toString());
    }

    private static String values(String str) {
        int indexOf = str.indexOf(63);
        if (!(indexOf != -1)) {
            int i = afWarnLog + 9;
            afRDLog = i % 128;
            int i2 = i % 2;
            return "";
        }
        String substring = str.substring(indexOf);
        int i3 = afRDLog + 27;
        afWarnLog = i3 % 128;
        int i4 = i3 % 2;
        return substring;
    }

    private AFf1lSDK.AFa1vSDK AFInAppEventType(final Map<String, String> map) {
        AFf1lSDK.AFa1vSDK aFa1vSDK = new AFf1lSDK.AFa1vSDK() { // from class: com.appsflyer.internal.AFb1tSDK.5
            @Override // com.appsflyer.internal.AFf1lSDK.AFa1vSDK
            public final void values(String str) {
                AFb1tSDK.this.values().afWarnLog().valueOf(str, DeepLinkResult.Error.NETWORK);
            }

            @Override // com.appsflyer.internal.AFf1lSDK.AFa1vSDK
            public final void values(Map<String, String> map2) {
                for (String str : map2.keySet()) {
                    map.put(str, map2.get(str));
                }
                AFb1tSDK.this.values().afWarnLog().AFInAppEventType(map);
            }
        };
        int i = afRDLog + 111;
        afWarnLog = i % 128;
        int i2 = i % 2;
        return aFa1vSDK;
    }

    public static boolean valueOf(Context context) {
        int i = afRDLog + 101;
        afWarnLog = i % 128;
        int i2 = i % 2;
        try {
            if (!(GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) != 0)) {
                int i3 = afRDLog + 97;
                afWarnLog = i3 % 128;
                int i4 = i3 % 2;
                return true;
            }
        } catch (Throwable th) {
            AFLogger.afErrorLog("WARNING:  Google play services is unavailable. ", th);
        }
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gms", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            AFLogger.INSTANCE.m97e(AFg1gSDK.GENERAL, "WARNING:  Google Play Services is unavailable. ", e);
            return false;
        }
    }

    private String values(Context context, String str) {
        if (!(context == null)) {
            AFInAppEventParameterName(context);
            String AFInAppEventParameterName2 = values().AFInAppEventType().AFInAppEventParameterName(str);
            int i = afWarnLog + 3;
            afRDLog = i % 128;
            int i2 = i % 2;
            return AFInAppEventParameterName2;
        }
        int i3 = afWarnLog + 103;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
        return null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setPreinstallAttribution(String str, String str2, String str3) {
        AFLogger.afDebugLog("setPreinstallAttribution API called");
        JSONObject jSONObject = new JSONObject();
        if (str != null) {
            try {
                jSONObject.put("pid", str);
                int i = afWarnLog + 63;
                afRDLog = i % 128;
                int i2 = i % 2;
            } catch (JSONException e) {
                AFLogger.afErrorLog(e.getMessage(), e);
            }
        }
        boolean z = false;
        if (!(str2 == null)) {
            jSONObject.put("c", str2);
        }
        if (str3 == null) {
            z = true;
        }
        if (!z) {
            jSONObject.put("af_siteid", str3);
            int i3 = afWarnLog + 47;
            afRDLog = i3 % 128;
            int i4 = i3 % 2;
        }
        if (!jSONObject.has("pid")) {
            AFLogger.afWarnLog("Cannot set preinstall attribution data without a media source");
            return;
        }
        int i5 = afRDLog + 111;
        afWarnLog = i5 % 128;
        int i6 = i5 % 2;
        AFInAppEventType("preInstallName", jSONObject.toString());
    }

    private static void registerClient(String str) {
        try {
            if (!new JSONObject(str).has("pid")) {
                AFLogger.afWarnLog("Cannot set preinstall attribution data without a media source");
                int i = afWarnLog + 117;
                afRDLog = i % 128;
                if (i % 2 == 0) {
                    int i2 = 12 / 0;
                    return;
                }
                return;
            }
            int i3 = afRDLog + 35;
            afWarnLog = i3 % 128;
            if (i3 % 2 != 0) {
                AFInAppEventType("preInstallName", str);
                int i4 = 67 / 0;
            } else {
                AFInAppEventType("preInstallName", str);
            }
        } catch (JSONException e) {
            AFLogger.afErrorLog("Error parsing JSON for preinstall", e);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final boolean isPreInstalledApp(Context context) {
        int i = afWarnLog + 39;
        afRDLog = i % 128;
        int i2 = i % 2;
        try {
            if (!((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 1) == 0)) {
                int i3 = afRDLog + 9;
                afWarnLog = i3 % 128;
                int i4 = i3 % 2;
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            AFLogger.afErrorLog("Could not check if app is pre installed", e);
        }
        int i5 = afWarnLog + 113;
        afRDLog = i5 % 128;
        if (i5 % 2 != 0) {
            return false;
        }
        int i6 = 75 / 0;
        return false;
    }

    public static String AFKeystoreWrapper(AFd1tSDK aFd1tSDK, String str) {
        int i = afWarnLog + 51;
        afRDLog = i % 128;
        int i2 = i % 2;
        String AFKeystoreWrapper2 = aFd1tSDK.AFKeystoreWrapper("CACHED_CHANNEL", (String) null);
        if ((AFKeystoreWrapper2 != null ? '\n' : 'U') != 'U') {
            return AFKeystoreWrapper2;
        }
        aFd1tSDK.valueOf("CACHED_CHANNEL", str);
        int i3 = afRDLog + 23;
        afWarnLog = i3 % 128;
        int i4 = i3 % 2;
        return str;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final String getAttributionId(Context context) {
        try {
            String AFInAppEventType = new AFb1jSDK(context, values()).AFInAppEventType();
            int i = afWarnLog + 73;
            afRDLog = i % 128;
            int i2 = i % 2;
            return AFInAppEventType;
        } catch (Throwable th) {
            AFLogger.afErrorLog("Could not collect facebook attribution id. ", th);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12, types: [android.os.StrictMode$ThreadPolicy] */
    /* JADX WARN: Type inference failed for: r1v14, types: [android.os.StrictMode$ThreadPolicy] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    public static synchronized SharedPreferences AFInAppEventType(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (AFb1tSDK.class) {
            if (!(valueOf().f177w != null)) {
                int i = afRDLog + 99;
                afWarnLog = i % 128;
                ?? r1 = i % 2 != 0 ? 83 : 32;
                try {
                    if (r1 != 32) {
                        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                        valueOf().f177w = context.getApplicationContext().getSharedPreferences("appsflyer-data", 1);
                        r1 = allowThreadDiskReads;
                    } else {
                        StrictMode.ThreadPolicy allowThreadDiskReads2 = StrictMode.allowThreadDiskReads();
                        valueOf().f177w = context.getApplicationContext().getSharedPreferences("appsflyer-data", 0);
                        r1 = allowThreadDiskReads2;
                    }
                    StrictMode.setThreadPolicy(r1);
                } catch (Throwable th) {
                    StrictMode.setThreadPolicy(r1);
                    throw th;
                }
            }
            sharedPreferences = valueOf().f177w;
            int i2 = afRDLog + 51;
            afWarnLog = i2 % 128;
            if (i2 % 2 != 0) {
                Object obj = null;
                obj.hashCode();
                throw null;
            }
        }
        return sharedPreferences;
    }

    public final AFd1tSDK AFKeystoreWrapper(Context context) {
        int i = afRDLog + 119;
        afWarnLog = i % 128;
        int i2 = i % 2;
        AFInAppEventParameterName(context);
        AFd1tSDK values2 = values().values();
        int i3 = afWarnLog + 71;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
        return values2;
    }

    public static int AFInAppEventType(AFd1tSDK aFd1tSDK, boolean z) {
        int i = afWarnLog + 21;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? '!' : ',') != '!') {
            return values(aFd1tSDK, "appsFlyerCount", z);
        }
        values(aFd1tSDK, "appsFlyerCount", z);
        throw null;
    }

    private static int AFKeystoreWrapper(AFd1tSDK aFd1tSDK, boolean z) {
        int i = afRDLog + 31;
        afWarnLog = i % 128;
        if (i % 2 == 0) {
            return values(aFd1tSDK, "appsFlyerInAppEventCount", z);
        }
        values(aFd1tSDK, "appsFlyerInAppEventCount", z);
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    private static int values(AFd1tSDK aFd1tSDK, String str, boolean z) {
        int AFInAppEventParameterName2 = aFd1tSDK.AFInAppEventParameterName(str, 0);
        if (!(z ? false : true)) {
            int i = afWarnLog + 113;
            afRDLog = i % 128;
            int i2 = i % 2;
            AFInAppEventParameterName2++;
            aFd1tSDK.values(str, AFInAppEventParameterName2);
            int i3 = afWarnLog + 79;
            afRDLog = i3 % 128;
            int i4 = i3 % 2;
        }
        int i5 = afWarnLog + 53;
        afRDLog = i5 % 128;
        int i6 = i5 % 2;
        return AFInAppEventParameterName2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final String getAppsFlyerUID(Context context) {
        values().mo78i().AFInAppEventParameterName("getAppsFlyerUID", new String[0]);
        if ((context == null ? (char) 4 : ';') == ';') {
            AFInAppEventParameterName(context);
            AFd1sSDK AFInAppEventType = values().AFInAppEventType();
            return AFb1kSDK.AFInAppEventType(AFInAppEventType.AFInAppEventParameterName, AFInAppEventType.AFKeystoreWrapper);
        }
        int i = afWarnLog + 53;
        int i2 = i % 128;
        afRDLog = i2;
        int i3 = i % 2;
        int i4 = i2 + 109;
        afWarnLog = i4 % 128;
        int i5 = i4 % 2;
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0052, code lost:
    
        r2 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x002d, code lost:
    
        if (r23 == null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x004b, code lost:
    
        if ((r23 == null) != true) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x004d, code lost:
    
        r2 = r23.toString();
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00fa A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00fb  */
    @Override // com.appsflyer.AppsFlyerLib
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void validateAndLogInAppPurchase(android.content.Context r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.util.Map<java.lang.String, java.lang.String> r23) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.validateAndLogInAppPurchase(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Map):void");
    }

    @Override // com.appsflyer.AppsFlyerLib
    @Deprecated
    public final boolean isStopped() {
        int i = afWarnLog + 29;
        afRDLog = i % 128;
        int i2 = i % 2;
        boolean AFKeystoreWrapper2 = values().force().AFKeystoreWrapper();
        int i3 = afRDLog + 37;
        afWarnLog = i3 % 128;
        int i4 = i3 % 2;
        return AFKeystoreWrapper2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setLogLevel(AFLogger.LogLevel logLevel) {
        boolean z;
        int i = afWarnLog + 101;
        afRDLog = i % 128;
        int i2 = i % 2;
        if (!(logLevel.getCom.google.firebase.analytics.FirebaseAnalytics.Param.LEVEL java.lang.String() > AFLogger.LogLevel.NONE.getCom.google.firebase.analytics.FirebaseAnalytics.Param.LEVEL java.lang.String())) {
            int i3 = afWarnLog + 53;
            afRDLog = i3 % 128;
            int i4 = i3 % 2;
            z = false;
        } else {
            int i5 = afWarnLog + 81;
            afRDLog = i5 % 128;
            int i6 = i5 % 2;
            z = true;
        }
        values().mo78i().AFInAppEventParameterName("log", String.valueOf(z));
        AppsFlyerProperties.getInstance().set("logLevel", logLevel.getCom.google.firebase.analytics.FirebaseAnalytics.Param.LEVEL java.lang.String());
        if (!z) {
            values().afLogForce().AFKeystoreWrapper();
        } else {
            values().afLogForce().mo95d();
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setHost(String str, String str2) {
        String str3;
        int i = afRDLog + 13;
        afWarnLog = i % 128;
        if ((i % 2 != 0 ? 'F' : 'A') == 'F') {
            AFc1rSDK.AFKeystoreWrapper(str2);
            Object obj = null;
            obj.hashCode();
            throw null;
        }
        if (AFc1rSDK.AFKeystoreWrapper(str2)) {
            AFLogger.afWarnLog("hostname was empty or null - call for setHost is skipped");
            int i2 = afWarnLog + 49;
            afRDLog = i2 % 128;
            int i3 = i2 % 2;
            return;
        }
        if ((str != null ? 'T' : (char) 16) != 16) {
            str3 = str.trim();
        } else {
            int i4 = afRDLog + 111;
            afWarnLog = i4 % 128;
            int i5 = i4 % 2;
            str3 = "";
        }
        AFe1gSDK.AFInAppEventParameterName(new AFe1kSDK(str3, str2.trim()));
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final String getHostName() {
        int i = afRDLog + 37;
        afWarnLog = i % 128;
        int i2 = i % 2;
        String AFInAppEventType = values().afVerboseLog().AFInAppEventType();
        int i3 = afWarnLog + 51;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
        return AFInAppEventType;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final String getHostPrefix() {
        int i = afWarnLog + 121;
        afRDLog = i % 128;
        int i2 = i % 2;
        String AFInAppEventParameterName2 = values().afVerboseLog().AFInAppEventParameterName();
        int i3 = afWarnLog + 47;
        afRDLog = i3 % 128;
        if ((i3 % 2 == 0 ? (char) 22 : '<') == '<') {
            return AFInAppEventParameterName2;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setMinTimeBetweenSessions(int i) {
        int i2 = afWarnLog + 81;
        afRDLog = i2 % 128;
        int i3 = i2 % 2;
        this.f174d = TimeUnit.SECONDS.toMillis(i);
        int i4 = afWarnLog + 107;
        afRDLog = i4 % 128;
        int i5 = i4 % 2;
    }

    /* renamed from: d */
    private AFi1jSDK[] m69d() {
        AFi1jSDK[] AFKeystoreWrapper2;
        int i = afRDLog + 91;
        afWarnLog = i % 128;
        if (!(i % 2 != 0)) {
            AFKeystoreWrapper2 = values().mo79v().AFKeystoreWrapper();
        } else {
            AFKeystoreWrapper2 = values().mo79v().AFKeystoreWrapper();
            int i2 = 53 / 0;
        }
        int i3 = afWarnLog + 71;
        afRDLog = i3 % 128;
        if ((i3 % 2 == 0 ? '8' : (char) 6) == 6) {
            return AFKeystoreWrapper2;
        }
        int i4 = 95 / 0;
        return AFKeystoreWrapper2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class AFa1tSDK implements Runnable {
        private final AFa1pSDK valueOf;

        /* synthetic */ AFa1tSDK(AFb1tSDK aFb1tSDK, AFa1pSDK aFa1pSDK, byte b) {
            this(aFa1pSDK);
        }

        private AFa1tSDK(AFa1pSDK aFa1pSDK) {
            this.valueOf = aFa1pSDK;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AFb1tSDK.AFInAppEventParameterName(AFb1tSDK.this, this.valueOf);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setPluginInfo(PluginInfo pluginInfo) {
        int i = afWarnLog + 33;
        afRDLog = i % 128;
        if (!(i % 2 != 0)) {
            Objects.requireNonNull(pluginInfo);
            values().afDebugLog().AFInAppEventType(pluginInfo);
            int i2 = 34 / 0;
        } else {
            Objects.requireNonNull(pluginInfo);
            values().afDebugLog().AFInAppEventType(pluginInfo);
        }
    }

    /* loaded from: classes.dex */
    class AFa1vSDK implements AFe1bSDK {
        @Override // com.appsflyer.internal.AFe1bSDK
        public final void AFKeystoreWrapper(AFe1eSDK<?> aFe1eSDK) {
        }

        private AFa1vSDK() {
        }

        /* synthetic */ AFa1vSDK(AFb1tSDK aFb1tSDK, byte b) {
            this();
        }

        @Override // com.appsflyer.internal.AFe1bSDK
        public final void values(AFe1eSDK<?> aFe1eSDK) {
            if (aFe1eSDK instanceof AFf1jSDK) {
                AFb1tSDK.this.values().AFLogger().valueOf(((AFf1hSDK) aFe1eSDK).f212e.registerClient);
            }
        }

        @Override // com.appsflyer.internal.AFe1bSDK
        public final void AFInAppEventParameterName(AFe1eSDK<?> aFe1eSDK, AFe1dSDK aFe1dSDK) {
            JSONObject values;
            AFg1rSDK AFInAppEventParameterName;
            if (aFe1eSDK instanceof AFf1hSDK) {
                AFf1hSDK aFf1hSDK = (AFf1hSDK) aFe1eSDK;
                boolean z = aFe1eSDK instanceof AFf1jSDK;
                if (z && valueOf()) {
                    AFf1jSDK aFf1jSDK = (AFf1jSDK) aFe1eSDK;
                    boolean z2 = true;
                    if (aFf1jSDK.AFInAppEventType != AFe1dSDK.SUCCESS && aFf1jSDK.values != 1) {
                        z2 = false;
                    }
                    if (z2) {
                        AFg1mSDK aFg1mSDK = new AFg1mSDK(aFf1jSDK, AFb1tSDK.this.values().values());
                        AFe1fSDK afInfoLog = AFb1tSDK.this.values().afInfoLog();
                        afInfoLog.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(aFg1mSDK));
                    }
                }
                if (aFe1dSDK == AFe1dSDK.SUCCESS) {
                    AFb1tSDK aFb1tSDK = AFb1tSDK.this;
                    aFb1tSDK.AFKeystoreWrapper(AFb1tSDK.AFKeystoreWrapper(aFb1tSDK)).valueOf("sentSuccessfully", "true");
                    if (!(aFe1eSDK instanceof AFf1iSDK) && (AFInAppEventParameterName = new AFg1oSDK(AFb1tSDK.AFKeystoreWrapper(AFb1tSDK.this)).AFInAppEventParameterName()) != null && AFInAppEventParameterName.valueOf()) {
                        String str = AFInAppEventParameterName.AFInAppEventParameterName;
                        AFLogger.INSTANCE.m96d(AFg1gSDK.UNINSTALL, "Resending Uninstall token to AF servers: ".concat(String.valueOf(str)));
                        AFg1oSDK.AFInAppEventParameterName(str);
                    }
                    ResponseNetwork responseNetwork = aFf1hSDK.AFLogger;
                    if (responseNetwork != null && (values = AFc1vSDK.values((String) responseNetwork.getBody())) != null) {
                        AFb1tSDK.AFInAppEventParameterName(AFb1tSDK.this, values.optBoolean("send_background", false));
                    }
                    if (z) {
                        AFb1tSDK.values(AFb1tSDK.this, System.currentTimeMillis());
                        return;
                    }
                    return;
                }
                return;
            }
            if (!(aFe1eSDK instanceof AFg1mSDK) || aFe1dSDK == AFe1dSDK.SUCCESS) {
                return;
            }
            AFg1iSDK aFg1iSDK = new AFg1iSDK(AFb1tSDK.this.values());
            AFe1fSDK afInfoLog2 = AFb1tSDK.this.values().afInfoLog();
            afInfoLog2.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(aFg1iSDK));
        }

        private boolean valueOf() {
            return AFb1tSDK.this.AFInAppEventType != null;
        }
    }

    public final void AFInAppEventParameterName(Context context) {
        int i = afWarnLog;
        int i2 = i + 41;
        afRDLog = i2 % 128;
        int i3 = i2 % 2;
        AFd1lSDK aFd1lSDK = this.afInfoLog;
        if (context != null) {
            int i4 = i + 111;
            afRDLog = i4 % 128;
            if ((i4 % 2 == 0 ? '7' : '0') == '0') {
                AFd1kSDK aFd1kSDK = aFd1lSDK.AFInAppEventParameterName;
                if ((context != null ? (char) 17 : 'V') != 'V') {
                    aFd1kSDK.valueOf = context.getApplicationContext();
                    return;
                }
                return;
            }
            AFd1kSDK aFd1kSDK2 = aFd1lSDK.AFInAppEventParameterName;
            throw null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0041, code lost:
    
        r0 = new com.appsflyer.internal.AFf1xSDK(r5, r6, r4.AFKeystoreWrapper);
        r4 = r4.valueOf;
        r4.AFKeystoreWrapper.execute(new com.appsflyer.internal.AFe1fSDK.RunnableC07083(r4, r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003f, code lost:
    
        if (r4.valueOf(r5, r6, com.android.billingclient.api.BillingClient.FeatureType.SUBSCRIPTIONS) != false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0029, code lost:
    
        if (r4.valueOf(r5, r6, com.android.billingclient.api.BillingClient.FeatureType.SUBSCRIPTIONS) != false) goto L13;
     */
    @Override // com.appsflyer.AppsFlyerLib
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void sendPurchaseData(android.content.Context r4, java.util.Map<java.lang.String, java.lang.Object> r5, com.appsflyer.PurchaseHandler.PurchaseValidationCallback r6) {
        /*
            r3 = this;
            int r0 = com.appsflyer.internal.AFb1tSDK.afWarnLog
            int r0 = r0 + 55
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r1
            int r0 = r0 % 2
            r1 = 67
            if (r0 != 0) goto L10
            r0 = r1
            goto L12
        L10:
            r0 = 78
        L12:
            java.lang.String r2 = "subscriptions"
            if (r0 == r1) goto L2c
            r3.AFInAppEventParameterName(r4)
            com.appsflyer.internal.AFd1mSDK r4 = r3.values()
            com.appsflyer.PurchaseHandler r4 = r4.mo77e()
            java.lang.String[] r0 = new java.lang.String[]{r2}
            boolean r0 = r4.valueOf(r5, r6, r0)
            if (r0 == 0) goto L54
            goto L41
        L2c:
            r3.AFInAppEventParameterName(r4)
            com.appsflyer.internal.AFd1mSDK r4 = r3.values()
            com.appsflyer.PurchaseHandler r4 = r4.mo77e()
            java.lang.String[] r0 = new java.lang.String[]{r2}
            boolean r0 = r4.valueOf(r5, r6, r0)
            if (r0 == 0) goto L54
        L41:
            com.appsflyer.internal.AFf1xSDK r0 = new com.appsflyer.internal.AFf1xSDK
            com.appsflyer.internal.AFd1mSDK r1 = r4.AFKeystoreWrapper
            r0.<init>(r5, r6, r1)
            com.appsflyer.internal.AFe1fSDK r4 = r4.valueOf
            java.util.concurrent.Executor r5 = r4.AFKeystoreWrapper
            com.appsflyer.internal.AFe1fSDK$3 r6 = new com.appsflyer.internal.AFe1fSDK$3
            r6.<init>(r0)
            r5.execute(r6)
        L54:
            int r4 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r4 = r4 + 55
            int r5 = r4 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r5
            int r4 = r4 % 2
            r5 = 7
            if (r4 == 0) goto L63
            r4 = r5
            goto L65
        L63:
            r4 = 35
        L65:
            if (r4 == r5) goto L68
            return
        L68:
            r4 = 0
            throw r4     // Catch: java.lang.Throwable -> L6a
        L6a:
            r4 = move-exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.sendPurchaseData(android.content.Context, java.util.Map, com.appsflyer.PurchaseHandler$PurchaseValidationCallback):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0040, code lost:
    
        r0 = new com.appsflyer.internal.AFf1oSDK(r6, r7, r5.AFKeystoreWrapper);
        r5 = r5.valueOf;
        r5.AFKeystoreWrapper.execute(new com.appsflyer.internal.AFe1fSDK.RunnableC07083(r5, r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003e, code lost:
    
        if (r5.valueOf(r6, r7, "purchases") != false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0028, code lost:
    
        if (r5.valueOf(r6, r7, "purchases") != false) goto L13;
     */
    @Override // com.appsflyer.AppsFlyerLib
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void sendInAppPurchaseData(android.content.Context r5, java.util.Map<java.lang.String, java.lang.Object> r6, com.appsflyer.PurchaseHandler.PurchaseValidationCallback r7) {
        /*
            r4 = this;
            int r0 = com.appsflyer.internal.AFb1tSDK.afRDLog
            int r0 = r0 + 27
            int r1 = r0 % 128
            com.appsflyer.internal.AFb1tSDK.afWarnLog = r1
            int r0 = r0 % 2
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L10
            r0 = r1
            goto L11
        L10:
            r0 = r2
        L11:
            java.lang.String r3 = "purchases"
            if (r0 == 0) goto L2b
            r4.AFInAppEventParameterName(r5)
            com.appsflyer.internal.AFd1mSDK r5 = r4.values()
            com.appsflyer.PurchaseHandler r5 = r5.mo77e()
            java.lang.String[] r0 = new java.lang.String[]{r3}
            boolean r0 = r5.valueOf(r6, r7, r0)
            if (r0 == 0) goto L53
            goto L40
        L2b:
            r4.AFInAppEventParameterName(r5)
            com.appsflyer.internal.AFd1mSDK r5 = r4.values()
            com.appsflyer.PurchaseHandler r5 = r5.mo77e()
            java.lang.String[] r0 = new java.lang.String[]{r3}
            boolean r0 = r5.valueOf(r6, r7, r0)
            if (r0 == 0) goto L53
        L40:
            com.appsflyer.internal.AFf1oSDK r0 = new com.appsflyer.internal.AFf1oSDK
            com.appsflyer.internal.AFd1mSDK r3 = r5.AFKeystoreWrapper
            r0.<init>(r6, r7, r3)
            com.appsflyer.internal.AFe1fSDK r5 = r5.valueOf
            java.util.concurrent.Executor r6 = r5.AFKeystoreWrapper
            com.appsflyer.internal.AFe1fSDK$3 r7 = new com.appsflyer.internal.AFe1fSDK$3
            r7.<init>(r0)
            r6.execute(r7)
        L53:
            int r5 = com.appsflyer.internal.AFb1tSDK.afWarnLog
            int r5 = r5 + 111
            int r6 = r5 % 128
            com.appsflyer.internal.AFb1tSDK.afRDLog = r6
            int r5 = r5 % 2
            if (r5 != 0) goto L61
            r5 = r2
            goto L62
        L61:
            r5 = r1
        L62:
            if (r5 == r1) goto L6a
            r5 = 99
            int r5 = r5 / r2
            return
        L68:
            r5 = move-exception
            throw r5
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.sendInAppPurchaseData(android.content.Context, java.util.Map, com.appsflyer.PurchaseHandler$PurchaseValidationCallback):void");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void subscribeForDeepLink(DeepLinkListener deepLinkListener, long j) {
        int i = afWarnLog + 79;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? Matrix.MATRIX_TYPE_RANDOM_LT : (char) 27) != 27) {
            values().afWarnLog().AFInAppEventParameterName = deepLinkListener;
            values().afWarnLog().registerClient = j;
            int i2 = 16 / 0;
        } else {
            values().afWarnLog().AFInAppEventParameterName = deepLinkListener;
            values().afWarnLog().registerClient = j;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0041, code lost:
    
        if (r5.isEmpty() != false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0053, code lost:
    
        if (r6 == null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0059, code lost:
    
        if (r6.isEmpty() == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005c, code lost:
    
        r1 = new java.lang.StringBuilder("Setting partner data for ");
        r1.append(r5);
        r1.append(": ");
        r1.append(r6);
        com.appsflyer.AFLogger.afDebugLog(r1.toString());
        r1 = new org.json.JSONObject(r6).toString().length();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0084, code lost:
    
        if (r1 <= 1000) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0086, code lost:
    
        com.appsflyer.AFLogger.afWarnLog("Partner data 1000 characters limit exceeded");
        r6 = new java.util.HashMap();
        r6.put("error", "limit exceeded: ".concat(java.lang.String.valueOf(r1)));
        r0.valueOf.put(r5, r6);
        r5 = com.appsflyer.internal.AFb1tSDK.afWarnLog + 21;
        com.appsflyer.internal.AFb1tSDK.afRDLog = r5 % 128;
        r5 = r5 % 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00ae, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00af, code lost:
    
        r0.AFKeystoreWrapper.put(r5, r6);
        r0.valueOf.remove(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00b9, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00c2, code lost:
    
        if (r0.AFKeystoreWrapper.remove(r5) != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00c5, code lost:
    
        r2 = '+';
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00c6, code lost:
    
        if (r2 == '+') goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00c8, code lost:
    
        r5 = com.appsflyer.internal.AFb1tSDK.afWarnLog + 47;
        com.appsflyer.internal.AFb1tSDK.afRDLog = r5 % 128;
        r5 = r5 % 2;
        r5 = "Partner data is missing or `null`";
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00df, code lost:
    
        com.appsflyer.AFLogger.afWarnLog(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00e2, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d5, code lost:
    
        r5 = "Cleared partner data for ".concat(java.lang.String.valueOf(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0051, code lost:
    
        if ((r5.isEmpty() ? 23 : 17) != 23) goto L24;
     */
    @Override // com.appsflyer.AppsFlyerLib
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setPartnerData(java.lang.String r5, java.util.Map<java.lang.String, java.lang.Object> r6) {
        /*
            Method dump skipped, instructions count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.setPartnerData(java.lang.String, java.util.Map):void");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setImeiData(String str) {
        int i = afRDLog + 55;
        afWarnLog = i % 128;
        int i2 = i % 2;
        values().mo78i().AFInAppEventParameterName("setImeiData", str);
        values().force().unregisterClient = str;
        int i3 = afRDLog + 125;
        afWarnLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setAndroidIdData(String str) {
        int i = afRDLog + 45;
        afWarnLog = i % 128;
        int i2 = i % 2;
        values().mo78i().AFInAppEventParameterName("setAndroidIdData", str);
        values().afErrorLogForExcManagerOnly().values = str;
        int i3 = afWarnLog + 1;
        afRDLog = i3 % 128;
        if ((i3 % 2 == 0 ? '.' : (char) 24) != '.') {
        } else {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setResolveDeepLinkURLs(String... strArr) {
        int i = afRDLog + 65;
        afWarnLog = i % 128;
        int i2 = i % 2;
        AFLogger.afDebugLog(String.format("setResolveDeepLinkURLs %s", Arrays.toString(strArr)));
        AFc1uSDK afWarnLog2 = values().afWarnLog();
        afWarnLog2.f179e.clear();
        afWarnLog2.f179e.addAll(Arrays.asList(strArr));
        int i3 = afWarnLog + 9;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setOneLinkCustomDomain(String... strArr) {
        int i = afRDLog + 19;
        afWarnLog = i % 128;
        int i2 = i % 2;
        AFLogger.afDebugLog(String.format("setOneLinkCustomDomain %s", Arrays.toString(strArr)));
        values().afWarnLog().f178d = strArr;
        int i3 = afWarnLog + 117;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setPhoneNumber(String str) {
        int i = afWarnLog + 71;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? '*' : '=') != '*') {
            values().afErrorLogForExcManagerOnly().valueOf = AFb1mSDK.valueOf(str);
        } else {
            values().afErrorLogForExcManagerOnly().valueOf = AFb1mSDK.valueOf(str);
            int i2 = 21 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void logEvent(Context context, String str, Map<String, Object> map, AppsFlyerRequestListener appsFlyerRequestListener) {
        HashMap hashMap = map == null ? null : new HashMap(map);
        AFInAppEventParameterName(context);
        AFh1pSDK aFh1pSDK = new AFh1pSDK();
        aFh1pSDK.AFLogger = str;
        aFh1pSDK.AFInAppEventType = appsFlyerRequestListener;
        if (hashMap != null && hashMap.containsKey(AFInAppEventParameterName.TOUCH_OBJ)) {
            HashMap hashMap2 = new HashMap();
            Object obj = hashMap.get(AFInAppEventParameterName.TOUCH_OBJ);
            if (obj instanceof MotionEvent) {
                MotionEvent motionEvent = (MotionEvent) obj;
                HashMap hashMap3 = new HashMap();
                hashMap3.put("x", Float.valueOf(motionEvent.getX()));
                hashMap3.put("y", Float.valueOf(motionEvent.getY()));
                hashMap2.put("loc", hashMap3);
                hashMap2.put("pf", Float.valueOf(motionEvent.getPressure()));
                hashMap2.put("rad", Float.valueOf(motionEvent.getTouchMajor() / 2.0f));
            } else {
                hashMap2.put("error", "Parsing failed due to invalid input in 'af_touch_obj'.");
                AFLogger.INSTANCE.mo59w(AFg1gSDK.PREDICT, "Parsing failed due to invalid input in 'af_touch_obj'.", true);
            }
            Map<String, ?> singletonMap = Collections.singletonMap("tch_data", hashMap2);
            hashMap.remove(AFInAppEventParameterName.TOUCH_OBJ);
            aFh1pSDK.AFInAppEventParameterName(singletonMap);
        }
        aFh1pSDK.AFKeystoreWrapper = hashMap;
        AFb1bSDK mo78i = values().mo78i();
        String[] strArr = new String[2];
        strArr[0] = str;
        strArr[1] = new JSONObject(aFh1pSDK.AFKeystoreWrapper == null ? new HashMap() : aFh1pSDK.AFKeystoreWrapper).toString();
        mo78i.AFInAppEventParameterName("logEvent", strArr);
        if (str == null) {
            AFInAppEventParameterName(context, AFg1aSDK.logEvent);
        }
        AFInAppEventParameterName(aFh1pSDK, unregisterClient(context));
    }

    private static void valueOf(AFa1pSDK aFa1pSDK, AFh1uSDK aFh1uSDK) {
        int i = afWarnLog + 25;
        int i2 = i % 128;
        afRDLog = i2;
        int i3 = i % 2;
        if ((aFh1uSDK != null ? 'G' : '-') != 'G') {
            return;
        }
        int i4 = i2 + 125;
        afWarnLog = i4 % 128;
        if ((i4 % 2 != 0 ? 'a' : (char) 0) == 0) {
            aFa1pSDK.values = aFh1uSDK.values;
            aFa1pSDK.f161e = aFh1uSDK.valueOf;
            int i5 = afRDLog + 113;
            afWarnLog = i5 % 128;
            int i6 = i5 % 2;
            return;
        }
        aFa1pSDK.values = aFh1uSDK.values;
        aFa1pSDK.f161e = aFh1uSDK.valueOf;
        throw null;
    }

    private void registerClient() {
        int i = afRDLog + 15;
        afWarnLog = i % 128;
        int i2 = i % 2;
        if (AFf1qSDK.registerClient()) {
            return;
        }
        AFd1mSDK values2 = values();
        AFe1fSDK afInfoLog = values2.afInfoLog();
        afInfoLog.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(new AFf1qSDK(values2)));
        int i3 = afWarnLog + 115;
        afRDLog = i3 % 128;
        if (i3 % 2 != 0) {
            return;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setConsentData(AppsFlyerConsent appsFlyerConsent) {
        int i = afRDLog + 49;
        afWarnLog = i % 128;
        if ((i % 2 != 0 ? (char) 25 : 'V') != 25) {
            Objects.requireNonNull(appsFlyerConsent);
            values().afErrorLogForExcManagerOnly().f188d = appsFlyerConsent;
        } else {
            Objects.requireNonNull(appsFlyerConsent);
            values().afErrorLogForExcManagerOnly().f188d = appsFlyerConsent;
            int i2 = 22 / 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0010, code lost:
    
        if (r12 != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0017, code lost:
    
        r12 = r12.toCharArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0015, code lost:
    
        if (r12 != null) goto L11;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m68a(byte r10, int r11, java.lang.String r12, java.lang.Object[] r13) {
        /*
            Method dump skipped, instructions count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1tSDK.m68a(byte, int, java.lang.String, java.lang.Object[]):void");
    }
}
