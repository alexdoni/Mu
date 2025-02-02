package org.spongycastle.crypto;

/* loaded from: classes3.dex */
public interface Committer {
    Commitment commit(byte[] bArr);

    boolean isRevealed(Commitment commitment, byte[] bArr);
}
