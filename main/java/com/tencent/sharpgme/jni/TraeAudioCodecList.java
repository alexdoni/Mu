package com.tencent.sharpgme.jni;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes3.dex */
public class TraeAudioCodecList {
    private ArrayList<CodecInfo> _sessionInfoList = new ArrayList<>();
    private ReentrantLock mLock = new ReentrantLock();

    /* loaded from: classes3.dex */
    public class CodecInfo {
        public byte[] _tempBufdec;
        public AudioDecoder audioDecoder;
        public long sessionId;

        public CodecInfo() {
        }
    }

    public CodecInfo find(long j) {
        CodecInfo codecInfo;
        this.mLock.lock();
        int i = 0;
        while (true) {
            if (i >= this._sessionInfoList.size()) {
                codecInfo = null;
                break;
            }
            codecInfo = this._sessionInfoList.get(i);
            if (codecInfo.sessionId == j) {
                break;
            }
            i++;
        }
        this.mLock.unlock();
        return codecInfo;
    }

    public CodecInfo add(long j) {
        CodecInfo find = find(j);
        if (find != null) {
            return find;
        }
        CodecInfo codecInfo = new CodecInfo();
        codecInfo.sessionId = j;
        codecInfo.audioDecoder = new AudioDecoder();
        codecInfo._tempBufdec = new byte[3840];
        this.mLock.lock();
        this._sessionInfoList.add(codecInfo);
        this.mLock.unlock();
        return find(j);
    }

    public void remove(long j) {
        this.mLock.lock();
        int i = 0;
        while (true) {
            if (i >= this._sessionInfoList.size()) {
                break;
            }
            if (this._sessionInfoList.get(i).sessionId == j) {
                this._sessionInfoList.remove(i);
                break;
            }
            i++;
        }
        this.mLock.unlock();
    }
}
