package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes3.dex */
public class McElieceCCA2KeyParameters extends AsymmetricKeyParameter {
    private String params;

    public McElieceCCA2KeyParameters(boolean z, String str) {
        super(z);
        this.params = str;
    }

    public String getDigest() {
        return this.params;
    }
}
