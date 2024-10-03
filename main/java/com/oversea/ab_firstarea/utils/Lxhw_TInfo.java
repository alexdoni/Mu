package com.oversea.ab_firstarea.utils;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class Lxhw_TInfo {
    private static Lxhw_TInfo mInstance;
    private List<Info> listData = new ArrayList();
    private List<Info> listFunData = new ArrayList();
    public final String APPLICATION = "Application";
    public final String LIFECYCLE = RequestParameters.SUBRESOURCE_LIFECYCLE;
    public final String ATTACHBASECONTEXT = "attachBaseContext";
    public final String ONACTIVITYRESULT = "onActivityResult";
    public final String ONREQUESTPRERMISSIONRESULT = "onRequestPermissionsResult";
    public final String ROLEINFO = "roleinfosb";
    public final String EVENTAF = "EventAF";
    public final String ACCOUNTUP = "accountup";
    public final String SWITCHACCOUNT = "switchaccount";
    public final String BINDRESLUT = "onBindResult";
    public final String DIANZHAN = "onDianzanResult";
    public final String FENXIANG = "FENXIANG";
    public final String PINFEN = "onPingFenResult";
    public final String ADLISTENER = "addADListener";
    public final String DownHeadListener = "addDownHeadListener";
    public final String UpLoadHeadListener = "addUpLoadHeadListener";
    public final String WENJUANDIAOCHA = "showSurveyViewController";
    public final String SETGAMELANGUAGE = "setGameLanguage";
    public final String BILLING = "billing";

    public static Lxhw_TInfo getInstance() {
        if (mInstance == null) {
            mInstance = new Lxhw_TInfo();
        }
        return mInstance;
    }

    public void cleanData() {
        List<Info> list = this.listData;
        if (list != null) {
            list.clear();
        }
    }

    public void addData(String str, String str2) {
        if (this.listData != null) {
            Info info = new Info();
            info.key = str;
            info.value = str2;
            this.listData.add(info);
        }
    }

    public List<Info> getListData() {
        return this.listData;
    }

    /* loaded from: classes.dex */
    public static class Info {
        private String key;
        private String value;

        public String getKey() {
            return this.key;
        }

        public void setKey(String str) {
            this.key = str;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String str) {
            this.value = str;
        }
    }
}
