package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
enum zzlr implements zzls {
    INSTANCE;

    @Override // com.google.android.gms.internal.recaptcha.zzls
    public final /* synthetic */ long zza(byte[] bArr, int i) {
        if (ordinal() != 0) {
            throw null;
        }
        return (bArr[i] & 255) | ((bArr[i + 7] & 255) << 56) | ((bArr[i + 6] & 255) << 48) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 1] & 255) << 8);
    }
}
