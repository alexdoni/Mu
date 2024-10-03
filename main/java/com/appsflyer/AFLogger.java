package com.appsflyer;

import com.appsflyer.internal.AFg1gSDK;
import com.appsflyer.internal.AFg1hSDK;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(m1394d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u00012B\t\b\u0002¢\u0006\u0004\b0\u00101J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u0005\u0010\tJ7\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u000e\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0004\b\u000e\u0010\u0010J+\u0010\u000e\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u000e\u0010\u0011J3\u0010\u000e\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u000e\u0010\u0012J#\u0010\u0013\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0004\b\u0013\u0010\u0010J+\u0010\u0013\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u0013\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0014\u0010\u0006J\u001f\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u0014\u0010\tJ\u0017\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0015\u0010\u0006J\u0017\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0016\u0010\u0006J\u0017\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0017\u0010\u0006J\u0017\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0018\u0010\u0006J\u001f\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u0018\u0010\tJ'\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJG\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010 \u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b \u0010!J'\u0010\"\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\"\u0010\u001bJ!\u0010$\u001a\u00020\u00042\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010#\"\u00020\u0001¢\u0006\u0004\b$\u0010%J!\u0010&\u001a\u00020\u00042\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010#\"\u00020\u0001¢\u0006\u0004\b&\u0010%J'\u0010'\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b'\u0010\u001bJ'\u0010(\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b(\u0010\u001bR\u0017\u0010,\u001a\u0006*\u00020)0)X\u0083\u0080\u0002¢\u0006\u0006\n\u0004\b*\u0010+R\u0019\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00010-X\u0083\u0080\u0002¢\u0006\u0006\n\u0004\b.\u0010+"}, m1395d2 = {"Lcom/appsflyer/AFLogger;", "Lcom/appsflyer/internal/AFg1hSDK;", "", "p0", "", "afDebugLog", "(Ljava/lang/String;)V", "", "p1", "(Ljava/lang/String;Z)V", "", "p2", "p3", "p4", "afErrorLog", "(Ljava/lang/String;Ljava/lang/Throwable;ZZZ)V", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "(Ljava/lang/String;Ljava/lang/Throwable;Z)V", "(Ljava/lang/String;Ljava/lang/Throwable;ZZ)V", "afErrorLogForExcManagerOnly", "afInfoLog", "afLogForce", "afRDLog", "afVerboseLog", "afWarnLog", "Lcom/appsflyer/internal/AFg1gSDK;", "d", "(Lcom/appsflyer/internal/AFg1gSDK;Ljava/lang/String;Z)V", "p5", "p6", "e", "(Lcom/appsflyer/internal/AFg1gSDK;Ljava/lang/String;Ljava/lang/Throwable;ZZZZ)V", "force", "(Lcom/appsflyer/internal/AFg1gSDK;Ljava/lang/String;)V", "i", "", "registerClient", "([Lcom/appsflyer/internal/AFg1hSDK;)V", "unregisterClient", "v", "w", "Ljava/util/concurrent/ExecutorService;", "AFInAppEventParameterName", "Lkotlin/Lazy;", "AFKeystoreWrapper", "", "valueOf", "values", "<init>", "()V", "LogLevel"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AFLogger extends AFg1hSDK {
    public static final AFLogger INSTANCE = new AFLogger();

    /* renamed from: valueOf, reason: from kotlin metadata */
    private static final Lazy values = LazyKt.lazy(C06876.values);

    /* renamed from: AFInAppEventParameterName, reason: from kotlin metadata */
    private static final Lazy AFKeystoreWrapper = LazyKt.lazy(C06865.AFInAppEventType);

    private AFLogger() {
    }

    @Metadata(m1394d1 = {"\u0000\u0010\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\u0010\u0003\u001a\u001a\u0012\b\u0012\u0006*\u00020\u00010\u0001*\f\u0012\b\u0012\u0006*\u00020\u00010\u00010\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, m1395d2 = {"", "Lcom/appsflyer/internal/AFg1hSDK;", "", "values", "()Ljava/util/Set;"}, m1396k = 3, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* renamed from: com.appsflyer.AFLogger$6 */
    /* loaded from: classes.dex */
    static final class C06876 extends Lambda implements Function0<Set<AFg1hSDK>> {
        public static final C06876 values = ;

        C06876() {
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: values */
        public final Set<AFg1hSDK> invoke() {
            return Collections.synchronizedSet(new LinkedHashSet());
        }
    }

    @Metadata(m1394d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u0006*\u00020\u00000\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, m1395d2 = {"Ljava/util/concurrent/ExecutorService;", "AFInAppEventType", "()Ljava/util/concurrent/ExecutorService;"}, m1396k = 3, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* renamed from: com.appsflyer.AFLogger$5 */
    /* loaded from: classes.dex */
    static final class C06865 extends Lambda implements Function0<ExecutorService> {
        public static final C06865 AFInAppEventType = ;

        C06865() {
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: AFInAppEventType */
        public final ExecutorService invoke() {
            return Executors.newSingleThreadExecutor();
        }
    }

    public final void registerClient(final AFg1hSDK... p0) {
        Intrinsics.checkNotNullParameter(p0, "");
        ((ExecutorService) AFKeystoreWrapper.getValue()).execute(new Runnable() { // from class: com.appsflyer.AFLogger$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AFLogger.valueOf(p0);
            }
        });
    }

    public final void unregisterClient(final AFg1hSDK... p0) {
        Intrinsics.checkNotNullParameter(p0, "");
        ((ExecutorService) AFKeystoreWrapper.getValue()).execute(new Runnable() { // from class: com.appsflyer.AFLogger$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AFLogger.AFKeystoreWrapper(p0);
            }
        });
    }

    @Metadata(m1394d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, m1395d2 = {"Lcom/appsflyer/internal/AFg1hSDK;", "p0", "", "values", "(Lcom/appsflyer/internal/AFg1hSDK;)V"}, m1396k = 3, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* renamed from: com.appsflyer.AFLogger$1 */
    /* loaded from: classes.dex */
    static final class C06821 extends Lambda implements Function1<AFg1hSDK, Unit> {
        private /* synthetic */ String $AFKeystoreWrapper;
        private /* synthetic */ boolean $values;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06821(String str, boolean z) {
            super(1);
            r2 = str;
            r3 = z;
        }

        @Override // kotlin.jvm.functions.Function1
        public final /* synthetic */ Unit invoke(AFg1hSDK aFg1hSDK) {
            values(aFg1hSDK);
            return Unit.INSTANCE;
        }

        public final void values(AFg1hSDK aFg1hSDK) {
            Intrinsics.checkNotNullParameter(aFg1hSDK, "");
            aFg1hSDK.mo55d(AFg1gSDK.this, r2, r3);
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: d */
    public final void mo55d(AFg1gSDK p0, String p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        ((ExecutorService) AFKeystoreWrapper.getValue()).execute(new AFLogger$$ExternalSyntheticLambda0(new Function1<AFg1hSDK, Unit>() { // from class: com.appsflyer.AFLogger.1
            private /* synthetic */ String $AFKeystoreWrapper;
            private /* synthetic */ boolean $values;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C06821(String p12, boolean p22) {
                super(1);
                r2 = p12;
                r3 = p22;
            }

            @Override // kotlin.jvm.functions.Function1
            public final /* synthetic */ Unit invoke(AFg1hSDK aFg1hSDK) {
                values(aFg1hSDK);
                return Unit.INSTANCE;
            }

            public final void values(AFg1hSDK aFg1hSDK) {
                Intrinsics.checkNotNullParameter(aFg1hSDK, "");
                aFg1hSDK.mo55d(AFg1gSDK.this, r2, r3);
            }
        }));
    }

    @Metadata(m1394d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, m1395d2 = {"Lcom/appsflyer/internal/AFg1hSDK;", "p0", "", "valueOf", "(Lcom/appsflyer/internal/AFg1hSDK;)V"}, m1396k = 3, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* renamed from: com.appsflyer.AFLogger$3 */
    /* loaded from: classes.dex */
    static final class C06843 extends Lambda implements Function1<AFg1hSDK, Unit> {
        private /* synthetic */ String $AFInAppEventParameterName;
        private /* synthetic */ boolean $AFKeystoreWrapper;

        /* renamed from: $d */
        private /* synthetic */ boolean f138$d;
        private /* synthetic */ boolean $registerClient;
        private /* synthetic */ boolean $valueOf;
        private /* synthetic */ Throwable $values;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06843(String str, Throwable th, boolean z, boolean z2, boolean z3, boolean z4) {
            super(1);
            r2 = str;
            r3 = th;
            r4 = z;
            r5 = z2;
            r6 = z3;
            r7 = z4;
        }

        @Override // kotlin.jvm.functions.Function1
        public final /* synthetic */ Unit invoke(AFg1hSDK aFg1hSDK) {
            valueOf(aFg1hSDK);
            return Unit.INSTANCE;
        }

        public final void valueOf(AFg1hSDK aFg1hSDK) {
            Intrinsics.checkNotNullParameter(aFg1hSDK, "");
            aFg1hSDK.mo56e(AFg1gSDK.this, r2, r3, r4, r5, r6, r7);
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: e */
    public final void mo56e(AFg1gSDK p0, String p1, Throwable p2, boolean p3, boolean p4, boolean p5, boolean p6) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        Intrinsics.checkNotNullParameter(p2, "");
        ((ExecutorService) AFKeystoreWrapper.getValue()).execute(new AFLogger$$ExternalSyntheticLambda0(new Function1<AFg1hSDK, Unit>() { // from class: com.appsflyer.AFLogger.3
            private /* synthetic */ String $AFInAppEventParameterName;
            private /* synthetic */ boolean $AFKeystoreWrapper;

            /* renamed from: $d */
            private /* synthetic */ boolean f138$d;
            private /* synthetic */ boolean $registerClient;
            private /* synthetic */ boolean $valueOf;
            private /* synthetic */ Throwable $values;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C06843(String p12, Throwable p22, boolean p32, boolean p42, boolean p52, boolean p62) {
                super(1);
                r2 = p12;
                r3 = p22;
                r4 = p32;
                r5 = p42;
                r6 = p52;
                r7 = p62;
            }

            @Override // kotlin.jvm.functions.Function1
            public final /* synthetic */ Unit invoke(AFg1hSDK aFg1hSDK) {
                valueOf(aFg1hSDK);
                return Unit.INSTANCE;
            }

            public final void valueOf(AFg1hSDK aFg1hSDK) {
                Intrinsics.checkNotNullParameter(aFg1hSDK, "");
                aFg1hSDK.mo56e(AFg1gSDK.this, r2, r3, r4, r5, r6, r7);
            }
        }));
    }

    @Metadata(m1394d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, m1395d2 = {"Lcom/appsflyer/internal/AFg1hSDK;", "p0", "", "valueOf", "(Lcom/appsflyer/internal/AFg1hSDK;)V"}, m1396k = 3, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* renamed from: com.appsflyer.AFLogger$2 */
    /* loaded from: classes.dex */
    static final class C06832 extends Lambda implements Function1<AFg1hSDK, Unit> {
        private /* synthetic */ String $AFKeystoreWrapper;
        private /* synthetic */ boolean $valueOf;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06832(String str, boolean z) {
            super(1);
            r2 = str;
            r3 = z;
        }

        @Override // kotlin.jvm.functions.Function1
        public final /* synthetic */ Unit invoke(AFg1hSDK aFg1hSDK) {
            valueOf(aFg1hSDK);
            return Unit.INSTANCE;
        }

        public final void valueOf(AFg1hSDK aFg1hSDK) {
            Intrinsics.checkNotNullParameter(aFg1hSDK, "");
            aFg1hSDK.mo57i(AFg1gSDK.this, r2, r3);
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: i */
    public final void mo57i(AFg1gSDK p0, String p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        ((ExecutorService) AFKeystoreWrapper.getValue()).execute(new AFLogger$$ExternalSyntheticLambda0(new Function1<AFg1hSDK, Unit>() { // from class: com.appsflyer.AFLogger.2
            private /* synthetic */ String $AFKeystoreWrapper;
            private /* synthetic */ boolean $valueOf;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C06832(String p12, boolean p22) {
                super(1);
                r2 = p12;
                r3 = p22;
            }

            @Override // kotlin.jvm.functions.Function1
            public final /* synthetic */ Unit invoke(AFg1hSDK aFg1hSDK) {
                valueOf(aFg1hSDK);
                return Unit.INSTANCE;
            }

            public final void valueOf(AFg1hSDK aFg1hSDK) {
                Intrinsics.checkNotNullParameter(aFg1hSDK, "");
                aFg1hSDK.mo57i(AFg1gSDK.this, r2, r3);
            }
        }));
    }

    @Metadata(m1394d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, m1395d2 = {"Lcom/appsflyer/internal/AFg1hSDK;", "p0", "", "values", "(Lcom/appsflyer/internal/AFg1hSDK;)V"}, m1396k = 3, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* renamed from: com.appsflyer.AFLogger$9 */
    /* loaded from: classes.dex */
    static final class C06899 extends Lambda implements Function1<AFg1hSDK, Unit> {
        private /* synthetic */ String $AFInAppEventParameterName;
        private /* synthetic */ boolean $valueOf;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06899(String str, boolean z) {
            super(1);
            r2 = str;
            r3 = z;
        }

        @Override // kotlin.jvm.functions.Function1
        public final /* synthetic */ Unit invoke(AFg1hSDK aFg1hSDK) {
            values(aFg1hSDK);
            return Unit.INSTANCE;
        }

        public final void values(AFg1hSDK aFg1hSDK) {
            Intrinsics.checkNotNullParameter(aFg1hSDK, "");
            aFg1hSDK.mo59w(AFg1gSDK.this, r2, r3);
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: w */
    public final void mo59w(AFg1gSDK p0, String p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        ((ExecutorService) AFKeystoreWrapper.getValue()).execute(new AFLogger$$ExternalSyntheticLambda0(new Function1<AFg1hSDK, Unit>() { // from class: com.appsflyer.AFLogger.9
            private /* synthetic */ String $AFInAppEventParameterName;
            private /* synthetic */ boolean $valueOf;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C06899(String p12, boolean p22) {
                super(1);
                r2 = p12;
                r3 = p22;
            }

            @Override // kotlin.jvm.functions.Function1
            public final /* synthetic */ Unit invoke(AFg1hSDK aFg1hSDK) {
                values(aFg1hSDK);
                return Unit.INSTANCE;
            }

            public final void values(AFg1hSDK aFg1hSDK) {
                Intrinsics.checkNotNullParameter(aFg1hSDK, "");
                aFg1hSDK.mo59w(AFg1gSDK.this, r2, r3);
            }
        }));
    }

    @Metadata(m1394d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, m1395d2 = {"Lcom/appsflyer/internal/AFg1hSDK;", "p0", "", "values", "(Lcom/appsflyer/internal/AFg1hSDK;)V"}, m1396k = 3, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* renamed from: com.appsflyer.AFLogger$8 */
    /* loaded from: classes.dex */
    static final class C06888 extends Lambda implements Function1<AFg1hSDK, Unit> {
        private /* synthetic */ String $AFInAppEventParameterName;
        private /* synthetic */ boolean $values;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06888(String str, boolean z) {
            super(1);
            r2 = str;
            r3 = z;
        }

        @Override // kotlin.jvm.functions.Function1
        public final /* synthetic */ Unit invoke(AFg1hSDK aFg1hSDK) {
            values(aFg1hSDK);
            return Unit.INSTANCE;
        }

        public final void values(AFg1hSDK aFg1hSDK) {
            Intrinsics.checkNotNullParameter(aFg1hSDK, "");
            aFg1hSDK.mo58v(AFg1gSDK.this, r2, r3);
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    /* renamed from: v */
    public final void mo58v(AFg1gSDK p0, String p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        ((ExecutorService) AFKeystoreWrapper.getValue()).execute(new AFLogger$$ExternalSyntheticLambda0(new Function1<AFg1hSDK, Unit>() { // from class: com.appsflyer.AFLogger.8
            private /* synthetic */ String $AFInAppEventParameterName;
            private /* synthetic */ boolean $values;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C06888(String p12, boolean p22) {
                super(1);
                r2 = p12;
                r3 = p22;
            }

            @Override // kotlin.jvm.functions.Function1
            public final /* synthetic */ Unit invoke(AFg1hSDK aFg1hSDK) {
                values(aFg1hSDK);
                return Unit.INSTANCE;
            }

            public final void values(AFg1hSDK aFg1hSDK) {
                Intrinsics.checkNotNullParameter(aFg1hSDK, "");
                aFg1hSDK.mo58v(AFg1gSDK.this, r2, r3);
            }
        }));
    }

    @Metadata(m1394d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, m1395d2 = {"Lcom/appsflyer/internal/AFg1hSDK;", "p0", "", "AFKeystoreWrapper", "(Lcom/appsflyer/internal/AFg1hSDK;)V"}, m1396k = 3, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* renamed from: com.appsflyer.AFLogger$4 */
    /* loaded from: classes.dex */
    static final class C06854 extends Lambda implements Function1<AFg1hSDK, Unit> {
        private /* synthetic */ String $values;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06854(String str) {
            super(1);
            r2 = str;
        }

        public final void AFKeystoreWrapper(AFg1hSDK aFg1hSDK) {
            Intrinsics.checkNotNullParameter(aFg1hSDK, "");
            aFg1hSDK.force(AFg1gSDK.this, r2);
        }

        @Override // kotlin.jvm.functions.Function1
        public final /* synthetic */ Unit invoke(AFg1hSDK aFg1hSDK) {
            AFKeystoreWrapper(aFg1hSDK);
            return Unit.INSTANCE;
        }
    }

    @Override // com.appsflyer.internal.AFg1hSDK
    public final void force(AFg1gSDK p0, String p1) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        ((ExecutorService) AFKeystoreWrapper.getValue()).execute(new AFLogger$$ExternalSyntheticLambda0(new Function1<AFg1hSDK, Unit>() { // from class: com.appsflyer.AFLogger.4
            private /* synthetic */ String $values;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C06854(String p12) {
                super(1);
                r2 = p12;
            }

            public final void AFKeystoreWrapper(AFg1hSDK aFg1hSDK) {
                Intrinsics.checkNotNullParameter(aFg1hSDK, "");
                aFg1hSDK.force(AFg1gSDK.this, r2);
            }

            @Override // kotlin.jvm.functions.Function1
            public final /* synthetic */ Unit invoke(AFg1hSDK aFg1hSDK) {
                AFKeystoreWrapper(aFg1hSDK);
                return Unit.INSTANCE;
            }
        }));
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.i()", imports = {}))
    @JvmStatic
    public static final void afInfoLog(String p0, boolean p1) {
        Intrinsics.checkNotNullParameter(p0, "");
        INSTANCE.mo57i(AFg1gSDK.OTHER, p0, p1);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.d()", imports = {}))
    @JvmStatic
    public static final void afDebugLog(String p0, boolean p1) {
        Intrinsics.checkNotNullParameter(p0, "");
        INSTANCE.mo55d(AFg1gSDK.OTHER, p0, p1);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.e()", imports = {}))
    @JvmStatic
    public static final void afErrorLog(String p0, Throwable p1, boolean p2, boolean p3, boolean p4) {
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        AFg1hSDK.e$default(INSTANCE, AFg1gSDK.OTHER, p0, p1, p2, p3, p4, false, 64, null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.w()", imports = {}))
    @JvmStatic
    public static final void afWarnLog(String p0, boolean p1) {
        Intrinsics.checkNotNullParameter(p0, "");
        INSTANCE.mo59w(AFg1gSDK.OTHER, p0, p1);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.v()", imports = {}))
    @JvmStatic
    public static final void afVerboseLog(String p0) {
        Intrinsics.checkNotNullParameter(p0, "");
        INSTANCE.mo58v(AFg1gSDK.OTHER, p0, false);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.v()", imports = {}))
    @JvmStatic
    public static final void afRDLog(String p0) {
        Intrinsics.checkNotNullParameter(p0, "");
        INSTANCE.mo58v(AFg1gSDK.OTHER, p0, true);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.force()", imports = {}))
    @JvmStatic
    public static final void afLogForce(String p0) {
        Intrinsics.checkNotNullParameter(p0, "");
        INSTANCE.force(AFg1gSDK.OTHER, p0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.d()", imports = {}))
    @JvmStatic
    public static final void afDebugLog(String p0) {
        Intrinsics.checkNotNullParameter(p0, "");
        INSTANCE.mo55d(AFg1gSDK.OTHER, p0, true);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.i()", imports = {}))
    @JvmStatic
    public static final void afInfoLog(String p0) {
        Intrinsics.checkNotNullParameter(p0, "");
        INSTANCE.mo57i(AFg1gSDK.OTHER, p0, true);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.e()", imports = {}))
    @JvmStatic
    public static final void afErrorLog(String p0, Throwable p1) {
        AFLogger aFLogger = INSTANCE;
        AFg1gSDK aFg1gSDK = AFg1gSDK.OTHER;
        String str = p0;
        if (str == null || StringsKt.isBlank(str)) {
            p0 = JsonSerializer.Null;
        }
        String str2 = p0;
        if (p1 == null) {
            p1 = new NullPointerException("Invoked with null Throwable");
        }
        AFg1hSDK.e$default(aFLogger, aFg1gSDK, str2, p1, false, false, false, false, 120, null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.e()", imports = {}))
    @JvmStatic
    public static final void afErrorLogForExcManagerOnly(String p0, Throwable p1) {
        AFLogger aFLogger = INSTANCE;
        AFg1gSDK aFg1gSDK = AFg1gSDK.OTHER;
        String str = p0;
        if (str == null || StringsKt.isBlank(str)) {
            p0 = JsonSerializer.Null;
        }
        String str2 = p0;
        if (p1 == null) {
            p1 = new NullPointerException("Invoked with null Throwable");
        }
        AFg1hSDK.e$default(aFLogger, aFg1gSDK, str2, p1, false, false, true, false, 64, null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.e()", imports = {}))
    @JvmStatic
    public static final void afErrorLogForExcManagerOnly(String p0, Throwable p1, boolean p2) {
        AFLogger aFLogger = INSTANCE;
        AFg1gSDK aFg1gSDK = AFg1gSDK.OTHER;
        String str = p0;
        if (str == null || StringsKt.isBlank(str)) {
            p0 = JsonSerializer.Null;
        }
        if (p1 == null) {
            p1 = new NullPointerException("Invoked with null Throwable");
        }
        AFg1hSDK.e$default(aFLogger, aFg1gSDK, p0, p1, false, false, !p2, false, 64, null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.e()", imports = {}))
    @JvmStatic
    public static final void afErrorLog(String p0, Throwable p1, boolean p2) {
        AFLogger aFLogger = INSTANCE;
        AFg1gSDK aFg1gSDK = AFg1gSDK.OTHER;
        String str = p0;
        if (str == null || StringsKt.isBlank(str)) {
            p0 = JsonSerializer.Null;
        }
        String str2 = p0;
        if (p1 == null) {
            p1 = new NullPointerException("Invoked with null Throwable");
        }
        AFg1hSDK.e$default(aFLogger, aFg1gSDK, str2, p1, false, p2, false, false, 104, null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.e()", imports = {}))
    @JvmStatic
    public static final void afErrorLog(String p0, Throwable p1, boolean p2, boolean p3) {
        AFLogger aFLogger = INSTANCE;
        AFg1gSDK aFg1gSDK = AFg1gSDK.OTHER;
        String str = p0;
        if (str == null || StringsKt.isBlank(str)) {
            p0 = JsonSerializer.Null;
        }
        String str2 = p0;
        if (p1 == null) {
            p1 = new NullPointerException("Invoked with null Throwable");
        }
        AFg1hSDK.e$default(aFLogger, aFg1gSDK, str2, p1, false, p2, p3, false, 72, null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated since v6.13.0", replaceWith = @ReplaceWith(expression = "AFLogger.w()", imports = {}))
    @JvmStatic
    public static final void afWarnLog(String p0) {
        Intrinsics.checkNotNullParameter(p0, "");
        AFg1hSDK.w$default(INSTANCE, AFg1gSDK.OTHER, p0, false, 4, null);
    }

    @Metadata(m1394d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0007\u001a\u00020\u00028\u0007¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010"}, m1395d2 = {"Lcom/appsflyer/AFLogger$LogLevel;", "", "", "AFInAppEventType", "I", "getLevel", "()I", FirebaseAnalytics.Param.LEVEL, "p0", "<init>", "(Ljava/lang/String;II)V", "NONE", "ERROR", "WARNING", "INFO", "DEBUG", "VERBOSE"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
    /* loaded from: classes.dex */
    public enum LogLevel {
        NONE(0),
        ERROR(1),
        WARNING(2),
        INFO(3),
        DEBUG(4),
        VERBOSE(5);


        /* renamed from: AFInAppEventType, reason: from kotlin metadata */
        private final int com.google.firebase.analytics.FirebaseAnalytics.Param.LEVEL java.lang.String;

        LogLevel(int i) {
            this.com.google.firebase.analytics.FirebaseAnalytics.Param.LEVEL java.lang.String = i;
        }

        /* renamed from: getLevel, reason: from getter */
        public final int getCom.google.firebase.analytics.FirebaseAnalytics.Param.LEVEL java.lang.String() {
            return this.com.google.firebase.analytics.FirebaseAnalytics.Param.LEVEL java.lang.String;
        }
    }

    public static final void valueOf(AFg1hSDK[] aFg1hSDKArr) {
        Intrinsics.checkNotNullParameter(aFg1hSDKArr, "");
        Lazy lazy = values;
        Object value = lazy.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "");
        synchronized (((Set) value)) {
            Object value2 = lazy.getValue();
            Intrinsics.checkNotNullExpressionValue(value2, "");
            CollectionsKt.addAll((Set) value2, aFg1hSDKArr);
            Unit unit = Unit.INSTANCE;
        }
    }

    public static final void AFKeystoreWrapper(AFg1hSDK[] aFg1hSDKArr) {
        Intrinsics.checkNotNullParameter(aFg1hSDKArr, "");
        Lazy lazy = values;
        Object value = lazy.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "");
        synchronized (((Set) value)) {
            Object value2 = lazy.getValue();
            Intrinsics.checkNotNullExpressionValue(value2, "");
            ((Set) value2).removeAll(ArraysKt.toSet(aFg1hSDKArr));
            Unit unit = Unit.INSTANCE;
        }
    }

    public static final void values(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "");
        Lazy lazy = values;
        Object value = lazy.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "");
        synchronized (((Set) value)) {
            Object value2 = lazy.getValue();
            Intrinsics.checkNotNullExpressionValue(value2, "");
            Iterator it = ((Set) value2).iterator();
            while (it.hasNext()) {
                function1.invoke((AFg1hSDK) it.next());
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
