package com.facebook.appevents.ondeviceprocessing;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.app.NotificationCompat;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEvent;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnDeviceProcessingManager.kt */
@Metadata(m1394d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\u0007H\u0007J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0007J\u001c\u0010\u000e\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m1395d2 = {"Lcom/facebook/appevents/ondeviceprocessing/OnDeviceProcessingManager;", "", "()V", "ALLOWED_IMPLICIT_EVENTS", "", "", "isEventEligibleForOnDeviceProcessing", "", NotificationCompat.CATEGORY_EVENT, "Lcom/facebook/appevents/AppEvent;", "isOnDeviceProcessingEnabled", "sendCustomEventAsync", "", "applicationId", "sendInstallEventAsync", "preferencesName", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class OnDeviceProcessingManager {
    public static final OnDeviceProcessingManager INSTANCE = new OnDeviceProcessingManager();
    private static final Set<String> ALLOWED_IMPLICIT_EVENTS = SetsKt.setOf((Object[]) new String[]{AppEventsConstants.EVENT_NAME_PURCHASED, AppEventsConstants.EVENT_NAME_START_TRIAL, AppEventsConstants.EVENT_NAME_SUBSCRIBE});

    private OnDeviceProcessingManager() {
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0026 A[Catch: all -> 0x0030, TRY_LEAVE, TryCatch #0 {all -> 0x0030, blocks: (B:6:0x000a, B:8:0x0019, B:12:0x0026), top: B:5:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean isOnDeviceProcessingEnabled() {
        /*
            java.lang.Class<com.facebook.appevents.ondeviceprocessing.OnDeviceProcessingManager> r0 = com.facebook.appevents.ondeviceprocessing.OnDeviceProcessingManager.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            r2 = 0
            if (r1 == 0) goto La
            return r2
        La:
            com.facebook.FacebookSdk r1 = com.facebook.FacebookSdk.INSTANCE     // Catch: java.lang.Throwable -> L30
            android.content.Context r1 = com.facebook.FacebookSdk.getApplicationContext()     // Catch: java.lang.Throwable -> L30
            com.facebook.FacebookSdk r3 = com.facebook.FacebookSdk.INSTANCE     // Catch: java.lang.Throwable -> L30
            boolean r1 = com.facebook.FacebookSdk.getLimitEventAndDataUsage(r1)     // Catch: java.lang.Throwable -> L30
            r3 = 1
            if (r1 != 0) goto L23
            com.facebook.internal.Utility r1 = com.facebook.internal.Utility.INSTANCE     // Catch: java.lang.Throwable -> L30
            boolean r1 = com.facebook.internal.Utility.isDataProcessingRestricted()     // Catch: java.lang.Throwable -> L30
            if (r1 != 0) goto L23
            r1 = r3
            goto L24
        L23:
            r1 = r2
        L24:
            if (r1 == 0) goto L2f
            com.facebook.appevents.ondeviceprocessing.RemoteServiceWrapper r1 = com.facebook.appevents.ondeviceprocessing.RemoteServiceWrapper.INSTANCE     // Catch: java.lang.Throwable -> L30
            boolean r0 = com.facebook.appevents.ondeviceprocessing.RemoteServiceWrapper.isServiceAvailable()     // Catch: java.lang.Throwable -> L30
            if (r0 == 0) goto L2f
            r2 = r3
        L2f:
            return r2
        L30:
            r1 = move-exception
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r1, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.ondeviceprocessing.OnDeviceProcessingManager.isOnDeviceProcessingEnabled():boolean");
    }

    @JvmStatic
    public static final void sendInstallEventAsync(final String applicationId, final String preferencesName) {
        if (CrashShieldHandler.isObjectCrashing(OnDeviceProcessingManager.class)) {
            return;
        }
        try {
            FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
            final Context applicationContext = FacebookSdk.getApplicationContext();
            if (applicationContext == null || applicationId == null || preferencesName == null) {
                return;
            }
            FacebookSdk facebookSdk2 = FacebookSdk.INSTANCE;
            FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.appevents.ondeviceprocessing.OnDeviceProcessingManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OnDeviceProcessingManager.m1709sendInstallEventAsync$lambda0(applicationContext, preferencesName, applicationId);
                }
            });
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, OnDeviceProcessingManager.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendInstallEventAsync$lambda-0, reason: not valid java name */
    public static final void m1709sendInstallEventAsync$lambda0(Context context, String str, String str2) {
        if (CrashShieldHandler.isObjectCrashing(OnDeviceProcessingManager.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(context, "$context");
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            String stringPlus = Intrinsics.stringPlus(str2, "pingForOnDevice");
            if (sharedPreferences.getLong(stringPlus, 0L) == 0) {
                RemoteServiceWrapper remoteServiceWrapper = RemoteServiceWrapper.INSTANCE;
                RemoteServiceWrapper.sendInstallEvent(str2);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putLong(stringPlus, System.currentTimeMillis());
                edit.apply();
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, OnDeviceProcessingManager.class);
        }
    }

    @JvmStatic
    public static final void sendCustomEventAsync(final String applicationId, final AppEvent event) {
        if (CrashShieldHandler.isObjectCrashing(OnDeviceProcessingManager.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(applicationId, "applicationId");
            Intrinsics.checkNotNullParameter(event, "event");
            if (INSTANCE.isEventEligibleForOnDeviceProcessing(event)) {
                FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
                FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.appevents.ondeviceprocessing.OnDeviceProcessingManager$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        OnDeviceProcessingManager.m1708sendCustomEventAsync$lambda1(applicationId, event);
                    }
                });
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, OnDeviceProcessingManager.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendCustomEventAsync$lambda-1, reason: not valid java name */
    public static final void m1708sendCustomEventAsync$lambda1(String applicationId, AppEvent event) {
        if (CrashShieldHandler.isObjectCrashing(OnDeviceProcessingManager.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(applicationId, "$applicationId");
            Intrinsics.checkNotNullParameter(event, "$event");
            RemoteServiceWrapper remoteServiceWrapper = RemoteServiceWrapper.INSTANCE;
            RemoteServiceWrapper.sendCustomEvents(applicationId, CollectionsKt.listOf(event));
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, OnDeviceProcessingManager.class);
        }
    }

    private final boolean isEventEligibleForOnDeviceProcessing(AppEvent event) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            return (event.isImplicit() ^ true) || (event.isImplicit() && ALLOWED_IMPLICIT_EVENTS.contains(event.getName()));
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }
}
