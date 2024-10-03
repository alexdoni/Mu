package com.tencent.TMG.advance;

import com.tencent.TMG.advance.ITMGAudioDataObserver;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class TMGAudioDataObserver extends ITMGAudioDataObserver {
    static TMGAudioDataObserver sInstance = new TMGAudioDataObserver();
    private Map<Integer, ITMGAudioDataObserver.AudioDataCallback> mAudioDataCallbacks = new HashMap();

    public native int nativeRegisteAudioDataCallback();

    public native int nativeUnRegisteAudioDataCallback();

    public static ITMGAudioDataObserver GetInstance() {
        return sInstance;
    }

    private TMGAudioDataObserver() {
    }

    @Override // com.tencent.TMG.advance.ITMGAudioDataObserver
    public int RegisteAudioDataCallback(ITMGAudioDataObserver.AudioDataCallback audioDataCallback) {
        int nativeRegisteAudioDataCallback = nativeRegisteAudioDataCallback();
        if (nativeRegisteAudioDataCallback == 0) {
            this.mAudioDataCallbacks.remove(4);
            this.mAudioDataCallbacks.put(4, audioDataCallback);
        }
        return nativeRegisteAudioDataCallback;
    }

    @Override // com.tencent.TMG.advance.ITMGAudioDataObserver
    public int UnRegisteAudioDataCallback() {
        int nativeUnRegisteAudioDataCallback = nativeUnRegisteAudioDataCallback();
        this.mAudioDataCallbacks.remove(4);
        return nativeUnRegisteAudioDataCallback;
    }

    public void onAudioDataCallback(int i, long j, int i2, int i3, int i4, byte[] bArr) {
        ITMGAudioDataObserver.AudioDataCallback audioDataCallback = this.mAudioDataCallbacks.get(Integer.valueOf(i));
        if (audioDataCallback != null) {
            audioDataCallback.OnAudioDataCallback(i, j, i2, i3, i4, bArr);
        }
    }
}
