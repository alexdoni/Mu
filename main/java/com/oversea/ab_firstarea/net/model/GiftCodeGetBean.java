package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class GiftCodeGetBean extends BaseBean<Data> {
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
        private String gift_code;
        private int gift_id;

        public int getGift_id() {
            return this.gift_id;
        }

        public void setGift_id(int i) {
            this.gift_id = i;
        }

        public String getGift_code() {
            return this.gift_code;
        }

        public void setGift_code(String str) {
            this.gift_code = str;
        }
    }
}
