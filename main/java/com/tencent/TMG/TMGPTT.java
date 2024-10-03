package com.tencent.TMG;

import android.content.Intent;
import com.tencent.TMG.ITMGContext;

/* loaded from: classes3.dex */
public class TMGPTT extends ITMGPTT {
    private TMGContext mTmgContext;

    private native int nativeApplyAuthBuffer(byte[] bArr);

    private native int nativeCancelRecording();

    private native int nativeDownloadRecordedFile(String str, String str2);

    private native int nativeGetFileSize(String str);

    private native int nativeGetMicLevel();

    private native int nativeGetMicVolume();

    private native int nativeGetSpeakerLevel();

    private native int nativeGetSpeakerVolume();

    private native int nativeGetVoiceFileDuration(String str);

    private native int nativePauseRecording();

    private native int nativeResumeRecording();

    private native int nativeSetAppInfo(String str, String str2);

    private native int nativeSetMaxMessageLength(int i);

    private native int nativeSetMicVolume(int i);

    private native int nativeSetSpeakerVolume(int i);

    private native int nativeSpeechToText(String str, String str2, String str3);

    private native int nativeStartPlayFile(String str);

    private native int nativeStartPlayFileWithVoiceType(String str, int i);

    private native int nativeStartRecording(String str);

    private native int nativeStartRecordingWithStreamingRecognition(String str, String str2, String str3);

    private native int nativeStopPlayFile();

    private native int nativeStopRecording();

    private native int nativeUploadRecordedFile(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public TMGPTT(TMGContext tMGContext) {
        this.mTmgContext = tMGContext;
    }

    public void SetAppInfo(String str, String str2) {
        nativeSetAppInfo(str, str2);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int ApplyPTTAuthbuffer(byte[] bArr) {
        return nativeApplyAuthBuffer(bArr);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int SetMaxMessageLength(int i) {
        return nativeSetMaxMessageLength(i);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int StartRecording(String str) {
        return nativeStartRecording(str);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int StartRecordingWithStreamingRecognition(String str) {
        return nativeStartRecordingWithStreamingRecognition(str, "cmn-Hans-CN", "cmn-Hans-CN");
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int StartRecordingWithStreamingRecognition(String str, String str2) {
        return nativeStartRecordingWithStreamingRecognition(str, str2, str2);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int StartRecordingWithStreamingRecognition(String str, String str2, String str3) {
        return nativeStartRecordingWithStreamingRecognition(str, str2, str3);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int StopRecording() {
        return nativeStopRecording();
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int CancelRecording() {
        return nativeCancelRecording();
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int UploadRecordedFile(String str) {
        return nativeUploadRecordedFile(str);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int DownloadRecordedFile(String str, String str2) {
        return nativeDownloadRecordedFile(str, str2);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int PlayRecordedFile(String str) {
        return nativeStartPlayFile(str);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int PlayRecordedFile(String str, int i) {
        return nativeStartPlayFileWithVoiceType(str, i);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int StopPlayFile() {
        return nativeStopPlayFile();
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int GetMicLevel() {
        return nativeGetMicLevel();
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int SetMicVolume(int i) {
        return nativeSetMicVolume(i);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int GetMicVolume() {
        return nativeGetMicVolume();
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int GetSpeakerLevel() {
        return nativeGetSpeakerLevel();
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int SetSpeakerVolume(int i) {
        return nativeSetSpeakerVolume(i);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int GetSpeakerVolume() {
        return nativeGetSpeakerVolume();
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int SpeechToText(String str) {
        return SpeechToText(str, "cmn-Hans-CN");
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int SpeechToText(String str, String str2) {
        return nativeSpeechToText(str, str2, str2);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int ResumeRecording() {
        return nativeResumeRecording();
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int PauseRecording() {
        return nativePauseRecording();
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int SpeechToText(String str, String str2, String str3) {
        return nativeSpeechToText(str, str2, str3);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int GetFileSize(String str) {
        return nativeGetFileSize(str);
    }

    @Override // com.tencent.TMG.ITMGPTT
    public int GetVoiceFileDuration(String str) {
        return nativeGetVoiceFileDuration(str);
    }

    private void OnRecordFileComplete(int i, String str) {
        if (this.mTmgContext.mTmgDelegate != null) {
            this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVNET_TYPE_PTT_RECORD_COMPLETE, TMGCallbackHelper.GetPTTRecordIntent(i, str));
        }
    }

    private void OnUploadComplete(int i, String str, String str2) {
        if (this.mTmgContext.mTmgDelegate != null) {
            this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVNET_TYPE_PTT_UPLOAD_COMPLETE, TMGCallbackHelper.GetPTTUploadIntent(i, str, str2));
        }
    }

    private void OnDownloadComplete(int i, String str, String str2) {
        if (this.mTmgContext.mTmgDelegate != null) {
            this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVNET_TYPE_PTT_DOWNLOAD_COMPLETE, TMGCallbackHelper.GetPTTUploadIntent(i, str, str2));
        }
    }

    private void OnPlayFileComplete(int i, String str) {
        if (this.mTmgContext.mTmgDelegate != null) {
            this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVNET_TYPE_PTT_PLAY_COMPLETE, TMGCallbackHelper.GetPTTRecordIntent(i, str));
        }
    }

    private void OnSpeechToTextComplete(int i, String str, String str2) {
        if (this.mTmgContext.mTmgDelegate != null) {
            this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVNET_TYPE_PTT_SPEECH2TEXT_COMPLETE, TMGCallbackHelper.GetSpeechToTextIntent(i, str, str2));
        }
    }

    private void OnStreamingSpeechToText(int i, String str, String str2, String str3, boolean z) {
        if (this.mTmgContext.mTmgDelegate != null) {
            Intent StreamingRecIntennt = TMGCallbackHelper.StreamingRecIntennt(i, str, str2, str3);
            if (z) {
                this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVNET_TYPE_PTT_STREAMINGRECOGNITION_COMPLETE, StreamingRecIntennt);
            } else {
                this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVNET_TYPE_PTT_STREAMINGRECOGNITION_IS_RUNNING, StreamingRecIntennt);
            }
        }
    }
}
