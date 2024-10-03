package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.facebook.AuthenticationTokenClaims;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.r */
/* loaded from: classes3.dex */
public final class C2630r {

    /* renamed from: e */
    private static boolean f1472e = true;

    /* renamed from: a */
    private Context f1473a;

    /* renamed from: b */
    private long f1474b;

    /* renamed from: c */
    private int f1475c;

    /* renamed from: d */
    private boolean f1476d;

    public C2630r(Context context, boolean z) {
        this.f1473a = context;
        this.f1476d = z;
    }

    /* renamed from: a */
    public final void m1110a(int i, boolean z) {
        C2568ac m710a = C2568ac.m710a();
        if (m710a != null && !m710a.m719c().f874g && i != 1 && i != 3) {
            C2577al.m787e("UserInfo is disable", new Object[0]);
            return;
        }
        if (i == 1 || i == 3) {
            this.f1475c++;
        }
        C2566aa m646a = C2566aa.m646a(this.f1473a);
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.f847b = i;
        userInfoBean.f848c = m646a.f1028d;
        userInfoBean.f849d = m646a.m663f();
        userInfoBean.f850e = System.currentTimeMillis();
        userInfoBean.f851f = -1L;
        userInfoBean.f859n = m646a.f1039o;
        userInfoBean.f860o = i == 1 ? 1 : 0;
        userInfoBean.f857l = m646a.m654a();
        userInfoBean.f858m = m646a.f1049y;
        userInfoBean.f852g = m646a.f1050z;
        userInfoBean.f853h = m646a.f976A;
        userInfoBean.f854i = m646a.f977B;
        userInfoBean.f856k = m646a.f978C;
        userInfoBean.f863r = m646a.m678t();
        userInfoBean.f864s = m646a.m683y();
        userInfoBean.f861p = m646a.m684z();
        userInfoBean.f862q = m646a.f1048x;
        C2576ak.m772a().m775a(new a(userInfoBean, z), 0L);
    }

    /* renamed from: a */
    public final void m1111a(long j) {
        C2576ak.m772a().m775a(new c(j), j);
    }

