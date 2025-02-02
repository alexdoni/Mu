package org.spongycastle.jce.provider;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

/* compiled from: BrokenPBE.java */
/* loaded from: classes3.dex */
class OldPKCS12ParametersGenerator extends PBEParametersGenerator {
    public static final int IV_MATERIAL = 2;
    public static final int KEY_MATERIAL = 1;
    public static final int MAC_MATERIAL = 3;
    private Digest digest;

    /* renamed from: u */
    private int f2587u;

    /* renamed from: v */
    private int f2588v;

    public OldPKCS12ParametersGenerator(Digest digest) {
        this.digest = digest;
        if (digest instanceof MD5Digest) {
            this.f2587u = 16;
            this.f2588v = 64;
            return;
        }
        if (digest instanceof SHA1Digest) {
            this.f2587u = 20;
            this.f2588v = 64;
        } else if (digest instanceof RIPEMD160Digest) {
            this.f2587u = 20;
            this.f2588v = 64;
        } else {
            throw new IllegalArgumentException("Digest " + digest.getAlgorithmName() + " unsupported");
        }
    }

    private void adjust(byte[] bArr, int i, byte[] bArr2) {
        int i2 = (bArr2[bArr2.length - 1] & 255) + (bArr[(bArr2.length + i) - 1] & 255) + 1;
        bArr[(bArr2.length + i) - 1] = (byte) i2;
        int i3 = i2 >>> 8;
        for (int length = bArr2.length - 2; length >= 0; length--) {
            int i4 = i + length;
            int i5 = i3 + (bArr2[length] & 255) + (bArr[i4] & 255);
            bArr[i4] = (byte) i5;
            i3 = i5 >>> 8;
        }
    }

    private byte[] generateDerivedKey(int i, int i2) {
        byte[] bArr;
        byte[] bArr2;
        int i3;
        int i4 = this.f2588v;
        byte[] bArr3 = new byte[i4];
        byte[] bArr4 = new byte[i2];
        int i5 = 0;
        for (int i6 = 0; i6 != i4; i6++) {
            bArr3[i6] = (byte) i;
        }
        int i7 = 1;
        if (this.salt == null || this.salt.length == 0) {
            bArr = new byte[0];
        } else {
            int i8 = this.f2588v;
            int length = this.salt.length;
            int i9 = this.f2588v;
            int i10 = i8 * (((length + i9) - 1) / i9);
            bArr = new byte[i10];
            for (int i11 = 0; i11 != i10; i11++) {
                bArr[i11] = this.salt[i11 % this.salt.length];
            }
        }
        if (this.password == null || this.password.length == 0) {
            bArr2 = new byte[0];
        } else {
            int i12 = this.f2588v;
            int length2 = this.password.length;
            int i13 = this.f2588v;
            int i14 = i12 * (((length2 + i13) - 1) / i13);
            bArr2 = new byte[i14];
            for (int i15 = 0; i15 != i14; i15++) {
                bArr2[i15] = this.password[i15 % this.password.length];
            }
        }
        int length3 = bArr.length + bArr2.length;
        byte[] bArr5 = new byte[length3];
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr5, bArr.length, bArr2.length);
        int i16 = this.f2588v;
        byte[] bArr6 = new byte[i16];
        int i17 = this.f2587u;
        int i18 = ((i2 + i17) - 1) / i17;
        int i19 = 1;
        while (i19 <= i18) {
            int i20 = this.f2587u;
            byte[] bArr7 = new byte[i20];
            this.digest.update(bArr3, i5, i4);
            this.digest.update(bArr5, i5, length3);
            this.digest.doFinal(bArr7, i5);
            for (int i21 = i7; i21 != this.iterationCount; i21++) {
                this.digest.update(bArr7, i5, i20);
                this.digest.doFinal(bArr7, i5);
            }
            for (int i22 = i5; i22 != i16; i22++) {
                bArr6[i19] = bArr7[i22 % i20];
            }
            int i23 = i5;
            while (true) {
                int i24 = this.f2588v;
                if (i23 == length3 / i24) {
                    break;
                }
                adjust(bArr5, i24 * i23, bArr6);
                i23++;
            }
            if (i19 == i18) {
                int i25 = i19 - 1;
                int i26 = this.f2587u;
                int i27 = i25 * i26;
                int i28 = i2 - (i25 * i26);
                i3 = 0;
                System.arraycopy(bArr7, 0, bArr4, i27, i28);
            } else {
                i3 = 0;
                System.arraycopy(bArr7, 0, bArr4, (i19 - 1) * this.f2587u, i20);
            }
            i19++;
            i5 = i3;
            i7 = 1;
        }
        return bArr4;
    }

    @Override // org.spongycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i) {
        int i2 = i / 8;
        return new KeyParameter(generateDerivedKey(1, i2), 0, i2);
    }

    @Override // org.spongycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int i, int i2) {
        int i3 = i / 8;
        int i4 = i2 / 8;
        byte[] generateDerivedKey = generateDerivedKey(1, i3);
        return new ParametersWithIV(new KeyParameter(generateDerivedKey, 0, i3), generateDerivedKey(2, i4), 0, i4);
    }

    @Override // org.spongycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedMacParameters(int i) {
        int i2 = i / 8;
        return new KeyParameter(generateDerivedKey(3, i2), 0, i2);
    }
}
