package com.tencent.p014av.utils;

import com.tencent.p014av.sdk.HttpParam;
import com.tencent.p014av.utils.FileTransferUtils;

/* loaded from: classes3.dex */
public class S3FileTransfer {
    public static final String TAG = "S3FileTransfer";

    public static void uploadFile(final String str, final String str2, final HttpParam httpParam, final Object obj, final FileTransferUtils.UploadFileListener uploadFileListener) {
        new Thread(new Runnable() { // from class: com.tencent.av.utils.S3FileTransfer.1
            /* JADX WARN: Removed duplicated region for block: B:10:0x015f  */
            /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:14:0x0147 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:19:0x0136 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:7:0x0158  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 357
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.utils.S3FileTransfer.RunnableC25381.run():void");
            }
        }).start();
    }

    public static void downloadFile(final String str, final String str2, final HttpParam httpParam, final Object obj, final FileTransferUtils.DownloadFileListener downloadFileListener) {
        new Thread(new Runnable() { // from class: com.tencent.av.utils.S3FileTransfer.2
            /* JADX WARN: Removed duplicated region for block: B:10:0x0196  */
            /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:14:0x017e A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:19:0x016d A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:7:0x018f  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 412
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.utils.S3FileTransfer.RunnableC25392.run():void");
            }
        }).start();
    }
}
