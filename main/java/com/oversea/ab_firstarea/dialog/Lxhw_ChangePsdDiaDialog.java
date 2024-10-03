package com.oversea.ab_firstarea.dialog;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.oversea.ab_firstarea.dpresenter.PresenterChangePsdDia;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_ChangePsdDiaPresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_ChangePsdDiaView;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_ChangePsdDiaDialog extends Lxhw_BaseDialogFragment implements Lxhw_ChangePsdDiaView, View.OnClickListener {
    private static Lxhw_ChangePsdDiaDialog defaultInstance;
    private String TAG = "ChangePsdDiaDialog";
    private boolean isChanging = false;
    private LinearLayout line_new1;
    private LinearLayout line_old;
    private Activity mContext;
    private PresenterChangePsdDia presenter;
    private RelativeLayout root_viewp;
    private LinearLayout tw_account_layout;
    private Button tw_change_psd_btn;
    private EditText tw_input_newpsd_et;
    private EditText tw_input_newpsd_et2;
    private EditText tw_input_oldpsd_et;
    private ImageView tw_sdk_back_iv;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xchange_psd_dialog";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.presenter = new Lxhw_ChangePsdDiaPresenterImpl(this.mContext, this);
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_ChangePsdDiaDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Lxhw_ChangePsdDiaDialog.this.dismiss();
            }
        });
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_sdk_back_iv, 15);
        this.tw_input_oldpsd_et = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_input_oldpsd_et"));
        this.tw_input_newpsd_et = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_input_newpsd_et"));
        this.tw_input_newpsd_et2 = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_input_newpsd_et2"));
        this.tw_input_oldpsd_et.setLayerType(2, null);
        this.tw_input_newpsd_et.setLayerType(2, null);
        this.tw_input_newpsd_et2.setLayerType(2, null);
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_change_psd_btn"));
        this.tw_change_psd_btn = button;
        button.setOnClickListener(this);
        this.root_viewp = (RelativeLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "root_viewp"));
        this.line_old = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "line_old"));
        this.line_new1 = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "line_new1"));
    }

    public static Lxhw_ChangePsdDiaDialog getDefault() {
        Lxhw_ChangePsdDiaDialog lxhw_ChangePsdDiaDialog = defaultInstance;
        if (lxhw_ChangePsdDiaDialog == null && lxhw_ChangePsdDiaDialog == null) {
            defaultInstance = new Lxhw_ChangePsdDiaDialog();
        }
        return defaultInstance;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_change_psd_btn) {
            if (TextUtils.isEmpty(this.tw_input_oldpsd_et.getText())) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_input_old_password"));
                return;
            }
            if (TextUtils.isEmpty(this.tw_input_newpsd_et.getText())) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_input_password"));
                return;
            }
            if (TextUtils.isEmpty(this.tw_input_newpsd_et2.getText())) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_input_password"));
                return;
            }
            if (this.tw_input_oldpsd_et.getText().toString().trim().length() < 6) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_pwd_length_tip"));
                return;
            }
            if (this.tw_input_newpsd_et.getText().toString().trim().length() < 6) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_new_pwd_length_tip"));
                return;
            }
            if (this.tw_input_newpsd_et2.getText().toString().trim().length() < 6) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_new_pwd_length_tip"));
                return;
            }
            String trim = this.tw_input_oldpsd_et.getText().toString().trim();
            String trim2 = this.tw_input_newpsd_et.getText().toString().trim();
            if (!trim2.equals(this.tw_input_newpsd_et2.getText().toString().trim())) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_two_pwd_dif"));
            } else {
                this.presenter.doChangePassword(trim, trim2);
                Lxhw_DialogManage.getInstance().showDialog();
            }
        }
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, Object obj) {
        dismiss();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
    }
}
