package io.jsonwebtoken.io;

import java.util.Arrays;

/* loaded from: classes3.dex */
final class Base64 {
    private static final char[] BASE64URL_ALPHABET;
    private static final int[] BASE64URL_IALPHABET;
    private static final char[] BASE64_ALPHABET;
    private static final int[] BASE64_IALPHABET;
    static final Base64 DEFAULT;
    private static final int IALPHABET_MAX_INDEX;
    static final Base64 URL_SAFE;
    private final char[] ALPHABET;
    private final int[] IALPHABET;
    private final boolean urlsafe;

    static {
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        BASE64_ALPHABET = charArray;
        BASE64URL_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
        int[] iArr = new int[256];
        BASE64_IALPHABET = iArr;
        int[] iArr2 = new int[256];
        BASE64URL_IALPHABET = iArr2;
        IALPHABET_MAX_INDEX = iArr.length - 1;
        Arrays.fill(iArr, -1);
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            BASE64_IALPHABET[BASE64_ALPHABET[i]] = i;
            BASE64URL_IALPHABET[BASE64URL_ALPHABET[i]] = i;
        }
        BASE64_IALPHABET[61] = 0;
        BASE64URL_IALPHABET[61] = 0;
        DEFAULT = new Base64(false);
        URL_SAFE = new Base64(true);
    }

    private Base64(boolean z) {
        this.urlsafe = z;
        this.ALPHABET = z ? BASE64URL_ALPHABET : BASE64_ALPHABET;
        this.IALPHABET = z ? BASE64URL_IALPHABET : BASE64_IALPHABET;
    }

    private String getName() {
        return this.urlsafe ? "base64url" : "base64";
    }

    private char[] encodeToChar(byte[] bArr, boolean z) {
        int length = bArr != null ? bArr.length : 0;
        if (length == 0) {
            return new char[0];
        }
        int i = (length / 3) * 3;
        int i2 = length - i;
        int i3 = length - 1;
        int i4 = ((i3 / 3) + 1) << 2;
        int i5 = i4 + (z ? ((i4 - 1) / 76) << 1 : 0);
        char[] cArr = new char[this.urlsafe ? i5 - (i2 == 2 ? 1 : i2 == 1 ? 2 : 0) : i5];
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < i) {
            int i9 = i6 + 1;
            int i10 = i9 + 1;
            int i11 = ((bArr[i6] & 255) << 16) | ((bArr[i9] & 255) << 8);
            int i12 = i10 + 1;
            int i13 = i11 | (bArr[i10] & 255);
            int i14 = i7 + 1;
            char[] cArr2 = this.ALPHABET;
            cArr[i7] = cArr2[(i13 >>> 18) & 63];
            int i15 = i14 + 1;
            cArr[i14] = cArr2[(i13 >>> 12) & 63];
            int i16 = i15 + 1;
            cArr[i15] = cArr2[(i13 >>> 6) & 63];
            i7 = i16 + 1;
            cArr[i16] = cArr2[i13 & 63];
            if (z && (i8 = i8 + 1) == 19 && i7 < i5 - 2) {
                int i17 = i7 + 1;
                cArr[i7] = '\r';
                cArr[i17] = '\n';
                i7 = i17 + 1;
                i8 = 0;
            }
            i6 = i12;
        }
        if (i2 > 0) {
            int i18 = ((bArr[i] & 255) << 10) | (i2 == 2 ? (bArr[i3] & 255) << 2 : 0);
            char[] cArr3 = this.ALPHABET;
            cArr[i5 - 4] = cArr3[i18 >> 12];
            cArr[i5 - 3] = cArr3[(i18 >>> 6) & 63];
            if (i2 == 2) {
                cArr[i5 - 2] = cArr3[i18 & 63];
            } else if (!this.urlsafe) {
                cArr[i5 - 2] = '=';
            }
            if (!this.urlsafe) {
                cArr[i5 - 1] = '=';
            }
        }
        return cArr;
    }

    private int ctoi(char c) {
        int i = c > IALPHABET_MAX_INDEX ? -1 : this.IALPHABET[c];
        if (i >= 0) {
            return i;
        }
        throw new DecodingException("Illegal " + getName() + " character: '" + c + "'");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final byte[] decodeFast(char[] cArr) throws DecodingException {
        int i;
        int i2 = 0;
        int length = cArr != null ? cArr.length : 0;
        if (length == 0) {
            return new byte[0];
        }
        int i3 = length - 1;
        int i4 = 0;
        while (i4 < i3 && this.IALPHABET[cArr[i4]] < 0) {
            i4++;
        }
        while (i3 > 0 && this.IALPHABET[cArr[i3]] < 0) {
            i3--;
        }
        int i5 = cArr[i3] == '=' ? cArr[i3 + (-1)] == '=' ? 2 : 1 : 0;
        int i6 = (i3 - i4) + 1;
        if (length > 76) {
            i = (cArr[76] == '\r' ? i6 / 78 : 0) << 1;
        } else {
            i = 0;
        }
        int i7 = (((i6 - i) * 6) >> 3) - i5;
        byte[] bArr = new byte[i7];
        int i8 = (i7 / 3) * 3;
        int i9 = 0;
        int i10 = 0;
        while (i9 < i8) {
            int i11 = i4 + 1;
            int i12 = i11 + 1;
            int ctoi = (ctoi(cArr[i4]) << 18) | (ctoi(cArr[i11]) << 12);
            int i13 = i12 + 1;
            int ctoi2 = ctoi | (ctoi(cArr[i12]) << 6);
            int i14 = i13 + 1;
            int ctoi3 = ctoi2 | ctoi(cArr[i13]);
            int i15 = i9 + 1;
            bArr[i9] = (byte) (ctoi3 >> 16);
            int i16 = i15 + 1;
            bArr[i15] = (byte) (ctoi3 >> 8);
            int i17 = i16 + 1;
            bArr[i16] = (byte) ctoi3;
            if (i > 0 && (i10 = i10 + 1) == 19) {
                i14 += 2;
                i10 = 0;
            }
            i4 = i14;
            i9 = i17;
        }
        if (i9 < i7) {
            int i18 = 0;
            while (i4 <= i3 - i5) {
                i2 |= ctoi(cArr[i4]) << (18 - (i18 * 6));
                i18++;
                i4++;
            }
            int i19 = 16;
            while (i9 < i7) {
                bArr[i9] = (byte) (i2 >> i19);
                i19 -= 8;
                i9++;
            }
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String encodeToString(byte[] bArr, boolean z) {
        return new String(encodeToChar(bArr, z));
    }
}
