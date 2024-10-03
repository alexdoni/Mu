package com.oversea.ab_firstplatform.imp;

import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.plugin.pay.XPay;

/* loaded from: classes2.dex */
public class XSimpleDefaultPay implements XPay {
    @Override // com.oversea.ab_firstplatform.plugin.XPlugin
    public boolean isSupportMethod(String str) {
        return true;
    }

    @Override // com.oversea.ab_firstplatform.plugin.pay.XPay
    public void pay(Lxhw_PayParams lxhw_PayParams) {
    }
}
