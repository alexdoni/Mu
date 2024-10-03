package com.facebook.appevents;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.internal.AppEventUtility;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: AnalyticsUserIDStore.kt */
@Metadata(m1394d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0007J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0007J\u0012\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1395d2 = {"Lcom/facebook/appevents/AnalyticsUserIDStore;", "", "()V", "ANALYTICS_USER_ID_KEY", "", "TAG", "kotlin.jvm.PlatformType", "initialized", "", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "userID", "getUserID", "initAndWait", "", "initStore", "setUserID", "id", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AnalyticsUserIDStore {
    private static final String ANALYTICS_USER_ID_KEY = "com.facebook.appevents.AnalyticsUserIDStore.userID";
    private static volatile boolean initialized;
    private static String userID;
    public static final AnalyticsUserIDStore INSTANCE = new AnalyticsUserIDStore();
    private static final String TAG = "AnalyticsUserIDStore";
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private AnalyticsUserIDStore() {
    }

    @JvmStatic
    public static final void initStore() {
        if (initialized) {
            return;
        }
        InternalAppEventsLogger.INSTANCE.getAnalyticsExecutor().execute(new Runnable() { // from class: com.facebook.appevents.AnalyticsUserIDStore$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AnalyticsUserIDStore.m1642initStore$lambda0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initStore$lambda-0, reason: not valid java name */
    public static final void m1642initStore$lambda0() {
        INSTANCE.initAndWait();
    }

    @JvmStatic
    public static final void setUserID(final String id) {
        AppEventUtility appEventUtility = AppEventUtility.INSTANCE;
        AppEventUtility.assertIsNotMainThread();
        if (!initialized) {
            Log.w(TAG, "initStore should have been called before calling setUserID");
            INSTANCE.initAndWait();
        }
        InternalAppEventsLogger.INSTANCE.getAnalyticsExecutor().execute(new Runnable() { // from class: com.facebook.appevents.AnalyticsUserIDStore$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AnalyticsUserIDStore.m1643setUserID$lambda1(id);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setUserID$lambda-1, reason: not valid java name */
    public static final void m1643setUserID$lambda1(String str) {
        ReentrantReadWriteLock reentrantReadWriteLock = lock;
        reentrantReadWriteLock.writeLock().lock();
        try {
            userID = str;
            FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
            edit.putString(ANALYTICS_USER_ID_KEY, userID);
            edit.apply();
            reentrantReadWriteLock.writeLock().unlock();
        } catch (Throwable th) {
            lock.writeLock().unlock();
            throw th;
        }
    }

    @JvmStatic
    public static final String getUserID() {
        if (!initialized) {
            Log.w(TAG, "initStore should have been called before calling setUserID");
            INSTANCE.initAndWait();
        }
        ReentrantReadWriteLock reentrantReadWriteLock = lock;
        reentrantReadWriteLock.readLock().lock();
        try {
            String str = userID;
            reentrantReadWriteLock.readLock().unlock();
            return str;
        } catch (Throwable th) {
            lock.readLock().unlock();
            throw th;
        }
    }

    private final void initAndWait() {
        if (initialized) {
            return;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = lock;
        reentrantReadWriteLock.writeLock().lock();
        try {
            if (!initialized) {
                FacebookSdk facebookSdk = FacebookSdk.INSTANCE;
                userID = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).getString(ANALYTICS_USER_ID_KEY, null);
                initialized = true;
                reentrantReadWriteLock.writeLock().unlock();
                return;
            }
            reentrantReadWriteLock.writeLock().unlock();
        } catch (Throwable th) {
            lock.writeLock().unlock();
            throw th;
        }
    }
}
