package com.p008ld.sdk.internal;

import android.app.Activity;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzm;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDValidate.kt */
/* loaded from: classes2.dex */
public final class LDValidate {
    public static final LDValidate INSTANCE = new LDValidate();

    private LDValidate() {
    }

    @JvmStatic
    public static final void notEmpty(String arg, String name) {
        Intrinsics.checkNotNullParameter(arg, "arg");
        Intrinsics.checkNotNullParameter(name, "name");
        if (arg.length() > 0) {
            return;
        }
        throw new IllegalArgumentException(("Argument '" + name + "' cannot be empty").toString());
    }

    @JvmStatic
    public static final void notNull(Object obj, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (obj != null) {
            return;
        }
        throw new NullPointerException("Argument '" + name + "' cannot be null");
    }

    @JvmStatic
    public static final void sdkInitialized() {
        if (!LDSdk.isInitialized()) {
            throw new LDSdkNotInitializedException("The SDK has not been initialized, make sure to call LDSdk.sdkInitialized() first.");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
    
        if ((((java.lang.CharSequence) r3).length() == 0) == false) goto L9;
     */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> boolean notNullOrEmpty(java.lang.Object r3, final java.lang.String r4, final com.p008ld.sdk.internal.LDQueryCallback<T> r5) {
        /*
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            boolean r0 = r3 instanceof java.lang.String
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L19
            r0 = r3
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L16
            r0 = r1
            goto L17
        L16:
            r0 = r2
        L17:
            if (r0 != 0) goto L28
        L19:
            boolean r0 = r3 instanceof java.util.List
            if (r0 == 0) goto L26
            r0 = r3
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L28
        L26:
            if (r3 != 0) goto L33
        L28:
            com.ld.sdk.internal.LDValidate$notNullOrEmpty$1 r3 = new com.ld.sdk.internal.LDValidate$notNullOrEmpty$1
            r3.<init>()
            kotlin.jvm.functions.Function0 r3 = (kotlin.jvm.functions.Function0) r3
            com.p008ld.sdk.util.zzm.zza(r3)
            r1 = r2
        L33:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.internal.LDValidate.notNullOrEmpty(java.lang.Object, java.lang.String, com.ld.sdk.internal.LDQueryCallback):boolean");
    }

    public static /* synthetic */ boolean notNullOrEmpty$default(String str, String str2, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = new Function1<LDException, Unit>() { // from class: com.ld.sdk.internal.LDValidate$notNullOrEmpty$2
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LDException lDException) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LDException lDException) {
                    invoke2(lDException);
                    return Unit.INSTANCE;
                }
            };
        }
        return notNullOrEmpty(str, str2, (Function1<? super LDException, Unit>) function1);
    }

    @JvmStatic
    public static final boolean notNullOrEmpty(String str, final String name, final Function1<? super LDException, Unit> onCompletion) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(onCompletion, "onCompletion");
        String str2 = str;
        if (!(str2 == null || str2.length() == 0)) {
            return true;
        }
        zzm.zza(new Function0<Unit>() { // from class: com.ld.sdk.internal.LDValidate$notNullOrEmpty$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                onCompletion.invoke(new LDException("Argument '" + name + "' cannot be null or empty"));
            }
        });
        return false;
    }

    @JvmStatic
    public static final boolean checkNullOrEmpty(LDUser lDUser, final LDCallback<Boolean> lDCallback) {
        if (lDUser != null) {
            return true;
        }
        zzm.zza(new Function0<Unit>() { // from class: com.ld.sdk.internal.LDValidate$checkNullOrEmpty$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LDCallback<Boolean> lDCallback2 = lDCallback;
                if (lDCallback2 != null) {
                    lDCallback2.done(false, new LDNotLoginException("Not Logged In"));
                }
            }
        });
        return false;
    }

    @JvmStatic
    public static final boolean checkNullOrEmpty(Object obj, final String str, final LDCallback<Boolean> lDCallback) {
        if ((!(obj instanceof Activity) || !((Activity) obj).isFinishing()) && obj != null) {
            return true;
        }
        zzm.zza(new Function0<Unit>() { // from class: com.ld.sdk.internal.LDValidate$checkNullOrEmpty$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LDCallback<Boolean> lDCallback2 = lDCallback;
                if (lDCallback2 != null) {
                    lDCallback2.done(false, new LDException("Argument '" + str + "' cannot be null"));
                }
            }
        });
        return false;
    }

    @JvmStatic
    public static final boolean checkNullOrEmpty(Object obj, final LDLoginCallback lDLoginCallback) {
        if (!(obj instanceof ConfigBean) || ((ConfigBean) obj).sdkCustomerServiceConfigVoList != null) {
            return true;
        }
        zzm.zza(new Function0<Unit>() { // from class: com.ld.sdk.internal.LDValidate$checkNullOrEmpty$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LDLoginCallback lDLoginCallback2 = LDLoginCallback.this;
                if (lDLoginCallback2 != null) {
                    lDLoginCallback2.onError(zzi.zza(LDSdk.getApp(), "ld_not_init_error_text"));
                }
            }
        });
        return false;
    }

    @JvmStatic
    public static final boolean checkNullOrEmpty(Object obj, final String str, final LDPayCallback lDPayCallback) {
        if ((!(obj instanceof Activity) || !((Activity) obj).isFinishing()) && obj != null) {
            return true;
        }
        zzm.zza(new Function0<Unit>() { // from class: com.ld.sdk.internal.LDValidate$checkNullOrEmpty$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LDPayCallback lDPayCallback2 = LDPayCallback.this;
                if (lDPayCallback2 != null) {
                    lDPayCallback2.onError(new LDException("Argument '" + str + "' cannot be null"));
                }
            }
        });
        return false;
    }
}
