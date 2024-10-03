package com.oversea.ab_firstarea.dialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import com.xsdk.ab_firstbase.statisics.util.languagelib.MultiLanguageUtil;

/* loaded from: classes.dex */
public class Lxhw_ChangeLanguageDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    Bundle bundle;
    Handler handler = new Handler() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_ChangeLanguageDialog.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Lxhw_ChangeLanguageDialog.this.dismiss();
            Log.e("handler", "handleMessage= ");
            ImageUtils.setSharePreferences(Lxhw_ChangeLanguageDialog.this.mContext, Constants.SDK_CHANGELANG_SD, "1");
            if (Lxhw_ChangeLanguageDialog.this.mContext != null) {
                Lxhw_ChangeLanguageDialog.this.mContext.finish();
            }
            System.exit(0);
        }
    };
    int languageType;
    Button tw_kfno;
    Button tw_kfyes;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xchangelanguage_layout";
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
        Bundle arguments = getArguments();
        this.bundle = arguments;
        if (arguments.containsKey("languageType")) {
            this.languageType = this.bundle.getInt("languageType");
        }
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
            MultiLanguageUtil.getInstance().updateLanguage(this.languageType);
            this.handler.sendEmptyMessageDelayed(1, 1500L);
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.handler = null;
    }
}
