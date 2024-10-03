package org.spongycastle.crypto.tls;

import java.io.IOException;

/* loaded from: classes3.dex */
public class DTLSTransport implements DatagramTransport {
    private final DTLSRecordLayer recordLayer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DTLSTransport(DTLSRecordLayer dTLSRecordLayer) {
        this.recordLayer = dTLSRecordLayer;
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public int getReceiveLimit() throws IOException {
        return this.recordLayer.getReceiveLimit();
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public int getSendLimit() throws IOException {
        return this.recordLayer.getSendLimit();
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public int receive(byte[] bArr, int i, int i2, int i3) throws IOException {
        try {
            return this.recordLayer.receive(bArr, i, i2, i3);
        } catch (IOException e) {
            this.recordLayer.fail((short) 80);
            throw e;
        } catch (RuntimeException e2) {
            this.recordLayer.fail((short) 80);
            throw new TlsFatalAlert((short) 80, e2);
        } catch (TlsFatalAlert e3) {
            this.recordLayer.fail(e3.getAlertDescription());
            throw e3;
        }
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public void send(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.recordLayer.send(bArr, i, i2);
        } catch (RuntimeException e) {
            this.recordLayer.fail((short) 80);
            throw new TlsFatalAlert((short) 80, e);
        } catch (TlsFatalAlert e2) {
            this.recordLayer.fail(e2.getAlertDescription());
            throw e2;
        } catch (IOException e3) {
            this.recordLayer.fail((short) 80);
            throw e3;
        }
    }

    @Override // org.spongycastle.crypto.tls.DatagramTransport
    public void close() throws IOException {
        this.recordLayer.close();
    }
}
