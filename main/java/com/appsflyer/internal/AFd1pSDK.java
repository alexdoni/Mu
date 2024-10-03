package com.appsflyer.internal;

import android.content.SharedPreferences;
import com.appsflyer.AFLogger;

/* loaded from: classes.dex */
public final class AFd1pSDK implements AFd1tSDK {
    private final SharedPreferences AFInAppEventParameterName;

    public AFd1pSDK(SharedPreferences sharedPreferences) {
        this.AFInAppEventParameterName = sharedPreferences;
    }

    @Override // com.appsflyer.internal.AFd1tSDK
    public final void valueOf(String str, String str2) {
        this.AFInAppEventParameterName.edit().putString(str, str2).apply();
    }

    @Override // com.appsflyer.internal.AFd1tSDK
    public final String AFKeystoreWrapper(String str, String str2) {
        try {
            return this.AFInAppEventParameterName.getString(str, str2);
        } catch (ClassCastException e) {
            AFLogger.afErrorLog("Unexpected data type found for key ".concat(String.valueOf(str)), e);
            return str2;
        }
    }

    @Override // com.appsflyer.internal.AFd1tSDK
    public final boolean values(String str) {
        try {
            return this.AFInAppEventParameterName.getBoolean(str, false);
        } catch (ClassCastException e) {
            AFLogger.afErrorLog("Unexpected data type found for key ".concat(String.valueOf(str)), e);
            return false;
        }
    }

    @Override // com.appsflyer.internal.AFd1tSDK
    public final void values(String str, boolean z) {
        this.AFInAppEventParameterName.edit().putBoolean(str, z).apply();
    }

    @Override // com.appsflyer.internal.AFd1tSDK
    public final long AFKeystoreWrapper(String str, long j) {
        try {
            return this.AFInAppEventParameterName.getLong(str, j);
        } catch (ClassCastException e) {
            AFLogger.afErrorLog("Unexpected data type found for key ".concat(String.valueOf(str)), e);
            return j;
        }
    }

    @Override // com.appsflyer.internal.AFd1tSDK
    public final void AFInAppEventParameterName(String str, long j) {
        this.AFInAppEventParameterName.edit().putLong(str, j).apply();
    }

    @Override // com.appsflyer.internal.AFd1tSDK
    public final void values(String str, int i) {
        this.AFInAppEventParameterName.edit().putInt(str, i).apply();
    }

    @Override // com.appsflyer.internal.AFd1tSDK
    public final int AFInAppEventParameterName(String str, int i) {
        try {
            return this.AFInAppEventParameterName.getInt(str, i);
        } catch (ClassCastException e) {
            AFLogger.afErrorLog("Unexpected data type found for key ".concat(String.valueOf(str)), e);
            return i;
        }
    }

    @Override // com.appsflyer.internal.AFd1tSDK
    public final boolean valueOf(String str) {
        return this.AFInAppEventParameterName.contains(str);
    }

    @Override // com.appsflyer.internal.AFd1tSDK
    public final void AFInAppEventType(String str) {
        this.AFInAppEventParameterName.edit().remove(str).apply();
    }
}
