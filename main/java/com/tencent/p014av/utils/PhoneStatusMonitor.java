package com.tencent.p014av.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.PhoneStateListener;

/* loaded from: classes3.dex */
public class PhoneStatusMonitor {
    static final String TAG = "PhoneStatusMonitor";
    Context mContext;
    PhoneStatusListener mPhoneStatusListener;
    boolean mIsCalling = false;
    boolean isIsInited = false;
    PhoneStateListener mPhoneStateListener = new QQPhoneStateListener();
    PhoneStatusReceiver mPhoneStatusReceiver = new PhoneStatusReceiver();

    /* loaded from: classes3.dex */
    public interface PhoneStatusListener {
        void onCallStateChanged(boolean z);
    }

    public PhoneStatusMonitor(Context context, PhoneStatusListener phoneStatusListener) {
        this.mContext = context;
        this.mPhoneStatusListener = phoneStatusListener;
    }

    public void init() {
        if (this.isIsInited) {
            return;
        }
        PhoneStatusTools.listen(this.mContext, this.mPhoneStateListener, 32);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        intentFilter.addAction("android.intent.action.PHONE_STATE2");
        intentFilter.addAction("android.intent.action.PHONE_STATE_2");
        intentFilter.addAction("android.intent.action.PHONE_STATE_EXT");
        intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        this.mContext.registerReceiver(this.mPhoneStatusReceiver, intentFilter);
        this.isIsInited = true;
    }

    public void uninit() {
        if (this.isIsInited) {
            PhoneStatusTools.listen(this.mContext, this.mPhoneStateListener, 0);
            this.mContext.unregisterReceiver(this.mPhoneStatusReceiver);
            this.isIsInited = false;
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }

    public boolean isCalling() {
        return this.mIsCalling;
    }

    /* loaded from: classes3.dex */
    class QQPhoneStateListener extends PhoneStateListener {
        QQPhoneStateListener() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            if (i == 0) {
                QLog.m598d(PhoneStatusMonitor.TAG, "onCallStateChanged CALL_STATE_IDLE");
                if (PhoneStatusMonitor.this.mIsCalling && !PhoneStatusTools.isCalling(PhoneStatusMonitor.this.mContext)) {
                    PhoneStatusMonitor.this.mIsCalling = false;
                    if (PhoneStatusMonitor.this.mPhoneStatusListener != null) {
                        PhoneStatusMonitor.this.mPhoneStatusListener.onCallStateChanged(false);
                    }
                }
            } else if (i == 1 || i == 2) {
                QLog.m598d(PhoneStatusMonitor.TAG, "onCallStateChanged CALL_STATE_RINGING or CALL_STATE_OFFHOOK");
                if (!PhoneStatusMonitor.this.mIsCalling) {
                    PhoneStatusMonitor.this.mIsCalling = true;
                    if (PhoneStatusMonitor.this.mPhoneStatusListener != null) {
                        PhoneStatusMonitor.this.mPhoneStatusListener.onCallStateChanged(true);
                    }
                }
            }
            super.onCallStateChanged(i, str);
        }
    }

    /* loaded from: classes3.dex */
    class PhoneStatusReceiver extends BroadcastReceiver {
        PhoneStatusReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
                QLog.m598d(PhoneStatusMonitor.TAG, "onReceive NEW_OUTGOING_CALL");
                return;
            }
            QLog.m598d(PhoneStatusMonitor.TAG, "onReceive PHONE_STATE");
            if (PhoneStatusMonitor.this.mIsCalling && !PhoneStatusTools.isCalling(PhoneStatusMonitor.this.mContext)) {
                PhoneStatusMonitor.this.mIsCalling = false;
                if (PhoneStatusMonitor.this.mPhoneStatusListener != null) {
                    PhoneStatusMonitor.this.mPhoneStatusListener.onCallStateChanged(false);
                    return;
                }
                return;
            }
            if (PhoneStatusMonitor.this.mIsCalling || !PhoneStatusTools.isCalling(PhoneStatusMonitor.this.mContext)) {
                return;
            }
            PhoneStatusMonitor.this.mIsCalling = true;
            if (PhoneStatusMonitor.this.mPhoneStatusListener != null) {
                PhoneStatusMonitor.this.mPhoneStatusListener.onCallStateChanged(true);
            }
        }
    }
}
