package com.oversea.ab_firstarea.net.model;

import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.List;

/* loaded from: classes.dex */
public class IssueMsgListBean extends BaseBean<Data> {
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
        private List<Issue_msg_list> issue_msg_list;

        public void setIssue_msg_list(List<Issue_msg_list> list) {
            this.issue_msg_list = list;
        }

        public List<Issue_msg_list> getIssue_msg_list() {
            return this.issue_msg_list;
        }
    }

    /* loaded from: classes.dex */
    public static class Issue_msg_list {
        private String from_name;
        private int from_type;
        private int issue_id;
        private String msg_image_url;
        private String msg_text;
        private long msg_time;
        private int msg_type;

        public void setIssue_id(int i) {
            this.issue_id = i;
        }

        public int getIssue_id() {
            return this.issue_id;
        }

        public void setFrom_type(int i) {
            this.from_type = i;
        }

        public int getFrom_type() {
            return this.from_type;
        }

        public void setFrom_name(String str) {
            this.from_name = str;
        }

        public String getFrom_name() {
            return this.from_name;
        }

        public void setMsg_type(int i) {
            this.msg_type = i;
        }

        public int getMsg_type() {
            return this.msg_type;
        }

        public void setMsg_text(String str) {
            this.msg_text = str;
        }

        public String getMsg_text() {
            return this.msg_text;
        }

        public void setMsg_image_url(String str) {
            this.msg_image_url = str;
        }

        public String getMsg_image_url() {
            return this.msg_image_url;
        }

        public void setMsg_time(long j) {
            this.msg_time = j;
        }

        public long getMsg_time() {
            return this.msg_time;
        }
    }
}
