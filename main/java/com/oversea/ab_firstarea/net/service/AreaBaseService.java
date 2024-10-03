package com.oversea.ab_firstarea.net.service;

import android.text.TextUtils;
import com.oversea.ab_firstarea.channel.PTypeUrl;
import com.oversea.ab_firstarea.channel.ProjectType;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes.dex */
public class AreaBaseService {
    public static String ACLOUDSTS = "";
    public static final String ACLOUDSTS_ROUTE = "/common/alibaba_cloud/sts";
    public static final String AD_UPLOAD_ROUTE = "/cashout/income/log";
    public static String AD_UPLOAD_URL = "";
    public static String BASE_URL = "";
    public static final String BINDEMAILSENDCODE_ROUTE = "/common/user/bind_email_send_code";
    public static String BINDEMAILSENDCODE_URL = "";
    public static final String BINDEMAIL_ROUTE = "/common/user/bind_email";
    public static String BINDEMAIL_URL = "";
    public static final String BINDPHONESENDCODE_ROUTE = "/common/user/bind_phone_send_code";
    public static String BINDPHONESENDCODE_URL = "";
    public static final String BINDPHONE_ROUTE = "/common/user/bind_phone";
    public static String BINDPHONE_URL = "";
    public static final String CHANGEPASSWORDBYEMAIL_ROUTE = "/common/user/change_password_by_email";
    public static String CHANGEPASSWORDBYEMAIL_URL = "";
    public static final String CHANGEPASSWORDBYPHONE_ROUTE = "/common/user/change_password_by_phone";
    public static String CHANGEPASSWORDBYPHONE_URL = "";
    public static final String CHANGEPASSWORDEMAILCHECKCODE_ROUTE = "/common/user/change_password_by_email_check_code";
    public static String CHANGEPASSWORDEMAILCHECKCODE_URL = "";
    public static final String CHANGEPASSWORDEMAILSENDCODE_ROUTE = "/common/user/change_password_by_email_send_code";
    public static String CHANGEPASSWORDEMAILSENDCODE_URL = "";
    public static final String CHANGEPASSWORDPHONECHECKCODE_ROUTE = "/common/user/change_password_by_phone_check_code";
    public static String CHANGEPASSWORDPHONECHECKCODE_URL = "";
    public static final String CHANGEPASSWORDPHONESENDCODE_ROUTE = "/common/user/change_password_by_phone_send_code";
    public static String CHANGEPASSWORDPHONESENDCODE_URL = "";
    public static final String CHANGEPASSWORD_ROUTE = "/common/user/change_password";
    public static String CHANGEPASSWORD_URL = "";
    public static String COMMON_URL = "";
    public static String COSSTS = "";
    public static final String COSSTS_ROUTE = "/common/tencent_cloud/sts";
    public static final String CUSTOMERBASEINFO_ROUTE = "/common/customer_service/base_info";
    public static String CUSTOMERBASEINFO_URL = "";
    public static final String CUSTOMERISSUECOMMIT_ROUTE = "/common/customer_service/issue_commit";
    public static String CUSTOMERISSUECOMMIT_URL = "";
    public static final String CUSTOMERISSUELIST_ROUTE = "/common/customer_service/issue_list";
    public static String CUSTOMERISSUELIST_URL = "";
    public static final String CUSTOMERISSUEMSGLISTSEND_ROUTE = "/common/customer_service/issue_msg_send";
    public static String CUSTOMERISSUEMSGLISTSEND_URL = "";
    public static final String CUSTOMERISSUEMSGLIST_ROUTE = "/common/customer_service/issue_msg_list";
    public static String CUSTOMERISSUEMSGLIST_URL = "";
    public static final String CUSTOMERISSUETYPELIST_ROUTE = "/common/customer_service/issue_type_list";
    public static String CUSTOMERISSUETYPELIST_URL = "";
    public static String DEL_ACCOUNT_ROUTE = "/common/user/game_user_delete";
    public static String DEL_ACCOUNT_URL = "";
    public static final String EVENT_ROUTE = "/event_report";
    public static final String FIREBASEUPDATETOKEN_ROUTE = "/common/firebase/update_token";
    public static String FIREBASEUPDATETOKEN_URL = "";
    public static final String GIFTCODEGETRECORDLIST_ROUTE = "/common/gift/gift_code_get_record_list";
    public static String GIFTCODEGETRECORDLIST_URL = "";
    public static final String GIFTCODEGET_ROUTE = "/common/gift/gift_code_get";
    public static String GIFTCODEGET_URL = "";
    public static final String GIFTLIST_ROUTE = "/common/gift/gift_list";
    public static String GIFTLIST_URL = "";
    public static final String GOOGLE_INTEGRITY_ROUTE = "/common/google/decode_integrity_token";
    public static String GOOGLE_INTEGRITY_URL = "";
    public static String GOOGLE_PRE_REGISTRATION_REWARD_ROUTE = "/common/google/pre_registration_reward";
    public static String GOOGLE_PRE_REGISTRATION_REWARD_URL = "";
    public static final String GOOGLE_RECAPTCHA_ROUTE = "/common/google/recaptcha";
    public static String GOOGLE_RECAPTCHA_URL = "";
    public static final String GUEST_ROUTE = "/common/user/guest_login";
    public static String GUEST_URL = "";
    public static final String HTTPS = "https://";
    public static final String INIT_ROUTE = "/common/init/get";
    public static String INIT_URL = "";
    public static final String LOGIN_ROUTE = "/common/user/login";
    public static String LOGIN_URL = "";
    public static final String LOGOUT_ROUTE = "/common/user/logout";
    public static String LOGOUT_URL = "";
    public static String PAYINIT = "";
    public static final String PAYINIT_ROUTE = "/pay_for_sdk/pay/init";
    public static String PAYNOTIFY = "";
    public static String PAYNOTIFYNOORDER = "";
    public static final String PAYNOTIFYNOORDER_ROUTE = "/pay_for_sdk/notify/google/resend_notify";
    public static final String PAYNOTIFY_ROUTE = "/pay_for_sdk/notify/pay_notify";
    public static String PAYORDERCREATE = "";
    public static final String PAYORDERCREATE_ROUTE = "/pay_for_sdk/order/pay_order_create";
    public static String PAYORDER_MYCARD_CREATE = "";
    public static final String PAYORDER_MYCARD_CREATE_ROUTE = "/pay_for_sdk/order/pay_order_mycard_ingame_pay";
    public static String PAYPLATFORMURL = "";
    public static String PAYPLATFORM_ROUTE = "/p/login";
    public static String PAYQUERY = "";
    public static final String PAY_QUERY_ROUTE = "/pay_for_sdk/order/query";
    public static String PAY_STATUE = "";
    public static String PRODUCTID_LIST = "";
    public static final String PRODUCTID_LIST_ROUTE = "/common/product/product_list";
    public static final String REGISTER_ROUTE = "/common/user/register";
    public static String REGISTER_URL = "";
    public static String ROLLBACK_ACCOUNT_ROUTE = "/common/user/game_user_delete_cancel";
    public static String ROLLBACK_ACCOUNT_URL = "";
    public static String SURVEYURL = "";
    public static final String THIRD_LOGIN_ROUTE = "/common/user/third_login";
    public static String THIRD_LOGIN_URL = "";
    public static final String TOKEN_LOGIN_ROUTE = "/common/user/refresh";
    public static String TOKEN_LOGIN_URL = "";
    public static String TRANSLATEURL = "";
    public static String TRANSLATE_ROUTE = "/utils/translate";
    public static final String UNBINDEMAILSENDCODE_ROUTE = "/common/user/unbind_email_send_code";
    public static String UNBINDEMAILSENDCODE_URL = "";
    public static final String UNBINDEMAIL_ROUTE = "/common/user/unbind_email";
    public static String UNBINDEMAIL_URL = "";
    public static final String UNBINDPHONESENDCODE_ROUTE = "/common/user/unbind_phone_send_code";
    public static String UNBINDPHONESENDCODE_URL = "";
    public static final String UNBINDPHONE_ROUTE = "/common/user/unbind_phone";
    public static String UNBINDPHONE_URL = "";
    public static final String UPGRADEACCOUNT_ROUTE = "/common/user/upgrade_account";
    public static String UPGRADEACCOUNT_URL = "";
    public static final String USERINFO_ROUTE = "/common/user/user_info";
    public static String USERINFO_URL = "";
    public static final String USER_BIND_INFO_ROUTE = "/common/user/user_bind_info";
    public static String USER_BIND_INFO_URL = "";
    public static String USER_INFO_CERTIFCATION_VI_ROUTE = "/common/user/user_info_certification";
    public static String USER_INFO_CERTIFCATION_VI_URL = "";
    public static String EVENTURL = PTypeUrl.EVENT_REPORT_URL + "/event_report?appv=1.0&counter=dau";
    public static final String DATAREPORT_ROUTE = "/common/data_report/report";
    public static String DATAREPORT_URL = PTypeUrl.EVENT_REPORT_URL + DATAREPORT_ROUTE;

