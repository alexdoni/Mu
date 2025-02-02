package org.spongycastle.crypto.params;

/* loaded from: classes3.dex */
public class ECKeyParameters extends AsymmetricKeyParameter {
    ECDomainParameters params;

    /* JADX INFO: Access modifiers changed from: protected */
    public ECKeyParameters(boolean z, ECDomainParameters eCDomainParameters) {
        super(z);
        this.params = eCDomainParameters;
    }

    public ECDomainParameters getParameters() {
        return this.params;
    }
}
