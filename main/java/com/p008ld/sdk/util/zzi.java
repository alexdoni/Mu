package com.p008ld.sdk.util;

import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.FileProvider;
import com.facebook.share.internal.ShareConstants;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.core.UserAccountMgr;
import com.p008ld.sdk.core.bean.AccountMsgInfo;
import com.p008ld.sdk.core.bean.CouponBean;
import com.p008ld.sdk.core.bean.CouponItem;
import com.p008ld.sdk.core.bean.GiftPackage;
import com.p008ld.sdk.internal.LDIdentifiers;
import com.tencent.p014av.sdk.AVError;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: LDOpenUtils.java */
/* loaded from: classes2.dex */
public class zzi {
    public static String zza = "android.intent.action.OPEN_HOST_URI";
    public static String zzb = "1";
    public static String zzc = "2";
    public static String zzd = "3";
    private static String zze = null;
    private static String zzf = null;
    private static String zzg = null;
    private static long zzh = 0;
    private static long zzi = 1000;
    private static int zzj = -1;

    public static void zza(Context context, String str, String str2) {
        try {
            if (str.equals(zzd)) {
                UserAccountMgr.zza().zza(AVError.AV_ERR_IMSDK_TIMEOUT, true, null);
                return;
            }
            if (str2 == null || str2.equals("")) {
                return;
            }
            if (str.equals(zzc)) {
                PackageManager packageManager = context.getPackageManager();
                Intent intent = new Intent(zza);
                if (packageManager.queryBroadcastReceivers(intent, 0).size() > 0) {
                    intent.putExtra(ShareConstants.MEDIA_URI, str2);
                    context.sendBroadcast(intent);
                    return;
                }
            }
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
            intent2.addCategory("android.intent.category.BROWSABLE");
            intent2.setData(Uri.parse(str2));
            if (zza(context, intent2)) {
                intent2.setComponent(new ComponentName("com.android.browser", "com.android.browser.BrowserActivity"));
                intent2.addFlags(268435456);
                context.startActivity(intent2);
            } else {
                Intent parseUri = Intent.parseUri(str2, 0);
                parseUri.addFlags(268435456);
                context.startActivity(parseUri);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LDLog.logThrowable2Local(e);
        }
    }

    private static boolean zza(Context context, Intent intent) {
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, 65536).iterator();
        while (it.hasNext()) {
            if (it.next().activityInfo.packageName.contains("com.android.browser")) {
                return true;
            }
        }
        return false;
    }

    public static boolean zza(Context context) {
        return context == null || context.getResources() == null || context.getResources().getConfiguration().orientation == 2;
    }