    public static void setUrl(String str) {
        COMMON_URL = str;
        if (!str.contains("http")) {
            COMMON_URL = HTTPS + str;
        }
        INIT_URL = COMMON_URL + INIT_ROUTE;
        REGISTER_URL = COMMON_URL + REGISTER_ROUTE;
        LOGIN_URL = COMMON_URL + LOGIN_ROUTE;
        GUEST_URL = COMMON_URL + GUEST_ROUTE;
        THIRD_LOGIN_URL = COMMON_URL + THIRD_LOGIN_ROUTE;
        USER_BIND_INFO_URL = COMMON_URL + USER_BIND_INFO_ROUTE;
        CHANGEPASSWORDPHONESENDCODE_URL = COMMON_URL + CHANGEPASSWORDPHONESENDCODE_ROUTE;
        CHANGEPASSWORDPHONECHECKCODE_URL = COMMON_URL + CHANGEPASSWORDPHONECHECKCODE_ROUTE;
        CHANGEPASSWORDBYPHONE_URL = COMMON_URL + CHANGEPASSWORDBYPHONE_ROUTE;
        CHANGEPASSWORDEMAILSENDCODE_URL = COMMON_URL + CHANGEPASSWORDEMAILSENDCODE_ROUTE;
        CHANGEPASSWORDEMAILCHECKCODE_URL = COMMON_URL + CHANGEPASSWORDEMAILCHECKCODE_ROUTE;
        CHANGEPASSWORDBYEMAIL_URL = COMMON_URL + CHANGEPASSWORDBYEMAIL_ROUTE;
        LOGOUT_URL = COMMON_URL + LOGOUT_ROUTE;
        USERINFO_URL = COMMON_URL + USERINFO_ROUTE;
        CHANGEPASSWORD_URL = COMMON_URL + CHANGEPASSWORD_ROUTE;
        BINDPHONESENDCODE_URL = COMMON_URL + BINDPHONESENDCODE_ROUTE;
        BINDPHONE_URL = COMMON_URL + BINDPHONE_ROUTE;
        UNBINDPHONESENDCODE_URL = COMMON_URL + UNBINDPHONESENDCODE_ROUTE;
        UNBINDPHONE_URL = COMMON_URL + UNBINDPHONE_ROUTE;
        BINDEMAILSENDCODE_URL = COMMON_URL + BINDEMAILSENDCODE_ROUTE;
        BINDEMAIL_URL = COMMON_URL + BINDEMAIL_ROUTE;
        UNBINDEMAILSENDCODE_URL = COMMON_URL + UNBINDEMAILSENDCODE_ROUTE;
        UNBINDEMAIL_URL = COMMON_URL + UNBINDEMAIL_ROUTE;
        UPGRADEACCOUNT_URL = COMMON_URL + UPGRADEACCOUNT_ROUTE;
        CUSTOMERBASEINFO_URL = COMMON_URL + CUSTOMERBASEINFO_ROUTE;
        CUSTOMERISSUETYPELIST_URL = COMMON_URL + CUSTOMERISSUETYPELIST_ROUTE;
        CUSTOMERISSUELIST_URL = COMMON_URL + CUSTOMERISSUELIST_ROUTE;
        CUSTOMERISSUECOMMIT_URL = COMMON_URL + CUSTOMERISSUECOMMIT_ROUTE;
        CUSTOMERISSUEMSGLIST_URL = COMMON_URL + CUSTOMERISSUEMSGLIST_ROUTE;
        CUSTOMERISSUEMSGLISTSEND_URL = COMMON_URL + CUSTOMERISSUEMSGLISTSEND_ROUTE;
        GIFTLIST_URL = COMMON_URL + GIFTLIST_ROUTE;
        GIFTCODEGET_URL = COMMON_URL + GIFTCODEGET_ROUTE;
        GIFTCODEGETRECORDLIST_URL = COMMON_URL + GIFTCODEGETRECORDLIST_ROUTE;
        ACLOUDSTS = COMMON_URL + ACLOUDSTS_ROUTE;
        COSSTS = COMMON_URL + COSSTS_ROUTE;
        FIREBASEUPDATETOKEN_URL = COMMON_URL + FIREBASEUPDATETOKEN_ROUTE;
        GOOGLE_INTEGRITY_URL = COMMON_URL + GOOGLE_INTEGRITY_ROUTE;
        GOOGLE_RECAPTCHA_URL = COMMON_URL + GOOGLE_RECAPTCHA_ROUTE;
        PRODUCTID_LIST = COMMON_URL + PRODUCTID_LIST_ROUTE;
        TRANSLATEURL = COMMON_URL + TRANSLATE_ROUTE;
        USER_INFO_CERTIFCATION_VI_URL = COMMON_URL + USER_INFO_CERTIFCATION_VI_ROUTE;
        DEL_ACCOUNT_URL = COMMON_URL + DEL_ACCOUNT_ROUTE;
        ROLLBACK_ACCOUNT_URL = COMMON_URL + ROLLBACK_ACCOUNT_ROUTE;
        GOOGLE_PRE_REGISTRATION_REWARD_URL = COMMON_URL + GOOGLE_PRE_REGISTRATION_REWARD_ROUTE;
        TOKEN_LOGIN_URL = COMMON_URL + TOKEN_LOGIN_ROUTE;
    }

