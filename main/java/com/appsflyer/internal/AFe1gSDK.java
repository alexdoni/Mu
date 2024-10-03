package com.appsflyer.internal;

import android.util.Base64;
import com.appsflyer.AFLogger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

@Metadata(m1394d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0017\u0012\u0006\u0010\u0013\u001a\u00020\n\u0012\u0006\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0005\u001a\u00020\u0002X\u0083\u0080\u0002¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0013\u0010\u0007\u001a\u00020\u0002X\u0083\u0080\u0002¢\u0006\u0006\n\u0004\b\u0006\u0010\u0004R\u0011\u0010\t\u001a\u00020\u00028G¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0006\u001a\u00020\n8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0011\u0010\u000b\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\u000f8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0010R\u0011\u0010\u0012\u001a\u00020\u00028G¢\u0006\u0006\u001a\u0004\b\u000b\u0010\b"}, m1395d2 = {"Lcom/appsflyer/internal/AFe1gSDK;", "", "", "AFLogger", "Lkotlin/Lazy;", "values", "valueOf", "AFInAppEventType", "()Ljava/lang/String;", "AFKeystoreWrapper", "Lcom/appsflyer/internal/AFd1sSDK;", "AFInAppEventParameterName", "Lcom/appsflyer/internal/AFd1sSDK;", "", "()Z", "Lcom/appsflyer/internal/AFd1tSDK;", "Lcom/appsflyer/internal/AFd1tSDK;", "e", "d", "p0", "p1", "<init>", "(Lcom/appsflyer/internal/AFd1sSDK;Lcom/appsflyer/internal/AFd1tSDK;)V", "AFa1vSDK"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AFe1gSDK {

    /* renamed from: e */
    private static AFe1kSDK f198e;

    /* renamed from: AFInAppEventParameterName, reason: from kotlin metadata */
    private final AFd1sSDK valueOf;

    /* renamed from: AFInAppEventType, reason: from kotlin metadata */
    private final AFd1tSDK e;

    /* renamed from: AFLogger, reason: from kotlin metadata */
    private final Lazy values;

    /* renamed from: valueOf, reason: from kotlin metadata */
    public final Lazy AFInAppEventType;

    /* renamed from: AFa1vSDK, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static String AFKeystoreWrapper = "https://%scdn-%ssettings.%s/android/v1/%s/settings";
    public static String values = "https://%scdn-%stestsettings.%s/android/v1/%s/settings";

    /* renamed from: d */
    private static final List<String> f197d = CollectionsKt.listOf((Object[]) new String[]{"googleplay", "playstore", "googleplaystore"});

    /* loaded from: classes.dex */
    public /* synthetic */ class AFa1uSDK {
        public static final /* synthetic */ int[] AFInAppEventType;

        static {
            int[] iArr = new int[AFe1cSDK.values().length];
            iArr[AFe1cSDK.DEFAULT.ordinal()] = 1;
            iArr[AFe1cSDK.API.ordinal()] = 2;
            iArr[AFe1cSDK.RC.ordinal()] = 3;
            AFInAppEventType = iArr;
        }
    }

    public static final void AFInAppEventParameterName(AFe1kSDK aFe1kSDK) {
        Companion.AFInAppEventType(aFe1kSDK);
    }

    public AFe1gSDK(AFd1sSDK aFd1sSDK, AFd1tSDK aFd1tSDK) {
        Intrinsics.checkNotNullParameter(aFd1sSDK, "");
        Intrinsics.checkNotNullParameter(aFd1tSDK, "");
        this.valueOf = aFd1sSDK;
        this.e = aFd1tSDK;
        this.values = LazyKt.lazy(new Function0<String>() { // from class: com.appsflyer.internal.AFe1gSDK.5
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFInAppEventParameterName, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String AFKeystoreWrapper2 = AFb1tSDK.AFKeystoreWrapper(AFe1gSDK.this.e, AFe1gSDK.this.valueOf.AFLogger());
                String str = AFKeystoreWrapper2;
                if (!(str == null || StringsKt.isBlank(str))) {
                    String obj = StringsKt.trim((CharSequence) str).toString();
                    Companion companion = AFe1gSDK.INSTANCE;
                    List<String> AFKeystoreWrapper3 = Companion.AFKeystoreWrapper();
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "");
                    String lowerCase = obj.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "");
                    if (!AFKeystoreWrapper3.contains(lowerCase)) {
                        AFKeystoreWrapper2 = "-".concat(String.valueOf(obj));
                    } else {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String format = String.format("AF detected using redundant Google-Play channel for attribution - %s. Using without channel postfix.", Arrays.copyOf(new Object[]{obj}, 1));
                        Intrinsics.checkNotNullExpressionValue(format, "");
                        AFLogger.afWarnLog(format);
                        AFKeystoreWrapper2 = "";
                    }
                }
                return StringsKt.trim((CharSequence) (AFKeystoreWrapper2 != null ? AFKeystoreWrapper2 : "")).toString();
            }
        });
        this.AFInAppEventType = LazyKt.lazy(new Function0<String>() { // from class: com.appsflyer.internal.AFe1gSDK.4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFKeystoreWrapper, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String packageName = AFe1gSDK.this.valueOf.AFInAppEventParameterName.valueOf.getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "");
                return AFe1gSDK.values(packageName, AFe1gSDK.valueOf(AFe1gSDK.this));
            }
        });
    }

    @Metadata(m1394d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0011\u0010\u0012R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\u0006\u001a\u00020\u0003X\u0087\u0002¢\u0006\u0006\n\u0004\b\u0006\u0010\tR\u0012\u0010\u000b\u001a\u00020\u0003X\u0087\u0002¢\u0006\u0006\n\u0004\b\n\u0010\tR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\f@\u0007X\u0087\n¢\u0006\f\n\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010"}, m1395d2 = {"Lcom/appsflyer/internal/AFe1gSDK$AFa1vSDK;", "", "", "", "d", "Ljava/util/List;", "AFKeystoreWrapper", "()Ljava/util/List;", "valueOf", "Ljava/lang/String;", "values", "AFInAppEventParameterName", "Lcom/appsflyer/internal/AFe1kSDK;", "e", "Lcom/appsflyer/internal/AFe1kSDK;", "AFInAppEventType", "(Lcom/appsflyer/internal/AFe1kSDK;)V", "<init>", "()V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* renamed from: com.appsflyer.internal.AFe1gSDK$AFa1vSDK, reason: from kotlin metadata */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static List<String> AFKeystoreWrapper() {
            return AFe1gSDK.f197d;
        }

        public static void AFInAppEventType(AFe1kSDK aFe1kSDK) {
            AFe1gSDK.f198e = aFe1kSDK;
        }
    }

    public static boolean valueOf() {
        return f198e == null;
    }

    public final String AFInAppEventParameterName() {
        AFe1cSDK aFe1cSDK;
        if (valueOf()) {
            aFe1cSDK = AFe1cSDK.DEFAULT;
        } else {
            aFe1cSDK = AFe1cSDK.API;
        }
        int i = AFa1uSDK.AFInAppEventType[aFe1cSDK.ordinal()];
        if (i == 1) {
            return (String) this.AFInAppEventType.getValue();
        }
        if (i != 2) {
            if (i == 3) {
                return "";
            }
            throw new NoWhenBranchMatchedException();
        }
        AFe1kSDK aFe1kSDK = f198e;
        String str = aFe1kSDK != null ? aFe1kSDK.values : null;
        return str == null ? "" : str;
    }

    public final String AFInAppEventType() {
        AFe1cSDK aFe1cSDK;
        if (valueOf()) {
            aFe1cSDK = AFe1cSDK.DEFAULT;
        } else {
            aFe1cSDK = AFe1cSDK.API;
        }
        int i = AFa1uSDK.AFInAppEventType[aFe1cSDK.ordinal()];
        if (i == 1) {
            return "appsflyersdk.com";
        }
        if (i != 2) {
            if (i == 3) {
                return "";
            }
            throw new NoWhenBranchMatchedException();
        }
        AFe1kSDK aFe1kSDK = f198e;
        String str = aFe1kSDK != null ? aFe1kSDK.AFInAppEventParameterName : null;
        return str == null ? "" : str;
    }

    public static final /* synthetic */ String values(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(str2);
        String obj = sb2.toString();
        Intrinsics.checkNotNullParameter(obj, "");
        MessageDigest messageDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA256);
        byte[] bytes = obj.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "");
        byte[] digest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(digest, "");
        String encodeToString = Base64.encodeToString(digest, 2);
        Intrinsics.checkNotNullExpressionValue(encodeToString, "");
        String lowerCase = encodeToString.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "");
        String substring = new Regex("[^\\w]+").replace(lowerCase, "").substring(0, 6);
        Intrinsics.checkNotNullExpressionValue(substring, "");
        sb.append(substring);
        sb.append('-');
        return sb.toString();
    }

    public static final /* synthetic */ String valueOf(AFe1gSDK aFe1gSDK) {
        return (String) aFe1gSDK.values.getValue();
    }
}
