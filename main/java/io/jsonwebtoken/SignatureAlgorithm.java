package io.jsonwebtoken;

import com.facebook.internal.security.OidcSecurityUtil;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.crypto.SecretKey;

/* loaded from: classes3.dex */
public enum SignatureAlgorithm {
    NONE("none", "No digital signature or MAC performed", "None", null, false, 0, 0),
    HS256("HS256", "HMAC using SHA-256", "HMAC", "HmacSHA256", true, 256, 256),
    HS384("HS384", "HMAC using SHA-384", "HMAC", "HmacSHA384", true, 384, 384),
    HS512("HS512", "HMAC using SHA-512", "HMAC", "HmacSHA512", true, 512, 512),
    RS256("RS256", "RSASSA-PKCS-v1_5 using SHA-256", "RSA", OidcSecurityUtil.SIGNATURE_ALGORITHM_SHA256, true, 256, 2048),
    RS384("RS384", "RSASSA-PKCS-v1_5 using SHA-384", "RSA", "SHA384withRSA", true, 384, 2048),
    RS512("RS512", "RSASSA-PKCS-v1_5 using SHA-512", "RSA", "SHA512withRSA", true, 512, 2048),
    ES256("ES256", "ECDSA using P-256 and SHA-256", "ECDSA", "SHA256withECDSA", true, 256, 256),
    ES384("ES384", "ECDSA using P-384 and SHA-384", "ECDSA", "SHA384withECDSA", true, 384, 384),
    ES512("ES512", "ECDSA using P-521 and SHA-512", "ECDSA", "SHA512withECDSA", true, 512, 521),
    PS256("PS256", "RSASSA-PSS using SHA-256 and MGF1 with SHA-256", "RSA", "SHA256withRSAandMGF1", false, 256, 2048),
    PS384("PS384", "RSASSA-PSS using SHA-384 and MGF1 with SHA-384", "RSA", "SHA384withRSAandMGF1", false, 384, 2048),
    PS512("PS512", "RSASSA-PSS using SHA-512 and MGF1 with SHA-512", "RSA", "SHA512withRSAandMGF1", false, 512, 2048);

    private static final List<SignatureAlgorithm> PREFERRED_EC_ALGS;
    private static final List<SignatureAlgorithm> PREFERRED_HMAC_ALGS;
    private final String description;
    private final int digestLength;
    private final String familyName;
    private final String jcaName;
    private final boolean jdkStandard;
    private final int minKeyLength;
    private final String value;

    private static String keyType(boolean z) {
        return z ? "signing" : "verification";
    }

    static {
        SignatureAlgorithm signatureAlgorithm = HS256;
        SignatureAlgorithm signatureAlgorithm2 = HS384;
        SignatureAlgorithm signatureAlgorithm3 = HS512;
        SignatureAlgorithm signatureAlgorithm4 = ES256;
        SignatureAlgorithm signatureAlgorithm5 = ES384;
        SignatureAlgorithm signatureAlgorithm6 = ES512;
        PREFERRED_HMAC_ALGS = Collections.unmodifiableList(Arrays.asList(signatureAlgorithm3, signatureAlgorithm2, signatureAlgorithm));
        PREFERRED_EC_ALGS = Collections.unmodifiableList(Arrays.asList(signatureAlgorithm6, signatureAlgorithm5, signatureAlgorithm4));
    }

