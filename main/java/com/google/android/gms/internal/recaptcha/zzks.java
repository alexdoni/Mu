package com.google.android.gms.internal.recaptcha;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzks extends zzkj<Map.Entry> {
    final /* synthetic */ zzkt zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzks(zzkt zzktVar) {
        this.zza = zzktVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        int i2;
        Object[] objArr;
        Object[] objArr2;
        i2 = this.zza.zzc;
        zzjn.zza(i, i2, FirebaseAnalytics.Param.INDEX);
        objArr = this.zza.zzb;
        int i3 = i + i;
        Object obj = objArr[i3];
        objArr2 = this.zza.zzb;
        return new AbstractMap.SimpleImmutableEntry(obj, objArr2[i3 + 1]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i;
        i = this.zza.zzc;
        return i;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzke
    public final boolean zzf() {
        return true;
    }
}
