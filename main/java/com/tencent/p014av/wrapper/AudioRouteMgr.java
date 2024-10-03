package com.tencent.p014av.wrapper;

import android.content.Context;
import com.tencent.p014av.utils.QLog;
import com.tencent.sharpgme.jni.TraeAudioManager;
import com.tencent.sharpgme.jni.TraeAudioSession;

/* loaded from: classes3.dex */
public class AudioRouteMgr {
    private static AudioRouteMgr s_instance;
    private Context mContext = null;
    private TraeAudioSession mAudioSession = null;

    private native void onAudioRouteChange(int i);

    public int stop() {
        return 0;
    }

    public static AudioRouteMgr getInstance() {
        if (s_instance == null) {
            s_instance = new AudioRouteMgr();
        }
        return s_instance;
    }

    public int setContext(Context context) {
        this.mContext = context;
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onOutputChanage(String str) {
        int i;
        QLog.m606w("AudioRoute", " onOutputChanage:" + str);
        if (str.equals(TraeAudioManager.DEVICE_EARPHONE)) {
            i = 0;
        } else if (str.equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
            i = 1;
        } else if (str.equals(TraeAudioManager.DEVICE_WIREDHEADSET)) {
            i = 2;
        } else {
            i = str.equals(TraeAudioManager.DEVICE_BLUETOOTHHEADSET) ? 3 : -1;
        }
        onAudioRouteChange(i);
    }

    public int start() {
        if (this.mAudioSession != null) {
            return 0;
        }
        this.mAudioSession = new TraeAudioSession(this.mContext, new TraeAudioSession.ITraeAudioCallback() { // from class: com.tencent.av.wrapper.AudioRouteMgr.1
            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onAudioRouteSwitchEnd(String str, long j) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onAudioRouteSwitchStart(String str, String str2) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onConnectDeviceRes(int i, String str, boolean z) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onDeviceChangabledUpdate(boolean z) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onGetConnectedDeviceRes(int i, String str) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onGetConnectingDeviceRes(int i, String str) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onGetDeviceListRes(int i, String[] strArr, String str, String str2, String str3) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onGetStreamTypeRes(int i, int i2) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onIsDeviceChangabledRes(int i, boolean z) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onRingCompletion(int i, String str) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onServiceStateUpdate(boolean z) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onStreamTypeUpdate(int i) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onVoicecallPreprocessRes(int i) {
            }

            @Override // com.tencent.sharpgme.jni.TraeAudioSession.ITraeAudioCallback
            public void onDeviceListUpdate(String[] strArr, String str, String str2, String str3) {
                AudioRouteMgr.this.onOutputChanage(str);
            }
        });
        return 0;
    }
}
