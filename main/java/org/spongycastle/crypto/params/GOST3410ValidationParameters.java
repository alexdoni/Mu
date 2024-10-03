package org.spongycastle.crypto.params;

/* loaded from: classes3.dex */
public class GOST3410ValidationParameters {

    /* renamed from: c */
    private int f2499c;

    /* renamed from: cL */
    private long f2500cL;

    /* renamed from: x0 */
    private int f2501x0;
    private long x0L;

    public GOST3410ValidationParameters(int i, int i2) {
        this.f2501x0 = i;
        this.f2499c = i2;
    }

    public GOST3410ValidationParameters(long j, long j2) {
        this.x0L = j;
        this.f2500cL = j2;
    }

    public int getC() {
        return this.f2499c;
    }

    public int getX0() {
        return this.f2501x0;
    }

    public long getCL() {
        return this.f2500cL;
    }

    public long getX0L() {
        return this.x0L;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410ValidationParameters)) {
            return false;
        }
        GOST3410ValidationParameters gOST3410ValidationParameters = (GOST3410ValidationParameters) obj;
        return gOST3410ValidationParameters.f2499c == this.f2499c && gOST3410ValidationParameters.f2501x0 == this.f2501x0 && gOST3410ValidationParameters.f2500cL == this.f2500cL && gOST3410ValidationParameters.x0L == this.x0L;
    }

    public int hashCode() {
        int i = this.f2501x0 ^ this.f2499c;
        long j = this.x0L;
        int i2 = (i ^ ((int) j)) ^ ((int) (j >> 32));
        long j2 = this.f2500cL;
        return (i2 ^ ((int) j2)) ^ ((int) (j2 >> 32));
    }
}
