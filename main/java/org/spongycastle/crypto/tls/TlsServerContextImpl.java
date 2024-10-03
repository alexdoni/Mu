package org.spongycastle.crypto.tls;

import java.security.SecureRandom;

/* loaded from: classes3.dex */
class TlsServerContextImpl extends AbstractTlsContext implements TlsServerContext {
    @Override // org.spongycastle.crypto.tls.TlsContext
    public boolean isServer() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsServerContextImpl(SecureRandom secureRandom, SecurityParameters securityParameters) {
        super(secureRandom, securityParameters);
    }
}
