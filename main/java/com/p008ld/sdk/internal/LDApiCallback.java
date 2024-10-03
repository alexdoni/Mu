package com.p008ld.sdk.internal;

import android.app.Activity;
import com.p008ld.sdk.bean.CaptchaBean;
import com.p008ld.sdk.bean.CoolingOffBean;
import com.p008ld.sdk.bean.LDResult;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.zza.zza;
import com.p008ld.sdk.zza.zzb;
import com.p008ld.sdk.zzd.zzb;
import com.p008ld.sdk.zzd.zzc;
import com.p008ld.sdk.zzd.zzd;
import com.p008ld.sdk.zzd.zze;
import com.p008ld.sdk.zzd.zzg;
import java.net.URLDecoder;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/* compiled from: LDApiCallback.kt */
/* loaded from: classes2.dex */
public abstract class LDApiCallback<T> implements Callback<LDResult<T>> {
    public abstract void done(T t, LDException lDException);

    /* JADX INFO: Access modifiers changed from: private */
    public final void wrapDone(T t, LDException lDException) {
        try {
            done(t, lDException);
        } catch (Exception e) {
            e.printStackTrace();
            LDLog.logThrowable2Local(e);
        }
    }

    private final void wrapDone(Call<LDResult<T>> call, LDException lDException) {
        try {
            zzg.zza.zzb(call, lDException);
            done(null, lDException);
        } catch (Exception e) {
            e.printStackTrace();
            LDLog.logThrowable2Local(e);
        }
    }

