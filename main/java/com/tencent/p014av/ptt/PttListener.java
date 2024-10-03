package com.tencent.p014av.ptt;

/* loaded from: classes3.dex */
public interface PttListener {

    /* loaded from: classes3.dex */
    public interface DownloadFileListener {
        void onCompleted(int i, String str, String str2, int i2, int i3);
    }

    /* loaded from: classes3.dex */
    public interface PlayFileListener {
        void onCompleted(int i, String str);
    }

    /* loaded from: classes3.dex */
    public interface RecordFileListener {
        void onCompleted(int i, String str);
    }

    /* loaded from: classes3.dex */
    public interface StreamingRecognitionListener {
        void onCompleted(int i, String str, String str2, String str3, int i2);
    }

    /* loaded from: classes3.dex */
    public interface UploadFileListener {
        void onCompleted(int i, String str, String str2, int i2, int i3);
    }

    /* loaded from: classes3.dex */
    public interface Voice2TextListener {
        void onCompleted(int i, String str, String str2, int i2);
    }
}
