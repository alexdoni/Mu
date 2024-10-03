package com.oversea.ab_firstarea.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.facebook.internal.AnalyticsEvents;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public abstract class Lxhw_BaseFullScreenDialog extends DialogFragment {
    private static final String TAG = "Lxhw_BaseFullScreenDialog";
    public Activity mContext;

    public abstract String getLayoutId();

    public abstract void initView(View view);

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, Util.getIdByName(this.mContext, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "dialog_fragment_navigationBarColor_style"));
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (Build.VERSION.SDK_INT >= 28) {
            if (getDialog() != null && getDialog().getWindow() != null) {
                getDialog().getWindow().addFlags(67108864);
                getDialog().getWindow().addFlags(512);
                WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
                attributes.layoutInDisplayCutoutMode = 1;
                attributes.systemUiVisibility = 2050;
                getDialog().getWindow().setAttributes(attributes);
                return;
            }
            Log.e(TAG, "dialog全屏设置失败,dialog或window为空");
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(Util.getIdByName(getActivity(), "layout", getLayoutId()), viewGroup);
        setCancelable(false);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_BaseFullScreenDialog.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4 || keyEvent.getAction() != 1) {
                    return false;
                }
                Lxhw_BaseFullScreenDialog.this.dismiss();
                return true;
            }
        });
        initView(inflate);
        return inflate;
    }
}
