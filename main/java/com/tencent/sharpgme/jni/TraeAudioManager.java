package com.tencent.sharpgme.jni;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.security.CertificateUtil;
import com.luck.picture.lib.config.PictureMimeType;
import com.tencent.p014av.ptt.PttError;
import com.tencent.p014av.utils.QLog;
import com.tencent.sharpgme.jni.TraeMediaPlayer;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes3.dex */
public class TraeAudioManager extends BroadcastReceiver {
    public static final String ACTION_TRAEAUDIOMANAGER_NOTIFY = "com.tencent.sharpgme.ACTION_TRAEAUDIOMANAGER_NOTIFY";
    public static final String ACTION_TRAEAUDIOMANAGER_REQUEST = "com.tencent.sharpgme.ACTION_TRAEAUDIOMANAGER_REQUEST";
    public static final String ACTION_TRAEAUDIOMANAGER_RES = "com.tencent.sharpgme.ACTION_TRAEAUDIOMANAGER_RES";
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_A2DP = 128;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES = 256;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER = 512;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_SCO = 16;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_SCO_CARKIT = 64;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_SCO_HEADSET = 32;
    static final int AUDIO_DEVICE_OUT_EARPIECE = 1;
    static final int AUDIO_DEVICE_OUT_SPEAKER = 2;
    static final int AUDIO_DEVICE_OUT_WIRED_HEADPHONE = 8;
    static final int AUDIO_DEVICE_OUT_WIRED_HEADSET = 4;
    public static final int AUDIO_MANAGER_ACTIVE_NONE = 0;
    public static final int AUDIO_MANAGER_ACTIVE_RING = 2;
    public static final int AUDIO_MANAGER_ACTIVE_VOICECALL = 1;
    static final String AUDIO_PARAMETER_STREAM_ROUTING = "routing";
    public static final String CONNECTDEVICE_DEVICENAME = "CONNECTDEVICE_DEVICENAME";
    public static final String CONNECTDEVICE_RESULT_DEVICENAME = "CONNECTDEVICE_RESULT_DEVICENAME";
    public static String CurConnectedDevice = "DEVICE_NONE";
    public static final String DEVICE_BLUETOOTHHEADSET = "DEVICE_BLUETOOTHHEADSET";
    public static final String DEVICE_EARPHONE = "DEVICE_EARPHONE";
    public static final String DEVICE_NONE = "DEVICE_NONE";
    public static final String DEVICE_SPEAKERPHONE = "DEVICE_SPEAKERPHONE";
    public static final int DEVICE_STATUS_CONNECTED = 2;
    public static final int DEVICE_STATUS_CONNECTING = 1;
    public static final int DEVICE_STATUS_DISCONNECTED = 0;
    public static final int DEVICE_STATUS_DISCONNECTING = 3;
    public static final int DEVICE_STATUS_ERROR = -1;
    public static final int DEVICE_STATUS_UNCHANGEABLE = 4;
    public static final String DEVICE_WIREDHEADSET = "DEVICE_WIREDHEADSET";
    public static final int EARACTION_AWAY = 0;
    public static final int EARACTION_CLOSE = 1;
    public static final String EXTRA_DATA_AVAILABLEDEVICE_LIST = "EXTRA_DATA_AVAILABLEDEVICE_LIST";
    public static final String EXTRA_DATA_CONNECTEDDEVICE = "EXTRA_DATA_CONNECTEDDEVICE";
    public static final String EXTRA_DATA_DEVICECONFIG = "EXTRA_DATA_DEVICECONFIG";
    public static final String EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME = "EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME";
    public static final String EXTRA_DATA_PREV_CONNECTEDDEVICE = "EXTRA_DATA_PREV_CONNECTEDDEVICE";
    public static final String EXTRA_DATA_ROUTESWITCHEND_DEV = "EXTRA_DATA_ROUTESWITCHEND_DEV";
    public static final String EXTRA_DATA_ROUTESWITCHEND_TIME = "EXTRA_DATA_ROUTESWITCHEND_TIME";
    public static final String EXTRA_DATA_ROUTESWITCHSTART_FROM = "EXTRA_DATA_ROUTESWITCHSTART_FROM";
    public static final String EXTRA_DATA_ROUTESWITCHSTART_TO = "EXTRA_DATA_ROUTESWITCHSTART_TO";
    public static final String EXTRA_DATA_STREAMTYPE = "EXTRA_DATA_STREAMTYPE";
    public static final String EXTRA_EARACTION = "EXTRA_EARACTION";
    public static final int FORCE_ANALOG_DOCK = 8;
    public static final int FORCE_BT_A2DP = 4;
    public static final int FORCE_BT_CAR_DOCK = 6;
    public static final int FORCE_BT_DESK_DOCK = 7;
    public static final int FORCE_BT_SCO = 3;
    public static final int FORCE_DEFAULT = 0;
    public static final int FORCE_DIGITAL_DOCK = 9;
    public static final int FORCE_HEADPHONES = 2;
    public static final int FORCE_NONE = 0;
    public static final int FORCE_NO_BT_A2DP = 10;
    public static final int FORCE_SPEAKER = 1;
    public static final int FORCE_WIRED_ACCESSORY = 5;
    public static final int FOR_COMMUNICATION = 0;
    public static final int FOR_DOCK = 3;
    public static final int FOR_MEDIA = 1;
    public static final int FOR_RECORD = 2;
    public static final String GETCONNECTEDDEVICE_RESULT_LIST = "GETCONNECTEDDEVICE_REULT_LIST";
    public static final String GETCONNECTINGDEVICE_RESULT_LIST = "GETCONNECTINGDEVICE_REULT_LIST";
    public static final String ISDEVICECHANGABLED_RESULT_ISCHANGABLED = "ISDEVICECHANGABLED_REULT_ISCHANGABLED";
    public static boolean IsEarPhoneSupported = false;
    public static boolean IsMusicScene = false;
    public static boolean IsUpdateSceneFlag = false;
    public static final int MODE_GAME_PLAYBACK = 7;
    public static final int MODE_MUSIC_PLAYBACK = 2;
    public static final int MODE_MUSIC_PLAY_RECORD = 1;
    public static final int MODE_MUSIC_PLAY_RECORD_HIGH_QUALITY = 3;
    public static final int MODE_MUSIC_PLAY_RECORD_LOW_QUALITY = 5;
    public static final int MODE_VOICE_CHAT = 0;
    public static final int MODE_VOICE_PLAYBACK = 4;
    public static final String MUSIC_CONFIG = "DEVICE_SPEAKERPHONE;DEVICE_WIREDHEADSET;DEVICE_BLUETOOTHHEADSET;";
    public static final String NOTIFY_DEVICECHANGABLE_UPDATE = "NOTIFY_DEVICECHANGABLE_UPDATE";
    public static final String NOTIFY_DEVICECHANGABLE_UPDATE_DATE = "NOTIFY_DEVICECHANGABLE_UPDATE_DATE";
    public static final String NOTIFY_DEVICELIST_UPDATE = "NOTIFY_DEVICELISTUPDATE";
    public static final String NOTIFY_RING_COMPLETION = "NOTIFY_RING_COMPLETION";
    public static final String NOTIFY_ROUTESWITCHEND = "NOTIFY_ROUTESWITCHEND";
    public static final String NOTIFY_ROUTESWITCHSTART = "NOTIFY_ROUTESWITCHSTART";
    public static final String NOTIFY_SERVICE_STATE = "NOTIFY_SERVICE_STATE";
    public static final String NOTIFY_SERVICE_STATE_DATE = "NOTIFY_SERVICE_STATE_DATE";
    public static final String NOTIFY_STREAMTYPE_UPDATE = "NOTIFY_STREAMTYPE_UPDATE";
    private static final int NUM_FORCE_CONFIG = 11;
    private static final int NUM_FORCE_USE = 4;
    public static final String OPERATION_CONNECTDEVICE = "OPERATION_CONNECTDEVICE";
    public static final String OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE = "OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE";
    public static final String OPERATION_EARACTION = "OPERATION_EARACTION";
    public static final String OPERATION_FORCE_CONNECTDEVICE = "OPERATION_FORCE_CONNECTDEVICE";
    public static final String OPERATION_GETCONNECTEDDEVICE = "OPERATION_GETCONNECTEDDEVICE";
    public static final String OPERATION_GETCONNECTINGDEVICE = "OPERATION_GETCONNECTINGDEVICE";
    public static final String OPERATION_GETDEVICELIST = "OPERATION_GETDEVICELIST";
    public static final String OPERATION_GETSTREAMTYPE = "OPERATION_GETSTREAMTYPE";
    public static final String OPERATION_ISDEVICECHANGABLED = "OPERATION_ISDEVICECHANGABLED";
    public static final String OPERATION_RECOVER_AUDIO_FOCUS = "OPERATION_RECOVER_AUDIO_FOCUS";
    public static final String OPERATION_REGISTERAUDIOSESSION = "OPERATION_REGISTERAUDIOSESSION";
    public static final String OPERATION_REQUEST_RELEASE_AUDIO_FOCUS = "OPERATION_REQUEST_RELEASE_AUDIO_FOCUS";
    public static final String OPERATION_STARTRING = "OPERATION_STARTRING";
    public static final String OPERATION_STARTSERVICE = "OPERATION_STARTSERVICE";
    public static final String OPERATION_STOPRING = "OPERATION_STOPRING";
    public static final String OPERATION_STOPSERVICE = "OPERATION_STOPSERVICE";
    public static final String OPERATION_VOICECALL_AUDIOPARAM_CHANGED = "OPERATION_VOICECALL_AUDIOPARAM_CHANGED";
    public static final String OPERATION_VOICECALL_POSTPROCESS = "OPERATION_VOICECALL_POSTROCESS";
    public static final String OPERATION_VOICECALL_PREPROCESS = "OPERATION_VOICECALL_PREPROCESS";
    public static final String PARAM_DEVICE = "PARAM_DEVICE";
    public static final String PARAM_ERROR = "PARAM_ERROR";
    public static final String PARAM_ISHOSTSIDE = "PARAM_ISHOSTSIDE";
    public static final String PARAM_MODEPOLICY = "PARAM_MODEPOLICY";
    public static final String PARAM_OPERATION = "PARAM_OPERATION";
    public static final String PARAM_RES_ERRCODE = "PARAM_RES_ERRCODE";
    public static final String PARAM_RING_DATASOURCE = "PARAM_RING_DATASOURCE";
    public static final String PARAM_RING_FILEPATH = "PARAM_RING_FILEPATH";
    public static final String PARAM_RING_LOOP = "PARAM_RING_LOOP";
    public static final String PARAM_RING_LOOPCOUNT = "PARAM_RING_LOOPCOUNT";
    public static final String PARAM_RING_MODE = "PARAM_RING_MODE";
    public static final String PARAM_RING_RSID = "PARAM_RING_RSID";
    public static final String PARAM_RING_URI = "PARAM_RING_URI";
    public static final String PARAM_RING_USERDATA_STRING = "PARAM_RING_USERDATA_STRING";
    public static final String PARAM_SESSIONID = "PARAM_SESSIONID";
    public static final String PARAM_STATUS = "PARAM_STATUS";
    public static final String PARAM_STREAMTYPE = "PARAM_STREAMTYPE";
    public static final String REGISTERAUDIOSESSION_ISREGISTER = "REGISTERAUDIOSESSION_ISREGISTER";
    public static final int RES_ERRCODE_DEVICE_BTCONNCECTED_TIMEOUT = 10;
    public static final int RES_ERRCODE_DEVICE_NOT_VISIABLE = 8;
    public static final int RES_ERRCODE_DEVICE_UNCHANGEABLE = 9;
    public static final int RES_ERRCODE_DEVICE_UNKOWN = 7;
    public static final int RES_ERRCODE_NONE = 0;
    public static final int RES_ERRCODE_RING_NOT_EXIST = 5;
    public static final int RES_ERRCODE_SERVICE_OFF = 1;
    public static final int RES_ERRCODE_STOPRING_INTERRUPT = 4;
    public static final int RES_ERRCODE_VOICECALLPOST_INTERRUPT = 6;
    public static final int RES_ERRCODE_VOICECALL_EXIST = 2;
    public static final int RES_ERRCODE_VOICECALL_NOT_EXIST = 3;
    private static final String TAG = "TRAEJava";
    public static final String VIDEO_CONFIG = "DEVICE_EARPHONE;DEVICE_SPEAKERPHONE;DEVICE_BLUETOOTHHEADSET;DEVICE_WIREDHEADSET;";
    public static final String VOICECALL_CONFIG = "DEVICE_SPEAKERPHONE;DEVICE_EARPHONE;DEVICE_BLUETOOTHHEADSET;DEVICE_WIREDHEADSET;";
    public static int nSceneMode = -1;
    public static int nSpeakerStreamType;
    public static int nSpecialModeBypass3A;
    Context _context;
    TraeAudioManagerLooper mTraeAudioManagerLooper;
    static ReentrantLock _glock = new ReentrantLock();
    static TraeAudioManager _ginstance = null;
    static int _gHostProcessId = -1;
    static final String[] forceName = {"FORCE_NONE", "FORCE_SPEAKER", "FORCE_HEADPHONES", "FORCE_BT_SCO", "FORCE_BT_A2DP", "FORCE_WIRED_ACCESSORY", "FORCE_BT_CAR_DOCK", "FORCE_BT_DESK_DOCK", "FORCE_ANALOG_DOCK", "FORCE_NO_BT_A2DP", "FORCE_DIGITAL_DOCK"};
    AudioManager _am = null;
    boolean _speakerOn = true;
    int _activeMode = 0;
    int _prevMode = 0;
    int _streamType = 0;
    int _modePolicy = -1;
    private int bluetoothState = 4;
    boolean IsBluetoothA2dpExisted = true;
    TraeAudioSessionHost _audioSessionHost = null;
    DeviceConfigManager _deviceConfigManager = null;
    BluetoohHeadsetCheckInterface _bluetoothCheck = null;
    String sessionConnectedDev = DEVICE_NONE;
    ReentrantLock _lock = new ReentrantLock();
    switchThread _switchThread = null;

    /* loaded from: classes3.dex */
    public interface Bluetooth_State {
        public static final int ERROR = 2;
        public static final int HEADSET_AVAILABLE = 4;
        public static final int HEADSET_UNAVAILABLE = 3;
        public static final int SCO_CONNECTED = 7;
        public static final int SCO_CONNECTING = 6;
        public static final int SCO_DISCONNECTING = 5;
        public static final int UNINITIALIZED = 1;
    }

    int InternalSessionEarAction(HashMap<String, Object> hashMap) {
        return 0;
    }

    /* loaded from: classes3.dex */
    public class Parameters {
        public static final String BLUETOOTHPOLICY = "com.tencent.sharpgme.TraeAudioManager.Parameters.BLUETOOTHPOLICY";
        public static final String CONTEXT = "com.tencent.sharpgme.TraeAudioManager.Parameters.CONTEXT";
        public static final String DEVICECONFIG = "com.tencent.sharpgme.TraeAudioManager.Parameters.DEVICECONFIG";
        public static final String MODEPOLICY = "com.tencent.sharpgme.TraeAudioManager.Parameters.MODEPOLICY";

        public Parameters() {
        }
    }

    public static String getConnectedDevice() {
        return CurConnectedDevice;
    }

    public static boolean checkDevName(String str) {
        if (str == null) {
            return false;
        }
        return DEVICE_SPEAKERPHONE.equals(str) || DEVICE_EARPHONE.equals(str) || DEVICE_WIREDHEADSET.equals(str) || DEVICE_BLUETOOTHHEADSET.equals(str);
    }

