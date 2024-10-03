package com.appsflyer;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B%\b\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010\u0003\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÇ\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÇ\u0003¢\u0006\u0004\b\u0007\u0010\u0006J2\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002HÇ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\r\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012H×\u0001¢\u0006\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0006R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0006R\u001a\u0010\u001e\u001a\u00020\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u0004"}, m1395d2 = {"Lcom/appsflyer/AppsFlyerConsent;", "", "", "component1", "()Z", "component2", "()Ljava/lang/Boolean;", "component3", "p0", "p1", "p2", "copy", "(ZLjava/lang/Boolean;Ljava/lang/Boolean;)Lcom/appsflyer/AppsFlyerConsent;", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "AFInAppEventType", "Ljava/lang/Boolean;", "getHasConsentForAdsPersonalization", "hasConsentForAdsPersonalization", "values", "getHasConsentForDataUsage", "hasConsentForDataUsage", "AFInAppEventParameterName", "Z", "isUserSubjectToGDPR", "<init>", "(ZLjava/lang/Boolean;Ljava/lang/Boolean;)V", "Companion"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final /* data */ class AppsFlyerConsent {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: AFInAppEventParameterName, reason: from kotlin metadata */
    private final boolean isUserSubjectToGDPR;

    /* renamed from: AFInAppEventType, reason: from kotlin metadata */
    private final Boolean hasConsentForAdsPersonalization;

    /* renamed from: values, reason: from kotlin metadata */
    private final Boolean hasConsentForDataUsage;

    public /* synthetic */ AppsFlyerConsent(boolean z, Boolean bool, Boolean bool2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, bool, bool2);
    }

    public static /* synthetic */ AppsFlyerConsent copy$default(AppsFlyerConsent appsFlyerConsent, boolean z, Boolean bool, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = appsFlyerConsent.isUserSubjectToGDPR;
        }
        if ((i & 2) != 0) {
            bool = appsFlyerConsent.hasConsentForDataUsage;
        }
        if ((i & 4) != 0) {
            bool2 = appsFlyerConsent.hasConsentForAdsPersonalization;
        }
        return appsFlyerConsent.copy(z, bool, bool2);
    }

    @JvmStatic
    public static final AppsFlyerConsent forGDPRUser(boolean z, boolean z2) {
        return INSTANCE.forGDPRUser(z, z2);
    }

    @JvmStatic
    public static final AppsFlyerConsent forNonGDPRUser() {
        return INSTANCE.forNonGDPRUser();
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsUserSubjectToGDPR() {
        return this.isUserSubjectToGDPR;
    }

    /* renamed from: component2, reason: from getter */
    public final Boolean getHasConsentForDataUsage() {
        return this.hasConsentForDataUsage;
    }

    /* renamed from: component3, reason: from getter */
    public final Boolean getHasConsentForAdsPersonalization() {
        return this.hasConsentForAdsPersonalization;
    }

    public final AppsFlyerConsent copy(boolean p0, Boolean p1, Boolean p2) {
        return new AppsFlyerConsent(p0, p1, p2);
    }

    public final boolean equals(Object p0) {
        if (this == p0) {
            return true;
        }
        if (!(p0 instanceof AppsFlyerConsent)) {
            return false;
        }
        AppsFlyerConsent appsFlyerConsent = (AppsFlyerConsent) p0;
        return this.isUserSubjectToGDPR == appsFlyerConsent.isUserSubjectToGDPR && Intrinsics.areEqual(this.hasConsentForDataUsage, appsFlyerConsent.hasConsentForDataUsage) && Intrinsics.areEqual(this.hasConsentForAdsPersonalization, appsFlyerConsent.hasConsentForAdsPersonalization);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public final int hashCode() {
        boolean z = this.isUserSubjectToGDPR;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        Boolean bool = this.hasConsentForDataUsage;
        int hashCode = (i + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.hasConsentForAdsPersonalization;
        return hashCode + (bool2 != null ? bool2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AppsFlyerConsent(isUserSubjectToGDPR=");
        sb.append(this.isUserSubjectToGDPR);
        sb.append(", hasConsentForDataUsage=");
        sb.append(this.hasConsentForDataUsage);
        sb.append(", hasConsentForAdsPersonalization=");
        sb.append(this.hasConsentForAdsPersonalization);
        sb.append(')');
        return sb.toString();
    }

    private AppsFlyerConsent(boolean z, Boolean bool, Boolean bool2) {
        this.isUserSubjectToGDPR = z;
        this.hasConsentForDataUsage = bool;
        this.hasConsentForAdsPersonalization = bool2;
    }

    public final boolean isUserSubjectToGDPR() {
        return this.isUserSubjectToGDPR;
    }

    public final Boolean getHasConsentForDataUsage() {
        return this.hasConsentForDataUsage;
    }

    public final Boolean getHasConsentForAdsPersonalization() {
        return this.hasConsentForAdsPersonalization;
    }

    @Metadata(m1394d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\b\u0010\t"}, m1395d2 = {"Lcom/appsflyer/AppsFlyerConsent$Companion;", "", "", "p0", "p1", "Lcom/appsflyer/AppsFlyerConsent;", "forGDPRUser", "(ZZ)Lcom/appsflyer/AppsFlyerConsent;", "forNonGDPRUser", "()Lcom/appsflyer/AppsFlyerConsent;", "<init>", "()V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AppsFlyerConsent forGDPRUser(boolean p0, boolean p1) {
            return new AppsFlyerConsent(true, Boolean.valueOf(p0), Boolean.valueOf(p1), null);
        }

        @JvmStatic
        public final AppsFlyerConsent forNonGDPRUser() {
            return new AppsFlyerConsent(false, null, 0 == true ? 1 : 0, 0 == true ? 1 : 0);
        }
    }
}
