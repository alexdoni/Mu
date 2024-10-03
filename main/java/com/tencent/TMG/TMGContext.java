package com.tencent.TMG;

import android.content.Context;
import android.content.Intent;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tencent.TMG.ITMGContext;
import com.tencent.TMG.TMGRoom;
import com.tencent.TMG.advance.TMGAudioRecordCtrl;
import com.tencent.p014av.sdk.AVError;
import com.tencent.p014av.utils.QLog;
import com.tencent.p014av.wrapper.GMEAudioInterrupt;
import com.tencent.p014av.wrapper.OpensdkGameWrapper;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class TMGContext extends ITMGContext {
    private static final String TAG = "TMGContext";
    private OpensdkGameWrapper gameWrapper;
    Context mContext;
    private boolean sdk_match;
    public ITMGContext.ITMGDelegate mTmgDelegate = null;
    TMGRoom mRoom = new TMGRoom(this);
    TMGRoomManager mRoomManager = new TMGRoomManager(this);
    TMGAudioCtrl mAudioCtrl = new TMGAudioCtrl(this);
    TMGPTT mPTT = new TMGPTT(this);
    TMGAudioEffectCtrl mEffectCtrl = new TMGAudioEffectCtrl(this);
    List<String> mLstUinSpeaking = new ArrayList();
    TMGRoom.EventListener mRoomEventListener = new TMGRoom.EventListener() { // from class: com.tencent.TMG.TMGContext.1
        @Override // com.tencent.TMG.TMGRoom.EventListener
        public void onEnterRoomComplete(int i, String str) {
            if (TMGContext.this.mTmgDelegate != null) {
                TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_ENTER_ROOM, TMGCallbackHelper.GetCallBackIntent(i, str));
            }
        }

        @Override // com.tencent.TMG.TMGRoom.EventListener
        public void onExitRoomComplete() {
            TMGContext.this.mAudioCtrl.StopTrackingVolume();
            TMGContext.this.mLstUinSpeaking.clear();
            if (TMGContext.this.mTmgDelegate != null) {
                TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_EXIT_ROOM, TMGCallbackHelper.GetCallBackIntent(0, ""));
            }
        }

        @Override // com.tencent.TMG.TMGRoom.EventListener
        public void onRoomDisconnect(int i, String str) {
            if (TMGContext.this.mTmgDelegate != null) {
                TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_ROOM_DISCONNECT, TMGCallbackHelper.GetCallBackIntent(i, str));
            }
        }

        @Override // com.tencent.TMG.TMGRoom.EventListener
        public void onEndpointsUpdateInfo(int i, String[] strArr) {
            if (TMGContext.this.mTmgDelegate != null) {
                int i2 = 1;
                if (i != 1) {
                    i2 = 2;
                    if (i != 2) {
                        if (i != 5) {
                            i2 = 6;
                            if (i != 6) {
                                i2 = 0;
                            }
                        } else {
                            i2 = 5;
                        }
                    }
                }
                for (String str : strArr) {
                    if (i2 == 5) {
                        if (!TMGContext.this.mLstUinSpeaking.contains(str)) {
                            TMGContext.this.mLstUinSpeaking.add(str);
                        }
                    } else if (TMGContext.this.mLstUinSpeaking.contains(str)) {
                        TMGContext.this.mLstUinSpeaking.remove(str);
                    }
                }
                if (i2 != 0) {
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVNET_TYPE_USER_UPDATE, TMGCallbackHelper.GetUpdateInfoIntent(i2, strArr));
                }
            }
        }

        @Override // com.tencent.TMG.TMGRoom.EventListener
        public void onRoomEvent(int i, int i2, Object obj) {
            if (i == 400) {
                int intValue = ((Integer) obj).intValue();
                QLog.m600e(TMGContext.TAG, "AVRoomMultiDelegateJni TMGContext.onRoomEvent type = " + i + ", subtype = " + i2 + ", nNewState=" + intValue);
                if (TMGContext.this.mTmgDelegate != null) {
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_CHANGE_ROOM_TYPE, TMGCallbackHelper.GetRoomTypeChangedEventIntent(0, i2, intValue, ""));
                    return;
                }
                return;
            }
            if (i == 401) {
                String str = (String) obj;
                QLog.m600e(TMGContext.TAG, "AVRoomMultiDelegateJni TMGContext.onRoomEvent type = " + i + ", ret=" + str);
                try {
                    int i3 = new JSONObject(str).getInt("result");
                    QLog.m598d(TMGContext.TAG, "AVRoomMultiDelegateJni TMGContext.onRoomEvent , nRoomShareCode =" + String.valueOf(i3));
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_ROOM_SHARING_START, TMGCallbackHelper.GetRoomShareEventIntent(i3));
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            if (i == 402) {
                String str2 = (String) obj;
                QLog.m600e(TMGContext.TAG, "AVRoomMultiDelegateJni TMGContext.onRoomEvent type = " + i + ", ret=" + str2);
                try {
                    int i4 = new JSONObject(str2).getInt("result");
                    QLog.m598d(TMGContext.TAG, "AVRoomMultiDelegateJni TMGContext.onRoomEvent , nRoomShareCode =" + String.valueOf(i4));
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_ROOM_SHARING_STOP, TMGCallbackHelper.GetRoomShareEventIntent(i4));
                    return;
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    return;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    return;
                }
            }
            if (i == 450) {
                try {
                    JSONObject jSONObject = new JSONObject((String) obj);
                    int i5 = jSONObject.getInt("AllUser");
                    int i6 = jSONObject.getInt("AccUser");
                    int i7 = jSONObject.getInt("ProxyUser");
                    QLog.m598d(TMGContext.TAG, "AVRoomMultiDelegateJni TMGContext.onRoomEvent , AllUser=" + String.valueOf(i5) + ", AccUser=" + String.valueOf(i6) + ", ProxyUser=" + String.valueOf(i7));
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_NUMBER_OF_USERS_UPDATE, TMGCallbackHelper.GetNumeberOfUsersEventIntent(i5, i6, i7));
                    return;
                } catch (JSONException e5) {
                    e5.printStackTrace();
                    return;
                } catch (Exception e6) {
                    e6.printStackTrace();
                    return;
                }
            }
            if (i == 451) {
                try {
                    int i8 = new JSONObject((String) obj).getInt("AudioStreams");
                    QLog.m598d(TMGContext.TAG, "AVRoomMultiDelegateJni TMGContext.onRoomEvent , AllUser=" + String.valueOf(i8));
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_NUMBER_OF_AUDIOSTREAMS_UPDATE, TMGCallbackHelper.GetAudioStreamsEventIntent(i8));
                    return;
                } catch (JSONException e7) {
                    e7.printStackTrace();
                    return;
                } catch (Exception e8) {
                    e8.printStackTrace();
                    return;
                }
            }
            if (i == 500) {
                try {
                    JSONObject jSONObject2 = new JSONObject((String) obj);
                    int i9 = jSONObject2.getInt("Weight");
                    double d = jSONObject2.getDouble("Loss");
                    int i10 = jSONObject2.getInt("Delay");
                    QLog.m598d(TMGContext.TAG, "AVRoomMultiDelegateJni TMGContext.onRoomEvent , nWeight=" + String.valueOf(i9) + ", Loss=" + String.valueOf(d) + ", Delay=" + String.valueOf(i10));
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_CHANGE_ROOM_QUALITY, TMGCallbackHelper.GetBadQualityEventIntent(i9, d, i10));
                    return;
                } catch (JSONException e9) {
                    e9.printStackTrace();
                    return;
                } catch (Exception e10) {
                    e10.printStackTrace();
                    return;
                }
            }
            if (i == 1000) {
                TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_RECONNECT_START, TMGCallbackHelper.GetCallBackIntent(i2, ""));
                return;
            }
            if (i == 1001) {
                TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_RECONNECT_SUCCESS, TMGCallbackHelper.GetCallBackIntent(i2, ""));
                return;
            }
            if (i == 1002) {
                try {
                    JSONObject jSONObject3 = new JSONObject(((String) obj).replace(" ", "_"));
                    int i11 = jSONObject3.getInt("result");
                    String string = jSONObject3.getString("error_info");
                    Intent intent = new Intent();
                    intent.putExtra("result", i11);
                    intent.putExtra("error_info", string);
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_SWITCH_ROOM, intent);
                    return;
                } catch (JSONException e11) {
                    e11.printStackTrace();
                    return;
                } catch (Exception e12) {
                    e12.printStackTrace();
                    return;
                }
            }
            if (i == 6000) {
                try {
                    JSONObject jSONObject4 = new JSONObject((String) obj);
                    Intent intent2 = new Intent();
                    String string2 = jSONObject4.getString("SenderID");
                    String string3 = jSONObject4.getString("ReceiverID");
                    String string4 = jSONObject4.getString("UUID");
                    boolean z = jSONObject4.getBoolean("OperateValue");
                    int i12 = jSONObject4.getInt("Result");
                    intent2.putExtra("SenderID", string2);
                    intent2.putExtra("ReceiverID", string3);
                    intent2.putExtra("UUID", string4);
                    intent2.putExtra("OperateValue", z);
                    intent2.putExtra("Result", i12);
                    intent2.putExtra("OperateType", i2);
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVNET_TYPE_ROOM_MANAGEMENT_OPERATOR, intent2);
                    return;
                } catch (JSONException e13) {
                    e13.printStackTrace();
                    return;
                } catch (Exception e14) {
                    e14.printStackTrace();
                    return;
                }
            }
            if (i == 403) {
                try {
                    JSONObject jSONObject5 = new JSONObject((String) obj);
                    String string5 = jSONObject5.getString("error_info");
                    int i13 = jSONObject5.getInt("result");
                    Intent intent3 = new Intent();
                    intent3.putExtra("result", i13);
                    intent3.putExtra("error_info", string5);
                    intent3.putExtra("sub_event", i2);
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_SERVER_AUDIO_ROUTE_EVENT, intent3);
                    return;
                } catch (JSONException e15) {
                    e15.printStackTrace();
                    return;
                } catch (Exception e16) {
                    e16.printStackTrace();
                    return;
                }
            }
            if (i == 404) {
                try {
                    JSONObject jSONObject6 = new JSONObject((String) obj);
                    String string6 = jSONObject6.getString(FirebaseAnalytics.Param.CONTENT);
                    String string7 = jSONObject6.getString("senderid");
                    Intent intent4 = new Intent();
                    intent4.putExtra(FirebaseAnalytics.Param.CONTENT, string6);
                    intent4.putExtra("senderid", string7);
                    intent4.putExtra("sub_event", i2);
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_CUSTOMDATA_UPDATE, intent4);
                    return;
                } catch (JSONException e17) {
                    e17.printStackTrace();
                    return;
                } catch (Exception e18) {
                    e18.printStackTrace();
                    return;
                }
            }
            if (i == 407) {
                try {
                    JSONObject jSONObject7 = new JSONObject((String) obj);
                    int i14 = jSONObject7.getInt("result");
                    Intent intent5 = new Intent();
                    intent5.putExtra("sub_event", i2);
                    intent5.putExtra("result", i14);
                    if (i2 == 1) {
                        intent5.putExtra("slice_type", jSONObject7.getInt("slice_type"));
                        intent5.putExtra(FirebaseAnalytics.Param.INDEX, jSONObject7.getInt(FirebaseAnalytics.Param.INDEX));
                        intent5.putExtra("voice_text_str", jSONObject7.getString("voice_text_str"));
                    } else {
                        intent5.putExtra("error_info", jSONObject7.getString("error_info"));
                    }
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_REALTIME_ASR, intent5);
                    return;
                } catch (JSONException e19) {
                    e19.printStackTrace();
                    return;
                } catch (Exception e20) {
                    e20.printStackTrace();
                    return;
                }
            }
            if (i == 406) {
                try {
                    JSONObject jSONObject8 = new JSONObject((String) obj);
                    String string8 = jSONObject8.getString("error_info");
                    int i15 = jSONObject8.getInt("ErrorCode");
                    String string9 = jSONObject8.getString("Accompanier_Openid");
                    Intent intent6 = new Intent();
                    intent6.putExtra("error_info", string8);
                    intent6.putExtra("result", i15);
                    intent6.putExtra("Accompanier_Openid", string9);
                    intent6.putExtra("sub_type", i2);
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_CHORUS_EVENT, intent6);
                    return;
                } catch (JSONException e21) {
                    e21.printStackTrace();
                    return;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    return;
                }
            }
            if (i == 452) {
                try {
                    JSONObject jSONObject9 = new JSONObject((String) obj);
                    String string10 = jSONObject9.getString("error_info");
                    int i16 = jSONObject9.getInt("ErrorCode");
                    int i17 = jSONObject9.getInt("teamid");
                    Intent intent7 = new Intent();
                    intent7.putExtra("error_info", string10);
                    intent7.putExtra("result", i16);
                    intent7.putExtra("teamid", i17);
                    intent7.putExtra("sub_type", i2);
                    TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_CHANGETEAMID, intent7);
                } catch (JSONException e23) {
                    e23.printStackTrace();
                } catch (Exception e24) {
                    e24.printStackTrace();
                }
            }
        }

        @Override // com.tencent.TMG.TMGRoom.EventListener
        public void onDisableAudioIssue() {
            if (TMGContext.this.mTmgDelegate != null) {
                Intent intent = new Intent();
                intent.putExtra("reason", 65536);
                intent.putExtra("error_info", "");
                TMGContext.this.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_AUDIO_DATA_EMPTY, intent);
            }
        }
    };

    private native int nativeCheckMic();

    private native int nativeEnterRoom(String str, int i, byte[] bArr, TMGRoom.EventListener eventListener);

    private native int nativeExitRoom();

    private native String nativeGetParam(String str);

    private native String nativeGetSDKVersion();

    private native int nativePause();

    private native int nativePoll();

    private native int nativeResume();

    private native int nativeSetAppVersion(String str);

    private native int nativeSetLogLevel(int i, int i2);

    private native int nativeSetLogPath(String str);

    private native int nativeSetParam(String str, String str2);

    private native void nativeSetRegion(String str);

    private native int nativeStart(String str, String str2);

    private native int nativeStartAudioEngine(boolean z);

    private native int nativeStartRealTimeASR(String str);

    private native int nativeStop();

    private native int nativeStopAudioEngine();

    private native int nativeStopRealTimeASR();

    private native boolean nativeisRoomEntered();

    public native int nativeSetRangeAudioMode(int i);

    public native int nativeSetRangeAudioTeamID(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public TMGContext(Context context) {
        this.gameWrapper = null;
        this.mContext = null;
        this.sdk_match = true;
        this.mContext = context;
        TMGAudioRecordCtrl.GetInstance().setTMGContext(this);
        OpensdkGameWrapper opensdkGameWrapper = new OpensdkGameWrapper(this.mContext);
        this.gameWrapper = opensdkGameWrapper;
        if (opensdkGameWrapper.initOpensdk() == 7015) {
            this.sdk_match = false;
        }
    }

    @Override // com.tencent.TMG.ITMGContext
    public int Poll() {
        return nativePoll();
    }

    @Override // com.tencent.TMG.ITMGContext
    public ITMGContext.ITMG_RECORD_PERMISSION CheckMicPermission() {
        if (this.gameWrapper != null) {
            return ITMGContext.ITMG_RECORD_PERMISSION.values()[this.gameWrapper.CheckMicPermission()];
        }
        QLog.m600e(TAG, "gameWrapper is not install");
        return ITMGContext.ITMG_RECORD_PERMISSION.ITMG_PERMISSION_ERROR;
    }

    @Override // com.tencent.TMG.ITMGContext
    public ITMGContext.ITMG_CHECK_MIC_STATUS CheckMic() {
        int nativeCheckMic = nativeCheckMic();
        QLog.m598d(TAG, "CheckMic = " + nativeCheckMic);
        if (nativeCheckMic >= 0 && nativeCheckMic < ITMGContext.ITMG_CHECK_MIC_STATUS.values().length) {
            return ITMGContext.ITMG_CHECK_MIC_STATUS.values()[nativeCheckMic];
        }
        return ITMGContext.ITMG_CHECK_MIC_STATUS.ITMG_CHECK_MIC_STATUS_ERROR_FUNC;
    }

    @Override // com.tencent.TMG.ITMGContext
    public int Pause() {
        return nativePause();
    }

    @Override // com.tencent.TMG.ITMGContext
    public int Resume() {
        return nativeResume();
    }

    @Override // com.tencent.TMG.ITMGContext
    public int SetLogLevel(int i, int i2) {
        return nativeSetLogLevel(i, i2);
    }

    @Override // com.tencent.TMG.ITMGContext
    public int SetLogPath(String str) {
        return nativeSetLogPath(str);
    }

    @Override // com.tencent.TMG.ITMGContext
    public int Uninit() {
        this.mAudioCtrl.StopTrackingVolume();
        this.mLstUinSpeaking.clear();
        nativeStop();
        GMEAudioInterrupt.uninitInterruptHandler();
        nativePoll();
        return 0;
    }

    @Override // com.tencent.TMG.ITMGContext
    public int SetTMGDelegate(ITMGContext.ITMGDelegate iTMGDelegate) {
        QLog.m602i("API", String.format("SetTMGDelegate.delegate=%s", iTMGDelegate));
        this.mTmgDelegate = iTMGDelegate;
        return 0;
    }

    @Override // com.tencent.TMG.ITMGContext
    public void SetRegion(String str) {
        nativeSetRegion(str);
    }

    @Override // com.tencent.TMG.ITMGContext
    public int Init(String str, String str2) {
        this.mPTT.SetAppInfo(str, str2);
        GMEAudioInterrupt.initInterruptHandler();
        return this.sdk_match ? nativeStart(str, str2) : AVError.AV_ERR_SDK_NOT_FULL_UPDATE;
    }

    @Override // com.tencent.TMG.ITMGContext
    public String GetSDKVersion() {
        return nativeGetSDKVersion();
    }

    @Override // com.tencent.TMG.ITMGContext
    public int SetRecvMixStreamCount(int i) {
        return nativeSetParam("RecvMixStreamCount", String.valueOf(i));
    }

    @Override // com.tencent.TMG.ITMGContext
    public int SetAdvanceParams(String str, String str2) {
        return nativeSetParam(str, str2);
    }

    @Override // com.tencent.TMG.ITMGContext
    public String GetAdvanceParams(String str) {
        return nativeGetParam(str);
    }

    @Override // com.tencent.TMG.ITMGContext
    public void SetAppVersion(String str) {
        nativeSetAppVersion(str);
    }

    @Override // com.tencent.TMG.ITMGContext
    public boolean IsRoomEntered() {
        return nativeisRoomEntered();
    }

    @Override // com.tencent.TMG.ITMGContext
    public int EnterRoom(String str, int i, byte[] bArr) {
        return nativeEnterRoom(str, i, bArr, this.mRoomEventListener);
    }

    @Override // com.tencent.TMG.ITMGContext
    public int StartAudioEngine(boolean z) {
        return nativeStartAudioEngine(z);
    }

    @Override // com.tencent.TMG.ITMGContext
    public int StopAudioEngine() {
        return nativeStopAudioEngine();
    }

    @Override // com.tencent.TMG.ITMGContext
    public int SetRangeAudioMode(ITMGContext.ITMG_RANGE_AUDIO_MODE itmg_range_audio_mode) {
        return nativeSetRangeAudioMode(itmg_range_audio_mode.ordinal());
    }

    @Override // com.tencent.TMG.ITMGContext
    public int SetRangeAudioTeamID(int i) {
        return nativeSetRangeAudioTeamID(i);
    }

    @Override // com.tencent.TMG.ITMGContext
    public int ExitRoom() {
        this.mAudioCtrl.StopTrackingVolume();
        this.mLstUinSpeaking.clear();
        return exitRoom();
    }

    @Override // com.tencent.TMG.ITMGContext
    public int StartRealTimeASR() {
        return nativeStartRealTimeASR("cmn-Hans-CN");
    }

    @Override // com.tencent.TMG.ITMGContext
    public int StartRealTimeASR(String str) {
        return nativeStartRealTimeASR(str);
    }

    @Override // com.tencent.TMG.ITMGContext
    public int StopRealTimeASR() {
        return nativeStopRealTimeASR();
    }

    @Override // com.tencent.TMG.ITMGContext
    public ITMGRoom GetRoom() {
        return this.mRoom;
    }

    @Override // com.tencent.TMG.ITMGContext
    public ITMGRoomManager GetRoomManager() {
        return this.mRoomManager;
    }

    @Override // com.tencent.TMG.ITMGContext
    public ITMGAudioCtrl GetAudioCtrl() {
        return this.mAudioCtrl;
    }

    @Override // com.tencent.TMG.ITMGContext
    public ITMGAudioEffectCtrl GetAudioEffectCtrl() {
        return this.mEffectCtrl;
    }

    @Override // com.tencent.TMG.ITMGContext
    public ITMGPTT GetPTT() {
        return this.mPTT;
    }

    public int exitRoom() {
        return nativeExitRoom();
    }
}
