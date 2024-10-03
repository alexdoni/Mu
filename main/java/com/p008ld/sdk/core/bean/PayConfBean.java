package com.p008ld.sdk.core.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class PayConfBean {
    public static final int PAY_MODE_LD_COIN = 0;
    public String amount;
    public long coin;
    public String coinDescription;
    public String countryCode;
    public String countryName;
    public String currency;
    public String currencyLogo;
    public List<PayChannelProductVosBean.PayChannelConfigVosBean> payChannelConfigVos;
    public List<PayChannelProductVosBean> payChannelProductVos;

    /* loaded from: classes2.dex */
    public static class PayChannelProductVosBean {
        public long coin;
        public String countryCode;
        public String currency;

        /* renamed from: id */
        public long f687id;
        public List<PayChannelConfigVosBean> payChannelConfigVos;
        public int payChannelId;
        public long price;

        /* loaded from: classes2.dex */
        public static class PayChannelConfigVosBean {
            public long coin;
            public String countryCode;
            public String currency;
            public float discount;
            public String discountPrice;
            public long gradeChannelTypeId;

            /* renamed from: id */
            public int f688id;
            public String myCurrency;
            public int osWeb;
            public String payChannelCode;
            public String payChannelIcon;
            public String payChannelName;
            public String payChannelType;
            public String price;
            public long productId;
        }
    }
}
