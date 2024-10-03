package com.oversea.ab_firstarea.channel;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PTypeUrl {
    public static String EVENT_REPORT_URL = "https://api-logs.muglobal.9ring.com";
    public static String SURVEYURL = "https://survey.muglobal.9ring.com";
    public static final String TAG = "X_LOG";
    public static final String TESTAPISDKURL1 = "https://test-api-sdk.fifun.games";
    public static final String TESTAPISDKURL2 = "";
    public static final String TESTGPURL = "https://test-api-pay.fifun.games";
    public static final String TESTWEBPURL = "https://test-pay.fifun.games";
    public static final String TYPE = "9ring";
    public static final String TYPENAME = "mu3_135_ld_om";
    public static final String ZIANAPISDKURL1 = "https://api-sdk.poglay.com";
    public static final String ZIANAPISDKURL2 = "";
    public static final String ZIANGPURL = "https://api-pay.poglay.com";
    public static final String ZIANWEBPURL = "https://pay.poglay.com";
    public static String curApiSdkUrl1 = "";
    public static String curApiSdkUrl2 = "";
    public static String curGPUrl = "";
    public static String curWPUrl = "";
    public static List<String> apiSDKUrlList = new ArrayList();
    public static List<String> apiPayUrlList = new ArrayList();
    public static List<String> apiLogUrlList = new ArrayList();
    public static List<String> webPayUrlList = new ArrayList();
    public static List<String> surveyUrlList = new ArrayList();

    static {
        init();
    }

    private static void init() {
        ArrayList arrayList = new ArrayList();
        apiSDKUrlList = arrayList;
        arrayList.add("https://api-sdk.muglobal.9ring.com");
        apiSDKUrlList.add("https://api-sdk.muglobal.9ringx.com");
        ArrayList arrayList2 = new ArrayList();
        apiPayUrlList = arrayList2;
        arrayList2.add("https://api-pay.muglobal.9ring.com");
        apiPayUrlList.add("https://api-pay.muglobal.9ringx.com");
        ArrayList arrayList3 = new ArrayList();
        apiLogUrlList = arrayList3;
        arrayList3.add("https://api-logs.muglobal.9ring.com");
        apiLogUrlList.add("https://api-logs.muglobal.9ringx.com");
        ArrayList arrayList4 = new ArrayList();
        webPayUrlList = arrayList4;
        arrayList4.add("https://pay.muglobal.9ring.com");
        webPayUrlList.add("https://pay.muglobal.9ringx.com");
        ArrayList arrayList5 = new ArrayList();
        surveyUrlList = arrayList5;
        arrayList5.add("https://survey.muglobal.9ring.com");
        surveyUrlList.add("https://survey.muglobal.9ringx.com");
    }

    public static void setProjectType() {
        Log.v("X_LOG", "TYPENAME=mu3_135_ld_om type=" + ProjectType.pType);
        if (ProjectType.TEST.equals(ProjectType.pType)) {
            curApiSdkUrl1 = TESTAPISDKURL1;
            curApiSdkUrl2 = "";
            curGPUrl = TESTGPURL;
            curWPUrl = TESTWEBPURL;
            return;
        }
        if (ProjectType.ZIAN.equals(ProjectType.pType)) {
            curApiSdkUrl1 = ZIANAPISDKURL1;
            curApiSdkUrl2 = "";
            curGPUrl = ZIANGPURL;
            curWPUrl = ZIANWEBPURL;
            return;
        }
        List<String> list = apiSDKUrlList;
        if (list == null || list.size() == 0) {
            init();
        }
        curApiSdkUrl1 = apiSDKUrlList.get(0);
        curGPUrl = apiPayUrlList.get(0);
        curWPUrl = webPayUrlList.get(0);
        SURVEYURL = surveyUrlList.get(0);
        EVENT_REPORT_URL = apiLogUrlList.get(0);
    }
}
