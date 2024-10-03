package com.mu.data;

import com.facebook.internal.NativeProtocol;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MUPayData {
    public String amount;
    public String app_Ext1;
    public String app_Ext2;
    public String app_User_Id;
    public String app_name;
    public String app_order_Id;
    public String app_user_Name;
    public String balance;
    public long createTime;
    public String extToken;
    public String game_Role_Id;
    public String gangName;
    public String notify_Uri;
    public String product_Id;
    public String product_desc;
    public String product_name;
    public String roleLevel;
    public String serverName;
    public String sid;
    public String sign;
    public String vipLevel;

    public static MUPayData ParsePayData(String str) {
        MUPayData mUPayData = new MUPayData();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("app_User_Id")) {
                mUPayData.app_User_Id = jSONObject.getString("app_User_Id");
            }
            if (jSONObject.has("game_Role_Id")) {
                mUPayData.game_Role_Id = jSONObject.getString("game_Role_Id");
            }
            if (jSONObject.has("app_user_Name")) {
                mUPayData.app_user_Name = jSONObject.getString("app_user_Name");
            }
            if (jSONObject.has("notify_Uri")) {
                mUPayData.notify_Uri = jSONObject.getString("notify_Uri");
            }
            if (jSONObject.has("amount")) {
                mUPayData.amount = jSONObject.getString("amount");
            }
            if (jSONObject.has("app_Ext1")) {
                mUPayData.app_Ext1 = jSONObject.getString("app_Ext1");
            }
            if (jSONObject.has("app_Ext2")) {
                mUPayData.app_Ext2 = jSONObject.getString("app_Ext2");
            }
            if (jSONObject.has(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING)) {
                mUPayData.app_name = jSONObject.getString(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING);
            }
            if (jSONObject.has("app_order_Id")) {
                mUPayData.app_order_Id = jSONObject.getString("app_order_Id");
            }
            if (jSONObject.has("product_Id")) {
                mUPayData.product_Id = jSONObject.getString("product_Id");
            }
            if (jSONObject.has("sid")) {
                mUPayData.sid = jSONObject.getString("sid");
            }
            if (jSONObject.has("serverName")) {
                mUPayData.serverName = jSONObject.getString("serverName");
            }
            if (jSONObject.has("product_name")) {
                mUPayData.product_name = jSONObject.getString("product_name");
            }
            if (jSONObject.has("product_desc")) {
                mUPayData.product_desc = jSONObject.getString("product_desc");
            }
            if (jSONObject.has("vipLevel")) {
                mUPayData.vipLevel = jSONObject.getString("vipLevel");
            }
            if (jSONObject.has("roleLevel")) {
                mUPayData.roleLevel = jSONObject.getString("roleLevel");
            }
            if (jSONObject.has("gangName")) {
                mUPayData.gangName = jSONObject.getString("gangName");
            }
            if (jSONObject.has("createTime")) {
                mUPayData.createTime = jSONObject.getLong("createTime");
            }
            if (jSONObject.has("balance")) {
                mUPayData.balance = jSONObject.getString("balance");
            }
            if (jSONObject.has("sign")) {
                mUPayData.sign = jSONObject.getString("sign");
            }
            if (jSONObject.has("extToken")) {
                mUPayData.extToken = jSONObject.getString("extToken");
            }
        } catch (JSONException unused) {
        }
        return mUPayData;
    }
}
