package com.oversea.ab_firstarea.dialog;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.oversea.ab_firstarea.dpresenter.PresenterForgetpwAccount;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_ForgetpwAccountPresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_ForgetpwAccountView;
import com.oversea.ab_firstarea.net.model.UserBindInfoBean;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.IsFastClickUtils;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_ForgetpwAccountDialog extends Lxhw_BaseDialogFragment implements Lxhw_ForgetpwAccountView, View.OnClickListener {
    private EditText mAccountEdit;
    private Button mSutmitBtn;
    private PresenterForgetpwAccount presenter;
    ImageView tw_sdk_back_iv;
    private String TAG = "ForgetpwAccountDialog";
    private String emial = "";
    private String mobile = "";

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xforgetpwaccount_layout";
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.presenter = new Lxhw_ForgetpwAccountPresenterImpl(getActivity(), this);
        EditText editText = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_register_account"));
        this.mAccountEdit = editText;
        editText.setLayerType(2, null);
        this.mAccountEdit.setLayerType(2, null);
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_forgetpwd_submit_btn"));
        this.mSutmitBtn = button;
        button.setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_sdk_back_iv, 15);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_sdk_back_iv) {
            dismiss();
            return;
        }
        if (TextUtils.isEmpty(this.mAccountEdit.getText())) {
            ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Util.getString(Lxhw_XSDK.getInstance().getContext(), "tw_user_name_nil"));
        } else {
            if (view != this.mSutmitBtn || IsFastClickUtils.isFastClick(Lxhw_Platform.getInstance().fastClickTime)) {
                return;
            }
            this.presenter.doForgetPwAccount(this.mAccountEdit.getText().toString());
            Lxhw_DialogManage.getInstance().showDialog();
        }
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, UserBindInfoBean userBindInfoBean) {
        if (TextUtils.isEmpty(userBindInfoBean.getData().getEmail()) && TextUtils.isEmpty(userBindInfoBean.getData().getPhone())) {
            ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Lxhw_XSDK.getInstance().getContext().getString(Util.getIdByName(Lxhw_XSDK.getInstance().getContext(), TypedValues.Custom.S_STRING, "tw_no_bind_mobile_email")));
        }
    }
}
