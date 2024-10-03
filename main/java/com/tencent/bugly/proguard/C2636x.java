package com.tencent.bugly.proguard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.x */
/* loaded from: classes3.dex */
public final class C2636x extends SQLiteOpenHelper {

    /* renamed from: a */
    public static String f1541a = "bugly_db";

    /* renamed from: b */
    public static int f1542b = 16;

    /* renamed from: c */
    protected Context f1543c;

    /* renamed from: d */
    private List<AbstractC2627o> f1544d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2636x(Context context, List<AbstractC2627o> list) {
        super(context, f1541a + "_", (SQLiteDatabase.CursorFactory) null, f1542b);
        C2566aa.m646a(context).getClass();
        this.f1543c = context;
        this.f1544d = list;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_ui ( _id INTEGER PRIMARY KEY , _tm int , _ut int , _tp int , _dt blob , _pc text ) ");
            C2577al.m785c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_lr ( _id INTEGER PRIMARY KEY , _tp int , _tm int , _pc text , _th text , _dt blob ) ");
            C2577al.m785c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_pf ( _id integer , _tp text , _tm int , _dt blob,primary key(_id,_tp )) ");
            C2577al.m785c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_cr ( _id INTEGER PRIMARY KEY , _tm int , _s1 text , _up int , _me int , _uc int , _dt blob ) ");
            C2577al.m785c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS dl_1002 (_id integer primary key autoincrement, _dUrl varchar(100), _sFile varchar(100), _sLen INTEGER, _tLen INTEGER, _MD5 varchar(100), _DLTIME INTEGER)");
            C2577al.m785c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append("CREATE TABLE IF NOT EXISTS ge_1002 (_id integer primary key autoincrement, _time INTEGER, _datas blob)");
            C2577al.m785c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS st_1002 ( _id integer , _tp text , _tm int , _dt blob,primary key(_id,_tp )) ");
            C2577al.m785c(sb.toString(), new Object[0]);
            sQLiteDatabase.execSQL(sb.toString(), new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_sla ( _id TEXT NOT NULL , _tm INTEGER NOT NULL , _dt TEXT NOT NULL , PRIMARY KEY(_id) ) ");
            String sb2 = sb.toString();
            C2577al.m785c(sb2, new Object[0]);
            sQLiteDatabase.execSQL(sb2, new String[0]);
        } catch (Throwable th) {
            if (!C2577al.m784b(th)) {
                th.printStackTrace();
            }
        }
        List<AbstractC2627o> list = this.f1544d;
        if (list == null) {
            return;
        }
        Iterator<AbstractC2627o> it = list.iterator();
        while (it.hasNext()) {
            try {
                it.next().onDbCreate(sQLiteDatabase);
            } catch (Throwable th2) {
                if (!C2577al.m784b(th2)) {
                    th2.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    private synchronized boolean m1178a(SQLiteDatabase sQLiteDatabase) {
        try {
            String[] strArr = {"t_lr", "t_ui", "t_pf"};
            for (int i = 0; i < 3; i++) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ".concat(String.valueOf(strArr[i])), new String[0]);
            }
        } catch (Throwable th) {
            if (!C2577al.m784b(th)) {
                th.printStackTrace();
            }
            return false;
        }
        return true;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final synchronized void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        C2577al.m786d("[Database] Upgrade %d to %d , drop tables!", Integer.valueOf(i), Integer.valueOf(i2));
        List<AbstractC2627o> list = this.f1544d;
        if (list != null) {
            Iterator<AbstractC2627o> it = list.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onDbUpgrade(sQLiteDatabase, i, i2);
                } catch (Throwable th) {
                    if (!C2577al.m784b(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
        if (m1178a(sQLiteDatabase)) {
            onCreate(sQLiteDatabase);
            return;
        }
        C2577al.m786d("[Database] Failed to drop, delete db.", new Object[0]);
        File databasePath = this.f1543c.getDatabasePath(f1541a);
        if (databasePath != null && databasePath.canWrite()) {
            databasePath.delete();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final synchronized void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (C2567ab.m689c() >= 11) {
            C2577al.m786d("[Database] Downgrade %d to %d drop tables.", Integer.valueOf(i), Integer.valueOf(i2));
            List<AbstractC2627o> list = this.f1544d;
            if (list != null) {
                Iterator<AbstractC2627o> it = list.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onDbDowngrade(sQLiteDatabase, i, i2);
                    } catch (Throwable th) {
                        if (!C2577al.m784b(th)) {
                            th.printStackTrace();
                        }
                    }
                }
            }
            if (m1178a(sQLiteDatabase)) {
                onCreate(sQLiteDatabase);
                return;
            }
            C2577al.m786d("[Database] Failed to drop, delete db.", new Object[0]);
            File databasePath = this.f1543c.getDatabasePath(f1541a);
            if (databasePath != null && databasePath.canWrite()) {
                databasePath.delete();
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        sQLiteDatabase = null;
        int i = 0;
        while (sQLiteDatabase == null && i < 5) {
            i++;
            try {
                sQLiteDatabase = super.getReadableDatabase();
            } catch (Throwable unused) {
                C2577al.m786d("[Database] Try to get db(count: %d).", Integer.valueOf(i));
                if (i == 5) {
                    C2577al.m787e("[Database] Failed to get db.", new Object[0]);
                }
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return sQLiteDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        sQLiteDatabase = null;
        int i = 0;
        while (sQLiteDatabase == null && i < 5) {
            i++;
            try {
                sQLiteDatabase = super.getWritableDatabase();
            } catch (Throwable unused) {
                C2577al.m786d("[Database] Try to get db(count: %d).", Integer.valueOf(i));
                if (i == 5) {
                    C2577al.m787e("[Database] Failed to get db.", new Object[0]);
                }
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (sQLiteDatabase == null) {
            C2577al.m786d("[Database] db error delay error record 1min.", new Object[0]);
        }
        return sQLiteDatabase;
    }
}
