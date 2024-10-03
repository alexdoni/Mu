package com.google.android.gms.internal.recaptcha;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public class zzrr extends IOException {
    private zzsn zza;
    private boolean zzb;

    public zzrr(IOException iOException) {
        super(iOException.getMessage(), iOException);
        this.zza = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzrq zza() {
        return new zzrq("Protocol message tag had invalid wire type.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzrr zzb() {
        return new zzrr("Protocol message end-group tag did not match expected tag.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzrr zzc() {
        return new zzrr("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzrr zzd() {
        return new zzrr("Protocol message had invalid UTF-8.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzrr zze() {
        return new zzrr("CodedInputStream encountered a malformed varint.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzrr zzf() {
        return new zzrr("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzrr zzg() {
        return new zzrr("Failed to parse the message.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzrr zzi() {
        return new zzrr("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzrr zzj() {
        return new zzrr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzrr zzh(zzsn zzsnVar) {
        this.zza = zzsnVar;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzk() {
        this.zzb = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzl() {
        return this.zzb;
    }

    public zzrr(String str) {
        super(str);
        this.zza = null;
    }
}
