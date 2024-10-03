package com.oversea.ab_firstarea.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.utils.DebugModeManager;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class Lxhw_BaseDialogFragment extends DialogFragment {
    public Activity mContext;

    public abstract String getLayoutId();

    public abstract void initView(View view);

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return super.onCreateDialog(bundle);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
            Window window = getDialog().getWindow();
            if (window != null) {
                window.setBackgroundDrawable(new ColorDrawable(0));
                window.addFlags(1024);
            }
            getDialog().requestWindowFeature(1);
            initSoftInputListener();
        }
        View inflate = layoutInflater.inflate(Util.getIdByName(this.mContext, "layout", getLayoutId()), viewGroup);
        initView(inflate);
        return inflate;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        int i;
        int i2;
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        if (displayMetrics.widthPixels >= displayMetrics.heightPixels) {
            i = (int) (displayMetrics.heightPixels * 0.95d);
            i2 = displayMetrics.heightPixels;
        } else {
            i = (int) (displayMetrics.widthPixels * 0.95d);
            i2 = displayMetrics.widthPixels;
        }
        getDialog().getWindow().setLayout(i, (int) (i2 * 0.95d));
    }

    private void setFullScreen() {
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        getDialog().getWindow().getDecorView().setSystemUiVisibility(4);
        getDialog().getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment.1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                Lxhw_BaseDialogFragment.this.getDialog().getWindow().getDecorView().setSystemUiVisibility(4);
            }
        });
    }

    public void dealEditViewKeepOut(final View view, final View view2) {
        LLog.v_noControl("BaseDialogFragment", "1111");
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Rect rect = new Rect();
                Log.v("BaseDialogFragment", "222");
                view.getWindowVisibleDisplayFrame(rect);
                int height = view.getRootView().getHeight() - rect.bottom;
                LLog.v_noControl("BaseDialogFragment", "rootInvisibleHeight=" + height + "rootView.getRootView().getHeight()=" + view.getRootView().getHeight() + "rect.bottom=" + rect.bottom);
                if (height > 100) {
                    int[] iArr = new int[2];
                    view2.getLocationInWindow(iArr);
                    int height2 = (iArr[1] + view2.getHeight()) - rect.bottom;
                    LLog.v_noControl("BaseDialogFragment", "srollHeight=" + height2);
                    view.scrollTo(0, height2);
                    return;
                }
                view.scrollTo(0, 0);
                LLog.v_noControl("BaseDialogFragment", " scrollTo(0, 0)");
            }
        });
    }

    private boolean hasNavBar() {
        Resources resources = getActivity().getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (identifier != 0) {
            boolean z = resources.getBoolean(identifier);
            String navBarOverride = getNavBarOverride();
            if ("1".equals(navBarOverride)) {
                return false;
            }
            if ("0".equals(navBarOverride)) {
                return true;
            }
            return z;
        }
        return !ViewConfiguration.get(getActivity()).hasPermanentMenuKey();
    }

    private static String getNavBarOverride() {
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(null, "qemu.hw.mainkeys");
        } catch (Throwable unused) {
            return null;
        }
    }

    private void initSoftInputListener() {
        if (InitBean.getInstance().getGame_info() == null || InitBean.getInstance().getGame_info().getIs_debug() != 1 || getDialog() == null || getDialog().getWindow() == null || getDialog().getWindow().getDecorView() == null) {
            return;
        }
        getDialog().getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                DebugModeManager.getInstance().setDebugModeByDialogFragment(Lxhw_BaseDialogFragment.this.mContext, motionEvent);
                return false;
            }
        });
    }

    public void setDefaultDisplay() {
        try {
            if (Build.VERSION.SDK_INT <= 23 || isSamSung()) {
                return;
            }
            Resources resources = this.mContext.getResources();
            Configuration configuration = resources.getConfiguration();
            if (resources.getDisplayMetrics().densityDpi != DisplayMetrics.DENSITY_DEVICE_STABLE) {
                configuration.densityDpi = DisplayMetrics.DENSITY_DEVICE_STABLE;
                resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            }
        } catch (Exception unused) {
        }
    }

    private boolean isSamSung() {
        return getBrand().toLowerCase().contains("samsung");
    }

    private String getBrand() {
        return Build.BRAND;
    }
}
