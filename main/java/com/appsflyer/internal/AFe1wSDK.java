package com.appsflyer.internal;

import android.graphics.Color;
import android.graphics.ImageFormat;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ExpandableListView;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerProperties;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes.dex */
public final class AFe1wSDK {
    private static int $10 = 0;
    private static int $11 = 1;
    private static String AFInAppEventType = null;
    private static char[] AFLogger = null;

    /* renamed from: e */
    private static char f203e = 0;
    private static int registerClient = 1;
    private static int unregisterClient;
    public static String values;
    private final AFe1vSDK AFInAppEventParameterName;
    private final AFd1sSDK AFKeystoreWrapper;

    /* renamed from: d */
    private final AFe1gSDK f204d;
    private final AppsFlyerProperties valueOf;

    static void AFKeystoreWrapper() {
        AFLogger = new char[]{29503, 29494, 29489, 29465, 29557, 29484, 29482, 28867, 29500, 29497, 29498, 29485, 28866, 29501, 29502, 29451};
        f203e = (char) 18118;
    }

    static {
        AFKeystoreWrapper();
        values = "https://%sgcdsdk.%s/install_data/v5.0/";
        AFInAppEventType = "https://%sonelink.%s/shortlink-sdk/v2";
        int i = unregisterClient + 65;
        registerClient = i % 128;
        int i2 = i % 2;
    }

