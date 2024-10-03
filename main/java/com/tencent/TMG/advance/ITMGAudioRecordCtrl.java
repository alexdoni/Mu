package com.tencent.TMG.advance;

/* loaded from: classes3.dex */
public abstract class ITMGAudioRecordCtrl {
    public static final int ITMG_AUDIO_RECORDING_KTV = 1;
    public static final int ITMG_AUDIO_RECORDING_SELF = 0;

    public abstract int AdjustAudioTimeByMs(int i);

    public abstract int CancelMixRecordFile();

    public abstract int GetAccompanyTotalTimeByMs();

    public abstract int GetPreviewTimeByMs();

    public abstract int GetRecordFileDurationByMs();

    public abstract int GetRecordTimeByMs();

    public abstract int MixRecordFile(boolean z);

    public abstract int PausePreview();

    public abstract int PauseRecord();

    public abstract int ResumePreview();

    public abstract int ResumeRecord();

    public abstract int SetAccompanyFile(String str);

    public abstract int SetMixWeights(float f, float f2);

    public abstract int SetPreviewTimeByMs(int i);

    public abstract int SetRecordKaraokeType(int i);

    public abstract int SetRecordTimeByMs(int i, int i2);

    public abstract int StartPreview();

    public abstract int StartRecord(int i, String str, String str2, String str3);

    public abstract int StopPreview();

    public abstract int StopRecord();

    public static ITMGAudioRecordCtrl GetInstance() {
        return TMGAudioRecordCtrl.GetInstance();
    }
}
