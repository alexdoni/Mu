package com.p008ld.sdk.zzd;

import com.google.common.net.HttpHeaders;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.bean.CaptchaBean;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDNative;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zze;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzm;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/* compiled from: LDInterceptor.kt */
/* loaded from: classes2.dex */
public final class zzf implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        String nowTime;
        Map<String, String> zza;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        String oldHttpUrl = URLDecoder.decode(request.url().getUrl(), "UTF-8");
        String lan = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(oldHttpUrl, "oldHttpUrl");
        String str = oldHttpUrl;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) zzd.zza.zzc(), false, 2, (Object) null)) {
            Request.Builder newBuilder = chain.request().newBuilder();
            newBuilder.addHeader(HttpHeaders.COOKIE, "space_token=" + zza.zza.zza().zzb());
            newBuilder.addHeader(HttpHeaders.CONNECTION, "close");
            newBuilder.addHeader("Cache-Control", "no-cache");
            newBuilder.addHeader("sdkVersion", LDSdk.getSdkVersionCode());
            newBuilder.addHeader("clientVersion", zze.zzo());
            String zzb = zzi.zzb();
            Intrinsics.checkNotNullExpressionValue(zzb, "getMnqVersion()");
            newBuilder.addHeader("mnqVersion", zzb);
            String zzc = zzi.zzc();
            Intrinsics.checkNotNullExpressionValue(zzc, "getDeviceId()");
            newBuilder.addHeader("deviceId", zzc);
            Intrinsics.checkNotNullExpressionValue(lan, "lan");
            newBuilder.addHeader("language", lan);
            newBuilder.addHeader("appId", LDSdk.getAppId());
            return chain.proceed(newBuilder.build());
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) zzd.zza.zzd(), false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) zzd.zza.zze(), false, 2, (Object) null)) {
            return chain.proceed(chain.request());
        }
        Request.Builder newBuilder2 = request.newBuilder();
        try {
            nowTime = URLEncoder.encode(LDUtil.getNowTime(), "UTF-8");
        } catch (Exception unused) {
            LDLog.m573e("intercept -> nowTime is null");
            nowTime = "";
        }
        boolean areEqual = Intrinsics.areEqual(request.headers().get("SKIP_SID"), "0");
        String sid = LDSdk.getSid();
        if (!areEqual) {
            String str2 = sid;
            if (str2 == null || str2.length() == 0) {
                sid = com.p008ld.sdk.model.zza.zza.zza().zzb();
            }
        }
        String zza2 = zze.zza.zza(areEqual, sid);
        String genUUID = LDUtil.genUUID();
        Request.Builder addHeader = newBuilder2.addHeader("protocol_version", "1").addHeader("sdk_platform", "Android").addHeader(ComConstants.sdk_version_code, "2.2.0").addHeader("app_id", "6666").addHeader("ext_app_id", LDSdk.getAppId()).addHeader("channel_id", LDSdk.getChannelId()).addHeader("sub_channel_id", LDSdk.getSunChannelId()).addHeader("device_id", zza2).addHeader("request_id", genUUID);
        Intrinsics.checkNotNullExpressionValue(nowTime, "nowTime");
        addHeader.addHeader("timestamp", nowTime).addHeader("language_code", zze.zzj());
        CaptchaBean zzb2 = zzb.zza.zzb();
        if (zzb2 != null) {
            newBuilder2.addHeader("captcha_id", zzb2.getCaptchaId());
            newBuilder2.addHeader("captcha_data", zzb2.getCaptchaData());
        }
        if (Intrinsics.areEqual(request.method(), "POST") || Intrinsics.areEqual(request.method(), "PUT")) {
            RequestBody body = request.body();
            Intrinsics.checkNotNull(body);
            zza = zza(body);
        } else {
            zza = zza(request.url());
        }
        String zza3 = zza(zza, nowTime, genUUID, zza2);
        if (zza3 != null) {
            newBuilder2.addHeader("sign", zza3);
        }
        newBuilder2.removeHeader("SKIP_SID");
        Response proceed = chain.proceed(newBuilder2.build());
        if (zzb.zza.zzb() != null) {
            zzb.zza.zzb(null);
        }
        return proceed;
    }

    private final Map<String, String> zza(RequestBody requestBody) {
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        return zzm.zza(buffer.readUtf8());
    }

    private final Map<String, String> zza(HttpUrl httpUrl) {
        Set<String> queryParameterNames = httpUrl.queryParameterNames();
        HashMap hashMap = new HashMap();
        if (!queryParameterNames.isEmpty()) {
            for (String str : queryParameterNames) {
                HashMap hashMap2 = hashMap;
                String queryParameter = httpUrl.queryParameter(str);
                if (queryParameter == null) {
                    queryParameter = "";
                }
                hashMap2.put(str, queryParameter);
            }
        }
        return hashMap;
    }

    private final String zza(Map<String, ? extends Object> map, String str, String str2, String str3) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        if ("cbe324bcbd1663f709079baf25ed640d".length() == 0) {
            LDLog.m573e("assembleSign -> key is null");
            return null;
        }
        return LDNative.INSTANCE.encrypt5(LDUtil.toJson(map), str, str2, str3, "cbe324bcbd1663f709079baf25ed640d");
    }
}
