package com.tencent.sharpgme.jni;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import com.tencent.p014av.utils.QLog;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class AudioDecoder {
    private static final String TAG = "AudioDecoder";
    private String srcPath;
    private MediaCodec mediaDecode = null;
    private MediaExtractor mediaExtractor = null;
    private ByteBuffer[] decodeInputBuffers = null;
    private ByteBuffer[] decodeOutputBuffers = null;
    private MediaCodec.BufferInfo decodeBufferInfo = null;
    private OnCompleteListener onCompleteListener = null;
    private OnProgressListener onProgressListener = null;
    private long fileTotalMs = 0;
    private RingBuffer decRingBuffer = null;
    int sampleRate = 0;
    int channels = 0;
    int nFrameSize = 3840;
    boolean IsTenFramesReady = false;
    int nFirstThreeFrameInfo = 3;
    int m_nIndex = 0;
    private boolean codeOver = true;

    /* loaded from: classes3.dex */
    public interface OnCompleteListener {
        void completed();
    }

    /* loaded from: classes3.dex */
    public interface OnProgressListener {
        void progress();
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public int getChannels() {
        return this.channels;
    }

    public long getFileTotalMs() {
        return this.fileTotalMs;
    }

    public int getFrameSize() {
        return this.nFrameSize;
    }

    public void setIOPath(String str) {
        this.srcPath = str;
    }

    public void setIndex(int i) {
        this.m_nIndex = i;
    }

    public int prepare(int i) {
        if (this.srcPath == null) {
            return -1;
        }
        return initMediaDecode(i);
    }

    private int initMediaDecode(int i) {
        try {
            MediaExtractor mediaExtractor = new MediaExtractor();
            this.mediaExtractor = mediaExtractor;
            mediaExtractor.setDataSource(this.srcPath);
            if (this.mediaExtractor.getTrackCount() > 1) {
                QLog.m606w(TAG, "m_nIndex: " + this.m_nIndex + " initMediaDecode mediaExtractor container video, getTrackCount: " + this.mediaExtractor.getTrackCount());
                this.codeOver = true;
                return -2;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= this.mediaExtractor.getTrackCount()) {
                    break;
                }
                MediaFormat trackFormat = this.mediaExtractor.getTrackFormat(i2);
                String string = trackFormat.getString("mime");
                QLog.m606w(TAG, "m_nIndex: " + this.m_nIndex + " initMediaDecode mediaExtractor audio type:" + string);
                if (string.startsWith("audio/mpeg")) {
                    this.mediaExtractor.selectTrack(i2);
                    MediaCodec createDecoderByType = MediaCodec.createDecoderByType(string);
                    this.mediaDecode = createDecoderByType;
                    createDecoderByType.configure(trackFormat, (Surface) null, (MediaCrypto) null, 0);
                    this.sampleRate = trackFormat.getInteger("sample-rate");
                    this.channels = trackFormat.getInteger("channel-count");
                    this.fileTotalMs = trackFormat.getLong("durationUs") / 1000;
                    int i3 = (((this.sampleRate * this.channels) * 2) * 20) / 1000;
                    this.nFrameSize = i3;
                    this.decRingBuffer = new RingBuffer(i3 * i);
                    QLog.m606w(TAG, "m_nIndex: " + this.m_nIndex + " initMediaDecode open succeed, mp3 format:(" + this.sampleRate + "," + this.channels + "), fileTotalMs:" + this.fileTotalMs + "ms RingBufferFrame:" + i);
                    break;
                }
                i2++;
            }
            MediaCodec mediaCodec = this.mediaDecode;
            if (mediaCodec == null) {
                Log.e(TAG, "m_nIndex: " + this.m_nIndex + " initMediaDecode create mediaDecode failed");
                this.codeOver = true;
                return -1;
            }
            if (this.decRingBuffer == null) {
                Log.e(TAG, "m_nIndex: " + this.m_nIndex + " initMediaDecode create decRingBuffer failed");
                this.codeOver = true;
                return -1;
            }
            mediaCodec.start();
            this.decodeInputBuffers = this.mediaDecode.getInputBuffers();
            this.decodeOutputBuffers = this.mediaDecode.getOutputBuffers();
            this.decodeBufferInfo = new MediaCodec.BufferInfo();
            this.codeOver = false;
            this.IsTenFramesReady = false;
            this.nFirstThreeFrameInfo = 3;
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            this.codeOver = true;
            return -1;
        }
    }

    private void srcAudioFormatToPCM() {
        try {
            if (this.decodeInputBuffers.length <= 1) {
                QLog.m606w(TAG, "m_nIndex: " + this.m_nIndex + " srcAudioFormatToPCM decodeInputBuffers.length to small," + this.decodeInputBuffers.length);
                this.codeOver = true;
                return;
            }
            int dequeueInputBuffer = this.mediaDecode.dequeueInputBuffer(200L);
            if (dequeueInputBuffer < 0) {
                QLog.m606w(TAG, "m_nIndex: " + this.m_nIndex + " srcAudioFormatToPCM decodeInputBuffers.inputIndex <0");
                this.codeOver = true;
                return;
            }
            ByteBuffer inputBuffer = this.mediaDecode.getInputBuffer(dequeueInputBuffer);
            inputBuffer.clear();
            int readSampleData = this.mediaExtractor.readSampleData(inputBuffer, 0);
            if (readSampleData < 0) {
                QLog.m606w(TAG, "m_nIndex: " + this.m_nIndex + " srcAudioFormatToPCM readSampleData over,end");
                this.codeOver = true;
            } else {
                this.mediaDecode.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, 0L, 0);
                this.mediaExtractor.advance();
            }
            int dequeueOutputBuffer = this.mediaDecode.dequeueOutputBuffer(this.decodeBufferInfo, 10000L);
            while (dequeueOutputBuffer >= 0) {
                ByteBuffer outputBuffer = this.mediaDecode.getOutputBuffer(dequeueOutputBuffer);
                byte[] bArr = new byte[this.decodeBufferInfo.size];
                try {
                    outputBuffer.get(bArr);
                    outputBuffer.clear();
                    if (this.decRingBuffer != null && this.decodeBufferInfo.size > 0) {
                        this.decRingBuffer.Push(bArr, this.decodeBufferInfo.size);
                        int i = this.nFirstThreeFrameInfo;
                        this.nFirstThreeFrameInfo = i - 1;
                        if (i > 0) {
                            QLog.m606w(TAG, "m_nIndex: " + this.m_nIndex + " DecodeOneFrame size: " + this.decodeBufferInfo.size + " Remain: " + (this.decRingBuffer.RemainRead() / this.nFrameSize));
                        }
                    }
                    this.mediaDecode.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if (this.decodeBufferInfo.size > 0) {
                        return;
                    } else {
                        dequeueOutputBuffer = this.mediaDecode.dequeueOutputBuffer(this.decodeBufferInfo, 10000L);
                    }
                } catch (Exception unused) {
                    QLog.m606w(TAG, "m_nIndex: " + this.m_nIndex + " srcAudioFormatToPCM wrong outputIndex: " + dequeueOutputBuffer);
                    this.codeOver = true;
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int SeekTo(int i) {
        MediaExtractor mediaExtractor = this.mediaExtractor;
        if (mediaExtractor == null) {
            return 0;
        }
        long sampleTime = mediaExtractor.getSampleTime();
        int RemainRead = i + ((this.decRingBuffer.RemainRead() * 20) / this.nFrameSize);
        QLog.m606w(TAG, "m_nIndex: " + this.m_nIndex + " current PlayMs: " + (sampleTime / 1000) + " SeekTo: " + RemainRead);
        this.mediaExtractor.seekTo((long) (RemainRead * 1000), 2);
        long sampleTime2 = this.mediaExtractor.getSampleTime();
        int i2 = (int) ((sampleTime2 - sampleTime) / 1000);
        QLog.m606w(TAG, "m_nIndex: " + this.m_nIndex + " total SeekTo time: " + i2 + " t2:" + (sampleTime2 / 1000));
        return i2;
    }

    public int ReadOneFrame(byte[] bArr, int i) {
        int i2 = 20;
        if (!this.IsTenFramesReady) {
            int i3 = 20;
            while (this.decRingBuffer.RemainRead() / this.nFrameSize < 10) {
                int i4 = i3 - 1;
                if (i3 <= 0 || this.codeOver) {
                    break;
                }
                srcAudioFormatToPCM();
                i3 = i4;
            }
            QLog.m606w(TAG, "m_nIndex: " + this.m_nIndex + " 10 FramesReady Remain frame: " + (this.decRingBuffer.RemainRead() / this.nFrameSize));
            this.IsTenFramesReady = true;
        }
        while (!this.codeOver && this.decRingBuffer.RemainRead() / this.nFrameSize < 10) {
            int i5 = i2 - 1;
            if (i2 <= 0) {
                break;
            }
            srcAudioFormatToPCM();
            i2 = i5;
        }
        if (this.decRingBuffer.RemainRead() < i) {
            return -1;
        }
        this.decRingBuffer.Pop(bArr, i);
        return i;
    }

    public void release() {
        try {
            QLog.m606w(TAG, "release mediaDecode");
            MediaCodec mediaCodec = this.mediaDecode;
            if (mediaCodec != null) {
                mediaCodec.stop();
                this.mediaDecode.release();
                this.mediaDecode = null;
            }
            MediaExtractor mediaExtractor = this.mediaExtractor;
            if (mediaExtractor != null) {
                mediaExtractor.release();
                this.mediaExtractor = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.onCompleteListener != null) {
            this.onCompleteListener = null;
        }
        if (this.onProgressListener != null) {
            this.onProgressListener = null;
        }
    }

    public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    private void showLog(String str) {
        Log.e("AudioCodec", str);
    }
}
