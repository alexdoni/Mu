package com.tencent.p014av.ptt;

import android.os.Environment;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.tencent.p014av.utils.QLog;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class FileManager {
    private static FileManager s_instance;
    private String fileNamePrx = "silk_";
    private String fullDirPath = null;
    private String silkFolderName = "tsilkfile";
    private String TAG = "FileManager";
    private ArrayList<String> fileNameList = new ArrayList<>();
    private int MAX_LENGTH = 5;

    public static FileManager getInstance() {
        if (s_instance == null) {
            FileManager fileManager = new FileManager();
            s_instance = fileManager;
            fileManager.createSilkTempDir();
            s_instance.deleteAllFile();
        }
        return s_instance;
    }

    private boolean createSilkTempDir() {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + RemoteSettings.FORWARD_SLASH_STRING + this.silkFolderName);
            if (file.exists()) {
                this.fullDirPath = file.getAbsolutePath();
                return true;
            }
            file.mkdir();
            this.fullDirPath = file.getAbsolutePath();
            return true;
        } catch (Exception e) {
            QLog.m602i(this.TAG, "create temp file error : e = " + e);
            return false;
        }
    }

    public String genSilkFileName() {
        if (this.fullDirPath == null && !createSilkTempDir()) {
            QLog.m602i(this.TAG, "can not create silk temp dir!");
            return null;
        }
        String str = this.fullDirPath + RemoteSettings.FORWARD_SLASH_STRING + this.fileNamePrx + System.currentTimeMillis() + ".silk";
        this.fileNameList.add(str);
        return str;
    }

    public void deleteFile() {
        if (this.fileNameList.size() > this.MAX_LENGTH) {
            for (int i = 0; i < this.fileNameList.size(); i++) {
                String str = this.fileNameList.get(i);
                try {
                    File file = new File(str);
                    if (file.exists()) {
                        file.delete();
                    }
                } catch (Exception e) {
                    QLog.m602i(this.TAG, "delete file failed! file name = " + str + "exception = " + e);
                }
            }
            return;
        }
        if (this.fileNameList.size() > 0) {
            String str2 = this.fileNameList.get(0);
            this.fileNameList.remove(0);
            try {
                File file2 = new File(str2);
                if (file2.exists()) {
                    file2.delete();
                }
            } catch (Exception e2) {
                QLog.m602i(this.TAG, "delete file failed! file name = " + str2 + "exception = " + e2);
            }
        }
    }

    public boolean deleteAllFile() {
        if (this.fullDirPath == null) {
            QLog.m602i(this.TAG, "Target dir is null!");
            return true;
        }
        try {
            File[] listFiles = new File(this.fullDirPath).listFiles();
            if (listFiles != null && listFiles.length != 0) {
                QLog.m602i(this.TAG, "begin delete " + listFiles.length + " files");
                for (int i = 0; i < listFiles.length; i++) {
                    listFiles[i].delete();
                }
                QLog.m602i(this.TAG, "end delete files");
            }
            return true;
        } catch (Exception unused) {
            QLog.m602i(this.TAG, "delete all file failed!");
            return false;
        }
    }
}
