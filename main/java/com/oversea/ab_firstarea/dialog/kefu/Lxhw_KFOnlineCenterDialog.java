package com.oversea.ab_firstarea.dialog.kefu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.internal.ServerProtocol;
import com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dpresenter.PresenterKFOnlineCenter;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_KfOnlineCenterPresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_KFOnlineCenterView;
import com.oversea.ab_firstarea.net.model.IssueListBean;
import com.oversea.ab_firstarea.net.model.IssueTypeListBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstarea.utils.CommonAdapter;
import com.oversea.ab_firstarea.utils.ViewHolder;
import com.oversea.ab_firstarea.utils.osshandle.OssHandel;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_KFOnlineCenterDialog extends Lxhw_BaseDialogFragment implements Lxhw_KFOnlineCenterView, View.OnClickListener {
    Bundle bundle;
    private ListView mListView;
    private PresenterKFOnlineCenter presenter;
    ListView tw_email_lv;
    ImageView tw_sdk_back_iv;
    ImageView tw_submit;
    LinearLayout tw_tip_layout;
    private CommonAdapter<IssueListBean.Issue_list> typeAdapter;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xkfonlinecenter_layout";
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, IssueTypeListBean issueTypeListBean) {
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.presenter = new Lxhw_KfOnlineCenterPresenterImpl(this.mContext, this);
        this.bundle = getArguments();
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_sdk_back_iv, 15);
        ImageView imageView2 = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_submit"));
        this.tw_submit = imageView2;
        imageView2.setOnClickListener(this);
        this.tw_email_lv = (ListView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_email_lv"));
        LinearLayout linearLayout = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_tip_layout"));
        this.tw_tip_layout = linearLayout;
        linearLayout.setVisibility(8);
        this.mListView = (ListView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_issue_lv"));
        this.presenter.doRequestCom();
        this.presenter.doIssueList();
        Lxhw_AreaPlatform.getInstance().getManageSomeRequestModel().acloudsts();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_sdk_back_iv) {
            dismiss();
            return;
        }
        if (view == this.tw_submit) {
            if (ManageBean.getInstance().getIssueTypeListBean() != null) {
                Lxhw_DialogManage.getInstance().enterKFOnlineCICreate(this.mContext);
                dismiss();
            } else {
                this.presenter.doRequestCom();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFOnlineCenterDialog$1 */
    /* loaded from: classes.dex */
    public class RunnableC23451 implements Runnable {
        final /* synthetic */ IssueListBean val$bean;

        RunnableC23451(IssueListBean issueListBean) {
            this.val$bean = issueListBean;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.val$bean.getData().getIssue_list() != null && this.val$bean.getData().getIssue_list().size() > 0) {
                Lxhw_KFOnlineCenterDialog.this.typeAdapter = new CommonAdapter<IssueListBean.Issue_list>(Lxhw_KFOnlineCenterDialog.this.mContext, Util.getIdByName(Lxhw_KFOnlineCenterDialog.this.mContext, "layout", "drhw_xkfonlinecenter_item")) { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFOnlineCenterDialog.1.1
                    @Override // com.oversea.ab_firstarea.utils.CommonAdapter
                    public void convert(ViewHolder viewHolder, final IssueListBean.Issue_list issue_list, int i, View view) {
                        viewHolder.setText(Util.getIdByName(this.mContext, "id", "title_tv"), issue_list.getIssue_title());
                        viewHolder.setText(Util.getIdByName(this.mContext, "id", "time_tv"), issue_list.getIssue_time() + "");
                        viewHolder.setText(Util.getIdByName(this.mContext, "id", "ticket_number_tv"), issue_list.getIssue_id() + "");
                        if (issue_list.getIssue_status() == 0 || issue_list.getIssue_status() == 2) {
                            viewHolder.setBackgroundRes(Util.getIdByName(this.mContext, "id", "type_btn"), Util.getIdByName(this.mContext, "drawable", "bluestate"));
                            viewHolder.setText(Util.getIdByName(this.mContext, "id", "type_btn"), this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_kefu_wait")));
                        } else if (issue_list.getIssue_status() == 1) {
                            viewHolder.setBackgroundRes(Util.getIdByName(this.mContext, "id", "type_btn"), Util.getIdByName(this.mContext, "drawable", "drhw_greenstate"));
                            viewHolder.setText(Util.getIdByName(this.mContext, "id", "type_btn"), this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_kefu_reply")));
                        } else if (issue_list.getIssue_status() == 3) {
                            viewHolder.setBackgroundRes(Util.getIdByName(this.mContext, "id", "type_btn"), Util.getIdByName(this.mContext, "drawable", "drhw_redstate"));
                            viewHolder.setText(Util.getIdByName(this.mContext, "id", "type_btn"), this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_kefu_solve")));
                        }
                        viewHolder.setOnClickListener(Util.getIdByName(this.mContext, "id", "item_layout"), new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFOnlineCenterDialog.1.1.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                Log.i("onlineCenterDialog", "  item_layout click");
                                Bundle bundle = new Bundle();
                                bundle.putString("issue_id", issue_list.getIssue_id() + "");
                                bundle.putString(ServerProtocol.DIALOG_PARAM_STATE, issue_list.getIssue_status() + "");
                                Lxhw_DialogManage.getInstance().enterKFDiaChatDialog(Lxhw_KFOnlineCenterDialog.this.getActivity(), bundle);
                            }
                        });
                    }
                };
                Lxhw_KFOnlineCenterDialog.this.mListView.setAdapter((ListAdapter) Lxhw_KFOnlineCenterDialog.this.typeAdapter);
                Lxhw_KFOnlineCenterDialog.this.typeAdapter.setDatas(this.val$bean.getData().getIssue_list());
                return;
            }
            Lxhw_KFOnlineCenterDialog.this.tw_tip_layout.setVisibility(0);
        }
    }

    private void loadItem(IssueListBean issueListBean) {
        this.mContext.runOnUiThread(new RunnableC23451(issueListBean));
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_KFOnlineCenterView
    public void onReqIssueListSuccess(IssueListBean issueListBean) {
        loadItem(issueListBean);
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        OssHandel.getInstance().release();
    }
}
