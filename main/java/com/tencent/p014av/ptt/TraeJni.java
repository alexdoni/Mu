package com.tencent.p014av.ptt;

/* loaded from: classes3.dex */
public class TraeJni {
    static TraeJni sInstance;

    private native boolean destory();

    private native boolean init();

    private native byte[] turnPCM2SILK(byte[] bArr, long j);

    private native byte[] turnSILK2PCM(byte[] bArr, long j);

    static {
        System.loadLibrary("silk");
        sInstance = null;
    }

    public static TraeJni getInstance() {
        if (sInstance == null) {
            sInstance = new TraeJni();
        }
        return sInstance;
    }

    public byte[] turnPCM(byte[] bArr, long j) {
        return turnSILK2PCM(bArr, j);
    }

    public byte[] turnSilk(byte[] bArr, long j) {
        return turnPCM2SILK(bArr, j);
    }

    public boolean initTRAE() {
        return init();
    }

    public boolean destoryTRAE() {
        return destory();
    }
}
