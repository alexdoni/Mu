package org.spongycastle.pqc.crypto.newhope;

import com.tencent.p014av.ptt.PttError;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
class ErrorCorrection {
    static int abs(int i) {
        int i2 = i >> 31;
        return (i ^ i2) - i2;
    }

    ErrorCorrection() {
    }

    /* renamed from: f */
    static int m1549f(int[] iArr, int i, int i2, int i3) {
        int i4 = (i3 * 2730) >> 25;
        int i5 = i4 - ((12288 - (i3 - (i4 * PttError.VOICE_DOWNLOAD_FILE_ACCESSERROR))) >> 31);
        iArr[i] = (i5 >> 1) + (i5 & 1);
        int i6 = i5 - 1;
        iArr[i2] = (i6 >> 1) + (i6 & 1);
        return abs(i3 - ((iArr[i] * 2) * PttError.VOICE_DOWNLOAD_FILE_ACCESSERROR));
    }

    /* renamed from: g */
    static int m1550g(int i) {
        int i2 = (i * 2730) >> 27;
        int i3 = i2 - ((CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA - (i - (CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA * i2))) >> 31);
        return abs((((i3 >> 1) + (i3 & 1)) * 98312) - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void helpRec(short[] sArr, short[] sArr2, byte[] bArr, byte b) {
        short s = 8;
        byte[] bArr2 = new byte[8];
        bArr2[0] = b;
        byte[] bArr3 = new byte[32];
        ChaCha20.process(bArr, bArr2, bArr3, 0, 32);
        int[] iArr = new int[8];
        int i = 0;
        while (i < 256) {
            int i2 = i + 0;
            int i3 = ((bArr3[i >>> 3] >>> (i & 7)) & 1) * 4;
            int i4 = i + 256;
            int i5 = i + 512;
            int i6 = i + 768;
            int m1549f = (24577 - (((m1549f(iArr, 0, 4, (sArr2[i2] * s) + i3) + m1549f(iArr, 1, 5, (sArr2[i4] * s) + i3)) + m1549f(iArr, 2, 6, (sArr2[i5] * s) + i3)) + m1549f(iArr, 3, 7, (sArr2[i6] * 8) + i3))) >> 31;
            int i7 = ~m1549f;
            int i8 = (i7 & iArr[0]) ^ (iArr[4] & m1549f);
            int i9 = (iArr[1] & i7) ^ (iArr[5] & m1549f);
            int i10 = (iArr[2] & i7) ^ (m1549f & iArr[6]);
            int i11 = (i7 & iArr[3]) ^ (iArr[7] & m1549f);
            sArr[i2] = (short) ((i8 - i11) & 3);
            sArr[i4] = (short) ((i9 - i11) & 3);
            sArr[i5] = (short) ((i10 - i11) & 3);
            sArr[i6] = (short) (3 & ((-m1549f) + (i11 * 2)));
            i++;
            s = 8;
        }
    }

    static short LDDecode(int i, int i2, int i3, int i4) {
        return (short) (((((m1550g(i) + m1550g(i2)) + m1550g(i3)) + m1550g(i4)) - 98312) >>> 31);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void rec(byte[] bArr, short[] sArr, short[] sArr2) {
        Arrays.fill(bArr, (byte) 0);
        for (int i = 0; i < 256; i++) {
            int i2 = i + 0;
            int i3 = (sArr[i2] * 8) + 196624;
            int i4 = sArr2[i2] * 2;
            int i5 = i + 768;
            short s = sArr2[i5];
            int i6 = i3 - ((i4 + s) * PttError.VOICE_DOWNLOAD_FILE_ACCESSERROR);
            int i7 = i + 256;
            int i8 = ((sArr[i7] * 8) + 196624) - (((sArr2[i7] * 2) + s) * PttError.VOICE_DOWNLOAD_FILE_ACCESSERROR);
            int i9 = i + 512;
            int i10 = ((sArr[i9] * 8) + 196624) - (((sArr2[i9] * 2) + s) * PttError.VOICE_DOWNLOAD_FILE_ACCESSERROR);
            int i11 = ((sArr[i5] * 8) + 196624) - (s * 12289);
            int i12 = i >>> 3;
            bArr[i12] = (byte) ((LDDecode(i6, i8, i10, i11) << (i & 7)) | bArr[i12]);
        }
    }
}
