package com.p003ff.sdk.ffoversea.p004c.p005b;

import android.app.Activity;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.plugin.channel.AbstractTsdkManager;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.lifecycle.XActivityCallbackAdapter;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserExtraData;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.p008ld.sdk.LDSdkManager;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.bean.GameRoleInfo;
import com.p008ld.sdk.core.bean.LdGamePayInfo;
import com.p008ld.sdk.internal.LDCallback;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDExitCallback;
import com.p008ld.sdk.internal.LDLoginCallback;
import com.p008ld.sdk.internal.LDNotLoginException;
import com.p008ld.sdk.internal.LDPayCallback;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.math.BigDecimal;

/* loaded from: classes.dex */
public class Fsdk extends AbstractTsdkManager {
    private static final String TAG = "LD_SDK";
    private Activity activity = Lxhw_XSDK.getInstance().getContext();

    @Override // com.oversea.ab_firstarea.plugin.channel.AbstractTsdkManager
    public void init() {
        LLog.i_Control(TAG, "Fsdk init");
        Lxhw_XSDK.getInstance().setActivityCallback(new XActivityCallbackAdapter() { // from class: com.ff.sdk.ffoversea.c.b.Fsdk.1
            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallbackAdapter, com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onResume() {
                LDSdkManager.getInstance().onResume(Fsdk.this.activity);
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallbackAdapter, com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onPause() {
                LDSdkManager.getInstance().onPause(Fsdk.this.activity);
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallbackAdapter, com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onDestroy() {
                LDSdkManager.getInstance().unInit(Fsdk.this.activity);
            }
        });
        LDSdkManager.getInstance().init(this.activity, new LDCallback<Boolean>() { // from class: com.ff.sdk.ffoversea.c.b.Fsdk.2
            @Override // com.p008ld.sdk.internal.LDCallback
            public void done(Boolean bool, LDException lDException) {
                if (lDException == null) {
                    Fsdk.this.initSuccess();
                    return;
                }
                LLog.e_noControl(Fsdk.TAG, "init fail: " + lDException.toString());
            }
        });
    }

    @Override // com.oversea.ab_firstarea.plugin.channel.AbstractTsdkManager
    public void login() {
        LLog.i_Control(TAG, "Fsdk login");
        LDSdkManager.getInstance().showLoginView(this.activity, new LDLoginCallback() { // from class: com.ff.sdk.ffoversea.c.b.Fsdk.3
            @Override // com.p008ld.sdk.internal.LDLoginCallback
            public void onSuccess(String str, String str2) {
                LLog.i_Control(Fsdk.TAG, "login onSuccess: " + str + " ; " + str2);
                Fsdk.this.loginService(9, str2, str);
            }

            @Override // com.p008ld.sdk.internal.LDLoginCallback
            public void onError(String str) {
                LLog.e_noControl(Fsdk.TAG, "login onError: " + str);
            }

            @Override // com.p008ld.sdk.internal.LDLoginCallback
            public void onLogout() {
                Fsdk.this.logoutSuccess();
            }
        });
    }

    @Override // com.oversea.ab_firstarea.plugin.channel.AbstractTsdkManager
    public void logout() {
        LLog.i_Control(TAG, "Fsdk logout");
        LDUser.logout(true);
    }

    @Override // com.oversea.ab_firstarea.plugin.channel.AbstractTsdkManager
    public void pay(Lxhw_PayParams lxhw_PayParams) {
        long j;
        LLog.i_Control(TAG, "Fsdk pay:" + lxhw_PayParams.toString());
        LdGamePayInfo ldGamePayInfo = new LdGamePayInfo();
        ldGamePayInfo.cpUserId = lxhw_PayParams.getRoleId();
        ldGamePayInfo.cpOrderId = lxhw_PayParams.getOrderId();
        ldGamePayInfo.tradeName = lxhw_PayParams.getProductName();
        ldGamePayInfo.productId = lxhw_PayParams.getProductId();
        ldGamePayInfo.currencyType = lxhw_PayParams.getCurrencyType();
        try {
            j = new BigDecimal(lxhw_PayParams.getCommodityPrice()).multiply(new BigDecimal(100)).longValue();
        } catch (Exception unused) {
            j = 0;
        }
        LLog.i_Control(TAG, "pay price: " + j);
        ldGamePayInfo.commodityPrice = j;
        LDSdkManager.getInstance().showChargeView(this.activity, ldGamePayInfo, new LDPayCallback() { // from class: com.ff.sdk.ffoversea.c.b.Fsdk.4
            @Override // com.p008ld.sdk.internal.LDPayCallback
            public void onSuccess(String str, String str2) {
                LLog.i_Control(Fsdk.TAG, "paySuccess: " + str + " ; " + str2);
                Fsdk.this.paySuccess();
                Fsdk.this.buyFinish();
            }

            @Override // com.p008ld.sdk.internal.LDPayCallback
            public void onError(LDException lDException) {
                if (lDException instanceof LDNotLoginException) {
                    Fsdk.this.login();
                } else {
                    LLog.e_Control(Fsdk.TAG, "payFail: " + lDException.toString());
                }
                Fsdk.this.payFail();
                Fsdk.this.buyFinish();
            }

            @Override // com.p008ld.sdk.internal.LDPayCallback
            public void onCancel() {
                LLog.i_Control(Fsdk.TAG, "payCancel");
                Fsdk.this.payCancel();
                Fsdk.this.buyFinish();
            }
        });
    }

    @Override // com.oversea.ab_firstarea.plugin.channel.AbstractTsdkManager
    public void exit() {
        LLog.i_Control(TAG, "Fsdk exit");
        LDSdkManager.getInstance().showExitView(this.activity, new LDExitCallback() { // from class: com.ff.sdk.ffoversea.c.b.Fsdk.5
            @Override // com.p008ld.sdk.internal.LDExitCallback
            public void done(boolean z) {
                if (z) {
                    Fsdk.this.activity.finish();
                    System.exit(0);
                }
            }
        });
    }

    @Override // com.oversea.ab_firstarea.plugin.channel.AbstractTsdkManager
    public void submitGameInfo(Lxhw_UserExtraData lxhw_UserExtraData) {
        LLog.i_Control(TAG, "Fsdk submitGameInfo");
        if (lxhw_UserExtraData.getDataType() == 2 || lxhw_UserExtraData.getDataType() == 3) {
            GameRoleInfo gameRoleInfo = new GameRoleInfo();
            gameRoleInfo.serverId = lxhw_UserExtraData.getServerId();
            gameRoleInfo.serverName = lxhw_UserExtraData.getServerName();
            gameRoleInfo.roleId = lxhw_UserExtraData.getRoleID();
            gameRoleInfo.roleName = lxhw_UserExtraData.getRoleName();
            gameRoleInfo.roleType = "0";
            gameRoleInfo.level = lxhw_UserExtraData.getRoleLevel();
            gameRoleInfo.money = lxhw_UserExtraData.getRemain_coin();
            gameRoleInfo.partyName = "0";
            gameRoleInfo.vipLevel = lxhw_UserExtraData.getVipLevel();
            gameRoleInfo.powerNum = 0;
            LDSdkManager.getInstance().enterGame(this.activity, gameRoleInfo, new LDCallback<Boolean>() { // from class: com.ff.sdk.ffoversea.c.b.Fsdk.6
                @Override // com.p008ld.sdk.internal.LDCallback
                public void done(Boolean bool, LDException lDException) {
                    if (lDException == null) {
                        LLog.i_noControl(Fsdk.TAG, "submitGameInfo: success");
                        return;
                    }
                    LLog.e_noControl(Fsdk.TAG, "submitGameInfo: " + lDException.toString());
                }
            });
        }
    }

    public void buyFinish() {
        Lxhw_DialogManage.getInstance().cancelDialog();
        ComConstants.GETORDER_STATU = 4;
    }
}
