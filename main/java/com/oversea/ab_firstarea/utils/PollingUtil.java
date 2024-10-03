package com.oversea.ab_firstarea.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class PollingUtil {
    private static volatile PollingUtil instance;
    private Context context;
    private TaskHandler mHanlder;
    private Map<Runnable, Runnable> mTaskMap = new HashMap();

    public static PollingUtil getInstance() {
        if (instance == null) {
            synchronized (PollingUtil.class) {
                if (instance == null) {
                    instance = new PollingUtil();
                }
            }
        }
        return instance;
    }

    public PollingUtil init(Context context) {
        if (context == null) {
            try {
                throw new NoSuchFieldException("PollingUtil init, context can not be null");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        this.context = context;
        this.mHanlder = new TaskHandler(context.getMainLooper());
        return instance;
    }

    public PollingUtil startPolling(long j, Runnable runnable) {
        startPolling(j, false, runnable);
        return instance;
    }

    public PollingUtil startPolling(final long j, boolean z, final Runnable runnable) {
        if (runnable == null) {
            try {
                throw new NoSuchFieldException("runnable can not be null");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        if (z) {
            runnable.run();
        }
        if (this.mTaskMap.get(runnable) == null) {
            this.mTaskMap.put(runnable, new Runnable() { // from class: com.oversea.ab_firstarea.utils.PollingUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    runnable.run();
                    PollingUtil.this.post(runnable, j);
                }
            });
        }
        post(runnable, j);
        return instance;
    }

    public void endPolling(Runnable runnable) {
        if (this.mTaskMap.containsKey(runnable)) {
            this.mHanlder.removeCallbacks(this.mTaskMap.get(runnable));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void post(Runnable runnable, long j) {
        Runnable runnable2 = this.mTaskMap.get(runnable);
        this.mHanlder.removeCallbacks(runnable2);
        this.mHanlder.postDelayed(runnable2, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class TaskHandler extends Handler {
        public TaskHandler(Looper looper) {
            super(looper);
        }
    }
}
