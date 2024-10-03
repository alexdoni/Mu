package com.tencent.sharpgme.jni;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import com.luck.picture.lib.config.PictureMimeType;
import com.tencent.p014av.utils.QLog;
import com.tencent.sharpgme.jni.TraeAudioCodecList;
import com.tencent.sharpgme.jni.TraeAudioSession;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes3.dex */
public class AudioDeviceInterface {
    private static final FileFilter CPU_FILTER = new FileFilter() { // from class: com.tencent.sharpgme.jni.AudioDeviceInterface.4
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            String name = file.getName();
            if (!name.startsWith("cpu")) {
                return false;
            }
            for (int i = 3; i < name.length(); i++) {
                if (name.charAt(i) < '0' || name.charAt(i) > '9') {
                    return false;
                }
            }
            return true;
        }
    };
    public static final int OUTPUT_MODE_HEADSET = 0;
    public static final int OUTPUT_MODE_SPEAKER = 1;
    private static final String TAG = "TRAEJava";
    private static boolean _dumpEnable = false;
    private static AudioRecord _lastAudioRecord = null;
    private static boolean _logEnable = true;
    private static boolean _startRecordRefuse = false;
    private static boolean isSupportVivoKTVHelper = false;
    private static boolean isUseForceVolumeControl = false;
    private static int lastSwitchState;
    private static String[] mDeviceList;
    private static VivoKTVHelper mVivoKTVHelper;
    static int weatherRequest;
    private TraeAudioSession _asAudioManager;
    private ByteBuffer[] _decBuffers;
    private ByteBuffer _playBuffer;
    private boolean _preDone;
    private Condition _precon;
    private ReentrantLock _prelock;
    private ByteBuffer _recBuffer;
    private byte[] _tempBufPlay;
    private byte[] _tempBufRec;
    private TraeAudioCodecList _traeAudioCodecList;
    private int switchState;
    private boolean usingJava;
    private AudioTrack _audioTrack = null;
    private AudioRecord _audioRecord = null;
    private int _streamType = 0;
    private int _playSamplerate = 8000;
    private int _channelOutType = 4;
    private int _audioSource = 0;
    private int _deviceStat = 0;
    private int _sceneMode = 0;
    private int _sessionId = 0;
    private Context _context = null;
    private int _modePolicy = -1;
    private int _audioSourcePolicy = -1;
    private int _audioStreamTypePolicy = -1;
    private AudioManager _audioManager = null;
    private boolean _doPlayInit = true;
    private boolean _doRecInit = true;
    private boolean _isRecording = false;
    private boolean _isPlaying = false;
    private int _bufferedRecSamples = 0;
    private int _bufferedPlaySamples = 0;
    private int _playPosition = 0;
    private File _rec_dump = null;
    private File _play_dump = null;
    private FileOutputStream _rec_out = null;
    private FileOutputStream _play_out = null;
    private int nRecordLengthMs = 0;
    private int nPlayLengthMs = 0;
    private TraeAudioSession _as = null;
    private String _connectedDev = TraeAudioManager.DEVICE_NONE;
    private boolean _audioRouteChanged = false;

    private int getAudioSessionId(AudioRecord audioRecord) {
        return 0;
    }

    private int isSupportLowLatency() {
        return 0;
    }

    public static void setStartRecordRefuse(boolean z) {
        _startRecordRefuse = z;
    }

    public AudioDeviceInterface() {
        this._traeAudioCodecList = null;
        ReentrantLock reentrantLock = new ReentrantLock();
        this._prelock = reentrantLock;
        this._precon = reentrantLock.newCondition();
        this._preDone = false;
        this.usingJava = true;
        this.switchState = 0;
        this._asAudioManager = null;
        try {
            this._playBuffer = ByteBuffer.allocateDirect(1920);
            this._recBuffer = ByteBuffer.allocateDirect(1920);
            this._decBuffers = new ByteBuffer[11];
        } catch (Exception e) {
            QLog.m606w(TAG, e.getMessage());
        }
        this._tempBufPlay = new byte[1920];
        this._tempBufRec = new byte[1920];
        this._traeAudioCodecList = new TraeAudioCodecList();
        QLog.m606w(TAG, "AudioDeviceInterface apiLevel:" + Build.VERSION.SDK_INT);
        QLog.m606w(TAG, " SDK_INT:" + Build.VERSION.SDK_INT);
        QLog.m606w(TAG, "manufacture:" + Build.MANUFACTURER);
        QLog.m606w(TAG, "MODEL:" + Build.MODEL);
    }

    public void setContext(Context context) {
        this._context = context;
    }

    private int getLowlatencySamplerate() {
        if (this._context == null) {
            QLog.m600e(TAG, "getLowlatencySamplerate err, _context:" + this._context + " api:" + Build.VERSION.SDK_INT);
            return 0;
        }
        QLog.m606w(TAG, "LOW_LATENCY:".concat(this._context.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency") ? "Y" : "N"));
        QLog.m600e(TAG, "getLowlatencySamplerate not support right now!");
        return 0;
    }

    private int getLowlatencyFramesPerBuffer() {
        if (this._context == null) {
            QLog.m600e(TAG, "getLowlatencySamplerate err, _context:" + this._context + " api:" + Build.VERSION.SDK_INT);
            return 0;
        }
        QLog.m606w(TAG, "LOW_LATENCY:".concat(this._context.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency") ? "Y" : "N"));
        return 0;
    }

    private boolean IsContainRecordAbility() {
        int i = this._deviceStat;
        return i == 0 || i == 1 || i == 3 || i == 5;
    }

    private int OpenSpeaker(boolean z) {
        return TraeAudioManager.OpenSpeaker(z);
    }

    private int InitSetting(int i, int i2, int i3, int i4, int i5) {
        this._audioSourcePolicy = i;
        this._audioStreamTypePolicy = i2;
        this._modePolicy = i3;
        this._deviceStat = i4;
        this._sceneMode = i5;
        if (i4 == 1 || i4 == 5 || i4 == 2 || i4 == 3) {
            TraeAudioManager.IsMusicScene = true;
        } else {
            TraeAudioManager.IsMusicScene = false;
        }
        int i6 = this._sceneMode;
        if (i6 == 0 || i6 == 4) {
            TraeAudioManager.IsEarPhoneSupported = true;
        } else {
            TraeAudioManager.IsEarPhoneSupported = false;
        }
        TraeAudioManager.IsUpdateSceneFlag = true;
        TraeAudioManager.nSceneMode = i5;
        QLog.m606w(TAG, "InitSetting: _audioSourcePolicy:" + this._audioSourcePolicy + " _audioStreamTypePolicy:" + this._audioStreamTypePolicy + " _modePolicy:" + this._modePolicy + " DeviceStat:" + i4 + " isSupportVivoKTVHelper:" + isSupportVivoKTVHelper + " sceneMode:" + i5);
        return 0;
    }

    private int SetAudParam(int i, int i2) {
        TraeAudioManager.nSpecialModeBypass3A = i;
        TraeAudioManager.nSpeakerStreamType = i2;
        QLog.m606w(TAG, "SetAudParam: specialModeBypass3A:" + i + " speakerStreamType:" + i2);
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0198  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int InitRecording(int r23, int r24) {
        /*
            Method dump skipped, instructions count: 556
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.sharpgme.jni.AudioDeviceInterface.InitRecording(int, int):int");
    }

    private int InitPlayback(int i, int i2) {
        int i3;
        AudioManager audioManager;
        QLog.m606w(TAG, "InitPlayback entry: sampleRate " + i);
        if (!this._isPlaying && this._audioTrack == null) {
            int i4 = 2;
            if (i2 <= 2) {
                if (this._audioManager == null) {
                    try {
                        this._audioManager = (AudioManager) this._context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
                    } catch (Exception e) {
                        QLog.m606w(TAG, e.getMessage());
                        return -1;
                    }
                }
                if (i2 == 2) {
                    this._channelOutType = 12;
                } else {
                    this._channelOutType = 4;
                }
                this._playSamplerate = i;
                int minBufferSize = AudioTrack.getMinBufferSize(i, this._channelOutType, 2);
                int i5 = this._channelOutType;
                if (i5 == 12) {
                    QLog.m606w(TAG, "InitPlayback, _channelOutType stero");
                } else if (i5 == 4) {
                    QLog.m606w(TAG, "InitPlayback, _channelOutType Mono");
                }
                int i6 = (((i * 20) * 1) * 2) / 1000;
                if (this._channelOutType == 12) {
                    i6 *= 2;
                }
                QLog.m606w(TAG, "InitPlayback: minPlayBufSize:" + minBufferSize + " 20msFz:" + i6);
                this._bufferedPlaySamples = 0;
                AudioTrack audioTrack = this._audioTrack;
                if (audioTrack != null) {
                    audioTrack.release();
                    this._audioTrack = null;
                }
                int[] iArr = {0, 0, 3, 1};
                this._streamType = TraeAudioManager.getAudioStreamType(this._audioStreamTypePolicy);
                if (this._audioRouteChanged) {
                    QLog.m606w(TAG, "_audioRouteChanged:" + this._audioRouteChanged + " _streamType:" + this._streamType);
                    if (this._audioManager.getMode() == 0 && this._connectedDev.equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
                        this._streamType = 3;
                    } else {
                        this._streamType = 0;
                    }
                    this._audioRouteChanged = false;
                }
                iArr[0] = this._streamType;
                int i7 = minBufferSize;
                int i8 = 0;
                for (int i9 = 4; i8 < i9 && this._audioTrack == null; i9 = 4) {
                    this._streamType = iArr[i8];
                    QLog.m606w(TAG, "InitPlayback: min play buf size is " + minBufferSize + " hw_sr:" + AudioTrack.getNativeOutputSampleRate(this._streamType));
                    int i10 = 1;
                    while (true) {
                        if (i10 > i4) {
                            break;
                        }
                        int i11 = minBufferSize * i10;
                        if (i11 >= i6 * 4 || i10 >= i4) {
                            try {
                                this.nPlayLengthMs = (i11 * 500) / (i * i2);
                                AudioTrack audioTrack2 = new AudioTrack(this._streamType, this._playSamplerate, this._channelOutType, 2, i11, 1);
                                this._audioTrack = audioTrack2;
                                if (audioTrack2.getState() == 1) {
                                    i7 = i11;
                                    break;
                                }
                                QLog.m606w(TAG, "InitPlayback: play not initialized playBufSize:" + i11 + " sr:" + this._playSamplerate);
                                this._audioTrack.release();
                                this._audioTrack = null;
                            } catch (Exception e2) {
                                QLog.m606w(TAG, e2.getMessage() + " _audioTrack:" + this._audioTrack);
                                AudioTrack audioTrack3 = this._audioTrack;
                                if (audioTrack3 != null) {
                                    audioTrack3.release();
                                }
                                this._audioTrack = null;
                            }
                        }
                        i10++;
                        i7 = i11;
                        i4 = 2;
                    }
                    i8++;
                    i4 = 2;
                }
                if (this._audioTrack == null) {
                    QLog.m606w(TAG, "InitPlayback fail!!!");
                    return -1;
                }
                TraeAudioSession traeAudioSession = this._as;
                if (traeAudioSession != null && (audioManager = this._audioManager) != null) {
                    traeAudioSession.voiceCallAudioParamChanged(audioManager.getMode(), this._streamType);
                }
                this._playPosition = this._audioTrack.getPlaybackHeadPosition();
                QLog.m606w(TAG, "InitPlayback exit: streamType:" + this._streamType + " samplerate:" + this._playSamplerate + " _playPosition:" + this._playPosition + " playBufSize:" + i7 + " nPlayLengthMs:" + this.nPlayLengthMs);
                int streamType = this._connectedDev.equals(TraeAudioManager.DEVICE_BLUETOOTHHEADSET) ? 6 : this._audioTrack.getStreamType();
                if ((!isUseForceVolumeControl || (((i3 = this._sceneMode) != 6 && i3 != 8) || TraeAudioManager.nSpecialModeBypass3A != 1 || TraeAudioManager.nSpeakerStreamType != 1)) && this._audioManager != null) {
                    QLog.m606w(TAG, "InitPlayback forceVolumeControlStream");
                    TraeAudioManager.forceVolumeControlStream(this._audioManager, streamType);
                    isUseForceVolumeControl = true;
                }
                if (streamType != 0) {
                    isUseForceVolumeControl = false;
                }
                return 0;
            }
        }
        QLog.m600e(TAG, "InitPlayback _isPlaying:" + this._isPlaying);
        return -1;
    }

    private int getPlayRecordSysBufferMs() {
        return (this.nRecordLengthMs + this.nPlayLengthMs) * 2;
    }

    private String getDumpFilePath(String str, int i) {
        QLog.m606w(TAG, "manufacture:" + Build.MANUFACTURER);
        QLog.m606w(TAG, "MODEL:" + Build.MODEL);
        String str2 = Environment.getExternalStorageDirectory().getPath() + "/MF-" + Build.MANUFACTURER + "-M-" + Build.MODEL + "-as-" + TraeAudioManager.getAudioSource(this._audioSourcePolicy) + "-st-" + this._streamType + "-m-" + i + "-" + str;
        QLog.m606w(TAG, "dump:" + str2);
        QLog.m606w(TAG, "dump replace:" + str2.replace(" ", "_"));
        return str2.replace(" ", "_");
    }

    private int StartRecording() {
        QLog.m606w(TAG, "StartRecording entry  " + this);
        if (this._isRecording) {
            QLog.m600e(TAG, "StartRecording _isRecording:" + this._isRecording);
            return -1;
        }
        if (this._audioRecord == null) {
            QLog.m600e(TAG, "StartRecording _audioRecord:" + this._audioRecord);
            return -1;
        }
        if (_startRecordRefuse) {
            QLog.m600e(TAG, "StartRecording _startRecordRefuse:" + _startRecordRefuse);
            return -1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            this._audioRecord.startRecording();
            boolean z = Build.BRAND.equals("vivo") && Build.VERSION.SDK_INT <= 26;
            boolean z2 = Build.BRAND.equals("OPPO") && Build.VERSION.SDK_INT == 23;
            if (System.currentTimeMillis() - currentTimeMillis > 100 && this._audioRecord.getRecordingState() != 3 && (z || z2)) {
                _startRecordRefuse = true;
                QLog.m600e(TAG, "StartRecording startRecording refuse by user  " + this);
                _lastAudioRecord = this._audioRecord;
                this._audioRecord = null;
                return -1;
            }
            if (_dumpEnable) {
                AudioManager audioManager = this._audioManager;
                this._rec_dump = new File(getDumpFilePath("jnirecord.pcm", audioManager != null ? audioManager.getMode() : -1));
                try {
                    this._rec_out = new FileOutputStream(this._rec_dump);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            this._isRecording = true;
            QLog.m606w(TAG, "StartRecording ok");
            return 0;
        } catch (IllegalStateException e2) {
            QLog.m600e(TAG, "StartRecording fail");
            e2.printStackTrace();
            return -1;
        }
    }

    private int StartPlayback() {
        if (this._isPlaying) {
            QLog.m600e(TAG, "StartPlayback _isPlaying");
            return -1;
        }
        AudioTrack audioTrack = this._audioTrack;
        if (audioTrack == null) {
            QLog.m600e(TAG, "StartPlayback _audioTrack:" + this._audioTrack);
            return -1;
        }
        try {
            audioTrack.play();
            if (_dumpEnable) {
                AudioManager audioManager = this._audioManager;
                this._play_dump = new File(getDumpFilePath("jniplay.pcm", audioManager != null ? audioManager.getMode() : -1));
                try {
                    this._play_out = new FileOutputStream(this._play_dump);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            this._isPlaying = true;
            QLog.m606w(TAG, "StartPlayback ok");
            return 0;
        } catch (IllegalStateException e2) {
            QLog.m600e(TAG, "StartPlayback fail");
            e2.printStackTrace();
            return -1;
        }
    }

    private int StopRecording() {
        QLog.m606w(TAG, "StopRecording entry");
        AudioRecord audioRecord = this._audioRecord;
        if (audioRecord == null) {
            QLog.m600e(TAG, "UnintRecord:" + this._audioRecord);
            return -1;
        }
        if (_startRecordRefuse) {
            QLog.m600e(TAG, "StopRecording _startRecordRefuse:" + _startRecordRefuse);
            return -1;
        }
        if (audioRecord.getRecordingState() == 3) {
            try {
                QLog.m606w(TAG, "StopRecording stop... state:" + this._audioRecord.getRecordingState());
                this._audioRecord.stop();
            } catch (IllegalStateException e) {
                QLog.m600e(TAG, "StopRecording  err");
                e.printStackTrace();
                return -1;
            }
        }
        QLog.m606w(TAG, "StopRecording releaseing... state:" + this._audioRecord.getRecordingState());
        this._audioRecord.release();
        this._audioRecord = null;
        this._isRecording = false;
        QLog.m606w(TAG, "StopRecording exit ok");
        return 0;
    }

    private int StopPlayback() {
        QLog.m606w(TAG, "StopPlayback entry _isPlaying:" + this._isPlaying);
        AudioTrack audioTrack = this._audioTrack;
        if (audioTrack == null) {
            QLog.m600e(TAG, "StopPlayback _isPlaying:" + this._isPlaying + " " + this._audioTrack);
            return -1;
        }
        if (audioTrack.getPlayState() == 3) {
            try {
                QLog.m606w(TAG, "StopPlayback stoping...");
                this._audioTrack.stop();
                QLog.m606w(TAG, "StopPlayback flushing... state:" + this._audioTrack.getPlayState());
                this._audioTrack.flush();
            } catch (IllegalStateException e) {
                QLog.m600e(TAG, "StopPlayback err");
                e.printStackTrace();
                return -1;
            }
        }
        QLog.m606w(TAG, "StopPlayback releaseing... state:" + this._audioTrack.getPlayState());
        this._audioTrack.release();
        this._audioTrack = null;
        this._isPlaying = false;
        QLog.m606w(TAG, "StopPlayback exit ok");
        return 0;
    }

    private int PlayAudio(int i) {
        boolean z;
        Context context;
        Object obj;
        int i2;
        Object obj2;
        FileOutputStream fileOutputStream;
        int i3 = i;
        int i4 = 1;
        boolean z2 = !this._isPlaying;
        AudioTrack audioTrack = this._audioTrack;
        int i5 = 0;
        if (z2 || (audioTrack == null)) {
            QLog.m600e(TAG, "PlayAudio: _isPlaying " + this._isPlaying + " " + this._audioTrack);
            return -1;
        }
        if (audioTrack == null) {
            return -2;
        }
        try {
            if (this._doPlayInit) {
                try {
                    Process.setThreadPriority(-19);
                } catch (Exception e) {
                    QLog.m606w(TAG, "Set play thread priority failed: " + e.getMessage());
                }
                this._doPlayInit = false;
            }
            if (_dumpEnable && (fileOutputStream = this._play_out) != null) {
                try {
                    fileOutputStream.write(this._tempBufPlay, 0, 0);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (this._audioRouteChanged) {
                if (this._audioManager == null && (context = this._context) != null) {
                    this._audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
                }
                AudioManager audioManager = this._audioManager;
                if (audioManager != null && audioManager.getMode() == 0 && this._connectedDev.equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
                    this._streamType = 3;
                } else {
                    this._streamType = 0;
                }
                z = this._streamType != this._audioTrack.getStreamType();
                this._audioRouteChanged = false;
            } else {
                z = false;
            }
            this._playBuffer.get(this._tempBufPlay);
            if (z) {
                try {
                    this._playBuffer.rewind();
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    QLog.m606w(TAG, " track resting: _streamType:" + this._streamType + " at.st:" + this._audioTrack.getStreamType());
                    Object obj3 = null;
                    if (this._audioTrack.getPlayState() == 3) {
                        try {
                            QLog.m606w(TAG, "StopPlayback stoping...");
                            this._audioTrack.stop();
                            this._audioTrack.flush();
                            QLog.m606w(TAG, "StopPlayback flushing... state:" + this._audioTrack.getPlayState());
                            this._audioTrack.release();
                            QLog.m606w(TAG, "StopPlayback releaseing... state:" + this._audioTrack.getPlayState());
                            this._audioTrack = null;
                        } catch (IllegalStateException unused) {
                            QLog.m600e(TAG, "StopPlayback err");
                        }
                    }
                    int i6 = 2;
                    int minBufferSize = AudioTrack.getMinBufferSize(this._playSamplerate, this._channelOutType, 2);
                    int i7 = 4;
                    int[] iArr = {0, 0, 3, 1};
                    iArr[0] = this._streamType;
                    int i8 = (((this._playSamplerate * 20) * 1) * 2) / 1000;
                    if (this._channelOutType == 12) {
                        i8 *= 2;
                    }
                    int i9 = i8;
                    while (i5 < i7 && this._audioTrack == null) {
                        this._streamType = iArr[i5];
                        QLog.m606w(TAG, "InitPlayback: min play buf size is " + minBufferSize + " hw_sr:" + AudioTrack.getNativeOutputSampleRate(this._streamType));
                        int i10 = i4;
                        while (true) {
                            if (i10 > i6) {
                                obj = obj3;
                                i2 = i4;
                                break;
                            }
                            int i11 = minBufferSize * i10;
                            if (i11 >= i9 * 4 || i10 >= i6) {
                                try {
                                } catch (Exception e3) {
                                    e = e3;
                                    i2 = i4;
                                }
                                try {
                                    this._audioTrack = new AudioTrack(this._streamType, this._playSamplerate, this._channelOutType, 2, i11, 1);
                                    QLog.m606w(TAG, " _audioTrack:" + this._audioTrack);
                                    i2 = 1;
                                    if (this._audioTrack.getState() == 1) {
                                        obj = null;
                                        break;
                                    }
                                    QLog.m606w(TAG, "InitPlayback: play not initialized playBufSize:" + i11 + " sr:" + this._playSamplerate);
                                    this._audioTrack.release();
                                    this._audioTrack = null;
                                } catch (Exception e4) {
                                    e = e4;
                                    i2 = 1;
                                    QLog.m606w(TAG, e.getMessage() + " _audioTrack:" + this._audioTrack);
                                    AudioTrack audioTrack2 = this._audioTrack;
                                    if (audioTrack2 != null) {
                                        audioTrack2.release();
                                    }
                                    obj2 = null;
                                    this._audioTrack = null;
                                    i10++;
                                    i4 = i2;
                                    obj3 = obj2;
                                    i6 = 2;
                                }
                            } else {
                                i2 = i4;
                            }
                            obj2 = null;
                            i10++;
                            i4 = i2;
                            obj3 = obj2;
                            i6 = 2;
                        }
                        i5++;
                        i4 = i2;
                        i7 = 4;
                        obj3 = obj;
                        i6 = 2;
                    }
                    AudioTrack audioTrack3 = this._audioTrack;
                    if (audioTrack3 != null) {
                        try {
                            audioTrack3.play();
                            this._as.voiceCallAudioParamChanged(this._audioManager.getMode(), this._streamType);
                            TraeAudioManager.forceVolumeControlStream(this._audioManager, this._connectedDev.equals(TraeAudioManager.DEVICE_BLUETOOTHHEADSET) ? 6 : this._audioTrack.getStreamType());
                        } catch (Exception unused2) {
                        }
                    }
                    QLog.m600e(TAG, "  track reset used:" + (SystemClock.elapsedRealtime() - elapsedRealtime) + "ms");
                    return i3;
                } catch (Exception e5) {
                    e = e5;
                }
            } else {
                int write = this._audioTrack.write(this._tempBufPlay, 0, i3);
                try {
                    this._playBuffer.rewind();
                    if (write < 0) {
                        QLog.m600e(TAG, "Could not write data from sc (write = " + write + ", length = " + i3 + ")");
                        return -1;
                    }
                    if (write != i3) {
                        QLog.m600e(TAG, "Could not write all data from sc (write = " + write + ", length = " + i3 + ")");
                    }
                    this._bufferedPlaySamples += write >> 1;
                    int playbackHeadPosition = this._audioTrack.getPlaybackHeadPosition();
                    if (playbackHeadPosition < this._playPosition) {
                        this._playPosition = 0;
                    }
                    this._bufferedPlaySamples -= playbackHeadPosition - this._playPosition;
                    this._playPosition = playbackHeadPosition;
                    boolean z3 = this._isRecording;
                    return write;
                } catch (Exception e6) {
                    e = e6;
                    i3 = write;
                }
            }
        } catch (Exception e7) {
            e = e7;
            i3 = 0;
        }
        QLog.m600e(TAG, "PlayAudio Exception: " + e.getMessage());
        return i3;
    }

    private int OpenslesNeedResetAudioTrack(boolean z) {
        Context context;
        try {
        } catch (Exception e) {
            QLog.m600e(TAG, "PlayAudio Exception: " + e.getMessage());
        }
        if (!TraeAudioManager.isCloseSystemAPM(this._modePolicy)) {
            return -1;
        }
        if (this._audioRouteChanged || z) {
            if (this._audioManager == null && (context = this._context) != null) {
                this._audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
            }
            AudioManager audioManager = this._audioManager;
            if (audioManager == null) {
                return 0;
            }
            if (audioManager.getMode() == 0 && this._connectedDev.equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
                this._audioStreamTypePolicy = 3;
            } else {
                this._audioStreamTypePolicy = 0;
            }
            this._audioRouteChanged = false;
        }
        return this._audioStreamTypePolicy;
    }

    private int RecordAudio(int i) {
        FileOutputStream fileOutputStream;
        if (!this._isRecording) {
            QLog.m600e(TAG, "RecordAudio: _isRecording " + this._isRecording);
            return -1;
        }
        int i2 = 0;
        try {
            if (this._audioRecord == null) {
                return -2;
            }
            if (this._doRecInit) {
                try {
                    Process.setThreadPriority(-19);
                } catch (Exception e) {
                    QLog.m606w(TAG, "Set rec thread priority failed: " + e.getMessage());
                }
                this._doRecInit = false;
            }
            this._recBuffer.rewind();
            int read = this._audioRecord.read(this._tempBufRec, 0, i);
            try {
                if (read < 0) {
                    QLog.m600e(TAG, "Could not read data from sc (read = " + read + ", length = " + i + ")");
                    return -1;
                }
                this._recBuffer.put(this._tempBufRec, 0, read);
                if (_dumpEnable && (fileOutputStream = this._rec_out) != null) {
                    try {
                        fileOutputStream.write(this._tempBufRec, 0, read);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (read == i) {
                    return read;
                }
                QLog.m600e(TAG, "Could not read all data from sc (read = " + read + ", length = " + i + ")");
                return -1;
            } catch (Exception e3) {
                e = e3;
                i2 = read;
                QLog.m600e(TAG, "RecordAudio Exception: " + e.getMessage());
                return i2;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    private int SetPlayoutVolume(int i) {
        Context context;
        if (this._audioManager == null && (context = this._context) != null) {
            this._audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        }
        AudioManager audioManager = this._audioManager;
        if (audioManager == null) {
            return -1;
        }
        audioManager.setStreamVolume(0, i, 0);
        return 0;
    }

    private int GetPlayoutVolume() {
        Context context;
        if (this._audioManager == null && (context = this._context) != null) {
            this._audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        }
        AudioManager audioManager = this._audioManager;
        if (audioManager != null) {
            return audioManager.getStreamVolume(0);
        }
        return -1;
    }

    public static String getTraceInfo() {
        StringBuffer stringBuffer = new StringBuffer("");
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int length = stackTrace.length;
        stringBuffer.append(stackTrace[2].getClassName()).append(".").append(stackTrace[2].getMethodName()).append(": ").append(stackTrace[2].getLineNumber());
        return stringBuffer.toString();
    }

    public static final void LogTraceEntry(String str) {
        if (_logEnable) {
            QLog.m606w(TAG, getTraceInfo() + " entry:" + str);
        }
    }

    public static final void LogTraceExit() {
        if (_logEnable) {
            QLog.m606w(TAG, getTraceInfo() + " exit");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onOutputChanage(String str) {
        int i;
        String str2;
        String str3;
        String str4;
        QLog.m606w(TAG, " onOutputChanage:" + str);
        setAudioRouteSwitchState(str);
        if (!TraeAudioManager.isCloseSystemAPM(this._modePolicy) || (i = this._deviceStat) == 1 || i == 5 || i == 2 || i == 3) {
            return;
        }
        this._connectedDev = str;
        StringBuilder sb = new StringBuilder(" onOutputChanage:");
        sb.append(str);
        if (this._audioManager == null) {
            str2 = " am==null";
        } else {
            str2 = " mode:" + this._audioManager.getMode();
        }
        sb.append(str2);
        sb.append(" st:");
        sb.append(this._streamType);
        if (this._audioTrack == null) {
            str3 = "_audioTrack==null";
        } else {
            str3 = " at.st:" + this._audioTrack.getStreamType();
        }
        sb.append(str3);
        QLog.m606w(TAG, sb.toString());
        try {
            if (this._audioManager == null) {
                this._audioManager = (AudioManager) this._context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
            }
            StringBuilder sb2 = new StringBuilder(" curr mode:");
            sb2.append(str);
            if (this._audioManager == null) {
                str4 = "am==null";
            } else {
                str4 = " mode:" + this._audioManager.getMode();
            }
            sb2.append(str4);
            QLog.m606w(TAG, sb2.toString());
            if (this._audioManager != null && this._connectedDev.equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
                this._audioManager.setMode(0);
            }
        } catch (Exception e) {
            QLog.m606w(TAG, e.getMessage());
        }
        this._audioRouteChanged = true;
    }

    public int requestRecordPermission() {
        if (!IsContainRecordAbility()) {
            QLog.m606w(TAG, "audience not need record permission");
            return -1;
        }
        try {
            QLog.m606w(TAG, "requestRecordPermission");
            Context context = this._context;
            if (context != null) {
                SharedPreferences.Editor edit = context.getSharedPreferences("GMEApplyForAudioRecord", 0).edit();
                edit.putInt("GMEApplyForAudioRecord", 1);
                edit.apply();
                if (weatherRequest == 0) {
                    Context context2 = this._context;
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
        return 0;
    }

    private int reConnectDevice() {
        int i = lastSwitchState;
        String str = i == 1 ? TraeAudioManager.DEVICE_EARPHONE : i == 2 ? TraeAudioManager.DEVICE_SPEAKERPHONE : i == 3 ? TraeAudioManager.DEVICE_WIREDHEADSET : i == 4 ? TraeAudioManager.DEVICE_BLUETOOTHHEADSET : TraeAudioManager.DEVICE_NONE;
        if (this._as == null) {
            return -1;
        }
        if (str.equals(TraeAudioManager.DEVICE_NONE)) {
            QLog.m606w(TAG, "TraeAudioSession connectHighestPriorityDevice ");
            this._as.connectHighestPriorityDevice();
            return 0;
        }
        QLog.m606w(TAG, "TraeAudioSession reConnectDevice ");
        this._as.forceConnectDevice(str);
        return 0;
    }

    public int call_preprocess() {
        LogTraceEntry("");
        requestRecordPermission();
        this.switchState = 0;
        this._streamType = TraeAudioManager.getAudioStreamType(this._audioStreamTypePolicy);
        if (this._as == null) {
            this._as = new TraeAudioSession(this._context, new TraeAudioSession.ITraeAudioCallback() { // from class: com.tencent.sharpgme.jni.AudioDeviceInterface.1
                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onAudioRouteSwitchEnd(String str, long j) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onAudioRouteSwitchStart(String str, String str2) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onConnectDeviceRes(int i, String str, boolean z) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onDeviceChangabledUpdate(boolean z) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onGetConnectingDeviceRes(int i, String str) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onGetStreamTypeRes(int i, int i2) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onIsDeviceChangabledRes(int i, boolean z) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onRingCompletion(int i, String str) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onStreamTypeUpdate(int i) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onServiceStateUpdate(boolean z) {
                    if (z) {
                        return;
                    }
                    try {
                        AudioDeviceInterface.this._prelock.lock();
                        AudioDeviceInterface.this._preDone = true;
                        QLog.m600e(AudioDeviceInterface.TAG, "onServiceStateUpdate signalAll");
                        AudioDeviceInterface.this._precon.signalAll();
                        AudioDeviceInterface.this._prelock.unlock();
                    } catch (Exception unused) {
                    }
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onDeviceListUpdate(String[] strArr, String str, String str2, String str3) {
                    String[] unused = AudioDeviceInterface.mDeviceList = strArr;
                    if (AudioDeviceInterface.this.usingJava) {
                        AudioDeviceInterface.this.onOutputChanage(str);
                    }
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onGetDeviceListRes(int i, String[] strArr, String str, String str2, String str3) {
                    String[] unused = AudioDeviceInterface.mDeviceList = strArr;
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onGetConnectedDeviceRes(int i, String str) {
                    if (i == 0) {
                        AudioDeviceInterface.this.onOutputChanage(str);
                    }
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onVoicecallPreprocessRes(int i) {
                    try {
                        AudioDeviceInterface.this._prelock.lock();
                        AudioDeviceInterface.this._preDone = true;
                        QLog.m600e(AudioDeviceInterface.TAG, "onVoicecallPreprocessRes signalAll");
                        AudioDeviceInterface.this._precon.signalAll();
                        AudioDeviceInterface.this._prelock.unlock();
                    } catch (Exception unused) {
                    }
                }
            });
        }
        this._preDone = false;
        if (this._as != null) {
            this._prelock.lock();
            try {
                try {
                    if (this._audioManager == null) {
                        this._audioManager = (AudioManager) this._context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
                    }
                    AudioManager audioManager = this._audioManager;
                    if (audioManager != null) {
                        if (audioManager.getMode() == 2) {
                            int i = 5;
                            while (true) {
                                int i2 = i - 1;
                                if (i <= 0 || this._audioManager.getMode() != 2) {
                                    break;
                                }
                                QLog.m600e(TAG, "call_preprocess waiting...  mode:" + this._audioManager.getMode());
                                Thread.sleep(500L);
                                i = i2;
                            }
                        }
                        if (this._audioManager.isMicrophoneMute()) {
                            this._audioManager.setMicrophoneMute(false);
                            QLog.m600e(TAG, "media call_preprocess setMicrophoneMute false");
                        }
                    }
                    this._as.voiceCallPreprocess(this._modePolicy, this._streamType);
                    TraeAudioManager.IsUpdateSceneFlag = true;
                    reConnectDevice();
                    int i3 = 7;
                    while (true) {
                        int i4 = i3 - 1;
                        if (i3 <= 0) {
                            break;
                        }
                        try {
                            if (this._preDone) {
                                break;
                            }
                            this._precon.await(1L, TimeUnit.SECONDS);
                            QLog.m600e(TAG, "call_preprocess waiting...  as:" + this._as);
                            i3 = i4;
                        } catch (InterruptedException unused) {
                        }
                    }
                    QLog.m600e(TAG, "call_preprocess done!");
                    this._as.getConnectedDevice();
                } catch (InterruptedException unused2) {
                }
            } finally {
                this._prelock.unlock();
            }
        }
        LogTraceExit();
        return 0;
    }

    public int call_postprocess() {
        LogTraceEntry("");
        this.switchState = 0;
        TraeAudioSession traeAudioSession = this._as;
        if (traeAudioSession != null) {
            traeAudioSession.voiceCallPostprocess();
            this._as.release();
            this._as = null;
        }
        TraeAudioManager.IsUpdateSceneFlag = false;
        LogTraceExit();
        return 0;
    }

    public int call_preprocess_media() {
        LogTraceEntry("");
        requestRecordPermission();
        this.switchState = 0;
        VivoKTVHelper vivoKTVHelper = mVivoKTVHelper;
        if (vivoKTVHelper != null && isSupportVivoKTVHelper) {
            vivoKTVHelper.openKTVDevice();
            mVivoKTVHelper.setPreModeParam(1);
            mVivoKTVHelper.setPlayFeedbackParam(1);
            mVivoKTVHelper.setPlayFeedbackParam(0);
        }
        if (this._as == null) {
            this._as = new TraeAudioSession(this._context, new TraeAudioSession.ITraeAudioCallback() { // from class: com.tencent.sharpgme.jni.AudioDeviceInterface.2
                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onAudioRouteSwitchEnd(String str, long j) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onAudioRouteSwitchStart(String str, String str2) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onConnectDeviceRes(int i, String str, boolean z) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onDeviceChangabledUpdate(boolean z) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onGetConnectingDeviceRes(int i, String str) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onGetStreamTypeRes(int i, int i2) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onIsDeviceChangabledRes(int i, boolean z) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onRingCompletion(int i, String str) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onServiceStateUpdate(boolean z) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onStreamTypeUpdate(int i) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onVoicecallPreprocessRes(int i) {
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onDeviceListUpdate(String[] strArr, String str, String str2, String str3) {
                    String[] unused = AudioDeviceInterface.mDeviceList = strArr;
                    if (AudioDeviceInterface.this.usingJava) {
                        AudioDeviceInterface.this.onOutputChanage(str);
                    }
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onGetDeviceListRes(int i, String[] strArr, String str, String str2, String str3) {
                    String[] unused = AudioDeviceInterface.mDeviceList = strArr;
                }

                @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                public void onGetConnectedDeviceRes(int i, String str) {
                    if (i == 0) {
                        AudioDeviceInterface.this.onOutputChanage(str);
                    }
                }
            });
        }
        if (this._as != null) {
            try {
                if (this._audioManager == null) {
                    this._audioManager = (AudioManager) this._context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
                }
                AudioManager audioManager = this._audioManager;
                if (audioManager != null) {
                    if (audioManager.getMode() == 2) {
                        int i = 5;
                        while (true) {
                            int i2 = i - 1;
                            if (i <= 0 || this._audioManager.getMode() != 2) {
                                break;
                            }
                            QLog.m600e(TAG, "media call_preprocess_media waiting...  mode:" + this._audioManager.getMode());
                            Thread.sleep(500L);
                            i = i2;
                        }
                    }
                    if (this._deviceStat == 7) {
                        if (this._audioManager.getMode() != 3) {
                            QLog.m606w(TAG, "call_preprocess_media _audioManager setMode 3");
                            this._audioManager.setMode(3);
                        }
                    } else if (this._audioManager.getMode() != 0) {
                        QLog.m606w(TAG, "call_preprocess_media _audioManager setMode 0");
                        this._audioManager.setMode(0);
                    }
                    if (this._audioManager.isMicrophoneMute()) {
                        this._audioManager.setMicrophoneMute(false);
                        QLog.m600e(TAG, "media call_preprocess_media setMicrophoneMute false");
                    }
                }
                TraeAudioManager.IsUpdateSceneFlag = true;
                reConnectDevice();
                this._as.getConnectedDevice();
                QLog.m600e(TAG, "call_preprocess_media done!");
            } catch (InterruptedException unused) {
            }
        }
        LogTraceExit();
        return 0;
    }

    public int call_postprocess_media() {
        LogTraceEntry("");
        this.switchState = 0;
        TraeAudioSession traeAudioSession = this._as;
        if (traeAudioSession != null) {
            traeAudioSession.release();
            this._as = null;
        }
        TraeAudioManager.IsUpdateSceneFlag = false;
        VivoKTVHelper vivoKTVHelper = mVivoKTVHelper;
        if (vivoKTVHelper != null && isSupportVivoKTVHelper) {
            vivoKTVHelper.closeKTVDevice();
        }
        LogTraceExit();
        return 0;
    }

    public void setJavaInterface(int i) {
        if (i == 0) {
            this.usingJava = false;
        } else {
            this.usingJava = true;
        }
        QLog.m606w(TAG, "setJavaInterface flg:" + i);
    }

    private void setAudioRouteSwitchState(String str) {
        if (str.equals(TraeAudioManager.DEVICE_EARPHONE)) {
            this.switchState = 1;
        } else if (str.equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
            this.switchState = 2;
        } else if (str.equals(TraeAudioManager.DEVICE_WIREDHEADSET)) {
            this.switchState = 3;
        } else if (str.equals(TraeAudioManager.DEVICE_BLUETOOTHHEADSET)) {
            this.switchState = 4;
        } else {
            this.switchState = 0;
        }
        lastSwitchState = this.switchState;
    }

    public int getAudioRouteSwitchState() {
        return this.switchState;
    }

    private void initTRAEAudioManager() {
        lastSwitchState = 0;
        Context context = this._context;
        if (context != null) {
            TraeAudioManager.init(context);
            QLog.m606w(TAG, "initTRAEAudioManager , TraeAudioSession create");
            if (this._asAudioManager == null) {
                this._asAudioManager = new TraeAudioSession(this._context, new TraeAudioSession.ITraeAudioCallback() { // from class: com.tencent.sharpgme.jni.AudioDeviceInterface.3
                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onAudioRouteSwitchEnd(String str, long j) {
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onAudioRouteSwitchStart(String str, String str2) {
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onConnectDeviceRes(int i, String str, boolean z) {
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onDeviceChangabledUpdate(boolean z) {
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onGetConnectingDeviceRes(int i, String str) {
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onGetStreamTypeRes(int i, int i2) {
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onIsDeviceChangabledRes(int i, boolean z) {
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onRingCompletion(int i, String str) {
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onServiceStateUpdate(boolean z) {
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onStreamTypeUpdate(int i) {
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onVoicecallPreprocessRes(int i) {
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onDeviceListUpdate(String[] strArr, String str, String str2, String str3) {
                        String[] unused = AudioDeviceInterface.mDeviceList = strArr;
                        if (AudioDeviceInterface.this.usingJava) {
                            AudioDeviceInterface.this.onOutputChanage(str);
                        }
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onGetDeviceListRes(int i, String[] strArr, String str, String str2, String str3) {
                        String[] unused = AudioDeviceInterface.mDeviceList = strArr;
                    }

                    @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
                    public void onGetConnectedDeviceRes(int i, String str) {
                        if (i == 0) {
                            AudioDeviceInterface.this.onOutputChanage(str);
                        }
                    }
                });
            }
        }
    }

    private int startService(String str) {
        QLog.m606w(TAG, "TraeAudioSession startService: " + this._asAudioManager + " deviceConfig:" + str);
        TraeAudioSession traeAudioSession = this._asAudioManager;
        if (traeAudioSession != null) {
            return traeAudioSession.startService(str);
        }
        return -1;
    }

    private int stopService() {
        QLog.m606w(TAG, "TraeAudioSession stopService: " + this._asAudioManager);
        TraeAudioSession traeAudioSession = this._asAudioManager;
        if (traeAudioSession != null) {
            return traeAudioSession.stopService();
        }
        return -1;
    }

    private int SetAudioOutputMode(int i) {
        TraeAudioSession traeAudioSession;
        QLog.m606w(TAG, "TraeAudioSession SetAudioOutputMode: " + i);
        if (i != 0) {
            if (1 != i || (traeAudioSession = this._asAudioManager) == null) {
                return -1;
            }
            traeAudioSession.connectDevice(TraeAudioManager.DEVICE_SPEAKERPHONE);
            return 0;
        }
        if (mDeviceList == null || this._asAudioManager == null) {
            return -1;
        }
        boolean z = false;
        do {
            int i2 = 0;
            while (true) {
                String[] strArr = mDeviceList;
                if (i2 >= strArr.length || z) {
                    break;
                }
                if (TraeAudioManager.DEVICE_WIREDHEADSET.equals(strArr[i2])) {
                    this._asAudioManager.connectDevice(TraeAudioManager.DEVICE_WIREDHEADSET);
                    z = true;
                    break;
                }
                i2++;
            }
            int i3 = 0;
            while (true) {
                String[] strArr2 = mDeviceList;
                if (i3 >= strArr2.length || z) {
                    break;
                }
                if (TraeAudioManager.DEVICE_BLUETOOTHHEADSET.equals(strArr2[i3])) {
                    this._asAudioManager.connectDevice(TraeAudioManager.DEVICE_BLUETOOTHHEADSET);
                    z = true;
                    break;
                }
                i3++;
            }
            int i4 = 0;
            while (true) {
                String[] strArr3 = mDeviceList;
                if (i4 >= strArr3.length || z) {
                    break;
                }
                if (TraeAudioManager.DEVICE_EARPHONE.equals(strArr3[i4])) {
                    this._asAudioManager.connectDevice(TraeAudioManager.DEVICE_EARPHONE);
                    z = true;
                    break;
                }
                i4++;
            }
        } while (!z);
        return 0;
    }

    private int getAndroidSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    public int hasLightSensorManager() {
        Context context = this._context;
        if (context == null) {
            return 1;
        }
        try {
            if (((SensorManager) context.getSystemService("sensor")).getDefaultSensor(5) == null) {
                QLog.m606w(TAG, "not hasLightSensorManager null == sensor8");
                return 0;
            }
            QLog.m606w(TAG, "hasLightSensorManager");
            return 1;
        } catch (Exception e) {
            QLog.m606w(TAG, e.getMessage());
            return 1;
        }
    }

    public int getNumberOfCPUCores() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
        } catch (NullPointerException | SecurityException unused) {
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int is_x86() {
        /*
            r10 = this;
            java.lang.String r0 = ""
            java.lang.String r1 = "UTF-8"
            r2 = -1
            r3 = 1
            r4 = 0
            r5 = 0
            java.lang.String r6 = "/proc/%d/maps"
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L5a java.io.IOException -> L64 java.io.FileNotFoundException -> L6e
            int r8 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L5a java.io.IOException -> L64 java.io.FileNotFoundException -> L6e
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L5a java.io.IOException -> L64 java.io.FileNotFoundException -> L6e
            r7[r4] = r8     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L5a java.io.IOException -> L64 java.io.FileNotFoundException -> L6e
            java.lang.String r6 = java.lang.String.format(r6, r7)     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L5a java.io.IOException -> L64 java.io.FileNotFoundException -> L6e
            java.io.File r7 = new java.io.File     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L5a java.io.IOException -> L64 java.io.FileNotFoundException -> L6e
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L5a java.io.IOException -> L64 java.io.FileNotFoundException -> L6e
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L5a java.io.IOException -> L64 java.io.FileNotFoundException -> L6e
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L5a java.io.IOException -> L64 java.io.FileNotFoundException -> L6e
            r5 = 10240(0x2800, float:1.4349E-41)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L4c java.lang.NumberFormatException -> L4f java.io.IOException -> L52 java.io.FileNotFoundException -> L55
        L28:
            int r7 = r6.read(r5)     // Catch: java.lang.Throwable -> L4c java.lang.NumberFormatException -> L4f java.io.IOException -> L52 java.io.FileNotFoundException -> L55
            if (r7 != r2) goto L37
            r6.close()     // Catch: java.lang.Exception -> L32
            goto L77
        L32:
            r1 = move-exception
            r1.printStackTrace()
            goto L77
        L37:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4c java.lang.NumberFormatException -> L4f java.io.IOException -> L52 java.io.FileNotFoundException -> L55
            r8.<init>()     // Catch: java.lang.Throwable -> L4c java.lang.NumberFormatException -> L4f java.io.IOException -> L52 java.io.FileNotFoundException -> L55
            r8.append(r0)     // Catch: java.lang.Throwable -> L4c java.lang.NumberFormatException -> L4f java.io.IOException -> L52 java.io.FileNotFoundException -> L55
            java.lang.String r9 = new java.lang.String     // Catch: java.lang.Throwable -> L4c java.lang.NumberFormatException -> L4f java.io.IOException -> L52 java.io.FileNotFoundException -> L55
            r9.<init>(r5, r4, r7, r1)     // Catch: java.lang.Throwable -> L4c java.lang.NumberFormatException -> L4f java.io.IOException -> L52 java.io.FileNotFoundException -> L55
            r8.append(r9)     // Catch: java.lang.Throwable -> L4c java.lang.NumberFormatException -> L4f java.io.IOException -> L52 java.io.FileNotFoundException -> L55
            java.lang.String r0 = r8.toString()     // Catch: java.lang.Throwable -> L4c java.lang.NumberFormatException -> L4f java.io.IOException -> L52 java.io.FileNotFoundException -> L55
            goto L28
        L4c:
            r0 = move-exception
            r5 = r6
            goto L8d
        L4f:
            r1 = move-exception
            r5 = r6
            goto L5b
        L52:
            r1 = move-exception
            r5 = r6
            goto L65
        L55:
            r1 = move-exception
            r5 = r6
            goto L6f
        L58:
            r0 = move-exception
            goto L8d
        L5a:
            r1 = move-exception
        L5b:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L58
            if (r5 == 0) goto L77
            r5.close()     // Catch: java.lang.Exception -> L32
            goto L77
        L64:
            r1 = move-exception
        L65:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L58
            if (r5 == 0) goto L77
            r5.close()     // Catch: java.lang.Exception -> L32
            goto L77
        L6e:
            r1 = move-exception
        L6f:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L58
            if (r5 == 0) goto L77
            r5.close()     // Catch: java.lang.Exception -> L32
        L77:
            java.lang.String r1 = "libhoudini.so"
            int r0 = r0.indexOf(r1)
            java.lang.String r1 = "TRAEJava"
            if (r0 == r2) goto L87
            java.lang.String r0 = "is_x86, load libhoudini.so"
            com.tencent.p014av.utils.QLog.m606w(r1, r0)
            return r3
        L87:
            java.lang.String r0 = "is_x86, not load libhoudini.so"
            com.tencent.p014av.utils.QLog.m606w(r1, r0)
            return r4
        L8d:
            if (r5 == 0) goto L97
            r5.close()     // Catch: java.lang.Exception -> L93
            goto L97
        L93:
            r1 = move-exception
            r1.printStackTrace()
        L97:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.sharpgme.jni.AudioDeviceInterface.is_x86():int");
    }

    static boolean isHardcodeOpenSles() {
        return Build.MANUFACTURER.equals("Xiaomi") ? Build.MODEL.equals("MI 5") || Build.MODEL.equals("MI 5s") || Build.MODEL.equals("MI 5s Plus") : Build.MANUFACTURER.equals("samsung") && Build.MODEL.equals("SM-G9350");
    }

    private int isSupportVivoKTVHelper() {
        Context context = this._context;
        if (context != null) {
            VivoKTVHelper vivoKTVHelper = VivoKTVHelper.getInstance(context);
            mVivoKTVHelper = vivoKTVHelper;
            if (vivoKTVHelper != null) {
                isSupportVivoKTVHelper = vivoKTVHelper.isDeviceSupportKaraoke();
            }
        }
        return isSupportVivoKTVHelper ? 1 : 0;
    }

    private int EnableVivoKTVLoopback(int i) {
        QLog.m606w(TAG, "EnableVivoKTVLoopback: " + i + " isSupportVivoKTVHelper:" + isSupportVivoKTVHelper + " mVivoKTVHelper:" + mVivoKTVHelper);
        VivoKTVHelper vivoKTVHelper = mVivoKTVHelper;
        if (vivoKTVHelper == null || !isSupportVivoKTVHelper) {
            return -1;
        }
        vivoKTVHelper.setPlayFeedbackParam(i);
        return 0;
    }

    private int isVivoKTVLoopback() {
        VivoKTVHelper vivoKTVHelper = mVivoKTVHelper;
        if (vivoKTVHelper == null || !isSupportVivoKTVHelper) {
            return 0;
        }
        return vivoKTVHelper.getPlayFeedbackParam();
    }

    private void uninitTRAEAudioManager() {
        if (this._context != null) {
            QLog.m606w(TAG, "uninitTRAEAudioManager , stopService");
            TraeAudioSession traeAudioSession = this._asAudioManager;
            if (traeAudioSession != null) {
                traeAudioSession.stopService();
                this._asAudioManager.release();
                this._asAudioManager = null;
                return;
            }
            return;
        }
        QLog.m606w(TAG, "uninitTRAEAudioManager , context null");
    }

    private int OpenMp3File(String str, int i, int i2) {
        TraeAudioCodecList.CodecInfo add;
        long j = i;
        if (this._traeAudioCodecList.find(j) != null || (add = this._traeAudioCodecList.add(j)) == null || add.audioDecoder == null) {
            return -1;
        }
        add.audioDecoder.setIOPath(str);
        add.audioDecoder.setIndex(i);
        int prepare = add.audioDecoder.prepare(i2);
        if (prepare == 0) {
            return 0;
        }
        QLog.m606w(TAG, "openFile mp3 Failed!!!");
        add.audioDecoder.release();
        add.audioDecoder = null;
        this._traeAudioCodecList.remove(j);
        return prepare;
    }

    private ByteBuffer getDecBuffer(int i) {
        ByteBuffer[] byteBufferArr = this._decBuffers;
        if (i >= byteBufferArr.length) {
            QLog.m606w(TAG, "getDecBuffer failed!! index:" + i);
            return null;
        }
        if (byteBufferArr[i] == null) {
            byteBufferArr[i] = ByteBuffer.allocateDirect(3840);
        }
        return this._decBuffers[i];
    }

    private int ReadMp3File(int i) {
        ByteBuffer decBuffer;
        TraeAudioCodecList.CodecInfo find = this._traeAudioCodecList.find(i);
        if (find == null || (decBuffer = getDecBuffer(i)) == null) {
            return -1;
        }
        decBuffer.rewind();
        int frameSize = find.audioDecoder.getFrameSize();
        int ReadOneFrame = find.audioDecoder.ReadOneFrame(find._tempBufdec, frameSize);
        decBuffer.put(find._tempBufdec, 0, frameSize);
        return ReadOneFrame;
    }

    private int CloseMp3File(int i) {
        long j = i;
        TraeAudioCodecList.CodecInfo find = this._traeAudioCodecList.find(j);
        if (find == null) {
            return -1;
        }
        find.audioDecoder.release();
        find.audioDecoder = null;
        this._traeAudioCodecList.remove(j);
        return 0;
    }

    private int SeekMp3To(int i, int i2) {
        TraeAudioCodecList.CodecInfo find = this._traeAudioCodecList.find(i);
        if (find != null) {
            return find.audioDecoder.SeekTo(i2);
        }
        return 0;
    }

    private int getMp3SampleRate(int i) {
        TraeAudioCodecList.CodecInfo find = this._traeAudioCodecList.find(i);
        if (find != null) {
            return find.audioDecoder.getSampleRate();
        }
        return -1;
    }

    private int getMp3Channels(int i) {
        TraeAudioCodecList.CodecInfo find = this._traeAudioCodecList.find(i);
        if (find != null) {
            return find.audioDecoder.getChannels();
        }
        return -1;
    }

    private long getMp3FileTotalMs(int i) {
        TraeAudioCodecList.CodecInfo find = this._traeAudioCodecList.find(i);
        if (find != null) {
            return find.audioDecoder.getFileTotalMs();
        }
        return -1L;
    }
}
