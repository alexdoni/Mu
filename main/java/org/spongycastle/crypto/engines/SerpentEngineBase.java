package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

/* loaded from: classes3.dex */
public abstract class SerpentEngineBase implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    static final int PHI = -1640531527;
    static final int ROUNDS = 32;

    /* renamed from: X0 */
    protected int f2350X0;

    /* renamed from: X1 */
    protected int f2351X1;

    /* renamed from: X2 */
    protected int f2352X2;

    /* renamed from: X3 */
    protected int f2353X3;
    protected boolean encrypting;
    protected int[] wKey;

    /* JADX INFO: Access modifiers changed from: protected */
    public static int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    protected static int rotateRight(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    protected abstract void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2);

    protected abstract void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2);

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Serpent";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    protected abstract int[] makeWorkingKey(byte[] bArr);

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.encrypting = z;
            this.wKey = makeWorkingKey(((KeyParameter) cipherParameters).getKey());
        } else {
            throw new IllegalArgumentException("invalid parameter passed to " + getAlgorithmName() + " init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public final int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.wKey == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.encrypting) {
            encryptBlock(bArr, i, bArr2, i2);
            return 16;
        }
        decryptBlock(bArr, i, bArr2, i2);
        return 16;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb0(int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = i3 ^ i5;
        int i7 = i2 ^ i6;
        int i8 = (i4 & i) ^ i7;
        this.f2353X3 = i8;
        int i9 = i ^ (i2 & i5);
        this.f2352X2 = (i3 | i9) ^ i7;
        int i10 = (i6 ^ i9) & i8;
        this.f2351X1 = (~i6) ^ i10;
        this.f2350X0 = (~i9) ^ i10;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib0(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i2 ^ i;
        int i7 = (i5 | i6) ^ i4;
        int i8 = i3 ^ i7;
        int i9 = i6 ^ i8;
        this.f2352X2 = i9;
        int i10 = (i6 & i4) ^ i5;
        int i11 = (i9 & i10) ^ i7;
        this.f2351X1 = i11;
        int i12 = (i & i7) ^ (i11 | i8);
        this.f2353X3 = i12;
        this.f2350X0 = i12 ^ (i10 ^ i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb1(int i, int i2, int i3, int i4) {
        int i5 = (~i) ^ i2;
        int i6 = (i | i5) ^ i3;
        int i7 = i4 ^ i6;
        this.f2352X2 = i7;
        int i8 = i2 ^ (i4 | i5);
        int i9 = i7 ^ i5;
        int i10 = (i6 & i8) ^ i9;
        this.f2353X3 = i10;
        int i11 = i8 ^ i6;
        this.f2351X1 = i10 ^ i11;
        this.f2350X0 = i6 ^ (i11 & i9);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib1(int i, int i2, int i3, int i4) {
        int i5 = i4 ^ i2;
        int i6 = i ^ (i2 & i5);
        int i7 = i5 ^ i6;
        int i8 = i3 ^ i7;
        this.f2353X3 = i8;
        int i9 = i2 ^ (i5 & i6);
        int i10 = i6 ^ (i8 | i9);
        this.f2351X1 = i10;
        int i11 = ~i10;
        int i12 = i9 ^ i8;
        this.f2350X0 = i11 ^ i12;
        this.f2352X2 = (i11 | i12) ^ i7;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb2(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i2 ^ i4;
        int i7 = (i3 & i5) ^ i6;
        this.f2350X0 = i7;
        int i8 = i3 ^ i5;
        int i9 = i2 & (i3 ^ i7);
        int i10 = i8 ^ i9;
        this.f2353X3 = i10;
        int i11 = i ^ ((i9 | i4) & (i7 | i8));
        this.f2352X2 = i11;
        this.f2351X1 = (i11 ^ (i4 | i5)) ^ (i6 ^ i10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib2(int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i4;
        int i6 = ~i5;
        int i7 = i ^ i3;
        int i8 = i3 ^ i5;
        int i9 = (i2 & i8) ^ i7;
        this.f2350X0 = i9;
        int i10 = (((i | i6) ^ i4) | i7) ^ i5;
        this.f2353X3 = i10;
        int i11 = ~i8;
        int i12 = i10 | i9;
        this.f2351X1 = i11 ^ i12;
        this.f2352X2 = (i12 ^ i7) ^ (i4 & i11);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb3(int i, int i2, int i3, int i4) {
        int i5 = i ^ i2;
        int i6 = i & i3;
        int i7 = i | i4;
        int i8 = i3 ^ i4;
        int i9 = i6 | (i5 & i7);
        int i10 = i8 ^ i9;
        this.f2352X2 = i10;
        int i11 = (i7 ^ i2) ^ i9;
        int i12 = i5 ^ (i8 & i11);
        this.f2350X0 = i12;
        int i13 = i12 & i10;
        this.f2351X1 = i11 ^ i13;
        this.f2353X3 = (i2 | i4) ^ (i8 ^ i13);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib3(int i, int i2, int i3, int i4) {
        int i5 = i | i2;
        int i6 = i2 ^ i3;
        int i7 = i ^ (i2 & i6);
        int i8 = i3 ^ i7;
        int i9 = i4 | i7;
        int i10 = i6 ^ i9;
        this.f2350X0 = i10;
        int i11 = (i9 | i6) ^ i4;
        this.f2352X2 = i8 ^ i11;
        int i12 = i5 ^ i11;
        int i13 = i7 ^ (i10 & i12);
        this.f2353X3 = i13;
        this.f2351X1 = i13 ^ (i12 ^ i10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb4(int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = i3 ^ (i4 & i5);
        int i7 = i2 | i6;
        this.f2353X3 = i5 ^ i7;
        int i8 = ~i2;
        int i9 = (i5 | i8) ^ i6;
        this.f2350X0 = i9;
        int i10 = i8 ^ i5;
        int i11 = (i7 & i10) ^ (i9 & i);
        this.f2352X2 = i11;
        this.f2351X1 = (i ^ i6) ^ (i10 & i11);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib4(int i, int i2, int i3, int i4) {
        int i5 = i2 ^ ((i3 | i4) & i);
        int i6 = i3 ^ (i & i5);
        int i7 = i4 ^ i6;
        this.f2351X1 = i7;
        int i8 = ~i;
        int i9 = (i6 & i7) ^ i5;
        this.f2353X3 = i9;
        int i10 = i4 ^ (i7 | i8);
        this.f2350X0 = i9 ^ i10;
        this.f2352X2 = (i8 ^ i7) ^ (i5 & i10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb5(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i2;
        int i7 = i ^ i4;
        int i8 = (i3 ^ i5) ^ (i6 | i7);
        this.f2350X0 = i8;
        int i9 = i4 & i8;
        int i10 = (i6 ^ i8) ^ i9;
        this.f2351X1 = i10;
        int i11 = i7 ^ (i8 | i5);
        this.f2352X2 = (i6 | i9) ^ i11;
        this.f2353X3 = (i11 & i10) ^ (i2 ^ i9);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib5(int i, int i2, int i3, int i4) {
        int i5 = ~i3;
        int i6 = (i2 & i5) ^ i4;
        int i7 = i & i6;
        int i8 = (i2 ^ i5) ^ i7;
        this.f2353X3 = i8;
        int i9 = i8 | i2;
        this.f2351X1 = i6 ^ (i & i9);
        int i10 = i4 | i;
        this.f2350X0 = (i5 ^ i9) ^ i10;
        this.f2352X2 = ((i ^ i3) | i7) ^ (i2 & i10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb6(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i4;
        int i7 = i2 ^ i6;
        int i8 = i3 ^ (i5 | i6);
        int i9 = i2 ^ i8;
        this.f2351X1 = i9;
        int i10 = (i6 | i9) ^ i4;
        int i11 = (i8 & i10) ^ i7;
        this.f2352X2 = i11;
        int i12 = i10 ^ i8;
        this.f2350X0 = i11 ^ i12;
        this.f2353X3 = (i12 & i7) ^ (~i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib6(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i2;
        int i7 = i3 ^ i6;
        int i8 = (i3 | i5) ^ i4;
        this.f2351X1 = i7 ^ i8;
        int i9 = i6 ^ (i7 & i8);
        int i10 = i8 ^ (i2 | i9);
        this.f2353X3 = i10;
        int i11 = i2 | i10;
        this.f2350X0 = i9 ^ i11;
        this.f2352X2 = (i4 & i5) ^ (i11 ^ i7);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb7(int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i3;
        int i6 = (i3 & i5) ^ i4;
        int i7 = i ^ i6;
        int i8 = i2 ^ ((i4 | i5) & i7);
        this.f2351X1 = i8;
        int i9 = (i & i7) ^ i5;
        this.f2353X3 = i9;
        int i10 = (i8 | i6) ^ i7;
        int i11 = i6 ^ (i9 & i10);
        this.f2352X2 = i11;
        this.f2350X0 = (i9 & i11) ^ (~i10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib7(int i, int i2, int i3, int i4) {
        int i5 = (i & i2) | i3;
        int i6 = (i | i2) & i4;
        int i7 = i5 ^ i6;
        this.f2353X3 = i7;
        int i8 = i2 ^ i6;
        int i9 = ((i7 ^ (~i4)) | i8) ^ i;
        this.f2351X1 = i9;
        int i10 = (i8 ^ i3) ^ (i4 | i9);
        this.f2350X0 = i10;
        this.f2352X2 = ((i & i7) ^ i10) ^ (i5 ^ i9);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: LT */
    public final void m1530LT() {
        int rotateLeft = rotateLeft(this.f2350X0, 13);
        int rotateLeft2 = rotateLeft(this.f2352X2, 3);
        int i = (this.f2351X1 ^ rotateLeft) ^ rotateLeft2;
        int i2 = (this.f2353X3 ^ rotateLeft2) ^ (rotateLeft << 3);
        this.f2351X1 = rotateLeft(i, 1);
        int rotateLeft3 = rotateLeft(i2, 7);
        this.f2353X3 = rotateLeft3;
        this.f2350X0 = rotateLeft((rotateLeft ^ this.f2351X1) ^ rotateLeft3, 5);
        this.f2352X2 = rotateLeft((this.f2353X3 ^ rotateLeft2) ^ (this.f2351X1 << 7), 22);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void inverseLT() {
        int rotateRight = (rotateRight(this.f2352X2, 22) ^ this.f2353X3) ^ (this.f2351X1 << 7);
        int rotateRight2 = rotateRight(this.f2350X0, 5) ^ this.f2351X1;
        int i = this.f2353X3;
        int i2 = rotateRight2 ^ i;
        int rotateRight3 = rotateRight(i, 7);
        int rotateRight4 = rotateRight(this.f2351X1, 1);
        this.f2353X3 = (rotateRight3 ^ rotateRight) ^ (i2 << 3);
        this.f2351X1 = (rotateRight4 ^ i2) ^ rotateRight;
        this.f2352X2 = rotateRight(rotateRight, 3);
        this.f2350X0 = rotateRight(i2, 13);
    }
}
