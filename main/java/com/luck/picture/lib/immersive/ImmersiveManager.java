package com.luck.picture.lib.immersive;

import android.R;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.luck.picture.lib.utils.DensityUtil;
import com.tencent.p014av.ptt.PttError;

/* loaded from: classes2.dex */
public class ImmersiveManager {
    private static final String TAG_FAKE_STATUS_BAR_VIEW = "TAG_FAKE_STATUS_BAR_VIEW";
    private static final String TAG_MARGIN_ADDED = "TAG_MARGIN_ADDED";
    private static final String TAG_NAVIGATION_BAR_VIEW = "TAG_NAVIGATION_BAR_VIEW";

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

    public static void translucentStatusBar(Activity activity, boolean z) {
        Window window = activity.getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        View decorView = window.getDecorView();
        if (z) {
            decorView.setSystemUiVisibility(9472);
        } else {
            window.getDecorView().setSystemUiVisibility(PttError.GMESDK_UNINSTALLERROR);
        }
        View childAt = ((ViewGroup) window.findViewById(R.id.content)).getChildAt(0);
        if (childAt != null) {
            childAt.setFitsSystemWindows(false);
            ViewCompat.requestApplyInsets(childAt);
        }
    }

    private static void initBarBelowLOLLIPOP(Activity activity) {
        Window window = activity.getWindow();
        window.addFlags(67108864);
        setupStatusBarView(activity);
        if (DensityUtil.isNavBarVisible(activity)) {
            window.addFlags(134217728);
            setupNavBarView(activity);
        }
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

    private static void setupNavBarView(Activity activity) {
        FrameLayout.LayoutParams layoutParams;
        Window window = activity.getWindow();
        View findViewWithTag = window.getDecorView().findViewWithTag(TAG_NAVIGATION_BAR_VIEW);
        if (findViewWithTag == null) {
            findViewWithTag = new View(activity);
            findViewWithTag.setTag(TAG_NAVIGATION_BAR_VIEW);
            ((ViewGroup) window.getDecorView()).addView(findViewWithTag);
        }
        if (DensityUtil.isNavigationAtBottom(activity)) {
            layoutParams = new FrameLayout.LayoutParams(-1, DensityUtil.getNavigationBarHeight(activity));
            layoutParams.gravity = 80;
        } else {
            layoutParams = new FrameLayout.LayoutParams(DensityUtil.getNavigationBarWidth(activity), -1);
            layoutParams.gravity = GravityCompat.END;
        }
        findViewWithTag.setLayoutParams(layoutParams);
        findViewWithTag.setBackgroundColor(0);
        findViewWithTag.setVisibility(0);
    }
}
