package com.appsflyer.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFc1aSDK {
    public static Uri AFKeystoreWrapper(Intent intent) {
        if (intent == null) {
            return null;
        }
        AFi1aSDK aFi1aSDK = new AFi1aSDK(intent);
        Intrinsics.checkNotNullParameter("android.intent.extra.REFERRER", "");
        Uri uri = (Uri) ((Parcelable) aFi1aSDK.AFInAppEventType(new Function0<T>() { // from class: com.appsflyer.internal.AFi1aSDK.1
            private /* synthetic */ String $valueOf;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C07221(String str) {
                super(0);
                r2 = str;
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: values */
            public final Parcelable invoke() {
                return AFi1aSDK.this.AFInAppEventParameterName.getParcelableExtra(r2);
            }
        }, "Error while trying to read android.intent.extra.REFERRER extra from intent", null, true));
        if (uri != null) {
            return uri;
        }
        String AFKeystoreWrapper = aFi1aSDK.AFKeystoreWrapper("android.intent.extra.REFERRER_NAME");
        if (AFKeystoreWrapper != null) {
            return Uri.parse(AFKeystoreWrapper);
        }
        return null;
    }
}
