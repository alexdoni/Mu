package com.google.zxing.datamatrix.encoder;

import com.muglobal.p011ld.BuildConfig;
import com.xsdk.ab_firstbase.net.okhttp3.CallCode;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.Primes;

/* loaded from: classes2.dex */
public final class ErrorCorrection {
    private static final int MODULO_VALUE = 301;
    private static final int[] FACTOR_SETS = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[][] FACTORS = {new int[]{228, 48, 15, 111, 62}, new int[]{23, 68, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA, 240, 92, 254}, new int[]{28, 24, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384, CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256, 223, 248, 116, 255, 110, 61}, new int[]{CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, CallCode.HTTP_RESET, 12, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256, 39, 245, 60, 97, 120}, new int[]{41, CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, 91, 61, 42, CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA, 213, 97, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256, 100, 242}, new int[]{CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, 97, 192, 252, 95, 9, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, 119, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, 45, 18, CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256, 83, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384}, new int[]{83, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256, 100, 39, 188, 75, 66, 61, 241, 213, 109, BuildConfig.VERSION_CODE, 94, 254, 225, 48, 90, 188}, new int[]{15, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256, 244, 9, 233, 71, CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256, 2, 188, CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, 253, 79, 108, 82, 27, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256}, new int[]{52, 190, 88, CallCode.HTTP_RESET, 109, 39, CipherSuite.TLS_PSK_WITH_NULL_SHA256, 21, CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 251, 223, CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA, 21, 5, CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256, 254, 124, 12, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256, 96, 50, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256}, new int[]{Primes.SMALL_FACTOR_LIMIT, 231, 43, 97, 71, 96, 103, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256, 37, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256, 53, 75, 34, 249, 121, 17, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, 110, 213, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA, 120, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, 233, CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256, 93, 255}, new int[]{245, 127, 242, 218, 130, 250, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384, 102, 120, 84, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 220, 251, 80, CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256, 229, 18, 2, 4, 68, 33, 101, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA, 95, 119, 115, 44, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256, 59, 25, 225, 98, 81, 112}, new int[]{77, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA, 31, 19, 38, 22, CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA, 247, 105, 122, 2, 245, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA, 242, 8, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384, 95, 100, 9, CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, 105, 214, 111, 57, 121, 21, 1, 253, 57, 54, 101, 248, CallCode.HTTP_ACCEPTED, 69, 50, CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA, CipherSuite.TLS_PSK_WITH_NULL_SHA384, 226, 5, 9, 5}, new int[]{245, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256, 223, 96, 32, 117, 22, 238, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA, 238, 231, CallCode.HTTP_RESET, 188, 237, 87, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, 106, 16, CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA, 118, 23, 37, 90, CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256, CallCode.HTTP_RESET, 131, 88, 120, 100, 66, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256, 240, 82, 44, CipherSuite.TLS_PSK_WITH_NULL_SHA256, 87, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA, CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384, 69, 213, 92, 253, 225, 19}, new int[]{CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384, 9, 223, 238, 12, 17, 220, 208, 100, 29, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256, 230, 192, 215, 235, CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, 36, 223, 38, 200, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, 54, 228, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, 218, 234, 117, CallCode.HTTP_NOT_AUTHORITATIVE, 29, 232, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, 238, 22, CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA, CallCode.HTTP_CREATED, 117, 62, 207, CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, 13, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA, 245, 127, 67, 247, 28, CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA, 43, CallCode.HTTP_NOT_AUTHORITATIVE, 107, 233, 53, CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA, 46}, new int[]{242, 93, CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384, 50, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, 210, 39, 118, CallCode.HTTP_ACCEPTED, 188, CallCode.HTTP_CREATED, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA, 108, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256, 37, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384, 112, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA, 230, 245, 63, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 190, 250, 106, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384, 221, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384, 64, 114, 71, CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384, 44, CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA, 6, 27, 218, 51, 63, 87, 10, 40, 130, 188, 17, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384, 31, CipherSuite.TLS_PSK_WITH_NULL_SHA256, CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256, 4, 107, 232, 7, 94, CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256, 224, 124, 86, 47, 11, CallCode.HTTP_NO_CONTENT}, new int[]{220, 228, CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384, 89, 251, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, 56, 89, 33, CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA, 244, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 36, 73, 127, 213, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA, 248, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256, 234, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_PSK_WITH_NULL_SHA384, 68, 122, 93, 213, 15, CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256, 227, 236, 66, CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA, CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384, CallCode.HTTP_ACCEPTED, CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 25, 220, 232, 96, 210, 231, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA, 223, 239, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384, 241, 59, 52, CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256, 25, 49, 232, Primes.SMALL_FACTOR_LIMIT, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 64, 54, 108, CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, 63, 96, 103, 82, CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256}};
    private static final int[] LOG = new int[256];
    private static final int[] ALOG = new int[255];

