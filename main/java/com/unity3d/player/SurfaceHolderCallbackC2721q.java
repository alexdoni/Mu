package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.MediaController;
import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: com.unity3d.player.q */
/* loaded from: classes3.dex */
public final class SurfaceHolderCallbackC2721q extends FrameLayout implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl {

    /* renamed from: a */
    private static boolean f1855a = false;

    /* renamed from: b */
    private final Context f1856b;

    /* renamed from: c */
    private final SurfaceView f1857c;

    /* renamed from: d */
    private final SurfaceHolder f1858d;

    /* renamed from: e */
    private final String f1859e;

    /* renamed from: f */
    private final int f1860f;

    /* renamed from: g */
    private final int f1861g;

    /* renamed from: h */
    private final boolean f1862h;

    /* renamed from: i */
    private final long f1863i;

    /* renamed from: j */
    private final long f1864j;

    /* renamed from: k */
    private final FrameLayout f1865k;

    /* renamed from: l */
    private final Display f1866l;

    /* renamed from: m */
    private int f1867m;

    /* renamed from: n */
    private int f1868n;

    /* renamed from: o */
    private int f1869o;

    /* renamed from: p */
    private int f1870p;

    /* renamed from: q */
    private MediaPlayer f1871q;

    /* renamed from: r */
    private MediaController f1872r;

    /* renamed from: s */
    private boolean f1873s;

    /* renamed from: t */
    private boolean f1874t;

    /* renamed from: u */
    private int f1875u;

    /* renamed from: v */
    private boolean f1876v;

    /* renamed from: w */
    private boolean f1877w;

    /* renamed from: x */
    private a f1878x;

    /* renamed from: y */
    private b f1879y;

    /* renamed from: z */
    private volatile int f1880z;

    /* renamed from: com.unity3d.player.q$a */
    /* loaded from: classes3.dex */
    public interface a {
        /* renamed from: a */
        void mo1373a(int i);
    }

    /* renamed from: com.unity3d.player.q$b */
    /* loaded from: classes3.dex */
    public class b implements Runnable {

        /* renamed from: b */
        private SurfaceHolderCallbackC2721q f1882b;

        /* renamed from: c */
        private boolean f1883c = false;

        public b(SurfaceHolderCallbackC2721q surfaceHolderCallbackC2721q) {
            this.f1882b = surfaceHolderCallbackC2721q;
        }

