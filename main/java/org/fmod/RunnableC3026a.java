package org.fmod;

import android.media.AudioRecord;
import android.util.Log;
import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: org.fmod.a */
/* loaded from: classes3.dex */
public final class RunnableC3026a implements Runnable {

    /* renamed from: a */
    private final FMODAudioDevice f1978a;

    /* renamed from: b */
    private final ByteBuffer f1979b;

    /* renamed from: c */
    private final int f1980c;

    /* renamed from: d */
    private final int f1981d;

    /* renamed from: e */
    private final int f1982e = 2;

    /* renamed from: f */
    private volatile Thread f1983f;

    /* renamed from: g */
    private volatile boolean f1984g;

    /* renamed from: h */
    private AudioRecord f1985h;

    /* renamed from: i */
    private boolean f1986i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3026a(FMODAudioDevice fMODAudioDevice, int i, int i2) {
        this.f1978a = fMODAudioDevice;
        this.f1980c = i;
        this.f1981d = i2;
        this.f1979b = ByteBuffer.allocateDirect(AudioRecord.getMinBufferSize(i, i2, 2));
    }

    /* renamed from: d */
    private void m1444d() {
        AudioRecord audioRecord = this.f1985h;
        if (audioRecord != null) {
            if (audioRecord.getState() == 1) {
                this.f1985h.stop();
            }
            this.f1985h.release();
            this.f1985h = null;
        }
        this.f1979b.position(0);
        this.f1986i = false;
    }

    /* renamed from: a */
    public final int m1445a() {
        return this.f1979b.capacity();
    }

    /* renamed from: b */
    public final void m1446b() {
        if (this.f1983f != null) {
            m1447c();
        }
        this.f1984g = true;
        this.f1983f = new Thread(this);
        this.f1983f.start();
    }

    /* renamed from: c */
    public final void m1447c() {
        while (this.f1983f != null) {
            this.f1984g = false;
            try {
                this.f1983f.join();
                this.f1983f = null;
            } catch (InterruptedException unused) {
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 3;
        while (this.f1984g) {
            if (!this.f1986i && i > 0) {
                m1444d();
                AudioRecord audioRecord = new AudioRecord(1, this.f1980c, this.f1981d, this.f1982e, this.f1979b.capacity());
                this.f1985h = audioRecord;
                boolean z = audioRecord.getState() == 1;
                this.f1986i = z;
                if (z) {
                    this.f1979b.position(0);
                    this.f1985h.startRecording();
                    i = 3;
                } else {
                    Log.e("FMOD", "AudioRecord failed to initialize (status " + this.f1985h.getState() + ")");
                    i += -1;
                    m1444d();
                }
            }
            if (this.f1986i && this.f1985h.getRecordingState() == 3) {
                AudioRecord audioRecord2 = this.f1985h;
                ByteBuffer byteBuffer = this.f1979b;
                this.f1978a.fmodProcessMicData(this.f1979b, audioRecord2.read(byteBuffer, byteBuffer.capacity()));
                this.f1979b.position(0);
            }
        }
        m1444d();
    }
}
