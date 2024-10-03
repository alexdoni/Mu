package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class ForgetpwAccountBean extends BaseBean {
    private Data data;

    @Override // com.oversea.ab_firstplatform.model.BaseBean
    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    /* loaded from: classes.dex */
    public static class Data {
        private int game_uid;
        private String platform_account;
        private int platform_uid;
        private String pwd;

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

        public String getPwd() {
            return this.pwd;
        }

        public void setPwd(String str) {
            this.pwd = str;
        }
    }
}
