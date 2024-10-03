package org.spongycastle.pqc.crypto.ntru;

import com.oversea.ab_firstarea.utils.UpdateHelper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* loaded from: classes3.dex */
public class NTRUEncryptionParameters implements Cloneable {

    /* renamed from: N */
    public int f2803N;
    public int bufferLenBits;
    int bufferLenTrits;

    /* renamed from: c */
    public int f2804c;

    /* renamed from: db */
    public int f2805db;

    /* renamed from: df */
    public int f2806df;
    public int df1;
    public int df2;
    public int df3;

    /* renamed from: dg */
    public int f2807dg;
    public int dm0;

    /* renamed from: dr */
    public int f2808dr;
    public int dr1;
    public int dr2;
    public int dr3;
    public boolean fastFp;
    public Digest hashAlg;
    public boolean hashSeed;
    int llen;
    public int maxMsgLenBytes;
    public int minCallsMask;
    public int minCallsR;
    public byte[] oid;
    public int pkLen;
    public int polyType;

    /* renamed from: q */
    public int f2809q;
    public boolean sparse;

    public NTRUEncryptionParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest) {
        this.f2803N = i;
        this.f2809q = i2;
        this.f2806df = i3;
        this.f2805db = i5;
        this.dm0 = i4;
        this.f2804c = i6;
        this.minCallsR = i7;
        this.minCallsMask = i8;
        this.hashSeed = z;
        this.oid = bArr;
        this.sparse = z2;
        this.fastFp = z3;
        this.polyType = 0;
        this.hashAlg = digest;
        init();
    }

    public NTRUEncryptionParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest) {
        this.f2803N = i;
        this.f2809q = i2;
        this.df1 = i3;
        this.df2 = i4;
        this.df3 = i5;
        this.f2805db = i7;
        this.dm0 = i6;
        this.f2804c = i8;
        this.minCallsR = i9;
        this.minCallsMask = i10;
        this.hashSeed = z;
        this.oid = bArr;
        this.sparse = z2;
        this.fastFp = z3;
        this.polyType = 1;
        this.hashAlg = digest;
        init();
    }

    private void init() {
        this.f2808dr = this.f2806df;
        this.dr1 = this.df1;
        this.dr2 = this.df2;
        this.dr3 = this.df3;
        int i = this.f2803N;
        this.f2807dg = i / 3;
        this.llen = 1;
        int i2 = this.f2805db;
        this.maxMsgLenBytes = (((((i * 3) / 2) / 8) - 1) - (i2 / 8)) - 1;
        this.bufferLenBits = (((((i * 3) / 2) + 7) / 8) * 8) + 1;
        this.bufferLenTrits = i - 1;
        this.pkLen = i2;
    }

    public NTRUEncryptionParameters(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f2803N = dataInputStream.readInt();
        this.f2809q = dataInputStream.readInt();
        this.f2806df = dataInputStream.readInt();
        this.df1 = dataInputStream.readInt();
        this.df2 = dataInputStream.readInt();
        this.df3 = dataInputStream.readInt();
        this.f2805db = dataInputStream.readInt();
        this.dm0 = dataInputStream.readInt();
        this.f2804c = dataInputStream.readInt();
        this.minCallsR = dataInputStream.readInt();
        this.minCallsMask = dataInputStream.readInt();
        this.hashSeed = dataInputStream.readBoolean();
        byte[] bArr = new byte[3];
        this.oid = bArr;
        dataInputStream.read(bArr);
        this.sparse = dataInputStream.readBoolean();
        this.fastFp = dataInputStream.readBoolean();
        this.polyType = dataInputStream.read();
        String readUTF = dataInputStream.readUTF();
        if (McElieceCCA2KeyGenParameterSpec.SHA512.equals(readUTF)) {
            this.hashAlg = new SHA512Digest();
        } else if (McElieceCCA2KeyGenParameterSpec.SHA256.equals(readUTF)) {
            this.hashAlg = new SHA256Digest();
        }
        init();
    }

    public NTRUEncryptionParameters clone() {
        if (this.polyType == 0) {
            return new NTRUEncryptionParameters(this.f2803N, this.f2809q, this.f2806df, this.dm0, this.f2805db, this.f2804c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, this.hashAlg);
        }
        return new NTRUEncryptionParameters(this.f2803N, this.f2809q, this.df1, this.df2, this.df3, this.dm0, this.f2805db, this.f2804c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, this.hashAlg);
    }

    public int getMaxMessageLength() {
        return this.maxMsgLenBytes;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.f2803N);
        dataOutputStream.writeInt(this.f2809q);
        dataOutputStream.writeInt(this.f2806df);
        dataOutputStream.writeInt(this.df1);
        dataOutputStream.writeInt(this.df2);
        dataOutputStream.writeInt(this.df3);
        dataOutputStream.writeInt(this.f2805db);
        dataOutputStream.writeInt(this.dm0);
        dataOutputStream.writeInt(this.f2804c);
        dataOutputStream.writeInt(this.minCallsR);
        dataOutputStream.writeInt(this.minCallsMask);
        dataOutputStream.writeBoolean(this.hashSeed);
        dataOutputStream.write(this.oid);
        dataOutputStream.writeBoolean(this.sparse);
        dataOutputStream.writeBoolean(this.fastFp);
        dataOutputStream.write(this.polyType);
        dataOutputStream.writeUTF(this.hashAlg.getAlgorithmName());
    }

    public int hashCode() {
        int i = (((((((((((((((((((((((((((((this.f2803N + 31) * 31) + this.bufferLenBits) * 31) + this.bufferLenTrits) * 31) + this.f2804c) * 31) + this.f2805db) * 31) + this.f2806df) * 31) + this.df1) * 31) + this.df2) * 31) + this.df3) * 31) + this.f2807dg) * 31) + this.dm0) * 31) + this.f2808dr) * 31) + this.dr1) * 31) + this.dr2) * 31) + this.dr3) * 31;
        boolean z = this.fastFp;
        int i2 = UpdateHelper.UPDATE_REQUEST_CODE;
        int i3 = (i + (z ? 1231 : 1237)) * 31;
        Digest digest = this.hashAlg;
        int hashCode = (((((((((((((((((((i3 + (digest == null ? 0 : digest.getAlgorithmName().hashCode())) * 31) + (this.hashSeed ? 1231 : 1237)) * 31) + this.llen) * 31) + this.maxMsgLenBytes) * 31) + this.minCallsMask) * 31) + this.minCallsR) * 31) + Arrays.hashCode(this.oid)) * 31) + this.pkLen) * 31) + this.polyType) * 31) + this.f2809q) * 31;
        if (!this.sparse) {
            i2 = 1237;
        }
        return hashCode + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUEncryptionParameters nTRUEncryptionParameters = (NTRUEncryptionParameters) obj;
        if (this.f2803N != nTRUEncryptionParameters.f2803N || this.bufferLenBits != nTRUEncryptionParameters.bufferLenBits || this.bufferLenTrits != nTRUEncryptionParameters.bufferLenTrits || this.f2804c != nTRUEncryptionParameters.f2804c || this.f2805db != nTRUEncryptionParameters.f2805db || this.f2806df != nTRUEncryptionParameters.f2806df || this.df1 != nTRUEncryptionParameters.df1 || this.df2 != nTRUEncryptionParameters.df2 || this.df3 != nTRUEncryptionParameters.df3 || this.f2807dg != nTRUEncryptionParameters.f2807dg || this.dm0 != nTRUEncryptionParameters.dm0 || this.f2808dr != nTRUEncryptionParameters.f2808dr || this.dr1 != nTRUEncryptionParameters.dr1 || this.dr2 != nTRUEncryptionParameters.dr2 || this.dr3 != nTRUEncryptionParameters.dr3 || this.fastFp != nTRUEncryptionParameters.fastFp) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUEncryptionParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUEncryptionParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return this.hashSeed == nTRUEncryptionParameters.hashSeed && this.llen == nTRUEncryptionParameters.llen && this.maxMsgLenBytes == nTRUEncryptionParameters.maxMsgLenBytes && this.minCallsMask == nTRUEncryptionParameters.minCallsMask && this.minCallsR == nTRUEncryptionParameters.minCallsR && Arrays.equals(this.oid, nTRUEncryptionParameters.oid) && this.pkLen == nTRUEncryptionParameters.pkLen && this.polyType == nTRUEncryptionParameters.polyType && this.f2809q == nTRUEncryptionParameters.f2809q && this.sparse == nTRUEncryptionParameters.sparse;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("EncryptionParameters(N=" + this.f2803N + " q=" + this.f2809q);
        if (this.polyType == 0) {
            sb.append(" polyType=SIMPLE df=" + this.f2806df);
        } else {
            sb.append(" polyType=PRODUCT df1=" + this.df1 + " df2=" + this.df2 + " df3=" + this.df3);
        }
        sb.append(" dm0=" + this.dm0 + " db=" + this.f2805db + " c=" + this.f2804c + " minCallsR=" + this.minCallsR + " minCallsMask=" + this.minCallsMask + " hashSeed=" + this.hashSeed + " hashAlg=" + this.hashAlg + " oid=" + Arrays.toString(this.oid) + " sparse=" + this.sparse + ")");
        return sb.toString();
    }
}
