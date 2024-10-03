package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;

/* loaded from: classes3.dex */
public class Camera2Wrapper implements InterfaceC2708d {

    /* renamed from: a */
    private Context f1611a;

    /* renamed from: b */
    private C2706b f1612b = null;

    /* renamed from: c */
    private final int f1613c = 100;

    public Camera2Wrapper(Context context) {
        this.f1611a = context;
        initCamera2Jni();
    }

    /* renamed from: a */
    private static int m1227a(float f) {
        return (int) Math.min(Math.max((f * 2000.0f) - 1000.0f, -900.0f), 900.0f);
    }

    private final native void deinitCamera2Jni();

    private final native void initCamera2Jni();

    private final native void nativeFrameReady(Object obj, Object obj2, Object obj3, int i, int i2, int i3);

    private final native void nativeSurfaceTextureReady(Object obj);

    /* renamed from: a */
    public final void m1228a() {
        deinitCamera2Jni();
        closeCamera2();
    }

    @Override // com.unity3d.player.InterfaceC2708d
    /* renamed from: a */
    public final void mo1229a(Object obj) {
        nativeSurfaceTextureReady(obj);
    }

    @Override // com.unity3d.player.InterfaceC2708d
    /* renamed from: a */
    public final void mo1230a(Object obj, Object obj2, Object obj3, int i, int i2, int i3) {
        nativeFrameReady(obj, obj2, obj3, i, i2, i3);
    }

    protected void closeCamera2() {
        C2706b c2706b = this.f1612b;
        if (c2706b != null) {
            c2706b.m1325b();
        }
        this.f1612b = null;
    }

    protected int getCamera2Count() {
        if (C2715k.f1819a) {
            return C2706b.m1288a(this.f1611a);
        }
        return 0;
    }

    protected int[] getCamera2Resolutions(int i) {
        if (C2715k.f1819a) {
            return C2706b.m1310d(this.f1611a, i);
        }
        return null;
    }

    protected int getCamera2SensorOrientation(int i) {
        if (C2715k.f1819a) {
            return C2706b.m1289a(this.f1611a, i);
        }
        return 0;
    }

    protected Object getCameraFocusArea(float f, float f2) {
        int m1227a = m1227a(f);
        int m1227a2 = m1227a(1.0f - f2);
        return new Camera.Area(new Rect(m1227a - 100, m1227a2 - 100, m1227a + 100, m1227a2 + 100), 1000);
    }

    protected Rect getFrameSizeCamera2() {
        C2706b c2706b = this.f1612b;
        return c2706b != null ? c2706b.m1322a() : new Rect();
    }

    protected boolean initializeCamera2(int i, int i2, int i3, int i4, int i5) {
        if (!C2715k.f1819a || this.f1612b != null || UnityPlayer.currentActivity == null) {
            return false;
        }
        C2706b c2706b = new C2706b(this);
        this.f1612b = c2706b;
        return c2706b.m1324a(this.f1611a, i, i2, i3, i4, i5);
    }

    protected boolean isCamera2AutoFocusPointSupported(int i) {
        if (C2715k.f1819a) {
            return C2706b.m1307c(this.f1611a, i);
        }
        return false;
    }

    protected boolean isCamera2FrontFacing(int i) {
        if (C2715k.f1819a) {
            return C2706b.m1305b(this.f1611a, i);
        }
        return false;
    }

    protected void pauseCamera2() {
        C2706b c2706b = this.f1612b;
        if (c2706b != null) {
            c2706b.m1327d();
        }
    }

    protected boolean setAutoFocusPoint(float f, float f2) {
        C2706b c2706b;
        if (!C2715k.f1819a || (c2706b = this.f1612b) == null) {
            return false;
        }
        return c2706b.m1323a(f, f2);
    }

    protected void startCamera2() {
        C2706b c2706b = this.f1612b;
        if (c2706b != null) {
            c2706b.m1326c();
        }
    }

    protected void stopCamera2() {
        C2706b c2706b = this.f1612b;
        if (c2706b != null) {
            c2706b.m1328e();
        }
    }
}
