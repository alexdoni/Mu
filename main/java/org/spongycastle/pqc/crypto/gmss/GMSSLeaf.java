package org.spongycastle.pqc.crypto.gmss;

import org.spongycastle.crypto.Digest;
import org.spongycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.encoders.Hex;

/* loaded from: classes3.dex */
public class GMSSLeaf {
    private byte[] concHashs;
    private GMSSRandom gmssRandom;

    /* renamed from: i */
    private int f2740i;

    /* renamed from: j */
    private int f2741j;
    private int keysize;
    private byte[] leaf;
    private int mdsize;
    private Digest messDigestOTS;
    byte[] privateKeyOTS;
    private byte[] seed;
    private int steps;
    private int two_power_w;

    /* renamed from: w */
    private int f2742w;

    private int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public GMSSLeaf(Digest digest, byte[][] bArr, int[] iArr) {
        this.f2740i = iArr[0];
        this.f2741j = iArr[1];
        this.steps = iArr[2];
        this.f2742w = iArr[3];
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        this.mdsize = this.messDigestOTS.getDigestSize();
        this.keysize = ((int) Math.ceil((r9 << 3) / this.f2742w)) + ((int) Math.ceil(getLog((r9 << this.f2742w) + 1) / this.f2742w));
        this.two_power_w = 1 << this.f2742w;
        this.privateKeyOTS = bArr[0];
        this.seed = bArr[1];
        this.concHashs = bArr[2];
        this.leaf = bArr[3];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GMSSLeaf(Digest digest, int i, int i2) {
        this.f2742w = i;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d = i;
        this.keysize = ((int) Math.ceil((r7 << 3) / d)) + ((int) Math.ceil(getLog((r7 << i) + 1) / d));
        this.two_power_w = 1 << i;
        this.steps = (int) Math.ceil(((((r8 - 1) * r7) + 1) + r7) / i2);
        int i3 = this.mdsize;
        this.seed = new byte[i3];
        this.leaf = new byte[i3];
        this.privateKeyOTS = new byte[i3];
        this.concHashs = new byte[i3 * this.keysize];
    }

    public GMSSLeaf(Digest digest, int i, int i2, byte[] bArr) {
        this.f2742w = i;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d = i;
        this.keysize = ((int) Math.ceil((r7 << 3) / d)) + ((int) Math.ceil(getLog((r7 << i) + 1) / d));
        this.two_power_w = 1 << i;
        this.steps = (int) Math.ceil(((((r8 - 1) * r7) + 1) + r7) / i2);
        int i3 = this.mdsize;
        this.seed = new byte[i3];
        this.leaf = new byte[i3];
        this.privateKeyOTS = new byte[i3];
        this.concHashs = new byte[i3 * this.keysize];
        initLeafCalc(bArr);
    }

    private GMSSLeaf(GMSSLeaf gMSSLeaf) {
        this.messDigestOTS = gMSSLeaf.messDigestOTS;
        this.mdsize = gMSSLeaf.mdsize;
        this.keysize = gMSSLeaf.keysize;
        this.gmssRandom = gMSSLeaf.gmssRandom;
        this.leaf = Arrays.clone(gMSSLeaf.leaf);
        this.concHashs = Arrays.clone(gMSSLeaf.concHashs);
        this.f2740i = gMSSLeaf.f2740i;
        this.f2741j = gMSSLeaf.f2741j;
        this.two_power_w = gMSSLeaf.two_power_w;
        this.f2742w = gMSSLeaf.f2742w;
        this.steps = gMSSLeaf.steps;
        this.seed = Arrays.clone(gMSSLeaf.seed);
        this.privateKeyOTS = Arrays.clone(gMSSLeaf.privateKeyOTS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initLeafCalc(byte[] bArr) {
        this.f2740i = 0;
        this.f2741j = 0;
        byte[] bArr2 = new byte[this.mdsize];
        System.arraycopy(bArr, 0, bArr2, 0, this.seed.length);
        this.seed = this.gmssRandom.nextSeed(bArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GMSSLeaf nextLeaf() {
        GMSSLeaf gMSSLeaf = new GMSSLeaf(this);
        gMSSLeaf.updateLeafCalc();
        return gMSSLeaf;
    }

    private void updateLeafCalc() {
        byte[] bArr = new byte[this.messDigestOTS.getDigestSize()];
        for (int i = 0; i < this.steps + 10000; i++) {
            int i2 = this.f2740i;
            if (i2 == this.keysize && this.f2741j == this.two_power_w - 1) {
                Digest digest = this.messDigestOTS;
                byte[] bArr2 = this.concHashs;
                digest.update(bArr2, 0, bArr2.length);
                byte[] bArr3 = new byte[this.messDigestOTS.getDigestSize()];
                this.leaf = bArr3;
                this.messDigestOTS.doFinal(bArr3, 0);
                return;
            }
            if (i2 == 0 || this.f2741j == this.two_power_w - 1) {
                this.f2740i = i2 + 1;
                this.f2741j = 0;
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else {
                Digest digest2 = this.messDigestOTS;
                byte[] bArr4 = this.privateKeyOTS;
                digest2.update(bArr4, 0, bArr4.length);
                this.privateKeyOTS = bArr;
                this.messDigestOTS.doFinal(bArr, 0);
                int i3 = this.f2741j + 1;
                this.f2741j = i3;
                if (i3 == this.two_power_w - 1) {
                    byte[] bArr5 = this.privateKeyOTS;
                    byte[] bArr6 = this.concHashs;
                    int i4 = this.mdsize;
                    System.arraycopy(bArr5, 0, bArr6, (this.f2740i - 1) * i4, i4);
                }
            }
        }
        throw new IllegalStateException("unable to updateLeaf in steps: " + this.steps + " " + this.f2740i + " " + this.f2741j);
    }

    public byte[] getLeaf() {
        return Arrays.clone(this.leaf);
    }

    public byte[][] getStatByte() {
        int i = this.mdsize;
        byte[][] bArr = {new byte[i], new byte[i], new byte[this.keysize * i], new byte[i]};
        bArr[0] = this.privateKeyOTS;
        bArr[1] = this.seed;
        bArr[2] = this.concHashs;
        bArr[3] = this.leaf;
        return bArr;
    }

    public int[] getStatInt() {
        return new int[]{this.f2740i, this.f2741j, this.steps, this.f2742w};
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < 4; i++) {
            str = str + getStatInt()[i] + " ";
        }
        String str2 = str + " " + this.mdsize + " " + this.keysize + " " + this.two_power_w + " ";
        byte[][] statByte = getStatByte();
        for (int i2 = 0; i2 < 4; i2++) {
            str2 = statByte[i2] != null ? str2 + new String(Hex.encode(statByte[i2])) + " " : str2 + "null ";
        }
        return str2;
    }
}
