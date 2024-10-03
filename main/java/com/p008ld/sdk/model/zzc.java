package com.p008ld.sdk.model;

import com.facebook.internal.ServerProtocol;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.GsonBuilder;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.bean.LDResult;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.bean.ClaimGiftBagInfo;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.core.bean.CouponBean;
import com.p008ld.sdk.core.bean.CreateOrderBean;
import com.p008ld.sdk.core.bean.GameRoleInfo;
import com.p008ld.sdk.core.bean.GiftBagInfo;
import com.p008ld.sdk.core.bean.LDGradeQueryInfo;
import com.p008ld.sdk.core.bean.LDProductInfo;
import com.p008ld.sdk.core.bean.LDProductQueryParam;
import com.p008ld.sdk.core.bean.LDUploadAuth;
import com.p008ld.sdk.core.bean.LdGamePayInfo;
import com.p008ld.sdk.core.bean.LoginMode;
import com.p008ld.sdk.core.bean.LoginResultInfo;
import com.p008ld.sdk.core.bean.OrderRecordBean;
import com.p008ld.sdk.core.bean.OrderStatusBean;
import com.p008ld.sdk.core.bean.PayInfo;
import com.p008ld.sdk.core.bean.PayOrderBean;
import com.p008ld.sdk.core.bean.PayOrderInfo;
import com.p008ld.sdk.core.bean.PayUrlBean;
import com.p008ld.sdk.core.bean.SendType;
import com.p008ld.sdk.core.bean.UserWalletResponseBean;
import com.p008ld.sdk.internal.LDApiCallback;
import com.p008ld.sdk.internal.LDApiOkCallback;
import com.p008ld.sdk.internal.LDCallback;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDNative;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.track.LDTrackProperties;
import com.p008ld.sdk.track.LDTrackRequest;
import com.p008ld.sdk.util.LDUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import retrofit2.Call;

/* compiled from: LDLoginModel.kt */
/* loaded from: classes2.dex */
public final class zzc extends com.p008ld.sdk.zzd.zzd<LDLoginService> {
    private final TreeMap<String, String> zzb = new TreeMap<>();

    @Override // com.p008ld.sdk.zzd.zzd
    protected Class<LDLoginService> zza() {
        return LDLoginService.class;
    }

