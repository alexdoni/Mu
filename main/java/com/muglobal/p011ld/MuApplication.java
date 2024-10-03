package com.muglobal.p011ld;

import android.content.pm.PackageManager;
import android.util.ArrayMap;
import android.util.Log;
import com.mu.update.ConfigInfo;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.lifecycle.XApplication;
import com.oversea.ab_firstplatform.statistics.ComConstants;

/* loaded from: classes2.dex */
public class MuApplication extends XApplication {
    @Override // com.oversea.ab_firstplatform.lifecycle.XApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        InitPID();
        InitGameID();
        InitOPName();
        SubmitDotData();
    }

    public void InitPID() {
        try {
            String str = getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getInt("CHANNEL_ID") + "";
            if (str == null || str.isEmpty()) {
                return;
            }
            ConfigInfo.pId = str;
            Log.i("ConfigInfo.pId : ", ConfigInfo.pId);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("ConfigInfo.pId Error: ", e.toString());
        }
    }

    public void InitGameID() {
        try {
            String str = getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getInt(ComConstants.GAME_ID) + "";
            if (str == null || str.isEmpty()) {
                return;
            }
            ConfigInfo.SDKGameID = str;
            Log.i("ConfigInfo.SDKGameID : ", ConfigInfo.SDKGameID);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("SDKGameID Error: ", e.toString());
        }
    }

    public void InitOPName() {
        try {
            String string = getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getString("OP_NAME");
            if (string == null || string.isEmpty()) {
                return;
            }
            ConfigInfo.opName = string;
            Log.i("ConfigInfo.opName : ", ConfigInfo.opName);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("opName Error: ", e.toString());
        }
    }

    public void SubmitDotData() {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("custom_launch_game", 1);
        Log.i("TAG", "custom_launch_game");
        Lxhw_Platform.getInstance().onTrackEventAF(this, "custom_launch_game", arrayMap);
    }
}
