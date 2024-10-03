package com.tencent.TMG;

/* loaded from: classes3.dex */
public abstract class ITMGAudioCtrl {
    public abstract int AddAudioBlackList(String str);

    public abstract int AddSameTeamSpatializer(String str);

    public abstract int EnableAudioCaptureDevice(boolean z);

    public abstract int EnableAudioDispatcher(boolean z);

    public abstract int EnableAudioPlayDevice(boolean z);

    public abstract int EnableAudioRecv(boolean z);

    public abstract int EnableAudioSend(boolean z);

    public abstract int EnableLoopBack(boolean z);

    public abstract int EnableMic(boolean z);

    public abstract int EnableSpatializer(boolean z, boolean z2);

    public abstract int EnableSpeaker(boolean z);

    public abstract int GetMicLevel();

    public abstract int GetMicState();

    public abstract int GetMicVolume();

    public abstract int GetRecvStreamLevel(String str);

    public abstract int GetSendStreamLevel();

    public abstract int GetSpeakerLevel();

    public abstract int GetSpeakerState();

    public abstract int GetSpeakerVolume();

    public abstract int GetSpeakerVolumeByOpenID(String str);

    public abstract int InitSpatializer(String str);

    public abstract boolean IsAudioCaptureDeviceEnabled();

    public abstract boolean IsAudioPlayDeviceEnabled();

    public abstract boolean IsAudioRecvEnabled();

    public abstract boolean IsAudioSendEnabled();

    public abstract boolean IsEnableSpatializer();

    public abstract int RemoveAudioBlackList(String str);

    public abstract int RemoveSameTeamSpatializer(String str);

    public abstract int SetAudioMixCount(int i);

    public abstract int SetMicVolume(int i);

    public abstract int SetSpeakerVolume(int i);

    public abstract int SetSpeakerVolumeByOpenID(String str, int i);

    @Deprecated
    public abstract int StopTrackingVolume();

    @Deprecated
    public abstract int TrackingVolume(float f);
}