    public final Call<LDResult<LDUser>> zza(String userId, String userToken, LDQueryCallback<LDUser> lDQueryCallback, String str, Boolean bool) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(userToken, "userToken");
        Map<String, String> mutableMap = MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("loginMode", LoginMode.AUTO.getValue()), TuplesKt.m1402to("userId", userId), TuplesKt.m1402to("auth", userToken)));
        if (str != null) {
            mutableMap.put("code", str);
        }
        Call<LDResult<LDUser>> userLogin = zzd().userLogin(com.p008ld.sdk.util.zzm.zza(zza(mutableMap, bool)));
        userLogin.enqueue(new zzj(lDQueryCallback));
        return userLogin;
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzj extends LDApiCallback<LDUser> {
        final /* synthetic */ LDQueryCallback<LDUser> zza;

        zzj(LDQueryCallback<LDUser> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<LDUser> zza;
            final /* synthetic */ LDUser zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<LDUser> lDQueryCallback, LDUser lDUser, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = lDUser;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                LDQueryCallback<LDUser> lDQueryCallback = this.zza;
                if (lDQueryCallback != null) {
                    lDQueryCallback.done((LDQueryCallback<LDUser>) this.zzb, this.zzc);
                }
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LDUser lDUser, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, lDUser, lDException));
        }
    }

    public final void zza(LoginMode method, String accessToken, LDQueryCallback<LDUser> lDQueryCallback, String str, String str2, String str3, Boolean bool) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Map<String, String> mutableMap = MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("loginMode", method.getValue()), TuplesKt.m1402to("auth", accessToken)));
        if (str3 != null) {
            mutableMap.put("code", str3);
        }
        if (str != null) {
            mutableMap.put("userId", str);
        }
        if (str2 != null) {
            mutableMap.put("token", str2);
        }
        zzd().userLogin(com.p008ld.sdk.util.zzm.zza(zza(mutableMap, bool))).enqueue(new zzm(lDQueryCallback));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzm extends LDApiCallback<LDUser> {
        final /* synthetic */ LDQueryCallback<LDUser> zza;

        zzm(LDQueryCallback<LDUser> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<LDUser> zza;
            final /* synthetic */ LDUser zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<LDUser> lDQueryCallback, LDUser lDUser, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = lDUser;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                LDQueryCallback<LDUser> lDQueryCallback = this.zza;
                if (lDQueryCallback != null) {
                    lDQueryCallback.done((LDQueryCallback<LDUser>) this.zzb, this.zzc);
                }
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LDUser lDUser, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, lDUser, lDException));
        }
    }

    public final void zza(String username, String email, String password, LDQueryCallback<LDUser> lDQueryCallback, String str, Boolean bool) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(password, "password");
        Map<String, String> mutableMap = MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("loginMode", LoginMode.USERNAME.getValue()), TuplesKt.m1402to("email", email), TuplesKt.m1402to("username", username), TuplesKt.m1402to("auth", zza(password))));
        if (str != null) {
            mutableMap.put("code", str);
        }
        zzd().userLogin(com.p008ld.sdk.util.zzm.zza(zza(mutableMap, bool))).enqueue(new zzk(lDQueryCallback));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzk extends LDApiCallback<LDUser> {
        final /* synthetic */ LDQueryCallback<LDUser> zza;

        zzk(LDQueryCallback<LDUser> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<LDUser> zza;
            final /* synthetic */ LDUser zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<LDUser> lDQueryCallback, LDUser lDUser, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = lDUser;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                LDQueryCallback<LDUser> lDQueryCallback = this.zza;
                if (lDQueryCallback != null) {
                    lDQueryCallback.done((LDQueryCallback<LDUser>) this.zzb, this.zzc);
                }
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LDUser lDUser, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, lDUser, lDException));
        }
    }

    public final void zzb(String email, String auth, String password, LDQueryCallback<LDUser> lDQueryCallback, String str, Boolean bool) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(auth, "auth");
        Intrinsics.checkNotNullParameter(password, "password");
        Map<String, String> mutableMap = MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("loginMode", LoginMode.EMAIL.getValue()), TuplesKt.m1402to("email", email), TuplesKt.m1402to("auth", auth), TuplesKt.m1402to("pwd", zza(password))));
        if (str != null) {
            mutableMap.put("code", str);
        }
        zzd().userLogin(com.p008ld.sdk.util.zzm.zza(zza(mutableMap, bool))).enqueue(new zzl(lDQueryCallback));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzl extends LDApiCallback<LDUser> {
        final /* synthetic */ LDQueryCallback<LDUser> zza;

        zzl(LDQueryCallback<LDUser> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<LDUser> zza;
            final /* synthetic */ LDUser zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<LDUser> lDQueryCallback, LDUser lDUser, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = lDUser;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                LDQueryCallback<LDUser> lDQueryCallback = this.zza;
                if (lDQueryCallback != null) {
                    lDQueryCallback.done((LDQueryCallback<LDUser>) this.zzb, this.zzc);
                }
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LDUser lDUser, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, lDUser, lDException));
        }
    }

    public final void zza(SendType sendType, String email, Function1<? super LDException, Unit> onCompletion) {
        Intrinsics.checkNotNullParameter(sendType, "sendType");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(onCompletion, "onCompletion");
        zzd().sendEmail(com.p008ld.sdk.util.zzm.zza(zza(MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("email", email), TuplesKt.m1402to("sendType", sendType.getValue()))), true, true))).enqueue(new zzy(onCompletion));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzy extends LDApiOkCallback<Object> {
        final /* synthetic */ Function1<LDException, Unit> zza;

        /* JADX WARN: Multi-variable type inference failed */
        zzy(Function1<? super LDException, Unit> function1) {
            this.zza = function1;
        }

        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ Function1<LDException, Unit> zza;
            final /* synthetic */ boolean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            zza(Function1<? super LDException, Unit> function1, boolean z, LDException lDException) {
                super(0);
                this.zza = function1;
                this.zzb = z;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.invoke(this.zzb ? null : this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiOkCallback
        public void done(boolean z, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, z, lDException));
        }
    }

    public final void zza(String email, String emailVerifyCode, String password, Function1<? super LDException, Unit> onCompletion) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(emailVerifyCode, "emailVerifyCode");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(onCompletion, "onCompletion");
        zzd().updateUser(com.p008ld.sdk.util.zzm.zza((Map<?, ?>) zza(this, MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("email", email), TuplesKt.m1402to("auth", emailVerifyCode), TuplesKt.m1402to("pwd", zza(password)))), false, false, 6, (Object) null))).enqueue(new zzz(onCompletion));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzz extends LDApiOkCallback<Object> {
        final /* synthetic */ Function1<LDException, Unit> zza;

        /* JADX WARN: Multi-variable type inference failed */
        zzz(Function1<? super LDException, Unit> function1) {
            this.zza = function1;
        }

        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ Function1<LDException, Unit> zza;
            final /* synthetic */ boolean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            zza(Function1<? super LDException, Unit> function1, boolean z, LDException lDException) {
                super(0);
                this.zza = function1;
                this.zzb = z;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.invoke(this.zzb ? null : this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiOkCallback
        public void done(boolean z, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, z, lDException));
        }
    }

    public final void zza(String email, String emailVerifyCode, String userId, Function1<? super LDException, Unit> onCompletion, String str, String str2) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(emailVerifyCode, "emailVerifyCode");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(onCompletion, "onCompletion");
        Map mutableMap = MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("email", email), TuplesKt.m1402to("auth", emailVerifyCode), TuplesKt.m1402to("userId", userId), TuplesKt.m1402to("loginMode", LoginMode.EMAIL.getValue())));
        if (str != null) {
            mutableMap.put("pwd", zza(str));
        }
        if (str2 != null) {
            mutableMap.put("code", str2);
        }
        zzd().bindUser(com.p008ld.sdk.util.zzm.zza((Map<?, ?>) zza(this, mutableMap, false, false, 6, (Object) null))).enqueue(new zza(onCompletion));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zza extends LDApiOkCallback<Object> {
        final /* synthetic */ Function1<LDException, Unit> zza;

        /* JADX WARN: Multi-variable type inference failed */
        zza(Function1<? super LDException, Unit> function1) {
            this.zza = function1;
        }

        /* compiled from: LDLoginModel.kt */
        /* renamed from: com.ld.sdk.model.zzc$zza$zza, reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        static final class C3255zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ Function1<LDException, Unit> zza;
            final /* synthetic */ boolean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C3255zza(Function1<? super LDException, Unit> function1, boolean z, LDException lDException) {
                super(0);
                this.zza = function1;
                this.zzb = z;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.invoke(this.zzb ? null : this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiOkCallback
        public void done(boolean z, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new C3255zza(this.zza, z, lDException));
        }
    }

    public final void zza(LoginMode mode, String userId, String auth, Function1<? super LDException, Unit> onCompletion, String str) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(auth, "auth");
        Intrinsics.checkNotNullParameter(onCompletion, "onCompletion");
        Map mutableMap = MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("auth", auth), TuplesKt.m1402to("userId", userId), TuplesKt.m1402to("loginMode", mode.getValue())));
        if (str != null) {
            mutableMap.put("code", str);
        }
        zzd().bindUser(com.p008ld.sdk.util.zzm.zza((Map<?, ?>) zza(this, mutableMap, false, false, 6, (Object) null))).enqueue(new zzb(onCompletion));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzb extends LDApiOkCallback<Object> {
        final /* synthetic */ Function1<LDException, Unit> zza;

        /* JADX WARN: Multi-variable type inference failed */
        zzb(Function1<? super LDException, Unit> function1) {
            this.zza = function1;
        }

        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ Function1<LDException, Unit> zza;
            final /* synthetic */ boolean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            zza(Function1<? super LDException, Unit> function1, boolean z, LDException lDException) {
                super(0);
                this.zza = function1;
                this.zzb = z;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.invoke(this.zzb ? null : this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiOkCallback
        public void done(boolean z, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, z, lDException));
        }
    }

    public final void zza(String str, String str2, String userId, String token, Function1<? super LDException, Unit> onCompletion) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(onCompletion, "onCompletion");
        Map mutableMap = MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("uid", userId), TuplesKt.m1402to("token", token)));
        if (str != null) {
            mutableMap.put("headPortraitUrl", str);
        }
        if (str2 != null) {
            mutableMap.put("nickname", str2);
        }
        zzd().updateUserBasicInfo(com.p008ld.sdk.util.zzm.zza((Map<?, ?>) zza(this, mutableMap, false, false, 6, (Object) null))).enqueue(new zzaa(onCompletion));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzaa extends LDApiOkCallback<Object> {
        final /* synthetic */ Function1<LDException, Unit> zza;

        /* JADX WARN: Multi-variable type inference failed */
        zzaa(Function1<? super LDException, Unit> function1) {
            this.zza = function1;
        }

        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ Function1<LDException, Unit> zza;
            final /* synthetic */ boolean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            zza(Function1<? super LDException, Unit> function1, boolean z, LDException lDException) {
                super(0);
                this.zza = function1;
                this.zzb = z;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.invoke(this.zzb ? null : this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiOkCallback
        public void done(boolean z, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, z, lDException));
        }
    }

    public final void zza(String userId, String token, String imgType, LDQueryCallback<LDUploadAuth> lDQueryCallback) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(imgType, "imgType");
        zzd().genProfileImgUploadAuth(com.p008ld.sdk.util.zzm.zza((Map<?, ?>) zza(this, MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("uid", userId), TuplesKt.m1402to("token", token), TuplesKt.m1402to("imgType", imgType))), false, false, 6, (Object) null))).enqueue(new zzf(lDQueryCallback));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzf extends LDApiCallback<LDUploadAuth> {
        final /* synthetic */ LDQueryCallback<LDUploadAuth> zza;

        zzf(LDQueryCallback<LDUploadAuth> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LDUploadAuth lDUploadAuth, LDException lDException) {
            LDQueryCallback<LDUploadAuth> lDQueryCallback = this.zza;
            if (lDQueryCallback != null) {
                lDQueryCallback.done((LDQueryCallback<LDUploadAuth>) lDUploadAuth, lDException);
            }
        }

        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<ConfigBean> zza;
            final /* synthetic */ ConfigBean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<ConfigBean> lDQueryCallback, ConfigBean configBean, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = configBean;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<ConfigBean>) this.zzb, this.zzc);
            }
        }
    }

    public final void zza(String userId, String token, LDQueryCallback<LDUploadAuth> lDQueryCallback) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(token, "token");
        zzd().genLogUploadAuth(com.p008ld.sdk.util.zzm.zza((Map<?, ?>) zza(this, MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("uid", userId), TuplesKt.m1402to("token", token))), false, false, 6, (Object) null))).enqueue(new zze(lDQueryCallback));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zze extends LDApiCallback<LDUploadAuth> {
        final /* synthetic */ LDQueryCallback<LDUploadAuth> zza;

        zze(LDQueryCallback<LDUploadAuth> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LDUploadAuth lDUploadAuth, LDException lDException) {
            LDQueryCallback<LDUploadAuth> lDQueryCallback = this.zza;
            if (lDQueryCallback != null) {
                lDQueryCallback.done((LDQueryCallback<LDUploadAuth>) lDUploadAuth, lDException);
            }
        }
    }

    public final void zza(String code, LDQueryCallback<LDUser> callBack) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        zzd().pcLogin(com.p008ld.sdk.util.zzm.zza((Map<?, ?>) MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("code", code))))).enqueue(new zzu(callBack));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzu extends LDApiCallback<LDUser> {
        final /* synthetic */ LDQueryCallback<LDUser> zza;

        zzu(LDQueryCallback<LDUser> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<LDUser> zza;
            final /* synthetic */ LDUser zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<LDUser> lDQueryCallback, LDUser lDUser, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = lDUser;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<LDUser>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LDUser lDUser, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, lDUser, lDException));
        }
    }

    private final String zza(String str) {
        String str2 = "\"" + str + "\"";
        Intrinsics.checkNotNullExpressionValue(str2, "StringBuilder().apply {\n…\\\"\")\n        }.toString()");
        String lowerCase = LDNative.INSTANCE.encrypt(str2).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return lowerCase;
    }

    private final Map<String, String> zza(Map<String, String> map, Boolean bool) {
        map.put("isCoolingOffPeriod", String.valueOf(bool));
        map.put("mainChannelId", LDSdk.getChannelId());
        map.put("subChannelId", LDSdk.getSunChannelId());
        map.put("phoneBrand", com.p008ld.sdk.util.zze.zzb());
        map.put("phoneModel", com.p008ld.sdk.util.zze.zzc());
        return zza(map, false, true);
    }

    static /* synthetic */ Map zza(zzc zzcVar, Map map, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return zzcVar.zza((Map<String, String>) map, z, z2);
    }

    private final Map<String, String> zza(Map<String, String> map, boolean z, boolean z2) {
        map.put("appId", "6666");
        map.put("subAppId", LDSdk.getAppId());
        if (z) {
            map.put("languageCode", com.p008ld.sdk.util.zze.zzj());
        }
        if (z2) {
            map.put("timeZone", com.p008ld.sdk.util.zze.zzk());
        }
        map.put("mid", com.p008ld.sdk.util.zze.zza(com.p008ld.sdk.util.zze.zza, false, null, 3, null));
        return map;
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzg extends LDApiCallback<ConfigBean> {
        final /* synthetic */ LDQueryCallback<ConfigBean> zza;

        zzg(LDQueryCallback<ConfigBean> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<ConfigBean> zza;
            final /* synthetic */ ConfigBean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<ConfigBean> lDQueryCallback, ConfigBean configBean, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = configBean;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<ConfigBean>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(ConfigBean configBean, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, configBean, lDException));
        }
    }

    public final void zza(LDQueryCallback<ConfigBean> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        zzf().getConfig(zzb()).enqueue(new zzg(callback));
    }

    public final void zzb(String uid, String token, LDQueryCallback<LoginResultInfo> callback) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(callback, "callback");
        HashMap hashMap = new HashMap();
        hashMap.put("uid", uid);
        hashMap.put("token", token);
        hashMap.put("cpAppId", LDSdk.getAppId());
        hashMap.put("channelId", LDSdk.getChannelId());
        hashMap.put("subChannelId", LDSdk.getSunChannelId());
        hashMap.put("appId", "6666");
        hashMap.put("sourceType", "3");
        String zzc = com.p008ld.sdk.util.zzi.zzc();
        Intrinsics.checkNotNullExpressionValue(zzc, "getDeviceId()");
        hashMap.put("mac", zzc);
        zzf().loginSpace(zza(hashMap), com.p008ld.sdk.util.zzm.zza(hashMap)).enqueue(new zzn(callback));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzn extends LDApiCallback<LoginResultInfo> {
        final /* synthetic */ LDQueryCallback<LoginResultInfo> zza;

        zzn(LDQueryCallback<LoginResultInfo> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<LoginResultInfo> zza;
            final /* synthetic */ LoginResultInfo zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<LoginResultInfo> lDQueryCallback, LoginResultInfo loginResultInfo, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = loginResultInfo;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<LoginResultInfo>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LoginResultInfo loginResultInfo, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, loginResultInfo, lDException));
        }
    }

    public final void zzc(String virtualCurrencyType, String firstCountryCode, LDQueryCallback<List<LDGradeQueryInfo>> lDQueryCallback) {
        Intrinsics.checkNotNullParameter(virtualCurrencyType, "virtualCurrencyType");
        Intrinsics.checkNotNullParameter(firstCountryCode, "firstCountryCode");
        zze().queryGrades(com.p008ld.sdk.util.zzm.zza((Map<?, ?>) MapsKt.toMutableMap(MapsKt.mapOf(TuplesKt.m1402to("virtualCurrencyType", virtualCurrencyType), TuplesKt.m1402to("firstCountryCode", firstCountryCode))))).enqueue(new zzr(lDQueryCallback));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzr extends LDApiCallback<List<? extends LDGradeQueryInfo>> {
        final /* synthetic */ LDQueryCallback<List<LDGradeQueryInfo>> zza;

        zzr(LDQueryCallback<List<LDGradeQueryInfo>> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<List<LDGradeQueryInfo>> zza;
            final /* synthetic */ List<LDGradeQueryInfo> zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<List<LDGradeQueryInfo>> lDQueryCallback, List<LDGradeQueryInfo> list, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = list;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                LDQueryCallback<List<LDGradeQueryInfo>> lDQueryCallback = this.zza;
                if (lDQueryCallback != null) {
                    lDQueryCallback.done((LDQueryCallback<List<LDGradeQueryInfo>>) this.zzb, this.zzc);
                }
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(List<LDGradeQueryInfo> list, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, list, lDException));
        }
    }

    public final void zza(PayInfo info, LDQueryCallback<PayUrlBean> callBack) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        HashMap hashMap = new HashMap();
        hashMap.put("appId", LDSdk.getAppId());
        hashMap.put("channelId", LDSdk.getChannelId());
        String str = info.gradeChannelTypeId;
        Intrinsics.checkNotNullExpressionValue(str, "info.gradeChannelTypeId");
        hashMap.put("gradeChannelTypeId", str);
        String str2 = info.regionCode;
        Intrinsics.checkNotNullExpressionValue(str2, "info.regionCode");
        hashMap.put("regionCode", str2);
        hashMap.put("source", "1");
        hashMap.put("subChannelId", LDSdk.getSunChannelId());
        String str3 = info.uid;
        Intrinsics.checkNotNullExpressionValue(str3, "info.uid");
        hashMap.put("uid", str3);
        zzf().rechargeLB(zza(hashMap), com.p008ld.sdk.util.zzm.zza(hashMap)).enqueue(new zzw(callBack));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzw extends LDApiCallback<PayUrlBean> {
        final /* synthetic */ LDQueryCallback<PayUrlBean> zza;

        zzw(LDQueryCallback<PayUrlBean> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<PayUrlBean> zza;
            final /* synthetic */ PayUrlBean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<PayUrlBean> lDQueryCallback, PayUrlBean payUrlBean, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = payUrlBean;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<PayUrlBean>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(PayUrlBean payUrlBean, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, payUrlBean, lDException));
        }
    }

    public final void zza(String ldUid, LdGamePayInfo info, LDQueryCallback<CreateOrderBean> callBack) {
        Intrinsics.checkNotNullParameter(ldUid, "ldUid");
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        HashMap hashMap = new HashMap();
        hashMap.put("amount", String.valueOf(info.commodityPrice));
        hashMap.put("appId", LDSdk.getAppId());
        hashMap.put("channelId", LDSdk.getChannelId());
        String str = info.cpOrderId;
        Intrinsics.checkNotNullExpressionValue(str, "info.cpOrderId");
        hashMap.put("cpOrderId", str);
        String str2 = info.cpUserId;
        Intrinsics.checkNotNullExpressionValue(str2, "info.cpUserId");
        hashMap.put("cpUid", str2);
        String str3 = info.currencyType;
        Intrinsics.checkNotNullExpressionValue(str3, "info.currencyType");
        hashMap.put("payCurrency", str3);
        hashMap.put("productId", info.productId.toString());
        String str4 = info.tradeName;
        Intrinsics.checkNotNullExpressionValue(str4, "info.tradeName");
        hashMap.put("productName", str4);
        hashMap.put("subChannelId", LDSdk.getSunChannelId());
        hashMap.put("uid", ldUid);
        if (!com.p008ld.sdk.util.zzi.zza((CharSequence) info.transparentParams)) {
            String str5 = info.transparentParams;
            Intrinsics.checkNotNullExpressionValue(str5, "info.transparentParams");
            hashMap.put("transparentParams", str5);
        }
        zzf().createDirectOrder(zza(hashMap), com.p008ld.sdk.util.zzm.zza(hashMap)).enqueue(new zzd(callBack));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzd extends LDApiCallback<CreateOrderBean> {
        final /* synthetic */ LDQueryCallback<CreateOrderBean> zza;

        zzd(LDQueryCallback<CreateOrderBean> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<CreateOrderBean> zza;
            final /* synthetic */ CreateOrderBean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<CreateOrderBean> lDQueryCallback, CreateOrderBean createOrderBean, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = createOrderBean;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<CreateOrderBean>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(CreateOrderBean createOrderBean, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, createOrderBean, lDException));
        }
    }

    public final void zza(String ldUid, PayOrderInfo info, LDQueryCallback<PayOrderBean> callBack) {
        Intrinsics.checkNotNullParameter(ldUid, "ldUid");
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        HashMap hashMap = new HashMap();
        hashMap.put("appId", LDSdk.getAppId());
        String str = info.gradeChannelTypeId;
        Intrinsics.checkNotNullExpressionValue(str, "info.gradeChannelTypeId");
        hashMap.put("gradeChannelTypeId", str);
        String str2 = info.orderId;
        Intrinsics.checkNotNullExpressionValue(str2, "info.orderId");
        hashMap.put("orderId", str2);
        String str3 = info.regionCode;
        Intrinsics.checkNotNullExpressionValue(str3, "info.regionCode");
        hashMap.put("regionCode", str3);
        hashMap.put("source", "1");
        hashMap.put("uid", ldUid);
        zzf().payDirectOrder(zza(hashMap), com.p008ld.sdk.util.zzm.zza(hashMap)).enqueue(new zzo(callBack));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzo extends LDApiCallback<PayOrderBean> {
        final /* synthetic */ LDQueryCallback<PayOrderBean> zza;

        zzo(LDQueryCallback<PayOrderBean> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<PayOrderBean> zza;
            final /* synthetic */ PayOrderBean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<PayOrderBean> lDQueryCallback, PayOrderBean payOrderBean, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = payOrderBean;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<PayOrderBean>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(PayOrderBean payOrderBean, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, payOrderBean, lDException));
        }
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzt extends LDApiCallback<OrderStatusBean> {
        final /* synthetic */ LDQueryCallback<OrderStatusBean> zza;

        zzt(LDQueryCallback<OrderStatusBean> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<OrderStatusBean> zza;
            final /* synthetic */ OrderStatusBean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<OrderStatusBean> lDQueryCallback, OrderStatusBean orderStatusBean, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = orderStatusBean;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<OrderStatusBean>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(OrderStatusBean orderStatusBean, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, orderStatusBean, lDException));
        }
    }

    public final void zzb(String orderId, LDQueryCallback<OrderStatusBean> callBack) {
        Intrinsics.checkNotNullParameter(orderId, "orderId");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        zzf().queryOrderStatus(zzb(), orderId).enqueue(new zzt(callBack));
    }

    public final void zzc(String userId, LDQueryCallback<List<OrderRecordBean>> callBack) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        HashMap hashMap = new HashMap();
        hashMap.put("userId", userId);
        zzf().queryLBList(com.p008ld.sdk.util.zzm.zza(hashMap)).enqueue(new zzs(callBack));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzs extends LDApiCallback<List<? extends OrderRecordBean>> {
        final /* synthetic */ LDQueryCallback<List<OrderRecordBean>> zza;

        zzs(LDQueryCallback<List<OrderRecordBean>> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<List<OrderRecordBean>> zza;
            final /* synthetic */ List<OrderRecordBean> zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            zza(LDQueryCallback<List<OrderRecordBean>> lDQueryCallback, List<? extends OrderRecordBean> list, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = list;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<List<OrderRecordBean>>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(List<? extends OrderRecordBean> list, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, list, lDException));
        }
    }

    public final void zzd(String userId, LDQueryCallback<List<OrderRecordBean>> callBack) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        HashMap hashMap = new HashMap();
        hashMap.put("userId", userId);
        zzf().queryDirectPurchaseList(com.p008ld.sdk.util.zzm.zza(hashMap)).enqueue(new zzp(callBack));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzp extends LDApiCallback<List<? extends OrderRecordBean>> {
        final /* synthetic */ LDQueryCallback<List<OrderRecordBean>> zza;

        zzp(LDQueryCallback<List<OrderRecordBean>> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<List<OrderRecordBean>> zza;
            final /* synthetic */ List<OrderRecordBean> zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            zza(LDQueryCallback<List<OrderRecordBean>> lDQueryCallback, List<? extends OrderRecordBean> list, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = list;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<List<OrderRecordBean>>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(List<? extends OrderRecordBean> list, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, list, lDException));
        }
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzi extends LDApiCallback<UserWalletResponseBean> {
        final /* synthetic */ LDQueryCallback<UserWalletResponseBean> zza;

        zzi(LDQueryCallback<UserWalletResponseBean> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<UserWalletResponseBean> zza;
            final /* synthetic */ UserWalletResponseBean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<UserWalletResponseBean> lDQueryCallback, UserWalletResponseBean userWalletResponseBean, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = userWalletResponseBean;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<UserWalletResponseBean>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(UserWalletResponseBean userWalletResponseBean, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, userWalletResponseBean, lDException));
        }
    }

    public final void zze(String userId, LDQueryCallback<UserWalletResponseBean> callBack) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        zzf().getUserWallet(zzb(), userId).enqueue(new zzi(callBack));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzh extends LDApiCallback<List<? extends CouponBean>> {
        final /* synthetic */ LDQueryCallback<List<CouponBean>> zza;

        zzh(LDQueryCallback<List<CouponBean>> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<List<CouponBean>> zza;
            final /* synthetic */ List<CouponBean> zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            zza(LDQueryCallback<List<CouponBean>> lDQueryCallback, List<? extends CouponBean> list, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = list;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<List<CouponBean>>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(List<? extends CouponBean> list, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, list, lDException));
        }
    }

    public final void zza(String userId, int i, LDQueryCallback<List<CouponBean>> callBack) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        zzf().getUserCouponList(zzb(), userId, i).enqueue(new zzh(callBack));
    }

    public final void zza(GameRoleInfo gameRoleInfo, String userId, LDCallback<Boolean> callback) {
        Intrinsics.checkNotNullParameter(gameRoleInfo, "gameRoleInfo");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        HashMap hashMap = new HashMap();
        String str = gameRoleInfo.roleId;
        Intrinsics.checkNotNullExpressionValue(str, "gameRoleInfo.roleId");
        hashMap.put("cpRoleId", str);
        String str2 = gameRoleInfo.level;
        Intrinsics.checkNotNullExpressionValue(str2, "gameRoleInfo.level");
        hashMap.put("cpRoleLevel", str2);
        String str3 = gameRoleInfo.roleName;
        Intrinsics.checkNotNullExpressionValue(str3, "gameRoleInfo.roleName");
        hashMap.put("cpRoleName", str3);
        String str4 = gameRoleInfo.roleType;
        Intrinsics.checkNotNullExpressionValue(str4, "gameRoleInfo.roleType");
        hashMap.put("cpRoleType", str4);
        String str5 = gameRoleInfo.serverId;
        Intrinsics.checkNotNullExpressionValue(str5, "gameRoleInfo.serverId");
        hashMap.put("cpServiceId", str5);
        String str6 = gameRoleInfo.serverName;
        Intrinsics.checkNotNullExpressionValue(str6, "gameRoleInfo.serverName");
        hashMap.put("cpServiceName", str6);
        String str7 = gameRoleInfo.partyName;
        Intrinsics.checkNotNullExpressionValue(str7, "gameRoleInfo.partyName");
        hashMap.put("partyName", str7);
        hashMap.put("cpVipLevel", String.valueOf(gameRoleInfo.vipLevel));
        hashMap.put("powerNum", String.valueOf(gameRoleInfo.powerNum));
        hashMap.put("userId", userId);
        zzf().reportRoleInfo(zza(hashMap), com.p008ld.sdk.util.zzm.zza(hashMap)).enqueue(new zzx(callback));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzx extends LDApiOkCallback<Object> {
        final /* synthetic */ LDCallback<Boolean> zza;

        zzx(LDCallback<Boolean> lDCallback) {
            this.zza = lDCallback;
        }

        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDCallback<Boolean> zza;
            final /* synthetic */ boolean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDCallback<Boolean> lDCallback, boolean z, LDException lDException) {
                super(0);
                this.zza = lDCallback;
                this.zzb = z;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done(Boolean.valueOf(this.zzb), this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiOkCallback
        public void done(boolean z, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, z, lDException));
        }
    }

    private final Map<String, String> zzb() {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", LDSdk.getAppId());
        String valueOf = String.valueOf(System.currentTimeMillis());
        hashMap.put("time", valueOf);
        String zza2 = com.p008ld.sdk.util.zzi.zza(LDSdk.getAppKey() + valueOf);
        Intrinsics.checkNotNullExpressionValue(zza2, "md5(LDSdk.getAppKey() + time)");
        hashMap.put("auth", zza2);
        return hashMap;
    }

    private final Map<String, String> zza(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
        String zza2 = zza(map, valueOf);
        hashMap.put("time", valueOf);
        hashMap.put("paySign", zza2);
        return hashMap;
    }

    private final String zza(Map<String, String> map, String str) {
        com.p008ld.sdk.util.zzq.zza(map);
        this.zzb.clear();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.zzb.put(entry.getKey(), entry.getValue());
        }
        return zzb(this.zzb, str);
    }

    private final String zzb(Map<String, String> map, String str) {
        map.remove("sign");
        String paramsJsonStr = new GsonBuilder().disableHtmlEscaping().create().toJson(map) + str + LDSdk.getAppKey();
        Intrinsics.checkNotNullExpressionValue(paramsJsonStr, "paramsJsonStr");
        byte[] bytes = paramsJsonStr.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        String md5 = LDUtil.md5(bytes);
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String upperCase = md5.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        return upperCase;
    }

    public final void zza(String userId, String eventKey, int i, String str, String str2, String str3, LDCallback<Boolean> lDCallback) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(eventKey, "eventKey");
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        String appId = LDSdk.getAppId();
        String zzm2 = com.p008ld.sdk.util.zze.zzm();
        String channelId = LDSdk.getChannelId();
        String sunChannelId = LDSdk.getSunChannelId();
        String sdkVersion = LDSdk.getSdkVersion();
        String zzn2 = com.p008ld.sdk.util.zze.zzn();
        String zzb2 = com.p008ld.sdk.util.zzi.zzb();
        String zza2 = com.p008ld.sdk.util.zze.zza();
        String zzc = com.p008ld.sdk.util.zzi.zzc();
        String zzd2 = com.p008ld.sdk.util.zzi.zzd(LDSdk.getApp());
        Intrinsics.checkNotNullExpressionValue(zzb2, "getMnqVersion()");
        Intrinsics.checkNotNullExpressionValue(zzc, "getDeviceId()");
        Intrinsics.checkNotNullExpressionValue(zzd2, "getMnqMacId(getApp())");
        arrayList.add(new LDTrackRequest("6666", String.valueOf(currentTimeMillis), eventKey, new LDTrackProperties(appId, zzm2, channelId, sunChannelId, sdkVersion, zzn2, zzb2, zza2, i, zzc, zzd2, userId, str2, str3, str), currentTimeMillis));
        zzg().uploadCollectionData(zza(currentTimeMillis, Integer.valueOf(arrayList.size())), arrayList).enqueue(new zzab(lDCallback));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzab extends LDApiOkCallback<Object> {
        final /* synthetic */ LDCallback<Boolean> zza;

        zzab(LDCallback<Boolean> lDCallback) {
            this.zza = lDCallback;
        }

        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDCallback<Boolean> zza;
            final /* synthetic */ boolean zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDCallback<Boolean> lDCallback, boolean z, LDException lDException) {
                super(0);
                this.zza = lDCallback;
                this.zzb = z;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                LDCallback<Boolean> lDCallback = this.zza;
                if (lDCallback != null) {
                    lDCallback.done(Boolean.valueOf(this.zzb), this.zzc);
                }
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiOkCallback
        public void done(boolean z, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, z, lDException));
        }
    }

    private final Map<String, String> zza(long j, Integer num) {
        String str = LDNative.INSTANCE.genSalt() + j + ((num != null ? num.intValue() : 1) * 1024);
        Intrinsics.checkNotNullExpressionValue(str, "stringBuilder.toString()");
        String lowerCase = LDNative.INSTANCE.encrypt(str).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        String substring = lowerCase.substring(lowerCase.length() - 8, lowerCase.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        HashMap hashMap = new HashMap();
        hashMap.put("timestamp", String.valueOf(j));
        hashMap.put("signature", substring);
        return hashMap;
    }

    public final void zza(LDUser lDUser, LDQueryCallback<GiftBagInfo> callBack) {
        String str;
        String str2;
        String email;
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        HashMap hashMap = new HashMap();
        String str3 = "";
        if (lDUser == null || (str = lDUser.getSpaceUserId()) == null) {
            str = "";
        }
        hashMap.put("userId", str);
        if (lDUser == null || (str2 = lDUser.getCpToken()) == null) {
            str2 = "";
        }
        hashMap.put("token", str2);
        if (lDUser != null && (email = lDUser.getEmail()) != null) {
            str3 = email;
        }
        hashMap.put("email", str3);
        hashMap.put("source", ServerProtocol.DIALOG_PARAM_SDK_VERSION);
        String zzd2 = com.p008ld.sdk.util.zzi.zzd(LDSdk.getApp());
        Intrinsics.checkNotNullExpressionValue(zzd2, "getMnqMacId(getApp())");
        hashMap.put("macId", zzd2);
        hashMap.put("packageName", com.p008ld.sdk.util.zze.zzm());
        zzh().queryGiftPackages(com.p008ld.sdk.util.zzm.zza(hashMap)).enqueue(new zzq(callBack));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzq extends LDApiCallback<GiftBagInfo> {
        final /* synthetic */ LDQueryCallback<GiftBagInfo> zza;

        zzq(LDQueryCallback<GiftBagInfo> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<GiftBagInfo> zza;
            final /* synthetic */ GiftBagInfo zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<GiftBagInfo> lDQueryCallback, GiftBagInfo giftBagInfo, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = giftBagInfo;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<GiftBagInfo>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(GiftBagInfo giftBagInfo, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, giftBagInfo, lDException));
        }
    }

    public final void zza(int i, LDUser lDUser, LDQueryCallback<ClaimGiftBagInfo> callBack) {
        String str;
        String str2;
        String email;
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        HashMap hashMap = new HashMap();
        hashMap.put("pid", Integer.valueOf(i));
        String str3 = "";
        if (lDUser == null || (str = lDUser.getSpaceUserId()) == null) {
            str = "";
        }
        hashMap.put("userId", str);
        if (lDUser == null || (str2 = lDUser.getCpToken()) == null) {
            str2 = "";
        }
        hashMap.put("token", str2);
        if (lDUser != null && (email = lDUser.getEmail()) != null) {
            str3 = email;
        }
        hashMap.put("email", str3);
        hashMap.put("source", ServerProtocol.DIALOG_PARAM_SDK_VERSION);
        String zzd2 = com.p008ld.sdk.util.zzi.zzd(LDSdk.getApp());
        Intrinsics.checkNotNullExpressionValue(zzd2, "getMnqMacId(getApp())");
        hashMap.put("macId", zzd2);
        zzh().claimGiftPackage(com.p008ld.sdk.util.zzm.zza(hashMap)).enqueue(new C3256zzc(callBack));
    }

    /* compiled from: LDLoginModel.kt */
    /* renamed from: com.ld.sdk.model.zzc$zzc, reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static final class C3256zzc extends LDApiCallback<ClaimGiftBagInfo> {
        final /* synthetic */ LDQueryCallback<ClaimGiftBagInfo> zza;

        C3256zzc(LDQueryCallback<ClaimGiftBagInfo> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* renamed from: com.ld.sdk.model.zzc$zzc$zza */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<ClaimGiftBagInfo> zza;
            final /* synthetic */ ClaimGiftBagInfo zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<ClaimGiftBagInfo> lDQueryCallback, ClaimGiftBagInfo claimGiftBagInfo, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = claimGiftBagInfo;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<ClaimGiftBagInfo>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(ClaimGiftBagInfo claimGiftBagInfo, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, claimGiftBagInfo, lDException));
        }
    }

    public final void zza(LDProductQueryParam lDProductQueryParam, LDQueryCallback<List<LDProductInfo>> callBack) {
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        HashMap hashMap = new HashMap();
        String str = lDProductQueryParam != null ? lDProductQueryParam.appProductId : null;
        if (str == null) {
            str = "";
        }
        hashMap.put("appProductId", str);
        String str2 = lDProductQueryParam != null ? lDProductQueryParam.currency : null;
        if (str2 == null) {
            str2 = "";
        }
        hashMap.put(FirebaseAnalytics.Param.CURRENCY, str2);
        String str3 = lDProductQueryParam != null ? lDProductQueryParam.productId : null;
        hashMap.put("productId", str3 != null ? str3 : "");
        zzf().queryProducts(com.p008ld.sdk.util.zzm.zza(hashMap)).enqueue(new zzv(callBack));
    }

    /* compiled from: LDLoginModel.kt */
    /* loaded from: classes2.dex */
    public static final class zzv extends LDApiCallback<List<? extends LDProductInfo>> {
        final /* synthetic */ LDQueryCallback<List<LDProductInfo>> zza;

        zzv(LDQueryCallback<List<LDProductInfo>> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: LDLoginModel.kt */
        /* loaded from: classes2.dex */
        public static final class zza extends Lambda implements Function0<Unit> {
            final /* synthetic */ LDQueryCallback<List<LDProductInfo>> zza;
            final /* synthetic */ List<LDProductInfo> zzb;
            final /* synthetic */ LDException zzc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            zza(LDQueryCallback<List<LDProductInfo>> lDQueryCallback, List<LDProductInfo> list, LDException lDException) {
                super(0);
                this.zza = lDQueryCallback;
                this.zzb = list;
                this.zzc = lDException;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* synthetic */ Unit invoke() {
                zza();
                return Unit.INSTANCE;
            }

            public final void zza() {
                this.zza.done((LDQueryCallback<List<LDProductInfo>>) this.zzb, this.zzc);
            }
        }

        @Override // com.p008ld.sdk.internal.LDApiCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(List<LDProductInfo> list, LDException lDException) {
            com.p008ld.sdk.util.zzm.zza(new zza(this.zza, list, lDException));
        }
    }
}
