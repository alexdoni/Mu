package io.jsonwebtoken.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSigner;
import io.jsonwebtoken.impl.crypto.JwtSigner;
import io.jsonwebtoken.impl.io.InstanceLocator;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoder;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.io.SerializationException;
import io.jsonwebtoken.io.Serializer;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Classes;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.InvalidKeyException;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes3.dex */
public class DefaultJwtBuilder implements JwtBuilder {
    private SignatureAlgorithm algorithm;
    private Encoder<byte[], String> base64UrlEncoder = Encoders.BASE64URL;
    private Claims claims;
    private CompressionCodec compressionCodec;
    private Header header;
    private Key key;
    private String payload;
    private Serializer<Map<String, ?>> serializer;

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder serializeToJsonWith(Serializer<Map<String, ?>> serializer) {
        Assert.notNull(serializer, "Serializer cannot be null.");
        this.serializer = serializer;
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder base64UrlEncodeWith(Encoder<byte[], String> encoder) {
        Assert.notNull(encoder, "base64UrlEncoder cannot be null.");
        this.base64UrlEncoder = encoder;
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setHeader(Header header) {
        this.header = header;
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setHeader(Map<String, Object> map) {
        this.header = new DefaultHeader(map);
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setHeaderParams(Map<String, Object> map) {
        if (!Collections.isEmpty(map)) {
            Header ensureHeader = ensureHeader();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                ensureHeader.put(entry.getKey(), entry.getValue());
            }
        }
        return this;
    }

    protected Header ensureHeader() {
        if (this.header == null) {
            this.header = new DefaultHeader();
        }
        return this.header;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setHeaderParam(String str, Object obj) {
        ensureHeader().put(str, obj);
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder signWith(Key key) throws InvalidKeyException {
        Assert.notNull(key, "Key argument cannot be null.");
        return signWith(key, SignatureAlgorithm.forSigningKey(key));
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder signWith(Key key, SignatureAlgorithm signatureAlgorithm) throws InvalidKeyException {
        Assert.notNull(key, "Key argument cannot be null.");
        Assert.notNull(signatureAlgorithm, "SignatureAlgorithm cannot be null.");
        signatureAlgorithm.assertValidSigningKey(key);
        this.algorithm = signatureAlgorithm;
        this.key = key;
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder signWith(SignatureAlgorithm signatureAlgorithm, byte[] bArr) throws InvalidKeyException {
        Assert.notNull(signatureAlgorithm, "SignatureAlgorithm cannot be null.");
        Assert.notEmpty(bArr, "secret key byte array cannot be null or empty.");
        Assert.isTrue(signatureAlgorithm.isHmac(), "Key bytes may only be specified for HMAC signatures.  If using RSA or Elliptic Curve, use the signWith(SignatureAlgorithm, Key) method instead.");
        return signWith(new SecretKeySpec(bArr, signatureAlgorithm.getJcaName()), signatureAlgorithm);
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder signWith(SignatureAlgorithm signatureAlgorithm, String str) throws InvalidKeyException {
        Assert.hasText(str, "base64-encoded secret key cannot be null or empty.");
        Assert.isTrue(signatureAlgorithm.isHmac(), "Base64-encoded key bytes may only be specified for HMAC signatures.  If using RSA or Elliptic Curve, use the signWith(SignatureAlgorithm, Key) method instead.");
        return signWith(signatureAlgorithm, Decoders.BASE64.decode(str));
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder signWith(SignatureAlgorithm signatureAlgorithm, Key key) {
        return signWith(key, signatureAlgorithm);
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder compressWith(CompressionCodec compressionCodec) {
        Assert.notNull(compressionCodec, "compressionCodec cannot be null");
        this.compressionCodec = compressionCodec;
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setPayload(String str) {
        this.payload = str;
        return this;
    }

    protected Claims ensureClaims() {
        if (this.claims == null) {
            this.claims = new DefaultClaims();
        }
        return this.claims;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setClaims(Claims claims) {
        this.claims = claims;
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder setClaims(Map<String, Object> map) {
        this.claims = new DefaultClaims(map);
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder addClaims(Map<String, Object> map) {
        ensureClaims().putAll(map);
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setIssuer(String str) {
        if (Strings.hasText(str)) {
            ensureClaims().setIssuer(str);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setIssuer(str);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setSubject(String str) {
        if (Strings.hasText(str)) {
            ensureClaims().setSubject(str);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setSubject(str);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setAudience(String str) {
        if (Strings.hasText(str)) {
            ensureClaims().setAudience(str);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setAudience(str);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setExpiration(Date date) {
        if (date != null) {
            ensureClaims().setExpiration(date);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setExpiration(date);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setNotBefore(Date date) {
        if (date != null) {
            ensureClaims().setNotBefore(date);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setNotBefore(date);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setIssuedAt(Date date) {
        if (date != null) {
            ensureClaims().setIssuedAt(date);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setIssuedAt(date);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.ClaimsMutator
    public JwtBuilder setId(String str) {
        if (Strings.hasText(str)) {
            ensureClaims().setId(str);
        } else {
            Claims claims = this.claims;
            if (claims != null) {
                claims.setId(str);
            }
        }
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public JwtBuilder claim(String str, Object obj) {
        Assert.hasText(str, "Claim property name cannot be null or empty.");
        Claims claims = this.claims;
        if (claims == null) {
            if (obj != null) {
                ensureClaims().put(str, obj);
            }
        } else if (obj == null) {
            claims.remove(str);
        } else {
            claims.put(str, obj);
        }
        return this;
    }

    @Override // io.jsonwebtoken.JwtBuilder
    public String compact() {
        JwsHeader defaultJwsHeader;
        if (this.serializer == null) {
            this.serializer = (Serializer) ((InstanceLocator) Classes.newInstance("io.jsonwebtoken.impl.io.RuntimeClasspathSerializerLocator")).getInstance2();
        }
        if (this.payload == null && Collections.isEmpty(this.claims)) {
            throw new IllegalStateException("Either 'payload' or 'claims' must be specified.");
        }
        if (this.payload != null && !Collections.isEmpty(this.claims)) {
            throw new IllegalStateException("Both 'payload' and 'claims' cannot both be specified. Choose either one.");
        }
        Header ensureHeader = ensureHeader();
        if (ensureHeader instanceof JwsHeader) {
            defaultJwsHeader = (JwsHeader) ensureHeader;
        } else {
            defaultJwsHeader = new DefaultJwsHeader(ensureHeader);
        }
        if (this.key != null) {
            defaultJwsHeader.setAlgorithm(this.algorithm.getValue());
        } else {
            defaultJwsHeader.setAlgorithm(SignatureAlgorithm.NONE.getValue());
        }
        CompressionCodec compressionCodec = this.compressionCodec;
        if (compressionCodec != null) {
            defaultJwsHeader.setCompressionAlgorithm(compressionCodec.getAlgorithmName());
        }
        String base64UrlEncode = base64UrlEncode(defaultJwsHeader, "Unable to serialize header to json.");
        try {
            String str = this.payload;
            byte[] bytes = str != null ? str.getBytes(Strings.UTF_8) : toJson(this.claims);
            CompressionCodec compressionCodec2 = this.compressionCodec;
            if (compressionCodec2 != null) {
                bytes = compressionCodec2.compress(bytes);
            }
            String str2 = base64UrlEncode + JwtParser.SEPARATOR_CHAR + this.base64UrlEncoder.encode(bytes);
            Key key = this.key;
            if (key != null) {
                return str2 + JwtParser.SEPARATOR_CHAR + createSigner(this.algorithm, key).sign(str2);
            }
            return str2 + JwtParser.SEPARATOR_CHAR;
        } catch (SerializationException e) {
            throw new IllegalArgumentException("Unable to serialize claims object to json: " + e.getMessage(), e);
        }
    }

    protected JwtSigner createSigner(SignatureAlgorithm signatureAlgorithm, Key key) {
        return new DefaultJwtSigner(signatureAlgorithm, key, this.base64UrlEncoder);
    }

    @Deprecated
    protected String base64UrlEncode(Object obj, String str) {
        Assert.isInstanceOf(Map.class, obj, "object argument must be a map.");
        try {
            return this.base64UrlEncoder.encode(toJson((Map) obj));
        } catch (SerializationException e) {
            throw new IllegalStateException(str, e);
        }
    }

    @Deprecated
    protected byte[] toJson(Object obj) throws SerializationException {
        Assert.isInstanceOf(Map.class, obj, "object argument must be a map.");
        return this.serializer.serialize((Map) obj);
    }
}
