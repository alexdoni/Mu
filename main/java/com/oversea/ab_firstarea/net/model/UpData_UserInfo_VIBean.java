package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class UpData_UserInfo_VIBean extends BaseBean<Data> {
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
        private int game_id;
        private int game_uid;
        private int login_log_id;
        private long login_time;
        private String platform_account;
        private int platform_uid;
        private String token;
        private long token_expiration;
        private long token_notBefore;

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

        public void setGame_uid(int i) {
            this.game_uid = i;
        }

        public int getGame_uid() {
            return this.game_uid;
        }

        public void setGame_id(int i) {
            this.game_id = i;
        }

        public int getGame_id() {
            return this.game_id;
        }

        public void setAccount_type(int i) {
            this.account_type = i;
        }

        public int getAccount_type() {
            return this.account_type;
        }

        public void setLogin_time(long j) {
            this.login_time = j;
        }

        public long getLogin_time() {
            return this.login_time;
        }

        public void setToken_expiration(long j) {
            this.token_expiration = j;
        }

        public long getToken_expiration() {
            return this.token_expiration;
        }

        public void setToken_notBefore(long j) {
            this.token_notBefore = j;
        }

        public long getToken_notBefore() {
            return this.token_notBefore;
        }

        public void setToken(String str) {
            this.token = str;
        }

        public String getToken() {
            return this.token;
        }

        public void setLogin_log_id(int i) {
            this.login_log_id = i;
        }

        public int getLogin_log_id() {
            return this.login_log_id;
        }

        public void cleanData() {
            this.platform_uid = 0;
            this.platform_account = "";
            this.game_uid = 0;
            this.game_id = 0;
            this.account_type = 0;
            this.login_time = 0L;
            this.token_expiration = 0L;
            this.token_notBefore = 0L;
            this.token = "";
            this.login_log_id = 0;
        }
    }
}
