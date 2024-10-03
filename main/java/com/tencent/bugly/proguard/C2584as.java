package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Pair;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C2572ag;
import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.as */
/* loaded from: classes3.dex */
public final class C2584as {

    /* renamed from: a */
    public static int f1170a;

    /* renamed from: h */
    private static final Map<Integer, Pair<String, String>> f1171h = new HashMap<Integer, Pair<String, String>>() { // from class: com.tencent.bugly.proguard.as.1
        {
            put(3, new Pair("203", "103"));
            put(7, new Pair("208", "108"));
            put(0, new Pair("200", "100"));
            put(1, new Pair("201", "101"));
            put(2, new Pair("202", "102"));
            put(4, new Pair("204", "104"));
            put(6, new Pair("206", "106"));
            put(5, new Pair("207", "107"));
        }
    };

    /* renamed from: i */
    private static final ArrayList<a> f1172i = new ArrayList<a>() { // from class: com.tencent.bugly.proguard.as.2
        {
            byte b2 = 0;
            add(new b(b2));
            add(new c(b2));
            add(new d(b2));
            add(new e(b2));
            add(new h(b2));
            add(new i(b2));
            add(new f(b2));
            add(new g(b2));
        }
    };

    /* renamed from: j */
    private static final Map<Integer, Integer> f1173j = new HashMap<Integer, Integer>() { // from class: com.tencent.bugly.proguard.as.3
        {
            put(3, 4);
            put(7, 7);
            put(2, 1);
            put(0, 0);
            put(1, 2);
            put(4, 3);
            put(5, 5);
            put(6, 6);
        }
    };

    /* renamed from: k */
    private static final Map<Integer, String> f1174k = new HashMap<Integer, String>() { // from class: com.tencent.bugly.proguard.as.4
        {
            put(3, "BuglyAnrCrash");
            put(0, "BuglyJavaCrash");
            put(1, "BuglyNativeCrash");
        }
    };

    /* renamed from: l */
    private static final Map<Integer, String> f1175l = new HashMap<Integer, String>() { // from class: com.tencent.bugly.proguard.as.5
        {
            put(3, "BuglyAnrCrashReport");
            put(0, "BuglyJavaCrashReport");
            put(1, "BuglyNativeCrashReport");
        }
    };

    /* renamed from: b */
    protected final Context f1176b;

    /* renamed from: c */
    protected final C2574ai f1177c;

    /* renamed from: d */
    protected final C2635w f1178d;

    /* renamed from: e */
    protected final C2568ac f1179e;

    /* renamed from: f */
    protected InterfaceC2588aw f1180f;

    /* renamed from: g */
    protected BuglyStrategy.C2551a f1181g;

    public C2584as(Context context, C2574ai c2574ai, C2635w c2635w, C2568ac c2568ac, BuglyStrategy.C2551a c2551a) {
        f1170a = 1004;
        this.f1176b = context;
        this.f1177c = c2574ai;
        this.f1178d = c2635w;
        this.f1179e = c2568ac;
        this.f1181g = c2551a;
        this.f1180f = null;
    }

