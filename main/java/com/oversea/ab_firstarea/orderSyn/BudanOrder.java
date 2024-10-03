package com.oversea.ab_firstarea.orderSyn;

import android.content.Context;
import android.os.AsyncTask;
import com.oversea.ab_firstarea.p012f.sdk.DealPayNotify;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes.dex */
public class BudanOrder {

    /* loaded from: classes.dex */
    public static class OrderTask extends AsyncTask<String, Void, String> {
        private String order;
        private String purchaseToken;
        private String roleId;
        private String serverId;
        private String signature;
        private String signedData;
        private String type;
        private String uid;
        private String url = "";
        private Context context = Lxhw_XSDK.getInstance().getContext();

        public OrderTask(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
            this.type = str;
            this.order = str2;
            this.uid = str3;
            this.serverId = str4;
            this.roleId = str5;
            this.signedData = str6;
            this.signature = str7;
            this.purchaseToken = str8;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(String... strArr) {
            LLog.i_Control("OrderTask yanzhegn pay end,change paystute to end");
            try {
                DealPayNotify dealPayNotify = new DealPayNotify(Lxhw_XSDK.getInstance().getContext());
                if (Lxhw_AreaPlatform.getInstance().iTestPay) {
                    if (Lxhw_AreaPlatform.getInstance().iTestPay_fahuo) {
                        dealPayNotify.setiTest(0);
                    } else {
                        dealPayNotify.setiTest(1);
                    }
                } else {
                    dealPayNotify.setiTest(0);
                }
                dealPayNotify.payNotify(this.type, this.order, this.uid, this.serverId, this.roleId, this.signedData, this.signature, this.purchaseToken, null);
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
}
