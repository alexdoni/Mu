package org.spongycastle.crypto.digests;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import kotlin.io.encoding.Base64;
import okio.Utf8;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

/* loaded from: classes3.dex */
public class DSTU7564Digest implements ExtendedDigest, Memoable {
    private static final int BITS_IN_BYTE = 8;
    private static final int NB_1024 = 16;
    private static final int NB_512 = 8;
    private static final int NR_1024 = 14;
    private static final int NR_512 = 10;
    private static final int REDUCTIONAL_POLYNOMIAL = 285;
    private static final int ROWS = 8;
    private static final int STATE_BYTES_SIZE_1024 = 128;
    private static final int STATE_BYTES_SIZE_512 = 64;
    private static final byte[][] mds_matrix = {new byte[]{1, 1, 5, 1, 8, 6, 7, 4}, new byte[]{4, 1, 1, 5, 1, 8, 6, 7}, new byte[]{7, 4, 1, 1, 5, 1, 8, 6}, new byte[]{6, 7, 4, 1, 1, 5, 1, 8}, new byte[]{8, 6, 7, 4, 1, 1, 5, 1}, new byte[]{1, 8, 6, 7, 4, 1, 1, 5}, new byte[]{5, 1, 8, 6, 7, 4, 1, 1}, new byte[]{1, 5, 1, 8, 6, 7, 4, 1}};
    private static final byte[][] sBoxes = {new byte[]{-88, 67, 95, 6, 107, 117, 108, 89, 113, -33, -121, -107, Ascii.ETB, -16, -40, 9, 109, -13, Ascii.f550GS, -53, -55, 77, 44, -81, 121, -32, -105, -3, 111, 75, 69, 57, 62, -35, -93, 79, -76, -74, -102, Ascii.f556SO, Ascii.f558US, -65, Ascii.NAK, -31, 73, -46, -109, -58, -110, 114, -98, 97, -47, 99, -6, -18, -12, Ascii.f547EM, -43, -83, 88, -92, -69, -95, -36, -14, -125, 55, 66, -28, 122, 50, -100, -52, -85, 74, -113, 110, 4, 39, 46, -25, -30, 90, -106, Ascii.SYN, 35, 43, -62, 101, 102, Ascii.f555SI, PSSSigner.TRAILER_IMPLICIT, -87, 71, 65, 52, 72, -4, -73, 106, -120, -91, 83, -122, -7, 91, -37, 56, 123, -61, Ascii.f554RS, 34, 51, 36, 40, 54, -57, -78, 59, -114, 119, -70, -11, Ascii.DC4, -97, 8, 85, -101, 76, -2, 96, 92, -38, Ascii.CAN, 70, -51, 125, 33, -80, Utf8.REPLACEMENT_BYTE, Ascii.ESC, -119, -1, -21, -124, 105, 58, -99, -41, -45, 112, 103, SignedBytes.MAX_POWER_OF_TWO, -75, -34, 93, 48, -111, -79, 120, 17, 1, -27, 0, 104, -104, -96, -59, 2, -90, 116, 45, 11, -94, 118, -77, -66, -50, -67, -82, -23, -118, 49, Ascii.f549FS, -20, -15, -103, -108, -86, -10, 38, 47, -17, -24, -116, 53, 3, -44, Byte.MAX_VALUE, -5, 5, -63, 94, -112, 32, Base64.padSymbol, -126, -9, -22, 10, Ascii.f546CR, 126, -8, 80, Ascii.SUB, -60, 7, 87, -72, 60, 98, -29, -56, -84, 82, 100, 16, -48, -39, 19, Ascii.f548FF, Ascii.DC2, 41, 81, -71, -49, -42, 115, -115, -127, 84, -64, -19, 78, 68, -89, 42, -123, 37, -26, -54, 124, -117, 86, Byte.MIN_VALUE}, new byte[]{-50, -69, -21, -110, -22, -53, 19, -63, -23, 58, -42, -78, -46, -112, Ascii.ETB, -8, 66, Ascii.NAK, 86, -76, 101, Ascii.f549FS, -120, 67, -59, 92, 54, -70, -11, 87, 103, -115, 49, -10, 100, 88, -98, -12, 34, -86, 117, Ascii.f555SI, 2, -79, -33, 109, 115, 77, 124, 38, 46, -9, 8, 93, 68, 62, -97, Ascii.DC4, -56, -82, 84, 16, -40, PSSSigner.TRAILER_IMPLICIT, Ascii.SUB, 107, 105, -13, -67, 51, -85, -6, -47, -101, 104, 78, Ascii.SYN, -107, -111, -18, 76, 99, -114, 91, -52, 60, Ascii.f547EM, -95, -127, 73, 123, -39, 111, 55, 96, -54, -25, 43, 72, -3, -106, 69, -4, 65, Ascii.DC2, Ascii.f546CR, 121, -27, -119, -116, -29, 32, 48, -36, -73, 108, 74, -75, Utf8.REPLACEMENT_BYTE, -105, -44, 98, 45, 6, -92, -91, -125, 95, 42, -38, -55, 0, 126, -94, 85, -65, 17, -43, -100, -49, Ascii.f556SO, 10, Base64.padSymbol, 81, 125, -109, Ascii.ESC, -2, -60, 71, 9, -122, 11, -113, -99, 106, 7, -71, -80, -104, Ascii.CAN, 50, 113, 75, -17, 59, 112, -96, -28, SignedBytes.MAX_POWER_OF_TWO, -1, -61, -87, -26, 120, -7, -117, 70, Byte.MIN_VALUE, Ascii.f554RS, 56, -31, -72, -88, -32, Ascii.f548FF, 35, 118, Ascii.f550GS, 37, 36, 5, -15, 110, -108, 40, -102, -124, -24, -93, 79, 119, -45, -123, -30, 82, -14, -126, 80, 122, 47, 116, 83, -77, 97, -81, 57, 53, -34, -51, Ascii.f558US, -103, -84, -83, 114, 44, -35, -48, -121, -66, 94, -90, -20, 4, -58, 3, 52, -5, -37, 89, -74, -62, 1, -16, 90, -19, -89, 102, 33, Byte.MAX_VALUE, -118, 39, -57, -64, 41, -41}, new byte[]{-109, -39, -102, -75, -104, 34, 69, -4, -70, 106, -33, 2, -97, -36, 81, 89, 74, Ascii.ETB, 43, -62, -108, -12, -69, -93, 98, -28, 113, -44, -51, 112, Ascii.SYN, -31, 73, 60, -64, -40, 92, -101, -83, -123, 83, -95, 122, -56, 45, -32, -47, 114, -90, 44, -60, -29, 118, 120, -73, -76, 9, 59, Ascii.f556SO, 65, 76, -34, -78, -112, 37, -91, -41, 3, 17, 0, -61, 46, -110, -17, 78, Ascii.DC2, -99, 125, -53, 53, 16, -43, 79, -98, 77, -87, 85, -58, -48, 123, Ascii.CAN, -105, -45, 54, -26, 72, 86, -127, -113, 119, -52, -100, -71, -30, -84, -72, 47, Ascii.NAK, -92, 124, -38, 56, Ascii.f554RS, 11, 5, -42, Ascii.DC4, 110, 108, 126, 102, -3, -79, -27, 96, -81, 94, 51, -121, -55, -16, 93, 109, Utf8.REPLACEMENT_BYTE, -120, -115, -57, -9, Ascii.f550GS, -23, -20, -19, Byte.MIN_VALUE, 41, 39, -49, -103, -88, 80, Ascii.f555SI, 55, 36, 40, 48, -107, -46, 62, 91, SignedBytes.MAX_POWER_OF_TWO, -125, -77, 105, 87, Ascii.f558US, 7, Ascii.f549FS, -118, PSSSigner.TRAILER_IMPLICIT, 32, -21, -50, -114, -85, -18, 49, -94, 115, -7, -54, 58, Ascii.SUB, -5, Ascii.f546CR, -63, -2, -6, -14, 111, -67, -106, -35, 67, 82, -74, 8, -13, -82, -66, Ascii.f547EM, -119, 50, 38, -80, -22, 75, 100, -124, -126, 107, -11, 121, -65, 1, 95, 117, 99, Ascii.ESC, 35, Base64.padSymbol, 104, 42, 101, -24, -111, -10, -1, 19, 88, -15, 71, 10, Byte.MAX_VALUE, -59, -89, -25, 97, 90, 6, 70, 68, 66, 4, -96, -37, 57, -122, 84, -86, -116, 52, 33, -117, -8, Ascii.f548FF, 116, 103}, new byte[]{104, -115, -54, 77, 115, 75, 78, 42, -44, 82, 38, -77, 84, Ascii.f554RS, Ascii.f547EM, Ascii.f558US, 34, 3, 70, Base64.padSymbol, 45, 74, 83, -125, 19, -118, -73, -43, 37, 121, -11, -67, 88, 47, Ascii.f546CR, 2, -19, 81, -98, 17, -14, 62, 85, 94, -47, Ascii.SYN, 60, 102, 112, 93, -13, 69, SignedBytes.MAX_POWER_OF_TWO, -52, -24, -108, 86, 8, -50, Ascii.SUB, 58, -46, -31, -33, -75, 56, 110, Ascii.f556SO, -27, -12, -7, -122, -23, 79, -42, -123, 35, -49, 50, -103, 49, Ascii.DC4, -82, -18, -56, 72, -45, 48, -95, -110, 65, -79, Ascii.CAN, -60, 44, 113, 114, 68, Ascii.NAK, -3, 55, -66, 95, -86, -101, -120, -40, -85, -119, -100, -6, 96, -22, PSSSigner.TRAILER_IMPLICIT, 98, Ascii.f548FF, 36, -90, -88, -20, 103, 32, -37, 124, 40, -35, -84, 91, 52, 126, 16, -15, 123, -113, 99, -96, 5, -102, 67, 119, 33, -65, 39, 9, -61, -97, -74, -41, 41, -62, -21, -64, -92, -117, -116, Ascii.f550GS, -5, -1, -63, -78, -105, 46, -8, 101, -10, 117, 7, 4, 73, 51, -28, -39, -71, -48, 66, -57, 108, -112, 0, -114, 111, 80, 1, -59, -38, 71, Utf8.REPLACEMENT_BYTE, -51, 105, -94, -30, 122, -89, -58, -109, Ascii.f555SI, 10, 6, -26, 43, -106, -93, Ascii.f549FS, -81, 106, Ascii.DC2, -124, 57, -25, -80, -126, -9, -2, -99, -121, 92, -127, 53, -34, -76, -91, -4, Byte.MIN_VALUE, -17, -53, -69, 107, 118, -70, 90, 125, 120, 11, -107, -29, -83, 116, -104, 59, 54, 100, 109, -36, -16, 89, -87, 76, Ascii.ETB, Byte.MAX_VALUE, -111, -72, -55, 87, Ascii.ESC, -32, 97}};
    private int blockSize;
    private byte[] buf;
    private int bufOff;
    private int columns;
    private int hashSize;
    private long inputLength;
    private byte[] mixColumnsResult;
    private byte[] padded;
    private int rounds;
    private byte[][] state;
    private byte[] tempBuffer;
    private long[] tempLongBuffer;
    private byte[][] tempState1;
    private byte[][] tempState2;

