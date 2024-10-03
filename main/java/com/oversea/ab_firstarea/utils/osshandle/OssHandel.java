package com.oversea.ab_firstarea.utils.osshandle;

import android.net.Uri;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.luck.picture.lib.config.PictureMimeType;
import com.oversea.ab_firstarea.channel.ProjectType;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.xsdk.ab_firstbase.statisics.util.ContextHolder;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.MD5;

/* loaded from: classes.dex */
public class OssHandel {
    private static OssHandel instance;
    private String fileName;
    private OSS oss;
    private boolean isUploadSuccess = false;
    private int ossType = 0;

    public void release() {
    }

    public static OssHandel getInstance() {
        if (instance == null) {
            instance = new OssHandel();
        }
        return instance;
    }

    public void init() {
        LLog.v_noControl("OssHandel init");
        if (ManageBean.getInstance().getaCloudStSBean().getAes123EcbCredentials() == null || ManageBean.getInstance().getaCloudStSBean().getAes123EcbCredentials() == null) {
            LLog.v_noControl("OssHandel init oss null");
        } else {
            initOSS();
        }
    }

    private void initOSS() {
        String endpoint = ManageBean.getInstance().getaCloudStSBean().getData().getOss().getEndpoint();
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(15000);
        clientConfiguration.setSocketTimeout(15000);
        clientConfiguration.setMaxConcurrentRequest(5);
        clientConfiguration.setMaxErrorRetry(2);
        this.oss = new OSSClient(ContextHolder.getContext(), endpoint, new OSSStsTokenCredentialProvider(ManageBean.getInstance().getaCloudStSBean().getAes123EcbCredentials().getAccessKeyId(), ManageBean.getInstance().getaCloudStSBean().getAes123EcbCredentials().getAccessKeySecret(), ManageBean.getInstance().getaCloudStSBean().getAes123EcbCredentials().getSecurityToken()), clientConfiguration);
    }

    public boolean isUploadSuccess() {
        return this.isUploadSuccess;
    }

    public void setUploadSuccess(boolean z) {
        this.isUploadSuccess = z;
    }

    public void startUploadFile() {
        setUploadSuccess(false);
    }

    public void finishUploadFile(boolean z, OssCallBack ossCallBack) {
        setUploadSuccess(z);
        if (ossCallBack != null) {
            LLog.v_noControl("OssHandel finishUploadFile");
            ossCallBack.onChatResult(z);
        } else {
            LLog.v_noControl("ossCallBack null");
        }
    }

    public void judgeIsUploadFile(String str, String str2, final Uri uri, final OssCallBack ossCallBack) {
        startUploadFile();
        final String fileName = setFileName(str, str2);
        new Thread(new Runnable() { // from class: com.oversea.ab_firstarea.utils.osshandle.OssHandel.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (OssHandel.this.oss.doesObjectExist(ManageBean.getInstance().getaCloudStSBean().getData().getOss().getBucket_name(), fileName)) {
                        LLog.v_noControl("judgeIsUploadFile iExist have");
                        OssHandel.this.finishUploadFile(true, ossCallBack);
                    } else {
                        OssHandel.this.uploadFile(uri, ossCallBack);
                    }
                } catch (ClientException e) {
                    OssHandel.this.finishUploadFile(false, ossCallBack);
                    e.printStackTrace();
                } catch (ServiceException e2) {
                    OssHandel.this.finishUploadFile(false, ossCallBack);
                    e2.printStackTrace();
                }
            }
        }).start();
    }

    public void uploadFile(Uri uri, final OssCallBack ossCallBack) {
        LLog.v_noControl("fileName=" + this.fileName + " " + uri.getPath());
        if (ManageBean.getInstance().getaCloudStSBean().getAes123EcbCredentials() == null) {
            LLog.v_noControl("OssHandel init aes123 null");
            finishUploadFile(false, ossCallBack);
        } else {
            PutObjectRequest putObjectRequest = new PutObjectRequest(ManageBean.getInstance().getaCloudStSBean().getData().getOss().getBucket_name(), this.fileName, uri);
            putObjectRequest.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() { // from class: com.oversea.ab_firstarea.utils.osshandle.OssHandel.2
                @Override // com.alibaba.sdk.android.oss.callback.OSSProgressCallback
                public void onProgress(PutObjectRequest putObjectRequest2, long j, long j2) {
                    LLog.i_Control("currentSize: " + j + " totalSize: " + j2);
                }
            });
            this.oss.asyncPutObject(putObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: com.oversea.ab_firstarea.utils.osshandle.OssHandel.3
                @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
                public void onSuccess(PutObjectRequest putObjectRequest2, PutObjectResult putObjectResult) {
                    LLog.i_Control("upload onSuccess");
                    OssHandel.this.finishUploadFile(true, ossCallBack);
                }

                @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
                public void onFailure(PutObjectRequest putObjectRequest2, ClientException clientException, ServiceException serviceException) {
                    OssHandel.this.finishUploadFile(false, ossCallBack);
                    if (clientException != null) {
                        clientException.printStackTrace();
                    }
                    if (serviceException != null) {
                        LLog.e_Control("upload fail:" + serviceException.getErrorCode());
                        LLog.e_Control("upload fail:" + serviceException.getRequestId());
                        LLog.e_Control("upload fail:" + serviceException.getHostId());
                        LLog.e_Control("upload fail:" + serviceException.getRawMessage());
                    }
                }
            });
        }
    }

    public String setFileName(String str, String str2) {
        String str3;
        String mD5String = MD5.getMD5String(str2);
        if (ProjectType.TEST.equals(ProjectType.pType)) {
            str3 = "test/" + Lxhw_AppInfoDecorator.getGame_id() + RemoteSettings.FORWARD_SLASH_STRING + str + RemoteSettings.FORWARD_SLASH_STRING + mD5String + PictureMimeType.PNG;
        } else {
            str3 = "prod/" + Lxhw_AppInfoDecorator.getGame_id() + RemoteSettings.FORWARD_SLASH_STRING + str + RemoteSettings.FORWARD_SLASH_STRING + mD5String + PictureMimeType.PNG;
        }
        this.fileName = str3;
        return str3;
    }

    public String presignUrl() {
        String str = RemoteSettings.FORWARD_SLASH_STRING + this.fileName;
        LLog.v_noControl("url=" + str);
        return str;
    }
}
