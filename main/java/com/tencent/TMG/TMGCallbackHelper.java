package com.tencent.TMG;

import android.content.Intent;
import androidx.core.provider.FontsContractCompat;
import java.util.Map;

/* loaded from: classes3.dex */
public class TMGCallbackHelper {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent GetCallBackIntent(int i, String str) {
        Intent intent = new Intent();
        intent.putExtra("result", i);
        intent.putExtra("error_info", str);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent GetUpdateInfoIntent(int i, String[] strArr) {
        Intent intent = new Intent();
        intent.putExtra("event_id", i);
        intent.putExtra("user_list", strArr);
        return intent;
    }

    static Intent GetAudioDeviceIntent(boolean z, int i) {
        Intent intent = new Intent();
        intent.putExtra("audio_state", z);
        intent.putExtra("audio_errcode", i);
        return intent;
    }

    static Intent GetRequestVideoIntent(String[] strArr, int i, String str) {
        Intent intent = new Intent();
        intent.putExtra("result", i);
        intent.putExtra("error_info", str);
        intent.putExtra("user_list", strArr);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent GetPTTRecordIntent(int i, String str) {
        Intent intent = new Intent();
        intent.putExtra("result", i);
        intent.putExtra("file_path", str);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent StreamingRecIntennt(int i, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.putExtra("result", i);
        intent.putExtra("file_path", str);
        intent.putExtra(FontsContractCompat.Columns.FILE_ID, str2);
        intent.putExtra("text", str3);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent GetPTTUploadIntent(int i, String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("result", i);
        intent.putExtra("file_path", str);
        intent.putExtra(FontsContractCompat.Columns.FILE_ID, str2);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent GetSpeechToTextIntent(int i, String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("result", i);
        intent.putExtra(FontsContractCompat.Columns.FILE_ID, str);
        intent.putExtra("text", str2);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent GetAccompanyFinishIntent(int i, boolean z, String str) {
        Intent intent = new Intent();
        intent.putExtra("result", i);
        intent.putExtra("file_path", str);
        intent.putExtra("is_finished", z);
        return intent;
    }

    static Intent GetCameraDeviceIntent(boolean z, int i, int i2) {
        Intent intent = new Intent();
        intent.putExtra("video_state", z);
        intent.putExtra("camera_id", i);
        intent.putExtra("video_errcode", i2);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent GetRoomTypeChangedEventIntent(int i, int i2, int i3, String str) {
        Intent intent = new Intent();
        intent.putExtra("result", i);
        intent.putExtra("sub_event_type", i2);
        intent.putExtra("new_room_type", i3);
        intent.putExtra("error_info", str);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent GetBadQualityEventIntent(int i, double d, int i2) {
        Intent intent = new Intent();
        intent.putExtra("weight", i);
        intent.putExtra("loss", d);
        intent.putExtra("delay", i2);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent GetAudioStreamsEventIntent(int i) {
        Intent intent = new Intent();
        intent.putExtra("AudioStreams", i);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent GetRoomShareEventIntent(int i) {
        Intent intent = new Intent();
        intent.putExtra("result", i);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent GetNumeberOfUsersEventIntent(int i, int i2, int i3) {
        Intent intent = new Intent();
        intent.putExtra("AllUser", i);
        intent.putExtra("AccUser", i2);
        intent.putExtra("ProxyUser", i3);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent GetMapIntent(Map<String, Integer> map) {
        Intent intent = new Intent();
        for (String str : map.keySet()) {
            intent.putExtra(str, map.get(str).intValue());
        }
        return intent;
    }
}
