package com.xsdk.ab_firstbase.statisics.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Ascii;
import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* loaded from: classes3.dex */
public class Util {
    private static String ANDROID_ID = null;
    private static String CPU = "";
    private static int HASROOT = -1;
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final int MOBILE_NETWORK = 0;
    private static final int NETWORK_CLASS_2_G = 1;
    private static final int NETWORK_CLASS_3_G = 2;
    private static final int NETWORK_CLASS_4_G = 3;
    private static final int NETWORK_CLASS_5_G = 4;
    private static final int NETWORK_CLASS_UNAVAILABLE = -1;
    private static final int NETWORK_CLASS_UNKNOWN = 0;
    private static final int NETWORK_CLASS_WIFI = -101;
    public static final int NETWORK_TYPE_1xRTT = 7;
    public static final int NETWORK_TYPE_CDMA = 4;
    public static final int NETWORK_TYPE_EDGE = 2;
    public static final int NETWORK_TYPE_EHRPD = 14;
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    public static final int NETWORK_TYPE_EVDO_A = 6;
    public static final int NETWORK_TYPE_EVDO_B = 12;
    public static final int NETWORK_TYPE_GPRS = 1;
    public static final int NETWORK_TYPE_HSDPA = 8;
    public static final int NETWORK_TYPE_HSPA = 10;
    public static final int NETWORK_TYPE_HSPAP = 15;
    public static final int NETWORK_TYPE_HSUPA = 9;
    public static final int NETWORK_TYPE_IDEN = 11;
    public static final int NETWORK_TYPE_LTE = 13;
    public static final int NETWORK_TYPE_NR = 20;
    public static final int NETWORK_TYPE_UMTS = 3;
    private static final int NETWORK_TYPE_UNAVAILABLE = -1;
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    private static final int NETWORK_TYPE_WIFI = -101;
    private static final int UNKNOWN_NETWORK = 2;
    private static final int WIFI_NETWORK = 1;
    private static long firsttime = 0;
    public static String gOAId = null;
    private static String lang = "";
    private static long mLastClickTime;
    private static String signatureSha1;

    public static String execShell(String str) {
        return "";
    }

