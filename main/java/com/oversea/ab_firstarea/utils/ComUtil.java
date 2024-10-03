package com.oversea.ab_firstarea.utils;

import com.facebook.share.internal.ShareConstants;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.json.JsonUtil;

/* loaded from: classes.dex */
public class ComUtil {
    public static String getBaseBeanTip(BaseBean baseBean) {
        if (baseBean == null) {
            return "";
        }
        try {
            if (((Integer) JsonUtil.get(baseBean.getMessage(), "code")).intValue() == 50000) {
                return (String) JsonUtil.get(baseBean.getMessage(), ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
            }
        } catch (Exception unused) {
        }
        return "[" + baseBean.getCode() + "] " + baseBean.getMessage();
    }
}
