package com.linecorp.linesdk.internal;

import android.text.TextUtils;
import java.util.List;

/* loaded from: classes2.dex */
public class JWKSet {
    private final List<JWK> keys;

    private JWKSet(Builder builder) {
        this.keys = builder.keys;
    }

    public List<JWK> getKeys() {
        return this.keys;
    }

    public JWK getJWK(String str) {
        for (JWK jwk : this.keys) {
            if (TextUtils.equals(jwk.getKeyId(), str)) {
                return jwk;
            }
        }
        return null;
    }

    public String toString() {
        return "JWKSet{keys=" + this.keys + '}';
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private List<JWK> keys;

        public Builder keys(List<JWK> list) {
            this.keys = list;
            return this;
        }

        public JWKSet build() {
            return new JWKSet(this);
        }
    }

    /* loaded from: classes2.dex */
    public static class JWK {
        private final String algorithm;
        private final String curve;
        private final String keyId;
        private final String keyType;
        private final String use;

        /* renamed from: x */
        private final String f693x;

        /* renamed from: y */
        private final String f694y;

        private JWK(Builder builder) {
            this.keyType = builder.keyType;
            this.algorithm = builder.algorithm;
            this.use = builder.use;
            this.keyId = builder.keyId;
            this.curve = builder.curve;
            this.f693x = builder.f695x;
            this.f694y = builder.f696y;
        }

        public String getKeyType() {
            return this.keyType;
        }

        public String getAlgorithm() {
            return this.algorithm;
        }

        public String getUse() {
            return this.use;
        }

        public String getKeyId() {
            return this.keyId;
        }

        public String getCurve() {
            return this.curve;
        }

        public String getX() {
            return this.f693x;
        }

        public String getY() {
            return this.f694y;
        }

        public String toString() {
            return "JWK{keyType='" + this.keyType + "', algorithm='" + this.algorithm + "', use='" + this.use + "', keyId='" + this.keyId + "', curve='" + this.curve + "', x='" + this.f693x + "', y='" + this.f694y + "'}";
        }

        /* loaded from: classes2.dex */
        public static final class Builder {
            private String algorithm;
            private String curve;
            private String keyId;
            private String keyType;
            private String use;

            /* renamed from: x */
            private String f695x;

            /* renamed from: y */
            private String f696y;

            public Builder keyType(String str) {
                this.keyType = str;
                return this;
            }

            public Builder algorithm(String str) {
                this.algorithm = str;
                return this;
            }

            public Builder use(String str) {
                this.use = str;
                return this;
            }

            public Builder keyId(String str) {
                this.keyId = str;
                return this;
            }

            public Builder curve(String str) {
                this.curve = str;
                return this;
            }

            /* renamed from: x */
            public Builder m586x(String str) {
                this.f695x = str;
                return this;
            }

            /* renamed from: y */
            public Builder m587y(String str) {
                this.f696y = str;
                return this;
            }

            public JWK build() {
                return new JWK(this);
            }
        }
    }
}
