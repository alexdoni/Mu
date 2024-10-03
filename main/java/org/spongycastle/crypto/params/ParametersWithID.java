package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes3.dex */
public class ParametersWithID implements CipherParameters {

    /* renamed from: id */
    private byte[] f2512id;
    private CipherParameters parameters;

    public ParametersWithID(CipherParameters cipherParameters, byte[] bArr) {
        this.parameters = cipherParameters;
        this.f2512id = bArr;
    }

    public byte[] getID() {
        return this.f2512id;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}