    private byte multiplyGF(byte b, byte b2) {
        byte b3 = 0;
        for (int i = 0; i < 8; i++) {
            if ((b2 & 1) == 1) {
                b3 = (byte) (b3 ^ b);
            }
            byte b4 = (byte) (b & Byte.MIN_VALUE);
            b = (byte) (b << 1);
            if (b4 == Byte.MIN_VALUE) {
                b = (byte) (b ^ Ascii.f550GS);
            }
            b2 = (byte) (b2 >> 1);
        }
        return b3;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "DSTU7564";
    }

    public DSTU7564Digest(DSTU7564Digest dSTU7564Digest) {
        copyIn(dSTU7564Digest);
    }

    private void copyIn(DSTU7564Digest dSTU7564Digest) {
        this.hashSize = dSTU7564Digest.hashSize;
        this.blockSize = dSTU7564Digest.blockSize;
        this.columns = dSTU7564Digest.columns;
        this.rounds = dSTU7564Digest.rounds;
        this.padded = Arrays.clone(dSTU7564Digest.padded);
        this.state = Arrays.clone(dSTU7564Digest.state);
        this.tempState1 = Arrays.clone(dSTU7564Digest.tempState1);
        this.tempState2 = Arrays.clone(dSTU7564Digest.tempState2);
        this.tempBuffer = Arrays.clone(dSTU7564Digest.tempBuffer);
        this.mixColumnsResult = Arrays.clone(dSTU7564Digest.mixColumnsResult);
        this.tempLongBuffer = Arrays.clone(dSTU7564Digest.tempLongBuffer);
        this.inputLength = dSTU7564Digest.inputLength;
        this.bufOff = dSTU7564Digest.bufOff;
        this.buf = Arrays.clone(dSTU7564Digest.buf);
    }

