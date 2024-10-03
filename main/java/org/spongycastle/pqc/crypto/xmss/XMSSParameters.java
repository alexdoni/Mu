package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.crypto.Digest;

/* loaded from: classes3.dex */
public final class XMSSParameters {
    private final int height;

    /* renamed from: k */
    private final int f2850k;
    private final XMSSOid oid;
    private final WOTSPlus wotsPlus;

    public XMSSParameters(int i, Digest digest) {
        if (i < 2) {
            throw new IllegalArgumentException("height must be >= 2");
        }
        if (digest == null) {
            throw new NullPointerException("digest == null");
        }
        WOTSPlus wOTSPlus = new WOTSPlus(new WOTSPlusParameters(digest));
        this.wotsPlus = wOTSPlus;
        this.height = i;
        this.f2850k = determineMinK();
        this.oid = DefaultXMSSOid.lookup(getDigest().getAlgorithmName(), getDigestSize(), getWinternitzParameter(), wOTSPlus.getParams().getLen(), i);
    }

    private int determineMinK() {
        int i = 2;
        while (true) {
            int i2 = this.height;
            if (i <= i2) {
                if ((i2 - i) % 2 == 0) {
                    return i;
                }
                i++;
            } else {
                throw new IllegalStateException("should never happen...");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Digest getDigest() {
        return this.wotsPlus.getParams().getDigest();
    }

    public int getDigestSize() {
        return this.wotsPlus.getParams().getDigestSize();
    }

    public int getWinternitzParameter() {
        return this.wotsPlus.getParams().getWinternitzParameter();
    }

    public int getHeight() {
        return this.height;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WOTSPlus getWOTSPlus() {
        return this.wotsPlus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getK() {
        return this.f2850k;
    }
}
