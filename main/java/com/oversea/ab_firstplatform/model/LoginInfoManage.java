package com.oversea.ab_firstplatform.model;

import android.content.Context;
import android.text.TextUtils;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.gson.Gson;
import com.xsdk.ab_firstbase.gson.reflect.TypeToken;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class LoginInfoManage {
    private static LoginInfoManage mInstance;
    private List<LoginInfo> loginInfos = null;

    public static LoginInfoManage getInstance() {
        if (mInstance == null) {
            mInstance = new LoginInfoManage();
        }
        return mInstance;
    }

    public List<LoginInfo> getLoginInfos(Context context) {
        List<LoginInfo> list = this.loginInfos;
        if (list == null || list.size() == 0) {
            String readAccount = ImageUtils.readAccount(context);
            if (!TextUtils.isEmpty(readAccount)) {
                this.loginInfos = (List) new Gson().fromJson(readAccount, new TypeToken<List<LoginInfo>>() { // from class: com.oversea.ab_firstplatform.model.LoginInfoManage.1
                }.getType());
            }
        }
        if (this.loginInfos == null) {
            this.loginInfos = new ArrayList();
        }
        return this.loginInfos;
    }

    public void setLoginInfos(Context context, List<LoginInfo> list) {
        this.loginInfos = list;
        ImageUtils.writeAccount(context, new Gson().toJson(this.loginInfos));
    }

    public void addLoginInfos(Context context, LoginInfo loginInfo) {
        List<LoginInfo> list = this.loginInfos;
        if (list != null) {
            list.add(0, loginInfo);
        }
        ImageUtils.writeAccount(context, new Gson().toJson(this.loginInfos));
    }

    public void updataLoginInfos(Context context, LoginInfo loginInfo) {
        List<LoginInfo> list = this.loginInfos;
        if (list != null) {
            if (list.size() > 0) {
                boolean z = false;
                for (int i = 0; i < this.loginInfos.size(); i++) {
                    if (this.loginInfos.get(i).getU().equals(loginInfo.getU())) {
                        this.loginInfos.get(i).setP(loginInfo.getP());
                        z = true;
                    }
                }
                if (!z) {
                    this.loginInfos.add(0, loginInfo);
                }
            } else {
                this.loginInfos.add(loginInfo);
            }
        }
        ImageUtils.writeAccount(context, new Gson().toJson(this.loginInfos));
    }

    public void isVisitorAccountModify(Context context, LoginInfo loginInfo) {
        String stringKeyForValue = ImageUtils.getStringKeyForValue(context, ComConstants.SDK_VISITOR_ACCOUNT);
        if (loginInfo == null || TextUtils.isEmpty(stringKeyForValue) || !loginInfo.getU().equals(stringKeyForValue)) {
            return;
        }
        ImageUtils.setSharePreferences(context, ComConstants.SDK_VISITOR_ACCOUNT, loginInfo.getU());
        ImageUtils.setSharePreferences(context, ComConstants.SDK_VISITOR_PASSWORD, loginInfo.getP());
    }

    public void updataLoginInfosAccountAndPwd(Context context, String str, LoginInfo loginInfo) {
        if (this.loginInfos != null) {
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= this.loginInfos.size()) {
                    break;
                }
                if (this.loginInfos.get(i).getU().equals(str)) {
                    this.loginInfos.get(i).setU(loginInfo.getU());
                    this.loginInfos.get(i).setP(loginInfo.getP());
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                updataLoginInfos(context, loginInfo);
            }
        }
        ImageUtils.writeAccount(context, new Gson().toJson(this.loginInfos));
    }

    public static LoginInfo getLastLoginInfo(Context context, List<LoginInfo> list) {
        LoginInfo loginInfo = new LoginInfo();
        String stringKeyForValue = ImageUtils.getStringKeyForValue(context, Constants.SDK_ACCOUNT);
        String stringKeyForValue2 = ImageUtils.getStringKeyForValue(context, Constants.SDK_PASSWORD);
        if (TextUtils.isEmpty(stringKeyForValue)) {
            return (list == null || list.size() <= 0) ? loginInfo : list.get(list.size() - 1);
        }
        loginInfo.setU(stringKeyForValue);
        loginInfo.setP(stringKeyForValue2);
        return loginInfo;
    }

    public void delAccount(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        List<LoginInfo> loginInfos = getLoginInfos(context);
        ArrayList arrayList = new ArrayList();
        for (LoginInfo loginInfo : loginInfos) {
            if (loginInfo.getU().equals(str) && loginInfo.getP().equals(str2)) {
                arrayList.add(loginInfo);
            }
        }
        if (loginInfos.size() == 0) {
            return;
        }
        loginInfos.removeAll(arrayList);
        setLoginInfos(context, loginInfos);
    }
}
