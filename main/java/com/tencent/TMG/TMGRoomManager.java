package com.tencent.TMG;

/* loaded from: classes3.dex */
public class TMGRoomManager extends ITMGRoomManager {
    private TMGContext mTmgContext;

    private native int nativeEnableMic(boolean z, String str);

    public native int nativeEnableAudioCaptureDevice(boolean z, String str);

    public native int nativeEnableAudioPlayDevice(boolean z, String str);

    public native int nativeEnableAudioRecv(boolean z, String str);

    public native int nativeEnableAudioSend(boolean z, String str);

    public native int nativeEnableSpeaker(boolean z, String str);

    public native int nativeForbidUserOperation(boolean z, String str);

    public native int nativeGetMicState(String str);

    public native int nativeGetSpeakerState(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public TMGRoomManager(TMGContext tMGContext) {
        this.mTmgContext = tMGContext;
    }

    @Override // com.tencent.TMG.ITMGRoomManager
    public int EnableMic(boolean z, String str) {
        return nativeEnableMic(z, str);
    }

    @Override // com.tencent.TMG.ITMGRoomManager
    public int EnableSpeaker(boolean z, String str) {
        return nativeEnableSpeaker(z, str);
    }

    @Override // com.tencent.TMG.ITMGRoomManager
    public int EnableAudioCaptureDevice(boolean z, String str) {
        return nativeEnableAudioCaptureDevice(z, str);
    }

    @Override // com.tencent.TMG.ITMGRoomManager
    public int EnableAudioPlayDevice(boolean z, String str) {
        return nativeEnableAudioPlayDevice(z, str);
    }

    @Override // com.tencent.TMG.ITMGRoomManager
    public int EnableAudioSend(boolean z, String str) {
        return nativeEnableAudioSend(z, str);
    }

    @Override // com.tencent.TMG.ITMGRoomManager
    public int EnableAudioRecv(boolean z, String str) {
        return nativeEnableAudioRecv(z, str);
    }

    @Override // com.tencent.TMG.ITMGRoomManager
    public int GetMicState(String str) {
        return nativeGetMicState(str);
    }

    @Override // com.tencent.TMG.ITMGRoomManager
    public int GetSpeakerState(String str) {
        return nativeGetSpeakerState(str);
    }

    @Override // com.tencent.TMG.ITMGRoomManager
    public int ForbidUserOperation(boolean z, String str) {
        return nativeForbidUserOperation(z, str);
    }
}
