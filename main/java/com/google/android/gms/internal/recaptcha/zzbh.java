package com.google.android.gms.internal.recaptcha;

import android.os.LocaleList;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.recaptcha.zzrg;
import com.google.android.gms.recaptcha.HttpStatusException;
import com.google.android.gms.recaptcha.RecaptchaNetworkException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.HttpsURLConnection;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzbh<ResponseT extends zzrg<ResponseT, ?>> {
    private final String zza;
    private final ExecutorService zzb;
    private final ResponseT zzc;

    public zzbh(String str, ExecutorService executorService, ResponseT responset) {
        this.zza = str;
        this.zzb = executorService;
        this.zzc = responset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ HttpURLConnection zzd(zzbh zzbhVar) throws IOException {
        if (URLUtil.isHttpsUrl(zzbhVar.zza) || URLUtil.isHttpUrl(zzbhVar.zza)) {
            if (URLUtil.isHttpUrl(zzbhVar.zza)) {
                return (HttpURLConnection) new URL(zzbhVar.zza).openConnection();
            }
            return (HttpsURLConnection) new URL(zzbhVar.zza).openConnection();
        }
        throw new MalformedURLException("Recaptcha server url only allows using Http or Https.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public final <RequestT extends zzrg> ResponseT zzc(RequestT requestt) throws RecaptchaNetworkException, HttpStatusException {
        String sb;
        try {
            int zzt = requestt.zzt();
            byte[] bArr = new byte[zzt];
            zzqj zzM = zzqj.zzM(bArr);
            requestt.zzM(zzM);
            zzM.zzO();
            try {
                zzm zza = zzf.zza();
                try {
                    HttpURLConnection zzb = zza.zzb(new zzbg(this), 21504, -1);
                    zzb.setConnectTimeout(60000);
                    zzb.setReadTimeout(60000);
                    zzb.setRequestProperty("Content-type", "application/x-protobuffer");
                    zzb.setRequestProperty("Content-Length", Integer.toString(zzt));
                    if (PlatformVersion.isAtLeastN()) {
                        sb = LocaleList.getDefault().toLanguageTags();
                    } else {
                        Locale locale = Locale.getDefault();
                        if (PlatformVersion.isAtLeastLollipop()) {
                            sb = locale.toLanguageTag();
                        } else {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(locale.getLanguage());
                            if (TextUtils.isEmpty(sb2.toString())) {
                                sb = "und";
                            } else {
                                String country = locale.getCountry();
                                if (!TextUtils.isEmpty(country)) {
                                    sb2.append("-");
                                    sb2.append(country);
                                }
                                String variant = locale.getVariant();
                                if (!TextUtils.isEmpty(variant)) {
                                    sb2.append("-");
                                    sb2.append(variant);
                                }
                                sb = sb2.toString();
                            }
                        }
                    }
                    zzb.setRequestProperty(HttpHeaders.ACCEPT_LANGUAGE, sb);
                    zzb.setRequestMethod("POST");
                    zzb.setDoOutput(true);
                    zzb.connect();
                    OutputStream outputStream = zzb.getOutputStream();
                    outputStream.write(bArr);
                    outputStream.close();
                    int responseCode = zzb.getResponseCode();
                    if (responseCode != 200) {
                        throw new HttpStatusException("Failed to fetch response", responseCode);
                    }
                    Object zzb2 = this.zzc.zzA().zzb(zzb.getInputStream());
                    zza.close();
                    return (ResponseT) zzb2;
                } finally {
                }
            } catch (IOException e) {
                zzak.zza("RecaptchaNetworkMgr", e);
                if (e instanceof MalformedURLException) {
                    throw new RecaptchaNetworkException(String.valueOf(e.getMessage()), e);
                }
                throw new RecaptchaNetworkException("Failed to connect to server", e);
            }
        } catch (IOException e2) {
            String name = requestt.getClass().getName();
            StringBuilder sb3 = new StringBuilder(String.valueOf(name).length() + 72);
            sb3.append("Serializing ");
            sb3.append(name);
            sb3.append(" to a byte array threw an IOException (should never happen).");
            throw new RuntimeException(sb3.toString(), e2);
        }
    }

    public final <RequestT extends zzrg> Task<ResponseT> zza(final RequestT requestt) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzb.execute(new Runnable() { // from class: com.google.android.gms.internal.recaptcha.zzbe
            @Override // java.lang.Runnable
            public final void run() {
                zzbh.this.zze(taskCompletionSource, requestt);
            }
        });
        return taskCompletionSource.getTask();
    }

    public final <RequestT extends zzrg> zzop<ResponseT> zzb(final RequestT requestt) {
        return zzow.zza(this.zzb).zza(new Callable() { // from class: com.google.android.gms.internal.recaptcha.zzbf
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzbh.this.zzc(requestt);
            }
        });
    }

    public final /* synthetic */ void zze(TaskCompletionSource taskCompletionSource, zzrg zzrgVar) {
        try {
            taskCompletionSource.setResult(zzc(zzrgVar));
        } catch (HttpStatusException | RecaptchaNetworkException e) {
            taskCompletionSource.setException(e);
        }
    }
}
