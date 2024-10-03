package com.appsflyer.internal;

import android.content.Context;
import java.util.Map;

/* loaded from: classes.dex */
public final class AFi1oSDK extends AFi1tSDK {
    private final AFd1mSDK AFInAppEventType;

    public AFi1oSDK(Runnable runnable, AFd1mSDK aFd1mSDK) {
        super("store", "huawei", runnable);
        this.AFInAppEventType = aFd1mSDK;
    }

    @Override // com.appsflyer.internal.AFi1jSDK
    public final void valueOf(Context context) {
        AFKeystoreWrapper(this.AFInAppEventType.values(), new AFc1bSDK<Map<String, Object>>(context, this.AFInAppEventType, "com.huawei.appmarket.commondata", "FFE391E0EA186D0734ED601E4E70E3224B7309D48E2075BAC46D8C667EAE7212", "3BAF59A2E5331C30675FAB35FF5FFF0D116142D3D4664F1C3CB804068B40614F") { // from class: com.appsflyer.internal.AFi1oSDK.3
            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Code restructure failed: missing block: B:14:0x00f8, code lost:
            
                if (r4 != null) goto L22;
             */
            /* JADX WARN: Code restructure failed: missing block: B:15:0x0111, code lost:
            
                r12.AFInAppEventParameterName.AFKeystoreWrapper();
             */
            /* JADX WARN: Code restructure failed: missing block: B:16:0x011a, code lost:
            
                return r12.AFInAppEventParameterName.valueOf;
             */
            /* JADX WARN: Code restructure failed: missing block: B:18:0x010e, code lost:
            
                r4.close();
             */
            /* JADX WARN: Code restructure failed: missing block: B:23:0x010c, code lost:
            
                if (0 == 0) goto L23;
             */
            @Override // com.appsflyer.internal.AFc1bSDK
            /* renamed from: values, reason: merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.Map<java.lang.String, java.lang.Object> AFInAppEventParameterName() {
                /*
                    Method dump skipped, instructions count: 289
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFi1oSDK.C07323.AFInAppEventParameterName():java.util.Map");
            }
        });
    }
}
