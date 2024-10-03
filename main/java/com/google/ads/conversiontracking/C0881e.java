package com.google.ads.conversiontracking;

import android.content.Context;
import android.util.Log;
import com.google.ads.conversiontracking.C0883g;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.ads.conversiontracking.e */
/* loaded from: classes.dex */
public class C0881e {

    /* renamed from: c */
    private Context f431c;

    /* renamed from: f */
    private C0882f f434f;

    /* renamed from: a */
    private final Object f429a = new Object();

    /* renamed from: d */
    private boolean f432d = true;

    /* renamed from: e */
    private boolean f433e = false;

    /* renamed from: b */
    private final List<C0880d> f430b = new LinkedList();

    public C0881e(Context context) {
        this.f431c = context;
        this.f434f = new C0882f(context);
        new Thread(new b()).start();
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        long m183b = (C0883g.m183b(context) + 300000) - C0883g.m161a();
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new a(), m183b > 0 ? m183b : 0L, 300000L, TimeUnit.MILLISECONDS);
    }

    /* renamed from: a */
    public void m148a(String str, C0883g.c cVar, boolean z, boolean z2, boolean z3) {
        final C0880d c0880d = new C0880d(str, cVar, z, z2);
        synchronized (this.f429a) {
            if (!z3) {
                m147a(new Runnable() { // from class: com.google.ads.conversiontracking.e.1
                    @Override // java.lang.Runnable
                    public void run() {
                        C0881e.this.m146a(c0880d);
                    }
                });
                return;
            }
            this.f434f.m157b(c0880d);
            if (this.f433e && C0883g.m188d(this.f431c)) {
                this.f430b.add(c0880d);
                this.f432d = true;
                this.f429a.notify();
            }
        }
    }

    /* renamed from: a */
    protected void m147a(Runnable runnable) {
        new Thread(runnable).start();
    }

    /* renamed from: com.google.ads.conversiontracking.e$a */
    /* loaded from: classes.dex */
    private class a implements Runnable {
        private a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (C0881e.this.f429a) {
                if (C0881e.this.f433e && C0883g.m188d(C0881e.this.f431c) && !C0881e.this.f432d) {
                    C0881e.this.f430b.addAll(C0881e.this.f434f.m154a(100L));
                    C0883g.m187c(C0881e.this.f431c);
                    C0881e.this.f432d = true;
                    C0881e.this.f429a.notify();
                }
            }
        }
    }

    /* renamed from: com.google.ads.conversiontracking.e$b */
    /* loaded from: classes.dex */
    public class b implements Runnable {

        /* renamed from: a */
        protected long f438a = 0;

        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0880d c0880d;
            try {
                C0881e.this.f433e = true;
                while (true) {
                    synchronized (C0881e.this.f429a) {
                        while (C0881e.this.f430b.isEmpty()) {
                            C0881e.this.f432d = false;
                            C0881e.this.f429a.wait();
                        }
                        C0881e.this.f432d = true;
                        c0880d = (C0880d) C0881e.this.f430b.remove(0);
                    }
                    if (c0880d != null) {
                        if (!C0883g.m182a(C0881e.this.f431c, c0880d.f425e, c0880d.f426f, c0880d.f422b)) {
                            C0881e.this.f434f.m155a(c0880d);
                        } else {
                            int m146a = C0881e.this.m146a(c0880d);
                            if (m146a == 2) {
                                C0881e.this.f434f.m155a(c0880d);
                                this.f438a = 0L;
                            } else if (m146a == 0) {
                                C0881e.this.f434f.m159c(c0880d);
                                m150a();
                                Thread.sleep(this.f438a);
                            } else {
                                C0881e.this.f434f.m159c(c0880d);
                                this.f438a = 0L;
                            }
                        }
                    }
                }
            } catch (InterruptedException unused) {
                Log.w("GoogleConversionReporter", "Dispatch thread is interrupted.");
                C0881e.this.f433e = false;
            }
        }

        /* renamed from: a */
        private void m150a() {
            long j = this.f438a;
            if (j == 0) {
                this.f438a = 1000L;
            } else {
                this.f438a = Math.min(j * 2, 60000L);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x006e, code lost:
    
        r4 = new java.lang.StringBuilder(33);
        r4.append("Receive response code ");
        r4.append(r3);
        android.util.Log.i("GoogleConversionReporter", r4.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0087, code lost:
    
        if (200 > r3) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0089, code lost:
    
        if (r3 >= 300) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x008b, code lost:
    
        r6 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008c, code lost:
    
        if (r6 != 2) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x008e, code lost:
    
        m149b(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0091, code lost:
    
        if (r0 == null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0093, code lost:
    
        r0.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0096, code lost:
    
        return r6;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int m146a(com.google.ads.conversiontracking.C0880d r10) {
        /*
            r9 = this;
            java.lang.String r0 = r10.f427g
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r1 = r0.length()
            java.lang.String r2 = "Pinging: "
            if (r1 == 0) goto L13
            java.lang.String r0 = r2.concat(r0)
            goto L18
        L13:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r2)
        L18:
            java.lang.String r1 = "GoogleConversionReporter"
            android.util.Log.i(r1, r0)
            java.lang.String r0 = r10.f427g
            r2 = 0
            r3 = 0
            r4 = r2
        L22:
            r5 = 5
            r6 = 1
            if (r4 >= r5) goto Lb1
            java.net.URL r5 = new java.net.URL     // Catch: java.lang.Throwable -> L9d java.io.IOException -> L9f
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L9d java.io.IOException -> L9f
            java.net.URLConnection r0 = r5.openConnection()     // Catch: java.lang.Throwable -> L9d java.io.IOException -> L9f
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch: java.lang.Throwable -> L9d java.io.IOException -> L9f
            r0.setInstanceFollowRedirects(r2)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            r3 = 60000(0xea60, float:8.4078E-41)
            r0.setConnectTimeout(r3)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            r0.setReadTimeout(r3)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            r0.setUseCaches(r2)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            int r3 = r0.getResponseCode()     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            r5 = 300(0x12c, float:4.2E-43)
            if (r5 > r3) goto L6e
            r7 = 400(0x190, float:5.6E-43)
            if (r3 >= r7) goto L6e
            java.lang.String r3 = "Location"
            java.lang.String r3 = r0.getHeaderField(r3)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            if (r5 == 0) goto L63
            java.lang.String r10 = "Unable to follow redirect, no Location header."
            android.util.Log.i(r1, r10)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            if (r0 == 0) goto L62
            r0.disconnect()
        L62:
            return r2
        L63:
            if (r0 == 0) goto L68
            r0.disconnect()
        L68:
            int r4 = r4 + 1
            r8 = r3
            r3 = r0
            r0 = r8
            goto L22
        L6e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            r7 = 33
            r4.<init>(r7)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            java.lang.String r7 = "Receive response code "
            r4.append(r7)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            r4.append(r3)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            android.util.Log.i(r1, r4)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
            r4 = 200(0xc8, float:2.8E-43)
            r7 = 2
            if (r4 > r3) goto L8c
            if (r3 >= r5) goto L8c
            r6 = r7
        L8c:
            if (r6 != r7) goto L91
            r9.m149b(r10)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L9a
        L91:
            if (r0 == 0) goto L96
            r0.disconnect()
        L96:
            return r6
        L97:
            r10 = move-exception
            r3 = r0
            goto Lab
        L9a:
            r10 = move-exception
            r3 = r0
            goto La0
        L9d:
            r10 = move-exception
            goto Lab
        L9f:
            r10 = move-exception
        La0:
            java.lang.String r0 = "Error sending ping"
            android.util.Log.e(r1, r0, r10)     // Catch: java.lang.Throwable -> L9d
            if (r3 == 0) goto Laa
            r3.disconnect()
        Laa:
            return r2
        Lab:
            if (r3 == 0) goto Lb0
            r3.disconnect()
        Lb0:
            throw r10
        Lb1:
            java.lang.String r10 = "Ping failed; too many redirects."
            android.util.Log.e(r1, r10)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.conversiontracking.C0881e.m146a(com.google.ads.conversiontracking.d):int");
    }

    /* renamed from: b */
    protected void m149b(C0880d c0880d) {
        if (c0880d.f422b || !c0880d.f421a) {
            return;
        }
        C0883g.m176a(this.f431c, c0880d.f425e, c0880d.f426f);
    }
}
