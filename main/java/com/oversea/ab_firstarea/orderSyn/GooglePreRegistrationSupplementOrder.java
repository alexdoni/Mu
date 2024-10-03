package com.oversea.ab_firstarea.orderSyn;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.oversea.ab_firstarea.p012f.sdk.DealPreRegNotify;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes.dex */
public class GooglePreRegistrationSupplementOrder {
    private static final String TAG = "PreRegSupplementOrder";
    private static volatile GooglePreRegistrationSupplementOrder instance;
    private boolean isGooglePreReg;

    private GooglePreRegistrationSupplementOrder() {
    }

    public static GooglePreRegistrationSupplementOrder getInstance() {
        if (instance == null) {
            synchronized (GooglePreRegistrationSupplementOrder.class) {
                if (instance == null) {
                    instance = new GooglePreRegistrationSupplementOrder();
                }
            }
        }
        return instance;
    }

    public boolean isGooglePreReg() {
        return this.isGooglePreReg;
    }

    public void setGooglePreReg(boolean z) {
        this.isGooglePreReg = z;
    }

    public void createOrder(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LLog.v_noControl("createAndOrder serverId or roleId null");
            return;
        }
        PreRegistrationOrderInfo preRegistrationOrderInfo = new PreRegistrationOrderInfo("", "", "", "");
        preRegistrationOrderInfo.setServerId(str);
        preRegistrationOrderInfo.setRoleId(str2);
        preRegistrationOrderInfo.setServerName(str3);
        preRegistrationOrderInfo.setRoleName(str4);
        GooglePreRegistrationSupplementOrderManager.setDataToSP(Lxhw_XSDK.getInstance().getContext(), ComConstants.OVERSEA_GOOGLEPREREG + Lxhw_XUserInfo.getInstance().getSdkId() + "", preRegistrationOrderInfo);
    }

    public void updateOrder(PreRegistrationOrderInfo preRegistrationOrderInfo) {
        if (preRegistrationOrderInfo == null) {
            return;
        }
        GooglePreRegistrationSupplementOrderManager.setDataToSP(Lxhw_XSDK.getInstance().getContext(), ComConstants.OVERSEA_GOOGLEPREREG + Lxhw_XUserInfo.getInstance().getSdkId() + "", preRegistrationOrderInfo);
    }

    public boolean isOrderExist() {
        return getPreRegistrationOrderInfo() != null;
    }

    public boolean isOrderAssociation() {
        if (getPreRegistrationOrderInfo() != null) {
            return (getPreRegistrationOrderInfo() == null || TextUtils.isEmpty(getPreRegistrationOrderInfo().getSignature())) ? false : true;
        }
        LLog.v_noControl("getOrderBaseInfo() orderBaseInfo = null");
        return false;
    }

    public PreRegistrationOrderInfo getPreRegistrationOrderInfoBySignature(String str) {
        if (TextUtils.isEmpty(str)) {
            LLog.v_noControl("getPreRegistrationOrderInfoBySignature() signature = null");
            return null;
        }
        if (getPreRegistrationOrderInfo() == null) {
            LLog.v_noControl("getPreRegistrationOrderInfoBySignature() orderInfo = null");
            return null;
        }
        if (!getPreRegistrationOrderInfo().getSignature().equals(str)) {
            LLog.v_noControl("getPreRegistrationOrderInfoBySignature().getSignature() != signature");
            return null;
        }
        return getPreRegistrationOrderInfo();
    }

    public PreRegistrationOrderInfo getPreRegistrationOrderInfo() {
        return GooglePreRegistrationSupplementOrderManager.getDataFromSP(Lxhw_XSDK.getInstance().getContext(), ComConstants.OVERSEA_GOOGLEPREREG + Lxhw_XUserInfo.getInstance().getSdkId() + "");
    }

    /* loaded from: classes.dex */
    public static class OrderTask extends AsyncTask<String, Void, String> {
        private String purchaseToken;
        private String roleId;
        private String roleName;
        private String serverId;
        private String serverName;
        private String signature;
        private String signedData;
        private String type;
        private String url = "";
        private Context context = Lxhw_XSDK.getInstance().getContext();

        public OrderTask(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
            this.type = str;
            this.signedData = str2;
            this.signature = str3;
            this.purchaseToken = str4;
            this.roleId = str5;
            this.roleName = str6;
            this.serverId = str7;
            this.serverName = str8;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(String... strArr) {
            Log.i(GooglePreRegistrationSupplementOrder.TAG, "doInBackground notyfy order end");
            try {
                new DealPreRegNotify(Lxhw_XSDK.getInstance().getContext()).payNotify(this.type, this.signedData, this.signature, this.purchaseToken, this.roleId, this.roleName, this.serverId, this.serverName);
                return Constants.SDK_LOGIN_TYPE_LOGOUT;
            } catch (Exception e) {
                e.printStackTrace();
                return Constants.SDK_LOGIN_TYPE_LOGOUT;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            super.onPostExecute((OrderTask) str);
        }
    }

    public void preRegRewardNotify(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        new OrderTask(ComConstants.SDK_PRE_REG_REWARD_NOTIFY, str, str2, str3, str4, str5, str6, str7).execute(new String[0]);
    }
}
