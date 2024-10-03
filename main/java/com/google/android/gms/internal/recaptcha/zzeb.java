package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzeb {
    private final zzed zza;
    private final zzfg zzb;
    private final List<zzfl> zzc;
    private final List<zzfk> zzd;
    private final Uri zze;
    private final Uri zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeb(zzea zzeaVar) {
        zzed zzedVar;
        zzfg zzfgVar;
        List<zzfl> list;
        List<zzfk> list2;
        Uri uri;
        Uri uri2;
        zzedVar = zzeaVar.zza;
        this.zza = zzedVar;
        zzfgVar = zzeaVar.zzb;
        this.zzb = zzfgVar;
        list = zzeaVar.zzc;
        this.zzc = list;
        list2 = zzeaVar.zzd;
        this.zzd = list2;
        uri = zzeaVar.zze;
        this.zze = uri;
        uri2 = zzeaVar.zzf;
        this.zzf = uri2;
    }

    public final Uri zza() {
        return this.zzf;
    }

    public final zzfg zzb() {
        return this.zzb;
    }

    public final List<InputStream> zzc(InputStream inputStream) throws IOException {
        zzdx zza;
        ArrayList arrayList = new ArrayList();
        arrayList.add(inputStream);
        if (!this.zzd.isEmpty() && (zza = zzdx.zza(this.zzd, this.zze, inputStream)) != null) {
            arrayList.add(zza);
        }
        for (zzfl zzflVar : this.zzc) {
            arrayList.add(zzflVar.zzc());
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    public final List<OutputStream> zzd(OutputStream outputStream) throws IOException {
        zzdy zza;
        ArrayList arrayList = new ArrayList();
        arrayList.add(outputStream);
        if (!this.zzd.isEmpty() && (zza = zzdy.zza(this.zzd, this.zze, outputStream)) != null) {
            arrayList.add(zza);
        }
        for (zzfl zzflVar : this.zzc) {
            arrayList.add(zzflVar.zzd());
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    public final boolean zze() {
        return !this.zzc.isEmpty();
    }
}