    public static boolean isHandfree(String str) {
        return checkDevName(str) && DEVICE_SPEAKERPHONE.equals(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class DeviceConfigManager {
        HashMap<String, DeviceConfig> deviceConfigs = new HashMap<>();
        String prevConnectedDevice = TraeAudioManager.DEVICE_NONE;
        String connectedDevice = TraeAudioManager.DEVICE_NONE;
        String connectingDevice = TraeAudioManager.DEVICE_NONE;
        ReentrantLock mLock = new ReentrantLock();
        boolean visiableUpdate = false;
        String _bluetoothDevName = "unknow";

        /* loaded from: classes3.dex */
        public class DeviceConfig {
            String deviceName = TraeAudioManager.DEVICE_NONE;
            boolean visible = false;
            int priority = 0;

            public DeviceConfig() {
            }

            public boolean init(String str, int i) {
                if (str == null || str.length() <= 0 || !TraeAudioManager.checkDevName(str)) {
                    return false;
                }
                this.deviceName = str;
                this.priority = i;
                return true;
            }

            public String getDeviceName() {
                return this.deviceName;
            }

            public boolean getVisible() {
                return this.visible;
            }

            public int getPriority() {
                return this.priority;
            }

            public void setVisible(boolean z) {
                this.visible = z;
            }
        }

        public DeviceConfigManager() {
        }

        public boolean init(String str) {
            String replace;
            AudioDeviceInterface.LogTraceEntry(" strConfigs:" + str);
            if (str != null && str.length() > 0 && (replace = str.replace("\n", "").replace("\r", "")) != null && replace.length() > 0) {
                if (replace.indexOf(";") < 0) {
                    replace = replace + ";";
                }
                String[] split = replace.split(";");
                if (split != null && 1 <= split.length) {
                    this.mLock.lock();
                    for (int i = 0; i < split.length; i++) {
                        _addConfig(split[i], i);
                    }
                    this.mLock.unlock();
                    TraeAudioManager.this.printDevices();
                    return true;
                }
            }
            return false;
        }

        boolean _addConfig(String str, int i) {
            AudioDeviceInterface.LogTraceEntry(" devName:" + str + " priority:" + i);
            DeviceConfig deviceConfig = new DeviceConfig();
            if (deviceConfig.init(str, i)) {
                if (this.deviceConfigs.containsKey(str)) {
                    QLog.m600e(TraeAudioManager.TAG, "err dev exist!");
                    return false;
                }
                this.deviceConfigs.put(str, deviceConfig);
                this.visiableUpdate = true;
                QLog.m606w(TraeAudioManager.TAG, " n" + getDeviceNumber() + " 0:" + getDeviceName(0));
                AudioDeviceInterface.LogTraceExit();
                return true;
            }
            QLog.m600e(TraeAudioManager.TAG, " err dev init!");
            return false;
        }

        public void clearConfig() {
            this.mLock.lock();
            this.deviceConfigs.clear();
            this.prevConnectedDevice = TraeAudioManager.DEVICE_NONE;
            this.connectedDevice = TraeAudioManager.DEVICE_NONE;
            TraeAudioManager.CurConnectedDevice = TraeAudioManager.DEVICE_NONE;
            this.connectingDevice = TraeAudioManager.DEVICE_NONE;
            this.mLock.unlock();
        }

        public boolean getVisiableUpdateFlag() {
            this.mLock.lock();
            boolean z = this.visiableUpdate;
            this.mLock.unlock();
            return z;
        }

        public void resetVisiableUpdateFlag() {
            this.mLock.lock();
            this.visiableUpdate = false;
            this.mLock.unlock();
        }

        public boolean setVisible(String str, boolean z) {
            boolean z2;
            this.mLock.lock();
            DeviceConfig deviceConfig = this.deviceConfigs.get(str);
            if (deviceConfig == null || deviceConfig.getVisible() == z) {
                z2 = false;
            } else {
                deviceConfig.setVisible(z);
                z2 = true;
                this.visiableUpdate = true;
                StringBuilder sb = new StringBuilder(" ++setVisible:");
                sb.append(str);
                sb.append(z ? " Y" : " N");
                QLog.m606w(TraeAudioManager.TAG, sb.toString());
            }
            this.mLock.unlock();
            return z2;
        }

        public void setBluetoothName(String str) {
            if (str == null) {
                this._bluetoothDevName = "unknow";
            } else if (str.isEmpty()) {
                this._bluetoothDevName = "unknow";
            } else {
                this._bluetoothDevName = str;
            }
        }

        public String getBluetoothName() {
            return this._bluetoothDevName;
        }

        public boolean getVisible(String str) {
            this.mLock.lock();
            DeviceConfig deviceConfig = this.deviceConfigs.get(str);
            boolean visible = deviceConfig != null ? deviceConfig.getVisible() : false;
            this.mLock.unlock();
            return visible;
        }

        public int getPriority(String str) {
            this.mLock.lock();
            DeviceConfig deviceConfig = this.deviceConfigs.get(str);
            int priority = deviceConfig != null ? deviceConfig.getPriority() : -1;
            this.mLock.unlock();
            return priority;
        }

        public int getDeviceNumber() {
            this.mLock.lock();
            int size = this.deviceConfigs.size();
            this.mLock.unlock();
            return size;
        }

        public String getDeviceName(int i) {
            DeviceConfig deviceConfig;
            this.mLock.lock();
            Iterator<Map.Entry<String, DeviceConfig>> it = this.deviceConfigs.entrySet().iterator();
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    deviceConfig = null;
                    break;
                }
                Map.Entry<String, DeviceConfig> next = it.next();
                if (i2 == i) {
                    deviceConfig = next.getValue();
                    break;
                }
                i2++;
            }
            String deviceName = deviceConfig != null ? deviceConfig.getDeviceName() : TraeAudioManager.DEVICE_NONE;
            this.mLock.unlock();
            return deviceName;
        }

        public String getAvailabledHighestPriorityDevice(String str) {
            this.mLock.lock();
            DeviceConfig deviceConfig = null;
            for (Map.Entry<String, DeviceConfig> entry : this.deviceConfigs.entrySet()) {
                entry.getKey();
                entry.getValue();
                DeviceConfig value = entry.getValue();
                if (value != null && value.getVisible() && !value.getDeviceName().equals(str) && (deviceConfig == null || value.getPriority() >= deviceConfig.getPriority())) {
                    deviceConfig = value;
                }
            }
            this.mLock.unlock();
            return deviceConfig != null ? deviceConfig.getDeviceName() : TraeAudioManager.DEVICE_SPEAKERPHONE;
        }

        public String getAvailabledHighestPriorityDevice() {
            this.mLock.lock();
            DeviceConfig deviceConfig = null;
            for (Map.Entry<String, DeviceConfig> entry : this.deviceConfigs.entrySet()) {
                entry.getKey();
                entry.getValue();
                DeviceConfig value = entry.getValue();
                if (value != null && value.getVisible() && (deviceConfig == null || value.getPriority() >= deviceConfig.getPriority())) {
                    deviceConfig = value;
                }
            }
            this.mLock.unlock();
            return deviceConfig != null ? deviceConfig.getDeviceName() : TraeAudioManager.DEVICE_SPEAKERPHONE;
        }

        public String getConnectingDevice() {
            this.mLock.lock();
            DeviceConfig deviceConfig = this.deviceConfigs.get(this.connectingDevice);
            String str = (deviceConfig == null || !deviceConfig.getVisible()) ? null : this.connectingDevice;
            this.mLock.unlock();
            return str;
        }

        public String getConnectedDevice() {
            this.mLock.lock();
            String _getConnectedDevice = _getConnectedDevice();
            this.mLock.unlock();
            return _getConnectedDevice;
        }

        public String getPrevConnectedDevice() {
            this.mLock.lock();
            String _getPrevConnectedDevice = _getPrevConnectedDevice();
            this.mLock.unlock();
            return _getPrevConnectedDevice;
        }

        public boolean setConnecting(String str) {
            boolean z;
            this.mLock.lock();
            DeviceConfig deviceConfig = this.deviceConfigs.get(str);
            if (deviceConfig == null || !deviceConfig.getVisible()) {
                z = false;
            } else {
                this.connectingDevice = str;
                z = true;
            }
            this.mLock.unlock();
            return z;
        }

        public boolean setConnected(String str) {
            boolean z;
            this.mLock.lock();
            DeviceConfig deviceConfig = this.deviceConfigs.get(str);
            if (deviceConfig == null || !deviceConfig.getVisible()) {
                z = false;
            } else {
                String str2 = this.connectedDevice;
                if (str2 != null && !str2.equals(str)) {
                    this.prevConnectedDevice = this.connectedDevice;
                }
                this.connectedDevice = str;
                TraeAudioManager.CurConnectedDevice = str;
                this.connectingDevice = "";
                z = true;
            }
            this.mLock.unlock();
            return z;
        }

        public boolean isConnected(String str) {
            this.mLock.lock();
            DeviceConfig deviceConfig = this.deviceConfigs.get(str);
            boolean equals = (deviceConfig == null || !deviceConfig.getVisible()) ? false : this.connectedDevice.equals(str);
            this.mLock.unlock();
            return equals;
        }

        public HashMap<String, Object> getSnapParams() {
            HashMap<String, Object> hashMap = new HashMap<>();
            this.mLock.lock();
            hashMap.put(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST, _getAvailableDeviceList());
            hashMap.put(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE, _getConnectedDevice());
            hashMap.put(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE, _getPrevConnectedDevice());
            this.mLock.unlock();
            return hashMap;
        }

        public ArrayList<String> getAvailableDeviceList() {
            new ArrayList();
            this.mLock.lock();
            ArrayList<String> _getAvailableDeviceList = _getAvailableDeviceList();
            this.mLock.unlock();
            return _getAvailableDeviceList;
        }

        ArrayList<String> _getAvailableDeviceList() {
            ArrayList<String> arrayList = new ArrayList<>();
            Iterator<Map.Entry<String, DeviceConfig>> it = this.deviceConfigs.entrySet().iterator();
            while (it.hasNext()) {
                DeviceConfig value = it.next().getValue();
                if (value != null && value.getVisible()) {
                    arrayList.add(value.getDeviceName());
                }
            }
            return arrayList;
        }

        String _getConnectedDevice() {
            DeviceConfig deviceConfig = this.deviceConfigs.get(this.connectedDevice);
            return (deviceConfig == null || !deviceConfig.getVisible()) ? TraeAudioManager.DEVICE_NONE : this.connectedDevice;
        }

        String _getPrevConnectedDevice() {
            DeviceConfig deviceConfig = this.deviceConfigs.get(this.prevConnectedDevice);
            return (deviceConfig == null || !deviceConfig.getVisible()) ? TraeAudioManager.DEVICE_NONE : this.prevConnectedDevice;
        }
    }

    void printDevices() {
        AudioDeviceInterface.LogTraceEntry("");
        int deviceNumber = this._deviceConfigManager.getDeviceNumber();
        QLog.m606w(TAG, "   ConnectedDevice:" + this._deviceConfigManager.getConnectedDevice());
        QLog.m606w(TAG, "   ConnectingDevice:" + this._deviceConfigManager.getConnectingDevice());
        QLog.m606w(TAG, "   prevConnectedDevice:" + this._deviceConfigManager.getPrevConnectedDevice());
        QLog.m606w(TAG, "   AHPDevice:" + this._deviceConfigManager.getAvailabledHighestPriorityDevice());
        QLog.m606w(TAG, "   deviceNamber:" + deviceNumber);
        for (int i = 0; i < deviceNumber; i++) {
            String deviceName = this._deviceConfigManager.getDeviceName(i);
            QLog.m606w(TAG, "      " + i + " devName:" + deviceName + " Visible:" + this._deviceConfigManager.getVisible(deviceName) + " Priority:" + this._deviceConfigManager.getPriority(deviceName));
        }
        String[] strArr = (String[]) this._deviceConfigManager.getAvailableDeviceList().toArray(new String[0]);
        QLog.m606w(TAG, "   AvailableNamber:" + strArr.length);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str = strArr[i2];
            QLog.m606w(TAG, "      " + i2 + " devName:" + str + " Visible:" + this._deviceConfigManager.getVisible(str) + " Priority:" + this._deviceConfigManager.getPriority(str));
        }
        AudioDeviceInterface.LogTraceExit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isCloseSystemAPM(int i) {
        if (i != -1) {
            return false;
        }
        if (Build.MANUFACTURER.equals("Xiaomi")) {
            if (Build.MODEL.equals("MI 2") || Build.MODEL.equals("MI 2A") || Build.MODEL.equals("MI 2S") || Build.MODEL.equals("MI 2SC")) {
                return true;
            }
        } else if (Build.MANUFACTURER.equals("samsung") && Build.MODEL.equals("SCH-I959")) {
            return true;
        }
        return false;
    }

    public static boolean IsEabiLowVersionByAbi(String str) {
        if (str == null) {
            return true;
        }
        if (str.contains("x86") || str.contains("mips")) {
            return false;
        }
        if (str.equalsIgnoreCase("armeabi")) {
            return true;
        }
        return (str.equalsIgnoreCase("armeabi-v7a") || str.equalsIgnoreCase("arm64-v8a")) ? false : true;
    }

