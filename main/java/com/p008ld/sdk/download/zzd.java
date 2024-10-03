package com.p008ld.sdk.download;

import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: OkHttp3Connection.java */
/* loaded from: classes2.dex */
public class zzd implements FileDownloadConnection {
    private final OkHttpClient zza;
    private final Request.Builder zzb;
    private Request zzc;
    private Response zzd;

    @Override // com.liulishuo.filedownloader.connection.FileDownloadConnection
    public boolean dispatchAddResumeOffset(String str, long j) {
        return false;
    }

    zzd(Request.Builder builder, OkHttpClient okHttpClient) {
        this.zzb = builder;
        this.zza = okHttpClient;
    }

    public zzd(String str, OkHttpClient okHttpClient) {
        this(new Request.Builder().url(str), okHttpClient);
    }

    @Override // com.liulishuo.filedownloader.connection.FileDownloadConnection
    public void addHeader(String str, String str2) {
        this.zzb.addHeader(str, str2);
    }

    @Override // com.liulishuo.filedownloader.connection.FileDownloadConnection
    public InputStream getInputStream() throws IOException {
        Response response = this.zzd;
        if (response == null) {
            throw new IOException("Please invoke #execute first!");
        }
        ResponseBody body = response.body();
        if (body == null) {
            throw new IOException("No body found on response!");
        }
        return body.byteStream();
    }

    @Override // com.liulishuo.filedownloader.connection.FileDownloadConnection
    public Map<String, List<String>> getRequestHeaderFields() {
        if (this.zzc == null) {
            this.zzc = this.zzb.build();
        }
        return this.zzc.headers().toMultimap();
    }

    @Override // com.liulishuo.filedownloader.connection.FileDownloadConnection
    public Map<String, List<String>> getResponseHeaderFields() {
        Response response = this.zzd;
        if (response == null) {
            return null;
        }
        return response.headers().toMultimap();
    }

    @Override // com.liulishuo.filedownloader.connection.FileDownloadConnection
    public String getResponseHeaderField(String str) {
        Response response = this.zzd;
        if (response == null) {
            return null;
        }
        return response.header(str);
    }

    @Override // com.liulishuo.filedownloader.connection.FileDownloadConnection
    public boolean setRequestMethod(String str) throws ProtocolException {
        this.zzb.method(str, null);
        return true;
    }

    @Override // com.liulishuo.filedownloader.connection.FileDownloadConnection
    public void execute() throws IOException {
        if (this.zzc == null) {
            this.zzc = this.zzb.build();
        }
        this.zzd = this.zza.newCall(this.zzc).execute();
    }

    @Override // com.liulishuo.filedownloader.connection.FileDownloadConnection
    public int getResponseCode() throws IOException {
        Response response = this.zzd;
        if (response == null) {
            throw new IllegalStateException("Please invoke #execute first!");
        }
        return response.code();
    }

    @Override // com.liulishuo.filedownloader.connection.FileDownloadConnection
    public void ending() {
        this.zzc = null;
        this.zzd = null;
    }

    /* compiled from: OkHttp3Connection.java */
    /* loaded from: classes2.dex */
    public static class zza implements FileDownloadHelper.ConnectionCreator {
        private OkHttpClient zza;
        private OkHttpClient.Builder zzb;

        public zza() {
        }

        public zza(OkHttpClient.Builder builder) {
            this.zzb = builder;
        }

        @Override // com.liulishuo.filedownloader.util.FileDownloadHelper.ConnectionCreator
        public FileDownloadConnection create(String str) throws IOException {
            if (this.zza == null) {
                synchronized (zza.class) {
                    if (this.zza == null) {
                        OkHttpClient.Builder builder = this.zzb;
                        this.zza = builder != null ? builder.build() : new OkHttpClient();
                        this.zzb = null;
                    }
                }
            }
            return new zzd(str, this.zza);
        }
    }
}
