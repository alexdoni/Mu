package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Pack;

/* loaded from: classes3.dex */
public class SipHash implements Mac {

    /* renamed from: c */
    protected final int f2407c;

    /* renamed from: d */
    protected final int f2408d;

    /* renamed from: k0 */
    protected long f2409k0;

    /* renamed from: k1 */
    protected long f2410k1;

    /* renamed from: m */
    protected long f2411m;

    /* renamed from: v0 */
    protected long f2412v0;

    /* renamed from: v1 */
    protected long f2413v1;

    /* renamed from: v2 */
    protected long f2414v2;

    /* renamed from: v3 */
    protected long f2415v3;
    protected int wordCount;
    protected int wordPos;

    protected static long rotateLeft(long j, int i) {
        return (j >>> (-i)) | (j << i);
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return 8;
    }

    public SipHash() {
        this.f2411m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
        this.f2407c = 2;
        this.f2408d = 4;
    }

    public SipHash(int i, int i2) {
        this.f2411m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
        this.f2407c = i;
        this.f2408d = i2;
    }

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        return "SipHash-" + this.f2407c + "-" + this.f2408d;
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("'params' must be an instance of KeyParameter");
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("'params' must be a 128-bit key");
        }
        this.f2409k0 = Pack.littleEndianToLong(key, 0);
        this.f2410k1 = Pack.littleEndianToLong(key, 8);
        reset();
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        this.f2411m = (this.f2411m >>> 8) | ((b & 255) << 56);
        int i = this.wordPos + 1;
        this.wordPos = i;
        if (i == 8) {
            processMessageWord();
            this.wordPos = 0;
        }
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        int i3 = i2 & (-8);
        int i4 = this.wordPos;
        int i5 = 0;
        if (i4 == 0) {
            while (i5 < i3) {
                this.f2411m = Pack.littleEndianToLong(bArr, i + i5);
                processMessageWord();
                i5 += 8;
            }
            while (i5 < i2) {
                this.f2411m = (this.f2411m >>> 8) | ((bArr[i + i5] & 255) << 56);
                i5++;
            }
            this.wordPos = i2 - i3;
            return;
        }
        int i6 = i4 << 3;
        int i7 = 0;
        while (i7 < i3) {
            long littleEndianToLong = Pack.littleEndianToLong(bArr, i + i7);
            this.f2411m = (this.f2411m >>> (-i6)) | (littleEndianToLong << i6);
            processMessageWord();
            this.f2411m = littleEndianToLong;
            i7 += 8;
        }
        while (i7 < i2) {
            this.f2411m = (this.f2411m >>> 8) | ((bArr[i + i7] & 255) << 56);
            int i8 = this.wordPos + 1;
            this.wordPos = i8;
            if (i8 == 8) {
                processMessageWord();
                this.wordPos = 0;
            }
            i7++;
        }
    }

    public long doFinal() throws DataLengthException, IllegalStateException {
        this.f2411m = ((this.f2411m >>> ((7 - this.wordPos) << 3)) >>> 8) | ((((this.wordCount << 3) + r2) & 255) << 56);
        processMessageWord();
        this.f2414v2 ^= 255;
        applySipRounds(this.f2408d);
        long j = ((this.f2412v0 ^ this.f2413v1) ^ this.f2414v2) ^ this.f2415v3;
        reset();
        return j;
    }

    @Override // org.spongycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        Pack.longToLittleEndian(doFinal(), bArr, i);
        return 8;
    }

    @Override // org.spongycastle.crypto.Mac
    public void reset() {
        long j = this.f2409k0;
        this.f2412v0 = 8317987319222330741L ^ j;
        long j2 = this.f2410k1;
        this.f2413v1 = 7237128888997146477L ^ j2;
        this.f2414v2 = j ^ 7816392313619706465L;
        this.f2415v3 = 8387220255154660723L ^ j2;
        this.f2411m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
    }

    protected void processMessageWord() {
        this.wordCount++;
        this.f2415v3 ^= this.f2411m;
        applySipRounds(this.f2407c);
        this.f2412v0 ^= this.f2411m;
    }

    protected void applySipRounds(int i) {
        long j = this.f2412v0;
        long j2 = this.f2413v1;
        long j3 = this.f2414v2;
        long j4 = this.f2415v3;
        for (int i2 = 0; i2 < i; i2++) {
            long j5 = j + j2;
            long j6 = j3 + j4;
            long rotateLeft = rotateLeft(j2, 13) ^ j5;
            long rotateLeft2 = rotateLeft(j4, 16) ^ j6;
            long j7 = j6 + rotateLeft;
            j = rotateLeft(j5, 32) + rotateLeft2;
            j2 = rotateLeft(rotateLeft, 17) ^ j7;
            j4 = rotateLeft(rotateLeft2, 21) ^ j;
            j3 = rotateLeft(j7, 32);
        }
        this.f2412v0 = j;
        this.f2413v1 = j2;
        this.f2414v2 = j3;
        this.f2415v3 = j4;
    }
}