    private static int getNetworkClassByType(int i) {
        int i2 = -101;
        if (i != -101) {
            i2 = -1;
            if (i != -1) {
                if (i == 20) {
                    return 4;
                }
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return 1;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return 2;
                    case 13:
                        return 3;
                    default:
                        return 0;
                }
            }
        }
        return i2;
    }

    public static String getSystemLang() {
        if (TextUtils.isEmpty(lang)) {
            lang = LanguageType.SERVER_EN;
        }
        return lang;
    }

    public static void setSystemLang(String str) {
        lang = str;
    }

    public static int getOrientation(Activity activity) {
        try {
            int i = activity.getResources().getConfiguration().orientation;
            if (i == 1) {
                return 1;
            }
            return i == 2 ? 2 : 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static int getIdByName(Context context, String str, String str2) {
        if (context == null) {
            context = ContextHolder.getContext();
        }
        return context.getResources().getIdentifier(str2, str, context.getPackageName());
    }

    public static String urlEncoded(String str) {
        if (str != null && !str.equals("")) {
            try {
                return URLEncoder.encode(new String(str.getBytes(), "UTF-8"), "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static synchronized String getAppName(Context context) {
        String string;
        synchronized (Util.class) {
            try {
                string = context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return string;
    }

    public static boolean checkAppInstalled(Context context, String str) {
        PackageInfo packageInfo;
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static void doStartApplicationWithPackageName(Activity activity, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = activity.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(packageInfo.packageName);
        ResolveInfo next = activity.getPackageManager().queryIntentActivities(intent, 0).iterator().next();
        if (next != null) {
            String str2 = next.activityInfo.packageName;
            String str3 = next.activityInfo.name;
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.setComponent(new ComponentName(str2, str3));
            activity.startActivity(intent2);
        }
    }

    public static String readtxt(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
            int i = 0;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return sb.toString();
                }
                if (i != 0) {
                    sb.append(new String(("\r\n" + readLine).getBytes(), "utf-8"));
                } else {
                    sb.append(new String(readLine.getBytes(), "utf-8"));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized String getVersionName(Context context) {
        String str;
        synchronized (Util.class) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return str;
    }

    public static synchronized int getVersionCode(Context context) {
        int i;
        synchronized (Util.class) {
            try {
                i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (Throwable th) {
                th.printStackTrace();
                return 0;
            }
        }
        return i;
    }

    public static synchronized String getPackageName(Context context) {
        String str;
        synchronized (Util.class) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return str;
    }

    public static synchronized int getTargetSdkVersion(Context context) {
        int i;
        synchronized (Util.class) {
            try {
                i = context.getApplicationInfo().targetSdkVersion;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return i;
    }

    public static synchronized int getMinSdkVersion(Context context) {
        int i;
        synchronized (Util.class) {
            try {
                i = Build.VERSION.SDK_INT >= 24 ? context.getApplicationInfo().minSdkVersion : 0;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getGoogleShopUrl(android.content.Context r4) {
        /*
            java.lang.String r0 = "getGoogleShopUrl"
            java.lang.String r1 = "https://play.google.com/store/apps/details?id="
            java.lang.String r2 = ""
            java.lang.String r4 = getPackageName(r4)     // Catch: java.lang.Throwable -> L23
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L23
            if (r3 == 0) goto L16
            java.lang.String r4 = "pagkageName null"
            android.util.Log.e(r0, r4)     // Catch: java.lang.Throwable -> L23
            goto L39
        L16:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L23
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L23
            r3.append(r4)     // Catch: java.lang.Throwable -> L23
            java.lang.String r4 = r3.toString()     // Catch: java.lang.Throwable -> L23
            goto L3a
        L23:
            r4 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "pagkageName e"
            r1.<init>(r3)
            java.lang.String r4 = r4.toString()
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            android.util.Log.e(r0, r4)
        L39:
            r4 = r2
        L3a:
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L41
            goto L42
        L41:
            r2 = r4
        L42:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xsdk.ab_firstbase.statisics.util.Util.getGoogleShopUrl(android.content.Context):java.lang.String");
    }

    public static void clearCookiesAndCache(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeAllCookies(null);
    }

    public static String getString(Context context, String str) {
        if (context == null) {
            return "";
        }
        try {
            return context.getString(getIdByName(context, TypedValues.Custom.S_STRING, str));
        } catch (Throwable unused) {
            Log.i("getString xml", "error getString : name " + str);
            return "";
        }
    }

    public static String getStringInt(Context context, String str, int i) {
        if (context == null) {
            return "";
        }
        try {
            return context.getString(getIdByName(context, TypedValues.Custom.S_STRING, str), Integer.valueOf(i));
        } catch (Throwable unused) {
            Log.i("getString xml", "error getStringInt : name " + str + " number " + i);
            return "";
        }
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean isEmail(String str) {
        if (TextUtils.isEmpty("^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$")) {
            return false;
        }
        return str.matches("^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
    }

    public static boolean isNumeric(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static long getStringToDate(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getISO8601Timestamp() {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZ");
        simpleDateFormat.setTimeZone(timeZone);
        String format = simpleDateFormat.format(new Date());
        return TextUtils.isEmpty(format) ? "" : format;
    }

    public static Locale getSysLocale() {
        if (Build.VERSION.SDK_INT >= 24) {
            return LocaleList.getDefault().get(0);
        }
        return Locale.getDefault();
    }

    public static String getISO3Country() {
        return getSysLocale().getISO3Country();
    }

    public static String getCountry() {
        String country = getSysLocale().getCountry();
        return TextUtils.isEmpty(country) ? "" : country;
    }

    public static String getISO3Language() {
        return getSysLocale().getISO3Language();
    }

    public static String getCurrentLauguage() {
        return Locale.getDefault().getLanguage();
    }

    public static String sortedMapJsonParams(Map<String, Object> map) {
        TreeMap treeMap = new TreeMap(new Comparator<String>() { // from class: com.xsdk.ab_firstbase.statisics.util.Util.1
            @Override // java.util.Comparator
            public int compare(String str, String str2) {
                return str.compareTo(str2);
            }
        });
        treeMap.putAll(map);
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : treeMap.entrySet()) {
            try {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                Log.i("sortedMdPParams", "e=" + e.toString());
            }
        }
        return jSONObject.toString();
    }

    public static String sortedMapPingjieParams(Map<String, Object> map) {
        TreeMap treeMap = new TreeMap(new Comparator<String>() { // from class: com.xsdk.ab_firstbase.statisics.util.Util.2
            @Override // java.util.Comparator
            public int compare(String str, String str2) {
                return str.compareTo(str2);
            }
        });
        treeMap.putAll(map);
        String str = "";
        for (Map.Entry entry : treeMap.entrySet()) {
            str = str + ((String) entry.getKey()) + "=" + entry.getValue() + "&";
        }
        return (str.length() <= 0 || !str.endsWith("&")) ? str : str.substring(0, str.length() - 1);
    }

    public static synchronized Bitmap getBitmap(Context context) {
        PackageManager packageManager;
        Bitmap bitmap;
        synchronized (Util.class) {
            ApplicationInfo applicationInfo = null;
            try {
                packageManager = context.getApplicationContext().getPackageManager();
                try {
                    applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException unused) {
                }
            } catch (PackageManager.NameNotFoundException unused2) {
                packageManager = null;
            }
            bitmap = ((BitmapDrawable) packageManager.getApplicationIcon(applicationInfo)).getBitmap();
        }
        return bitmap;
    }

    public static Bundle mapToBundle(Map<String, Object> map) {
        Bundle bundle = new Bundle();
        try {
            for (String str : map.keySet()) {
                bundle.putString(str, String.valueOf(map.get(str)));
            }
        } catch (Throwable th) {
            Log.e("mapToBundle", "e" + th.toString());
        }
        return bundle;
    }

    public static String getUniqueID(Context context) {
        return "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.DISPLAY.length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10);
    }

    public static String getAndroidID(Context context) {
        if (!TextUtils.isEmpty(ANDROID_ID)) {
            return ANDROID_ID;
        }
        try {
            ANDROID_ID = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
            ANDROID_ID = "";
        }
        if (TextUtils.isEmpty(ANDROID_ID)) {
            String stringKeyForValue = ImageUtils.getStringKeyForValue(context, Constants.SDK_ANDROIDID);
            if (TextUtils.isEmpty(stringKeyForValue)) {
                String str = "auto_" + getUUID();
                ANDROID_ID = str;
                ImageUtils.setSharePreferences(context, Constants.SDK_ANDROIDID, str);
            } else {
                ANDROID_ID = stringKeyForValue;
            }
        }
        return ANDROID_ID;
    }

    public static String getSharePreferencesUUID(Context context) {
        String stringKeyForValue = ImageUtils.getStringKeyForValue(context, Constants.SDK_ANDROID_UUID);
        if (!TextUtils.isEmpty(stringKeyForValue)) {
            return stringKeyForValue;
        }
        String uuid = getUUID();
        ImageUtils.setSharePreferences(context, Constants.SDK_ANDROID_UUID, uuid);
        return uuid;
    }

    public static String getUUID() {
        try {
            return UUID.randomUUID().toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String getOnlyDevice(Context context) {
        try {
            if (!TextUtils.isEmpty(getAndroidID(context))) {
                return getAndroidID(context);
            }
            return getUniqueID(context);
        } catch (Throwable unused) {
            return "123456";
        }
    }

    public static String getGMT() {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss '(UTC)'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat.format(calendar.getTime());
        } catch (Throwable th) {
            System.out.println("strgetGMT error=" + th.toString());
            return "";
        }
    }

    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getOSVersion() {
        String str = Build.VERSION.RELEASE;
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static String getPhoneModel() {
        String str = Build.MODEL;
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static String getPhoneMANUFACTURER() {
        String str = Build.MANUFACTURER;
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static boolean isStringNull(String str) {
        return str == null || str.equals("");
    }

    public static String exceptNullStr(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static String getCurrentNetworkType(Activity activity) {
        int networkClass = getNetworkClass(activity);
        System.out.println("***************networkClass=" + networkClass);
        return networkClass != -101 ? networkClass != -1 ? networkClass != 1 ? networkClass != 2 ? networkClass != 3 ? networkClass != 4 ? "未知" : "3" : "2" : "1" : "0" : Constants.SDK_LOGIN_TYPE_LOGOUT : "5";
    }

    private static int getNetworkClass(Activity activity) {
        int i = 0;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) activity.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
                int type = activeNetworkInfo.getType();
                if (type == 1) {
                    i = -101;
                } else if (type == 0) {
                    i = ((TelephonyManager) activity.getSystemService("phone")).getNetworkType();
                }
            } else {
                i = -1;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return getNetworkClassByType(i);
    }

    public static String getKeyHash(Context context, String str) {
        try {
            Log.d("KeyHash", "packageNmae=" + str);
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
            if (signatureArr.length <= 0) {
                return "";
            }
            Signature signature = signatureArr[0];
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(signature.toByteArray());
            String encodeToString = android.util.Base64.encodeToString(messageDigest.digest(), 0);
            Log.d("KeyHash:", "KeyHash是" + encodeToString);
            return encodeToString;
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException unused) {
            return "";
        }
    }

    public static boolean isEmulator() {
        String execShell = execShell("getprop ro.product.cpu.abi");
        return !TextUtils.isEmpty(execShell) && execShell.contains("x86");
    }

    public static boolean isOpenProxy(Context context) {
        return isWifiProxy(context) || checkVPN(context);
    }

    public static boolean isWifiProxy(Context context) {
        try {
            String property = System.getProperty("http.proxyHost");
            String property2 = System.getProperty("http.proxyPort");
            if (property2 == null) {
                property2 = Constants.SDK_LOGIN_TYPE_LOGOUT;
            }
            return (TextUtils.isEmpty(property) || Integer.parseInt(property2) == -1) ? false : true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private static boolean checkVPN(Context context) {
        try {
            NetworkInfo networkInfo = getConnectivityManager(context).getNetworkInfo(17);
            if (networkInfo == null) {
                return false;
            }
            return networkInfo.isConnected();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static int getNetWorkType(Context context) {
        boolean z;
        boolean z2;
        if (context == null) {
            return 2;
        }
        try {
            z = false;
            z2 = false;
            for (Network network : getConnectivityManager(context).getAllNetworks()) {
                try {
                    NetworkInfo networkInfo = getConnectivityManager(context).getNetworkInfo(network);
                    if (networkInfo == null) {
                        return 2;
                    }
                    if (networkInfo.getType() == 1) {
                        z |= networkInfo.isConnected();
                    }
                    if (networkInfo.getType() == 0) {
                        z2 |= networkInfo.isConnected();
                    }
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            z = false;
            z2 = false;
        }
        if (z) {
            return 1;
        }
        return z2 ? 0 : 2;
    }

    private static int getSensorNumber(Context context) {
        return ((SensorManager) context.getSystemService("sensor")).getSensorList(-1).size();
    }

    public static int isSimulator(Context context) {
        return getSensorNumber(context) < 17 ? 1 : 0;
    }

    public static int isUseAgent(Context context) {
        return checkVPN(context) ? 1 : 0;
    }

    public static String getCpu() {
        if (!TextUtils.isEmpty(CPU)) {
            return CPU;
        }
        try {
            String[] strArr = Build.SUPPORTED_ABIS;
            if (strArr != null && strArr.length > 0) {
                CPU = strArr[0];
            }
        } catch (Exception unused) {
        }
        return CPU;
    }

    public static String getScreenResolution(Context context) {
        if (context == null) {
            return "UNKNOW";
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
    }

    public static String getSocManufacturer() {
        try {
            return Build.SOC_MANUFACTURER;
        } catch (Throwable th) {
            Log.d("getSocManufacturer", "e: " + th.toString());
            return "";
        }
    }

    public static String getSocModel() {
        try {
            return Build.SOC_MODEL;
        } catch (Throwable th) {
            Log.d("getSocModel", "e: " + th.toString());
            return "";
        }
    }

    public static String getMemUnused(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return Formatter.formatFileSize(context, memoryInfo.availMem);
        } catch (Throwable th) {
            Log.d("getMemUnused", "e: " + th.toString());
            return "";
        }
    }

    public static String getTotalMemory(Context context) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            long longValue = new Long(Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue() * 1024).longValue();
            bufferedReader.close();
            return Formatter.formatFileSize(context, longValue);
        } catch (Throwable th) {
            Log.d("getTotalMemory", "e: " + th.toString());
            return "";
        }
    }

    private static boolean checkRoot() {
        boolean z;
        String[] strArr = {"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb", "/system/app/Superuser.apk"};
        int i = 0;
        while (true) {
            if (i >= 19) {
                z = false;
                break;
            }
            if (new File(strArr[i]).exists()) {
                z = true;
                break;
            }
            i++;
        }
        return (Build.TAGS != null && Build.TAGS.contains("test-keys")) || z;
    }

    public static int hasRoot() {
        int i = HASROOT;
        if (i != -1) {
            return i;
        }
        boolean checkRoot = checkRoot();
        HASROOT = checkRoot ? 1 : 0;
        return checkRoot ? 1 : 0;
    }

    public static String getSignatureSHA1(Context context) {
        if (!TextUtils.isEmpty(signatureSha1)) {
            return signatureSha1;
        }
        if (context == null) {
            try {
                context = ContextHolder.getContext();
            } catch (Exception unused) {
                return "";
            }
        }
        Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
        MessageDigest messageDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1);
        if (signatureArr != null) {
            for (Signature signature : signatureArr) {
                messageDigest.update(signature.toByteArray());
            }
        }
        String hexString = toHexString(messageDigest.digest());
        signatureSha1 = hexString;
        return hexString;
    }

    private static String toHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            char[] cArr = HEX_DIGITS;
            sb.append(cArr[(bArr[i] & 240) >>> 4]);
            sb.append(cArr[bArr[i] & Ascii.f555SI]);
        }
        return MD5.getMD5String(sb.toString());
    }

    public static String getAppName() {
        try {
            Context context = ContextHolder.getContext();
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 128)).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
