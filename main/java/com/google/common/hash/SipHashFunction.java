package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import javax.annotation.CheckForNull;

@Immutable
@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;

    /* renamed from: c */
    private final int f602c;

    /* renamed from: d */
    private final int f603d;

    /* renamed from: k0 */
    private final long f604k0;

    /* renamed from: k1 */
    private final long f605k1;

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SipHashFunction(int i, int i2, long j, long j2) {
        Preconditions.checkArgument(i > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", i);
        Preconditions.checkArgument(i2 > 0, "The number of SipRound iterations (d=%s) during Finalization must be positive.", i2);
        this.f602c = i;
        this.f603d = i2;
        this.f604k0 = j;
        this.f605k1 = j2;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new SipHasher(this.f602c, this.f603d, this.f604k0, this.f605k1);
    }

    public String toString() {
        int i = this.f602c;
        int i2 = this.f603d;
        long j = this.f604k0;
        long j2 = this.f605k1;
        StringBuilder sb = new StringBuilder(81);
        sb.append("Hashing.sipHash");
        sb.append(i);
        sb.append(i2);
        sb.append("(");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append(")");
        return sb.toString();
    }

    public boolean equals(@CheckForNull Object obj) {
        if (!(obj instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction sipHashFunction = (SipHashFunction) obj;
        return this.f602c == sipHashFunction.f602c && this.f603d == sipHashFunction.f603d && this.f604k0 == sipHashFunction.f604k0 && this.f605k1 == sipHashFunction.f605k1;
    }

    public int hashCode() {
        return (int) ((((getClass().hashCode() ^ this.f602c) ^ this.f603d) ^ this.f604k0) ^ this.f605k1);
    }

    /* loaded from: classes2.dex */
    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b */
        private long f606b;

        /* renamed from: c */
        private final int f607c;

        /* renamed from: d */
        private final int f608d;
        private long finalM;

        /* renamed from: v0 */
        private long f609v0;

        /* renamed from: v1 */
        private long f610v1;

        /* renamed from: v2 */
        private long f611v2;

        /* renamed from: v3 */
        private long f612v3;

        SipHasher(int i, int i2, long j, long j2) {
            super(8);
            this.f606b = 0L;
            this.finalM = 0L;
            this.f607c = i;
            this.f608d = i2;
            this.f609v0 = 8317987319222330741L ^ j;
            this.f610v1 = 7237128888997146477L ^ j2;
            this.f611v2 = 7816392313619706465L ^ j;
            this.f612v3 = 8387220255154660723L ^ j2;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected void process(ByteBuffer byteBuffer) {
            this.f606b += 8;
            processM(byteBuffer.getLong());
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected void processRemaining(ByteBuffer byteBuffer) {
            this.f606b += byteBuffer.remaining();
            int i = 0;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (byteBuffer.get() & 255) << i;
                i += 8;
            }
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected HashCode makeHash() {
            long j = this.finalM ^ (this.f606b << 56);
            this.finalM = j;
            processM(j);
            this.f611v2 ^= 255;
            sipRound(this.f608d);
            return HashCode.fromLong(((this.f609v0 ^ this.f610v1) ^ this.f611v2) ^ this.f612v3);
        }

        private void processM(long j) {
            this.f612v3 ^= j;
            sipRound(this.f607c);
            this.f609v0 = j ^ this.f609v0;
        }

        private void sipRound(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                long j = this.f609v0;
                long j2 = this.f610v1;
                this.f609v0 = j + j2;
                this.f611v2 += this.f612v3;
                this.f610v1 = Long.rotateLeft(j2, 13);
                long rotateLeft = Long.rotateLeft(this.f612v3, 16);
                long j3 = this.f610v1;
                long j4 = this.f609v0;
                this.f610v1 = j3 ^ j4;
                this.f612v3 = rotateLeft ^ this.f611v2;
                long rotateLeft2 = Long.rotateLeft(j4, 32);
                long j5 = this.f611v2;
                long j6 = this.f610v1;
                this.f611v2 = j5 + j6;
                this.f609v0 = rotateLeft2 + this.f612v3;
                this.f610v1 = Long.rotateLeft(j6, 17);
                long rotateLeft3 = Long.rotateLeft(this.f612v3, 21);
                long j7 = this.f610v1;
                long j8 = this.f611v2;
                this.f610v1 = j7 ^ j8;
                this.f612v3 = rotateLeft3 ^ this.f609v0;
                this.f611v2 = Long.rotateLeft(j8, 32);
            }
        }
    }
}
