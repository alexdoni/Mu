package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class ACloudStSBean extends BaseBean<Data> {
    private Aes123EcbCredentials aes123EcbCredentials;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.oversea.ab_firstplatform.model.BaseBean
    public Data getData() {
        if (super.getData() == null) {
            setData(new Data());
        }
        return (Data) super.getData();
    }

    public Aes123EcbCredentials getAes123EcbCredentials() {
        return this.aes123EcbCredentials;
    }

    public void setAes123EcbCredentials(Aes123EcbCredentials aes123EcbCredentials) {
        this.aes123EcbCredentials = aes123EcbCredentials;
    }

    /* loaded from: classes.dex */
    public static class Data {
        private Cos cos;
        private String credentials;
        private Oss oss;

        public Cos getCos() {
            return this.cos;
        }

        public void setCos(Cos cos) {
            this.cos = cos;
        }

        public void setOss(Oss oss) {
            this.oss = oss;
        }

        public Oss getOss() {
            return this.oss;
        }

        public void setCredentials(String str) {
            this.credentials = str;
        }

        public String getCredentials() {
            return this.credentials;
        }
    }

    /* loaded from: classes.dex */
    public static class Oss {
        private String bucket_domain;
        private String bucket_name;
        private String endpoint;
        private String image_domain;
        private String region;

        public String getImage_domain() {
            return this.image_domain;
        }

        public void setImage_domain(String str) {
            this.image_domain = str;
        }

        public void setBucket_name(String str) {
            this.bucket_name = str;
        }

        public String getBucket_name() {
            return this.bucket_name;
        }

        public void setBucket_domain(String str) {
            this.bucket_domain = str;
        }

        public String getBucket_domain() {
            return this.bucket_domain;
        }

        public void setEndpoint(String str) {
            this.endpoint = str;
        }

        public String getEndpoint() {
            return this.endpoint;
        }

        public void setRegion(String str) {
            this.region = str;
        }

        public String getRegion() {
            return this.region;
        }
    }

    /* loaded from: classes.dex */
    public static class Cos {
        private String bucket_domain;
        private String bucket_name;
        private String endpoint;
        private String image_domain;
        private String region;

        public String getImage_domain() {
            return this.image_domain;
        }

        public void setImage_domain(String str) {
            this.image_domain = str;
        }

        public void setBucket_name(String str) {
            this.bucket_name = str;
        }

        public String getBucket_name() {
            return this.bucket_name;
        }

        public void setBucket_domain(String str) {
            this.bucket_domain = str;
        }

        public String getBucket_domain() {
            return this.bucket_domain;
        }

        public void setEndpoint(String str) {
            this.endpoint = str;
        }

        public String getEndpoint() {
            return this.endpoint;
        }

        public void setRegion(String str) {
            this.region = str;
        }

        public String getRegion() {
            return this.region;
        }
    }

    /* loaded from: classes.dex */
    public static class Aes123EcbCredentials {
        private String AccessKeyId;
        private String AccessKeySecret;
        private String Expiration;
        private String SecurityToken;
        private long expiredTime;
        private long startTime;

        public long getExpiredTime() {
            return this.expiredTime;
        }

        public void setExpiredTime(long j) {
            this.expiredTime = j;
        }

        public long getStartTime() {
            return this.startTime;
        }

        public void setStartTime(long j) {
            this.startTime = j;
        }

        public String getSecurityToken() {
            return this.SecurityToken;
        }

        public void setSecurityToken(String str) {
            this.SecurityToken = str;
        }

        public String getAccessKeyId() {
            return this.AccessKeyId;
        }

        public void setAccessKeyId(String str) {
            this.AccessKeyId = str;
        }

        public String getAccessKeySecret() {
            return this.AccessKeySecret;
        }

        public void setAccessKeySecret(String str) {
            this.AccessKeySecret = str;
        }

        public String getExpiration() {
            return this.Expiration;
        }

        public void setExpiration(String str) {
            this.Expiration = str;
        }
    }
}
