package com.tencent.TMG;

import android.os.Handler;
import android.os.Looper;
import com.tencent.TMG.ITMGContext;
import com.tencent.p014av.audiodispatcher.AudioDispatcher;
import com.tencent.p014av.utils.QLog;
import com.tencent.sharpgme.jni.AudioDeviceInterface;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public class TMGAudioCtrl extends ITMGAudioCtrl {
    TMGContext mTmgContext;
    private TimerTask _watchTimertask = null;
    private Timer _watchTimer = null;

    public native int nativeAddAudioBlackList(String str);

    public native int nativeAddSameTeamSpatializer(String str);

    public native int nativeEnableAudioCaptureDevice(boolean z);

    public native int nativeEnableAudioDispatcher(boolean z);

    public native int nativeEnableAudioPlayDevice(boolean z);

    public native int nativeEnableAudioRecv(boolean z);

    public native int nativeEnableAudioSend(boolean z);

    public native int nativeEnableLoopback(boolean z);

    public native int nativeEnableSpatializer(boolean z, boolean z2);

    public native int nativeGetDynamicVolumeById(String str);

    public native int nativeGetMicDynamicVolume();

    public native int nativeGetMicVolume();

    public native int nativeGetSpeakerDynamicVolume();

    public native int nativeGetSpeakerVolume();

    public native int nativeGetSpeakerVolumeByOpenID(String str);

    public native int nativeInitSpatializer(String str);

    public native boolean nativeIsAudioCaptureDeviceEnabled();

    public native boolean nativeIsAudioPlayDeviceEnabled();

    public native boolean nativeIsAudioRecvEnabled();

    public native boolean nativeIsAudioSendEnabled();

    public native boolean nativeIsEnableSpatializer();

    public native int nativeRemoveAudioBlackList(String str);

    public native int nativeRemoveSameTeamSpatializer(String str);

    public native int nativeSetAudioMixCount(int i);

    public native int nativeSetMicVolume(int i);

    public native int nativeSetSpeakerVolume(int i);

    public native int nativeSetSpeakerVolumeByOpenID(String str, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public TMGAudioCtrl(TMGContext tMGContext) {
        this.mTmgContext = tMGContext;
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int EnableAudioCaptureDevice(boolean z) {
        AudioDeviceInterface.setStartRecordRefuse(false);
        AudioDispatcher.getInstance().onRecordStateChange(z);
        return nativeEnableAudioCaptureDevice(z);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public boolean IsAudioCaptureDeviceEnabled() {
        return nativeIsAudioCaptureDeviceEnabled();
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int EnableAudioPlayDevice(boolean z) {
        return nativeEnableAudioPlayDevice(z);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public boolean IsAudioPlayDeviceEnabled() {
        return nativeIsAudioPlayDeviceEnabled();
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int SetSpeakerVolumeByOpenID(String str, int i) {
        return nativeSetSpeakerVolumeByOpenID(str, i);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int GetSpeakerVolumeByOpenID(String str) {
        return nativeGetSpeakerVolumeByOpenID(str);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int EnableAudioSend(boolean z) {
        AudioDeviceInterface.setStartRecordRefuse(false);
        return nativeEnableAudioSend(z);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int EnableAudioRecv(boolean z) {
        return nativeEnableAudioRecv(z);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public boolean IsAudioSendEnabled() {
        return nativeIsAudioSendEnabled();
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public boolean IsAudioRecvEnabled() {
        return nativeIsAudioRecvEnabled();
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int EnableMic(boolean z) {
        int EnableAudioCaptureDevice = EnableAudioCaptureDevice(z);
        int EnableAudioSend = EnableAudioSend(z);
        if (EnableAudioCaptureDevice == 0 && EnableAudioSend == 0) {
            return 0;
        }
        return EnableAudioCaptureDevice != 0 ? EnableAudioCaptureDevice : EnableAudioSend;
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int GetMicState() {
        return (IsAudioCaptureDeviceEnabled() && IsAudioSendEnabled()) ? 1 : 0;
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int GetSpeakerState() {
        return (IsAudioPlayDeviceEnabled() && IsAudioRecvEnabled()) ? 1 : 0;
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int EnableSpeaker(boolean z) {
        int EnableAudioPlayDevice = EnableAudioPlayDevice(z);
        int EnableAudioRecv = EnableAudioRecv(z);
        if (EnableAudioPlayDevice == 0 && EnableAudioRecv == 0) {
            return 0;
        }
        return EnableAudioPlayDevice != 0 ? EnableAudioPlayDevice : EnableAudioRecv;
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int GetMicLevel() {
        return nativeGetMicDynamicVolume();
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int SetMicVolume(int i) {
        return nativeSetMicVolume(i);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int GetMicVolume() {
        return nativeGetMicVolume();
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int GetSpeakerLevel() {
        return nativeGetSpeakerDynamicVolume();
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int SetSpeakerVolume(int i) {
        return nativeSetSpeakerVolume(i);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int GetSpeakerVolume() {
        return nativeGetSpeakerVolume();
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int EnableLoopBack(boolean z) {
        return nativeEnableLoopback(z);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int AddAudioBlackList(String str) {
        return nativeAddAudioBlackList(str);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int RemoveAudioBlackList(String str) {
        return nativeRemoveAudioBlackList(str);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int GetSendStreamLevel() {
        return nativeGetDynamicVolumeById("");
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int SetAudioMixCount(int i) {
        return nativeSetAudioMixCount(i);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int GetRecvStreamLevel(String str) {
        return nativeGetDynamicVolumeById(str);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int InitSpatializer(String str) {
        return nativeInitSpatializer(str);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int EnableSpatializer(boolean z, boolean z2) {
        return nativeEnableSpatializer(z, z2);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public boolean IsEnableSpatializer() {
        return nativeIsEnableSpatializer();
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int AddSameTeamSpatializer(String str) {
        return nativeAddSameTeamSpatializer(str);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int RemoveSameTeamSpatializer(String str) {
        return nativeRemoveSameTeamSpatializer(str);
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int TrackingVolume(float f) {
        QLog.m602i("API", String.format("TrackingVolume. times=%s", Float.valueOf(f)));
        TimerTask timerTask = this._watchTimertask;
        if (timerTask != null) {
            timerTask.cancel();
            this._watchTimertask = null;
        }
        Timer timer = this._watchTimer;
        if (timer != null) {
            timer.cancel();
            this._watchTimer = null;
        }
        if (f > 0.0f) {
            this._watchTimer = new Timer();
            TimerTask timerTask2 = new TimerTask() { // from class: com.tencent.TMG.TMGAudioCtrl.1
                Looper looper = Looper.getMainLooper();
                Handler handler = new Handler(this.looper);

                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    this.handler.post(new Runnable() { // from class: com.tencent.TMG.TMGAudioCtrl.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            TMGAudioCtrl.this.UpdateSpeakingMemberVolume();
                        }
                    });
                }
            };
            this._watchTimertask = timerTask2;
            this._watchTimer.schedule(timerTask2, 0L, f * 1000.0f);
        }
        return 0;
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int StopTrackingVolume() {
        QLog.m602i("API", "StopTrackingVolume");
        TimerTask timerTask = this._watchTimertask;
        if (timerTask != null) {
            timerTask.cancel();
            this._watchTimertask = null;
        }
        Timer timer = this._watchTimer;
        if (timer == null) {
            return 0;
        }
        timer.cancel();
        this._watchTimer = null;
        return 0;
    }

    @Override // com.tencent.TMG.ITMGAudioCtrl
    public int EnableAudioDispatcher(boolean z) {
        return nativeEnableAudioDispatcher(z);
    }

    void UpdateSpeakingMemberVolume() {
        if (this.mTmgContext != null) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < this.mTmgContext.mLstUinSpeaking.size(); i++) {
                String str = this.mTmgContext.mLstUinSpeaking.get(i);
                hashMap.put(str, Integer.valueOf(nativeGetDynamicVolumeById(str)));
            }
            if (this.mTmgContext.mTmgDelegate != null) {
                this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVNET_TYPE_USER_VOLUMES, TMGCallbackHelper.GetMapIntent(hashMap));
            }
        }
    }
}
