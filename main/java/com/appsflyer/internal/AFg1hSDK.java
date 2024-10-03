package com.appsflyer.internal;

import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(m1394d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0016\b&\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007¢\u0006\u0004\b\u001f\u0010 J)\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\t\u0010\nJO\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J)\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\u0014\u0010\nJ)\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\u0015\u0010\nJ)\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\u0016\u0010\nJ\u001d\u0010\u0017\u001a\u00020\u0004*\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0005¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u0019\u001a\u00020\u0004*\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u0019\u0010\u0018R\u001a\u0010\u001e\u001a\u00020\u00068\u0017X\u0096D¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d"}, m1395d2 = {"Lcom/appsflyer/internal/AFg1hSDK;", "", "Lcom/appsflyer/internal/AFg1gSDK;", "p0", "", "p1", "", "p2", "", "d", "(Lcom/appsflyer/internal/AFg1gSDK;Ljava/lang/String;Z)V", "", "p3", "p4", "p5", "p6", "e", "(Lcom/appsflyer/internal/AFg1gSDK;Ljava/lang/String;Ljava/lang/Throwable;ZZZZ)V", "force", "(Lcom/appsflyer/internal/AFg1gSDK;Ljava/lang/String;)V", "i", "v", "w", "AFInAppEventParameterName", "(Ljava/lang/String;Lcom/appsflyer/internal/AFg1gSDK;)Ljava/lang/String;", "withTag$SDK_prodRelease", "AFKeystoreWrapper", "Z", "getShouldExtendMsg", "()Z", "shouldExtendMsg", "<init>", "()V", "AFa1uSDK"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public abstract class AFg1hSDK {

    /* renamed from: AFKeystoreWrapper, reason: from kotlin metadata */
    private final boolean shouldExtendMsg;
    public static long AFInAppEventType = System.currentTimeMillis();

    /* renamed from: d */
    public final void m96d(AFg1gSDK aFg1gSDK, String str) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        d$default(this, aFg1gSDK, str, false, 4, null);
    }

    /* renamed from: d */
    public void mo55d(AFg1gSDK p0, String p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
    }

    /* renamed from: e */
    public final void m97e(AFg1gSDK aFg1gSDK, String str, Throwable th) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(th, "");
        e$default(this, aFg1gSDK, str, th, false, false, false, false, 120, null);
    }

    /* renamed from: e */
    public final void m98e(AFg1gSDK aFg1gSDK, String str, Throwable th, boolean z) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(th, "");
        e$default(this, aFg1gSDK, str, th, z, false, false, false, 112, null);
    }

    /* renamed from: e */
    public final void m99e(AFg1gSDK aFg1gSDK, String str, Throwable th, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(th, "");
        e$default(this, aFg1gSDK, str, th, z, z2, false, false, 96, null);
    }

    /* renamed from: e */
    public final void m100e(AFg1gSDK aFg1gSDK, String str, Throwable th, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(th, "");
        e$default(this, aFg1gSDK, str, th, z, z2, z3, false, 64, null);
    }

    /* renamed from: e */
    public void mo56e(AFg1gSDK p0, String p1, Throwable p2, boolean p3, boolean p4, boolean p5, boolean p6) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        Intrinsics.checkNotNullParameter(p2, "");
    }

    public void force(AFg1gSDK p0, String p1) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
    }

    /* renamed from: i */
    public final void m101i(AFg1gSDK aFg1gSDK, String str) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        i$default(this, aFg1gSDK, str, false, 4, null);
    }

    /* renamed from: i */
    public void mo57i(AFg1gSDK p0, String p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
    }

    /* renamed from: v */
    public final void m102v(AFg1gSDK aFg1gSDK, String str) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        v$default(this, aFg1gSDK, str, false, 4, null);
    }

    /* renamed from: v */
    public void mo58v(AFg1gSDK p0, String p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
    }

    /* renamed from: w */
    public final void m103w(AFg1gSDK aFg1gSDK, String str) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        w$default(this, aFg1gSDK, str, false, 4, null);
    }

    /* renamed from: w */
    public void mo59w(AFg1gSDK p0, String p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
    }

    public boolean getShouldExtendMsg() {
        return this.shouldExtendMsg;
    }

    public static /* synthetic */ void d$default(AFg1hSDK aFg1hSDK, AFg1gSDK aFg1gSDK, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        aFg1hSDK.mo55d(aFg1gSDK, str, z);
    }

    public static /* synthetic */ void e$default(AFg1hSDK aFg1hSDK, AFg1gSDK aFg1gSDK, String str, Throwable th, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException();
        }
        aFg1hSDK.mo56e(aFg1gSDK, str, th, (i & 8) != 0 ? true : z, (i & 16) != 0 ? true : z2, (i & 32) != 0 ? true : z3, (i & 64) != 0 ? true : z4);
    }

    public static /* synthetic */ void i$default(AFg1hSDK aFg1hSDK, AFg1gSDK aFg1gSDK, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        aFg1hSDK.mo57i(aFg1gSDK, str, z);
    }

    public static /* synthetic */ void w$default(AFg1hSDK aFg1hSDK, AFg1gSDK aFg1gSDK, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        aFg1hSDK.mo59w(aFg1gSDK, str, z);
    }

    public static /* synthetic */ void v$default(AFg1hSDK aFg1hSDK, AFg1gSDK aFg1gSDK, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        aFg1hSDK.mo58v(aFg1gSDK, str, z);
    }

    public final String withTag$SDK_prodRelease(String str, AFg1gSDK aFg1gSDK) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        StringBuilder sb = new StringBuilder("[");
        sb.append(aFg1gSDK.values);
        sb.append("] ");
        sb.append(str);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String AFInAppEventParameterName(String str, AFg1gSDK aFg1gSDK) {
        Intrinsics.checkNotNullParameter(aFg1gSDK, "");
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            str = JsonSerializer.Null;
        }
        String withTag$SDK_prodRelease = withTag$SDK_prodRelease(str, aFg1gSDK);
        if (!getShouldExtendMsg()) {
            return withTag$SDK_prodRelease;
        }
        StringBuilder sb = new StringBuilder("(");
        sb.append(System.currentTimeMillis() - AFInAppEventType);
        sb.append(") [");
        sb.append(Thread.currentThread().getName());
        sb.append("] ");
        sb.append(withTag$SDK_prodRelease);
        return sb.toString();
    }
}
