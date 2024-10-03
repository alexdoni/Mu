package com.p008ld.sdk.download;

import android.content.Context;
import com.facebook.internal.security.CertificateUtil;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.internal.LDDownloadCallback;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzj;
import com.p008ld.sdk.util.zzm;
import com.xsdk.ab_firstbase.net.okhttp3.CallCode;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: LDDownloadHelper.kt */
/* loaded from: classes2.dex */
public final class zza {
    public static final zza zza = new zza();
    private static final DecimalFormat zzb = new DecimalFormat("0.00");

    private zza() {
    }

    @JvmStatic
    public static final int zza(Context context, ConfigBean config, LDDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(callback, "callback");
        String url = config.newVersionUrl;
        String str = url;
        if (str == null || str.length() == 0) {
            zzm.zza(new C3252zza(callback));
            return -1;
        }
        Intrinsics.checkNotNullExpressionValue(url, "url");
        if (StringsKt.endsWith(url, "xapk", true)) {
            zzm.zza(new zzb(callback));
            return -1;
        }
        Intrinsics.checkNotNullExpressionValue(url, "url");
        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "?", false, 2, (Object) null)) {
            Intrinsics.checkNotNullExpressionValue(url, "url");
            if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "&", false, 2, (Object) null)) {
                try {
                    String encode = URLEncoder.encode(url, "utf-8");
                    Intrinsics.checkNotNullExpressionValue(encode, "encode(url, \"utf-8\")");
                    url = new Regex("%2F").replace(new Regex("%3A").replace(new Regex("\\+").replace(encode, "%20"), CertificateUtil.DELIMITER), RemoteSettings.FORWARD_SLASH_STRING);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        zza zzaVar = zza;
        String zza2 = zza(context);
        if (!zzj.zza(new File(zza2))) {
            zzm.zza(new zzc(callback));
            return -1;
        }
        String str2 = zza2 + File.separator + zzaVar.zza(config);
        LDLog.m573e("downloadApp -> url = " + url + " , downloadPath = " + str2);
        if (zzj.zzc(str2)) {
            zzm.zza(new zzd(callback, str2));
            return -2;
        }
        BaseDownloadTask autoRetryTimes = FileDownloader.getImpl().create(url).setPath(str2).setCallbackProgressTimes(CallCode.HTTP_MULT_CHOICE).setMinIntervalUpdateSpeed(1000).setForceReDownload(true).setAutoRetryTimes(3);
        if (config.newPkgSizeLong >= Integer.MAX_VALUE) {
            autoRetryTimes.setListener(new zze(callback));
        } else {
            autoRetryTimes.setListener(new zzf(callback));
        }
        autoRetryTimes.setTag(config.gameName);
        autoRetryTimes.start();
        return autoRetryTimes.getId();
    }

    /* compiled from: LDDownloadHelper.kt */
    /* renamed from: com.ld.sdk.download.zza$zza, reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    static final class C3252zza extends Lambda implements Function0<Unit> {
        final /* synthetic */ LDDownloadCallback zza;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C3252zza(LDDownloadCallback lDDownloadCallback) {
            super(0);
            this.zza = lDDownloadCallback;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* synthetic */ Unit invoke() {
            zza();
            return Unit.INSTANCE;
        }

        public final void zza() {
            this.zza.onError(new LDException("newVersionUrl is null"));
        }
    }

    /* compiled from: LDDownloadHelper.kt */
    /* loaded from: classes2.dex */
    static final class zzb extends Lambda implements Function0<Unit> {
        final /* synthetic */ LDDownloadCallback zza;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzb(LDDownloadCallback lDDownloadCallback) {
            super(0);
            this.zza = lDDownloadCallback;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* synthetic */ Unit invoke() {
            zza();
            return Unit.INSTANCE;
        }

        public final void zza() {
            this.zza.onError(new LDException("ld does not support download app with xapk format"));
        }
    }

    /* compiled from: LDDownloadHelper.kt */
    /* loaded from: classes2.dex */
    static final class zzc extends Lambda implements Function0<Unit> {
        final /* synthetic */ LDDownloadCallback zza;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzc(LDDownloadCallback lDDownloadCallback) {
            super(0);
            this.zza = lDDownloadCallback;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* synthetic */ Unit invoke() {
            zza();
            return Unit.INSTANCE;
        }

        public final void zza() {
            this.zza.onError(new LDException("apk dir path is null"));
        }
    }

    /* compiled from: LDDownloadHelper.kt */
    /* loaded from: classes2.dex */
    static final class zzd extends Lambda implements Function0<Unit> {
        final /* synthetic */ LDDownloadCallback zza;
        final /* synthetic */ String zzb;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzd(LDDownloadCallback lDDownloadCallback, String str) {
            super(0);
            this.zza = lDDownloadCallback;
            this.zzb = str;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* synthetic */ Unit invoke() {
            zza();
            return Unit.INSTANCE;
        }

        public final void zza() {
            this.zza.onSuccess(true, this.zzb);
        }
    }

    /* compiled from: LDDownloadHelper.kt */
    /* loaded from: classes2.dex */
    public static final class zze extends com.p008ld.sdk.download.zzb {
        final /* synthetic */ LDDownloadCallback zza;

        zze(LDDownloadCallback lDDownloadCallback) {
            this.zza = lDDownloadCallback;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.p008ld.sdk.download.zzb, com.liulishuo.filedownloader.FileDownloadLargeFileListener
        public void progress(BaseDownloadTask task, long j, long j2) {
            Intrinsics.checkNotNullParameter(task, "task");
            this.zza.onProgress(j, j2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.p008ld.sdk.download.zzb, com.liulishuo.filedownloader.FileDownloadListener
        public void completed(BaseDownloadTask task) {
            Intrinsics.checkNotNullParameter(task, "task");
            LDDownloadCallback lDDownloadCallback = this.zza;
            String path = task.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "task.path");
            lDDownloadCallback.onSuccess(false, path);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.p008ld.sdk.download.zzb, com.liulishuo.filedownloader.FileDownloadListener
        public void error(BaseDownloadTask task, Throwable th) {
            Intrinsics.checkNotNullParameter(task, "task");
            LDLog.m573e("LDDownloadHelper-> download large error:" + th);
            this.zza.onError(th);
        }
    }

    /* compiled from: LDDownloadHelper.kt */
    /* loaded from: classes2.dex */
    public static final class zzf extends FileDownloadSampleListener {
        final /* synthetic */ LDDownloadCallback zza;

        zzf(LDDownloadCallback lDDownloadCallback) {
            this.zza = lDDownloadCallback;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.liulishuo.filedownloader.FileDownloadSampleListener, com.liulishuo.filedownloader.FileDownloadListener
        public void progress(BaseDownloadTask task, int i, int i2) {
            Intrinsics.checkNotNullParameter(task, "task");
            this.zza.onProgress(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.liulishuo.filedownloader.FileDownloadSampleListener, com.liulishuo.filedownloader.FileDownloadListener
        public void completed(BaseDownloadTask task) {
            Intrinsics.checkNotNullParameter(task, "task");
            LDDownloadCallback lDDownloadCallback = this.zza;
            String path = task.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "task.path");
            lDDownloadCallback.onSuccess(false, path);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.liulishuo.filedownloader.FileDownloadSampleListener, com.liulishuo.filedownloader.FileDownloadListener
        public void error(BaseDownloadTask task, Throwable th) {
            Intrinsics.checkNotNullParameter(task, "task");
            LDLog.m573e("LDDownloadHelper-> download error:" + th);
            this.zza.onError(th);
        }
    }

    @JvmStatic
    public static final String zza(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return zzj.zza(context) + File.separator + "ld_apk_file";
    }

    @JvmStatic
    public static final boolean zza(Context context, ConfigBean config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        try {
            StringBuilder sb = new StringBuilder();
            zza zzaVar = zza;
            sb.append(zza(context));
            sb.append(File.separator);
            sb.append(zzaVar.zza(config));
            File file = new File(sb.toString());
            if (zzj.zzd(file)) {
                return file.length() >= config.newPkgSizeLong;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private final String zza(ConfigBean configBean) {
        return LDUtil.md5(configBean.newVersionUrl + configBean.newVersionCode) + ".apk";
    }

    @JvmStatic
    public static final Pair<String, Integer> zza(long j, long j2) {
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = zzb;
        float f = (float) j;
        float f2 = 1024;
        sb.append(decimalFormat.format((f / f2) / f2));
        sb.append("MB/");
        float f3 = (float) j2;
        sb.append(decimalFormat.format((f3 / f2) / f2));
        sb.append("MB");
        return new Pair<>(sb.toString(), Integer.valueOf((int) ((f / f3) * 100)));
    }
}
