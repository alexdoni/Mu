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
import com.p008ld.sdk.util.zza;
import java.util.List;

/* loaded from: classes2.dex */
public class LDSdkManager {
    private static LDSdkManager instance;
    private ISdkHandler impl;

    private LDSdkManager() {
    }

    public static synchronized LDSdkManager getInstance() {
        LDSdkManager lDSdkManager;
        synchronized (LDSdkManager.class) {
            if (instance == null) {
                instance = new LDSdkManager();
            }
            lDSdkManager = instance;
        }
        return lDSdkManager;
    }

    public void init(Activity activity, LDCallback<Boolean> lDCallback) {
        initImpl();
        this.impl.init(activity, lDCallback);
    }

    private void initImpl() {
        ISdkHandler iSdkHandler = this.impl;
        if (iSdkHandler == null && iSdkHandler == null) {
            this.impl = new zzb();
        }
    }

    public void showLoginView(final Activity activity, final LDLoginCallback lDLoginCallback) {
        zza.zza(new Runnable() { // from class: com.ld.sdk.LDSdkManager.1
            @Override // java.lang.Runnable
            public void run() {
                if (LDSdkManager.this.impl != null) {
                    LDSdkManager.this.impl.showLoginView(activity, lDLoginCallback);
                }
            }
        });
    }

    public void autoLogin(Activity activity, LDLoginCallback lDLoginCallback) {
        ISdkHandler iSdkHandler = this.impl;
        if (iSdkHandler != null) {
            iSdkHandler.autoLogin(activity, lDLoginCallback);
        }
    }

    public void unInit(Activity activity) {
        ISdkHandler iSdkHandler = this.impl;
        if (iSdkHandler != null) {
            iSdkHandler.unInit(activity);
            this.impl = null;
        }
    }

    public void showChargeView(final Activity activity, final LdGamePayInfo ldGamePayInfo, final LDPayCallback lDPayCallback) {
        zza.zza(new Runnable() { // from class: com.ld.sdk.LDSdkManager.2
            @Override // java.lang.Runnable
            public void run() {
                if (LDSdkManager.this.impl != null) {
                    LDSdkManager.this.impl.showChargeView(activity, ldGamePayInfo, lDPayCallback);
                }
            }
        });
    }

    public void showExitView(final Activity activity, final LDExitCallback lDExitCallback) {
        zza.zza(new Runnable() { // from class: com.ld.sdk.LDSdkManager.3
            @Override // java.lang.Runnable
            public void run() {
                if (LDSdkManager.this.impl != null) {
                    LDSdkManager.this.impl.showExitView(activity, lDExitCallback);
                }
            }
        });
    }

    public void showFloatView(Activity activity) {
        ISdkHandler iSdkHandler = this.impl;
        if (iSdkHandler != null) {
            iSdkHandler.showFloatWindow(activity, true);
        }
    }

    public void hideFlowView(Activity activity) {
        ISdkHandler iSdkHandler = this.impl;
        if (iSdkHandler != null) {
            iSdkHandler.showFloatWindow(activity, false);
        }
    }

    public void showUserCenterView(Activity activity, LDCallback<Boolean> lDCallback) {
        ISdkHandler iSdkHandler = this.impl;
        if (iSdkHandler != null) {
            iSdkHandler.showUserCenterView(activity, lDCallback);
        }
    }

    public void enterGame(Context context, GameRoleInfo gameRoleInfo, LDCallback<Boolean> lDCallback) {
        ISdkHandler iSdkHandler = this.impl;
        if (iSdkHandler != null) {
            iSdkHandler.enterGame(context, gameRoleInfo, lDCallback);
        }
    }

    public void reportCpLogin(String str, LDCallback<Boolean> lDCallback) {
        ISdkHandler iSdkHandler = this.impl;
        if (iSdkHandler != null) {
            iSdkHandler.reportCpLogin(str, lDCallback);
        }
    }

    public void queryProducts(LDProductQueryParam lDProductQueryParam, LDQueryCallback<List<LDProductInfo>> lDQueryCallback) {
        ISdkHandler iSdkHandler = this.impl;
        if (iSdkHandler != null) {
            iSdkHandler.queryProducts(lDProductQueryParam, lDQueryCallback);
        }
    }

    public void onResume(Activity activity) {
        getInstance().showFloatView(activity);
    }

    public void onPause(Activity activity) {
        getInstance().hideFlowView(activity);
    }

    public void jumpPage(Context context, String str, int i) {
        ISdkHandler iSdkHandler = this.impl;
        if (iSdkHandler != null) {
            iSdkHandler.jumpPage(context, str, i);
        }
    }

    public String getLoginInfo() {
        ISdkHandler iSdkHandler = this.impl;
        if (iSdkHandler != null) {
            return iSdkHandler.getLoginInfo();
        }
        return null;
    }
}
