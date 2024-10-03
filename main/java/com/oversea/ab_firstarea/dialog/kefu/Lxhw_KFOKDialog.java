package com.oversea.ab_firstarea.dialog.kefu;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_KFOKDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    Bundle bundle;
    Button tw_kfno;
    Button tw_kfyes;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xkfok_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (displayMetrics.widthPixels >= displayMetrics.heightPixels) {
            int i = (int) (displayMetrics.heightPixels * 0.75d);
            getDialog().getWindow().setLayout(i, (int) (i * 0.6d));
        } else {
            int i2 = (int) (displayMetrics.widthPixels * 0.75d);
            getDialog().getWindow().setLayout(i2, (int) (i2 * 0.6d));
        }
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.bundle = getArguments();
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_kfno"));
        this.tw_kfno = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_kfyes"));
        this.tw_kfyes = button2;
        button2.setOnClickListener(this);
        setCancelable(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_kfno) {
            dismiss();
        } else if (view == this.tw_kfyes) {
            dismiss();
        }
    }
}
