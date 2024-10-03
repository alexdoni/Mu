package com.tencent.TMG;

import com.tencent.TMG.ITMGAudioEffectCtrl;
import com.tencent.TMG.ITMGContext;
import com.tencent.p014av.utils.QLog;

/* loaded from: classes3.dex */
public class TMGAudioEffectCtrl extends ITMGAudioEffectCtrl {
    private final String TAG = "TMGAudioEffectCtrl";
    private TMGContext mTmgContext;

    /* loaded from: classes3.dex */
    public static class AccompanyCompleteCallback {
        protected void onComplete(int i, boolean z, String str) {
        }
    }

    public native int nativeEnableRecordAccompany(boolean z);

    public native int nativeEnableRecordLocalMic(boolean z);

    public native int nativeEnableRecordRemote(boolean z);

    public native int nativeGetAccompanyFileCurrentPlayedTimeByMs();

    public native int nativeGetAccompanyFileCurrentPlayedTimeMsById(String str);

    public native int nativeGetAccompanyFileTotalTimeByMs();

    public native int nativeGetAccompanyFileTotalTimeMsById(String str);

    public native int nativeGetAccompanyVolume();

    public native int nativeGetEffectsVolume();

    public native int nativeGetHardWareDelay();

    public native boolean nativeIsAccompanyPlayEnd();

    public native int nativePauseAccompany();

    public native int nativePauseAllEffects();

    public native int nativePauseEffect(int i);

    public native int nativePauseRecord();

    public native int nativePlayEffect(int i, String str, boolean z, double d, double d2, int i2);

    public native int nativeResumeAccompany();

    public native int nativeResumeAllEffects();

    public native int nativeResumeEffect(int i);

    public native int nativeResumeRecord();

    public native int nativeSetAccompanyFileCurrentPlayedTimeByMs(int i);

    public native int nativeSetAccompanyKey(int i);

    public native int nativeSetAccompanyVolume(int i);

    public native int nativeSetEffectsVolume(int i);

    public native int nativeSetHardWareDelay(int i);

    public native int nativeSetKaraokeType(int i);

    public native int nativeSetKaraokeTypeAdv(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18);

    public native int nativeSetVoiceType(int i);

    public native int nativeStartAccompany(String str, boolean z, int i, int i2, int i3, AccompanyCompleteCallback accompanyCompleteCallback);

    public native int nativeStartPreviewDelayTest(AccompanyCompleteCallback accompanyCompleteCallback);

    public native int nativeStartRecord(String str, int i, int i2, boolean z, boolean z2, boolean z3);

    public native int nativeStartRecordForHardwareDelayTest(AccompanyCompleteCallback accompanyCompleteCallback);

    public native int nativeStopAccompany(int i);

    public native int nativeStopAllEffects();

    public native int nativeStopEffect(int i);

    public native int nativeStopPreviewDelayTest();

    public native int nativeStopRecord();

    public native int nativeStopRecordForHardwareDelayTest();

