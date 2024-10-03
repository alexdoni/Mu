package com.facebook.appevents.cloudbridge;

import android.content.SharedPreferences;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AppEventsCAPIManager.kt */
@Metadata(m1394d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0007J\u0015\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR@\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e8A@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, m1395d2 = {"Lcom/facebook/appevents/cloudbridge/AppEventsCAPIManager;", "", "()V", "SETTINGS_PATH", "", "TAG", "kotlin.jvm.PlatformType", "isEnabled", "", "isEnabled$facebook_core_release", "()Z", "setEnabled$facebook_core_release", "(Z)V", "valuesToSave", "", "savedCloudBridgeCredentials", "getSavedCloudBridgeCredentials$facebook_core_release", "()Ljava/util/Map;", "setSavedCloudBridgeCredentials$facebook_core_release", "(Ljava/util/Map;)V", "enable", "", "getCAPIGSettingsFromGraphResponse", "response", "Lcom/facebook/GraphResponse;", "getCAPIGSettingsFromGraphResponse$facebook_core_release", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AppEventsCAPIManager {
    private static final String SETTINGS_PATH = "/cloudbridge_settings";
    private static boolean isEnabled;
    public static final AppEventsCAPIManager INSTANCE = new AppEventsCAPIManager();
    private static final String TAG = AppEventsCAPIManager.class.getCanonicalName();

    private AppEventsCAPIManager() {
    }

    public final boolean isEnabled$facebook_core_release() {
        return isEnabled;
    }

    public final void setEnabled$facebook_core_release(boolean z) {
        isEnabled = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004a A[Catch: all -> 0x00aa, TryCatch #0 {all -> 0x00aa, blocks: (B:6:0x000a, B:9:0x001a, B:11:0x003e, B:16:0x004a, B:18:0x004f, B:23:0x005b, B:25:0x0060, B:31:0x006d), top: B:5:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b A[Catch: all -> 0x00aa, TryCatch #0 {all -> 0x00aa, blocks: (B:6:0x000a, B:9:0x001a, B:11:0x003e, B:16:0x004a, B:18:0x004f, B:23:0x005b, B:25:0x0060, B:31:0x006d), top: B:5:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006d A[Catch: all -> 0x00aa, TRY_LEAVE, TryCatch #0 {all -> 0x00aa, blocks: (B:6:0x000a, B:9:0x001a, B:11:0x003e, B:16:0x004a, B:18:0x004f, B:23:0x005b, B:25:0x0060, B:31:0x006d), top: B:5:0x000a }] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.util.Map<java.lang.String, java.lang.Object> getSavedCloudBridgeCredentials$facebook_core_release() {
        /*
            java.lang.Class<com.facebook.appevents.cloudbridge.AppEventsCAPIManager> r0 = com.facebook.appevents.cloudbridge.AppEventsCAPIManager.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            r2 = 0
            if (r1 == 0) goto La
            return r2
        La:
            com.facebook.FacebookSdk r1 = com.facebook.FacebookSdk.INSTANCE     // Catch: java.lang.Throwable -> Laa
            android.content.Context r1 = com.facebook.FacebookSdk.getApplicationContext()     // Catch: java.lang.Throwable -> Laa
            java.lang.String r3 = "com.facebook.sdk.CloudBridgeSavedCredentials"
            r4 = 0
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r3, r4)     // Catch: java.lang.Throwable -> Laa
            if (r1 != 0) goto L1a
            return r2
        L1a:
            com.facebook.appevents.cloudbridge.SettingsAPIFields r3 = com.facebook.appevents.cloudbridge.SettingsAPIFields.DATASETID     // Catch: java.lang.Throwable -> Laa
            java.lang.String r3 = r3.getRawValue()     // Catch: java.lang.Throwable -> Laa
            java.lang.String r3 = r1.getString(r3, r2)     // Catch: java.lang.Throwable -> Laa
            com.facebook.appevents.cloudbridge.SettingsAPIFields r5 = com.facebook.appevents.cloudbridge.SettingsAPIFields.URL     // Catch: java.lang.Throwable -> Laa
            java.lang.String r5 = r5.getRawValue()     // Catch: java.lang.Throwable -> Laa
            java.lang.String r5 = r1.getString(r5, r2)     // Catch: java.lang.Throwable -> Laa
            com.facebook.appevents.cloudbridge.SettingsAPIFields r6 = com.facebook.appevents.cloudbridge.SettingsAPIFields.ACCESSKEY     // Catch: java.lang.Throwable -> Laa
            java.lang.String r6 = r6.getRawValue()     // Catch: java.lang.Throwable -> Laa
            java.lang.String r1 = r1.getString(r6, r2)     // Catch: java.lang.Throwable -> Laa
            r6 = r3
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch: java.lang.Throwable -> Laa
            r7 = 1
            if (r6 == 0) goto L47
            boolean r6 = kotlin.text.StringsKt.isBlank(r6)     // Catch: java.lang.Throwable -> Laa
            if (r6 == 0) goto L45
            goto L47
        L45:
            r6 = r4
            goto L48
        L47:
            r6 = r7
        L48:
            if (r6 != 0) goto La9
            r6 = r5
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch: java.lang.Throwable -> Laa
            if (r6 == 0) goto L58
            boolean r6 = kotlin.text.StringsKt.isBlank(r6)     // Catch: java.lang.Throwable -> Laa
            if (r6 == 0) goto L56
            goto L58
        L56:
            r6 = r4
            goto L59
        L58:
            r6 = r7
        L59:
            if (r6 != 0) goto La9
            r6 = r1
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch: java.lang.Throwable -> Laa
            if (r6 == 0) goto L69
            boolean r6 = kotlin.text.StringsKt.isBlank(r6)     // Catch: java.lang.Throwable -> Laa
            if (r6 == 0) goto L67
            goto L69
        L67:
            r6 = r4
            goto L6a
        L69:
            r6 = r7
        L6a:
            if (r6 == 0) goto L6d
            goto La9
        L6d:
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap     // Catch: java.lang.Throwable -> Laa
            r6.<init>()     // Catch: java.lang.Throwable -> Laa
            java.util.Map r6 = (java.util.Map) r6     // Catch: java.lang.Throwable -> Laa
            com.facebook.appevents.cloudbridge.SettingsAPIFields r8 = com.facebook.appevents.cloudbridge.SettingsAPIFields.URL     // Catch: java.lang.Throwable -> Laa
            java.lang.String r8 = r8.getRawValue()     // Catch: java.lang.Throwable -> Laa
            r6.put(r8, r5)     // Catch: java.lang.Throwable -> Laa
            com.facebook.appevents.cloudbridge.SettingsAPIFields r8 = com.facebook.appevents.cloudbridge.SettingsAPIFields.DATASETID     // Catch: java.lang.Throwable -> Laa
            java.lang.String r8 = r8.getRawValue()     // Catch: java.lang.Throwable -> Laa
            r6.put(r8, r3)     // Catch: java.lang.Throwable -> Laa
            com.facebook.appevents.cloudbridge.SettingsAPIFields r8 = com.facebook.appevents.cloudbridge.SettingsAPIFields.ACCESSKEY     // Catch: java.lang.Throwable -> Laa
            java.lang.String r8 = r8.getRawValue()     // Catch: java.lang.Throwable -> Laa
            r6.put(r8, r1)     // Catch: java.lang.Throwable -> Laa
            com.facebook.internal.Logger$Companion r8 = com.facebook.internal.Logger.INSTANCE     // Catch: java.lang.Throwable -> Laa
            com.facebook.LoggingBehavior r9 = com.facebook.LoggingBehavior.APP_EVENTS     // Catch: java.lang.Throwable -> Laa
            java.lang.String r10 = com.facebook.appevents.cloudbridge.AppEventsCAPIManager.TAG     // Catch: java.lang.Throwable -> Laa
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> Laa
            java.lang.String r11 = " \n\nLoading Cloudbridge settings from saved Prefs: \n================\n DATASETID: %s\n URL: %s \n ACCESSKEY: %s \n\n "
            r12 = 3
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch: java.lang.Throwable -> Laa
            r12[r4] = r3     // Catch: java.lang.Throwable -> Laa
            r12[r7] = r5     // Catch: java.lang.Throwable -> Laa
            r3 = 2
            r12[r3] = r1     // Catch: java.lang.Throwable -> Laa
            r8.log(r9, r10, r11, r12)     // Catch: java.lang.Throwable -> Laa
            return r6
        La9:
            return r2
        Laa:
            r1 = move-exception
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r1, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.cloudbridge.AppEventsCAPIManager.getSavedCloudBridgeCredentials$facebook_core_release():java.util.Map");
    }

    public final void setSavedCloudBridgeCredentials$facebook_core_release(Map<String, ? extends Object> map) {
        FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
        SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences(FacebookSdk.CLOUDBRIDGE_SAVED_CREDENTIALS, 0);
        if (sharedPreferences == null) {
            return;
        }
        if (map == null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.clear();
            edit.apply();
            return;
        }
        Object obj = map.get(SettingsAPIFields.DATASETID.getRawValue());
        Object obj2 = map.get(SettingsAPIFields.URL.getRawValue());
        Object obj3 = map.get(SettingsAPIFields.ACCESSKEY.getRawValue());
        if (obj == null || obj2 == null || obj3 == null) {
            return;
        }
        SharedPreferences.Editor edit2 = sharedPreferences.edit();
        edit2.putString(SettingsAPIFields.DATASETID.getRawValue(), obj.toString());
        edit2.putString(SettingsAPIFields.URL.getRawValue(), obj2.toString());
        edit2.putString(SettingsAPIFields.ACCESSKEY.getRawValue(), obj3.toString());
        edit2.apply();
        Logger.INSTANCE.log(LoggingBehavior.APP_EVENTS, TAG.toString(), " \n\nSaving Cloudbridge settings from saved Prefs: \n================\n DATASETID: %s\n URL: %s \n ACCESSKEY: %s \n\n ", obj, obj2, obj3);
    }

    @JvmStatic
    public static final void enable() {
        try {
            GraphRequest.Callback callback = new GraphRequest.Callback() { // from class: com.facebook.appevents.cloudbridge.AppEventsCAPIManager$$ExternalSyntheticLambda0
                @Override // com.facebook.GraphRequest.Callback
                public final void onCompleted(GraphResponse graphResponse) {
                    AppEventsCAPIManager.m1671enable$lambda0(graphResponse);
                }
            };
            FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
            GraphRequest graphRequest = new GraphRequest(null, Intrinsics.stringPlus(FacebookSdk.getApplicationId(), SETTINGS_PATH), null, HttpMethod.GET, callback, null, 32, null);
            Logger.Companion companion = Logger.INSTANCE;
            LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
            String str = TAG;
            if (str != null) {
                companion.log(loggingBehavior, str, " \n\nCreating Graph Request: \n=============\n%s\n\n ", graphRequest);
                graphRequest.executeAsync();
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } catch (JSONException e) {
            Logger.Companion companion2 = Logger.INSTANCE;
            LoggingBehavior loggingBehavior2 = LoggingBehavior.APP_EVENTS;
            String str2 = TAG;
            if (str2 != null) {
                companion2.log(loggingBehavior2, str2, " \n\nGraph Request Exception: \n=============\n%s\n\n ", ExceptionsKt.stackTraceToString(e));
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: enable$lambda-0, reason: not valid java name */
    public static final void m1671enable$lambda0(GraphResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        INSTANCE.getCAPIGSettingsFromGraphResponse$facebook_core_release(response);
    }

    public final void getCAPIGSettingsFromGraphResponse$facebook_core_release(GraphResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        boolean z = false;
        if (response.getError() != null) {
            Logger.Companion companion = Logger.INSTANCE;
            LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
            String str = TAG;
            if (str != null) {
                companion.log(loggingBehavior, str, " \n\nGraph Response Error: \n================\nResponse Error: %s\nResponse Error Exception: %s\n\n ", response.getError().toString(), String.valueOf(response.getError().getException()));
                Map<String, Object> savedCloudBridgeCredentials$facebook_core_release = getSavedCloudBridgeCredentials$facebook_core_release();
                if (savedCloudBridgeCredentials$facebook_core_release != null) {
                    URL url = new URL(String.valueOf(savedCloudBridgeCredentials$facebook_core_release.get(SettingsAPIFields.URL.getRawValue())));
                    AppEventsConversionsAPITransformerWebRequests appEventsConversionsAPITransformerWebRequests = AppEventsConversionsAPITransformerWebRequests.INSTANCE;
                    AppEventsConversionsAPITransformerWebRequests.configure(String.valueOf(savedCloudBridgeCredentials$facebook_core_release.get(SettingsAPIFields.DATASETID.getRawValue())), url.getProtocol() + "://" + ((Object) url.getHost()), String.valueOf(savedCloudBridgeCredentials$facebook_core_release.get(SettingsAPIFields.ACCESSKEY.getRawValue())));
                    isEnabled = true;
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        Logger.Companion companion2 = Logger.INSTANCE;
        LoggingBehavior loggingBehavior2 = LoggingBehavior.APP_EVENTS;
        String TAG2 = TAG;
        if (TAG2 != null) {
            companion2.log(loggingBehavior2, TAG2, " \n\nGraph Response Received: \n================\n%s\n\n ", response);
            JSONObject graphObject = response.getGraphObject();
            try {
                Utility utility = Utility.INSTANCE;
                Object obj = graphObject == null ? null : graphObject.get("data");
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                }
                List<String> convertJSONArrayToList = Utility.convertJSONArrayToList((JSONArray) obj);
                Utility utility2 = Utility.INSTANCE;
                Map<String, ? extends Object> convertJSONObjectToHashMap = Utility.convertJSONObjectToHashMap(new JSONObject((String) CollectionsKt.firstOrNull((List) convertJSONArrayToList)));
                String str2 = (String) convertJSONObjectToHashMap.get(SettingsAPIFields.URL.getRawValue());
                String str3 = (String) convertJSONObjectToHashMap.get(SettingsAPIFields.DATASETID.getRawValue());
                String str4 = (String) convertJSONObjectToHashMap.get(SettingsAPIFields.ACCESSKEY.getRawValue());
                if (str2 == null || str3 == null || str4 == null) {
                    Logger.Companion companion3 = Logger.INSTANCE;
                    LoggingBehavior loggingBehavior3 = LoggingBehavior.APP_EVENTS;
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    companion3.log(loggingBehavior3, TAG2, "CloudBridge Settings API response doesn't have valid data");
                    return;
                }
                try {
                    AppEventsConversionsAPITransformerWebRequests appEventsConversionsAPITransformerWebRequests2 = AppEventsConversionsAPITransformerWebRequests.INSTANCE;
                    AppEventsConversionsAPITransformerWebRequests.configure(str3, str2, str4);
                    setSavedCloudBridgeCredentials$facebook_core_release(convertJSONObjectToHashMap);
                    if (convertJSONObjectToHashMap.get(SettingsAPIFields.ENABLED.getRawValue()) != null) {
                        Object obj2 = convertJSONObjectToHashMap.get(SettingsAPIFields.ENABLED.getRawValue());
                        if (obj2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                        }
                        z = ((Boolean) obj2).booleanValue();
                    }
                    isEnabled = z;
                    return;
                } catch (MalformedURLException e) {
                    Logger.Companion companion4 = Logger.INSTANCE;
                    LoggingBehavior loggingBehavior4 = LoggingBehavior.APP_EVENTS;
                    String TAG3 = TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                    companion4.log(loggingBehavior4, TAG3, "CloudBridge Settings API response doesn't have valid url\n %s ", ExceptionsKt.stackTraceToString(e));
                    return;
                }
            } catch (NullPointerException e2) {
                Logger.Companion companion5 = Logger.INSTANCE;
                LoggingBehavior loggingBehavior5 = LoggingBehavior.APP_EVENTS;
                String TAG4 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG4, "TAG");
                companion5.log(loggingBehavior5, TAG4, "CloudBridge Settings API response is not a valid json: \n%s ", ExceptionsKt.stackTraceToString(e2));
                return;
            } catch (JSONException e3) {
                Logger.Companion companion6 = Logger.INSTANCE;
                LoggingBehavior loggingBehavior6 = LoggingBehavior.APP_EVENTS;
                String TAG5 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG5, "TAG");
                companion6.log(loggingBehavior6, TAG5, "CloudBridge Settings API response is not a valid json: \n%s ", ExceptionsKt.stackTraceToString(e3));
                return;
            }
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}
