package com.p008ld.sdk.model;

import com.p008ld.sdk.bean.CaptchaBean;
import com.p008ld.sdk.bean.InitBean;
import com.p008ld.sdk.bean.LDResult;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/* compiled from: LDBaseService.kt */
/* loaded from: classes2.dex */
public interface LDBaseService {
    @Headers({"SKIP_SID:0"})
    @POST("sdk/initSdk")
    Call<LDResult<InitBean>> initSdk(@Body RequestBody requestBody);

    @POST("sdk/refreshCaptcha")
    Call<LDResult<CaptchaBean>> refreshCaptcha(@Body RequestBody requestBody);
}
