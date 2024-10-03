package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* loaded from: classes3.dex */
public class NTRUSigningParameters implements Cloneable {

    /* renamed from: B */
    public int f2823B;

    /* renamed from: N */
    public int f2824N;
    double beta;
    public double betaSq;
    int bitsF;

    /* renamed from: d */
    public int f2825d;

    /* renamed from: d1 */
    public int f2826d1;

    /* renamed from: d2 */
    public int f2827d2;

    /* renamed from: d3 */
    public int f2828d3;
    public Digest hashAlg;
    double normBound;
    public double normBoundSq;

    /* renamed from: q */
    public int f2829q;
    public int signFailTolerance;

    public NTRUSigningParameters(int i, int i2, int i3, int i4, double d, double d2, Digest digest) {
        this.signFailTolerance = 100;
        this.bitsF = 6;
        this.f2824N = i;
        this.f2829q = i2;
        this.f2825d = i3;
        this.f2823B = i4;
        this.beta = d;
        this.normBound = d2;
        this.hashAlg = digest;
        init();
    }

    public NTRUSigningParameters(int i, int i2, int i3, int i4, int i5, int i6, double d, double d2, double d3, Digest digest) {
        this.signFailTolerance = 100;
        this.bitsF = 6;
        this.f2824N = i;
        this.f2829q = i2;
        this.f2826d1 = i3;
        this.f2827d2 = i4;
        this.f2828d3 = i5;
        this.f2823B = i6;
        this.beta = d;
        this.normBound = d2;
        this.hashAlg = digest;
        init();
    }

    private void init() {
        double d = this.beta;
        this.betaSq = d * d;
        double d2 = this.normBound;
        this.normBoundSq = d2 * d2;
    }

    public NTRUSigningParameters(InputStream inputStream) throws IOException {
        this.signFailTolerance = 100;
        this.bitsF = 6;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f2824N = dataInputStream.readInt();
        this.f2829q = dataInputStream.readInt();
        this.f2825d = dataInputStream.readInt();
        this.f2826d1 = dataInputStream.readInt();
        this.f2827d2 = dataInputStream.readInt();
        this.f2828d3 = dataInputStream.readInt();
        this.f2823B = dataInputStream.readInt();
        this.beta = dataInputStream.readDouble();
        this.normBound = dataInputStream.readDouble();
        this.signFailTolerance = dataInputStream.readInt();
        this.bitsF = dataInputStream.readInt();
        String readUTF = dataInputStream.readUTF();
        if (McElieceCCA2KeyGenParameterSpec.SHA512.equals(readUTF)) {
            this.hashAlg = new SHA512Digest();
        } else if (McElieceCCA2KeyGenParameterSpec.SHA256.equals(readUTF)) {
            this.hashAlg = new SHA256Digest();
        }
        init();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.f2824N);
        dataOutputStream.writeInt(this.f2829q);
        dataOutputStream.writeInt(this.f2825d);
        dataOutputStream.writeInt(this.f2826d1);
        dataOutputStream.writeInt(this.f2827d2);
        dataOutputStream.writeInt(this.f2828d3);
        dataOutputStream.writeInt(this.f2823B);
        dataOutputStream.writeDouble(this.beta);
        dataOutputStream.writeDouble(this.normBound);
        dataOutputStream.writeInt(this.signFailTolerance);
        dataOutputStream.writeInt(this.bitsF);
        dataOutputStream.writeUTF(this.hashAlg.getAlgorithmName());
    }

    public NTRUSigningParameters clone() {
        return new NTRUSigningParameters(this.f2824N, this.f2829q, this.f2825d, this.f2823B, this.beta, this.normBound, this.hashAlg);
    }

    public int hashCode() {
        int i = ((this.f2823B + 31) * 31) + this.f2824N;
        long doubleToLongBits = Double.doubleToLongBits(this.beta);
        int i2 = (i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        long doubleToLongBits2 = Double.doubleToLongBits(this.betaSq);
        int i3 = ((((((((((((i2 * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + this.bitsF) * 31) + this.f2825d) * 31) + this.f2826d1) * 31) + this.f2827d2) * 31) + this.f2828d3) * 31;
        Digest digest = this.hashAlg;
        int hashCode = i3 + (digest == null ? 0 : digest.getAlgorithmName().hashCode());
        long doubleToLongBits3 = Double.doubleToLongBits(this.normBound);
        int i4 = (hashCode * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)));
        long doubleToLongBits4 = Double.doubleToLongBits(this.normBoundSq);
        return (((((i4 * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)))) * 31) + this.f2829q) * 31) + this.signFailTolerance;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUSigningParameters)) {
            return false;
        }
        NTRUSigningParameters nTRUSigningParameters = (NTRUSigningParameters) obj;
        if (this.f2823B != nTRUSigningParameters.f2823B || this.f2824N != nTRUSigningParameters.f2824N || Double.doubleToLongBits(this.beta) != Double.doubleToLongBits(nTRUSigningParameters.beta) || Double.doubleToLongBits(this.betaSq) != Double.doubleToLongBits(nTRUSigningParameters.betaSq) || this.bitsF != nTRUSigningParameters.bitsF || this.f2825d != nTRUSigningParameters.f2825d || this.f2826d1 != nTRUSigningParameters.f2826d1 || this.f2827d2 != nTRUSigningParameters.f2827d2 || this.f2828d3 != nTRUSigningParameters.f2828d3) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUSigningParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUSigningParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return Double.doubleToLongBits(this.normBound) == Double.doubleToLongBits(nTRUSigningParameters.normBound) && Double.doubleToLongBits(this.normBoundSq) == Double.doubleToLongBits(nTRUSigningParameters.normBoundSq) && this.f2829q == nTRUSigningParameters.f2829q && this.signFailTolerance == nTRUSigningParameters.signFailTolerance;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuilder sb = new StringBuilder("SignatureParameters(N=" + this.f2824N + " q=" + this.f2829q);
        sb.append(" B=" + this.f2823B + " beta=" + decimalFormat.format(this.beta) + " normBound=" + decimalFormat.format(this.normBound) + " hashAlg=" + this.hashAlg + ")");
        return sb.toString();
    }
}
