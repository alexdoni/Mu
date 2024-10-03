package com.mu.update;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import com.facebook.internal.ServerProtocol;
import com.google.common.net.HttpHeaders;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.mu.update.ConfigInfo;
import com.mu.utility.Encrypt.Encrypt;
import com.mu.utility.FileUtils;
import com.mu.utility.MuDebug;
import com.tencent.p014av.sdk.AVError;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class VersionManager {
    private Handler MainHandler;
    public File debugFile;
    DownloadVersionThread downloadVersionThread;
    String errorMsg;
    Context mContext;
    public APKVersionBase netApkVersion = new APKVersionBase();
    public File versionFile;

    /* loaded from: classes2.dex */
    public class APKVersionBase {
        public boolean canForceUpdate;
        public String newApk;
        public String newVersion;
        public int packageCount;
        public String resUrl;

        public APKVersionBase() {
        }
    }

    public VersionManager(Context context, Handler handler) {
        this.mContext = context;
        this.MainHandler = handler;
    }

    public void LoadVersion() {
        HotUpdateMgr.getInstance().mUpdateState = ConfigInfo.UpdateState.LoadVersion;
        LoadAPKVersion();
        LoadVersionFilePath();
        CloseVersionThread();
        DownloadVersionThread downloadVersionThread = new DownloadVersionThread();
        this.downloadVersionThread = downloadVersionThread;
        downloadVersionThread.start();
    }

    public void CloseVersionThread() {
        DownloadVersionThread downloadVersionThread = this.downloadVersionThread;
        if (downloadVersionThread != null) {
            downloadVersionThread.interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class DownloadVersionThread extends Thread {
        private DownloadVersionThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                VersionManager.this.LoadClientDebug();
                VersionManager.this.LoadClientVersion();
                if (VersionManager.this.LoadServerVersion()) {
                    VersionManager.this.MainHandler.sendEmptyMessage(2);
                    sleep(30L);
                }
            } catch (InterruptedException unused) {
            }
        }
    }

    void LoadClientDebug() {
        File file = this.debugFile;
        if (file.exists()) {
            BufferedReader bufferedReader = null;
            try {
                try {
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                        try {
                            String readLine = bufferedReader2.readLine();
                            ConfigInfo.VersionURL = readLine;
                            ConfigInfo.isDebugState = true;
                            MuDebug.Log("HotUpdateMgr.getInstance().CoverInstallation ...." + readLine);
                            bufferedReader2.close();
                            bufferedReader2.close();
                        } catch (Exception e) {
                            e = e;
                            bufferedReader = bufferedReader2;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    void LoadClientVersion() {
        File file = this.versionFile;
        if (file.exists()) {
            BufferedReader bufferedReader = null;
            try {
                try {
                    try {
                        HotUpdateMgr.getInstance().firstGame = false;
                        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                        try {
                            JSONObject jSONObject = new JSONObject(FileUtils.LoadJson(bufferedReader2));
                            String string = jSONObject.getString("apkVersion");
                            String string2 = jSONObject.getString("resVersion");
                            ConfigInfo.CurAPKVersion = string2;
                            if (string2.isEmpty()) {
                                HotUpdateMgr.getInstance().CoverInstallation = true;
                            } else if (!string.equals(ConfigInfo.PackageVersion)) {
                                HotUpdateMgr.getInstance().CoverInstallation = true;
                            }
                            checkCoverInstallation();
                            MuDebug.Log("HotUpdateMgr.getInstance().CoverInstallation ...." + HotUpdateMgr.getInstance().CoverInstallation);
                            if (HotUpdateMgr.getInstance().CoverInstallation) {
                                SetFirstData();
                            }
                            bufferedReader2.close();
                            bufferedReader2.close();
                        } catch (Exception e) {
                            e = e;
                            bufferedReader = bufferedReader2;
                            e.printStackTrace();
                            SetFirstData();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
                return;
            } catch (IOException e4) {
                e4.printStackTrace();
                return;
            }
        }
        SetFirstData();
    }

    public void SetFirstData() {
        ConfigInfo.CurAPKVersion = ConfigInfo.PackageVersion;
        HotUpdateMgr.getInstance().firstGame = true;
        CreateFoler(Environment.getExternalStorageDirectory().toString() + "/Android/data/" + this.mContext.getPackageName());
    }

    public void CreateFoler(String str) {
        new File(str + "/version/").mkdirs();
    }

    boolean LoadServerVersion() {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        BufferedReader bufferedReader = null;
        JSONObject jSONObject = null;
        bufferedReader = null;
        bufferedReader = null;
        bufferedReader = null;
        bufferedReader = null;
        bufferedReader = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(ConfigInfo.VersionURL).openConnection();
            try {
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Charset", "UTF-8");
                httpURLConnection.setConnectTimeout(AVError.AV_ERR_IMSDK_TIMEOUT);
                httpURLConnection.setReadTimeout(AVError.AV_ERR_IMSDK_TIMEOUT);
                httpURLConnection.setUseCaches(true);
                httpURLConnection.setRequestProperty(HttpHeaders.CONNECTION, HttpHeaders.KEEP_ALIVE);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() != 200) {
                    httpURLConnection.disconnect();
                    this.MainHandler.sendEmptyMessage(3);
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }
                inputStream = httpURLConnection.getInputStream();
                try {
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
                        try {
                            String DeCode = Encrypt.DeCode(FileUtils.LoadJson(bufferedReader2));
                            ConfigInfo.versionConfig = DeCode;
                            JSONObject jSONObject2 = new JSONObject(DeCode);
                            ConfigInfo.ServerConfig = jSONObject2.toString();
                            ConfigInfo.openVerify = jSONObject2.getString("openVerify").equals("1");
                            this.netApkVersion.resUrl = jSONObject2.getString("resUrl") + RemoteSettings.FORWARD_SLASH_STRING;
                            boolean z = ConfigInfo.isDebugState;
                            this.netApkVersion.newApk = jSONObject2.getString("newApk");
                            JSONArray jSONArray = jSONObject2.getJSONArray("VersionList");
                            HashMap hashMap = new HashMap();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                                hashMap.put(jSONObject3.getString(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION), jSONObject3.toString());
                            }
                            String str = ConfigInfo.PackageVersion;
                            if (hashMap.containsKey(str)) {
                                jSONObject = new JSONObject((String) hashMap.get(str));
                            } else if (hashMap.containsKey(ConfigInfo.CurAPKVersion)) {
                                jSONObject = new JSONObject((String) hashMap.get(ConfigInfo.CurAPKVersion));
                            } else if (hashMap.containsKey("default")) {
                                jSONObject = new JSONObject((String) hashMap.get("default"));
                            } else {
                                MuDebug.Log("错误未找到对应配置信息");
                            }
                            bufferedReader2.close();
                            inputStream.close();
                            httpURLConnection.disconnect();
                            this.netApkVersion.newVersion = jSONObject.getString("resVersion");
                            this.netApkVersion.canForceUpdate = jSONObject.getInt("CanForceUpdate") == 1;
                            try {
                                bufferedReader2.close();
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            return true;
                        } catch (Exception e3) {
                            e = e3;
                            bufferedReader = bufferedReader2;
                            this.errorMsg = e.getMessage();
                            this.MainHandler.sendEmptyMessage(3);
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                    return false;
                                }
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                    throw th;
                                }
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e6) {
                    e = e6;
                }
            } catch (Exception e7) {
                e = e7;
                inputStream = null;
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
            }
        } catch (Exception e8) {
            e = e8;
            httpURLConnection = null;
            inputStream = null;
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            inputStream = null;
        }
    }

    public String GetApkVersion() {
        return ConfigInfo.CurAPKVersion;
    }

    public int ParseApkVersion() {
        return ParseVersion(ConfigInfo.PackageVersion, ConfigInfo.CurAPKVersion);
    }

    public void checkCoverInstallation() {
        if (ParseApkVersion() == 1) {
            HotUpdateMgr.getInstance().CoverInstallation = true;
        }
    }

    public void LoadAPKVersion() {
        try {
            ConfigInfo.CurAPKVersion = this.mContext.getPackageManager().getPackageInfo(ConfigInfo.ApkName, 0).versionName;
            String[] split = ConfigInfo.CurAPKVersion.split("\\.");
            ConfigInfo.PackageVersion = split[0] + "." + split[1] + "." + split[2];
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    void LoadVersionFilePath() {
        String file = Environment.getExternalStorageDirectory().toString();
        String packageName = this.mContext.getPackageName();
        File file2 = new File(file + "/Android/data/" + packageName + "/version/");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        MuDebug.Log("fileRootPathFile.exists() ..." + file2.exists());
        this.versionFile = new File(file2, "VersionFile.json");
        File file3 = new File(file + "/Android/data/" + packageName + "/files/");
        if (!file3.exists()) {
            file3.mkdirs();
        }
        this.debugFile = new File(file3, "version/debugPath.txt");
        ConfigInfo.VersionFilePath = this.versionFile.getPath();
    }

    public int ApkParseVersion(String str, String str2) {
        if (str == null || str.equals("")) {
            str = "1.1.0";
        }
        if (str2 == null || str2.equals("")) {
            str2 = "1.1.0";
        }
        List<String> versionList = getVersionList(str);
        List<String> versionList2 = getVersionList(str2);
        int parseInt = Integer.parseInt(versionList.get(1));
        int parseInt2 = Integer.parseInt(versionList2.get(1));
        if (parseInt == parseInt2) {
            return 0;
        }
        return parseInt < parseInt2 ? -1 : 1;
    }

    public int ParseVersion(String str, String str2) {
        if (str == null || str.equals("")) {
            str = "1.1.0";
        }
        if (str2 == null || str2.equals("")) {
            str2 = "1.1.0";
        }
        List<String> versionList = getVersionList(str);
        List<String> versionList2 = getVersionList(str2);
        if (versionList.size() > versionList2.size()) {
            int size = versionList.size() - versionList2.size();
            for (int i = 0; i < size; i++) {
                versionList2.add("0");
            }
        } else if (versionList.size() < versionList2.size()) {
            int size2 = versionList2.size() - versionList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                versionList.add("0");
            }
        }
        int parseInt = Integer.parseInt(versionList.get(1));
        int parseInt2 = Integer.parseInt(versionList2.get(1));
        if (parseInt == parseInt2) {
            return 0;
        }
        return parseInt < parseInt2 ? -1 : 1;
    }

    List<String> getVersionList(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split("\\.")) {
            arrayList.add(str2);
        }
        return arrayList;
    }

    public void WriteApkVersion() {
        try {
            FileUtils.WriteJson(SetVersionDate(), this.versionFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public String SetVersionDate() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resVersion", ConfigInfo.CurAPKVersion);
            jSONObject.put("apkVersion", ConfigInfo.PackageVersion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
