package com.appsflyer.internal;

import com.appsflyer.AppsFlyerConsent;
import com.appsflyer.AppsFlyerProperties;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFg1uSDK implements AFg1vSDK {
    private final AFd1qSDK AFInAppEventParameterName;
    private final AFg1sSDK valueOf;
    private final AppsFlyerProperties values;

    public AFg1uSDK(AFg1sSDK aFg1sSDK, AFd1qSDK aFd1qSDK, AppsFlyerProperties appsFlyerProperties) {
        Intrinsics.checkNotNullParameter(aFg1sSDK, "");
        Intrinsics.checkNotNullParameter(aFd1qSDK, "");
        Intrinsics.checkNotNullParameter(appsFlyerProperties, "");
        this.valueOf = aFg1sSDK;
        this.AFInAppEventParameterName = aFd1qSDK;
        this.values = appsFlyerProperties;
    }

    @Override // com.appsflyer.internal.AFg1vSDK
    public final void AFInAppEventParameterName(Map<String, Object> map, AFf1zSDK aFf1zSDK) {
        Intrinsics.checkNotNullParameter(map, "");
        Intrinsics.checkNotNullParameter(aFf1zSDK, "");
        AFg1tSDK AFInAppEventType = this.valueOf.AFInAppEventType();
        AppsFlyerConsent appsFlyerConsent = this.AFInAppEventParameterName.f188d;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (appsFlyerConsent != null) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put("gdpr_applies", Boolean.valueOf(appsFlyerConsent.isUserSubjectToGDPR()));
            Boolean hasConsentForDataUsage = appsFlyerConsent.getHasConsentForDataUsage();
            if (hasConsentForDataUsage != null) {
                linkedHashMap2.put("ad_user_data_enabled", Boolean.valueOf(hasConsentForDataUsage.booleanValue()));
            }
            Boolean hasConsentForAdsPersonalization = appsFlyerConsent.getHasConsentForAdsPersonalization();
            if (hasConsentForAdsPersonalization != null) {
                linkedHashMap2.put("ad_personalization_enabled", Boolean.valueOf(hasConsentForAdsPersonalization.booleanValue()));
            }
            linkedHashMap.put("manual", linkedHashMap2);
        }
        if (AFInAppEventType != null) {
            boolean z = appsFlyerConsent != null;
            LinkedHashMap linkedHashMap3 = new LinkedHashMap();
            linkedHashMap3.put("policy_version", Integer.valueOf(AFInAppEventType.valueOf));
            linkedHashMap3.put("cmp_sdk_id", Integer.valueOf(AFInAppEventType.values));
            linkedHashMap3.put("cmp_sdk_version", Integer.valueOf(AFInAppEventType.AFInAppEventParameterName));
            if (z) {
                linkedHashMap3.put("gdpr_applies", -1);
                linkedHashMap3.put("tcstring", "");
            } else {
                linkedHashMap3.put("gdpr_applies", Integer.valueOf(AFInAppEventType.AFKeystoreWrapper));
                linkedHashMap3.put("tcstring", AFInAppEventType.AFInAppEventType);
            }
            linkedHashMap.put("tcf", linkedHashMap3);
        }
        if (!linkedHashMap.isEmpty()) {
            map.put("consent_data", linkedHashMap);
        }
        if (!(aFf1zSDK == AFf1zSDK.CONVERSION) || this.values.getString(AppsFlyerProperties.ENABLE_TCF_DATA_COLLECTION) == null) {
            return;
        }
        Map<String, Object> values = AFb1tSDK.values(map);
        Intrinsics.checkNotNullExpressionValue(values, "");
        values.put("api", MapsKt.mapOf(TuplesKt.m1402to(AppsFlyerProperties.ENABLE_TCF_DATA_COLLECTION, this.values.getString(AppsFlyerProperties.ENABLE_TCF_DATA_COLLECTION))));
    }
}
