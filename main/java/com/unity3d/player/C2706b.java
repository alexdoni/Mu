package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* renamed from: com.unity3d.player.b */
/* loaded from: classes3.dex */
public final class C2706b {

    /* renamed from: b */
    private static CameraManager f1766b;

    /* renamed from: c */
    private static String[] f1767c;

    /* renamed from: e */
    private static Semaphore f1768e = new Semaphore(1);

    /* renamed from: a */
    private InterfaceC2708d f1773a;

    /* renamed from: d */
    private CameraDevice f1774d;

    /* renamed from: f */
    private HandlerThread f1775f;

    /* renamed from: g */
    private Handler f1776g;

    /* renamed from: h */
    private Rect f1777h;

    /* renamed from: i */
    private Rect f1778i;

    /* renamed from: j */
    private int f1779j;

    /* renamed from: k */
    private int f1780k;

    /* renamed from: n */
    private int f1783n;

    /* renamed from: o */
    private int f1784o;

    /* renamed from: q */
    private Range f1786q;

    /* renamed from: s */
    private Image f1788s;

    /* renamed from: t */
    private CaptureRequest.Builder f1789t;

    /* renamed from: w */
    private int f1792w;

    /* renamed from: x */
    private SurfaceTexture f1793x;

    /* renamed from: l */
    private float f1781l = -1.0f;

    /* renamed from: m */
    private float f1782m = -1.0f;

    /* renamed from: p */
    private boolean f1785p = false;

    /* renamed from: r */
    private ImageReader f1787r = null;

    /* renamed from: u */
    private CameraCaptureSession f1790u = null;

    /* renamed from: v */
    private Object f1791v = new Object();

    /* renamed from: y */
    private Surface f1794y = null;

    /* renamed from: z */
    private int f1795z = a.f1803c;