    static {
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            ALOG[i2] = i;
            LOG[i] = i2;
            i <<= 1;
            if (i >= 256) {
                i ^= 301;
            }
        }
    }

    private ErrorCorrection() {
    }

    public static String encodeECC200(String str, SymbolInfo symbolInfo) {
        if (str.length() != symbolInfo.getDataCapacity()) {
            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
        }
        StringBuilder sb = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
        sb.append(str);
        int interleavedBlockCount = symbolInfo.getInterleavedBlockCount();
        if (interleavedBlockCount == 1) {
            sb.append(createECCBlock(str, symbolInfo.getErrorCodewords()));
        } else {
            sb.setLength(sb.capacity());
            int[] iArr = new int[interleavedBlockCount];
            int[] iArr2 = new int[interleavedBlockCount];
            int[] iArr3 = new int[interleavedBlockCount];
            int i = 0;
            while (i < interleavedBlockCount) {
                int i2 = i + 1;
                iArr[i] = symbolInfo.getDataLengthForInterleavedBlock(i2);
                iArr2[i] = symbolInfo.getErrorLengthForInterleavedBlock(i2);
                iArr3[i] = 0;
                if (i > 0) {
                    iArr3[i] = iArr3[i - 1] + iArr[i];
                }
                i = i2;
            }
            for (int i3 = 0; i3 < interleavedBlockCount; i3++) {
                StringBuilder sb2 = new StringBuilder(iArr[i3]);
                for (int i4 = i3; i4 < symbolInfo.getDataCapacity(); i4 += interleavedBlockCount) {
                    sb2.append(str.charAt(i4));
                }
                String createECCBlock = createECCBlock(sb2.toString(), iArr2[i3]);
                int i5 = i3;
                int i6 = 0;
                while (i5 < iArr2[i3] * interleavedBlockCount) {
                    sb.setCharAt(symbolInfo.getDataCapacity() + i5, createECCBlock.charAt(i6));
                    i5 += interleavedBlockCount;
                    i6++;
                }
            }
        }
        return sb.toString();
    }

    private static String createECCBlock(CharSequence charSequence, int i) {
        return createECCBlock(charSequence, 0, charSequence.length(), i);
    }

    private static String createECCBlock(CharSequence charSequence, int i, int i2, int i3) {
        int i4;
        int i5;
        int i6 = 0;
        while (true) {
            int[] iArr = FACTOR_SETS;
            if (i6 >= iArr.length) {
                i6 = -1;
                break;
            }
            if (iArr[i6] == i3) {
                break;
            }
            i6++;
        }
        if (i6 < 0) {
            throw new IllegalArgumentException("Illegal number of error correction codewords specified: ".concat(String.valueOf(i3)));
        }
        int[] iArr2 = FACTORS[i6];
        char[] cArr = new char[i3];
        for (int i7 = 0; i7 < i3; i7++) {
            cArr[i7] = 0;
        }
        for (int i8 = i; i8 < i + i2; i8++) {
            int i9 = i3 - 1;
            int charAt = cArr[i9] ^ charSequence.charAt(i8);
            while (i9 > 0) {
                if (charAt != 0 && (i5 = iArr2[i9]) != 0) {
                    char c = cArr[i9 - 1];
                    int[] iArr3 = ALOG;
                    int[] iArr4 = LOG;
                    cArr[i9] = (char) (iArr3[(iArr4[charAt] + iArr4[i5]) % 255] ^ c);
                } else {
                    cArr[i9] = cArr[i9 - 1];
                }
                i9--;
            }
            if (charAt != 0 && (i4 = iArr2[0]) != 0) {
                int[] iArr5 = ALOG;
                int[] iArr6 = LOG;
                cArr[0] = (char) iArr5[(iArr6[charAt] + iArr6[i4]) % 255];
            } else {
                cArr[0] = 0;
            }
        }
        char[] cArr2 = new char[i3];
        for (int i10 = 0; i10 < i3; i10++) {
            cArr2[i10] = cArr[(i3 - i10) - 1];
        }
        return String.valueOf(cArr2);
    }
}
