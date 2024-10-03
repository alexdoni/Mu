package com.tencent.sharpgme.jni;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Process;
import com.tencent.p014av.utils.QLog;
import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes3.dex */
public class TraeAudioSession extends BroadcastReceiver {
    private static final String TAG = "TRAEJava";
    static int s_nSessionIdAllocator;
    private ITraeAudioCallback mCallback;
    private Context mContext;
    private boolean mIsHostside;
    private long mSessionId;
    private String _connectedDev = TraeAudioManager.DEVICE_NONE;
    private boolean _canSwtich2Earphone = true;
    final String TRAE_ACTION_PHONE_STATE = "android.intent.action.PHONE_STATE";

    /* loaded from: classes3.dex */
    public interface ITraeAudioCallback {
        void onAudioRouteSwitchEnd(String str, long j);

        void onAudioRouteSwitchStart(String str, String str2);

        void onConnectDeviceRes(int i, String str, boolean z);

        void onDeviceChangabledUpdate(boolean z);

        void onDeviceListUpdate(String[] strArr, String str, String str2, String str3);

        void onGetConnectedDeviceRes(int i, String str);

        void onGetConnectingDeviceRes(int i, String str);

        void onGetDeviceListRes(int i, String[] strArr, String str, String str2, String str3);

        void onGetStreamTypeRes(int i, int i2);

        void onIsDeviceChangabledRes(int i, boolean z);

        void onRingCompletion(int i, String str);

        void onServiceStateUpdate(boolean z);

        void onStreamTypeUpdate(int i);

        void onVoicecallPreprocessRes(int i);
    }

    public static long requestSessionId() {
        long myPid = Process.myPid() << 32;
        int i = s_nSessionIdAllocator + 1;
        s_nSessionIdAllocator = i;
        return myPid + i;
    }

