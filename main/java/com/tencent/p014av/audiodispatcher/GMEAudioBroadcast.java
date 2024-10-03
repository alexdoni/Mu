package com.tencent.p014av.audiodispatcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.tencent.p014av.utils.QLog;

/* loaded from: classes3.dex */
public class GMEAudioBroadcast extends BroadcastReceiver {
    private static final String ACTION_STRING = "GMEAudioBroadcast";
    private static final String AUDIO_CHANGE_EVENT_ACTION_STRING = "GMEMicUseEventBroadcast";
    private static final int BIT = 16;
    private static final int CHANNEL = 2;
    private static final int SAMPLE_RATE = 48000;
    private static final String TAG = "GMEAudioBroadcast";
    private static Context mContext;
    private static GMEAudioBroadcast mGMEAudioBroadcast;

    public native boolean nativeIsAudioCaptureDeviceEnabled();

    public static GMEAudioBroadcast getInstance() {
        if (mGMEAudioBroadcast == null) {
            mGMEAudioBroadcast = new GMEAudioBroadcast();
        }
        return mGMEAudioBroadcast;
    }

    public void registerBroadcast(Context context) {
        try {
            mContext = context;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("GMEAudioBroadcast");
            context.registerReceiver(mGMEAudioBroadcast, intentFilter);
            QLog.m600e("GMEAudioBroadcast", "registerBroadcast successfully");
        } catch (Exception e) {
            QLog.m600e("GMEAudioBroadcast", "registerBroadcast exception e=" + e.toString());
        }
    }

    public void unRegisterBroadcast(Context context) {
        try {
            context.unregisterReceiver(mGMEAudioBroadcast);
            QLog.m600e("GMEAudioBroadcast", "unregisterBroadcast successfully");
        } catch (Exception e) {
            QLog.m600e("GMEAudioBroadcast", "unregisterBroadcast exception e=" + e.toString());
        }
    }

    public void onAudioCaptureChange(final boolean z) {
        QLog.m600e("GMEAudioBroadcast", "onAudioCaptureChange " + z);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.tencent.av.audiodispatcher.GMEAudioBroadcast.1
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent();
                intent.setAction(GMEAudioBroadcast.AUDIO_CHANGE_EVENT_ACTION_STRING);
                intent.putExtra("gme_mic_use_event", z);
                if (GMEAudioBroadcast.mContext != null) {
                    GMEAudioBroadcast.mContext.sendBroadcast(intent, null);
                }
            }
        }, 500L);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean nativeIsAudioCaptureDeviceEnabled = nativeIsAudioCaptureDeviceEnabled();
        boolean startServer = AudioDispatcher.getInstance().startServer();
        int port = AudioDispatcher.getInstance().getPort();
        String stringExtra = intent.getStringExtra("from_action");
        Intent intent2 = new Intent();
        intent2.setAction(stringExtra);
        intent2.putExtra("gme_recording", true);
        intent2.putExtra("gme_server_socket_ready", startServer);
        intent2.putExtra("gme_server_port", port);
        intent2.putExtra("gme_audio_sample_rate", SAMPLE_RATE);
        intent2.putExtra("gme_audio_channel", 2);
        intent2.putExtra("gme_audio_bit", 16);
        context.sendBroadcast(intent2, null);
        QLog.m600e("GMEAudioBroadcast", "onReceive start:" + startServer + "  port: " + port + " isRecording：" + nativeIsAudioCaptureDeviceEnabled + "  startServerRet: " + startServer);
    }
}
