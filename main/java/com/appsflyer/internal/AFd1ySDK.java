package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class AFd1ySDK {
    public final String[] AFInAppEventType;

    public AFd1ySDK(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            this.AFInAppEventType = null;
            return;
        }
        Pattern compile = Pattern.compile("[\\w]{1,45}");
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null && compile.matcher(str).matches()) {
                arrayList.add(str.toLowerCase(Locale.getDefault()));
            } else {
                AFLogger.afWarnLog("Invalid partner name: ".concat(String.valueOf(str)));
            }
        }
        if (arrayList.contains(LanguageType.SERVER_FOLLOW_SYSTEM)) {
            this.AFInAppEventType = new String[]{LanguageType.SERVER_FOLLOW_SYSTEM};
        } else if (!arrayList.isEmpty()) {
            this.AFInAppEventType = (String[]) arrayList.toArray(new String[0]);
        } else {
            this.AFInAppEventType = null;
        }
    }
}
