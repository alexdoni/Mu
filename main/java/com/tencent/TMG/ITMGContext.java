package com.tencent.TMG;

import android.content.Context;
import android.content.Intent;
import androidx.core.view.PointerIconCompat;
import com.tencent.p014av.sdk.AVError;

/* loaded from: classes3.dex */
public abstract class ITMGContext {
    public static final int AV_SUB_EVENT_CUSTOMDATA_UPDATE = 0;
    public static final int AV_SUB_EVENT_SERVER_AUDIO_ROUTE_UPDATE = 0;
    public static final int ITMG_EVENT_ID_USER_ENTER = 1;
    public static final int ITMG_EVENT_ID_USER_EXIT = 2;
    public static final int ITMG_EVENT_ID_USER_HAS_AUDIO = 5;
    public static final int ITMG_EVENT_ID_USER_NO_AUDIO = 6;
    public static final int ITMG_LOG_LEVEL_DEBUG = 3;
    public static final int ITMG_LOG_LEVEL_ERROR = 1;
    public static final int ITMG_LOG_LEVEL_INFO = 2;
    public static final int ITMG_LOG_LEVEL_NONE = -1;
    public static final int ITMG_LOG_LEVEL_VERBOSE = 4;
    public static final int ITMG_MAIN_EVENT_TYPE_CHORUS_SUB_EVENT_ACCOMPANIER_OPTION = 7;
    public static final int ITMG_MAIN_EVENT_TYPE_CHORUS_SUB_EVENT_HAS_CMD_PACK = 2;
    public static final int ITMG_MAIN_EVENT_TYPE_CHORUS_SUB_EVENT_HAS_NO_CMD_PACK = 1;
    public static final int ITMG_MAIN_EVENT_TYPE_CHORUS_SUB_EVENT_START = 3;
    public static final int ITMG_MAIN_EVENT_TYPE_CHORUS_SUB_EVENT_STATUS_REFUSE = 9;
    public static final int ITMG_MAIN_EVENT_TYPE_CHORUS_SUB_EVENT_STOP = 6;
    public static final int ITMG_MAIN_EVENT_TYPE_CHORUS_SUB_EVENT_STOP_BY_PEER = 10;
    public static final int ITMG_REALTIME_ASR_CONTENT = 1;
    public static final int ITMG_REALTIME_ASR_END = 2;
    public static final int ITMG_REALTIME_ASR_START = 0;
    public static final int ITMG_ROOM_CHANGE_EVENT_COMPLETE = 3;
    public static final int ITMG_ROOM_CHANGE_EVENT_ENTERROOM = 1;
    public static final int ITMG_ROOM_CHANGE_EVENT_REQUEST = 4;
    public static final int ITMG_ROOM_CHANGE_EVENT_START = 2;
    public static final int ITMG_ROOM_MANAGEMENT_AUDIO_REC_OP = 3;
    public static final int ITMG_ROOM_MANAGEMENT_AUDIO_SEND_OP = 2;
    public static final int ITMG_ROOM_MANAGEMENT_CAPTURE_OP = 0;
    public static final int ITMG_ROOM_MANAGEMENT_GET_MIC_STATE = 6;
    public static final int ITMG_ROOM_MANAGEMENT_GET_SPEAKER_STATE = 7;
    public static final int ITMG_ROOM_MANAGEMENT_MIC_OP = 4;
    public static final int ITMG_ROOM_MANAGEMENT_PLAY_OP = 1;
    public static final int ITMG_ROOM_MANAGEMENT_SPEAKER_OP = 5;
    public static final int ITMG_ROOM_MANAGERMENT_FOBIN_OP = 8;
    public static final int ITMG_ROOM_TYPE_FLUENCY = 1;
    public static final int ITMG_ROOM_TYPE_HIGHQUALITY = 3;
    public static final int ITMG_ROOM_TYPE_STANDARD = 2;
    private static ITMGContext mSelf;

    /* loaded from: classes3.dex */
    public static abstract class ITMGDelegate {
        public void OnEvent(ITMG_MAIN_EVENT_TYPE itmg_main_event_type, Intent intent) {
        }
    }

    /* loaded from: classes3.dex */
    public enum ITMG_CHECK_MIC_STATUS {
        ITMG_CHECK_MIC_STATUS_AVAILABLE,
        ITMG_CHECK_MIC_STATUS_ERROR_FUNC,
        ITMG_CHECK_MIC_STATUS_NO_GRANTED,
        ITMG_CHECK_MIC_STATUS_INVALID_MIC,
        ITMG_CHECK_MIC_STATUS_JNI_ERROR,
        ITMG_CHECK_MIC_STATUS_NOT_INIT
    }