    /* renamed from: a */
    private static List<C2583ar> m867a(List<C2583ar> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (C2583ar c2583ar : list) {
            if (c2583ar.f1167d && c2583ar.f1165b <= currentTimeMillis - 86400000) {
                arrayList.add(c2583ar);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static CrashDetailBean m862a(List<C2583ar> list, CrashDetailBean crashDetailBean) {
        CrashDetailBean crashDetailBean2;
        List<CrashDetailBean> m892c;
        if (list.isEmpty()) {
            return crashDetailBean;
        }
        ArrayList arrayList = new ArrayList(10);
        for (C2583ar c2583ar : list) {
            if (c2583ar.f1168e) {
                arrayList.add(c2583ar);
            }
        }
        if (arrayList.isEmpty() || (m892c = m892c(arrayList)) == null || m892c.isEmpty()) {
            crashDetailBean2 = null;
        } else {
            Collections.sort(m892c);
            crashDetailBean2 = m892c.get(0);
            m869a(crashDetailBean2, m892c);
        }
        if (crashDetailBean2 == null) {
            crashDetailBean.f927j = true;
            crashDetailBean.f937t = 0;
            crashDetailBean.f936s = "";
            crashDetailBean2 = crashDetailBean;
        }
        m885b(crashDetailBean2, list);
        if (crashDetailBean2.f935r != crashDetailBean.f935r) {
            String str = crashDetailBean2.f936s;
            StringBuilder sb = new StringBuilder();
            sb.append(crashDetailBean.f935r);
            if (!str.contains(sb.toString())) {
                crashDetailBean2.f937t++;
                crashDetailBean2.f936s += crashDetailBean.f935r + "\n";
            }
        }
        return crashDetailBean2;
    }

    /* renamed from: a */
    private static void m869a(CrashDetailBean crashDetailBean, List<CrashDetailBean> list) {
        String[] split;
        StringBuilder sb = new StringBuilder(128);
        for (int i2 = 1; i2 < list.size(); i2++) {
            CrashDetailBean crashDetailBean2 = list.get(i2);
            if (crashDetailBean2.f936s != null && (split = crashDetailBean2.f936s.split("\n")) != null) {
                for (String str : split) {
                    if (!crashDetailBean.f936s.contains(str)) {
                        crashDetailBean.f937t++;
                        sb.append(str);
                        sb.append("\n");
                    }
                }
            }
        }
        crashDetailBean.f936s += sb.toString();
    }

    /* renamed from: b */
    private static void m885b(CrashDetailBean crashDetailBean, List<C2583ar> list) {
        StringBuilder sb = new StringBuilder(64);
        for (C2583ar c2583ar : list) {
            if (!c2583ar.f1168e && !c2583ar.f1167d) {
                String str = crashDetailBean.f936s;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(c2583ar.f1165b);
                if (!str.contains(sb2.toString())) {
                    crashDetailBean.f937t++;
                    sb.append(c2583ar.f1165b);
                    sb.append("\n");
                }
            }
        }
        crashDetailBean.f936s += sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0244 A[RETURN] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean m900a(com.tencent.bugly.crashreport.crash.CrashDetailBean r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2584as.m900a(com.tencent.bugly.crashreport.crash.CrashDetailBean, boolean):boolean");
    }

    /* renamed from: a */
    private static boolean m882a(String str) {
        if (C2585at.f1204r != null && !C2585at.f1204r.isEmpty()) {
            try {
                C2577al.m785c("Crash regular filter for crash stack is: %s", C2585at.f1204r);
                if (Pattern.compile(C2585at.f1204r).matcher(str).find()) {
                    C2577al.m786d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                    return true;
                }
            } catch (Exception e2) {
                C2577al.m782a(e2);
                C2577al.m786d("Failed to compile " + C2585at.f1204r, new Object[0]);
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m881a(CrashDetailBean crashDetailBean, List<C2583ar> list, List<C2583ar> list2) {
        boolean z = false;
        for (C2583ar c2583ar : list) {
            if (crashDetailBean.f938u.equals(c2583ar.f1166c)) {
                if (c2583ar.f1168e) {
                    z = true;
                }
                list2.add(c2583ar);
            }
        }
        return z;
    }

    /* renamed from: a */
    public static List<CrashDetailBean> m866a() {
        StrategyBean m719c = C2568ac.m710a().m719c();
        if (m719c == null) {
            C2577al.m786d("have not synced remote!", new Object[0]);
            return null;
        }
        if (!m719c.f873f) {
            C2577al.m786d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            C2577al.m783b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long m836b = C2581ap.m836b();
        List<C2583ar> m884b = m884b();
        C2577al.m785c("Size of crash list loaded from DB: %s", Integer.valueOf(m884b.size()));
        if (m884b == null || m884b.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.addAll(m867a(m884b));
        m884b.removeAll(arrayList);
        Iterator<C2583ar> it = m884b.iterator();
        while (it.hasNext()) {
            C2583ar next = it.next();
            if (next.f1165b < m836b - C2585at.f1196j) {
                arrayList2.add(next);
                it.remove();
                arrayList.add(next);
            } else if (next.f1167d) {
                if (next.f1165b >= currentTimeMillis - 86400000) {
                    it.remove();
                } else if (!next.f1168e) {
                    it.remove();
                    arrayList.add(next);
                }
            } else if (next.f1169f >= 3 && next.f1165b < currentTimeMillis - 86400000) {
                it.remove();
                arrayList.add(next);
            }
        }
        m889b(arrayList2);
        if (arrayList.size() > 0) {
            m894d(arrayList);
        }
        ArrayList arrayList3 = new ArrayList();
        List<CrashDetailBean> m892c = m892c(m884b);
        if (m892c != null && m892c.size() > 0) {
            String str = C2566aa.m648b().f1039o;
            Iterator<CrashDetailBean> it2 = m892c.iterator();
            while (it2.hasNext()) {
                CrashDetailBean next2 = it2.next();
                if (!str.equals(next2.f923f)) {
                    it2.remove();
                    arrayList3.add(next2);
                }
            }
        }
        if (arrayList3.size() > 0) {
            m897e(arrayList3);
        }
        return m892c;
    }

    /* renamed from: b */
    private static void m889b(List<C2583ar> list) {
        List<CrashDetailBean> m892c = m892c(list);
        if (m892c == null || m892c.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (CrashDetailBean crashDetailBean : m892c) {
            String str = f1175l.get(Integer.valueOf(crashDetailBean.f919b));
            if (!TextUtils.isEmpty(str)) {
                C2577al.m785c("find expired data,crashId:%s eventType:%s", crashDetailBean.f920c, str);
                arrayList.add(new C2572ag.c(crashDetailBean.f920c, str, crashDetailBean.f935r, false, 0L, "expired", null));
            }
        }
        C2572ag.a.m744a().m742a(arrayList);
    }

    /* renamed from: a */
    public final void m898a(CrashDetailBean crashDetailBean) {
        int i2 = crashDetailBean.f919b;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 3 && !C2585at.m904a().m920k()) {
                    return;
                }
            } else if (!C2585at.m904a().m919j()) {
                return;
            }
        } else if (!C2585at.m904a().m919j()) {
            return;
        }
        if (this.f1180f != null) {
            C2577al.m785c("Calling 'onCrashHandleEnd' of RQD crash listener.", new Object[0]);
        }
    }

    /* renamed from: b */
    public final void m902b(CrashDetailBean crashDetailBean, boolean z) {
        if (C2585at.f1201o) {
            C2577al.m781a("try to upload right now", new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            m899a(arrayList, 3000L, z, crashDetailBean.f919b == 7, z);
            return;
        }
        C2577al.m781a("do not upload spot crash right now, crash would be uploaded when app next start", new Object[0]);
    }

    /* renamed from: a */
    public final void m899a(final List<CrashDetailBean> list, long j, final boolean z, boolean z2, boolean z3) {
        if (!C2566aa.m646a(this.f1176b).f1030f) {
            C2577al.m786d("warn: not upload process", new Object[0]);
            return;
        }
        C2574ai c2574ai = this.f1177c;
        if (c2574ai == null) {
            C2577al.m786d("warn: upload manager is null", new Object[0]);
            return;
        }
        if (!z3 && !c2574ai.m763b(C2585at.f1188a)) {
            C2577al.m786d("warn: not crashHappen or not should upload", new Object[0]);
            return;
        }
        StrategyBean m719c = this.f1179e.m719c();
        if (!m719c.f873f) {
            C2577al.m786d("remote report is disable!", new Object[0]);
            C2577al.m783b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
            return;
        }
        if (list == null || list.size() == 0) {
            C2577al.m786d("warn: crashList is null or crashList num is 0", new Object[0]);
            return;
        }
        try {
            String str = m719c.f885r;
            String str2 = StrategyBean.f869b;
            C2608bp m865a = m865a(this.f1176b, list, C2566aa.m648b());
            if (m865a == null) {
                C2577al.m786d("create eupPkg fail!", new Object[0]);
                return;
            }
            byte[] m728a = C2570ae.m728a((AbstractC2625m) m865a);
            if (m728a == null) {
                C2577al.m786d("send encode fail!", new Object[0]);
                return;
            }
            C2609bq m724a = C2570ae.m724a(this.f1176b, 830, m728a);
            if (m724a == null) {
                C2577al.m786d("request package is null.", new Object[0]);
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            InterfaceC2573ah interfaceC2573ah = new InterfaceC2573ah() { // from class: com.tencent.bugly.proguard.as.6
                @Override // com.tencent.bugly.proguard.InterfaceC2573ah
                /* renamed from: a */
                public final void mo745a(boolean z4, String str3) {
                    C2584as.m878a(list, z4, System.currentTimeMillis() - currentTimeMillis, z ? "realtime" : "cache", str3);
                    C2584as.m880a(z4, (List<CrashDetailBean>) list);
                }
            };
            if (z) {
                this.f1177c.m760a(f1170a, m724a, str, str2, interfaceC2573ah, j, z2);
            } else {
                this.f1177c.m761a(f1170a, m724a, str, str2, interfaceC2573ah, false);
            }
        } catch (Throwable th) {
            C2577al.m787e("req cr error %s", th.toString());
            if (C2577al.m784b(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m880a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            C2577al.m785c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean crashDetailBean : list) {
                C2577al.m785c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.f920c, Integer.valueOf(crashDetailBean.f929l), Boolean.valueOf(crashDetailBean.f921d), Boolean.valueOf(crashDetailBean.f927j));
                crashDetailBean.f929l++;
                crashDetailBean.f921d = z;
                C2577al.m785c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.f920c, Integer.valueOf(crashDetailBean.f929l), Boolean.valueOf(crashDetailBean.f921d), Boolean.valueOf(crashDetailBean.f927j));
            }
            Iterator<CrashDetailBean> it = list.iterator();
            while (it.hasNext()) {
                C2585at.m904a().m909a(it.next());
            }
            C2577al.m785c("update state size %d", Integer.valueOf(list.size()));
        }
        if (z) {
            return;
        }
        C2577al.m783b("[crash] upload fail.", new Object[0]);
    }

    /* renamed from: c */
    private static ContentValues m891c(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.f917a > 0) {
                contentValues.put(FileDownloadModel.f718ID, Long.valueOf(crashDetailBean.f917a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.f935r));
            contentValues.put("_s1", crashDetailBean.f938u);
            int i2 = 1;
            contentValues.put("_up", Integer.valueOf(crashDetailBean.f921d ? 1 : 0));
            if (!crashDetailBean.f927j) {
                i2 = 0;
            }
            contentValues.put("_me", Integer.valueOf(i2));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.f929l));
            contentValues.put("_dt", C2581ap.m833a(crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private static CrashDetailBean m861a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex(FileDownloadModel.f718ID));
            CrashDetailBean crashDetailBean = (CrashDetailBean) C2581ap.m815a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean != null) {
                crashDetailBean.f917a = j;
            }
            return crashDetailBean;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    public final void m901b(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return;
        }
        ContentValues m891c = m891c(crashDetailBean);
        if (m891c != null) {
            long m1168a = C2635w.m1154a().m1168a("t_cr", m891c, (InterfaceC2634v) null);
            if (m1168a >= 0) {
                C2577al.m785c("insert %s success!", "t_cr");
                crashDetailBean.f917a = m1168a;
            }
        }
        if (C2585at.f1198l) {
            m895d(crashDetailBean);
        }
    }

    /* renamed from: c */
    private static List<CrashDetailBean> m892c(List<C2583ar> list) {
        Cursor cursor;
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_id in (");
        Iterator<C2583ar> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().f1164a);
            sb.append(",");
        }
        if (sb.toString().contains(",")) {
            sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
        }
        sb.append(")");
        String sb2 = sb.toString();
        sb.setLength(0);
        try {
            cursor = C2635w.m1154a().m1169a("t_cr", (String[]) null, sb2);
            if (cursor == null) {
                return null;
            }
            try {
                ArrayList arrayList = new ArrayList();
                sb.append("_id in (");
                int i2 = 0;
                while (cursor.moveToNext()) {
                    CrashDetailBean m861a = m861a(cursor);
                    if (m861a != null) {
                        arrayList.add(m861a);
                    } else {
                        try {
                            sb.append(cursor.getLong(cursor.getColumnIndex(FileDownloadModel.f718ID)));
                            sb.append(",");
                            i2++;
                        } catch (Throwable unused) {
                            C2577al.m786d("unknown id!", new Object[0]);
                        }
                    }
                }
                if (sb.toString().contains(",")) {
                    sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
                }
                sb.append(")");
                String sb3 = sb.toString();
                if (i2 > 0) {
                    C2577al.m786d("deleted %s illegal data %d", "t_cr", Integer.valueOf(C2635w.m1154a().m1167a("t_cr", sb3)));
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
    private static C2583ar m883b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C2583ar c2583ar = new C2583ar();
            c2583ar.f1164a = cursor.getLong(cursor.getColumnIndex(FileDownloadModel.f718ID));
            c2583ar.f1165b = cursor.getLong(cursor.getColumnIndex("_tm"));
            c2583ar.f1166c = cursor.getString(cursor.getColumnIndex("_s1"));
            c2583ar.f1167d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            c2583ar.f1168e = cursor.getInt(cursor.getColumnIndex("_me")) == 1;
            c2583ar.f1169f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return c2583ar;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    private static List<C2583ar> m884b() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor m1169a = C2635w.m1154a().m1169a("t_cr", new String[]{FileDownloadModel.f718ID, "_tm", "_s1", "_up", "_me", "_uc"}, (String) null);
            if (m1169a == null) {
                if (m1169a != null) {
                    m1169a.close();
                }
                return null;
            }
            try {
                if (m1169a.getCount() <= 0) {
                    if (m1169a != null) {
                        m1169a.close();
                    }
                    return arrayList;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("_id in (");
                int i2 = 0;
                while (m1169a.moveToNext()) {
                    C2583ar m883b = m883b(m1169a);
                    if (m883b != null) {
                        arrayList.add(m883b);
                    } else {
                        try {
                            sb.append(m1169a.getLong(m1169a.getColumnIndex(FileDownloadModel.f718ID)));
                            sb.append(",");
                            i2++;
                        } catch (Throwable unused) {
                            C2577al.m786d("unknown id!", new Object[0]);
                        }
                    }
                }
                if (sb.toString().contains(",")) {
                    sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
                }
                sb.append(")");
                String sb2 = sb.toString();
                sb.setLength(0);
                if (i2 > 0) {
                    C2577al.m786d("deleted %s illegal data %d", "t_cr", Integer.valueOf(C2635w.m1154a().m1167a("t_cr", sb2)));
                }
                if (m1169a != null) {
                    m1169a.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursor = m1169a;
                try {
                    if (!C2577al.m782a(th)) {
                        th.printStackTrace();
                    }
                    return arrayList;
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: d */
    private static void m894d(List<C2583ar> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_id in (");
        Iterator<C2583ar> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().f1164a);
            sb.append(",");
        }
        StringBuilder sb2 = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
        sb2.append(")");
        String sb3 = sb2.toString();
        sb2.setLength(0);
        try {
            C2577al.m785c("deleted %s data %d", "t_cr", Integer.valueOf(C2635w.m1154a().m1167a("t_cr", sb3)));
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: e */
    private static void m897e(List<CrashDetailBean> list) {
        try {
            if (list.size() == 0) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (CrashDetailBean crashDetailBean : list) {
                sb.append(" or _id = ");
                sb.append(crashDetailBean.f917a);
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                sb2 = sb2.substring(4);
            }
            sb.setLength(0);
            C2577al.m785c("deleted %s data %d", "t_cr", Integer.valueOf(C2635w.m1154a().m1167a("t_cr", sb2)));
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private static C2607bo m864a(Context context, CrashDetailBean crashDetailBean, C2566aa c2566aa) {
        ArrayList<C2604bl> arrayList = null;
        if (context == null || crashDetailBean == null || c2566aa == null) {
            C2577al.m786d("enExp args == null", new Object[0]);
            return null;
        }
        C2607bo c2607bo = new C2607bo();
        c2607bo.f1337a = m896e(crashDetailBean);
        c2607bo.f1338b = crashDetailBean.f935r;
        c2607bo.f1339c = crashDetailBean.f931n;
        c2607bo.f1340d = crashDetailBean.f932o;
        c2607bo.f1341e = crashDetailBean.f933p;
        c2607bo.f1343g = crashDetailBean.f934q;
        c2607bo.f1344h = crashDetailBean.f943z;
        c2607bo.f1345i = crashDetailBean.f920c;
        c2607bo.f1346j = null;
        c2607bo.f1348l = crashDetailBean.f930m;
        c2607bo.f1349m = crashDetailBean.f922e;
        c2607bo.f1342f = crashDetailBean.f892B;
        c2607bo.f1350n = null;
        if (crashDetailBean.f925h != null && !crashDetailBean.f925h.isEmpty()) {
            arrayList = new ArrayList<>(crashDetailBean.f925h.size());
            for (Map.Entry<String, PlugInBean> entry : crashDetailBean.f925h.entrySet()) {
                C2604bl c2604bl = new C2604bl();
                c2604bl.f1317a = entry.getValue().f865a;
                c2604bl.f1319c = entry.getValue().f867c;
                c2604bl.f1321e = entry.getValue().f866b;
                arrayList.add(c2604bl);
            }
        }
        c2607bo.f1352p = arrayList;
        C2577al.m785c("libInfo %s", c2607bo.f1351o);
        ArrayList<C2606bn> arrayList2 = new ArrayList<>(20);
        m872a(arrayList2, crashDetailBean);
        m874a(arrayList2, crashDetailBean.f940w);
        m887b(arrayList2, crashDetailBean.f941x);
        m893c(arrayList2, crashDetailBean.f916Z);
        m875a(arrayList2, crashDetailBean.f918aa, context);
        m877a(arrayList2, crashDetailBean.f942y);
        m873a(arrayList2, crashDetailBean, context);
        m886b(arrayList2, crashDetailBean, context);
        m876a(arrayList2, c2566aa.f987L);
        m888b(arrayList2, crashDetailBean.f915Y);
        c2607bo.f1353q = arrayList2;
        if (crashDetailBean.f927j) {
            c2607bo.f1347k = crashDetailBean.f937t;
        }
        c2607bo.f1354r = m868a(crashDetailBean, c2566aa);
        c2607bo.f1355s = new HashMap();
        if (crashDetailBean.f909S != null && crashDetailBean.f909S.size() > 0) {
            c2607bo.f1355s.putAll(crashDetailBean.f909S);
            C2577al.m781a("setted message size %d", Integer.valueOf(c2607bo.f1355s.size()));
        }
        Map<String, String> map = c2607bo.f1355s;
        C2577al.m785c("pss:" + crashDetailBean.f899I + " vss:" + crashDetailBean.f900J + " javaHeap:" + crashDetailBean.f901K, new Object[0]);
        StringBuilder sb = new StringBuilder();
        sb.append(crashDetailBean.f899I);
        map.put("SDK_UPLOAD_U1", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(crashDetailBean.f900J);
        map.put("SDK_UPLOAD_U2", sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(crashDetailBean.f901K);
        map.put("SDK_UPLOAD_U3", sb3.toString());
        Object[] objArr = new Object[12];
        objArr[0] = crashDetailBean.f931n;
        objArr[1] = crashDetailBean.f920c;
        objArr[2] = c2566aa.m659d();
        objArr[3] = Long.valueOf((crashDetailBean.f935r - crashDetailBean.f907Q) / 1000);
        objArr[4] = Boolean.valueOf(crashDetailBean.f928k);
        objArr[5] = Boolean.valueOf(crashDetailBean.f908R);
        objArr[6] = Boolean.valueOf(crashDetailBean.f927j);
        objArr[7] = Boolean.valueOf(crashDetailBean.f919b == 1);
        objArr[8] = Integer.valueOf(crashDetailBean.f937t);
        objArr[9] = crashDetailBean.f936s;
        objArr[10] = Boolean.valueOf(crashDetailBean.f921d);
        objArr[11] = Integer.valueOf(c2607bo.f1354r.size());
        C2577al.m785c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", objArr);
        return c2607bo;
    }

    /* renamed from: a */
    private static C2608bp m865a(Context context, List<CrashDetailBean> list, C2566aa c2566aa) {
        if (context == null || list == null || list.size() == 0 || c2566aa == null) {
            C2577al.m786d("enEXPPkg args == null!", new Object[0]);
            return null;
        }
        C2608bp c2608bp = new C2608bp();
        c2608bp.f1359a = new ArrayList<>();
        Iterator<CrashDetailBean> it = list.iterator();
        while (it.hasNext()) {
            c2608bp.f1359a.add(m864a(context, it.next(), c2566aa));
        }
        return c2608bp;
    }

    /* renamed from: a */
    private static C2606bn m863a(String str, Context context, String str2) {
        FileInputStream fileInputStream;
        if (str2 == null || context == null) {
            C2577al.m786d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        C2577al.m785c("zip %s", str2);
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        if (!C2581ap.m831a(file, file2)) {
            C2577al.m786d("zip fail!", new Object[0]);
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            fileInputStream = new FileInputStream(file2);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
                byteArrayOutputStream.flush();
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            C2577al.m785c("read bytes :%d", Integer.valueOf(byteArray.length));
            C2606bn c2606bn = new C2606bn((byte) 2, file2.getName(), byteArray);
            try {
                fileInputStream.close();
            } catch (IOException e2) {
                if (!C2577al.m782a(e2)) {
                    e2.printStackTrace();
                }
            }
            if (file2.exists()) {
                C2577al.m785c("del tmp", new Object[0]);
                file2.delete();
            }
            return c2606bn;
        } catch (Throwable th2) {
            th = th2;
            try {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        if (!C2577al.m782a(e3)) {
                            e3.printStackTrace();
                        }
                    }
                }
                if (file2.exists()) {
                    C2577al.m785c("del tmp", new Object[0]);
                    file2.delete();
                }
                return null;
            } catch (Throwable th3) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        if (!C2577al.m782a(e4)) {
                            e4.printStackTrace();
                        }
                    }
                }
                if (file2.exists()) {
                    C2577al.m785c("del tmp", new Object[0]);
                    file2.delete();
                }
                throw th3;
            }
        }
    }

    /* renamed from: d */
    private boolean m895d(CrashDetailBean crashDetailBean) {
        String absolutePath;
        try {
            C2577al.m785c("save eup logs", new Object[0]);
            C2566aa m648b = C2566aa.m648b();
            String str = "#--------\npackage:" + m648b.m661e() + "\nversion:" + m648b.f1039o + "\nsdk:" + m648b.f1032h + "\nprocess:" + crashDetailBean.f891A + "\ndate:" + C2581ap.m823a(new Date(crashDetailBean.f935r)) + "\ntype:" + crashDetailBean.f931n + "\nmessage:" + crashDetailBean.f932o + "\nstack:\n" + crashDetailBean.f934q + "\neupID:" + crashDetailBean.f920c + "\n";
            if (C2585at.f1199m == null) {
                if (Environment.getExternalStorageState().equals("mounted")) {
                    absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tencent/" + this.f1176b.getPackageName();
                } else {
                    absolutePath = null;
                }
            } else {
                File file = new File(C2585at.f1199m);
                if (file.isFile()) {
                    file = file.getParentFile();
                }
                absolutePath = file.getAbsolutePath();
            }
            C2578am.m793a(absolutePath + "/euplog.txt", str, C2585at.f1200n);
            return true;
        } catch (Throwable th) {
            C2577al.m786d("rqdp{  save error} %s", th.toString());
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    /* renamed from: a */
    public static void m871a(String str, String str2, String str3, String str4, String str5, CrashDetailBean crashDetailBean) {
        String str6;
        C2566aa m648b = C2566aa.m648b();
        if (m648b == null) {
            return;
        }
        C2577al.m787e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
        C2577al.m787e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
        C2577al.m787e("# PKG NAME: %s", m648b.f1027c);
        C2577al.m787e("# APP VER: %s", m648b.f1039o);
        C2577al.m787e("# SDK VER: %s", m648b.f1032h);
        C2577al.m787e("# LAUNCH TIME: %s", C2581ap.m823a(new Date(C2566aa.m648b().f1001a)));
        C2577al.m787e("# CRASH TYPE: %s", str);
        C2577al.m787e("# CRASH TIME: %s", str2);
        C2577al.m787e("# CRASH PROCESS: %s", str3);
        C2577al.m787e("# CRASH FOREGROUND: %s", Boolean.valueOf(m648b.m654a()));
        C2577al.m787e("# CRASH THREAD: %s", str4);
        if (crashDetailBean != null) {
            C2577al.m787e("# REPORT ID: %s", crashDetailBean.f920c);
            Object[] objArr = new Object[2];
            objArr[0] = m648b.m667h();
            objArr[1] = m648b.m676r().booleanValue() ? "ROOTED" : "UNROOT";
            C2577al.m787e("# CRASH DEVICE: %s %s", objArr);
            C2577al.m787e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.f893C), Long.valueOf(crashDetailBean.f894D), Long.valueOf(crashDetailBean.f895E));
            C2577al.m787e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.f896F), Long.valueOf(crashDetailBean.f897G), Long.valueOf(crashDetailBean.f898H));
            if (!C2581ap.m844b(crashDetailBean.f905O)) {
                C2577al.m787e("# EXCEPTION FIRED BY %s %s", crashDetailBean.f905O, crashDetailBean.f904N);
            } else if (crashDetailBean.f919b == 3) {
                Object[] objArr2 = new Object[1];
                if (crashDetailBean.f910T == null) {
                    str6 = JsonSerializer.Null;
                } else {
                    str6 = crashDetailBean.f910T.get("BUGLY_CR_01");
                }
                objArr2[0] = str6;
                C2577al.m787e("# EXCEPTION ANR MESSAGE:\n %s", objArr2);
            }
        }
        if (!C2581ap.m844b(str5)) {
            C2577al.m787e("# CRASH STACK: ", new Object[0]);
            C2577al.m787e(str5, new Object[0]);
        }
        C2577al.m787e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
    }

    /* renamed from: a */
    private static void m870a(CrashDetailBean crashDetailBean, Map<String, String> map) {
        String value;
        if (map == null || map.isEmpty()) {
            C2577al.m786d("extra map is empty. CrashBean won't have userDatas.", new Object[0]);
            return;
        }
        crashDetailBean.f909S = new LinkedHashMap(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!C2581ap.m844b(entry.getKey())) {
                String key = entry.getKey();
                if (key.length() > 100) {
                    key = key.substring(0, 100);
                    C2577al.m786d("setted key length is over limit %d substring to %s", 100, key);
                }
                if (!C2581ap.m844b(entry.getValue()) && entry.getValue().length() > 100000) {
                    value = entry.getValue().substring(entry.getValue().length() - BuglyStrategy.C2551a.MAX_USERDATA_VALUE_LENGTH);
                    C2577al.m786d("setted %s value length is over limit %d substring", key, Integer.valueOf(BuglyStrategy.C2551a.MAX_USERDATA_VALUE_LENGTH));
                } else {
                    value = entry.getValue();
                }
                crashDetailBean.f909S.put(key, value);
                C2577al.m781a("add setted key %s value size:%d", key, Integer.valueOf(value.length()));
            }
        }
    }

    /* renamed from: e */
    private static String m896e(CrashDetailBean crashDetailBean) {
        try {
            Pair<String, String> pair = f1171h.get(Integer.valueOf(crashDetailBean.f919b));
            if (pair == null) {
                C2577al.m787e("crash type error! %d", Integer.valueOf(crashDetailBean.f919b));
                return "";
            }
            if (crashDetailBean.f927j) {
                return (String) pair.first;
            }
            return (String) pair.second;
        } catch (Exception e2) {
            C2577al.m782a(e2);
            return "";
        }
    }

    /* renamed from: a */
    private static void m872a(ArrayList<C2606bn> arrayList, CrashDetailBean crashDetailBean) {
        if (crashDetailBean.f927j && crashDetailBean.f936s != null && crashDetailBean.f936s.length() > 0) {
            try {
                arrayList.add(new C2606bn((byte) 1, "alltimes.txt", crashDetailBean.f936s.getBytes("utf-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
                C2577al.m782a(e2);
            }
        }
    }

    /* renamed from: a */
    private static void m874a(ArrayList<C2606bn> arrayList, String str) {
        if (str != null) {
            try {
                arrayList.add(new C2606bn((byte) 1, "log.txt", str.getBytes("utf-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
                C2577al.m782a(e2);
            }
        }
    }

    /* renamed from: b */
    private static void m887b(ArrayList<C2606bn> arrayList, String str) {
        if (str != null) {
            try {
                arrayList.add(new C2606bn((byte) 1, "jniLog.txt", str.getBytes("utf-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
                C2577al.m782a(e2);
            }
        }
    }

    /* renamed from: c */
    private static void m893c(ArrayList<C2606bn> arrayList, String str) {
        if (C2581ap.m844b(str)) {
            return;
        }
        try {
            C2606bn c2606bn = new C2606bn((byte) 1, "crashInfos.txt", str.getBytes("utf-8"));
            C2577al.m785c("attach crash infos", new Object[0]);
            arrayList.add(c2606bn);
        } catch (Exception e2) {
            e2.printStackTrace();
            C2577al.m782a(e2);
        }
    }

    /* renamed from: a */
    private static void m875a(ArrayList<C2606bn> arrayList, String str, Context context) {
        if (str != null) {
            try {
                C2606bn m863a = m863a("backupRecord.zip", context, str);
                if (m863a != null) {
                    C2577al.m785c("attach backup record", new Object[0]);
                    arrayList.add(m863a);
                }
            } catch (Exception e2) {
                C2577al.m782a(e2);
            }
        }
    }

    /* renamed from: a */
    private static void m877a(ArrayList<C2606bn> arrayList, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        try {
            C2606bn c2606bn = new C2606bn((byte) 2, "buglylog.zip", bArr);
            C2577al.m785c("attach user log", new Object[0]);
            arrayList.add(c2606bn);
        } catch (Exception e2) {
            C2577al.m782a(e2);
        }
    }

    /* renamed from: a */
    private static void m873a(ArrayList<C2606bn> arrayList, CrashDetailBean crashDetailBean, Context context) {
        C2606bn m863a;
        if (crashDetailBean.f919b != 3) {
            return;
        }
        C2577al.m785c("crashBean.anrMessages:%s", crashDetailBean.f910T);
        try {
            if (crashDetailBean.f910T != null && crashDetailBean.f910T.containsKey("BUGLY_CR_01")) {
                if (!TextUtils.isEmpty(crashDetailBean.f910T.get("BUGLY_CR_01"))) {
                    arrayList.add(new C2606bn((byte) 1, "anrMessage.txt", crashDetailBean.f910T.get("BUGLY_CR_01").getBytes("utf-8")));
                    C2577al.m785c("attach anr message", new Object[0]);
                }
                crashDetailBean.f910T.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.f939v == null || (m863a = m863a("trace.zip", context, crashDetailBean.f939v)) == null) {
                return;
            }
            C2577al.m785c("attach traces", new Object[0]);
            arrayList.add(m863a);
        } catch (Exception e2) {
            e2.printStackTrace();
            C2577al.m782a(e2);
        }
    }

    /* renamed from: b */
    private static void m886b(ArrayList<C2606bn> arrayList, CrashDetailBean crashDetailBean, Context context) {
        if (crashDetailBean.f919b == 1 && crashDetailBean.f939v != null) {
            try {
                C2606bn m863a = m863a("tomb.zip", context, crashDetailBean.f939v);
                if (m863a != null) {
                    C2577al.m785c("attach tombs", new Object[0]);
                    arrayList.add(m863a);
                }
            } catch (Exception e2) {
                C2577al.m782a(e2);
            }
        }
    }

    /* renamed from: a */
    private static void m876a(ArrayList<C2606bn> arrayList, List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        try {
            arrayList.add(new C2606bn((byte) 1, "martianlog.txt", sb.toString().getBytes("utf-8")));
            C2577al.m785c("attach pageTracingList", new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: b */
    private static void m888b(ArrayList<C2606bn> arrayList, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        try {
            arrayList.add(new C2606bn((byte) 1, "userExtraByteData", bArr));
            C2577al.m785c("attach extraData", new Object[0]);
        } catch (Exception e2) {
            C2577al.m782a(e2);
        }
    }

    /* renamed from: a */
    private static Map<String, String> m868a(CrashDetailBean crashDetailBean, C2566aa c2566aa) {
        HashMap hashMap = new HashMap(30);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(crashDetailBean.f893C);
            hashMap.put("A9", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.f894D);
            hashMap.put("A11", sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(crashDetailBean.f895E);
            hashMap.put("A10", sb3.toString());
            hashMap.put("A23", crashDetailBean.f923f);
            c2566aa.getClass();
            hashMap.put("A7", "");
            hashMap.put("A6", C2566aa.m649n());
            hashMap.put("A5", c2566aa.m672m());
            hashMap.put("A22", c2566aa.m665g());
            StringBuilder sb4 = new StringBuilder();
            sb4.append(crashDetailBean.f897G);
            hashMap.put("A2", sb4.toString());
            StringBuilder sb5 = new StringBuilder();
            sb5.append(crashDetailBean.f896F);
            hashMap.put("A1", sb5.toString());
            hashMap.put("A24", c2566aa.f1035k);
            StringBuilder sb6 = new StringBuilder();
            sb6.append(crashDetailBean.f898H);
            hashMap.put("A17", sb6.toString());
            hashMap.put("A25", c2566aa.m665g());
            hashMap.put("A15", c2566aa.m675q());
            StringBuilder sb7 = new StringBuilder();
            sb7.append(c2566aa.m676r());
            hashMap.put("A13", sb7.toString());
            hashMap.put("A34", crashDetailBean.f891A);
            if (c2566aa.f982G != null) {
                hashMap.put("productIdentify", c2566aa.f982G);
            }
            hashMap.put("A26", URLEncoder.encode(crashDetailBean.f902L, "utf-8"));
            boolean z = true;
            if (crashDetailBean.f919b == 1) {
                hashMap.put("A27", crashDetailBean.f905O);
                hashMap.put("A28", crashDetailBean.f904N);
                StringBuilder sb8 = new StringBuilder();
                sb8.append(crashDetailBean.f928k);
                hashMap.put("A29", sb8.toString());
            }
            hashMap.put("A30", crashDetailBean.f906P);
            StringBuilder sb9 = new StringBuilder();
            sb9.append(crashDetailBean.f907Q);
            hashMap.put("A18", sb9.toString());
            StringBuilder sb10 = new StringBuilder();
            if (crashDetailBean.f908R) {
                z = false;
            }
            sb10.append(z);
            hashMap.put("A36", sb10.toString());
            StringBuilder sb11 = new StringBuilder();
            sb11.append(c2566aa.f1050z);
            hashMap.put("F02", sb11.toString());
            StringBuilder sb12 = new StringBuilder();
            sb12.append(c2566aa.f976A);
            hashMap.put("F03", sb12.toString());
            hashMap.put("F04", c2566aa.m659d());
            StringBuilder sb13 = new StringBuilder();
            sb13.append(c2566aa.f977B);
            hashMap.put("F05", sb13.toString());
            hashMap.put("F06", c2566aa.f1049y);
            hashMap.put("F08", c2566aa.f980E);
            hashMap.put("F09", c2566aa.f981F);
            StringBuilder sb14 = new StringBuilder();
            sb14.append(c2566aa.f978C);
            hashMap.put("F10", sb14.toString());
            m879a(hashMap, crashDetailBean);
        } catch (Exception e2) {
            e2.printStackTrace();
            C2577al.m782a(e2);
        }
        return hashMap;
    }

    /* renamed from: a */
    private static void m879a(Map<String, String> map, CrashDetailBean crashDetailBean) {
        if (crashDetailBean.f911U >= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(crashDetailBean.f911U);
            map.put("C01", sb.toString());
        }
        if (crashDetailBean.f912V >= 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.f912V);
            map.put("C02", sb2.toString());
        }
        if (crashDetailBean.f913W != null && crashDetailBean.f913W.size() > 0) {
            for (Map.Entry<String, String> entry : crashDetailBean.f913W.entrySet()) {
                map.put("C03_" + entry.getKey(), entry.getValue());
            }
        }
        if (crashDetailBean.f914X == null || crashDetailBean.f914X.size() <= 0) {
            return;
        }
        for (Map.Entry<String, String> entry2 : crashDetailBean.f914X.entrySet()) {
            map.put("C04_" + entry2.getKey(), entry2.getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.as$a */
    /* loaded from: classes3.dex */
    public static abstract class a {

        /* renamed from: a */
        final int f1186a;

        /* renamed from: a */
        abstract boolean mo903a();

        /* synthetic */ a(int i, byte b) {
            this(i);
        }

        private a(int i) {
            this.f1186a = i;
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.as$b */
    /* loaded from: classes3.dex */
    static class b extends a {
        /* synthetic */ b(byte b) {
            this();
        }

        private b() {
            super(3, (byte) 0);
        }

        @Override // com.tencent.bugly.proguard.C2584as.a
        /* renamed from: a */
        final boolean mo903a() {
            return C2585at.m904a().m920k();
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.as$c */
    /* loaded from: classes3.dex */
    static class c extends a {
        @Override // com.tencent.bugly.proguard.C2584as.a
        /* renamed from: a */
        final boolean mo903a() {
            return true;
        }

        /* synthetic */ c(byte b) {
            this();
        }

        private c() {
            super(7, (byte) 0);
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.as$d */
    /* loaded from: classes3.dex */
    static class d extends a {
        @Override // com.tencent.bugly.proguard.C2584as.a
        /* renamed from: a */
        final boolean mo903a() {
            return true;
        }

        /* synthetic */ d(byte b) {
            this();
        }

        private d() {
            super(2, (byte) 0);
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.as$e */
    /* loaded from: classes3.dex */
    static class e extends a {
        /* synthetic */ e(byte b) {
            this();
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private e() {
            /*
                r1 = this;
                r0 = 0
                r1.<init>(r0, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2584as.e.<init>():void");
        }

        @Override // com.tencent.bugly.proguard.C2584as.a
        /* renamed from: a */
        final boolean mo903a() {
            return C2585at.m904a().m919j();
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.as$h */
    /* loaded from: classes3.dex */
    static class h extends a {
        /* synthetic */ h(byte b) {
            this();
        }

        private h() {
            super(1, (byte) 0);
        }

        @Override // com.tencent.bugly.proguard.C2584as.a
        /* renamed from: a */
        final boolean mo903a() {
            return C2585at.m904a().m919j();
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.as$i */
    /* loaded from: classes3.dex */
    static class i extends a {
        /* synthetic */ i(byte b) {
            this();
        }

        private i() {
            super(4, (byte) 0);
        }

        @Override // com.tencent.bugly.proguard.C2584as.a
        /* renamed from: a */
        final boolean mo903a() {
            return (C2585at.m904a().f1206B & 4) > 0;
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.as$f */
    /* loaded from: classes3.dex */
    static class f extends a {
        /* synthetic */ f(byte b) {
            this();
        }

        private f() {
            super(5, (byte) 0);
        }

        @Override // com.tencent.bugly.proguard.C2584as.a
        /* renamed from: a */
        final boolean mo903a() {
            return (C2585at.m904a().f1206B & 2) > 0;
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.as$g */
    /* loaded from: classes3.dex */
    static class g extends a {
        /* synthetic */ g(byte b) {
            this();
        }

        private g() {
            super(6, (byte) 0);
        }

        @Override // com.tencent.bugly.proguard.C2584as.a
        /* renamed from: a */
        final boolean mo903a() {
            return (C2585at.m904a().f1206B & 1) > 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0036, code lost:
    
        if (r0.size() >= com.tencent.bugly.proguard.C2585at.f1190d) goto L25;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m890b(com.tencent.bugly.crashreport.crash.CrashDetailBean r8, java.util.List<com.tencent.bugly.proguard.C2583ar> r9, java.util.List<com.tencent.bugly.proguard.C2583ar> r10) {
        /*
            r7 = this;
            int r0 = r8.f919b
            r1 = 1
            r2 = 0
            if (r0 == 0) goto Lb
            if (r0 != r1) goto L9
            goto Lb
        L9:
            r3 = r2
            goto Lc
        Lb:
            r3 = r1
        Lc:
            r4 = 3
            if (r0 != r4) goto L11
            r0 = r1
            goto L12
        L11:
            r0 = r2
        L12:
            boolean r4 = com.tencent.bugly.proguard.C2628p.f1469c
            if (r4 != 0) goto L1f
            if (r0 != 0) goto L1c
            if (r3 != 0) goto L1c
            r0 = r1
            goto L20
        L1c:
            boolean r0 = com.tencent.bugly.proguard.C2585at.f1191e
            goto L20
        L1f:
            r0 = r2
        L20:
            if (r0 != 0) goto L23
            return r2
        L23:
            java.util.ArrayList r0 = new java.util.ArrayList
            r3 = 10
            r0.<init>(r3)
            boolean r9 = m881a(r8, r9, r0)
            if (r9 != 0) goto L38
            int r9 = r0.size()     // Catch: java.lang.Exception -> L6d
            int r3 = com.tencent.bugly.proguard.C2585at.f1190d     // Catch: java.lang.Exception -> L6d
            if (r9 < r3) goto L78
        L38:
            java.lang.String r9 = "same crash occur too much do merged!"
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L6d
            com.tencent.bugly.proguard.C2577al.m781a(r9, r3)     // Catch: java.lang.Exception -> L6d
            com.tencent.bugly.crashreport.crash.CrashDetailBean r8 = m862a(r0, r8)     // Catch: java.lang.Exception -> L6d
            java.util.Iterator r9 = r0.iterator()     // Catch: java.lang.Exception -> L6d
        L47:
            boolean r0 = r9.hasNext()     // Catch: java.lang.Exception -> L6d
            if (r0 == 0) goto L5f
            java.lang.Object r0 = r9.next()     // Catch: java.lang.Exception -> L6d
            com.tencent.bugly.proguard.ar r0 = (com.tencent.bugly.proguard.C2583ar) r0     // Catch: java.lang.Exception -> L6d
            long r3 = r0.f1164a     // Catch: java.lang.Exception -> L6d
            long r5 = r8.f917a     // Catch: java.lang.Exception -> L6d
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 == 0) goto L47
            r10.add(r0)     // Catch: java.lang.Exception -> L6d
            goto L47
        L5f:
            r7.m901b(r8)     // Catch: java.lang.Exception -> L6d
            m894d(r10)     // Catch: java.lang.Exception -> L6d
            java.lang.String r8 = "[crash] save crash success. For this device crash many times, it will not upload crashes immediately"
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L6d
            com.tencent.bugly.proguard.C2577al.m783b(r8, r9)     // Catch: java.lang.Exception -> L6d
            return r1
        L6d:
            r8 = move-exception
            com.tencent.bugly.proguard.C2577al.m782a(r8)
            java.lang.String r8 = "Failed to merge crash."
            java.lang.Object[] r9 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.C2577al.m786d(r8, r9)
        L78:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2584as.m890b(com.tencent.bugly.crashreport.crash.CrashDetailBean, java.util.List, java.util.List):boolean");
    }

    /* renamed from: a */
    static /* synthetic */ void m878a(List list, boolean z, long j, String str, String str2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CrashDetailBean crashDetailBean = (CrashDetailBean) it.next();
            String str3 = f1175l.get(Integer.valueOf(crashDetailBean.f919b));
            if (!TextUtils.isEmpty(str3)) {
                arrayList.add(new C2572ag.c(crashDetailBean.f920c, str3, crashDetailBean.f935r, z, j, str, str2));
            }
        }
        C2572ag.a.m744a().m742a(arrayList);
    }
}