    /* renamed from: A */
    private CameraCaptureSession.CaptureCallback f1769A = new CameraCaptureSession.CaptureCallback() { // from class: com.unity3d.player.b.1
        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public final void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            C2706b.this.m1299a(captureRequest.getTag());
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public final void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            C2711g.Log(5, "Camera2: Capture session failed " + captureRequest.getTag() + " reason " + captureFailure.getReason());
            C2706b.this.m1299a(captureRequest.getTag());
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public final void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public final void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i, long j) {
        }
    };

    /* renamed from: B */
    private final CameraDevice.StateCallback f1770B = new CameraDevice.StateCallback() { // from class: com.unity3d.player.b.3
        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public final void onClosed(CameraDevice cameraDevice) {
            C2706b.f1768e.release();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public final void onDisconnected(CameraDevice cameraDevice) {
            C2711g.Log(5, "Camera2: CameraDevice disconnected.");
            C2706b.this.m1297a(cameraDevice);
            C2706b.f1768e.release();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public final void onError(CameraDevice cameraDevice, int i) {
            C2711g.Log(6, "Camera2: Error opeining CameraDevice " + i);
            C2706b.this.m1297a(cameraDevice);
            C2706b.f1768e.release();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public final void onOpened(CameraDevice cameraDevice) {
            C2706b.this.f1774d = cameraDevice;
            C2706b.f1768e.release();
        }
    };

    /* renamed from: C */
    private final ImageReader.OnImageAvailableListener f1771C = new ImageReader.OnImageAvailableListener() { // from class: com.unity3d.player.b.4
        @Override // android.media.ImageReader.OnImageAvailableListener
        public final void onImageAvailable(ImageReader imageReader) {
            if (C2706b.f1768e.tryAcquire()) {
                Image acquireNextImage = imageReader.acquireNextImage();
                if (acquireNextImage != null) {
                    Image.Plane[] planes = acquireNextImage.getPlanes();
                    if (acquireNextImage.getFormat() == 35 && planes != null && planes.length == 3) {
                        C2706b.this.f1773a.mo1230a(planes[0].getBuffer(), planes[1].getBuffer(), planes[2].getBuffer(), planes[0].getRowStride(), planes[1].getRowStride(), planes[1].getPixelStride());
                    } else {
                        C2711g.Log(6, "Camera2: Wrong image format.");
                    }
                    if (C2706b.this.f1788s != null) {
                        C2706b.this.f1788s.close();
                    }
                    C2706b.this.f1788s = acquireNextImage;
                }
                C2706b.f1768e.release();
            }
        }
    };

    /* renamed from: D */
    private final SurfaceTexture.OnFrameAvailableListener f1772D = new SurfaceTexture.OnFrameAvailableListener() { // from class: com.unity3d.player.b.5
        @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
        public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
            C2706b.this.f1773a.mo1229a(surfaceTexture);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* renamed from: com.unity3d.player.b$a */
    /* loaded from: classes3.dex */
    public static final class a {

        /* renamed from: a */
        public static final int f1801a = 1;

        /* renamed from: b */
        public static final int f1802b = 2;

        /* renamed from: c */
        public static final int f1803c = 3;

        /* renamed from: d */
        private static final /* synthetic */ int[] f1804d = {1, 2, 3};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C2706b(InterfaceC2708d interfaceC2708d) {
        this.f1773a = null;
        this.f1773a = interfaceC2708d;
        m1314g();
    }

    /* renamed from: a */
    public static int m1288a(Context context) {
        return m1308c(context).length;
    }

    /* renamed from: a */
    public static int m1289a(Context context, int i) {
        try {
            return ((Integer) m1301b(context).getCameraCharacteristics(m1308c(context)[i]).get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        } catch (CameraAccessException e) {
            C2711g.Log(6, "Camera2: CameraAccessException " + e);
            return 0;
        }
    }

    /* renamed from: a */
    private static int m1290a(Range[] rangeArr, int i) {
        int i2 = -1;
        double d = Double.MAX_VALUE;
        for (int i3 = 0; i3 < rangeArr.length; i3++) {
            int intValue = ((Integer) rangeArr[i3].getLower()).intValue();
            int intValue2 = ((Integer) rangeArr[i3].getUpper()).intValue();
            float f = i;
            if (f + 0.1f > intValue && f - 0.1f < intValue2) {
                return i;
            }
            if (r4 < d) {
                i2 = i3;
                d = r4;
            }
        }
        return ((Integer) (i > ((Integer) rangeArr[i2].getUpper()).intValue() ? rangeArr[i2].getUpper() : rangeArr[i2].getLower())).intValue();
    }

    /* renamed from: a */
    private static Rect m1291a(Size[] sizeArr, double d, double d2) {
        double d3 = Double.MAX_VALUE;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < sizeArr.length; i3++) {
            int width = sizeArr[i3].getWidth();
            int height = sizeArr[i3].getHeight();
            double abs = Math.abs(Math.log(d / width)) + Math.abs(Math.log(d2 / height));
            if (abs < d3) {
                i = width;
                i2 = height;
                d3 = abs;
            }
        }
        return new Rect(0, 0, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1297a(CameraDevice cameraDevice) {
        synchronized (this.f1791v) {
            this.f1790u = null;
        }
        cameraDevice.close();
        this.f1774d = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1299a(Object obj) {
        if (obj != "Focus") {
            if (obj == "Cancel focus") {
                synchronized (this.f1791v) {
                    if (this.f1790u != null) {
                        m1320j();
                    }
                }
                return;
            }
            return;
        }
        this.f1785p = false;
        synchronized (this.f1791v) {
            if (this.f1790u != null) {
                try {
                    this.f1789t.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                    this.f1789t.setTag("Regular");
                    this.f1790u.setRepeatingRequest(this.f1789t.build(), this.f1769A, this.f1776g);
                } catch (CameraAccessException e) {
                    C2711g.Log(6, "Camera2: CameraAccessException " + e);
                }
            }
        }
    }

    /* renamed from: a */
    private static Size[] m1300a(CameraCharacteristics cameraCharacteristics) {
        String str;
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null) {
            str = "Camera2: configuration map is not available.";
        } else {
            Size[] outputSizes = streamConfigurationMap.getOutputSizes(35);
            if (outputSizes != null && outputSizes.length != 0) {
                return outputSizes;
            }
            str = "Camera2: output sizes for YUV_420_888 format are not avialable.";
        }
        C2711g.Log(6, str);
        return null;
    }

    /* renamed from: b */
    private static CameraManager m1301b(Context context) {
        if (f1766b == null) {
            f1766b = (CameraManager) context.getSystemService("camera");
        }
        return f1766b;
    }

    /* renamed from: b */
    private void m1303b(CameraCharacteristics cameraCharacteristics) {
        int intValue = ((Integer) cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue();
        this.f1780k = intValue;
        if (intValue > 0) {
            this.f1778i = (Rect) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
            float width = this.f1777h.width() / this.f1777h.height();
            if (width > r4.width() / this.f1778i.height()) {
                this.f1783n = 0;
                this.f1784o = (int) ((this.f1778i.height() - (this.f1778i.width() / width)) / 2.0f);
            } else {
                this.f1784o = 0;
                this.f1783n = (int) ((this.f1778i.width() - (this.f1778i.height() * width)) / 2.0f);
            }
            this.f1779j = Math.min(this.f1778i.width(), this.f1778i.height()) / 20;
        }
    }

    /* renamed from: b */
    public static boolean m1305b(Context context, int i) {
        try {
            return ((Integer) m1301b(context).getCameraCharacteristics(m1308c(context)[i]).get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
        } catch (CameraAccessException e) {
            C2711g.Log(6, "Camera2: CameraAccessException " + e);
            return false;
        }
    }

    /* renamed from: c */
    public static boolean m1307c(Context context, int i) {
        try {
            return ((Integer) m1301b(context).getCameraCharacteristics(m1308c(context)[i]).get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0;
        } catch (CameraAccessException e) {
            C2711g.Log(6, "Camera2: CameraAccessException " + e);
            return false;
        }
    }

    /* renamed from: c */
    private static String[] m1308c(Context context) {
        if (f1767c == null) {
            try {
                f1767c = m1301b(context).getCameraIdList();
            } catch (CameraAccessException e) {
                C2711g.Log(6, "Camera2: CameraAccessException " + e);
                f1767c = new String[0];
            }
        }
        return f1767c;
    }

    /* renamed from: d */
    public static int[] m1310d(Context context, int i) {
        try {
            Size[] m1300a = m1300a(m1301b(context).getCameraCharacteristics(m1308c(context)[i]));
            if (m1300a == null) {
                return null;
            }
            int[] iArr = new int[m1300a.length * 2];
            for (int i2 = 0; i2 < m1300a.length; i2++) {
                int i3 = i2 * 2;
                iArr[i3] = m1300a[i2].getWidth();
                iArr[i3 + 1] = m1300a[i2].getHeight();
            }
            return iArr;
        } catch (CameraAccessException e) {
            C2711g.Log(6, "Camera2: CameraAccessException " + e);
            return null;
        }
    }

    /* renamed from: g */
    private void m1314g() {
        HandlerThread handlerThread = new HandlerThread("CameraBackground");
        this.f1775f = handlerThread;
        handlerThread.start();
        this.f1776g = new Handler(this.f1775f.getLooper());
    }

    /* renamed from: h */
    private void m1317h() {
        this.f1775f.quit();
        try {
            this.f1775f.join(4000L);
            this.f1775f = null;
            this.f1776g = null;
        } catch (InterruptedException e) {
            this.f1775f.interrupt();
            C2711g.Log(6, "Camera2: Interrupted while waiting for the background thread to finish " + e);
        }
    }

    /* renamed from: i */
    private void m1319i() {
        try {
            if (!f1768e.tryAcquire(4L, TimeUnit.SECONDS)) {
                C2711g.Log(5, "Camera2: Timeout waiting to lock camera for closing.");
                return;
            }
            this.f1774d.close();
            try {
                if (!f1768e.tryAcquire(4L, TimeUnit.SECONDS)) {
                    C2711g.Log(5, "Camera2: Timeout waiting to close camera.");
                }
            } catch (InterruptedException e) {
                C2711g.Log(6, "Camera2: Interrupted while waiting to close camera " + e);
            }
            this.f1774d = null;
            f1768e.release();
        } catch (InterruptedException e2) {
            C2711g.Log(6, "Camera2: Interrupted while trying to lock camera for closing " + e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m1320j() {
        try {
            if (this.f1780k != 0) {
                float f = this.f1781l;
                if (f >= 0.0f && f <= 1.0f) {
                    float f2 = this.f1782m;
                    if (f2 >= 0.0f && f2 <= 1.0f) {
                        this.f1785p = true;
                        int width = this.f1778i.width();
                        int i = (int) (((width - (r2 * 2)) * this.f1781l) + this.f1783n);
                        int height = this.f1778i.height();
                        int i2 = (int) (((height - (r3 * 2)) * (1.0d - this.f1782m)) + this.f1784o);
                        int max = Math.max(this.f1779j + 1, Math.min(i, (this.f1778i.width() - this.f1779j) - 1));
                        int max2 = Math.max(this.f1779j + 1, Math.min(i2, (this.f1778i.height() - this.f1779j) - 1));
                        CaptureRequest.Builder builder = this.f1789t;
                        CaptureRequest.Key key = CaptureRequest.CONTROL_AF_REGIONS;
                        int i3 = this.f1779j;
                        builder.set(key, new MeteringRectangle[]{new MeteringRectangle(max - i3, max2 - i3, i3 * 2, i3 * 2, 999)});
                        this.f1789t.set(CaptureRequest.CONTROL_AF_MODE, 1);
                        this.f1789t.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
                        this.f1789t.setTag("Focus");
                        this.f1790u.capture(this.f1789t.build(), this.f1769A, this.f1776g);
                        return;
                    }
                }
            }
            this.f1789t.set(CaptureRequest.CONTROL_AF_MODE, 4);
            this.f1789t.setTag("Regular");
            CameraCaptureSession cameraCaptureSession = this.f1790u;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.setRepeatingRequest(this.f1789t.build(), this.f1769A, this.f1776g);
            }
        } catch (CameraAccessException e) {
            C2711g.Log(6, "Camera2: CameraAccessException " + e);
        }
    }

    /* renamed from: k */
    private void m1321k() {
        try {
            CameraCaptureSession cameraCaptureSession = this.f1790u;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.stopRepeating();
                this.f1789t.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
                this.f1789t.set(CaptureRequest.CONTROL_AF_MODE, 0);
                this.f1789t.setTag("Cancel focus");
                this.f1790u.capture(this.f1789t.build(), this.f1769A, this.f1776g);
            }
        } catch (CameraAccessException e) {
            C2711g.Log(6, "Camera2: CameraAccessException " + e);
        }
    }

    /* renamed from: a */
    public final Rect m1322a() {
        return this.f1777h;
    }

    /* renamed from: a */
    public final boolean m1323a(float f, float f2) {
        if (this.f1780k <= 0) {
            return false;
        }
        if (this.f1785p) {
            C2711g.Log(5, "Camera2: Setting manual focus point already started.");
            return false;
        }
        this.f1781l = f;
        this.f1782m = f2;
        synchronized (this.f1791v) {
            if (this.f1790u != null && this.f1795z != a.f1802b) {
                m1321k();
            }
        }
        return true;
    }

    /* renamed from: a */
    public final boolean m1324a(Context context, int i, int i2, int i3, int i4, int i5) {
        try {
            CameraCharacteristics cameraCharacteristics = f1766b.getCameraCharacteristics(m1308c(context)[i]);
            if (((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2) {
                C2711g.Log(5, "Camera2: only LEGACY hardware level is supported.");
                return false;
            }
            Size[] m1300a = m1300a(cameraCharacteristics);
            if (m1300a != null && m1300a.length != 0) {
                this.f1777h = m1291a(m1300a, i2, i3);
                Range[] rangeArr = (Range[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
                if (rangeArr != null && rangeArr.length != 0) {
                    int m1290a = m1290a(rangeArr, i4);
                    this.f1786q = new Range(Integer.valueOf(m1290a), Integer.valueOf(m1290a));
                    try {
                        if (!f1768e.tryAcquire(4L, TimeUnit.SECONDS)) {
                            C2711g.Log(5, "Camera2: Timeout waiting to lock camera for opening.");
                            return false;
                        }
                        try {
                            f1766b.openCamera(m1308c(context)[i], this.f1770B, this.f1776g);
                            try {
                            } catch (InterruptedException e) {
                                C2711g.Log(6, "Camera2: Interrupted while waiting to open camera " + e);
                            }
                            if (!f1768e.tryAcquire(4L, TimeUnit.SECONDS)) {
                                C2711g.Log(5, "Camera2: Timeout waiting to open camera.");
                                return false;
                            }
                            f1768e.release();
                            this.f1792w = i5;
                            m1303b(cameraCharacteristics);
                            return this.f1774d != null;
                        } catch (CameraAccessException e2) {
                            C2711g.Log(6, "Camera2: CameraAccessException " + e2);
                            f1768e.release();
                            return false;
                        }
                    } catch (InterruptedException e3) {
                        C2711g.Log(6, "Camera2: Interrupted while trying to lock camera for opening " + e3);
                        return false;
                    }
                }
                C2711g.Log(6, "Camera2: target FPS ranges are not avialable.");
            }
            return false;
        } catch (CameraAccessException e4) {
            C2711g.Log(6, "Camera2: CameraAccessException " + e4);
            return false;
        }
    }

    /* renamed from: b */
    public final void m1325b() {
        if (this.f1774d != null) {
            m1328e();
            m1319i();
            this.f1769A = null;
            this.f1794y = null;
            this.f1793x = null;
            Image image = this.f1788s;
            if (image != null) {
                image.close();
                this.f1788s = null;
            }
            ImageReader imageReader = this.f1787r;
            if (imageReader != null) {
                imageReader.close();
                this.f1787r = null;
            }
        }
        m1317h();
    }

    /* renamed from: c */
    public final void m1326c() {
        if (this.f1787r == null) {
            ImageReader newInstance = ImageReader.newInstance(this.f1777h.width(), this.f1777h.height(), 35, 2);
            this.f1787r = newInstance;
            newInstance.setOnImageAvailableListener(this.f1771C, this.f1776g);
            this.f1788s = null;
            if (this.f1792w != 0) {
                SurfaceTexture surfaceTexture = new SurfaceTexture(this.f1792w);
                this.f1793x = surfaceTexture;
                surfaceTexture.setDefaultBufferSize(this.f1777h.width(), this.f1777h.height());
                this.f1793x.setOnFrameAvailableListener(this.f1772D, this.f1776g);
                this.f1794y = new Surface(this.f1793x);
            }
        }
        try {
            if (this.f1790u == null) {
                CameraDevice cameraDevice = this.f1774d;
                Surface surface = this.f1794y;
                cameraDevice.createCaptureSession(surface != null ? Arrays.asList(surface, this.f1787r.getSurface()) : Arrays.asList(this.f1787r.getSurface()), new CameraCaptureSession.StateCallback() { // from class: com.unity3d.player.b.2
                    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                    public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                        C2711g.Log(6, "Camera2: CaptureSession configuration failed.");
                    }

                    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                    public final void onConfigured(CameraCaptureSession cameraCaptureSession) {
                        if (C2706b.this.f1774d == null) {
                            return;
                        }
                        synchronized (C2706b.this.f1791v) {
                            C2706b.this.f1790u = cameraCaptureSession;
                            try {
                                C2706b c2706b = C2706b.this;
                                c2706b.f1789t = c2706b.f1774d.createCaptureRequest(1);
                                if (C2706b.this.f1794y != null) {
                                    C2706b.this.f1789t.addTarget(C2706b.this.f1794y);
                                }
                                C2706b.this.f1789t.addTarget(C2706b.this.f1787r.getSurface());
                                C2706b.this.f1789t.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, C2706b.this.f1786q);
                                C2706b.this.m1320j();
                            } catch (CameraAccessException e) {
                                C2711g.Log(6, "Camera2: CameraAccessException " + e);
                            }
                        }
                    }
                }, this.f1776g);
            } else if (this.f1795z == a.f1802b) {
                this.f1790u.setRepeatingRequest(this.f1789t.build(), this.f1769A, this.f1776g);
            }
            this.f1795z = a.f1801a;
        } catch (CameraAccessException e) {
            C2711g.Log(6, "Camera2: CameraAccessException " + e);
        }
    }

    /* renamed from: d */
    public final void m1327d() {
        synchronized (this.f1791v) {
            CameraCaptureSession cameraCaptureSession = this.f1790u;
            if (cameraCaptureSession != null) {
                try {
                    cameraCaptureSession.stopRepeating();
                    this.f1795z = a.f1802b;
                } catch (CameraAccessException e) {
                    C2711g.Log(6, "Camera2: CameraAccessException " + e);
                }
            }
        }
    }

    /* renamed from: e */
    public final void m1328e() {
        synchronized (this.f1791v) {
            CameraCaptureSession cameraCaptureSession = this.f1790u;
            if (cameraCaptureSession != null) {
                try {
                    cameraCaptureSession.abortCaptures();
                } catch (CameraAccessException e) {
                    C2711g.Log(6, "Camera2: CameraAccessException " + e);
                }
                this.f1790u.close();
                this.f1790u = null;
                this.f1795z = a.f1803c;
            }
        }
    }
}
