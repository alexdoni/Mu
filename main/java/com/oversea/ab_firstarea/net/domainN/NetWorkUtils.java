package com.oversea.ab_firstarea.net.domainN;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes.dex */
public class NetWorkUtils {
    public static void isNetWorkAvailableOfGet(String str, final Comparable<Bundle> comparable) {
        NosExecutor.getNOSExecutor().execute(new PingIpTask(str, new Handler(Looper.getMainLooper()) { // from class: com.oversea.ab_firstarea.net.domainN.NetWorkUtils.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                Comparable comparable2 = comparable;
                if (comparable2 != null) {
                    comparable2.compareTo(message.getData());
                }
            }
        }));
    }

    /* loaded from: classes.dex */
    private static class Connection implements Runnable {
        private int responseCode;
        private String urlStr;

        public Connection(String str) {
            this.urlStr = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.urlStr).openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.connect();
                set(httpURLConnection.getResponseCode());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        public synchronized void set(int i) {
            this.responseCode = i;
        }

        public synchronized int get() {
            return this.responseCode;
        }
    }
}
