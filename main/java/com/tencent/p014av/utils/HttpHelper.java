package com.tencent.p014av.utils;

import com.tencent.p014av.sdk.HttpParam;
import java.io.File;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes3.dex */
public class HttpHelper {
    private static final String TAG = "HttpHelper";

    /* loaded from: classes3.dex */
    public interface HttpRequestListener {
        void onCompleted(String str, int i, String str2, byte[] bArr, Object obj);
    }

    public static native boolean nativeCheckClientTrusted(Object obj, String str);

    public static native boolean nativeCheckServerTrusted(Object obj, String str);

    public static native boolean nativeVerify(String str, Object obj);

    public static String getHost(String str) {
        try {
            return new URL(str).getHost();
        } catch (MalformedURLException unused) {
            return "";
        }
    }

    public static boolean httpGetRequest(String str, Object obj, HttpRequestListener httpRequestListener) {
        return httpGetRequest(str, obj, 5000, httpRequestListener);
    }

    public static void doExceptionLog(String str) {
        InetAddress inetAddress = null;
        if (str != null) {
            try {
                inetAddress = InetAddress.getByName(getHost(str));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        if (inetAddress != null) {
            QLog.m600e(TAG, "doExceptionLog address.getHostAddress()=" + inetAddress.getHostAddress() + ", url=" + String.valueOf(str));
            return;
        }
        QLog.m600e(TAG, "doExceptionLog address null, url=" + String.valueOf(str));
    }

    public static boolean httpGetRequest(final String str, final Object obj, final int i, final HttpRequestListener httpRequestListener) {
        QLog.m602i(TAG, "httpGetRequest|url = " + str + "|| http request timeout =" + i);
        new Thread(new Runnable() { // from class: com.tencent.av.utils.HttpHelper.1
            /* JADX WARN: Removed duplicated region for block: B:64:0x014b  */
            /* JADX WARN: Removed duplicated region for block: B:67:0x0152  */
            /* JADX WARN: Removed duplicated region for block: B:69:? A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:70:0x0140 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 347
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.utils.HttpHelper.RunnableC25341.run():void");
            }
        }).start();
        return true;
    }

    public static boolean httpPostRequest(String str, byte[] bArr, Map<String, String> map, Object obj, HttpRequestListener httpRequestListener) {
        return httpPostRequest(str, bArr, map, obj, 5000, httpRequestListener);
    }

    public static boolean httpPostRequest(final String str, final byte[] bArr, final Map<String, String> map, final Object obj, final int i, final HttpRequestListener httpRequestListener) {
        QLog.m602i(TAG, "httpPostRequest|url = " + str + "|| http request timeout =" + i);
        new Thread(new Runnable() { // from class: com.tencent.av.utils.HttpHelper.2
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:72:0x0189  */
            /* JADX WARN: Removed duplicated region for block: B:75:0x0190  */
            /* JADX WARN: Removed duplicated region for block: B:77:? A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:78:0x017f A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r0v13 */
            /* JADX WARN: Type inference failed for: r0v14 */
            /* JADX WARN: Type inference failed for: r0v16 */
            /* JADX WARN: Type inference failed for: r0v17 */
            /* JADX WARN: Type inference failed for: r0v18 */
            /* JADX WARN: Type inference failed for: r0v19 */
            /* JADX WARN: Type inference failed for: r0v21 */
            /* JADX WARN: Type inference failed for: r0v22 */
            /* JADX WARN: Type inference failed for: r0v23, types: [byte[]] */
            /* JADX WARN: Type inference failed for: r0v24 */
            /* JADX WARN: Type inference failed for: r0v25 */
            /* JADX WARN: Type inference failed for: r0v26 */
            /* JADX WARN: Type inference failed for: r0v27 */
            /* JADX WARN: Type inference failed for: r0v28 */
            /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.String] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 409
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.utils.HttpHelper.RunnableC25352.run():void");
            }
        }).start();
        return true;
    }

