package com.tencent.p014av.ptt;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import com.luck.picture.lib.config.PictureMimeType;
import com.tencent.p014av.utils.QLog;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class Recorder {
    private static final int ABNORMAL_NO_DATA = 0;
    private static final int AUDIO_FORMAT = 2;
    private static final int CHANNEL_CONFIG = 16;
    private static final int DEFAULT_FRAME_LENGTH_PER_READ = 800;
    private static final int DEFAULT_RECORDER_INIT_BUFFER_SIZE = 20480;
    private static final int FRAME_LENGTH_160 = 160;
    private static final int INVALID_VALUE = -1;
    private static final int SAMPLE_RATE_IN_HZ = 16000;
    private static final String TAG = "Recorder";
    public static final int VOLUME_STATE_INIT = 0;
    public static final int VOLUME_STATE_LOW = 1;
    public static final int VOLUME_STATE_NORMAL = 2;
    private static int weatherRequest;
    private Context context;
    private String deviceInfo;
    AudioManager mAudioManager;
    private int mAudioSource;
    private int mMode;
    private RingBuffer ringBuffer;
    AudioRecord mRecord = null;
    private int recorderInitBufSize = DEFAULT_RECORDER_INIT_BUFFER_SIZE;
    private int frameLengthPerRead = DEFAULT_FRAME_LENGTH_PER_READ;
    public int recordLevel = 0;
    public int recordGain = 100;
    private int MAX_NO_DATA_TIME_MS = 1000;
    private int MAX_RECORD_TIME = 59000;
    private int MIN_RECORD_TIME = 0;
    private long maxRecorderTime = 59000;
    public String pcmFilePath = null;
    private String DEVICE_VIVO = "vivo";
    private String DEVICE_OPPO = "oppo";
    private String DEVICE_BBK = "bbk";
    private String DEVICE_MEIZU = "meizu";
    private int IGNORE_DATA_FRAME_NORMAL = 10;
    private int IGNORE_DATA_FRAME_MEIZU = 15;
    private OnQQRecorderListener mListener = null;
    RecordThread recordThread = null;

    /* loaded from: classes3.dex */
    public interface OnQQRecorderListener {
        void onBeginReceiveData();

        void onReceiveRecordData(byte[] bArr);

        void onRecorderAbnormal(int i);

        void onRecorderEnd();

        void onRecorderError(String str, String str2);

        void onRecorderFailed(String str, int i);

        void onRecorderPrepare(String str);

        void onRecorderStart();
    }

    /* loaded from: classes3.dex */
    public interface RECORDER_FAILED_REASON {
        public static final int AudioRecordNotInit = 4;
        public static final int AudioTooShort = 5;
        public static final int CreateFileFailed = 2;
        public static final int InitAudioRecordError = 3;
        public static final int RecorderNotReady = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeProcess(byte[] bArr, int i, int i2, int i3, int i4);

    public void setQQRecorderListener(OnQQRecorderListener onQQRecorderListener) {
        QLog.m602i(TAG, "setQQRecorderListener");
        if (onQQRecorderListener == null) {
            QLog.m602i(TAG, "setQQRecorderListener--> listener is null");
        } else {
            this.mListener = onQQRecorderListener;
        }
    }

    public void setMaxRecorderTime(int i) {
        int i2 = this.MAX_RECORD_TIME;
        if (i <= i2) {
            this.maxRecorderTime = i;
        } else {
            this.maxRecorderTime = i2;
        }
    }

    public Recorder(Context context) {
        this.mAudioManager = null;
        this.mMode = -1;
        this.mAudioSource = -1;
        this.deviceInfo = "";
        this.ringBuffer = null;
        this.context = context;
        this.mAudioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        this.ringBuffer = new RingBuffer(6400);
        this.deviceInfo = Build.MANUFACTURER;
        QLog.m602i(TAG, "device info = " + this.deviceInfo);
        this.mMode = 0;
        this.mAudioSource = 0;
        QLog.m602i(TAG, "mMode = " + this.mMode + " | mAudioSource = " + this.mAudioSource + " | recorderBufSize = " + this.recorderInitBufSize + " | readLength = " + this.frameLengthPerRead);
    }

    private void initBufferSize(int i, int i2, int i3) {
        if (i != -1) {
            int minBufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ, 16, 2);
            if (minBufferSize != -2 && minBufferSize != -1) {
                this.recorderInitBufSize = minBufferSize * i;
            }
        } else if (i2 != -1) {
            this.recorderInitBufSize = i2;
        }
        if (i3 != -1) {
            this.frameLengthPerRead = i3;
        }
    }

    public void start() {
        QLog.m602i(TAG, "QQRecord Start");
        if (this.recordThread == null) {
            this.recordThread = new RecordThread();
            TraeJni.getInstance().initTRAE();
            this.recordThread.start();
        } else {
            QLog.m602i(TAG, "Record :start --> Record is Not Ready");
            OnQQRecorderListener onQQRecorderListener = this.mListener;
            if (onQQRecorderListener != null) {
                onQQRecorderListener.onRecorderFailed("", 1);
            }
        }
    }

    public void stop() {
        QLog.m602i(TAG, "QQRecord Stop");
        RecordThread recordThread = this.recordThread;
        if (recordThread != null) {
            recordThread.isRunning = false;
        }
    }

    private void requestRecordPermission() {
        try {
            QLog.m606w(TAG, "requestRecordPermission");
            Context context = this.context;
            if (context != null) {
                SharedPreferences.Editor edit = context.getSharedPreferences("GMEApplyForAudioRecord", 0).edit();
                edit.putInt("GMEApplyForAudioRecord", 1);
                edit.apply();
                if (weatherRequest == 0) {
                    Context context2 = this.context;
                    if (context2 instanceof Activity) {
                        weatherRequest = 1;
                        Activity activity = (Activity) context2;
                        if (activity.checkSelfPermission("android.permission.RECORD_AUDIO") != 0) {
                            activity.requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 101);
                        }
                    }
                }
            }
        } catch (Exception unused) {
            QLog.m600e(TAG, "requestPermissions error");
        }
    }

    public boolean initRecording() {
        requestRecordPermission();
        AudioRecord audioRecord = this.mRecord;
        if (audioRecord != null) {
            audioRecord.release();
            this.mRecord = null;
        }
        AudioManager audioManager = this.mAudioManager;
        QLog.m602i(TAG, "Record :startRecording | audio mode = " + (audioManager != null ? audioManager.getMode() : 0));
        if (this.mAudioManager != null && this.mMode != -1) {
            QLog.m602i(TAG, "Record :initRecording --> SetMode ,mode = " + this.mMode);
            this.mAudioManager.setMode(this.mMode);
        }
        if (this.mRecord == null) {
            QLog.m602i(TAG, "Record: new AudioRecord --> mAudioSource = " + this.mAudioSource + " ,SAMPLE_RATE_IN_HZ =16000 ,CHANNEL_CONFIG = 16 ,AUDIO_FORMAT =2 ,recorderInitBufSize = " + this.recorderInitBufSize);
            try {
                this.mRecord = new AudioRecord(this.mAudioSource, SAMPLE_RATE_IN_HZ, 16, 2, this.recorderInitBufSize);
            } catch (IllegalArgumentException e) {
                QLog.m603i(TAG, "Record : new AudioRecord Failed:" + Log.getStackTraceString(e), e);
                return false;
            }
        }
        if (this.mRecord.getState() == 1) {
            return true;
        }
        QLog.m602i(TAG, "Record State = " + this.mRecord.getState());
        AudioRecord audioRecord2 = this.mRecord;
        if (audioRecord2 != null) {
            audioRecord2.release();
        }
        this.mRecord = null;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class RecordThread extends Thread {
        public volatile boolean isRunning = true;

        public RecordThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int i;
            int i2;
            StringBuilder sb;
            byte[] bArr;
            int i3;
            QLog.m602i(Recorder.TAG, "RecordThread Start : " + this);
            try {
                try {
                    if (Recorder.this.mListener != null) {
                        Recorder.this.mListener.onRecorderPrepare("");
                    }
                    bArr = new byte[Recorder.this.frameLengthPerRead];
                } catch (Exception e) {
                    e = e;
                    i2 = 0;
                } catch (Throwable th) {
                    th = th;
                    i = 0;
                }
                if (Recorder.this.mRecord != null) {
                    Recorder.this.mRecord.startRecording();
                    if (Recorder.this.mListener != null) {
                        Recorder.this.mListener.onRecorderStart();
                    }
                    long j = 0;
                    long j2 = 0;
                    long j3 = 0;
                    boolean z = false;
                    byte[] bArr2 = null;
                    int i4 = 0;
                    while (this.isRunning) {
                        try {
                            if (j2 != j && SystemClock.elapsedRealtime() - j2 > Recorder.this.MAX_NO_DATA_TIME_MS && !z) {
                                if (j3 == 0 && Recorder.this.mListener != null) {
                                    Recorder.this.mListener.onRecorderAbnormal(0);
                                    this.isRunning = false;
                                }
                                z = true;
                            }
                            if (bArr2 != null) {
                                i3 = bArr2.length;
                                System.arraycopy(bArr2, 0, bArr, 0, i3);
                            } else {
                                i3 = 0;
                            }
                            int read = Recorder.this.mRecord.read(bArr, i3, Recorder.this.frameLengthPerRead - i3);
                            if (j2 == j) {
                                j2 = SystemClock.elapsedRealtime();
                            }
                            if (Recorder.this.frameLengthPerRead - i3 != read) {
                                QLog.m602i(Recorder.TAG, " Recording --> Request Size = " + (Recorder.this.frameLengthPerRead - i3) + " ,Really Size = " + read);
                            }
                            if (Recorder.this.mListener != null) {
                                Recorder.this.mListener.onBeginReceiveData();
                            }
                            if (read <= 0) {
                                Thread.sleep(10L);
                            } else {
                                j3 += read;
                                int i5 = read + i3;
                                int i6 = i5 % 160;
                                if (i6 != 0) {
                                    byte[] bArr3 = new byte[i6];
                                    i5 -= i6;
                                    System.arraycopy(bArr, i5, bArr3, 0, i6);
                                    bArr2 = bArr3;
                                } else {
                                    bArr2 = null;
                                }
                                Recorder.this.ringBuffer.Push(bArr, i5);
                                int i7 = 640;
                                byte[] bArr4 = new byte[640];
                                while (Recorder.this.ringBuffer.RemainRead() >= i7) {
                                    if (Recorder.this.ringBuffer.Pop(bArr4, i7)) {
                                        i4++;
                                        if ((Recorder.this.deviceInfo.equalsIgnoreCase(Recorder.this.DEVICE_MEIZU) && i4 <= Recorder.this.IGNORE_DATA_FRAME_MEIZU) || i4 <= Recorder.this.IGNORE_DATA_FRAME_NORMAL) {
                                            Arrays.fill(bArr4, (byte) 0);
                                        }
                                        Recorder recorder = Recorder.this;
                                        recorder.recordLevel = recorder.nativeProcess(bArr4, 640, recorder.recordGain, Recorder.SAMPLE_RATE_IN_HZ, 1);
                                        byte[] turnSilk = TraeJni.getInstance().turnSilk(bArr4, 640L);
                                        byte[] bArr5 = new byte[turnSilk.length + 2];
                                        bArr5[0] = (byte) (turnSilk.length & 255);
                                        bArr5[1] = (byte) ((turnSilk.length >>> 8) & 255);
                                        System.arraycopy(turnSilk, 0, bArr5, 2, turnSilk.length);
                                        Recorder.this.mListener.onReceiveRecordData(bArr5);
                                        j2 = j2;
                                        i7 = 640;
                                    }
                                }
                            }
                            j = 0;
                        } catch (Exception e2) {
                            e = e2;
                            i2 = i4;
                        } catch (Throwable th2) {
                            th = th2;
                            i = i4;
                            QLog.m602i(Recorder.TAG, "record silk count = " + i);
                            Recorder.this.releaseRecordThreadResource();
                            throw th;
                        }
                    }
                    if (SystemClock.elapsedRealtime() - j2 > Recorder.this.MIN_RECORD_TIME && j2 != 0) {
                        if (Recorder.this.mListener != null) {
                            Recorder.this.mListener.onRecorderEnd();
                        }
                        if (Recorder.this.mAudioManager != null && Recorder.this.mMode != -1) {
                            Recorder.this.mAudioManager.setMode(0);
                        }
                        sb = new StringBuilder("record silk count = ");
                        sb.append(i4);
                        QLog.m602i(Recorder.TAG, sb.toString());
                        Recorder.this.releaseRecordThreadResource();
                    }
                    if (Recorder.this.mListener != null) {
                        Recorder.this.mListener.onRecorderFailed(null, 5);
                    }
                    if (Recorder.this.mAudioManager != null) {
                        Recorder.this.mAudioManager.setMode(0);
                    }
                    sb = new StringBuilder("record silk count = ");
                    sb.append(i4);
                    QLog.m602i(Recorder.TAG, sb.toString());
                    Recorder.this.releaseRecordThreadResource();
                }
                i2 = 0;
                try {
                    if (Recorder.this.mListener != null) {
                        Recorder.this.mListener.onRecorderFailed("", 4);
                    }
                    Recorder.this.releaseRecordThreadResource();
                    QLog.m602i(Recorder.TAG, "record silk count = 0");
                    Recorder.this.releaseRecordThreadResource();
                    return;
                } catch (Exception e3) {
                    e = e3;
                }
                QLog.m603i(Recorder.TAG, "record Exception: " + Log.getStackTraceString(e), e);
                if (Recorder.this.mListener != null) {
                    Recorder.this.mListener.onRecorderError("", e.getMessage());
                }
                sb = new StringBuilder("record silk count = ");
                sb.append(i2);
                QLog.m602i(Recorder.TAG, sb.toString());
                Recorder.this.releaseRecordThreadResource();
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseRecordThreadResource() {
        AudioRecord audioRecord = this.mRecord;
        if (audioRecord != null) {
            audioRecord.release();
        }
        this.mRecord = null;
        this.recordLevel = 0;
        this.recordThread = null;
    }

    public boolean isStop() {
        if (this.recordThread == null) {
            return true;
        }
        return !r0.isRunning;
    }

    public boolean isRecording() {
        return this.recordThread != null;
    }
}
