package com.facebook.appevents.aam;

import android.app.Activity;
import com.facebook.FacebookSdk;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MetadataIndexer.kt */
@Metadata(m1394d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\tH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m1395d2 = {"Lcom/facebook/appevents/aam/MetadataIndexer;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "enabled", "", "enable", "", "onActivityResumed", "activity", "Landroid/app/Activity;", "updateRules", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class MetadataIndexer {
    public static final MetadataIndexer INSTANCE = new MetadataIndexer();
    private static final String TAG = MetadataIndexer.class.getCanonicalName();
    private static boolean enabled;

    private MetadataIndexer() {
    }

    @JvmStatic
    public static final void onActivityResumed(Activity activity) {
        if (CrashShieldHandler.isObjectCrashing(MetadataIndexer.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(activity, "activity");
            try {
                if (enabled && !MetadataRule.INSTANCE.getRules().isEmpty()) {
                    MetadataViewObserver.INSTANCE.startTrackingActivity(activity);
                }
            } catch (Exception unused) {
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, MetadataIndexer.class);
        }
    }

    private final void updateRules() {
        String rawAamRules;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            FetchedAppSettingsManager fetchedAppSettingsManager = FetchedAppSettingsManager.INSTANCE;
            FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
            FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
            if (queryAppSettings == null || (rawAamRules = queryAppSettings.getRawAamRules()) == null) {
                return;
            }
            MetadataRule.INSTANCE.updateRules(rawAamRules);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    @JvmStatic
    public static final void enable() {
        if (CrashShieldHandler.isObjectCrashing(MetadataIndexer.class)) {
            return;
        }
        try {
            try {
                FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
                FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.appevents.aam.MetadataIndexer$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        MetadataIndexer.m1667enable$lambda0();
                    }
                });
            } catch (Exception e) {
                Utility utility = Utility.INSTANCE;
                Utility.logd(TAG, e);
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, MetadataIndexer.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: enable$lambda-0, reason: not valid java name */
    public static final void m1667enable$lambda0() {
        if (CrashShieldHandler.isObjectCrashing(MetadataIndexer.class)) {
            return;
        }
        try {
            FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
            if (AttributionIdentifiers.INSTANCE.isTrackingLimited(FacebookSdk.getApplicationContext())) {
                return;
            }
            INSTANCE.updateRules();
            enabled = true;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, MetadataIndexer.class);
        }
    }
}
