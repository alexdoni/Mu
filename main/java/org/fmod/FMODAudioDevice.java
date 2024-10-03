package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class FMODAudioDevice implements Runnable {

    /* renamed from: h */
    private static int f1967h = 0;

    /* renamed from: i */
    private static int f1968i = 1;

    /* renamed from: j */
    private static int f1969j = 2;

    /* renamed from: k */
    private static int f1970k = 3;

    /* renamed from: a */
    private volatile Thread f1971a = null;

    /* renamed from: b */
    private volatile boolean f1972b = false;

    /* renamed from: c */
    private AudioTrack f1973c = null;

    /* renamed from: d */
    private boolean f1974d = false;

    /* renamed from: e */
    private ByteBuffer f1975e = null;

    /* renamed from: f */
    private byte[] f1976f = null;

    /* renamed from: g */
    private volatile RunnableC3026a f1977g;

    private native int fmodGetInfo(int i);

    private native int fmodProcess(ByteBuffer byteBuffer);

    private void releaseAudioTrack() {
        AudioTrack audioTrack = this.f1973c;
        if (audioTrack != null) {
            if (audioTrack.getState() == 1) {
                this.f1973c.stop();
            }
            this.f1973c.release();
            this.f1973c = null;
        }
        this.f1975e = null;
        this.f1976f = null;
        this.f1974d = false;
    }

    public synchronized void close() {
        stop();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public native int fmodProcessMicData(ByteBuffer byteBuffer, int i);

    public boolean isRunning() {
        return this.f1971a != null && this.f1971a.isAlive();
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = 3;
        while (this.f1972b) {
            if (!this.f1974d && i > 0) {
                releaseAudioTrack();
                int fmodGetInfo = fmodGetInfo(f1967h);
                int round = Math.round(AudioTrack.getMinBufferSize(fmodGetInfo, 3, 2) * 1.1f) & (-4);
                int fmodGetInfo2 = fmodGetInfo(f1968i);
                int fmodGetInfo3 = fmodGetInfo(f1969j) * fmodGetInfo2 * 4;
                AudioTrack audioTrack = new AudioTrack(3, fmodGetInfo, 3, 2, fmodGetInfo3 > round ? fmodGetInfo3 : round, 1);
                this.f1973c = audioTrack;
                boolean z = audioTrack.getState() == 1;
                this.f1974d = z;
                if (z) {
                    ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fmodGetInfo2 * 2 * 2);
                    this.f1975e = allocateDirect;
                    this.f1976f = new byte[allocateDirect.capacity()];
                    this.f1973c.play();
                    i = 3;
                } else {
                    Log.e("FMOD", "AudioTrack failed to initialize (status " + this.f1973c.getState() + ")");
                    releaseAudioTrack();
                    i += -1;
                }
            }
            if (this.f1974d) {
                if (fmodGetInfo(f1970k) == 1) {
                    fmodProcess(this.f1975e);
                    ByteBuffer byteBuffer = this.f1975e;
                    byteBuffer.get(this.f1976f, 0, byteBuffer.capacity());
                    this.f1973c.write(this.f1976f, 0, this.f1975e.capacity());
                    this.f1975e.position(0);
                } else {
                    releaseAudioTrack();
                }
            }
        }
        releaseAudioTrack();
    }

    public synchronized void start() {
        if (this.f1971a != null) {
            stop();
        }
        this.f1971a = new Thread(this, "FMODAudioDevice");
        this.f1971a.setPriority(10);
        this.f1972b = true;
        this.f1971a.start();
        if (this.f1977g != null) {
            this.f1977g.m1446b();
        }
    }

    public synchronized int startAudioRecord(int i, int i2, int i3) {
        if (this.f1977g == null) {
            this.f1977g = new RunnableC3026a(this, i, i2);
            this.f1977g.m1446b();
        }
        return this.f1977g.m1445a();
    }

    public synchronized void stop() {
        while (this.f1971a != null) {
            this.f1972b = false;
            try {
                this.f1971a.join();
                this.f1971a = null;
            } catch (InterruptedException unused) {
            }
        }
        if (this.f1977g != null) {
            this.f1977g.m1447c();
        }
    }

    public synchronized void stopAudioRecord() {
        if (this.f1977g != null) {
            this.f1977g.m1447c();
            this.f1977g = null;
        }
    }
}
