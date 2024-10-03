package com.appsflyer.internal;

import android.net.Uri;
import android.text.AndroidCharacter;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ExpandableListView;
import com.appsflyer.AFLogger;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.appsflyer.internal.components.network.http.exceptions.ParsingException;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes.dex */
public final class AFf1lSDK extends AFf1rSDK<Map<String, String>> {
    private final UUID afErrorLog;
    private String afInfoLog;

    /* renamed from: e */
    public AFa1vSDK f219e;
    private final AFe1wSDK force;

    /* renamed from: i */
    private final boolean f220i;

    /* renamed from: v */
    private String f221v;

    /* renamed from: w */
    private String f222w;

    /* loaded from: classes.dex */
    public interface AFa1vSDK {
        void values(String str);

        void values(Map<String, String> map);
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final boolean AFInAppEventParameterName() {
        return false;
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final long AFInAppEventType() {
        return 3000L;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final AppsFlyerRequestListener registerClient() {
        return null;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final boolean unregisterClient() {
        return false;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    /* renamed from: v */
    protected final boolean mo91v() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AFf1lSDK(AFd1mSDK aFd1mSDK, UUID uuid, Uri uri) {
        super(AFf1zSDK.ONELINK, new AFf1zSDK[]{AFf1zSDK.RC_CDN}, aFd1mSDK, uuid.toString());
        boolean z = false;
        this.force = aFd1mSDK.AFKeystoreWrapper();
        this.afErrorLog = uuid;
        try {
            if (!AFc1rSDK.AFInAppEventType(uri.getHost()) && !AFc1rSDK.AFInAppEventType(uri.getPath())) {
                try {
                    Object[] objArr = {uri, aFd1mSDK.afWarnLog()};
                    Object obj = AFc1iSDK.afErrorLog.get(-33252961);
                    if (obj == null) {
                        obj = ((Class) AFc1iSDK.AFInAppEventParameterName(View.getDefaultSize(0, 0) + 36, (char) (1 - (ViewConfiguration.getGlobalActionKeyTimeout() > 0L ? 1 : (ViewConfiguration.getGlobalActionKeyTimeout() == 0L ? 0 : -1))), ViewConfiguration.getJumpTapTimeout() >> 16)).getDeclaredConstructor(Uri.class, AFc1uSDK.class);
                        AFc1iSDK.afErrorLog.put(-33252961, obj);
                    }
                    Object newInstance = ((Constructor) obj).newInstance(objArr);
                    try {
                        Object obj2 = AFc1iSDK.afErrorLog.get(-1814091915);
                        if (obj2 == null) {
                            obj2 = ((Class) AFc1iSDK.AFInAppEventParameterName((ViewConfiguration.getLongPressTimeout() >> 16) + 36, (char) ((-1) - TextUtils.indexOf((CharSequence) "", '0', 0)), ViewConfiguration.getKeyRepeatTimeout() >> 16)).getMethod("AFInAppEventType", null);
                            AFc1iSDK.afErrorLog.put(-1814091915, obj2);
                        }
                        Object invoke = ((Method) obj2).invoke(newInstance, null);
                        try {
                            Object obj3 = AFc1iSDK.afErrorLog.get(-1435527598);
                            if (obj3 == null) {
                                obj3 = ((Class) AFc1iSDK.AFInAppEventParameterName(51 - (KeyEvent.getMaxKeyCode() >> 16), (char) (18308 - (TypedValue.complexToFloat(0) > 0.0f ? 1 : (TypedValue.complexToFloat(0) == 0.0f ? 0 : -1))), (ViewConfiguration.getJumpTapTimeout() >> 16) + 36)).getMethod("AFInAppEventParameterName", null);
                                AFc1iSDK.afErrorLog.put(-1435527598, obj3);
                            }
                            boolean booleanValue = ((Boolean) ((Method) obj3).invoke(invoke, null)).booleanValue();
                            try {
                                Object obj4 = AFc1iSDK.afErrorLog.get(2045606441);
                                if (obj4 == null) {
                                    obj4 = ((Class) AFc1iSDK.AFInAppEventParameterName(51 - TextUtils.getOffsetBefore("", 0), (char) (18307 - (ExpandableListView.getPackedPositionForChild(0, 0) > 0L ? 1 : (ExpandableListView.getPackedPositionForChild(0, 0) == 0L ? 0 : -1))), AndroidCharacter.getMirror('0') - '\f')).getMethod("valueOf", null);
                                    AFc1iSDK.afErrorLog.put(2045606441, obj4);
                                }
                                z = ((Boolean) ((Method) obj4).invoke(invoke, null)).booleanValue();
                                String[] split = uri.getPath().split(RemoteSettings.FORWARD_SLASH_STRING);
                                if (booleanValue && split.length == 3) {
                                    this.f222w = split[1];
                                    this.afInfoLog = split[2];
                                    this.f221v = uri.toString();
                                }
                            } catch (Throwable th) {
                                Throwable cause = th.getCause();
                                if (cause == null) {
                                    throw th;
                                }
                                throw cause;
                            }
                        } catch (Throwable th2) {
                            Throwable cause2 = th2.getCause();
                            if (cause2 == null) {
                                throw th2;
                            }
                            throw cause2;
                        }
                    } catch (Throwable th3) {
                        Throwable cause3 = th3.getCause();
                        if (cause3 == null) {
                            throw th3;
                        }
                        throw cause3;
                    }
                } catch (Throwable th4) {
                    Throwable cause4 = th4.getCause();
                    if (cause4 == null) {
                        throw th4;
                    }
                    throw cause4;
                }
            }
        } catch (Exception e) {
            AFLogger.afErrorLogForExcManagerOnly("OneLinkValidator: reflection init failed", e);
        }
        this.f220i = z;
    }

    /* renamed from: w */
    public final boolean m92w() {
        return (TextUtils.isEmpty(this.f222w) || TextUtils.isEmpty(this.afInfoLog) || this.f222w.equals("app")) ? false : true;
    }

    /* renamed from: i */
    public final boolean m90i() {
        return this.f220i;
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final void AFKeystoreWrapper() {
        super.AFKeystoreWrapper();
        AFa1vSDK aFa1vSDK = this.f219e;
        if (aFa1vSDK != null) {
            if (this.AFInAppEventType == AFe1dSDK.SUCCESS && this.AFLogger != null) {
                aFa1vSDK.values((Map<String, String>) this.AFLogger.getBody());
                return;
            }
            Throwable m85e = m85e();
            if (m85e instanceof ParsingException) {
                if (((ParsingException) m85e).getRawResponse().isSuccessful()) {
                    aFa1vSDK.values("Can't parse one link data");
                    return;
                } else {
                    String str = this.f221v;
                    aFa1vSDK.values(str != null ? str : "Can't get OneLink data");
                    return;
                }
            }
            String str2 = this.f221v;
            aFa1vSDK.values(str2 != null ? str2 : "Can't get OneLink data");
        }
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final AFe1uSDK<Map<String, String>> valueOf(String str) {
        return this.force.values(this.f222w, this.afInfoLog, this.afErrorLog, str);
    }
}
