package com.oversea.ab_firstarea.net.domainN;

import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class NosExecutor extends ThreadPoolExecutor {
    private static final int capacity = 20;
    private static final int corePoolSize = 1;
    private static final long keepAliveTime = 60;
    private static final int maximumPoolSize = 2;
    private static volatile NosExecutor nosExecutor;
    private static final TimeUnit unit = TimeUnit.SECONDS;

    private NosExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
        allowsCoreThreadTimeOut();
    }

    public static NosExecutor getNOSExecutor() {
        if (nosExecutor == null) {
            synchronized (NosExecutor.class) {
                if (nosExecutor == null) {
                    nosExecutor = new NosExecutor(1, 2, keepAliveTime, unit, new LinkedBlockingQueue(20), new NosThreadFactory(), new ThreadPoolExecutor.AbortPolicy() { // from class: com.oversea.ab_firstarea.net.domainN.NosExecutor.1
                        @Override // java.util.concurrent.ThreadPoolExecutor.AbortPolicy, java.util.concurrent.RejectedExecutionHandler
                        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                            LLog.e_Control("X_THREAD", "Task " + runnable.toString() + " rejected from " + threadPoolExecutor.toString());
                        }
                    });
                }
            }
        }
        return nosExecutor;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        try {
            super.execute(runnable);
        } catch (Throwable th) {
            LLog.e_Control("X_THREAD", th.getMessage());
        }
    }
}
