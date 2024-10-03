package com.p008ld.sdk.util;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.p008ld.sdk.zzb.zzb;
import java.lang.reflect.Type;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* compiled from: LDUtil.kt */
/* loaded from: classes2.dex */
public final class zzm {
    private static final Lazy zza = LazyKt.lazy(zza.zza);

    public static final RequestBody zza(Map<?, ?> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        String json = new Gson().toJson(map);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(json, "json");
        return companion.create(json, MediaType.INSTANCE.get("application/json;charset=utf-8"));
    }

    /* compiled from: LDUtil.kt */
    /* loaded from: classes2.dex */
    static final class zza extends Lambda implements Function0<Gson> {
        public static final zza zza = new zza();

        zza() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public final Gson invoke() {
            return new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
        }
    }

    public static final Gson zza() {
        Object value = zza.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-gson>(...)");
        return (Gson) value;
    }

    public static final Map<String, String> zza(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Object fromJson = zza().fromJson(str, (Type) Map.class);
        Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson<Map<String…>>(this, Map::class.java)");
        return (Map) fromJson;
    }

    public static final String zzb(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String str2 = (String) zzb.zza.zza().zza(str, Long.MAX_VALUE, String.class);
        return str2 == null ? "" : str2;
    }

    public static final void zza(final Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            block.invoke();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ld.sdk.util.zzm$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    zzm.zzb(Function0.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zzb(Function0 block) {
        Intrinsics.checkNotNullParameter(block, "$block");
        block.invoke();
    }
}
