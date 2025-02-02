package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public final class KDFFeedbackParameters implements DerivationParameters {
    private static final int UNUSED_R = -1;
    private final byte[] fixedInputData;

    /* renamed from: iv */
    private final byte[] f2506iv;

    /* renamed from: ki */
    private final byte[] f2507ki;

    /* renamed from: r */
    private final int f2508r;
    private final boolean useCounter;

    private KDFFeedbackParameters(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, boolean z) {
        if (bArr == null) {
            throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
        }
        this.f2507ki = Arrays.clone(bArr);
        if (bArr3 == null) {
            this.fixedInputData = new byte[0];
        } else {
            this.fixedInputData = Arrays.clone(bArr3);
        }
        this.f2508r = i;
        if (bArr2 == null) {
            this.f2506iv = new byte[0];
        } else {
            this.f2506iv = Arrays.clone(bArr2);
        }
        this.useCounter = z;
    }

    public static KDFFeedbackParameters createWithCounter(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        if (i != 8 && i != 16 && i != 24 && i != 32) {
            throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
        }
        return new KDFFeedbackParameters(bArr, bArr2, bArr3, i, true);
    }

    public static KDFFeedbackParameters createWithoutCounter(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return new KDFFeedbackParameters(bArr, bArr2, bArr3, -1, false);
    }

    public byte[] getKI() {
        return this.f2507ki;
    }

    public byte[] getIV() {
        return this.f2506iv;
    }

    public boolean useCounter() {
        return this.useCounter;
    }

    public int getR() {
        return this.f2508r;
    }

    public byte[] getFixedInputData() {
        return Arrays.clone(this.fixedInputData);
    }
}
