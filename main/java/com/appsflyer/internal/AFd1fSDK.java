package com.appsflyer.internal;

import android.util.Base64;
import com.facebook.appevents.UserDataStore;
import com.google.firebase.messaging.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONObject;

@Metadata(m1394d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB)\u0012\u0006\u0010\u0002\u001a\u00020\f\u0012\u0006\u0010\u0015\u001a\u00020\f\u0012\u0006\u0010\u0016\u001a\u00020\f\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0006¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH×\u0001¢\u0006\u0004\b\r\u0010\u000eR\u0012\u0010\n\u001a\u00020\u0006X\u0086\u0002¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0011\u0010\u000f\u001a\u00020\fX\u0007¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\fX\u0007¢\u0006\u0006\n\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0011\u001a\u00020\fX\u0007¢\u0006\u0006\n\u0004\b\u0014\u0010\u0012"}, m1395d2 = {"Lcom/appsflyer/internal/AFd1fSDK;", "", "p0", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Lorg/json/JSONObject;", "values", "()Lorg/json/JSONObject;", "", "toString", "()Ljava/lang/String;", "AFInAppEventParameterName", "I", "valueOf", "Ljava/lang/String;", "AFInAppEventType", "AFKeystoreWrapper", "p1", "p2", "p3", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "AFa1uSDK"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final /* data */ class AFd1fSDK {

    /* renamed from: AFa1uSDK, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: AFInAppEventParameterName, reason: from kotlin metadata */
    int values;
    public final String AFInAppEventType;

    /* renamed from: AFKeystoreWrapper, reason: from kotlin metadata */
    public final String valueOf;

    /* renamed from: valueOf, reason: from kotlin metadata */
    final String AFInAppEventParameterName;

    public final boolean equals(Object p0) {
        if (this == p0) {
            return true;
        }
        if (!(p0 instanceof AFd1fSDK)) {
            return false;
        }
        AFd1fSDK aFd1fSDK = (AFd1fSDK) p0;
        return Intrinsics.areEqual(this.AFInAppEventType, aFd1fSDK.AFInAppEventType) && Intrinsics.areEqual(this.AFInAppEventParameterName, aFd1fSDK.AFInAppEventParameterName) && Intrinsics.areEqual(this.valueOf, aFd1fSDK.valueOf) && this.values == aFd1fSDK.values;
    }

    public final int hashCode() {
        return (((((this.AFInAppEventType.hashCode() * 31) + this.AFInAppEventParameterName.hashCode()) * 31) + this.valueOf.hashCode()) * 31) + this.values;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AFd1fSDK(AFInAppEventType=");
        sb.append(this.AFInAppEventType);
        sb.append(", AFInAppEventParameterName=");
        sb.append(this.AFInAppEventParameterName);
        sb.append(", valueOf=");
        sb.append(this.valueOf);
        sb.append(", values=");
        sb.append(this.values);
        sb.append(')');
        return sb.toString();
    }

    public AFd1fSDK(String str, String str2, String str3, int i) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        this.AFInAppEventType = str;
        this.AFInAppEventParameterName = str2;
        this.valueOf = str3;
        this.values = i;
    }

    public /* synthetic */ AFd1fSDK(String str, String str2, String str3, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i2 & 8) != 0 ? 1 : i);
    }

    @Metadata(m1394d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ1\u0010\b\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0016\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0004\"\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0003\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u000b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u000b\u0010\r"}, m1395d2 = {"Lcom/appsflyer/internal/AFd1fSDK$AFa1uSDK;", "", "", "p0", "", "", "p1", "", "AFInAppEventType", "(Ljava/lang/Integer;[Ljava/lang/String;)Z", "Lcom/appsflyer/internal/AFd1fSDK;", "AFInAppEventParameterName", "(Ljava/lang/String;)Lcom/appsflyer/internal/AFd1fSDK;", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "<init>", "()V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* renamed from: com.appsflyer.internal.AFd1fSDK$AFa1uSDK, reason: from kotlin metadata */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static AFd1fSDK AFInAppEventParameterName(String p0) {
            Intrinsics.checkNotNullParameter(p0, "");
            List<String> split$default = StringsKt.split$default((CharSequence) p0, new String[]{"\n"}, false, 0, 6, (Object) null);
            if (split$default.size() != 4) {
                return null;
            }
            String str = null;
            String str2 = null;
            String str3 = null;
            Integer num = null;
            for (String str4 : split$default) {
                if (StringsKt.startsWith$default(str4, "label=", false, 2, (Object) null)) {
                    str = AFInAppEventParameterName(str4, "label=");
                } else if (StringsKt.startsWith$default(str4, "hashName=", false, 2, (Object) null)) {
                    str2 = AFInAppEventParameterName(str4, "hashName=");
                } else if (StringsKt.startsWith$default(str4, "stackTrace=", false, 2, (Object) null)) {
                    str3 = AFInAppEventParameterName(str4, "stackTrace=");
                } else {
                    if (!StringsKt.startsWith$default(str4, "c=", false, 2, (Object) null)) {
                        break;
                    }
                    String substring = str4.substring(2);
                    Intrinsics.checkNotNullExpressionValue(substring, "");
                    num = Integer.valueOf(Integer.parseInt(StringsKt.trim((CharSequence) substring).toString()));
                }
            }
            if (AFInAppEventType(num, str, str2, str3)) {
                return null;
            }
            Intrinsics.checkNotNull(str);
            Intrinsics.checkNotNull(str2);
            Intrinsics.checkNotNull(str3);
            Intrinsics.checkNotNull(num);
            return new AFd1fSDK(str, str2, str3, num.intValue());
        }

        private static boolean AFInAppEventType(Integer p0, String... p1) {
            boolean z = p0 == null;
            int length = p1.length;
            for (int i = 0; i < 3; i++) {
                String str = p1[i];
                if (!z) {
                    String str2 = str;
                    if (!(str2 == null || str2.length() == 0)) {
                        z = false;
                    }
                }
                z = true;
            }
            return z;
        }

        private static String AFInAppEventParameterName(String str, String str2) {
            String substring = str.substring(str2.length());
            Intrinsics.checkNotNullExpressionValue(substring, "");
            String obj = StringsKt.trim((CharSequence) substring).toString();
            Intrinsics.checkNotNullParameter(obj, "");
            byte[] bytes = obj.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "");
            Intrinsics.checkNotNullParameter(bytes, "");
            byte[] decode = Base64.decode(bytes, 2);
            Intrinsics.checkNotNullExpressionValue(decode, "");
            return new String(decode, Charsets.UTF_8);
        }
    }

    public final JSONObject values() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.ScionAnalytics.PARAM_LABEL, this.AFInAppEventType);
        jSONObject.put("hash_name", this.AFInAppEventParameterName);
        jSONObject.put(UserDataStore.STATE, this.valueOf);
        jSONObject.put("c", String.valueOf(this.values));
        return jSONObject;
    }
}
