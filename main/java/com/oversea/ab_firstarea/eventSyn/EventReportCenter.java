package com.oversea.ab_firstarea.eventSyn;

import android.content.Context;
import android.os.Handler;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class EventReportCenter {
    private static EventReportCenter instance;
    private Timer logTimer;
    private TimerTask logTimerTask;
    private Context mContext;
    TimerTask task;
    private String TAG = "EventReportCenter";
    private int maxSaveCount = 1000;
    private int pollWaitTime = 60000;
    private int waitEveryTime = CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
    private int pollWaitFailCount = 0;
    private int pollWaitFailMaxCount = 2;
    private int topTop = 20;
    private int curIndex = 0;
    private boolean isende = false;
    Handler mHandler = new Handler();
    private Timer timer = new Timer();

    public void test() {
    }

    public static EventReportCenter getInstance() {
        if (instance == null) {
            instance = new EventReportCenter();
        }
        return instance;
    }
}
