package com.oversea.ab_firstarea.dialog;

import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.oversea.ab_firstarea.dpresenter.PresenterDelAccount;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_DelAccountPresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_DelAccountView;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_DelAccountDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener, Lxhw_DelAccountView {
    private Button btnCancel;
    private Button btnConfirm;
    private PresenterDelAccount presenter;
    private TextView tvContent;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xdel_account_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
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
            i = displayMetrics.heightPixels;
            i2 = displayMetrics.heightPixels;
        } else {
            i = (int) (displayMetrics.widthPixels * 0.95d);
            i2 = displayMetrics.widthPixels;
        }
        getDialog().getWindow().setLayout(i, (int) (i2 * 0.95d));
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tv_del_tip"));
        this.tvContent = textView;
        try {
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        } catch (Exception unused) {
        }
        this.presenter = new Lxhw_DelAccountPresenterImpl(this.mContext, this);
        this.btnCancel = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "btn_cancel"));
        this.btnConfirm = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "btn_confirm"));
        this.btnCancel.setOnClickListener(this);
        this.btnConfirm.setOnClickListener(this);
        setCancelable(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.btnCancel) {
            dismissAllowingStateLoss();
        } else if (view == this.btnConfirm) {
            this.presenter.delAccount();
            Lxhw_DialogManage.getInstance().showDialog();
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, Object obj) {
        Lxhw_DialogManage.getInstance().cancelDialog();
        delLocalAccount();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_DialogManage.getInstance().cancelDialog();
        ToastUtils.toastShow(this.mContext, ComUtil.getBaseBeanTip(baseBean));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006c A[Catch: Exception -> 0x00be, TryCatch #0 {Exception -> 0x00be, blocks: (B:3:0x0008, B:5:0x001e, B:12:0x008b, B:16:0x005c, B:17:0x0064, B:18:0x006c, B:19:0x0022, B:22:0x002c, B:25:0x0036, B:28:0x0040, B:31:0x004a), top: B:2:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void delLocalAccount() {
        /*
            r9 = this;
            java.lang.String r0 = ""
            java.lang.String r1 = "SDK_PASSWORD"
            java.lang.String r2 = "SDK_ACCOUNT"
            java.lang.String r3 = "SDK_LOGIN_TYPE"
            com.oversea.ab_firstplatform.Lxhw_XSDK r4 = com.oversea.ab_firstplatform.Lxhw_XSDK.getInstance()     // Catch: java.lang.Exception -> Lbe
            android.app.Activity r4 = r4.getContext()     // Catch: java.lang.Exception -> Lbe
            java.lang.String r4 = com.xsdk.ab_firstbase.statisics.util.ImageUtils.getStringKeyForValue(r4, r3)     // Catch: java.lang.Exception -> Lbe
            int r5 = r4.hashCode()     // Catch: java.lang.Exception -> Lbe
            r6 = 56
            r7 = 3
            r8 = 2
            if (r5 == r6) goto L4a
            switch(r5) {
                case 49: goto L40;
                case 50: goto L36;
                case 51: goto L2c;
                case 52: goto L22;
                default: goto L21;
            }     // Catch: java.lang.Exception -> Lbe
        L21:
            goto L54
        L22:
            java.lang.String r5 = "4"
            boolean r4 = r4.equals(r5)     // Catch: java.lang.Exception -> Lbe
            if (r4 == 0) goto L54
            r4 = r8
            goto L55
        L2c:
            java.lang.String r5 = "3"
            boolean r4 = r4.equals(r5)     // Catch: java.lang.Exception -> Lbe
            if (r4 == 0) goto L54
            r4 = r7
            goto L55
        L36:
            java.lang.String r5 = "2"
            boolean r4 = r4.equals(r5)     // Catch: java.lang.Exception -> Lbe
            if (r4 == 0) goto L54
            r4 = 1
            goto L55
        L40:
            java.lang.String r5 = "1"
            boolean r4 = r4.equals(r5)     // Catch: java.lang.Exception -> Lbe
            if (r4 == 0) goto L54
            r4 = 0
            goto L55
        L4a:
            java.lang.String r5 = "8"
            boolean r4 = r4.equals(r5)     // Catch: java.lang.Exception -> Lbe
            if (r4 == 0) goto L54
            r4 = 4
            goto L55
        L54:
            r4 = -1
        L55:
            if (r4 == 0) goto L6c
            if (r4 == r8) goto L64
            if (r4 == r7) goto L5c
            goto L8b
        L5c:
            com.oversea.ab_firstarea.haiwai.FaceBookControl r0 = com.oversea.ab_firstarea.haiwai.FaceBookControl.getInstance()     // Catch: java.lang.Exception -> Lbe
            r0.revokeAuth()     // Catch: java.lang.Exception -> Lbe
            goto L8b
        L64:
            com.oversea.ab_firstarea.haiwai.LoginGooglePlay r0 = com.oversea.ab_firstarea.haiwai.LoginGooglePlay.getInstent()     // Catch: java.lang.Exception -> Lbe
            r0.revokeAccess()     // Catch: java.lang.Exception -> Lbe
            goto L8b
        L6c:
            android.app.Activity r4 = r9.mContext     // Catch: java.lang.Exception -> Lbe
            java.lang.String r4 = com.xsdk.ab_firstbase.statisics.util.ImageUtils.getStringKeyForValue(r4, r2)     // Catch: java.lang.Exception -> Lbe
            android.app.Activity r5 = r9.mContext     // Catch: java.lang.Exception -> Lbe
            java.lang.String r5 = com.xsdk.ab_firstbase.statisics.util.ImageUtils.getStringKeyForValue(r5, r1)     // Catch: java.lang.Exception -> Lbe
            com.oversea.ab_firstplatform.model.LoginInfoManage r6 = com.oversea.ab_firstplatform.model.LoginInfoManage.getInstance()     // Catch: java.lang.Exception -> Lbe
            android.app.Activity r7 = r9.mContext     // Catch: java.lang.Exception -> Lbe
            r6.delAccount(r7, r4, r5)     // Catch: java.lang.Exception -> Lbe
            android.app.Activity r4 = r9.mContext     // Catch: java.lang.Exception -> Lbe
            com.xsdk.ab_firstbase.statisics.util.ImageUtils.setSharePreferences(r4, r2, r0)     // Catch: java.lang.Exception -> Lbe
            android.app.Activity r2 = r9.mContext     // Catch: java.lang.Exception -> Lbe
            com.xsdk.ab_firstbase.statisics.util.ImageUtils.setSharePreferences(r2, r1, r0)     // Catch: java.lang.Exception -> Lbe
        L8b:
            android.app.Activity r0 = r9.mContext     // Catch: java.lang.Exception -> Lbe
            java.lang.String r1 = "-1"
            com.xsdk.ab_firstbase.statisics.util.ImageUtils.setSharePreferences(r0, r3, r1)     // Catch: java.lang.Exception -> Lbe
            android.app.Activity r0 = r9.mContext     // Catch: java.lang.Exception -> Lbe
            android.app.Activity r1 = r9.mContext     // Catch: java.lang.Exception -> Lbe
            java.lang.String r2 = "string"
            java.lang.String r3 = "tw_delete_account_success"
            int r1 = com.xsdk.ab_firstbase.statisics.util.Util.getIdByName(r1, r2, r3)     // Catch: java.lang.Exception -> Lbe
            java.lang.String r0 = r0.getString(r1)     // Catch: java.lang.Exception -> Lbe
            android.app.Activity r1 = r9.mContext     // Catch: java.lang.Exception -> Lbe
            com.xsdk.ab_firstbase.statisics.util.ToastUtils.toastShow(r1, r0)     // Catch: java.lang.Exception -> Lbe
            com.oversea.ab_firstarea.dialog.Lxhw_DialogManage r0 = com.oversea.ab_firstarea.dialog.Lxhw_DialogManage.getInstance()     // Catch: java.lang.Exception -> Lbe
            android.app.Activity r1 = r9.mContext     // Catch: java.lang.Exception -> Lbe
            java.lang.String r2 = "UserCenterDialog"
            r0.removeFragment(r1, r2)     // Catch: java.lang.Exception -> Lbe
            r9.dismissAllowingStateLoss()     // Catch: java.lang.Exception -> Lbe
            com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform r0 = com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform.getInstance()     // Catch: java.lang.Exception -> Lbe
            r0.callbackSwitchAccount()     // Catch: java.lang.Exception -> Lbe
            goto Lc6
        Lbe:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            com.xsdk.ab_firstbase.statisics.util.LLog.e_noControl(r0)
        Lc6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oversea.ab_firstarea.dialog.Lxhw_DelAccountDialog.delLocalAccount():void");
    }
}