    public static void setPayUrl(String str) {
        if (ProjectType.TEST.equals(ProjectType.pType) || ProjectType.ZIAN.equals(ProjectType.pType)) {
            str = PTypeUrl.curGPUrl;
        }
        if (!str.contains("http")) {
            str = HTTPS + str;
        }
        PAYINIT = str + PAYINIT_ROUTE;
        PAYORDERCREATE = str + PAYORDERCREATE_ROUTE;
        PAYORDER_MYCARD_CREATE = str + PAYORDER_MYCARD_CREATE_ROUTE;
        PAYNOTIFY = str + PAYNOTIFY_ROUTE;
        PAYNOTIFYNOORDER = str + PAYNOTIFYNOORDER_ROUTE;
        PAYQUERY = str + PAY_QUERY_ROUTE;
    }

    public static void setPlatformPayUrl(String str) {
        if (!str.contains("http")) {
            str = HTTPS + str;
        }
        PAYPLATFORMURL = str + PAYPLATFORM_ROUTE;
        if (ProjectType.TEST.equals(ProjectType.pType) || ProjectType.ZIAN.equals(ProjectType.pType)) {
            PAYPLATFORMURL = PTypeUrl.curWPUrl + PAYPLATFORM_ROUTE;
            StringBuilder sb = new StringBuilder("PAYPLATFORMURL=");
            sb.append(PAYPLATFORMURL);
            LLog.v_noControl(sb.toString());
        }
    }

    public static void setSurveyUrl(String str) {
        if (!str.contains("http")) {
            str = HTTPS + str;
        }
        SURVEYURL = str;
    }

    public static void setApiLogsUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!str.contains("http")) {
            str = HTTPS + str;
        }
        EVENTURL = str + "/event_report?appv=1.0&counter=dau";
        DATAREPORT_URL = str + DATAREPORT_ROUTE;
        AD_UPLOAD_URL = str + AD_UPLOAD_ROUTE;
    }
}