    public DSTU7564Digest(int i) {
        byte[][] bArr;
        if (i == 256 || i == 384 || i == 512) {
            this.hashSize = i / 8;
            if (i > 256) {
                this.blockSize = 128;
                this.columns = 16;
                this.rounds = 14;
                this.state = new byte[128];
            } else {
                this.blockSize = 64;
                this.columns = 8;
                this.rounds = 10;
                this.state = new byte[64];
            }
            int i2 = 0;
            while (true) {
                bArr = this.state;
                if (i2 >= bArr.length) {
                    break;
                }
                bArr[i2] = new byte[this.columns];
                i2++;
            }
            bArr[0][0] = (byte) bArr.length;
            this.padded = null;
            this.tempState1 = new byte[128];
            this.tempState2 = new byte[128];
            for (int i3 = 0; i3 < this.state.length; i3++) {
                this.tempState1[i3] = new byte[8];
                this.tempState2[i3] = new byte[8];
            }
            this.tempBuffer = new byte[16];
            this.mixColumnsResult = new byte[8];
            this.tempLongBuffer = new long[this.columns];
            this.buf = new byte[this.blockSize];
            return;
        }
        throw new IllegalArgumentException("Hash size is not recommended. Use 256/384/512 instead");
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return this.hashSize;
    }

