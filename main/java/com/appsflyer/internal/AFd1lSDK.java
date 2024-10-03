package com.appsflyer.internal;

import android.content.Context;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.PurchaseHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFd1lSDK implements AFd1mSDK {
    private static final int values = (int) TimeUnit.SECONDS.toMillis(30);
    public final AFd1kSDK AFInAppEventParameterName = new AFd1kSDK();
    private ExecutorService AFInAppEventType;
    private ScheduledExecutorService AFKeystoreWrapper;
    private AFd1sSDK AFLogger;
    private AFh1aSDK AFLogger$LogLevel;
    private AFd1uSDK AFVersionDeclaration;
    private AFc1oSDK AppsFlyer2dXConversionCallback;
    private AFi1qSDK afDebugLog;
    private AFg1xSDK afErrorLog;
    private AFd1xSDK afErrorLogForExcManagerOnly;
    private AFe1fSDK afInfoLog;
    private AFc1hSDK afLogForce;
    private AFd1hSDK afRDLog;
    private AFb1vSDK afVerboseLog;
    private AFe1gSDK afWarnLog;

    /* renamed from: d */
    private AFe1vSDK f183d;

    /* renamed from: e */
    private AFf1bSDK f184e;
    private AFg1qSDK force;
    private AFc1uSDK getLevel;

    /* renamed from: i */
    private AFb1bSDK f185i;
    private AFg1fSDK init;
    private AFi1ySDK onAppOpenAttributionNative;
    private AFg1pSDK onDeepLinkingNative;
    private AFb1ySDK onInstallConversionDataLoadedNative;
    private AFg1vSDK onInstallConversionFailureNative;
    private AFd1qSDK onResponseNative;
    private AFg1cSDK registerClient;
    private PurchaseHandler unregisterClient;

    /* renamed from: v */
    private AFi1iSDK f186v;
    private ExecutorService valueOf;

    /* renamed from: w */
    private AFi1dSDK f187w;

    @Override // com.appsflyer.internal.AFd1mSDK
    public final AFe1wSDK AFKeystoreWrapper() {
        return new AFe1wSDK(init(), AFInAppEventType(), AppsFlyerProperties.getInstance(), afVerboseLog());
    }

    private synchronized AFe1vSDK init() {
        if (this.f183d == null) {
            this.f183d = new AFe1vSDK(new AFe1sSDK(values), AFInAppEventParameterName());
        }
        return this.f183d;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final synchronized ExecutorService AFInAppEventParameterName() {
        if (this.AFInAppEventType == null) {
            this.AFInAppEventType = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue());
        }
        return this.AFInAppEventType;
    }

    private synchronized ExecutorService onInstallConversionDataLoadedNative() {
        if (this.valueOf == null) {
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            Intrinsics.checkNotNullExpressionValue(newSingleThreadExecutor, "");
            this.valueOf = newSingleThreadExecutor;
        }
        return this.valueOf;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final synchronized ScheduledExecutorService valueOf() {
        if (this.AFKeystoreWrapper == null) {
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
            Intrinsics.checkNotNullExpressionValue(newScheduledThreadPool, "");
            this.AFKeystoreWrapper = newScheduledThreadPool;
        }
        return this.AFKeystoreWrapper;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final synchronized AFd1sSDK AFInAppEventType() {
        if (this.AFLogger == null) {
            AFd1kSDK mo80w = mo80w();
            Context context = this.AFInAppEventParameterName.valueOf;
            if (context != null) {
                AFd1pSDK aFd1pSDK = new AFd1pSDK(AFb1tSDK.AFInAppEventType(context));
                if (this.onResponseNative == null) {
                    this.onResponseNative = new AFd1qSDK();
                }
                this.AFLogger = new AFd1sSDK(mo80w, aFd1pSDK, this.onResponseNative);
            } else {
                throw new IllegalStateException("Context must be set via setContext method before calling this dependency.");
            }
        }
        return this.AFLogger;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final AFd1tSDK values() {
        Context context = this.AFInAppEventParameterName.valueOf;
        if (context != null) {
            return new AFd1pSDK(AFb1tSDK.AFInAppEventType(context));
        }
        throw new IllegalStateException("Context must be set via setContext method before calling this dependency.");
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    /* renamed from: e */
    public final synchronized PurchaseHandler mo77e() {
        if (this.unregisterClient == null) {
            this.unregisterClient = new PurchaseHandler(this);
        }
        return this.unregisterClient;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    /* renamed from: d */
    public final synchronized AFf1bSDK mo76d() {
        if (this.f184e == null) {
            AFg1ySDK aFg1ySDK = new AFg1ySDK(values());
            this.f184e = new AFf1bSDK(new AFf1eSDK(), AFInAppEventType(), force(), aFg1ySDK, new AFe1wSDK(init(), AFInAppEventType(), AppsFlyerProperties.getInstance(), afVerboseLog()), new AFf1aSDK(AFInAppEventType(), aFg1ySDK), afInfoLog());
        }
        return this.f184e;
    }

    private synchronized AFg1pSDK onConversionDataSuccess() {
        if (this.onDeepLinkingNative == null) {
            this.onDeepLinkingNative = new AFg1pSDK(mo80w(), AFInAppEventType());
        }
        return this.onDeepLinkingNative;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final synchronized AFg1cSDK AFLogger() {
        if (this.registerClient == null) {
            this.registerClient = new AFg1cSDK(values());
        }
        return this.registerClient;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final AFg1qSDK unregisterClient() {
        if (this.force == null) {
            Context context = this.AFInAppEventParameterName.valueOf;
            if (context == null) {
                throw new IllegalStateException("Context must be set via setContext method before calling this dependency.");
            }
            if (this.afDebugLog == null) {
                this.afDebugLog = new AFi1pSDK();
            }
            AFi1qSDK aFi1qSDK = this.afDebugLog;
            if (this.AFVersionDeclaration == null) {
                this.AFVersionDeclaration = new AFa1tSDK();
            }
            AFd1uSDK aFd1uSDK = this.AFVersionDeclaration;
            if (this.f187w == null) {
                Context context2 = this.AFInAppEventParameterName.valueOf;
                if (context2 != null) {
                    this.f187w = new AFi1bSDK(context2, onInstallConversionDataLoadedNative());
                } else {
                    throw new IllegalStateException("Context must be set via setContext method before calling this dependency.");
                }
            }
            AFi1dSDK aFi1dSDK = this.f187w;
            if (this.onInstallConversionDataLoadedNative == null) {
                this.onInstallConversionDataLoadedNative = new AFa1aSDK();
            }
            AFb1ySDK aFb1ySDK = this.onInstallConversionDataLoadedNative;
            AFg1cSDK AFLogger = AFLogger();
            AFd1tSDK values2 = values();
            AFd1sSDK AFInAppEventType = AFInAppEventType();
            if (this.AFLogger$LogLevel == null) {
                Context context3 = this.AFInAppEventParameterName.valueOf;
                if (context3 != null) {
                    this.AFLogger$LogLevel = new AFh1aSDK(context3);
                } else {
                    throw new IllegalStateException("Context must be set via setContext method before calling this dependency.");
                }
            }
            AFh1aSDK aFh1aSDK = this.AFLogger$LogLevel;
            AFg1xSDK force = force();
            AFb1cSDK aFb1cSDK = new AFb1cSDK();
            AFd1kSDK mo80w = mo80w();
            AFg1pSDK onConversionDataSuccess = onConversionDataSuccess();
            if (this.onResponseNative == null) {
                this.onResponseNative = new AFd1qSDK();
            }
            this.force = new AFg1lSDK(context, aFi1qSDK, aFd1uSDK, aFi1dSDK, aFb1ySDK, AFLogger, values2, AFInAppEventType, aFh1aSDK, force, aFb1cSDK, mo80w, onConversionDataSuccess, this.onResponseNative);
        }
        return this.force;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final AFi1dSDK registerClient() {
        if (this.f187w == null) {
            Context context = this.AFInAppEventParameterName.valueOf;
            if (context != null) {
                this.f187w = new AFi1bSDK(context, onInstallConversionDataLoadedNative());
            } else {
                throw new IllegalStateException("Context must be set via setContext method before calling this dependency.");
            }
        }
        return this.f187w;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final synchronized AFe1fSDK afInfoLog() {
        if (this.afInfoLog == null) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 6, 300L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>() { // from class: com.appsflyer.internal.AFd1lSDK.1
                /* JADX INFO: Access modifiers changed from: private */
                @Override // java.util.concurrent.LinkedBlockingQueue, java.util.Queue, java.util.concurrent.BlockingQueue
                /* renamed from: AFKeystoreWrapper, reason: merged with bridge method [inline-methods] */
                public boolean offer(Runnable runnable) {
                    if (isEmpty()) {
                        return super.offer(runnable);
                    }
                    return false;
                }
            }, new AFa1vSDK());
            threadPoolExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() { // from class: com.appsflyer.internal.AFd1lSDK$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.RejectedExecutionHandler
                public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor2) {
                    AFd1lSDK.values(runnable, threadPoolExecutor2);
                }
            });
            this.afInfoLog = new AFe1fSDK(threadPoolExecutor);
        }
        return this.afInfoLog;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    /* renamed from: i */
    public final synchronized AFb1bSDK mo78i() {
        if (this.f185i == null) {
            this.f185i = new AFb1aSDK(this);
        }
        return this.f185i;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    /* renamed from: v */
    public final synchronized AFi1iSDK mo79v() {
        if (this.f186v == null) {
            this.f186v = new AFi1iSDK(this);
        }
        return this.f186v;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final synchronized AFg1xSDK force() {
        if (this.afErrorLog == null) {
            this.afErrorLog = new AFg1xSDK(mo80w(), new AFg1zSDK());
        }
        return this.afErrorLog;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    /* renamed from: w */
    public final synchronized AFd1kSDK mo80w() {
        return this.AFInAppEventParameterName;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final synchronized AFb1vSDK afRDLog() {
        if (this.afVerboseLog == null) {
            this.afVerboseLog = new AFb1iSDK(mo80w());
        }
        return this.afVerboseLog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.appsflyer.internal.AFd1mSDK
    /* renamed from: onResponseNative, reason: merged with bridge method [inline-methods] */
    public synchronized AFd1hSDK onAppOpenAttributionNative() {
        if (this.afRDLog == null) {
            this.afRDLog = new AFd1hSDK(this);
        }
        return this.afRDLog;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final synchronized AFe1gSDK afVerboseLog() {
        if (this.afWarnLog == null) {
            this.afWarnLog = new AFe1gSDK(AFInAppEventType(), values());
        }
        return this.afWarnLog;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final AFi1qSDK afDebugLog() {
        if (this.afDebugLog == null) {
            this.afDebugLog = new AFi1pSDK();
        }
        return this.afDebugLog;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final synchronized AFc1uSDK afWarnLog() {
        if (this.getLevel == null) {
            this.getLevel = new AFc1uSDK(this);
        }
        return this.getLevel;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final synchronized AFc1hSDK afErrorLog() {
        if (this.afLogForce == null) {
            this.afLogForce = new AFd1zSDK(mo80w());
        }
        return this.afLogForce;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final AFh1aSDK getLevel() {
        if (this.AFLogger$LogLevel == null) {
            Context context = this.AFInAppEventParameterName.valueOf;
            if (context != null) {
                this.AFLogger$LogLevel = new AFh1aSDK(context);
            } else {
                throw new IllegalStateException("Context must be set via setContext method before calling this dependency.");
            }
        }
        return this.AFLogger$LogLevel;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final AFi1ySDK AFVersionDeclaration() {
        if (this.onAppOpenAttributionNative == null) {
            this.onAppOpenAttributionNative = new AFi1xSDK();
        }
        return this.onAppOpenAttributionNative;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final AFd1qSDK afErrorLogForExcManagerOnly() {
        if (this.onResponseNative == null) {
            this.onResponseNative = new AFd1qSDK();
        }
        return this.onResponseNative;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final AFd1xSDK AFLogger$LogLevel() {
        if (this.afErrorLogForExcManagerOnly == null) {
            ExecutorService onInstallConversionDataLoadedNative = onInstallConversionDataLoadedNative();
            ScheduledExecutorService valueOf = valueOf();
            AFc1uSDK afWarnLog = afWarnLog();
            if (this.onAppOpenAttributionNative == null) {
                this.onAppOpenAttributionNative = new AFi1xSDK();
            }
            this.afErrorLogForExcManagerOnly = new AFd1vSDK(onInstallConversionDataLoadedNative, valueOf, afWarnLog, this.onAppOpenAttributionNative);
        }
        return this.afErrorLogForExcManagerOnly;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final AFg1fSDK afLogForce() {
        if (this.init == null) {
            this.init = new AFh1ySDK(this);
        }
        return this.init;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final AFg1vSDK onInstallConversionFailureNative() {
        if (this.onInstallConversionFailureNative == null) {
            Context context = this.AFInAppEventParameterName.valueOf;
            if (context != null) {
                AFg1wSDK aFg1wSDK = new AFg1wSDK(context, AppsFlyerProperties.getInstance());
                if (this.onResponseNative == null) {
                    this.onResponseNative = new AFd1qSDK();
                }
                this.onInstallConversionFailureNative = new AFg1uSDK(aFg1wSDK, this.onResponseNative, AppsFlyerProperties.getInstance());
            } else {
                throw new IllegalStateException("Context must be set via setContext method before calling this dependency.");
            }
        }
        return this.onInstallConversionFailureNative;
    }

    @Override // com.appsflyer.internal.AFd1mSDK
    public final AFc1oSDK AppsFlyer2dXConversionCallback() {
        if (this.AppsFlyer2dXConversionCallback == null) {
            this.AppsFlyer2dXConversionCallback = new AFc1eSDK(values());
        }
        return this.AppsFlyer2dXConversionCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void values(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        try {
            threadPoolExecutor.getQueue().put(runnable);
        } catch (InterruptedException e) {
            AFLogger.afErrorLogForExcManagerOnly("could not create executor for queue", e);
            Thread.currentThread().interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class AFa1vSDK implements ThreadFactory {
        private static final AtomicInteger values = new AtomicInteger();
        private final AtomicInteger AFInAppEventType = new AtomicInteger();

        public AFa1vSDK() {
            values.incrementAndGet();
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            int i = values.get();
            int incrementAndGet = this.AFInAppEventType.incrementAndGet();
            StringBuilder sb = new StringBuilder("queue-");
            sb.append(i);
            sb.append("-");
            sb.append(incrementAndGet);
            return new Thread(runnable, sb.toString());
        }
    }
}
