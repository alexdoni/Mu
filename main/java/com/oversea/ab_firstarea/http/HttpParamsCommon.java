package com.oversea.ab_firstarea.http;

import android.text.TextUtils;
import android.util.Log;
import com.oversea.ab_firstarea.haiwai.AppsFlyerControl;
import com.oversea.ab_firstarea.haiwai.FirebaseControl;
import com.oversea.ab_firstarea.haiwai.GooglePlayControl;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.SDKVersion;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.statisics.util.MD5;
import com.xsdk.ab_firstbase.statisics.util.Util;
import com.xsdk.ab_firstbase.statisics.util.languagelib.MultiLanguageUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HttpParamsCommon {
    Map<String, Object> params = new HashMap();
    public String doValue = "";

    public Map<String, Object> createCommonParams(String str) {
        this.doValue = str;
        String packageName = Lxhw_XSDK.getInstance().getApplication() != null ? Lxhw_XSDK.getInstance().getApplication().getPackageName() : "";
        this.params.put("datetime_timezone", Util.getISO8601Timestamp());
        this.params.put("system_area_code", Util.getCountry());
        this.params.put("system_lang_code", MultiLanguageUtil.getInstance().getCurrentSystemLauguage());
        this.params.put("lang_code", Util.getSystemLang());
        this.params.put("app_id", packageName);
        this.params.put("game_id", Integer.valueOf(Lxhw_AppInfoDecorator.getGame_id()));
        this.params.put(ComConstants.sdk_version_code, SDKVersion.sdkVersion);
        this.params.put("app_version", Util.getVersionCode(Lxhw_XSDK.getInstance().getApplication()) + "");
        this.params.put("os", 2);
        this.params.put("appsflyer_id", AppsFlyerControl.getInstance().getTrack_appsflyerId(Lxhw_XSDK.getInstance().getApplication()));
        this.params.put("advertising_id", GooglePlayControl.getInstance().getGAid());
        this.params.put("oaid", Util.getUniqueID(Lxhw_XSDK.getInstance().getApplication()));
        this.params.put("android_id", Util.getAndroidID(Lxhw_XSDK.getInstance().getApplication()));
        this.params.put("imei", "");
        this.params.put("model", Util.getPhoneModel());
        this.params.put("brand", Util.getPhoneMANUFACTURER());
        this.params.put("system_version", Util.getOSVersion());
        this.params.put("app_instance_id", FirebaseControl.getInstance().getAppInstanceID());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("is_use_agent", Util.isUseAgent(Lxhw_XSDK.getInstance().getApplication()));
            jSONObject.put("is_simulator", Util.isSimulator(Lxhw_XSDK.getInstance().getApplication()));
            jSONObject.put("network_type", Util.getNetWorkType(Lxhw_XSDK.getInstance().getApplication()));
            jSONObject.put("soc_manufacturer", Util.getSocManufacturer());
            jSONObject.put("soc_model", Util.getSocModel());
            jSONObject.put("cpu", Util.getCpu());
            jSONObject.put("resolution", Util.getScreenResolution(Lxhw_XSDK.getInstance().getApplication()));
            jSONObject.put("memory_unused", Util.getMemUnused(Lxhw_XSDK.getInstance().getApplication()));
            jSONObject.put("total_memory", Util.getTotalMemory(Lxhw_XSDK.getInstance().getApplication()));
            jSONObject.put("is_jailbreak_root", Util.hasRoot());
            jSONObject.put("sign_info", Util.getSignatureSHA1(Lxhw_XSDK.getInstance().getApplication()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.params.put("ext_safety", jSONObject.toString());
        return this.params;
    }

    public Map<String, Object> addSignV2(Map<String, Object> map, String str) {
        if (map != null) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            String str2 = (str.equals(ComConstants.SDK_PAY_NOTIFY) || str.equals(ComConstants.SDK_PAY_CHAXUN_BUDAN_NOTIFY) || str.equals(ComConstants.SDK_PAY_LOCAL_BUDAN_NOTIFY)) ? AreaBaseService.PAYNOTIFY_ROUTE : str;
            if (str.equals(ComConstants.SDK_PRE_REG_REWARD_NOTIFY)) {
                str2 = AreaBaseService.GOOGLE_PRE_REGISTRATION_REWARD_ROUTE;
            }
            if (str.equals(ComConstants.SDK_PAY_CHAXUN_BUDAN_NOORDER_NOTIFY)) {
                str2 = AreaBaseService.PAYNOTIFYNOORDER_ROUTE;
            }
            map.put("timestamp", Long.valueOf(currentTimeMillis));
            map.put("sign", MD5.getMD5String(Lxhw_AppInfoDecorator.getGame_id() + str2 + currentTimeMillis + Lxhw_AppInfoDecorator.getSecret_key_app()));
        }
        return map;
    }

    public Map<String, Object> addSign(Map<String, Object> map, String str) {
        if (map != null) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            String str2 = (str.equals(ComConstants.SDK_PAY_NOTIFY) || str.equals(ComConstants.SDK_PAY_CHAXUN_BUDAN_NOTIFY) || str.equals(ComConstants.SDK_PAY_LOCAL_BUDAN_NOTIFY)) ? AreaBaseService.PAYNOTIFY_ROUTE : str;
            if (str.equals(ComConstants.SDK_PRE_REG_REWARD_NOTIFY)) {
                str2 = AreaBaseService.GOOGLE_PRE_REGISTRATION_REWARD_ROUTE;
            }
            if (str.equals(ComConstants.SDK_PAY_CHAXUN_BUDAN_NOORDER_NOTIFY)) {
                str2 = AreaBaseService.PAYNOTIFYNOORDER_ROUTE;
            }
            map.put("timestamp", Long.valueOf(currentTimeMillis));
            map.put("sign_version", "3");
            String sortedMapPingjieParams = Util.sortedMapPingjieParams(map);
            if (TextUtils.isEmpty(sortedMapPingjieParams)) {
                map.remove("sign_version");
                return addSignV2(map, str);
            }
            map.put("sign", MD5.getMD5String(sortedMapPingjieParams + "|" + str2 + "|" + Lxhw_AppInfoDecorator.getSecret_key_app()));
        }
        return map;
    }

    public Map<String, Object> addSignTime(Map<String, Object> map, String str, long j) {
        if (map != null) {
            String str2 = (str.equals(ComConstants.SDK_PAY_NOTIFY) || str.equals(ComConstants.SDK_PAY_CHAXUN_BUDAN_NOTIFY) || str.equals(ComConstants.SDK_PAY_LOCAL_BUDAN_NOTIFY)) ? AreaBaseService.PAYNOTIFY_ROUTE : str;
            map.put("timestamp", Long.valueOf(j));
            map.put("sign_version", "3");
            String sortedMapPingjieParams = Util.sortedMapPingjieParams(map);
            if (TextUtils.isEmpty(sortedMapPingjieParams)) {
                map.remove("sign_version");
                return addSignTimeV2(map, str, j);
            }
            map.put("sign", MD5.getMD5String(sortedMapPingjieParams + "|" + str2 + "|" + Lxhw_AppInfoDecorator.getSecret_key_app()));
        }
        return map;
    }

    public Map<String, Object> addSignTimeV2(Map<String, Object> map, String str, long j) {
        if (map != null) {
            if (str.equals(ComConstants.SDK_PAY_NOTIFY) || str.equals(ComConstants.SDK_PAY_CHAXUN_BUDAN_NOTIFY) || str.equals(ComConstants.SDK_PAY_LOCAL_BUDAN_NOTIFY)) {
                str = AreaBaseService.PAYNOTIFY_ROUTE;
            }
            map.put("timestamp", Long.valueOf(j));
            map.put("sign", MD5.getMD5String(Lxhw_AppInfoDecorator.getGame_id() + str + j + Lxhw_AppInfoDecorator.getSecret_key_app()));
        }
        return map;
    }

    public Map<String, Object> createCommonParamsOld(String str) {
        this.doValue = str;
        this.params.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        this.params.put("datetime_timezone", Util.getISO8601Timestamp());
        this.params.put("system_area_code", Util.getCountry());
        this.params.put("system_lang_code", MultiLanguageUtil.getInstance().getCurrentSystemLauguage());
        this.params.put("app_id", Lxhw_AppInfoDecorator.getApp_id());
        this.params.put("game_id", Integer.valueOf(Lxhw_AppInfoDecorator.getGame_id()));
        this.params.put(ComConstants.sdk_version_code, SDKVersion.sdkVersion);
        this.params.put("app_version", Util.getVersionCode(Lxhw_XSDK.getInstance().getContext()) + "");
        this.params.put("os", 2);
        this.params.put("appsflyer_id", AppsFlyerControl.getInstance().getTrack_appsflyerId(Lxhw_XSDK.getInstance().getContext()));
        this.params.put("advertising_id", GooglePlayControl.getInstance().getGAid());
        this.params.put("oaid", Util.getUniqueID(Lxhw_XSDK.getInstance().getContext()));
        this.params.put("android_id", Util.getAndroidID(Lxhw_XSDK.getInstance().getContext()));
        this.params.put("imei", "");
        this.params.put("model", Util.getPhoneModel());
        this.params.put("brand", Util.getPhoneMANUFACTURER());
        this.params.put("system_version", Util.getOSVersion());
        return this.params;
    }

    public Map<String, Object> addSignOld(Map<String, Object> map) {
        if (map != null) {
            String str = Util.sortedMapPingjieParams(map) + map.get("timestamp") + Lxhw_AppInfoDecorator.getSecret_key_app();
            Log.v("jsonencodeParams", str);
            String mD5String = MD5.getMD5String(str);
            Log.v("sign", mD5String);
            map.put("sign", mD5String);
        }
        return map;
    }

    public void addParam(String str, Object obj) {
        Map<String, Object> map = this.params;
        if (map != null) {
            map.put(str, obj);
        }
    }

    public Map<String, Object> getParams() {
        return this.params;
    }
}