    @Override // org.spongycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.blockSize;
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.buf;
        int i = this.bufOff;
        int i2 = i + 1;
        this.bufOff = i2;
        bArr[i] = b;
        if (i2 == this.blockSize) {
            processBlock(bArr, 0);
            this.bufOff = 0;
        }
        this.inputLength++;
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.bufOff != 0 && i2 > 0) {
            update(bArr[i]);
            i2--;
            i++;
        }
        if (i2 > 0) {
            while (i2 > this.blockSize) {
                processBlock(bArr, i);
                int i3 = this.blockSize;
                i += i3;
                this.inputLength += i3;
                i2 -= i3;
            }
            while (i2 > 0) {
                update(bArr[i]);
                i2--;
                i++;
            }
        }
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        byte[] pad = pad(this.buf, 0, this.bufOff);
        this.padded = pad;
        int length = pad.length;
        int i2 = 0;
        while (length != 0) {
            processBlock(this.padded, i2);
            int i3 = this.blockSize;
            i2 += i3;
            length -= i3;
        }
        byte[][] bArr2 = new byte[128];
        int i4 = 0;
        while (true) {
            byte[][] bArr3 = this.state;
            if (i4 >= bArr3.length) {
                break;
            }
            byte[] bArr4 = new byte[8];
            bArr2[i4] = bArr4;
            System.arraycopy(bArr3[i4], 0, bArr4, 0, 8);
            i4++;
        }
        for (int i5 = 0; i5 < this.rounds; i5++) {
            for (int i6 = 0; i6 < this.columns; i6++) {
                byte[] bArr5 = bArr2[i6];
                bArr5[0] = (byte) (bArr5[0] ^ ((byte) ((i6 * 16) ^ i5)));
            }
            for (int i7 = 0; i7 < 8; i7++) {
                for (int i8 = 0; i8 < this.columns; i8++) {
                    byte[] bArr6 = bArr2[i8];
                    bArr6[i7] = sBoxes[i7 % 4][bArr6[i7] & 255];
                }
            }
            int i9 = -1;
            int i10 = 0;
            while (i10 < 8) {
                i9 = (i10 == 7 && this.columns == 16) ? 11 : i9 + 1;
                int i11 = 0;
                while (true) {
                    int i12 = this.columns;
                    if (i11 >= i12) {
                        break;
                    }
                    this.tempBuffer[(i11 + i9) % i12] = bArr2[i11][i10];
                    i11++;
                }
                for (int i13 = 0; i13 < this.columns; i13++) {
                    bArr2[i13][i10] = this.tempBuffer[i13];
                }
                i10++;
            }
            for (int i14 = 0; i14 < this.columns; i14++) {
                Arrays.fill(this.mixColumnsResult, (byte) 0);
                for (int i15 = 7; i15 >= 0; i15--) {
                    byte b = 0;
                    for (int i16 = 7; i16 >= 0; i16--) {
                        b = (byte) (b ^ multiplyGF(bArr2[i14][i16], mds_matrix[i15][i16]));
                    }
                    this.mixColumnsResult[i15] = b;
                }
                for (int i17 = 0; i17 < 8; i17++) {
                    bArr2[i14][i17] = this.mixColumnsResult[i17];
                }
            }
        }
        for (int i18 = 0; i18 < 8; i18++) {
            for (int i19 = 0; i19 < this.columns; i19++) {
                byte[] bArr7 = this.state[i19];
                bArr7[i18] = (byte) (bArr7[i18] ^ bArr2[i19][i18]);
            }
        }
        int i20 = this.columns * 8;
        byte[] bArr8 = new byte[i20];
        int i21 = 0;
        for (int i22 = 0; i22 < this.columns; i22++) {
            for (int i23 = 0; i23 < 8; i23++) {
                bArr8[i21] = this.state[i22][i23];
                i21++;
            }
        }
        int i24 = this.hashSize;
        System.arraycopy(bArr8, i20 - i24, bArr, i, i24);
        reset();
        return this.hashSize;
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        byte[][] bArr;
        int i = 0;
        while (true) {
            bArr = this.state;
            if (i >= bArr.length) {
                break;
            }
            bArr[i] = new byte[this.columns];
            i++;
        }
        bArr[0][0] = (byte) bArr.length;
        this.inputLength = 0L;
        this.bufOff = 0;
        Arrays.fill(this.buf, (byte) 0);
        byte[] bArr2 = this.padded;
        if (bArr2 != null) {
            Arrays.fill(bArr2, (byte) 0);
        }
    }

    private void processBlock(byte[] bArr, int i) {
        for (int i2 = 0; i2 < this.state.length; i2++) {
            Arrays.fill(this.tempState1[i2], (byte) 0);
            Arrays.fill(this.tempState2[i2], (byte) 0);
        }
        for (int i3 = 0; i3 < 8; i3++) {
            for (int i4 = 0; i4 < this.columns; i4++) {
                int i5 = (i4 * 8) + i3 + i;
                this.tempState1[i4][i3] = (byte) (this.state[i4][i3] ^ bArr[i5]);
                this.tempState2[i4][i3] = bArr[i5];
            }
        }
        m1454P();
        m1455Q();
        for (int i6 = 0; i6 < 8; i6++) {
            for (int i7 = 0; i7 < this.columns; i7++) {
                byte[] bArr2 = this.state[i7];
                bArr2[i6] = (byte) (bArr2[i6] ^ ((byte) (this.tempState1[i7][i6] ^ this.tempState2[i7][i6])));
            }
        }
    }

    /* renamed from: Q */
    private void m1455Q() {
        for (int i = 0; i < this.rounds; i++) {
            for (int i2 = 0; i2 < this.columns; i2++) {
                this.tempLongBuffer[i2] = Pack.littleEndianToLong(this.tempState2[i2], 0);
                long[] jArr = this.tempLongBuffer;
                long j = jArr[i2] + ((((((this.columns - i2) - 1) * 16) ^ i) << 56) ^ 67818912035696883L);
                jArr[i2] = j;
                Pack.longToLittleEndian(j, this.tempState2[i2], 0);
            }
            for (int i3 = 0; i3 < 8; i3++) {
                for (int i4 = 0; i4 < this.columns; i4++) {
                    byte[] bArr = this.tempState2[i4];
                    bArr[i3] = sBoxes[i3 % 4][bArr[i3] & 255];
                }
            }
            int i5 = -1;
            int i6 = 0;
            while (i6 < 8) {
                i5 = (i6 == 7 && this.columns == 16) ? 11 : i5 + 1;
                int i7 = 0;
                while (true) {
                    int i8 = this.columns;
                    if (i7 >= i8) {
                        break;
                    }
                    this.tempBuffer[(i7 + i5) % i8] = this.tempState2[i7][i6];
                    i7++;
                }
                for (int i9 = 0; i9 < this.columns; i9++) {
                    this.tempState2[i9][i6] = this.tempBuffer[i9];
                }
                i6++;
            }
            for (int i10 = 0; i10 < this.columns; i10++) {
                Arrays.fill(this.mixColumnsResult, (byte) 0);
                for (int i11 = 7; i11 >= 0; i11--) {
                    byte b = 0;
                    for (int i12 = 7; i12 >= 0; i12--) {
                        b = (byte) (b ^ multiplyGF(this.tempState2[i10][i12], mds_matrix[i11][i12]));
                    }
                    this.mixColumnsResult[i11] = b;
                }
                for (int i13 = 0; i13 < 8; i13++) {
                    this.tempState2[i10][i13] = this.mixColumnsResult[i13];
                }
            }
        }
    }

    /* renamed from: P */
    private void m1454P() {
        for (int i = 0; i < this.rounds; i++) {
            for (int i2 = 0; i2 < this.columns; i2++) {
                byte[] bArr = this.tempState1[i2];
                bArr[0] = (byte) (bArr[0] ^ ((byte) ((i2 * 16) ^ i)));
            }
            for (int i3 = 0; i3 < 8; i3++) {
                for (int i4 = 0; i4 < this.columns; i4++) {
                    byte[] bArr2 = this.tempState1[i4];
                    bArr2[i3] = sBoxes[i3 % 4][bArr2[i3] & 255];
                }
            }
            int i5 = -1;
            int i6 = 0;
            while (i6 < 8) {
                i5 = (i6 == 7 && this.columns == 16) ? 11 : i5 + 1;
                int i7 = 0;
                while (true) {
                    int i8 = this.columns;
                    if (i7 >= i8) {
                        break;
                    }
                    this.tempBuffer[(i7 + i5) % i8] = this.tempState1[i7][i6];
                    i7++;
                }
                for (int i9 = 0; i9 < this.columns; i9++) {
                    this.tempState1[i9][i6] = this.tempBuffer[i9];
                }
                i6++;
            }
            for (int i10 = 0; i10 < this.columns; i10++) {
                Arrays.fill(this.mixColumnsResult, (byte) 0);
                for (int i11 = 7; i11 >= 0; i11--) {
                    byte b = 0;
                    for (int i12 = 7; i12 >= 0; i12--) {
                        b = (byte) (b ^ multiplyGF(this.tempState1[i10][i12], mds_matrix[i11][i12]));
                    }
                    this.mixColumnsResult[i11] = b;
                }
                for (int i13 = 0; i13 < 8; i13++) {
                    this.tempState1[i10][i13] = this.mixColumnsResult[i13];
                }
            }
        }
    }

    private byte[] pad(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        int i3 = this.blockSize;
        if (i3 - i2 < 13) {
            bArr2 = new byte[i3 * 2];
        } else {
            bArr2 = new byte[i3];
        }
        System.arraycopy(bArr, i, bArr2, 0, i2);
        bArr2[i2] = Byte.MIN_VALUE;
        Pack.longToLittleEndian(this.inputLength * 8, bArr2, bArr2.length - 12);
        return bArr2;
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new DSTU7564Digest(this);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((DSTU7564Digest) memoable);
    }
}
