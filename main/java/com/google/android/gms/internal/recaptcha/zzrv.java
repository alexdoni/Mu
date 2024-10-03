package com.google.android.gms.internal.recaptcha;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzrv extends zzpk<String> implements RandomAccess, zzrw {
    public static final zzrw zza;
    private static final zzrv zzb;
    private final List<Object> zzc;

    static {
        zzrv zzrvVar = new zzrv(10);
        zzb = zzrvVar;
        zzrvVar.zzb();
        zza = zzrvVar;
    }

    public zzrv() {
        this(10);
    }

    private static String zzj(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzpy) {
            return ((zzpy) obj).zzr(zzrp.zza);
        }
        return zzrp.zzh((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpk, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpk, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zza();
        if (collection instanceof zzrw) {
            collection = ((zzrw) collection).zzh();
        }
        boolean addAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpk, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpk, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        Object remove = this.zzc.remove(i);
        this.modCount++;
        return zzj(remove);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpk, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzj(this.zzc.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzro
    public final /* bridge */ /* synthetic */ zzro zzd(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzc);
        return new zzrv((ArrayList<Object>) arrayList);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrw
    public final zzrw zze() {
        return zzc() ? new zztw(this) : this;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrw
    public final Object zzf(int i) {
        return this.zzc.get(i);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: zzg, reason: merged with bridge method [inline-methods] */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzpy) {
            zzpy zzpyVar = (zzpy) obj;
            String zzr = zzpyVar.zzr(zzrp.zza);
            if (zzpyVar.zzj()) {
                this.zzc.set(i, zzr);
            }
            return zzr;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzrp.zzh(bArr);
        if (zzrp.zzj(bArr)) {
            this.zzc.set(i, zzh);
        }
        return zzh;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrw
    public final List<?> zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrw
    public final void zzi(zzpy zzpyVar) {
        zza();
        this.zzc.add(zzpyVar);
        this.modCount++;
    }

    public zzrv(int i) {
        this.zzc = new ArrayList(i);
    }

    private zzrv(ArrayList<Object> arrayList) {
        this.zzc = arrayList;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzpk, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }
}
