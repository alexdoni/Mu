package com.tencent.p014av.sig;

import com.tencent.p014av.sdk.GMELibLoader;

/* loaded from: classes3.dex */
public class AuthBuffer {
    private static boolean mIsSoLoaded = false;
    private static AuthBuffer sAuthBuffer;

    public native byte[] genAuthBuffer(int i, String str, String str2, String str3);

    private static void loadSo() {
        mIsSoLoaded = GMELibLoader.loadSdkLibrary(null) == 0;
    }

    private AuthBuffer() {
    }

    public static AuthBuffer getInstance() {
        AuthBuffer authBuffer;
        synchronized (AuthBuffer.class) {
            if (sAuthBuffer == null) {
                loadSo();
                if (mIsSoLoaded) {
                    sAuthBuffer = new AuthBuffer();
                }
            }
            authBuffer = sAuthBuffer;
        }
        return authBuffer;
    }
}
