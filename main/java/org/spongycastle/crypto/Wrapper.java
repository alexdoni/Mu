package org.spongycastle.crypto;

/* loaded from: classes3.dex */
public interface Wrapper {
    String getAlgorithmName();

    void init(boolean z, CipherParameters cipherParameters);

    byte[] unwrap(byte[] bArr, int i, int i2) throws InvalidCipherTextException;

    byte[] wrap(byte[] bArr, int i, int i2);
}
