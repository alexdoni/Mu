package com.tencent.p014av.wrapper;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.tencent.p014av.utils.PhoneStatusMonitor;
import com.tencent.p014av.utils.QLog;

/* loaded from: classes3.dex */
public class GMEAudioInterrupt {
    static final String TAG = "GMEAudioInterrupt";
    private static GMEAudioInterrupt mself;
    private PhoneStatusMonitor.PhoneStatusListener mPhoneStatusListener;
    private PhoneStatusMonitor mPhoneStatusMonitor;

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeInterruptPuase();

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeInterruptResume();

    public static void getInstance(Object obj) {
        final Context context = (Context) obj;
        if (mself == null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.av.wrapper.GMEAudioInterrupt.1
                @Override // java.lang.Runnable
                public void run() {
                    if (GMEAudioInterrupt.mself == null) {
                        GMEAudioInterrupt unused = GMEAudioInterrupt.mself = new GMEAudioInterrupt(context);
                    }
                }
            });
        }
    }

    public static void initInterruptHandler() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.av.wrapper.GMEAudioInterrupt.2
            @Override // java.lang.Runnable
            public void run() {
                if (GMEAudioInterrupt.mself != null) {
                    GMEAudioInterrupt.mself.initInterruptHandlerInternal();
                }
            }
        });
    }

    public static void uninitInterruptHandler() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.av.wrapper.GMEAudioInterrupt.3
            @Override // java.lang.Runnable
            public void run() {
                if (GMEAudioInterrupt.mself != null) {
                    GMEAudioInterrupt.mself.uninitInterruptHandlerInternal();
                }
            }
        });
    }

    public void initInterruptHandlerInternal() {
        PhoneStatusMonitor phoneStatusMonitor = this.mPhoneStatusMonitor;
        if (phoneStatusMonitor != null) {
            phoneStatusMonitor.init();
        }
    }

    public void uninitInterruptHandlerInternal() {
        PhoneStatusMonitor phoneStatusMonitor = this.mPhoneStatusMonitor;
        if (phoneStatusMonitor != null) {
            phoneStatusMonitor.uninit();
        }
    }

    private GMEAudioInterrupt(Context context) {
        this.mPhoneStatusMonitor = null;
        this.mPhoneStatusListener = null;
        this.mPhoneStatusListener = new MyPhoneStatusListener();
        if (this.mPhoneStatusMonitor == null) {
            this.mPhoneStatusMonitor = new PhoneStatusMonitor(context, this.mPhoneStatusListener);
        }
    }

    /* loaded from: classes3.dex */
    class MyPhoneStatusListener implements PhoneStatusMonitor.PhoneStatusListener {
        MyPhoneStatusListener() {
        }

        @Override // com.tencent.av.utils.PhoneStatusMonitor.PhoneStatusListener
        public void onCallStateChanged(boolean z) {
            QLog.m602i(GMEAudioInterrupt.TAG, "onCallStateChanged isCalling: " + z);
            if (z) {
                QLog.m602i(GMEAudioInterrupt.TAG, "MyPhoneStatusListener iscalling ");
                GMEAudioInterrupt.this.nativeInterruptPuase();
                QLog.m602i(GMEAudioInterrupt.TAG, "MyPhoneStatusListener stopService ");
            } else {
                QLog.m602i(GMEAudioInterrupt.TAG, "MyPhoneStatusListener notcalling ");
                GMEAudioInterrupt.this.nativeInterruptResume();
                QLog.m602i(GMEAudioInterrupt.TAG, "MyPhoneStatusListener startService ");
            }
        }
    }
}
