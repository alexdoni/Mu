package com.oversea.ab_firstarea.utils;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.HandlerUtils;
import com.oversea.ab_firstplatform.Lxhw_XSDK;

/* loaded from: classes.dex */
public class DebugModeManager {
    private static volatile DebugModeManager manager;
    private int downActX;
    private int downActY;
    private int downFragX;
    private int downFragY;
    private int upActX;
    private int upActY;
    private int upFragX;
    private int upFragY;

    public static DebugModeManager getInstance() {
        if (manager == null) {
            synchronized (DebugModeManager.class) {
                if (manager == null) {
                    manager = new DebugModeManager();
                }
            }
        }
        return manager;
    }

    public void upload(String str, String str2) {
        if (InitBean.getInstance().getGame_info() == null || Lxhw_XSDK.getInstance().getContext() == null || InitBean.getInstance().getGame_info().getIs_debug() != 1) {
            return;
        }
        Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), str, str2);
    }

    public void setDebugModeByActivity(final Activity activity) {
        if (InitBean.getInstance().getGame_info() == null || activity == null || InitBean.getInstance().getGame_info().getIs_debug() != 1) {
            return;
        }
        HandlerUtils.getInstance().startPolling(InitBean.getInstance().getGame_info().getPolling_time(), new HandlerUtils.HandlerCallback() { // from class: com.oversea.ab_firstarea.utils.DebugModeManager.1
            @Override // com.oversea.ab_firstarea.utils.HandlerUtils.HandlerCallback
            public void call() {
                Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(activity, "custom_sdk_online", System.currentTimeMillis() + "");
            }
        });
    }

    public void setDebugModeByDialogFragment(Context context, MotionEvent motionEvent) {
        if (InitBean.getInstance().getGame_info() == null || context == null || InitBean.getInstance().getGame_info().getIs_debug() != 1) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            this.downFragX = (int) motionEvent.getRawX();
            this.downFragY = (int) motionEvent.getRawY();
        }
        if (motionEvent.getAction() == 1) {
            this.upFragX = (int) motionEvent.getRawX();
            this.upFragY = (int) motionEvent.getRawY();
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(context, "custom_sdk_touch", this.downFragX + "," + this.downFragY + "," + this.upFragX + "," + this.upFragY);
            resetFrag();
        }
    }

    private void resetAct() {
        this.upActY = 0;
        this.upActX = 0;
        this.downActY = 0;
        this.downActX = 0;
    }

    private void resetFrag() {
        this.upFragY = 0;
        this.upFragX = 0;
        this.downFragY = 0;
        this.downFragX = 0;
    }
}
