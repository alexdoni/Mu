package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.List;

/* loaded from: classes.dex */
public class GiftTypeListBean extends BaseBean<Data> {
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
        private List<Gift_list> gift_list;

        public void setGift_list(List<Gift_list> list) {
            this.gift_list = list;
        }

        public List<Gift_list> getGift_list() {
            return this.gift_list;
        }
    }

    /* loaded from: classes.dex */
    public static class Gift_list {
        private String effective_end_time;
        private String effective_start_time;
        private int gift_code_total;
        private String gift_desc;
        private String gift_icon;
        private int gift_id;
        private String gift_name;
        private boolean isGet;
        private int not_get_gift_code_num;
        private String remain_gift_code_rate;

        public void setGift_id(int i) {
            this.gift_id = i;
        }

        public int getGift_id() {
            return this.gift_id;
        }

        public void setGift_name(String str) {
            this.gift_name = str;
        }

        public String getGift_name() {
            return this.gift_name;
        }

        public void setGift_desc(String str) {
            this.gift_desc = str;
        }

        public String getGift_desc() {
            return this.gift_desc;
        }

        public void setGift_icon(String str) {
            this.gift_icon = str;
        }

        public String getGift_icon() {
            return this.gift_icon;
        }

        public void setEffective_start_time(String str) {
            this.effective_start_time = str;
        }

        public String getEffective_start_time() {
            return this.effective_start_time;
        }

        public void setEffective_end_time(String str) {
            this.effective_end_time = str;
        }

        public String getEffective_end_time() {
            return this.effective_end_time;
        }

        public void setGift_code_total(int i) {
            this.gift_code_total = i;
        }

        public int getGift_code_total() {
            return this.gift_code_total;
        }

        public void setNot_get_gift_code_num(int i) {
            this.not_get_gift_code_num = i;
        }

        public int getNot_get_gift_code_num() {
            return this.not_get_gift_code_num;
        }

        public void setRemain_gift_code_rate(String str) {
            this.remain_gift_code_rate = str;
        }

        public String getRemain_gift_code_rate() {
            return this.remain_gift_code_rate;
        }

        public boolean isGet() {
            return this.isGet;
        }

        public void setGet(boolean z) {
            this.isGet = z;
        }
    }
}