    public static boolean uploadFileToS3Request(final String str, final String str2, final HttpParam httpParam, final HttpRequestListener httpRequestListener) {
        new Thread(new Runnable() { // from class: com.tencent.av.utils.HttpHelper.3
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:12:0x01ca  */
            /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:16:0x01b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:21:0x01a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:26:0x0197 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:9:0x01c3  */
            /* JADX WARN: Type inference failed for: r0v2 */
            /* JADX WARN: Type inference failed for: r0v4 */
            /* JADX WARN: Type inference failed for: r0v5 */
            /* JADX WARN: Type inference failed for: r0v50 */
            /* JADX WARN: Type inference failed for: r0v54 */
            /* JADX WARN: Type inference failed for: r0v7 */
            /* JADX WARN: Type inference failed for: r3v10 */
            /* JADX WARN: Type inference failed for: r3v11 */
            /* JADX WARN: Type inference failed for: r3v12 */
            /* JADX WARN: Type inference failed for: r3v18, types: [java.io.OutputStream, java.io.DataOutputStream] */
            /* JADX WARN: Type inference failed for: r3v19 */
            /* JADX WARN: Type inference failed for: r3v2 */
            /* JADX WARN: Type inference failed for: r3v21 */
            /* JADX WARN: Type inference failed for: r3v22, types: [java.io.OutputStream] */
            /* JADX WARN: Type inference failed for: r3v23 */
            /* JADX WARN: Type inference failed for: r3v3 */
            /* JADX WARN: Type inference failed for: r3v4 */
            /* JADX WARN: Type inference failed for: r3v5 */
            /* JADX WARN: Type inference failed for: r3v6 */
            /* JADX WARN: Type inference failed for: r3v7 */
            /* JADX WARN: Type inference failed for: r3v8 */
            /* JADX WARN: Type inference failed for: r3v9 */
            /* JADX WARN: Type inference failed for: r8v12, types: [java.net.HttpURLConnection] */
            /* JADX WARN: Type inference failed for: r8v13 */
            /* JADX WARN: Type inference failed for: r8v14 */
            /* JADX WARN: Type inference failed for: r8v15, types: [java.net.HttpURLConnection] */
            /* JADX WARN: Type inference failed for: r8v16 */
            /* JADX WARN: Type inference failed for: r8v17 */
            /* JADX WARN: Type inference failed for: r8v23 */
            /* JADX WARN: Type inference failed for: r8v24 */
            /* JADX WARN: Type inference failed for: r8v3 */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 466
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.utils.HttpHelper.RunnableC25363.run():void");
            }
        }).start();
        return true;
    }

    public static boolean uploadFileToCosRequest(final String str, final String str2, final String str3, final HttpRequestListener httpRequestListener) {
        new Thread(new Runnable() { // from class: com.tencent.av.utils.HttpHelper.4
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:12:0x0204  */
            /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:16:0x01f2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:21:0x01e0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:26:0x01d1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:9:0x01fd  */
            /* JADX WARN: Type inference failed for: r0v24 */
            /* JADX WARN: Type inference failed for: r0v25 */
            /* JADX WARN: Type inference failed for: r0v29, types: [byte[]] */
            /* JADX WARN: Type inference failed for: r12v10, types: [com.tencent.av.utils.HttpHelper$HttpRequestListener] */
            /* JADX WARN: Type inference failed for: r16v5, types: [byte[]] */
            /* JADX WARN: Type inference failed for: r16v6 */
            /* JADX WARN: Type inference failed for: r16v7 */
            /* JADX WARN: Type inference failed for: r3v1 */
            /* JADX WARN: Type inference failed for: r3v10 */
            /* JADX WARN: Type inference failed for: r3v13, types: [java.net.HttpURLConnection] */
            /* JADX WARN: Type inference failed for: r3v2 */
            /* JADX WARN: Type inference failed for: r3v24 */
            /* JADX WARN: Type inference failed for: r3v25 */
            /* JADX WARN: Type inference failed for: r3v26 */
            /* JADX WARN: Type inference failed for: r3v27 */
            /* JADX WARN: Type inference failed for: r3v3 */
            /* JADX WARN: Type inference failed for: r3v5 */
            /* JADX WARN: Type inference failed for: r3v9 */
            /* JADX WARN: Type inference failed for: r8v10 */
            /* JADX WARN: Type inference failed for: r8v11 */
            /* JADX WARN: Type inference failed for: r8v12 */
            /* JADX WARN: Type inference failed for: r8v13 */
            /* JADX WARN: Type inference failed for: r8v14 */
            /* JADX WARN: Type inference failed for: r8v15 */
            /* JADX WARN: Type inference failed for: r8v2 */
            /* JADX WARN: Type inference failed for: r8v25, types: [java.io.BufferedInputStream, java.io.InputStream] */
            /* JADX WARN: Type inference failed for: r8v3 */
            /* JADX WARN: Type inference failed for: r8v4 */
            /* JADX WARN: Type inference failed for: r8v6 */
            /* JADX WARN: Type inference failed for: r8v8 */
            /* JADX WARN: Type inference failed for: r8v9 */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 524
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.utils.HttpHelper.RunnableC25374.run():void");
            }
        }).start();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File openFile(String str) {
        if (str == null || str.equals("")) {
            QLog.m602i(TAG, "checkFile| filePath == null.");
            return null;
        }
        File file = new File(str);
        if (file.exists()) {
            return file;
        }
        QLog.m602i(TAG, "checkFile| filePath is not exist. path=" + str);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getParamString(Map<String, String> map, String str) {
        String str2 = "";
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                str2 = ((str2 + "--" + str + "\r\n") + "Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n") + entry.getValue() + "\r\n";
            }
        }
        return str2;
    }

    public static SSLSocketFactory createSSLSocketFactory() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new TrustManager[]{new TrustAllManager()}, new SecureRandom());
            return sSLContext.getSocketFactory();
        } catch (Exception unused) {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static class TrustAllManager implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            HttpHelper.nativeCheckClientTrusted(x509CertificateArr, str);
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            HttpHelper.nativeCheckServerTrusted(x509CertificateArr, str);
        }
    }

    /* loaded from: classes3.dex */
    public static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return HttpHelper.nativeVerify(str, sSLSession);
        }
    }
}
