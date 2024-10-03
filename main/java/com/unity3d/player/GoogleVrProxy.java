package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import com.unity3d.player.GoogleVrVideo;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class GoogleVrProxy extends C2707c implements GoogleVrVideo {

    /* renamed from: f */
    private boolean f1615f;

    /* renamed from: g */
    private boolean f1616g;

    /* renamed from: h */
    private Runnable f1617h;

    /* renamed from: i */
    private Vector f1618i;

    /* renamed from: j */
    private SurfaceView f1619j;

    /* renamed from: k */
    private C2666a f1620k;

    /* renamed from: l */
    private Thread f1621l;

    /* renamed from: m */
    private Handler f1622m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.unity3d.player.GoogleVrProxy$a */
    /* loaded from: classes3.dex */
    public class C2666a {

        /* renamed from: a */
        public boolean f1634a = false;

        /* renamed from: b */
        public boolean f1635b = false;

        /* renamed from: c */
        public boolean f1636c = false;

        /* renamed from: d */
        public boolean f1637d = false;

        /* renamed from: e */
        public boolean f1638e = true;

        /* renamed from: f */
        public boolean f1639f = false;

        C2666a() {
        }

        /* renamed from: a */
        public final boolean m1249a() {
            return this.f1634a && this.f1635b;
        }

        /* renamed from: b */
        public final void m1250b() {
            this.f1634a = false;
            this.f1635b = false;
            this.f1637d = false;
            this.f1638e = true;
            this.f1639f = false;
        }
    }

    public GoogleVrProxy(InterfaceC2710f interfaceC2710f) {
        super("Google VR", interfaceC2710f);
        this.f1615f = false;
        this.f1616g = false;
        this.f1617h = null;
        this.f1618i = new Vector();
        this.f1619j = null;
        this.f1620k = new C2666a();
        this.f1621l = null;
        this.f1622m = new Handler(Looper.getMainLooper()) { // from class: com.unity3d.player.GoogleVrProxy.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                if (message.what != 135711) {
                    super.handleMessage(message);
                    return;
                }
                switch (message.arg1) {
                    case 2147483645:
                        Iterator it = GoogleVrProxy.this.f1618i.iterator();
                        while (it.hasNext()) {
                            ((GoogleVrVideo.GoogleVrVideoCallbacks) it.next()).onFrameAvailable();
                        }
                        return;
                    case 2147483646:
                        Surface surface = (Surface) message.obj;
                        Iterator it2 = GoogleVrProxy.this.f1618i.iterator();
                        while (it2.hasNext()) {
                            ((GoogleVrVideo.GoogleVrVideoCallbacks) it2.next()).onSurfaceAvailable(surface);
                        }
                        return;
                    default:
                        super.handleMessage(message);
                        return;
                }
            }
        };
        initVrJni();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1236a(boolean z) {
        this.f1620k.f1637d = z;
    }

    /* renamed from: a */
    private boolean m1237a(ClassLoader classLoader) {
        try {
            Class<?> loadClass = classLoader.loadClass("com.unity3d.unitygvr.GoogleVR");
            C2720p c2720p = new C2720p(loadClass, loadClass.getConstructor(new Class[0]).newInstance(new Object[0]));
            c2720p.m1365a("initialize", new Class[]{Activity.class, Context.class, SurfaceView.class, Boolean.TYPE, Handler.class});
            c2720p.m1365a("deinitialize", new Class[0]);
            c2720p.m1365a("load", new Class[]{Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Runnable.class});
            c2720p.m1365a("enable", new Class[]{Boolean.TYPE});
            c2720p.m1365a("unload", new Class[0]);
            c2720p.m1365a("pause", new Class[0]);
            c2720p.m1365a("resume", new Class[0]);
            c2720p.m1365a("getGvrLayout", new Class[0]);
            c2720p.m1365a("getVideoSurfaceId", new Class[0]);
            c2720p.m1365a("getVideoSurface", new Class[0]);
            this.f1805a = c2720p;
            return true;
        } catch (Exception e) {
            reportError("Exception initializing GoogleVR from Unity library. " + e.getLocalizedMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public boolean m1240d() {
        return this.f1620k.f1637d;
    }

    /* renamed from: e */
    private void m1242e() {
        Activity activity = (Activity) this.f1807c;
        if (!this.f1616g || this.f1620k.f1639f || activity == null) {
            return;
        }
        this.f1620k.f1639f = true;
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(268435456);
        activity.startActivity(intent);
    }

    /* renamed from: f */
    private static boolean m1243f() {
        return Build.VERSION.SDK_INT >= 24;
    }

    private final native void initVrJni();

    private final native boolean isQuiting();

    private final native void setVrVideoTransform(float[][] fArr);

    /* renamed from: a */
    public final void m1244a(Intent intent) {
        if (intent == null || !intent.getBooleanExtra("android.intent.extra.VR_LAUNCH", false)) {
            return;
        }
        this.f1616g = true;
    }

    /* renamed from: a */
    public final boolean m1245a() {
        return this.f1620k.f1634a;
    }

    /* renamed from: a */
    public final boolean m1246a(Activity activity, Context context, SurfaceView surfaceView, Runnable runnable) {
        String str;
        boolean z;
        if (activity == null || context == null || surfaceView == null || runnable == null) {
            str = "Invalid parameters passed to Google VR initialization.";
        } else {
            this.f1620k.m1250b();
            this.f1807c = context;
            this.f1617h = runnable;
            if (this.f1616g && !m1243f()) {
                str = "Daydream requires a device that supports an api version of 24 (Nougat) or better.";
            } else {
                if (!m1237a(UnityPlayer.class.getClassLoader())) {
                    return false;
                }
                try {
                    z = ((Boolean) this.f1805a.m1364a("initialize", activity, context, surfaceView, Boolean.valueOf(this.f1616g), this.f1622m)).booleanValue();
                } catch (Exception e) {
                    reportError("Exception while trying to initialize Unity Google VR Library. " + e.getLocalizedMessage());
                    z = false;
                }
                if (z) {
                    this.f1619j = surfaceView;
                    this.f1620k.f1634a = true;
                    this.f1808d = "";
                    return true;
                }
                str = "Unable to initialize GoogleVR library.";
            }
        }
        reportError(str);
        return false;
    }

    /* renamed from: b */
    public final void m1247b() {
        resumeGvrLayout();
    }

    /* renamed from: c */
    public final void m1248c() {
        SurfaceView surfaceView = this.f1619j;
        if (surfaceView != null) {
            surfaceView.getHolder().setSizeFromLayout();
        }
    }

    @Override // com.unity3d.player.GoogleVrVideo
    public void deregisterGoogleVrVideoListener(GoogleVrVideo.GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (this.f1618i.contains(googleVrVideoCallbacks)) {
            googleVrVideoCallbacks.onSurfaceUnavailable();
            this.f1618i.remove(googleVrVideoCallbacks);
        }
    }

    protected Object getVideoSurface() {
        if (m1240d() && !this.f1620k.f1638e) {
            try {
                return this.f1805a.m1364a("getVideoSurface", new Object[0]);
            } catch (Exception e) {
                reportError("Exception caught while Getting GoogleVR Video Surface. " + e.getLocalizedMessage());
            }
        }
        return null;
    }

    protected int getVideoSurfaceId() {
        if (m1240d() && !this.f1620k.f1638e) {
            try {
                return ((Integer) this.f1805a.m1364a("getVideoSurfaceId", new Object[0])).intValue();
            } catch (Exception e) {
                reportError("Exception caught while getting Video Surface ID from GoogleVR. " + e.getLocalizedMessage());
            }
        }
        return -1;
    }

    protected long loadGoogleVr(final boolean z, final boolean z2, final boolean z3, final boolean z4, final boolean z5) {
        if (!this.f1620k.f1634a) {
            return 0L;
        }
        final AtomicLong atomicLong = new AtomicLong(0L);
        this.f1808d = (z || z2) ? "Daydream" : "Cardboard";
        if (!runOnUiThreadWithSync(new Runnable() { // from class: com.unity3d.player.GoogleVrProxy.2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    atomicLong.set(((Long) GoogleVrProxy.this.f1805a.m1364a("load", Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3), Boolean.valueOf(z4), Boolean.valueOf(z5), GoogleVrProxy.this.f1617h)).longValue());
                    GoogleVrProxy.this.f1620k.f1635b = true;
                } catch (Exception e) {
                    GoogleVrProxy.this.reportError("Exception caught while loading GoogleVR. " + e.getLocalizedMessage());
                    atomicLong.set(0L);
                }
            }
        }) || atomicLong.longValue() == 0) {
            reportError("Google VR had a fatal issue while loading. VR will not be available.");
        }
        return atomicLong.longValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void pauseGvrLayout() {
        if (this.f1620k.m1249a() && !this.f1620k.f1638e) {
            if (m1240d()) {
                Iterator it = this.f1618i.iterator();
                while (it.hasNext()) {
                    ((GoogleVrVideo.GoogleVrVideoCallbacks) it.next()).onSurfaceUnavailable();
                }
            }
            if (this.f1805a != null) {
                this.f1805a.m1364a("pause", new Object[0]);
            }
            this.f1620k.f1638e = true;
        }
    }

    @Override // com.unity3d.player.GoogleVrVideo
    public void registerGoogleVrVideoListener(GoogleVrVideo.GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (this.f1618i.contains(googleVrVideoCallbacks)) {
            return;
        }
        this.f1618i.add(googleVrVideoCallbacks);
        Surface surface = (Surface) getVideoSurface();
        if (surface != null) {
            googleVrVideoCallbacks.onSurfaceAvailable(surface);
        }
    }

    protected void resumeGvrLayout() {
        if (this.f1620k.m1249a() && this.f1620k.f1638e) {
            if (this.f1805a != null) {
                this.f1805a.m1364a("resume", new Object[0]);
            }
            this.f1620k.f1638e = false;
        }
    }

    protected void setGoogleVrModeEnabled(final boolean z) {
        if (!this.f1620k.m1249a() || this.f1806b == null || this.f1807c == null) {
            return;
        }
        if (!z && isQuiting()) {
            m1242e();
        }
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.GoogleVrProxy.3
            @Override // java.lang.Runnable
            public final void run() {
                if (z == GoogleVrProxy.this.m1240d()) {
                    return;
                }
                try {
                    if (z && !GoogleVrProxy.this.m1240d()) {
                        if (GoogleVrProxy.this.f1805a != null && GoogleVrProxy.this.f1806b != null && !GoogleVrProxy.this.f1806b.addViewToPlayer((View) GoogleVrProxy.this.f1805a.m1364a("getGvrLayout", new Object[0]), true)) {
                            GoogleVrProxy.this.reportError("Unable to add Google VR to view hierarchy.");
                            return;
                        }
                        if (GoogleVrProxy.this.f1805a != null) {
                            GoogleVrProxy.this.f1805a.m1364a("enable", true);
                        }
                        GoogleVrProxy.this.m1236a(true);
                        return;
                    }
                    if (z || !GoogleVrProxy.this.m1240d()) {
                        return;
                    }
                    GoogleVrProxy.this.m1236a(false);
                    if (GoogleVrProxy.this.f1805a != null) {
                        GoogleVrProxy.this.f1805a.m1364a("enable", false);
                    }
                    if (GoogleVrProxy.this.f1805a == null || GoogleVrProxy.this.f1806b == null) {
                        return;
                    }
                    GoogleVrProxy.this.f1806b.removeViewFromPlayer((View) GoogleVrProxy.this.f1805a.m1364a("getGvrLayout", new Object[0]));
                } catch (Exception e) {
                    GoogleVrProxy.this.reportError("Exception enabling Google VR on UI Thread. " + e.getLocalizedMessage());
                }
            }
        });
    }

    @Override // com.unity3d.player.GoogleVrVideo
    public void setVideoLocationTransform(float[] fArr) {
        float[][] fArr2 = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 4, 4);
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                fArr2[i][i2] = fArr[(i * 4) + i2];
            }
        }
        setVrVideoTransform(fArr2);
    }

    protected void unloadGoogleVr() {
        if (this.f1620k.f1637d) {
            setGoogleVrModeEnabled(false);
        }
        if (this.f1620k.f1636c) {
            this.f1620k.f1636c = false;
        }
        this.f1619j = null;
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.GoogleVrProxy.4
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (GoogleVrProxy.this.f1805a != null) {
                        GoogleVrProxy.this.f1805a.m1364a("unload", new Object[0]);
                        GoogleVrProxy.this.f1805a.m1364a("deinitialize", new Object[0]);
                        GoogleVrProxy.this.f1805a = null;
                    }
                    GoogleVrProxy.this.f1620k.f1635b = false;
                } catch (Exception e) {
                    GoogleVrProxy.this.reportError("Exception unloading Google VR on UI Thread. " + e.getLocalizedMessage());
                }
            }
        });
    }
}
