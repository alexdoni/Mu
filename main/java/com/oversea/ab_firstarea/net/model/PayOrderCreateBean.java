package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class PayOrderCreateBean extends BaseBean<Data> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.oversea.ab_firstplatform.model.BaseBean
    public Data getData() {
        if (super.getData() == null) {
            setData(new Data());
        }
        return (Data) super.getData();
    }

    /* loaded from: classes.dex */
    public static class Data {
        private int jump_to_mycard_point_view;
        private String order_number;
        private String trade_pay_url;

        public int getJump_to_mycard_point_view() {
            return this.jump_to_mycard_point_view;
        }

        public void setJump_to_mycard_point_view(int i) {
            this.jump_to_mycard_point_view = i;
        }

        public String getTrade_pay_url() {
            return this.trade_pay_url;
        }

        public void setTrade_pay_url(String str) {
            this.trade_pay_url = str;
        }

        public String getOrder_number() {
            return this.order_number;
        }

        public void setOrder_number(String str) {
            this.order_number = str;
        }
    }
}
