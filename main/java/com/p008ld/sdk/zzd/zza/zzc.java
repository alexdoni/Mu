package com.p008ld.sdk.zzd.zza;

import androidx.core.app.NotificationCompat;
import com.facebook.share.internal.ShareConstants;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.p008ld.sdk.bean.CaptchaBean;
import com.p008ld.sdk.bean.CoolingOffBean;
import com.p008ld.sdk.bean.LDResult;
import com.p008ld.sdk.internal.LDCaptchaException;
import com.p008ld.sdk.internal.LDCoolingOffException;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDFreezingException;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.zzm;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Converter;

/* compiled from: LDResponseBodyConverter.java */
/* loaded from: classes2.dex */
final class zzc<T> implements Converter<ResponseBody, T> {
    private final Gson zza;
    private final TypeAdapter<T> zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(Gson gson, TypeAdapter<T> typeAdapter) {
        this.zza = gson;
        this.zzb = typeAdapter;
    }

    @Override // retrofit2.Converter
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public T convert(ResponseBody responseBody) throws IOException {
        JSONObject jSONObject;
        try {
            String string = responseBody.string();
            try {
                jSONObject = new JSONObject(string);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = null;
            }
            if (jSONObject == null) {
                LDLog.m573e("LDResponseBodyConverter -> mJSONObject is null");
                responseBody.close();
                throw new LDException((Integer) (-1), "mJSONObject is null");
            }
            int optInt = jSONObject.optInt("code", -1);
            if (optInt == 200) {
                MediaType mediaType = responseBody.get$contentType();
                JsonReader newJsonReader = this.zza.newJsonReader(new InputStreamReader(new ByteArrayInputStream(string.getBytes()), mediaType != null ? mediaType.charset(StandardCharsets.UTF_8) : StandardCharsets.UTF_8));
                T read2 = this.zzb.read2(newJsonReader);
                if (newJsonReader.peek() == JsonToken.END_DOCUMENT) {
                    return read2;
                }
                throw new JsonIOException("JSON document was not fully consumed.");
            }
            if (jSONObject.has(ShareConstants.WEB_DIALOG_PARAM_MESSAGE)) {
                String optString = jSONObject.optString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, "");
                if (optInt == com.p008ld.sdk.zzd.zzc.ERROR_CAPTCHA.zza()) {
                    LDResult zza = zza(string, CaptchaBean.class);
                    responseBody.close();
                    throw new LDCaptchaException((CaptchaBean) zza.getData(), optString);
                }
                if (optInt == com.p008ld.sdk.zzd.zzc.ERROR_FREEZING.zza()) {
                    LDResult zza2 = zza(string, String.class);
                    responseBody.close();
                    throw new LDFreezingException((String) zza2.getData(), optString);
                }
                if (optInt == com.p008ld.sdk.zzd.zzc.ERROR_IN_COOLING_OFF_PERIOD.zza()) {
                    LDResult zza3 = zza(string, CoolingOffBean.class);
                    responseBody.close();
                    throw new LDCoolingOffException((CoolingOffBean) zza3.getData(), optString);
                }
                responseBody.close();
                throw new LDException(Integer.valueOf(optInt), optString);
            }
            String optString2 = jSONObject.optString(NotificationCompat.CATEGORY_MESSAGE, "");
            responseBody.close();
            throw new LDException(Integer.valueOf(optInt), optString2);
        } finally {
            responseBody.close();
        }
    }

    public static <T> LDResult<T> zza(String str, final Type type) {
        return (LDResult) zzm.zza().fromJson(str, new ParameterizedType() { // from class: com.ld.sdk.zzd.zza.zzc.1
            @Override // java.lang.reflect.ParameterizedType
            public Type getOwnerType() {
                return null;
            }

            @Override // java.lang.reflect.ParameterizedType
            public Type[] getActualTypeArguments() {
                return new Type[]{type};
            }

            @Override // java.lang.reflect.ParameterizedType
            public Type getRawType() {
                return LDResult.class;
            }
        });
    }
}
