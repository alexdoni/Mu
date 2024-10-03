package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ag */
/* loaded from: classes3.dex */
public final class C2572ag {

    /* renamed from: a */
    private final SimpleDateFormat f1067a;

    /* renamed from: b */
    private final C2569ad f1068b;

    /* synthetic */ C2572ag(byte b2) {
        this();
    }

    private C2572ag() {
        this.f1067a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.US);
        this.f1068b = new C2569ad();
    }

    /* renamed from: a */
    public final void m742a(List<c> list) {
        if (list == null || list.isEmpty()) {
            C2577al.m786d("sla batch report event is null", new Object[0]);
            return;
        }
        C2577al.m785c("sla batch report event size:%s", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList();
        Iterator<c> it = list.iterator();
        while (it.hasNext()) {
            b m737b = m737b(it.next());
            if (m737b != null) {
                arrayList.add(m737b);
            }
        }
        m740e(arrayList);
        m743b(arrayList);
    }

    /* renamed from: b */
    public final void m743b(final List<b> list) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            C2576ak.m772a().m774a(new Runnable() { // from class: com.tencent.bugly.proguard.ag.1
                @Override // java.lang.Runnable
                public final void run() {
                    C2572ag.m738c(list);
                }
            });
        } else {
            m738c(list);
        }
    }

    /* renamed from: c */
    static void m738c(List<b> list) {
        if (list == null || list.isEmpty()) {
            C2577al.m785c("sla batch report data is empty", new Object[0]);
            return;
        }
        C2577al.m785c("sla batch report list size:%s", Integer.valueOf(list.size()));
        if (list.size() > 30) {
            list = list.subList(0, 29);
        }
        ArrayList arrayList = new ArrayList();
        Iterator<b> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().f1074c);
        }
        Pair<Integer, String> m721a = C2569ad.m721a(arrayList);
        C2577al.m785c("sla batch report result, rspCode:%s rspMsg:%s", m721a.first, m721a.second);
        if (((Integer) m721a.first).intValue() == 200) {
            m739d(list);
        }
    }

    /* renamed from: e */
    private static void m740e(List<b> list) {
        for (b bVar : list) {
            C2577al.m785c("sla save id:%s time:%s msg:%s", bVar.f1072a, Long.valueOf(bVar.f1073b), bVar.f1074c);
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FileDownloadModel.f718ID, bVar.f1072a);
                contentValues.put("_tm", Long.valueOf(bVar.f1073b));
                contentValues.put("_dt", bVar.f1074c);
                C2635w.m1154a().m1168a("t_sla", contentValues, (InterfaceC2634v) null);
            } catch (Throwable th) {
                C2577al.m784b(th);
            }
        }
    }

    /* renamed from: d */
    public static void m739d(List<b> list) {
        if (list == null || list.isEmpty()) {
            C2577al.m785c("sla batch delete list is null", new Object[0]);
            return;
        }
        C2577al.m785c("sla batch delete list size:%s", Integer.valueOf(list.size()));
        try {
            String str = "_id in (" + m735a(",", list) + ")";
            C2577al.m785c("sla batch delete where:%s", str);
            C2635w.m1154a().m1167a("t_sla", str);
        } catch (Throwable th) {
            C2577al.m784b(th);
        }
    }

    /* renamed from: a */
    private static String m735a(String str, Iterable<b> iterable) {
        Iterator<b> it = iterable.iterator();
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("'");
        sb.append(it.next().f1072a);
        sb.append("'");
        while (it.hasNext()) {
            sb.append(str);
            sb.append("'");
            sb.append(it.next().f1072a);
            sb.append("'");
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static List<b> m736a() {
        Cursor m1170a = C2635w.m1154a().m1170a("t_sla", new String[]{FileDownloadModel.f718ID, "_tm", "_dt"}, (String) null, "_tm", "30");
        if (m1170a == null) {
            return null;
        }
        if (m1170a.getCount() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (m1170a.moveToNext()) {
            try {
                b bVar = new b();
                bVar.f1072a = m1170a.getString(m1170a.getColumnIndex(FileDownloadModel.f718ID));
                bVar.f1073b = m1170a.getLong(m1170a.getColumnIndex("_tm"));
                bVar.f1074c = m1170a.getString(m1170a.getColumnIndex("_dt"));
                C2577al.m785c(bVar.toString(), new Object[0]);
                arrayList.add(bVar);
            } finally {
                try {
                    return arrayList;
                } finally {
                }
            }
        }
        return arrayList;
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.ag$c */
    /* loaded from: classes3.dex */
    public static class c {

        /* renamed from: a */
        String f1075a;

        /* renamed from: b */
        String f1076b;

        /* renamed from: c */
        long f1077c;

        /* renamed from: d */
        boolean f1078d;

        /* renamed from: e */
        long f1079e;

        /* renamed from: f */
        String f1080f;

        /* renamed from: g */
        String f1081g;

        public c(String str, String str2, long j, boolean z, long j2, String str3, String str4) {
            this.f1075a = str;
            this.f1076b = str2;
            this.f1077c = j;
            this.f1078d = z;
            this.f1079e = j2;
            this.f1080f = str3;
            this.f1081g = str4;
        }

        public c() {
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.ag$b */
    /* loaded from: classes3.dex */
    public static class b {

        /* renamed from: a */
        String f1072a;

        /* renamed from: b */
        public long f1073b;

        /* renamed from: c */
        public String f1074c;

        public final String toString() {
            return "SLAData{uuid='" + this.f1072a + "', time=" + this.f1073b + ", data='" + this.f1074c + "'}";
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.ag$a */
    /* loaded from: classes3.dex */
    public static class a {

        /* renamed from: a */
        private static final C2572ag f1071a = new C2572ag(0);

        /* renamed from: a */
        public static /* synthetic */ C2572ag m744a() {
            return f1071a;
        }
    }

    /* renamed from: a */
    public final void m741a(c cVar) {
        if (TextUtils.isEmpty(cVar.f1076b)) {
            C2577al.m786d("sla report event is null", new Object[0]);
        } else {
            C2577al.m785c("sla report single event", new Object[0]);
            m742a(Collections.singletonList(cVar));
        }
    }

    /* renamed from: b */
    private b m737b(c cVar) {
        if (cVar == null || TextUtils.isEmpty(cVar.f1076b)) {
            C2577al.m786d("sla convert event is null", new Object[0]);
            return null;
        }
        C2566aa m648b = C2566aa.m648b();
        if (m648b == null) {
            C2577al.m786d("sla convert failed because ComInfoManager is null", new Object[0]);
            return null;
        }
        StringBuilder sb = new StringBuilder("&app_version=");
        sb.append(m648b.f1039o);
        sb.append("&app_name=");
        sb.append(m648b.f1041q);
        sb.append("&app_bundle_id=");
        sb.append(m648b.f1027c);
        sb.append("&client_type=android&user_id=");
        sb.append(m648b.m663f());
        sb.append("&sdk_version=");
        sb.append(m648b.f1032h);
        sb.append("&event_code=");
        sb.append(cVar.f1076b);
        sb.append("&event_result=");
        sb.append(cVar.f1078d ? 1 : 0);
        sb.append("&event_time=");
        sb.append(this.f1067a.format(new Date(cVar.f1077c)));
        sb.append("&event_cost=");
        sb.append(cVar.f1079e);
        sb.append("&device_id=");
        sb.append(m648b.m665g());
        sb.append("&debug=");
        sb.append(m648b.f979D ? 1 : 0);
        sb.append("&param_0=");
        sb.append(cVar.f1080f);
        sb.append("&param_1=");
        sb.append(cVar.f1075a);
        sb.append("&param_2=");
        sb.append(m648b.f988M ? "rqd" : "ext");
        sb.append("&param_4=");
        sb.append(m648b.m661e());
        String sb2 = sb.toString();
        if (!TextUtils.isEmpty(cVar.f1081g)) {
            sb2 = sb2 + "&param_3=" + cVar.f1081g;
        }
        C2577al.m785c("sla convert eventId:%s eventType:%s, eventTime:%s success:%s cost:%s from:%s uploadMsg:", cVar.f1075a, cVar.f1076b, Long.valueOf(cVar.f1077c), Boolean.valueOf(cVar.f1078d), Long.valueOf(cVar.f1079e), cVar.f1080f, cVar.f1081g);
        String str = cVar.f1075a + "-" + cVar.f1076b;
        b bVar = new b();
        bVar.f1072a = str;
        bVar.f1073b = cVar.f1077c;
        bVar.f1074c = sb2;
        return bVar;
    }
}
