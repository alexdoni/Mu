package com.mu.utility;

import android.content.Context;
import android.view.OrientationEventListener;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes2.dex */
public class MyOrientationDetector extends OrientationEventListener {
    public static int unityOrientation;

    public MyOrientationDetector(Context context) {
        super(context);
    }

    @Override // android.view.OrientationEventListener
    public void onOrientationChanged(int i) {
        if (i == -1) {
            return;
        }
        int i2 = 0;
        if (i <= 350 && i >= 10) {
            if (i > 70 && i < 100) {
                i2 = 90;
            } else if (i > 170 && i < 190) {
                i2 = CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
            } else if (i > 260 && i < 280) {
                i2 = 270;
            }
        }
        unityOrientation = i2;
    }
}
