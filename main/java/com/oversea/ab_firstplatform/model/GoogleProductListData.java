package com.oversea.ab_firstplatform.model;

import java.util.List;

/* loaded from: classes2.dex */
public class GoogleProductListData extends BaseBean<Data> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.oversea.ab_firstplatform.model.BaseBean
    public Data getData() {
        if (super.getData() == null) {
            setData(new Data());
        }
        return (Data) super.getData();
    }

    /* loaded from: classes2.dex */
    public static class Data {
        private List<Product_list> product_list;

        public void setProduct_list(List<Product_list> list) {
            this.product_list = list;
        }

        public List<Product_list> getProduct_list() {
            return this.product_list;
        }
    }

    /* loaded from: classes2.dex */
    public static class Product_list {
        private String product_id = "";
        private String product_type = "";
        private String product_name = "";
        private String amount = "";
        private String currency_code = "";

        public String getProduct_id() {
            return this.product_id;
        }

        public void setProduct_id(String str) {
            this.product_id = str;
        }

        public String getProduct_type() {
            return this.product_type;
        }

        public void setProduct_type(String str) {
            this.product_type = str;
        }

        public String getProduct_name() {
            return this.product_name;
        }

        public void setProduct_name(String str) {
            this.product_name = str;
        }

        public String getAmount() {
            return this.amount;
        }

        public void setAmount(String str) {
            this.amount = str;
        }

        public String getCurrency_code() {
            return this.currency_code;
        }

        public void setCurrency_code(String str) {
            this.currency_code = str;
        }
    }
}
