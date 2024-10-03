package com.oversea.ab_firstarea.orderSyn;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xsdk.ab_firstbase.gson.Gson;
import com.xsdk.ab_firstbase.gson.reflect.TypeToken;

/* loaded from: classes.dex */
public class GooglePreRegistrationSupplementOrderManager {
    private static String fileName = "overssdkpreregistrationorderjson";

    public static PreRegistrationOrderInfo getDataFromSP(Context context, String str) {
        String string = context.getSharedPreferences(fileName, 0).getString(str, "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return (PreRegistrationOrderInfo) new Gson().fromJson(string, new TypeToken<PreRegistrationOrderInfo>() { // from class: com.oversea.ab_firstarea.orderSyn.GooglePreRegistrationSupplementOrderManager.1
        }.getType());
    }

    public static void setDataToSP(Context context, String str, OrderBaseInfo orderBaseInfo) {
        if (context == null || orderBaseInfo == null) {
            return;
        }
        String json = new Gson().toJson(orderBaseInfo);
        SharedPreferences.Editor edit = context.getSharedPreferences(fileName, 0).edit();
        edit.clear();
        edit.putString(str, json);
        edit.commit();
    }
}
