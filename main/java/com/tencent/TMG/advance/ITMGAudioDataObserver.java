package com.tencent.TMG.advance;

/* loaded from: classes3.dex */
public abstract class ITMGAudioDataObserver {
    public static final int AUDIO_DATA_TYPE_CAPTURE_PLAY = 4;

    /* loaded from: classes3.dex */
    public interface AudioDataCallback {
        void OnAudioDataCallback(int i, long j, int i2, int i3, int i4, byte[] bArr);
    }

    public abstract int RegisteAudioDataCallback(AudioDataCallback audioDataCallback);

    public abstract int UnRegisteAudioDataCallback();

    public static ITMGAudioDataObserver GetInstance() {
        return TMGAudioDataObserver.GetInstance();
    }
}
