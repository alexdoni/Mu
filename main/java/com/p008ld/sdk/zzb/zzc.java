package com.p008ld.sdk.zzb;

import com.google.gson.reflect.TypeToken;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.bean.LoginInfo;
import com.p008ld.sdk.core.bean.LoginMode;
import com.p008ld.sdk.core.bean.PublicUserInfo;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LDCacheHelper.kt */
/* loaded from: classes2.dex */
public final class zzc {
    public static final zza zza = new zza(null);
    private static volatile zzc zzc;
    private List<? extends PublicUserInfo> zzb;

    @JvmStatic
    public static final synchronized zzc zzc() {
        zzc zza2;
        synchronized (zzc.class) {
            zza2 = zza.zza();
        }
        return zza2;
    }

    /* compiled from: LDCacheHelper.kt */
    /* loaded from: classes2.dex */
    public static final class zza {
        public /* synthetic */ zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private zza() {
        }

        @JvmStatic
        public final synchronized zzc zza() {
            zzc zzcVar;
            if (zzc.zzc == null) {
                zzc zzcVar2 = new zzc();
                zzcVar2.zze();
                zzc.zzc = zzcVar2;
            }
            zzcVar = zzc.zzc;
            if (zzcVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("instance");
                zzcVar = null;
            }
            return zzcVar;
        }
    }

    public final List<PublicUserInfo> zza() {
        return this.zzb;
    }

    public final void zza(List<? extends PublicUserInfo> list) {
        zza(list, true);
    }

