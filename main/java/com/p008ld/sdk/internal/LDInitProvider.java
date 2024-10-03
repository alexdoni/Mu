package com.p008ld.sdk.internal;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.util.LDLog;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDInitProvider.kt */
/* loaded from: classes2.dex */
public final class LDInitProvider extends ContentProvider {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "LDInitProvider";

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return 0;
    }

    /* compiled from: LDInitProvider.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        try {
            if (LDSdk.getAutoInit()) {
                Context context = getContext();
                if (context == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                LDSdk.sdkInitialized(context);
                return false;
            }
            LDLog.m573e(TAG + " - 请开发者自行调用LDSdk.sdkInitialized(context)完成sdk初始化");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            LDLog.m573e(TAG + " - Failed to auto initialize the LD SDK : " + e);
            return false;
        }
    }
}
