package org.spongycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.lang.reflect.Array;
import kotlin.io.encoding.Base64;
import okio.Utf8;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.util.Pack;

/* loaded from: classes3.dex */
public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: m1 */
    private static final int f2283m1 = -2139062144;

    /* renamed from: m2 */
    private static final int f2284m2 = 2139062143;

    /* renamed from: m3 */
    private static final int f2285m3 = 27;

    /* renamed from: m4 */
    private static final int f2286m4 = -1061109568;

    /* renamed from: m5 */
    private static final int f2287m5 = 1061109567;

    /* renamed from: C0 */
    private int f2288C0;

    /* renamed from: C1 */
    private int f2289C1;

    /* renamed from: C2 */
    private int f2290C2;

    /* renamed from: C3 */
    private int f2291C3;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;

    /* renamed from: S */
    private static final byte[] f2281S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, 49, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, 16, -1, -13, -46, -51, Ascii.f548FF, 19, -20, 95, -105, 68, Ascii.ETB, -60, -89, 126, Base64.padSymbol, 100, 93, Ascii.f547EM, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, Ascii.f549FS, -90, -76, -58, -24, -35, 116, Ascii.f558US, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, Ascii.f556SO, 97, 53, 87, -71, -122, -63, Ascii.f550GS, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.f554RS, -121, -23, -50, 85, 40, -33, -116, -95, -119, Ascii.f546CR, -65, -26, 66, 104, 65, -103, 45, Ascii.f555SI, -80, 84, -69, Ascii.SYN};

    /* renamed from: Si */
    private static final byte[] f2282Si = {82, 9, 106, -43, 48, 54, -91, 56, -65, SignedBytes.MAX_POWER_OF_TWO, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, Base64.padSymbol, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, Ascii.NAK, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, Ascii.f554RS, -113, -54, Utf8.REPLACEMENT_BYTE, Ascii.f555SI, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, Ascii.f549FS, 117, -33, 110, 71, -15, Ascii.SUB, 113, Ascii.f550GS, 41, -59, -119, 111, -73, 98, Ascii.f556SO, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, Ascii.f558US, -35, -88, 51, -120, 7, -57, 49, -79, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, Ascii.f547EM, -75, 74, Ascii.f546CR, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, -42, 38, -31, 105, Ascii.DC4, 99, 85, 33, Ascii.f548FF, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 77, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 47, 94, 188, 99, 198, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, 53, 106, 212, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 125, 250, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA};

    private static int FFmulX(int i) {
        return (((i & f2283m1) >>> 7) * 27) ^ ((f2284m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = (f2287m5 & i) << 2;
        int i3 = i & f2286m4;
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (i2 ^ (i4 >>> 2));
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "AES";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }

    private static int mcol(int i) {
        int shift = shift(i, 8);
        int i2 = i ^ shift;
        return FFmulX(i2) ^ (shift ^ shift(i2, 16));
    }

    private static int inv_mcol(int i) {
        int shift = shift(i, 8) ^ i;
        int FFmulX = i ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    private static int subWord(int i) {
        byte[] bArr = f2281S;
        return (bArr[(i >> 24) & 255] << Ascii.CAN) | (bArr[i & 255] & 255) | ((bArr[(i >> 8) & 255] & 255) << 8) | ((bArr[(i >> 16) & 255] & 255) << 16);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >> 2;
        int i2 = i + 6;
        this.ROUNDS = i2;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2 + 1, 4);
        if (i == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt4;
            for (int i3 = 1; i3 <= 10; i3++) {
                littleEndianToInt ^= subWord(shift(littleEndianToInt4, 8)) ^ rcon[i3 - 1];
                int[] iArr2 = iArr[i3];
                iArr2[0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                iArr2[1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                iArr2[2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                iArr2[3] = littleEndianToInt4;
            }
        } else if (i == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt9;
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt10;
            int subWord = littleEndianToInt5 ^ (subWord(shift(littleEndianToInt10, 8)) ^ 1);
            int[] iArr3 = iArr[1];
            iArr3[2] = subWord;
            int i4 = littleEndianToInt6 ^ subWord;
            iArr3[3] = i4;
            int i5 = littleEndianToInt7 ^ i4;
            int[] iArr4 = iArr[2];
            iArr4[0] = i5;
            int i6 = littleEndianToInt8 ^ i5;
            iArr4[1] = i6;
            int i7 = littleEndianToInt9 ^ i6;
            iArr4[2] = i7;
            int i8 = littleEndianToInt10 ^ i7;
            iArr4[3] = i8;
            int i9 = 2;
            for (int i10 = 3; i10 < 12; i10 += 3) {
                int subWord2 = subWord(shift(i8, 8)) ^ i9;
                int i11 = i9 << 1;
                int i12 = subWord ^ subWord2;
                int[] iArr5 = iArr[i10];
                iArr5[0] = i12;
                int i13 = i4 ^ i12;
                iArr5[1] = i13;
                int i14 = i5 ^ i13;
                iArr5[2] = i14;
                int i15 = i6 ^ i14;
                iArr5[3] = i15;
                int i16 = i7 ^ i15;
                int i17 = i10 + 1;
                int[] iArr6 = iArr[i17];
                iArr6[0] = i16;
                int i18 = i8 ^ i16;
                iArr6[1] = i18;
                int subWord3 = subWord(shift(i18, 8)) ^ i11;
                i9 = i11 << 1;
                subWord = i12 ^ subWord3;
                int[] iArr7 = iArr[i17];
                iArr7[2] = subWord;
                i4 = i13 ^ subWord;
                iArr7[3] = i4;
                i5 = i14 ^ i4;
                int[] iArr8 = iArr[i10 + 2];
                iArr8[0] = i5;
                i6 = i15 ^ i5;
                iArr8[1] = i6;
                i7 = i16 ^ i6;
                iArr8[2] = i7;
                i8 = i18 ^ i7;
                iArr8[3] = i8;
            }
            int subWord4 = (subWord(shift(i8, 8)) ^ i9) ^ subWord;
            int[] iArr9 = iArr[12];
            iArr9[0] = subWord4;
            int i19 = subWord4 ^ i4;
            iArr9[1] = i19;
            int i20 = i19 ^ i5;
            iArr9[2] = i20;
            iArr9[3] = i20 ^ i6;
        } else if (i == 8) {
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = littleEndianToInt18;
            int i21 = 1;
            for (int i22 = 2; i22 < 14; i22 += 2) {
                int subWord5 = subWord(shift(littleEndianToInt18, 8)) ^ i21;
                i21 <<= 1;
                littleEndianToInt11 ^= subWord5;
                int[] iArr10 = iArr[i22];
                iArr10[0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                iArr10[1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr10[2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr10[3] = littleEndianToInt14;
                littleEndianToInt15 ^= subWord(littleEndianToInt14);
                int[] iArr11 = iArr[i22 + 1];
                iArr11[0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                iArr11[1] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                iArr11[2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                iArr11[3] = littleEndianToInt18;
            }
            int subWord6 = (subWord(shift(littleEndianToInt18, 8)) ^ i21) ^ littleEndianToInt11;
            int[] iArr12 = iArr[14];
            iArr12[0] = subWord6;
            int i23 = subWord6 ^ littleEndianToInt12;
            iArr12[1] = i23;
            int i24 = i23 ^ littleEndianToInt13;
            iArr12[2] = i24;
            iArr12[3] = i24 ^ littleEndianToInt14;
        } else {
            throw new IllegalStateException("Should never get here");
        }
        if (!z) {
            for (int i25 = 1; i25 < this.ROUNDS; i25++) {
                for (int i26 = 0; i26 < 4; i26++) {
                    int[] iArr13 = iArr[i25];
                    iArr13[i26] = inv_mcol(iArr13[i26]);
                }
            }
        }
        return iArr;
    }

    public AESLightEngine() {
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
        } else {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.WorkingKey == null) {
            throw new IllegalStateException("AES engine not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.forEncryption) {
            unpackBlock(bArr, i);
            encryptBlock(this.WorkingKey);
            packBlock(bArr2, i2);
            return 16;
        }
        unpackBlock(bArr, i);
        decryptBlock(this.WorkingKey);
        packBlock(bArr2, i2);
        return 16;
    }

    private void unpackBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i] & 255) | ((bArr[i2] & 255) << 8);
        int i5 = i3 + 1;
        int i6 = i4 | ((bArr[i3] & 255) << 16);
        int i7 = i5 + 1;
        this.f2288C0 = i6 | (bArr[i5] << Ascii.CAN);
        int i8 = i7 + 1;
        int i9 = bArr[i7] & 255;
        int i10 = i8 + 1;
        int i11 = ((bArr[i8] & 255) << 8) | i9;
        int i12 = i10 + 1;
        int i13 = i11 | ((bArr[i10] & 255) << 16);
        int i14 = i12 + 1;
        this.f2289C1 = i13 | (bArr[i12] << Ascii.CAN);
        int i15 = i14 + 1;
        int i16 = bArr[i14] & 255;
        int i17 = i15 + 1;
        int i18 = ((bArr[i15] & 255) << 8) | i16;
        int i19 = i17 + 1;
        int i20 = i18 | ((bArr[i17] & 255) << 16);
        int i21 = i19 + 1;
        this.f2290C2 = i20 | (bArr[i19] << Ascii.CAN);
        int i22 = i21 + 1;
        int i23 = bArr[i21] & 255;
        int i24 = i22 + 1;
        int i25 = ((bArr[i22] & 255) << 8) | i23;
        int i26 = i25 | ((bArr[i24] & 255) << 16);
        this.f2291C3 = (bArr[i24 + 1] << Ascii.CAN) | i26;
    }

    private void packBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = this.f2288C0;
        bArr[i] = (byte) i3;
        int i4 = i2 + 1;
        bArr[i2] = (byte) (i3 >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i3 >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i3 >> 24);
        int i7 = i6 + 1;
        int i8 = this.f2289C1;
        bArr[i6] = (byte) i8;
        int i9 = i7 + 1;
        bArr[i7] = (byte) (i8 >> 8);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >> 16);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i8 >> 24);
        int i12 = i11 + 1;
        int i13 = this.f2290C2;
        bArr[i11] = (byte) i13;
        int i14 = i12 + 1;
        bArr[i12] = (byte) (i13 >> 8);
        int i15 = i14 + 1;
        bArr[i14] = (byte) (i13 >> 16);
        int i16 = i15 + 1;
        bArr[i15] = (byte) (i13 >> 24);
        int i17 = i16 + 1;
        int i18 = this.f2291C3;
        bArr[i16] = (byte) i18;
        int i19 = i17 + 1;
        bArr[i17] = (byte) (i18 >> 8);
        bArr[i19] = (byte) (i18 >> 16);
        bArr[i19 + 1] = (byte) (i18 >> 24);
    }

    private void encryptBlock(int[][] iArr) {
        int i = this.f2288C0;
        int[] iArr2 = iArr[0];
        int i2 = i ^ iArr2[0];
        int i3 = this.f2289C1 ^ iArr2[1];
        int i4 = this.f2290C2 ^ iArr2[2];
        int i5 = iArr2[3] ^ this.f2291C3;
        int i6 = 1;
        while (i6 < this.ROUNDS - 1) {
            byte[] bArr = f2281S;
            int mcol = mcol((((bArr[i2 & 255] & 255) ^ ((bArr[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i4 >> 16) & 255] & 255) << 16)) ^ (bArr[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][0];
            int mcol2 = mcol((((bArr[i3 & 255] & 255) ^ ((bArr[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr[(i2 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][1];
            int mcol3 = mcol((((bArr[i4 & 255] & 255) ^ ((bArr[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i2 >> 16) & 255] & 255) << 16)) ^ (bArr[(i3 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][2];
            int mcol4 = mcol(((((bArr[(i2 >> 8) & 255] & 255) << 8) ^ (bArr[i5 & 255] & 255)) ^ ((bArr[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr[(i4 >> 24) & 255] << Ascii.CAN));
            int i7 = i6 + 1;
            int i8 = mcol4 ^ iArr[i6][3];
            int mcol5 = mcol((((bArr[mcol & 255] & 255) ^ ((bArr[(mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr[(mcol3 >> 16) & 255] & 255) << 16)) ^ (bArr[(i8 >> 24) & 255] << Ascii.CAN)) ^ iArr[i7][0];
            int mcol6 = mcol((((bArr[mcol2 & 255] & 255) ^ ((bArr[(mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i8 >> 16) & 255] & 255) << 16)) ^ (bArr[(mcol >> 24) & 255] << Ascii.CAN)) ^ iArr[i7][1];
            int mcol7 = mcol((((bArr[mcol3 & 255] & 255) ^ ((bArr[(i8 >> 8) & 255] & 255) << 8)) ^ ((bArr[(mcol >> 16) & 255] & 255) << 16)) ^ (bArr[(mcol2 >> 24) & 255] << Ascii.CAN)) ^ iArr[i7][2];
            int mcol8 = mcol((((bArr[i8 & 255] & 255) ^ ((bArr[(mcol >> 8) & 255] & 255) << 8)) ^ ((bArr[(mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr[(mcol3 >> 24) & 255] << Ascii.CAN));
            int i9 = i7 + 1;
            i5 = iArr[i7][3] ^ mcol8;
            i2 = mcol5;
            i3 = mcol6;
            i4 = mcol7;
            i6 = i9;
        }
        byte[] bArr2 = f2281S;
        int mcol9 = mcol((((bArr2[i2 & 255] & 255) ^ ((bArr2[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i4 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][0];
        int mcol10 = mcol((((bArr2[i3 & 255] & 255) ^ ((bArr2[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i2 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][1];
        int mcol11 = mcol((((bArr2[i4 & 255] & 255) ^ ((bArr2[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i2 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i3 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][2];
        int mcol12 = mcol(((((bArr2[(i2 >> 8) & 255] & 255) << 8) ^ (bArr2[i5 & 255] & 255)) ^ ((bArr2[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][3];
        int i10 = (((bArr2[mcol9 & 255] & 255) ^ ((bArr2[(mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(mcol11 >> 16) & 255] & 255) << 16)) ^ (bArr2[(mcol12 >> 24) & 255] << Ascii.CAN);
        int[] iArr3 = iArr[i6 + 1];
        this.f2288C0 = iArr3[0] ^ i10;
        this.f2289C1 = ((((bArr2[mcol10 & 255] & 255) ^ ((bArr2[(mcol11 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(mcol12 >> 16) & 255] & 255) << 16)) ^ (bArr2[(mcol9 >> 24) & 255] << Ascii.CAN)) ^ iArr3[1];
        this.f2290C2 = ((((bArr2[mcol11 & 255] & 255) ^ ((bArr2[(mcol12 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(mcol9 >> 16) & 255] & 255) << 16)) ^ (bArr2[(mcol10 >> 24) & 255] << Ascii.CAN)) ^ iArr3[2];
        this.f2291C3 = iArr3[3] ^ ((((bArr2[mcol12 & 255] & 255) ^ ((bArr2[(mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(mcol10 >> 16) & 255] & 255) << 16)) ^ (bArr2[(mcol11 >> 24) & 255] << Ascii.CAN));
    }

    private void decryptBlock(int[][] iArr) {
        int i = this.f2288C0;
        int i2 = this.ROUNDS;
        int[] iArr2 = iArr[i2];
        int i3 = i ^ iArr2[0];
        int i4 = this.f2289C1 ^ iArr2[1];
        int i5 = this.f2290C2 ^ iArr2[2];
        int i6 = i2 - 1;
        int i7 = iArr2[3] ^ this.f2291C3;
        while (i6 > 1) {
            byte[] bArr = f2282Si;
            int inv_mcol = inv_mcol((((bArr[i3 & 255] & 255) ^ ((bArr[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr[(i4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][0];
            int inv_mcol2 = inv_mcol((((bArr[i4 & 255] & 255) ^ ((bArr[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i7 >> 16) & 255] & 255) << 16)) ^ (bArr[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][1];
            int inv_mcol3 = inv_mcol((((bArr[i5 & 255] & 255) ^ ((bArr[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][2];
            int inv_mcol4 = inv_mcol((bArr[(i3 >> 24) & 255] << Ascii.CAN) ^ (((bArr[i7 & 255] & 255) ^ ((bArr[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i4 >> 16) & 255] & 255) << 16)));
            int i8 = i6 - 1;
            int i9 = inv_mcol4 ^ iArr[i6][3];
            int inv_mcol5 = inv_mcol((((bArr[inv_mcol & 255] & 255) ^ ((bArr[(i9 >> 8) & 255] & 255) << 8)) ^ ((bArr[(inv_mcol3 >> 16) & 255] & 255) << 16)) ^ (bArr[(inv_mcol2 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][0];
            i4 = inv_mcol((((bArr[inv_mcol2 & 255] & 255) ^ ((bArr[(inv_mcol >> 8) & 255] & 255) << 8)) ^ ((bArr[(i9 >> 16) & 255] & 255) << 16)) ^ (bArr[(inv_mcol3 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][1];
            i5 = inv_mcol((((bArr[inv_mcol3 & 255] & 255) ^ ((bArr[(inv_mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr[(inv_mcol >> 16) & 255] & 255) << 16)) ^ (bArr[(i9 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][2];
            int inv_mcol6 = inv_mcol((((bArr[i9 & 255] & 255) ^ ((bArr[(inv_mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(inv_mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr[(inv_mcol >> 24) & 255] << Ascii.CAN));
            int i10 = i8 - 1;
            i7 = iArr[i8][3] ^ inv_mcol6;
            i3 = inv_mcol5;
            i6 = i10;
        }
        byte[] bArr2 = f2282Si;
        int inv_mcol7 = inv_mcol((((bArr2[i3 & 255] & 255) ^ ((bArr2[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][0];
        int inv_mcol8 = inv_mcol((((bArr2[i4 & 255] & 255) ^ ((bArr2[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i7 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][1];
        int inv_mcol9 = inv_mcol((((bArr2[i5 & 255] & 255) ^ ((bArr2[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][2];
        int inv_mcol10 = inv_mcol((bArr2[(i3 >> 24) & 255] << Ascii.CAN) ^ (((bArr2[i7 & 255] & 255) ^ ((bArr2[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i6][3];
        int i11 = (((bArr2[inv_mcol7 & 255] & 255) ^ ((bArr2[(inv_mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(inv_mcol9 >> 16) & 255] & 255) << 16)) ^ (bArr2[(inv_mcol8 >> 24) & 255] << Ascii.CAN);
        int[] iArr3 = iArr[0];
        this.f2288C0 = i11 ^ iArr3[0];
        this.f2289C1 = ((((bArr2[inv_mcol8 & 255] & 255) ^ ((bArr2[(inv_mcol7 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(inv_mcol10 >> 16) & 255] & 255) << 16)) ^ (bArr2[(inv_mcol9 >> 24) & 255] << Ascii.CAN)) ^ iArr3[1];
        this.f2290C2 = ((((bArr2[inv_mcol9 & 255] & 255) ^ ((bArr2[(inv_mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(inv_mcol7 >> 16) & 255] & 255) << 16)) ^ (bArr2[(inv_mcol10 >> 24) & 255] << Ascii.CAN)) ^ iArr3[2];
        this.f2291C3 = iArr3[3] ^ ((((bArr2[inv_mcol10 & 255] & 255) ^ ((bArr2[(inv_mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(inv_mcol8 >> 16) & 255] & 255) << 16)) ^ (bArr2[(inv_mcol7 >> 24) & 255] << Ascii.CAN));
    }
}
