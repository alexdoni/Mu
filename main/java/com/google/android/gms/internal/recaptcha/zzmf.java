package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public class zzmf extends zzmg {

    @CheckForNull
    private transient zzmg zza;
    final zzmc zzb;

    @CheckForNull
    final Character zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmf(zzmc zzmcVar, @CheckForNull Character ch) {
        this.zzb = zzmcVar;
        boolean z = true;
        if (ch != null && zzmcVar.zzc(ch.charValue())) {
            z = false;
        }
        zzjn.zzg(z, "Padding character %s was already in alphabet", ch);
        this.zzc = ch;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzmf) {
            zzmf zzmfVar = (zzmf) obj;
            if (this.zzb.equals(zzmfVar.zzb) && zzji.zza(this.zzc, zzmfVar.zzc)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.zzb.hashCode() ^ Arrays.hashCode(new Object[]{this.zzc});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.zzb.toString());
        if (8 % this.zzb.zzb != 0) {
            if (this.zzc == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.zzc);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    zzmg zza(zzmc zzmcVar, @CheckForNull Character ch) {
        return new zzmf(zzmcVar, ch);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzmg
    void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzjn.zzh(0, i2, bArr.length);
        while (i3 < i2) {
            zze(appendable, bArr, i3, Math.min(this.zzb.zzd, i2 - i3));
            i3 += this.zzb.zzd;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzmg
    final int zzc(int i) {
        zzmc zzmcVar = this.zzb;
        return zzmcVar.zzc * zzmk.zza(i, zzmcVar.zzd, RoundingMode.CEILING);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzmg
    public final zzmg zzd() {
        zzmg zzmgVar = this.zza;
        if (zzmgVar == null) {
            zzmc zzb = this.zzb.zzb();
            zzmgVar = zzb == this.zzb ? this : zza(zzb, this.zzc);
            this.zza = zzmgVar;
        }
        return zzmgVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzjn.zzh(i, i + i2, bArr.length);
        int i3 = 0;
        zzjn.zze(i2 <= this.zzb.zzd);
        long j = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            j = (j | (bArr[i + i4] & 255)) << 8;
        }
        int i5 = ((i2 + 1) * 8) - this.zzb.zzb;
        while (i3 < i2 * 8) {
            zzmc zzmcVar = this.zzb;
            appendable.append(zzmcVar.zza(((int) (j >>> (i5 - i3))) & zzmcVar.zza));
            i3 += this.zzb.zzb;
        }
        if (this.zzc != null) {
            while (i3 < this.zzb.zzd * 8) {
                appendable.append(this.zzc.charValue());
                i3 += this.zzb.zzb;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmf(String str, String str2, @CheckForNull Character ch) {
        this(new zzmc(str, str2.toCharArray()), ch);
    }
}
