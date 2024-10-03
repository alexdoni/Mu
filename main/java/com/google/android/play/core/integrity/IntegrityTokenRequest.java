package com.google.android.play.core.integrity;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
public abstract class IntegrityTokenRequest {

    /* compiled from: com.google.android.play:integrity@@1.0.1 */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public abstract IntegrityTokenRequest build();

        public abstract Builder setCloudProjectNumber(long j);

        public abstract Builder setNonce(String str);
    }

    public static Builder builder() {
        return new zza();
    }

    public abstract Long cloudProjectNumber();

    public abstract String nonce();
}
