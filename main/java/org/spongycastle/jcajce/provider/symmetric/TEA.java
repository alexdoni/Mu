package org.spongycastle.jcajce.provider.symmetric;

import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.TEAEngine;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

/* loaded from: classes3.dex */
public final class TEA {

    /* loaded from: classes3.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "TEA IV";
        }
    }

    private TEA() {
    }

    /* loaded from: classes3.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new TEAEngine());
        }
    }

    /* loaded from: classes3.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("TEA", 128, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes3.dex */
    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = TEA.class.getName();

        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder sb = new StringBuilder();
            String str = PREFIX;
            sb.append(str);
            sb.append("$ECB");
            configurableProvider.addAlgorithm("Cipher.TEA", sb.toString());
            configurableProvider.addAlgorithm("KeyGenerator.TEA", str + "$KeyGen");
            configurableProvider.addAlgorithm("AlgorithmParameters.TEA", str + "$AlgParams");
        }
    }
}
