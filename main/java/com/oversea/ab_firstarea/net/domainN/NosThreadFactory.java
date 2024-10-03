package com.oversea.ab_firstarea.net.domainN;

import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.lang.Thread;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class NosThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final ThreadGroup group = Thread.currentThread().getThreadGroup();
    private final String namePrefix = "NOS task pool No." + poolNumber.getAndIncrement() + ", nosThread No.";

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        final String str = this.namePrefix + this.threadNumber.getAndIncrement();
        LLog.i_Control("X_THREAD", "NOS engine production, name is [" + str + "]");
        Thread thread = new Thread(this.group, runnable, str, 0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.oversea.ab_firstarea.net.domainN.NosThreadFactory.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread2, Throwable th) {
                LLog.e_Control("X_THREAD", "NOS engine production, Throwable name is [" + str + "]");
            }
        });
        return thread;
    }
}
