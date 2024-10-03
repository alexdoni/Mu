package com.p008ld.sdk.zzd.zza;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/* compiled from: LDRequestBodyConverter.java */
/* loaded from: classes2.dex */
final class zzb<T> implements Converter<T, RequestBody> {
    private static final MediaType zza = MediaType.get("application/json; charset=UTF-8");
    private static final Charset zzb = Charset.forName("UTF-8");
    private final Gson zzc;
    private final TypeAdapter<T> zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(Gson gson, TypeAdapter<T> typeAdapter) {
        this.zzc = gson;
        this.zzd = typeAdapter;
    }

    @Override // retrofit2.Converter
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public RequestBody convert(T t) throws IOException {
        Buffer buffer = new Buffer();
        JsonWriter newJsonWriter = this.zzc.newJsonWriter(new OutputStreamWriter(buffer.outputStream(), zzb));
        this.zzd.write(newJsonWriter, t);
        newJsonWriter.close();
        return RequestBody.create(zza, buffer.readByteString());
    }
}