    /* loaded from: classes3.dex */
    public enum ITMG_RANGE_AUDIO_MODE {
        ITMG_RANGE_AUDIO_MODE_WORLD,
        ITMG_RANGE_AUDIO_MODE_TEAM
    }

    /* loaded from: classes3.dex */
    public enum ITMG_RECORD_PERMISSION {
        ITMG_PERMISSION_GRANTED,
        ITMG_PERMISSION_Denied,
        ITMG_PERMISSION_NotDetermined,
        ITMG_PERMISSION_ERROR
    }

    public abstract ITMG_CHECK_MIC_STATUS CheckMic();

    public abstract ITMG_RECORD_PERMISSION CheckMicPermission();

    public abstract int EnterRoom(String str, int i, byte[] bArr);

    public abstract int ExitRoom();

    public abstract String GetAdvanceParams(String str);

    public abstract ITMGAudioCtrl GetAudioCtrl();

    public abstract ITMGAudioEffectCtrl GetAudioEffectCtrl();

    public abstract ITMGPTT GetPTT();

    public abstract ITMGRoom GetRoom();

    public abstract ITMGRoomManager GetRoomManager();

    public abstract String GetSDKVersion();

    public abstract int Init(String str, String str2);

    public abstract boolean IsRoomEntered();

    public abstract int Pause();

    public abstract int Poll();

    public abstract int Resume();

    public abstract int SetAdvanceParams(String str, String str2);

    public abstract void SetAppVersion(String str);

    public abstract int SetLogLevel(int i, int i2);

    public abstract int SetLogPath(String str);

    public abstract int SetRangeAudioMode(ITMG_RANGE_AUDIO_MODE itmg_range_audio_mode);

    public abstract int SetRangeAudioTeamID(int i);

    public abstract int SetRecvMixStreamCount(int i);

    public abstract void SetRegion(String str);

    public abstract int SetTMGDelegate(ITMGDelegate iTMGDelegate);

    public abstract int StartAudioEngine(boolean z);

    public abstract int StartRealTimeASR();

    public abstract int StartRealTimeASR(String str);

    public abstract int StopAudioEngine();

    public abstract int StopRealTimeASR();

    public abstract int Uninit();

    /* loaded from: classes3.dex */
    public enum ITMG_MAIN_EVENT_TYPE {
        ITMG_MAIN_EVENT_TYPE_NONE(0),
        ITMG_MAIN_EVENT_TYPE_ENTER_ROOM(1),
        ITMG_MAIN_EVENT_TYPE_EXIT_ROOM(2),
        ITMG_MAIN_EVENT_TYPE_ROOM_DISCONNECT(3),
        ITMG_MAIN_EVNET_TYPE_USER_UPDATE(4),
        ITMG_MAIN_EVENT_TYPE_NUMBER_OF_USERS_UPDATE(7),
        ITMG_MAIN_EVENT_TYPE_NUMBER_OF_AUDIOSTREAMS_UPDATE(8),
        ITMG_MAIN_EVENT_TYPE_RECONNECT_START(11),
        ITMG_MAIN_EVENT_TYPE_RECONNECT_SUCCESS(12),
        ITMG_MAIN_EVENT_TYPE_SWITCH_ROOM(13),
        ITMG_MAIN_EVENT_TYPE_CHANGE_ROOM_TYPE(21),
        ITMG_MAIN_EVENT_TYPE_AUDIO_DATA_EMPTY(22),
        ITMG_MAIN_EVENT_TYPE_ROOM_SHARING_START(23),
        ITMG_MAIN_EVENT_TYPE_ROOM_SHARING_STOP(24),
        ITMG_MAIN_EVENT_TYPE_RECORD_COMPLETED(30),
        ITMG_MAIN_EVENT_TYPE_RECORD_PREVIEW_COMPLETED(31),
        ITMG_MAIN_EVENT_TYPE_RECORD_MIX_COMPLETED(32),
        ITMG_MAIN_EVNET_TYPE_USER_VOLUMES(PointerIconCompat.TYPE_GRAB),
        ITMG_MAIN_EVENT_TYPE_ACCOMPANY_FINISH(1090),
        ITMG_MAIN_EVENT_TYPE_SERVER_AUDIO_ROUTE_EVENT(1091),
        ITMG_MAIN_EVENT_TYPE_CUSTOMDATA_UPDATE(1092),
        ITMG_MAIN_EVENT_TYPE_REALTIME_ASR(1093),
        ITMG_MAIN_EVENT_TYPE_CHORUS_EVENT(1094),
        ITMG_MAIN_EVENT_TYPE_CHANGETEAMID(1095),
        ITMG_MAIN_EVENT_TYPE_HARDWARE_TEST_RECORD_FINISH(2001),
        ITMG_MAIN_EVENT_TYPE_HARDWARE_TEST_PREVIEW_FINISH(2002),
        ITMG_MAIN_EVNET_TYPE_PTT_RECORD_COMPLETE(AVError.AV_ERR_RECORD_OPENFILE_FAILED),
        ITMG_MAIN_EVNET_TYPE_PTT_UPLOAD_COMPLETE(AVError.AV_ERR_RECORD_FILE_FORAMT_NOTSUPPORT),
        ITMG_MAIN_EVNET_TYPE_PTT_DOWNLOAD_COMPLETE(AVError.AV_ERR_RECORD_DECODER_FAILED),
        ITMG_MAIN_EVNET_TYPE_PTT_PLAY_COMPLETE(AVError.AV_ERR_RECORD_BAD_PARAM),
        ITMG_MAIN_EVNET_TYPE_PTT_SPEECH2TEXT_COMPLETE(AVError.AV_ERR_RECORD_MEMORY_ALLOC_FAILED),
        ITMG_MAIN_EVNET_TYPE_PTT_STREAMINGRECOGNITION_COMPLETE(AVError.AV_ERR_RECORD_CREATE_THREAD_FAILED),
        ITMG_MAIN_EVNET_TYPE_PTT_STREAMINGRECOGNITION_IS_RUNNING(AVError.AV_ERR_RECORD_STATE_ILLIGAL),
        ITMG_MAIN_EVNET_TYPE_ROOM_MANAGEMENT_OPERATOR(6000),
        ITMG_MAIN_EVENT_TYPE_CHANGE_ROOM_QUALITY(1022);

