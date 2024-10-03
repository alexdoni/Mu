package com.oversea.ab_firstarea.net.domainN;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.net.URL;

/* loaded from: classes.dex */
public class PingIpTask implements Runnable {
    private Handler mHandler;
    private String pingIp;

    public PingIpTask(String str, Handler handler) {
        this.pingIp = str;
        this.mHandler = handler;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        Message message = new Message();
        Bundle bundle = new Bundle();
        Runtime runtime = Runtime.getRuntime();
        try {
            try {
                String host = new URL(this.pingIp).toURI().getHost();
                int waitFor = runtime.exec("ping -c 1 -w 3000 " + host).waitFor();
                if (waitFor == 0) {
                    str = "run suc: " + host;
                } else {
                    str = "run fail: " + host;
                }
                LLog.i_Control(str);
                long currentTimeMillis2 = System.currentTimeMillis();
                bundle.putInt("resCode", waitFor == 0 ? 200 : -1);
                bundle.putInt("responseTime", (int) (currentTimeMillis2 - currentTimeMillis));
                message.setData(bundle);
            } catch (Exception e) {
                e.printStackTrace();
                bundle.putInt("resCode", -1);
                bundle.putInt("responseTime", DomainName.getInstance().morenTime);
            }
        } finally {
            this.mHandler.sendMessage(message);
        }
    }
}