    @Override // retrofit2.Callback
    public void onResponse(Call<LDResult<T>> call, Response<LDResult<T>> response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.isSuccessful()) {
            LDResult<T> body = response.body();
            if (body != null) {
                if (body.isSuccess()) {
                    T data = body.getData();
                    if (data == null) {
                        wrapDone((Call) call, new LDException("data is null"));
                        return;
                    } else {
                        wrapDone((LDApiCallback<T>) data, (LDException) null);
                        return;
                    }
                }
                Integer code = body.getCode();
                int intValue = code != null ? code.intValue() : -1;
                String message = body.getMessage();
                if (message == null && (message = body.getMsg()) == null) {
                    message = "";
                }
                wrapDone((Call) call, new LDException(Integer.valueOf(intValue), message));
                return;
            }
            wrapDone((Call) call, new LDException("body is null"));
            return;
        }
        wrapDone((Call) call, new LDException(new HttpException(response)));
    }

    @Override // retrofit2.Callback
    public void onFailure(Call<LDResult<T>> call, Throwable t) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(t, "t");
        LDException zza = zzg.zza.zza(call, t);
        if (t instanceof HttpException) {
            wrapDone((LDApiCallback<T>) null, zza);
            return;
        }
        if (t instanceof LDException) {
            LDException lDException = (LDException) t;
            int errorCode = lDException.getErrorCode();
            String oldHttpUrl = URLDecoder.decode(call.request().url().getUrl(), "UTF-8");
            Intrinsics.checkNotNullExpressionValue(oldHttpUrl, "oldHttpUrl");
            String str = oldHttpUrl;
            if ((StringsKt.contains$default((CharSequence) str, (CharSequence) zzd.zza.zza(), false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) zzd.zza.zzb(), false, 2, (Object) null)) && zzc.zza.zza(errorCode)) {
                try {
                    handleSdkException(this, call, (LDException) t);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    LDLog.logThrowable2Local(e);
                    return;
                }
            }
            wrapDone((LDApiCallback<T>) null, lDException);
            return;
        }
        wrapDone((LDApiCallback<T>) null, zza);
    }

    private final <T> void handleSdkException(final Callback<LDResult<T>> callback, final Call<LDResult<T>> call, LDException lDException) {
        Function1<CoolingOffBean, Unit> zzc;
        Function0<Unit> zzb;
        Function1<String, Unit> zza;
        final int errorCode = lDException.getErrorCode();
        String message = lDException.getMessage();
        if (message == null) {
            message = "";
        }
        final String str = message;
        if (errorCode == zzc.ERROR_CAPTCHA.zza()) {
            LDUtil.toast(str);
            Intrinsics.checkNotNull(lDException, "null cannot be cast to non-null type com.ld.sdk.internal.LDCaptchaException");
            final CaptchaBean captchaBean = ((LDCaptchaException) lDException).getCaptchaBean();
            Activity topActivity = LDUtil.getTopActivity();
            if (topActivity != null) {
                if (captchaBean.getCaptchaType() == 1) {
                    new zzb(topActivity, captchaBean, new LDCallback1<String>() { // from class: com.ld.sdk.internal.LDApiCallback$handleSdkException$1$1
                        @Override // com.p008ld.sdk.internal.LDCallback1
                        public void done(String answer) {
                            Intrinsics.checkNotNullParameter(answer, "answer");
                            if (answer.length() == 0) {
                                this.wrapDone((LDApiCallback<T>) ((LDApiCallback) null), new LDException(Integer.valueOf(errorCode), str));
                                return;
                            }
                            zzb.zza zzaVar = com.p008ld.sdk.zzd.zzb.zza;
                            CaptchaBean captchaBean2 = CaptchaBean.this;
                            captchaBean2.setCaptchaData(answer);
                            zzaVar.zzb(captchaBean2);
                            call.clone().enqueue(callback);
                        }
                    }).show();
                    return;
                } else {
                    new zza(topActivity, captchaBean, new LDCallback1<String>() { // from class: com.ld.sdk.internal.LDApiCallback$handleSdkException$1$2
                        @Override // com.p008ld.sdk.internal.LDCallback1
                        public void done(String answer) {
                            Intrinsics.checkNotNullParameter(answer, "answer");
                            if (answer.length() == 0) {
                                this.wrapDone((LDApiCallback<T>) ((LDApiCallback) null), new LDException(Integer.valueOf(errorCode), str));
                                return;
                            }
                            zzb.zza zzaVar = com.p008ld.sdk.zzd.zzb.zza;
                            CaptchaBean captchaBean2 = CaptchaBean.this;
                            captchaBean2.setCaptchaData(answer);
                            zzaVar.zzb(captchaBean2);
                            call.clone().enqueue(callback);
                        }
                    }).show();
                    return;
                }
            }
            return;
        }
        if (errorCode == zzc.ERROR_FREEZING.zza()) {
            Intrinsics.checkNotNull(lDException, "null cannot be cast to non-null type com.ld.sdk.internal.LDFreezingException");
            String url = ((LDFreezingException) lDException).getUrl();
            zze zzeVar = com.p008ld.sdk.zzd.zza.zzb;
            if (zzeVar != null && (zza = zzeVar.zza()) != null) {
                zza.invoke(url);
            }
            wrapDone((LDApiCallback<T>) null, new LDException(Integer.valueOf(errorCode), str));
            return;
        }
        if (errorCode == zzc.ERROR_TOKEN.zza()) {
            zze zzeVar2 = com.p008ld.sdk.zzd.zza.zzb;
            if (zzeVar2 != null && (zzb = zzeVar2.zzb()) != null) {
                zzb.invoke();
            }
            wrapDone((LDApiCallback<T>) null, new LDException(Integer.valueOf(errorCode), str));
            return;
        }
        if (errorCode == zzc.ERROR_IN_COOLING_OFF_PERIOD.zza()) {
            Intrinsics.checkNotNull(lDException, "null cannot be cast to non-null type com.ld.sdk.internal.LDCoolingOffException");
            CoolingOffBean coolingBean = ((LDCoolingOffException) lDException).getCoolingBean();
            zze zzeVar3 = com.p008ld.sdk.zzd.zza.zzb;
            if (zzeVar3 != null && (zzc = zzeVar3.zzc()) != null) {
                zzc.invoke(coolingBean);
            }
            wrapDone((LDApiCallback<T>) null, new LDException(Integer.valueOf(errorCode), str));
            return;
        }
        LDUtil.toast(lDException.toString());
    }
}
