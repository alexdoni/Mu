package org.spongycastle.pqc.crypto.gmss.util;

import java.lang.reflect.Array;
import org.spongycastle.crypto.Digest;

/* loaded from: classes3.dex */
public class WinternitzOTSignature {
    private int checksumsize;
    private GMSSRandom gmssRandom;
    private int keysize;
    private int mdsize;
    private Digest messDigestOTS;
    private int messagesize;
    private byte[][] privateKeyOTS;

    /* renamed from: w */
    private int f2751w;

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public WinternitzOTSignature(byte[] bArr, Digest digest, int i) {
        this.f2751w = i;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d = i;
        int ceil = (int) Math.ceil((r8 << 3) / d);
        this.messagesize = ceil;
        int log = getLog((ceil << i) + 1);
        this.checksumsize = log;
        int ceil2 = this.messagesize + ((int) Math.ceil(log / d));
        this.keysize = ceil2;
        this.privateKeyOTS = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, ceil2, this.mdsize);
        int i2 = this.mdsize;
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        for (int i3 = 0; i3 < this.keysize; i3++) {
            this.privateKeyOTS[i3] = this.gmssRandom.nextSeed(bArr2);
        }
    }

    public byte[][] getPrivateKey() {
        return this.privateKeyOTS;
    }

    public byte[] getPublicKey() {
        int i = this.keysize;
        int i2 = this.mdsize;
        int i3 = i * i2;
        byte[] bArr = new byte[i3];
        byte[] bArr2 = new byte[i2];
        int i4 = 1 << this.f2751w;
        for (int i5 = 0; i5 < this.keysize; i5++) {
            Digest digest = this.messDigestOTS;
            byte[] bArr3 = this.privateKeyOTS[i5];
            digest.update(bArr3, 0, bArr3.length);
            byte[] bArr4 = new byte[this.messDigestOTS.getDigestSize()];
            this.messDigestOTS.doFinal(bArr4, 0);
            for (int i6 = 2; i6 < i4; i6++) {
                this.messDigestOTS.update(bArr4, 0, bArr4.length);
                bArr4 = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(bArr4, 0);
            }
            int i7 = this.mdsize;
            System.arraycopy(bArr4, 0, bArr, i7 * i5, i7);
        }
        this.messDigestOTS.update(bArr, 0, i3);
        byte[] bArr5 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(bArr5, 0);
        return bArr5;
    }

    public byte[] getSignature(byte[] bArr) {
        int i;
        int i2 = this.keysize;
        int i3 = this.mdsize;
        byte[] bArr2 = new byte[i2 * i3];
        byte[] bArr3 = new byte[i3];
        this.messDigestOTS.update(bArr, 0, bArr.length);
        int digestSize = this.messDigestOTS.getDigestSize();
        byte[] bArr4 = new byte[digestSize];
        this.messDigestOTS.doFinal(bArr4, 0);
        int i4 = this.f2751w;
        int i5 = 8;
        if (8 % i4 == 0) {
            int i6 = 8 / i4;
            int i7 = (1 << i4) - 1;
            byte[] bArr5 = new byte[this.mdsize];
            int i8 = 0;
            int i9 = 0;
            for (int i10 = 0; i10 < digestSize; i10++) {
                for (int i11 = 0; i11 < i6; i11++) {
                    int i12 = bArr4[i10] & i7;
                    i8 += i12;
                    System.arraycopy(this.privateKeyOTS[i9], 0, bArr5, 0, this.mdsize);
                    while (i12 > 0) {
                        this.messDigestOTS.update(bArr5, 0, bArr5.length);
                        bArr5 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr5, 0);
                        i12--;
                    }
                    int i13 = this.mdsize;
                    System.arraycopy(bArr5, 0, bArr2, i9 * i13, i13);
                    bArr4[i10] = (byte) (bArr4[i10] >>> this.f2751w);
                    i9++;
                }
            }
            int i14 = (this.messagesize << this.f2751w) - i8;
            int i15 = 0;
            while (i15 < this.checksumsize) {
                System.arraycopy(this.privateKeyOTS[i9], 0, bArr5, 0, this.mdsize);
                for (int i16 = i14 & i7; i16 > 0; i16--) {
                    this.messDigestOTS.update(bArr5, 0, bArr5.length);
                    bArr5 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr5, 0);
                }
                int i17 = this.mdsize;
                System.arraycopy(bArr5, 0, bArr2, i9 * i17, i17);
                int i18 = this.f2751w;
                i14 >>>= i18;
                i9++;
                i15 += i18;
            }
        } else if (i4 < 8) {
            int i19 = this.mdsize;
            int i20 = i19 / i4;
            int i21 = (1 << i4) - 1;
            byte[] bArr6 = new byte[i19];
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            while (i22 < i20) {
                long j = 0;
                for (int i26 = 0; i26 < this.f2751w; i26++) {
                    j ^= (bArr4[i23] & 255) << (i26 << 3);
                    i23++;
                }
                int i27 = 0;
                while (i27 < i5) {
                    int i28 = i20;
                    int i29 = (int) (j & i21);
                    i25 += i29;
                    System.arraycopy(this.privateKeyOTS[i24], 0, bArr6, 0, this.mdsize);
                    while (i29 > 0) {
                        this.messDigestOTS.update(bArr6, 0, bArr6.length);
                        bArr6 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr6, 0);
                        i29--;
                    }
                    int i30 = this.mdsize;
                    System.arraycopy(bArr6, 0, bArr2, i24 * i30, i30);
                    j >>>= this.f2751w;
                    i24++;
                    i27++;
                    i20 = i28;
                    i5 = 8;
                }
                i22++;
                i5 = 8;
            }
            int i31 = this.mdsize % this.f2751w;
            int i32 = 0;
            long j2 = 0;
            while (i32 < i31) {
                j2 ^= (bArr4[i23] & 255) << (i32 << 3);
                i23++;
                i32++;
                i31 = i31;
            }
            int i33 = i31 << 3;
            int i34 = 0;
            while (i34 < i33) {
                int i35 = (int) (i21 & j2);
                i25 += i35;
                System.arraycopy(this.privateKeyOTS[i24], 0, bArr6, 0, this.mdsize);
                while (i35 > 0) {
                    this.messDigestOTS.update(bArr6, 0, bArr6.length);
                    bArr6 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr6, 0);
                    i35--;
                }
                int i36 = this.mdsize;
                System.arraycopy(bArr6, 0, bArr2, i24 * i36, i36);
                int i37 = this.f2751w;
                j2 >>>= i37;
                i24++;
                i34 += i37;
            }
            int i38 = (this.messagesize << this.f2751w) - i25;
            int i39 = 0;
            while (i39 < this.checksumsize) {
                System.arraycopy(this.privateKeyOTS[i24], 0, bArr6, 0, this.mdsize);
                for (int i40 = i38 & i21; i40 > 0; i40--) {
                    this.messDigestOTS.update(bArr6, 0, bArr6.length);
                    bArr6 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr6, 0);
                }
                int i41 = this.mdsize;
                System.arraycopy(bArr6, 0, bArr2, i24 * i41, i41);
                int i42 = this.f2751w;
                i38 >>>= i42;
                i24++;
                i39 += i42;
            }
        } else if (i4 < 57) {
            int i43 = this.mdsize;
            int i44 = (i43 << 3) - i4;
            int i45 = (1 << i4) - 1;
            byte[] bArr7 = new byte[i43];
            int i46 = 0;
            int i47 = 0;
            int i48 = 0;
            while (i47 <= i44) {
                int i49 = i47 % 8;
                i47 += this.f2751w;
                int i50 = 0;
                long j3 = 0;
                for (int i51 = i47 >>> 3; i51 < ((i47 + 7) >>> 3); i51++) {
                    j3 ^= (bArr4[i51] & 255) << (i50 << 3);
                    i50++;
                }
                long j4 = (j3 >>> i49) & i45;
                i46 = (int) (i46 + j4);
                System.arraycopy(this.privateKeyOTS[i48], 0, bArr7, 0, this.mdsize);
                while (j4 > 0) {
                    this.messDigestOTS.update(bArr7, 0, bArr7.length);
                    bArr7 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr7, 0);
                    j4--;
                }
                int i52 = this.mdsize;
                System.arraycopy(bArr7, 0, bArr2, i48 * i52, i52);
                i48++;
            }
            int i53 = i47 >>> 3;
            if (i53 < this.mdsize) {
                int i54 = i47 % 8;
                int i55 = 0;
                long j5 = 0;
                while (true) {
                    i = this.mdsize;
                    if (i53 >= i) {
                        break;
                    }
                    j5 ^= (bArr4[i53] & 255) << (i55 << 3);
                    i55++;
                    i53++;
                }
                long j6 = (j5 >>> i54) & i45;
                i46 = (int) (i46 + j6);
                System.arraycopy(this.privateKeyOTS[i48], 0, bArr7, 0, i);
                while (j6 > 0) {
                    this.messDigestOTS.update(bArr7, 0, bArr7.length);
                    bArr7 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr7, 0);
                    j6--;
                }
                int i56 = this.mdsize;
                System.arraycopy(bArr7, 0, bArr2, i48 * i56, i56);
                i48++;
            }
            int i57 = (this.messagesize << this.f2751w) - i46;
            int i58 = i48;
            int i59 = 0;
            while (i59 < this.checksumsize) {
                System.arraycopy(this.privateKeyOTS[i58], 0, bArr7, 0, this.mdsize);
                for (long j7 = i57 & i45; j7 > 0; j7--) {
                    this.messDigestOTS.update(bArr7, 0, bArr7.length);
                    bArr7 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr7, 0);
                }
                int i60 = this.mdsize;
                System.arraycopy(bArr7, 0, bArr2, i58 * i60, i60);
                int i61 = this.f2751w;
                i57 >>>= i61;
                i58++;
                i59 += i61;
            }
        }
        return bArr2;
    }
}
