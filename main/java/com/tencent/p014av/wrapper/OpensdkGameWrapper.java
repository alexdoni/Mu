package com.tencent.p014av.wrapper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.facebook.internal.security.CertificateUtil;
import com.tencent.TMG.ITMGContext;
import com.tencent.p014av.net.NetInfo;
import com.tencent.p014av.ptt.NetworkProvider;
import com.tencent.p014av.ptt.PCMPlayer;
import com.tencent.p014av.ptt.PttError;
import com.tencent.p014av.ptt.PttListener;
import com.tencent.p014av.ptt.Recorder;
import com.tencent.p014av.ptt.TokenFetcher;
import com.tencent.p014av.ptt.TraeJni;
import com.tencent.p014av.sdk.GMELibLoader;
import com.tencent.p014av.sdk.HttpParam;
import com.tencent.p014av.signature.GenerateTestUserSig;
import com.tencent.p014av.utils.Configs;
import com.tencent.p014av.utils.CosFileTransfer;
import com.tencent.p014av.utils.FileTransferUtils;
import com.tencent.p014av.utils.QLog;
import com.tencent.p014av.utils.S3FileTransfer;
import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* loaded from: classes3.dex */
public class OpensdkGameWrapper implements Recorder.OnQQRecorderListener {
    private static final String TAG = "OpensdkGameWrapper";
    private static Context mContext;
    private static OpensdkGameWrapper mSelf;
    private long objPoint = 0;
    private PCMPlayer pcmPlayer;
    private Recorder recorder;

    public static native void OnCaptureData(byte[] bArr, int i, long j);

    public static native void OnCaptureDataOK(int i, long j);

    public static native void nativeHandleResponse(String str, int i, String str2, String str3, long j);

    public static native String nativetcping(String str);

    public native void nativeInitOpensdk(Context context, String str);

    public native void nativePlayRecordedFileCallback(int i, String str, long j);

    @Override // com.tencent.av.ptt.Recorder.OnQQRecorderListener
    public void onBeginReceiveData() {
    }

    public OpensdkGameWrapper(Context context) {
        this.recorder = null;
        this.pcmPlayer = null;
        mContext = context;
        GMEJavaInstance.InitJavaEnv(context);
        Recorder recorder = new Recorder(context);
        this.recorder = recorder;
        recorder.setQQRecorderListener(this);
        this.pcmPlayer = new PCMPlayer();
        SharedPreferences.Editor edit = context.getSharedPreferences("BuglySdkInfos", 0).edit();
        edit.putString("9c06d3bb60", Configs.GMESDK_VERSION);
        edit.commit();
    }

    public Context getmContextCon() {
        return mContext;
    }

