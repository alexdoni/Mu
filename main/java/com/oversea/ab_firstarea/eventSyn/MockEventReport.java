package com.oversea.ab_firstarea.eventSyn;

import android.content.Context;
import android.text.TextUtils;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;
import com.xsdk.ab_firstbase.eventdb.EventReport;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MockEventReport {
    private static MockEventReport mInstance;
    private String TAG = "MockEventReport";

    public static MockEventReport getInstance() {
        if (mInstance == null) {
            mInstance = new MockEventReport();
        }
        return mInstance;
    }

    public void onTrackPollEvent(Context context, EventReport eventReport, int i) {
        if (eventReport != null && Util.isNetworkAvailable(Lxhw_XSDK.getInstance().getApplication())) {
            HttpRequestCenter.getInstance().doRequestCommon("onTrackEvent", createEventReportParams(context, eventReport, i), AreaBaseService.EVENTURL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.eventSyn.MockEventReport.1
                @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
                public void httpRequestCallBackListener(String str) {
                    try {
                        if (TextUtils.isEmpty(str)) {
                            return;
                        }
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str);
                        LLog.v_noControl(MockEventReport.this.TAG, "onTrackPollEvent code=" + baseBean.getCode());
                        baseBean.getCode();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }

                @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
                public void httpRequestFail(BaseBean baseBean) {
                    LLog.v_noControl(MockEventReport.this.TAG, "onTrackPollEvent httpRequestFail=" + baseBean.getCode());
                }
            });
        }
    }

    public void onTrackEvent(Context context, String str, Map<String, Object> map) {
        onTrackPollEvent(context, createEventReport(str, map), 1);
    }

    private EventReport createEventReport(String str, Map<String, Object> map) {
        EventReport eventReport = new EventReport();
        eventReport.timestamp = System.currentTimeMillis() / 1000;
        eventReport.event_name = Util.exceptNullStr(str);
        if (map == null || map.size() == 0) {
            eventReport.event_extra = "";
        } else {
            eventReport.event_extra = new JSONObject(map).toString();
        }
        eventReport.platform_uid = Lxhw_XUserInfo.getInstance().getSdkId();
        eventReport.server_id = Lxhw_Platform.getInstance().userExtraData.getServerId();
        eventReport.server_name = Lxhw_Platform.getInstance().userExtraData.getServerName();
        eventReport.role_id = Lxhw_Platform.getInstance().userExtraData.getRoleID();
        eventReport.role_name = Lxhw_Platform.getInstance().userExtraData.getRoleName();
        eventReport.role_level = Lxhw_Platform.getInstance().userExtraData.getRoleLevel();
        eventReport.remain_coin = Lxhw_Platform.getInstance().userExtraData.getRemain_coin();
        return eventReport;
    }

    private Map<String, Object> createEventReportParams(Context context, EventReport eventReport, int i) {
        LLog.v_noControl(this.TAG, "onTrackPollEvent eventName=" + eventReport.event_name + " type=" + i);
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("onTrackEvent");
        createCommonParams.put("device_uuid", Util.getSharePreferencesUUID(context));
        createCommonParams.put("platform_uid", Integer.valueOf(eventReport.platform_uid));
        createCommonParams.put("event_name", Util.exceptNullStr(eventReport.event_name));
        createCommonParams.put("event_extra", Util.exceptNullStr(eventReport.event_extra));
        createCommonParams.put("server_id", Util.exceptNullStr(eventReport.server_id));
        createCommonParams.put("server_name", Util.exceptNullStr(eventReport.server_name));
        createCommonParams.put("role_id", Util.exceptNullStr(eventReport.role_id));
        createCommonParams.put("role_name", Util.exceptNullStr(eventReport.role_name));
        createCommonParams.put("role_level", Util.exceptNullStr(eventReport.role_level));
        createCommonParams.put("remain_coin", Util.exceptNullStr(eventReport.remain_coin));
        if (Lxhw_AreaPlatform.getInstance().is_first_start) {
            createCommonParams.put("is_first_start", 1);
        } else {
            createCommonParams.put("is_first_start", 0);
        }
        httpParamsCommon.addSignTime(createCommonParams, AreaBaseService.EVENT_ROUTE, eventReport.timestamp);
        return createCommonParams;
    }

    public boolean CheckEventReportIntervalTime(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("error")) {
            return true;
        }
        if (!EventReportIntervalTimeManager.getInstance().getEventData().containsKey(str)) {
            EventReportIntervalTimeManager.getInstance().putEventName(str, System.currentTimeMillis());
            return true;
        }
        if (System.currentTimeMillis() - EventReportIntervalTimeManager.getInstance().getEventData().get(str).longValue() < 900000) {
            return false;
        }
        EventReportIntervalTimeManager.getInstance().putEventName(str, System.currentTimeMillis());
        return true;
    }
}
