package com.p008ld.sdk;

import android.app.Activity;
import android.content.Context;
import com.p008ld.sdk.core.bean.GameRoleInfo;
import com.p008ld.sdk.core.bean.LDProductInfo;
import com.p008ld.sdk.core.bean.LDProductQueryParam;
import com.p008ld.sdk.core.bean.LdGamePayInfo;
import com.p008ld.sdk.internal.LDCallback;
import com.p008ld.sdk.internal.LDExitCallback;
import com.p008ld.sdk.internal.LDLoginCallback;
import com.p008ld.sdk.internal.LDPayCallback;
import com.p008ld.sdk.internal.LDQueryCallback;
import java.util.List;

/* loaded from: classes2.dex */
public interface ISdkHandler {
    void autoLogin(Activity activity, LDLoginCallback lDLoginCallback);

    void enterGame(Context context, GameRoleInfo gameRoleInfo, LDCallback<Boolean> lDCallback);

    String getLoginInfo();

    void init(Activity activity, LDCallback<Boolean> lDCallback);

    void jumpPage(Context context, String str, int i);

    void queryProducts(LDProductQueryParam lDProductQueryParam, LDQueryCallback<List<LDProductInfo>> lDQueryCallback);

    void reportCpLogin(String str, LDCallback<Boolean> lDCallback);

    void showChargeView(Activity activity, LdGamePayInfo ldGamePayInfo, LDPayCallback lDPayCallback);

    void showExitView(Activity activity, LDExitCallback lDExitCallback);

    void showFloatWindow(Activity activity, boolean z);

    void showLoginView(Activity activity, LDLoginCallback lDLoginCallback);

    void showUserCenterView(Activity activity, LDCallback<Boolean> lDCallback);

    void unInit(Activity activity);
}
