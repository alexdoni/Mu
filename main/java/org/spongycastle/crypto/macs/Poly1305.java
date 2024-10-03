package org.spongycastle.crypto.macs;

import org.spongycastle.asn1.cmc.BodyPartID;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Pack;

/* loaded from: classes3.dex */
public class Poly1305 implements Mac {
    private static final int BLOCK_SIZE = 16;
    private final BlockCipher cipher;
    private final byte[] currentBlock;
    private int currentBlockOffset;

    /* renamed from: h0 */
    private int f2389h0;

    /* renamed from: h1 */
    private int f2390h1;

    /* renamed from: h2 */
    private int f2391h2;

    /* renamed from: h3 */
    private int f2392h3;

    /* renamed from: h4 */
    private int f2393h4;

    /* renamed from: k0 */
    private int f2394k0;

    /* renamed from: k1 */
    private int f2395k1;

    /* renamed from: k2 */
    private int f2396k2;

    /* renamed from: k3 */
    private int f2397k3;

    /* renamed from: r0 */
    private int f2398r0;

    /* renamed from: r1 */
    private int f2399r1;

    /* renamed from: r2 */
    private int f2400r2;

    /* renamed from: r3 */
    private int f2401r3;

    /* renamed from: r4 */
    private int f2402r4;

    /* renamed from: s1 */
    private int f2403s1;

    /* renamed from: s2 */
    private int f2404s2;

    /* renamed from: s3 */
    private int f2405s3;

    /* renamed from: s4 */
    private int f2406s4;
    private final byte[] singleByte;

    private static final long mul32x32_64(int i, int i2) {
        return (i & BodyPartID.bodyIdMax) * i2;
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return 16;
    }

    public Poly1305() {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        this.cipher = null;
    }

