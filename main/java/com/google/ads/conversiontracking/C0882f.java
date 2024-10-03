package com.google.ads.conversiontracking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;
import java.util.Locale;

/* renamed from: com.google.ads.conversiontracking.f */
/* loaded from: classes.dex */
public class C0882f {

    /* renamed from: a */
    private static final String f440a = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT, %s INTEGER, %s INTEGER, %s TEXT, %s INTEGER, %s INTEGER,%s INTEGER);", "conversiontracking", "conversion_ping_id", "string_url", "preference_key", "is_repeatable", "parameter_is_null", "preference_name", "record_time", "retry_count", "last_retry_time");

    /* renamed from: b */
    private final a f441b;

    /* renamed from: c */
    private final Object f442c = new Object();

    public C0882f(Context context) {
        this.f441b = new a(context, "google_conversion_tracking.db");
    }

    /* renamed from: a */
    public void m155a(C0880d c0880d) {
        if (c0880d == null) {
            return;
        }
        synchronized (this.f442c) {
            SQLiteDatabase m152a = m152a();
            if (m152a == null) {
                return;
            }
            m152a.delete("conversiontracking", String.format(Locale.US, "%s = %d", "conversion_ping_id", Long.valueOf(c0880d.f428h)), null);
        }
    }

    /* renamed from: a */
    public SQLiteDatabase m152a() {
        try {
            return this.f441b.getWritableDatabase();
        } catch (SQLiteException unused) {
            Log.w("GoogleConversionReporter", "Error opening writable conversion tracking database");
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002e, code lost:
    
        if (r11.moveToFirst() != false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
    
        r1.add(m153a(r11));
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
    
        if (r11.moveToNext() != false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0069, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0065, code lost:
    
        if (r11 == null) goto L30;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.google.ads.conversiontracking.C0880d> m154a(long r13) {
        /*
            r12 = this;
            java.lang.Object r0 = r12.f442c
            monitor-enter(r0)
            java.util.LinkedList r1 = new java.util.LinkedList     // Catch: java.lang.Throwable -> L70
            r1.<init>()     // Catch: java.lang.Throwable -> L70
            r2 = 0
            int r2 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r2 > 0) goto L10
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L70
            return r1
        L10:
            android.database.sqlite.SQLiteDatabase r2 = r12.m152a()     // Catch: java.lang.Throwable -> L70
            if (r2 != 0) goto L18
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L70
            return r1
        L18:
            r11 = 0
            java.lang.String r9 = "last_retry_time ASC"
            java.lang.String r3 = "conversiontracking"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r10 = java.lang.String.valueOf(r13)     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            boolean r13 = r11.moveToFirst()     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            if (r13 == 0) goto L3d
        L30:
            com.google.ads.conversiontracking.d r13 = r12.m153a(r11)     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            r1.add(r13)     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            boolean r13 = r11.moveToNext()     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            if (r13 != 0) goto L30
        L3d:
            if (r11 == 0) goto L68
        L3f:
            r11.close()     // Catch: java.lang.Throwable -> L70
            goto L68
        L43:
            r13 = move-exception
            goto L6a
        L45:
            r13 = move-exception
            java.lang.String r14 = "GoogleConversionReporter"
            java.lang.String r2 = "Error extracing ping Info: "
            java.lang.String r13 = r13.getMessage()     // Catch: java.lang.Throwable -> L43
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch: java.lang.Throwable -> L43
            int r3 = r13.length()     // Catch: java.lang.Throwable -> L43
            if (r3 == 0) goto L5d
            java.lang.String r13 = r2.concat(r13)     // Catch: java.lang.Throwable -> L43
            goto L62
        L5d:
            java.lang.String r13 = new java.lang.String     // Catch: java.lang.Throwable -> L43
            r13.<init>(r2)     // Catch: java.lang.Throwable -> L43
        L62:
            android.util.Log.w(r14, r13)     // Catch: java.lang.Throwable -> L43
            if (r11 == 0) goto L68
            goto L3f
        L68:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L70
            return r1
        L6a:
            if (r11 == 0) goto L6f
            r11.close()     // Catch: java.lang.Throwable -> L70
        L6f:
            throw r13     // Catch: java.lang.Throwable -> L70
        L70:
            r13 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L70
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.conversiontracking.C0882f.m154a(long):java.util.List");
    }

    /* renamed from: b */
    public void m157b(C0880d c0880d) {
        if (c0880d == null) {
            return;
        }
        synchronized (this.f442c) {
            SQLiteDatabase m152a = m152a();
            if (m152a == null) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("string_url", c0880d.f427g);
            contentValues.put("preference_key", c0880d.f426f);
            int i = 1;
            contentValues.put("is_repeatable", Integer.valueOf(c0880d.f422b ? 1 : 0));
            if (!c0880d.f421a) {
                i = 0;
            }
            contentValues.put("parameter_is_null", Integer.valueOf(i));
            contentValues.put("preference_name", c0880d.f425e);
            contentValues.put("record_time", Long.valueOf(c0880d.f424d));
            contentValues.put("retry_count", (Integer) 0);
            contentValues.put("last_retry_time", Long.valueOf(c0880d.f424d));
            c0880d.f428h = m152a.insert("conversiontracking", null, contentValues);
            m156b();
            if (m158c() > 20000) {
                m160d();
            }
        }
    }

    /* renamed from: b */
    public void m156b() {
        synchronized (this.f442c) {
            SQLiteDatabase m152a = m152a();
            if (m152a == null) {
                return;
            }
            m152a.delete("conversiontracking", String.format(Locale.US, "(%s > %d) or (%s < %d and %s > 0)", "retry_count", 9000L, "record_time", Long.valueOf(C0883g.m161a() - 43200000), "retry_count"), null);
        }
    }

    /* renamed from: c */
    public void m159c(C0880d c0880d) {
        if (c0880d == null) {
            return;
        }
        synchronized (this.f442c) {
            SQLiteDatabase m152a = m152a();
            if (m152a == null) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("last_retry_time", Long.valueOf(C0883g.m161a()));
            contentValues.put("retry_count", Integer.valueOf(c0880d.f423c + 1));
            m152a.update("conversiontracking", contentValues, String.format(Locale.US, "%s = %d", "conversion_ping_id", Long.valueOf(c0880d.f428h)), null);
            m156b();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0024, code lost:
    
        if (r3 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0026, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0050, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x004c, code lost:
    
        if (r3 == null) goto L29;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int m158c() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.f442c
            monitor-enter(r0)
            android.database.sqlite.SQLiteDatabase r1 = r7.m152a()     // Catch: java.lang.Throwable -> L57
            r2 = 0
            if (r1 != 0) goto Lc
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L57
            return r2
        Lc:
            r3 = 0
            java.lang.String r4 = "select count(*) from conversiontracking"
            android.database.Cursor r3 = r1.rawQuery(r4, r3)     // Catch: java.lang.Throwable -> L2a android.database.sqlite.SQLiteException -> L2c
            boolean r1 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L2a android.database.sqlite.SQLiteException -> L2c
            if (r1 == 0) goto L24
            int r1 = r3.getInt(r2)     // Catch: java.lang.Throwable -> L2a android.database.sqlite.SQLiteException -> L2c
            if (r3 == 0) goto L22
            r3.close()     // Catch: java.lang.Throwable -> L57
        L22:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L57
            return r1
        L24:
            if (r3 == 0) goto L4f
        L26:
            r3.close()     // Catch: java.lang.Throwable -> L57
            goto L4f
        L2a:
            r1 = move-exception
            goto L51
        L2c:
            r1 = move-exception
            java.lang.String r4 = "GoogleConversionReporter"
            java.lang.String r5 = "Error getting record count"
            java.lang.String r1 = r1.getMessage()     // Catch: java.lang.Throwable -> L2a
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L2a
            int r6 = r1.length()     // Catch: java.lang.Throwable -> L2a
            if (r6 == 0) goto L44
            java.lang.String r1 = r5.concat(r1)     // Catch: java.lang.Throwable -> L2a
            goto L49
        L44:
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> L2a
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L2a
        L49:
            android.util.Log.w(r4, r1)     // Catch: java.lang.Throwable -> L2a
            if (r3 == 0) goto L4f
            goto L26
        L4f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L57
            return r2
        L51:
            if (r3 == 0) goto L56
            r3.close()     // Catch: java.lang.Throwable -> L57
        L56:
            throw r1     // Catch: java.lang.Throwable -> L57
        L57:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L57
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.conversiontracking.C0882f.m158c():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0056, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0052, code lost:
    
        if (r10 == null) goto L27;
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m160d() {
        /*
            r11 = this;
            java.lang.Object r0 = r11.f442c
            monitor-enter(r0)
            android.database.sqlite.SQLiteDatabase r1 = r11.m152a()     // Catch: java.lang.Throwable -> L5d
            if (r1 != 0) goto Lb
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5d
            return
        Lb:
            r10 = 0
            java.lang.String r8 = "record_time ASC"
            java.lang.String r2 = "conversiontracking"
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.String r9 = "1"
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L30 android.database.sqlite.SQLiteException -> L32
            if (r10 == 0) goto L2a
            boolean r1 = r10.moveToFirst()     // Catch: java.lang.Throwable -> L30 android.database.sqlite.SQLiteException -> L32
            if (r1 == 0) goto L2a
            com.google.ads.conversiontracking.d r1 = r11.m153a(r10)     // Catch: java.lang.Throwable -> L30 android.database.sqlite.SQLiteException -> L32
            r11.m155a(r1)     // Catch: java.lang.Throwable -> L30 android.database.sqlite.SQLiteException -> L32
        L2a:
            if (r10 == 0) goto L55
        L2c:
            r10.close()     // Catch: java.lang.Throwable -> L5d
            goto L55
        L30:
            r1 = move-exception
            goto L57
        L32:
            r1 = move-exception
            java.lang.String r2 = "GoogleConversionReporter"
            java.lang.String r3 = "Error remove oldest record"
            java.lang.String r1 = r1.getMessage()     // Catch: java.lang.Throwable -> L30
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L30
            int r4 = r1.length()     // Catch: java.lang.Throwable -> L30
            if (r4 == 0) goto L4a
            java.lang.String r1 = r3.concat(r1)     // Catch: java.lang.Throwable -> L30
            goto L4f
        L4a:
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> L30
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L30
        L4f:
            android.util.Log.w(r2, r1)     // Catch: java.lang.Throwable -> L30
            if (r10 == 0) goto L55
            goto L2c
        L55:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5d
            return
        L57:
            if (r10 == 0) goto L5c
            r10.close()     // Catch: java.lang.Throwable -> L5d
        L5c:
            throw r1     // Catch: java.lang.Throwable -> L5d
        L5d:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5d
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.conversiontracking.C0882f.m160d():void");
    }

    /* renamed from: a */
    public C0880d m153a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        int i = cursor.getInt(7);
        String string = cursor.getString(1);
        if (i > 0) {
            string = Uri.parse(string).buildUpon().appendQueryParameter("retry", Integer.toString(i)).build().toString();
        }
        return new C0880d(cursor.getLong(0), string, cursor.getString(2), cursor.getInt(3) > 0, cursor.getInt(4) > 0, cursor.getString(5), cursor.getLong(6), i);
    }

    /* renamed from: com.google.ads.conversiontracking.f$a */
    /* loaded from: classes.dex */
    public class a extends SQLiteOpenHelper {
        public a(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 5);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(C0882f.f440a);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            StringBuilder sb = new StringBuilder(64);
            sb.append("Database updated from version ");
            sb.append(i);
            sb.append(" to version ");
            sb.append(i2);
            Log.i("GoogleConversionReporter", sb.toString());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS conversiontracking");
            onCreate(sQLiteDatabase);
        }
    }
}
