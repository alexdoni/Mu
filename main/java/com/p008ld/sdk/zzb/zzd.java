package com.p008ld.sdk.zzb;

import com.google.gson.Gson;
import com.p008ld.sdk.zzd.zzb;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDCacheWrapper.kt */
/* loaded from: classes2.dex */
public abstract class zzd extends zzb {
    private Gson zzb = zzb.zza.zza().zza();

    @Override // com.p008ld.sdk.zzb.zzb
    public <T> void zza(String key, T t) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (t == null) {
            return;
        }
        String json = this.zzb.toJson(t);
        Intrinsics.checkNotNullExpressionValue(json, "json");
        zza(key, json);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0021 A[Catch: Exception -> 0x0028, TRY_LEAVE, TryCatch #0 {Exception -> 0x0028, blocks: (B:3:0x000b, B:5:0x0014, B:12:0x0021), top: B:2:0x000b }] */
    @Override // com.p008ld.sdk.zzb.zzb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T zza(java.lang.String r2, long r3, java.lang.Class<T> r5) {
        /*
            r1 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "cls"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            r0 = 0
            java.lang.String r2 = r1.zza(r2, r3)     // Catch: java.lang.Exception -> L28
            r3 = r2
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch: java.lang.Exception -> L28
            if (r3 == 0) goto L1d
            boolean r3 = kotlin.text.StringsKt.isBlank(r3)     // Catch: java.lang.Exception -> L28
            if (r3 == 0) goto L1b
            goto L1d
        L1b:
            r3 = 0
            goto L1e
        L1d:
            r3 = 1
        L1e:
            if (r3 == 0) goto L21
            return r0
        L21:
            com.google.gson.Gson r3 = r1.zzb     // Catch: java.lang.Exception -> L28
            java.lang.Object r2 = r3.fromJson(r2, r5)     // Catch: java.lang.Exception -> L28
            return r2
        L28:
            r2 = move-exception
            r2.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.zzb.zzd.zza(java.lang.String, long, java.lang.Class):java.lang.Object");
    }

    @Override // com.p008ld.sdk.zzb.zzb
    public <T> void zza(String key, List<? extends T> entity) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(entity, "entity");
        if (entity.isEmpty()) {
            return;
        }
        String json = this.zzb.toJson(entity);
        Intrinsics.checkNotNullExpressionValue(json, "json");
        zza(key, json);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0021 A[Catch: Exception -> 0x0028, TRY_LEAVE, TryCatch #0 {Exception -> 0x0028, blocks: (B:3:0x000b, B:5:0x0014, B:12:0x0021), top: B:2:0x000b }] */
    @Override // com.p008ld.sdk.zzb.zzb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T zza(java.lang.String r2, long r3, java.lang.reflect.Type r5) {
        /*
            r1 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "typeOfT"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            r0 = 0
            java.lang.String r2 = r1.zza(r2, r3)     // Catch: java.lang.Exception -> L28
            r3 = r2
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch: java.lang.Exception -> L28
            if (r3 == 0) goto L1d
            boolean r3 = kotlin.text.StringsKt.isBlank(r3)     // Catch: java.lang.Exception -> L28
            if (r3 == 0) goto L1b
            goto L1d
        L1b:
            r3 = 0
            goto L1e
        L1d:
            r3 = 1
        L1e:
            if (r3 == 0) goto L21
            return r0
        L21:
            com.google.gson.Gson r3 = r1.zzb     // Catch: java.lang.Exception -> L28
            java.lang.Object r2 = r3.fromJson(r2, r5)     // Catch: java.lang.Exception -> L28
            return r2
        L28:
            r2 = move-exception
            r2.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.zzb.zzd.zza(java.lang.String, long, java.lang.reflect.Type):java.lang.Object");
    }
}