    public final List<PublicUserInfo> zzb() {
        List<PublicUserInfo> zza2 = zza();
        if (zza2 == null || zza2.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<PublicUserInfo> zza3 = zza();
        Intrinsics.checkNotNull(zza3);
        for (PublicUserInfo publicUserInfo : zza3) {
            if (Intrinsics.areEqual(publicUserInfo.loginType, LoginMode.USERNAME.getValue()) || Intrinsics.areEqual(publicUserInfo.loginType, LoginMode.EMAIL.getValue())) {
                if (publicUserInfo.username != null) {
                    String str = publicUserInfo.username;
                    Intrinsics.checkNotNullExpressionValue(str, "info.username");
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) "@", false, 2, (Object) null)) {
                        arrayList.add(publicUserInfo);
                    }
                }
            }
        }
        return arrayList;
    }

    /* compiled from: LDCacheHelper.kt */
    /* loaded from: classes2.dex */
    public static final class zzb extends TypeToken<List<? extends PublicUserInfo>> {
        zzb() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zze() {
        List<? extends PublicUserInfo> list;
        Type type = new zzb().getType();
        com.p008ld.sdk.zzb.zzb zza2 = com.p008ld.sdk.zzb.zzb.zza.zza();
        if (zza2 != null) {
            Intrinsics.checkNotNullExpressionValue(type, "type");
            list = (List) zza2.zza("KEY_LD_USER_LIST", Long.MAX_VALUE, type);
        } else {
            list = null;
        }
        List<? extends PublicUserInfo> list2 = list instanceof List ? list : null;
        List<? extends PublicUserInfo> list3 = list2;
        if (list3 == null || list3.isEmpty()) {
            return false;
        }
        zza(list2, false);
        return true;
    }

    private final void zza(List<? extends PublicUserInfo> list, boolean z) {
        this.zzb = list;
        if (z) {
            List<? extends PublicUserInfo> list2 = list;
            if (!(list2 == null || list2.isEmpty())) {
                com.p008ld.sdk.zzb.zzb.zza.zza().zza("KEY_LD_USER_LIST", (List) list);
            } else {
                com.p008ld.sdk.zzb.zzb.zza.zza().zza("KEY_LD_USER_LIST");
            }
        }
    }

    private final void zza(PublicUserInfo publicUserInfo) {
        ArrayList arrayList;
        List<PublicUserInfo> zza2 = zza();
        if (zza2 == null || (arrayList = CollectionsKt.toMutableList((Collection) zza2)) == null) {
            arrayList = new ArrayList();
        }
        arrayList.add(0, publicUserInfo);
        zza(arrayList);
    }

    public final void zza(String str, String str2, String str3) {
        if (str == null) {
            str = "";
        }
        PublicUserInfo zzb2 = zzb(str);
        if (zzb2 == null) {
            return;
        }
        boolean z = zzb2.loginType == LoginMode.USERNAME.getValue();
        PublicUserInfo publicUserInfo = new PublicUserInfo();
        publicUserInfo.loginType = zzb2.loginType;
        publicUserInfo.ldUserId = zzb2.ldUserId;
        publicUserInfo.ldUserToken = zzb2.ldUserToken;
        publicUserInfo.nickName = str3 == null ? zzb2.nickName : str3;
        if (str2 == null) {
            str2 = zzb2.headPortraitUrl;
        }
        publicUserInfo.headPortraitUrl = str2;
        publicUserInfo.password = zzb2.password;
        if (z) {
            str3 = zzb2.username;
        } else if (str3 == null) {
            str3 = zzb2.nickName;
        }
        publicUserInfo.username = str3;
        zzb(publicUserInfo);
    }

    public final void zza(LoginInfo loginInfo, LDUser user) {
        Intrinsics.checkNotNullParameter(loginInfo, "loginInfo");
        Intrinsics.checkNotNullParameter(user, "user");
        String uid = user.getUid();
        String str = "";
        if (uid == null) {
            uid = "";
        }
        PublicUserInfo zzb2 = zzb(uid);
        PublicUserInfo publicUserInfo = new PublicUserInfo();
        publicUserInfo.loginType = user.getLoginType();
        publicUserInfo.ldUserId = user.getUid();
        publicUserInfo.ldUserToken = user.getToken();
        publicUserInfo.nickName = user.getNickname();
        String headPortraitUrl = user.getHeadPortraitUrl();
        if (headPortraitUrl == null) {
            headPortraitUrl = "";
        }
        publicUserInfo.headPortraitUrl = headPortraitUrl;
        if (zzb2 == null) {
            if (loginInfo.isUserNameMode() && loginInfo.rememberPwd) {
                str = loginInfo.password;
            }
            publicUserInfo.password = str;
            String str2 = loginInfo.username;
            publicUserInfo.username = str2 == null || str2.length() == 0 ? user.getNickname() : loginInfo.username;
            zza(publicUserInfo);
            return;
        }
        if (loginInfo.isUserNameMode()) {
            if (loginInfo.rememberPwd) {
                str = loginInfo.password;
            }
        } else {
            str = zzb2.password;
        }
        publicUserInfo.password = str;
        publicUserInfo.username = zzb2.username;
        zzb(publicUserInfo);
    }

    private final void zzb(PublicUserInfo publicUserInfo) {
        ArrayList arrayList;
        List<PublicUserInfo> zza2 = zza();
        if (zza2 == null || (arrayList = CollectionsKt.toMutableList((Collection) zza2)) == null) {
            arrayList = new ArrayList();
        }
        if (!arrayList.isEmpty()) {
            Iterator<? extends PublicUserInfo> it = arrayList.iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                } else if (Intrinsics.areEqual(it.next().ldUserId, publicUserInfo.ldUserId)) {
                    break;
                } else {
                    i++;
                }
            }
            Integer valueOf = Integer.valueOf(i);
            if (!(valueOf.intValue() != -1)) {
                valueOf = null;
            }
            if (valueOf != null) {
                arrayList.remove(valueOf.intValue());
            }
            arrayList.add(0, publicUserInfo);
            zza(arrayList);
        }
    }

    public final void zza(String uid) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(uid, "uid");
        List<PublicUserInfo> zza2 = zza();
        if (zza2 == null || (arrayList = CollectionsKt.toMutableList((Collection) zza2)) == null) {
            arrayList = new ArrayList();
        }
        if (!arrayList.isEmpty()) {
            Iterator<? extends PublicUserInfo> it = arrayList.iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                } else if (Intrinsics.areEqual(it.next().ldUserId, uid)) {
                    break;
                } else {
                    i++;
                }
            }
            Integer valueOf = Integer.valueOf(i);
            if (!(valueOf.intValue() != -1)) {
                valueOf = null;
            }
            if (valueOf != null) {
                arrayList.remove(valueOf.intValue());
            }
            zza(arrayList);
        }
    }

    private final PublicUserInfo zzb(String str) {
        ArrayList arrayList;
        List<PublicUserInfo> zza2 = zza();
        if (zza2 == null || (arrayList = CollectionsKt.toMutableList((Collection) zza2)) == null) {
            arrayList = new ArrayList();
        }
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                if (Intrinsics.areEqual(((PublicUserInfo) it.next()).ldUserId, str)) {
                    break;
                }
                i++;
            }
            Integer valueOf = Integer.valueOf(i);
            if (!(valueOf.intValue() != -1)) {
                valueOf = null;
            }
            if (valueOf != null) {
                return (PublicUserInfo) arrayList.get(valueOf.intValue());
            }
        }
        return null;
    }
}
