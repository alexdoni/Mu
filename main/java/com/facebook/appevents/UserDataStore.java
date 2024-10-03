package com.facebook.appevents;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import com.facebook.FacebookSdk;
import com.facebook.appevents.aam.MetadataRule;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.share.internal.ShareConstants;
import io.jsonwebtoken.Header;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* compiled from: UserDataStore.kt */
@Metadata(m1394d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010 \u001a\u00020!H\u0007J\b\u0010\"\u001a\u00020\u0004H\u0007J\r\u0010#\u001a\u00020\u0004H\u0001¢\u0006\u0002\b$J\b\u0010%\u001a\u00020!H\u0002J\b\u0010&\u001a\u00020!H\u0007J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0004H\u0002J\u0018\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004H\u0002J\u001c\u0010,\u001a\u00020!2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0016H\u0007J\u0012\u0010.\u001a\u00020!2\b\u0010-\u001a\u0004\u0018\u00010/H\u0007Jl\u0010.\u001a\u00020!2\b\u00100\u001a\u0004\u0018\u00010\u00042\b\u00101\u001a\u0004\u0018\u00010\u00042\b\u00102\u001a\u0004\u0018\u00010\u00042\b\u00103\u001a\u0004\u0018\u00010\u00042\b\u00104\u001a\u0004\u0018\u00010\u00042\b\u00105\u001a\u0004\u0018\u00010\u00042\b\u00106\u001a\u0004\u0018\u00010\u00042\b\u00107\u001a\u0004\u0018\u00010\u00042\b\u00108\u001a\u0004\u0018\u00010\u00042\b\u00109\u001a\u0004\u0018\u00010\u0004H\u0007J\u0012\u0010:\u001a\u00020!2\b\u0010-\u001a\u0004\u0018\u00010/H\u0002J\u0018\u0010;\u001a\u00020!2\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n \u0012*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R$\u0010\u0015\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000¨\u0006>"}, m1395d2 = {"Lcom/facebook/appevents/UserDataStore;", "", "()V", "CITY", "", "COUNTRY", "DATA_SEPARATOR", "DATE_OF_BIRTH", "EMAIL", "FIRST_NAME", "GENDER", "INTERNAL_USER_DATA_KEY", "LAST_NAME", "MAX_NUM", "", "PHONE", "STATE", "TAG", "kotlin.jvm.PlatformType", "USER_DATA_KEY", "ZIP", "enabledInternalUserData", "", "getEnabledInternalUserData", "()Ljava/util/Map;", "externalHashedUserData", "Ljava/util/concurrent/ConcurrentHashMap;", "initialized", "Ljava/util/concurrent/atomic/AtomicBoolean;", "internalHashedUserData", "sharedPreferences", "Landroid/content/SharedPreferences;", "clear", "", "getAllHashedUserData", "getHashedUserData", "getHashedUserData$facebook_core_release", "initAndWait", "initStore", "maybeSHA256Hashed", "", "data", "normalizeData", ShareConstants.MEDIA_TYPE, "setInternalUd", "ud", "setUserDataAndHash", "Landroid/os/Bundle;", "email", "firstName", "lastName", "phone", "dateOfBirth", "gender", "city", ServerProtocol.DIALOG_PARAM_STATE, Header.COMPRESSION_ALGORITHM, UserDataStore.COUNTRY, "updateHashUserData", "writeDataIntoCache", "key", "value", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class UserDataStore {
    public static final String CITY = "ct";
    public static final String COUNTRY = "country";
    private static final String DATA_SEPARATOR = ",";
    public static final String DATE_OF_BIRTH = "db";
    public static final String EMAIL = "em";
    public static final String FIRST_NAME = "fn";
    public static final String GENDER = "ge";
    private static final String INTERNAL_USER_DATA_KEY = "com.facebook.appevents.UserDataStore.internalUserData";
    public static final String LAST_NAME = "ln";
    private static final int MAX_NUM = 5;
    public static final String PHONE = "ph";
    public static final String STATE = "st";
    private static final String USER_DATA_KEY = "com.facebook.appevents.UserDataStore.userData";
    public static final String ZIP = "zp";
    private static SharedPreferences sharedPreferences;
    public static final UserDataStore INSTANCE = new UserDataStore();
    private static final String TAG = "UserDataStore";
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static final ConcurrentHashMap<String, String> externalHashedUserData = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, String> internalHashedUserData = new ConcurrentHashMap<>();

    private UserDataStore() {
    }

    @JvmStatic
    public static final void initStore() {
        if (CrashShieldHandler.isObjectCrashing(UserDataStore.class)) {
            return;
        }
        try {
            if (initialized.get()) {
                return;
            }
            INSTANCE.initAndWait();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, UserDataStore.class);
        }
    }

    private final void writeDataIntoCache(final String key, final String value) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
            FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.appevents.UserDataStore$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    UserDataStore.m1666writeDataIntoCache$lambda0(key, value);
                }
            });
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    /* renamed from: writeDataIntoCache$lambda-0 */
    public static final void m1666writeDataIntoCache$lambda0(String key, String value) {
        if (CrashShieldHandler.isObjectCrashing(UserDataStore.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(key, "$key");
            Intrinsics.checkNotNullParameter(value, "$value");
            if (!initialized.get()) {
                INSTANCE.initAndWait();
            }
            SharedPreferences sharedPreferences2 = sharedPreferences;
            if (sharedPreferences2 != null) {
                sharedPreferences2.edit().putString(key, value).apply();
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
                throw null;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, UserDataStore.class);
        }
    }

    @JvmStatic
    public static final void setUserDataAndHash(final Bundle ud) {
        if (CrashShieldHandler.isObjectCrashing(UserDataStore.class)) {
            return;
        }
        try {
            InternalAppEventsLogger.INSTANCE.getAnalyticsExecutor().execute(new Runnable() { // from class: com.facebook.appevents.UserDataStore$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    UserDataStore.m1665setUserDataAndHash$lambda1(ud);
                }
            });
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, UserDataStore.class);
        }
    }

    /* renamed from: setUserDataAndHash$lambda-1 */
    public static final void m1665setUserDataAndHash$lambda1(Bundle bundle) {
        if (CrashShieldHandler.isObjectCrashing(UserDataStore.class)) {
            return;
        }
        try {
            if (!initialized.get()) {
                Log.w(TAG, "initStore should have been called before calling setUserData");
                INSTANCE.initAndWait();
            }
            UserDataStore userDataStore = INSTANCE;
            userDataStore.updateHashUserData(bundle);
            Utility utility = Utility.INSTANCE;
            userDataStore.writeDataIntoCache(USER_DATA_KEY, Utility.mapToJsonStr(externalHashedUserData));
            Utility utility2 = Utility.INSTANCE;
            userDataStore.writeDataIntoCache(INTERNAL_USER_DATA_KEY, Utility.mapToJsonStr(internalHashedUserData));
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, UserDataStore.class);
        }
    }

    @JvmStatic
    public static final void setUserDataAndHash(String email, String firstName, String lastName, String phone, String dateOfBirth, String gender, String city, String r10, String r11, String r12) {
        if (CrashShieldHandler.isObjectCrashing(UserDataStore.class)) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            if (email != null) {
                bundle.putString(EMAIL, email);
            }
            if (firstName != null) {
                bundle.putString(FIRST_NAME, firstName);
            }
            if (lastName != null) {
                bundle.putString(LAST_NAME, lastName);
            }
            if (phone != null) {
                bundle.putString(PHONE, phone);
            }
            if (dateOfBirth != null) {
                bundle.putString(DATE_OF_BIRTH, dateOfBirth);
            }
            if (gender != null) {
                bundle.putString(GENDER, gender);
            }
            if (city != null) {
                bundle.putString(CITY, city);
            }
            if (r10 != null) {
                bundle.putString(STATE, r10);
            }
            if (r11 != null) {
                bundle.putString(ZIP, r11);
            }
            if (r12 != null) {
                bundle.putString(COUNTRY, r12);
            }
            setUserDataAndHash(bundle);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, UserDataStore.class);
        }
    }

    @JvmStatic
    public static final void clear() {
        if (CrashShieldHandler.isObjectCrashing(UserDataStore.class)) {
            return;
        }
        try {
            InternalAppEventsLogger.INSTANCE.getAnalyticsExecutor().execute(new Runnable() { // from class: com.facebook.appevents.UserDataStore$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    UserDataStore.m1664clear$lambda2();
                }
            });
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, UserDataStore.class);
        }
    }

    /* renamed from: clear$lambda-2 */
    public static final void m1664clear$lambda2() {
        if (CrashShieldHandler.isObjectCrashing(UserDataStore.class)) {
            return;
        }
        try {
            if (!initialized.get()) {
                Log.w(TAG, "initStore should have been called before calling setUserData");
                INSTANCE.initAndWait();
            }
            externalHashedUserData.clear();
            SharedPreferences sharedPreferences2 = sharedPreferences;
            if (sharedPreferences2 != null) {
                sharedPreferences2.edit().putString(USER_DATA_KEY, null).apply();
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
                throw null;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, UserDataStore.class);
        }
    }

    @JvmStatic
    public static final String getHashedUserData$facebook_core_release() {
        if (CrashShieldHandler.isObjectCrashing(UserDataStore.class)) {
            return null;
        }
        try {
            if (!initialized.get()) {
                Log.w(TAG, "initStore should have been called before calling setUserID");
                INSTANCE.initAndWait();
            }
            Utility utility = Utility.INSTANCE;
            return Utility.mapToJsonStr(externalHashedUserData);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, UserDataStore.class);
            return null;
        }
    }

    @JvmStatic
    public static final String getAllHashedUserData() {
        if (CrashShieldHandler.isObjectCrashing(UserDataStore.class)) {
            return null;
        }
        try {
            if (!initialized.get()) {
                INSTANCE.initAndWait();
            }
            HashMap hashMap = new HashMap();
            hashMap.putAll(externalHashedUserData);
            hashMap.putAll(INSTANCE.getEnabledInternalUserData());
            Utility utility = Utility.INSTANCE;
            return Utility.mapToJsonStr(hashMap);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, UserDataStore.class);
            return null;
        }
    }

    private final Map<String, String> getEnabledInternalUserData() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            Set<String> enabledRuleNames = MetadataRule.INSTANCE.getEnabledRuleNames();
            for (String str : internalHashedUserData.keySet()) {
                if (enabledRuleNames.contains(str)) {
                    hashMap.put(str, internalHashedUserData.get(str));
                }
            }
            return hashMap;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final synchronized void initAndWait() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            AtomicBoolean atomicBoolean = initialized;
            if (atomicBoolean.get()) {
                return;
            }
            FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
            Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(FacebookSdk.getApplicationContext())");
            sharedPreferences = defaultSharedPreferences;
            if (defaultSharedPreferences != null) {
                String string = defaultSharedPreferences.getString(USER_DATA_KEY, "");
                if (string == null) {
                    string = "";
                }
                SharedPreferences sharedPreferences2 = sharedPreferences;
                if (sharedPreferences2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
                    throw null;
                }
                String string2 = sharedPreferences2.getString(INTERNAL_USER_DATA_KEY, "");
                if (string2 == null) {
                    string2 = "";
                }
                ConcurrentHashMap<String, String> concurrentHashMap = externalHashedUserData;
                Utility utility = Utility.INSTANCE;
                concurrentHashMap.putAll(Utility.jsonStrToMap(string));
                ConcurrentHashMap<String, String> concurrentHashMap2 = internalHashedUserData;
                Utility utility2 = Utility.INSTANCE;
                concurrentHashMap2.putAll(Utility.jsonStrToMap(string2));
                atomicBoolean.set(true);
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            throw null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    private final void updateHashUserData(Bundle ud) {
        if (CrashShieldHandler.isObjectCrashing(this) || ud == null) {
            return;
        }
        try {
            for (String key : ud.keySet()) {
                Object obj = ud.get(key);
                if (obj != null) {
                    String obj2 = obj.toString();
                    if (maybeSHA256Hashed(obj2)) {
                        ConcurrentHashMap<String, String> concurrentHashMap = externalHashedUserData;
                        if (obj2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                        String lowerCase = obj2.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
                        concurrentHashMap.put(key, lowerCase);
                    } else {
                        Utility utility = Utility.INSTANCE;
                        Intrinsics.checkNotNullExpressionValue(key, "key");
                        String sha256hash = Utility.sha256hash(normalizeData(key, obj2));
                        if (sha256hash != null) {
                            externalHashedUserData.put(key, sha256hash);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    @JvmStatic
    public static final void setInternalUd(Map<String, String> ud) {
        if (CrashShieldHandler.isObjectCrashing(UserDataStore.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(ud, "ud");
            if (!initialized.get()) {
                INSTANCE.initAndWait();
            }
            for (Map.Entry<String, String> entry : ud.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Utility utility = Utility.INSTANCE;
                UserDataStore userDataStore = INSTANCE;
                String str = value;
                int i = 1;
                int length = str.length() - 1;
                int i2 = 0;
                boolean z = false;
                while (i2 <= length) {
                    boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i2 : length), 32) <= 0;
                    if (z) {
                        if (!z2) {
                            break;
                        } else {
                            length--;
                        }
                    } else if (z2) {
                        i2++;
                    } else {
                        z = true;
                    }
                }
                String sha256hash = Utility.sha256hash(userDataStore.normalizeData(key, str.subSequence(i2, length + 1).toString()));
                ConcurrentHashMap<String, String> concurrentHashMap = internalHashedUserData;
                if (concurrentHashMap.containsKey(key)) {
                    String str2 = concurrentHashMap.get(key);
                    String[] strArr = null;
                    if (str2 != null) {
                        List<String> split = new Regex(DATA_SEPARATOR).split(str2, 0);
                        if (split != null) {
                            Object[] array = split.toArray(new String[0]);
                            if (array == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                            strArr = (String[]) array;
                        }
                    }
                    if (strArr == null) {
                        strArr = new String[0];
                    }
                    Set mutableSetOf = SetsKt.mutableSetOf(Arrays.copyOf(strArr, strArr.length));
                    if (mutableSetOf.contains(sha256hash)) {
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    if (strArr.length == 0) {
                        sb.append(sha256hash);
                    } else if (strArr.length < 5) {
                        sb.append(str2);
                        sb.append(DATA_SEPARATOR);
                        sb.append(sha256hash);
                    } else {
                        while (true) {
                            int i3 = i + 1;
                            sb.append(strArr[i]);
                            sb.append(DATA_SEPARATOR);
                            if (i3 >= 5) {
                                break;
                            } else {
                                i = i3;
                            }
                        }
                        sb.append(sha256hash);
                        mutableSetOf.remove(strArr[0]);
                    }
                    internalHashedUserData.put(key, sb.toString());
                } else {
                    concurrentHashMap.put(key, sha256hash);
                }
            }
            UserDataStore userDataStore2 = INSTANCE;
            Utility utility2 = Utility.INSTANCE;
            userDataStore2.writeDataIntoCache(INTERNAL_USER_DATA_KEY, Utility.mapToJsonStr(internalHashedUserData));
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, UserDataStore.class);
        }
    }

    private final boolean maybeSHA256Hashed(String data) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            return new Regex("[A-Fa-f0-9]{64}").matches(data);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final String normalizeData(String r9, String data) {
        String str;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            String str2 = data;
            int length = str2.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = Intrinsics.compare((int) str2.charAt(!z ? i : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    }
                    length--;
                } else if (z2) {
                    i++;
                } else {
                    z = true;
                }
            }
            String obj = str2.subSequence(i, length + 1).toString();
            if (obj != null) {
                String lowerCase = obj.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
                if (Intrinsics.areEqual(EMAIL, r9)) {
                    if (Patterns.EMAIL_ADDRESS.matcher(lowerCase).matches()) {
                        return lowerCase;
                    }
                    Log.e(TAG, "Setting email failure: this is not a valid email address");
                    return "";
                }
                if (Intrinsics.areEqual(PHONE, r9)) {
                    return new Regex("[^0-9]").replace(lowerCase, "");
                }
                if (!Intrinsics.areEqual(GENDER, r9)) {
                    return lowerCase;
                }
                if (!(lowerCase.length() > 0)) {
                    str = "";
                } else {
                    if (lowerCase == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    str = lowerCase.substring(0, 1);
                    Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
                if (!Intrinsics.areEqual("f", str) && !Intrinsics.areEqual("m", str)) {
                    Log.e(TAG, "Setting gender failure: the supported value for gender is f or m");
                    return "";
                }
                return str;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
