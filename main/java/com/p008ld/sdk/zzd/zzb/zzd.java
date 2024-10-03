package com.p008ld.sdk.zzd.zzb;

import android.text.TextUtils;
import com.p008ld.sdk.LDSdk;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: LoggingInterceptor.java */
/* loaded from: classes2.dex */
public class zzd implements Interceptor {
    private zza zza;

    private zzd(zza zzaVar) {
        this.zza = zzaVar;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (this.zza.zzc().size() > 0) {
            Headers headers = request.headers();
            Request.Builder newBuilder = request.newBuilder();
            newBuilder.headers(this.zza.zzc());
            for (String str : headers.names()) {
                newBuilder.addHeader(str, headers.get(str));
            }
            request = newBuilder.build();
        }
        if (!LDSdk.getDebugLogEnable() || this.zza.zzb() == zzb.NONE) {
            return chain.proceed(request);
        }
        MediaType contentType = request.body() != null ? request.body().getContentType() : null;
        String subtype = contentType != null ? contentType.subtype() : null;
        if (subtype != null && (subtype.contains("json") || subtype.contains("xml") || subtype.contains("plain") || subtype.contains("html"))) {
            zze.zza(this.zza, request);
        } else {
            zze.zzb(this.zza, request);
        }
        long nanoTime = System.nanoTime();
        Response proceed = chain.proceed(request);
        List<String> encodedPathSegments = request.url().encodedPathSegments();
        long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
        String headers2 = proceed.headers().toString();
        int code = proceed.code();
        boolean isSuccessful = proceed.isSuccessful();
        ResponseBody body = proceed.body();
        MediaType mediaType = body.get$contentType();
        String subtype2 = mediaType != null ? mediaType.subtype() : null;
        if (subtype2 != null && (subtype2.contains("json") || subtype2.contains("xml") || subtype2.contains("plain") || subtype2.contains("html"))) {
            String string = body.string();
            zze.zza(this.zza, millis, isSuccessful, code, headers2, zze.zzc(string), encodedPathSegments);
            return proceed.newBuilder().body(ResponseBody.create(mediaType, string)).build();
        }
        zze.zza(this.zza, millis, isSuccessful, code, headers2, encodedPathSegments);
        return proceed;
    }

    /* compiled from: LoggingInterceptor.java */
    /* loaded from: classes2.dex */
    public static class zza {
        private static String zza = "LoggingI";
        private String zzc;
        private String zzd;
        private zzc zzg;
        private int zzb = 4;
        private zzb zze = zzb.BASIC;
        private Headers.Builder zzf = new Headers.Builder();

        /* JADX INFO: Access modifiers changed from: package-private */
        public int zza() {
            return this.zzb;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public zzb zzb() {
            return this.zze;
        }

        Headers zzc() {
            return this.zzf.build();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String zza(boolean z) {
            return z ? TextUtils.isEmpty(this.zzc) ? zza : this.zzc : TextUtils.isEmpty(this.zzd) ? zza : this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public zzc zzd() {
            return this.zzg;
        }

        public zza zza(zzb zzbVar) {
            this.zze = zzbVar;
            return this;
        }

        public zza zza(String str) {
            this.zzc = str;
            return this;
        }

        public zza zzb(String str) {
            this.zzd = str;
            return this;
        }

        public zza zza(int i) {
            this.zzb = i;
            return this;
        }

        public zzd zze() {
            return new zzd(this);
        }
    }
}
