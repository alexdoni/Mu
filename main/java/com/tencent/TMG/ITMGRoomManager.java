package com.tencent.TMG;

/* loaded from: classes3.dex */
public abstract class ITMGRoomManager {
    public abstract int EnableAudioCaptureDevice(boolean z, String str);

    public abstract int EnableAudioPlayDevice(boolean z, String str);

    public abstract int EnableAudioRecv(boolean z, String str);

    public abstract int EnableAudioSend(boolean z, String str);

    public abstract int EnableMic(boolean z, String str);

    public abstract int EnableSpeaker(boolean z, String str);

    public abstract int ForbidUserOperation(boolean z, String str);

    public abstract int GetMicState(String str);

    public abstract int GetSpeakerState(String str);
}
