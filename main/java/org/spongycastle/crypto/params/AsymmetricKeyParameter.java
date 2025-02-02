package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes3.dex */
public class AsymmetricKeyParameter implements CipherParameters {
    boolean privateKey;

    public AsymmetricKeyParameter(boolean z) {
        this.privateKey = z;
    }

    public boolean isPrivate() {
        return this.privateKey;
    }
}
