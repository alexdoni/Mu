package org.spongycastle.crypto.digests;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import kotlin.io.encoding.Base64;
import okio.Utf8;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.util.Memoable;

/* loaded from: classes3.dex */
public class MD2Digest implements ExtendedDigest, Memoable {
    private static final int DIGEST_LENGTH = 16;

    /* renamed from: S */
    private static final byte[] f2156S = {41, 46, 67, -55, -94, -40, 124, 1, Base64.padSymbol, 54, 84, -95, -20, -16, 6, 19, 98, -89, 5, -13, -64, -57, 115, -116, -104, -109, 43, -39, PSSSigner.TRAILER_IMPLICIT, 76, -126, -54, Ascii.f554RS, -101, 87, 60, -3, -44, -32, Ascii.SYN, 103, 66, 111, Ascii.CAN, -118, Ascii.ETB, -27, Ascii.DC2, -66, 78, -60, -42, -38, -98, -34, 73, -96, -5, -11, -114, -69, 47, -18, 122, -87, 104, 121, -111, Ascii.NAK, -78, 7, Utf8.REPLACEMENT_BYTE, -108, -62, 16, -119, 11, 34, 95, 33, Byte.MIN_VALUE, Byte.MAX_VALUE, 93, -102, 90, -112, 50, 39, 53, 62, -52, -25, -65, -9, -105, 3, -1, Ascii.f547EM, 48, -77, 72, -91, -75, -47, -41, 94, -110, 42, -84, 86, -86, -58, 79, -72, 56, -46, -106, -92, 125, -74, 118, -4, 107, -30, -100, 116, 4, -15, 69, -99, 112, 89, 100, 113, -121, 32, -122, 91, -49, 101, -26, 45, -88, 2, Ascii.ESC, 96, 37, -83, -82, -80, -71, -10, Ascii.f549FS, 70, 97, 105, 52, SignedBytes.MAX_POWER_OF_TWO, 126, Ascii.f555SI, 85, 71, -93, 35, -35, 81, -81, 58, -61, 92, -7, -50, -70, -59, -22, 38, 44, 83, Ascii.f546CR, 110, -123, 40, -124, 9, -45, -33, -51, -12, 65, -127, 77, 82, 106, -36, 55, -56, 108, -63, -85, -6, 36, -31, 123, 8, Ascii.f548FF, -67, -79, 74, 120, -120, -107, -117, -29, 99, -24, 109, -23, -53, -43, -2, 59, 0, Ascii.f550GS, 57, -14, -17, -73, Ascii.f556SO, 102, 88, -48, -28, -90, 119, 114, -8, -21, 117, 75, 10, 49, 68, 80, -76, -113, -19, Ascii.f558US, Ascii.SUB, -37, -103, -115, 51, -97, 17, -125, Ascii.DC4};

    /* renamed from: C */
    private byte[] f2157C;
    private int COff;

    /* renamed from: M */
    private byte[] f2158M;

    /* renamed from: X */
    private byte[] f2159X;
    private int mOff;
    private int xOff;

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "MD2";
    }

    @Override // org.spongycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 16;
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    public MD2Digest() {
        this.f2159X = new byte[48];
        this.f2158M = new byte[16];
        this.f2157C = new byte[16];
        reset();
    }

    public MD2Digest(MD2Digest mD2Digest) {
        this.f2159X = new byte[48];
        this.f2158M = new byte[16];
        this.f2157C = new byte[16];
        copyIn(mD2Digest);
    }

    private void copyIn(MD2Digest mD2Digest) {
        byte[] bArr = mD2Digest.f2159X;
        System.arraycopy(bArr, 0, this.f2159X, 0, bArr.length);
        this.xOff = mD2Digest.xOff;
        byte[] bArr2 = mD2Digest.f2158M;
        System.arraycopy(bArr2, 0, this.f2158M, 0, bArr2.length);
        this.mOff = mD2Digest.mOff;
        byte[] bArr3 = mD2Digest.f2157C;
        System.arraycopy(bArr3, 0, this.f2157C, 0, bArr3.length);
        this.COff = mD2Digest.COff;
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        int length = this.f2158M.length;
        int i2 = this.mOff;
        byte b = (byte) (length - i2);
        while (true) {
            byte[] bArr2 = this.f2158M;
            if (i2 < bArr2.length) {
                bArr2[i2] = b;
                i2++;
            } else {
                processCheckSum(bArr2);
                processBlock(this.f2158M);
                processBlock(this.f2157C);
                System.arraycopy(this.f2159X, this.xOff, bArr, i, 16);
                reset();
                return 16;
            }
        }
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        this.xOff = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.f2159X;
            if (i == bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        this.mOff = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.f2158M;
            if (i2 == bArr2.length) {
                break;
            }
            bArr2[i2] = 0;
            i2++;
        }
        this.COff = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.f2157C;
            if (i3 == bArr3.length) {
                return;
            }
            bArr3[i3] = 0;
            i3++;
        }
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.f2158M;
        int i = this.mOff;
        int i2 = i + 1;
        this.mOff = i2;
        bArr[i] = b;
        if (i2 == 16) {
            processCheckSum(bArr);
            processBlock(this.f2158M);
            this.mOff = 0;
        }
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.mOff != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > 16) {
            System.arraycopy(bArr, i, this.f2158M, 0, 16);
            processCheckSum(this.f2158M);
            processBlock(this.f2158M);
            i2 -= 16;
            i += 16;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }

    protected void processCheckSum(byte[] bArr) {
        byte b = this.f2157C[15];
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f2157C;
            b = (byte) (f2156S[(b ^ bArr[i]) & 255] ^ bArr2[i]);
            bArr2[i] = b;
        }
    }

    protected void processBlock(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f2159X;
            bArr2[i + 16] = bArr[i];
            bArr2[i + 32] = (byte) (bArr[i] ^ bArr2[i]);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 18; i3++) {
            for (int i4 = 0; i4 < 48; i4++) {
                byte[] bArr3 = this.f2159X;
                byte b = (byte) (f2156S[i2] ^ bArr3[i4]);
                bArr3[i4] = b;
                i2 = b & 255;
            }
            i2 = (i2 + i3) % 256;
        }
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new MD2Digest(this);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((MD2Digest) memoable);
    }
}
