package org.spongycastle.crypto.params;

/* loaded from: classes3.dex */
public class ElGamalKeyParameters extends AsymmetricKeyParameter {
    private ElGamalParameters params;

    /* JADX INFO: Access modifiers changed from: protected */
    public ElGamalKeyParameters(boolean z, ElGamalParameters elGamalParameters) {
        super(z);
        this.params = elGamalParameters;
    }

    public ElGamalParameters getParameters() {
        return this.params;
    }

    public int hashCode() {
        ElGamalParameters elGamalParameters = this.params;
        if (elGamalParameters != null) {
            return elGamalParameters.hashCode();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ElGamalKeyParameters)) {
            return false;
        }
        ElGamalKeyParameters elGamalKeyParameters = (ElGamalKeyParameters) obj;
        ElGamalParameters elGamalParameters = this.params;
        if (elGamalParameters == null) {
            return elGamalKeyParameters.getParameters() == null;
        }
        return elGamalParameters.equals(elGamalKeyParameters.getParameters());
    }
}
