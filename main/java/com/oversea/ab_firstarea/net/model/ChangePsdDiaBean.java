package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class ChangePsdDiaBean extends BaseBean<Data> {
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
        private int game_id;
        private int game_uid;
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

        public void cleanData() {
            this.platform_uid = 0;
            this.platform_account = "";
            this.game_uid = 0;
            this.game_id = 0;
        }
    }
}
