package com.p008ld.sdk.model;

import com.p008ld.sdk.bean.CaptchaBean;
import com.p008ld.sdk.bean.InitBean;
import com.p008ld.sdk.bean.LDResult;
import com.p008ld.sdk.internal.LDApiCallback;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDIdentifiers;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zze;
import com.p008ld.sdk.util.zzm;
import com.p008ld.sdk.zzd.zzd;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: LDBaseModel.kt */
/* loaded from: classes2.dex */
public final class zzb extends zzd<LDBaseService> {
    @Override // com.p008ld.sdk.zzd.zzd
    protected Class<LDBaseService> zza() {
        return LDBaseService.class;
    }

    public final String zzb() {
        String str = "";
        Pair[] pairArr = new Pair[10];
        pairArr[0] = TuplesKt.m1402to("android_id", LDIdentifiers.Companion.getAdvertId());
        pairArr[1] = TuplesKt.m1402to("system_version_name", zze.zzf());
        pairArr[2] = TuplesKt.m1402to("system_version_code", Integer.valueOf(zze.zzg()));
        StringBuilder sb = new StringBuilder();
        sb.append(zze.zzh());
        sb.append('x');
        sb.append(zze.zzi());
        pairArr[3] = TuplesKt.m1402to("screen_size", sb.toString());
        pairArr[4] = TuplesKt.m1402to("brand", zze.zzb());
        pairArr[5] = TuplesKt.m1402to("model", zze.zzc());
        pairArr[6] = TuplesKt.m1402to("system", zze.zzl() ? "Harmony" : "Android");
        pairArr[7] = TuplesKt.m1402to("timeZoneId", zze.zzk());
        pairArr[8] = TuplesKt.m1402to("simCountryIso", zze.zzd());
        pairArr[9] = TuplesKt.m1402to("simOperator", zze.zze());
        try {
            LDResult<InitBean> body = zzc().initSdk(zzm.zza((Map<?, ?>) MapsKt.mapOf(TuplesKt.m1402to("deviceInfo", LDUtil.toJson(MapsKt.mapOf(pairArr)))))).execute().body();
            if (body != null) {
                if (body.isSuccess() && body.getData() != null) {
                    InitBean data = body.getData();
                    Intrinsics.checkNotNull(data);
                    str = data.getSid();
                } else {
                    LDLog.m573e("initSdk -> fail: " + body);
                }
            } else {
                LDLog.m573e("initSdk -> fail: response is null...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LDLog.m573e("initSdk -> error: " + e);
        }
        return str;
    }

    public final void zza(String captchaId, LDQueryCallback<CaptchaBean> callback) {
        Intrinsics.checkNotNullParameter(captchaId, "captchaId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        zzc().refreshCaptcha(zzm.zza((Map<?, ?>) MapsKt.mapOf(TuplesKt.m1402to("captchaId", captchaId)))).enqueue(new zza(callback));
    }

    /* compiled from: LDBaseModel.kt */
    /* loaded from: classes2.dex */
    public static final class zza extends LDApiCallback<CaptchaBean> {
        final /* synthetic */ LDQueryCallback<CaptchaBean> zza;

        zza(LDQueryCallback<CaptchaBean> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDBaseModel.kt */
        /* renamed from: com.ld.sdk.model.zzb$zza$zza, reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C3254zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<CaptchaBean> zza;
            final /* synthetic */ CaptchaBean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C3254zza(LDQueryCallback<CaptchaBean> lDQueryCallback, CaptchaBean captchaBean, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = captchaBean;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<CaptchaBean>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(CaptchaBean captchaBean, LDException lDException) {
            zzm.zza(new C3254zza(this.zza, captchaBean, lDException));
        }
    }
}
