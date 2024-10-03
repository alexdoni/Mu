package com.p008ld.sdk.zzd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.p008ld.sdk.bean.CaptchaBean;
import com.p008ld.sdk.internal.LDValidate;
import com.p008ld.sdk.zzd.zzb.zzd;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* compiled from: LDApiClient.kt */
/* loaded from: classes2.dex */
public final class zzb {
    public static final zza zza = new zza(null);
    private static volatile zzb zze;
    private static CaptchaBean zzf;
    private Retrofit zzb;
    private Retrofit.Builder zzc;
    private final Lazy zzd;

    public /* synthetic */ zzb(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private zzb() {
        this.zzd = LazyKt.lazy(new C3264zzb());
        Retrofit.Builder addConverterFactory = new Retrofit.Builder().client(zzd()).addConverterFactory(com.p008ld.sdk.zzd.zza.zza.zza());
        Intrinsics.checkNotNullExpressionValue(addConverterFactory, "Builder()\n            .c…onverterFactory.create())");
        this.zzc = addConverterFactory;
    }

    /* compiled from: LDApiClient.kt */
    /* renamed from: com.ld.sdk.zzd.zzb$zzb, reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    static final class C3264zzb extends Lambda implements Function0<OkHttpClient> {
        C3264zzb() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public final OkHttpClient invoke() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            zzb zzbVar = zzb.this;
            builder.addInterceptor(new zzf());
            builder.addInterceptor(zzbVar.zze());
            builder.retryOnConnectionFailure(true);
            builder.connectTimeout(com.p008ld.sdk.zza.zza.zzb(), TimeUnit.SECONDS);
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.writeTimeout(30L, TimeUnit.SECONDS);
            return builder.build();
        }
    }

    private final OkHttpClient zzd() {
        return (OkHttpClient) this.zzd.getValue();
    }

    /* compiled from: LDApiClient.kt */
    /* loaded from: classes2.dex */
    public static final class zza {
        public /* synthetic */ zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private zza() {
        }

        @JvmStatic
        public final synchronized zzb zza() {
            zzb zzbVar;
            zzbVar = null;
            byte b = 0;
            if (zzb.zze == null) {
                zzb.zze = new zzb(b == true ? 1 : 0);
            }
            zzb zzbVar2 = zzb.zze;
            if (zzbVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("INSTANCE");
            } else {
                zzbVar = zzbVar2;
            }
            return zzbVar;
        }

        public final void zza(CaptchaBean captchaBean) {
            zzb.zzf = captchaBean;
        }

        public final CaptchaBean zzb() {
            return zzb.zzf;
        }

        @JvmStatic
        public final void zzb(CaptchaBean captchaBean) {
            zza(captchaBean);
        }
    }

    public final <T> T zza(String url, Class<T> serviceClass) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(serviceClass, "serviceClass");
        LDValidate.notEmpty(url, "url");
        Retrofit build = this.zzc.baseUrl(url).build();
        Intrinsics.checkNotNullExpressionValue(build, "mDefaultBuilder.baseUrl(url).build()");
        this.zzb = build;
        if (build == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRetrofit");
            build = null;
        }
        return (T) build.create(serviceClass);
    }

    public final Gson zza() {
        Gson create = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
        Intrinsics.checkNotNullExpressionValue(create, "GsonBuilder()\n          …g()\n            .create()");
        return create;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final zzd zze() {
        zzd zze2 = new zzd.zza().zza(com.p008ld.sdk.zzd.zzb.zzb.BASIC).zza(4).zza("request").zzb("response").zze();
        Intrinsics.checkNotNullExpressionValue(zze2, "Builder()\n            .s…se\")\n            .build()");
        return zze2;
    }
}
