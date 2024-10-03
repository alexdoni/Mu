package com.tencent.p014av.utils;

import java.io.File;
import java.util.Map;

/* loaded from: classes3.dex */
public class FileTransferUtils {
    public static final String TAG = "FileTransferUtils";

    /* loaded from: classes3.dex */
    public interface DownloadFileListener {
        void onCompleted(int i, Object obj);
    }

    /* loaded from: classes3.dex */
    public interface UploadFileListener {
        void onCompleted(int i, String str, Object obj);
    }

    public static File openFile(String str) {
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

    public static String getParamString(Map<String, String> map, String str) {
        String str2 = "";
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                str2 = ((str2 + "--" + str + "\r\n") + "Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n") + entry.getValue() + "\r\n";
            }
        }
        return str2;
    }
}
