package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class UserBindInfoBean extends BaseBean<Data> {
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
        private int bind_email_time;
        private int bind_phone_time;
        private String email;
        private String phone;
        private String phone_prefix;
        private String platform_account;
        private int platform_uid;

        public void setPlatform_uid(int i) {
            this.platform_uid = i;
        }

        public int getPlatform_uid() {
            return this.platform_uid;
        }

        public void setPlatform_account(String str) {
            this.platform_account = str;
        }

        public String getPlatform_account() {
            return this.platform_account;
        }

        public void setPhone_prefix(String str) {
            this.phone_prefix = str;
        }

        public String getPhone_prefix() {
            return this.phone_prefix;
        }

        public void setPhone(String str) {
            this.phone = str;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setBind_phone_time(int i) {
            this.bind_phone_time = i;
        }

        public int getBind_phone_time() {
            return this.bind_phone_time;
        }

        public void setEmail(String str) {
            this.email = str;
        }

        public String getEmail() {
            return this.email;
        }

        public void setBind_email_time(int i) {
            this.bind_email_time = i;
        }

        public int getBind_email_time() {
            return this.bind_email_time;
        }
    }
}
