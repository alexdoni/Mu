package com.p008ld.sdk.zzd.zza;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* compiled from: LDGsonConverterFactory.java */
/* loaded from: classes2.dex */
public final class zza extends Converter.Factory {
    private final Gson zza;

    public static zza zza() {
        return zza(new Gson());
    }

    public static zza zza(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        return new zza(gson);
    }

    private zza(Gson gson) {
        this.zza = gson;
    }

    @Override // retrofit2.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        return new zzc(this.zza, this.zza.getAdapter(TypeToken.get(type)));
    }

    @Override // retrofit2.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        return new zzb(this.zza, this.zza.getAdapter(TypeToken.get(type)));
    }
}
