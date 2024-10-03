package com.oversea.ab_firstarea.net.model;

import android.text.TextUtils;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.List;

/* loaded from: classes.dex */
public class PayInitBean extends BaseBean<Data> {
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
        private int additional_rate;
        private int android_pay_type;
        private int inapp_enable_third_level;
        private int ios_pay_type;
        private String pay_channel_code;
        private int pay_channel_group_id;
        private int pay_channel_id;
        private int pay_channel_parent_id;
        private int popup_switch;
        private String popup_tips;
        private String popup_url;
        private String store_product_id;
        private List<WebPayListBean> web_pay_list;
        private List<WebPayListBean> web_pay_list_format;

        public List<WebPayListBean> getWeb_pay_child_list() {
            return this.web_pay_list;
        }

        public void setWeb_pay_child_list(List<WebPayListBean> list) {
            this.web_pay_list = list;
        }

        public int getPay_channel_parent_id() {
            return this.pay_channel_parent_id;
        }

        public void setPay_channel_parent_id(int i) {
            this.pay_channel_parent_id = i;
        }

        public int getPay_channel_group_id() {
            return this.pay_channel_group_id;
        }

        public void setPay_channel_group_id(int i) {
            this.pay_channel_group_id = i;
        }

        public int getPay_channel_id() {
            return this.pay_channel_id;
        }

        public void setPay_channel_id(int i) {
            this.pay_channel_id = i;
        }

        public String getPay_channel_code() {
            return this.pay_channel_code;
        }

        public void setPay_channel_code(String str) {
            this.pay_channel_code = str;
        }

        public int getIos_pay_type() {
            return this.ios_pay_type;
        }

        public void setIos_pay_type(int i) {
            this.ios_pay_type = i;
        }

        public int getAndroid_pay_type() {
            return this.android_pay_type;
        }

        public void setAndroid_pay_type(int i) {
            this.android_pay_type = i;
        }

        public int getInapp_enable_third_level() {
            return this.inapp_enable_third_level;
        }

        public void setInapp_enable_third_level(int i) {
            this.inapp_enable_third_level = i;
        }

        public int getPopup_switch() {
            return this.popup_switch;
        }

        public void setPopup_switch(int i) {
            this.popup_switch = i;
        }

        public String getPopup_tips() {
            if (TextUtils.isEmpty(this.popup_tips)) {
                this.popup_tips = "";
            }
            return this.popup_tips;
        }

        public void setPopup_tips(String str) {
            this.popup_tips = str;
        }

        public String getPopup_url() {
            if (TextUtils.isEmpty(this.popup_url)) {
                this.popup_url = "";
            }
            return this.popup_url;
        }

        public void setPopup_url(String str) {
            this.popup_url = str;
        }

        public int getAdditional_rate() {
            return this.additional_rate;
        }

        public void setAdditional_rate(int i) {
            this.additional_rate = i;
        }

        public String getStore_product_id() {
            return this.store_product_id;
        }

        public void setStore_product_id(String str) {
            this.store_product_id = str;
        }

        public List<WebPayListBean> getWeb_pay_list() {
            return this.web_pay_list_format;
        }

        public void setWeb_pay_list(List<WebPayListBean> list) {
            this.web_pay_list_format = list;
        }

        /* loaded from: classes.dex */
        public static class WebPayListBean {
            private List<childChannelBean> children;
            private String icon;
            private boolean isExpend;
            private int is_wv;
            private int pay_channel_group_id;
            private String pay_channel_group_name;
            private int pay_channel_id;
            private String pay_channel_name;
            private int pay_channel_parent_id;
            private String promotion;

            public List<childChannelBean> getChildrens() {
                return this.children;
            }

            public void setChildrens(List<childChannelBean> list) {
                this.children = list;
            }

            public boolean isExpend() {
                return this.isExpend;
            }

            public void setExpend(boolean z) {
                this.isExpend = z;
            }

            public String getPay_channel_group_name() {
                return this.pay_channel_group_name;
            }

            public void setPay_channel_group_name(String str) {
                this.pay_channel_group_name = str;
            }

            public int getIs_wv() {
                return this.is_wv;
            }

            public void setIs_wv(int i) {
                this.is_wv = i;
            }

            public String getPromotion() {
                return this.promotion;
            }

            public void setPromotion(String str) {
                this.promotion = str;
            }

            public int getPay_channel_parent_id() {
                return this.pay_channel_parent_id;
            }

            public void setPay_channel_parent_id(int i) {
                this.pay_channel_parent_id = i;
            }

            public int getPay_channel_group_id() {
                return this.pay_channel_group_id;
            }

            public void setPay_channel_group_id(int i) {
                this.pay_channel_group_id = i;
            }

            public int getPay_channel_id() {
                return this.pay_channel_id;
            }

            public void setPay_channel_id(int i) {
                this.pay_channel_id = i;
            }

            public String getIcon() {
                return this.icon;
            }

            public void setIcon(String str) {
                this.icon = str;
            }

            public String getPay_channel_name() {
                return this.pay_channel_name;
            }

            public void setPay_channel_name(String str) {
                this.pay_channel_name = str;
            }

            /* loaded from: classes.dex */
            public static class childChannelBean {
                private String icon;
                private int is_wv;
                private int pay_channel_group_id;
                private int pay_channel_id;
                private String pay_channel_name;
                private int pay_channel_parent_id;
                private String promotion;

                public int getIs_wv() {
                    return this.is_wv;
                }

                public void setIs_wv(int i) {
                    this.is_wv = i;
                }

                public String getPromotion() {
                    return this.promotion;
                }

                public void setPromotion(String str) {
                    this.promotion = str;
                }

                public int getPay_channel_parent_id() {
                    return this.pay_channel_parent_id;
                }

                public void setPay_channel_parent_id(int i) {
                    this.pay_channel_parent_id = i;
                }

                public int getPay_channel_group_id() {
                    return this.pay_channel_group_id;
                }

                public void setPay_channel_group_id(int i) {
                    this.pay_channel_group_id = i;
                }

                public int getPay_channel_id() {
                    return this.pay_channel_id;
                }

                public void setPay_channel_id(int i) {
                    this.pay_channel_id = i;
                }

                public String getIcon() {
                    return this.icon;
                }

                public void setIcon(String str) {
                    this.icon = str;
                }

                public String getPay_channel_name() {
                    return this.pay_channel_name;
                }

                public void setPay_channel_name(String str) {
                    this.pay_channel_name = str;
                }
            }
        }
    }
}
