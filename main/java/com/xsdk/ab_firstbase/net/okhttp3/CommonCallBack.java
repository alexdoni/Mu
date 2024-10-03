package com.xsdk.ab_firstbase.net.okhttp3;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/* loaded from: classes3.dex */
public abstract class CommonCallBack implements Callback {
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.xsdk.ab_firstbase.net.okhttp3.CommonCallBack.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.arg1 == 1) {
                CommonCallBack.this.onResponse((String) message.obj);
            } else {
                CommonCallBack.this.onFail(new Exception((String) message.obj), message.arg1);
            }
        }
    };

    public abstract void onFail(Exception exc);

    public abstract void onFail(Exception exc, int i);

    public abstract void onResponse(String str);

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException iOException) {
        Message obtain = Message.obtain();
        obtain.obj = iOException.getMessage();
        obtain.arg1 = 0;
        this.handler.sendMessage(obtain);
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        Message obtain = Message.obtain();
        try {
            if (response.code() == 200) {
                if (response.body() != null && response.isSuccessful()) {
                    obtain.obj = response.body().string();
                    obtain.arg1 = 1;
                } else {
                    obtain.arg1 = 301;
                    obtain.obj = "response.body error";
                }
            } else {
                if (response != null && response.body() != null) {
                    obtain.obj = response.body().string();
                } else {
                    obtain.obj = "network error";
                }
                obtain.arg1 = response.code();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("overs", e.getMessage());
            obtain.obj = e.getMessage();
            obtain.arg1 = 303;
        }
        this.handler.sendMessage(obtain);
    }
}