    public static void ExConnectDevice(Context context, String str) {
        if (context == null || str == null || str.length() <= 0) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, Long.MIN_VALUE);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_CONNECTDEVICE);
        intent.putExtra(TraeAudioManager.CONNECTDEVICE_DEVICENAME, str);
        context.sendBroadcast(intent);
    }

    public TraeAudioSession(Context context, ITraeAudioCallback iTraeAudioCallback) {
        this.mIsHostside = false;
        this.mSessionId = Long.MIN_VALUE;
        this.mIsHostside = Process.myPid() == TraeAudioManager._gHostProcessId;
        this.mSessionId = requestSessionId();
        this.mCallback = iTraeAudioCallback;
        this.mContext = context;
        if (context == null) {
            StringBuilder sb = new StringBuilder("AudioSession | Invalid parameters: ctx = ");
            String str = JsonSerializer.Null;
            sb.append(context == null ? JsonSerializer.Null : "{object}");
            sb.append("; cb = ");
            sb.append(iTraeAudioCallback != null ? "{object}" : str);
            QLog.m606w(TAG, sb.toString());
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_RES);
        intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY);
        if (context != null) {
            try {
                context.registerReceiver(this, intentFilter);
            } catch (Exception e) {
                QLog.m600e(TAG, "registerReceiver Exception: " + e.getMessage());
            }
        }
        registerAudioSession(this, true);
        QLog.m606w(TAG, "TraeAudioSession create, mSessionId: " + this.mSessionId);
    }

    public void release() {
        QLog.m606w(TAG, "TraeAudioSession release, mSessionId: " + this.mSessionId);
        Context context = this.mContext;
        if (context != null) {
            try {
                context.unregisterReceiver(this);
            } catch (Exception unused) {
            }
        }
        registerAudioSession(this, false);
        this.mContext = null;
        this.mCallback = null;
    }

    public void setCallback(ITraeAudioCallback iTraeAudioCallback) {
        this.mCallback = iTraeAudioCallback;
    }

    private int registerAudioSession(TraeAudioSession traeAudioSession, boolean z) {
        Context context = this.mContext;
        if (context == null) {
            return -1;
        }
        return TraeAudioManager.registerAudioSession(traeAudioSession, z, this.mSessionId, context);
    }

    public int startService(String str) {
        if (str == null || str.length() <= 0) {
            str = "internal_disable_dev_switch";
        }
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.startService(TraeAudioManager.OPERATION_STARTSERVICE, this.mSessionId, z, str);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_STARTSERVICE);
        intent.putExtra(TraeAudioManager.EXTRA_DATA_DEVICECONFIG, str);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int stopService() {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.stopService(TraeAudioManager.OPERATION_STOPSERVICE, this.mSessionId, z);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_STOPSERVICE);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int getDeviceList() {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.getDeviceList(TraeAudioManager.OPERATION_GETDEVICELIST, this.mSessionId, z);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_GETDEVICELIST);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int getStreamType() {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.getStreamType(TraeAudioManager.OPERATION_GETSTREAMTYPE, this.mSessionId, z);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_GETSTREAMTYPE);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int connectDevice(String str) {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.connectDevice(TraeAudioManager.OPERATION_CONNECTDEVICE, this.mSessionId, z, str);
        }
        if (this.mContext == null || str == null || str.length() <= 0) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_CONNECTDEVICE);
        intent.putExtra(TraeAudioManager.CONNECTDEVICE_DEVICENAME, str);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int forceConnectDevice(String str) {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.forceConnectDevice(TraeAudioManager.OPERATION_FORCE_CONNECTDEVICE, this.mSessionId, z, str);
        }
        if (this.mContext == null || str == null || str.length() <= 0) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_FORCE_CONNECTDEVICE);
        intent.putExtra(TraeAudioManager.CONNECTDEVICE_DEVICENAME, str);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int connectHighestPriorityDevice() {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.connectHighestPriorityDevice(TraeAudioManager.OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE, this.mSessionId, z);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int EarAction(int i) {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.earAction(TraeAudioManager.OPERATION_EARACTION, this.mSessionId, z, i);
        }
        if (this.mContext == null) {
            return -1;
        }
        if (i != 0 && i != 1) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_EARACTION);
        intent.putExtra(TraeAudioManager.EXTRA_EARACTION, i);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int isDeviceChangabled() {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.isDeviceChangabled(TraeAudioManager.OPERATION_ISDEVICECHANGABLED, this.mSessionId, z);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_ISDEVICECHANGABLED);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int getConnectedDevice() {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.getConnectedDevice(TraeAudioManager.OPERATION_GETCONNECTEDDEVICE, this.mSessionId, z);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_GETCONNECTEDDEVICE);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int getConnectingDevice() {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.getConnectingDevice(TraeAudioManager.OPERATION_GETCONNECTINGDEVICE, this.mSessionId, z);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_GETCONNECTINGDEVICE);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int voiceCallPreprocess(int i, int i2) {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.voicecallPreprocess(TraeAudioManager.OPERATION_VOICECALL_PREPROCESS, this.mSessionId, z, i, i2);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_MODEPOLICY, i);
        intent.putExtra(TraeAudioManager.PARAM_STREAMTYPE, i2);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_VOICECALL_PREPROCESS);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int voiceCallPostprocess() {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.voicecallPostprocess(TraeAudioManager.OPERATION_VOICECALL_POSTPROCESS, this.mSessionId, z);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_VOICECALL_POSTPROCESS);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int voiceCallAudioParamChanged(int i, int i2) {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.voiceCallAudioParamChanged(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST, this.mSessionId, z, i, i2);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_MODEPOLICY, i);
        intent.putExtra(TraeAudioManager.PARAM_STREAMTYPE, i2);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_VOICECALL_AUDIOPARAM_CHANGED);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int startRing(int i, int i2, Uri uri, String str, boolean z) {
        boolean z2 = this.mIsHostside;
        if (z2) {
            return TraeAudioManager.startRing(TraeAudioManager.OPERATION_STARTRING, this.mSessionId, z2, i, i2, uri, str, z, 1, "normal-ring", false);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_RING_DATASOURCE, i);
        intent.putExtra(TraeAudioManager.PARAM_RING_RSID, i2);
        intent.putExtra(TraeAudioManager.PARAM_RING_URI, uri);
        intent.putExtra(TraeAudioManager.PARAM_RING_FILEPATH, str);
        intent.putExtra(TraeAudioManager.PARAM_RING_LOOP, z);
        intent.putExtra(TraeAudioManager.PARAM_RING_MODE, false);
        intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, "normal-ring");
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_STARTRING);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int startRing(int i, int i2, Uri uri, String str, boolean z, int i3, String str2) {
        boolean z2 = this.mIsHostside;
        if (z2) {
            return TraeAudioManager.startRing(TraeAudioManager.OPERATION_STARTRING, this.mSessionId, z2, i, i2, uri, str, z, i3, str2, false);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_RING_DATASOURCE, i);
        intent.putExtra(TraeAudioManager.PARAM_RING_RSID, i2);
        intent.putExtra(TraeAudioManager.PARAM_RING_URI, uri);
        intent.putExtra(TraeAudioManager.PARAM_RING_FILEPATH, str);
        intent.putExtra(TraeAudioManager.PARAM_RING_LOOP, z);
        intent.putExtra(TraeAudioManager.PARAM_RING_LOOPCOUNT, i3);
        intent.putExtra(TraeAudioManager.PARAM_RING_MODE, false);
        intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, str2);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_STARTRING);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int startRing(int i, int i2, Uri uri, String str, boolean z, int i3, String str2, boolean z2) {
        boolean z3 = this.mIsHostside;
        if (z3) {
            return TraeAudioManager.startRing(TraeAudioManager.OPERATION_STARTRING, this.mSessionId, z3, i, i2, uri, str, z, i3, str2, z2);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_RING_DATASOURCE, i);
        intent.putExtra(TraeAudioManager.PARAM_RING_RSID, i2);
        intent.putExtra(TraeAudioManager.PARAM_RING_URI, uri);
        intent.putExtra(TraeAudioManager.PARAM_RING_FILEPATH, str);
        intent.putExtra(TraeAudioManager.PARAM_RING_LOOP, z);
        intent.putExtra(TraeAudioManager.PARAM_RING_LOOPCOUNT, i3);
        intent.putExtra(TraeAudioManager.PARAM_RING_MODE, z2);
        intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, str2);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_STARTRING);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int stopRing() {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.stopRing(TraeAudioManager.OPERATION_STOPRING, this.mSessionId, z);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_STOPRING);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int requestReleaseAudioFocus() {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.requestReleaseAudioFocus(TraeAudioManager.OPERATION_REQUEST_RELEASE_AUDIO_FOCUS, this.mSessionId, z);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_REQUEST_RELEASE_AUDIO_FOCUS);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int recoverAudioFocus() {
        boolean z = this.mIsHostside;
        if (z) {
            return TraeAudioManager.recoverAudioFocus(TraeAudioManager.OPERATION_RECOVER_AUDIO_FOCUS, this.mSessionId, z);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_RECOVER_AUDIO_FOCUS);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public void onReceiveCallback(Intent intent) {
        try {
            if (intent == null) {
                QLog.m606w(TAG, "[ERROR] intent = null!!");
                return;
            }
            long longExtra = intent.getLongExtra(TraeAudioManager.PARAM_SESSIONID, Long.MIN_VALUE);
            String stringExtra = intent.getStringExtra(TraeAudioManager.PARAM_OPERATION);
            int intExtra = intent.getIntExtra(TraeAudioManager.PARAM_RES_ERRCODE, 0);
            if (TraeAudioManager.ACTION_TRAEAUDIOMANAGER_RES.equals(intent.getAction()) && this.mSessionId == longExtra && TraeAudioManager.OPERATION_VOICECALL_PREPROCESS.equals(stringExtra)) {
                QLog.m606w(TAG, "AudioSession|[onReceiveCallback onVoicecallPreprocess] err:" + intExtra);
                ITraeAudioCallback iTraeAudioCallback = this.mCallback;
                if (iTraeAudioCallback != null) {
                    iTraeAudioCallback.onVoicecallPreprocessRes(intExtra);
                }
            }
        } catch (Exception e) {
            QLog.m600e(TAG, "AudioSession| nSessinId = " + this.mSessionId + " onReceive::intent:" + intent.toString() + " intent.getAction():" + intent.getAction() + " Exception:" + e.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.content.Intent] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ?? r2 = intent;
        try {
            if (r2 == 0) {
                QLog.m606w(TAG, "[ERROR] intent = null!!");
                return;
            }
            long longExtra = r2.getLongExtra(TraeAudioManager.PARAM_SESSIONID, Long.MIN_VALUE);
            String stringExtra = r2.getStringExtra(TraeAudioManager.PARAM_OPERATION);
            int intExtra = r2.getIntExtra(TraeAudioManager.PARAM_RES_ERRCODE, 0);
            if (TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY.equals(intent.getAction())) {
                if (TraeAudioManager.NOTIFY_SERVICE_STATE.equals(stringExtra)) {
                    boolean booleanExtra = r2.getBooleanExtra(TraeAudioManager.NOTIFY_SERVICE_STATE_DATE, false);
                    QLog.m606w(TAG, "AudioSession|[onServiceStateUpdate]".concat(booleanExtra ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF));
                    ITraeAudioCallback iTraeAudioCallback = this.mCallback;
                    if (iTraeAudioCallback != null) {
                        iTraeAudioCallback.onServiceStateUpdate(booleanExtra);
                        return;
                    }
                    return;
                }
                if (TraeAudioManager.NOTIFY_DEVICELIST_UPDATE.equals(stringExtra)) {
                    String[] stringArrayExtra = r2.getStringArrayExtra(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST);
                    String stringExtra2 = r2.getStringExtra(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE);
                    String stringExtra3 = r2.getStringExtra(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE);
                    String stringExtra4 = r2.getStringExtra(TraeAudioManager.EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME);
                    String str = "\n";
                    boolean z = true;
                    for (int i = 0; i < stringArrayExtra.length; i++) {
                        str = str + "AudioSession|    " + i + " " + stringArrayExtra[i] + "\n";
                        if (stringArrayExtra[i].equals(TraeAudioManager.DEVICE_WIREDHEADSET) || stringArrayExtra[i].equals(TraeAudioManager.DEVICE_BLUETOOTHHEADSET)) {
                            z = false;
                        }
                    }
                    QLog.m606w(TAG, "AudioSession|[onDeviceListUpdate]  connected:" + stringExtra2 + " prevConnected:" + stringExtra3 + " bt:" + stringExtra4 + " Num:" + stringArrayExtra.length + (str + "\n"));
                    this._canSwtich2Earphone = z;
                    this._connectedDev = stringExtra2;
                    ITraeAudioCallback iTraeAudioCallback2 = this.mCallback;
                    if (iTraeAudioCallback2 != null) {
                        iTraeAudioCallback2.onDeviceListUpdate(stringArrayExtra, stringExtra2, stringExtra3, stringExtra4);
                        return;
                    }
                    return;
                }
                if (TraeAudioManager.NOTIFY_DEVICECHANGABLE_UPDATE.equals(stringExtra)) {
                    boolean booleanExtra2 = r2.getBooleanExtra(TraeAudioManager.NOTIFY_DEVICECHANGABLE_UPDATE_DATE, true);
                    QLog.m606w(TAG, "AudioSession|[onDeviceChangabledUpdate]" + booleanExtra2);
                    ITraeAudioCallback iTraeAudioCallback3 = this.mCallback;
                    if (iTraeAudioCallback3 != null) {
                        iTraeAudioCallback3.onDeviceChangabledUpdate(booleanExtra2);
                        return;
                    }
                    return;
                }
                if (TraeAudioManager.NOTIFY_STREAMTYPE_UPDATE.equals(stringExtra)) {
                    int intExtra2 = r2.getIntExtra(TraeAudioManager.EXTRA_DATA_STREAMTYPE, -1);
                    QLog.m606w(TAG, "AudioSession|[onStreamTypeUpdate] err:" + intExtra + " st:" + intExtra2);
                    ITraeAudioCallback iTraeAudioCallback4 = this.mCallback;
                    if (iTraeAudioCallback4 != null) {
                        iTraeAudioCallback4.onStreamTypeUpdate(intExtra2);
                        return;
                    }
                    return;
                }
                if (TraeAudioManager.NOTIFY_ROUTESWITCHSTART.equals(stringExtra)) {
                    String stringExtra5 = r2.getStringExtra(TraeAudioManager.EXTRA_DATA_ROUTESWITCHSTART_FROM);
                    String stringExtra6 = r2.getStringExtra(TraeAudioManager.EXTRA_DATA_ROUTESWITCHSTART_TO);
                    ITraeAudioCallback iTraeAudioCallback5 = this.mCallback;
                    if (iTraeAudioCallback5 == null || stringExtra5 == null || stringExtra6 == null) {
                        return;
                    }
                    iTraeAudioCallback5.onAudioRouteSwitchStart(stringExtra5, stringExtra6);
                    return;
                }
                if (TraeAudioManager.NOTIFY_ROUTESWITCHEND.equals(stringExtra)) {
                    String stringExtra7 = r2.getStringExtra(TraeAudioManager.EXTRA_DATA_ROUTESWITCHEND_DEV);
                    long longExtra2 = r2.getLongExtra(TraeAudioManager.EXTRA_DATA_ROUTESWITCHEND_TIME, -1L);
                    ITraeAudioCallback iTraeAudioCallback6 = this.mCallback;
                    if (iTraeAudioCallback6 == null || stringExtra7 == null || longExtra2 == -1) {
                        return;
                    }
                    iTraeAudioCallback6.onAudioRouteSwitchEnd(stringExtra7, longExtra2);
                    return;
                }
                return;
            }
            try {
                if (!TraeAudioManager.ACTION_TRAEAUDIOMANAGER_RES.equals(intent.getAction()) || this.mSessionId != longExtra) {
                    return;
                }
                try {
                    if (TraeAudioManager.OPERATION_GETDEVICELIST.equals(stringExtra)) {
                        try {
                            String[] stringArrayExtra2 = r2.getStringArrayExtra(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST);
                            String stringExtra8 = r2.getStringExtra(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE);
                            String stringExtra9 = r2.getStringExtra(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE);
                            String stringExtra10 = r2.getStringExtra(TraeAudioManager.EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME);
                            String str2 = "\n";
                            boolean z2 = true;
                            for (int i2 = 0; i2 < stringArrayExtra2.length; i2++) {
                                try {
                                    str2 = str2 + "AudioSession|    " + i2 + " " + stringArrayExtra2[i2] + "\n";
                                    if (stringArrayExtra2[i2].equals(TraeAudioManager.DEVICE_WIREDHEADSET) || stringArrayExtra2[i2].equals(TraeAudioManager.DEVICE_BLUETOOTHHEADSET)) {
                                        z2 = false;
                                    }
                                } catch (Exception e) {
                                    e = e;
                                    r2 = TAG;
                                    QLog.m600e(r2, "AudioSession| nSessinId = " + this.mSessionId + " onReceive::intent:" + intent.toString() + " intent.getAction():" + intent.getAction() + " Exception:" + e.getMessage());
                                }
                            }
                            this._canSwtich2Earphone = z2;
                            this._connectedDev = stringExtra8;
                            String str3 = "AudioSession|[onGetDeviceListRes] err:" + intExtra + " connected:" + stringExtra8 + " prevConnected:" + stringExtra9 + " bt:" + stringExtra10 + " Num:" + stringArrayExtra2.length + (str2 + "\n");
                            String str4 = TAG;
                            QLog.m606w(str4, str3);
                            ITraeAudioCallback iTraeAudioCallback7 = this.mCallback;
                            r2 = str4;
                            if (iTraeAudioCallback7 != null) {
                                iTraeAudioCallback7.onGetDeviceListRes(intExtra, stringArrayExtra2, stringExtra8, stringExtra9, stringExtra10);
                                r2 = str4;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            r2 = TAG;
                            QLog.m600e(r2, "AudioSession| nSessinId = " + this.mSessionId + " onReceive::intent:" + intent.toString() + " intent.getAction():" + intent.getAction() + " Exception:" + e.getMessage());
                        }
                    } else {
                        r2 = TAG;
                        try {
                            if (TraeAudioManager.OPERATION_CONNECTDEVICE.equals(stringExtra)) {
                                String stringExtra11 = intent.getStringExtra(TraeAudioManager.CONNECTDEVICE_RESULT_DEVICENAME);
                                QLog.m606w(r2, "AudioSession|[onConnectDeviceRes] err:" + intExtra + " dev:" + stringExtra11);
                                ITraeAudioCallback iTraeAudioCallback8 = this.mCallback;
                                r2 = r2;
                                if (iTraeAudioCallback8 != null) {
                                    iTraeAudioCallback8.onConnectDeviceRes(intExtra, stringExtra11, intExtra == 0);
                                    r2 = r2;
                                }
                            } else if (TraeAudioManager.OPERATION_EARACTION.equals(stringExtra)) {
                                QLog.m606w(r2, "AudioSession|[onConnectDeviceRes] err:" + intExtra + " earAction:" + intent.getIntExtra(TraeAudioManager.EXTRA_EARACTION, -1));
                                r2 = r2;
                            } else if (TraeAudioManager.OPERATION_ISDEVICECHANGABLED.equals(stringExtra)) {
                                boolean booleanExtra3 = intent.getBooleanExtra(TraeAudioManager.ISDEVICECHANGABLED_RESULT_ISCHANGABLED, false);
                                StringBuilder sb = new StringBuilder("AudioSession|[onIsDeviceChangabledRes] err:");
                                sb.append(intExtra);
                                sb.append(" Changabled:");
                                sb.append(booleanExtra3 ? "Y" : "N");
                                QLog.m606w(r2, sb.toString());
                                ITraeAudioCallback iTraeAudioCallback9 = this.mCallback;
                                r2 = r2;
                                if (iTraeAudioCallback9 != null) {
                                    iTraeAudioCallback9.onIsDeviceChangabledRes(intExtra, booleanExtra3);
                                    r2 = r2;
                                }
                            } else if (TraeAudioManager.OPERATION_GETCONNECTEDDEVICE.equals(stringExtra)) {
                                String stringExtra12 = intent.getStringExtra(TraeAudioManager.GETCONNECTEDDEVICE_RESULT_LIST);
                                QLog.m606w(r2, "AudioSession|[onGetConnectedDeviceRes] err:" + intExtra + " dev:" + stringExtra12);
                                ITraeAudioCallback iTraeAudioCallback10 = this.mCallback;
                                r2 = r2;
                                if (iTraeAudioCallback10 != null) {
                                    iTraeAudioCallback10.onGetConnectedDeviceRes(intExtra, stringExtra12);
                                    r2 = r2;
                                }
                            } else if (TraeAudioManager.OPERATION_GETCONNECTINGDEVICE.equals(stringExtra)) {
                                String stringExtra13 = intent.getStringExtra(TraeAudioManager.GETCONNECTINGDEVICE_RESULT_LIST);
                                QLog.m606w(r2, "AudioSession|[onGetConnectingDeviceRes] err:" + intExtra + " dev:" + stringExtra13);
                                ITraeAudioCallback iTraeAudioCallback11 = this.mCallback;
                                r2 = r2;
                                if (iTraeAudioCallback11 != null) {
                                    iTraeAudioCallback11.onGetConnectingDeviceRes(intExtra, stringExtra13);
                                    r2 = r2;
                                }
                            } else if (TraeAudioManager.OPERATION_GETSTREAMTYPE.equals(stringExtra)) {
                                int intExtra3 = intent.getIntExtra(TraeAudioManager.EXTRA_DATA_STREAMTYPE, -1);
                                QLog.m606w(r2, "AudioSession|[onGetStreamTypeRes] err:" + intExtra + " st:" + intExtra3);
                                ITraeAudioCallback iTraeAudioCallback12 = this.mCallback;
                                r2 = r2;
                                if (iTraeAudioCallback12 != null) {
                                    iTraeAudioCallback12.onGetStreamTypeRes(intExtra, intExtra3);
                                    r2 = r2;
                                }
                            } else if (TraeAudioManager.NOTIFY_RING_COMPLETION.equals(stringExtra)) {
                                String stringExtra14 = intent.getStringExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING);
                                QLog.m606w(r2, "AudioSession|[onRingCompletion] err:" + intExtra + " userData:" + stringExtra14);
                                ITraeAudioCallback iTraeAudioCallback13 = this.mCallback;
                                r2 = r2;
                                if (iTraeAudioCallback13 != null) {
                                    iTraeAudioCallback13.onRingCompletion(intExtra, stringExtra14);
                                    r2 = r2;
                                }
                            } else {
                                r2 = r2;
                                if (TraeAudioManager.OPERATION_VOICECALL_PREPROCESS.equals(stringExtra)) {
                                    QLog.m606w(r2, "AudioSession|[onVoicecallPreprocess] err:" + intExtra);
                                    ITraeAudioCallback iTraeAudioCallback14 = this.mCallback;
                                    r2 = r2;
                                    if (iTraeAudioCallback14 != null) {
                                        iTraeAudioCallback14.onVoicecallPreprocessRes(intExtra);
                                        r2 = r2;
                                    }
                                }
                            }
                        } catch (Exception e3) {
                            e = e3;
                            QLog.m600e(r2, "AudioSession| nSessinId = " + this.mSessionId + " onReceive::intent:" + intent.toString() + " intent.getAction():" + intent.getAction() + " Exception:" + e.getMessage());
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Exception e6) {
            e = e6;
            r2 = TAG;
        }
    }
}
