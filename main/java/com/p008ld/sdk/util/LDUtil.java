package com.p008ld.sdk.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import com.google.gson.Gson;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.util.zza;
import com.tencent.p014av.ptt.PttError;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;

/* compiled from: LDUtil.kt */
/* loaded from: classes2.dex */
public final class LDUtil {
    public static final LDUtil INSTANCE = new LDUtil();
    private static final ThreadLocal<Map<String, SimpleDateFormat>> SDF_THREAD_LOCAL = new zza();
    private static Application sApp;

    private LDUtil() {
    }

    @JvmStatic
    public static final String md5(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return md5(bytes);
    }

    @JvmStatic
    public static final String md5(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(data);
            byte[] d = messageDigest.digest();
            Intrinsics.checkNotNullExpressionValue(d, "d");
            return bytesToHexString(d);
        } catch (Exception e) {
            Log.w("Algorithms.java getMD5", Log.getStackTraceString(e));
            return "";
        }
    }

    @JvmStatic
    private static final String bytesToHexString(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(cArr[Util.and(bArr[i], 240) >>> 4]);
            sb.append(cArr[Util.and(bArr[i], 15)]);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "buf.toString()");
        return sb2;
    }

    @JvmStatic
    public static final String getNowTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = new Date();
        try {
            Date parse = simpleDateFormat.parse(simpleDateFormat.format(date));
            Intrinsics.checkNotNullExpressionValue(parse, "sdf.parse(sdf.format(now))");
            date = parse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String format = INSTANCE.getDefaultFormat().format(date);
        Intrinsics.checkNotNullExpressionValue(format, "getDefaultFormat().format(now)");
        return format;
    }

    private final SimpleDateFormat getDefaultFormat() {
        return getSafeDateFormat("yyyyMMddHHmmss");
    }

    /* compiled from: LDUtil.kt */
    /* loaded from: classes2.dex */
    public static final class zza extends ThreadLocal<Map<String, ? extends SimpleDateFormat>> {
        zza() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public Map<String, SimpleDateFormat> initialValue() {
            return new HashMap();
        }
    }

    @JvmStatic
    public static final SimpleDateFormat getSafeDateFormat(String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        Map<String, SimpleDateFormat> map = SDF_THREAD_LOCAL.get();
        Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, java.text.SimpleDateFormat>");
        Map asMutableMap = TypeIntrinsics.asMutableMap(map);
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) asMutableMap.get(pattern);
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern, Locale.ENGLISH);
        asMutableMap.put(pattern, simpleDateFormat2);
        return simpleDateFormat2;
    }

    @JvmStatic
    public static final String millis2String(long j, DateFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return format.format(new Date(j));
    }

    @JvmStatic
    public static final int dip2px(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    @JvmStatic
    public static final int px2dp(float f) {
        return (int) ((f / Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    @JvmStatic
    public static final int sp2px(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    @JvmStatic
    public static final int px2sp(float f) {
        return (int) ((f / Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    @JvmStatic
    public static final void init(Application application) {
        if (application == null) {
            Log.e("Utils", "app is null.");
            return;
        }
        Application application2 = sApp;
        if (application2 == null) {
            sApp = application;
            com.p008ld.sdk.util.zza.zza.zza(application);
        } else {
            if (Intrinsics.areEqual(application2, application)) {
                return;
            }
            com.p008ld.sdk.util.zza.zza.zzb(application);
            sApp = application;
            com.p008ld.sdk.util.zza.zza.zza(application);
        }
    }

    @JvmStatic
    public static final Application getApp() {
        Application application = sApp;
        if (application != null) {
            Intrinsics.checkNotNull(application);
            return application;
        }
        init(com.p008ld.sdk.util.zza.zza.zzd());
        Application application2 = sApp;
        if (application2 == null) {
            throw new NullPointerException("reflect failed.");
        }
        Intrinsics.checkNotNull(application2);
        return application2;
    }

    @JvmStatic
    public static final Activity getTopActivity() {
        return com.p008ld.sdk.util.zza.zza.zza();
    }

    @JvmStatic
    public static final boolean isAppForeground() {
        return com.p008ld.sdk.util.zza.zza.zzc();
    }

    @JvmStatic
    public static final void registerAppStatusChangedListener(zza.zzb listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        com.p008ld.sdk.util.zza.zza.zza(listener);
    }

    @JvmStatic
    public static final void removeOnAppStatusChangedListener(zza.zzb listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        com.p008ld.sdk.util.zza.zza.zzb(listener);
    }

    @JvmStatic
    public static final Bitmap stringToBitmap(String base64Str) {
        Intrinsics.checkNotNullParameter(base64Str, "base64Str");
        try {
            byte[] decode = Base64.decode(base64Str, 0);
            Intrinsics.checkNotNullExpressionValue(decode, "decode(base64Str, Base64.DEFAULT)");
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @JvmStatic
    public static final Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(\n          …g.ARGB_8888\n            )");
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectF = new RectF(new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()));
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            canvas.drawRoundRect(rectF, 10.0f, 10.0f, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), rect, paint);
            return createBitmap;
        } catch (Exception unused) {
            return bitmap;
        }
    }

    @JvmStatic
    public static final void toast(int i) {
        Activity topActivity = getTopActivity();
        if (topActivity != null) {
            zzm.zza(new zzb(topActivity, i));
        }
    }

    /* compiled from: LDUtil.kt */
    /* loaded from: classes2.dex */
    static final class zzb extends Lambda implements Function0<Unit> {
        final /* synthetic */ Activity zza;
        final /* synthetic */ int zzb;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzb(Activity activity, int i) {
            super(0);
            this.zza = activity;
            this.zzb = i;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* synthetic */ Unit invoke() {
            zza();
            return Unit.INSTANCE;
        }

        public final void zza() {
            LDUtil.showToast(this.zza.getString(this.zzb));
        }
    }

    @JvmStatic
    public static final void toast(String str) {
        if (getTopActivity() != null) {
            zzm.zza(new zzc(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LDUtil.kt */
    /* loaded from: classes2.dex */
    public static final class zzc extends Lambda implements Function0<Unit> {
        final /* synthetic */ String zza;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzc(String str) {
            super(0);
            this.zza = str;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* synthetic */ Unit invoke() {
            zza();
            return Unit.INSTANCE;
        }

        public final void zza() {
            LDUtil.showToast(this.zza);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final void showToast(String str) {
        Object m1882constructorimpl;
        Unit unit;
        try {
            Result.Companion companion = Result.INSTANCE;
            if (str != null) {
                Toast.makeText(LDSdk.getApp(), str, 0).show();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            m1882constructorimpl = Result.m1882constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1885exceptionOrNullimpl = Result.m1885exceptionOrNullimpl(m1882constructorimpl);
        if (m1885exceptionOrNullimpl != null) {
            m1885exceptionOrNullimpl.printStackTrace();
        }
    }

    @JvmStatic
    public static final String toJson(Object obj) {
        String json = zzm.zza().toJson(obj);
        Intrinsics.checkNotNullExpressionValue(json, "gson.toJson(this)");
        return json;
    }

    @JvmStatic
    public static final int getIdentifier(Context context, String type, String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(name, "name");
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

    @JvmStatic
    public static final void transparentStatusBar(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Window window = activity.getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "activity.window");
        transparentStatusBar(window);
    }

    @JvmStatic
    public static final void transparentStatusBar(Window window) {
        Intrinsics.checkNotNullParameter(window, "window");
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | PttError.GMESDK_UNINSTALLERROR);
        window.setStatusBarColor(0);
    }

    @JvmStatic
    public static final boolean isGooglePlayServicesAvailable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Method methodQuietly = getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
        if (methodQuietly == null) {
            return false;
        }
        Object invokeMethodQuietly = invokeMethodQuietly(null, methodQuietly, context);
        return (invokeMethodQuietly instanceof Integer) && Intrinsics.areEqual(invokeMethodQuietly, (Object) 0);
    }

    @JvmStatic
    public static final Method getMethodQuietly(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        try {
            return clazz.getMethod(methodName, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    @JvmStatic
    public static final Method getMethodQuietly(String className, String methodName, Class<?>... parameterTypes) {
        Intrinsics.checkNotNullParameter(className, "className");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        try {
            Class<?> clazz = Class.forName(className);
            Intrinsics.checkNotNullExpressionValue(clazz, "clazz");
            return getMethodQuietly(clazz, methodName, (Class<?>[]) Arrays.copyOf(parameterTypes, parameterTypes.length));
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    @JvmStatic
    public static final Object invokeMethodQuietly(Object obj, Method method, Object... args) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(args, "args");
        try {
            return method.invoke(obj, Arrays.copyOf(args, args.length));
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @JvmStatic
    public static final <T> T fromJson(String json, Class<T> cls) {
        Intrinsics.checkNotNullParameter(json, "json");
        try {
            return (T) new Gson().fromJson(json, (Class) cls);
        } catch (Exception unused) {
            return null;
        }
    }

    @JvmStatic
    public static final String genUUID() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        return StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
    }

    @JvmStatic
    public static final void startGooglePlay(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName));
        intent.addFlags(268435456);
        try {
            intent.setPackage("com.android.vending");
            context.startActivity(intent);
        } catch (Exception e) {
            LDLog.m573e("Go to GP app occurs exception : " + e);
            try {
                intent.setPackage("");
                context.startActivity(intent);
            } catch (Exception e2) {
                LDLog.m573e("Go to app market occurs exception : " + e2);
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
                intent2.addFlags(268435456);
                try {
                    context.startActivity(intent2);
                } catch (Exception unused) {
                    LDLog.m573e("Finally launch app market failed.");
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0041, code lost:
    
        if (r0.equals("tr") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00b3, code lost:
    
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004b, code lost:
    
        if (r0.equals(com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType.SERVER_TH) == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0055, code lost:
    
        if (r0.equals(com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType.SERVER_RU) == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005f, code lost:
    
        if (r0.equals("pt") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008c, code lost:
    
        if (r0.equals(com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType.SERVER_FR) == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0095, code lost:
    
        if (r0.equals("fa") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009e, code lost:
    
        if (r0.equals(com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType.SERVER_ES) == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a7, code lost:
    
        if (r0.equals(com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType.SERVER_DE) == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b0, code lost:
    
        if (r0.equals("ar") == false) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String getAreaFromLanguage() {
        /*
            r2 = this;
            java.util.Locale r0 = java.util.Locale.getDefault()
            java.lang.String r0 = r0.getLanguage()
            java.lang.String r1 = "locale.language"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r0 = r0.toLowerCase()
            java.lang.String r1 = "this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            int r1 = r0.hashCode()
            switch(r1) {
                case 3121: goto Laa;
                case 3201: goto La1;
                case 3246: goto L98;
                case 3259: goto L8f;
                case 3276: goto L86;
                case 3365: goto L7a;
                case 3383: goto L6e;
                case 3428: goto L62;
                case 3588: goto L59;
                case 3651: goto L4f;
                case 3700: goto L45;
                case 3710: goto L3b;
                case 3763: goto L2d;
                case 3886: goto L1f;
                default: goto L1d;
            }
        L1d:
            goto Lb5
        L1f:
            java.lang.String r1 = "zh"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L29
            goto Lb5
        L29:
            java.lang.String r0 = "tw"
            goto Lb7
        L2d:
            java.lang.String r1 = "vi"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L37
            goto Lb5
        L37:
            java.lang.String r0 = "vn"
            goto Lb7
        L3b:
            java.lang.String r1 = "tr"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto Lb3
            goto Lb5
        L45:
            java.lang.String r1 = "th"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto Lb3
            goto Lb5
        L4f:
            java.lang.String r1 = "ru"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto Lb3
            goto Lb5
        L59:
            java.lang.String r1 = "pt"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto Lb3
            goto Lb5
        L62:
            java.lang.String r1 = "ko"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L6b
            goto Lb5
        L6b:
            java.lang.String r0 = "kr"
            goto Lb7
        L6e:
            java.lang.String r1 = "ja"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L77
            goto Lb5
        L77:
            java.lang.String r0 = "jp"
            goto Lb7
        L7a:
            java.lang.String r1 = "in"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L83
            goto Lb5
        L83:
            java.lang.String r0 = "id"
            goto Lb7
        L86:
            java.lang.String r1 = "fr"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto Lb3
            goto Lb5
        L8f:
            java.lang.String r1 = "fa"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto Lb3
            goto Lb5
        L98:
            java.lang.String r1 = "es"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto Lb3
            goto Lb5
        La1:
            java.lang.String r1 = "de"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto Lb3
            goto Lb5
        Laa:
            java.lang.String r1 = "ar"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto Lb3
            goto Lb5
        Lb3:
            r0 = r1
            goto Lb7
        Lb5:
            java.lang.String r0 = "en"
        Lb7:
            java.lang.String r1 = "getAreaFromLanguage: "
            java.lang.String r1 = r1.concat(r0)
            com.p008ld.sdk.util.LDLog.m573e(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.util.LDUtil.getAreaFromLanguage():java.lang.String");
    }
}
