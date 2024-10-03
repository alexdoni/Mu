package com.appsflyer.internal;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public final class AFi1fSDK implements SensorEventListener {
    private final String AFInAppEventParameterName;
    private final int AFInAppEventType;
    private final String AFKeystoreWrapper;

    /* renamed from: e */
    private final Executor f284e;
    private long registerClient;
    private double valueOf;
    private final int values;

    /* renamed from: d */
    private final float[][] f283d = new float[2];
    private final long[] AFLogger = new long[2];

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AFi1fSDK(Sensor sensor, ExecutorService executorService) {
        int type = sensor.getType();
        this.AFInAppEventType = type;
        String name = sensor.getName();
        name = name == null ? "" : name;
        this.AFKeystoreWrapper = name;
        String vendor = sensor.getVendor();
        String str = vendor != null ? vendor : "";
        this.AFInAppEventParameterName = str;
        this.values = ((((type + 31) * 31) + name.hashCode()) * 31) + str.hashCode();
        this.f284e = executorService;
    }

    private static double valueOf(float[] fArr, float[] fArr2) {
        int min = Math.min(fArr.length, fArr2.length);
        double d = 0.0d;
        for (int i = 0; i < min; i++) {
            d += StrictMath.pow(fArr[i] - fArr2[i], 2.0d);
        }
        return Math.sqrt(d);
    }

    private static List<Float> AFKeystoreWrapper(float[] fArr) {
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f : fArr) {
            arrayList.add(Float.valueOf(f));
        }
        return arrayList;
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(final SensorEvent sensorEvent) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.f284e.execute(new Runnable() { // from class: com.appsflyer.internal.AFi1fSDK$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AFi1fSDK.this.AFInAppEventParameterName(sensorEvent);
                }
            });
        } else {
            AFInAppEventParameterName(sensorEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: valueOf, reason: merged with bridge method [inline-methods] */
    public void AFInAppEventParameterName(SensorEvent sensorEvent) {
        long j = sensorEvent.timestamp;
        float[] fArr = sensorEvent.values;
        long currentTimeMillis = System.currentTimeMillis();
        float[][] fArr2 = this.f283d;
        float[] fArr3 = fArr2[0];
        if (fArr3 == null) {
            fArr2[0] = Arrays.copyOf(fArr, fArr.length);
            this.AFLogger[0] = currentTimeMillis;
            return;
        }
        float[] fArr4 = fArr2[1];
        if (fArr4 == null) {
            float[] copyOf = Arrays.copyOf(fArr, fArr.length);
            this.f283d[1] = copyOf;
            this.AFLogger[1] = currentTimeMillis;
            this.valueOf = valueOf(fArr3, copyOf);
            return;
        }
        if (50000000 <= j - this.registerClient) {
            this.registerClient = j;
            if (Arrays.equals(fArr4, fArr)) {
                this.AFLogger[1] = currentTimeMillis;
                return;
            }
            double valueOf = valueOf(fArr3, fArr);
            if (valueOf > this.valueOf) {
                this.f283d[1] = Arrays.copyOf(fArr, fArr.length);
                this.AFLogger[1] = currentTimeMillis;
                this.valueOf = valueOf;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void values(Map<AFi1fSDK, Map<String, Object>> map, boolean z) {
        if (values()) {
            map.put(this, AFInAppEventParameterName());
            if (z) {
                int length = this.f283d.length;
                for (int i = 0; i < length; i++) {
                    this.f283d[i] = null;
                }
                int length2 = this.AFLogger.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    this.AFLogger[i2] = 0;
                }
                this.valueOf = 0.0d;
                this.registerClient = 0L;
                return;
            }
            return;
        }
        if (map.containsKey(this)) {
            return;
        }
        map.put(this, AFInAppEventParameterName());
    }

    private boolean values(int i, String str, String str2) {
        return this.AFInAppEventType == i && this.AFKeystoreWrapper.equals(str) && this.AFInAppEventParameterName.equals(str2);
    }

    private Map<String, Object> AFInAppEventParameterName() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(7);
        concurrentHashMap.put("sT", Integer.valueOf(this.AFInAppEventType));
        concurrentHashMap.put("sN", this.AFKeystoreWrapper);
        concurrentHashMap.put("sV", this.AFInAppEventParameterName);
        float[] fArr = this.f283d[0];
        if (fArr != null) {
            concurrentHashMap.put("sVS", AFKeystoreWrapper(fArr));
        }
        float[] fArr2 = this.f283d[1];
        if (fArr2 != null) {
            concurrentHashMap.put("sVE", AFKeystoreWrapper(fArr2));
        }
        return concurrentHashMap;
    }

    private boolean values() {
        return this.f283d[0] != null;
    }

    public final int hashCode() {
        return this.values;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AFi1fSDK)) {
            return false;
        }
        AFi1fSDK aFi1fSDK = (AFi1fSDK) obj;
        return values(aFi1fSDK.AFInAppEventType, aFi1fSDK.AFKeystoreWrapper, aFi1fSDK.AFInAppEventParameterName);
    }
}
