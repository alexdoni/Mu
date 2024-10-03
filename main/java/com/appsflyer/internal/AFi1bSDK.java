package com.appsflyer.internal;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import com.appsflyer.AFLogger;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public final class AFi1bSDK implements AFi1dSDK {
    private static final BitSet AFKeystoreWrapper;
    private final Handler AFInAppEventParameterName;
    private final ExecutorService AFInAppEventType;
    private final Runnable AFLogger;
    private final Runnable afInfoLog;

    /* renamed from: d */
    private boolean f278d;

    /* renamed from: e */
    private final Map<AFi1fSDK, Map<String, Object>> f279e;

    /* renamed from: i */
    private final Runnable f280i;
    private final Map<AFi1fSDK, AFi1fSDK> registerClient;
    private boolean unregisterClient;
    private final Object valueOf;
    private final SensorManager values;

    static {
        BitSet bitSet = new BitSet(6);
        AFKeystoreWrapper = bitSet;
        bitSet.set(1);
        bitSet.set(2);
        bitSet.set(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.appsflyer.internal.AFi1bSDK$2 */
    /* loaded from: classes.dex */
    public final class RunnableC07262 implements Runnable {
        RunnableC07262() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (AFi1bSDK.this.valueOf) {
                AFi1bSDK.this.valueOf();
                AFi1bSDK.this.AFInAppEventParameterName.postDelayed(AFi1bSDK.this.afInfoLog, 150L);
                AFi1bSDK.this.f278d = true;
            }
        }
    }

    public /* synthetic */ void registerClient() {
        synchronized (this.valueOf) {
            this.AFInAppEventParameterName.post(new AFi1bSDK$$ExternalSyntheticLambda1(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.appsflyer.internal.AFi1bSDK$5 */
    /* loaded from: classes.dex */
    public final class RunnableC07275 implements Runnable {
        RunnableC07275() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (AFi1bSDK.this.valueOf) {
                if (AFi1bSDK.this.f278d) {
                    AFi1bSDK.this.AFInAppEventParameterName.removeCallbacks(AFi1bSDK.this.AFLogger);
                    AFi1bSDK.this.AFInAppEventParameterName.removeCallbacks(AFi1bSDK.this.afInfoLog);
                    AFi1bSDK.this.AFInAppEventType();
                    AFi1bSDK.this.f278d = false;
                }
            }
        }
    }

    private AFi1bSDK(SensorManager sensorManager, Handler handler, ExecutorService executorService) {
        this.valueOf = new Object();
        BitSet bitSet = AFKeystoreWrapper;
        this.registerClient = new HashMap(bitSet.size());
        this.f279e = new ConcurrentHashMap(bitSet.size());
        this.AFLogger = new Runnable() { // from class: com.appsflyer.internal.AFi1bSDK.2
            RunnableC07262() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                synchronized (AFi1bSDK.this.valueOf) {
                    AFi1bSDK.this.valueOf();
                    AFi1bSDK.this.AFInAppEventParameterName.postDelayed(AFi1bSDK.this.afInfoLog, 150L);
                    AFi1bSDK.this.f278d = true;
                }
            }
        };
        this.afInfoLog = new Runnable() { // from class: com.appsflyer.internal.AFi1bSDK$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AFi1bSDK.this.registerClient();
            }
        };
        this.f280i = new Runnable() { // from class: com.appsflyer.internal.AFi1bSDK.5
            RunnableC07275() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                synchronized (AFi1bSDK.this.valueOf) {
                    if (AFi1bSDK.this.f278d) {
                        AFi1bSDK.this.AFInAppEventParameterName.removeCallbacks(AFi1bSDK.this.AFLogger);
                        AFi1bSDK.this.AFInAppEventParameterName.removeCallbacks(AFi1bSDK.this.afInfoLog);
                        AFi1bSDK.this.AFInAppEventType();
                        AFi1bSDK.this.f278d = false;
                    }
                }
            }
        };
        this.values = sensorManager;
        this.AFInAppEventParameterName = handler;
        this.AFInAppEventType = executorService;
    }

    private static boolean values(int i) {
        return i >= 0 && AFKeystoreWrapper.get(i);
    }

    @Override // com.appsflyer.internal.AFi1dSDK
    public final void AFInAppEventParameterName() {
        this.AFInAppEventParameterName.post(this.f280i);
        this.AFInAppEventParameterName.post(this.AFLogger);
    }

    @Override // com.appsflyer.internal.AFi1dSDK
    public final synchronized void values() {
        this.AFInAppEventParameterName.post(this.f280i);
    }

    final void valueOf() {
        this.AFInAppEventParameterName.post(new Runnable() { // from class: com.appsflyer.internal.AFi1bSDK$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AFi1bSDK.this.AFLogger();
            }
        });
    }

    public /* synthetic */ void AFLogger() {
        try {
            for (Sensor sensor : this.values.getSensorList(-1)) {
                if (values(sensor.getType())) {
                    AFi1fSDK aFi1fSDK = new AFi1fSDK(sensor, this.AFInAppEventType);
                    if (!this.registerClient.containsKey(aFi1fSDK)) {
                        this.registerClient.put(aFi1fSDK, aFi1fSDK);
                    }
                    this.values.registerListener(this.registerClient.get(aFi1fSDK), sensor, 1, this.AFInAppEventParameterName);
                }
            }
        } catch (Throwable th) {
            AFLogger.afErrorLogForExcManagerOnly("registerListeners error", th);
        }
        this.unregisterClient = true;
    }

    final void AFInAppEventType() {
        this.AFInAppEventParameterName.post(new AFi1bSDK$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: d */
    public /* synthetic */ void m115d() {
        try {
            if (!this.registerClient.isEmpty()) {
                for (AFi1fSDK aFi1fSDK : this.registerClient.values()) {
                    this.values.unregisterListener(aFi1fSDK);
                    aFi1fSDK.values(this.f279e, true);
                }
            }
        } catch (Throwable th) {
            AFLogger.afErrorLogForExcManagerOnly("error while unregistering listeners", th);
        }
        this.unregisterClient = false;
    }

    /* renamed from: e */
    private List<Map<String, Object>> m116e() {
        synchronized (this.valueOf) {
            Iterator<AFi1fSDK> it = this.registerClient.values().iterator();
            while (it.hasNext()) {
                it.next().values(this.f279e, true);
            }
            if (this.f279e.isEmpty()) {
                return new CopyOnWriteArrayList(Collections.emptyList());
            }
            return new CopyOnWriteArrayList(this.f279e.values());
        }
    }

    private List<Map<String, Object>> unregisterClient() {
        synchronized (this.valueOf) {
            if (!this.registerClient.isEmpty() && this.unregisterClient) {
                Iterator<AFi1fSDK> it = this.registerClient.values().iterator();
                while (it.hasNext()) {
                    it.next().values(this.f279e, false);
                }
            }
            if (this.f279e.isEmpty()) {
                return new CopyOnWriteArrayList(Collections.emptyList());
            }
            return new CopyOnWriteArrayList(this.f279e.values());
        }
    }

    @Override // com.appsflyer.internal.AFi1dSDK
    public final Map<String, Object> AFKeystoreWrapper() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        List<Map<String, Object>> unregisterClient = unregisterClient();
        if (!unregisterClient.isEmpty()) {
            concurrentHashMap.put("sensors", unregisterClient);
        } else {
            List<Map<String, Object>> m116e = m116e();
            if (!m116e.isEmpty()) {
                concurrentHashMap.put("sensors", m116e);
            }
        }
        return concurrentHashMap;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AFi1bSDK(android.content.Context r3, java.util.concurrent.ExecutorService r4) {
        /*
            r2 = this;
            android.content.Context r3 = r3.getApplicationContext()
            java.lang.String r0 = "sensor"
            java.lang.Object r3 = r3.getSystemService(r0)
            android.hardware.SensorManager r3 = (android.hardware.SensorManager) r3
            android.os.HandlerThread r0 = new android.os.HandlerThread
            java.lang.String r1 = "internal"
            r0.<init>(r1)
            r0.start()
            android.os.Handler r1 = new android.os.Handler
            android.os.Looper r0 = r0.getLooper()
            r1.<init>(r0)
            r2.<init>(r3, r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFi1bSDK.<init>(android.content.Context, java.util.concurrent.ExecutorService):void");
    }
}
