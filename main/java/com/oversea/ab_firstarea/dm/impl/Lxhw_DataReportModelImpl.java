package com.oversea.ab_firstarea.dm.impl;

import com.oversea.ab_firstarea.dm.Lxhw_DataReportModel;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserExtraData;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_DataReportModelImpl implements Lxhw_DataReportModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_DataReportModel
    public void report(int i, Lxhw_UserExtraData lxhw_UserExtraData) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("report");
        createCommonParams.put("login_log_id", Integer.valueOf(i));
        createCommonParams.put("report_type", lxhw_UserExtraData.getDataType() + "");
        createCommonParams.put("server_id", lxhw_UserExtraData.getServerId());
        createCommonParams.put("server_name", lxhw_UserExtraData.getServerName());
        createCommonParams.put("role_id", lxhw_UserExtraData.getRoleID());
        createCommonParams.put("role_name", lxhw_UserExtraData.getRoleName());
        createCommonParams.put("role_level", lxhw_UserExtraData.getRoleLevel());
        createCommonParams.put("remain_coin", lxhw_UserExtraData.getRemain_coin());
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.DATAREPORT_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("report", createCommonParams, AreaBaseService.DATAREPORT_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_DataReportModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str) {
                try {
                    LLog.v_Control(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                LLog.v_noControl("report " + baseBean.getCode() + " " + baseBean.getMessage());
            }
        });
    }
}
