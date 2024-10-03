package com.oversea.ab_firstarea.newlogin.dialog;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment;
import com.oversea.ab_firstarea.haiwai.AppsFlyerControl;
import com.oversea.ab_firstarea.haiwai.FirebaseControl;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.newlogin.adapter.AgreementAdapter;
import com.oversea.ab_firstarea.newlogin.bean.AgreementBean;
import com.oversea.ab_firstarea.newlogin.impl.AgreementDialogCallback;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.CommonAdapter;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.DensityUtil;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class Lxhw_AgreementDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    private AgreementAdapter agreementAdapter;
    private List<AgreementBean> agreementBeanList = new ArrayList();
    private Button btnAgreementOk;
    private Button btnAgreementOkAll;
    private AgreementDialogCallback callback;
    private ExpandableListView expandableListView;
    private ListView lvAgreement;
    private CommonAdapter<AgreementBean> mAdapter;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xagreement_layout";
    }

    public void setAgreementCallback(AgreementDialogCallback agreementDialogCallback) {
        this.callback = agreementDialogCallback;
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        int i;
        int dip2px;
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        if (displayMetrics.widthPixels >= displayMetrics.heightPixels) {
            i = displayMetrics.heightPixels;
            dip2px = DensityUtil.dip2px(this.mContext, 270.0f);
        } else {
            i = (int) (displayMetrics.widthPixels * 0.95d);
            dip2px = DensityUtil.dip2px(this.mContext, 270.0f);
        }
        getDialog().getWindow().setLayout(i, dip2px);
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        setCancelable(false);
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "btn_agreement_ok"));
        this.btnAgreementOk = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "btn_agreement_all_ok"));
        this.btnAgreementOkAll = button2;
        button2.setOnClickListener(this);
        initExpandableListView(view);
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_AGREEMENT_SHOW);
    }

    private void initExpandableListView(View view) {
        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(Util.getIdByName(this.mContext, "id", "listView"));
        this.expandableListView = expandableListView;
        expandableListView.setVisibility(0);
        if (InitBean.getInstance().getTerm_infos() != null) {
            for (int i = 0; i < InitBean.getInstance().getTerm_infos().size(); i++) {
                InitBean.Term_infos term_infos = InitBean.getInstance().getTerm_infos().get(i);
                AgreementBean agreementBean = new AgreementBean();
                if (term_infos != null) {
                    agreementBean.setAgreement(term_infos.getTitle());
                    agreementBean.setUrl(term_infos.getContent_url());
                    agreementBean.setNecessary(term_infos.getIs_validate() == 1);
                    agreementBean.setChecked(true);
                    if (term_infos.getChildren() != null) {
                        ArrayList arrayList = new ArrayList();
                        for (int i2 = 0; i2 < term_infos.getChildren().size(); i2++) {
                            InitBean.Term_infos.Children children = term_infos.getChildren().get(i2);
                            if (children != null) {
                                arrayList.add(new AgreementBean.Children(children.getTitle(), children.getContent_url(), true, children.getIs_validate() == 1));
                            }
                        }
                        agreementBean.setChildren(arrayList);
                    }
                }
                this.agreementBeanList.add(agreementBean);
            }
            if (InitBean.getInstance().getIs_in_european_union() == 1) {
                AgreementBean agreementBean2 = new AgreementBean();
                agreementBean2.setAgreement("Personalized Advertising");
                agreementBean2.setUrl("");
                agreementBean2.setNecessary(true);
                agreementBean2.setChecked(true);
                this.agreementBeanList.add(agreementBean2);
            }
        }
        AgreementAdapter agreementAdapter = new AgreementAdapter(this.mContext, this.agreementBeanList);
        this.agreementAdapter = agreementAdapter;
        this.expandableListView.setAdapter(agreementAdapter);
        this.agreementAdapter.setCheckBoxListener(new AgreementAdapter.CheckBoxListener() { // from class: com.oversea.ab_firstarea.newlogin.dialog.Lxhw_AgreementDialog.1
            @Override // com.oversea.ab_firstarea.newlogin.adapter.AgreementAdapter.CheckBoxListener
            public void checkStateListener(int i3, boolean z) {
                if (((AgreementBean) Lxhw_AgreementDialog.this.agreementBeanList.get(i3)).getChildren() != null) {
                    for (int i4 = 0; i4 < ((AgreementBean) Lxhw_AgreementDialog.this.agreementBeanList.get(i3)).getChildren().size(); i4++) {
                        ((AgreementBean) Lxhw_AgreementDialog.this.agreementBeanList.get(i3)).getChildren().get(i4).setChecked(z);
                    }
                }
                Lxhw_AgreementDialog.this.agreementAdapter.reFreshData(Lxhw_AgreementDialog.this.agreementBeanList);
                Lxhw_AgreementDialog.this.notifyCheckedState();
            }

            @Override // com.oversea.ab_firstarea.newlogin.adapter.AgreementAdapter.CheckBoxListener
            public void checkStateListener(int i3, int i4, boolean z) {
                boolean z2;
                int i5 = 0;
                while (true) {
                    if (i5 >= ((AgreementBean) Lxhw_AgreementDialog.this.agreementBeanList.get(i3)).getChildren().size()) {
                        z2 = true;
                        break;
                    } else {
                        if (!((AgreementBean) Lxhw_AgreementDialog.this.agreementBeanList.get(i3)).getChildren().get(i5).isChecked()) {
                            z2 = false;
                            break;
                        }
                        i5++;
                    }
                }
                if (z2) {
                    ((AgreementBean) Lxhw_AgreementDialog.this.agreementBeanList.get(i3)).setChecked(true);
                } else {
                    ((AgreementBean) Lxhw_AgreementDialog.this.agreementBeanList.get(i3)).setChecked(false);
                }
                Lxhw_AgreementDialog.this.agreementAdapter.reFreshData(Lxhw_AgreementDialog.this.agreementBeanList);
                Lxhw_AgreementDialog.this.notifyCheckedState();
            }
        });
        for (int i3 = 0; i3 < this.agreementBeanList.size(); i3++) {
            this.expandableListView.expandGroup(i3);
        }
        this.expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { // from class: com.oversea.ab_firstarea.newlogin.dialog.Lxhw_AgreementDialog.2
            @Override // android.widget.ExpandableListView.OnGroupClickListener
            public boolean onGroupClick(ExpandableListView expandableListView2, View view2, int i4, long j) {
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyCheckedState() {
        if (isNecessaryCheck()) {
            this.btnAgreementOk.setBackground(this.mContext.getResources().getDrawable(Util.getIdByName(this.mContext, "drawable", "drhw_shape_black")));
            this.btnAgreementOk.setTextColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_white")));
        } else {
            this.btnAgreementOk.setBackground(this.mContext.getResources().getDrawable(Util.getIdByName(this.mContext, "drawable", "drhw_shape_while")));
            this.btnAgreementOk.setTextColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_color_c999999")));
        }
    }

    private boolean isNecessaryCheck() {
        for (AgreementBean agreementBean : this.agreementBeanList) {
            if (agreementBean.isNecessary() && !agreementBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.btnAgreementOk) {
            if (!isNecessaryCheck()) {
                ToastUtils.toastShow(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_agreement_check_tip")));
                return;
            }
            Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_AGREEMENT_CLICK_OK);
        } else if (view == this.btnAgreementOkAll) {
            Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.PC_FF_AGREEMENT_CLICK_ALL_CONFIRM);
        }
        if (InitBean.getInstance().getIs_in_european_union() == 1) {
            boolean booleanValue = ImageUtils.getStringKeyForBoolValue(this.mContext, Constants.SDK_GDPR_ANALYTICS, true).booleanValue();
            boolean booleanValue2 = ImageUtils.getStringKeyForBoolValue(this.mContext, Constants.SDK_GDPR_STORAGE, true).booleanValue();
            boolean booleanValue3 = ImageUtils.getStringKeyForBoolValue(this.mContext, Constants.SDK_GDPR_USER_DATA, true).booleanValue();
            boolean booleanValue4 = ImageUtils.getStringKeyForBoolValue(this.mContext, Constants.SDK_GDPR_PERSONALIZATION, true).booleanValue();
            FirebaseControl.getInstance().setConsent(booleanValue, booleanValue2, booleanValue3, booleanValue4);
            AppsFlyerControl.getInstance().setConsentAndStart(this.mContext, true, booleanValue3, booleanValue4);
        }
        ImageUtils.setSharePreferences((Context) this.mContext, Constants.FF_SDK_FIRST_INSTALL, true);
        AgreementDialogCallback agreementDialogCallback = this.callback;
        if (agreementDialogCallback != null) {
            agreementDialogCallback.onAgreementCallback();
        }
        dismissAllowingStateLoss();
    }
}
