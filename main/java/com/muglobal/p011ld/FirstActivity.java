package com.muglobal.p011ld;

import android.os.Bundle;
import android.os.Handler;
import android.util.ArrayMap;
import android.util.Log;
import com.mu.FirstActivityBase;
import com.mu.update.ConfigInfo;
import com.mu.update.HotUpdateMgr;
import com.oversea.ab_firstplatform.Lxhw_Platform;

/* loaded from: classes2.dex */
public class FirstActivity extends FirstActivityBase {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.CheckPermissions();
    }

    @Override // com.mu.FirstActivityBase
    public void EnterGame() {
        HotUpdateMgr.getInstance().Init(this, MainActivity.class, "https://client.qj3oz.gqj3ea.xyz/gameConfigs/android/wuxianjindan_1201/Version.json", true);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (HotUpdateMgr.getInstance().splashstate != ConfigInfo.SplashState.NONE) {
            if (HotUpdateMgr.getInstance().splashstate == ConfigInfo.SplashState.Start) {
                SubmitDotData("custom_flash_screen_show");
                new Handler().postDelayed(new Runnable() { // from class: com.muglobal.ld.FirstActivity.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HotUpdateMgr.getInstance().splashstate = ConfigInfo.SplashState.NONE;
                        FirstActivity.this.SubmitDotData("custom_flash_screen_close");
                    }
                }, 5000L);
            }
            HotUpdateMgr.getInstance().splashstate = ConfigInfo.SplashState.NONE;
        }
    }

    public void SubmitDotData(String str) {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(str, 1);
        Log.i("TAG", str);
        Lxhw_Platform.getInstance().onTrackEventAF(this, str, arrayMap);
    }
}
