package com.oversea.ab_firstarea.orderSyn;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xsdk.ab_firstbase.gson.Gson;
import com.xsdk.ab_firstbase.gson.reflect.TypeToken;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class BudanOrderListManage {
    private static String fileName = "overssdkorderjson.xml";

    public static List<OrderBaseInfo> getStringKeyForList(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            String string = context.getSharedPreferences(fileName, 0).getString(str, "");
            return !TextUtils.isEmpty(string) ? (List) new Gson().fromJson(string, new TypeToken<List<OrderBaseInfo>>() { // from class: com.oversea.ab_firstarea.orderSyn.BudanOrderListManage.1
            }.getType()) : arrayList;
        } catch (Throwable th) {
            LLog.e_noControl("getStringKeyForList e=" + th.toString());
            return arrayList;
        }
    }

    public static void setSharePreferencesList(Context context, String str, List<OrderBaseInfo> list) {
        if (context == null) {
            return;
        }
        try {
            String json = new Gson().toJson(list);
            SharedPreferences.Editor edit = context.getSharedPreferences(fileName, 0).edit();
            edit.clear();
            edit.putString(str, json);
            edit.commit();
        } catch (Throwable th) {
            LLog.e_noControl("setSharePreferencesList e=" + th.toString());
        }
    }

    public static OrderBaseInfo getStringKeyForResultOPR(Context context, String str) {
        String string = context.getSharedPreferences(fileName, 0).getString(str, "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return (OrderBaseInfo) new Gson().fromJson(string, new TypeToken<OrderBaseInfo>() { // from class: com.oversea.ab_firstarea.orderSyn.BudanOrderListManage.2
        }.getType());
    }

    public static void setSharePreferencesResultOPR(Context context, String str, OrderBaseInfo orderBaseInfo) {
        if (context == null) {
            return;
        }
        String json = new Gson().toJson(orderBaseInfo);
        SharedPreferences.Editor edit = context.getSharedPreferences(fileName, 0).edit();
        edit.clear();
        edit.putString(str, json);
        edit.commit();
    }
}
