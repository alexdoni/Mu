package com.p008ld.sdk.zzd;

import android.net.ParseException;
import com.google.gson.JsonParseException;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.bean.LDResult;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.zzd.zzb.zze;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.HttpException;

/* compiled from: LDNetErrorHandler.kt */
/* loaded from: classes2.dex */
public final class zzg {
    public static final zzg zza = new zzg();

    private zzg() {
    }

    public final <T> LDException zza(Call<LDResult<T>> call, Throwable e) {
        LDException lDException;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        if (e instanceof LDException) {
            lDException = (LDException) e;
        } else if (e instanceof HttpException) {
            lDException = new LDException(Integer.valueOf(((HttpException) e).code()), zzt.zza(LDSdk.getApp(), "ld_request_exception"));
        } else if (e instanceof SocketTimeoutException) {
            lDException = new LDException((Integer) (-1), zzt.zza(LDSdk.getApp(), "ld_request_timeout"));
        } else {
            if (e instanceof UnknownHostException ? true : e instanceof CancellationException ? true : e instanceof ConnectException) {
                lDException = new LDException((Integer) (-1), zzt.zza(LDSdk.getApp(), "ld_net_network_error"));
            } else {
                if (e instanceof JsonParseException ? true : e instanceof ParseException ? true : e instanceof JSONException) {
                    lDException = new LDException((Integer) (-1), zzt.zza(LDSdk.getApp(), "ld_net_server_data_error"));
                } else {
                    lDException = new LDException((Integer) (-1), zzt.zza(LDSdk.getApp(), "ld_net_server_error"));
                }
            }
        }
        LDSdk.debug(e);
        zzb(call, e);
        return lDException;
    }

    public final <T> void zzb(Call<LDResult<T>> call, Throwable ex) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(ex, "ex");
        try {
            String decode = URLDecoder.decode(call.request().url().getUrl(), "UTF-8");
            String headers = call.request().headers().toString();
            String zzb = zze.zza(headers) ? "" : zze.zzb(headers);
            LDLog.log2Local("LDNetErrorHandler -> url = " + decode + ",\n" + zzb + ",requestBody = " + zze.zza(call.request()) + ",\nresponse:" + ex);
        } catch (Exception e) {
            e.printStackTrace();
            LDLog.logThrowable2Local(e);
        }
    }
}
