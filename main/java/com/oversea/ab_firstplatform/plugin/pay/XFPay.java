package com.oversea.ab_firstplatform.plugin.pay;

import com.oversea.ab_firstplatform.imp.XSimpleDefaultPay;

/* loaded from: classes2.dex */
public class XFPay {
    private static XFPay instance;
    private XPay payPlugin;

    private XFPay() {
    }

    public static XFPay getInstance() {
        if (instance == null) {
            instance = new XFPay();
        }
        return instance;
    }

    public void init() {
        if (this.payPlugin == null) {
            this.payPlugin = new XSimpleDefaultPay();
        }
    }

    public boolean isSupport(String str) {
        XPay xPay = this.payPlugin;
        if (xPay == null) {
            return false;
        }
        return xPay.isSupportMethod(str);
    }

    public void pay(Lxhw_PayParams lxhw_PayParams) {
        XPay xPay = this.payPlugin;
        if (xPay == null) {
            return;
        }
        xPay.pay(lxhw_PayParams);
    }
}
