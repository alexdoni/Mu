package com.oversea.ab_firstarea.haiwai;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.EnumMap;

/* loaded from: classes.dex */
public class FirebaseControl {
    private static FirebaseControl instance;
    private String appInstanceID;
    private String firebase_msg_token = "";
    private FirebaseAnalytics mFirebaseAnalytics;

    public static FirebaseControl getInstance() {
        if (instance == null) {
            instance = new FirebaseControl();
        }
        return instance;
    }

    public void initFireBase(Context context) {
        try {
            this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
            setCrashlyticsCustomKey("android_id", Util.getAndroidID(context));
            setCrashlyticsUserId(Util.getAndroidID(context));
            String channelName = getChannelName(context);
            if (TextUtils.isEmpty(channelName)) {
                return;
            }
            setUserProperty("app_store", channelName);
        } catch (Throwable th) {
            LLog.e_Control("firebase initFireBase=" + th.toString());
        }
    }

    public void setUserId(String str) {
        try {
            if (this.mFirebaseAnalytics == null || TextUtils.isEmpty(str)) {
                return;
            }
            this.mFirebaseAnalytics.setUserId(str);
        } catch (Throwable unused) {
        }
    }

    public void setUserProperty(String str, String str2) {
        FirebaseAnalytics firebaseAnalytics = this.mFirebaseAnalytics;
        if (firebaseAnalytics != null) {
            firebaseAnalytics.setUserProperty(str, str2);
        }
    }

    public void setCrashlyticsCustomKey(String str, String str2) {
        FirebaseCrashlytics.getInstance().setCustomKey(str, str2);
    }

    public void setCrashlyticsUserId(String str) {
        FirebaseCrashlytics.getInstance().setUserId(str);
    }

    public void setLogEvent(String str, Bundle bundle) {
        FirebaseAnalytics firebaseAnalytics = this.mFirebaseAnalytics;
        if (firebaseAnalytics != null && bundle != null) {
            firebaseAnalytics.logEvent(str, bundle);
        } else {
            LLog.e_noControl("firebase  mFirebaseAnalytics or bundle null");
        }
    }

    public void setConsent(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mFirebaseAnalytics != null) {
            LLog.i_Control("firebase setConsent analytics_storage=" + z + " ad_storage=" + z2 + " ad_user_data=" + z3 + " ad_personalization_signals=" + z4);
            EnumMap enumMap = new EnumMap(FirebaseAnalytics.ConsentType.class);
            if (z) {
                enumMap.put((EnumMap) FirebaseAnalytics.ConsentType.ANALYTICS_STORAGE, (FirebaseAnalytics.ConsentType) FirebaseAnalytics.ConsentStatus.GRANTED);
            } else {
                enumMap.put((EnumMap) FirebaseAnalytics.ConsentType.ANALYTICS_STORAGE, (FirebaseAnalytics.ConsentType) FirebaseAnalytics.ConsentStatus.DENIED);
            }
            if (z2) {
                enumMap.put((EnumMap) FirebaseAnalytics.ConsentType.AD_STORAGE, (FirebaseAnalytics.ConsentType) FirebaseAnalytics.ConsentStatus.GRANTED);
            } else {
                enumMap.put((EnumMap) FirebaseAnalytics.ConsentType.AD_STORAGE, (FirebaseAnalytics.ConsentType) FirebaseAnalytics.ConsentStatus.DENIED);
            }
            if (z3) {
                enumMap.put((EnumMap) FirebaseAnalytics.ConsentType.AD_USER_DATA, (FirebaseAnalytics.ConsentType) FirebaseAnalytics.ConsentStatus.GRANTED);
            } else {
                enumMap.put((EnumMap) FirebaseAnalytics.ConsentType.AD_USER_DATA, (FirebaseAnalytics.ConsentType) FirebaseAnalytics.ConsentStatus.DENIED);
            }
            if (z4) {
                enumMap.put((EnumMap) FirebaseAnalytics.ConsentType.AD_PERSONALIZATION, (FirebaseAnalytics.ConsentType) FirebaseAnalytics.ConsentStatus.GRANTED);
            } else {
                enumMap.put((EnumMap) FirebaseAnalytics.ConsentType.AD_PERSONALIZATION, (FirebaseAnalytics.ConsentType) FirebaseAnalytics.ConsentStatus.DENIED);
            }
            this.mFirebaseAnalytics.setConsent(enumMap);
        }
    }

    public String getAppInstanceID() {
        if (TextUtils.isEmpty(this.appInstanceID)) {
            try {
                this.mFirebaseAnalytics.getAppInstanceId().addOnCompleteListener(new OnCompleteListener<String>() { // from class: com.oversea.ab_firstarea.haiwai.FirebaseControl.1
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public void onComplete(Task<String> task) {
                        if (task == null || !task.isSuccessful()) {
                            return;
                        }
                        FirebaseControl.this.appInstanceID = task.getResult();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String str = this.appInstanceID;
        return str == null ? "" : str;
    }

    public String getMsgToken() {
        if (TextUtils.isEmpty(this.firebase_msg_token)) {
            try {
                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() { // from class: com.oversea.ab_firstarea.haiwai.FirebaseControl.2
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public void onComplete(Task<String> task) {
                        if (!task.isSuccessful()) {
                            LLog.e_Control("firebase Fetching FCM registration token failed: " + task.getException());
                        } else {
                            FirebaseControl.this.firebase_msg_token = task.getResult();
                            LLog.e_Control("firebase firebase_msg_token=" + FirebaseControl.this.firebase_msg_token);
                        }
                    }
                });
            } catch (Exception unused) {
            }
        }
        if (TextUtils.isEmpty(this.firebase_msg_token)) {
            this.firebase_msg_token = "";
        }
        return this.firebase_msg_token;
    }

    private String getChannelName(Context context) {
        if (context == null) {
            return "";
        }
        String metaData = ImageUtils.getMetaData(context, "AF_STORE");
        Log.i("X_LOG", "channel:" + metaData);
        return TextUtils.isEmpty(metaData) ? "" : metaData;
    }
}