    public AFe1wSDK(AFe1vSDK aFe1vSDK, AFd1sSDK aFd1sSDK, AppsFlyerProperties appsFlyerProperties, AFe1gSDK aFe1gSDK) {
        this.AFInAppEventParameterName = aFe1vSDK;
        this.AFKeystoreWrapper = aFd1sSDK;
        this.valueOf = appsFlyerProperties;
        this.f204d = aFe1gSDK;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.appsflyer.internal.AFe1uSDK<java.lang.String> AFInAppEventParameterName(java.util.Map<java.lang.String, java.lang.Object> r11, java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 307
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFe1wSDK.AFInAppEventParameterName(java.util.Map, java.lang.String, java.lang.String):com.appsflyer.internal.AFe1uSDK");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.appsflyer.internal.AFe1uSDK<java.lang.String> AFInAppEventType(java.util.Map<java.lang.String, java.lang.Object> r17, java.lang.String r18, java.lang.String r19) {
        /*
            Method dump skipped, instructions count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFe1wSDK.AFInAppEventType(java.util.Map, java.lang.String, java.lang.String):com.appsflyer.internal.AFe1uSDK");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0023, code lost:
    
        r8 = com.appsflyer.internal.AFe1gSDK.values;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0021, code lost:
    
        if (r8 != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
    
        if (r8 != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0026, code lost:
    
        r8 = com.appsflyer.internal.AFe1gSDK.AFKeystoreWrapper;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.appsflyer.internal.AFe1uSDK<com.appsflyer.internal.AFh1hSDK> valueOf(boolean r8, boolean r9, java.lang.String r10, int r11) {
        /*
            r7 = this;
            int r11 = com.appsflyer.internal.AFe1wSDK.unregisterClient
            int r11 = r11 + 109
            int r0 = r11 % 128
            com.appsflyer.internal.AFe1wSDK.registerClient = r0
            r0 = 2
            int r11 = r11 % r0
            r1 = 0
            java.lang.String r2 = ""
            if (r11 != 0) goto L1c
            com.appsflyer.internal.AFe1gSDK r11 = r7.f204d
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r2)
            r3 = 78
            int r3 = r3 / r1
            if (r8 == 0) goto L26
            goto L23
        L1a:
            r8 = move-exception
            throw r8
        L1c:
            com.appsflyer.internal.AFe1gSDK r11 = r7.f204d
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r2)
            if (r8 == 0) goto L26
        L23:
            java.lang.String r8 = com.appsflyer.internal.AFe1gSDK.values
            goto L28
        L26:
            java.lang.String r8 = com.appsflyer.internal.AFe1gSDK.AFKeystoreWrapper
        L28:
            r3 = 1
            if (r9 == 0) goto L2d
            r9 = r3
            goto L2e
        L2d:
            r9 = r1
        L2e:
            if (r9 == 0) goto L3c
            int r9 = com.appsflyer.internal.AFe1wSDK.unregisterClient
            int r9 = r9 + 25
            int r4 = r9 % 128
            com.appsflyer.internal.AFe1wSDK.registerClient = r4
            int r9 = r9 % r0
            java.lang.String r9 = "stg"
            goto L3d
        L3c:
            r9 = r2
        L3d:
            kotlin.jvm.internal.StringCompanionObject r4 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            r4 = 4
            java.lang.Object[] r5 = new java.lang.Object[r4]
            boolean r6 = com.appsflyer.internal.AFe1gSDK.valueOf()
            if (r6 == 0) goto L4a
            r6 = r3
            goto L4b
        L4a:
            r6 = r1
        L4b:
            if (r6 == 0) goto L56
            kotlin.Lazy r6 = r11.AFInAppEventType
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L57
        L56:
            r6 = r2
        L57:
            r5[r1] = r6
            r5[r3] = r9
            java.lang.String r9 = r11.AFInAppEventType()
            r5[r0] = r9
            r9 = 3
            r5[r9] = r10
            java.lang.Object[] r9 = java.util.Arrays.copyOf(r5, r4)
            java.lang.String r8 = java.lang.String.format(r8, r9)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r2)
            com.appsflyer.internal.AFe1oSDK r9 = new com.appsflyer.internal.AFe1oSDK
            java.lang.String r10 = "GET"
            r9.<init>(r8, r10)
            r8 = 1500(0x5dc, float:2.102E-42)
            r9.unregisterClient = r8
            com.appsflyer.internal.AFe1nSDK r8 = new com.appsflyer.internal.AFe1nSDK
            r8.<init>()
            com.appsflyer.internal.AFe1uSDK r8 = r7.AFInAppEventParameterName(r9, r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFe1wSDK.valueOf(boolean, boolean, java.lang.String, int):com.appsflyer.internal.AFe1uSDK");
    }

    public final AFe1uSDK<String> AFInAppEventParameterName(AFa1pSDK aFa1pSDK, String str, AFd1kSDK aFd1kSDK) {
        int i = registerClient + 53;
        unregisterClient = i % 128;
        Object obj = null;
        if (i % 2 != 0) {
            try {
                try {
                    Object[] objArr = {aFa1pSDK, str, aFd1kSDK};
                    Object obj2 = AFa1ySDK.afErrorLog.get(-829551611);
                    if (obj2 == null) {
                        obj2 = ((Class) AFa1ySDK.AFKeystoreWrapper(72 - View.MeasureSpec.getSize(0), (char) KeyEvent.getDeadChar(0, 0), (AudioTrack.getMinVolume() > 0.0f ? 1 : (AudioTrack.getMinVolume() == 0.0f ? 0 : -1)) + 37)).getMethod("AFInAppEventType", AFa1pSDK.class, String.class, AFd1kSDK.class);
                        AFa1ySDK.afErrorLog.put(-829551611, obj2);
                    }
                    obj.hashCode();
                    throw null;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                Throwable cause = th2.getCause();
                if (cause != null) {
                    throw cause;
                }
                throw th2;
            }
        } else {
            try {
                Object[] objArr2 = {aFa1pSDK, str, aFd1kSDK};
                Object obj3 = AFa1ySDK.afErrorLog.get(-829551611);
                if (obj3 == null) {
                    obj3 = ((Class) AFa1ySDK.AFKeystoreWrapper(View.MeasureSpec.getSize(0) + 72, (char) (Process.getGidForName("") + 1), Color.argb(0, 0, 0, 0) + 37)).getMethod("AFInAppEventType", AFa1pSDK.class, String.class, AFd1kSDK.class);
                    AFa1ySDK.afErrorLog.put(-829551611, obj3);
                }
                AFe1uSDK<String> AFInAppEventParameterName = AFInAppEventParameterName(new AFe1oSDK(aFa1pSDK.unregisterClient, (byte[]) ((Method) obj3).invoke(null, objArr2), "POST", Collections.emptyMap(), aFa1pSDK.AFInAppEventParameterName()), new AFe1lSDK());
                int i2 = unregisterClient + 31;
                registerClient = i2 % 128;
                if (!(i2 % 2 == 0)) {
                    return AFInAppEventParameterName;
                }
                int i3 = 23 / 0;
                return AFInAppEventParameterName;
            } catch (Throwable th3) {
                try {
                    Throwable cause2 = th3.getCause();
                    if (cause2 != null) {
                        throw cause2;
                    }
                    throw th3;
                } catch (Throwable th4) {
                    th = th4;
                }
            }
        }
        AFLogger.afErrorLogForExcManagerOnly("AFFinalizer: reflection init failed", th);
        return null;
    }

    public final AFe1uSDK<AFc1qSDK> AFKeystoreWrapper(AFc1kSDK aFc1kSDK) {
        AFe1uSDK<AFc1qSDK> AFInAppEventParameterName = AFInAppEventParameterName(new AFe1oSDK(aFc1kSDK.unregisterClient, AFa1qSDK.valueOf(aFc1kSDK.valueOf()).toString().getBytes(Charset.defaultCharset()), "POST", Collections.emptyMap(), aFc1kSDK.AFInAppEventParameterName()), new AFc1jSDK());
        int i = registerClient + 35;
        unregisterClient = i % 128;
        if (i % 2 == 0) {
            return AFInAppEventParameterName;
        }
        throw null;
    }

    public final AFe1uSDK<Map<String, Object>> AFInAppEventType(String str, String str2) {
        String packageName = this.AFKeystoreWrapper.AFInAppEventParameterName.valueOf.getPackageName();
        AFd1sSDK aFd1sSDK = this.AFKeystoreWrapper;
        AFe1uSDK<Map<String, Object>> AFInAppEventParameterName = AFInAppEventParameterName(AFe1qSDK.valueOf(packageName, AFb1kSDK.AFInAppEventType(aFd1sSDK.AFInAppEventParameterName, aFd1sSDK.AFKeystoreWrapper), str, str2), new AFe1tSDK());
        int i = unregisterClient + 17;
        registerClient = i % 128;
        if (!(i % 2 == 0)) {
            return AFInAppEventParameterName;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    public final AFe1uSDK<String> valueOf(AFh1qSDK aFh1qSDK) {
        AFe1uSDK<String> AFInAppEventParameterName = AFInAppEventParameterName(new AFe1oSDK(aFh1qSDK.unregisterClient, aFh1qSDK.AFInAppEventType(), "POST", Collections.emptyMap(), true), new AFe1lSDK());
        int i = registerClient + 75;
        unregisterClient = i % 128;
        int i2 = i % 2;
        return AFInAppEventParameterName;
    }

    public final AFe1uSDK<String> AFKeystoreWrapper(String str, Map<String, String> map, String str2, UUID uuid, String str3) {
        String obj = uuid.toString();
        HashMap hashMap = new HashMap();
        hashMap.put("ttl", Constants.SDK_LOGIN_TYPE_LOGOUT);
        hashMap.put("uuid", obj);
        hashMap.put("data", map);
        hashMap.put("meta", valueOf());
        if (str2 != null) {
            int i = unregisterClient + 59;
            registerClient = i % 128;
            int i2 = i % 2;
            hashMap.put("brand_domain", str2);
        }
        String jSONObject = AFa1qSDK.valueOf(hashMap).toString();
        HashMap hashMap2 = new HashMap();
        Object[] objArr = new Object[1];
        m86a((byte) (11 - Color.blue(0)), ((Process.getThreadPriority(0) + 20) >> 6) + 12, "\u0002\u000f\u0007\f\u0003\u0001\u0005\r\u0007\t\u0005\u000e", objArr);
        hashMap2.put(((String) objArr[0]).intern(), values(str3, obj, "POST", jSONObject));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(AFInAppEventType, AppsFlyerLib.getInstance().getHostPrefix(), AFb1tSDK.valueOf().getHostName()));
        sb.append(RemoteSettings.FORWARD_SLASH_STRING);
        sb.append(str);
        AFe1uSDK<String> AFInAppEventType2 = AFInAppEventType(new AFe1oSDK(sb.toString(), jSONObject.getBytes(Charset.defaultCharset()), "POST", hashMap2, false), (AFe1jSDK) new AFe1lSDK(), true);
        int i3 = unregisterClient + 3;
        registerClient = i3 % 128;
        if ((i3 % 2 == 0 ? 'I' : 'K') == 'K') {
            return AFInAppEventType2;
        }
        Object obj2 = null;
        obj2.hashCode();
        throw null;
    }

    public final AFe1uSDK<Map<String, String>> values(String str, String str2, UUID uuid, String str3) {
        String obj = uuid.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(AFInAppEventType, AppsFlyerLib.getInstance().getHostPrefix(), AFb1tSDK.valueOf().getHostName()));
        sb.append(RemoteSettings.FORWARD_SLASH_STRING);
        sb.append(str);
        sb.append("?id=");
        sb.append(str2);
        String obj2 = sb.toString();
        Map<String, Object> valueOf = valueOf();
        String valueOf2 = String.valueOf(valueOf.get("build_number"));
        HashMap hashMap = new HashMap();
        hashMap.put("Af-UUID", uuid.toString());
        hashMap.put("Af-Meta-Sdk-Ver", valueOf2);
        hashMap.put("Af-Meta-Counter", String.valueOf(valueOf.get("counter")));
        hashMap.put("Af-Meta-Model", String.valueOf(valueOf.get("model")));
        hashMap.put("Af-Meta-Platform", String.valueOf(valueOf.get("platformextension")));
        hashMap.put("Af-Meta-System-Version", String.valueOf(valueOf.get(ServerProtocol.DIALOG_PARAM_SDK_VERSION)));
        Object[] objArr = new Object[1];
        m86a((byte) ((KeyEvent.getMaxKeyCode() >> 16) + 11), 13 - (ViewConfiguration.getScrollFriction() > 0.0f ? 1 : (ViewConfiguration.getScrollFriction() == 0.0f ? 0 : -1)), "\u0002\u000f\u0007\f\u0003\u0001\u0005\r\u0007\t\u0005\u000e", objArr);
        hashMap.put(((String) objArr[0]).intern(), values(str3, obj, "GET", obj, str, str2, valueOf2));
        AFe1uSDK<Map<String, String>> AFInAppEventParameterName = AFInAppEventParameterName(new AFe1oSDK(obj2, null, "GET", hashMap, false), new AFe1pSDK());
        int i = unregisterClient + 17;
        registerClient = i % 128;
        int i2 = i % 2;
        return AFInAppEventParameterName;
    }

    public final AFe1uSDK<String> AFInAppEventType(String str) {
        AFe1oSDK aFe1oSDK = new AFe1oSDK(str, null, "GET", Collections.emptyMap(), false);
        aFe1oSDK.unregisterClient = 10000;
        aFe1oSDK.AFInAppEventType = false;
        AFe1uSDK<String> AFInAppEventParameterName = AFInAppEventParameterName(aFe1oSDK, new AFe1lSDK());
        int i = registerClient + 125;
        unregisterClient = i % 128;
        if (!(i % 2 != 0)) {
            return AFInAppEventParameterName;
        }
        Object obj = null;
        obj.hashCode();
        throw null;
    }

    public final AFe1xSDK AFInAppEventType(Map<String, Object> map, String str) {
        int i = registerClient + 69;
        unregisterClient = i % 128;
        int i2 = i % 2;
        try {
            try {
                Object[] objArr = {map, str};
                Object obj = AFa1ySDK.afErrorLog.get(1391384416);
                if (obj == null) {
                    obj = ((Class) AFa1ySDK.AFKeystoreWrapper((ExpandableListView.getPackedPositionForChild(0, 0) > 0L ? 1 : (ExpandableListView.getPackedPositionForChild(0, 0) == 0L ? 0 : -1)) + 73, (char) (ViewConfiguration.getEdgeSlop() >> 16), 36 - ImageFormat.getBitsPerPixel(0))).getMethod("AFInAppEventType", Map.class, String.class);
                    AFa1ySDK.afErrorLog.put(1391384416, obj);
                }
                byte[] bArr = (byte[]) ((Method) obj).invoke(null, objArr);
                if (bArr != null) {
                    return new AFe1xSDK(this.AFKeystoreWrapper, bArr);
                }
                AFLogger.afErrorLogForExcManagerOnly("AFFinalizer: failed to create bytes", new IllegalArgumentException("failed to create bytes from proxyData"));
                int i3 = registerClient + 9;
                unregisterClient = i3 % 128;
                int i4 = i3 % 2;
                return null;
            } catch (Throwable th) {
                Throwable cause = th.getCause();
                if (cause != null) {
                    throw cause;
                }
                throw th;
            }
        } catch (Exception e) {
            AFLogger.afErrorLogForExcManagerOnly("AFFinalizer: reflection init failed", e);
            return null;
        }
    }

    private <T> AFe1uSDK<T> AFInAppEventParameterName(AFe1oSDK aFe1oSDK, AFe1jSDK<T> aFe1jSDK) {
        AFe1uSDK<T> AFInAppEventType2;
        int i = registerClient + 45;
        unregisterClient = i % 128;
        if (!(i % 2 != 0)) {
            AFInAppEventType2 = AFInAppEventType(aFe1oSDK, aFe1jSDK, values());
        } else {
            AFInAppEventType2 = AFInAppEventType(aFe1oSDK, aFe1jSDK, values());
            int i2 = 41 / 0;
        }
        int i3 = unregisterClient + 115;
        registerClient = i3 % 128;
        int i4 = i3 % 2;
        return AFInAppEventType2;
    }

    private Map<String, Object> valueOf() {
        HashMap hashMap = new HashMap();
        hashMap.put("build_number", "6.13.1");
        hashMap.put("counter", Integer.valueOf(this.AFKeystoreWrapper.AFKeystoreWrapper.AFInAppEventParameterName("appsFlyerCount", 0)));
        hashMap.put("model", Build.MODEL);
        Object[] objArr = new Object[1];
        m86a((byte) (50 - (ViewConfiguration.getKeyRepeatTimeout() >> 16)), 5 - (ViewConfiguration.getScrollDefaultDelay() >> 16), "\u000e\n\r\u0005㘰", objArr);
        hashMap.put(((String) objArr[0]).intern(), Build.BRAND);
        hashMap.put(ServerProtocol.DIALOG_PARAM_SDK_VERSION, Integer.toString(Build.VERSION.SDK_INT));
        AFd1sSDK aFd1sSDK = this.AFKeystoreWrapper;
        hashMap.put("app_version_name", AFb1uSDK.AFKeystoreWrapper(aFd1sSDK.AFInAppEventParameterName.valueOf, aFd1sSDK.AFInAppEventParameterName.valueOf.getPackageName()));
        hashMap.put("app_id", this.AFKeystoreWrapper.AFInAppEventParameterName.valueOf.getPackageName());
        hashMap.put("platformextension", new AFb1cSDK().valueOf());
        int i = unregisterClient + 111;
        registerClient = i % 128;
        int i2 = i % 2;
        return hashMap;
    }

    private static String values(String str, String str2, String... strArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        arrayList.add(1, "v2");
        String join = TextUtils.join("\u2063", (String[]) arrayList.toArray(new String[0]));
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append("v2");
        String AFInAppEventType2 = AFb1mSDK.AFInAppEventType(join, sb.toString());
        int i = unregisterClient + 103;
        registerClient = i % 128;
        if (i % 2 == 0) {
            throw null;
        }
        return AFInAppEventType2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002f, code lost:
    
        r0 = com.appsflyer.internal.AFe1wSDK.registerClient + 73;
        com.appsflyer.internal.AFe1wSDK.unregisterClient = r0 % 128;
        r0 = r0 % 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002c, code lost:
    
        if ((!r4.valueOf.getBoolean(com.appsflyer.AppsFlyerProperties.HTTP_CACHE, true) ? '\'' : 'W') != '\'') goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
    
        if (r4.valueOf.getBoolean(com.appsflyer.AppsFlyerProperties.HTTP_CACHE, true) == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002e, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean values() {
        /*
            r4 = this;
            int r0 = com.appsflyer.internal.AFe1wSDK.unregisterClient
            int r0 = r0 + 113
            int r1 = r0 % 128
            com.appsflyer.internal.AFe1wSDK.registerClient = r1
            int r0 = r0 % 2
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L10
            r0 = r1
            goto L11
        L10:
            r0 = r2
        L11:
            java.lang.String r3 = "http_cache"
            if (r0 == r2) goto L1e
            com.appsflyer.AppsFlyerProperties r0 = r4.valueOf
            boolean r0 = r0.getBoolean(r3, r2)
            if (r0 != 0) goto L2e
            goto L2f
        L1e:
            com.appsflyer.AppsFlyerProperties r0 = r4.valueOf
            boolean r0 = r0.getBoolean(r3, r2)
            r3 = 39
            if (r0 != 0) goto L2a
            r0 = r3
            goto L2c
        L2a:
            r0 = 87
        L2c:
            if (r0 == r3) goto L2f
        L2e:
            return r1
        L2f:
            int r0 = com.appsflyer.internal.AFe1wSDK.registerClient
            int r0 = r0 + 73
            int r1 = r0 % 128
            com.appsflyer.internal.AFe1wSDK.unregisterClient = r1
            int r0 = r0 % 2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFe1wSDK.values():boolean");
    }

    private <T> AFe1uSDK<T> AFInAppEventType(AFe1oSDK aFe1oSDK, AFe1jSDK<T> aFe1jSDK, boolean z) {
        aFe1oSDK.AFKeystoreWrapper = z;
        AFe1vSDK aFe1vSDK = this.AFInAppEventParameterName;
        AFe1uSDK<T> aFe1uSDK = new AFe1uSDK<>(aFe1oSDK, aFe1vSDK.values, aFe1vSDK.AFKeystoreWrapper, aFe1jSDK);
        int i = unregisterClient + 5;
        registerClient = i % 128;
        if ((i % 2 == 0 ? '4' : 'O') == 'O') {
            return aFe1uSDK;
        }
        throw null;
    }

    /* renamed from: a */
    private static void m86a(byte b, int i, String str, Object[] objArr) {
        int i2;
        int i3 = $10 + 109;
        $11 = i3 % 128;
        int i4 = i3 % 2;
        char[] cArr = str;
        if (str != null) {
            cArr = str.toCharArray();
        }
        char[] cArr2 = cArr;
        AFj1gSDK aFj1gSDK = new AFj1gSDK();
        char[] cArr3 = AFLogger;
        if (cArr3 != null) {
            int length = cArr3.length;
            char[] cArr4 = new char[length];
            for (int i5 = 0; i5 < length; i5++) {
                cArr4[i5] = (char) (cArr3[i5] ^ (-5398819829411789118L));
            }
            cArr3 = cArr4;
        }
        char c = (char) (f203e ^ (-5398819829411789118L));
        char[] cArr5 = new char[i];
        if (i % 2 != 0) {
            int i6 = $10 + 59;
            $11 = i6 % 128;
            if (i6 % 2 == 0) {
                i2 = i + 97;
                cArr5[i2] = (char) (cArr2[i2] >>> b);
            } else {
                i2 = i - 1;
                cArr5[i2] = (char) (cArr2[i2] - b);
            }
        } else {
            i2 = i;
        }
        if (i2 > 1) {
            aFj1gSDK.AFInAppEventType = 0;
            while (true) {
                if ((aFj1gSDK.AFInAppEventType < i2 ? ')' : ']') == ']') {
                    break;
                }
                aFj1gSDK.AFInAppEventParameterName = cArr2[aFj1gSDK.AFInAppEventType];
                aFj1gSDK.values = cArr2[aFj1gSDK.AFInAppEventType + 1];
                if (!(aFj1gSDK.AFInAppEventParameterName != aFj1gSDK.values)) {
                    cArr5[aFj1gSDK.AFInAppEventType] = (char) (aFj1gSDK.AFInAppEventParameterName - b);
                    cArr5[aFj1gSDK.AFInAppEventType + 1] = (char) (aFj1gSDK.values - b);
                } else {
                    aFj1gSDK.valueOf = aFj1gSDK.AFInAppEventParameterName / c;
                    aFj1gSDK.f288d = aFj1gSDK.AFInAppEventParameterName % c;
                    aFj1gSDK.AFKeystoreWrapper = aFj1gSDK.values / c;
                    aFj1gSDK.registerClient = aFj1gSDK.values % c;
                    if (aFj1gSDK.f288d == aFj1gSDK.registerClient) {
                        aFj1gSDK.valueOf = ((aFj1gSDK.valueOf + c) - 1) % c;
                        aFj1gSDK.AFKeystoreWrapper = ((aFj1gSDK.AFKeystoreWrapper + c) - 1) % c;
                        int i7 = (aFj1gSDK.valueOf * c) + aFj1gSDK.f288d;
                        int i8 = (aFj1gSDK.AFKeystoreWrapper * c) + aFj1gSDK.registerClient;
                        cArr5[aFj1gSDK.AFInAppEventType] = cArr3[i7];
                        cArr5[aFj1gSDK.AFInAppEventType + 1] = cArr3[i8];
                    } else if (aFj1gSDK.valueOf == aFj1gSDK.AFKeystoreWrapper) {
                        int i9 = $10 + 45;
                        $11 = i9 % 128;
                        int i10 = i9 % 2;
                        aFj1gSDK.f288d = ((aFj1gSDK.f288d + c) - 1) % c;
                        aFj1gSDK.registerClient = ((aFj1gSDK.registerClient + c) - 1) % c;
                        int i11 = (aFj1gSDK.valueOf * c) + aFj1gSDK.f288d;
                        int i12 = (aFj1gSDK.AFKeystoreWrapper * c) + aFj1gSDK.registerClient;
                        cArr5[aFj1gSDK.AFInAppEventType] = cArr3[i11];
                        cArr5[aFj1gSDK.AFInAppEventType + 1] = cArr3[i12];
                        int i13 = $10 + 19;
                        $11 = i13 % 128;
                        int i14 = i13 % 2;
                    } else {
                        int i15 = (aFj1gSDK.valueOf * c) + aFj1gSDK.registerClient;
                        int i16 = (aFj1gSDK.AFKeystoreWrapper * c) + aFj1gSDK.f288d;
                        cArr5[aFj1gSDK.AFInAppEventType] = cArr3[i15];
                        cArr5[aFj1gSDK.AFInAppEventType + 1] = cArr3[i16];
                    }
                }
                aFj1gSDK.AFInAppEventType += 2;
            }
        }
        int i17 = 0;
        while (true) {
            if ((i17 < i ? '9' : 'F') == 'F') {
                objArr[0] = new String(cArr5);
                return;
            } else {
                cArr5[i17] = (char) (cArr5[i17] ^ 13722);
                i17++;
            }
        }
    }
}
