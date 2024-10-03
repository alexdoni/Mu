package com.oversea.ab_firstarea.dm;

import com.oversea.ab_firstarea.dpresenter.OnMobileConfirmListener;
import com.oversea.ab_firstarea.net.model.MobileConfirmBean;

/* loaded from: classes.dex */
public interface Lxhw_MobileConfirmModel {
    void doBindPhone(String str, String str2, String str3, OnMobileConfirmListener<MobileConfirmBean> onMobileConfirmListener);

    void doBindPhoneSendCode(String str, String str2, OnMobileConfirmListener<MobileConfirmBean> onMobileConfirmListener);
}
