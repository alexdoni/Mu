package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.w */
/* loaded from: classes3.dex */
public final class C2635w {

    /* renamed from: a */
    public static boolean f1520a = false;

    /* renamed from: b */
    private static C2635w f1521b;

    /* renamed from: c */
    private static C2636x f1522c;

    private C2635w(Context context, List<AbstractC2627o> list) {
        f1522c = new C2636x(context, list);
    }

    /* renamed from: a */
    public static synchronized C2635w m1155a(Context context, List<AbstractC2627o> list) {
        C2635w c2635w;
        synchronized (C2635w.class) {
            if (f1521b == null) {
                f1521b = new C2635w(context, list);
            }
            c2635w = f1521b;
        }
        return c2635w;
    }

    /* renamed from: a */
    public static synchronized C2635w m1154a() {
        C2635w c2635w;
        synchronized (C2635w.class) {
            c2635w = f1521b;
        }
        return c2635w;
    }

    /* renamed from: a */
    public final Cursor m1169a(String str, String[] strArr, String str2) {
        return m1170a(str, strArr, str2, (String) null, (String) null);
    }

    /* renamed from: a */
    public final Cursor m1170a(String str, String[] strArr, String str2, String str3, String str4) {
        return m1153a(false, str, strArr, str2, null, null, null, str3, str4, null);
    }

