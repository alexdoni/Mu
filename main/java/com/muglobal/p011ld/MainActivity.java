package com.muglobal.p011ld;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.ArrayMap;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mu.MainActivityBase;
import com.mu.data.MUPayData;
import com.mu.data.MuGameData;
import com.mu.update.ConfigInfo;
import com.mu.update.HotUpdateMgr;
import com.mu.utility.MuDebug;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.init.Lxhw_AccountUpgradeCallback;
import com.oversea.ab_firstplatform.init.Lxhw_SDKCallBack;
import com.oversea.ab_firstplatform.init.Lxhw_TranslateCallback;
import com.oversea.ab_firstplatform.model.TSBean;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserExtraData;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;
import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;
import com.xsdk.ab_firstbase.statisics.util.languagelib.MultiLanguageUtil;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MainActivity extends MainActivityBase {
    private String Language = LanguageType.SERVER_EN;
    String index;
    private MainActivity mainActivity;

    @Override // com.mu.MainActivityBase
    public boolean IsNeedRuntimeUpdate() {
        return false;
    }

    @Override // com.mu.MainActivityBase
    public int IsSDKDevelopApk() {
        return 1;
    }

    @Override // com.mu.MainActivityBase
    public String getPlatformID() {
        return null;
    }

    @Override // com.mu.MainActivityBase
    public int isSpecialLoadSkin() {
        return 0;
    }

    @Override // com.mu.MainActivityBase
    public void login(String str) {
    }

    @Override // com.mu.MainActivityBase
    public void initOnCreate(Bundle bundle) {
        this.mainActivity = this;
        Lxhw_Platform.getInstance().initSdk(this.mainActivity, new Lxhw_SDKCallBack() { // from class: com.muglobal.ld.MainActivity.1
            @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKCallBack
            public void onDianzanResult(boolean z) {
            }

            @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKCallBack
            public void onInitResult(boolean z) {
            }

            @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKCallBack
            public void onLoginFail() {
            }

            @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKCallBack
            public void onPayResult(int i, String str) {
            }

            @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKCallBack
            public void onPingFenResult(boolean z) {
            }

            @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKCallBack
            public void onShareResult(boolean z) {
            }

            @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKCallBack
            public void onSwitchAccount() {
                MainActivity.this.sendCallback(MainActivityBase.CALLBACK_LOGOUT, "");
                Log.i("TAG", "onSwitchAccount ");
            }

            @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKCallBack
            public void onBindResult(boolean z) {
                Log.i("TAG", "onBindResult b=" + z);
                if (z) {
                    Log.i("TAG", "绑定成功 b=" + z);
                    return;
                }
                Log.i("TAG", "绑定失败");
            }

            @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKCallBack
            public void onLoginResult(Lxhw_XUserInfo lxhw_XUserInfo) {
                Log.i("TAG", "onLoginResult success");
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("userid", lxhw_XUserInfo.getUid());
                    jSONObject.put("token", lxhw_XUserInfo.getToken());
                    jSONObject.put("time", System.currentTimeMillis());
                    jSONObject.put("pid", ConfigInfo.pId);
                    jSONObject.put("gid", ConfigInfo.SDKGameID);
                    jSONObject.put("opName", ConfigInfo.opName);
                } catch (Exception e) {
                    Log.e("onLoginResult Error: ", e.toString());
                }
                MainActivity.this.sendCallback(MainActivityBase.CALLBACK_LOGIN, jSONObject.toString());
            }
        });
        Lxhw_Platform.getInstance().addAccountUpgradeListener(new Lxhw_AccountUpgradeCallback() { // from class: com.muglobal.ld.MainActivity.2
            @Override // com.oversea.ab_firstplatform.init.Lxhw_AccountUpgradeCallback
            public void result() {
                Lxhw_Platform.getInstance().login(MainActivity.this.mainActivity);
                Log.i("level up:", "帐号升级成功");
                MainActivity.this.sendCallback(MainActivityBase.CALLBACK_OPENQUITWND, "");
            }
        });
    }

    @Override // com.mu.MainActivityBase
    public void login() {
        Lxhw_Platform.getInstance().login(this.mainActivity);
    }

    public void SubmitDotData(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put(jSONObject.get("key").toString(), jSONObject.get("value"));
            Log.i("TAG", jSONObject.get("key").toString() + "     " + jSONObject.get("value"));
            Lxhw_Platform.getInstance().onTrackEventAF(this.mainActivity, jSONObject.get("key").toString(), arrayMap);
        } catch (JSONException unused) {
        }
    }

    public void fbShareBtnClick() {
        Lxhw_Platform.getInstance().fbShare(this);
    }

    public void fbLikeBtnClick() {
        Lxhw_Platform.getInstance().dianzan(this);
    }

    public void appReviewBtnClick() {
        Lxhw_Platform.getInstance().openGooglePlayInappReview(this);
    }

    public void showSurveyBtnClick() {
        Lxhw_AreaPlatform.getInstance().showSurveyViewController();
    }

    public void discordBtnClick() {
        Lxhw_Platform.getInstance().openDiscord(this);
    }

    public void getTranslateResult(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.index = jSONObject.get(FirebaseAnalytics.Param.INDEX).toString();
            String obj = jSONObject.get("sourceLanguage").toString();
            String obj2 = jSONObject.get("nowLanguage").toString();
            this.Language = obj2;
            if (obj2.isEmpty()) {
                this.Language = LanguageType.SERVER_EN;
            } else {
                Log.i("TAG", this.Language);
            }
            Log.i("TAg", obj);
            Lxhw_AreaPlatform.getInstance().getTranslateResult(obj, this.Language, this.index, new Lxhw_TranslateCallback() { // from class: com.muglobal.ld.MainActivity.3
                @Override // com.oversea.ab_firstplatform.init.Lxhw_TranslateCallback
                public void result(TSBean tSBean) {
                    if (tSBean == null || !tSBean.isResult()) {
                        return;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(FirebaseAnalytics.Param.INDEX, MainActivity.this.index);
                        jSONObject2.put("translateLanguage", tSBean.getTranslatedText());
                        jSONObject2.put("sourceLanguage", tSBean.getSourceText());
                        Log.i("TAg getTranslatedText", tSBean.getTranslatedText());
                        Log.i("TAg getSourceText", tSBean.getSourceText());
                        Log.i("TAg", MainActivity.this.index);
                        MainActivity.this.sendCallback(MainActivityBase.CALLBACK_TRANSLATERESULT, jSONObject2.toString());
                        MainActivity.this.index = "";
                    } catch (JSONException unused) {
                    }
                }
            });
        } catch (JSONException unused) {
        }
    }

    public void showCustomerService() {
        Lxhw_AreaPlatform.getInstance().showCustomerService(this.mainActivity);
    }

    @Override // com.mu.MainActivityBase
    public void pay(String str) {
        int i;
        MUPayData ParsePayData = MUPayData.ParsePayData(str);
        Lxhw_PayParams lxhw_PayParams = new Lxhw_PayParams();
        lxhw_PayParams.setCpOrderID(ParsePayData.app_order_Id);
        lxhw_PayParams.setProduct_type(Lxhw_PayParams.ProductType.COIN);
        float f = 0.0f;
        try {
            f = Float.parseFloat(ParsePayData.amount);
            Log.i(FirebaseAnalytics.Param.PRICE, f + "");
        } catch (NumberFormatException unused) {
        }
        lxhw_PayParams.setAmount(f);
        lxhw_PayParams.setProductId(ParsePayData.product_Id);
        Log.i("product_Id：", ParsePayData.product_Id + "");
        lxhw_PayParams.setProductName(ParsePayData.product_name);
        lxhw_PayParams.setProductDesc(ParsePayData.product_desc);
        lxhw_PayParams.setRoleId(ParsePayData.game_Role_Id);
        lxhw_PayParams.setRoleName(ParsePayData.app_user_Name);
        try {
            i = Integer.parseInt(ParsePayData.roleLevel);
        } catch (NumberFormatException unused2) {
            i = 0;
        }
        lxhw_PayParams.setRoleLevel(i);
        lxhw_PayParams.setServerId(ParsePayData.sid);
        lxhw_PayParams.setServerName(ParsePayData.serverName);
        lxhw_PayParams.setVip(ParsePayData.vipLevel);
        lxhw_PayParams.setExtension(ParsePayData.app_Ext1);
        Lxhw_Platform.getInstance().pay(this.mainActivity, lxhw_PayParams);
    }

    @Override // com.mu.MainActivityBase
    public void changeAccount() {
        Lxhw_Platform.getInstance().logOut(this.mainActivity);
    }

    @Override // com.mu.MainActivityBase
    public void logoutAccount() {
        Lxhw_Platform.getInstance().logOut(this.mainActivity);
    }

    @Override // com.mu.MainActivityBase
    public void submitExtendData(String str) {
        MuGameData ParseGameData = MuGameData.ParseGameData(str);
        Lxhw_UserExtraData lxhw_UserExtraData = new Lxhw_UserExtraData();
        int i = ParseGameData.dataType;
        if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            lxhw_UserExtraData.setDataType(ParseGameData.dataType);
            lxhw_UserExtraData.setServerId(ParseGameData.serverID + "");
            lxhw_UserExtraData.setServerName(ParseGameData.serverName);
            lxhw_UserExtraData.setRoleID(ParseGameData.roleID);
            lxhw_UserExtraData.setRoleName(ParseGameData.roleName);
            lxhw_UserExtraData.setRoleLevel(ParseGameData.roleLevel);
            lxhw_UserExtraData.setRemain_coin(ParseGameData.moneyNum + "");
            Lxhw_Platform.getInstance().submitExtendData(this.mainActivity, lxhw_UserExtraData);
        }
    }

    @Override // com.mu.MainActivityBase
    public void logoutGame() {
        Lxhw_Platform.getInstance().logOut(this.mainActivity);
    }

    @Override // com.mu.MainActivityBase
    public void CallUserAgreement() {
        MuDebug.Log("获取用户协议");
    }

    @Override // com.mu.MainActivityBase
    public void AccountCancellation() {
        MuDebug.Log("用户注销");
    }

    @Override // com.mu.MainActivityBase
    public String GetResUpdateUrl() {
        return HotUpdateMgr.getInstance().mVersionManager.netApkVersion.resUrl;
    }

    @Override // com.mu.MainActivityBase
    public String GetServerResVersion() {
        return HotUpdateMgr.getInstance().mVersionManager.netApkVersion.newVersion + "." + HotUpdateMgr.getInstance().mVersionManager.netApkVersion.packageCount;
    }

    @Override // com.mu.MainActivityBase
    public String GetLocalResVersion() {
        return ConfigInfo.CurAPKVersion + "." + HotUpdateMgr.getInstance().mVersionManager.netApkVersion.packageCount;
    }

    @Override // com.mu.MainActivityBase
    public String GetInstallResVersion() {
        return ConfigInfo.PackageVersion + ".0";
    }

    @Override // com.mu.MainActivityBase
    public void ResUpdateSuccess(String str) {
        ConfigInfo.CurAPKVersion = str;
        HotUpdateMgr.getInstance().mVersionManager.WriteApkVersion();
    }

    @Override // com.mu.MainActivityBase
    public int IsExternalNet() {
        return ConfigInfo.externalNet;
    }

    @Override // com.mu.MainActivityBase
    public boolean IsSdk() {
        return !ConfigInfo.SDKGameID.equals("");
    }

    @Override // com.mu.MainActivityBase
    public String GetPid() {
        return ConfigInfo.pId;
    }

    @Override // com.mu.MainActivityBase
    public String GetHotUpdateConfigUrl() {
        return ConfigInfo.VersionURL;
    }

    public boolean GetDebugState() {
        return ConfigInfo.isDebugState;
    }

    public String GetVersionConfig() {
        return ConfigInfo.versionConfig;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
        Lxhw_Platform.getInstance().onDestroy(this);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        Log.i("MainActivity", "attachBaseContext");
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(context, 0));
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Log.i("MainActivity", "onActivityResult");
        Lxhw_Platform.getInstance().onActivityResult(i, i2, intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
        Lxhw_Platform.getInstance().onPause(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Lxhw_Platform.getInstance().onNewIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
        Lxhw_Platform.getInstance().onResume(this);
    }

    @Override // com.mu.MainActivityBase, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        Lxhw_Platform.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    public static Locale getSysLocale() {
        if (Build.VERSION.SDK_INT >= 24) {
            Log.d("LocaleList", "LocaleList: " + LocaleList.getDefault().toString());
            Locale locale = LocaleList.getDefault().get(0);
            Log.d("LocaleList", "country:" + locale.getCountry());
            return locale;
        }
        return Locale.getDefault();
    }
}
