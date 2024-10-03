package com.tencent.sharpgme.jni;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import com.luck.picture.lib.config.PictureMimeType;
import com.tencent.p014av.utils.QLog;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public class TraeMediaPlayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private static final String TAG = "TRAEJava";
    public static final int TRAE_MEDIAPLAER_DATASOURCE_FILEPATH = 2;
    public static final int TRAE_MEDIAPLAER_DATASOURCE_RSID = 0;
    public static final int TRAE_MEDIAPLAER_DATASOURCE_URI = 1;
    public static final int TRAE_MEDIAPLAER_STOP = 100;
    private Context _context;
    private OnCompletionListener mCallback;
    private MediaPlayer mMediaPlay = null;
    private int _streamType = 0;
    private boolean _hasCall = false;
    private boolean _loop = false;
    private int _durationMS = -1;
    int _loopCount = 0;
    boolean _ringMode = false;
    private Timer _watchTimer = null;
    private TimerTask _watchTimertask = null;
    private int _prevVolume = -1;

    /* loaded from: classes3.dex */
    public interface OnCompletionListener {
        void onCompletion();
    }

    public TraeMediaPlayer(Context context, OnCompletionListener onCompletionListener) {
        this._context = context;
        this.mCallback = onCompletionListener;
    }

    public boolean playRing(int i, int i2, Uri uri, String str, boolean z, int i3, boolean z2, boolean z3, int i4) {
        String str2;
        IllegalStateException illegalStateException;
        String str3;
        IllegalArgumentException illegalArgumentException;
        String str4;
        IOException iOException;
        Exception exc;
        MediaPlayer mediaPlayer;
        int i5;
        StringBuilder sb = new StringBuilder("TraeMediaPlay | playRing datasource:");
        sb.append(i);
        sb.append(" rsid:");
        sb.append(i2);
        sb.append(" uri:");
        sb.append(uri);
        sb.append(" filepath:");
        sb.append(str);
        sb.append(" loop:");
        sb.append(z ? "Y" : "N");
        sb.append(" :loopCount");
        sb.append(i3);
        sb.append(" ringMode:");
        sb.append(z2 ? "Y" : "N");
        sb.append(" hasCall:");
        sb.append(z3);
        sb.append(" cst:");
        sb.append(i4);
        QLog.m600e(TAG, sb.toString());
        String str5 = null;
        if (!z && i3 <= 0) {
            StringBuilder sb2 = new StringBuilder("TraeMediaPlay | playRing err datasource:");
            sb2.append(i);
            sb2.append(" loop:");
            sb2.append(z ? "Y" : "N");
            sb2.append(" :loopCount");
            sb2.append(i3);
            QLog.m600e(TAG, sb2.toString());
            return false;
        }
        try {
            try {
                try {
                    MediaPlayer mediaPlayer2 = this.mMediaPlay;
                    if (mediaPlayer2 != null) {
                        try {
                            if (mediaPlayer2.isPlaying()) {
                                return false;
                            }
                            try {
                                this.mMediaPlay.release();
                                mediaPlayer = null;
                            } catch (Exception unused) {
                                mediaPlayer = null;
                            } catch (Throwable th) {
                                this.mMediaPlay = null;
                                throw th;
                            }
                            this.mMediaPlay = mediaPlayer;
                        } catch (IOException e) {
                            iOException = e;
                            str4 = " ";
                            QLog.m598d(TAG, "TraeMediaPlay | IOException: " + iOException.getLocalizedMessage() + str4 + iOException.getMessage());
                            try {
                                this.mMediaPlay.release();
                            } catch (Exception unused2) {
                            }
                            this.mMediaPlay = null;
                            return false;
                        } catch (IllegalArgumentException e2) {
                            illegalArgumentException = e2;
                            str3 = " ";
                            QLog.m598d(TAG, "TraeMediaPlay | IllegalArgumentException: " + illegalArgumentException.getLocalizedMessage() + str3 + illegalArgumentException.getMessage());
                            this.mMediaPlay.release();
                            this.mMediaPlay = null;
                            return false;
                        } catch (IllegalStateException e3) {
                            illegalStateException = e3;
                            str2 = " ";
                            QLog.m598d(TAG, "TraeMediaPlay | IllegalStateException: " + illegalStateException.getLocalizedMessage() + str2 + illegalStateException.getMessage());
                            this.mMediaPlay.release();
                            this.mMediaPlay = null;
                            return false;
                        }
                    }
                    Timer timer = this._watchTimer;
                    if (timer != null) {
                        timer.cancel();
                        this._watchTimer = null;
                        this._watchTimertask = null;
                    }
                    AudioManager audioManager = (AudioManager) this._context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
                    MediaPlayer mediaPlayer3 = new MediaPlayer();
                    this.mMediaPlay = mediaPlayer3;
                    mediaPlayer3.setOnCompletionListener(this);
                    this.mMediaPlay.setOnErrorListener(this);
                    if (i == 0) {
                        QLog.m600e(TAG, "TraeMediaPlay | rsid:" + i2);
                        AssetFileDescriptor openRawResourceFd = this._context.getResources().openRawResourceFd(i2);
                        if (openRawResourceFd == null) {
                            QLog.m600e(TAG, "TraeMediaPlay | afd == null rsid:" + i2);
                            this.mMediaPlay.release();
                            this.mMediaPlay = null;
                            return false;
                        }
                        try {
                            this.mMediaPlay.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
                        } catch (Exception unused3) {
                        } catch (Throwable th2) {
                            openRawResourceFd.close();
                            throw th2;
                        }
                        openRawResourceFd.close();
                    } else if (i == 1) {
                        QLog.m600e(TAG, "TraeMediaPlay | uri:" + uri);
                        this.mMediaPlay.setDataSource(this._context, uri);
                    } else if (i == 2) {
                        QLog.m600e(TAG, "TraeMediaPlay | FilePath:" + str);
                        this.mMediaPlay.setDataSource(str);
                    } else {
                        QLog.m600e(TAG, "TraeMediaPlay | err datasource:" + i);
                        this.mMediaPlay.release();
                        this.mMediaPlay = null;
                    }
                    if (this.mMediaPlay == null) {
                        return false;
                    }
                    this._ringMode = z2;
                    if (z2) {
                        this._streamType = 2;
                        i5 = 1;
                    } else {
                        this._streamType = 0;
                        i5 = 3;
                    }
                    this._hasCall = z3;
                    if (z3) {
                        this._streamType = i4;
                    }
                    this.mMediaPlay.setAudioStreamType(this._streamType);
                    this.mMediaPlay.prepare();
                    this.mMediaPlay.setLooping(z);
                    this.mMediaPlay.start();
                    this._loop = z;
                    if (z) {
                        this._loopCount = 1;
                        this._durationMS = -1;
                    } else {
                        this._loopCount = i3;
                        this._durationMS = this.mMediaPlay.getDuration() * i3;
                    }
                    this._loopCount--;
                    if (!this._hasCall) {
                        audioManager.setMode(i5);
                    }
                    if (this._durationMS > 0) {
                        this._watchTimer = new Timer();
                        TimerTask timerTask = new TimerTask() { // from class: com.tencent.sharpgme.jni.TraeMediaPlayer.1
                            @Override // java.util.TimerTask, java.lang.Runnable
                            public void run() {
                                if (TraeMediaPlayer.this.mMediaPlay != null) {
                                    QLog.m600e(TraeMediaPlayer.TAG, "TraeMediaPlay | play timeout");
                                    if (TraeMediaPlayer.this.mCallback != null) {
                                        TraeMediaPlayer.this.mCallback.onCompletion();
                                    }
                                }
                            }
                        };
                        this._watchTimertask = timerTask;
                        this._watchTimer.schedule(timerTask, this._durationMS + 1000);
                    }
                    QLog.m600e(TAG, "TraeMediaPlay | DurationMS:" + this.mMediaPlay.getDuration() + " loop:" + z);
                    return true;
                } catch (IOException e4) {
                    str4 = " ";
                    iOException = e4;
                } catch (IllegalArgumentException e5) {
                    str3 = " ";
                    illegalArgumentException = e5;
                } catch (IllegalStateException e6) {
                    str2 = " ";
                    illegalStateException = e6;
                }
            } catch (SecurityException e7) {
                try {
                    StringBuilder sb3 = new StringBuilder("TraeMediaPlay | SecurityException: ");
                    sb3.append(e7.getLocalizedMessage());
                    sb3.append(" ");
                    sb3.append(e7.getMessage());
                    QLog.m598d(TAG, sb3.toString());
                } catch (Exception e8) {
                    e = e8;
                    str5 = " ";
                    exc = e;
                    QLog.m598d(TAG, "TraeMediaPlay | Except: " + exc.getLocalizedMessage() + str5 + exc.getMessage());
                    this.mMediaPlay.release();
                    this.mMediaPlay = null;
                    return false;
                }
                this.mMediaPlay.release();
                this.mMediaPlay = null;
                return false;
            } catch (Exception e9) {
                exc = e9;
                str5 = " ";
                QLog.m598d(TAG, "TraeMediaPlay | Except: " + exc.getLocalizedMessage() + str5 + exc.getMessage());
                this.mMediaPlay.release();
                this.mMediaPlay = null;
                return false;
            }
        } catch (Exception e10) {
            e = e10;
            exc = e;
            QLog.m598d(TAG, "TraeMediaPlay | Except: " + exc.getLocalizedMessage() + str5 + exc.getMessage());
            this.mMediaPlay.release();
            this.mMediaPlay = null;
            return false;
        }
    }

    public void stopRing() {
        QLog.m598d(TAG, "TraeMediaPlay stopRing ");
        MediaPlayer mediaPlayer = this.mMediaPlay;
        if (mediaPlayer == null) {
            return;
        }
        if (mediaPlayer.isPlaying()) {
            this.mMediaPlay.stop();
        }
        this.mMediaPlay.reset();
        try {
            Timer timer = this._watchTimer;
            if (timer != null) {
                timer.cancel();
                this._watchTimer = null;
                this._watchTimertask = null;
            }
            this.mMediaPlay.release();
        } catch (Exception unused) {
        }
        this.mMediaPlay = null;
        this._durationMS = -1;
    }

    public int getStreamType() {
        return this._streamType;
    }

    public int getDuration() {
        return this._durationMS;
    }

    public boolean hasCall() {
        return this._hasCall;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        AudioDeviceInterface.LogTraceEntry(" cb:" + this.mCallback + " loopCount:" + this._loopCount + " _loop:" + this._loop);
        if (this._loop) {
            QLog.m598d(TAG, "loop play,continue...");
            return;
        }
        try {
            if (this._loopCount <= 0) {
                volumeUndo();
                if (this.mMediaPlay.isPlaying()) {
                    this.mMediaPlay.stop();
                }
                this.mMediaPlay.reset();
                this.mMediaPlay.release();
                this.mMediaPlay = null;
                OnCompletionListener onCompletionListener = this.mCallback;
                if (onCompletionListener != null) {
                    onCompletionListener.onCompletion();
                }
            } else {
                this.mMediaPlay.start();
                this._loopCount--;
            }
        } catch (Exception unused) {
        }
        AudioDeviceInterface.LogTraceExit();
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        AudioDeviceInterface.LogTraceEntry(" cb:" + this.mCallback + " arg1:" + i + " arg2:" + i2);
        try {
            this.mMediaPlay.release();
        } catch (Exception unused) {
        }
        this.mMediaPlay = null;
        OnCompletionListener onCompletionListener = this.mCallback;
        if (onCompletionListener != null) {
            onCompletionListener.onCompletion();
        }
        AudioDeviceInterface.LogTraceExit();
        return false;
    }

    private void volumeDo() {
        if (this.mMediaPlay != null && this._ringMode && this._streamType != 2) {
            try {
                AudioManager audioManager = (AudioManager) this._context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
                int streamVolume = audioManager.getStreamVolume(this._streamType);
                int streamMaxVolume = audioManager.getStreamMaxVolume(this._streamType);
                int streamVolume2 = audioManager.getStreamVolume(2);
                int streamMaxVolume2 = audioManager.getStreamMaxVolume(2);
                int i = (int) (((streamVolume2 * 1.0d) / streamMaxVolume2) * streamMaxVolume);
                QLog.m600e(TAG, "TraeMediaPlay volumeDo currV:" + streamVolume + " maxV:" + streamMaxVolume + " currRV:" + streamVolume2 + " maxRV:" + streamMaxVolume2 + " setV:" + i);
                int i2 = i + 1;
                if (i2 < streamMaxVolume) {
                    streamMaxVolume = i2;
                }
                audioManager.setStreamVolume(this._streamType, streamMaxVolume, 0);
                this._prevVolume = streamVolume;
            } catch (Exception unused) {
            }
        }
    }

    private void volumeUndo() {
        if (this.mMediaPlay != null && this._ringMode && this._streamType != 2 && this._prevVolume != -1) {
            try {
                QLog.m600e(TAG, "TraeMediaPlay volumeUndo _prevVolume:" + this._prevVolume);
                ((AudioManager) this._context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO)).setStreamVolume(this._streamType, this._prevVolume, 0);
            } catch (Exception unused) {
            }
        }
    }
}
