package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.Digest;

/* loaded from: classes3.dex */
public class KDF1BytesGenerator extends BaseKDFBytesGenerator {
    public KDF1BytesGenerator(Digest digest) {
        super(0, digest);
    }
}