    public Poly1305(BlockCipher blockCipher) {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit block cipher.");
        }
        this.cipher = blockCipher;
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] bArr;
        if (this.cipher == null) {
            bArr = null;
        } else {
            if (!(cipherParameters instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("Poly1305 requires an IV when used with a block cipher.");
            }
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        }
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Poly1305 requires a key.");
        }
        setKey(((KeyParameter) cipherParameters).getKey(), bArr);
        reset();
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        int i = 16;
        if (this.cipher != null && (bArr2 == null || bArr2.length != 16)) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit IV.");
        }
        int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
        this.f2398r0 = 67108863 & littleEndianToInt;
        int i2 = ((littleEndianToInt >>> 26) | (littleEndianToInt2 << 6)) & 67108611;
        this.f2399r1 = i2;
        int i3 = ((littleEndianToInt2 >>> 20) | (littleEndianToInt3 << 12)) & 67092735;
        this.f2400r2 = i3;
        int i4 = ((littleEndianToInt3 >>> 14) | (littleEndianToInt4 << 18)) & 66076671;
        this.f2401r3 = i4;
        int i5 = (littleEndianToInt4 >>> 8) & 1048575;
        this.f2402r4 = i5;
        this.f2403s1 = i2 * 5;
        this.f2404s2 = i3 * 5;
        this.f2405s3 = i4 * 5;
        this.f2406s4 = i5 * 5;
        BlockCipher blockCipher = this.cipher;
        if (blockCipher != null) {
            byte[] bArr3 = new byte[16];
            blockCipher.init(true, new KeyParameter(bArr, 16, 16));
            this.cipher.processBlock(bArr2, 0, bArr3, 0);
            i = 0;
            bArr = bArr3;
        }
        this.f2394k0 = Pack.littleEndianToInt(bArr, i + 0);
        this.f2395k1 = Pack.littleEndianToInt(bArr, i + 4);
        this.f2396k2 = Pack.littleEndianToInt(bArr, i + 8);
        this.f2397k3 = Pack.littleEndianToInt(bArr, i + 12);
    }

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        if (this.cipher == null) {
            return "Poly1305";
        }
        return "Poly1305-" + this.cipher.getAlgorithmName();
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        byte[] bArr = this.singleByte;
        bArr[0] = b;
        update(bArr, 0, 1);
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        int i3 = 0;
        while (i2 > i3) {
            if (this.currentBlockOffset == 16) {
                processBlock();
                this.currentBlockOffset = 0;
            }
            int min = Math.min(i2 - i3, 16 - this.currentBlockOffset);
            System.arraycopy(bArr, i3 + i, this.currentBlock, this.currentBlockOffset, min);
            i3 += min;
            this.currentBlockOffset += min;
        }
    }

    private void processBlock() {
        int i = this.currentBlockOffset;
        if (i < 16) {
            this.currentBlock[i] = 1;
            for (int i2 = i + 1; i2 < 16; i2++) {
                this.currentBlock[i2] = 0;
            }
        }
        long littleEndianToInt = Pack.littleEndianToInt(this.currentBlock, 0) & BodyPartID.bodyIdMax;
        long littleEndianToInt2 = Pack.littleEndianToInt(this.currentBlock, 4) & BodyPartID.bodyIdMax;
        long littleEndianToInt3 = Pack.littleEndianToInt(this.currentBlock, 8) & BodyPartID.bodyIdMax;
        long littleEndianToInt4 = BodyPartID.bodyIdMax & Pack.littleEndianToInt(this.currentBlock, 12);
        int i3 = (int) (this.f2389h0 + (littleEndianToInt & 67108863));
        this.f2389h0 = i3;
        this.f2390h1 = (int) (this.f2390h1 + ((((littleEndianToInt2 << 32) | littleEndianToInt) >>> 26) & 67108863));
        this.f2391h2 = (int) (this.f2391h2 + (((littleEndianToInt2 | (littleEndianToInt3 << 32)) >>> 20) & 67108863));
        this.f2392h3 = (int) (this.f2392h3 + ((((littleEndianToInt4 << 32) | littleEndianToInt3) >>> 14) & 67108863));
        int i4 = (int) (this.f2393h4 + (littleEndianToInt4 >>> 8));
        this.f2393h4 = i4;
        if (this.currentBlockOffset == 16) {
            this.f2393h4 = i4 + 16777216;
        }
        long mul32x32_64 = mul32x32_64(i3, this.f2398r0) + mul32x32_64(this.f2390h1, this.f2406s4) + mul32x32_64(this.f2391h2, this.f2405s3) + mul32x32_64(this.f2392h3, this.f2404s2) + mul32x32_64(this.f2393h4, this.f2403s1);
        long mul32x32_642 = mul32x32_64(this.f2389h0, this.f2399r1) + mul32x32_64(this.f2390h1, this.f2398r0) + mul32x32_64(this.f2391h2, this.f2406s4) + mul32x32_64(this.f2392h3, this.f2405s3) + mul32x32_64(this.f2393h4, this.f2404s2);
        long mul32x32_643 = mul32x32_64(this.f2389h0, this.f2400r2) + mul32x32_64(this.f2390h1, this.f2399r1) + mul32x32_64(this.f2391h2, this.f2398r0) + mul32x32_64(this.f2392h3, this.f2406s4) + mul32x32_64(this.f2393h4, this.f2405s3);
        long mul32x32_644 = mul32x32_64(this.f2389h0, this.f2401r3) + mul32x32_64(this.f2390h1, this.f2400r2) + mul32x32_64(this.f2391h2, this.f2399r1) + mul32x32_64(this.f2392h3, this.f2398r0) + mul32x32_64(this.f2393h4, this.f2406s4);
        long mul32x32_645 = mul32x32_64(this.f2389h0, this.f2402r4) + mul32x32_64(this.f2390h1, this.f2401r3) + mul32x32_64(this.f2391h2, this.f2400r2) + mul32x32_64(this.f2392h3, this.f2399r1) + mul32x32_64(this.f2393h4, this.f2398r0);
        long j = mul32x32_642 + (mul32x32_64 >>> 26);
        long j2 = mul32x32_643 + (j >>> 26);
        this.f2391h2 = ((int) j2) & 67108863;
        long j3 = mul32x32_644 + (j2 >>> 26);
        this.f2392h3 = ((int) j3) & 67108863;
        long j4 = mul32x32_645 + (j3 >>> 26);
        this.f2393h4 = ((int) j4) & 67108863;
        int i5 = (((int) mul32x32_64) & 67108863) + (((int) (j4 >>> 26)) * 5);
        this.f2390h1 = (((int) j) & 67108863) + (i5 >>> 26);
        this.f2389h0 = i5 & 67108863;
    }

    @Override // org.spongycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        if (i + 16 > bArr.length) {
            throw new OutputLengthException("Output buffer is too short.");
        }
        if (this.currentBlockOffset > 0) {
            processBlock();
        }
        int i2 = this.f2390h1;
        int i3 = this.f2389h0;
        int i4 = i2 + (i3 >>> 26);
        int i5 = this.f2391h2 + (i4 >>> 26);
        int i6 = this.f2392h3 + (i5 >>> 26);
        int i7 = i5 & 67108863;
        int i8 = this.f2393h4 + (i6 >>> 26);
        int i9 = i6 & 67108863;
        int i10 = (i3 & 67108863) + ((i8 >>> 26) * 5);
        int i11 = i8 & 67108863;
        int i12 = (i4 & 67108863) + (i10 >>> 26);
        int i13 = i10 & 67108863;
        int i14 = i13 + 5;
        int i15 = (i14 >>> 26) + i12;
        int i16 = (i15 >>> 26) + i7;
        int i17 = (i16 >>> 26) + i9;
        int i18 = 67108863 & i17;
        int i19 = ((i17 >>> 26) + i11) - 67108864;
        int i20 = (i19 >>> 31) - 1;
        int i21 = ~i20;
        this.f2389h0 = (i13 & i21) | (i14 & 67108863 & i20);
        this.f2390h1 = (i12 & i21) | (i15 & 67108863 & i20);
        this.f2391h2 = (i7 & i21) | (i16 & 67108863 & i20);
        this.f2392h3 = (i18 & i20) | (i9 & i21);
        this.f2393h4 = (i11 & i21) | (i19 & i20);
        long j = ((r1 | (r0 << 26)) & BodyPartID.bodyIdMax) + (this.f2394k0 & BodyPartID.bodyIdMax);
        long j2 = (((r0 >>> 6) | (r3 << 20)) & BodyPartID.bodyIdMax) + (this.f2395k1 & BodyPartID.bodyIdMax);
        long j3 = (((r3 >>> 12) | (r2 << 14)) & BodyPartID.bodyIdMax) + (this.f2396k2 & BodyPartID.bodyIdMax);
        long j4 = (((r2 >>> 18) | (r4 << 8)) & BodyPartID.bodyIdMax) + (BodyPartID.bodyIdMax & this.f2397k3);
        Pack.intToLittleEndian((int) j, bArr, i);
        long j5 = j2 + (j >>> 32);
        Pack.intToLittleEndian((int) j5, bArr, i + 4);
        long j6 = j3 + (j5 >>> 32);
        Pack.intToLittleEndian((int) j6, bArr, i + 8);
        Pack.intToLittleEndian((int) (j4 + (j6 >>> 32)), bArr, i + 12);
        reset();
        return 16;
    }

    @Override // org.spongycastle.crypto.Mac
    public void reset() {
        this.currentBlockOffset = 0;
        this.f2393h4 = 0;
        this.f2392h3 = 0;
        this.f2391h2 = 0;
        this.f2390h1 = 0;
        this.f2389h0 = 0;
    }
}