    /* renamed from: a */
    public final int m1167a(String str, String str2) {
        return m1150a(str, str2, (String[]) null, (InterfaceC2634v) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0054, code lost:
    
        if (0 != 0) goto L17;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized long m1168a(java.lang.String r8, android.content.ContentValues r9, com.tencent.bugly.proguard.InterfaceC2634v r10) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = -1
            r2 = 0
            com.tencent.bugly.proguard.x r3 = com.tencent.bugly.proguard.C2635w.f1522c     // Catch: java.lang.Throwable -> L41
            android.database.sqlite.SQLiteDatabase r2 = r3.getWritableDatabase()     // Catch: java.lang.Throwable -> L41
            if (r2 == 0) goto L30
            if (r9 == 0) goto L30
            java.lang.String r3 = "_id"
            long r3 = r2.replace(r8, r3, r9)     // Catch: java.lang.Throwable -> L41
            r5 = 0
            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            r5 = 0
            r6 = 1
            if (r9 < 0) goto L26
            java.lang.String r9 = "[Database] insert %s success."
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L41
            r6[r5] = r8     // Catch: java.lang.Throwable -> L41
            com.tencent.bugly.proguard.C2577al.m785c(r9, r6)     // Catch: java.lang.Throwable -> L41
            goto L2f
        L26:
            java.lang.String r9 = "[Database] replace %s error."
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L41
            r6[r5] = r8     // Catch: java.lang.Throwable -> L41
            com.tencent.bugly.proguard.C2577al.m786d(r9, r6)     // Catch: java.lang.Throwable -> L41
        L2f:
            r0 = r3
        L30:
            if (r10 == 0) goto L35
            java.lang.Long.valueOf(r0)     // Catch: java.lang.Throwable -> L3f
        L35:
            boolean r8 = com.tencent.bugly.proguard.C2635w.f1520a     // Catch: java.lang.Throwable -> L3f
            if (r8 == 0) goto L57
            if (r2 == 0) goto L57
        L3b:
            r2.close()     // Catch: java.lang.Throwable -> L3f
            goto L57
        L3f:
            r8 = move-exception
            goto L69
        L41:
            r8 = move-exception
            boolean r9 = com.tencent.bugly.proguard.C2577al.m782a(r8)     // Catch: java.lang.Throwable -> L59
            if (r9 != 0) goto L4b
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L59
        L4b:
            if (r10 == 0) goto L50
            java.lang.Long.valueOf(r0)     // Catch: java.lang.Throwable -> L3f
        L50:
            boolean r8 = com.tencent.bugly.proguard.C2635w.f1520a     // Catch: java.lang.Throwable -> L3f
            if (r8 == 0) goto L57
            if (r2 == 0) goto L57
            goto L3b
        L57:
            monitor-exit(r7)
            return r0
        L59:
            r8 = move-exception
            if (r10 == 0) goto L5f
            java.lang.Long.valueOf(r0)     // Catch: java.lang.Throwable -> L3f
        L5f:
            boolean r9 = com.tencent.bugly.proguard.C2635w.f1520a     // Catch: java.lang.Throwable -> L3f
            if (r9 == 0) goto L68
            if (r2 == 0) goto L68
            r2.close()     // Catch: java.lang.Throwable -> L3f
        L68:
            throw r8     // Catch: java.lang.Throwable -> L3f
        L69:
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2635w.m1168a(java.lang.String, android.content.ContentValues, com.tencent.bugly.proguard.v):long");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized Cursor m1153a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, InterfaceC2634v interfaceC2634v) {
        Cursor cursor;
        cursor = null;
        try {
            SQLiteDatabase writableDatabase = f1522c.getWritableDatabase();
            if (writableDatabase != null) {
                cursor = writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
            }
        } finally {
            try {
                return cursor;
            } finally {
            }
        }
        return cursor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0033, code lost:
    
        if (r1 != null) goto L12;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int m1150a(java.lang.String r4, java.lang.String r5, java.lang.String[] r6, com.tencent.bugly.proguard.InterfaceC2634v r7) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            r1 = 0
            com.tencent.bugly.proguard.x r2 = com.tencent.bugly.proguard.C2635w.f1522c     // Catch: java.lang.Throwable -> L20
            android.database.sqlite.SQLiteDatabase r1 = r2.getWritableDatabase()     // Catch: java.lang.Throwable -> L20
            if (r1 == 0) goto Lf
            int r0 = r1.delete(r4, r5, r6)     // Catch: java.lang.Throwable -> L20
        Lf:
            if (r7 == 0) goto L14
            java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> L1e
        L14:
            boolean r4 = com.tencent.bugly.proguard.C2635w.f1520a     // Catch: java.lang.Throwable -> L1e
            if (r4 == 0) goto L36
            if (r1 == 0) goto L36
        L1a:
            r1.close()     // Catch: java.lang.Throwable -> L1e
            goto L36
        L1e:
            r4 = move-exception
            goto L48
        L20:
            r4 = move-exception
            boolean r5 = com.tencent.bugly.proguard.C2577al.m782a(r4)     // Catch: java.lang.Throwable -> L38
            if (r5 != 0) goto L2a
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L38
        L2a:
            if (r7 == 0) goto L2f
            java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> L1e
        L2f:
            boolean r4 = com.tencent.bugly.proguard.C2635w.f1520a     // Catch: java.lang.Throwable -> L1e
            if (r4 == 0) goto L36
            if (r1 == 0) goto L36
            goto L1a
        L36:
            monitor-exit(r3)
            return r0
        L38:
            r4 = move-exception
            if (r7 == 0) goto L3e
            java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> L1e
        L3e:
            boolean r5 = com.tencent.bugly.proguard.C2635w.f1520a     // Catch: java.lang.Throwable -> L1e
            if (r5 == 0) goto L47
            if (r1 == 0) goto L47
            r1.close()     // Catch: java.lang.Throwable -> L1e
        L47:
            throw r4     // Catch: java.lang.Throwable -> L1e
        L48:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2635w.m1150a(java.lang.String, java.lang.String, java.lang.String[], com.tencent.bugly.proguard.v):int");
    }

    /* renamed from: a */
    public final boolean m1174a(int i, String str, byte[] bArr, boolean z) {
        if (!z) {
            a aVar = new a();
            aVar.m1177a(i, str, bArr);
            C2576ak.m772a().m774a(aVar);
            return true;
        }
        return m1159a(i, str, bArr, (InterfaceC2634v) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m1159a(int i, String str, byte[] bArr, InterfaceC2634v interfaceC2634v) {
        try {
            C2637y c2637y = new C2637y();
            c2637y.f1545a = i;
            c2637y.f1550f = str;
            c2637y.f1549e = System.currentTimeMillis();
            c2637y.f1551g = bArr;
            boolean m1163b = m1163b(c2637y);
            if (interfaceC2634v == null) {
                return m1163b;
            }
            Boolean.valueOf(m1163b);
            return m1163b;
        } catch (Throwable th) {
            try {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
                return false;
            } finally {
                if (interfaceC2634v != null) {
                    Boolean bool = Boolean.FALSE;
                }
            }
        }
    }

    /* renamed from: a */
    public final Map<String, byte[]> m1172a(int i, InterfaceC2634v interfaceC2634v) {
        HashMap hashMap = null;
        try {
            List<C2637y> m1165c = m1165c(i);
            if (m1165c == null) {
                return null;
            }
            HashMap hashMap2 = new HashMap();
            try {
                for (C2637y c2637y : m1165c) {
                    byte[] bArr = c2637y.f1551g;
                    if (bArr != null) {
                        hashMap2.put(c2637y.f1550f, bArr);
                    }
                }
                return hashMap2;
            } catch (Throwable th) {
                th = th;
                hashMap = hashMap2;
                if (C2577al.m782a(th)) {
                    return hashMap;
                }
                th.printStackTrace();
                return hashMap;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    public final synchronized boolean m1175a(C2637y c2637y) {
        ContentValues m1164c;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = f1522c.getWritableDatabase();
            if (sQLiteDatabase == null || (m1164c = m1164c(c2637y)) == null) {
                if (f1520a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return false;
            }
            long replace = sQLiteDatabase.replace("t_lr", FileDownloadModel.f718ID, m1164c);
            if (replace >= 0) {
                C2577al.m785c("[Database] insert %s success.", "t_lr");
                c2637y.f1545a = replace;
                return true;
            }
            if (f1520a && sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            return false;
        } catch (Throwable th) {
            try {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
                if (f1520a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return false;
            } finally {
                if (f1520a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            }
        }
    }

    /* renamed from: b */
    private synchronized boolean m1163b(C2637y c2637y) {
        ContentValues m1166d;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = f1522c.getWritableDatabase();
            if (sQLiteDatabase == null || (m1166d = m1166d(c2637y)) == null) {
                if (f1520a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return false;
            }
            long replace = sQLiteDatabase.replace("t_pf", FileDownloadModel.f718ID, m1166d);
            if (replace >= 0) {
                C2577al.m785c("[Database] insert %s success.", "t_pf");
                c2637y.f1545a = replace;
                return true;
            }
            if (f1520a && sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            return false;
        } catch (Throwable th) {
            try {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
                if (f1520a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return false;
            } finally {
                if (f1520a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            }
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b3 A[Catch: all -> 0x00c5, TRY_LEAVE, TryCatch #0 {all -> 0x00c5, blocks: (B:51:0x00ad, B:53:0x00b3), top: B:50:0x00ad, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00b8 A[Catch: all -> 0x00d7, TRY_ENTER, TryCatch #1 {, blocks: (B:3:0x0001, B:11:0x002d, B:12:0x0030, B:15:0x0036, B:41:0x009b, B:42:0x009e, B:45:0x00a4, B:56:0x00b8, B:57:0x00bb, B:60:0x00c1, B:63:0x00c8, B:64:0x00cb, B:67:0x00d1, B:68:0x00d4, B:51:0x00ad, B:53:0x00b3), top: B:2:0x0001, inners: #0 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized java.util.List<com.tencent.bugly.proguard.C2637y> m1171a(int r12) {
        /*
            Method dump skipped, instructions count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2635w.m1171a(int):java.util.List");
    }

    /* renamed from: a */
    public final synchronized void m1173a(List<C2637y> list) {
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase writableDatabase = f1522c.getWritableDatabase();
                if (writableDatabase != null) {
                    StringBuilder sb = new StringBuilder();
                    for (C2637y c2637y : list) {
                        sb.append(" or _id = ");
                        sb.append(c2637y.f1545a);
                    }
                    String sb2 = sb.toString();
                    if (sb2.length() > 0) {
                        sb2 = sb2.substring(4);
                    }
                    sb.setLength(0);
                    try {
                        C2577al.m785c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", sb2, null)));
                    } catch (Throwable th) {
                        try {
                            if (!C2577al.m782a(th)) {
                                th.printStackTrace();
                            }
                            if (f1520a) {
                                writableDatabase.close();
                            }
                        } finally {
                            if (f1520a) {
                                writableDatabase.close();
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public final synchronized void m1176b(int i) {
        String concat;
        SQLiteDatabase writableDatabase = f1522c.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    concat = "_tp = ".concat(String.valueOf(i));
                } catch (Throwable th) {
                    try {
                        if (!C2577al.m782a(th)) {
                            th.printStackTrace();
                        }
                        if (f1520a && writableDatabase != null) {
                            writableDatabase.close();
                            return;
                        }
                    } finally {
                        if (f1520a && writableDatabase != null) {
                            writableDatabase.close();
                        }
                    }
                }
            } else {
                concat = null;
            }
            C2577al.m785c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", concat, null)));
        }
    }

    /* renamed from: c */
    private static ContentValues m1164c(C2637y c2637y) {
        if (c2637y == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (c2637y.f1545a > 0) {
                contentValues.put(FileDownloadModel.f718ID, Long.valueOf(c2637y.f1545a));
            }
            contentValues.put("_tp", Integer.valueOf(c2637y.f1546b));
            contentValues.put("_pc", c2637y.f1547c);
            contentValues.put("_th", c2637y.f1548d);
            contentValues.put("_tm", Long.valueOf(c2637y.f1549e));
            if (c2637y.f1551g != null) {
                contentValues.put("_dt", c2637y.f1551g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private static C2637y m1156a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C2637y c2637y = new C2637y();
            c2637y.f1545a = cursor.getLong(cursor.getColumnIndex(FileDownloadModel.f718ID));
            c2637y.f1546b = cursor.getInt(cursor.getColumnIndex("_tp"));
            c2637y.f1547c = cursor.getString(cursor.getColumnIndex("_pc"));
            c2637y.f1548d = cursor.getString(cursor.getColumnIndex("_th"));
            c2637y.f1549e = cursor.getLong(cursor.getColumnIndex("_tm"));
            c2637y.f1551g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return c2637y;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00cb, code lost:
    
        if (r1 != null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x00ae, code lost:
    
        if (r1 != null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x00b0, code lost:
    
        r1.close();
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized java.util.List<com.tencent.bugly.proguard.C2637y> m1165c(int r12) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2635w.m1165c(int):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized boolean m1158a(int i, String str, InterfaceC2634v interfaceC2634v) {
        boolean z;
        String str2;
        z = false;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase writableDatabase = f1522c.getWritableDatabase();
            if (writableDatabase != null) {
                try {
                    if (C2581ap.m844b(str)) {
                        str2 = "_id = ".concat(String.valueOf(i));
                    } else {
                        str2 = "_id = " + i + " and _tp = \"" + str + "\"";
                    }
                    int delete = writableDatabase.delete("t_pf", str2, null);
                    C2577al.m785c("[Database] deleted %s data %d", "t_pf", Integer.valueOf(delete));
                    if (delete > 0) {
                        z = true;
                    }
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabase = writableDatabase;
                    try {
                        if (!C2577al.m782a(th)) {
                            th.printStackTrace();
                        }
                        return z;
                    } finally {
                        if (interfaceC2634v != null) {
                            Boolean bool = Boolean.FALSE;
                        }
                        if (f1520a && sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                    }
                }
            }
            if (interfaceC2634v != null) {
                Boolean.valueOf(z);
            }
            if (f1520a && writableDatabase != null) {
                writableDatabase.close();
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return z;
    }

    /* renamed from: d */
    private static ContentValues m1166d(C2637y c2637y) {
        if (c2637y != null && !C2581ap.m844b(c2637y.f1550f)) {
            try {
                ContentValues contentValues = new ContentValues();
                if (c2637y.f1545a > 0) {
                    contentValues.put(FileDownloadModel.f718ID, Long.valueOf(c2637y.f1545a));
                }
                contentValues.put("_tp", c2637y.f1550f);
                contentValues.put("_tm", Long.valueOf(c2637y.f1549e));
                if (c2637y.f1551g != null) {
                    contentValues.put("_dt", c2637y.f1551g);
                }
                return contentValues;
            } catch (Throwable th) {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: b */
    private static C2637y m1162b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C2637y c2637y = new C2637y();
            c2637y.f1545a = cursor.getLong(cursor.getColumnIndex(FileDownloadModel.f718ID));
            c2637y.f1549e = cursor.getLong(cursor.getColumnIndex("_tm"));
            c2637y.f1550f = cursor.getString(cursor.getColumnIndex("_tp"));
            c2637y.f1551g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return c2637y;
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.w$a */
    /* loaded from: classes3.dex */
    public class a extends Thread {

        /* renamed from: b */
        private int f1524b = 4;

        /* renamed from: c */
        private InterfaceC2634v f1525c = null;

        /* renamed from: d */
        private String f1526d;

        /* renamed from: e */
        private ContentValues f1527e;

        /* renamed from: f */
        private boolean f1528f;

        /* renamed from: g */
        private String[] f1529g;

        /* renamed from: h */
        private String f1530h;

        /* renamed from: i */
        private String[] f1531i;

        /* renamed from: j */
        private String f1532j;

        /* renamed from: k */
        private String f1533k;

        /* renamed from: l */
        private String f1534l;

        /* renamed from: m */
        private String f1535m;

        /* renamed from: n */
        private String f1536n;

        /* renamed from: o */
        private String[] f1537o;

        /* renamed from: p */
        private int f1538p;

        /* renamed from: q */
        private String f1539q;

        /* renamed from: r */
        private byte[] f1540r;

        public a() {
        }

        /* renamed from: a */
        public final void m1177a(int i, String str, byte[] bArr) {
            this.f1538p = i;
            this.f1539q = str;
            this.f1540r = bArr;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            switch (this.f1524b) {
                case 1:
                    C2635w.this.m1168a(this.f1526d, this.f1527e, this.f1525c);
                    return;
                case 2:
                    C2635w.this.m1150a(this.f1526d, this.f1536n, this.f1537o, this.f1525c);
                    return;
                case 3:
                    Cursor m1153a = C2635w.this.m1153a(this.f1528f, this.f1526d, this.f1529g, this.f1530h, this.f1531i, this.f1532j, this.f1533k, this.f1534l, this.f1535m, this.f1525c);
                    if (m1153a != null) {
                        m1153a.close();
                        return;
                    }
                    return;
                case 4:
                    C2635w.this.m1159a(this.f1538p, this.f1539q, this.f1540r, this.f1525c);
                    return;
                case 5:
                    C2635w.this.m1172a(this.f1538p, this.f1525c);
                    return;
                case 6:
                    C2635w.this.m1158a(this.f1538p, this.f1539q, this.f1525c);
                    return;
                default:
                    return;
            }
        }
    }
}
