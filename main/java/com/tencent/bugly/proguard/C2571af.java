package com.tencent.bugly.proguard;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.af */
/* loaded from: classes3.dex */
public final class C2571af {

    /* renamed from: a */
    static C2571af f1064a;

    /* renamed from: b */
    protected Context f1065b;

    /* renamed from: c */
    public Map<String, String> f1066c = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2571af(Context context) {
        this.f1065b = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x017b A[Catch: all -> 0x016e, TRY_LEAVE, TryCatch #9 {all -> 0x016e, blocks: (B:23:0x009c, B:25:0x00b3, B:28:0x00c4, B:38:0x00c2, B:79:0x00ed, B:97:0x00f5, B:85:0x0120, B:88:0x012b, B:51:0x014c, B:54:0x0159, B:67:0x0175, B:69:0x017b), top: B:22:0x009c }] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] m734a(java.lang.String r21, byte[] r22, com.tencent.bugly.proguard.RunnableC2575aj r23, java.util.Map<java.lang.String, java.lang.String> r24) {
        /*
            Method dump skipped, instructions count: 437
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2571af.m734a(java.lang.String, byte[], com.tencent.bugly.proguard.aj, java.util.Map):byte[]");
    }

    /* renamed from: a */
    private static Map<String, String> m732a(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List<String> list = headerFields.get(str);
            if (list.size() > 0) {
                hashMap.put(str, list.get(0));
            }
        }
        return hashMap;
    }

    /* renamed from: b */
    private static byte[] m733b(HttpURLConnection httpURLConnection) {
        BufferedInputStream bufferedInputStream;
        if (httpURLConnection == null) {
            return null;
        }
        try {
            bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        } catch (Throwable th) {
            th = th;
            bufferedInputStream = null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                bufferedInputStream.close();
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            return byteArray;
        } catch (Throwable th3) {
            th = th3;
            try {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
                return null;
            } finally {
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static HttpURLConnection m731a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            C2577al.m787e("destUrl is null.", new Object[0]);
            return null;
        }
        HttpURLConnection m730a = m730a(str2, str);
        if (m730a == null) {
            C2577al.m787e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            m730a.setRequestProperty("wup_version", "3.0");
            if (map != null && map.size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    m730a.setRequestProperty(entry.getKey(), URLEncoder.encode(entry.getValue(), "utf-8"));
                }
            }
            m730a.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            m730a.setRequestProperty("A38", URLEncoder.encode(str2, "utf-8"));
            OutputStream outputStream = m730a.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return m730a;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            C2577al.m787e("Failed to upload, please check your network.", new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    private static HttpURLConnection m730a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str2);
            if (C2579an.f1127a != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection(C2579an.f1127a);
            } else if (str != null && str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
