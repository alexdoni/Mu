package com.google.android.gms.internal.recaptcha;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzsh<K, V> extends LinkedHashMap<K, V> {
    private static final zzsh zza;
    private boolean zzb;

    static {
        zzsh zzshVar = new zzsh();
        zza = zzshVar;
        zzshVar.zzb = false;
    }

    private zzsh() {
        this.zzb = true;
    }

    public static <K, V> zzsh<K, V> zza() {
        return zza;
    }

    private static int zzf(Object obj) {
        if (obj instanceof byte[]) {
            return zzrp.zzb((byte[]) obj);
        }
        if (obj instanceof zzri) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    private final void zzg() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzg();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        boolean equals;
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        for (Map.Entry<K, V> entry : entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            V value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(value instanceof byte[]) || !(obj2 instanceof byte[])) {
                equals = value.equals(obj2);
            } else {
                equals = Arrays.equals((byte[]) value, (byte[]) obj2);
            }
            if (!equals) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int i = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            i += zzf(entry.getValue()) ^ zzf(entry.getKey());
        }
        return i;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        zzg();
        zzrp.zze(k);
        zzrp.zze(v);
        return (V) super.put(k, v);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        zzg();
        for (K k : map.keySet()) {
            zzrp.zze(k);
            zzrp.zze(map.get(k));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        zzg();
        return (V) super.remove(obj);
    }

    public final zzsh<K, V> zzb() {
        return isEmpty() ? new zzsh<>() : new zzsh<>(this);
    }

    public final void zzc() {
        this.zzb = false;
    }

    public final void zzd(zzsh<K, V> zzshVar) {
        zzg();
        if (zzshVar.isEmpty()) {
            return;
        }
        putAll(zzshVar);
    }

    public final boolean zze() {
        return this.zzb;
    }

    private zzsh(Map<K, V> map) {
        super(map);
        this.zzb = true;
    }
}
