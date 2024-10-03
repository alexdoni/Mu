package com.tencent.p014av.ptt;

import android.util.Base64;
import com.tencent.p014av.utils.HttpHelper;
import com.tencent.p014av.utils.QLog;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes3.dex */
public class TokenFetcher {
    public static final String TAG = "TokenFetcher";
    private static TokenFetcher s_instance;
    private HostnameVerifier _hostnameVerifier;
    private SSLSocketFactory _sslSocketFactory;
    private AppInfo mAppInfo;
    private int StreamRecTimeOut = 30;
    private final int MILLONSECOND = 1000;

    /* loaded from: classes3.dex */
    public static class AppInfo {
        public String accounttype;
        public String appidat3rd;
        public byte[] authBuffer;
        public String identifier;
        public String sdk_appid;
    }

    /* loaded from: classes3.dex */
    public interface HttpRequestListener {
        void onCompleted(int i, String str, Object obj);
    }

    private TokenFetcher() {
        this.mAppInfo = null;
        AppInfo appInfo = new AppInfo();
        this.mAppInfo = appInfo;
        appInfo.sdk_appid = "1400029763";
        this.mAppInfo.appidat3rd = "1400029763";
        this.mAppInfo.accounttype = "12346";
        this.mAppInfo.authBuffer = null;
        this.mAppInfo.identifier = "354589908";
        this._sslSocketFactory = HttpHelper.createSSLSocketFactory();
        this._hostnameVerifier = new HttpHelper.TrustAllHostnameVerifier();
    }

    public static TokenFetcher getInstance() {
        TokenFetcher tokenFetcher;
        synchronized (TokenFetcher.class) {
            if (s_instance == null) {
                s_instance = new TokenFetcher();
            }
            tokenFetcher = s_instance;
        }
        return tokenFetcher;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.mAppInfo = appInfo;
        if (appInfo.authBuffer != null) {
            QLog.m602i(TAG, String.format("setAppInfo authbuffer=%s", Base64.encodeToString(this.mAppInfo.authBuffer, 0)));
        } else {
            QLog.m602i(TAG, String.format("setAppInfo authbuffer is null", new Object[0]));
        }
    }

    public AppInfo getAppInfo() {
        return this.mAppInfo;
    }

    public void SetStreamingRecTimeOut(int i) {
        this.StreamRecTimeOut = i;
    }

    public void httpRequestWithBody(final String str, final byte[] bArr, int i, final HttpRequestListener httpRequestListener, final Object obj) {
        new Thread(new Runnable() { // from class: com.tencent.av.ptt.TokenFetcher.1
            /* JADX WARN: Code restructure failed: missing block: B:52:0x01c8, code lost:
            
                if (r0 == 0) goto L116;
             */
            /* JADX WARN: Code restructure failed: missing block: B:53:0x01ca, code lost:
            
                r0.onCompleted(1, r2, r5);
                r0 = r0;
                r4 = r4;
                r5 = r5;
             */
            /* JADX WARN: Code restructure failed: missing block: B:55:0x01cf, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:75:0x017e, code lost:
            
                if (r0 == 0) goto L116;
             */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v10 */
            /* JADX WARN: Type inference failed for: r0v4, types: [com.tencent.av.ptt.TokenFetcher$HttpRequestListener] */
            /* JADX WARN: Type inference failed for: r0v6, types: [com.tencent.av.ptt.TokenFetcher$HttpRequestListener] */
            /* JADX WARN: Type inference failed for: r0v7, types: [com.tencent.av.ptt.TokenFetcher$HttpRequestListener] */
            /* JADX WARN: Type inference failed for: r0v9 */
            /* JADX WARN: Type inference failed for: r4v10 */
            /* JADX WARN: Type inference failed for: r4v13, types: [java.io.BufferedReader] */
            /* JADX WARN: Type inference failed for: r4v14, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r4v15 */
            /* JADX WARN: Type inference failed for: r4v17 */
            /* JADX WARN: Type inference failed for: r4v18 */
            /* JADX WARN: Type inference failed for: r4v2 */
            /* JADX WARN: Type inference failed for: r4v21 */
            /* JADX WARN: Type inference failed for: r4v22 */
            /* JADX WARN: Type inference failed for: r4v27 */
            /* JADX WARN: Type inference failed for: r4v29 */
            /* JADX WARN: Type inference failed for: r4v3 */
            /* JADX WARN: Type inference failed for: r4v8, types: [java.io.BufferedReader] */
            /* JADX WARN: Type inference failed for: r4v9, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r5v11, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r5v6, types: [java.lang.String] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 514
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.ptt.TokenFetcher.RunnableC25301.run():void");
            }
        }).start();
    }

    public void httpRequest(final String str, final String str2, final HttpRequestListener httpRequestListener, final Object obj) {
        new Thread(new Runnable() { // from class: com.tencent.av.ptt.TokenFetcher.2
            /* JADX WARN: Code restructure failed: missing block: B:52:0x01af, code lost:
            
                if (r0 == null) goto L119;
             */
            /* JADX WARN: Code restructure failed: missing block: B:53:0x01b1, code lost:
            
                r1 = r5;
                r0.onCompleted(1, r4, r1);
                r1 = r1;
                r2 = r2;
             */
            /* JADX WARN: Code restructure failed: missing block: B:55:0x01b6, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:75:0x0171, code lost:
            
                if (r0 == null) goto L119;
             */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v13, types: [com.tencent.av.ptt.TokenFetcher$HttpRequestListener] */
            /* JADX WARN: Type inference failed for: r0v41 */
            /* JADX WARN: Type inference failed for: r0v42 */
            /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v1 */
            /* JADX WARN: Type inference failed for: r2v10 */
            /* JADX WARN: Type inference failed for: r2v13 */
            /* JADX WARN: Type inference failed for: r2v16 */
            /* JADX WARN: Type inference failed for: r2v19 */
            /* JADX WARN: Type inference failed for: r2v28 */
            /* JADX WARN: Type inference failed for: r2v29 */
            /* JADX WARN: Type inference failed for: r2v30 */
            /* JADX WARN: Type inference failed for: r2v32 */
            /* JADX WARN: Type inference failed for: r2v4 */
            /* JADX WARN: Type inference failed for: r2v5, types: [java.io.BufferedReader] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 489
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.p014av.ptt.TokenFetcher.RunnableC25312.run():void");
            }
        }).start();
    }
}
