package com.mu.utility;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.mu.update.ConfigInfo;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class FileUtils {
    private static final int FAILED = 0;
    private static final int SUCCESS = 1;
    private static FileUtils instance;
    private FileOperateCallback callback;
    private Context context;
    private String errorStr;
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.mu.utility.FileUtils.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (FileUtils.this.callback != null) {
                if (message.what == 1) {
                    FileUtils.this.callback.onSuccess();
                }
                if (message.what == 0) {
                    FileUtils.this.callback.onFailed(message.obj.toString());
                }
            }
        }
    };
    private volatile boolean isSuccess;

    /* loaded from: classes2.dex */
    public interface FileOperateCallback {
        void onFailed(String str);

        void onSuccess();
    }

    public static FileUtils getInstance(Context context) {
        if (instance == null) {
            instance = new FileUtils(context);
        }
        return instance;
    }

    private FileUtils(Context context) {
        this.context = context;
    }

    public FileUtils copyAssetsToSD(final String str, final String str2) {
        new Thread(new Runnable() { // from class: com.mu.utility.FileUtils.2
            @Override // java.lang.Runnable
            public void run() {
                FileUtils fileUtils = FileUtils.this;
                fileUtils.copyAssetsToDst(fileUtils.context, str, str2);
                if (FileUtils.this.isSuccess) {
                    FileUtils.this.handler.obtainMessage(1).sendToTarget();
                } else {
                    FileUtils.this.handler.obtainMessage(0, FileUtils.this.errorStr).sendToTarget();
                }
            }
        }).start();
        return this;
    }

    public void setFileOperateCallback(FileOperateCallback fileOperateCallback) {
        this.callback = fileOperateCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copyAssetsToDst(Context context, String str, String str2) {
        try {
            String[] list = context.getAssets().list(str);
            if (list.length > 0) {
                File file = new File(str2);
                if (!file.exists()) {
                    file.mkdirs();
                }
                for (String str3 : list) {
                    if (str.equals("")) {
                        copyAssetsToDst(context, str3, str2 + File.separator + str3);
                    } else {
                        copyAssetsToDst(context, str + File.separator + str3, str2 + File.separator + str3);
                    }
                }
            } else {
                File file2 = new File(str2);
                InputStream open = context.getAssets().open(str);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                open.available();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = open.read(bArr);
                    if (read == -1) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, read);
                    }
                }
                fileOutputStream.flush();
                open.close();
                fileOutputStream.close();
            }
            this.isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            this.errorStr = e.getMessage();
            this.isSuccess = false;
        }
    }

    public static void RecursionDeleteFile(File file, Boolean bool) {
        if (file.isFile()) {
            File file2 = new File(file.getAbsolutePath() + System.currentTimeMillis());
            file.renameTo(file2);
            file2.delete();
            return;
        }
        if (file.isDirectory() && bool.booleanValue()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                File file3 = new File(file.getAbsolutePath() + System.currentTimeMillis());
                file.renameTo(file3);
                file3.delete();
                return;
            }
            for (File file4 : listFiles) {
                RecursionDeleteFile(file4, bool);
            }
            File file5 = new File(file.getAbsolutePath() + System.currentTimeMillis());
            file.renameTo(file5);
            file5.delete();
        }
    }

    public static void WriteJson(String str, String str2) throws IOException, JSONException {
        try {
            File file = new File(str2);
            if (!file.exists()) {
                try {
                    file.getParentFile().mkdirs();
                } catch (Exception unused) {
                }
                file.createNewFile();
                new DataOutputStream(new FileOutputStream(file)).writeBytes(str);
                return;
            }
            new DataOutputStream(new FileOutputStream(file)).writeBytes(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadContent(File file) {
        BufferedReader bufferedReader = null;
        if (!file.exists()) {
            return null;
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            MuDebug.LogError("读取文件异常" + file.getName(), e);
        }
        return LoadJson(bufferedReader);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0023, code lost:
    
        if (r3 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0034, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0031, code lost:
    
        if (r3 == null) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String LoadJson(java.io.BufferedReader r3) {
        /*
            java.lang.String r0 = ""
            if (r3 != 0) goto La
            java.lang.String r3 = "读取JSON错误"
            com.mu.utility.MuDebug.LogError(r3)
            return r0
        La:
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            if (r1 == 0) goto L20
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            r2.<init>()     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            r2.append(r0)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            r2.append(r1)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            goto La
        L20:
            r3.close()     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b
            if (r3 == 0) goto L34
        L25:
            r3.close()     // Catch: java.io.IOException -> L34
            goto L34
        L29:
            r0 = move-exception
            goto L35
        L2b:
            r1 = move-exception
            java.lang.String r2 = "读取文件流异常"
            com.mu.utility.MuDebug.LogError(r2, r1)     // Catch: java.lang.Throwable -> L29
            if (r3 == 0) goto L34
            goto L25
        L34:
            return r0
        L35:
            if (r3 == 0) goto L3a
            r3.close()     // Catch: java.io.IOException -> L3a
        L3a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mu.utility.FileUtils.LoadJson(java.io.BufferedReader):java.lang.String");
    }

    public static String loadContent(InputStream inputStream) throws IOException {
        char[] cArr = new char[1024];
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        while (true) {
            int read = inputStreamReader.read(cArr, 0, 1024);
            if (read >= 0) {
                sb.append(cArr, 0, read);
            } else {
                return sb.toString();
            }
        }
    }

    public static boolean checkApkValid(File file) {
        if (file != null && file.exists()) {
            return true;
        }
        ConfigInfo.mUpdateState = ConfigInfo.UpdateState.apkInstallFailed;
        return false;
    }
}
