package org.spongycastle.crypto.params;

/* loaded from: classes3.dex */
public class CramerShoupKeyParameters extends AsymmetricKeyParameter {
    private CramerShoupParameters params;

    /* JADX INFO: Access modifiers changed from: protected */
    public CramerShoupKeyParameters(boolean z, CramerShoupParameters cramerShoupParameters) {
        super(z);
        this.params = cramerShoupParameters;
    }

    public CramerShoupParameters getParameters() {
        return this.params;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupKeyParameters)) {
            return false;
        }
        CramerShoupKeyParameters cramerShoupKeyParameters = (CramerShoupKeyParameters) obj;
        CramerShoupParameters cramerShoupParameters = this.params;
        if (cramerShoupParameters == null) {
            return cramerShoupKeyParameters.getParameters() == null;
        }
        return cramerShoupParameters.equals(cramerShoupKeyParameters.getParameters());
    }

    public int hashCode() {
        int i = !isPrivate() ? 1 : 0;
        CramerShoupParameters cramerShoupParameters = this.params;
        return cramerShoupParameters != null ? i ^ cramerShoupParameters.hashCode() : i;
    }
}
