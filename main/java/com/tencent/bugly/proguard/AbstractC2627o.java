package com.tencent.bugly.proguard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.o */
/* loaded from: classes3.dex */
public abstract class AbstractC2627o {

    /* renamed from: id */
    public int f1466id;
    public String moduleName;
    public String version;
    public String versionKey;

    public abstract String[] getTables();

    public abstract void init(Context context, boolean z, BuglyStrategy buglyStrategy);

    public void onDbCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onServerStrategyChanged(StrategyBean strategyBean) {
    }

    public void onDbUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            if (getTables() == null) {
                return;
            }
            for (String str : getTables()) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ".concat(String.valueOf(str)));
            }
            onDbCreate(sQLiteDatabase);
        } catch (Throwable th) {
            if (C2577al.m784b(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    public void onDbDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            if (getTables() == null) {
                return;
            }
            for (String str : getTables()) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ".concat(String.valueOf(str)));
            }
            onDbCreate(sQLiteDatabase);
        } catch (Throwable th) {
            if (C2577al.m784b(th)) {
                return;
            }
            th.printStackTrace();
        }
    }
}
