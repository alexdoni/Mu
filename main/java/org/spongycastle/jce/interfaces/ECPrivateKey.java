package org.spongycastle.jce.interfaces;

import java.math.BigInteger;
import java.security.PrivateKey;

/* loaded from: classes3.dex */
public interface ECPrivateKey extends ECKey, PrivateKey {
    BigInteger getD();
}
