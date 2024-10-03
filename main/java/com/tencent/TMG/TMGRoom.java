package com.tencent.TMG;

import android.content.Intent;
import com.tencent.TMG.ITMGContext;
import com.tencent.p014av.sdk.AVCallback;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class TMGRoom extends ITMGRoom {
    TMGContext mTmgContext;

    /* loaded from: classes3.dex */
    public interface EventListener {
        void onDisableAudioIssue();

        void onEndpointsUpdateInfo(int i, String[] strArr);

        void onEnterRoomComplete(int i, String str);

        void onExitRoomComplete();

        void onRoomDisconnect(int i, String str);

        void onRoomEvent(int i, int i2, Object obj);
    }

    public native int nativeChangeRoomType(int i, AVCallback aVCallback);

    public native String nativeGetQualityTips();

    public native String nativeGetRoomID();

    public native int nativeGetRoomType();

    public native int nativeGetServerAudioRoute(int[] iArr, String[] strArr, String[] strArr2, int[] iArr2);

    public native int nativeSendCustomData(byte[] bArr, int i);

    public native int nativeSetServerAudioRoute(int i, String[] strArr, int i2, String[] strArr2);

    public native int nativeStartChorusVocalAccompaniment(String str);

    public native int nativeStartChorusWithOpenID(String str);

    public native int nativeStartRoomSharing(String str, String str2, byte[] bArr);

    public native int nativeStopChorus();

    public native int nativeStopChorusVocalAccompaniment();

    public native int nativeStopRoomSharing();

    public native int nativeStopSendCustomData();

    public native int nativeSwitchRoom(String str, byte[] bArr);

    public native int nativeUpdateAudioRecvRange(int i);

    public native int nativeUpdateSelfPosition(int[] iArr, float[] fArr, float[] fArr2, float[] fArr3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public TMGRoom(TMGContext tMGContext) {
        this.mTmgContext = tMGContext;
    }

    @Override // com.tencent.TMG.ITMGRoom
    public String GetQualityTips() {
        return nativeGetQualityTips();
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int ChangeRoomType(final int i) {
        return nativeChangeRoomType(i, new AVCallback() { // from class: com.tencent.TMG.TMGRoom.1
            @Override // com.tencent.p014av.sdk.AVCallback
            public void onComplete(int i2, String str) {
                Intent GetRoomTypeChangedEventIntent = TMGCallbackHelper.GetRoomTypeChangedEventIntent(i2, 4, i, str);
                if (TMGRoom.this.mTmgContext.mTmgDelegate != null) {
                    TMGRoom.this.mTmgContext.mTmgDelegate.OnEvent(ITMGContext.ITMG_MAIN_EVENT_TYPE.ITMG_MAIN_EVENT_TYPE_CHANGE_ROOM_TYPE, GetRoomTypeChangedEventIntent);
                }
            }
        });
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int StartRoomSharing(String str, String str2, byte[] bArr) {
        return nativeStartRoomSharing(str, str2, bArr);
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int SetServerAudioRoute(ITMGContext.ITMG_SERVER_AUDIO_ROUTE_SEND_TYPE itmg_server_audio_route_send_type, ArrayList<String> arrayList, ITMGContext.ITMG_SERVER_AUDIO_ROUTE_RECV_TYPE itmg_server_audio_route_recv_type, ArrayList<String> arrayList2) {
        String[] strArr = new String[arrayList.size()];
        String[] strArr2 = new String[arrayList2.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = arrayList.get(i);
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            strArr2[i2] = arrayList2.get(i2);
        }
        return nativeSetServerAudioRoute(itmg_server_audio_route_send_type.getValue(), strArr, itmg_server_audio_route_recv_type.getValue(), strArr2);
    }

    @Override // com.tencent.TMG.ITMGRoom
    public ITMGContext.ITMG_SERVER_AUDIO_ROUTE_SEND_TYPE GetCurrentSendAudioRoute(ArrayList<String> arrayList) {
        int[] iArr = {1, 1};
        int[] iArr2 = {0, 0};
        String[] strArr = new String[10];
        String[] strArr2 = new String[10];
        if (arrayList == null) {
            return ITMGContext.ITMG_SERVER_AUDIO_ROUTE_SEND_TYPE.AUDIO_ROUTE_SEND_INQUIRE_ERROR;
        }
        if (nativeGetServerAudioRoute(iArr, strArr, strArr2, iArr2) == 0) {
            for (int i = 0; i < iArr2[0] && i < 10; i++) {
                arrayList.add(strArr[i]);
            }
            return ITMGContext.ITMG_SERVER_AUDIO_ROUTE_SEND_TYPE.valueOf(iArr[0]);
        }
        return ITMGContext.ITMG_SERVER_AUDIO_ROUTE_SEND_TYPE.AUDIO_ROUTE_SEND_INQUIRE_ERROR;
    }

    @Override // com.tencent.TMG.ITMGRoom
    public ITMGContext.ITMG_SERVER_AUDIO_ROUTE_RECV_TYPE GetCurrentRecvAudioRoute(ArrayList<String> arrayList) {
        int[] iArr = {1, 1};
        int[] iArr2 = {0, 0};
        String[] strArr = new String[10];
        String[] strArr2 = new String[10];
        if (arrayList == null) {
            return ITMGContext.ITMG_SERVER_AUDIO_ROUTE_RECV_TYPE.AUDIO_ROUTE_RECV_INQUIRE_ERROR;
        }
        if (nativeGetServerAudioRoute(iArr, strArr, strArr2, iArr2) == 0) {
            for (int i = 0; i < iArr2[1] && i < 10; i++) {
                arrayList.add(strArr2[i]);
            }
            return ITMGContext.ITMG_SERVER_AUDIO_ROUTE_RECV_TYPE.valueOf(iArr[1]);
        }
        return ITMGContext.ITMG_SERVER_AUDIO_ROUTE_RECV_TYPE.AUDIO_ROUTE_RECV_INQUIRE_ERROR;
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int SendCustomData(byte[] bArr, int i) {
        return nativeSendCustomData(bArr, i);
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int StopSendCustomData() {
        return nativeStopSendCustomData();
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int StopRoomSharing() {
        return nativeStopRoomSharing();
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int GetRoomType() {
        return nativeGetRoomType();
    }

    @Override // com.tencent.TMG.ITMGRoom
    public String GetRoomID() {
        return nativeGetRoomID();
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int StartChorusWithOpenID(String str) {
        return nativeStartChorusWithOpenID(str);
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int StopChorus() {
        return nativeStopChorus();
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int StartChorusVocalAccompaniment(String str) {
        return nativeStartChorusVocalAccompaniment(str);
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int StopChorusVocalAccompaniment() {
        return nativeStopChorusVocalAccompaniment();
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int UpdateAudioRecvRange(int i) {
        return nativeUpdateAudioRecvRange(i);
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int UpdateSelfPosition(int[] iArr, float[] fArr, float[] fArr2, float[] fArr3) {
        return nativeUpdateSelfPosition(iArr, fArr, fArr2, fArr3);
    }

    @Override // com.tencent.TMG.ITMGRoom
    public int SwitchRoom(String str, byte[] bArr) {
        return nativeSwitchRoom(str, bArr);
    }
}