    public int CheckMicPermission() {
        int ordinal = ITMGContext.ITMG_RECORD_PERMISSION.ITMG_PERMISSION_GRANTED.ordinal();
        try {
            Context context = mContext;
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.checkSelfPermission("android.permission.RECORD_AUDIO") != 0) {
                    ordinal = ITMGContext.ITMG_RECORD_PERMISSION.ITMG_PERMISSION_Denied.ordinal();
                    int i = mContext.getSharedPreferences("GMEApplyForAudioRecord", 0).getInt("GMEApplyForAudioRecord", 0);
                    if (activity.shouldShowRequestPermissionRationale("android.permission.RECORD_AUDIO") || i == 0) {
                        ordinal = ITMGContext.ITMG_RECORD_PERMISSION.ITMG_PERMISSION_NotDetermined.ordinal();
                    }
                }
            } else {
                ordinal = ITMGContext.ITMG_RECORD_PERMISSION.ITMG_PERMISSION_NotDetermined.ordinal();
            }
        } catch (Exception e) {
            e.printStackTrace();
            QLog.m600e(TAG, String.format("CheckMicPermission retCode is: 3", new Object[0]));
            ordinal = ITMGContext.ITMG_RECORD_PERMISSION.ITMG_PERMISSION_ERROR.ordinal();
        }
        QLog.m600e(TAG, String.format("CheckMicPermission retCode is: %d", Integer.valueOf(ordinal)));
        return ordinal;
    }

    public int CheckMic() {
        if (CheckMicPermission() != ITMGContext.ITMG_RECORD_PERMISSION.ITMG_PERMISSION_GRANTED.ordinal()) {
            return ITMGContext.ITMG_CHECK_MIC_STATUS.ITMG_CHECK_MIC_STATUS_NO_GRANTED.ordinal();
        }
        return ITMGContext.ITMG_CHECK_MIC_STATUS.ITMG_CHECK_MIC_STATUS_AVAILABLE.ordinal();
    }

    public int initOpensdk() {
        QLog.m602i(TAG, "initOpensdk start.");
        int loadSdkLibrary = loadSdkLibrary();
        if (loadSdkLibrary == 0 || loadSdkLibrary == 7015) {
            Context context = mContext;
            nativeInitOpensdk(context, getPhoneInfo(context));
            GMEAudioInterrupt.getInstance(mContext);
            GMEAudioInterrupt.initInterruptHandler();
            TraeJni.getInstance().initTRAE();
        }
        QLog.m602i(TAG, "initOpensdk end. ret:" + loadSdkLibrary);
        return loadSdkLibrary;
    }

    public static int loadSdkLibrary() {
        return GMELibLoader.loadSdkLibrary(mContext);
    }

    private String getPhoneInfo(Context context) {
        String str = ((((((((((((((("PRODUCT=" + Build.PRODUCT + ";") + "CPU_ABI=" + Build.CPU_ABI + ";") + "TAGS=" + Build.TAGS + ";") + "VERSION_CODES_BASE=1;") + "MODEL=" + Build.MODEL + ";") + "SDK=" + Build.VERSION.SDK_INT + ";") + "VERSION_RELEASE=" + Build.VERSION.RELEASE + ";") + "DEVICE=" + Build.DEVICE + ";") + "DISPLAY=" + Build.DISPLAY + ";") + "BRAND=" + Build.BRAND + ";") + "BOARD=" + Build.BOARD + ";") + "FINGERPRINT=" + Build.FINGERPRINT + ";") + "ID=" + Build.ID + ";") + "MANUFACTURER=" + Build.MANUFACTURER + ";") + "USER=" + Build.USER + ";") + "PROCESSORS=" + Runtime.getRuntime().availableProcessors() + ";";
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String str2 = (((((str + "DATADIR=" + applicationInfo.dataDir + ";") + "LIBDIR=" + applicationInfo.nativeLibraryDir + ";") + "HW_AVC_ENC=0;") + "HW_AVC_DEC=0;") + "HW_HEVC_DEC=0;") + "HW_HEVC_ENC=0;";
        QLog.m602i(TAG, "getPhoneInfo=" + str2);
        return str2;
    }

    public int playRecordedFile(String str, final long j) {
        QLog.m602i(TAG, "playRecordedFile|filePath=" + str);
        PttListener.PlayFileListener playFileListener = new PttListener.PlayFileListener() { // from class: com.tencent.av.wrapper.OpensdkGameWrapper.1
            @Override // com.tencent.av.ptt.PttListener.PlayFileListener
            public void onCompleted(int i, String str2) {
                if (str2 == null) {
                    str2 = "";
                }
                QLog.m602i(OpensdkGameWrapper.TAG, String.format("playRecordedFile|onCompleted| code=%d, filePath=%s", Integer.valueOf(i), str2));
                OpensdkGameWrapper.this.nativePlayRecordedFileCallback(i, str2, j);
            }
        };
        try {
            this.pcmPlayer.initPCMPlayer();
            this.pcmPlayer.play(str, playFileListener);
            return 0;
        } catch (Exception e) {
            QLog.m602i(TAG, "play recording failed! e = " + e);
            playFileListener.onCompleted(PttError.PLAYER_INIT_ERROR, null);
            return 0;
        }
    }

    public int stopPlayFile() {
        QLog.m602i(TAG, "stopPlayFile.");
        this.pcmPlayer.stop();
        return 0;
    }

    public int getPlayingLevel() {
        return this.pcmPlayer.playLevel;
    }

    public int setPlayingGain(int i) {
        this.pcmPlayer.playGain = i;
        return i;
    }

    public boolean PathFileExit(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean deleteFile(String str) {
        File file;
        try {
            file = new File(str);
        } catch (Exception unused) {
        }
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("delete file failed" + str + "    success");
                return true;
            }
            QLog.m600e(TAG, "delete file failed" + str);
            return false;
        }
        QLog.m600e(TAG, "delete file failed：" + str + "does not exit！");
        return false;
    }

    public String getDeviceInfo() {
        String str = Build.MODEL + "_" + Build.VERSION.SDK + "_" + NetworkProvider.getNetTypeName(mContext);
        QLog.m602i(TAG, String.format("getDeviceInfo | info=%s", str));
        return str;
    }

    public String getDeviceType() {
        return Build.MODEL;
    }

    public String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    public void PostTextToURL(final String str, String str2, final String str3, final long j) {
        QLog.m600e(TAG, "uploadRecordedFile|strURL= " + str + "PostData:" + str2 + "strRequestUserData:" + str3);
        TokenFetcher.getInstance().httpRequest(str, str2, new TokenFetcher.HttpRequestListener() { // from class: com.tencent.av.wrapper.OpensdkGameWrapper.2
            @Override // com.tencent.av.ptt.TokenFetcher.HttpRequestListener
            public void onCompleted(int i, String str4, Object obj) {
                OpensdkGameWrapper.nativeHandleResponse(str3, i, str, str4, j);
            }
        }, str3);
    }

    public void UploadFileToCos(final String str, String str2, String str3, final String str4, final long j) {
        QLog.m600e(TAG, "uploadRecordedFile|strURL= " + str + "strFilePath:" + str2 + "strToken:" + str3);
        CosFileTransfer.uploadFile(str2, str, str3, 20971520, null, new FileTransferUtils.UploadFileListener() { // from class: com.tencent.av.wrapper.OpensdkGameWrapper.3
            @Override // com.tencent.av.utils.FileTransferUtils.UploadFileListener
            public void onCompleted(int i, String str5, Object obj) {
                OpensdkGameWrapper.nativeHandleResponse(str4, i, str, str5, j);
            }
        });
    }

    public void DownloadFileFromCos(final String str, final String str2, String str3, final String str4, final long j) {
        QLog.m602i(TAG, "DownloadFileFromCos|strURL= " + str + "strFilePath:" + str2 + "strToken:" + str3 + "strRequestUserData:" + str4);
        CosFileTransfer.downloadFile(str, str2, str3, null, new FileTransferUtils.DownloadFileListener() { // from class: com.tencent.av.wrapper.OpensdkGameWrapper.4
            @Override // com.tencent.av.utils.FileTransferUtils.DownloadFileListener
            public void onCompleted(int i, Object obj) {
                OpensdkGameWrapper.nativeHandleResponse(str4, i, str, str2, j);
            }
        });
    }

    public void UploadFileToS3(final String str, String str2, HttpParam httpParam, final String str3, final long j) {
        QLog.m602i(TAG, "UploadFileToS3|strURL= " + str + "strFilePath:" + str2 + "strToken:" + httpParam.signature + "strRequestUserData:" + str3);
        S3FileTransfer.uploadFile(str2, str, httpParam, null, new FileTransferUtils.UploadFileListener() { // from class: com.tencent.av.wrapper.OpensdkGameWrapper.5
            @Override // com.tencent.av.utils.FileTransferUtils.UploadFileListener
            public void onCompleted(int i, String str4, Object obj) {
                OpensdkGameWrapper.nativeHandleResponse(str3, i, str, str4, j);
            }
        });
    }

    public void DownloadFileFromS3(final String str, final String str2, HttpParam httpParam, final String str3, final long j) {
        QLog.m602i(TAG, "DownloadFileFromS3|strURL= " + str + "strFilePath:" + str2 + "strToken:" + httpParam.signature + "strRequestUserData:" + str3);
        S3FileTransfer.downloadFile(str, str2, httpParam, null, new FileTransferUtils.DownloadFileListener() { // from class: com.tencent.av.wrapper.OpensdkGameWrapper.6
            @Override // com.tencent.av.utils.FileTransferUtils.DownloadFileListener
            public void onCompleted(int i, Object obj) {
                OpensdkGameWrapper.nativeHandleResponse(str3, i, str, str2, j);
            }
        });
    }

    public void PostStreamAudioData(final String str, byte[] bArr, boolean z, final String str2, final long j) {
        QLog.m600e(TAG, "PostStreamAudioData|strURL= " + str + "end:" + (z ? 1 : 0) + "strRequestUserData:" + str2);
        TokenFetcher.getInstance().httpRequestWithBody(str, bArr, z ? 1 : 0, new TokenFetcher.HttpRequestListener() { // from class: com.tencent.av.wrapper.OpensdkGameWrapper.7
            @Override // com.tencent.av.ptt.TokenFetcher.HttpRequestListener
            public void onCompleted(int i, String str3, Object obj) {
                OpensdkGameWrapper.nativeHandleResponse(str2, i, str, str3, j);
            }
        }, str2);
    }

    public int setRecordingGain(int i) {
        this.recorder.recordGain = i;
        return i;
    }

    public int getRecordingLevel() {
        return this.recorder.recordLevel;
    }

    public void SetStreamingRecTimeOut(int i) {
        TokenFetcher.getInstance().SetStreamingRecTimeOut(i);
    }

    public void stopRecording() {
        this.recorder.stop();
    }

    public int startRecord(long j) {
        this.objPoint = j;
        if (!this.recorder.initRecording()) {
            return 4099;
        }
        this.recorder.start();
        return 0;
    }

    public static String GenerateUUID() {
        return UUID.randomUUID().toString();
    }

    public static void DoNetDiagnose(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("SSONetDiagnose start: " + str);
        arrayList.add(str2);
        OutPutDNSServers(arrayList);
        DoDNSResolution(str, arrayList);
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                QLog.m600e("GMENetDiagnoseHelper |", (String) arrayList.get(i));
            }
        }
    }

    public static byte[] turnPCM2SILE(byte[] bArr) {
        return TraeJni.getInstance().turnSilk(bArr, bArr.length);
    }

    public static void OutPutDNSServers(ArrayList<String> arrayList) {
        List<String> dNSServers = NetInfo.getDNSServers();
        if (dNSServers == null || dNSServers.size() <= 0) {
            return;
        }
        for (int i = 0; i < dNSServers.size(); i++) {
            String str = dNSServers.get(i);
            if (str.contains(".")) {
                arrayList.add("#" + (i + 1) + ". IPv4 DNS ip:" + str);
            } else if (str.contains(CertificateUtil.DELIMITER)) {
                arrayList.add("#" + (i + 1) + ". IPv6 DNS ip:" + str);
            } else {
                arrayList.add("#" + (i + 1) + ".  DNS ip:" + str);
            }
        }
    }

    public static String base64EncodeUrl(String str, String str2) {
        GenerateTestUserSig.setSecretkey(str2);
        return GenerateTestUserSig.genTestUserSig(str);
    }

    public static void DoInterfaceTrace(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("InterfaceTrace start: ");
        NetInfo.tracerout(str, arrayList);
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                QLog.m600e("GMENetDiagnoseHelper targetIP:  ", str + "  |" + ((String) arrayList.get(i)));
            }
        }
    }

    public static void DoDNSResolution(String str, ArrayList<String> arrayList) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            InetAddress[] allByName = InetAddress.getAllByName(str);
            arrayList.add("DNS resolution cost :  " + (System.currentTimeMillis() - currentTimeMillis) + "  ms");
            int i = 0;
            while (i < allByName.length) {
                if (i == 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("DNS resolution url name : ");
                    sb.append(allByName[i].getHostName() == null ? " " : allByName[i].getHostName());
                    arrayList.add(sb.toString());
                }
                String hostAddress = allByName[i].getHostAddress();
                if (hostAddress == null) {
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("DNS resolution ip-");
                i++;
                sb2.append(i);
                sb2.append(": ");
                sb2.append(hostAddress);
                arrayList.add(sb2.toString());
                NetInfo.tracerout(hostAddress, arrayList);
                arrayList.add(nativetcping(hostAddress));
            }
        } catch (Exception e) {
            arrayList.add("DNS resolution failed" + e.toString());
        }
    }

    @Override // com.tencent.av.ptt.Recorder.OnQQRecorderListener
    public void onReceiveRecordData(byte[] bArr) {
        OnCaptureData(bArr, bArr.length, this.objPoint);
    }

    @Override // com.tencent.av.ptt.Recorder.OnQQRecorderListener
    public void onRecorderFailed(String str, int i) {
        QLog.m602i(TAG, "on recorder failed! reason = " + i);
        OnCaptureDataOK(i == 3 ? PttError.RECORDER_MIC_PERMISSION_ERROR : i == 2 ? PttError.RECORDER_OPENFILE_ERROR : i == 4 ? 4098 : i == 1 ? 4099 : i == 5 ? PttError.RECORDER_VOICE_RECORD_TOO_SHORT : 0, this.objPoint);
    }

    @Override // com.tencent.av.ptt.Recorder.OnQQRecorderListener
    public void onRecorderPrepare(String str) {
        QLog.m602i(TAG, "on recorder prepare : path = " + str);
    }

    @Override // com.tencent.av.ptt.Recorder.OnQQRecorderListener
    public void onRecorderStart() {
        QLog.m602i(TAG, "on recorder start!");
    }

    @Override // com.tencent.av.ptt.Recorder.OnQQRecorderListener
    public void onRecorderEnd() {
        QLog.m602i(TAG, "on recorder end!");
        OnCaptureDataOK(0, this.objPoint);
    }

    @Override // com.tencent.av.ptt.Recorder.OnQQRecorderListener
    public void onRecorderError(String str, String str2) {
        QLog.m602i(TAG, "on recorder error : path = " + str + " , reason = " + str2);
        OnCaptureDataOK(PttError.RECORDER_OPENFILE_ERROR, this.objPoint);
    }

    @Override // com.tencent.av.ptt.Recorder.OnQQRecorderListener
    public void onRecorderAbnormal(int i) {
        QLog.m602i(TAG, "on recorder abnormal!");
        OnCaptureDataOK(4100, this.objPoint);
    }
}
