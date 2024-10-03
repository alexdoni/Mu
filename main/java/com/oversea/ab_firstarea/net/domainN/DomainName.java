package com.oversea.ab_firstarea.net.domainN;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.oversea.ab_firstarea.channel.PTypeUrl;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class DomainName {
    private static final int UPDATE_CONTENT = 1111;
    static DomainName domainName;
    public DomainPingModle firstPingModle;
    private Handler handler;
    public JSONArray member_dom_arr;
    private String TAG = "DomainName";
    public int morenTime = 10000;
    public String member_dom = "";
    List<String> fixed_dom = new ArrayList();
    List<String> finish_dom = new ArrayList();
    private List<String> pay_domain_list = new ArrayList();
    private List<String> pay_platform_domain_list = new ArrayList();
    private List<String> survey_domain_backup_list = new ArrayList();
    private List<String> api_logs_domain_backup_list = new ArrayList();
    public boolean isFirstinit = false;
    public boolean isChangeDomOneXunhuan = false;
    public int curIndex = 0;
    public int curPayIndex = 0;
    public int curPlatformPayIndex = 0;
    public int curSurveyDomainBackupListIndex = 0;
    public int curApiLogsIndex = 0;
    int pingIndex = 0;

    public void init(Context context) {
    }

    public static DomainName getInstance() {
        if (domainName == null) {
            domainName = new DomainName();
        }
        return domainName;
    }

    private void initDomain() {
        AreaBaseService.setUrl(PTypeUrl.curApiSdkUrl1);
        AreaBaseService.setPayUrl(PTypeUrl.curGPUrl);
        AreaBaseService.setPlatformPayUrl(PTypeUrl.curWPUrl);
        AreaBaseService.setSurveyUrl(PTypeUrl.SURVEYURL);
        AreaBaseService.setApiLogsUrl(PTypeUrl.EVENT_REPORT_URL);
    }

    public void justDomHaveHttpAdd(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!str.contains(AreaBaseService.HTTPS)) {
            str = AreaBaseService.HTTPS + str;
        }
        if (ishave(this.fixed_dom, str)) {
            return;
        }
        this.fixed_dom.add(str);
    }

    public List<DomainPingModle> paixu(List<DomainPingModle> list) {
        Collections.sort(list, new Comparator<DomainPingModle>() { // from class: com.oversea.ab_firstarea.net.domainN.DomainName.1
            @Override // java.util.Comparator
            public int compare(DomainPingModle domainPingModle, DomainPingModle domainPingModle2) {
                return domainPingModle.getResponseTime() - domainPingModle2.getResponseTime();
            }
        });
        return list;
    }

    public void hefinishdom() {
        try {
            this.finish_dom.clear();
            this.curIndex = 0;
            List<String> list = this.fixed_dom;
            if (list != null && list.size() > 0) {
                for (String str : this.fixed_dom) {
                    if (!ishave(this.finish_dom, str)) {
                        this.finish_dom.add(str);
                    }
                }
            }
            if (!TextUtils.isEmpty(this.member_dom)) {
                this.finish_dom.add(this.member_dom);
            }
            JSONArray jSONArray = this.member_dom_arr;
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i = 0; i < this.member_dom_arr.length(); i++) {
                    String str2 = (String) this.member_dom_arr.get(i);
                    if (!TextUtils.isEmpty(str2) && !ishave(this.finish_dom, str2)) {
                        this.finish_dom.add(str2);
                    }
                }
            }
            AreaBaseService.setUrl(this.finish_dom.get(0));
        } catch (Throwable unused) {
        }
    }

    public boolean ishave(List<String> list, String str) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void addPay_domain(String str) {
        if (TextUtils.isEmpty(str) || ishave(this.pay_domain_list, str)) {
            return;
        }
        this.pay_domain_list.add(str);
    }

    public void addPay_domain_list(List<String> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            addPay_domain(it.next());
        }
    }

    public void changePayDomainList() {
        this.curPayIndex++;
        List<String> list = this.pay_domain_list;
        if (list == null || list.size() <= 0) {
            return;
        }
        if (this.curPayIndex >= this.pay_domain_list.size()) {
            this.curPayIndex = 0;
        }
        if (this.pay_domain_list.get(this.curPayIndex).contains(AreaBaseService.HTTPS)) {
            AreaBaseService.setPayUrl(this.pay_domain_list.get(this.curPayIndex));
            return;
        }
        AreaBaseService.setPayUrl(AreaBaseService.HTTPS + this.pay_domain_list.get(this.curPayIndex));
    }

    public void addPlatformPay_domain(String str) {
        if (TextUtils.isEmpty(str) || ishave(this.pay_platform_domain_list, str)) {
            return;
        }
        this.pay_platform_domain_list.add(str);
    }

    public void addPlatformPay_domain_list(List<String> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            addPlatformPay_domain(it.next());
        }
    }

    public void changePlatformPayDomainList() {
        this.curPlatformPayIndex++;
        List<String> list = this.pay_platform_domain_list;
        if (list == null || list.size() <= 0) {
            return;
        }
        if (this.curPlatformPayIndex >= this.pay_platform_domain_list.size()) {
            this.curPlatformPayIndex = 0;
        }
        if (this.pay_platform_domain_list.get(this.curPlatformPayIndex).contains(AreaBaseService.HTTPS)) {
            AreaBaseService.setPlatformPayUrl(this.pay_platform_domain_list.get(this.curPlatformPayIndex));
            return;
        }
        AreaBaseService.setPlatformPayUrl(AreaBaseService.HTTPS + this.pay_platform_domain_list.get(this.curPlatformPayIndex));
    }

    public void addSurveydomain(String str) {
        if (TextUtils.isEmpty(str) || ishave(this.survey_domain_backup_list, str)) {
            return;
        }
        this.survey_domain_backup_list.add(str);
    }

    public void addSurvey_domain_list(List<String> list) {
        List<String> list2 = this.survey_domain_backup_list;
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            addSurveydomain(it.next());
        }
    }

    public void changeSurveyDomainList() {
        this.curSurveyDomainBackupListIndex++;
        List<String> list = this.survey_domain_backup_list;
        if (list == null || list.size() <= 0) {
            return;
        }
        if (this.curSurveyDomainBackupListIndex >= this.survey_domain_backup_list.size()) {
            this.curSurveyDomainBackupListIndex = 0;
        }
        if (this.survey_domain_backup_list.get(this.curSurveyDomainBackupListIndex).contains(AreaBaseService.HTTPS)) {
            AreaBaseService.setSurveyUrl(this.survey_domain_backup_list.get(this.curSurveyDomainBackupListIndex));
            return;
        }
        AreaBaseService.setSurveyUrl(AreaBaseService.HTTPS + this.survey_domain_backup_list.get(this.curSurveyDomainBackupListIndex));
    }

    public void addApiLogsDomain(String str) {
        if (TextUtils.isEmpty(str) || ishave(this.api_logs_domain_backup_list, str)) {
            return;
        }
        this.api_logs_domain_backup_list.add(str);
    }

    public void addApiLogs_domain_list(List<String> list) {
        List<String> list2 = this.api_logs_domain_backup_list;
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            addApiLogsDomain(it.next());
        }
    }

    public void changeApiLogsDomainList() {
        this.curApiLogsIndex++;
        List<String> list = this.api_logs_domain_backup_list;
        if (list == null || list.size() <= 0) {
            return;
        }
        if (this.curApiLogsIndex >= this.api_logs_domain_backup_list.size()) {
            this.curApiLogsIndex = 0;
        }
        if (this.api_logs_domain_backup_list.get(this.curApiLogsIndex).contains(AreaBaseService.HTTPS)) {
            AreaBaseService.setApiLogsUrl(this.api_logs_domain_backup_list.get(this.curApiLogsIndex));
            return;
        }
        AreaBaseService.setApiLogsUrl(AreaBaseService.HTTPS + this.api_logs_domain_backup_list.get(this.curApiLogsIndex));
    }
}
