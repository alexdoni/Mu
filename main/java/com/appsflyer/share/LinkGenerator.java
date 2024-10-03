package com.appsflyer.share;

import android.content.Context;
import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.CreateOneLinkHttpTask;
import com.appsflyer.internal.AFb1hSDK;
import com.appsflyer.internal.AFb1tSDK;
import com.appsflyer.internal.AFd1mSDK;
import com.appsflyer.internal.AFe1fSDK;
import com.appsflyer.internal.AFf1uSDK;
import com.appsflyer.internal.AFj1vSDK;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public class LinkGenerator {
    String AFInAppEventParameterName;
    private String AFInAppEventType;
    private final String AFKeystoreWrapper;
    private String AFLogger;
    private final Map<String, String> afInfoLog = new HashMap();

    /* renamed from: d */
    private String f295d;

    /* renamed from: e */
    private String f296e;

    /* renamed from: i */
    private String f297i;
    private String registerClient;
    private String unregisterClient;

    /* renamed from: v */
    private String f298v;
    private String valueOf;
    String values;

    /* loaded from: classes.dex */
    public interface ResponseListener {
        void onResponse(String str);

        void onResponseError(String str);
    }

    public LinkGenerator(String str) {
        this.AFKeystoreWrapper = str;
    }

    public LinkGenerator setBrandDomain(String str) {
        this.f298v = str;
        return this;
    }

    public String getBrandDomain() {
        return this.f298v;
    }

    public LinkGenerator setDeeplinkPath(String str) {
        this.f296e = str;
        return this;
    }

    public LinkGenerator setBaseDeeplink(String str) {
        this.f297i = str;
        return this;
    }

    public String getChannel() {
        return this.AFInAppEventType;
    }

    public LinkGenerator setChannel(String str) {
        this.AFInAppEventType = str;
        return this;
    }

    public LinkGenerator setReferrerCustomerId(String str) {
        this.registerClient = str;
        return this;
    }

    public String getMediaSource() {
        return this.AFKeystoreWrapper;
    }

    public Map<String, String> getUserParams() {
        return new HashMap(this.afInfoLog);
    }

    public String getCampaign() {
        return this.valueOf;
    }

    public LinkGenerator setCampaign(String str) {
        this.valueOf = str;
        return this;
    }

    public LinkGenerator addParameter(String str, String str2) {
        this.afInfoLog.put(str, str2);
        return this;
    }

    public LinkGenerator addParameters(Map<String, String> map) {
        if (map != null) {
            this.afInfoLog.putAll(map);
        }
        return this;
    }

    public LinkGenerator setReferrerUID(String str) {
        this.unregisterClient = str;
        return this;
    }

    public LinkGenerator setReferrerName(String str) {
        this.f295d = str;
        return this;
    }

    public LinkGenerator setReferrerImageURL(String str) {
        this.AFLogger = str;
        return this;
    }

    public LinkGenerator setBaseURL(String str, String str2, String str3) {
        if (str == null || str.length() <= 0) {
            this.values = String.format("https://%s/%s", String.format("%sapp.%s", AppsFlyerLib.getInstance().getHostPrefix(), AFb1tSDK.valueOf().getHostName()), str3);
        } else {
            if (str2 == null || str2.length() < 5) {
                str2 = "go.onelink.me";
            }
            this.values = String.format("https://%s/%s", str2, str);
        }
        return this;
    }

    private Map<String, String> AFKeystoreWrapper() {
        HashMap hashMap = new HashMap();
        hashMap.put("pid", this.AFKeystoreWrapper);
        String str = this.unregisterClient;
        if (str != null) {
            hashMap.put("af_referrer_uid", str);
        }
        String str2 = this.AFInAppEventType;
        if (str2 != null) {
            hashMap.put(AFInAppEventParameterName.AF_CHANNEL, str2);
        }
        String str3 = this.registerClient;
        if (str3 != null) {
            hashMap.put("af_referrer_customer_id", str3);
        }
        String str4 = this.valueOf;
        if (str4 != null) {
            hashMap.put("c", str4);
        }
        String str5 = this.f295d;
        if (str5 != null) {
            hashMap.put("af_referrer_name", str5);
        }
        String str6 = this.AFLogger;
        if (str6 != null) {
            hashMap.put("af_referrer_image_url", str6);
        }
        if (this.f297i != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f297i);
            String str7 = this.f296e;
            if (str7 != null) {
                this.f296e = str7.replaceFirst("^[/]", "");
                sb.append(this.f297i.endsWith(RemoteSettings.FORWARD_SLASH_STRING) ? "" : RemoteSettings.FORWARD_SLASH_STRING);
                sb.append(this.f296e);
            }
            hashMap.put("af_dp", sb.toString());
        }
        for (Map.Entry<String, String> entry : this.afInfoLog.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
        return AFb1hSDK.AFInAppEventParameterName(hashMap);
    }

    public String generateLink() {
        StringBuilder sb = new StringBuilder();
        String str = this.values;
        if (str != null && str.startsWith("http")) {
            sb.append(this.values);
        } else {
            sb.append(String.format(AFj1vSDK.valueOf, AppsFlyerLib.getInstance().getHostPrefix(), AFb1tSDK.valueOf().getHostName()));
        }
        if (this.AFInAppEventParameterName != null) {
            sb.append('/');
            sb.append(this.AFInAppEventParameterName);
        }
        Map<String, String> AFKeystoreWrapper = AFKeystoreWrapper();
        StringBuilder sb2 = new StringBuilder();
        for (Map.Entry<String, String> entry : AFKeystoreWrapper.entrySet()) {
            if (sb2.length() == 0) {
                sb2.append('?');
            } else {
                sb2.append(Typography.amp);
            }
            sb2.append(entry.getKey());
            sb2.append('=');
            sb2.append(entry.getValue());
        }
        sb.append(sb2.toString());
        return sb.toString();
    }

    public void generateLink(Context context, ResponseListener responseListener) {
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID);
        String str = this.f298v;
        Map<String, String> AFKeystoreWrapper = AFKeystoreWrapper();
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
            AFLogger.afInfoLog("CustomerUserId not set, generate User Invite Link is disabled", true);
            return;
        }
        AFb1tSDK.valueOf().AFInAppEventParameterName(context);
        AFd1mSDK values = AFb1tSDK.valueOf().values();
        AFf1uSDK aFf1uSDK = new AFf1uSDK(values, UUID.randomUUID(), string, AFKeystoreWrapper, str, responseListener, this);
        AFe1fSDK afInfoLog = values.afInfoLog();
        afInfoLog.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(aFf1uSDK));
    }

    @Deprecated
    public void generateLink(Context context, final CreateOneLinkHttpTask.ResponseListener responseListener) {
        generateLink(context, new ResponseListener() { // from class: com.appsflyer.share.LinkGenerator.3
            @Override // com.appsflyer.share.LinkGenerator.ResponseListener
            public final void onResponse(String str) {
                responseListener.onResponse(str);
            }

            @Override // com.appsflyer.share.LinkGenerator.ResponseListener
            public final void onResponseError(String str) {
                responseListener.onResponseError(str);
            }
        });
    }
}
