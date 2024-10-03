package com.p008ld.sdk.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.Lifecycle;
import com.p008ld.sdk.LDSdk;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: AppLifecycleImpl.java */
/* loaded from: classes2.dex */
public class zza implements Application.ActivityLifecycleCallbacks {
    static final zza zza = new zza();
    private static final Activity zze = new Activity();
    private static final Handler zzi = new Handler(Looper.getMainLooper());
    private final LinkedList<Activity> zzb = new LinkedList<>();
    private final List<zzb> zzc = new CopyOnWriteArrayList();
    private final Map<Activity, List<C3260zza>> zzd = new ConcurrentHashMap();
    private int zzf = 0;
    private int zzg = 0;
    private boolean zzh = false;

    /* compiled from: AppLifecycleImpl.java */
    /* renamed from: com.ld.sdk.util.zza$zza, reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C3260zza {
        public void zza(Activity activity) {
        }

        public void zza(Activity activity, Lifecycle.Event event) {
        }

        public void zzb(Activity activity) {
        }

        public void zzc(Activity activity) {
        }

        public void zzd(Activity activity) {
        }

        public void zze(Activity activity) {
        }

        public void zzf(Activity activity) {
        }
    }

    /* compiled from: AppLifecycleImpl.java */
    /* loaded from: classes2.dex */
    public interface zzb {
        void zza(Activity activity);

