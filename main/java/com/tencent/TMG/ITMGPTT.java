package com.tencent.TMG;

/* loaded from: classes3.dex */
public abstract class ITMGPTT {
    public abstract int ApplyPTTAuthbuffer(byte[] bArr);

    public abstract int CancelRecording();

    public abstract int DownloadRecordedFile(String str, String str2);

    public abstract int GetFileSize(String str);

    public abstract int GetMicLevel();

    public abstract int GetMicVolume();

    public abstract int GetSpeakerLevel();

    public abstract int GetSpeakerVolume();

    public abstract int GetVoiceFileDuration(String str);

    public abstract int PauseRecording();

    public abstract int PlayRecordedFile(String str);

    public abstract int PlayRecordedFile(String str, int i);

    public abstract int ResumeRecording();

    public abstract int SetMaxMessageLength(int i);

    public abstract int SetMicVolume(int i);

    public abstract int SetSpeakerVolume(int i);

    public abstract int SpeechToText(String str);

    public abstract int SpeechToText(String str, String str2);

    public abstract int SpeechToText(String str, String str2, String str3);

    public abstract int StartRecording(String str);

    public abstract int StartRecordingWithStreamingRecognition(String str);

    public abstract int StartRecordingWithStreamingRecognition(String str, String str2);

    public abstract int StartRecordingWithStreamingRecognition(String str, String str2, String str3);

    public abstract int StopPlayFile();

    public abstract int StopRecording();

    public abstract int UploadRecordedFile(String str);
}
