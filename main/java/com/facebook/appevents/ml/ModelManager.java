package com.facebook.appevents.ml;

import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.GraphRequest;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.integrity.IntegrityManager;
import com.facebook.appevents.internal.FileDownloadTask;
import com.facebook.appevents.ml.ModelManager;
import com.facebook.appevents.suggestedevents.SuggestedEventsManager;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.share.internal.ShareConstants;
import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ModelManager.kt */
@Metadata(m1394d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001:\u000278B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0019H\u0007J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\n\u0010\u001e\u001a\u0004\u0018\u00010\u001bH\u0002J\u0012\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"H\u0007J\u0010\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%H\u0002J\u0014\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0002J\u0010\u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020\u001bH\u0002J9\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010-2\u0006\u0010!\u001a\u00020\"2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020'0-2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00040-H\u0007¢\u0006\u0002\u00100J%\u00101\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010-2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020'H\u0002¢\u0006\u0002\u00105J%\u00106\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010-2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020'H\u0002¢\u0006\u0002\u00105R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014R\u001c\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, m1395d2 = {"Lcom/facebook/appevents/ml/ModelManager;", "", "()V", "ASSET_URI_KEY", "", "CACHE_KEY_MODELS", "CACHE_KEY_REQUEST_TIMESTAMP", "MODEL_ASSERT_STORE", "MODEL_REQUEST_INTERVAL_MILLISECONDS", "", "MTML_INTEGRITY_DETECT_PREDICTION", "", "MTML_SUGGESTED_EVENTS_PREDICTION", "MTML_USE_CASE", "RULES_URI_KEY", "THRESHOLD_KEY", "USE_CASE_KEY", "VERSION_ID_KEY", "isLocaleEnglish", "", "()Z", "taskHandlers", "", "Lcom/facebook/appevents/ml/ModelManager$TaskHandler;", "addModels", "", ModelManager.CACHE_KEY_MODELS, "Lorg/json/JSONObject;", "enable", "enableMTML", "fetchModels", "getRuleFile", "Ljava/io/File;", "task", "Lcom/facebook/appevents/ml/ModelManager$Task;", "isValidTimestamp", "timestamp", "", "parseJsonArray", "", "jsonArray", "Lorg/json/JSONArray;", "parseRawJsonObject", "jsonObject", "predict", "", "denses", "texts", "(Lcom/facebook/appevents/ml/ModelManager$Task;[[F[Ljava/lang/String;)[Ljava/lang/String;", "processIntegrityDetectionResult", "res", "Lcom/facebook/appevents/ml/MTensor;", ModelManager.THRESHOLD_KEY, "(Lcom/facebook/appevents/ml/MTensor;[F)[Ljava/lang/String;", "processSuggestedEventResult", "Task", "TaskHandler", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class ModelManager {
    private static final String ASSET_URI_KEY = "asset_uri";
    private static final String CACHE_KEY_MODELS = "models";
    private static final String CACHE_KEY_REQUEST_TIMESTAMP = "model_request_timestamp";
    private static final String MODEL_ASSERT_STORE = "com.facebook.internal.MODEL_STORE";
    public static final int MODEL_REQUEST_INTERVAL_MILLISECONDS = 259200000;
    private static final String MTML_USE_CASE = "MTML";
    private static final String RULES_URI_KEY = "rules_uri";
    private static final String THRESHOLD_KEY = "thresholds";
    private static final String USE_CASE_KEY = "use_case";
    private static final String VERSION_ID_KEY = "version_id";
    public static final ModelManager INSTANCE = new ModelManager();
    private static final Map<String, TaskHandler> taskHandlers = new ConcurrentHashMap();
    private static final List<String> MTML_SUGGESTED_EVENTS_PREDICTION = CollectionsKt.listOf((Object[]) new String[]{"other", AppEventsConstants.EVENT_NAME_COMPLETED_REGISTRATION, AppEventsConstants.EVENT_NAME_ADDED_TO_CART, AppEventsConstants.EVENT_NAME_PURCHASED, AppEventsConstants.EVENT_NAME_INITIATED_CHECKOUT});
    private static final List<String> MTML_INTEGRITY_DETECT_PREDICTION = CollectionsKt.listOf((Object[]) new String[]{"none", IntegrityManager.INTEGRITY_TYPE_ADDRESS, IntegrityManager.INTEGRITY_TYPE_HEALTH});

    /* compiled from: ModelManager.kt */
    @Metadata(m1396k = 3, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Task.valuesCustom().length];
            iArr[Task.MTML_APP_EVENT_PREDICTION.ordinal()] = 1;
            iArr[Task.MTML_INTEGRITY_DETECT.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ModelManager() {
    }

    public static final /* synthetic */ float[] access$parseJsonArray(ModelManager modelManager, JSONArray jSONArray) {
        if (CrashShieldHandler.isObjectCrashing(ModelManager.class)) {
            return null;
        }
        try {
            return modelManager.parseJsonArray(jSONArray);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ModelManager.class);
            return null;
        }
    }

    /* compiled from: ModelManager.kt */
    @Metadata(m1394d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m1395d2 = {"Lcom/facebook/appevents/ml/ModelManager$Task;", "", "(Ljava/lang/String;I)V", "toKey", "", "toUseCase", "MTML_INTEGRITY_DETECT", "MTML_APP_EVENT_PREDICTION", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public enum Task {
        MTML_INTEGRITY_DETECT,
        MTML_APP_EVENT_PREDICTION;

        /* compiled from: ModelManager.kt */
        @Metadata(m1396k = 3, m1397mv = {1, 5, 1}, m1399xi = 48)
        /* loaded from: classes.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Task.valuesCustom().length];
                iArr[Task.MTML_INTEGRITY_DETECT.ordinal()] = 1;
                iArr[Task.MTML_APP_EVENT_PREDICTION.ordinal()] = 2;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public final String toKey() {
            int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
            if (i == 1) {
                return "integrity_detect";
            }
            if (i == 2) {
                return "app_event_pred";
            }
            throw new NoWhenBranchMatchedException();
        }

        public final String toUseCase() {
            int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
            if (i == 1) {
                return "MTML_INTEGRITY_DETECT";
            }
            if (i == 2) {
                return "MTML_APP_EVENT_PRED";
            }
            throw new NoWhenBranchMatchedException();
        }

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static Task[] valuesCustom() {
            Task[] valuesCustom = values();
            return (Task[]) Arrays.copyOf(valuesCustom, valuesCustom.length);
        }
    }

    @JvmStatic
    public static final void enable() {
        if (CrashShieldHandler.isObjectCrashing(ModelManager.class)) {
            return;
        }
        try {
            Utility utility = Utility.INSTANCE;
            Utility.runOnNonUiThread(new Runnable() { // from class: com.facebook.appevents.ml.ModelManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ModelManager.m1702enable$lambda0();
                }
            });
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ModelManager.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0060 A[Catch: all -> 0x0080, Exception -> 0x0084, TryCatch #2 {Exception -> 0x0084, all -> 0x0080, blocks: (B:6:0x000d, B:8:0x0021, B:13:0x002e, B:14:0x0039, B:16:0x0049, B:18:0x004f, B:20:0x0077, B:23:0x0057, B:26:0x0060, B:27:0x0034), top: B:5:0x000d }] */
    /* renamed from: enable$lambda-0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m1702enable$lambda0() {
        /*
            java.lang.String r0 = "model_request_timestamp"
            java.lang.String r1 = "models"
            java.lang.Class<com.facebook.appevents.ml.ModelManager> r2 = com.facebook.appevents.ml.ModelManager.class
            boolean r3 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r2)
            if (r3 == 0) goto Ld
            return
        Ld:
            com.facebook.FacebookSdk r3 = com.facebook.FacebookSdk.INSTANCE     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            android.content.Context r3 = com.facebook.FacebookSdk.getApplicationContext()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            java.lang.String r4 = "com.facebook.internal.MODEL_STORE"
            r5 = 0
            android.content.SharedPreferences r3 = r3.getSharedPreferences(r4, r5)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            r4 = 0
            java.lang.String r4 = r3.getString(r1, r4)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            if (r4 == 0) goto L34
            r6 = r4
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            int r6 = r6.length()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            if (r6 != 0) goto L2b
            r5 = 1
        L2b:
            if (r5 == 0) goto L2e
            goto L34
        L2e:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            goto L39
        L34:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            r5.<init>()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
        L39:
            r6 = 0
            long r6 = r3.getLong(r0, r6)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            com.facebook.internal.FeatureManager r4 = com.facebook.internal.FeatureManager.INSTANCE     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            com.facebook.internal.FeatureManager$Feature r4 = com.facebook.internal.FeatureManager.Feature.ModelRequest     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            boolean r4 = com.facebook.internal.FeatureManager.isEnabled(r4)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            if (r4 == 0) goto L57
            int r4 = r5.length()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            if (r4 == 0) goto L57
            com.facebook.appevents.ml.ModelManager r4 = com.facebook.appevents.ml.ModelManager.INSTANCE     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            boolean r4 = r4.isValidTimestamp(r6)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            if (r4 != 0) goto L77
        L57:
            com.facebook.appevents.ml.ModelManager r4 = com.facebook.appevents.ml.ModelManager.INSTANCE     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            org.json.JSONObject r5 = r4.fetchModels()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            if (r5 != 0) goto L60
            return
        L60:
            android.content.SharedPreferences$Editor r3 = r3.edit()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            java.lang.String r4 = r5.toString()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            android.content.SharedPreferences$Editor r1 = r3.putString(r1, r4)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            android.content.SharedPreferences$Editor r0 = r1.putLong(r0, r3)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            r0.apply()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
        L77:
            com.facebook.appevents.ml.ModelManager r0 = com.facebook.appevents.ml.ModelManager.INSTANCE     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            r0.addModels(r5)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            r0.enableMTML()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L84
            goto L84
        L80:
            r0 = move-exception
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r0, r2)
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.ml.ModelManager.m1702enable$lambda0():void");
    }

    private final boolean isValidTimestamp(long timestamp) {
        if (CrashShieldHandler.isObjectCrashing(this) || timestamp == 0) {
            return false;
        }
        try {
            return System.currentTimeMillis() - timestamp < 259200000;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final void addModels(JSONObject models) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            Iterator<String> keys = models.keys();
            while (keys.hasNext()) {
                try {
                    TaskHandler build = TaskHandler.INSTANCE.build(models.getJSONObject(keys.next()));
                    if (build != null) {
                        taskHandlers.put(build.getUseCase(), build);
                    }
                } catch (JSONException unused) {
                    return;
                }
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    private final JSONObject parseRawJsonObject(JSONObject jsonObject) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            try {
                JSONArray jSONArray = jsonObject.getJSONArray("data");
                int length = jSONArray.length();
                if (length <= 0) {
                    return jSONObject;
                }
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put(VERSION_ID_KEY, jSONObject2.getString(VERSION_ID_KEY));
                    jSONObject3.put(USE_CASE_KEY, jSONObject2.getString(USE_CASE_KEY));
                    jSONObject3.put(THRESHOLD_KEY, jSONObject2.getJSONArray(THRESHOLD_KEY));
                    jSONObject3.put(ASSET_URI_KEY, jSONObject2.getString(ASSET_URI_KEY));
                    if (jSONObject2.has(RULES_URI_KEY)) {
                        jSONObject3.put(RULES_URI_KEY, jSONObject2.getString(RULES_URI_KEY));
                    }
                    jSONObject.put(jSONObject2.getString(USE_CASE_KEY), jSONObject3);
                    if (i2 >= length) {
                        return jSONObject;
                    }
                    i = i2;
                }
            } catch (JSONException unused) {
                return new JSONObject();
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final JSONObject fetchModels() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            String[] strArr = {USE_CASE_KEY, VERSION_ID_KEY, ASSET_URI_KEY, RULES_URI_KEY, THRESHOLD_KEY};
            Bundle bundle = new Bundle();
            bundle.putString(GraphRequest.FIELDS_PARAM, TextUtils.join(",", strArr));
            GraphRequest newGraphPathRequest = GraphRequest.INSTANCE.newGraphPathRequest(null, "app/model_asset", null);
            newGraphPathRequest.setParameters(bundle);
            JSONObject graphObject = newGraphPathRequest.executeAndWait().getGraphObject();
            if (graphObject == null) {
                return null;
            }
            return parseRawJsonObject(graphObject);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final void enableMTML() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            String str = null;
            int i = 0;
            for (Map.Entry<String, TaskHandler> entry : taskHandlers.entrySet()) {
                String key = entry.getKey();
                TaskHandler value = entry.getValue();
                if (Intrinsics.areEqual(key, Task.MTML_APP_EVENT_PREDICTION.toUseCase())) {
                    String assetUri = value.getAssetUri();
                    int max = Math.max(i, value.getVersionId());
                    FeatureManager featureManager = FeatureManager.INSTANCE;
                    if (FeatureManager.isEnabled(FeatureManager.Feature.SuggestedEvents) && isLocaleEnglish()) {
                        arrayList.add(value.setOnPostExecute(new Runnable() { // from class: com.facebook.appevents.ml.ModelManager$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                ModelManager.m1703enableMTML$lambda1();
                            }
                        }));
                    }
                    str = assetUri;
                    i = max;
                }
                if (Intrinsics.areEqual(key, Task.MTML_INTEGRITY_DETECT.toUseCase())) {
                    str = value.getAssetUri();
                    i = Math.max(i, value.getVersionId());
                    FeatureManager featureManager2 = FeatureManager.INSTANCE;
                    if (FeatureManager.isEnabled(FeatureManager.Feature.IntelligentIntegrity)) {
                        arrayList.add(value.setOnPostExecute(new Runnable() { // from class: com.facebook.appevents.ml.ModelManager$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                ModelManager.m1704enableMTML$lambda2();
                            }
                        }));
                    }
                }
            }
            if (str == null || i <= 0 || arrayList.isEmpty()) {
                return;
            }
            TaskHandler.INSTANCE.execute(new TaskHandler(MTML_USE_CASE, str, null, i, null), arrayList);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: enableMTML$lambda-1, reason: not valid java name */
    public static final void m1703enableMTML$lambda1() {
        if (CrashShieldHandler.isObjectCrashing(ModelManager.class)) {
            return;
        }
        try {
            SuggestedEventsManager suggestedEventsManager = SuggestedEventsManager.INSTANCE;
            SuggestedEventsManager.enable();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ModelManager.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: enableMTML$lambda-2, reason: not valid java name */
    public static final void m1704enableMTML$lambda2() {
        if (CrashShieldHandler.isObjectCrashing(ModelManager.class)) {
            return;
        }
        try {
            IntegrityManager integrityManager = IntegrityManager.INSTANCE;
            IntegrityManager.enable();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ModelManager.class);
        }
    }

    private final boolean isLocaleEnglish() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            Utility utility = Utility.INSTANCE;
            Locale resourceLocale = Utility.getResourceLocale();
            if (resourceLocale != null) {
                String language = resourceLocale.getLanguage();
                Intrinsics.checkNotNullExpressionValue(language, "locale.language");
                if (!StringsKt.contains$default((CharSequence) language, (CharSequence) LanguageType.SERVER_EN, false, 2, (Object) null)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final float[] parseJsonArray(JSONArray jsonArray) {
        if (CrashShieldHandler.isObjectCrashing(this) || jsonArray == null) {
            return null;
        }
        try {
            float[] fArr = new float[jsonArray.length()];
            int length = jsonArray.length();
            if (length > 0) {
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    try {
                        String string = jsonArray.getString(i);
                        Intrinsics.checkNotNullExpressionValue(string, "jsonArray.getString(i)");
                        fArr[i] = Float.parseFloat(string);
                    } catch (JSONException unused) {
                    }
                    if (i2 >= length) {
                        break;
                    }
                    i = i2;
                }
            }
            return fArr;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    @JvmStatic
    public static final File getRuleFile(Task task) {
        if (CrashShieldHandler.isObjectCrashing(ModelManager.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(task, "task");
            TaskHandler taskHandler = taskHandlers.get(task.toUseCase());
            if (taskHandler == null) {
                return null;
            }
            return taskHandler.getRuleFile();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ModelManager.class);
            return null;
        }
    }

    @JvmStatic
    public static final String[] predict(Task task, float[][] denses, String[] texts) {
        if (CrashShieldHandler.isObjectCrashing(ModelManager.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(denses, "denses");
            Intrinsics.checkNotNullParameter(texts, "texts");
            TaskHandler taskHandler = taskHandlers.get(task.toUseCase());
            Model model = taskHandler == null ? null : taskHandler.getModel();
            if (model == null) {
                return null;
            }
            float[] thresholds = taskHandler.getThresholds();
            int length = texts.length;
            int length2 = denses[0].length;
            MTensor mTensor = new MTensor(new int[]{length, length2});
            if (length > 0) {
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    System.arraycopy(denses[i], 0, mTensor.getData(), i * length2, length2);
                    if (i2 >= length) {
                        break;
                    }
                    i = i2;
                }
            }
            MTensor predictOnMTML = model.predictOnMTML(mTensor, texts, task.toKey());
            if (predictOnMTML != null && thresholds != null) {
                if (!(predictOnMTML.getData().length == 0)) {
                    if (!(thresholds.length == 0)) {
                        int i3 = WhenMappings.$EnumSwitchMapping$0[task.ordinal()];
                        if (i3 == 1) {
                            return INSTANCE.processSuggestedEventResult(predictOnMTML, thresholds);
                        }
                        if (i3 == 2) {
                            return INSTANCE.processIntegrityDetectionResult(predictOnMTML, thresholds);
                        }
                        throw new NoWhenBranchMatchedException();
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ModelManager.class);
            return null;
        }
    }

    private final String[] processSuggestedEventResult(MTensor res, float[] thresholds) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            int shape = res.getShape(0);
            int shape2 = res.getShape(1);
            float[] data = res.getData();
            if (shape2 != thresholds.length) {
                return null;
            }
            IntRange until = RangesKt.until(0, shape);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
            Iterator<Integer> it = until.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                String str = "other";
                int length = thresholds.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    int i3 = i2 + 1;
                    if (data[(nextInt * shape2) + i2] >= thresholds[i]) {
                        str = MTML_SUGGESTED_EVENTS_PREDICTION.get(i2);
                    }
                    i++;
                    i2 = i3;
                }
                arrayList.add(str);
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final String[] processIntegrityDetectionResult(MTensor res, float[] thresholds) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            int shape = res.getShape(0);
            int shape2 = res.getShape(1);
            float[] data = res.getData();
            if (shape2 != thresholds.length) {
                return null;
            }
            IntRange until = RangesKt.until(0, shape);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
            Iterator<Integer> it = until.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                String str = "none";
                int length = thresholds.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    int i3 = i2 + 1;
                    if (data[(nextInt * shape2) + i2] >= thresholds[i]) {
                        str = MTML_INTEGRITY_DETECT_PREDICTION.get(i2);
                    }
                    i++;
                    i2 = i3;
                }
                arrayList.add(str);
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    /* compiled from: ModelManager.kt */
    @Metadata(m1394d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000 *2\u00020\u0001:\u0001*B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0010\u0010)\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\f\"\u0004\b\u001e\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\f\"\u0004\b$\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u0006+"}, m1395d2 = {"Lcom/facebook/appevents/ml/ModelManager$TaskHandler;", "", "useCase", "", "assetUri", "ruleUri", "versionId", "", ModelManager.THRESHOLD_KEY, "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I[F)V", "getAssetUri", "()Ljava/lang/String;", "setAssetUri", "(Ljava/lang/String;)V", "model", "Lcom/facebook/appevents/ml/Model;", "getModel", "()Lcom/facebook/appevents/ml/Model;", "setModel", "(Lcom/facebook/appevents/ml/Model;)V", "onPostExecute", "Ljava/lang/Runnable;", "ruleFile", "Ljava/io/File;", "getRuleFile", "()Ljava/io/File;", "setRuleFile", "(Ljava/io/File;)V", "getRuleUri", "setRuleUri", "getThresholds", "()[F", "setThresholds", "([F)V", "getUseCase", "setUseCase", "getVersionId", "()I", "setVersionId", "(I)V", "setOnPostExecute", "Companion", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final class TaskHandler {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private String assetUri;
        private Model model;
        private Runnable onPostExecute;
        private File ruleFile;
        private String ruleUri;
        private float[] thresholds;
        private String useCase;
        private int versionId;

        public TaskHandler(String useCase, String assetUri, String str, int i, float[] fArr) {
            Intrinsics.checkNotNullParameter(useCase, "useCase");
            Intrinsics.checkNotNullParameter(assetUri, "assetUri");
            this.useCase = useCase;
            this.assetUri = assetUri;
            this.ruleUri = str;
            this.versionId = i;
            this.thresholds = fArr;
        }

        public final String getUseCase() {
            return this.useCase;
        }

        public final void setUseCase(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.useCase = str;
        }

        public final String getAssetUri() {
            return this.assetUri;
        }

        public final void setAssetUri(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.assetUri = str;
        }

        public final String getRuleUri() {
            return this.ruleUri;
        }

        public final void setRuleUri(String str) {
            this.ruleUri = str;
        }

        public final int getVersionId() {
            return this.versionId;
        }

        public final void setVersionId(int i) {
            this.versionId = i;
        }

        public final float[] getThresholds() {
            return this.thresholds;
        }

        public final void setThresholds(float[] fArr) {
            this.thresholds = fArr;
        }

        public final File getRuleFile() {
            return this.ruleFile;
        }

        public final void setRuleFile(File file) {
            this.ruleFile = file;
        }

        public final Model getModel() {
            return this.model;
        }

        public final void setModel(Model model) {
            this.model = model;
        }

        public final TaskHandler setOnPostExecute(Runnable onPostExecute) {
            this.onPostExecute = onPostExecute;
            return this;
        }

        /* compiled from: ModelManager.kt */
        @Metadata(m1394d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\"\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u000e\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0004J\u001c\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0016¨\u0006\u0017"}, m1395d2 = {"Lcom/facebook/appevents/ml/ModelManager$TaskHandler$Companion;", "", "()V", "build", "Lcom/facebook/appevents/ml/ModelManager$TaskHandler;", "json", "Lorg/json/JSONObject;", "deleteOldFiles", "", "useCase", "", "versionId", "", "download", ShareConstants.MEDIA_URI, "name", "onComplete", "Lcom/facebook/appevents/internal/FileDownloadTask$Callback;", "execute", "handler", "master", "slaves", "", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
        /* loaded from: classes.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final TaskHandler build(JSONObject json) {
                if (json == null) {
                    return null;
                }
                try {
                    String useCase = json.getString(ModelManager.USE_CASE_KEY);
                    String assetUri = json.getString(ModelManager.ASSET_URI_KEY);
                    String optString = json.optString(ModelManager.RULES_URI_KEY, null);
                    int i = json.getInt(ModelManager.VERSION_ID_KEY);
                    float[] access$parseJsonArray = ModelManager.access$parseJsonArray(ModelManager.INSTANCE, json.getJSONArray(ModelManager.THRESHOLD_KEY));
                    Intrinsics.checkNotNullExpressionValue(useCase, "useCase");
                    Intrinsics.checkNotNullExpressionValue(assetUri, "assetUri");
                    return new TaskHandler(useCase, assetUri, optString, i, access$parseJsonArray);
                } catch (Exception unused) {
                    return null;
                }
            }

            public final void execute(TaskHandler handler) {
                Intrinsics.checkNotNullParameter(handler, "handler");
                execute(handler, CollectionsKt.listOf(handler));
            }

            public final void execute(TaskHandler master, final List<TaskHandler> slaves) {
                Intrinsics.checkNotNullParameter(master, "master");
                Intrinsics.checkNotNullParameter(slaves, "slaves");
                deleteOldFiles(master.getUseCase(), master.getVersionId());
                download(master.getAssetUri(), master.getUseCase() + '_' + master.getVersionId(), new FileDownloadTask.Callback() { // from class: com.facebook.appevents.ml.ModelManager$TaskHandler$Companion$$ExternalSyntheticLambda0
                    @Override // com.facebook.appevents.internal.FileDownloadTask.Callback
                    public final void onComplete(File file) {
                        ModelManager.TaskHandler.Companion.m1706execute$lambda1(slaves, file);
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: execute$lambda-1, reason: not valid java name */
            public static final void m1706execute$lambda1(List slaves, File file) {
                Intrinsics.checkNotNullParameter(slaves, "$slaves");
                Intrinsics.checkNotNullParameter(file, "file");
                final Model build = Model.INSTANCE.build(file);
                if (build != null) {
                    Iterator it = slaves.iterator();
                    while (it.hasNext()) {
                        final TaskHandler taskHandler = (TaskHandler) it.next();
                        TaskHandler.INSTANCE.download(taskHandler.getRuleUri(), taskHandler.getUseCase() + '_' + taskHandler.getVersionId() + "_rule", new FileDownloadTask.Callback() { // from class: com.facebook.appevents.ml.ModelManager$TaskHandler$Companion$$ExternalSyntheticLambda1
                            @Override // com.facebook.appevents.internal.FileDownloadTask.Callback
                            public final void onComplete(File file2) {
                                ModelManager.TaskHandler.Companion.m1707execute$lambda1$lambda0(ModelManager.TaskHandler.this, build, file2);
                            }
                        });
                    }
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: execute$lambda-1$lambda-0, reason: not valid java name */
            public static final void m1707execute$lambda1$lambda0(TaskHandler slave, Model model, File file) {
                Intrinsics.checkNotNullParameter(slave, "$slave");
                Intrinsics.checkNotNullParameter(file, "file");
                slave.setModel(model);
                slave.setRuleFile(file);
                Runnable runnable = slave.onPostExecute;
                if (runnable == null) {
                    return;
                }
                runnable.run();
            }

            private final void deleteOldFiles(String useCase, int versionId) {
                File[] listFiles;
                Utils utils = Utils.INSTANCE;
                File mlDir = Utils.getMlDir();
                if (mlDir == null || (listFiles = mlDir.listFiles()) == null) {
                    return;
                }
                if (listFiles.length == 0) {
                    return;
                }
                String str = useCase + '_' + versionId;
                int length = listFiles.length;
                int i = 0;
                while (i < length) {
                    File file = listFiles[i];
                    i++;
                    String name = file.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "name");
                    if (StringsKt.startsWith$default(name, useCase, false, 2, (Object) null) && !StringsKt.startsWith$default(name, str, false, 2, (Object) null)) {
                        file.delete();
                    }
                }
            }

            private final void download(String uri, String name, FileDownloadTask.Callback onComplete) {
                Utils utils = Utils.INSTANCE;
                File file = new File(Utils.getMlDir(), name);
                if (uri == null || file.exists()) {
                    onComplete.onComplete(file);
                } else {
                    new FileDownloadTask(uri, file, onComplete).execute(new String[0]);
                }
            }
        }
    }
}
