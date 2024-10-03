package com.appsflyer.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.facebook.internal.AttributionIdentifiers;

/* loaded from: classes.dex */
final class AFb1jSDK extends AFc1bSDK<String> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AFb1jSDK(Context context, AFd1mSDK aFd1mSDK) {
        super(context, aFd1mSDK, AttributionIdentifiers.ATTRIBUTION_ID_CONTENT_PROVIDER, "E3F9E1E0CF99D0E56A055BA65E241B3399F7CEA524326B0CDD6EC1327ED0FDC1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.appsflyer.internal.AFc1bSDK
    /* renamed from: values, reason: merged with bridge method [inline-methods] */
    public String AFInAppEventParameterName() {
        Cursor cursor;
        Throwable th;
        try {
            ContentResolver contentResolver = this.AFInAppEventType.getContentResolver();
            StringBuilder sb = new StringBuilder("content://");
            sb.append(this.values);
            cursor = contentResolver.query(Uri.parse(sb.toString()), new String[]{"aid"}, null, null, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("aid"));
                        if (cursor != null) {
                            cursor.close();
                        }
                        return string;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            cursor = null;
            th = th3;
        }
    }

    public final String AFInAppEventType() {
        this.AFKeystoreWrapper.AFInAppEventParameterName().execute(this.valueOf);
        return (String) super.valueOf();
    }

    @Override // com.appsflyer.internal.AFc1bSDK
    public final /* synthetic */ String valueOf() {
        this.AFKeystoreWrapper.AFInAppEventParameterName().execute(this.valueOf);
        return (String) super.valueOf();
    }
}