        private int index;

        ITMG_MAIN_EVENT_TYPE(int i) {
            this.index = i;
        }
    }

    /* loaded from: classes3.dex */
    public enum ITMG_SERVER_AUDIO_ROUTE_SEND_TYPE {
        AUDIO_ROUTE_SEND_INQUIRE_ERROR(0),
        AUDIO_ROUTE_NOT_SEND_TO_ANYONE(1),
        AUDIO_ROUTE_SEND_TO_ALL(2),
        AUDIO_ROUTE_SEND_BLACK_LIST(3),
        AUDIO_ROUTE_SEND_WHITE_LIST(4);

        private int value;

        ITMG_SERVER_AUDIO_ROUTE_SEND_TYPE(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static ITMG_SERVER_AUDIO_ROUTE_SEND_TYPE valueOf(int i) {
            if (i == 1) {
                return AUDIO_ROUTE_NOT_SEND_TO_ANYONE;
            }
            if (i == 2) {
                return AUDIO_ROUTE_SEND_TO_ALL;
            }
            if (i == 3) {
                return AUDIO_ROUTE_SEND_BLACK_LIST;
            }
            if (i == 4) {
                return AUDIO_ROUTE_SEND_WHITE_LIST;
            }
            return AUDIO_ROUTE_SEND_INQUIRE_ERROR;
        }
    }

    /* loaded from: classes3.dex */
    public enum ITMG_SERVER_AUDIO_ROUTE_RECV_TYPE {
        AUDIO_ROUTE_RECV_INQUIRE_ERROR(0),
        AUDIO_ROUTE_NOT_RECV_FROM_ANYONE(1),
        AUDIO_ROUTE_RECV_FROM_ALL(2),
        AUDIO_ROUTE_RECV_BLACK_LIST(3),
        AUDIO_ROUTE_RECV_WHITE_LIST(4);

        private int value;

        ITMG_SERVER_AUDIO_ROUTE_RECV_TYPE(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static ITMG_SERVER_AUDIO_ROUTE_RECV_TYPE valueOf(int i) {
            if (i == 1) {
                return AUDIO_ROUTE_NOT_RECV_FROM_ANYONE;
            }
            if (i == 2) {
                return AUDIO_ROUTE_RECV_FROM_ALL;
            }
            if (i == 3) {
                return AUDIO_ROUTE_RECV_BLACK_LIST;
            }
            if (i == 4) {
                return AUDIO_ROUTE_RECV_WHITE_LIST;
            }
            return AUDIO_ROUTE_RECV_INQUIRE_ERROR;
        }
    }

    public static ITMGContext GetInstance(Context context) {
        if (mSelf == null && context != null) {
            mSelf = new TMGContext(context);
        }
        return mSelf;
    }
}
