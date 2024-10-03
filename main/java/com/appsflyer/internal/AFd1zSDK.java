package com.appsflyer.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.appsflyer.AFLogger;
import com.appsflyer.internal.AFa1oSDK;
import com.facebook.applinks.AppLinkData;
import com.facebook.share.internal.ShareConstants;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFd1zSDK implements AFc1hSDK {
    private boolean AFKeystoreWrapper;
    Map<String, Object> valueOf;
    private final AFd1kSDK values;

    public AFd1zSDK(AFd1kSDK aFd1kSDK) {
        Intrinsics.checkNotNullParameter(aFd1kSDK, "");
        this.values = aFd1kSDK;
    }

    private boolean AFKeystoreWrapper() {
        return this.AFKeystoreWrapper;
    }

    @Override // com.appsflyer.internal.AFc1hSDK
    public final void values(boolean z) {
        this.AFKeystoreWrapper = z;
    }

    @Override // com.appsflyer.internal.AFc1hSDK
    public final Map<String, Object> valueOf() {
        return this.valueOf;
    }

    @Override // com.appsflyer.internal.AFc1hSDK
    public final void AFInAppEventType() {
        Context context;
        if (AFKeystoreWrapper() && (context = this.values.valueOf) != null) {
            this.valueOf = new LinkedHashMap();
            AFa1vSDK aFa1vSDK = new AFa1vSDK(System.currentTimeMillis());
            try {
                Class.forName("com.facebook.FacebookSdk").getMethod("sdkInitialize", Context.class).invoke(null, context);
                Class<?> cls = Class.forName("com.facebook.applinks.AppLinkData");
                Class<?> cls2 = Class.forName("com.facebook.applinks.AppLinkData$CompletionHandler");
                Method method = cls.getMethod("fetchDeferredAppLinkData", Context.class, String.class, cls2);
                Object newProxyInstance = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new InvocationHandler() { // from class: com.appsflyer.internal.AFa1oSDK.1
                    private /* synthetic */ AFa1ySDK AFKeystoreWrapper;
                    private /* synthetic */ Class values;

                    public C06921(Class cls3, AFa1ySDK aFa1vSDK2) {
                        r1 = cls3;
                        r2 = aFa1vSDK2;
                    }

                    @Override // java.lang.reflect.InvocationHandler
                    public final Object invoke(Object obj, Method method2, Object[] objArr) throws Throwable {
                        String str;
                        String str2;
                        String str3;
                        Bundle bundle;
                        if (method2.getName().equals("onDeferredAppLinkDataFetched")) {
                            Object obj2 = objArr[0];
                            if (obj2 != null) {
                                Bundle bundle2 = (Bundle) Bundle.class.cast(r1.getMethod("getArgumentBundle", new Class[0]).invoke(r1.cast(obj2), new Object[0]));
                                if (bundle2 != null) {
                                    str2 = bundle2.getString(AppLinkData.ARGUMENTS_NATIVE_URL);
                                    str3 = bundle2.getString("target_url");
                                    Bundle bundle3 = bundle2.getBundle("extras");
                                    str = (bundle3 == null || (bundle = bundle3.getBundle(ShareConstants.DEEPLINK_CONTEXT)) == null) ? null : bundle.getString(ShareConstants.PROMO_CODE);
                                } else {
                                    str = null;
                                    str2 = null;
                                    str3 = null;
                                }
                                AFa1ySDK aFa1ySDK = r2;
                                if (aFa1ySDK != null) {
                                    aFa1ySDK.AFKeystoreWrapper(str2, str3, str);
                                }
                            } else {
                                AFa1ySDK aFa1ySDK2 = r2;
                                if (aFa1ySDK2 != null) {
                                    aFa1ySDK2.AFKeystoreWrapper(null, null, null);
                                }
                            }
                            return null;
                        }
                        AFa1ySDK aFa1ySDK3 = r2;
                        if (aFa1ySDK3 != null) {
                            aFa1ySDK3.AFKeystoreWrapper("onDeferredAppLinkDataFetched invocation failed");
                        }
                        return null;
                    }
                });
                String string = context.getString(context.getResources().getIdentifier(ComConstants.facebook_app_id, TypedValues.Custom.S_STRING, context.getPackageName()));
                if (TextUtils.isEmpty(string)) {
                    aFa1vSDK2.AFKeystoreWrapper("Facebook app id not defined in resources");
                } else {
                    method.invoke(null, context, string, newProxyInstance);
                }
            } catch (ClassNotFoundException e) {
                AFLogger.afErrorLogForExcManagerOnly("FB class missing error", e);
                aFa1vSDK2.AFKeystoreWrapper(e.toString());
            } catch (IllegalAccessException e2) {
                AFLogger.afErrorLogForExcManagerOnly("FB illegal access", e2);
                aFa1vSDK2.AFKeystoreWrapper(e2.toString());
            } catch (NoSuchMethodException e3) {
                AFLogger.afErrorLogForExcManagerOnly("FB method missing error", e3);
                aFa1vSDK2.AFKeystoreWrapper(e3.toString());
            } catch (InvocationTargetException e4) {
                AFLogger.afErrorLogForExcManagerOnly("FB invocation error", e4);
                aFa1vSDK2.AFKeystoreWrapper(e4.toString());
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class AFa1vSDK implements AFa1oSDK.AFa1ySDK {
        private /* synthetic */ long valueOf;

        AFa1vSDK(long j) {
            this.valueOf = j;
        }

        @Override // com.appsflyer.internal.AFa1oSDK.AFa1ySDK
        public final void AFKeystoreWrapper(String str, String str2, String str3) {
            Map<String, Object> map;
            if (str != null) {
                AFLogger.afInfoLog("Facebook Deferred AppLink data received: ".concat(String.valueOf(str)));
                Map<String, Object> map2 = AFd1zSDK.this.valueOf;
                if (map2 != null) {
                    map2.put("link", str);
                }
                if (str2 != null && (map = AFd1zSDK.this.valueOf) != null) {
                    map.put("target_url", str2);
                }
                if (str3 != null) {
                    AFd1zSDK aFd1zSDK = AFd1zSDK.this;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                    linkedHashMap2.put(ShareConstants.PROMO_CODE, str3);
                    linkedHashMap.put(ShareConstants.DEEPLINK_CONTEXT, linkedHashMap2);
                    Map<String, Object> map3 = aFd1zSDK.valueOf;
                    if (map3 != null) {
                        map3.put("extras", linkedHashMap);
                    }
                }
            } else {
                Map<String, Object> map4 = AFd1zSDK.this.valueOf;
                if (map4 != null) {
                    map4.put("link", "");
                }
            }
            String valueOf = String.valueOf(System.currentTimeMillis() - this.valueOf);
            Map<String, Object> map5 = AFd1zSDK.this.valueOf;
            if (map5 != null) {
                map5.put("ttr", valueOf);
            }
        }

        @Override // com.appsflyer.internal.AFa1oSDK.AFa1ySDK
        public final void AFKeystoreWrapper(String str) {
            Map<String, Object> map = AFd1zSDK.this.valueOf;
            if (map != null) {
                map.put("error", str);
            }
        }
    }

    @Override // com.appsflyer.internal.AFc1hSDK
    public final boolean values() {
        if (AFKeystoreWrapper()) {
            Map<String, Object> map = this.valueOf;
            if (map == null || map.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
