package org.spongycastle.crypto.engines;

import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import java.util.Enumeration;
import java.util.Hashtable;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

/* loaded from: classes3.dex */
public class GOST28147Engine implements BlockCipher {
    protected static final int BLOCK_SIZE = 8;
    private boolean forEncryption;
    private static byte[] Sbox_Default = {4, 10, 9, 2, Ascii.f546CR, 8, 0, Ascii.f556SO, 6, 11, 1, Ascii.f548FF, 7, Ascii.f555SI, 5, 3, Ascii.f556SO, 11, 4, Ascii.f548FF, 6, Ascii.f546CR, Ascii.f555SI, 10, 2, 3, 8, 1, 0, 7, 5, 9, 5, 8, 1, Ascii.f546CR, 10, 3, 4, 2, Ascii.f556SO, Ascii.f555SI, Ascii.f548FF, 7, 6, 0, 9, 11, 7, Ascii.f546CR, 10, 1, 0, 8, 9, Ascii.f555SI, Ascii.f556SO, 4, 6, Ascii.f548FF, 11, 2, 5, 3, 6, Ascii.f548FF, 7, 1, 5, Ascii.f555SI, Ascii.f546CR, 8, 4, 10, 9, Ascii.f556SO, 0, 3, 11, 2, 4, 11, 10, 0, 7, 2, 1, Ascii.f546CR, 3, 6, 8, 5, 9, Ascii.f548FF, Ascii.f555SI, Ascii.f556SO, Ascii.f546CR, 11, 4, 1, 3, Ascii.f555SI, 5, 9, 0, 10, Ascii.f556SO, 7, 6, 8, 2, Ascii.f548FF, 1, Ascii.f555SI, Ascii.f546CR, 0, 5, 7, 10, 4, 9, 2, 3, Ascii.f556SO, 6, 11, 8, Ascii.f548FF};
    private static byte[] ESbox_Test = {4, 2, Ascii.f555SI, 5, 9, 1, 0, 8, Ascii.f556SO, 3, 11, Ascii.f548FF, Ascii.f546CR, 7, 10, 6, Ascii.f548FF, 9, Ascii.f555SI, Ascii.f556SO, 8, 1, 3, 10, 2, 7, 4, Ascii.f546CR, 6, 0, 11, 5, Ascii.f546CR, 8, Ascii.f556SO, Ascii.f548FF, 7, 3, 9, 10, 1, 5, 2, 4, 6, Ascii.f555SI, 0, 11, Ascii.f556SO, 9, 11, 2, 5, Ascii.f555SI, 7, 1, 0, Ascii.f546CR, Ascii.f548FF, 6, 10, 4, 3, 8, 3, Ascii.f556SO, 5, 9, 6, 8, 0, Ascii.f546CR, 10, 11, 7, Ascii.f548FF, 2, 1, Ascii.f555SI, 4, 8, Ascii.f555SI, 6, 11, 1, 9, Ascii.f548FF, 5, Ascii.f546CR, 3, 7, 10, 0, Ascii.f556SO, 2, 4, 9, 11, Ascii.f548FF, 0, 3, 6, 7, 5, 4, 8, Ascii.f556SO, Ascii.f555SI, 1, 10, 2, Ascii.f546CR, Ascii.f548FF, 6, 5, 2, 11, 0, 9, Ascii.f546CR, 3, Ascii.f556SO, 7, 10, Ascii.f555SI, 4, 1, 8};
    private static byte[] ESbox_A = {9, 6, 3, 2, 8, 11, 1, 7, 10, 4, Ascii.f556SO, Ascii.f555SI, Ascii.f548FF, 0, Ascii.f546CR, 5, 3, 7, Ascii.f556SO, 9, 8, 10, Ascii.f555SI, 0, 5, 2, 6, Ascii.f548FF, 11, 4, Ascii.f546CR, 1, Ascii.f556SO, 4, 6, 2, 11, 3, Ascii.f546CR, 8, Ascii.f548FF, Ascii.f555SI, 5, 10, 0, 7, 1, 9, Ascii.f556SO, 7, 10, Ascii.f548FF, Ascii.f546CR, 1, 3, 9, 0, 2, 11, 4, Ascii.f555SI, 8, 5, 6, 11, 5, 1, 9, 8, Ascii.f546CR, Ascii.f555SI, 0, Ascii.f556SO, 4, 2, 3, Ascii.f548FF, 7, 10, 6, 3, 10, Ascii.f546CR, Ascii.f548FF, 1, 2, 0, 11, 7, 5, 9, 4, 8, Ascii.f555SI, Ascii.f556SO, 6, 1, Ascii.f546CR, 2, 9, 7, 10, 6, 0, 8, Ascii.f548FF, 4, 5, Ascii.f555SI, 3, 11, Ascii.f556SO, 11, 10, Ascii.f555SI, 5, 0, Ascii.f548FF, Ascii.f556SO, 8, 6, 2, 3, 9, 1, 7, Ascii.f546CR, 4};
    private static byte[] ESbox_B = {8, 4, 11, 1, 3, 5, 0, 9, 2, Ascii.f556SO, 10, Ascii.f548FF, Ascii.f546CR, 6, 7, Ascii.f555SI, 0, 1, 2, 10, 4, Ascii.f546CR, 5, Ascii.f548FF, 9, 7, 3, Ascii.f555SI, 11, 8, 6, Ascii.f556SO, Ascii.f556SO, Ascii.f548FF, 0, 10, 9, 2, Ascii.f546CR, 11, 7, 5, 8, Ascii.f555SI, 3, 6, 1, 4, 7, 5, 0, Ascii.f546CR, 11, 6, 1, 2, 3, 10, Ascii.f548FF, Ascii.f555SI, 4, Ascii.f556SO, 9, 8, 2, 7, Ascii.f548FF, Ascii.f555SI, 9, 5, 10, 11, 1, 4, 0, Ascii.f546CR, 6, 8, Ascii.f556SO, 3, 8, 3, 2, 6, 4, Ascii.f546CR, Ascii.f556SO, 11, Ascii.f548FF, 1, 7, Ascii.f555SI, 10, 0, 9, 5, 5, 2, 10, 11, 9, 1, Ascii.f548FF, 3, 7, 4, Ascii.f546CR, 0, 6, Ascii.f555SI, 8, Ascii.f556SO, 0, 4, 11, Ascii.f556SO, 8, 3, 7, 1, 10, 2, 9, 6, Ascii.f555SI, Ascii.f546CR, 5, Ascii.f548FF};
    private static byte[] ESbox_C = {1, 11, Ascii.f548FF, 2, 9, Ascii.f546CR, 0, Ascii.f555SI, 4, 5, 8, Ascii.f556SO, 10, 7, 6, 3, 0, 1, 7, Ascii.f546CR, 11, 4, 5, 2, 8, Ascii.f556SO, Ascii.f555SI, Ascii.f548FF, 9, 10, 6, 3, 8, 2, 5, 0, 4, 9, Ascii.f555SI, 10, 3, 7, Ascii.f548FF, Ascii.f546CR, 6, Ascii.f556SO, 1, 11, 3, 6, 0, 1, 5, Ascii.f546CR, 10, 8, 11, 2, 9, 7, Ascii.f556SO, Ascii.f555SI, Ascii.f548FF, 4, 8, Ascii.f546CR, 11, 0, 4, 5, 1, 2, 9, 3, Ascii.f548FF, Ascii.f556SO, 6, Ascii.f555SI, 10, 7, Ascii.f548FF, 9, 11, 1, 8, Ascii.f556SO, 2, 4, 7, 3, 6, 5, 10, 0, Ascii.f555SI, Ascii.f546CR, 10, 9, 6, 8, Ascii.f546CR, Ascii.f556SO, 2, 0, Ascii.f555SI, 3, 5, 11, 4, 1, Ascii.f548FF, 7, 7, 4, 0, 5, 10, 2, Ascii.f555SI, Ascii.f556SO, Ascii.f548FF, 6, 1, 11, Ascii.f546CR, 9, 3, 8};
    private static byte[] ESbox_D = {Ascii.f555SI, Ascii.f548FF, 2, 10, 6, 4, 5, 0, 7, 9, Ascii.f556SO, Ascii.f546CR, 1, 11, 8, 3, 11, 6, 3, 4, Ascii.f548FF, Ascii.f555SI, Ascii.f556SO, 2, 7, Ascii.f546CR, 8, 0, 5, 10, 9, 1, 1, Ascii.f548FF, 11, 0, Ascii.f555SI, Ascii.f556SO, 6, 5, 10, Ascii.f546CR, 4, 8, 9, 3, 7, 2, 1, 5, Ascii.f556SO, Ascii.f548FF, 10, 7, 0, Ascii.f546CR, 6, 2, 11, 4, 9, 3, Ascii.f555SI, 8, 0, Ascii.f548FF, 8, 9, Ascii.f546CR, 2, 10, 11, 7, 3, 6, 5, 4, Ascii.f556SO, Ascii.f555SI, 1, 8, 0, Ascii.f555SI, 3, 2, 5, Ascii.f556SO, 11, 1, 10, 4, 7, Ascii.f548FF, 9, Ascii.f546CR, 6, 3, 0, 6, Ascii.f555SI, 1, Ascii.f556SO, 9, 2, Ascii.f546CR, 8, Ascii.f548FF, 4, 11, 10, 5, 7, 1, 10, 6, 8, Ascii.f555SI, 11, 0, 4, Ascii.f548FF, 3, 5, 9, 7, Ascii.f546CR, 2, Ascii.f556SO};
    private static byte[] DSbox_Test = {4, 10, 9, 2, Ascii.f546CR, 8, 0, Ascii.f556SO, 6, 11, 1, Ascii.f548FF, 7, Ascii.f555SI, 5, 3, Ascii.f556SO, 11, 4, Ascii.f548FF, 6, Ascii.f546CR, Ascii.f555SI, 10, 2, 3, 8, 1, 0, 7, 5, 9, 5, 8, 1, Ascii.f546CR, 10, 3, 4, 2, Ascii.f556SO, Ascii.f555SI, Ascii.f548FF, 7, 6, 0, 9, 11, 7, Ascii.f546CR, 10, 1, 0, 8, 9, Ascii.f555SI, Ascii.f556SO, 4, 6, Ascii.f548FF, 11, 2, 5, 3, 6, Ascii.f548FF, 7, 1, 5, Ascii.f555SI, Ascii.f546CR, 8, 4, 10, 9, Ascii.f556SO, 0, 3, 11, 2, 4, 11, 10, 0, 7, 2, 1, Ascii.f546CR, 3, 6, 8, 5, 9, Ascii.f548FF, Ascii.f555SI, Ascii.f556SO, Ascii.f546CR, 11, 4, 1, 3, Ascii.f555SI, 5, 9, 0, 10, Ascii.f556SO, 7, 6, 8, 2, Ascii.f548FF, 1, Ascii.f555SI, Ascii.f546CR, 0, 5, 7, 10, 4, 9, 2, 3, Ascii.f556SO, 6, 11, 8, Ascii.f548FF};
    private static byte[] DSbox_A = {10, 4, 5, 6, 8, 1, 3, 7, Ascii.f546CR, Ascii.f548FF, Ascii.f556SO, 0, 9, 2, 11, Ascii.f555SI, 5, Ascii.f555SI, 4, 0, 2, Ascii.f546CR, 11, 9, 1, 7, 6, 3, Ascii.f548FF, Ascii.f556SO, 10, 8, 7, Ascii.f555SI, Ascii.f548FF, Ascii.f556SO, 9, 4, 1, 0, 3, 11, 5, 2, 6, 10, 8, Ascii.f546CR, 4, 10, 7, Ascii.f548FF, 0, Ascii.f555SI, 2, 8, Ascii.f556SO, 1, 6, 5, Ascii.f546CR, 11, 9, 3, 7, 6, 4, 11, 9, Ascii.f548FF, 2, 10, 1, 8, 0, Ascii.f556SO, Ascii.f555SI, Ascii.f546CR, 3, 5, 7, 6, 2, 4, Ascii.f546CR, 9, Ascii.f555SI, 0, 10, 1, 5, 11, 8, Ascii.f556SO, Ascii.f548FF, 3, Ascii.f546CR, Ascii.f556SO, 4, 1, 7, 0, 5, 10, 3, Ascii.f548FF, 8, Ascii.f555SI, 6, 2, 9, 11, 1, 3, 10, 9, 5, 11, 4, Ascii.f555SI, 8, 6, 7, Ascii.f556SO, Ascii.f546CR, 0, 2, Ascii.f548FF};
    private static Hashtable sBoxes = new Hashtable();
    private int[] workingKey = null;

