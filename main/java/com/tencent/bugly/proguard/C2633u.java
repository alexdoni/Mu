package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.u */
/* loaded from: classes3.dex */
public final class C2633u {

    /* renamed from: a */
    public static final long f1509a = System.currentTimeMillis();

    /* renamed from: b */
    private static C2633u f1510b;

    /* renamed from: c */
    private Context f1511c;

    /* renamed from: f */
    private SharedPreferences f1514f;

    /* renamed from: e */
    private Map<Integer, Map<String, C2632t>> f1513e = new HashMap();

    /* renamed from: d */
    private String f1512d = C2566aa.m648b().f1028d;

    private C2633u(Context context) {
        this.f1511c = context;
        this.f1514f = context.getSharedPreferences("crashrecord", 0);
    }

    /* renamed from: a */
    public static synchronized C2633u m1135a(Context context) {
        C2633u c2633u;
        synchronized (C2633u.class) {
            if (f1510b == null) {
                f1510b = new C2633u(context);
            }
            c2633u = f1510b;
        }
        return c2633u;
    }

    /* renamed from: a */
    public static synchronized C2633u m1134a() {
        C2633u c2633u;
        synchronized (C2633u.class) {
            c2633u = f1510b;
        }
        return c2633u;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public synchronized boolean m1145c(int i) {
        try {
            List<C2632t> m1146d = m1146d(i);
            if (m1146d == null) {
                return false;
            }
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (C2632t c2632t : m1146d) {
                if (c2632t.f1503b != null && c2632t.f1503b.equalsIgnoreCase(this.f1512d) && c2632t.f1505d > 0) {
                    arrayList.add(c2632t);
                }
                if (c2632t.f1504c + 86400000 < currentTimeMillis) {
                    arrayList2.add(c2632t);
                }
            }
            Collections.sort(arrayList);
            if (arrayList.size() >= 2) {
                if (arrayList.size() <= 0 || ((C2632t) arrayList.get(arrayList.size() - 1)).f1504c + 86400000 >= currentTimeMillis) {
                    return true;
                }
                m1146d.clear();
                m1138a(i, (int) m1146d);
                return false;
            }
            m1146d.removeAll(arrayList2);
            m1138a(i, (int) m1146d);
            return false;
        } catch (Exception unused) {
            C2577al.m787e("isFrequentCrash failed", new Object[0]);
            return false;
        }
    }

    /* renamed from: a */
    public final void m1147a(final int i) {
        C2576ak.m772a().m774a(new Runnable() { // from class: com.tencent.bugly.proguard.u.1

            /* renamed from: a */
            final /* synthetic */ int f1515a = 1004;

            @Override // java.lang.Runnable
            public final void run() {
                C2632t c2632t;
                try {
                    if (TextUtils.isEmpty(C2633u.this.f1512d)) {
                        return;
                    }
                    List<C2632t> m1146d = C2633u.this.m1146d(this.f1515a);
                    if (m1146d == null) {
                        m1146d = new ArrayList();
                    }
                    if (C2633u.this.f1513e.get(Integer.valueOf(this.f1515a)) == null) {
                        C2633u.this.f1513e.put(Integer.valueOf(this.f1515a), new HashMap());
                    }
                    if (((Map) C2633u.this.f1513e.get(Integer.valueOf(this.f1515a))).get(C2633u.this.f1512d) != null) {
                        c2632t = (C2632t) ((Map) C2633u.this.f1513e.get(Integer.valueOf(this.f1515a))).get(C2633u.this.f1512d);
                        c2632t.f1505d = i;
                    } else {
                        c2632t = new C2632t();
                        c2632t.f1502a = this.f1515a;
                        c2632t.f1508g = C2633u.f1509a;
                        c2632t.f1503b = C2633u.this.f1512d;
                        c2632t.f1507f = C2566aa.m648b().f1039o;
                        c2632t.f1506e = C2566aa.m648b().f1032h;
                        c2632t.f1504c = System.currentTimeMillis();
                        c2632t.f1505d = i;
                        ((Map) C2633u.this.f1513e.get(Integer.valueOf(this.f1515a))).put(C2633u.this.f1512d, c2632t);
                    }
                    ArrayList arrayList = new ArrayList();
                    boolean z = false;
                    for (C2632t c2632t2 : m1146d) {
                        if (C2633u.m1140a(c2632t2, c2632t)) {
                            c2632t2.f1505d = c2632t.f1505d;
                            z = true;
                        }
                        if (C2633u.m1142b(c2632t2, c2632t)) {
                            arrayList.add(c2632t2);
                        }
                    }
                    m1146d.removeAll(arrayList);
                    if (!z) {
                        m1146d.add(c2632t);
                    }
                    C2633u.this.m1138a(this.f1515a, (int) m1146d);
                } catch (Exception unused) {
                    C2577al.m787e("saveCrashRecord failed", new Object[0]);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004a, code lost:
    
        if (r6 == null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003e, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003c, code lost:
    
        if (r6 == null) goto L31;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized <T extends java.util.List<?>> T m1146d(int r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            android.content.Context r3 = r5.f1511c     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            java.lang.String r4 = "crashrecord"
            java.io.File r3 = r3.getDir(r4, r1)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            r2.<init>(r3, r6)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            boolean r6 = r2.exists()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            if (r6 != 0) goto L1c
            monitor-exit(r5)
            return r0
        L1c:
            java.io.ObjectInputStream r6 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L31 java.lang.ClassNotFoundException -> L34 java.io.IOException -> L42
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L31 java.lang.ClassNotFoundException -> L34 java.io.IOException -> L42
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L31 java.lang.ClassNotFoundException -> L34 java.io.IOException -> L42
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L31 java.lang.ClassNotFoundException -> L34 java.io.IOException -> L42
            java.lang.Object r2 = r6.readObject()     // Catch: java.lang.ClassNotFoundException -> L35 java.io.IOException -> L43 java.lang.Throwable -> L4d
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.ClassNotFoundException -> L35 java.io.IOException -> L43 java.lang.Throwable -> L4d
            r6.close()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            monitor-exit(r5)
            return r2
        L31:
            r2 = move-exception
            r6 = r0
            goto L4e
        L34:
            r6 = r0
        L35:
            java.lang.String r2 = "get object error"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L4d
            com.tencent.bugly.proguard.C2577al.m781a(r2, r3)     // Catch: java.lang.Throwable -> L4d
            if (r6 == 0) goto L5d
        L3e:
            r6.close()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            goto L5d
        L42:
            r6 = r0
        L43:
            java.lang.String r2 = "open record file error"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L4d
            com.tencent.bugly.proguard.C2577al.m781a(r2, r3)     // Catch: java.lang.Throwable -> L4d
            if (r6 == 0) goto L5d
            goto L3e
        L4d:
            r2 = move-exception
        L4e:
            if (r6 == 0) goto L53
            r6.close()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
        L53:
            throw r2     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
        L54:
            r6 = move-exception
            goto L5f
        L56:
            java.lang.String r6 = "readCrashRecord error"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L54
            com.tencent.bugly.proguard.C2577al.m787e(r6, r1)     // Catch: java.lang.Throwable -> L54
        L5d:
            monitor-exit(r5)
            return r0
        L5f:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2633u.m1146d(int):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0049 A[Catch: all -> 0x004d, Exception -> 0x004f, TRY_ENTER, TryCatch #1 {Exception -> 0x004f, blocks: (B:9:0x0006, B:15:0x0025, B:22:0x003f, B:30:0x0049, B:31:0x004c), top: B:8:0x0006, outer: #5 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized <T extends java.util.List<?>> void m1138a(int r5, T r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r6 != 0) goto L5
            monitor-exit(r4)
            return
        L5:
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            android.content.Context r2 = r4.f1511c     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            java.lang.String r3 = "crashrecord"
            java.io.File r2 = r2.getDir(r3, r0)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            r1.<init>(r2, r5)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            r5 = 0
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L30
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L30
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L30
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L30
            r2.writeObject(r6)     // Catch: java.io.IOException -> L2a java.lang.Throwable -> L46
            r2.close()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            monitor-exit(r4)
            return
        L2a:
            r5 = move-exception
            goto L33
        L2c:
            r6 = move-exception
            r2 = r5
            r5 = r6
            goto L47
        L30:
            r6 = move-exception
            r2 = r5
            r5 = r6
        L33:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L46
            java.lang.String r5 = "open record file error"
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L46
            com.tencent.bugly.proguard.C2577al.m781a(r5, r6)     // Catch: java.lang.Throwable -> L46
            if (r2 == 0) goto L44
            r2.close()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            monitor-exit(r4)
            return
        L44:
            monitor-exit(r4)
            return
        L46:
            r5 = move-exception
        L47:
            if (r2 == 0) goto L4c
            r2.close()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
        L4c:
            throw r5     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
        L4d:
            r5 = move-exception
            goto L58
        L4f:
            java.lang.String r5 = "writeCrashRecord error"
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L4d
            com.tencent.bugly.proguard.C2577al.m787e(r5, r6)     // Catch: java.lang.Throwable -> L4d
            monitor-exit(r4)
            return
        L58:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2633u.m1138a(int, java.util.List):void");
    }

    /* renamed from: b */
    public final synchronized boolean m1148b(final int i) {
        boolean z;
        z = true;
        try {
            z = this.f1514f.getBoolean(i + "_" + this.f1512d, true);
            C2576ak.m772a().m774a(new Runnable() { // from class: com.tencent.bugly.proguard.u.2
                @Override // java.lang.Runnable
                public final void run() {
                    boolean m1145c = C2633u.this.m1145c(i);
                    C2633u.this.f1514f.edit().putBoolean(i + "_" + C2633u.this.f1512d, !m1145c).commit();
                }
            });
        } catch (Exception unused) {
            C2577al.m787e("canInit error", new Object[0]);
            return z;
        }
        return z;
    }

    /* renamed from: a */
    static /* synthetic */ boolean m1140a(C2632t c2632t, C2632t c2632t2) {
        return c2632t.f1508g == c2632t2.f1508g && c2632t.f1503b != null && c2632t.f1503b.equalsIgnoreCase(c2632t2.f1503b);
    }

    /* renamed from: b */
    static /* synthetic */ boolean m1142b(C2632t c2632t, C2632t c2632t2) {
        if (c2632t.f1506e == null || c2632t.f1506e.equalsIgnoreCase(c2632t2.f1506e)) {
            return !(c2632t.f1507f == null || c2632t.f1507f.equalsIgnoreCase(c2632t2.f1507f)) || c2632t.f1505d <= 0;
        }
        return true;
    }
}