    static boolean IsEabiLowVersion() {
        String str = Build.CPU_ABI;
        try {
            String str2 = (String) Build.class.getDeclaredField("CPU_ABI2").get(null);
            QLog.m606w(TAG, "IsEabiVersion CPU_ABI:" + str + " CPU_ABI2:" + str2);
            return IsEabiLowVersionByAbi(str) && IsEabiLowVersionByAbi(str2);
        } catch (Exception unused) {
            return IsEabiLowVersionByAbi(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getAudioSource(int i) {
        if (IsMusicScene) {
            return 0;
        }
        if (IsEabiLowVersion()) {
            QLog.m606w(TAG, "[Config] armeabi low Version, getAudioSource _audioSourcePolicy:" + i + " source:0");
            return 0;
        }
        if (i >= 0) {
            QLog.m606w(TAG, "[Config] getAudioSource _audioSourcePolicy:" + i + " source:" + i);
            return i;
        }
        QLog.m606w(TAG, "[Config] getAudioSource _audioSourcePolicy:" + i + " source:7");
        return 7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getAudioStreamType(int i) {
        if (IsMusicScene) {
            return 3;
        }
        if (IsEabiLowVersion()) {
            QLog.m606w(TAG, "[Config] armeabi low Version, getAudioStreamType audioStreamTypePolicy:" + i + " streamType:3");
            return 3;
        }
        int i2 = i >= 0 ? i : 0;
        QLog.m606w(TAG, "[Config] getAudioStreamType audioStreamTypePolicy:" + i + " streamType:" + i2);
        return i2;
    }

    static int getCallAudioMode(int i) {
        if (IsMusicScene) {
            return 0;
        }
        if (IsEabiLowVersion()) {
            QLog.m606w(TAG, "[Config] armeabi low Version, getCallAudioMode modePolicy:" + i + " mode:0");
            return 0;
        }
        if (i >= 0) {
            QLog.m606w(TAG, "[Config] getCallAudioMode modePolicy:" + i + " mode:" + i);
            return i;
        }
        QLog.m606w(TAG, "[Config] getCallAudioMode _modePolicy:" + i + " mode:3facturer:" + Build.MANUFACTURER + " model:" + Build.MODEL);
        return 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0077 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void updateDeviceStatus() {
        /*
            r7 = this;
            com.tencent.sharpgme.jni.TraeAudioManager$DeviceConfigManager r0 = r7._deviceConfigManager
            int r0 = r0.getDeviceNumber()
            r1 = 0
            r2 = r1
        L8:
            if (r2 >= r0) goto L7a
            com.tencent.sharpgme.jni.TraeAudioManager$DeviceConfigManager r3 = r7._deviceConfigManager
            java.lang.String r3 = r3.getDeviceName(r2)
            r4 = 1
            if (r3 == 0) goto L53
            java.lang.String r5 = "DEVICE_BLUETOOTHHEADSET"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L31
            com.tencent.sharpgme.jni.TraeAudioManager$BluetoohHeadsetCheckInterface r5 = r7._bluetoothCheck
            if (r5 != 0) goto L26
            com.tencent.sharpgme.jni.TraeAudioManager$DeviceConfigManager r5 = r7._deviceConfigManager
            boolean r5 = r5.setVisible(r3, r1)
            goto L54
        L26:
            com.tencent.sharpgme.jni.TraeAudioManager$DeviceConfigManager r6 = r7._deviceConfigManager
            boolean r5 = r5.isConnected()
            boolean r5 = r6.setVisible(r3, r5)
            goto L54
        L31:
            java.lang.String r5 = "DEVICE_WIREDHEADSET"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L46
            com.tencent.sharpgme.jni.TraeAudioManager$DeviceConfigManager r5 = r7._deviceConfigManager
            android.media.AudioManager r6 = r7._am
            boolean r6 = r6.isWiredHeadsetOn()
            boolean r5 = r5.setVisible(r3, r6)
            goto L54
        L46:
            java.lang.String r5 = "DEVICE_SPEAKERPHONE"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L53
            com.tencent.sharpgme.jni.TraeAudioManager$DeviceConfigManager r5 = r7._deviceConfigManager
            r5.setVisible(r3, r4)
        L53:
            r5 = r1
        L54:
            if (r5 != r4) goto L77
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "pollUpdateDevice dev:"
            r4.<init>(r5)
            r4.append(r3)
            java.lang.String r5 = " Visible:"
            r4.append(r5)
            com.tencent.sharpgme.jni.TraeAudioManager$DeviceConfigManager r5 = r7._deviceConfigManager
            boolean r3 = r5.getVisible(r3)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            java.lang.String r4 = "TRAEJava"
            com.tencent.p014av.utils.QLog.m606w(r4, r3)
        L77:
            int r2 = r2 + 1
            goto L8
        L7a:
            r7.checkAutoDeviceListUpdate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.sharpgme.jni.TraeAudioManager.updateDeviceStatus():void");
    }

    void _updateEarphoneVisable() {
        if (this._deviceConfigManager.getVisible(DEVICE_WIREDHEADSET)) {
            QLog.m606w(TAG, " detected headset plugin,so disable earphone");
            this._deviceConfigManager.setVisible(DEVICE_EARPHONE, false);
        } else {
            QLog.m606w(TAG, " detected headset plugout,so enable earphone");
            this._deviceConfigManager.setVisible(DEVICE_EARPHONE, true);
        }
    }

    void checkAutoDeviceListUpdate() {
        if (this._deviceConfigManager.getVisiableUpdateFlag()) {
            QLog.m606w(TAG, "checkAutoDeviceListUpdate got update!");
            _updateEarphoneVisable();
            this._deviceConfigManager.resetVisiableUpdateFlag();
            internalSendMessage(32785, new HashMap<>());
        }
    }

    void checkDevicePlug(String str, boolean z) {
        if (this._deviceConfigManager.getVisiableUpdateFlag()) {
            StringBuilder sb = new StringBuilder("checkDevicePlug got update dev:");
            sb.append(str);
            sb.append(z ? " piugin" : " plugout");
            sb.append(" connectedDev:");
            sb.append(this._deviceConfigManager.getConnectedDevice());
            QLog.m606w(TAG, sb.toString());
            _updateEarphoneVisable();
            this._deviceConfigManager.resetVisiableUpdateFlag();
            if (z) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put(PARAM_DEVICE, str);
                internalSendMessage(32786, hashMap);
                return;
            }
            String connectedDevice = this._deviceConfigManager.getConnectedDevice();
            if (connectedDevice.equals(str) || connectedDevice.equals(DEVICE_NONE)) {
                HashMap<String, Object> hashMap2 = new HashMap<>();
                hashMap2.put(PARAM_DEVICE, str);
                internalSendMessage(TraeAudioManagerLooper.MESSAGE_AUTO_DEVICELIST_PLUGOUT_UPDATE, hashMap2);
            } else {
                QLog.m606w(TAG, " ---No switch,plugout:" + str + " connectedDev:" + connectedDevice);
                internalSendMessage(32785, new HashMap<>());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class TraeAudioManagerLooper extends Thread {
        public static final int MESSAGE_AUTO_DEVICELIST_PLUGIN_UPDATE = 32786;
        public static final int MESSAGE_AUTO_DEVICELIST_PLUGOUT_UPDATE = 32787;
        public static final int MESSAGE_AUTO_DEVICELIST_UPDATE = 32785;
        public static final int MESSAGE_BEGIN = 32768;
        public static final int MESSAGE_CONNECTDEVICE = 32775;
        public static final int MESSAGE_CONNECT_HIGHEST_PRIORITY_DEVICE = 32789;
        public static final int MESSAGE_DISABLE = 32773;
        public static final int MESSAGE_EARACTION = 32776;
        public static final int MESSAGE_ENABLE = 32772;
        public static final int MESSAGE_FORCE_CONNECTDEVICE = 32792;
        public static final int MESSAGE_GETCONNECTEDDEVICE = 32778;
        public static final int MESSAGE_GETCONNECTINGDEVICE = 32779;
        public static final int MESSAGE_GETDEVICELIST = 32774;
        public static final int MESSAGE_GETSTREAMTYPE = 32784;
        public static final int MESSAGE_ISDEVICECHANGABLED = 32777;
        public static final int MESSAGE_RECOVER_AUDIO_FOCUS = 32791;
        public static final int MESSAGE_REQUEST_RELEASE_AUDIO_FOCUS = 32790;
        public static final int MESSAGE_STARTRING = 32782;
        public static final int MESSAGE_STOPRING = 32783;
        public static final int MESSAGE_VOICECALLPOSTPROCESS = 32781;
        public static final int MESSAGE_VOICECALLPREPROCESS = 32780;
        public static final int MESSAGE_VOICECALL_AUIDOPARAM_CHANGED = 32788;
        TraeAudioManager _parent;
        final boolean[] _started;
        Handler mMsgHandler = null;
        TraeMediaPlayer _ringPlayer = null;
        long _ringSessionID = -1;
        String _ringOperation = "";
        String _ringUserdata = "";
        boolean _enabled = false;
        String _lastCfg = "";
        int _preServiceMode = 0;
        int _preRingMode = 0;
        boolean IsFirstConnectDeviceFlag = true;
        long _voiceCallSessionID = -1;
        String _voiceCallOperation = "";
        AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = null;
        int _focusSteamType = 0;

        String msgToText(int i) {
            switch (i) {
                case 32768:
                    return "MESSAGE_BEGIN";
                case PttError.VOICE_V2T_INTERNAL_ERROR /* 32769 */:
                    return "MESSAGE_SETWIREDHEADSET";
                case PttError.VOICE_V2T_NETWORK_FAIL /* 32770 */:
                    return "MESSAGE_SETBLUETOOTHHEADSET";
                case 32771:
                    return "MESSAGE_SETDEVICECONFIG";
                case 32772:
                    return "MESSAGE_ENABLE";
                case 32773:
                    return "MESSAGE_DISABLE";
                case 32774:
                    return "MESSAGE_GETDEVICELIST";
                case 32775:
                    return "MESSAGE_CONNECTDEVICE";
                case 32776:
                    return "MESSAGE_EARACTION";
                case 32777:
                    return "MESSAGE_ISDEVICECHANGABLED";
                case MESSAGE_GETCONNECTEDDEVICE /* 32778 */:
                    return "MESSAGE_GETCONNECTEDDEVICE";
                case MESSAGE_GETCONNECTINGDEVICE /* 32779 */:
                    return "MESSAGE_GETCONNECTINGDEVICE";
                case MESSAGE_VOICECALLPREPROCESS /* 32780 */:
                    return "MESSAGE_VOICECALLPREPROCESS";
                case MESSAGE_VOICECALLPOSTPROCESS /* 32781 */:
                    return "MESSAGE_VOICECALLPOSTPROCESS";
                case MESSAGE_STARTRING /* 32782 */:
                    return "MESSAGE_STARTRING";
                case MESSAGE_STOPRING /* 32783 */:
                    return "MESSAGE_STOPRING";
                case 32784:
                    return "MESSAGE_GETSTREAMTYPE";
                case 32785:
                    return "MESSAGE_AUTO_DEVICELIST_UPDATE";
                case 32786:
                    return "MESSAGE_AUTO_DEVICELIST_PLUGIN_UPDATE";
                case MESSAGE_AUTO_DEVICELIST_PLUGOUT_UPDATE /* 32787 */:
                    return "MESSAGE_AUTO_DEVICELIST_PLUGOUT_UPDATE";
                case MESSAGE_VOICECALL_AUIDOPARAM_CHANGED /* 32788 */:
                    return "MESSAGE_VOICECALL_AUIDOPARAM_CHANGED";
                case MESSAGE_CONNECT_HIGHEST_PRIORITY_DEVICE /* 32789 */:
                    return "MESSAGE_CONNECT_HIGHEST_PRIORITY_DEVICE";
                case MESSAGE_REQUEST_RELEASE_AUDIO_FOCUS /* 32790 */:
                    return "MESSAGE_REQUEST_RELEASE_AUDIO_FOCUS";
                case MESSAGE_RECOVER_AUDIO_FOCUS /* 32791 */:
                    return "MESSAGE_RECOVER_AUDIO_FOCUS";
                case MESSAGE_FORCE_CONNECTDEVICE /* 32792 */:
                    return "MESSAGE_FORCE_CONNECTDEVICE";
                default:
                    return "MESSAGE_NONE";
            }
        }

        public TraeAudioManagerLooper(TraeAudioManager traeAudioManager) {
            boolean[] zArr = {false};
            this._started = zArr;
            this._parent = traeAudioManager;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            QLog.m600e(TraeAudioManager.TAG, "TraeAudioManagerLooper start...");
            start();
            synchronized (zArr) {
                if (!zArr[0]) {
                    try {
                        zArr.wait(3000L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
            QLog.m600e(TraeAudioManager.TAG, "  start used:" + (SystemClock.elapsedRealtime() - elapsedRealtime) + "ms");
        }

        public void quit() {
            AudioDeviceInterface.LogTraceEntry("");
            if (this.mMsgHandler == null) {
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.mMsgHandler.getLooper().quit();
            synchronized (this._started) {
                boolean[] zArr = this._started;
                if (zArr[0]) {
                    try {
                        zArr.wait(10000L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
            QLog.m600e(TraeAudioManager.TAG, "  quit used:" + (SystemClock.elapsedRealtime() - elapsedRealtime) + "ms");
            this.mMsgHandler = null;
            AudioDeviceInterface.LogTraceExit();
        }

        public int sendMessage(int i, HashMap<String, Object> hashMap) {
            Handler handler = this.mMsgHandler;
            if (handler != null) {
                return this.mMsgHandler.sendMessage(Message.obtain(handler, i, hashMap)) ? 0 : -1;
            }
            StringBuilder sb = new StringBuilder(" fail mMsgHandler==null _enabled:");
            sb.append(this._enabled ? "Y" : "N");
            sb.append(" activeMode:");
            sb.append(TraeAudioManager.this._activeMode);
            sb.append(" msg:");
            sb.append(i);
            AudioDeviceInterface.LogTraceEntry(sb.toString());
            return -1;
        }

        void startService(HashMap<String, Object> hashMap) {
            String str = (String) hashMap.get(TraeAudioManager.EXTRA_DATA_DEVICECONFIG);
            Log.w(TraeAudioManager.TAG, "startService cfg:" + str);
            StringBuilder sb = new StringBuilder(" _enabled:");
            sb.append(this._enabled ? "Y" : "N");
            sb.append(" activeMode:");
            sb.append(TraeAudioManager.this._activeMode);
            sb.append(" cfg:");
            sb.append(str);
            AudioDeviceInterface.LogTraceEntry(sb.toString());
            if (TraeAudioManager.this._context == null) {
                return;
            }
            if (str.equals("resume service")) {
                str = this._lastCfg;
            }
            QLog.m606w(TraeAudioManager.TAG, "   startService:" + str);
            if (!(this._enabled && this._lastCfg.equals(str)) && TraeAudioManager.this._activeMode == 0) {
                if (this._enabled) {
                    stopService();
                }
                _prev_startService();
                TraeAudioManager.this._deviceConfigManager.clearConfig();
                TraeAudioManager.this._deviceConfigManager.init(str);
                this._lastCfg = str;
                if (TraeAudioManager.this._am != null) {
                    this._preServiceMode = TraeAudioManager.this._am.getMode();
                }
                this._enabled = true;
                if (this._ringPlayer == null) {
                    this._ringPlayer = new TraeMediaPlayer(TraeAudioManager.this._context, new TraeMediaPlayer.OnCompletionListener() { // from class: com.tencent.sharpgme.jni.TraeAudioManager.TraeAudioManagerLooper.1
                        @Override // com.tencent.sharpgme.jni.TraeMediaPlayer.OnCompletionListener
                        public void onCompletion() {
                            QLog.m606w(TraeAudioManager.TAG, "_ringPlayer onCompletion _activeMode:" + TraeAudioManager.this._activeMode + " _preRingMode:" + TraeAudioManagerLooper.this._preRingMode);
                            HashMap<String, Object> hashMap2 = new HashMap<>();
                            hashMap2.put(TraeAudioManager.PARAM_ISHOSTSIDE, true);
                            TraeAudioManagerLooper.this.sendMessage(TraeAudioManagerLooper.MESSAGE_STOPRING, hashMap2);
                            TraeAudioManagerLooper.this.notifyRingCompletion();
                        }
                    });
                }
                notifyServiceState(this._enabled);
                TraeAudioManager.this.updateDeviceStatus();
                AudioDeviceInterface.LogTraceExit();
            }
        }

        boolean isNeedForceVolumeType() {
            if (Build.MANUFACTURER.equals("Xiaomi")) {
                return Build.MODEL.equals("MI 5") || Build.MODEL.equals("MI 5s") || Build.MODEL.equals("MI 5s Plus");
            }
            return false;
        }

        void stopService() {
            StringBuilder sb = new StringBuilder(" _enabled:");
            sb.append(this._enabled ? "Y" : "N");
            sb.append(" activeMode:");
            sb.append(TraeAudioManager.this._activeMode);
            AudioDeviceInterface.LogTraceEntry(sb.toString());
            if (this._enabled) {
                if (TraeAudioManager.this._activeMode == 1) {
                    interruptVoicecallPostprocess();
                } else if (TraeAudioManager.this._activeMode == 2) {
                    interruptRing();
                }
                if (TraeAudioManager.this._switchThread != null) {
                    QLog.m606w(TraeAudioManager.TAG, "_switchThread:" + TraeAudioManager.this._switchThread.getDeviceName());
                    TraeAudioManager.this._switchThread.quit();
                    TraeAudioManager.this._switchThread = null;
                }
                TraeMediaPlayer traeMediaPlayer = this._ringPlayer;
                if (traeMediaPlayer != null) {
                    traeMediaPlayer.stopRing();
                }
                this._ringPlayer = null;
                this._enabled = false;
                notifyServiceState(false);
                if (TraeAudioManager.this._am != null && TraeAudioManager.this._context != null) {
                    try {
                        TraeAudioManager.this.InternalSetMode(0);
                        QLog.m606w(TraeAudioManager.TAG, "forceVolumeControlStream: -1");
                        TraeAudioManager.forceVolumeControlStream(TraeAudioManager.this._am, -1);
                    } catch (Exception unused) {
                    }
                }
                _post_stopService();
                AudioDeviceInterface.LogTraceExit();
            }
        }

        int notifyServiceState(boolean z) {
            if (TraeAudioManager.this._context == null) {
                return -1;
            }
            Intent intent = new Intent();
            intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY);
            intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_SERVICE_STATE);
            intent.putExtra(TraeAudioManager.NOTIFY_SERVICE_STATE_DATE, z);
            if (TraeAudioManager.this._context == null) {
                return 0;
            }
            TraeAudioManager.this._context.sendBroadcast(intent);
            return 0;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            AudioDeviceInterface.LogTraceEntry("");
            Looper.prepare();
            this.mMsgHandler = new Handler() { // from class: com.tencent.sharpgme.jni.TraeAudioManager.TraeAudioManagerLooper.2
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    HashMap<String, Object> hashMap;
                    try {
                        hashMap = (HashMap) message.obj;
                    } catch (Exception unused) {
                        hashMap = null;
                    }
                    StringBuilder sb = new StringBuilder("TraeAudioManagerLooper msg:");
                    sb.append(message.what);
                    sb.append(CertificateUtil.DELIMITER);
                    sb.append(TraeAudioManagerLooper.this.msgToText(message.what));
                    sb.append(" _enabled:");
                    sb.append(TraeAudioManagerLooper.this._enabled ? "Y" : "N");
                    QLog.m606w(TraeAudioManager.TAG, sb.toString());
                    if (message.what == 32772) {
                        TraeAudioManagerLooper.this.startService(hashMap);
                        return;
                    }
                    if (!TraeAudioManagerLooper.this._enabled) {
                        QLog.m606w(TraeAudioManager.TAG, "******* disabled ,skip msg******");
                        TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 1);
                        return;
                    }
                    switch (message.what) {
                        case 32773:
                            TraeAudioManagerLooper.this.stopService();
                            return;
                        case 32774:
                            TraeAudioManagerLooper.this.InternalSessionGetDeviceList(hashMap);
                            return;
                        case 32775:
                            TraeAudioManager.this.InternalSessionConnectDevice(hashMap, false);
                            return;
                        case 32776:
                            TraeAudioManager.this.InternalSessionEarAction(hashMap);
                            return;
                        case 32777:
                            TraeAudioManager.this.InternalSessionIsDeviceChangabled(hashMap);
                            return;
                        case TraeAudioManagerLooper.MESSAGE_GETCONNECTEDDEVICE /* 32778 */:
                            TraeAudioManager.this.InternalSessionGetConnectedDevice(hashMap);
                            return;
                        case TraeAudioManagerLooper.MESSAGE_GETCONNECTINGDEVICE /* 32779 */:
                            TraeAudioManager.this.InternalSessionGetConnectingDevice(hashMap);
                            return;
                        case TraeAudioManagerLooper.MESSAGE_VOICECALLPREPROCESS /* 32780 */:
                            TraeAudioManagerLooper.this.InternalVoicecallPreprocess(hashMap);
                            return;
                        case TraeAudioManagerLooper.MESSAGE_VOICECALLPOSTPROCESS /* 32781 */:
                            TraeAudioManagerLooper.this.InternalVoicecallPostprocess(hashMap);
                            return;
                        case TraeAudioManagerLooper.MESSAGE_STARTRING /* 32782 */:
                            TraeAudioManagerLooper.this.InternalStartRing(hashMap);
                            return;
                        case TraeAudioManagerLooper.MESSAGE_STOPRING /* 32783 */:
                            TraeAudioManagerLooper.this.InternalStopRing(hashMap);
                            return;
                        case 32784:
                            TraeAudioManagerLooper.this.InternalGetStreamType(hashMap);
                            return;
                        case 32785:
                        case TraeAudioManagerLooper.MESSAGE_CONNECT_HIGHEST_PRIORITY_DEVICE /* 32789 */:
                            String availabledHighestPriorityDevice = TraeAudioManager.this._deviceConfigManager.getAvailabledHighestPriorityDevice();
                            String connectedDevice = TraeAudioManager.this._deviceConfigManager.getConnectedDevice();
                            QLog.m606w(TraeAudioManager.TAG, "MESSAGE_AUTO_DEVICELIST_UPDATE  connectedDev:" + connectedDevice + " highestDev" + availabledHighestPriorityDevice);
                            if (TraeAudioManager.IsUpdateSceneFlag && TraeAudioManagerLooper.this.IsFirstConnectDeviceFlag) {
                                TraeAudioManagerLooper.this.IsFirstConnectDeviceFlag = false;
                                if (TraeAudioManager.IsMusicScene && !TraeAudioManager.this.IsBluetoothA2dpExisted) {
                                    TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this._deviceConfigManager.getAvailabledHighestPriorityDevice(TraeAudioManager.DEVICE_BLUETOOTHHEADSET), null, true);
                                    return;
                                } else {
                                    TraeAudioManager.this.InternalConnectDevice(availabledHighestPriorityDevice, null, true);
                                    return;
                                }
                            }
                            if (!availabledHighestPriorityDevice.equals(connectedDevice)) {
                                TraeAudioManager.this.InternalConnectDevice(availabledHighestPriorityDevice, null, false);
                                return;
                            } else {
                                TraeAudioManager.this.InternalNotifyDeviceListUpdate();
                                return;
                            }
                        case 32786:
                            String str = (String) hashMap.get(TraeAudioManager.PARAM_DEVICE);
                            if (TraeAudioManager.this.InternalConnectDevice(str, null, false) != 0) {
                                QLog.m606w(TraeAudioManager.TAG, " plugin dev:" + str + " sessionConnectedDev:" + TraeAudioManager.this.sessionConnectedDev + " connected fail,auto switch!");
                                TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this._deviceConfigManager.getAvailabledHighestPriorityDevice(), null, false);
                                return;
                            }
                            return;
                        case TraeAudioManagerLooper.MESSAGE_AUTO_DEVICELIST_PLUGOUT_UPDATE /* 32787 */:
                            if (TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this.sessionConnectedDev, null, false) != 0) {
                                QLog.m606w(TraeAudioManager.TAG, " plugout dev:" + ((String) hashMap.get(TraeAudioManager.PARAM_DEVICE)) + " sessionConnectedDev:" + TraeAudioManager.this.sessionConnectedDev + " connected fail,auto switch!");
                                TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this._deviceConfigManager.getAvailabledHighestPriorityDevice(), null, false);
                                return;
                            }
                            return;
                        case TraeAudioManagerLooper.MESSAGE_VOICECALL_AUIDOPARAM_CHANGED /* 32788 */:
                            Integer num = (Integer) hashMap.get(TraeAudioManager.PARAM_STREAMTYPE);
                            if (num == null) {
                                QLog.m600e(TraeAudioManager.TAG, " MESSAGE_VOICECALL_AUIDOPARAM_CHANGED params.get(PARAM_STREAMTYPE)==null!!");
                                return;
                            } else {
                                TraeAudioManager.this._streamType = num.intValue();
                                TraeAudioManagerLooper.this.InternalNotifyStreamTypeUpdate(num.intValue());
                                return;
                            }
                        case TraeAudioManagerLooper.MESSAGE_REQUEST_RELEASE_AUDIO_FOCUS /* 32790 */:
                            TraeAudioManagerLooper.this.abandonAudioFocus();
                            return;
                        case TraeAudioManagerLooper.MESSAGE_RECOVER_AUDIO_FOCUS /* 32791 */:
                            TraeAudioManagerLooper traeAudioManagerLooper = TraeAudioManagerLooper.this;
                            traeAudioManagerLooper.requestAudioFocus(TraeAudioManager.this._streamType);
                            return;
                        case TraeAudioManagerLooper.MESSAGE_FORCE_CONNECTDEVICE /* 32792 */:
                            TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this._deviceConfigManager.getAvailabledHighestPriorityDevice(), null, true);
                            return;
                        default:
                            return;
                    }
                }
            };
            _init();
            synchronized (this._started) {
                boolean[] zArr = this._started;
                zArr[0] = true;
                zArr.notifyAll();
            }
            Looper.loop();
            _uninit();
            synchronized (this._started) {
                boolean[] zArr2 = this._started;
                zArr2[0] = false;
                zArr2.notifyAll();
            }
            AudioDeviceInterface.LogTraceExit();
        }

        void _init() {
            AudioDeviceInterface.LogTraceEntry("");
            try {
                TraeAudioManager.this._audioSessionHost = new TraeAudioSessionHost();
                TraeAudioManager traeAudioManager = TraeAudioManager.this;
                traeAudioManager._deviceConfigManager = new DeviceConfigManager();
                TraeAudioManager._gHostProcessId = Process.myPid();
                TraeAudioManager traeAudioManager2 = TraeAudioManager.this;
                traeAudioManager2._am = (AudioManager) traeAudioManager2._context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
                TraeAudioManager traeAudioManager3 = TraeAudioManager.this;
                traeAudioManager3._bluetoothCheck = traeAudioManager3.CreateBluetoothCheck(traeAudioManager3._context, TraeAudioManager.this._deviceConfigManager);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.HEADSET_PLUG");
                intentFilter.addAction("android.media.AUDIO_BECOMING_NOISY");
                TraeAudioManager.this._bluetoothCheck.addAction(intentFilter);
                intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
                TraeAudioManager.this._context.registerReceiver(this._parent, intentFilter);
            } catch (Exception unused) {
                QLog.m606w(TraeAudioManager.TAG, "======7");
            }
            AudioDeviceInterface.LogTraceExit();
        }

        void _prev_startService() {
            try {
                TraeAudioManager traeAudioManager = TraeAudioManager.this;
                traeAudioManager._am = (AudioManager) traeAudioManager._context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
                if (TraeAudioManager.this._bluetoothCheck == null) {
                    TraeAudioManager traeAudioManager2 = TraeAudioManager.this;
                    traeAudioManager2._bluetoothCheck = traeAudioManager2.CreateBluetoothCheck(traeAudioManager2._context, TraeAudioManager.this._deviceConfigManager);
                }
                TraeAudioManager.this._context.unregisterReceiver(this._parent);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.HEADSET_PLUG");
                intentFilter.addAction("android.media.AUDIO_BECOMING_NOISY");
                TraeAudioManager.this._bluetoothCheck.addAction(intentFilter);
                intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
                TraeAudioManager.this._context.registerReceiver(this._parent, intentFilter);
            } catch (Exception unused) {
            }
        }

        void _post_stopService() {
            try {
                if (TraeAudioManager.this._bluetoothCheck != null) {
                    TraeAudioManager.this._bluetoothCheck.release();
                }
                TraeAudioManager.this._bluetoothCheck = null;
                if (TraeAudioManager.this._context != null) {
                    TraeAudioManager.this._context.unregisterReceiver(this._parent);
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
                    TraeAudioManager.this._context.registerReceiver(this._parent, intentFilter);
                }
            } catch (Exception unused) {
            }
        }

        void _uninit() {
            AudioDeviceInterface.LogTraceEntry("");
            try {
                stopService();
                if (TraeAudioManager.this._bluetoothCheck != null) {
                    TraeAudioManager.this._bluetoothCheck.release();
                }
                TraeAudioManager.this._bluetoothCheck = null;
                if (TraeAudioManager.this._context != null) {
                    TraeAudioManager.this._context.unregisterReceiver(this._parent);
                    TraeAudioManager.this._context = null;
                }
                if (TraeAudioManager.this._deviceConfigManager != null) {
                    TraeAudioManager.this._deviceConfigManager.clearConfig();
                }
                TraeAudioManager.this._deviceConfigManager = null;
            } catch (Exception unused) {
            }
            AudioDeviceInterface.LogTraceExit();
        }

        int InternalSessionGetDeviceList(HashMap<String, Object> hashMap) {
            Intent intent = new Intent();
            HashMap<String, Object> snapParams = TraeAudioManager.this._deviceConfigManager.getSnapParams();
            ArrayList arrayList = (ArrayList) snapParams.get(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST);
            String str = (String) snapParams.get(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE);
            String str2 = (String) snapParams.get(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE);
            intent.putExtra(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST, (String[]) arrayList.toArray(new String[0]));
            intent.putExtra(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE, str);
            intent.putExtra(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE, str2);
            intent.putExtra(TraeAudioManager.EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME, TraeAudioManager.this._deviceConfigManager.getBluetoothName());
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
            return 0;
        }

        void requestAudioFocus(int i) {
            if (this.mAudioFocusChangeListener == null) {
                this.mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.tencent.sharpgme.jni.TraeAudioManager.TraeAudioManagerLooper.3
                    @Override // android.media.AudioManager.OnAudioFocusChangeListener
                    public void onAudioFocusChange(int i2) {
                        QLog.m606w(TraeAudioManager.TAG, "focusChange:" + i2 + " _focusSteamType:" + TraeAudioManagerLooper.this._focusSteamType + " currMode:" + TraeAudioManager.this._am.getMode() + " _activeMode:" + TraeAudioManager.this._activeMode);
                    }
                };
                if (TraeAudioManager.this._am != null) {
                    try {
                        int requestAudioFocus = TraeAudioManager.this._am.requestAudioFocus(this.mAudioFocusChangeListener, i, 1);
                        if (requestAudioFocus != 1) {
                            QLog.m600e(TraeAudioManager.TAG, "request audio focus fail. " + requestAudioFocus + " mode:" + TraeAudioManager.this._am.getMode());
                        }
                        this._focusSteamType = i;
                        QLog.m606w(TraeAudioManager.TAG, "-------requestAudioFocus _focusSteamType:" + this._focusSteamType);
                    } catch (SecurityException e) {
                        QLog.m600e(TraeAudioManager.TAG, "request audio focus exception. " + e);
                    }
                }
            }
        }

        void abandonAudioFocus() {
            if (TraeAudioManager.this._am == null || this.mAudioFocusChangeListener == null) {
                return;
            }
            QLog.m606w(TraeAudioManager.TAG, "-------abandonAudioFocus _focusSteamType:" + this._focusSteamType);
            TraeAudioManager.this._am.abandonAudioFocus(this.mAudioFocusChangeListener);
            this.mAudioFocusChangeListener = null;
        }

        int InternalVoicecallPreprocess(HashMap<String, Object> hashMap) {
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode);
            if (hashMap == null) {
                return -1;
            }
            if (TraeAudioManager.this._am == null) {
                QLog.m600e(TraeAudioManager.TAG, " InternalVoicecallPreprocess am==null!!");
                return -1;
            }
            if (TraeAudioManager.this._activeMode == 1) {
                TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 2);
                return -1;
            }
            this._voiceCallSessionID = ((Long) hashMap.get(TraeAudioManager.PARAM_SESSIONID)).longValue();
            this._voiceCallOperation = (String) hashMap.get(TraeAudioManager.PARAM_OPERATION);
            TraeAudioManager.this._activeMode = 1;
            TraeAudioManager traeAudioManager = TraeAudioManager.this;
            traeAudioManager._prevMode = traeAudioManager._am.getMode();
            Integer.valueOf(-1);
            Integer.valueOf(0);
            Integer num = (Integer) hashMap.get(TraeAudioManager.PARAM_MODEPOLICY);
            if (num == null) {
                QLog.m600e(TraeAudioManager.TAG, " params.get(PARAM_MODEPOLICY)==null!!");
                TraeAudioManager.this._modePolicy = -1;
            } else {
                TraeAudioManager.this._modePolicy = num.intValue();
            }
            QLog.m600e(TraeAudioManager.TAG, "  _modePolicy:" + TraeAudioManager.this._modePolicy);
            Integer num2 = (Integer) hashMap.get(TraeAudioManager.PARAM_STREAMTYPE);
            if (num2 == null) {
                QLog.m600e(TraeAudioManager.TAG, " params.get(PARAM_STREAMTYPE)==null!!");
                TraeAudioManager.this._streamType = 0;
            } else {
                TraeAudioManager.this._streamType = num2.intValue();
            }
            if ((TraeAudioManager.nSceneMode == 6 || TraeAudioManager.nSceneMode == 8) && TraeAudioManager.nSpecialModeBypass3A == 1 && TraeAudioManager.nSpeakerStreamType == 1) {
                QLog.m606w(TraeAudioManager.TAG, "VOIP select different mode,bypass 3A");
                if (TraeAudioManager.this._deviceConfigManager.getConnectedDevice().equals(TraeAudioManager.DEVICE_WIREDHEADSET)) {
                    TraeAudioManager.this.InternalSetMode(0);
                } else if (TraeAudioManager.isCloseSystemAPM(TraeAudioManager.this._modePolicy) && TraeAudioManager.this._activeMode != 2 && TraeAudioManager.this._deviceConfigManager != null) {
                    if (TraeAudioManager.this._deviceConfigManager.getConnectedDevice().equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
                        TraeAudioManager.this.InternalSetMode(0);
                    } else {
                        TraeAudioManager.this.InternalSetMode(3);
                    }
                } else {
                    TraeAudioManager traeAudioManager2 = TraeAudioManager.this;
                    traeAudioManager2.InternalSetMode(TraeAudioManager.getCallAudioMode(traeAudioManager2._modePolicy));
                }
            } else if (TraeAudioManager.isCloseSystemAPM(TraeAudioManager.this._modePolicy) && TraeAudioManager.this._activeMode != 2 && TraeAudioManager.this._deviceConfigManager != null) {
                if (TraeAudioManager.this._deviceConfigManager.getConnectedDevice().equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
                    TraeAudioManager.this.InternalSetMode(0);
                } else {
                    TraeAudioManager.this.InternalSetMode(3);
                }
            } else {
                TraeAudioManager traeAudioManager3 = TraeAudioManager.this;
                traeAudioManager3.InternalSetMode(TraeAudioManager.getCallAudioMode(traeAudioManager3._modePolicy));
            }
            TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 0);
            AudioDeviceInterface.LogTraceExit();
            return 0;
        }

