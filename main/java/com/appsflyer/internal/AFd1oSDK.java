package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(m1394d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\n\u001a\u00020\f\u0012\u0014\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0015¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0006\u001a\u00020\u0005*\u0004\u0018\u00010\u0005H'¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u0003\u001a\u00020\u0002*\u00020\b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0003\u0010\u000bR\u0012\u0010\u0006\u001a\u00020\fX\u0087\u0002¢\u0006\u0006\n\u0004\b\u0006\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000e8'X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u000fR \u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011X\u0087\u0002¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u001a\u0010\u0003\u001a\u00020\u00028\u0017X\u0097D¢\u0006\f\n\u0004\b\u0010\u0010\u0014\u001a\u0004\b\u0010\u0010\u0004R\u0011\u0010\u0016\u001a\u00020\u0015X\u0007¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00058'X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0018"}, m1395d2 = {"Lcom/appsflyer/internal/AFd1oSDK;", "", "", "values", "()Z", "", "AFInAppEventParameterName", "(Ljava/lang/String;)Ljava/lang/String;", "Ljava/net/HttpURLConnection;", "", "p0", "(Ljava/net/HttpURLConnection;J)Z", "", "[B", "Lcom/appsflyer/internal/AFe1rSDK;", "()Lcom/appsflyer/internal/AFe1rSDK;", "valueOf", "", "Ljava/util/Map;", "AFKeystoreWrapper", "Z", "", "AFInAppEventType", "I", "()Ljava/lang/String;", "unregisterClient", "p1", "p2", "<init>", "([BLjava/util/Map;I)V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public abstract class AFd1oSDK {
    public byte[] AFInAppEventParameterName;
    public int AFInAppEventType;

    /* renamed from: valueOf, reason: from kotlin metadata */
    private final boolean values;

    /* renamed from: values, reason: from kotlin metadata */
    public Map<String, String> AFKeystoreWrapper;

    public abstract AFe1rSDK AFInAppEventParameterName();

    public abstract String AFInAppEventParameterName(String str);

    public abstract String AFKeystoreWrapper();

    public AFd1oSDK(byte[] bArr, Map<String, String> map, int i) {
        Intrinsics.checkNotNullParameter(bArr, "");
        this.AFInAppEventParameterName = bArr;
        this.AFKeystoreWrapper = map;
        this.AFInAppEventType = i;
        this.values = true;
    }

    /* renamed from: valueOf, reason: from getter */
    public boolean getValues() {
        return this.values;
    }

    public final boolean values() {
        HttpURLConnection httpURLConnection;
        Throwable th;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            String AFKeystoreWrapper = AFKeystoreWrapper();
            Intrinsics.checkNotNullParameter(AFKeystoreWrapper, "");
            URLConnection openConnection = new URL(AFKeystoreWrapper).openConnection();
            if (openConnection != null) {
                httpURLConnection = (HttpURLConnection) openConnection;
                try {
                    boolean values = values(httpURLConnection, currentTimeMillis);
                    if (httpURLConnection == null) {
                        return values;
                    }
                    httpURLConnection.disconnect();
                    return values;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                        StringBuilder sb = new StringBuilder("error: ");
                        sb.append(th);
                        sb.append("\n\ttook ");
                        sb.append(currentTimeMillis2);
                        sb.append("ms\n\t");
                        sb.append(th.getMessage());
                        String obj = sb.toString();
                        StringBuilder sb2 = new StringBuilder("HTTP: [");
                        sb2.append(httpURLConnection != null ? httpURLConnection.hashCode() : 0);
                        sb2.append("] ");
                        sb2.append(obj);
                        String AFInAppEventParameterName = AFInAppEventParameterName(sb2.toString());
                        if (getValues()) {
                            AFLogger.afRDLog(AFInAppEventParameterName);
                        } else {
                            AFLogger.afVerboseLog(AFInAppEventParameterName);
                        }
                        return false;
                    } finally {
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    }
                }
            }
            throw new NullPointerException("null cannot be cast to non-null type java.net.HttpURLConnection");
        } catch (Throwable th3) {
            httpURLConnection = null;
            th = th3;
        }
    }

    private final boolean values(HttpURLConnection httpURLConnection, long j) {
        InputStream errorStream;
        String str = "";
        httpURLConnection.setRequestMethod("POST");
        StringBuilder sb = new StringBuilder();
        sb.append(httpURLConnection.getRequestMethod());
        sb.append(':');
        sb.append(httpURLConnection.getURL());
        StringBuilder sb2 = new StringBuilder(sb.toString());
        sb2.append("\n length: ");
        sb2.append(new String(this.AFInAppEventParameterName, Charsets.UTF_8).length());
        Map<String, String> map = this.AFKeystoreWrapper;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb2.append("\n ");
                sb2.append(entry.getKey());
                sb2.append(": ");
                sb2.append(entry.getValue());
            }
        }
        StringBuilder sb3 = new StringBuilder("HTTP: [");
        sb3.append(httpURLConnection.hashCode());
        sb3.append("] ");
        sb3.append((Object) sb2);
        String AFInAppEventParameterName = AFInAppEventParameterName(sb3.toString());
        if (getValues()) {
            AFLogger.afRDLog(AFInAppEventParameterName);
        } else {
            AFLogger.afVerboseLog(AFInAppEventParameterName);
        }
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setReadTimeout(this.AFInAppEventType);
        httpURLConnection.setConnectTimeout(this.AFInAppEventType);
        httpURLConnection.addRequestProperty("Content-Type", AFInAppEventParameterName().AFKeystoreWrapper);
        Map<String, String> map2 = this.AFKeystoreWrapper;
        if (map2 != null) {
            for (Map.Entry<String, String> entry2 : map2.entrySet()) {
                httpURLConnection.addRequestProperty(entry2.getKey(), entry2.getValue());
            }
        }
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(this.AFInAppEventParameterName.length));
        OutputStream outputStream = httpURLConnection.getOutputStream();
        Intrinsics.checkNotNullExpressionValue(outputStream, "");
        BufferedOutputStream bufferedOutputStream = outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, 8192);
        bufferedOutputStream.write(this.AFInAppEventParameterName);
        bufferedOutputStream.close();
        if (AFd1eSDK.AFKeystoreWrapper(httpURLConnection)) {
            errorStream = httpURLConnection.getInputStream();
        } else {
            errorStream = httpURLConnection.getErrorStream();
        }
        if (errorStream != null) {
            Intrinsics.checkNotNullExpressionValue(errorStream, "");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, Charsets.UTF_8), 8192);
            String joinToString$default = CollectionsKt.joinToString$default(TextStreamsKt.readLines(bufferedReader), null, null, null, 0, null, null, 63, null);
            bufferedReader.close();
            if (joinToString$default != null) {
                str = joinToString$default;
            }
        }
        long currentTimeMillis = System.currentTimeMillis() - j;
        StringBuilder sb4 = new StringBuilder("response code:");
        sb4.append(httpURLConnection.getResponseCode());
        sb4.append(' ');
        sb4.append(httpURLConnection.getResponseMessage());
        sb4.append("\n\tbody:");
        sb4.append(str);
        sb4.append("\n\ttook ");
        sb4.append(currentTimeMillis);
        sb4.append("ms");
        String obj = sb4.toString();
        StringBuilder sb5 = new StringBuilder("HTTP: [");
        sb5.append(httpURLConnection.hashCode());
        sb5.append("] ");
        sb5.append(obj);
        String AFInAppEventParameterName2 = AFInAppEventParameterName(sb5.toString());
        if (getValues()) {
            AFLogger.afRDLog(AFInAppEventParameterName2);
        } else {
            AFLogger.afVerboseLog(AFInAppEventParameterName2);
        }
        return AFd1eSDK.AFKeystoreWrapper(httpURLConnection);
    }
}
