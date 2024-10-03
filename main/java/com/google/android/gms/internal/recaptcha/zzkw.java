package com.google.android.gms.internal.recaptcha;

import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzkw<K, V> extends zzkl<K, V> {
    static final zzkl<Object, Object> zza = new zzkw(null, new Object[0], 0);
    final transient Object[] zzb;
    private final transient Object zzc;
    private final transient int zzd;

    private zzkw(Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0058, code lost:
    
        r2[r6] = (byte) r3;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0099, code lost:
    
        r2[r6] = (short) r3;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00ce, code lost:
    
        r2[r7] = r3;
        r1 = r1 + 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [int[]] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r8v0, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <K, V> com.google.android.gms.internal.recaptcha.zzkw<K, V> zzg(int r10, java.lang.Object[] r11) {
        /*
            Method dump skipped, instructions count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.recaptcha.zzkw.zzg(int, java.lang.Object[]):com.google.android.gms.internal.recaptcha.zzkw");
    }

    private static IllegalArgumentException zzh(Object obj, Object obj2, Object[] objArr, int i) {
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        String valueOf3 = String.valueOf(objArr[i]);
        String valueOf4 = String.valueOf(objArr[i ^ 1]);
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        StringBuilder sb = new StringBuilder(length + 39 + length2 + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("Multiple entries with same key: ");
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        sb.append(" and ");
        sb.append(valueOf3);
        sb.append("=");
        sb.append(valueOf4);
        return new IllegalArgumentException(sb.toString());
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkl, java.util.Map
    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        Object obj2 = this.zzc;
        Object[] objArr = this.zzb;
        int i = this.zzd;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (objArr[0].equals(obj)) {
                return (V) objArr[1];
            }
            return null;
        }
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof byte[]) {
            byte[] bArr = (byte[]) obj2;
            int length = bArr.length - 1;
            int zza2 = zzkb.zza(obj.hashCode());
            while (true) {
                int i2 = zza2 & length;
                int i3 = bArr[i2] & 255;
                if (i3 == 255) {
                    return null;
                }
                if (objArr[i3].equals(obj)) {
                    return (V) objArr[i3 ^ 1];
                }
                zza2 = i2 + 1;
            }
        } else if (obj2 instanceof short[]) {
            short[] sArr = (short[]) obj2;
            int length2 = sArr.length - 1;
            int zza3 = zzkb.zza(obj.hashCode());
            while (true) {
                int i4 = zza3 & length2;
                char c = (char) sArr[i4];
                if (c == 65535) {
                    return null;
                }
                if (objArr[c].equals(obj)) {
                    return (V) objArr[c ^ 1];
                }
                zza3 = i4 + 1;
            }
        } else {
            int[] iArr = (int[]) obj2;
            int length3 = iArr.length - 1;
            int zza4 = zzkb.zza(obj.hashCode());
            while (true) {
                int i5 = zza4 & length3;
                int i6 = iArr[i5];
                if (i6 == -1) {
                    return null;
                }
                if (objArr[i6].equals(obj)) {
                    return (V) objArr[i6 ^ 1];
                }
                zza4 = i5 + 1;
            }
        }
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkl
    final zzke<V> zza() {
        return new zzkv(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkl
    final zzkn<Map.Entry<K, V>> zzd() {
        return new zzkt(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzkl
    final zzkn<K> zze() {
        return new zzku(this, new zzkv(this.zzb, 0, this.zzd));
    }
}
