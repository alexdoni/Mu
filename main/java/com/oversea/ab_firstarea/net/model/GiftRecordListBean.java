package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.List;

/* loaded from: classes.dex */
public class GiftRecordListBean extends BaseBean<Data> {
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
        private List<Gift_code_get_record_list> gift_code_get_record_list;

        public List<Gift_code_get_record_list> getGift_code_get_record_list() {
            return this.gift_code_get_record_list;
        }

        public void setGift_code_get_record_list(List<Gift_code_get_record_list> list) {
            this.gift_code_get_record_list = list;
        }
    }

    /* loaded from: classes.dex */
    public static class Gift_code_get_record_list {
        private String effective_end_time;
        private String effective_start_time;
        private String gift_code;
        private String gift_desc;
        private String gift_icon;
        private int gift_id;
        private String gift_name;

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

        public String getGift_code() {
            return this.gift_code;
        }

        public void setGift_code(String str) {
            this.gift_code = str;
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
    }
}
