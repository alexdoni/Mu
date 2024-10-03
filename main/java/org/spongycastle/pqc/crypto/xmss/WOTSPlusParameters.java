package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.crypto.Digest;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class WOTSPlusParameters {
    private final Digest digest;
    private final int digestSize;
    private final int len;
    private final int len1;
    private final int len2;
    private final XMSSOid oid;
    private final int winternitzParameter;

    /* JADX INFO: Access modifiers changed from: protected */
    public WOTSPlusParameters(Digest digest) {
        if (digest == null) {
            throw new NullPointerException("digest == null");
        }
        this.digest = digest;
        int digestSize = XMSSUtil.getDigestSize(digest);
        this.digestSize = digestSize;
        this.winternitzParameter = 16;
        int ceil = (int) Math.ceil((digestSize * 8) / XMSSUtil.log2(16));
        this.len1 = ceil;
        int floor = ((int) Math.floor(XMSSUtil.log2((16 - 1) * ceil) / XMSSUtil.log2(16))) + 1;
        this.len2 = floor;
        int i = ceil + floor;
        this.len = i;
        WOTSPlusOid lookup = WOTSPlusOid.lookup(digest.getAlgorithmName(), digestSize, 16, i);
        this.oid = lookup;
        if (lookup != null) {
            return;
        }
        throw new IllegalArgumentException("cannot find OID for digest algorithm: " + digest.getAlgorithmName());
    }

    protected XMSSOid getOid() {
        return this.oid;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Digest getDigest() {
        return this.digest;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getDigestSize() {
        return this.digestSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getWinternitzParameter() {
        return this.winternitzParameter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLen() {
        return this.len;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLen1() {
        return this.len1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLen2() {
        return this.len2;
    }
}
