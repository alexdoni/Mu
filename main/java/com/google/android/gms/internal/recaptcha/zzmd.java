package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzmd extends zzmf {
    final char[] zza;

    private zzmd(zzmc zzmcVar) {
        super(zzmcVar, null);
        char[] cArr;
        this.zza = new char[512];
        cArr = zzmcVar.zzf;
        zzjn.zze(cArr.length == 16);
        for (int i = 0; i < 256; i++) {
            this.zza[i] = zzmcVar.zza(i >>> 4);
            this.zza[i | 256] = zzmcVar.zza(i & 15);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzmf
    final zzmg zza(zzmc zzmcVar, @CheckForNull Character ch) {
        return new zzmd(zzmcVar);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzmf, com.google.android.gms.internal.recaptcha.zzmg
    final void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzjn.zzh(0, i2, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = bArr[i3] & 255;
            appendable.append(this.zza[i4]);
            appendable.append(this.zza[i4 | 256]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmd(String str, String str2) {
        this(new zzmc("base16()", "0123456789ABCDEF".toCharArray()));
    }
}
