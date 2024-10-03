package com.oversea.ab_firstarea.utils;

import com.oversea.ab_firstarea.callback.HeartArrivedCallback;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class HeartPacketUtil {
    private static volatile HeartPacketUtil instance;
    private HeartArrivedCallback callback;
    private int durationNum;
    private ScheduledExecutorService executor;
    private Runnable runnable = new Runnable() { // from class: com.oversea.ab_firstarea.utils.HeartPacketUtil.1
        @Override // java.lang.Runnable
        public void run() {
            HeartPacketUtil.access$008(HeartPacketUtil.this);
            if (HeartPacketUtil.this.callback != null) {
                HeartPacketUtil.this.callback.onHeartArrived(HeartPacketUtil.this.durationNum);
            }
        }
    };

    static /* synthetic */ int access$008(HeartPacketUtil heartPacketUtil) {
        int i = heartPacketUtil.durationNum;
        heartPacketUtil.durationNum = i + 1;
        return i;
    }

    public static HeartPacketUtil getInstance() {
        if (instance == null) {
            synchronized (HeartPacketUtil.class) {
                if (instance == null) {
                    instance = new HeartPacketUtil();
                }
            }
        }
        return instance;
    }

    public void scheduleRequest(long j, long j2, HeartArrivedCallback heartArrivedCallback) {
        if (j2 == 0) {
            return;
        }
        this.durationNum = 0;
        this.callback = heartArrivedCallback;
        stop();
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
        this.executor = newScheduledThreadPool;
        newScheduledThreadPool.scheduleAtFixedRate(this.runnable, j, j2, TimeUnit.MINUTES);
    }

    public void stop() {
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            this.executor = null;
            this.durationNum = 0;
        }
    }
}
