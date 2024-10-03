package com.tencent.TMG;

import com.tencent.TMG.ITMGContext;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public abstract class ITMGRoom {
    public abstract int ChangeRoomType(int i);

    public abstract ITMGContext.ITMG_SERVER_AUDIO_ROUTE_RECV_TYPE GetCurrentRecvAudioRoute(ArrayList<String> arrayList);

    public abstract ITMGContext.ITMG_SERVER_AUDIO_ROUTE_SEND_TYPE GetCurrentSendAudioRoute(ArrayList<String> arrayList);

    public abstract String GetQualityTips();

    public abstract String GetRoomID();

    public abstract int GetRoomType();

    public abstract int SendCustomData(byte[] bArr, int i);

    public abstract int SetServerAudioRoute(ITMGContext.ITMG_SERVER_AUDIO_ROUTE_SEND_TYPE itmg_server_audio_route_send_type, ArrayList<String> arrayList, ITMGContext.ITMG_SERVER_AUDIO_ROUTE_RECV_TYPE itmg_server_audio_route_recv_type, ArrayList<String> arrayList2);

    public abstract int StartChorusVocalAccompaniment(String str);

    public abstract int StartChorusWithOpenID(String str);

    public abstract int StartRoomSharing(String str, String str2, byte[] bArr);

    public abstract int StopChorus();

    public abstract int StopChorusVocalAccompaniment();

    public abstract int StopRoomSharing();

    public abstract int StopSendCustomData();

    public abstract int SwitchRoom(String str, byte[] bArr);

    public abstract int UpdateAudioRecvRange(int i);

    public abstract int UpdateSelfPosition(int[] iArr, float[] fArr, float[] fArr2, float[] fArr3);
}
