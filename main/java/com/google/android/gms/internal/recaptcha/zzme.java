package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzme extends zzmf {
    private zzme(zzmc zzmcVar, @CheckForNull Character ch) {
        super(zzmcVar, ch);
        char[] cArr;
        cArr = zzmcVar.zzf;
        zzjn.zze(cArr.length == 64);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzmf
    final zzmg zza(zzmc zzmcVar, @CheckForNull Character ch) {
        return new zzme(zzmcVar, ch);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzmf, com.google.android.gms.internal.recaptcha.zzmg
    final void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzjn.zzh(0, i2, bArr.length);
        int i4 = i2;
        while (i4 >= 3) {
            int i5 = i3 + 1;
            int i6 = i5 + 1;
            int i7 = ((bArr[i3] & 255) << 16) | ((bArr[i5] & 255) << 8) | (bArr[i6] & 255);
            appendable.append(this.zzb.zza(i7 >>> 18));
            appendable.append(this.zzb.zza((i7 >>> 12) & 63));
            appendable.append(this.zzb.zza((i7 >>> 6) & 63));
            appendable.append(this.zzb.zza(i7 & 63));
            i4 -= 3;
            i3 = i6 + 1;
        }
        if (i3 < i2) {
            zze(appendable, bArr, i3, i2 - i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzme(String str, String str2, @CheckForNull Character ch) {
        this(new zzmc(str, str2.toCharArray()), ch);
    }
}
