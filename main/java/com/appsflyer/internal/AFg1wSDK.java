package com.appsflyer.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFg1wSDK implements AFg1sSDK {
    private final AppsFlyerProperties AFInAppEventParameterName;
    private final Context values;

    public AFg1wSDK(Context context, AppsFlyerProperties appsFlyerProperties) {
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(appsFlyerProperties, "");
        this.values = context;
        this.AFInAppEventParameterName = appsFlyerProperties;
    }

    @Override // com.appsflyer.internal.AFg1sSDK
    public final AFg1tSDK AFInAppEventType() {
        String string;
        if (!Boolean.parseBoolean(this.AFInAppEventParameterName.getString(AppsFlyerProperties.ENABLE_TCF_DATA_COLLECTION))) {
            return null;
        }
        try {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.values);
            int i = defaultSharedPreferences.getInt("IABTCF_gdprApplies", -1);
            int i2 = defaultSharedPreferences.getInt("IABTCF_CmpSdkID", -1);
            int i3 = defaultSharedPreferences.getInt("IABTCF_PolicyVersion", -1);
            int i4 = defaultSharedPreferences.getInt("IABTCF_CmpSdkVersion", -1);
            String str = "";
            if (i == 1 && (string = defaultSharedPreferences.getString("IABTCF_TCString", "")) != null) {
                Intrinsics.checkNotNullExpressionValue(string, "");
                str = string;
            }
            return new AFg1tSDK(i3, i, i2, i4, str);
        } catch (Exception e) {
            AFg1hSDK.e$default(AFLogger.INSTANCE, AFg1gSDK.DMA, "TCF data collection exception", e, false, false, false, false, 120, null);
            return null;
        }
    }
}
