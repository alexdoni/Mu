package com.unity3d.player;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.PixelCopy;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unity3d.player.j */
/* loaded from: classes3.dex */
public final class C2714j implements Application.ActivityLifecycleCallbacks {

    /* renamed from: b */
    Activity f1815b;

    /* renamed from: a */
    WeakReference f1814a = new WeakReference(null);

    /* renamed from: c */
    View f1816c = null;

    /* renamed from: com.unity3d.player.j$a */
    /* loaded from: classes3.dex */
    class a extends View implements PixelCopy.OnPixelCopyFinishedListener {

        /* renamed from: a */
        Bitmap f1817a;

        a(Context context) {
            super(context);
        }

        /* renamed from: a */
        public final void m1336a(SurfaceView surfaceView) {
            Bitmap createBitmap = Bitmap.createBitmap(surfaceView.getWidth(), surfaceView.getHeight(), Bitmap.Config.ARGB_8888);
            this.f1817a = createBitmap;
            PixelCopy.request(surfaceView, createBitmap, this, new Handler(Looper.getMainLooper()));
        }

        @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
        public final void onPixelCopyFinished(int i) {
            if (i == 0) {
                setBackground(new LayerDrawable(new Drawable[]{new ColorDrawable(ViewCompat.MEASURED_STATE_MASK), new BitmapDrawable(getResources(), this.f1817a)}));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2714j(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            this.f1815b = activity;
            activity.getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    /* renamed from: a */
    public final void m1332a() {
        Activity activity = this.f1815b;
        if (activity != null) {
            activity.getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    /* renamed from: a */
    public final void m1333a(SurfaceView surfaceView) {
        if (C2715k.f1821c && this.f1814a.get() != this.f1815b && this.f1816c == null) {
            a aVar = new a(this.f1815b);
            aVar.m1336a(surfaceView);
            this.f1816c = aVar;
        }
    }

    /* renamed from: a */
    public final void m1334a(ViewGroup viewGroup) {
        View view = this.f1816c;
        if (view == null || view.getParent() != null) {
            return;
        }
        viewGroup.addView(this.f1816c);
        viewGroup.bringChildToFront(this.f1816c);
    }

    /* renamed from: b */
    public final void m1335b(ViewGroup viewGroup) {
        View view = this.f1816c;
        if (view == null || view.getParent() == null) {
            return;
        }
        viewGroup.removeView(this.f1816c);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        this.f1814a = new WeakReference(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