    public static String zza(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static boolean zzb(Context context) {
        return context.getPackageName().equals("com.android.ld.appstore");
    }

    public static boolean zza() {
        return new File("/system/lib/libldutils.so").exists();
    }

    public static String zzb() {
        try {
            if (zze == null) {
                zze = zzc(LDUtil.getApp());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zze;
    }

    public static String zzc(Context context) {
        String zzb2 = zzb(context, "phone.version");
        return (zzb2 == null || zzb2.equals("")) ? zzb(context, "ro.product.cversion") : zzb2;
    }

    public static String zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String str2 = "";
            for (byte b : MessageDigest.getInstance("MD5").digest(("\"" + str + "\"").getBytes())) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str2 = str2 + hexString;
            }
            return str2;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String zza(Context context, String str) {
        if (context == null) {
            return "";
        }
        try {
            return context.getString(context.getResources().getIdentifier(str, TypedValues.Custom.S_STRING, context.getPackageName()));
        } catch (Exception unused) {
            return context.getString(context.getResources().getIdentifier("ld_network_anomaly_text", TypedValues.Custom.S_STRING, context.getPackageName()));
        }
    }

    public static String zzc() {
        String str = zzg;
        if (str == null || str.equals("")) {
            try {
                Application app = LDUtil.getApp();
                String zzd2 = zzd(app);
                zzg = zzd2;
                if (zzd2 == null || zzd2.equals("") || zzg.equals("0")) {
                    zzg = LDIdentifiers.getAdvertId();
                }
                String str2 = zzg;
                if (str2 == null || str2.equals("") || zzg.equals("0")) {
                    zzg = app.getSharedPreferences("SpUtil", 0).getString("oaid", "");
                }
                String str3 = zzg;
                if (str3 == null || str3.equals("") || zzg.equals("0")) {
                    zzg = zze(app);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return zzg;
    }

    public static String zzd(Context context) {
        if (zzf == null) {
            try {
                String zzb2 = zzb(context, "ro.product.cmid");
                zzf = zzb2;
                if (zzb2 == null || zzb2.equals("")) {
                    zzf = zzb(context, "phone.mechineid");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return zzf;
    }

    public static String zze(Context context) {
        try {
            String string = Settings.System.getString(context.getContentResolver(), "android_id");
            return (string == null || string.equals("") || string.equals("0")) ? UUID.randomUUID().toString().replace("-", "") : string;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String zzb(Context context, String str) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) loadClass.getMethod("get", String.class).invoke(loadClass, str);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String zza(String str, String str2) {
        return str + " " + str2;
    }

    public static String zzb(String str) {
        String substring = str.substring(0, str.indexOf("@"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < substring.length(); i++) {
            sb.append("*");
        }
        return str.replace(substring, sb.toString());
    }

    public static boolean zza(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static int zza(List list, String str) {
        int i;
        int i2;
        int i3 = 0;
        if (list == null || list.size() == 0) {
            return 0;
        }
        Set<String> zzc2 = zzk.zza(LDSdk.getApp()).zzc(str);
        try {
        } catch (Exception e) {
            e = e;
        }
        if (zzc2 != null) {
            LDLog.m573e("getUnread -> " + str + "，local = " + zzc2);
            i = 0;
            for (Object obj : list) {
                try {
                    if (obj instanceof AccountMsgInfo) {
                        AccountMsgInfo accountMsgInfo = (AccountMsgInfo) obj;
                        if (accountMsgInfo.msgType != 5) {
                            i2 = accountMsgInfo.f678id;
                        }
                    } else {
                        i2 = 0;
                    }
                    if (obj instanceof GiftPackage) {
                        i2 = ((GiftPackage) obj).getId();
                    }
                    if (obj instanceof CouponItem) {
                        i2 = ((CouponItem) obj).num;
                    }
                    if (!zzc2.contains(String.valueOf(i2)) && i2 != 0) {
                        i++;
                    }
                } catch (Exception e2) {
                    e = e2;
                    i3 = i;
                    e.printStackTrace();
                    i = i3;
                    LDLog.m573e("getUnread -> " + str + "，unreadNumber = " + i);
                    return i;
                }
            }
            LDLog.m573e("getUnread -> " + str + "，unreadNumber = " + i);
            return i;
        }
        if (!list.isEmpty() && str.equals("msgSet")) {
            for (Object obj2 : list) {
                if (!(obj2 instanceof AccountMsgInfo) || ((AccountMsgInfo) obj2).msgType != 5) {
                    i3++;
                }
            }
            i = i3;
            LDLog.m573e("getUnread -> " + str + "，unreadNumber = " + i);
            return i;
        }
        return list.size();
    }

    public static void zza(Context context, List list, String str) {
        if (list == null) {
            return;
        }
        Set<String> zzc2 = zzk.zza(context).zzc(str);
        HashSet hashSet = new HashSet();
        if (zzc2 != null && zzc2.size() > 0) {
            hashSet.addAll(zzc2);
        }
        try {
            for (Object obj : list) {
                if (obj instanceof AccountMsgInfo) {
                    hashSet.add(String.valueOf(((AccountMsgInfo) obj).f678id));
                }
                if (obj instanceof GiftPackage) {
                    hashSet.add(String.valueOf(((GiftPackage) obj).getId()));
                }
                if (obj instanceof CouponItem) {
                    hashSet.add(String.valueOf(((CouponItem) obj).num));
                }
                if (obj instanceof CouponBean) {
                    hashSet.add(String.valueOf(((CouponBean) obj).couponId));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        zzk.zza(context).zzd(str);
        zzk.zza(context).zza(str, hashSet);
    }

    public static int zza(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean zzc(String str) {
        return str.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    }

    public static String zzb(String str, String str2) {
        int indexOf;
        int length;
        if (str == null || str.trim().length() == 0 || !str2.contains(str) || (length = str.length() + (indexOf = str2.indexOf(str))) == 0 || indexOf == -1) {
            return str2;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) str2);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), indexOf, length, 33);
        return spannableStringBuilder.toString();
    }

    public static boolean zzd() {
        return zza(-1, zzi);
    }

    public static boolean zza(int i, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = zzh;
        long j3 = currentTimeMillis - j2;
        if (zzj == i && j2 > 0 && j3 < j) {
            return true;
        }
        zzh = currentTimeMillis;
        zzj = i;
        return false;
    }

    public static void zzc(Context context, String str) {
        if (context == null) {
            return;
        }
        try {
            File file = new File(str);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            if (Build.VERSION.SDK_INT >= 24) {
                Uri uriForFile = FileProvider.getUriForFile(context, zzf(context), file);
                intent.setFlags(1);
                intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }
            context.startActivity(intent);
        } catch (Exception e) {
            LDUtil.toast("安装出错");
            e.printStackTrace();
        }
    }

    public static String zzf(Context context) {
        try {
            for (ProviderInfo providerInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers) {
                if (providerInfo.name.equals("com.ld.sdk.internal.ApkFileProvider")) {
                    LDLog.m573e("install apk authority: " + providerInfo.authority);
                    return providerInfo.authority;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("install apk authority", "" + e.toString());
        }
        return "";
    }

    public static boolean zzd(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static void zza(Context context, CharSequence charSequence) {
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", charSequence));
        LDUtil.toast(zzt.zza(context, "ld_copy_success_text"));
    }
}