        void zzb(Activity activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostSaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPrePaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreSaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zza(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zzb(Application application) {
        this.zzb.clear();
        application.unregisterActivityLifecycleCallbacks(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Activity zza() {
        for (Activity activity : zzb()) {
            if (zza(activity)) {
                return activity;
            }
        }
        return null;
    }

    List<Activity> zzb() {
        if (!this.zzb.isEmpty()) {
            return new LinkedList(this.zzb);
        }
        this.zzb.addAll(zze());
        return new LinkedList(this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zza(zzb zzbVar) {
        this.zzc.add(zzbVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zzb(zzb zzbVar) {
        this.zzc.remove(zzbVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean zzc() {
        return !this.zzh;
    }

    private void zza(Activity activity, Lifecycle.Event event) {
        zza(activity, event, this.zzd.get(activity));
        zza(activity, event, this.zzd.get(zze));
    }

    private void zza(Activity activity, Lifecycle.Event event, List<C3260zza> list) {
        if (list == null) {
            return;
        }
        for (C3260zza c3260zza : list) {
            c3260zza.zza(activity, event);
            if (event.equals(Lifecycle.Event.ON_CREATE)) {
                c3260zza.zza(activity);
            } else if (event.equals(Lifecycle.Event.ON_START)) {
                c3260zza.zzb(activity);
            } else if (event.equals(Lifecycle.Event.ON_RESUME)) {
                c3260zza.zzc(activity);
            } else if (event.equals(Lifecycle.Event.ON_PAUSE)) {
                c3260zza.zzd(activity);
            } else if (event.equals(Lifecycle.Event.ON_STOP)) {
                c3260zza.zze(activity);
            } else if (event.equals(Lifecycle.Event.ON_DESTROY)) {
                c3260zza.zzf(activity);
            }
        }
        if (event.equals(Lifecycle.Event.ON_DESTROY)) {
            this.zzd.remove(activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Application zzd() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("getApplication", new Class[0]).invoke(zzf(), new Object[0]);
            if (invoke == null) {
                return null;
            }
            return (Application) invoke;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.zzb.size() == 0) {
            zzb(activity, true);
        }
        zzi();
        zzb(activity);
        zza(activity, Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        if (!this.zzh) {
            zzb(activity);
        }
        int i = this.zzg;
        if (i < 0) {
            this.zzg = i + 1;
        } else {
            this.zzf++;
        }
        zza(activity, Lifecycle.Event.ON_START);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        zzb(activity);
        if (this.zzh) {
            this.zzh = false;
            zzb(activity, true);
        }
        zza(activity, false);
        zza(activity, Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        zza(activity, Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (activity.isChangingConfigurations()) {
            this.zzg--;
        } else {
            int i = this.zzf - 1;
            this.zzf = i;
            if (i <= 0) {
                this.zzh = true;
                zzb(activity, false);
            }
        }
        zza(activity, true);
        zza(activity, Lifecycle.Event.ON_STOP);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        this.zzb.remove(activity);
        zza(activity.getWindow());
        zza(activity, Lifecycle.Event.ON_DESTROY);
    }

    private void zza(final Activity activity, boolean z) {
        try {
            if (z) {
                Window window = activity.getWindow();
                window.getDecorView().setTag(-123, Integer.valueOf(window.getAttributes().softInputMode));
                window.setSoftInputMode(3);
            } else {
                final Object tag = activity.getWindow().getDecorView().getTag(-123);
                if (!(tag instanceof Integer)) {
                } else {
                    zza(new Runnable() { // from class: com.ld.sdk.util.zza.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                Window window2 = activity.getWindow();
                                if (window2 != null) {
                                    window2.setSoftInputMode(((Integer) tag).intValue());
                                }
                            } catch (Exception unused) {
                            }
                        }
                    }, 100L);
                }
            }
        } catch (Exception unused) {
        }
    }

    private void zzb(Activity activity, boolean z) {
        if (this.zzc.isEmpty()) {
            return;
        }
        for (zzb zzbVar : this.zzc) {
            if (z) {
                zzbVar.zza(activity);
            } else {
                zzbVar.zzb(activity);
            }
        }
    }

    private void zzb(Activity activity) {
        if (this.zzb.contains(activity)) {
            if (this.zzb.getFirst().equals(activity)) {
                return;
            }
            this.zzb.remove(activity);
            this.zzb.addFirst(activity);
            return;
        }
        this.zzb.addFirst(activity);
    }

    private List<Activity> zze() {
        Object obj;
        LinkedList linkedList = new LinkedList();
        Activity activity = null;
        try {
            Object zzf = zzf();
            Field declaredField = zzf.getClass().getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            obj = declaredField.get(zzf);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivitiesByReflect: " + e.getMessage());
        }
        if (!(obj instanceof Map)) {
            return linkedList;
        }
        for (Object obj2 : ((Map) obj).values()) {
            Class<?> cls = obj2.getClass();
            Field declaredField2 = cls.getDeclaredField("activity");
            declaredField2.setAccessible(true);
            Activity activity2 = (Activity) declaredField2.get(obj2);
            if (activity == null) {
                Field declaredField3 = cls.getDeclaredField("paused");
                declaredField3.setAccessible(true);
                if (declaredField3.getBoolean(obj2)) {
                    linkedList.add(activity2);
                } else {
                    activity = activity2;
                }
            } else {
                linkedList.add(activity2);
            }
        }
        if (activity != null) {
            linkedList.addFirst(activity);
        }
        return linkedList;
    }

    private Object zzf() {
        Object zzg = zzg();
        return zzg != null ? zzg : zzh();
    }

    private Object zzg() {
        try {
            Field declaredField = Class.forName("android.app.ActivityThread").getDeclaredField("sCurrentActivityThread");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivityThreadInActivityThreadStaticField: " + e.getMessage());
            return null;
        }
    }

    private Object zzh() {
        try {
            return Class.forName("android.app.ActivityThread").getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivityThreadInActivityThreadStaticMethod: " + e.getMessage());
            return null;
        }
    }

    private static void zzi() {
        if (Build.VERSION.SDK_INT < 26 || !ValueAnimator.areAnimatorsEnabled()) {
            try {
                Field declaredField = ValueAnimator.class.getDeclaredField("sDurationScale");
                declaredField.setAccessible(true);
                if (((Float) declaredField.get(null)).floatValue() == 0.0f) {
                    declaredField.set(null, Float.valueOf(1.0f));
                    Log.i("UtilsActivityLifecycle", "setAnimatorsEnabled: Animators are enabled now!");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void zza(Window window) {
        InputMethodManager inputMethodManager = (InputMethodManager) LDSdk.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        String[] strArr = {"mLastSrvView", "mCurRootView", "mServedView", "mNextServedView"};
        for (int i = 0; i < 4; i++) {
            try {
                Field declaredField = InputMethodManager.class.getDeclaredField(strArr[i]);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                Object obj = declaredField.get(inputMethodManager);
                if ((obj instanceof View) && ((View) obj).getRootView() == window.getDecorView().getRootView()) {
                    declaredField.set(inputMethodManager, null);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static boolean zza(Activity activity) {
        return (activity == null || activity.isFinishing() || activity.isDestroyed()) ? false : true;
    }

    public static void zza(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            zzi.post(runnable);
        }
    }

    public static void zza(Runnable runnable, long j) {
        zzi.postDelayed(runnable, j);
    }
}