    /* renamed from: a */
    public final void m1109a() {
        this.f1474b = C2581ap.m836b() + 86400000;
        C2576ak.m772a().m775a(new b(), (this.f1474b - System.currentTimeMillis()) + 5000);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.r$a */
    /* loaded from: classes3.dex */
    public class a implements Runnable {

        /* renamed from: b */
        private boolean f1482b;

        /* renamed from: c */
        private UserInfoBean f1483c;

        public a(UserInfoBean userInfoBean, boolean z) {
            this.f1483c = userInfoBean;
            this.f1482b = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (C2630r.this.f1476d) {
                try {
                    UserInfoBean userInfoBean = this.f1483c;
                    if (userInfoBean != null) {
                        C2630r.m1096a(userInfoBean);
                        C2577al.m785c("[UserInfo] Record user info.", new Object[0]);
                        C2630r.this.m1097a(this.f1483c, false);
                    }
                    if (this.f1482b) {
                        C2630r.this.m1112b();
                    }
                } catch (Throwable th) {
                    if (C2577al.m782a(th)) {
                        return;
                    }
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    private static void m1100a(List<UserInfoBean> list, List<UserInfoBean> list2) {
        int size = list.size() - 20;
        if (size > 0) {
            int i = 0;
            while (i < list.size() - 1) {
                int i2 = i + 1;
                for (int i3 = i2; i3 < list.size(); i3++) {
                    if (list.get(i).f850e > list.get(i3).f850e) {
                        UserInfoBean userInfoBean = list.get(i);
                        list.set(i, list.get(i3));
                        list.set(i3, userInfoBean);
                    }
                }
                i = i2;
            }
            for (int i4 = 0; i4 < size; i4++) {
                list2.add(list.get(i4));
            }
        }
    }

    /* renamed from: b */
    private static void m1107b(List<UserInfoBean> list, List<UserInfoBean> list2) {
        Iterator<UserInfoBean> it = list.iterator();
        while (it.hasNext()) {
            UserInfoBean next = it.next();
            if (next.f851f != -1) {
                it.remove();
                if (next.f850e < C2581ap.m836b()) {
                    list2.add(next);
                }
            }
        }
    }

    /* renamed from: a */
    private static int m1093a(List<UserInfoBean> list) {
        long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        for (UserInfoBean userInfoBean : list) {
            if (userInfoBean.f850e > currentTimeMillis - AuthenticationTokenClaims.MAX_TIME_SINCE_TOKEN_ISSUED && (userInfoBean.f847b == 1 || userInfoBean.f847b == 4 || userInfoBean.f847b == 3)) {
                i++;
            }
        }
        return i;
    }

    /* renamed from: a */
    private void m1101a(final List<UserInfoBean> list, boolean z) {
        C2566aa m648b;
        if (!m1108b(z)) {
            long currentTimeMillis = System.currentTimeMillis();
            for (UserInfoBean userInfoBean : list) {
                userInfoBean.f851f = currentTimeMillis;
                m1097a(userInfoBean, true);
            }
            C2577al.m786d("uploadCheck failed", new Object[0]);
            return;
        }
        int i = this.f1475c == 1 ? 1 : 2;
        C2614bv c2614bv = null;
        if (list != null && list.size() != 0 && (m648b = C2566aa.m648b()) != null) {
            m648b.m673o();
            C2614bv c2614bv2 = new C2614bv();
            c2614bv2.f1425b = m648b.f1028d;
            c2614bv2.f1426c = m648b.m665g();
            ArrayList<C2613bu> arrayList = new ArrayList<>();
            Iterator<UserInfoBean> it = list.iterator();
            while (it.hasNext()) {
                C2613bu m726a = C2570ae.m726a(it.next());
                if (m726a != null) {
                    arrayList.add(m726a);
                }
            }
            c2614bv2.f1427d = arrayList;
            c2614bv2.f1428e = new HashMap();
            Map<String, String> map = c2614bv2.f1428e;
            m648b.getClass();
            map.put("A7", "");
            c2614bv2.f1428e.put("A6", C2566aa.m649n());
            c2614bv2.f1428e.put("A5", m648b.m672m());
            Map<String, String> map2 = c2614bv2.f1428e;
            StringBuilder sb = new StringBuilder();
            sb.append(m648b.m670k());
            map2.put("A2", sb.toString());
            Map<String, String> map3 = c2614bv2.f1428e;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(m648b.m670k());
            map3.put("A1", sb2.toString());
            c2614bv2.f1428e.put("A24", m648b.f1035k);
            Map<String, String> map4 = c2614bv2.f1428e;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(m648b.m671l());
            map4.put("A17", sb3.toString());
            c2614bv2.f1428e.put("A15", m648b.m675q());
            Map<String, String> map5 = c2614bv2.f1428e;
            StringBuilder sb4 = new StringBuilder();
            sb4.append(m648b.m676r());
            map5.put("A13", sb4.toString());
            c2614bv2.f1428e.put("F08", m648b.f980E);
            c2614bv2.f1428e.put("F09", m648b.f981F);
            Map<String, String> m683y = m648b.m683y();
            if (m683y != null && m683y.size() > 0) {
                for (Map.Entry<String, String> entry : m683y.entrySet()) {
                    c2614bv2.f1428e.put("C04_" + entry.getKey(), entry.getValue());
                }
            }
            if (i == 1) {
                c2614bv2.f1424a = (byte) 1;
            } else if (i != 2) {
                C2577al.m787e("unknown up type %d ", Integer.valueOf(i));
            } else {
                c2614bv2.f1424a = (byte) 2;
            }
            c2614bv = c2614bv2;
        }
        if (c2614bv == null) {
            C2577al.m786d("[UserInfo] Failed to create UserInfoPackage.", new Object[0]);
            return;
        }
        byte[] m728a = C2570ae.m728a((AbstractC2625m) c2614bv);
        if (m728a == null) {
            C2577al.m786d("[UserInfo] Failed to encode data.", new Object[0]);
            return;
        }
        C2609bq m724a = C2570ae.m724a(this.f1473a, 840, m728a);
        if (m724a == null) {
            C2577al.m786d("[UserInfo] Request package is null.", new Object[0]);
            return;
        }
        C2574ai.m746a().m761a(1001, m724a, C2568ac.m710a().m719c().f884q, StrategyBean.f868a, new InterfaceC2573ah() { // from class: com.tencent.bugly.proguard.r.1
            @Override // com.tencent.bugly.proguard.InterfaceC2573ah
            /* renamed from: a */
            public final void mo745a(boolean z2, String str) {
                if (z2) {
                    C2577al.m785c("[UserInfo] Successfully uploaded user info.", new Object[0]);
                    long currentTimeMillis2 = System.currentTimeMillis();
                    for (UserInfoBean userInfoBean2 : list) {
                        userInfoBean2.f851f = currentTimeMillis2;
                        C2630r.this.m1097a(userInfoBean2, true);
                    }
                }
            }
        }, this.f1475c == 1);
    }

    /* renamed from: b */
    public final void m1112b() {
        C2576ak m772a = C2576ak.m772a();
        if (m772a != null) {
            m772a.m774a(new Runnable() { // from class: com.tencent.bugly.proguard.r.2

                /* renamed from: a */
                final /* synthetic */ boolean f1479a = false;

                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        C2630r.this.m1102a(this.f1479a);
                    } catch (Throwable th) {
                        C2577al.m782a(th);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.r$b */
    /* loaded from: classes3.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < C2630r.this.f1474b) {
                C2576ak.m772a().m775a(new b(), (C2630r.this.f1474b - currentTimeMillis) + 5000);
            } else {
                C2630r.this.m1110a(3, false);
                C2630r.this.m1109a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.r$c */
    /* loaded from: classes3.dex */
    public class c implements Runnable {

        /* renamed from: b */
        private long f1486b;

        public c(long j) {
            this.f1486b = j;
        }

        @Override // java.lang.Runnable
        public final void run() {
            C2630r.this.m1112b();
            C2630r.this.m1111a(this.f1486b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1097a(UserInfoBean userInfoBean, boolean z) {
        List<UserInfoBean> m1095a;
        if (userInfoBean == null) {
            return;
        }
        if (!z && userInfoBean.f847b != 1 && (m1095a = m1095a(C2566aa.m646a(this.f1473a).f1028d)) != null && m1095a.size() >= 20) {
            C2577al.m781a("[UserInfo] There are too many user info in local: %d", Integer.valueOf(m1095a.size()));
            return;
        }
        long m1168a = C2635w.m1154a().m1168a("t_ui", m1105b(userInfoBean), (InterfaceC2634v) null);
        if (m1168a >= 0) {
            C2577al.m785c("[Database] insert %s success with ID: %d", "t_ui", Long.valueOf(m1168a));
            userInfoBean.f846a = m1168a;
        }
    }

    /* renamed from: a */
    public static List<UserInfoBean> m1095a(String str) {
        Cursor cursor;
        String str2;
        try {
            if (C2581ap.m844b(str)) {
                str2 = null;
            } else {
                str2 = "_pc = '" + str + "'";
            }
            cursor = C2635w.m1154a().m1169a("t_ui", (String[]) null, str2);
            if (cursor == null) {
                return null;
            }
            try {
                StringBuilder sb = new StringBuilder();
                ArrayList arrayList = new ArrayList();
                while (cursor.moveToNext()) {
                    UserInfoBean m1094a = m1094a(cursor);
                    if (m1094a != null) {
                        arrayList.add(m1094a);
                    } else {
                        try {
                            long j = cursor.getLong(cursor.getColumnIndex(FileDownloadModel.f718ID));
                            sb.append(" or _id = ");
                            sb.append(j);
                        } catch (Throwable unused) {
                            C2577al.m786d("[Database] unknown id.", new Object[0]);
                        }
                    }
                }
                String sb2 = sb.toString();
                if (sb2.length() > 0) {
                    C2577al.m786d("[Database] deleted %s error data %d", "t_ui", Integer.valueOf(C2635w.m1154a().m1167a("t_ui", sb2.substring(4))));
                }
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                try {
                    if (!C2577al.m782a(th)) {
                        th.printStackTrace();
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    /* renamed from: b */
    private static void m1106b(List<UserInfoBean> list) {
        if (list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size() && i < 50; i++) {
            UserInfoBean userInfoBean = list.get(i);
            sb.append(" or _id = ");
            sb.append(userInfoBean.f846a);
        }
        String sb2 = sb.toString();
        if (sb2.length() > 0) {
            sb2 = sb2.substring(4);
        }
        sb.setLength(0);
        try {
            C2577al.m785c("[Database] deleted %s data %d", "t_ui", Integer.valueOf(C2635w.m1154a().m1167a("t_ui", sb2)));
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    private static ContentValues m1105b(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.f846a > 0) {
                contentValues.put(FileDownloadModel.f718ID, Long.valueOf(userInfoBean.f846a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.f850e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f851f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.f847b));
            contentValues.put("_pc", userInfoBean.f848c);
            contentValues.put("_dt", C2581ap.m833a(userInfoBean));
            return contentValues;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private static UserInfoBean m1094a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex(FileDownloadModel.f718ID));
            UserInfoBean userInfoBean = (UserInfoBean) C2581ap.m815a(blob, UserInfoBean.CREATOR);
            if (userInfoBean != null) {
                userInfoBean.f846a = j;
            }
            return userInfoBean;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b A[Catch: all -> 0x0092, TRY_ENTER, TryCatch #0 {, blocks: (B:3:0x0001, B:10:0x002b, B:12:0x003e, B:14:0x004c, B:15:0x0061, B:17:0x0067, B:19:0x006c, B:22:0x0073, B:25:0x0089, B:29:0x005b, B:30:0x0009, B:33:0x0010, B:36:0x0017, B:38:0x001d), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0067 A[Catch: all -> 0x0092, TryCatch #0 {, blocks: (B:3:0x0001, B:10:0x002b, B:12:0x003e, B:14:0x004c, B:15:0x0061, B:17:0x0067, B:19:0x006c, B:22:0x0073, B:25:0x0089, B:29:0x005b, B:30:0x0009, B:33:0x0010, B:36:0x0017, B:38:0x001d), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029 A[DONT_GENERATE] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void m1102a(boolean r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.f1476d     // Catch: java.lang.Throwable -> L92
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L9
        L7:
            r0 = r2
            goto L27
        L9:
            com.tencent.bugly.proguard.ai r0 = com.tencent.bugly.proguard.C2574ai.m746a()     // Catch: java.lang.Throwable -> L92
            if (r0 != 0) goto L10
            goto L7
        L10:
            com.tencent.bugly.proguard.ac r3 = com.tencent.bugly.proguard.C2568ac.m710a()     // Catch: java.lang.Throwable -> L92
            if (r3 != 0) goto L17
            goto L7
        L17:
            boolean r3 = r3.m718b()     // Catch: java.lang.Throwable -> L92
            if (r3 == 0) goto L26
            r3 = 1001(0x3e9, float:1.403E-42)
            boolean r0 = r0.m763b(r3)     // Catch: java.lang.Throwable -> L92
            if (r0 != 0) goto L26
            goto L7
        L26:
            r0 = r1
        L27:
            if (r0 != 0) goto L2b
            monitor-exit(r7)
            return
        L2b:
            android.content.Context r0 = r7.f1473a     // Catch: java.lang.Throwable -> L92
            com.tencent.bugly.proguard.aa r0 = com.tencent.bugly.proguard.C2566aa.m646a(r0)     // Catch: java.lang.Throwable -> L92
            java.lang.String r0 = r0.f1028d     // Catch: java.lang.Throwable -> L92
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L92
            r3.<init>()     // Catch: java.lang.Throwable -> L92
            java.util.List r0 = m1095a(r0)     // Catch: java.lang.Throwable -> L92
            if (r0 == 0) goto L5b
            m1100a(r0, r3)     // Catch: java.lang.Throwable -> L92
            m1107b(r0, r3)     // Catch: java.lang.Throwable -> L92
            int r4 = m1093a(r0)     // Catch: java.lang.Throwable -> L92
            r5 = 15
            if (r4 <= r5) goto L60
            java.lang.String r5 = "[UserInfo] Upload user info too many times in 10 min: %d"
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L92
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L92
            r6[r2] = r4     // Catch: java.lang.Throwable -> L92
            com.tencent.bugly.proguard.C2577al.m786d(r5, r6)     // Catch: java.lang.Throwable -> L92
            r4 = r2
            goto L61
        L5b:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L92
            r0.<init>()     // Catch: java.lang.Throwable -> L92
        L60:
            r4 = r1
        L61:
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L92
            if (r5 <= 0) goto L6a
            m1106b(r3)     // Catch: java.lang.Throwable -> L92
        L6a:
            if (r4 == 0) goto L89
            int r3 = r0.size()     // Catch: java.lang.Throwable -> L92
            if (r3 != 0) goto L73
            goto L89
        L73:
            java.lang.String r3 = "[UserInfo] Upload user info(size: %d)"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L92
            int r4 = r0.size()     // Catch: java.lang.Throwable -> L92
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L92
            r1[r2] = r4     // Catch: java.lang.Throwable -> L92
            com.tencent.bugly.proguard.C2577al.m785c(r3, r1)     // Catch: java.lang.Throwable -> L92
            r7.m1101a(r0, r8)     // Catch: java.lang.Throwable -> L92
            monitor-exit(r7)
            return
        L89:
            java.lang.String r8 = "[UserInfo] There is no user info in local database."
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L92
            com.tencent.bugly.proguard.C2577al.m785c(r8, r0)     // Catch: java.lang.Throwable -> L92
            monitor-exit(r7)
            return
        L92:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2630r.m1102a(boolean):void");
    }

    /* renamed from: b */
    private boolean m1108b(boolean z) {
        boolean z2;
        boolean z3 = true;
        if (!f1472e) {
            return true;
        }
        File file = new File(this.f1473a.getFilesDir(), "bugly_last_us_up_tm");
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            C2578am.m791a(file, String.valueOf(currentTimeMillis), 1024L, false);
            return true;
        }
        if (!file.exists()) {
            C2578am.m791a(file, String.valueOf(currentTimeMillis), 1024L, false);
        } else {
            BufferedReader m813a = C2581ap.m813a(file);
            try {
                if (m813a != null) {
                    try {
                        long longValue = Long.valueOf(m813a.readLine().trim()).longValue();
                        if (currentTimeMillis >= longValue && currentTimeMillis - longValue <= 86400000) {
                            z2 = true;
                            if (z2 || currentTimeMillis - longValue >= 300000) {
                                C2578am.m791a(file, String.valueOf(currentTimeMillis), 1024L, false);
                            } else {
                                z3 = false;
                            }
                        }
                        z2 = false;
                        if (z2) {
                        }
                        C2578am.m791a(file, String.valueOf(currentTimeMillis), 1024L, false);
                    } catch (Throwable th) {
                        try {
                            C2577al.m784b(th);
                            C2578am.m791a(file, String.valueOf(currentTimeMillis), 1024L, false);
                            if (m813a != null) {
                                m813a.close();
                            }
                        } catch (Throwable th2) {
                            if (m813a != null) {
                                try {
                                    m813a.close();
                                } catch (Exception e) {
                                    C2577al.m782a(e);
                                }
                            }
                            throw th2;
                        }
                    }
                }
                if (m813a != null) {
                    m813a.close();
                }
            } catch (Exception e2) {
                C2577al.m782a(e2);
            }
        }
        return z3;
    }

    /* renamed from: a */
    static /* synthetic */ void m1096a(UserInfoBean userInfoBean) {
        C2566aa m648b;
        if (userInfoBean == null || (m648b = C2566aa.m648b()) == null) {
            return;
        }
        userInfoBean.f855j = m648b.m659d();
    }
}