    /* JADX INFO: Access modifiers changed from: package-private */
    public TMGAudioEffectCtrl(TMGContext tMGContext) {
        this.mTmgContext = tMGContext;
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int SetAccompanyKey(int i) {
        return nativeSetAccompanyKey(i);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int StartAccompany(String str, boolean z, int i) {
        return StartAccompanyDownloading(str, z, i, 0);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int StartRecordForHardwareDelayTest() {
        return nativeStartRecordForHardwareDelayTest(new AccompanyCompleteCallback() { // from class: com.tencent.TMG.TMGAudioEffectCtrl.1
            @Override // com.tencent.TMG.TMGAudioEffectCtrl.AccompanyCompleteCallback
            protected void onComplete(int i, boolean z, String str) {
                QLog.m602i("TMGAudioEffectCtrl", "StartRecordForHardwareDelayTest onComplete, result=" + i + ", filePath=" + str + ",is_finished=" + z);
                if (TMGAudioEffectCtrl.this.mTmgContext.mTmgDelegate != null) {
                    TMGAudioEffectCtrl.this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_HARDWARE_TEST_RECORD_FINISH, TMGCallbackHelper.GetAccompanyFinishIntent(0, z, str));
                }
            }
        });
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int StopRecordForHardwareDelayTest() {
        return nativeStopRecordForHardwareDelayTest();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int StartPreviewDelayTest() {
        return nativeStartPreviewDelayTest(new AccompanyCompleteCallback() { // from class: com.tencent.TMG.TMGAudioEffectCtrl.2
            @Override // com.tencent.TMG.TMGAudioEffectCtrl.AccompanyCompleteCallback
            protected void onComplete(int i, boolean z, String str) {
                QLog.m602i("TMGAudioEffectCtrl", "StartPreviewDelayTest onComplete, result=" + i + ", filePath=" + str + ",is_finished=" + z);
                if (TMGAudioEffectCtrl.this.mTmgContext.mTmgDelegate != null) {
                    TMGAudioEffectCtrl.this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_HARDWARE_TEST_PREVIEW_FINISH, TMGCallbackHelper.GetAccompanyFinishIntent(0, z, str));
                }
            }
        });
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int StopPreviewDelayTest() {
        return nativeStopPreviewDelayTest();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int SetHardWareDelay(int i) {
        return nativeSetHardWareDelay(i);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int GetHardWareDelay() {
        return nativeGetHardWareDelay();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int StartAccompanyDownloading(String str, boolean z, int i, int i2) {
        return nativeStartAccompany(str, z, i, 0, i2, new AccompanyCompleteCallback() { // from class: com.tencent.TMG.TMGAudioEffectCtrl.3
            @Override // com.tencent.TMG.TMGAudioEffectCtrl.AccompanyCompleteCallback
            protected void onComplete(int i3, boolean z2, String str2) {
                QLog.m602i("TMGAudioEffectCtrl", "StartAccompany onComplete, result=" + i3 + ", filePath=" + str2 + ",is_finished=" + z2);
                if (TMGAudioEffectCtrl.this.mTmgContext.mTmgDelegate != null) {
                    TMGAudioEffectCtrl.this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_ACCOMPANY_FINISH, TMGCallbackHelper.GetAccompanyFinishIntent(0, z2, str2));
                }
            }
        });
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int StopAccompany(int i) {
        return nativeStopAccompany(i);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public boolean IsAccompanyPlayEnd() {
        return nativeIsAccompanyPlayEnd();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int PauseAccompany() {
        return nativePauseAccompany();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int ResumeAccompany() {
        return nativeResumeAccompany();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int SetAccompanyVolume(int i) {
        return nativeSetAccompanyVolume(i);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int GetAccompanyVolume() {
        return nativeGetAccompanyVolume();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public long GetAccompanyFileTotalTimeByMs() {
        return nativeGetAccompanyFileTotalTimeByMs();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public long GetAccompanyFileCurrentPlayedTimeByMs() {
        return nativeGetAccompanyFileCurrentPlayedTimeByMs();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public long GetAccompanyFileTotalTimeByMs(String str) {
        return nativeGetAccompanyFileTotalTimeMsById(str);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public long GetAccompanyFileCurrentPlayedTimeByMs(String str) {
        return nativeGetAccompanyFileCurrentPlayedTimeMsById(str);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int SetAccompanyFileCurrentPlayedTimeByMs(long j) {
        return nativeSetAccompanyFileCurrentPlayedTimeByMs((int) j);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int GetEffectsVolume() {
        return nativeGetEffectsVolume();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int SetEffectsVolume(int i) {
        return nativeSetEffectsVolume(i);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int PlayEffect(int i, String str, boolean z) {
        return nativePlayEffect(i, str, z, 1.0d, 0.0d, 100);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int PauseEffect(int i) {
        return nativePauseEffect(i);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int PauseAllEffects() {
        return nativePauseAllEffects();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int ResumeEffect(int i) {
        return nativeResumeEffect(i);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int ResumeAllEffects() {
        return nativeResumeAllEffects();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int StopEffect(int i) {
        return nativeStopEffect(i);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int StopAllEffects() {
        return nativeStopAllEffects();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int StartRecord(String str, int i, int i2, boolean z, boolean z2, boolean z3) {
        return nativeStartRecord(str, i, i2, z, z2, z3);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int StopRecord() {
        return nativeStopRecord();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int PauseRecord() {
        return nativePauseRecord();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int ResumeRecord() {
        return nativeResumeRecord();
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int EnableRecordLocalMic(boolean z) {
        return nativeEnableRecordLocalMic(z);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int EnableRecordAccompany(boolean z) {
        return nativeEnableRecordAccompany(z);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int EnableRecordRemote(boolean z) {
        return nativeEnableRecordRemote(z);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int SetVoiceType(int i) {
        return nativeSetVoiceType(i);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int SetKaraokeType(int i) {
        return nativeSetKaraokeType(i);
    }

    @Override // com.tencent.TMG.ITMGAudioEffectCtrl
    public int SetKaraokeType(ITMGAudioEffectCtrl.ITMG_VOICE_TYPE_EQUALIZER itmg_voice_type_equalizer, ITMGAudioEffectCtrl.ITMG_VOICE_TYPE_REVERB itmg_voice_type_reverb) {
        return nativeSetKaraokeTypeAdv(itmg_voice_type_equalizer.EQUALIZER_32HZ, itmg_voice_type_equalizer.EQUALIZER_64HZ, itmg_voice_type_equalizer.EQUALIZER_128HZ, itmg_voice_type_equalizer.EQUALIZER_250HZ, itmg_voice_type_equalizer.EQUALIZER_500HZ, itmg_voice_type_equalizer.EQUALIZER_1KHZ, itmg_voice_type_equalizer.EQUALIZER_2KHZ, itmg_voice_type_equalizer.EQUALIZER_4KHZ, itmg_voice_type_equalizer.EQUALIZER_8KHZ, itmg_voice_type_equalizer.EQUALIZER_16KHZ, itmg_voice_type_equalizer.EQUALIZER_MASTER_GAIN, itmg_voice_type_reverb.HARMONIC_GAIN, itmg_voice_type_reverb.HARMONIC_START_FREQUENCY, itmg_voice_type_reverb.HARMONIC_BASS_CONTROL, itmg_voice_type_reverb.REVERB_SIZE, itmg_voice_type_reverb.REVERB_DEPTH, itmg_voice_type_reverb.REVERB_GAIN, itmg_voice_type_reverb.REVERB_ECHO_DEPTH);
    }
}
