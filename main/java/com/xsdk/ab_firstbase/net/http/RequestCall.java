package com.xsdk.ab_firstbase.net.http;

import android.content.DialogInterface;
import android.os.AsyncTask;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.xsdk.ab_firstbase.net.okhttp3.CommonCallBack;
import com.xsdk.ab_firstbase.net.okhttp3.OkHttpManger;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.json.JsonUtil;

/* loaded from: classes3.dex */
public class RequestCall implements DialogInterface.OnCancelListener {
    private CommomCallBack callback;
    private long connTimeOut;
    private int method;
    private AsyncTask<String, Integer, HttpCallResult> postAsyncTask;
    private long readTimeOut;
    private HttpRequest twHttpRequest;
    private long writeTimeOut;

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    public void doGetData() {
        callWithOkHttp();
    }

    private void callWithOkHttp() {
        LLog.i_Control("url-" + this.twHttpRequest.url + "\n" + this.twHttpRequest.mParameters);
        OkHttpManger.getInstance().postAsynBackString(this.twHttpRequest.url, this.method, this.twHttpRequest.mParameters, this.twHttpRequest.headers, new CommonCallBack() { // from class: com.xsdk.ab_firstbase.net.http.RequestCall.1
            @Override // com.xsdk.ab_firstbase.net.okhttp3.CommonCallBack
            public void onResponse(String str) {
                try {
                    if (RequestCall.this.callback != null) {
                        RequestCall.this.callback.onAfter(str, RequestCall.this.twHttpRequest.url, RequestCall.this.twHttpRequest);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                LLog.i_Control(RequestCall.this.twHttpRequest.url + "\n" + str);
            }

            @Override // com.xsdk.ab_firstbase.net.okhttp3.CommonCallBack
            public void onFail(Exception exc) {
                RequestCall.this.callback.onFailure(-1, exc.getMessage());
                LLog.e_Control("fail code=-1 msg=" + exc.getMessage());
            }

            @Override // com.xsdk.ab_firstbase.net.okhttp3.CommonCallBack
            public void onFail(Exception exc, int i) {
                try {
                    if (((Integer) JsonUtil.get(exc.getMessage(), "code")).intValue() == 50000) {
                        Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.CUSTOM_SDK_IP_BLOCKED, "");
                    }
                } catch (Exception unused) {
                }
                if (RequestCall.this.callback != null) {
                    RequestCall.this.callback.onFailure(i, exc.getMessage());
                    LLog.e_Control(RequestCall.this.twHttpRequest.url + " ;fail code=" + i + " msg=" + exc.getMessage());
                }
            }
        });
    }

    public RequestCall(HttpRequest httpRequest, int i) {
        this.twHttpRequest = httpRequest;
        this.method = i;
    }

    public void execute(CommomCallBack commomCallBack) {
        this.callback = commomCallBack;
        doGetData();
    }

    public void execute() {
        doGetData();
    }

    public long getReadTimeOut() {
        return this.readTimeOut;
    }

    public void setReadTimeOut(long j) {
        this.readTimeOut = j;
    }

    public long getWriteTimeOut() {
        return this.writeTimeOut;
    }

    public void setWriteTimeOut(long j) {
        this.writeTimeOut = j;
    }

    public long getConnTimeOut() {
        return this.connTimeOut;
    }

    public void setConnTimeOut(long j) {
        this.connTimeOut = j;
    }
}
