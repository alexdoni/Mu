package com.tencent.p014av.ptt;

import com.tencent.p014av.ptt.PttListener;
import com.tencent.p014av.utils.QLog;
import java.io.File;
import java.util.Map;

/* loaded from: classes3.dex */
public class FileTransfer {
    public static final String TAG = "FileTransfer";
    private static FileTransfer s_instance;

    /* loaded from: classes3.dex */
    public interface GetSignListener {
        void onGetSignCompleted(int i, String str, String str2);
    }

    public static FileTransfer getInstance() {
        FileTransfer fileTransfer;
        synchronized (FileTransfer.class) {
            if (s_instance == null) {
                s_instance = new FileTransfer();
            }
            fileTransfer = s_instance;
        }
        return fileTransfer;
    }

    private FileTransfer() {
    }

    public void uploadFile(final String str, final String str2, final String str3, final int i, final PttListener.UploadFileListener uploadFileListener) {
        final long currentTimeMillis = System.currentTimeMillis();
        new Thread(new Runnable() { // from class: com.tencent.av.ptt.FileTransfer.1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:11:0x0280  */
            /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:15:0x026e A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:20:0x0263 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:25:0x0258 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0279  */
            /* JADX WARN: Type inference failed for: r16v0 */
            /* JADX WARN: Type inference failed for: r16v1 */
            /* JADX WARN: Type inference failed for: r16v2 */
            /* JADX WARN: Type inference failed for: r16v3 */
            /* JADX WARN: Type inference failed for: r16v4 */
            /* JADX WARN: Type inference failed for: r16v5 */
            /* JADX WARN: Type inference failed for: r5v10 */
            /* JADX WARN: Type inference failed for: r5v11 */
            /* JADX WARN: Type inference failed for: r5v12 */
            /* JADX WARN: Type inference failed for: r5v13 */
            /* JADX WARN: Type inference failed for: r5v14 */
            /* JADX WARN: Type inference failed for: r5v15 */
            /* JADX WARN: Type inference failed for: r5v17 */
            /* JADX WARN: Type inference failed for: r5v18 */
            /* JADX WARN: Type inference failed for: r5v2 */
            /* JADX WARN: Type inference failed for: r5v21 */
            /* JADX WARN: Type inference failed for: r5v22 */
            /* JADX WARN: Type inference failed for: r5v23 */
            /* JADX WARN: Type inference failed for: r5v24 */
            /* JADX WARN: Type inference failed for: r5v25 */
            /* JADX WARN: Type inference failed for: r5v26 */
            /* JADX WARN: Type inference failed for: r5v3 */
            /* JADX WARN: Type inference failed for: r5v32 */
            /* JADX WARN: Type inference failed for: r5v33, types: [java.io.BufferedReader] */
            /* JADX WARN: Type inference failed for: r5v37 */
            /* JADX WARN: Type inference failed for: r5v38 */
            /* JADX WARN: Type inference failed for: r5v4 */
            /* JADX WARN: Type inference failed for: r5v5 */
            /* JADX WARN: Type inference failed for: r5v6 */
            /* JADX WARN: Type inference failed for: r5v7 */
            /* JADX WARN: Type inference failed for: r5v8 */
            /* JADX WARN: Type inference failed for: r5v9 */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 660
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.ptt.FileTransfer.RunnableC25281.run():void");
            }
        }).start();
    }

    public void downloadFile(final String str, final String str2, final String str3, final PttListener.DownloadFileListener downloadFileListener) {
        final long currentTimeMillis = System.currentTimeMillis();
        new Thread(new Runnable() { // from class: com.tencent.av.ptt.FileTransfer.2
            /* JADX WARN: Removed duplicated region for block: B:10:0x0149  */
            /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:14:0x0138 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:19:0x012e A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:7:0x0142  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 348
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.ptt.FileTransfer.RunnableC25292.run():void");
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File openFile(String str) {
        if (str == null || str.equals("")) {
            QLog.m602i(TAG, "checkFile| filePath == null.");
            return null;
        }
        File file = new File(str);
        if (file.exists()) {
            return file;
        }
        QLog.m602i(TAG, "checkFile| filePath is not exist. path=" + str);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getParamString(Map<String, String> map, String str) {
        String str2 = "";
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                str2 = ((str2 + "--" + str + "\r\n") + "Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n") + entry.getValue() + "\r\n";
            }
        }
        return str2;
    }

    public void applyMessageKey(String str, String str2, String str3, String str4) {
        QLog.m602i(TAG, String.format("applyMessageKey| appid=%s, bucket=%s, region=%s, sign=%s", str, str2, str3, str4));
    }
}
