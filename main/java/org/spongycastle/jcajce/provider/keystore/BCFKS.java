package org.spongycastle.jcajce.provider.keystore;

import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

/* loaded from: classes3.dex */
public class BCFKS {
    private static final String PREFIX = "org.spongycastle.jcajce.provider.keystore.bcfks.";

    /* loaded from: classes3.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyStore.BCFKS", "org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi$Std");
            configurableProvider.addAlgorithm("KeyStore.BCFKS-DEF", "org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi$Def");
        }
    }
}
