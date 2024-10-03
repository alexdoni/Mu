package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class UserInfoBean extends BaseBean<Data> {
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
        private int account_type;
        private long bind_email_time;
        private long bind_phone_time;
        private int can_bind_email;
        private int can_bind_phone;
        private int can_change_password;
        private int can_unbind_email;
        private int can_unbind_phone;
        private int can_upgrade;
        private String email;
        private int info_certification_status;
        private String phone;
        private String phone_prefix;
        private String platform_account;
        private int platform_uid;

        public int getInfo_certification_status() {
            return this.info_certification_status;
        }

        public void setInfo_certification_status(int i) {
            this.info_certification_status = i;
        }

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

        public int getAccount_type() {
            return this.account_type;
        }

        public void setAccount_type(int i) {
            this.account_type = i;
        }

        public String getPhone_prefix() {
            return this.phone_prefix;
        }

        public void setPhone_prefix(String str) {
            this.phone_prefix = str;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String str) {
            this.phone = str;
        }

        public long getBind_phone_time() {
            return this.bind_phone_time;
        }

        public void setBind_phone_time(long j) {
            this.bind_phone_time = j;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String str) {
            this.email = str;
        }

        public long getBind_email_time() {
            return this.bind_email_time;
        }

        public void setBind_email_time(long j) {
            this.bind_email_time = j;
        }

        public int getCan_upgrade() {
            return this.can_upgrade;
        }

        public void setCan_upgrade(int i) {
            this.can_upgrade = i;
        }

        public int getCan_change_password() {
            return this.can_change_password;
        }

        public void setCan_change_password(int i) {
            this.can_change_password = i;
        }

        public int getCan_bind_phone() {
            return this.can_bind_phone;
        }

        public void setCan_bind_phone(int i) {
            this.can_bind_phone = i;
        }

        public int getCan_bind_email() {
            return this.can_bind_email;
        }

        public void setCan_bind_email(int i) {
            this.can_bind_email = i;
        }

        public int getCan_unbind_phone() {
            return this.can_unbind_phone;
        }

        public void setCan_unbind_phone(int i) {
            this.can_unbind_phone = i;
        }

        public int getCan_unbind_email() {
            return this.can_unbind_email;
        }

        public void setCan_unbind_email(int i) {
            this.can_unbind_email = i;
        }

        public void cleanData() {
            this.platform_uid = 0;
            this.platform_account = "";
            this.account_type = 0;
            this.phone_prefix = "";
            this.phone = "";
            this.bind_phone_time = 0L;
            this.email = "";
            this.bind_email_time = 0L;
            this.can_upgrade = 0;
            this.can_change_password = 0;
            this.can_bind_phone = 0;
            this.can_bind_email = 0;
            this.can_unbind_phone = 0;
            this.can_unbind_email = 0;
        }
    }
}
