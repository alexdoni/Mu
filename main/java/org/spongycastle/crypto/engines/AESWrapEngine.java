package org.spongycastle.crypto.engines;

/* loaded from: classes3.dex */
public class AESWrapEngine extends RFC3394WrapEngine {
    public AESWrapEngine() {
        super(new AESEngine());
    }

    public AESWrapEngine(boolean z) {
        super(new AESEngine(), z);
    }
}
