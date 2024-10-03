package org.spongycastle.jcajce.provider.digest;

import org.spongycastle.asn1.misc.MiscObjectIdentifiers;
import org.spongycastle.crypto.digests.Blake2bDigest;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;

/* loaded from: classes3.dex */
public class Blake2b {
    private Blake2b() {
    }

    /* loaded from: classes3.dex */
    public static class Blake2b512 extends BCMessageDigest implements Cloneable {
        public Blake2b512() {
            super(new Blake2bDigest(512));
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Blake2b512 blake2b512 = (Blake2b512) super.clone();
            blake2b512.digest = new Blake2bDigest((Blake2bDigest) this.digest);
            return blake2b512;
        }
    }

    /* loaded from: classes3.dex */
    public static class Blake2b384 extends BCMessageDigest implements Cloneable {
        public Blake2b384() {
            super(new Blake2bDigest(384));
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Blake2b384 blake2b384 = (Blake2b384) super.clone();
            blake2b384.digest = new Blake2bDigest((Blake2bDigest) this.digest);
            return blake2b384;
        }
    }

    /* loaded from: classes3.dex */
    public static class Blake2b256 extends BCMessageDigest implements Cloneable {
        public Blake2b256() {
            super(new Blake2bDigest(256));
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Blake2b256 blake2b256 = (Blake2b256) super.clone();
            blake2b256.digest = new Blake2bDigest((Blake2bDigest) this.digest);
            return blake2b256;
        }
    }

    /* loaded from: classes3.dex */
    public static class Blake2b160 extends BCMessageDigest implements Cloneable {
        public Blake2b160() {
            super(new Blake2bDigest(CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256));
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Blake2b160 blake2b160 = (Blake2b160) super.clone();
            blake2b160.digest = new Blake2bDigest((Blake2bDigest) this.digest);
            return blake2b160;
        }
    }

    /* loaded from: classes3.dex */
    public static class Mappings extends DigestAlgorithmProvider {
        private static final String PREFIX = Blake2b.class.getName();

        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder sb = new StringBuilder();
            String str = PREFIX;
            sb.append(str);
            sb.append("$Blake2b512");
            configurableProvider.addAlgorithm("MessageDigest.BLAKE2B-512", sb.toString());
            configurableProvider.addAlgorithm("Alg.Alias.MessageDigest." + MiscObjectIdentifiers.id_blake2b512, "BLAKE2B-512");
            configurableProvider.addAlgorithm("MessageDigest.BLAKE2B-384", str + "$Blake2b384");
            StringBuilder sb2 = new StringBuilder("Alg.Alias.MessageDigest.");
            sb2.append(MiscObjectIdentifiers.id_blake2b384);
            configurableProvider.addAlgorithm(sb2.toString(), "BLAKE2B-384");
            configurableProvider.addAlgorithm("MessageDigest.BLAKE2B-256", str + "$Blake2b256");
            StringBuilder sb3 = new StringBuilder("Alg.Alias.MessageDigest.");
            sb3.append(MiscObjectIdentifiers.id_blake2b256);
            configurableProvider.addAlgorithm(sb3.toString(), "BLAKE2B-256");
            configurableProvider.addAlgorithm("MessageDigest.BLAKE2B-160", str + "$Blake2b160");
            StringBuilder sb4 = new StringBuilder("Alg.Alias.MessageDigest.");
            sb4.append(MiscObjectIdentifiers.id_blake2b160);
            configurableProvider.addAlgorithm(sb4.toString(), "BLAKE2B-160");
        }
    }
}
