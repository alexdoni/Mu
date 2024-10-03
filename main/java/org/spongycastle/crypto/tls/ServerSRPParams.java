package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class ServerSRPParams {

    /* renamed from: B */
    protected BigInteger f2542B;

    /* renamed from: N */
    protected BigInteger f2543N;

    /* renamed from: g */
    protected BigInteger f2544g;

    /* renamed from: s */
    protected byte[] f2545s;

    public ServerSRPParams(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr, BigInteger bigInteger3) {
        this.f2543N = bigInteger;
        this.f2544g = bigInteger2;
        this.f2545s = Arrays.clone(bArr);
        this.f2542B = bigInteger3;
    }

    public BigInteger getB() {
        return this.f2542B;
    }

    public BigInteger getG() {
        return this.f2544g;
    }

    public BigInteger getN() {
        return this.f2543N;
    }

    public byte[] getS() {
        return this.f2545s;
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsSRPUtils.writeSRPParameter(this.f2543N, outputStream);
        TlsSRPUtils.writeSRPParameter(this.f2544g, outputStream);
        TlsUtils.writeOpaque8(this.f2545s, outputStream);
        TlsSRPUtils.writeSRPParameter(this.f2542B, outputStream);
    }

    public static ServerSRPParams parse(InputStream inputStream) throws IOException {
        return new ServerSRPParams(TlsSRPUtils.readSRPParameter(inputStream), TlsSRPUtils.readSRPParameter(inputStream), TlsUtils.readOpaque8(inputStream), TlsSRPUtils.readSRPParameter(inputStream));
    }
}