        /* renamed from: a */
        public final void m1374a() {
            this.f1883c = true;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
            if (this.f1883c) {
                return;
            }
            if (SurfaceHolderCallbackC2721q.f1855a) {
                SurfaceHolderCallbackC2721q.m1368b("Stopping the video player due to timeout.");
            }
            this.f1882b.CancelOnPrepare();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SurfaceHolderCallbackC2721q(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, a aVar) {
        super(context);
        this.f1873s = false;
        this.f1874t = false;
        this.f1875u = 0;
        this.f1876v = false;
        this.f1877w = false;
        this.f1880z = 0;
        this.f1878x = aVar;
        this.f1856b = context;
        this.f1865k = this;
        SurfaceView surfaceView = new SurfaceView(context);
        this.f1857c = surfaceView;
        SurfaceHolder holder = surfaceView.getHolder();
        this.f1858d = holder;
        holder.addCallback(this);
        setBackgroundColor(i);
        addView(surfaceView);
        this.f1866l = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        this.f1859e = str;
        this.f1860f = i2;
        this.f1861g = i3;
        this.f1862h = z;
        this.f1863i = j;
        this.f1864j = j2;
        if (f1855a) {
            m1368b("fileName: " + str);
        }
        if (f1855a) {
            m1368b("backgroundColor: " + i);
        }
        if (f1855a) {
            m1368b("controlMode: " + i2);
        }
        if (f1855a) {
            m1368b("scalingMode: " + i3);
        }
        if (f1855a) {
            m1368b("isURL: " + z);
        }
        if (f1855a) {
            m1368b("videoOffset: " + j);
        }
        if (f1855a) {
            m1368b("videoLength: " + j2);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    /* renamed from: a */
    private void m1366a(int i) {
        this.f1880z = i;
        a aVar = this.f1878x;
        if (aVar != null) {
            aVar.mo1373a(this.f1880z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m1368b(String str) {
        Log.i("Video", "VideoPlayer: " + str);
    }

    /* renamed from: c */
    private void m1370c() {
        FileInputStream fileInputStream;
        MediaPlayer mediaPlayer = this.f1871q;
        if (mediaPlayer != null) {
            mediaPlayer.setDisplay(this.f1858d);
            if (this.f1876v) {
                return;
            }
            if (f1855a) {
                m1368b("Resuming playback");
            }
            this.f1871q.start();
            return;
        }
        m1366a(0);
        doCleanUp();
        try {
            MediaPlayer mediaPlayer2 = new MediaPlayer();
            this.f1871q = mediaPlayer2;
            if (this.f1862h) {
                mediaPlayer2.setDataSource(this.f1856b, Uri.parse(this.f1859e));
            } else {
                if (this.f1864j != 0) {
                    fileInputStream = new FileInputStream(this.f1859e);
                    this.f1871q.setDataSource(fileInputStream.getFD(), this.f1863i, this.f1864j);
                } else {
                    try {
                        AssetFileDescriptor openFd = getResources().getAssets().openFd(this.f1859e);
                        this.f1871q.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                        openFd.close();
                    } catch (IOException unused) {
                        fileInputStream = new FileInputStream(this.f1859e);
                        this.f1871q.setDataSource(fileInputStream.getFD());
                    }
                }
                fileInputStream.close();
            }
            this.f1871q.setDisplay(this.f1858d);
            this.f1871q.setScreenOnWhilePlaying(true);
            this.f1871q.setOnBufferingUpdateListener(this);
            this.f1871q.setOnCompletionListener(this);
            this.f1871q.setOnPreparedListener(this);
            this.f1871q.setOnVideoSizeChangedListener(this);
            this.f1871q.setAudioStreamType(3);
            this.f1871q.prepareAsync();
            this.f1879y = new b(this);
            new Thread(this.f1879y).start();
        } catch (Exception e) {
            if (f1855a) {
                m1368b("error: " + e.getMessage() + e);
            }
            m1366a(2);
        }
    }

    /* renamed from: d */
    private void m1371d() {
        if (isPlaying()) {
            return;
        }
        m1366a(1);
        if (f1855a) {
            m1368b("startVideoPlayback");
        }
        updateVideoLayout();
        if (this.f1876v) {
            return;
        }
        start();
    }

    public final void CancelOnPrepare() {
        m1366a(2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m1372a() {
        return this.f1876v;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final boolean canPause() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final boolean canSeekBackward() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final boolean canSeekForward() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void destroyPlayer() {
        if (f1855a) {
            m1368b("destroyPlayer");
        }
        if (!this.f1876v) {
            pause();
        }
        doCleanUp();
    }

    protected final void doCleanUp() {
        b bVar = this.f1879y;
        if (bVar != null) {
            bVar.m1374a();
            this.f1879y = null;
        }
        MediaPlayer mediaPlayer = this.f1871q;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.f1871q = null;
        }
        this.f1869o = 0;
        this.f1870p = 0;
        this.f1874t = false;
        this.f1873s = false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final int getAudioSessionId() {
        MediaPlayer mediaPlayer = this.f1871q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getAudioSessionId();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final int getBufferPercentage() {
        if (this.f1862h) {
            return this.f1875u;
        }
        return 100;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final int getCurrentPosition() {
        MediaPlayer mediaPlayer = this.f1871q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCurrentPosition();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final int getDuration() {
        MediaPlayer mediaPlayer = this.f1871q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getDuration();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final boolean isPlaying() {
        boolean z = this.f1874t && this.f1873s;
        MediaPlayer mediaPlayer = this.f1871q;
        return mediaPlayer == null ? !z : mediaPlayer.isPlaying() || !z;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (f1855a) {
            m1368b("onBufferingUpdate percent:" + i);
        }
        this.f1875u = i;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (f1855a) {
            m1368b("onCompletion called");
        }
        destroyPlayer();
        m1366a(3);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 && (this.f1860f != 2 || i == 0 || keyEvent.isSystem())) {
            MediaController mediaController = this.f1872r;
            return mediaController != null ? mediaController.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
        }
        destroyPlayer();
        m1366a(3);
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (f1855a) {
            m1368b("onPrepared called");
        }
        b bVar = this.f1879y;
        if (bVar != null) {
            bVar.m1374a();
            this.f1879y = null;
        }
        int i = this.f1860f;
        if (i == 0 || i == 1) {
            MediaController mediaController = new MediaController(this.f1856b);
            this.f1872r = mediaController;
            mediaController.setMediaPlayer(this);
            this.f1872r.setAnchorView(this);
            this.f1872r.setEnabled(true);
            Context context = this.f1856b;
            if (context instanceof Activity) {
                this.f1872r.setSystemUiVisibility(((Activity) context).getWindow().getDecorView().getSystemUiVisibility());
            }
            this.f1872r.show();
        }
        this.f1874t = true;
        if (this.f1873s) {
            m1371d();
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f1860f != 2 || action != 0) {
            MediaController mediaController = this.f1872r;
            return mediaController != null ? mediaController.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
        }
        destroyPlayer();
        m1366a(3);
        return true;
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (f1855a) {
            m1368b("onVideoSizeChanged called " + i + "x" + i2);
        }
        if (i != 0 && i2 != 0) {
            this.f1873s = true;
            this.f1869o = i;
            this.f1870p = i2;
            if (this.f1874t) {
                m1371d();
                return;
            }
            return;
        }
        if (f1855a) {
            m1368b("invalid video width(" + i + ") or height(" + i2 + ")");
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final void pause() {
        MediaPlayer mediaPlayer = this.f1871q;
        if (mediaPlayer == null) {
            return;
        }
        if (this.f1877w) {
            mediaPlayer.pause();
        }
        this.f1876v = true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final void seekTo(int i) {
        MediaPlayer mediaPlayer = this.f1871q;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.seekTo(i);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final void start() {
        if (f1855a) {
            m1368b("Start");
        }
        MediaPlayer mediaPlayer = this.f1871q;
        if (mediaPlayer == null) {
            return;
        }
        if (this.f1877w) {
            mediaPlayer.start();
        }
        this.f1876v = false;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (f1855a) {
            m1368b("surfaceChanged called " + i + " " + i2 + "x" + i3);
        }
        if (this.f1867m == i2 && this.f1868n == i3) {
            return;
        }
        this.f1867m = i2;
        this.f1868n = i3;
        if (this.f1877w) {
            updateVideoLayout();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (f1855a) {
            m1368b("surfaceCreated called");
        }
        this.f1877w = true;
        m1370c();
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (f1855a) {
            m1368b("surfaceDestroyed called");
        }
        this.f1877w = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004d, code lost:
    
        if (r5 <= r3) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004f, code lost:
    
        r1 = (int) (r0 / r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0053, code lost:
    
        r0 = (int) (r1 * r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x005c, code lost:
    
        if (r5 >= r3) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateVideoLayout() {
        /*
            r8 = this;
            boolean r0 = com.unity3d.player.SurfaceHolderCallbackC2721q.f1855a
            if (r0 == 0) goto L9
            java.lang.String r0 = "updateVideoLayout"
            m1368b(r0)
        L9:
            android.media.MediaPlayer r0 = r8.f1871q
            if (r0 != 0) goto Le
            return
        Le:
            int r0 = r8.f1867m
            if (r0 == 0) goto L16
            int r0 = r8.f1868n
            if (r0 != 0) goto L34
        L16:
            android.content.Context r0 = r8.f1856b
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.util.DisplayMetrics r1 = new android.util.DisplayMetrics
            r1.<init>()
            android.view.Display r0 = r0.getDefaultDisplay()
            r0.getMetrics(r1)
            int r0 = r1.widthPixels
            r8.f1867m = r0
            int r0 = r1.heightPixels
            r8.f1868n = r0
        L34:
            int r0 = r8.f1867m
            int r1 = r8.f1868n
            boolean r2 = r8.f1873s
            if (r2 == 0) goto L64
            int r2 = r8.f1869o
            float r3 = (float) r2
            int r4 = r8.f1870p
            float r5 = (float) r4
            float r3 = r3 / r5
            float r5 = (float) r0
            float r6 = (float) r1
            float r5 = r5 / r6
            int r6 = r8.f1861g
            r7 = 1
            if (r6 != r7) goto L57
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 > 0) goto L53
        L4f:
            float r1 = (float) r0
            float r1 = r1 / r3
            int r1 = (int) r1
            goto L6d
        L53:
            float r0 = (float) r1
            float r0 = r0 * r3
            int r0 = (int) r0
            goto L6d
        L57:
            r7 = 2
            if (r6 != r7) goto L5f
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 < 0) goto L53
            goto L4f
        L5f:
            if (r6 != 0) goto L6d
            r0 = r2
            r1 = r4
            goto L6d
        L64:
            boolean r2 = com.unity3d.player.SurfaceHolderCallbackC2721q.f1855a
            if (r2 == 0) goto L6d
            java.lang.String r2 = "updateVideoLayout: Video size is not known yet"
            m1368b(r2)
        L6d:
            int r2 = r8.f1867m
            if (r2 != r0) goto L75
            int r2 = r8.f1868n
            if (r2 == r1) goto La0
        L75:
            boolean r2 = com.unity3d.player.SurfaceHolderCallbackC2721q.f1855a
            if (r2 == 0) goto L92
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "frameWidth = "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r3 = "; frameHeight = "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            m1368b(r2)
        L92:
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r3 = 17
            r2.<init>(r0, r1, r3)
            android.widget.FrameLayout r0 = r8.f1865k
            android.view.SurfaceView r1 = r8.f1857c
            r0.updateViewLayout(r1, r2)
        La0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.SurfaceHolderCallbackC2721q.updateVideoLayout():void");
    }
}