        int InternalVoicecallPostprocess(HashMap<String, Object> hashMap) {
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode);
            if (TraeAudioManager.this._am == null) {
                QLog.m600e(TraeAudioManager.TAG, " InternalVoicecallPostprocess am==null!!");
                return -1;
            }
            if (TraeAudioManager.this._activeMode != 1) {
                QLog.m600e(TraeAudioManager.TAG, " not ACTIVE_VOICECALL!!");
                TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 3);
                return -1;
            }
            TraeAudioManager.this._activeMode = 0;
            if (TraeAudioManager.this._switchThread != null) {
                QLog.m606w(TraeAudioManager.TAG, "_switchThread:" + TraeAudioManager.this._switchThread.getDeviceName());
                TraeAudioManager.this._switchThread.quit();
                TraeAudioManager.this._switchThread = null;
            }
            abandonAudioFocus();
            AudioDeviceInterface.LogTraceExit();
            return 0;
        }

        int interruptVoicecallPostprocess() {
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode);
            if (TraeAudioManager.this._am == null) {
                QLog.m600e(TraeAudioManager.TAG, " am==null!!");
                return -1;
            }
            if (TraeAudioManager.this._activeMode != 1) {
                QLog.m600e(TraeAudioManager.TAG, " not ACTIVE_RING!!");
                return -1;
            }
            TraeAudioManager.this._activeMode = 0;
            if (TraeAudioManager.this._prevMode != -1) {
                TraeAudioManager traeAudioManager = TraeAudioManager.this;
                traeAudioManager.InternalSetMode(traeAudioManager._prevMode);
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(TraeAudioManager.PARAM_SESSIONID, Long.valueOf(this._voiceCallSessionID));
            hashMap.put(TraeAudioManager.PARAM_OPERATION, this._voiceCallOperation);
            TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 6);
            AudioDeviceInterface.LogTraceExit();
            return 0;
        }

        int InternalStartRing(HashMap<String, Object> hashMap) {
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode);
            if (TraeAudioManager.this._am == null) {
                QLog.m600e(TraeAudioManager.TAG, " InternalStartRing am==null!!");
                return -1;
            }
            if (TraeAudioManager.this._activeMode == 2) {
                interruptRing();
            }
            try {
                this._ringSessionID = ((Long) hashMap.get(TraeAudioManager.PARAM_SESSIONID)).longValue();
                this._ringOperation = (String) hashMap.get(TraeAudioManager.PARAM_OPERATION);
                this._ringUserdata = (String) hashMap.get(TraeAudioManager.PARAM_RING_USERDATA_STRING);
                int intValue = ((Integer) hashMap.get(TraeAudioManager.PARAM_RING_DATASOURCE)).intValue();
                QLog.m606w(TraeAudioManager.TAG, "  dataSource:" + intValue);
                int intValue2 = ((Integer) hashMap.get(TraeAudioManager.PARAM_RING_RSID)).intValue();
                Uri uri = (Uri) hashMap.get(TraeAudioManager.PARAM_RING_URI);
                String str = (String) hashMap.get(TraeAudioManager.PARAM_RING_FILEPATH);
                boolean booleanValue = ((Boolean) hashMap.get(TraeAudioManager.PARAM_RING_LOOP)).booleanValue();
                int intValue3 = ((Integer) hashMap.get(TraeAudioManager.PARAM_RING_LOOPCOUNT)).intValue();
                boolean booleanValue2 = ((Boolean) hashMap.get(TraeAudioManager.PARAM_RING_MODE)).booleanValue();
                if (TraeAudioManager.this._activeMode != 1) {
                    TraeAudioManager.this._activeMode = 2;
                }
                Intent intent = new Intent();
                intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, this._ringUserdata);
                TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
                this._preRingMode = TraeAudioManager.this._am.getMode();
                this._ringPlayer.playRing(intValue, intValue2, uri, str, booleanValue, intValue3, booleanValue2, TraeAudioManager.this._activeMode == 1, TraeAudioManager.this._streamType);
                QLog.m606w(TraeAudioManager.TAG, " _ringUserdata:" + this._ringUserdata + " DurationMS:" + this._ringPlayer.getDuration());
                if (!this._ringPlayer.hasCall()) {
                    requestAudioFocus(this._ringPlayer.getStreamType());
                }
                InternalNotifyStreamTypeUpdate(this._ringPlayer.getStreamType());
                AudioDeviceInterface.LogTraceExit();
                return 0;
            } catch (Exception unused) {
                QLog.m600e(TraeAudioManager.TAG, " startRing err params");
                return -1;
            }
        }

        int InternalStopRing(HashMap<String, Object> hashMap) {
            TraeMediaPlayer traeMediaPlayer;
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode + " _preRingMode:" + this._preRingMode);
            if (TraeAudioManager.this._am == null || (traeMediaPlayer = this._ringPlayer) == null) {
                QLog.m600e(TraeAudioManager.TAG, " InternalStopRing am==null!!");
                return -1;
            }
            traeMediaPlayer.stopRing();
            if (!this._ringPlayer.hasCall() && TraeAudioManager.this._activeMode == 2) {
                abandonAudioFocus();
                TraeAudioManager.this._activeMode = 0;
            }
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, this._ringUserdata);
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
            AudioDeviceInterface.LogTraceExit();
            return 0;
        }

        int InternalGetStreamType(HashMap<String, Object> hashMap) {
            int i;
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode + " _preRingMode:" + this._preRingMode);
            if (TraeAudioManager.this._am == null) {
                QLog.m600e(TraeAudioManager.TAG, " InternalStopRing am==null!!");
                return -1;
            }
            if (TraeAudioManager.this._activeMode == 2) {
                i = this._ringPlayer.getStreamType();
            } else {
                i = TraeAudioManager.this._streamType;
            }
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.EXTRA_DATA_STREAMTYPE, i);
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
            AudioDeviceInterface.LogTraceExit();
            return 0;
        }

        int InternalNotifyStreamTypeUpdate(final int i) {
            if (TraeAudioManager.this._context == null) {
                return -1;
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.sharpgme.jni.TraeAudioManager.TraeAudioManagerLooper.4
                @Override // java.lang.Runnable
                public void run() {
                    Intent intent = new Intent();
                    intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY);
                    intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_STREAMTYPE_UPDATE);
                    intent.putExtra(TraeAudioManager.EXTRA_DATA_STREAMTYPE, i);
                    if (TraeAudioManager.this._context != null) {
                        TraeAudioManager.this._context.sendBroadcast(intent);
                    }
                }
            });
            return 0;
        }

        int interruptRing() {
            AudioDeviceInterface.LogTraceEntry(" activeMode:" + TraeAudioManager.this._activeMode + " _preRingMode:" + this._preRingMode);
            if (TraeAudioManager.this._am == null) {
                QLog.m600e(TraeAudioManager.TAG, " interruptRing am==null!!");
                return -1;
            }
            if (TraeAudioManager.this._activeMode != 2) {
                QLog.m600e(TraeAudioManager.TAG, " not ACTIVE_RING!!");
                return -1;
            }
            this._ringPlayer.stopRing();
            abandonAudioFocus();
            TraeAudioManager.this._activeMode = 0;
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(TraeAudioManager.PARAM_SESSIONID, Long.valueOf(this._ringSessionID));
            hashMap.put(TraeAudioManager.PARAM_OPERATION, this._ringOperation);
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, this._ringUserdata);
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 4);
            AudioDeviceInterface.LogTraceExit();
            return 0;
        }

        void notifyRingCompletion() {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(TraeAudioManager.PARAM_SESSIONID, Long.valueOf(this._ringSessionID));
            hashMap.put(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_RING_COMPLETION);
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, this._ringUserdata);
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
        }
    }

    public static int OpenSpeaker(boolean z) {
        int i;
        _glock.lock();
        TraeAudioManager traeAudioManager = _ginstance;
        if (traeAudioManager != null) {
            if (z != traeAudioManager._speakerOn) {
                traeAudioManager._speakerOn = z;
                i = traeAudioManager.InternalSetSpeaker(traeAudioManager._context, z);
            } else {
                i = 0;
            }
        } else {
            QLog.m606w(TAG, "TraeAudioManager|static SetSpeakerForTest|null == _ginstance");
            i = -1;
        }
        _glock.unlock();
        return i;
    }

    public static int SetSpeakerForTest(Context context, boolean z) {
        int i;
        _glock.lock();
        TraeAudioManager traeAudioManager = _ginstance;
        if (traeAudioManager != null) {
            i = traeAudioManager.InternalSetSpeaker(context, z);
        } else {
            QLog.m606w(TAG, "TraeAudioManager|static SetSpeakerForTest|null == _ginstance");
            i = -1;
        }
        _glock.unlock();
        return i;
    }

    int InternalSetSpeaker(Context context, boolean z) {
        QLog.m602i(TAG, "InternalSetSpeaker entry. speakerOn:" + z);
        if (context == null) {
            QLog.m600e(TAG, "Could not InternalSetSpeaker - no context");
            return -1;
        }
        AudioManager audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        if (audioManager == null) {
            QLog.m600e(TAG, "Could not InternalSetSpeaker - no audio manager");
            return -1;
        }
        boolean isSpeakerphoneOn = audioManager.isSpeakerphoneOn();
        if (isSpeakerphoneOn != z) {
            audioManager.setSpeakerphoneOn(z);
        }
        int i = audioManager.isSpeakerphoneOn() == z ? 0 : -1;
        QLog.m602i(TAG, String.format("InternalSetSpeaker exit:Speaker:%s->%s, mode=%s", Boolean.valueOf(isSpeakerphoneOn), Boolean.valueOf(audioManager.isSpeakerphoneOn()), Integer.valueOf(audioManager.getMode())));
        return i;
    }

    void InternalSetMode(int i) {
        QLog.m606w(TAG, "SetMode entry:" + i);
        AudioManager audioManager = this._am;
        if (audioManager == null) {
            QLog.m606w(TAG, "setMode:" + i + " fail am=null");
            return;
        }
        try {
            audioManager.setMode(i);
        } catch (Exception unused) {
            QLog.m600e(TAG, "setMode:" + i + " exception");
        }
        StringBuilder sb = new StringBuilder("setMode:");
        sb.append(i);
        sb.append(this._am.getMode() != i ? "fail" : "success");
        QLog.m606w(TAG, sb.toString());
    }

    public static int registerAudioSession(TraeAudioSession traeAudioSession, boolean z, long j, Context context) {
        int i;
        _glock.lock();
        TraeAudioManager traeAudioManager = _ginstance;
        if (traeAudioManager != null) {
            if (z) {
                traeAudioManager._audioSessionHost.add(traeAudioSession, j, context);
            } else {
                traeAudioManager._audioSessionHost.remove(j);
            }
            i = 0;
        } else {
            i = -1;
        }
        _glock.unlock();
        return i;
    }

    public static int sendMessage(int i, HashMap<String, Object> hashMap) {
        _glock.lock();
        TraeAudioManager traeAudioManager = _ginstance;
        int internalSendMessage = traeAudioManager != null ? traeAudioManager.internalSendMessage(i, hashMap) : -1;
        _glock.unlock();
        return internalSendMessage;
    }

    public static int init(Context context) {
        _glock.lock();
        if (_ginstance == null) {
            Log.w(TAG, "TraeAudioManager first init _ginstance:" + _ginstance);
        }
        if (_ginstance == null) {
            _ginstance = new TraeAudioManager(context);
        }
        _glock.unlock();
        return 0;
    }

    public static void uninit() {
        _glock.lock();
        Log.w(TAG, "TraeAudioManager uninit _ginstance:" + _ginstance);
        TraeAudioManager traeAudioManager = _ginstance;
        if (traeAudioManager != null) {
            traeAudioManager.release();
            _ginstance = null;
        }
        _glock.unlock();
    }

    TraeAudioManager(Context context) {
        this._context = null;
        this.mTraeAudioManagerLooper = null;
        AudioDeviceInterface.LogTraceEntry(" context:" + context);
        if (context == null) {
            return;
        }
        this._context = context;
        this.mTraeAudioManagerLooper = new TraeAudioManagerLooper(this);
        AudioDeviceInterface.LogTraceExit();
    }

    public void release() {
        AudioDeviceInterface.LogTraceEntry("");
        TraeAudioManagerLooper traeAudioManagerLooper = this.mTraeAudioManagerLooper;
        if (traeAudioManagerLooper != null) {
            traeAudioManagerLooper.quit();
            this.mTraeAudioManagerLooper = null;
        }
        AudioDeviceInterface.LogTraceExit();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || context == null) {
            QLog.m598d(TAG, "onReceive intent or context is null!");
            return;
        }
        try {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra(PARAM_OPERATION);
            QLog.m606w(TAG, "TraeAudioManager|onReceive::Action:" + intent.getAction());
            DeviceConfigManager deviceConfigManager = this._deviceConfigManager;
            if (deviceConfigManager == null) {
                QLog.m598d(TAG, "_deviceConfigManager null!");
                return;
            }
            boolean visible = deviceConfigManager.getVisible(DEVICE_WIREDHEADSET);
            boolean visible2 = this._deviceConfigManager.getVisible(DEVICE_BLUETOOTHHEADSET);
            if ("android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
                onHeadsetPlug(context, intent);
                if (!visible && this._deviceConfigManager.getVisible(DEVICE_WIREDHEADSET)) {
                    checkDevicePlug(DEVICE_WIREDHEADSET, true);
                }
                if (!visible || this._deviceConfigManager.getVisible(DEVICE_WIREDHEADSET)) {
                    return;
                }
                checkDevicePlug(DEVICE_WIREDHEADSET, false);
                return;
            }
            if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                return;
            }
            if (ACTION_TRAEAUDIOMANAGER_REQUEST.equals(action)) {
                QLog.m606w(TAG, "   OPERATION:" + stringExtra);
                if (OPERATION_STARTSERVICE.equals(stringExtra)) {
                    startService(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent.getStringExtra(EXTRA_DATA_DEVICECONFIG));
                    return;
                }
                if (OPERATION_STOPSERVICE.equals(stringExtra)) {
                    stopService(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    return;
                }
                if (OPERATION_GETDEVICELIST.equals(stringExtra)) {
                    getDeviceList(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    return;
                }
                if (OPERATION_GETSTREAMTYPE.equals(stringExtra)) {
                    getStreamType(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    return;
                }
                if (OPERATION_CONNECTDEVICE.equals(stringExtra)) {
                    connectDevice(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent.getStringExtra(CONNECTDEVICE_DEVICENAME));
                    return;
                }
                if (OPERATION_FORCE_CONNECTDEVICE.equals(stringExtra)) {
                    forceConnectDevice(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent.getStringExtra(CONNECTDEVICE_DEVICENAME));
                    return;
                }
                if (OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE.equals(stringExtra)) {
                    connectHighestPriorityDevice(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    return;
                }
                if (OPERATION_EARACTION.equals(stringExtra)) {
                    earAction(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent.getIntExtra(EXTRA_EARACTION, -1));
                    return;
                }
                if (OPERATION_ISDEVICECHANGABLED.equals(stringExtra)) {
                    isDeviceChangabled(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    return;
                }
                if (OPERATION_GETCONNECTEDDEVICE.equals(stringExtra)) {
                    getConnectedDevice(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    return;
                }
                if (OPERATION_GETCONNECTINGDEVICE.equals(stringExtra)) {
                    getConnectingDevice(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    return;
                }
                if (OPERATION_VOICECALL_PREPROCESS.equals(stringExtra)) {
                    voicecallPreprocess(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent.getIntExtra(PARAM_MODEPOLICY, -1), intent.getIntExtra(PARAM_STREAMTYPE, -1));
                    return;
                }
                if (OPERATION_VOICECALL_POSTPROCESS.equals(stringExtra)) {
                    voicecallPostprocess(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    return;
                }
                if (OPERATION_VOICECALL_AUDIOPARAM_CHANGED.equals(stringExtra)) {
                    voiceCallAudioParamChanged(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent.getIntExtra(PARAM_MODEPOLICY, -1), intent.getIntExtra(PARAM_STREAMTYPE, -1));
                    return;
                } else {
                    if (OPERATION_STARTRING.equals(stringExtra)) {
                        startRing(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent.getIntExtra(PARAM_RING_DATASOURCE, -1), intent.getIntExtra(PARAM_RING_RSID, -1), (Uri) intent.getParcelableExtra(PARAM_RING_URI), intent.getStringExtra(PARAM_RING_FILEPATH), intent.getBooleanExtra(PARAM_RING_LOOP, false), intent.getIntExtra(PARAM_RING_LOOPCOUNT, 1), intent.getStringExtra(PARAM_RING_USERDATA_STRING), intent.getBooleanExtra(PARAM_RING_MODE, false));
                        return;
                    }
                    if (OPERATION_STOPRING.equals(stringExtra)) {
                        stopRing(stringExtra, intent.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                        return;
                    }
                    return;
                }
            }
            DeviceConfigManager deviceConfigManager2 = this._deviceConfigManager;
            if (deviceConfigManager2 != null) {
                BluetoohHeadsetCheckInterface bluetoohHeadsetCheckInterface = this._bluetoothCheck;
                if (bluetoohHeadsetCheckInterface != null) {
                    bluetoohHeadsetCheckInterface.onReceive(context, intent, deviceConfigManager2);
                }
                if (!visible2 && this._deviceConfigManager.getVisible(DEVICE_BLUETOOTHHEADSET)) {
                    checkDevicePlug(DEVICE_BLUETOOTHHEADSET, true);
                }
                if (!visible2 || this._deviceConfigManager.getVisible(DEVICE_BLUETOOTHHEADSET)) {
                    return;
                }
                checkDevicePlug(DEVICE_BLUETOOTHHEADSET, false);
            }
        } catch (Exception unused) {
        }
    }

    void onHeadsetPlug(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("name");
        if (stringExtra == null) {
            stringExtra = "unkonw";
        }
        String str = " [" + stringExtra + "] ";
        int intExtra = intent.getIntExtra(ServerProtocol.DIALOG_PARAM_STATE, -1);
        if (intExtra != -1) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(intExtra == 0 ? "unplugged" : "plugged");
            str = sb.toString();
        }
        String str2 = str + " mic:";
        int intExtra2 = intent.getIntExtra("microphone", -1);
        if (intExtra2 != -1) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append(intExtra2 == 1 ? "Y" : "unkown");
            str2 = sb2.toString();
        }
        QLog.m606w(TAG, "onHeadsetPlug:: " + str2);
        this._deviceConfigManager.setVisible(DEVICE_WIREDHEADSET, 1 == intExtra);
        QLog.m606w(TAG, "onHeadsetPlug exit");
    }

    int internalSendMessage(int i, HashMap<String, Object> hashMap) {
        TraeAudioManagerLooper traeAudioManagerLooper = this.mTraeAudioManagerLooper;
        if (traeAudioManagerLooper != null) {
            return traeAudioManagerLooper.sendMessage(i, hashMap);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getDeviceList(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32774, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getStreamType(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32784, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int startService(String str, long j, boolean z, String str2) {
        if (str2.length() <= 0) {
            return -1;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(EXTRA_DATA_DEVICECONFIG, str2);
        return sendMessage(32772, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int stopService(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32773, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int connectDevice(String str, long j, boolean z, String str2) {
        if (str2 == null) {
            return -1;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(CONNECTDEVICE_DEVICENAME, str2);
        hashMap.put(PARAM_DEVICE, str2);
        return sendMessage(32775, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int forceConnectDevice(String str, long j, boolean z, String str2) {
        if (str2 == null) {
            return -1;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(CONNECTDEVICE_DEVICENAME, str2);
        hashMap.put(PARAM_DEVICE, str2);
        return sendMessage(TraeAudioManagerLooper.MESSAGE_FORCE_CONNECTDEVICE, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int connectHighestPriorityDevice(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_CONNECT_HIGHEST_PRIORITY_DEVICE, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int earAction(String str, long j, boolean z, int i) {
        if (i != 0 && i != 1) {
            return -1;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(EXTRA_EARACTION, Integer.valueOf(i));
        return sendMessage(32776, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int isDeviceChangabled(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32777, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getConnectedDevice(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_GETCONNECTEDDEVICE, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getConnectingDevice(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_GETCONNECTINGDEVICE, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int voicecallPreprocess(String str, long j, boolean z, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(PARAM_MODEPOLICY, Integer.valueOf(i));
        hashMap.put(PARAM_STREAMTYPE, Integer.valueOf(i2));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_VOICECALLPREPROCESS, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int voicecallPostprocess(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_VOICECALLPOSTPROCESS, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int voiceCallAudioParamChanged(String str, long j, boolean z, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(PARAM_MODEPOLICY, Integer.valueOf(i));
        hashMap.put(PARAM_STREAMTYPE, Integer.valueOf(i2));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_VOICECALL_AUIDOPARAM_CHANGED, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int startRing(String str, long j, boolean z, int i, int i2, Uri uri, String str2, boolean z2, int i3, String str3, boolean z3) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(PARAM_RING_DATASOURCE, Integer.valueOf(i));
        hashMap.put(PARAM_RING_RSID, Integer.valueOf(i2));
        hashMap.put(PARAM_RING_URI, uri);
        hashMap.put(PARAM_RING_FILEPATH, str2);
        hashMap.put(PARAM_RING_LOOP, Boolean.valueOf(z2));
        hashMap.put(PARAM_RING_LOOPCOUNT, Integer.valueOf(i3));
        hashMap.put(PARAM_RING_MODE, Boolean.valueOf(z3));
        hashMap.put(PARAM_RING_USERDATA_STRING, str3);
        return sendMessage(TraeAudioManagerLooper.MESSAGE_STARTRING, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int stopRing(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_STOPRING, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int requestReleaseAudioFocus(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_REQUEST_RELEASE_AUDIO_FOCUS, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int recoverAudioFocus(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(TraeAudioManagerLooper.MESSAGE_RECOVER_AUDIO_FOCUS, hashMap);
    }

    int InternalSessionConnectDevice(HashMap<String, Object> hashMap, boolean z) {
        int i;
        AudioDeviceInterface.LogTraceEntry("");
        if (hashMap == null || this._context == null) {
            return -1;
        }
        if (IsMusicScene) {
            QLog.m606w(TAG, "MusicScene: InternalSessionConnectDevice failed");
            return -1;
        }
        String str = (String) hashMap.get(PARAM_DEVICE);
        Log.w(TAG, "ConnectDevice: " + str);
        if (!IsEarPhoneSupported && str.equals(DEVICE_EARPHONE)) {
            QLog.m600e(TAG, "InternalSessionConnectDevice IsEarPhoneSupported = false, Connect device:" + str + " failed");
            return -1;
        }
        boolean InternalIsDeviceChangeable = InternalIsDeviceChangeable();
        if (!checkDevName(str)) {
            i = 7;
        } else {
            i = !this._deviceConfigManager.getVisible(str) ? 8 : !InternalIsDeviceChangeable ? 9 : 0;
        }
        StringBuilder sb = new StringBuilder("sessonID:");
        sb.append((Long) hashMap.get(PARAM_SESSIONID));
        sb.append(" devName:");
        sb.append(str);
        sb.append(" bChangabled:");
        sb.append(InternalIsDeviceChangeable ? "Y" : "N");
        sb.append(" err:");
        sb.append(i);
        QLog.m606w(TAG, sb.toString());
        if (i != 0) {
            Intent intent = new Intent();
            intent.putExtra(CONNECTDEVICE_RESULT_DEVICENAME, (String) hashMap.get(PARAM_DEVICE));
            sendResBroadcast(intent, hashMap, i);
            return -1;
        }
        if (!z && str.equals(this._deviceConfigManager.getConnectedDevice())) {
            QLog.m600e(TAG, " --has connected!");
            Intent intent2 = new Intent();
            intent2.putExtra(CONNECTDEVICE_RESULT_DEVICENAME, (String) hashMap.get(PARAM_DEVICE));
            sendResBroadcast(intent2, hashMap, i);
            return 0;
        }
        QLog.m606w(TAG, " --connecting...");
        InternalConnectDevice(str, hashMap, z);
        AudioDeviceInterface.LogTraceExit();
        return 0;
    }

    int InternalConnectDevice(String str, HashMap<String, Object> hashMap, boolean z) {
        AudioDeviceInterface.LogTraceEntry(" devName:" + str);
        if (str == null) {
            return -1;
        }
        if (IsMusicScene && str.equals(DEVICE_EARPHONE)) {
            QLog.m600e(TAG, "MusicScene, Connect device:" + str + " failed");
            return -1;
        }
        if (!IsEarPhoneSupported && str.equals(DEVICE_EARPHONE)) {
            QLog.m600e(TAG, "IsEarPhoneSupported = false, Connect device:" + str + " failed");
            return -1;
        }
        if (!z && !this._deviceConfigManager.getConnectedDevice().equals(DEVICE_NONE) && str.equals(this._deviceConfigManager.getConnectedDevice())) {
            return 0;
        }
        if (!checkDevName(str) || !this._deviceConfigManager.getVisible(str)) {
            QLog.m600e(TAG, " checkDevName fail");
            return -1;
        }
        if (!InternalIsDeviceChangeable()) {
            QLog.m600e(TAG, " InternalIsDeviceChangeable fail");
            return -1;
        }
        this._deviceConfigManager.setConnecting(str);
        if (this._switchThread != null) {
            QLog.m606w(TAG, "_switchThread:" + this._switchThread.getDeviceName());
            this._switchThread.quit();
            this._switchThread = null;
        }
        if (str.equals(DEVICE_EARPHONE)) {
            this._switchThread = new earphoneSwitchThread();
        } else if (str.equals(DEVICE_SPEAKERPHONE)) {
            this._switchThread = new speakerSwitchThread();
        } else if (str.equals(DEVICE_WIREDHEADSET)) {
            this._switchThread = new headsetSwitchThread();
        } else if (str.equals(DEVICE_BLUETOOTHHEADSET)) {
            this._switchThread = new bluetoothHeadsetSwitchThread();
        }
        switchThread switchthread = this._switchThread;
        if (switchthread != null) {
            switchthread.setDeviceConnectParam(hashMap);
            this._switchThread.start();
        }
        AudioDeviceInterface.LogTraceExit();
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public abstract class switchThread extends Thread {
        boolean _running = true;
        boolean[] _exited = {false};
        HashMap<String, Object> _params = null;
        long _usingtime = 0;

        public abstract void _quit();

        public abstract void _run();

        public abstract String getDeviceName();

        switchThread() {
            QLog.m606w(TraeAudioManager.TAG, " ++switchThread:" + getDeviceName());
        }

        public void setDeviceConnectParam(HashMap<String, Object> hashMap) {
            this._params = hashMap;
        }

        void updateStatus() {
            TraeAudioManager.this._deviceConfigManager.setConnected(getDeviceName());
            processDeviceConnectRes(0);
        }

        void processDeviceConnectRes(int i) {
            TraeAudioManager.this._deviceConfigManager.setConnected(getDeviceName());
            TraeAudioManager.this.InternalNotifyDeviceChangableUpdate();
            AudioDeviceInterface.LogTraceEntry(getDeviceName() + " err:" + i);
            if (this._params == null) {
                TraeAudioManager.this.InternalNotifyDeviceListUpdate();
                return;
            }
            TraeAudioManager traeAudioManager = TraeAudioManager.this;
            traeAudioManager.sessionConnectedDev = traeAudioManager._deviceConfigManager.getConnectedDevice();
            Long l = (Long) this._params.get(TraeAudioManager.PARAM_SESSIONID);
            QLog.m606w(TraeAudioManager.TAG, " sessonID:" + l);
            if (l == null || l.longValue() == Long.MIN_VALUE) {
                TraeAudioManager.this.InternalNotifyDeviceListUpdate();
                QLog.m606w(TraeAudioManager.TAG, "processDeviceConnectRes sid null,don't send res");
                return;
            }
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.CONNECTDEVICE_RESULT_DEVICENAME, (String) this._params.get(TraeAudioManager.PARAM_DEVICE));
            if (TraeAudioManager.this.sendResBroadcast(intent, this._params, i) == 0) {
                TraeAudioManager.this.InternalNotifyDeviceListUpdate();
            }
            AudioDeviceInterface.LogTraceExit();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            AudioDeviceInterface.LogTraceEntry(getDeviceName());
            TraeAudioManager.this.InternalNotifyDeviceChangableUpdate();
            _run();
            synchronized (this._exited) {
                boolean[] zArr = this._exited;
                zArr[0] = true;
                zArr.notifyAll();
            }
            AudioDeviceInterface.LogTraceExit();
        }

        public void quit() {
            AudioDeviceInterface.LogTraceEntry(getDeviceName());
            this._running = false;
            QLog.m606w(TraeAudioManager.TAG, " quit:" + getDeviceName() + " _running:" + this._running);
            interrupt();
            synchronized (this._exited) {
                boolean[] zArr = this._exited;
                if (!zArr[0]) {
                    try {
                        zArr.wait(10000L);
                    } catch (InterruptedException unused) {
                    }
                }
                _quit();
            }
            AudioDeviceInterface.LogTraceExit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class earphoneSwitchThread extends switchThread {
        @Override // com.tencent.sharpgme.jni.TraeAudioManager.switchThread
        public void _quit() {
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.switchThread
        public String getDeviceName() {
            return TraeAudioManager.DEVICE_EARPHONE;
        }

        earphoneSwitchThread() {
            super();
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.switchThread
        public void _run() {
            if (TraeAudioManager.IsUpdateSceneFlag) {
                TraeAudioManager traeAudioManager = TraeAudioManager.this;
                traeAudioManager.InternalSetSpeaker(traeAudioManager._context, TraeAudioManager.this._speakerOn);
            }
            updateStatus();
            if (!TraeAudioManager.IsUpdateSceneFlag) {
                QLog.m606w(TraeAudioManager.TAG, "connect earphone: do nothing");
                return;
            }
            int i = 0;
            while (this._running) {
                if (TraeAudioManager.this._am.isSpeakerphoneOn() != TraeAudioManager.this._speakerOn) {
                    TraeAudioManager traeAudioManager2 = TraeAudioManager.this;
                    traeAudioManager2.InternalSetSpeaker(traeAudioManager2._context, TraeAudioManager.this._speakerOn);
                }
                try {
                    Thread.sleep(i < 5 ? 1000L : 4000L);
                } catch (InterruptedException unused) {
                }
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class speakerSwitchThread extends switchThread {
        @Override // com.tencent.sharpgme.jni.TraeAudioManager.switchThread
        public void _quit() {
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.switchThread
        public String getDeviceName() {
            return TraeAudioManager.DEVICE_SPEAKERPHONE;
        }

        speakerSwitchThread() {
            super();
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.switchThread
        public void _run() {
            if (!TraeAudioManager.IsMusicScene && TraeAudioManager.IsUpdateSceneFlag) {
                TraeAudioManager traeAudioManager = TraeAudioManager.this;
                traeAudioManager.InternalSetSpeaker(traeAudioManager._context, TraeAudioManager.this._speakerOn);
            }
            updateStatus();
            if (TraeAudioManager.IsMusicScene || !TraeAudioManager.IsUpdateSceneFlag) {
                QLog.m606w(TraeAudioManager.TAG, "connect speakerPhone: do nothing");
                return;
            }
            QLog.m606w(TraeAudioManager.TAG, " _run:" + getDeviceName() + " _running:" + this._running);
            int i = 0;
            while (this._running) {
                try {
                    if (TraeAudioManager.this._am.isSpeakerphoneOn() != TraeAudioManager.this._speakerOn) {
                        TraeAudioManager traeAudioManager2 = TraeAudioManager.this;
                        traeAudioManager2.InternalSetSpeaker(traeAudioManager2._context, TraeAudioManager.this._speakerOn);
                    }
                } catch (Exception unused) {
                }
                try {
                    Thread.sleep(i < 5 ? 1000L : 4000L);
                } catch (InterruptedException unused2) {
                }
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class headsetSwitchThread extends switchThread {
        @Override // com.tencent.sharpgme.jni.TraeAudioManager.switchThread
        public void _quit() {
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.switchThread
        public String getDeviceName() {
            return TraeAudioManager.DEVICE_WIREDHEADSET;
        }

        headsetSwitchThread() {
            super();
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.switchThread
        public void _run() {
            if (!TraeAudioManager.IsMusicScene && TraeAudioManager.IsUpdateSceneFlag) {
                TraeAudioManager traeAudioManager = TraeAudioManager.this;
                traeAudioManager.InternalSetSpeaker(traeAudioManager._context, false);
                TraeAudioManager.this._am.setWiredHeadsetOn(true);
            }
            updateStatus();
            if (TraeAudioManager.IsMusicScene || !TraeAudioManager.IsUpdateSceneFlag) {
                QLog.m606w(TraeAudioManager.TAG, "connect headset: do nothing");
                return;
            }
            int i = 0;
            while (this._running) {
                try {
                    if (TraeAudioManager.this._am.isSpeakerphoneOn()) {
                        TraeAudioManager traeAudioManager2 = TraeAudioManager.this;
                        traeAudioManager2.InternalSetSpeaker(traeAudioManager2._context, false);
                    }
                } catch (Exception unused) {
                }
                try {
                    Thread.sleep(i < 5 ? 1000L : 4000L);
                } catch (InterruptedException unused2) {
                }
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class bluetoothHeadsetSwitchThread extends switchThread {
        @Override // com.tencent.sharpgme.jni.TraeAudioManager.switchThread
        public String getDeviceName() {
            return TraeAudioManager.DEVICE_BLUETOOTHHEADSET;
        }

        bluetoothHeadsetSwitchThread() {
            super();
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.switchThread
        public void _run() {
            boolean z;
            if (TraeAudioManager.IsMusicScene || !TraeAudioManager.IsUpdateSceneFlag || TraeAudioManager.nSceneMode == 7) {
                QLog.m606w(TraeAudioManager.TAG, "bluetoothHeadsetSwitchThread: do nothing, IsMusicScene:" + TraeAudioManager.IsMusicScene + " ,IsUpdateSceneFlag:" + TraeAudioManager.IsUpdateSceneFlag);
                updateStatus();
                return;
            }
            try {
                QLog.m606w(TraeAudioManager.TAG, "bluetoothHeadsetSwitchThread sleep 1000");
                Thread.sleep(1000L);
            } catch (InterruptedException unused) {
            }
            if (TraeAudioManager.this.bluetoothState == 7) {
                QLog.m606w(TraeAudioManager.TAG, "WIREDHEADSET plugout, bluetoothState == Bluetooth_State.SCO_CONNECTED force to HEADSET_AVAILABLE, reconnect");
                TraeAudioManager.this.bluetoothState = 4;
            }
            int i = 0;
            while (TraeAudioManager.this.bluetoothState != 4) {
                int i2 = i + 1;
                if (i >= 10) {
                    break;
                }
                QLog.m606w(TraeAudioManager.TAG, "bluetoothHeadsetSwitchThread waiting Bluetooth_State HEADSET_AVAILABLE, " + TraeAudioManager.this.bluetoothState);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException unused2) {
                }
                i = i2;
            }
            boolean z2 = TraeAudioManager.this.bluetoothState != 4;
            if (z2 || !this._running) {
                z = false;
            } else {
                TraeAudioManager.this.bluetoothState = 6;
                _startBluetoothSco();
                QLog.m606w(TraeAudioManager.TAG, "bluetoothHeadsetSwitchThread _startBluetoothSco");
                z = true;
            }
            int i3 = 0;
            while (true) {
                if (!this._running) {
                    break;
                }
                int i4 = i3 + 1;
                if (i3 >= 10 || z2) {
                    break;
                }
                StringBuilder sb = new StringBuilder("bluetoothHeadsetSwitchThread i:");
                sb.append(i4);
                sb.append(" sco:");
                sb.append(TraeAudioManager.this._am.isBluetoothScoOn() ? "Y" : "N");
                sb.append(" :");
                sb.append(TraeAudioManager.this._deviceConfigManager.getBluetoothName());
                QLog.m606w(TraeAudioManager.TAG, sb.toString());
                if (TraeAudioManager.this.bluetoothState == 7) {
                    QLog.m606w(TraeAudioManager.TAG, "bluetoothHeadsetSwitchThread bluetoothState ==  Bluetooth_State.SCO_CONNECTED 1");
                    updateStatus();
                    break;
                }
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException unused3) {
                }
                if (TraeAudioManager.this.bluetoothState == 7) {
                    QLog.m606w(TraeAudioManager.TAG, "bluetoothHeadsetSwitchThread bluetoothState ==  Bluetooth_State.SCO_CONNECTED 2");
                    updateStatus();
                    break;
                }
                if (z && this._running) {
                    _stopBluetoothSco();
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException unused4) {
                    }
                    _startBluetoothSco();
                    QLog.m606w(TraeAudioManager.TAG, "bluetoothHeadsetSwitchThread retry start sco");
                }
                i3 = i4;
            }
            if (TraeAudioManager.this.bluetoothState != 7) {
                if (this._running && !z2) {
                    QLog.m600e(TraeAudioManager.TAG, "bluetoothHeadsetSwitchThread sco fail,remove btheadset");
                    TraeAudioManager.this._deviceConfigManager.setVisible(getDeviceName(), false);
                }
                processDeviceConnectRes(10);
                TraeAudioManager.this.checkAutoDeviceListUpdate();
            }
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.switchThread
        public void _quit() {
            if (TraeAudioManager.this._am == null) {
                return;
            }
            _stopBluetoothSco();
        }

        void _startBluetoothSco() {
            try {
                TraeAudioManager.this._am.setBluetoothScoOn(true);
                TraeAudioManager.this._am.startBluetoothSco();
            } catch (Exception unused) {
            }
        }

        void _stopBluetoothSco() {
            TraeAudioManager.this._am.stopBluetoothSco();
            TraeAudioManager.this._am.setBluetoothScoOn(false);
        }
    }

    int InternalSessionIsDeviceChangabled(HashMap<String, Object> hashMap) {
        Intent intent = new Intent();
        intent.putExtra(ISDEVICECHANGABLED_RESULT_ISCHANGABLED, InternalIsDeviceChangeable());
        sendResBroadcast(intent, hashMap, 0);
        return 0;
    }

    boolean InternalIsDeviceChangeable() {
        String connectingDevice = this._deviceConfigManager.getConnectingDevice();
        return connectingDevice == null || connectingDevice.equals(DEVICE_NONE) || connectingDevice.equals("");
    }

    int InternalSessionGetConnectedDevice(HashMap<String, Object> hashMap) {
        Intent intent = new Intent();
        intent.putExtra(GETCONNECTEDDEVICE_RESULT_LIST, this._deviceConfigManager.getConnectedDevice());
        sendResBroadcast(intent, hashMap, 0);
        return 0;
    }

    int InternalSessionGetConnectingDevice(HashMap<String, Object> hashMap) {
        Intent intent = new Intent();
        intent.putExtra(GETCONNECTINGDEVICE_RESULT_LIST, this._deviceConfigManager.getConnectingDevice());
        sendResBroadcast(intent, hashMap, 0);
        return 0;
    }

    int sendResBroadcast(final Intent intent, HashMap<String, Object> hashMap, final int i) {
        if (this._context == null) {
            return -1;
        }
        Long l = (Long) hashMap.get(PARAM_SESSIONID);
        QLog.m606w(TAG, " sessonID:" + l + " " + ((String) hashMap.get(PARAM_OPERATION)));
        if (l == null || l.longValue() == Long.MIN_VALUE) {
            InternalNotifyDeviceListUpdate();
            QLog.m600e(TAG, "sendResBroadcast sid null,don't send res");
            return -1;
        }
        final Long l2 = (Long) hashMap.get(PARAM_SESSIONID);
        final String str = (String) hashMap.get(PARAM_OPERATION);
        if (OPERATION_VOICECALL_PREPROCESS.equals(str)) {
            intent.setAction(ACTION_TRAEAUDIOMANAGER_RES);
            intent.putExtra(PARAM_SESSIONID, l2);
            intent.putExtra(PARAM_OPERATION, str);
            intent.putExtra(PARAM_RES_ERRCODE, i);
            TraeAudioSessionHost traeAudioSessionHost = this._audioSessionHost;
            if (traeAudioSessionHost == null) {
                return 0;
            }
            traeAudioSessionHost.sendToAudioSessionMessage(intent);
            return 0;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.sharpgme.jni.TraeAudioManager.1
            @Override // java.lang.Runnable
            public void run() {
                intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_RES);
                intent.putExtra(TraeAudioManager.PARAM_SESSIONID, l2);
                intent.putExtra(TraeAudioManager.PARAM_OPERATION, str);
                intent.putExtra(TraeAudioManager.PARAM_RES_ERRCODE, i);
                if (TraeAudioManager.this._context != null) {
                    TraeAudioManager.this._context.sendBroadcast(intent);
                }
            }
        });
        return 0;
    }

    int InternalNotifyDeviceListUpdate() {
        AudioDeviceInterface.LogTraceEntry("");
        if (this._context == null) {
            return -1;
        }
        HashMap<String, Object> snapParams = this._deviceConfigManager.getSnapParams();
        final ArrayList arrayList = (ArrayList) snapParams.get(EXTRA_DATA_AVAILABLEDEVICE_LIST);
        final String str = (String) snapParams.get(EXTRA_DATA_CONNECTEDDEVICE);
        final String str2 = (String) snapParams.get(EXTRA_DATA_PREV_CONNECTEDDEVICE);
        final String bluetoothName = this._deviceConfigManager.getBluetoothName();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.sharpgme.jni.TraeAudioManager.2
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent();
                intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY);
                intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_DEVICELIST_UPDATE);
                intent.putExtra(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST, (String[]) arrayList.toArray(new String[0]));
                intent.putExtra(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE, str);
                intent.putExtra(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE, str2);
                intent.putExtra(TraeAudioManager.EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME, bluetoothName);
                if (TraeAudioManager.this._context != null) {
                    TraeAudioManager.this._context.sendBroadcast(intent);
                }
            }
        });
        AudioDeviceInterface.LogTraceExit();
        return 0;
    }

    int InternalNotifyDeviceChangableUpdate() {
        if (this._context == null) {
            return -1;
        }
        final boolean InternalIsDeviceChangeable = InternalIsDeviceChangeable();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.sharpgme.jni.TraeAudioManager.3
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent();
                intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY);
                intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_DEVICECHANGABLE_UPDATE);
                intent.putExtra(TraeAudioManager.NOTIFY_DEVICECHANGABLE_UPDATE_DATE, InternalIsDeviceChangeable);
                if (TraeAudioManager.this._context != null) {
                    TraeAudioManager.this._context.sendBroadcast(intent);
                }
            }
        });
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public abstract class BluetoohHeadsetCheckInterface {
        abstract void _addAction(IntentFilter intentFilter);

        abstract void _onReceive(Context context, Intent intent);

        public abstract boolean init(Context context, DeviceConfigManager deviceConfigManager);

        public abstract String interfaceDesc();

        public abstract boolean isConnected();

        public abstract void release();

        BluetoohHeadsetCheckInterface() {
        }

        public void addAction(IntentFilter intentFilter) {
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
            _addAction(intentFilter);
        }

        public void onReceive(Context context, Intent intent, DeviceConfigManager deviceConfigManager) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1);
                QLog.m606w(TraeAudioManager.TAG, "BT ACTION_STATE_CHANGED|   EXTRA_STATE " + getBTActionStateChangedExtraString(intExtra));
                QLog.m606w(TraeAudioManager.TAG, "BT ACTION_STATE_CHANGED|   EXTRA_PREVIOUS_STATE " + getBTActionStateChangedExtraString(intExtra2));
                if (intExtra == 10) {
                    QLog.m606w(TraeAudioManager.TAG, "    BT off");
                    deviceConfigManager.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                    return;
                } else {
                    if (intExtra == 12) {
                        QLog.m606w(TraeAudioManager.TAG, "BT OFF-->ON,Visiable it...");
                        return;
                    }
                    return;
                }
            }
            "android.bluetooth.device.action.ACL_CONNECTED".equals(intent.getAction());
            "android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction());
            _onReceive(context, intent);
        }

        String getBTActionStateChangedExtraString(int i) {
            String str;
            switch (i) {
                case 10:
                    str = "STATE_OFF";
                    break;
                case 11:
                    str = "STATE_TURNING_ON";
                    break;
                case 12:
                    str = "STATE_ON";
                    break;
                case 13:
                    str = "STATE_TURNING_OFF";
                    break;
                default:
                    str = "unknow";
                    break;
            }
            return str + CertificateUtil.DELIMITER + i;
        }

        String getSCOAudioStateExtraString(int i) {
            return (i != -1 ? i != 0 ? i != 1 ? i != 2 ? "unknow" : "SCO_AUDIO_STATE_CONNECTING" : "SCO_AUDIO_STATE_CONNECTED" : "SCO_AUDIO_STATE_DISCONNECTED" : "SCO_AUDIO_STATE_ERROR") + CertificateUtil.DELIMITER + i;
        }

        String getBTAdapterConnectionState(int i) {
            return (i != 0 ? i != 1 ? i != 2 ? i != 3 ? "unknow" : "STATE_DISCONNECTING" : "STATE_CONNECTED" : "STATE_CONNECTING" : "STATE_DISCONNECTED") + CertificateUtil.DELIMITER + i;
        }

        String getBTHeadsetConnectionState(int i) {
            return (i != 0 ? i != 1 ? i != 2 ? i != 3 ? "unknow" : "STATE_DISCONNECTING" : "STATE_CONNECTED" : "STATE_CONNECTING" : "STATE_DISCONNECTED") + CertificateUtil.DELIMITER + i;
        }

        String getBTHeadsetAudioState(int i) {
            String str;
            if (i == 10) {
                str = "STATE_AUDIO_DISCONNECTED";
            } else if (i != 12) {
                str = "unknow:" + i;
            } else {
                str = "STATE_AUDIO_CONNECTED";
            }
            return str + CertificateUtil.DELIMITER + i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class BluetoohHeadsetCheckFake extends BluetoohHeadsetCheckInterface {
        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        void _addAction(IntentFilter intentFilter) {
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        void _onReceive(Context context, Intent intent) {
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        public boolean init(Context context, DeviceConfigManager deviceConfigManager) {
            return true;
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        public String interfaceDesc() {
            return "BluetoohHeadsetCheckFake";
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        public boolean isConnected() {
            return false;
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        public void release() {
        }

        BluetoohHeadsetCheckFake() {
            super();
        }
    }

    public BluetoohHeadsetCheckInterface CreateBluetoothCheck(Context context, DeviceConfigManager deviceConfigManager) {
        BluetoohHeadsetCheckInterface bluetoohHeadsetCheck = new BluetoohHeadsetCheck();
        if (!bluetoohHeadsetCheck.init(context, deviceConfigManager)) {
            bluetoohHeadsetCheck = new BluetoohHeadsetCheckFake();
        }
        QLog.m606w(TAG, "CreateBluetoothCheck:" + bluetoohHeadsetCheck.interfaceDesc() + " skip android4.3:N");
        return bluetoohHeadsetCheck;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class BluetoohHeadsetCheck extends BluetoohHeadsetCheckInterface implements BluetoothProfile.ServiceListener {
        BluetoothAdapter _adapter;
        Context _ctx;
        DeviceConfigManager _devCfg;
        BluetoothProfile _profile;
        private final ReentrantLock _profileLock;

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        public String interfaceDesc() {
            return "BluetoohHeadsetCheck";
        }

        BluetoohHeadsetCheck() {
            super();
            this._ctx = null;
            this._devCfg = null;
            this._adapter = null;
            this._profile = null;
            this._profileLock = new ReentrantLock();
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        public boolean init(Context context, DeviceConfigManager deviceConfigManager) {
            AudioDeviceInterface.LogTraceEntry("");
            if (context == null || deviceConfigManager == null) {
                QLog.m600e(TraeAudioManager.TAG, " err ctx==null||_devCfg==null");
                return false;
            }
            this._profileLock.lock();
            this._ctx = context;
            this._devCfg = deviceConfigManager;
            try {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                this._adapter = defaultAdapter;
                if (defaultAdapter == null) {
                    QLog.m600e(TraeAudioManager.TAG, " err getDefaultAdapter fail!");
                    return false;
                }
                if (defaultAdapter.isEnabled() && this._profile == null && !this._adapter.getProfileProxy(this._ctx, this, 1)) {
                    QLog.m600e(TraeAudioManager.TAG, "BluetoohHeadsetCheck: getProfileProxy HEADSET fail!");
                    return false;
                }
                this._profileLock.unlock();
                AudioDeviceInterface.LogTraceExit();
                return true;
            } finally {
                this._profileLock.unlock();
            }
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        public void release() {
            this._profileLock.lock();
            AudioDeviceInterface.LogTraceEntry("_profile:" + this._profile);
            try {
                try {
                    BluetoothAdapter bluetoothAdapter = this._adapter;
                    if (bluetoothAdapter != null) {
                        BluetoothProfile bluetoothProfile = this._profile;
                        if (bluetoothProfile != null) {
                            bluetoothAdapter.closeProfileProxy(1, bluetoothProfile);
                        }
                        this._profile = null;
                    }
                } catch (Exception e) {
                    QLog.m606w(TraeAudioManager.TAG, " closeProfileProxy:e:" + e.getMessage());
                }
                AudioDeviceInterface.LogTraceExit();
            } finally {
                this._profileLock.unlock();
            }
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        public boolean isConnected() {
            this._profileLock.lock();
            boolean z = false;
            try {
                try {
                    BluetoothProfile bluetoothProfile = this._profile;
                    if (bluetoothProfile != null) {
                        List<BluetoothDevice> connectedDevices = bluetoothProfile.getConnectedDevices();
                        if (connectedDevices == null) {
                            return false;
                        }
                        if (connectedDevices.size() > 0) {
                            z = true;
                        }
                    }
                } catch (Exception e) {
                    QLog.m601e(TraeAudioManager.TAG, "getConnectedDevices fail.", e);
                }
                return z;
            } finally {
                this._profileLock.unlock();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x00a6 A[Catch: all -> 0x00da, Exception -> 0x00dc, TryCatch #1 {Exception -> 0x00dc, blocks: (B:5:0x0033, B:9:0x003b, B:10:0x005b, B:12:0x005f, B:14:0x0067, B:16:0x006b, B:17:0x0087, B:19:0x008d, B:26:0x00a6, B:27:0x00af, B:32:0x009d), top: B:4:0x0033, outer: #2 }] */
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onServiceConnected(int r9, android.bluetooth.BluetoothProfile r10) {
            /*
                Method dump skipped, instructions count: 296
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheck.onServiceConnected(int, android.bluetooth.BluetoothProfile):void");
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            if (i == 1) {
                QLog.m606w(TraeAudioManager.TAG, "TRAEBluetoohProxy: HEADSET Disconnected");
                if (isConnected()) {
                    TraeAudioManager.this.checkDevicePlug(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                }
                this._profileLock.lock();
                AudioDeviceInterface.LogTraceEntry("_profile:" + this._profile + " profile:" + i);
                try {
                    BluetoothProfile bluetoothProfile = this._profile;
                    if (bluetoothProfile != null) {
                        this._adapter.closeProfileProxy(1, bluetoothProfile);
                        this._profile = null;
                    }
                    this._profileLock.unlock();
                    AudioDeviceInterface.LogTraceExit();
                } catch (Throwable th) {
                    this._profileLock.unlock();
                    throw th;
                }
            }
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        void _addAction(IntentFilter intentFilter) {
            QLog.m606w(TraeAudioManager.TAG, " " + interfaceDesc() + " _addAction");
            intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
            intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        void _onReceive(Context context, Intent intent) {
            if ("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 10);
                if (intExtra == 12) {
                    if (TraeAudioManager.this.bluetoothState == 6) {
                        QLog.m606w(TraeAudioManager.TAG, "bluetoothHeadsetSwitchThread ACTION_AUDIO_STATE_CHANGED +++ Bluetooth audio SCO is now connected, SCO_CONNECTED");
                        TraeAudioManager.this.bluetoothState = 7;
                        return;
                    }
                    return;
                }
                if (intExtra != 11 && intExtra == 10) {
                    QLog.m606w(TraeAudioManager.TAG, "ACTION_AUDIO_STATE_CHANGED +++ Bluetooth audio SCO is STATE_AUDIO_DISCONNECTED");
                    return;
                }
                return;
            }
            if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                if (intExtra2 == 0) {
                    TraeAudioManager.this.bluetoothState = 3;
                    this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                    QLog.m606w(TraeAudioManager.TAG, "jeringtest BluetoothHeadset ACTION_CONNECTION_STATE_CHANGED BluetoothProfile.STATE_DISCONNECTED");
                    return;
                } else {
                    if (intExtra2 != 2) {
                        return;
                    }
                    TraeAudioManager.this.bluetoothState = 4;
                    this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                    QLog.m606w(TraeAudioManager.TAG, "jeringtest  BluetoothHeadset ACTION_CONNECTION_STATE_CHANGED BluetoothProfile.STATE_CONNECTED");
                    return;
                }
            }
            if ("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                int intExtra3 = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", -1);
                int intExtra4 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE", -1);
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                QLog.m606w(TraeAudioManager.TAG, "BT ACTION_CONNECTION_STATE_CHANGED|   EXTRA_CONNECTION_STATE " + getBTAdapterConnectionState(intExtra3));
                QLog.m606w(TraeAudioManager.TAG, "    EXTRA_PREVIOUS_CONNECTION_STATE " + getBTAdapterConnectionState(intExtra4));
                StringBuilder sb = new StringBuilder("    EXTRA_DEVICE dev:");
                sb.append(bluetoothDevice != null ? bluetoothDevice.getName() : " ");
                QLog.m606w(TraeAudioManager.TAG, sb.toString());
                if (intExtra3 != 2) {
                    if (intExtra3 == 0) {
                        this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                        return;
                    }
                    return;
                } else {
                    StringBuilder sb2 = new StringBuilder("   dev:");
                    sb2.append(bluetoothDevice != null ? bluetoothDevice.getName() : " ");
                    sb2.append(" connected,start sco...");
                    QLog.m606w(TraeAudioManager.TAG, sb2.toString());
                    this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                    this._devCfg.setBluetoothName(bluetoothDevice != null ? bluetoothDevice.getName() : "unkown");
                    return;
                }
            }
            if ("android.media.ACTION_SCO_AUDIO_STATE_UPDATED".equals(intent.getAction())) {
                int intExtra5 = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                int intExtra6 = intent.getIntExtra("android.media.extra.SCO_AUDIO_PREVIOUS_STATE", -1);
                BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                StringBuilder sb3 = new StringBuilder("BT ACTION_SCO_AUDIO_STATE_UPDATED|   EXTRA_CONNECTION_STATE  dev:");
                sb3.append(bluetoothDevice2 != null ? bluetoothDevice2.getName() : " ");
                QLog.m606w(TraeAudioManager.TAG, sb3.toString());
                QLog.m606w(TraeAudioManager.TAG, "   EXTRA_SCO_AUDIO_STATE " + getSCOAudioStateExtraString(intExtra5));
                QLog.m606w(TraeAudioManager.TAG, "   EXTRA_SCO_AUDIO_PREVIOUS_STATE " + getSCOAudioStateExtraString(intExtra6));
            }
        }
    }

    /* loaded from: classes3.dex */
    class BluetoohHeadsetCheckFor2x extends BluetoohHeadsetCheckInterface {
        public static final String ACTION_BLUETOOTHHEADSET_AUDIO_STATE_CHANGED = "android.bluetooth.headset.action.AUDIO_STATE_CHANGED";
        public static final String ACTION_BLUETOOTHHEADSET_STATE_CHANGED = "android.bluetooth.headset.action.STATE_CHANGED";
        public static final int AUDIO_STATE_CONNECTED = 1;
        public static final int AUDIO_STATE_DISCONNECTED = 0;
        static final int STATE_CONNECTED = 2;
        static final int STATE_DISCONNECTED = 0;
        Class<?> BluetoothHeadsetClass;
        Object BluetoothHeadsetObj;
        Class<?> ListenerClass;
        Context _ctx;
        DeviceConfigManager _devCfg;
        Method getCurrentHeadsetMethod;

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        public String interfaceDesc() {
            return "BluetoohHeadsetCheckFor2x";
        }

        BluetoohHeadsetCheckFor2x() {
            super();
            this.BluetoothHeadsetClass = null;
            this.ListenerClass = null;
            this.BluetoothHeadsetObj = null;
            this.getCurrentHeadsetMethod = null;
            this._ctx = null;
            this._devCfg = null;
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        public boolean init(Context context, DeviceConfigManager deviceConfigManager) {
            AudioDeviceInterface.LogTraceEntry("");
            this._ctx = context;
            this._devCfg = deviceConfigManager;
            if (context == null || deviceConfigManager == null) {
                return false;
            }
            try {
                this.BluetoothHeadsetClass = Class.forName("android.bluetooth.BluetoothHeadset");
            } catch (Exception unused) {
                QLog.m600e(TraeAudioManager.TAG, "BTLooperThread BluetoothHeadset class not found");
            }
            if (this.BluetoothHeadsetClass == null) {
                return false;
            }
            try {
                this.ListenerClass = Class.forName("android.bluetooth.BluetoothHeadset$ServiceListener");
            } catch (Exception e) {
                QLog.m600e(TraeAudioManager.TAG, "BTLooperThread BluetoothHeadset.ServiceListener class not found:" + e);
            }
            try {
                this.getCurrentHeadsetMethod = this.BluetoothHeadsetClass.getDeclaredMethod("getCurrentHeadset", new Class[0]);
            } catch (NoSuchMethodException unused2) {
                QLog.m600e(TraeAudioManager.TAG, "BTLooperThread BluetoothHeadset method getCurrentHeadset NoSuchMethodException");
            }
            if (this.getCurrentHeadsetMethod == null) {
                return false;
            }
            try {
                this.BluetoothHeadsetObj = this.BluetoothHeadsetClass.getConstructor(Context.class, this.ListenerClass).newInstance(context, null);
            } catch (IllegalAccessException unused3) {
                QLog.m600e(TraeAudioManager.TAG, "BTLooperThread BluetoothHeadset getConstructor IllegalAccessException");
            } catch (IllegalArgumentException unused4) {
                QLog.m600e(TraeAudioManager.TAG, "BTLooperThread BluetoothHeadset getConstructor IllegalArgumentException");
            } catch (InstantiationException unused5) {
                QLog.m600e(TraeAudioManager.TAG, "BTLooperThread BluetoothHeadset getConstructor InstantiationException");
            } catch (NoSuchMethodException unused6) {
                QLog.m600e(TraeAudioManager.TAG, "BTLooperThread BluetoothHeadset getConstructor NoSuchMethodException");
            } catch (InvocationTargetException unused7) {
                QLog.m600e(TraeAudioManager.TAG, "BTLooperThread BluetoothHeadset getConstructor InvocationTargetException");
            }
            if (this.BluetoothHeadsetObj == null) {
                return false;
            }
            this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, isConnected());
            if (isConnected()) {
                this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                TraeAudioManager.this.checkDevicePlug(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
            } else {
                this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
            }
            AudioDeviceInterface.LogTraceExit();
            return true;
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        public void release() {
            Method method;
            AudioDeviceInterface.LogTraceEntry("");
            if (this.BluetoothHeadsetObj == null) {
                return;
            }
            try {
                method = this.BluetoothHeadsetClass.getDeclaredMethod("close", new Class[0]);
            } catch (NoSuchMethodException unused) {
                QLog.m600e(TraeAudioManager.TAG, "BTLooperThread _uninitHeadsetfor2x method close NoSuchMethodException");
                method = null;
            }
            if (method == null) {
                return;
            }
            try {
                method.invoke(this.BluetoothHeadsetObj, new Object[0]);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
            }
            this.BluetoothHeadsetClass = null;
            this.ListenerClass = null;
            this.BluetoothHeadsetObj = null;
            this.getCurrentHeadsetMethod = null;
            AudioDeviceInterface.LogTraceExit();
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0035 A[ORIG_RETURN, RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:13:0x0028  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean isConnected() {
            /*
                r5 = this;
                java.lang.String r0 = "TRAEJava"
                java.lang.reflect.Method r1 = r5.getCurrentHeadsetMethod
                r2 = 0
                if (r1 != 0) goto L8
                return r2
            L8:
                java.lang.Object r3 = r5.BluetoothHeadsetObj     // Catch: java.lang.reflect.InvocationTargetException -> L11 java.lang.IllegalAccessException -> L17 java.lang.IllegalArgumentException -> L1d
                java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch: java.lang.reflect.InvocationTargetException -> L11 java.lang.IllegalAccessException -> L17 java.lang.IllegalArgumentException -> L1d
                java.lang.Object r1 = r1.invoke(r3, r4)     // Catch: java.lang.reflect.InvocationTargetException -> L11 java.lang.IllegalAccessException -> L17 java.lang.IllegalArgumentException -> L1d
                goto L23
            L11:
                java.lang.String r1 = "BTLooperThread BluetoothHeadset method getCurrentHeadset InvocationTargetException"
                com.tencent.p014av.utils.QLog.m606w(r0, r1)
                goto L22
            L17:
                java.lang.String r1 = "BTLooperThread BluetoothHeadset method getCurrentHeadset IllegalAccessException"
                com.tencent.p014av.utils.QLog.m606w(r0, r1)
                goto L22
            L1d:
                java.lang.String r1 = "BTLooperThread BluetoothHeadset method getCurrentHeadset IllegalArgumentException"
                com.tencent.p014av.utils.QLog.m606w(r0, r1)
            L22:
                r1 = 0
            L23:
                if (r1 == 0) goto L28
                java.lang.String r3 = " Y"
                goto L2a
            L28:
                java.lang.String r3 = "N"
            L2a:
                java.lang.String r4 = "BTLooperThread BluetoothHeadset method getCurrentHeadset res:"
                java.lang.String r3 = r4.concat(r3)
                com.tencent.p014av.utils.QLog.m606w(r0, r3)
                if (r1 == 0) goto L36
                r2 = 1
            L36:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckFor2x.isConnected():boolean");
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        void _addAction(IntentFilter intentFilter) {
            QLog.m606w(TraeAudioManager.TAG, " " + interfaceDesc() + " _addAction");
            intentFilter.addAction(ACTION_BLUETOOTHHEADSET_AUDIO_STATE_CHANGED);
            intentFilter.addAction(ACTION_BLUETOOTHHEADSET_STATE_CHANGED);
        }

        @Override // com.tencent.sharpgme.jni.TraeAudioManager.BluetoohHeadsetCheckInterface
        void _onReceive(Context context, Intent intent) {
            if (ACTION_BLUETOOTHHEADSET_AUDIO_STATE_CHANGED.equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.headset.extra.STATE", -2);
                int intExtra2 = intent.getIntExtra("android.bluetooth.headset.extra.PREVIOUS_STATE", -2);
                int intExtra3 = intent.getIntExtra("android.bluetooth.headset.extra.AUDIO_STATE", -2);
                QLog.m606w(TraeAudioManager.TAG, "++ AUDIO_STATE_CHANGED|  STATE " + intExtra);
                QLog.m606w(TraeAudioManager.TAG, "       PREVIOUS_STATE " + intExtra2);
                QLog.m606w(TraeAudioManager.TAG, "       AUDIO_STATE " + intExtra3);
                if (intExtra3 == 2) {
                    this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                    return;
                } else {
                    if (intExtra3 == 0) {
                        this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                        return;
                    }
                    return;
                }
            }
            if (ACTION_BLUETOOTHHEADSET_STATE_CHANGED.equals(intent.getAction())) {
                int intExtra4 = intent.getIntExtra("android.bluetooth.headset.extra.STATE", -2);
                int intExtra5 = intent.getIntExtra("android.bluetooth.headset.extra.PREVIOUS_STATE", -2);
                int intExtra6 = intent.getIntExtra("android.bluetooth.headset.extra.AUDIO_STATE", -2);
                QLog.m606w(TraeAudioManager.TAG, "++ STATE_CHANGED|  STATE " + intExtra4);
                QLog.m606w(TraeAudioManager.TAG, "       PREVIOUS_STATE " + intExtra5);
                QLog.m606w(TraeAudioManager.TAG, "       AUDIO_STATE " + intExtra6);
                if (intExtra6 == 2) {
                    this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                } else if (intExtra6 == 0) {
                    this._devCfg.setVisible(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                }
            }
        }
    }

    static String getForceConfigName(int i) {
        if (i < 0) {
            return "unknow";
        }
        String[] strArr = forceName;
        return i < strArr.length ? strArr[i] : "unknow";
    }

    public static Object invokeMethod(Object obj, String str, Object[] objArr, Class[] clsArr) {
        try {
            return obj.getClass().getMethod(str, clsArr).invoke(obj, objArr);
        } catch (Exception e) {
            QLog.m606w(TAG, "invokeMethod Exception:" + e.getMessage());
            return null;
        }
    }

    public static Object invokeStaticMethod(String str, String str2, Object[] objArr, Class[] clsArr) {
        try {
            return Class.forName(str).getMethod(str2, clsArr).invoke(null, objArr);
        } catch (ClassNotFoundException unused) {
            QLog.m606w(TAG, "ClassNotFound:" + str);
            return null;
        } catch (IllegalAccessException unused2) {
            QLog.m606w(TAG, "IllegalAccess:" + str2);
            return null;
        } catch (IllegalArgumentException unused3) {
            QLog.m606w(TAG, "IllegalArgument:" + str2);
            return null;
        } catch (NoSuchMethodException unused4) {
            QLog.m606w(TAG, "NoSuchMethod:" + str2);
            return null;
        } catch (InvocationTargetException unused5) {
            QLog.m606w(TAG, "InvocationTarget:" + str2);
            return null;
        } catch (Exception e) {
            QLog.m606w(TAG, "invokeStaticMethod Exception:" + e.getMessage());
            return null;
        }
    }

    static void setParameters(String str) {
        QLog.m606w(TAG, "setParameters  :" + str);
        invokeStaticMethod("android.media.AudioSystem", "setParameters", new Object[]{str}, new Class[]{String.class});
    }

    static void setPhoneState(int i) {
        invokeStaticMethod("android.media.AudioSystem", "setPhoneState", new Object[]{Integer.valueOf(i)}, new Class[]{Integer.TYPE});
    }

    static void setForceUse(int i, int i2) {
        QLog.m606w(TAG, "setForceUse  usage:" + i + " config:" + i2 + " ->" + getForceConfigName(i2) + " res:" + invokeStaticMethod("android.media.AudioSystem", "setForceUse", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}, new Class[]{Integer.TYPE, Integer.TYPE}));
    }

    static int getForceUse(int i) {
        Integer num = 0;
        Object invokeStaticMethod = invokeStaticMethod("android.media.AudioSystem", "getForceUse", new Object[]{Integer.valueOf(i)}, new Class[]{Integer.TYPE});
        if (invokeStaticMethod != null) {
            num = (Integer) invokeStaticMethod;
        }
        QLog.m606w(TAG, "getForceUse  usage:" + i + " config:" + num + " ->" + getForceConfigName(num.intValue()));
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void forceVolumeControlStream(AudioManager audioManager, int i) {
        if (Build.MANUFACTURER.equals(Constants.SDK_ACCOUNT_TYPE_GOOGLE)) {
            QLog.m606w(TAG, "forceVolumeControlStream, Google phone nothing to do");
            return;
        }
        QLog.m606w(TAG, "forceVolumeControlStream  streamType:" + i + " res:" + invokeMethod(audioManager, "forceVolumeControlStream", new Object[]{Integer.valueOf(i)}, new Class[]{Integer.TYPE}));
    }
}
