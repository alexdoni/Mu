package com.xsdk.ab_firstbase.net.okhttp3;

import android.webkit.WebSettings;
import com.xsdk.ab_firstbase.statisics.util.ContextHolder;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/* loaded from: classes3.dex */
public class OkHttpManger {
    public static int GetMethod = 2;
    public static int PostMethod = 1;
    private static volatile OkHttpManger instance;
    private static OkHttpClient okHttpClient;
    private RequestBody requestBody;
    private String url = "";
    private String encode = "utf-8";
    private String method = "POST";
    private int readTimeout = 15000;
    private int connectTimeout = 15000;
    private int writeTimeout = 15000;

    public OkHttpManger() {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(this.readTimeout, TimeUnit.MILLISECONDS).readTimeout(this.connectTimeout, TimeUnit.MILLISECONDS).writeTimeout(this.writeTimeout, TimeUnit.MILLISECONDS).connectionPool(new ConnectionPool(10, 10L, TimeUnit.SECONDS)).addInterceptor(new DomainInterceptor()).followRedirects(true).build();
    }

    public static OkHttpManger getInstance() {
        if (instance == null) {
            synchronized (OkHttpManger.class) {
                if (instance == null) {
                    instance = new OkHttpManger();
                }
            }
        }
        return instance;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00e0 A[Catch: all -> 0x00f3, TryCatch #0 {all -> 0x00f3, blocks: (B:3:0x0004, B:5:0x002f, B:7:0x003a, B:9:0x0040, B:10:0x0048, B:12:0x004e, B:14:0x005e, B:17:0x0078, B:19:0x0080, B:21:0x0088, B:24:0x0091, B:26:0x00e0, B:27:0x00e5, B:31:0x009a, B:32:0x0034), top: B:2:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String postAsynBackString(java.lang.String r6, int r7, java.lang.String r8, java.util.Map<java.lang.String, java.lang.String> r9, okhttp3.Callback r10) {
        /*
            r5 = this;
            java.lang.String r0 = "game_id"
            java.lang.String r1 = "app_id"
            okhttp3.Headers$Builder r2 = new okhttp3.Headers$Builder     // Catch: java.lang.Throwable -> Lf3
            r2.<init>()     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r3 = "Accept-Charset"
            java.lang.String r4 = r5.encode     // Catch: java.lang.Throwable -> Lf3
            okhttp3.Headers$Builder r2 = r2.add(r3, r4)     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r3 = "Accept-Encoding"
            java.lang.String r4 = r5.encode     // Catch: java.lang.Throwable -> Lf3
            okhttp3.Headers$Builder r2 = r2.add(r3, r4)     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r3 = "Accept"
            java.lang.String r4 = r5.encode     // Catch: java.lang.Throwable -> Lf3
            okhttp3.Headers$Builder r2 = r2.add(r3, r4)     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r3 = "User-Agent"
            java.lang.String r4 = r5.getUserAgent()     // Catch: java.lang.Throwable -> Lf3
            okhttp3.Headers$Builder r2 = r2.add(r3, r4)     // Catch: java.lang.Throwable -> Lf3
            int r3 = com.xsdk.ab_firstbase.net.okhttp3.OkHttpManger.PostMethod     // Catch: java.lang.Throwable -> Lf3
            if (r3 != r7) goto L34
            java.lang.String r7 = "POST"
            r5.method = r7     // Catch: java.lang.Throwable -> Lf3
            goto L38
        L34:
            java.lang.String r7 = "GET"
            r5.method = r7     // Catch: java.lang.Throwable -> Lf3
        L38:
            if (r9 == 0) goto L5e
            int r7 = r9.size()     // Catch: java.lang.Throwable -> Lf3
            if (r7 <= 0) goto L5e
            java.util.Set r7 = r9.keySet()     // Catch: java.lang.Throwable -> Lf3
            java.util.Iterator r7 = r7.iterator()     // Catch: java.lang.Throwable -> Lf3
        L48:
            boolean r3 = r7.hasNext()     // Catch: java.lang.Throwable -> Lf3
            if (r3 == 0) goto L5e
            java.lang.Object r3 = r7.next()     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> Lf3
            java.lang.Object r4 = r9.get(r3)     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> Lf3
            r2.add(r3, r4)     // Catch: java.lang.Throwable -> Lf3
            goto L48
        L5e:
            okhttp3.Request$Builder r7 = new okhttp3.Request$Builder     // Catch: java.lang.Throwable -> Lf3
            r7.<init>()     // Catch: java.lang.Throwable -> Lf3
            okhttp3.Request$Builder r7 = r7.url(r6)     // Catch: java.lang.Throwable -> Lf3
            okhttp3.Headers r9 = r2.build()     // Catch: java.lang.Throwable -> Lf3
            r7.headers(r9)     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r9 = "api-sdk"
            boolean r9 = r6.contains(r9)     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r2 = "application/json"
            if (r9 != 0) goto L9a
            java.lang.String r9 = "api-pay"
            boolean r9 = r6.contains(r9)     // Catch: java.lang.Throwable -> Lf3
            if (r9 != 0) goto L9a
            java.lang.String r9 = "common/data_report/report"
            boolean r9 = r6.contains(r9)     // Catch: java.lang.Throwable -> Lf3
            if (r9 != 0) goto L9a
            java.lang.String r9 = "cashout/income/log"
            boolean r6 = r6.contains(r9)     // Catch: java.lang.Throwable -> Lf3
            if (r6 == 0) goto L91
            goto L9a
        L91:
            okhttp3.MediaType r6 = okhttp3.MediaType.parse(r2)     // Catch: java.lang.Throwable -> Lf3
            okhttp3.RequestBody r6 = okhttp3.RequestBody.create(r8, r6)     // Catch: java.lang.Throwable -> Lf3
            goto Lde
        L9a:
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: java.lang.Throwable -> Lf3
            r6.<init>()     // Catch: java.lang.Throwable -> Lf3
            java.lang.Object r9 = com.xsdk.ab_firstbase.statisics.util.json.JsonUtil.get(r8, r1)     // Catch: java.lang.Throwable -> Lf3
            r6.put(r1, r9)     // Catch: java.lang.Throwable -> Lf3
            java.lang.Object r9 = com.xsdk.ab_firstbase.statisics.util.json.JsonUtil.get(r8, r0)     // Catch: java.lang.Throwable -> Lf3
            r6.put(r0, r9)     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r9 = "device"
            java.lang.String r0 = "android_id"
            java.lang.Object r0 = com.xsdk.ab_firstbase.statisics.util.json.JsonUtil.get(r8, r0)     // Catch: java.lang.Throwable -> Lf3
            r6.put(r9, r0)     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r9 = "os"
            r0 = 2
            r6.put(r9, r0)     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r9 = new java.lang.String     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r0 = "cXdlIUAjMTIzLyp6eGMtJQ=="
            byte[] r0 = com.xsdk.ab_firstbase.statisics.util.Base64.decode(r0)     // Catch: java.lang.Throwable -> Lf3
            r9.<init>(r0)     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r8 = com.xsdk.ab_firstbase.statisics.util.AESUtil.aesGcmEncrypt(r8, r9)     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r9 = "app_data"
            r6.put(r9, r8)     // Catch: java.lang.Throwable -> Lf3
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> Lf3
            okhttp3.MediaType r8 = okhttp3.MediaType.parse(r2)     // Catch: java.lang.Throwable -> Lf3
            okhttp3.RequestBody r6 = okhttp3.RequestBody.create(r6, r8)     // Catch: java.lang.Throwable -> Lf3
        Lde:
            if (r6 == 0) goto Le5
            java.lang.String r8 = r5.method     // Catch: java.lang.Throwable -> Lf3
            r7.method(r8, r6)     // Catch: java.lang.Throwable -> Lf3
        Le5:
            okhttp3.OkHttpClient r6 = com.xsdk.ab_firstbase.net.okhttp3.OkHttpManger.okHttpClient     // Catch: java.lang.Throwable -> Lf3
            okhttp3.Request r7 = r7.build()     // Catch: java.lang.Throwable -> Lf3
            okhttp3.Call r6 = r6.newCall(r7)     // Catch: java.lang.Throwable -> Lf3
            r6.enqueue(r10)     // Catch: java.lang.Throwable -> Lf3
            goto Lfb
        Lf3:
            r6 = move-exception
            java.lang.String r6 = r6.getMessage()
            com.xsdk.ab_firstbase.statisics.util.LLog.v_noControl(r6)
        Lfb:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xsdk.ab_firstbase.net.okhttp3.OkHttpManger.postAsynBackString(java.lang.String, int, java.lang.String, java.util.Map, okhttp3.Callback):java.lang.String");
    }

    private String getUserAgent() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            try {
                str = WebSettings.getDefaultUserAgent(ContextHolder.getContext()) + "; GZFF";
            } catch (Exception unused) {
                str = System.getProperty("http.agent") + "; GZFF";
            }
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt > 31 && charAt < 127) {
                    stringBuffer.append(charAt);
                }
                stringBuffer.append(String.format("\\u%04x", Integer.valueOf(charAt)));
            }
        } catch (Exception e) {
            stringBuffer.append("okhttp/3.14.9");
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