    SignatureAlgorithm(String str, String str2, String str3, String str4, boolean z, int i, int i2) {
        this.value = str;
        this.description = str2;
        this.familyName = str3;
        this.jcaName = str4;
        this.jdkStandard = z;
        this.digestLength = i;
        this.minKeyLength = i2;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getJcaName() {
        return this.jcaName;
    }

    public boolean isJdkStandard() {
        return this.jdkStandard;
    }

    public boolean isHmac() {
        return this.familyName.equals("HMAC");
    }

    public boolean isRsa() {
        return this.familyName.equals("RSA");
    }

    public boolean isEllipticCurve() {
        return this.familyName.equals("ECDSA");
    }

    public int getMinKeyLength() {
        return this.minKeyLength;
    }

    public void assertValidSigningKey(Key key) throws InvalidKeyException {
        assertValid(key, true);
    }

    public void assertValidVerificationKey(Key key) throws InvalidKeyException {
        assertValid(key, false);
    }

    private void assertValid(Key key, boolean z) throws InvalidKeyException {
        if (this == NONE) {
            throw new InvalidKeyException("The 'NONE' signature algorithm does not support cryptographic keys.");
        }
        if (isHmac()) {
            if (!(key instanceof SecretKey)) {
                throw new InvalidKeyException(this.familyName + " " + keyType(z) + " keys must be SecretKey instances.");
            }
            SecretKey secretKey = (SecretKey) key;
            byte[] encoded = secretKey.getEncoded();
            if (encoded == null) {
                throw new InvalidKeyException("The " + keyType(z) + " key's encoded bytes cannot be null.");
            }
            String algorithm = secretKey.getAlgorithm();
            if (algorithm == null) {
                throw new InvalidKeyException("The " + keyType(z) + " key's algorithm cannot be null.");
            }
            if (!HS256.jcaName.equalsIgnoreCase(algorithm) && !HS384.jcaName.equalsIgnoreCase(algorithm) && !HS512.jcaName.equalsIgnoreCase(algorithm)) {
                throw new InvalidKeyException("The " + keyType(z) + " key's algorithm '" + algorithm + "' does not equal a valid HmacSHA* algorithm name and cannot be used with " + name() + ".");
            }
            int length = encoded.length * 8;
            if (length >= this.minKeyLength) {
                return;
            }
            throw new WeakKeyException("The " + keyType(z) + " key's size is " + length + " bits which is not secure enough for the " + name() + " algorithm.  The JWT JWA Specification (RFC 7518, Section 3.2) states that keys used with " + name() + " MUST have a size >= " + this.minKeyLength + " bits (the key size must be greater than or equal to the hash output size).  Consider using the " + Keys.class.getName() + " class's 'secretKeyFor(SignatureAlgorithm." + name() + ")' method to create a key guaranteed to be secure enough for " + name() + ".  See https://tools.ietf.org/html/rfc7518#section-3.2 for more information.");
        }
        if (z && !(key instanceof PrivateKey)) {
            throw new InvalidKeyException(this.familyName + " signing keys must be PrivateKey instances.");
        }
        if (isEllipticCurve()) {
            if (!(key instanceof ECKey)) {
                throw new InvalidKeyException(this.familyName + " " + keyType(z) + " keys must be ECKey instances.");
            }
            int bitLength = ((ECKey) key).getParams().getOrder().bitLength();
            if (bitLength >= this.minKeyLength) {
                return;
            }
            throw new WeakKeyException("The " + keyType(z) + " key's size (ECParameterSpec order) is " + bitLength + " bits which is not secure enough for the " + name() + " algorithm.  The JWT JWA Specification (RFC 7518, Section 3.4) states that keys used with " + name() + " MUST have a size >= " + this.minKeyLength + " bits.  Consider using the " + Keys.class.getName() + " class's 'keyPairFor(SignatureAlgorithm." + name() + ")' method to create a key pair guaranteed to be secure enough for " + name() + ".  See https://tools.ietf.org/html/rfc7518#section-3.4 for more information.");
        }
        if (!(key instanceof RSAKey)) {
            throw new InvalidKeyException(this.familyName + " " + keyType(z) + " keys must be RSAKey instances.");
        }
        int bitLength2 = ((RSAKey) key).getModulus().bitLength();
        if (bitLength2 < this.minKeyLength) {
            String str = name().startsWith("P") ? "3.5" : "3.3";
            throw new WeakKeyException("The " + keyType(z) + " key's size is " + bitLength2 + " bits which is not secure enough for the " + name() + " algorithm.  The JWT JWA Specification (RFC 7518, Section " + str + ") states that keys used with " + name() + " MUST have a size >= " + this.minKeyLength + " bits.  Consider using the " + Keys.class.getName() + " class's 'keyPairFor(SignatureAlgorithm." + name() + ")' method to create a key pair guaranteed to be secure enough for " + name() + ".  See https://tools.ietf.org/html/rfc7518#section-" + str + " for more information.");
        }
    }

    public static SignatureAlgorithm forSigningKey(Key key) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("Key argument cannot be null.");
        }
        boolean z = key instanceof SecretKey;
        if (!z && (!(key instanceof PrivateKey) || (!(key instanceof ECKey) && !(key instanceof RSAKey)))) {
            throw new InvalidKeyException("JWT standard signing algorithms require either 1) a SecretKey for HMAC-SHA algorithms or 2) a private RSAKey for RSA algorithms or 3) a private ECKey for Elliptic Curve algorithms.  The specified key is of type " + key.getClass().getName());
        }
        if (z) {
            int length = io.jsonwebtoken.lang.Arrays.length(((SecretKey) key).getEncoded()) * 8;
            for (SignatureAlgorithm signatureAlgorithm : PREFERRED_HMAC_ALGS) {
                if (length >= signatureAlgorithm.minKeyLength) {
                    return signatureAlgorithm;
                }
            }
            throw new WeakKeyException("The specified SecretKey is not strong enough to be used with JWT HMAC signature algorithms.  The JWT specification requires HMAC keys to be >= 256 bits long.  The specified key is " + length + " bits.  See https://tools.ietf.org/html/rfc7518#section-3.2 for more information.");
        }
        if (key instanceof RSAKey) {
            int bitLength = ((RSAKey) key).getModulus().bitLength();
            if (bitLength >= 4096) {
                SignatureAlgorithm signatureAlgorithm2 = RS512;
                signatureAlgorithm2.assertValidSigningKey(key);
                return signatureAlgorithm2;
            }
            if (bitLength >= 3072) {
                SignatureAlgorithm signatureAlgorithm3 = RS384;
                signatureAlgorithm3.assertValidSigningKey(key);
                return signatureAlgorithm3;
            }
            SignatureAlgorithm signatureAlgorithm4 = RS256;
            if (bitLength >= signatureAlgorithm4.minKeyLength) {
                signatureAlgorithm4.assertValidSigningKey(key);
                return signatureAlgorithm4;
            }
            throw new WeakKeyException("The specified RSA signing key is not strong enough to be used with JWT RSA signature algorithms.  The JWT specification requires RSA keys to be >= 2048 bits long.  The specified RSA key is " + bitLength + " bits.  See https://tools.ietf.org/html/rfc7518#section-3.3 for more information.");
        }
        int bitLength2 = ((ECKey) key).getParams().getOrder().bitLength();
        for (SignatureAlgorithm signatureAlgorithm5 : PREFERRED_EC_ALGS) {
            if (bitLength2 >= signatureAlgorithm5.minKeyLength) {
                signatureAlgorithm5.assertValidSigningKey(key);
                return signatureAlgorithm5;
            }
        }
        throw new WeakKeyException("The specified Elliptic Curve signing key is not strong enough to be used with JWT ECDSA signature algorithms.  The JWT specification requires ECDSA keys to be >= 256 bits long.  The specified ECDSA key is " + bitLength2 + " bits.  See https://tools.ietf.org/html/rfc7518#section-3.4 for more information.");
    }

    public static SignatureAlgorithm forName(String str) throws io.jsonwebtoken.security.SignatureException {
        for (SignatureAlgorithm signatureAlgorithm : values()) {
            if (signatureAlgorithm.getValue().equalsIgnoreCase(str)) {
                return signatureAlgorithm;
            }
        }
        throw new io.jsonwebtoken.security.SignatureException("Unsupported signature algorithm '" + str + "'");
    }
}
