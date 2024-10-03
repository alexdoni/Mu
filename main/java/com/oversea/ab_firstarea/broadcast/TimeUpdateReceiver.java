package com.oversea.ab_firstarea.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes.dex */
public class TimeUpdateReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action) && action.equals("android.intent.action.DATE_CHANGED")) {
            long currentTimeMillis = System.currentTimeMillis();
            LLog.i_Control("getRetention ACTION_DATE_CHANGED: " + currentTimeMillis);
            AreaSdk.getInstance().getRetention(currentTimeMillis);
        }
    }
}
