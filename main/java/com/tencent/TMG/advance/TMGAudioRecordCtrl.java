package com.tencent.TMG.advance;

import android.content.Intent;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.tencent.TMG.ITMGContext;
import com.tencent.TMG.TMGContext;

/* loaded from: classes3.dex */
public class TMGAudioRecordCtrl extends ITMGAudioRecordCtrl {
    static TMGAudioRecordCtrl sInstance = new TMGAudioRecordCtrl();
    private TMGContext mTmgContext;

    public native int nativeAdjustAudioTimeByMs(int i);

    public native int nativeCancelMixRecordFile();

    public native int nativeGetAccompanyTotalTimeByMs();

    public native int nativeGetPreviewTimeByMs();

    public native int nativeGetRecordFileDurationByMs();

    public native int nativeGetRecordTimeByMs();

    public native int nativeMixRecordFile(boolean z);

    public native int nativePausePreview();

    public native int nativePauseRecord();

    public native int nativeResumePreview();

    public native int nativeResumeRecord();

    public native int nativeSetAccompanyFile(String str);

    public native int nativeSetMixWeights(float f, float f2);

    public native int nativeSetPreviewTimeByMs(int i);

    public native int nativeSetRecordKaraokeType(int i);

    public native int nativeSetRecordTimeByMs(int i, int i2);

    public native int nativeStartPreview();

    public native int nativeStartRecord(int i, String str, String str2, String str3);

    public native int nativeStopPreview();

    public native int nativeStopRecord();

    private TMGAudioRecordCtrl() {
    }

    public static TMGAudioRecordCtrl GetInstance() {
        return sInstance;
    }

    public void setTMGContext(TMGContext tMGContext) {
        this.mTmgContext = tMGContext;
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int StartRecord(int i, String str, String str2, String str3) {
        return nativeStartRecord(i, str, str2, str3);
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int StopRecord() {
        return nativeStopRecord();
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int PauseRecord() {
        return nativePauseRecord();
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int ResumeRecord() {
        return nativeResumeRecord();
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int SetAccompanyFile(String str) {
        return nativeSetAccompanyFile(str);
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int GetAccompanyTotalTimeByMs() {
        return nativeGetAccompanyTotalTimeByMs();
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int GetRecordTimeByMs() {
        return nativeGetRecordTimeByMs();
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int SetRecordTimeByMs(int i, int i2) {
        return nativeSetRecordTimeByMs(i, i2);
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int SetRecordKaraokeType(int i) {
        return nativeSetRecordKaraokeType(i);
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int GetRecordFileDurationByMs() {
        return nativeGetRecordFileDurationByMs();
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int StartPreview() {
        return nativeStartPreview();
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int StopPreview() {
        return nativeStopPreview();
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int PausePreview() {
        return nativePausePreview();
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int ResumePreview() {
        return nativeResumePreview();
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int SetPreviewTimeByMs(int i) {
        return nativeSetPreviewTimeByMs(i);
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int GetPreviewTimeByMs() {
        return nativeGetPreviewTimeByMs();
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int SetMixWeights(float f, float f2) {
        return nativeSetMixWeights(f, f2);
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int AdjustAudioTimeByMs(int i) {
        return nativeAdjustAudioTimeByMs(i);
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int MixRecordFile(boolean z) {
        return nativeMixRecordFile(z);
    }

    @Override // com.tencent.TMG.advance.ITMGAudioRecordCtrl
    public int CancelMixRecordFile() {
        return nativeCancelMixRecordFile();
    }

    public void onRecordCompleted(int i, String str, int i2) {
        if (this.mTmgContext.mTmgDelegate != null) {
            Intent intent = new Intent();
            intent.putExtra("result", i);
            intent.putExtra("filepath", str);
            intent.putExtra(TypedValues.TransitionType.S_DURATION, i2);
            this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_RECORD_COMPLETED, intent);
        }
    }

    public void onPreviewCompleted(int i) {
        if (this.mTmgContext.mTmgDelegate != null) {
            Intent intent = new Intent();
            intent.putExtra("result", i);
            this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_RECORD_PREVIEW_COMPLETED, intent);
        }
    }

    public void onMixCompleted(int i, String str, String str2, int i2) {
        if (this.mTmgContext.mTmgDelegate != null) {
            Intent intent = new Intent();
            intent.putExtra("result", i);
            intent.putExtra("filepath", str);
            intent.putExtra("mic_filepath", str2);
            intent.putExtra(TypedValues.TransitionType.S_DURATION, i2);
            this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_RECORD_MIX_COMPLETED, intent);
        }
    }
}
