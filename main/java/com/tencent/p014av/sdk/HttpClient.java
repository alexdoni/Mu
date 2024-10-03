package com.tencent.p014av.sdk;

import com.tencent.p014av.utils.HttpHelper;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class HttpClient {
    public static String LOGTAG = "HttpClient";
    public long mNativeEntity;

    public native void nativeHttpResult(long j, MultiVideoResult multiVideoResult);

    public HttpClient(long j) {
        this.mNativeEntity = j;
    }

    public void uninit() {
        this.mNativeEntity = 0L;
    }

    /* loaded from: classes3.dex */
    public class CsCmdCallbackImpl implements HttpHelper.HttpRequestListener {
        public CsCmdCallbackImpl() {
        }

        @Override // com.tencent.av.utils.HttpHelper.HttpRequestListener
        public void onCompleted(String str, int i, String str2, byte[] bArr, Object obj) {
            MultiVideoResult multiVideoResult = new MultiVideoResult();
            multiVideoResult.statusCode = i;
            multiVideoResult.errorInfo = str2;
            if (bArr == null || bArr.length == 0) {
                multiVideoResult.result = AVError.AV_ERR_HTTP_REQ_FAIL;
            } else {
                multiVideoResult.result = 0;
                multiVideoResult.response = bArr;
            }
            HttpClient httpClient = HttpClient.this;
            httpClient.nativeHttpResult(httpClient.mNativeEntity, multiVideoResult);
        }
    }

    /* loaded from: classes3.dex */
    public class MultiVideoResult {
        public String errorInfo;
        public byte[] response;
        public int result = AVError.AV_ERR_HTTP_REQ_FAIL;
        public int statusCode;

        public MultiVideoResult() {
        }
    }

    public void httpGet(String str, int i) {
        HttpHelper.httpGetRequest(str, null, i, new CsCmdCallbackImpl());
    }

    public void httpPost(String str, byte[] bArr, int i) {
        HttpHelper.httpPostRequest(str, bArr, null, null, i, new CsCmdCallbackImpl());
    }

    public void httpPost(String str, byte[] bArr, int i, String str2) {
        CsCmdCallbackImpl csCmdCallbackImpl = new CsCmdCallbackImpl();
        if (str2.length() == 0) {
            HttpHelper.httpPostRequest(str, bArr, null, null, i, csCmdCallbackImpl);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Host", str2);
        HttpHelper.httpPostRequest(str, bArr, hashMap, null, i, csCmdCallbackImpl);
    }

    public void uploadFileToCos(String str, String str2, String str3) {
        HttpHelper.uploadFileToCosRequest(str, str2, str3, new CsCmdCallbackImpl());
    }

    public void uploadFileToS3(String str, String str2, HttpParam httpParam) {
        HttpHelper.uploadFileToS3Request(str, str2, httpParam, new CsCmdCallbackImpl());
    }
}
