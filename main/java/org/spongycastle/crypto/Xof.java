package org.spongycastle.crypto;

/* loaded from: classes3.dex */
public interface Xof extends ExtendedDigest {
    int doFinal(byte[] bArr, int i, int i2);

    int doOutput(byte[] bArr, int i, int i2);
}
