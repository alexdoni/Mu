package com.yalantis.ucrop.statusbar;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.yalantis.ucrop.util.DensityUtil;

/* loaded from: classes3.dex */
public class ImmersiveManager {
    private static final String TAG_FAKE_STATUS_BAR_VIEW = "TAG_FAKE_STATUS_BAR_VIEW";
    private static final String TAG_MARGIN_ADDED = "TAG_MARGIN_ADDED";

    public static void immersiveAboveAPI23(AppCompatActivity appCompatActivity, int i, int i2, boolean z) {
        immersiveAboveAPI23(appCompatActivity, false, false, i, i2, z);
    }

    public static void immersiveAboveAPI23(AppCompatActivity appCompatActivity, boolean z, boolean z2, int i, int i2, boolean z3) {
        try {
            Window window = appCompatActivity.getWindow();
            boolean z4 = true;
            if (z && z2) {
                window.clearFlags(201326592);
                LightStatusBarUtils.setLightStatusBar(appCompatActivity, true, true, i == 0, z3);
                window.addFlags(Integer.MIN_VALUE);
            } else if (!z && !z2) {
                window.requestFeature(1);
                window.clearFlags(201326592);
                if (i != 0) {
                    z4 = false;
                }
                LightStatusBarUtils.setLightStatusBar(appCompatActivity, false, false, z4, z3);
                window.addFlags(Integer.MIN_VALUE);
            } else {
                if (z) {
                    return;
                }
                window.requestFeature(1);
                window.clearFlags(201326592);
                LightStatusBarUtils.setLightStatusBar(appCompatActivity, false, true, i == 0, z3);
                window.addFlags(Integer.MIN_VALUE);
            }
            window.setStatusBarColor(i);
            window.setNavigationBarColor(i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initBarBelowLOLLIPOP(Activity activity) {
        activity.getWindow().addFlags(67108864);
        setupStatusBarView(activity);
    }

    private static void setupStatusBarView(Activity activity) {
        Window window = activity.getWindow();
        View findViewWithTag = window.getDecorView().findViewWithTag(TAG_FAKE_STATUS_BAR_VIEW);
        if (findViewWithTag == null) {
            findViewWithTag = new View(activity);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, DensityUtil.getStatusBarHeight(activity));
            layoutParams.gravity = 48;
            findViewWithTag.setLayoutParams(layoutParams);
            findViewWithTag.setVisibility(0);
            findViewWithTag.setTag(TAG_MARGIN_ADDED);
            ((ViewGroup) window.getDecorView()).addView(findViewWithTag);
        }
        findViewWithTag.setBackgroundColor(0);
    }
}
