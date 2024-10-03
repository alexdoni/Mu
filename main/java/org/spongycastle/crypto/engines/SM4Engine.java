package org.spongycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import kotlin.io.encoding.Base64;
import okio.Utf8;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.util.Pack;

/* loaded from: classes3.dex */
public class SM4Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: X */
    private final int[] f2347X = new int[4];

    /* renamed from: rk */
    private int[] f2348rk;
    private static final byte[] Sbox = {-42, -112, -23, -2, -52, -31, Base64.padSymbol, -73, Ascii.SYN, -74, Ascii.DC4, -62, 40, -5, 44, 5, 43, 103, -102, 118, 42, -66, 4, -61, -86, 68, 19, 38, 73, -122, 6, -103, -100, 66, 80, -12, -111, -17, -104, 122, 51, 84, 11, 67, -19, -49, -84, 98, -28, -77, Ascii.f549FS, -87, -55, 8, -24, -107, Byte.MIN_VALUE, -33, -108, -6, 117, -113, Utf8.REPLACEMENT_BYTE, -90, 71, 7, -89, -4, -13, 115, Ascii.ETB, -70, -125, 89, 60, Ascii.f547EM, -26, -123, 79, -88, 104, 107, -127, -78, 113, 100, -38, -117, -8, -21, Ascii.f555SI, 75, 112, 86, -99, 53, Ascii.f554RS, 36, Ascii.f556SO, 94, 99, 88, -47, -94, 37, 34, 124, 59, 1, 33, 120, -121, -44, 0, 70, 87, -97, -45, 39, 82, 76, 54, 2, -25, -96, -60, -56, -98, -22, -65, -118, -46, SignedBytes.MAX_POWER_OF_TWO, -57, 56, -75, -93, -9, -14, -50, -7, 97, Ascii.NAK, -95, -32, -82, 93, -92, -101, 52, Ascii.SUB, 85, -83, -109, 50, 48, -11, -116, -79, -29, Ascii.f550GS, -10, -30, 46, -126, 102, -54, 96, -64, 41, 35, -85, Ascii.f546CR, 83, 78, 111, -43, -37, 55, 69, -34, -3, -114, 47, 3, -1, 106, 114, 109, 108, 91, 81, -115, Ascii.ESC, -81, -110, -69, -35, PSSSigner.TRAILER_IMPLICIT, Byte.MAX_VALUE, 17, -39, 92, 65, Ascii.f558US, 16, 90, -40, 10, -63, 49, -120, -91, -51, 123, -67, 45, 116, -48, Ascii.DC2, -72, -27, -76, -80, -119, 105, -105, 74, Ascii.f548FF, -106, 119, 126, 101, -71, -15, 9, -59, 110, -58, -124, Ascii.CAN, -16, 125, -20, 58, -36, 77, 32, 121, -18, 95, 62, -41, -53, 57, 72};

    /* renamed from: CK */
    private static final int[] f2345CK = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};

    /* renamed from: FK */
    private static final int[] f2346FK = {-1548633402, 1453994832, 1736282519, -1301273892};

    private int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "SM4";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }

    private int tau(int i) {
        byte[] bArr = Sbox;
        return (bArr[i & 255] & 255) | ((bArr[(i >> 24) & 255] & 255) << 24) | ((bArr[(i >> 16) & 255] & 255) << 16) | ((bArr[(i >> 8) & 255] & 255) << 8);
    }

    private int L_ap(int i) {
        return rotateLeft(i, 23) ^ (rotateLeft(i, 13) ^ i);
    }

    private int T_ap(int i) {
        return L_ap(tau(i));
    }

    private int[] expandKey(boolean z, byte[] bArr) {
        int[] iArr = new int[32];
        int bigEndianToInt = Pack.bigEndianToInt(bArr, 12);
        int[] iArr2 = {Pack.bigEndianToInt(bArr, 0), Pack.bigEndianToInt(bArr, 4), Pack.bigEndianToInt(bArr, 8), bigEndianToInt};
        int i = iArr2[0];
        int[] iArr3 = f2346FK;
        int i2 = i ^ iArr3[0];
        int i3 = iArr2[1] ^ iArr3[1];
        int i4 = iArr2[2] ^ iArr3[2];
        int i5 = bigEndianToInt ^ iArr3[3];
        int[] iArr4 = {i2, i3, i4, i5};
        if (z) {
            int i6 = (i3 ^ i4) ^ i5;
            int[] iArr5 = f2345CK;
            int T_ap = T_ap(i6 ^ iArr5[0]) ^ i2;
            iArr[0] = T_ap;
            int T_ap2 = T_ap((T_ap ^ (iArr4[2] ^ iArr4[3])) ^ iArr5[1]) ^ iArr4[1];
            iArr[1] = T_ap2;
            int T_ap3 = T_ap((T_ap2 ^ (iArr4[3] ^ iArr[0])) ^ iArr5[2]) ^ iArr4[2];
            iArr[2] = T_ap3;
            iArr[3] = T_ap((T_ap3 ^ (iArr[0] ^ iArr[1])) ^ iArr5[3]) ^ iArr4[3];
            for (int i7 = 4; i7 < 32; i7++) {
                iArr[i7] = iArr[i7 - 4] ^ T_ap(((iArr[i7 - 3] ^ iArr[i7 - 2]) ^ iArr[i7 - 1]) ^ f2345CK[i7]);
            }
        } else {
            int i8 = (i3 ^ i4) ^ i5;
            int[] iArr6 = f2345CK;
            int T_ap4 = T_ap(i8 ^ iArr6[0]) ^ i2;
            iArr[31] = T_ap4;
            int T_ap5 = T_ap((T_ap4 ^ (iArr4[2] ^ iArr4[3])) ^ iArr6[1]) ^ iArr4[1];
            iArr[30] = T_ap5;
            int T_ap6 = T_ap((T_ap5 ^ (iArr4[3] ^ iArr[31])) ^ iArr6[2]) ^ iArr4[2];
            iArr[29] = T_ap6;
            iArr[28] = T_ap((T_ap6 ^ (iArr[31] ^ iArr[30])) ^ iArr6[3]) ^ iArr4[3];
            for (int i9 = 27; i9 >= 0; i9--) {
                iArr[i9] = iArr[i9 + 4] ^ T_ap(((iArr[i9 + 3] ^ iArr[i9 + 2]) ^ iArr[i9 + 1]) ^ f2345CK[31 - i9]);
            }
        }
        return iArr;
    }

    /* renamed from: L */
    private int m1527L(int i) {
        return rotateLeft(i, 24) ^ (((rotateLeft(i, 2) ^ i) ^ rotateLeft(i, 10)) ^ rotateLeft(i, 18));
    }

    /* renamed from: T */
    private int m1529T(int i) {
        return m1527L(tau(i));
    }

    /* renamed from: R */
    private void m1528R(int[] iArr, int i) {
        int i2 = i + 1;
        int i3 = i + 2;
        int i4 = i + 3;
        int i5 = iArr[i] ^ iArr[i4];
        iArr[i] = i5;
        int i6 = i5 ^ iArr[i4];
        iArr[i4] = i6;
        iArr[i] = iArr[i] ^ i6;
        int i7 = iArr[i2] ^ iArr[i3];
        iArr[i2] = i7;
        int i8 = i7 ^ iArr[i3];
        iArr[i3] = i8;
        iArr[i2] = i8 ^ iArr[i2];
    }

    /* renamed from: F0 */
    private int m1523F0(int[] iArr, int i) {
        return m1529T((iArr[3] ^ (iArr[1] ^ iArr[2])) ^ i) ^ iArr[0];
    }

    /* renamed from: F1 */
    private int m1524F1(int[] iArr, int i) {
        return m1529T((iArr[0] ^ (iArr[2] ^ iArr[3])) ^ i) ^ iArr[1];
    }

    /* renamed from: F2 */
    private int m1525F2(int[] iArr, int i) {
        return m1529T((iArr[1] ^ (iArr[3] ^ iArr[0])) ^ i) ^ iArr[2];
    }

    /* renamed from: F3 */
    private int m1526F3(int[] iArr, int i) {
        return m1529T((iArr[2] ^ (iArr[0] ^ iArr[1])) ^ i) ^ iArr[3];
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            if (key.length != 16) {
                throw new IllegalArgumentException("SM4 requires a 128 bit key");
            }
            this.f2348rk = expandKey(z, key);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to SM4 init - " + cipherParameters.getClass().getName());
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.f2348rk == null) {
            throw new IllegalStateException("SM4 not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.f2347X[0] = Pack.bigEndianToInt(bArr, i);
        this.f2347X[1] = Pack.bigEndianToInt(bArr, i + 4);
        this.f2347X[2] = Pack.bigEndianToInt(bArr, i + 8);
        this.f2347X[3] = Pack.bigEndianToInt(bArr, i + 12);
        for (int i3 = 0; i3 < 32; i3 += 4) {
            int[] iArr = this.f2347X;
            iArr[0] = m1523F0(iArr, this.f2348rk[i3]);
            int[] iArr2 = this.f2347X;
            iArr2[1] = m1524F1(iArr2, this.f2348rk[i3 + 1]);
            int[] iArr3 = this.f2347X;
            iArr3[2] = m1525F2(iArr3, this.f2348rk[i3 + 2]);
            int[] iArr4 = this.f2347X;
            iArr4[3] = m1526F3(iArr4, this.f2348rk[i3 + 3]);
        }
        m1528R(this.f2347X, 0);
        Pack.intToBigEndian(this.f2347X[0], bArr2, i2);
        Pack.intToBigEndian(this.f2347X[1], bArr2, i2 + 4);
        Pack.intToBigEndian(this.f2347X[2], bArr2, i2 + 8);
        Pack.intToBigEndian(this.f2347X[3], bArr2, i2 + 12);
        return 16;
    }
}