    /* renamed from: S */
    private byte[] f2317S = Sbox_Default;

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "GOST28147";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }

    static {
        addSBox("Default", Sbox_Default);
        addSBox("E-TEST", ESbox_Test);
        addSBox("E-A", ESbox_A);
        addSBox("E-B", ESbox_B);
        addSBox("E-C", ESbox_C);
        addSBox("E-D", ESbox_D);
        addSBox("D-TEST", DSbox_Test);
        addSBox("D-A", DSbox_A);
    }

    private static void addSBox(String str, byte[] bArr) {
        sBoxes.put(Strings.toUpperCase(str), bArr);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithSBox) {
            ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
            byte[] sBox = parametersWithSBox.getSBox();
            if (sBox.length != Sbox_Default.length) {
                throw new IllegalArgumentException("invalid S-box passed to GOST28147 init");
            }
            this.f2317S = Arrays.clone(sBox);
            if (parametersWithSBox.getParameters() != null) {
                this.workingKey = generateWorkingKey(z, ((KeyParameter) parametersWithSBox.getParameters()).getKey());
                return;
            }
            return;
        }
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(z, ((KeyParameter) cipherParameters).getKey());
        } else {
            if (cipherParameters == null) {
                return;
            }
            throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = this.workingKey;
        if (iArr == null) {
            throw new IllegalStateException("GOST28147 engine not initialised");
        }
        if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 8 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        GOST28147Func(iArr, bArr, i, bArr2, i2);
        return 8;
    }

    private int[] generateWorkingKey(boolean z, byte[] bArr) {
        this.forEncryption = z;
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        int[] iArr = new int[8];
        for (int i = 0; i != 8; i++) {
            iArr[i] = bytesToint(bArr, i * 4);
        }
        return iArr;
    }

    private int GOST28147_mainStep(int i, int i2) {
        int i3 = i2 + i;
        byte[] bArr = this.f2317S;
        int i4 = (bArr[((i3 >> 0) & 15) + 0] << 0) + (bArr[((i3 >> 4) & 15) + 16] << 4) + (bArr[((i3 >> 8) & 15) + 32] << 8) + (bArr[((i3 >> 12) & 15) + 48] << Ascii.f548FF) + (bArr[((i3 >> 16) & 15) + 64] << 16) + (bArr[((i3 >> 20) & 15) + 80] << Ascii.DC4) + (bArr[((i3 >> 24) & 15) + 96] << Ascii.CAN) + (bArr[((i3 >> 28) & 15) + 112] << Ascii.f549FS);
        return (i4 << 11) | (i4 >>> 21);
    }

    private void GOST28147Func(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3;
        int i4;
        int bytesToint = bytesToint(bArr, i);
        int bytesToint2 = bytesToint(bArr, i + 4);
        int i5 = 7;
        if (this.forEncryption) {
            for (int i6 = 0; i6 < 3; i6++) {
                int i7 = 0;
                while (i7 < 8) {
                    int GOST28147_mainStep = bytesToint2 ^ GOST28147_mainStep(bytesToint, iArr[i7]);
                    i7++;
                    int i8 = bytesToint;
                    bytesToint = GOST28147_mainStep;
                    bytesToint2 = i8;
                }
            }
            i3 = bytesToint2;
            i4 = bytesToint;
            while (i5 > 0) {
                int GOST28147_mainStep2 = i3 ^ GOST28147_mainStep(i4, iArr[i5]);
                i5--;
                i3 = i4;
                i4 = GOST28147_mainStep2;
            }
        } else {
            int i9 = 0;
            while (i9 < 8) {
                int GOST28147_mainStep3 = bytesToint2 ^ GOST28147_mainStep(bytesToint, iArr[i9]);
                i9++;
                int i10 = bytesToint;
                bytesToint = GOST28147_mainStep3;
                bytesToint2 = i10;
            }
            i3 = bytesToint2;
            i4 = bytesToint;
            for (int i11 = 0; i11 < 3; i11++) {
                int i12 = 7;
                while (i12 >= 0 && (i11 != 2 || i12 != 0)) {
                    int GOST28147_mainStep4 = i3 ^ GOST28147_mainStep(i4, iArr[i12]);
                    i12--;
                    i3 = i4;
                    i4 = GOST28147_mainStep4;
                }
            }
        }
        int GOST28147_mainStep5 = GOST28147_mainStep(i4, iArr[0]) ^ i3;
        intTobytes(i4, bArr2, i2);
        intTobytes(GOST28147_mainStep5, bArr2, i2 + 4);
    }

    private int bytesToint(byte[] bArr, int i) {
        return ((bArr[i + 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) + ((bArr[i + 2] << 16) & 16711680) + ((bArr[i + 1] << 8) & 65280) + (bArr[i] & 255);
    }

    private void intTobytes(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >>> 24);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2] = (byte) i;
    }

    public static byte[] getSBox(String str) {
        byte[] bArr = (byte[]) sBoxes.get(Strings.toUpperCase(str));
        if (bArr == null) {
            throw new IllegalArgumentException("Unknown S-Box - possible types: \"Default\", \"E-Test\", \"E-A\", \"E-B\", \"E-C\", \"E-D\", \"D-Test\", \"D-A\".");
        }
        return Arrays.clone(bArr);
    }

    public static String getSBoxName(byte[] bArr) {
        Enumeration keys = sBoxes.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            if (Arrays.areEqual((byte[]) sBoxes.get(str), bArr)) {
                return str;
            }
        }
        throw new IllegalArgumentException("SBOX provided did not map to a known one");
    }
}
