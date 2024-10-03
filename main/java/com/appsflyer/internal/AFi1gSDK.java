package com.appsflyer.internal;

import android.content.Context;
import android.database.Cursor;
import java.util.Map;

/* loaded from: classes.dex */
public final class AFi1gSDK extends AFi1tSDK {
    private final AFd1mSDK AFInAppEventType;

    public AFi1gSDK(Runnable runnable, AFd1mSDK aFd1mSDK) {
        super("store", "samsung", runnable);
        this.AFInAppEventType = aFd1mSDK;
    }

    @Override // com.appsflyer.internal.AFi1jSDK
    public final void valueOf(Context context) {
        AFKeystoreWrapper(this.AFInAppEventType.values(), new AFc1bSDK<Map<String, Object>>(context, this.AFInAppEventType, "com.sec.android.app.samsungapps.referrer", "FBA3AF4E7757D9016E953FB3EE4671CA2BD9AF725F9A53D52ED4A38EAAA08901") { // from class: com.appsflyer.internal.AFi1gSDK.2
            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Code restructure failed: missing block: B:15:0x00bd, code lost:
            
                if (r2 != null) goto L24;
             */
            /* JADX WARN: Code restructure failed: missing block: B:16:0x00d8, code lost:
            
                r0 = ((android.content.pm.PackageItemInfo) r10.AFInAppEventType.getPackageManager().resolveContentProvider(r10.values, 128)).packageName;
                r10.AFInAppEventParameterName.valueOf.put("api_ver", java.lang.Long.valueOf(com.appsflyer.internal.AFb1uSDK.values(r10.AFInAppEventType, r0)));
                r10.AFInAppEventParameterName.valueOf.put("api_ver_name", com.appsflyer.internal.AFb1uSDK.AFKeystoreWrapper(r10.AFInAppEventType, r0));
                r10.AFInAppEventParameterName.AFKeystoreWrapper();
             */
            /* JADX WARN: Code restructure failed: missing block: B:17:0x0113, code lost:
            
                return r10.AFInAppEventParameterName.valueOf;
             */
            /* JADX WARN: Code restructure failed: missing block: B:19:0x00d5, code lost:
            
                r2.close();
             */
            /* JADX WARN: Code restructure failed: missing block: B:28:0x00d3, code lost:
            
                if (0 == 0) goto L25;
             */
            @Override // com.appsflyer.internal.AFc1bSDK
            /* renamed from: AFInAppEventType, reason: merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.Map<java.lang.String, java.lang.Object> AFInAppEventParameterName() {
                /*
                    Method dump skipped, instructions count: 282
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFi1gSDK.C07282.AFInAppEventParameterName():java.util.Map");
            }

            private static void AFKeystoreWrapper(String str, Map<String, Object> map, Cursor cursor) {
                int columnIndex = cursor.getColumnIndex(str);
                if (columnIndex == -1) {
                    return;
                }
                long j = cursor.getLong(columnIndex);
                if (j == 0) {
                    return;
                }
                map.put(str, Long.valueOf(j));
            }

            private static void values(String str, Map<String, Object> map, Cursor cursor) {
                String string;
                int columnIndex = cursor.getColumnIndex(str);
                if (columnIndex == -1 || (string = cursor.getString(columnIndex)) == null) {
                    return;
                }
                map.put(str, string);
            }
        });
    }
}
