package com.appsflyer.internal;

import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.luck.picture.lib.config.PictureMimeType;
import kotlin.Metadata;

@Metadata(m1394d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0002X\u0007¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004j\u0002\b\u0005j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f"}, m1395d2 = {"Lcom/appsflyer/internal/AFe1rSDK;", "", "", "AFInAppEventType", "Ljava/lang/String;", "AFKeystoreWrapper", "p0", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "AFInAppEventParameterName", "values", "valueOf", "unregisterClient", "e", "d", "AFLogger"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public enum AFe1rSDK {
    TEXT("text/plain"),
    JSON("application/json"),
    OCTET_STREAM(OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE),
    XML("application/xml"),
    HTML("text/html"),
    FORM("application/x-www-form-urlencoded"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG(PictureMimeType.PNG_Q);


    /* renamed from: AFInAppEventType, reason: from kotlin metadata */
    public final String AFKeystoreWrapper;

    AFe1rSDK(String str) {
        this.AFKeystoreWrapper = str;
    }
}
