package org.spongycastle.crypto.tls;

import java.security.SecureRandom;

/* loaded from: classes3.dex */
class TlsClientContextImpl extends AbstractTlsContext implements TlsClientContext {
    @Override // org.spongycastle.crypto.tls.TlsContext
    public boolean isServer() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsClientContextImpl(SecureRandom secureRandom, SecurityParameters securityParameters) {
        super(secureRandom, securityParameters);
    }
}
