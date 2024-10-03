package org.spongycastle.pqc.crypto.ntru;

import java.nio.ByteBuffer;
import org.spongycastle.crypto.Digest;

/* loaded from: classes3.dex */
public class NTRUSignerPrng {
    private int counter = 0;
    private Digest hashAlg;
    private byte[] seed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NTRUSignerPrng(byte[] bArr, Digest digest) {
        this.seed = bArr;
        this.hashAlg = digest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] nextBytes(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        while (allocate.hasRemaining()) {
            ByteBuffer allocate2 = ByteBuffer.allocate(this.seed.length + 4);
            allocate2.put(this.seed);
            allocate2.putInt(this.counter);
            byte[] array = allocate2.array();
            int digestSize = this.hashAlg.getDigestSize();
            byte[] bArr = new byte[digestSize];
            this.hashAlg.update(array, 0, array.length);
            this.hashAlg.doFinal(bArr, 0);
            if (allocate.remaining() < digestSize) {
                allocate.put(bArr, 0, allocate.remaining());
            } else {
                allocate.put(bArr);
            }
            this.counter++;
        }
        return allocate.array();
    }
}
