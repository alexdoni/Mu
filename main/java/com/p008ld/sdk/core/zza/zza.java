package com.p008ld.sdk.core.zza;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.config.PictureMimeType;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.bean.AccountInfo;
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
import com.p008ld.sdk.core.bean.LoginInfo;
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
import com.p008ld.sdk.internal.LDCallback;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.internal.LDValidate;
import com.p008ld.sdk.track.LDTrackEvent;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzv;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* compiled from: LDUserManager.kt */
/* loaded from: classes2.dex */
public final class zza {
    public static final C3249zza zza = new C3249zza(null);
    private static volatile zza zzg;
    private final com.p008ld.sdk.model.zzc zzb;
    private LDUser zzc;
    private ConfigBean zzd;
    private Context zze;
    private long zzf;

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    public /* synthetic */ class zzc {
        public static final /* synthetic */ int[] zza;

        static {
            int[] iArr = new int[LoginMode.values().length];
            try {
                iArr[LoginMode.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LoginMode.USERNAME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LoginMode.FACEBOOK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[LoginMode.GOOGLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[LoginMode.LINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            zza = iArr;
        }
    }

    @JvmStatic
    public static final synchronized zza zzf() {
        zza zza2;
        synchronized (zza.class) {
            zza2 = zza.zza();
        }
        return zza2;
    }

    public zza(com.p008ld.sdk.model.zzc loginModel) {
        Intrinsics.checkNotNullParameter(loginModel, "loginModel");
        this.zzb = loginModel;
    }

    /* compiled from: LDUserManager.kt */
    /* renamed from: com.ld.sdk.core.zza.zza$zza, reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static final class C3249zza {
        public /* synthetic */ C3249zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private C3249zza() {
        }

        @JvmStatic
        public final synchronized zza zza() {
            zza zzaVar;
            if (zza.zzg == null) {
                zza.zzg = new zza(new com.p008ld.sdk.model.zzc());
            }
            zzaVar = zza.zzg;
            if (zzaVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("instance");
                zzaVar = null;
            }
            return zzaVar;
        }
    }

    public final LDUser zza() {
        return this.zzc;
    }

    public final void zza(LDUser lDUser) {
        zza(lDUser, true);
    }

    private final String zzh() {
        String uid;
        LDUser lDUser = this.zzc;
        return (lDUser == null || (uid = lDUser.getUid()) == null) ? "" : uid;
    }

    private final String zzi() {
        String token;
        LDUser lDUser = this.zzc;
        return (lDUser == null || (token = lDUser.getToken()) == null) ? "" : token;
    }

    private final String zzj() {
        String spaceUserId;
        LDUser lDUser = this.zzc;
        return (lDUser == null || (spaceUserId = lDUser.getSpaceUserId()) == null) ? "" : spaceUserId;
    }

    public final String zzb() {
        String spaceUToken;
        LDUser lDUser = this.zzc;
        return (lDUser == null || (spaceUToken = lDUser.getSpaceUToken()) == null) ? "" : spaceUToken;
    }

    public final LDUser zzc() {
        LDUser lDUser = (LDUser) com.p008ld.sdk.zzb.zzb.zza.zza().zza("KEY_LD_USER_INFO", Long.MAX_VALUE, LDUser.class);
        if (lDUser != null) {
            zza(lDUser, false);
        }
        return lDUser;
    }

    public final String zzd() {
        if (zza() == null) {
            return null;
        }
        LDUser zza2 = zza();
        Intrinsics.checkNotNull(zza2);
        String uid = zza2.getUid();
        if (uid == null) {
            uid = "";
        }
        LDUser zza3 = zza();
        Intrinsics.checkNotNull(zza3);
        String token = zza3.getToken();
        if (token == null) {
            token = "";
        }
        LDUser zza4 = zza();
        Intrinsics.checkNotNull(zza4);
        String shortToken = zza4.getShortToken();
        if (shortToken == null) {
            shortToken = "";
        }
        LDUser zza5 = zza();
        Intrinsics.checkNotNull(zza5);
        String nickname = zza5.getNickname();
        if (nickname == null) {
            nickname = "";
        }
        LDUser zza6 = zza();
        Intrinsics.checkNotNull(zza6);
        String headPortraitUrl = zza6.getHeadPortraitUrl();
        String str = headPortraitUrl != null ? headPortraitUrl : "";
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uid", uid);
        jSONObject.put("token", token);
        jSONObject.put("shortToken", shortToken);
        jSONObject.put("nickName", nickname);
        jSONObject.put("headPortraitUrl", str);
        return jSONObject.toString();
    }

    private final void zza(LDUser lDUser, boolean z) {
        this.zzc = lDUser;
        if (z) {
            if (lDUser != null) {
                com.p008ld.sdk.zzb.zzb.zza.zza().zza("KEY_LD_USER_INFO", (String) lDUser);
            } else {
                com.p008ld.sdk.zzb.zzb.zza.zza().zza("KEY_LD_USER_INFO");
            }
        }
    }

    public final void zzb(LDUser lDUser) {
        zza(lDUser, true);
    }

    private final void zzb(LoginInfo loginInfo, LDQueryCallback<LDUser> lDQueryCallback) {
        String uid = loginInfo.uid;
        String token = loginInfo.token;
        if (LDValidate.notNullOrEmpty(uid, "useId", lDQueryCallback) && LDValidate.notNullOrEmpty(token, "userToken", lDQueryCallback)) {
            com.p008ld.sdk.model.zzc zzcVar = this.zzb;
            Intrinsics.checkNotNullExpressionValue(uid, "uid");
            Intrinsics.checkNotNullExpressionValue(token, "token");
            zzcVar.zza(uid, token, lDQueryCallback, (String) null, (Boolean) false);
        }
    }

    private final void zza(LoginMode loginMode, String str, LDQueryCallback<LDUser> lDQueryCallback) {
        if (LDValidate.notNullOrEmpty(str, "thirdToken", lDQueryCallback)) {
            this.zzb.zza(loginMode, str, lDQueryCallback, (String) null, (String) null, (String) null, (Boolean) false);
        }
    }

    private final void zzb(String str, String str2, String str3, LDQueryCallback<LDUser> lDQueryCallback) {
        if (LDValidate.notNullOrEmpty(str, "username", lDQueryCallback) && LDValidate.notNullOrEmpty(str2, "email", lDQueryCallback) && LDValidate.notNullOrEmpty(str3, "password", lDQueryCallback)) {
            this.zzb.zza(str, str2, str3, lDQueryCallback, (String) null, (Boolean) false);
        }
    }

    private final void zzc(String str, String str2, String str3, LDQueryCallback<LDUser> lDQueryCallback) {
        if (LDValidate.notNullOrEmpty(str, "email", lDQueryCallback) && LDValidate.notNullOrEmpty(str2, "auth", lDQueryCallback) && LDValidate.notNullOrEmpty(str3, "password", lDQueryCallback)) {
            this.zzb.zzb(str, str2, str3, lDQueryCallback, null, false);
        }
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    static final class zzl extends Lambda implements Function1<LDException, Unit> {
        final /* synthetic */ LDQueryCallback<String> zzb;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzl(LDQueryCallback<String> lDQueryCallback) {
            super(1);
            this.zzb = lDQueryCallback;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* synthetic */ Unit invoke(LDException lDException) {
            zza(lDException);
            return Unit.INSTANCE;
        }

        public final void zza(LDException lDException) {
            String zza;
            if (lDException == null || (zza = lDException.toString()) == null) {
                zza = com.p008ld.sdk.util.zzi.zza(zza.this.zze, "ld_send_success_text");
            }
            this.zzb.done((LDQueryCallback<String>) zza, lDException);
        }
    }

    public final void zza(SendType sendType, String email, LDQueryCallback<String> callback) {
        Intrinsics.checkNotNullParameter(sendType, "sendType");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (LDValidate.notNullOrEmpty(email, "email", callback)) {
            this.zzb.zza(sendType, email, new zzl(callback));
        }
    }

    public final void zza(String email, String emailVerifyCode, String password, LDQueryCallback<String> lDQueryCallback) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(emailVerifyCode, "emailVerifyCode");
        Intrinsics.checkNotNullParameter(password, "password");
        if (LDValidate.notNullOrEmpty(email, "email", lDQueryCallback) && LDValidate.notNullOrEmpty(emailVerifyCode, "emailVerifyCode", lDQueryCallback) && LDValidate.notNullOrEmpty(password, "password", lDQueryCallback)) {
            this.zzb.zza(email, emailVerifyCode, password, new zzm(lDQueryCallback));
        }
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    static final class zzm extends Lambda implements Function1<LDException, Unit> {
        final /* synthetic */ LDQueryCallback<String> zzb;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzm(LDQueryCallback<String> lDQueryCallback) {
            super(1);
            this.zzb = lDQueryCallback;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* synthetic */ Unit invoke(LDException lDException) {
            zza(lDException);
            return Unit.INSTANCE;
        }

        public final void zza(LDException lDException) {
            String lDException2;
            if (lDException == null) {
                if (zza.this.zzc == null) {
                    lDException2 = com.p008ld.sdk.util.zzi.zza(zza.this.zze, "ld_modify_password_success_text");
                    Intrinsics.checkNotNullExpressionValue(lDException2, "getResString(mContext, \"…y_password_success_text\")");
                } else {
                    lDException2 = com.p008ld.sdk.util.zzi.zza(zza.this.zze, "ld_modify_password_success_reset_login_text");
                    Intrinsics.checkNotNullExpressionValue(lDException2, "getResString(\n          …xt\"\n                    )");
                    zza.this.zza(true);
                }
            } else {
                lDException2 = lDException.toString();
            }
            LDQueryCallback<String> lDQueryCallback = this.zzb;
            if (lDQueryCallback != null) {
                lDQueryCallback.done((LDQueryCallback<String>) lDException2, lDException);
            }
        }
    }

    public final void zza(String email, String emailVerifyCode, LDQueryCallback<String> lDQueryCallback, String str) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(emailVerifyCode, "emailVerifyCode");
        String zzh2 = zzh();
        if (LDValidate.notNullOrEmpty(zzh2, "userId", lDQueryCallback) && LDValidate.notNullOrEmpty(email, "email", lDQueryCallback) && LDValidate.notNullOrEmpty(emailVerifyCode, "emailVerifyCode", lDQueryCallback)) {
            this.zzb.zza(email, emailVerifyCode, zzh2, new zzd(email, lDQueryCallback), str, (String) null);
        }
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    static final class zzd extends Lambda implements Function1<LDException, Unit> {
        final /* synthetic */ String zzb;
        final /* synthetic */ LDQueryCallback<String> zzc;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzd(String str, LDQueryCallback<String> lDQueryCallback) {
            super(1);
            this.zzb = str;
            this.zzc = lDQueryCallback;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* synthetic */ Unit invoke(LDException lDException) {
            zza(lDException);
            return Unit.INSTANCE;
        }

        public final void zza(LDException lDException) {
            String zza;
            if (lDException == null) {
                zza.this.zza(this.zzb, (String) null);
            }
            if (lDException == null || (zza = lDException.toString()) == null) {
                zza = com.p008ld.sdk.util.zzi.zza(zza.this.zze, "ld_bind_success_text");
            }
            LDQueryCallback<String> lDQueryCallback = this.zzc;
            if (lDQueryCallback != null) {
                lDQueryCallback.done((LDQueryCallback<String>) zza, lDException);
            }
        }
    }

    public final void zza(LoginMode mode, String auth, LDQueryCallback<String> lDQueryCallback, String str) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(auth, "auth");
        String zzh2 = zzh();
        if (LDValidate.notNullOrEmpty(zzh2, "userId", lDQueryCallback) && LDValidate.notNullOrEmpty(auth, "auth", lDQueryCallback)) {
            this.zzb.zza(mode, zzh2, auth, new zze(mode, str, lDQueryCallback), (String) null);
        }
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    static final class zze extends Lambda implements Function1<LDException, Unit> {
        final /* synthetic */ LoginMode zzb;
        final /* synthetic */ String zzc;
        final /* synthetic */ LDQueryCallback<String> zzd;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zze(LoginMode loginMode, String str, LDQueryCallback<String> lDQueryCallback) {
            super(1);
            this.zzb = loginMode;
            this.zzc = str;
            this.zzd = lDQueryCallback;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* synthetic */ Unit invoke(LDException lDException) {
            zza(lDException);
            return Unit.INSTANCE;
        }

        public final void zza(LDException lDException) {
            String zza;
            if (lDException == null && zza.this.zzc != null && this.zzb == LoginMode.GOOGLE) {
                zza.this.zza((String) null, this.zzc);
            }
            if (lDException == null || (zza = lDException.toString()) == null) {
                zza = com.p008ld.sdk.util.zzi.zza(zza.this.zze, "ld_google_account_bind_success_text");
            }
            LDQueryCallback<String> lDQueryCallback = this.zzd;
            if (lDQueryCallback != null) {
                lDQueryCallback.done((LDQueryCallback<String>) zza, lDException);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(String str, String str2) {
        if (zza() != null) {
            LDUser zza2 = zza();
            Intrinsics.checkNotNull(zza2);
            zza(new LDUser(zza2.getUid(), zza2.getToken(), zza2.getShortToken(), zza2.getLoginType(), zza2.getHeadPortraitUrl(), zza2.getNickname(), zza2.getThirdUserInfos(), str == null ? zza2.getEmail() : str, zza2.getMustBindEmail(), zza2.getNewUser(), zza2.getUserName(), str2 == null ? zza2.getGoogleAccount() : str2, zza2.getSpaceUserId(), zza2.getSpaceUToken(), zza2.getCpToken(), str != null ? 1 : zza2.getBindEmail()));
            LDLog.m573e("UserManager -> updateLocalUserByBind :currentUser = " + zza());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(String str, String str2) {
        if (zza() != null) {
            LDLog.m573e("UserManager -> updateLocalUserByUpdate：before-> headPortraitUrl = " + str + "，nickname = " + str2);
            LDUser zza2 = zza();
            Intrinsics.checkNotNull(zza2);
            zza(new LDUser(zza2.getUid(), zza2.getToken(), zza2.getShortToken(), zza2.getLoginType(), str == null ? zza2.getHeadPortraitUrl() : str, str2 == null ? zza2.getNickname() : str2, zza2.getThirdUserInfos(), zza2.getEmail(), zza2.getMustBindEmail(), zza2.getNewUser(), zza2.getUserName(), zza2.getGoogleAccount(), zza2.getSpaceUserId(), zza2.getSpaceUToken(), zza2.getCpToken(), zza2.getBindEmail()));
            LDLog.m573e("UserManager -> updateLocalUserByUpdate：after-> currentUser = " + zza());
            com.p008ld.sdk.zzb.zzc.zza.zza().zza(zza2.getUid(), str, str2);
        }
    }

    public final void zza(String str, String str2, LDQueryCallback<String> lDQueryCallback) {
        String zzh2 = zzh();
        String zzi2 = zzi();
        if (LDValidate.notNullOrEmpty(zzh2, "userId", lDQueryCallback) && LDValidate.notNullOrEmpty(zzi2, "token", lDQueryCallback)) {
            this.zzb.zza(str, str2, zzh2, zzi2, new zzn(str, str2, lDQueryCallback));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    public static final class zzn extends Lambda implements Function1<LDException, Unit> {
        final /* synthetic */ String zzb;
        final /* synthetic */ String zzc;
        final /* synthetic */ LDQueryCallback<String> zzd;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzn(String str, String str2, LDQueryCallback<String> lDQueryCallback) {
            super(1);
            this.zzb = str;
            this.zzc = str2;
            this.zzd = lDQueryCallback;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* synthetic */ Unit invoke(LDException lDException) {
            zza(lDException);
            return Unit.INSTANCE;
        }

        public final void zza(LDException lDException) {
            String zza;
            if (lDException == null || (zza = lDException.toString()) == null) {
                zza = com.p008ld.sdk.util.zzi.zza(zza.this.zze, "ld_update_success_text");
            }
            if (lDException == null) {
                zza.this.zzb(this.zzb, this.zzc);
            }
            LDQueryCallback<String> lDQueryCallback = this.zzd;
            if (lDQueryCallback != null) {
                lDQueryCallback.done((LDQueryCallback<String>) zza, lDException);
            }
        }
    }

    public final void zza(String localPath, LDQueryCallback<String> callback) {
        Object m1882constructorimpl;
        Intrinsics.checkNotNullParameter(localPath, "localPath");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            Result.Companion companion = Result.INSTANCE;
            String substring = localPath.substring(StringsKt.lastIndexOf$default((CharSequence) localPath, ".", 0, false, 6, (Object) null) + 1);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            m1882constructorimpl = Result.m1882constructorimpl(substring);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1888isFailureimpl(m1882constructorimpl)) {
            m1882constructorimpl = "png";
        }
        String str = (String) m1882constructorimpl;
        LDLog.m573e("UserManager -> uploadAvatar: " + localPath + " , " + str);
        zzd(str, new zzo(localPath, callback, this));
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    public static final class zzo implements LDQueryCallback<LDUploadAuth> {
        final /* synthetic */ String zza;
        final /* synthetic */ LDQueryCallback<String> zzb;
        final /* synthetic */ zza zzc;

        zzo(String str, LDQueryCallback<String> lDQueryCallback, zza zzaVar) {
            this.zza = str;
            this.zzb = lDQueryCallback;
            this.zzc = zzaVar;
        }

        @Override // com.p008ld.sdk.internal.LDCallback2
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LDUploadAuth lDUploadAuth, LDException lDException) {
            File file;
            if (lDUploadAuth != null) {
                if (PictureMimeType.isContent(this.zza)) {
                    file = com.p008ld.sdk.util.zzl.zza(Uri.parse(this.zza));
                } else {
                    file = new File(this.zza);
                }
                zzv zza = zzv.zza.zza();
                Intrinsics.checkNotNullExpressionValue(file, "file");
                zza.zza(lDUploadAuth, file, new C3250zza(this.zzc, this.zzb));
                return;
            }
            this.zzb.done((LDQueryCallback<String>) null, lDException);
        }

        /* compiled from: LDUserManager.kt */
        /* renamed from: com.ld.sdk.core.zza.zza$zzo$zza, reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C3250zza implements LDQueryCallback<String> {
            final /* synthetic */ zza zza;
            final /* synthetic */ LDQueryCallback<String> zzb;

            C3250zza(zza zzaVar, LDQueryCallback<String> lDQueryCallback) {
                this.zza = zzaVar;
                this.zzb = lDQueryCallback;
            }

            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(String str, LDException lDException) {
                if (lDException == null) {
                    this.zza.zza(str, (String) null, new C3251zza(this.zzb, str));
                } else {
                    this.zzb.done((LDQueryCallback<String>) null, lDException);
                }
            }

            /* compiled from: LDUserManager.kt */
            /* renamed from: com.ld.sdk.core.zza.zza$zzo$zza$zza, reason: collision with other inner class name */
            /* loaded from: classes2.dex */
            public static final class C3251zza implements LDQueryCallback<String> {
                final /* synthetic */ LDQueryCallback<String> zza;
                final /* synthetic */ String zzb;

                C3251zza(LDQueryCallback<String> lDQueryCallback, String str) {
                    this.zza = lDQueryCallback;
                    this.zzb = str;
                }

                @Override // com.p008ld.sdk.internal.LDCallback2
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(String str, LDException lDException) {
                    if (lDException == null) {
                        this.zza.done((LDQueryCallback<String>) this.zzb, (LDException) null);
                    } else {
                        this.zza.done((LDQueryCallback<String>) null, lDException);
                    }
                }
            }
        }
    }

    public final void zza(LDQueryCallback<String> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        File logFile = LDSdk.getLogFile();
        if (!com.p008ld.sdk.util.zzj.zzd(logFile)) {
            com.p008ld.sdk.util.zzm.zza(new zzp(callback));
        } else {
            zze(new zzq(logFile, callback));
        }
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    static final class zzp extends Lambda implements Function0<Unit> {
        final /* synthetic */ LDQueryCallback<String> zza;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzp(LDQueryCallback<String> lDQueryCallback) {
            super(0);
            this.zza = lDQueryCallback;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* synthetic */ Unit invoke() {
            zza();
            return Unit.INSTANCE;
        }

        public final void zza() {
            this.zza.done((LDQueryCallback<String>) null, new LDException("logFile does not exist!"));
        }
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    public static final class zzq implements LDQueryCallback<LDUploadAuth> {
        final /* synthetic */ File zza;
        final /* synthetic */ LDQueryCallback<String> zzb;

        zzq(File file, LDQueryCallback<String> lDQueryCallback) {
            this.zza = file;
            this.zzb = lDQueryCallback;
        }

        @Override // com.p008ld.sdk.internal.LDCallback2
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LDUploadAuth lDUploadAuth, LDException lDException) {
            if (lDUploadAuth != null) {
                zzv zza = zzv.zza.zza();
                File file = this.zza;
                Intrinsics.checkNotNull(file);
                zza.zza(lDUploadAuth, file, this.zzb);
                return;
            }
            this.zzb.done((LDQueryCallback<String>) null, lDException);
        }
    }

    private final void zzd(String str, LDQueryCallback<LDUploadAuth> lDQueryCallback) {
        String zzh2 = zzh();
        String zzi2 = zzi();
        if (LDValidate.notNullOrEmpty(zzh2, "userId", lDQueryCallback) && LDValidate.notNullOrEmpty(zzi2, "userToken", lDQueryCallback)) {
            this.zzb.zza(zzh2, zzi2, str, lDQueryCallback);
        }
    }

    private final void zze(LDQueryCallback<LDUploadAuth> lDQueryCallback) {
        String zzh2 = zzh();
        String zzi2 = zzi();
        if (LDValidate.notNullOrEmpty(zzh2, "userId", lDQueryCallback) && LDValidate.notNullOrEmpty(zzi2, "userToken", lDQueryCallback)) {
            this.zzb.zza(zzh2, zzi2, lDQueryCallback);
        }
    }

    public final void zzb(String code, LDQueryCallback<LDUser> callback) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (LDValidate.notNullOrEmpty(code, "stateCode", callback)) {
            this.zzb.zza(code, callback);
        }
    }

    public final ConfigBean zze() {
        if (this.zzd == null) {
            this.zzd = new ConfigBean();
        }
        ConfigBean configBean = this.zzd;
        Intrinsics.checkNotNull(configBean);
        return configBean;
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    public static final class zzf implements LDQueryCallback<ConfigBean> {
        final /* synthetic */ LDQueryCallback<ConfigBean> zzb;

        zzf(LDQueryCallback<ConfigBean> lDQueryCallback) {
            this.zzb = lDQueryCallback;
        }

        @Override // com.p008ld.sdk.internal.LDCallback2
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(ConfigBean configBean, LDException lDException) {
            if (configBean != null) {
                zza.this.zzd = configBean;
                this.zzb.done((LDQueryCallback<ConfigBean>) configBean, (LDException) null);
            } else {
                this.zzb.done((LDQueryCallback<ConfigBean>) null, lDException);
            }
        }
    }

    public final void zza(Context context, LDQueryCallback<ConfigBean> callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.zze = context;
        this.zzb.zza(new zzf(callback));
    }

    public final void zza(AccountInfo info, LDQueryCallback<String> callback) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(callback, "callback");
        String str = info.userName;
        Intrinsics.checkNotNullExpressionValue(str, "info.userName");
        String str2 = info.auth;
        Intrinsics.checkNotNullExpressionValue(str2, "info.auth");
        String str3 = info.password;
        Intrinsics.checkNotNullExpressionValue(str3, "info.password");
        zzc(str, str2, str3, new zzk(callback));
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    public static final class zzk implements LDQueryCallback<LDUser> {
        final /* synthetic */ LDQueryCallback<String> zza;

        zzk(LDQueryCallback<String> lDQueryCallback) {
            this.zza = lDQueryCallback;
        }

        @Override // com.p008ld.sdk.internal.LDCallback2
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LDUser lDUser, LDException lDException) {
            String zza;
            if (lDException == null || (zza = lDException.toString()) == null) {
                zza = com.p008ld.sdk.util.zzi.zza(LDSdk.getApp(), "ld_register_success_text");
            }
            this.zza.done((LDQueryCallback<String>) zza, lDException);
        }
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    static final class zzi extends Lambda implements Function0<Unit> {
        final /* synthetic */ LDQueryCallback<LDUser> zza;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzi(LDQueryCallback<LDUser> lDQueryCallback) {
            super(0);
            this.zza = lDQueryCallback;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* synthetic */ Unit invoke() {
            zza();
            return Unit.INSTANCE;
        }

        public final void zza() {
            this.zza.done((LDQueryCallback<LDUser>) null, new LDException(com.p008ld.sdk.util.zzi.zza(LDSdk.getApp(), "ld_not_init_error_text")));
        }
    }

    public final void zza(LoginInfo info, LDQueryCallback<LDUser> callback) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (this.zzd == null) {
            com.p008ld.sdk.util.zzm.zza(new zzi(callback));
            return;
        }
        LoginMode loginMode = info.loginMode;
        int i = loginMode == null ? -1 : zzc.zza[loginMode.ordinal()];
        if (i == 1) {
            zzc(info, callback);
            return;
        }
        if (i == 2) {
            zzd(info, callback);
        } else if (i == 3 || i == 4 || i == 5) {
            zze(info, callback);
        }
    }

    private final void zzc(LoginInfo loginInfo, LDQueryCallback<LDUser> lDQueryCallback) {
        zzb(loginInfo, new zzb(this, loginInfo, lDQueryCallback));
    }

    private final void zzd(LoginInfo loginInfo, LDQueryCallback<LDUser> lDQueryCallback) {
        zzb zzbVar = new zzb(this, loginInfo, lDQueryCallback);
        String str = loginInfo.username;
        Intrinsics.checkNotNullExpressionValue(str, "info.username");
        String str2 = loginInfo.username;
        Intrinsics.checkNotNullExpressionValue(str2, "info.username");
        String str3 = loginInfo.password;
        Intrinsics.checkNotNullExpressionValue(str3, "info.password");
        zzb(str, str2, str3, zzbVar);
    }

    private final void zze(LoginInfo loginInfo, LDQueryCallback<LDUser> lDQueryCallback) {
        zzb zzbVar = new zzb(this, loginInfo, lDQueryCallback);
        LoginMode loginMode = loginInfo.loginMode;
        Intrinsics.checkNotNullExpressionValue(loginMode, "info.loginMode");
        String str = loginInfo.auth;
        Intrinsics.checkNotNullExpressionValue(str, "info.auth");
        zza(loginMode, str, zzbVar);
    }

    public final void zza(LoginInfo loginInfo, LDUser user, LDQueryCallback<LDUser> lDQueryCallback) {
        Intrinsics.checkNotNullParameter(loginInfo, "loginInfo");
        Intrinsics.checkNotNullParameter(user, "user");
        String uid = user.getUid();
        if (uid == null) {
            uid = "";
        }
        String token = user.getToken();
        zzc(uid, token != null ? token : "", new zzj(loginInfo, user, lDQueryCallback));
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    public static final class zzj implements LDQueryCallback<LoginResultInfo> {
        final /* synthetic */ LoginInfo zzb;
        final /* synthetic */ LDUser zzc;
        final /* synthetic */ LDQueryCallback<LDUser> zzd;

        zzj(LoginInfo loginInfo, LDUser lDUser, LDQueryCallback<LDUser> lDQueryCallback) {
            this.zzb = loginInfo;
            this.zzc = lDUser;
            this.zzd = lDQueryCallback;
        }

        @Override // com.p008ld.sdk.internal.LDCallback2
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LoginResultInfo loginResultInfo, LDException lDException) {
            if (loginResultInfo != null) {
                LDLog.m573e("UserManager -> loginSpace success: " + LDUtil.toJson(loginResultInfo));
                zza.this.zza(this.zzb, this.zzc, loginResultInfo);
                LDSdk.trackLoginEvent(LDTrackEvent.LOGIN, null, this.zzb.loginMode.getValue());
                LDQueryCallback<LDUser> lDQueryCallback = this.zzd;
                if (lDQueryCallback != null) {
                    LDUser zza = zza.this.zza();
                    Intrinsics.checkNotNull(zza);
                    lDQueryCallback.done((LDQueryCallback<LDUser>) zza, (LDException) null);
                    return;
                }
                return;
            }
            LDLog.m573e("UserManager -> loginSpace failed：" + lDException);
            LDSdk.trackLoginEvent(LDTrackEvent.LOGIN, lDException, this.zzb.loginMode.getValue());
            LDQueryCallback<LDUser> lDQueryCallback2 = this.zzd;
            if (lDQueryCallback2 != null) {
                lDQueryCallback2.done((LDQueryCallback<LDUser>) null, lDException);
            }
        }
    }

    private final void zzc(String str, String str2, LDQueryCallback<LoginResultInfo> lDQueryCallback) {
        if (LDValidate.notNullOrEmpty(str, "userId", lDQueryCallback) && LDValidate.notNullOrEmpty(str2, "userToken", lDQueryCallback)) {
            this.zzb.zzb(str, str2, lDQueryCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    public final class zzb implements LDQueryCallback<LDUser> {
        final /* synthetic */ zza zza;
        private final LoginInfo zzb;
        private final LDQueryCallback<LDUser> zzc;

        public zzb(zza zzaVar, LoginInfo loginInfo, LDQueryCallback<LDUser> lDQueryCallback) {
            Intrinsics.checkNotNullParameter(loginInfo, "loginInfo");
            this.zza = zzaVar;
            this.zzb = loginInfo;
            this.zzc = lDQueryCallback;
        }

        @Override // com.p008ld.sdk.internal.LDCallback2
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(LDUser lDUser, LDException lDException) {
            String str;
            if (lDUser != null) {
                LDLog.m573e("UserManager -> login success：" + lDUser);
                this.zza.zza(this.zzb, lDUser, this.zzc);
                return;
            }
            LDLog.m573e("UserManager -> login failed ：" + lDException);
            LDSdk.trackLoginEvent(LDTrackEvent.LOGIN, lDException, this.zzb.loginMode.getValue());
            LDQueryCallback<LDUser> lDQueryCallback = this.zzc;
            if (lDQueryCallback != null) {
                Integer valueOf = Integer.valueOf(lDException != null ? lDException.getErrorCode() : -1);
                if (lDException == null || (str = lDException.getMessage()) == null) {
                    str = "";
                }
                lDQueryCallback.done((LDQueryCallback<LDUser>) null, new LDException(valueOf, str));
            }
        }
    }

    public final void zza(boolean z) {
        String str;
        Context context = this.zze;
        Intrinsics.checkNotNull(context);
        LDUser zza2 = zza();
        if (zza2 == null || (str = zza2.getUid()) == null) {
            str = "";
        }
        zza(context, str, true);
        zza((LDUser) null);
        LDLog.m573e("UserManager -> logout ok");
        if (!z || this.zze == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.ld.outlogin.action");
        Context context2 = this.zze;
        Intrinsics.checkNotNull(context2);
        intent.setPackage(context2.getPackageName());
        Context context3 = this.zze;
        Intrinsics.checkNotNull(context3);
        context3.sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0038, code lost:
    
        if (r2 != null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(com.p008ld.sdk.core.bean.LoginInfo r22, com.p008ld.sdk.core.LDUser r23, com.p008ld.sdk.core.bean.LoginResultInfo r24) {
        /*
            r21 = this;
            r0 = r22
            r1 = r24
            java.util.List r2 = r23.getThirdUserInfos()
            r3 = 0
            if (r2 == 0) goto L3a
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r2 = r2.iterator()
        L11:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L2f
            java.lang.Object r4 = r2.next()
            r5 = r4
            com.ld.sdk.core.bean.LDThirdUserInfo r5 = (com.p008ld.sdk.core.bean.LDThirdUserInfo) r5
            java.lang.String r5 = r5.getThirdType()
            com.ld.sdk.core.bean.LoginMode r6 = com.p008ld.sdk.core.bean.LoginMode.GOOGLE
            java.lang.String r6 = r6.getValue()
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            if (r5 == 0) goto L11
            goto L30
        L2f:
            r4 = r3
        L30:
            com.ld.sdk.core.bean.LDThirdUserInfo r4 = (com.p008ld.sdk.core.bean.LDThirdUserInfo) r4
            if (r4 == 0) goto L3a
            java.lang.String r2 = r4.getThirdEmail()
            if (r2 != 0) goto L3c
        L3a:
            java.lang.String r2 = ""
        L3c:
            r16 = r2
            com.ld.sdk.core.LDUser r2 = r21.zza()
            com.ld.sdk.core.LDUser r15 = new com.ld.sdk.core.LDUser
            java.lang.String r5 = r23.getUid()
            java.lang.String r6 = r23.getToken()
            java.lang.String r7 = r23.getShortToken()
            com.ld.sdk.core.bean.LoginMode r4 = r0.loginMode
            com.ld.sdk.core.bean.LoginMode r8 = com.p008ld.sdk.core.bean.LoginMode.AUTO
            if (r4 != r8) goto L5d
            if (r2 == 0) goto L61
            java.lang.String r3 = r2.getLoginType()
            goto L61
        L5d:
            java.lang.String r3 = r23.getLoginType()
        L61:
            r8 = r3
            java.lang.String r9 = r23.getHeadPortraitUrl()
            java.lang.String r10 = r23.getNickname()
            java.util.List r11 = r23.getThirdUserInfos()
            java.lang.String r12 = r23.getEmail()
            java.lang.Boolean r13 = r23.getMustBindEmail()
            java.lang.Boolean r14 = r23.getNewUser()
            java.lang.String r2 = r1.userName
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L89
            int r2 = r2.length()
            if (r2 != 0) goto L87
            goto L89
        L87:
            r2 = 0
            goto L8a
        L89:
            r2 = 1
        L8a:
            if (r2 == 0) goto L91
            java.lang.String r2 = r23.getEmail()
            goto L93
        L91:
            java.lang.String r2 = r1.userName
        L93:
            java.lang.String r3 = r1.userId
            java.lang.String r4 = r1.spaceToken
            java.lang.String r1 = r1.cpToken
            java.lang.String r19 = r23.getEmail()
            java.lang.CharSequence r19 = (java.lang.CharSequence) r19
            if (r19 == 0) goto Lab
            int r19 = r19.length()
            if (r19 != 0) goto La8
            goto Lab
        La8:
            r17 = 0
            goto Lad
        Lab:
            r17 = 1
        Lad:
            r18 = 1
            r20 = r17 ^ 1
            r18 = r4
            r4 = r15
            r0 = r15
            r15 = r2
            r17 = r3
            r19 = r1
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            r1 = r21
            r1.zza(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "UserManager -> updateLocalUserByLogin: currentUser = "
            r0.<init>(r2)
            com.ld.sdk.core.LDUser r2 = r21.zza()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.p008ld.sdk.util.LDLog.m573e(r0)
            com.ld.sdk.zzb.zzc$zza r0 = com.p008ld.sdk.zzb.zzc.zza
            com.ld.sdk.zzb.zzc r0 = r0.zza()
            com.ld.sdk.core.LDUser r2 = r21.zza()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            r3 = r22
            r0.zza(r3, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.core.zza.zza.zza(com.ld.sdk.core.bean.LoginInfo, com.ld.sdk.core.LDUser, com.ld.sdk.core.bean.LoginResultInfo):void");
    }

    public final void zza(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return;
        }
        zza(context, str, false);
        com.p008ld.sdk.zzb.zzc.zza.zza().zza(str);
    }

    private final void zza(Context context, String str, boolean z) {
        String invisibleJson = com.p008ld.sdk.util.zzk.zza(context).zza("KEY_INVISIBLE_USER_LIST");
        if (z) {
            Intrinsics.checkNotNullExpressionValue(invisibleJson, "invisibleJson");
            if (!StringsKt.contains$default((CharSequence) invisibleJson, (CharSequence) str, false, 2, (Object) null) || Intrinsics.areEqual(invisibleJson, "")) {
                return;
            }
        }
        Gson gson = new Gson();
        ArrayList arrayList = (ArrayList) gson.fromJson(invisibleJson, new zzh().getType());
        if (!z) {
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(str);
        } else {
            Intrinsics.checkNotNull(arrayList);
            arrayList.remove(str);
        }
        com.p008ld.sdk.util.zzk.zza(context).zza("KEY_INVISIBLE_USER_LIST", gson.toJson(arrayList));
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    public static final class zzh extends TypeToken<List<? extends String>> {
        zzh() {
        }
    }

    public final void zzb(String virtualCurrencyType, String str, LDQueryCallback<List<LDGradeQueryInfo>> lDQueryCallback) {
        Intrinsics.checkNotNullParameter(virtualCurrencyType, "virtualCurrencyType");
        if (LDValidate.notNullOrEmpty(virtualCurrencyType, "virtualCurrencyType", lDQueryCallback)) {
            com.p008ld.sdk.model.zzc zzcVar = this.zzb;
            if (str == null) {
                str = "";
            }
            zzcVar.zzc(virtualCurrencyType, str, lDQueryCallback);
        }
    }

    public final void zza(PayInfo info, LDQueryCallback<PayUrlBean> callBack) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        info.uid = zzj();
        this.zzb.zza(info, callBack);
    }

    public final void zza(LdGamePayInfo info, LDQueryCallback<CreateOrderBean> callBack) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        this.zzb.zza(zzj(), info, callBack);
    }

    public final void zza(PayOrderInfo info, LDQueryCallback<PayOrderBean> callBack) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        this.zzb.zza(zzj(), info, callBack);
    }

    public final void zzc(String orderId, LDQueryCallback<OrderStatusBean> callBack) {
        Intrinsics.checkNotNullParameter(orderId, "orderId");
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        this.zzb.zzb(orderId, callBack);
    }

    public final void zzb(LDQueryCallback<List<OrderRecordBean>> callBack) {
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        this.zzb.zzc(zzj(), callBack);
    }

    public final void zzc(LDQueryCallback<List<OrderRecordBean>> callBack) {
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        this.zzb.zzd(zzj(), callBack);
    }

    public final void zza(boolean z, LDCallback1<Long> callBack) {
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        if (!z) {
            long j = this.zzf;
            if (j != 0) {
                callBack.done(Long.valueOf(j));
                return;
            }
        }
        this.zzb.zze(zzj(), new zzg(callBack));
    }

    /* compiled from: LDUserManager.kt */
    /* loaded from: classes2.dex */
    public static final class zzg implements LDQueryCallback<UserWalletResponseBean> {
        final /* synthetic */ LDCallback1<Long> zzb;

        zzg(LDCallback1<Long> lDCallback1) {
            this.zzb = lDCallback1;
        }

        @Override // com.p008ld.sdk.internal.LDCallback2
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(UserWalletResponseBean userWalletResponseBean, LDException lDException) {
            if (lDException == null && userWalletResponseBean != null) {
                zza.this.zzf = userWalletResponseBean.amount;
            } else if (lDException != null) {
                LDLog.m573e("UserManager -> getUserWallet error ：" + lDException);
            }
            this.zzb.done(Long.valueOf(zza.this.zzf));
        }
    }

    public final void zza(int i, LDQueryCallback<List<CouponBean>> callBack) {
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        this.zzb.zza(zzj(), i, callBack);
    }

    public final void zza(GameRoleInfo gameRoleInfo, LDCallback<Boolean> callback) {
        Intrinsics.checkNotNullParameter(gameRoleInfo, "gameRoleInfo");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.zzb.zza(gameRoleInfo, zzj(), callback);
    }

    public final void zza(LDProductQueryParam lDProductQueryParam, LDQueryCallback<List<LDProductInfo>> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.zzb.zza(lDProductQueryParam, callback);
    }

    public final void zza(String eventKey, int i, String str, String str2, String str3, String str4, LDCallback<Boolean> lDCallback) {
        Intrinsics.checkNotNullParameter(eventKey, "eventKey");
        this.zzb.zza(str == null ? zzj() : str, eventKey, i, str2, str3, str4, lDCallback);
    }

    public final void zzd(LDQueryCallback<GiftBagInfo> callBack) {
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        this.zzb.zza(this.zzc, callBack);
    }

    public final void zzb(int i, LDQueryCallback<ClaimGiftBagInfo> callBack) {
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        this.zzb.zza(i, this.zzc, callBack);
    }
}
