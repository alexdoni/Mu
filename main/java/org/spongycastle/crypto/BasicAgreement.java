package org.spongycastle.crypto;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public interface BasicAgreement {
    BigInteger calculateAgreement(CipherParameters cipherParameters);

    int getFieldSize();

    void init(CipherParameters cipherParameters);
}
