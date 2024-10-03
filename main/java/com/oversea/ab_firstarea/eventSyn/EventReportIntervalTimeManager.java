package com.oversea.ab_firstarea.eventSyn;

import android.text.TextUtils;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class EventReportIntervalTimeManager {
    private static final String EVENT_REPORT_INTERVAL_TIME = "event_report_interval_time";
    public static final int INTERVAL_TIME = 900000;
    private static volatile EventReportIntervalTimeManager instance;

    public static EventReportIntervalTimeManager getInstance() {
        if (instance == null) {
            synchronized (EventReportIntervalTimeManager.class) {
                if (instance == null) {
                    instance = new EventReportIntervalTimeManager();
                }
            }
        }
        return instance;
    }

    public void putEventName(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Map<String, Long> eventData = getEventData();
        if (j <= 0) {
            j = System.currentTimeMillis();
        }
        eventData.put(str, Long.valueOf(j));
        saveEventData(eventData);
    }

    private void saveEventData(Map<String, Long> map) {
        if (map.isEmpty()) {
            return;
        }
        ImageUtils.setSharePreferences(Lxhw_XSDK.getInstance().getApplication(), EVENT_REPORT_INTERVAL_TIME, new JSONObject(map).toString());
    }

    public Map<String, Long> getEventData() {
        JSONObject jSONObject;
        HashMap hashMap = new HashMap();
        String stringKeyForValue = ImageUtils.getStringKeyForValue(Lxhw_XSDK.getInstance().getApplication(), EVENT_REPORT_INTERVAL_TIME);
        if (TextUtils.isEmpty(stringKeyForValue)) {
            return hashMap;
        }
        try {
            jSONObject = new JSONObject(stringKeyForValue);
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                hashMap.put(next, Long.valueOf(jSONObject.getString(next)));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return hashMap;
    }
}
