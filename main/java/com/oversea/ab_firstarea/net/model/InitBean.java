package com.oversea.ab_firstarea.net.model;

import android.text.TextUtils;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class InitBean extends BaseBean<InitBean> {
    public static InitBean initInfo;
    private List<Advert_realization_place_list> advert_realization_place_list;
    private Android_third_login_switch_config android_third_login_switch_config;
    private String country_code;
    private Customer_service_config customer_service_config;
    private Domain_info domain_info;
    private Game_info game_info;
    private int is_in_european_union;
    private Term_info term_info;
    private List<Term_infos> term_infos;

    public int getIs_in_european_union() {
        return this.is_in_european_union;
    }

    public void setIs_in_european_union(int i) {
        this.is_in_european_union = i;
    }

    public static InitBean getInstance() {
        if (initInfo == null) {
            initInfo = new InitBean();
        }
        return initInfo;
    }

    public String getCountry_code() {
        return this.country_code;
    }

    public void setCountry_code(String str) {
        this.country_code = str;
    }

    public Game_info getGame_info() {
        return this.game_info;
    }

    public void setGame_info(Game_info game_info) {
        this.game_info = game_info;
    }

    public Term_info getTerm_info() {
        return this.term_info;
    }

    public void setTerm_info(Term_info term_info) {
        this.term_info = term_info;
    }

    public List<Term_infos> getTerm_infos() {
        return this.term_infos;
    }

    public void setTerm_infos(List<Term_infos> list) {
        this.term_infos = list;
    }

    public Customer_service_config getCustomer_service_config() {
        if (this.customer_service_config == null) {
            this.customer_service_config = new Customer_service_config();
        }
        return this.customer_service_config;
    }

    public void setCustomer_service_config(Customer_service_config customer_service_config) {
        this.customer_service_config = customer_service_config;
    }

    public Android_third_login_switch_config getAndroid_third_login_switch_config() {
        if (this.android_third_login_switch_config == null) {
            this.android_third_login_switch_config = new Android_third_login_switch_config();
        }
        return this.android_third_login_switch_config;
    }

    public void setAndroid_third_login_switch_config(Android_third_login_switch_config android_third_login_switch_config) {
        this.android_third_login_switch_config = android_third_login_switch_config;
    }

    public void setAdvert_realization_place_list(List<Advert_realization_place_list> list) {
        this.advert_realization_place_list = list;
    }

    public List<Advert_realization_place_list> getAdvert_realization_place_list() {
        if (this.advert_realization_place_list == null) {
            this.advert_realization_place_list = new ArrayList();
        }
        return this.advert_realization_place_list;
    }

    public Domain_info getDomain_info() {
        return this.domain_info;
    }

    public void setDomain_info(Domain_info domain_info) {
        this.domain_info = domain_info;
    }

    /* loaded from: classes.dex */
    public static class Game_info {
        private int android_delete_account_switch;
        private int android_exam_switch;
        private String android_forced_update_version;
        private String android_game_icon;
        private int android_gift_switch;
        private String android_online_version;
        private int android_pre_registration_switch;
        private int android_suspended_switch;
        private int android_update_switch;
        private String android_video_advert_id;
        private String android_video_advert_platform;
        private int certification_switch;
        private String cp_game_url;
        private String discord_invite_url;
        private String fb_fans_url;
        private String fb_share_url;
        private long game_first_open_time;
        private String ios_online_version;
        private int ios_suspended_switch;
        private int ios_update_switch;
        private int is_auto_guest_login;
        private int is_debug;
        private int is_open_fcm;
        private int is_show_agreement;
        private int is_show_first_open_time;
        private int is_show_floating_btn;
        private int is_use_ad_realization;
        private int is_use_newsdk;
        private String lang_code;
        private int login_duration_report_time;
        private int platform_id;
        private int polling_time;
        private String sdk_background_img;
        private int sdk_background_switch;
        private int sdk_text_lang_type;
        private String tw_share_content;
        private String tw_share_img;

        public int getLogin_duration_report_time() {
            return this.login_duration_report_time;
        }

        public void setLogin_duration_report_time(int i) {
            this.login_duration_report_time = i;
        }

        public int getIs_open_fcm() {
            return this.is_open_fcm;
        }

        public void setIs_open_fcm(int i) {
            this.is_open_fcm = i;
        }

        public int getIs_auto_guest_login() {
            return this.is_auto_guest_login;
        }

        public void setIs_auto_guest_login(int i) {
            this.is_auto_guest_login = i;
        }

        public int getIs_debug() {
            return this.is_debug;
        }

        public void setIs_debug(int i) {
            this.is_debug = i;
        }

        public int getPolling_time() {
            return this.polling_time;
        }

        public void setPolling_time(int i) {
            this.polling_time = i;
        }

        public int getIs_use_ad_realization() {
            return this.is_use_ad_realization;
        }

        public void setIs_use_ad_realization(int i) {
            this.is_use_ad_realization = i;
        }

        public int getIs_show_floating_btn() {
            return this.is_show_floating_btn;
        }

        public void setIs_show_floating_btn(int i) {
            this.is_show_floating_btn = i;
        }

        public String getAndroid_video_advert_platform() {
            return this.android_video_advert_platform;
        }

        public void setAndroid_video_advert_platform(String str) {
            this.android_video_advert_platform = str;
        }

        public String getAndroid_video_advert_id() {
            return this.android_video_advert_id;
        }

        public void setAndroid_video_advert_id(String str) {
            this.android_video_advert_id = str;
        }

        public String getDiscord_invite_url() {
            return this.discord_invite_url;
        }

        public void setDiscord_invite_url(String str) {
            this.discord_invite_url = str;
        }

        public String getAndroid_online_version() {
            return this.android_online_version;
        }

        public void setAndroid_online_version(String str) {
            this.android_online_version = str;
        }

        public String getCp_game_url() {
            return this.cp_game_url;
        }

        public void setCp_game_url(String str) {
            this.cp_game_url = str;
        }

        public String getAndroid_game_icon() {
            return this.android_game_icon;
        }

        public void setAndroid_game_icon(String str) {
            this.android_game_icon = str;
        }

        public int getIs_show_first_open_time() {
            return this.is_show_first_open_time;
        }

        public void setIs_show_first_open_time(int i) {
            this.is_show_first_open_time = i;
        }

        public long getGame_first_open_time() {
            return this.game_first_open_time;
        }

        public void setGame_first_open_time(long j) {
            this.game_first_open_time = j;
        }

        public int getPlatform_id() {
            return this.platform_id;
        }

        public void setPlatform_id(int i) {
            this.platform_id = i;
        }

        public int getAndroid_delete_account_switch() {
            return this.android_delete_account_switch;
        }

        public void setAndroid_delete_account_switch(int i) {
            this.android_delete_account_switch = i;
        }

        public int getAndroid_gift_switch() {
            return this.android_gift_switch;
        }

        public void setAndroid_gift_switch(int i) {
            this.android_gift_switch = i;
        }

        public int getIs_use_newsdk() {
            return this.is_use_newsdk;
        }

        public void setIs_use_newsdk(int i) {
            this.is_use_newsdk = i;
        }

        public int getIs_show_agreement() {
            return this.is_show_agreement;
        }

        public void setIs_show_agreement(int i) {
            this.is_show_agreement = i;
        }

        public void setSdk_text_lang_type(int i) {
            this.sdk_text_lang_type = i;
        }

        public int getSdk_text_lang_type() {
            return this.sdk_text_lang_type;
        }

        public void setLang_code(String str) {
            this.lang_code = str;
        }

        public String getLang_code() {
            return this.lang_code;
        }

        public void setFb_fans_url(String str) {
            this.fb_fans_url = str;
        }

        public String getFb_fans_url() {
            return this.fb_fans_url;
        }

        public void setFb_share_url(String str) {
            this.fb_share_url = str;
        }

        public String getFb_share_url() {
            return this.fb_share_url;
        }

        public void setTw_share_img(String str) {
            this.tw_share_img = str;
        }

        public String getTw_share_img() {
            return this.tw_share_img;
        }

        public void setTw_share_content(String str) {
            this.tw_share_content = str;
        }

        public String getTw_share_content() {
            return this.tw_share_content;
        }

        public void setSdk_background_switch(int i) {
            this.sdk_background_switch = i;
        }

        public int getSdk_background_switch() {
            return this.sdk_background_switch;
        }

        public void setSdk_background_img(String str) {
            this.sdk_background_img = str;
        }

        public String getSdk_background_img() {
            return this.sdk_background_img;
        }

        public void setIos_online_version(String str) {
            this.ios_online_version = str;
        }

        public String getIos_online_version() {
            return this.ios_online_version;
        }

        public void setIos_update_switch(int i) {
            this.ios_update_switch = i;
        }

        public int getIos_update_switch() {
            return this.ios_update_switch;
        }

        public void setIos_suspended_switch(int i) {
            this.ios_suspended_switch = i;
        }

        public int getIos_suspended_switch() {
            return this.ios_suspended_switch;
        }

        public void setAndroid_forced_update_version(String str) {
            this.android_forced_update_version = str;
        }

        public String getAndroid_forced_update_version() {
            if (TextUtils.isEmpty(this.android_forced_update_version)) {
                this.android_forced_update_version = "0";
            }
            return this.android_forced_update_version;
        }

        public int getAndroid_update_switch() {
            return this.android_update_switch;
        }

        public void setAndroid_update_switch(int i) {
            this.android_update_switch = i;
        }

        public void setAndroid_suspended_switch(int i) {
            this.android_suspended_switch = i;
        }

        public int getAndroid_suspended_switch() {
            return this.android_suspended_switch;
        }

        public int getAndroid_exam_switch() {
            return this.android_exam_switch;
        }

        public void setAndroid_exam_switch(int i) {
            this.android_exam_switch = i;
        }

        public int getCertification_switch() {
            return this.certification_switch;
        }

        public void setCertification_switch(int i) {
            this.certification_switch = i;
        }

        public int getAndroid_pre_registration_switch() {
            return this.android_pre_registration_switch;
        }

        public void setAndroid_pre_registration_switch(int i) {
            this.android_pre_registration_switch = i;
        }
    }

    /* loaded from: classes.dex */
    public static class Advert_realization_place_list {
        private String advert_place_id;
        private int sort;
        private int type;

        public void setType(int i) {
            this.type = i;
        }

        public int getType() {
            return this.type;
        }

        public void setAdvert_place_id(String str) {
            this.advert_place_id = str;
        }

        public String getAdvert_place_id() {
            return this.advert_place_id;
        }

        public void setSort(int i) {
            this.sort = i;
        }

        public int getSort() {
            return this.sort;
        }
    }

    /* loaded from: classes.dex */
    public static class Domain_info {
        private List<String> api_logs_domain_backup_list;
        private String api_logs_domain_main;
        private List<String> pay_domain_backup_list;
        private String pay_domain_main;
        private List<String> pay_platform_domain_backup_list;
        private String pay_platform_domain_main;
        private List<String> sdk_domain_backup_list;
        private String sdk_domain_main;
        private List<String> survey_domain_backup_list;
        private String survey_domain_main;

        public void setSdk_domain_main(String str) {
            this.sdk_domain_main = str;
        }

        public String getSdk_domain_main() {
            return this.sdk_domain_main;
        }

        public void setSdk_domain_backup_list(List<String> list) {
            this.sdk_domain_backup_list = list;
        }

        public List<String> getSdk_domain_backup_list() {
            return this.sdk_domain_backup_list;
        }

        public void setPay_domain_main(String str) {
            this.pay_domain_main = str;
        }

        public String getPay_domain_main() {
            return this.pay_domain_main;
        }

        public void setPay_domain_backup_list(List<String> list) {
            this.pay_domain_backup_list = list;
        }

        public List<String> getPay_domain_backup_list() {
            return this.pay_domain_backup_list;
        }

        public String getPay_platform_domain_main() {
            return this.pay_platform_domain_main;
        }

        public void setPay_platform_domain_main(String str) {
            this.pay_platform_domain_main = str;
        }

        public List<String> getPay_platform_domain_backup_list() {
            return this.pay_platform_domain_backup_list;
        }

        public void setPay_platform_domain_backup_list(List<String> list) {
            this.pay_platform_domain_backup_list = list;
        }

        public void setSurvey_domain_main(String str) {
            this.survey_domain_main = str;
        }

        public String getSurvey_domain_main() {
            return this.survey_domain_main;
        }

        public void setSurvey_domain_backup_list(List<String> list) {
            this.survey_domain_backup_list = list;
        }

        public List<String> getSurvey_domain_backup_list() {
            return this.survey_domain_backup_list;
        }

        public String getApi_logs_domain_main() {
            return this.api_logs_domain_main;
        }

        public void setApi_logs_domain_main(String str) {
            this.api_logs_domain_main = str;
        }

        public List<String> getApi_logs_domain_backup_list() {
            return this.api_logs_domain_backup_list;
        }

        public void setApi_logs_domain_backup_list(List<String> list) {
            this.api_logs_domain_backup_list = list;
        }
    }

    /* loaded from: classes.dex */
    public static class Term_info {
        private String term_privacy_url;
        private String term_service_url;

        public String getTerm_privacy_url() {
            return this.term_privacy_url;
        }

        public void setTerm_privacy_url(String str) {
            this.term_privacy_url = str;
        }

        public String getTerm_service_url() {
            return this.term_service_url;
        }

        public void setTerm_service_url(String str) {
            this.term_service_url = str;
        }
    }

    /* loaded from: classes.dex */
    public static class Term_infos {
        private List<Children> children;
        private String content_url;
        private int is_validate;
        private String title;

        public List<Children> getChildren() {
            return this.children;
        }

        public void setChildren(List<Children> list) {
            this.children = list;
        }

        public String getContent_url() {
            return this.content_url;
        }

        public void setContent_url(String str) {
            this.content_url = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public int getIs_validate() {
            return this.is_validate;
        }

        public void setIs_validate(int i) {
            this.is_validate = i;
        }

        /* loaded from: classes.dex */
        public static class Children {
            private String content_url;
            private int is_validate;
            private String title;

            public String getContent_url() {
                return this.content_url;
            }

            public void setContent_url(String str) {
                this.content_url = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public int getIs_validate() {
                return this.is_validate;
            }

            public void setIs_validate(int i) {
                this.is_validate = i;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Android_third_login_switch_config {
        private int facebook_switch;
        private FacebookSwitchNewBean facebook_switch_new;
        private int google_swtich;
        private GoogleSwitchNewBean google_swtich_new;
        private int guest_switch;
        private GuestSwitchNewBean guest_switch_new;
        private int huawei_switch;
        private HuaweiSwitchNewBean huawei_switch_new;
        private int line_switch;
        private int platform_switch;
        private PlatformSwitchNewBean platform_switch_new;
        private StoreNewBean store_new;
        private int twitter_swtich;

        public StoreNewBean getStore_new() {
            if (this.store_new == null) {
                this.store_new = new StoreNewBean();
            }
            return this.store_new;
        }

        public void setStore_new(StoreNewBean storeNewBean) {
            this.store_new = storeNewBean;
        }

        public GoogleSwitchNewBean getGoogle_swtich_new() {
            return this.google_swtich_new;
        }

        public void setGoogle_swtich_new(GoogleSwitchNewBean googleSwitchNewBean) {
            this.google_swtich_new = googleSwitchNewBean;
        }

        public FacebookSwitchNewBean getFacebook_switch_new() {
            return this.facebook_switch_new;
        }

        public void setFacebook_switch_new(FacebookSwitchNewBean facebookSwitchNewBean) {
            this.facebook_switch_new = facebookSwitchNewBean;
        }

        public GuestSwitchNewBean getGuest_switch_new() {
            return this.guest_switch_new;
        }

        public void setGuest_switch_new(GuestSwitchNewBean guestSwitchNewBean) {
            this.guest_switch_new = guestSwitchNewBean;
        }

        public HuaweiSwitchNewBean getHuawei_switch_new() {
            return this.huawei_switch_new;
        }

        public void setHuawei_switch_new(HuaweiSwitchNewBean huaweiSwitchNewBean) {
            this.huawei_switch_new = huaweiSwitchNewBean;
        }

        public PlatformSwitchNewBean getPlatform_switch_new() {
            return this.platform_switch_new;
        }

        public void setPlatform_switch_new(PlatformSwitchNewBean platformSwitchNewBean) {
            this.platform_switch_new = platformSwitchNewBean;
        }

        public int getHuawei_switch() {
            return this.huawei_switch;
        }

        public void setHuawei_switch(int i) {
            this.huawei_switch = i;
        }

        public int getGoogle_swtich() {
            return this.google_swtich;
        }

        public void setGoogle_swtich(int i) {
            this.google_swtich = i;
        }

        public int getTwitter_swtich() {
            return this.twitter_swtich;
        }

        public void setTwitter_swtich(int i) {
            this.twitter_swtich = i;
        }

        public int getLine_switch() {
            return this.line_switch;
        }

        public void setLine_switch(int i) {
            this.line_switch = i;
        }

        public int getPlatform_switch() {
            return this.platform_switch;
        }

        public void setPlatform_switch(int i) {
            this.platform_switch = i;
        }

        public int getFacebook_switch() {
            return this.facebook_switch;
        }

        public void setFacebook_switch(int i) {
            this.facebook_switch = i;
        }

        public int getGuest_switch() {
            return this.guest_switch;
        }

        public void setGuest_switch(int i) {
            this.guest_switch = i;
        }

        /* loaded from: classes.dex */
        public static class GoogleSwitchNewBean {
            private int sort;

            @SerializedName("switch")
            private int switchA;

            public int getSwitchA() {
                return this.switchA;
            }

            public void setSwitchA(int i) {
                this.switchA = i;
            }

            public int getSort() {
                return this.sort;
            }

            public void setSort(int i) {
                this.sort = i;
            }
        }

        /* loaded from: classes.dex */
        public static class FacebookSwitchNewBean {
            private int sort;

            @SerializedName("switch")
            private int switchA;

            public int getSwitchA() {
                return this.switchA;
            }

            public void setSwitchA(int i) {
                this.switchA = i;
            }

            public int getSort() {
                return this.sort;
            }

            public void setSort(int i) {
                this.sort = i;
            }
        }

        /* loaded from: classes.dex */
        public static class GuestSwitchNewBean {
            private int sort;

            @SerializedName("switch")
            private int switchA;

            public int getSwitchA() {
                return this.switchA;
            }

            public void setSwitchA(int i) {
                this.switchA = i;
            }

            public int getSort() {
                return this.sort;
            }

            public void setSort(int i) {
                this.sort = i;
            }
        }

        /* loaded from: classes.dex */
        public static class HuaweiSwitchNewBean {
            private int sort;

            @SerializedName("switch")
            private int switchA;

            public int getSwitchA() {
                return this.switchA;
            }

            public void setSwitchA(int i) {
                this.switchA = i;
            }

            public int getSort() {
                return this.sort;
            }

            public void setSort(int i) {
                this.sort = i;
            }
        }

        /* loaded from: classes.dex */
        public static class PlatformSwitchNewBean {
            private int sort;

            @SerializedName("switch")
            private int switchA;

            public int getSwitchA() {
                return this.switchA;
            }

            public void setSwitchA(int i) {
                this.switchA = i;
            }

            public int getSort() {
                return this.sort;
            }

            public void setSort(int i) {
                this.sort = i;
            }
        }

        /* loaded from: classes.dex */
        public static class StoreNewBean {
            private int sort;

            @SerializedName("switch")
            private int switchA;

            public int getSwitchA() {
                return this.switchA;
            }

            public void setSwitchA(int i) {
                this.switchA = i;
            }

            public int getSort() {
                return this.sort;
            }

            public void setSort(int i) {
                this.sort = i;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Customer_service_config {
        private int email_customer_service_switch;
        private int online_customer_service_switch;

        public int getEmail_customer_service_switch() {
            return this.email_customer_service_switch;
        }

        public void setEmail_customer_service_switch(int i) {
            this.email_customer_service_switch = i;
        }

        public int getOnline_customer_service_switch() {
            return this.online_customer_service_switch;
        }

        public void setOnline_customer_service_switch(int i) {
            this.online_customer_service_switch = i;
        }
    }
}
