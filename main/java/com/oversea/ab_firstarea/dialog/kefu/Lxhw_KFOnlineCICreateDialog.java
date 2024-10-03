package com.oversea.ab_firstarea.dialog.kefu;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.oversea.ab_firstarea.camearAndphoto.Lxhw_CamearCallBack;
import com.oversea.ab_firstarea.camearAndphoto.Lxhw_CamearPhotoControl;
import com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dpresenter.PresenterSimple;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_SimplePresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_BaseView;
import com.oversea.ab_firstarea.haiwai.GooglePlayControl;
import com.oversea.ab_firstarea.net.model.IssueTypeListBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstarea.utils.CommonAdapter;
import com.oversea.ab_firstarea.utils.ViewHolder;
import com.oversea.ab_firstarea.utils.osshandle.OssCallBack;
import com.oversea.ab_firstarea.utils.osshandle.OssHandel;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.loading.LoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.IsFastClickUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Lxhw_KFOnlineCICreateDialog extends Lxhw_BaseDialogFragment implements Lxhw_BaseView<String>, View.OnClickListener {
    Bitmap bitmapP;
    Bundle bundle;
    IssueTypeListBean.Issue_type_list chooseDateBean;
    private ListView mListView;
    private PopupWindow pop;
    private PresenterSimple<String> presenter;
    TextView tw_kf_tv;
    EditText tw_kfcontent_et;
    LinearLayout tw_kfcontent_layout;
    EditText tw_kftile_et;
    private LinearLayout tw_kftip_layout;
    LinearLayout tw_kftype_layout;
    ImageView tw_kftype_more;
    ImageView tw_phone_iv;
    TextView tw_phone_tip2;
    ImageView tw_sdk_back_iv;
    Button tw_submit;
    private CommonAdapter<IssueTypeListBean.Issue_type_list> typeAdapter;
    String TAG = "Lhwl_KFOnlineCICreateDialog";
    private boolean isShow = false;
    String msg_image_url = "";
    Uri image_local_uri = null;
    Lxhw_CamearCallBack.CamearCallBackListener camearCallBackListener = new Lxhw_CamearCallBack.CamearCallBackListener() { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFOnlineCICreateDialog.2
        @Override // com.oversea.ab_firstarea.camearAndphoto.Lxhw_CamearCallBack.CamearCallBackListener
        public void onCCBResult(Uri uri, Bitmap bitmap) {
            if (bitmap != null) {
                if (ManageBean.getInstance().getaCloudStSBean().getData().getOss() == null) {
                    Lxhw_AreaPlatform.getInstance().getManageSomeRequestModel().acloudsts();
                    return;
                }
                Lxhw_KFOnlineCICreateDialog.this.bitmapP = bitmap;
                Lxhw_KFOnlineCICreateDialog.this.tw_kftip_layout.setVisibility(0);
                LLog.v_noControl("**onCCBResult have");
                Lxhw_KFOnlineCICreateDialog.this.tw_phone_iv.setImageBitmap(bitmap);
                Lxhw_KFOnlineCICreateDialog.this.image_local_uri = uri;
                return;
            }
            LLog.v_noControl("**onCCBResult no have");
        }
    };
    OssCallBack ossCallBack = new OssCallBack() { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFOnlineCICreateDialog.3
        @Override // com.oversea.ab_firstarea.utils.osshandle.OssCallBack
        public void onChatResult(boolean z) {
            LoadingDialog.cancelDialogForLoading();
            if (z) {
                LLog.v_noControl("onCiCreate图片上传成功");
                Lxhw_KFOnlineCICreateDialog.this.submitMsg();
            } else {
                LLog.v_noControl("onCiCreate图片上传失败");
                ToastUtils.toastShow(Lxhw_KFOnlineCICreateDialog.this.mContext, Util.getString(Lxhw_KFOnlineCICreateDialog.this.mContext, "tw_send_fail"));
            }
        }
    };

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xkfonlinecicreate_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.msg_image_url = "";
        this.image_local_uri = null;
        this.presenter = new Lxhw_SimplePresenterImpl(this.mContext, this);
        this.bundle = getArguments();
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_sdk_back_iv, 15);
        this.tw_kftype_layout = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_kftype_layout"));
        this.tw_kfcontent_layout = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_kfcontent_layout"));
        this.tw_sdk_back_iv.setOnClickListener(this);
        TextView textView = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_kf_tv"));
        this.tw_kf_tv = textView;
        textView.setOnClickListener(this);
        ImageView imageView2 = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_kftype_more"));
        this.tw_kftype_more = imageView2;
        imageView2.setOnClickListener(this);
        this.tw_kftile_et = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_kftile_et"));
        LinearLayout linearLayout = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_kftip_layout"));
        this.tw_kftip_layout = linearLayout;
        linearLayout.setVisibility(4);
        this.tw_kfcontent_et = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_kfcontent_et"));
        this.tw_phone_tip2 = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_phone_tip2"));
        ImageView imageView3 = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_phone_iv"));
        this.tw_phone_iv = imageView3;
        imageView3.setOnClickListener(this);
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_submit"));
        this.tw_submit = button;
        button.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_sdk_back_iv) {
            dismiss();
            return;
        }
        if (view == this.tw_submit) {
            if (this.chooseDateBean == null) {
                Toast.makeText(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_kefu_select_question")), 0).show();
                return;
            }
            if (TextUtils.isEmpty(this.tw_kftile_et.getText().toString())) {
                Toast.makeText(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_kefu_input_title")), 0).show();
                return;
            }
            if (TextUtils.isEmpty(this.tw_kfcontent_et.getText().toString())) {
                Toast.makeText(this.mContext, this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_kefu_input_content")), 0).show();
                return;
            }
            if (IsFastClickUtils.isFastClick(Lxhw_Platform.getInstance().fastClickTime)) {
                Log.e(this.TAG, "多次点击，返回...................");
                return;
            }
            if (this.image_local_uri == null) {
                submitMsg();
                return;
            }
            LoadingDialog.showDialogForLoading(this.mContext);
            OssHandel.getInstance().judgeIsUploadFile("customer_service/issue_commit", Util.getAndroidID(Lxhw_XSDK.getInstance().getContext()) + GooglePlayControl.getInstance().getGAid() + this.image_local_uri.getPath(), this.image_local_uri, this.ossCallBack);
            this.msg_image_url = OssHandel.getInstance().presignUrl();
            return;
        }
        if (view == this.tw_kftype_layout || view == this.tw_kf_tv || view == this.tw_kftype_more) {
            clickMore();
        } else if (view == this.tw_phone_iv) {
            openImage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void submitMsg() {
        String obj = this.tw_kftile_et.getText().toString();
        String obj2 = this.tw_kfcontent_et.getText().toString();
        HashMap hashMap = new HashMap();
        hashMap.put("role_id", Lxhw_Platform.getInstance().userExtraData == null ? "" : Lxhw_Platform.getInstance().userExtraData.getRoleID());
        hashMap.put("role_name", Lxhw_Platform.getInstance().userExtraData == null ? "" : Lxhw_Platform.getInstance().userExtraData.getRoleName());
        hashMap.put("server_id", Lxhw_Platform.getInstance().userExtraData == null ? "" : Lxhw_Platform.getInstance().userExtraData.getServerId());
        hashMap.put("server_name", Lxhw_Platform.getInstance().userExtraData != null ? Lxhw_Platform.getInstance().userExtraData.getServerName() : "");
        hashMap.put("issue_type_id", Integer.valueOf(this.chooseDateBean.getIssue_type_id()));
        hashMap.put("issue_title", obj);
        if (this.bitmapP == null && !TextUtils.isEmpty(obj2)) {
            hashMap.put("msg_type", 1);
        } else if (this.bitmapP != null && TextUtils.isEmpty(obj2)) {
            hashMap.put("msg_type", 2);
            if (!OssHandel.getInstance().isUploadSuccess()) {
                return;
            }
        } else {
            hashMap.put("msg_type", 3);
            if (!OssHandel.getInstance().isUploadSuccess()) {
                return;
            }
        }
        hashMap.put("msg_text", obj2);
        hashMap.put("msg_image_url", this.msg_image_url);
        this.presenter.doRequestComAddHead(AreaBaseService.CUSTOMERISSUECOMMIT_ROUTE, AreaBaseService.CUSTOMERISSUECOMMIT_URL, hashMap);
        Lxhw_DialogManage.getInstance().showDialog();
    }

    private void clickMore() {
        if (this.mContext == null || this.mContext.isFinishing()) {
            LLog.v_noControl("**clickMore mContext==null||mContext.isFinishing()");
        } else {
            this.mContext.runOnUiThread(new RunnableC23421());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFOnlineCICreateDialog$1 */
    /* loaded from: classes.dex */
    public class RunnableC23421 implements Runnable {
        RunnableC23421() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ManageBean.getInstance().getIssueTypeListBean() == null || ManageBean.getInstance().getIssueTypeListBean().getData().getIssue_type_list().size() <= 0) {
                return;
            }
            if (Lxhw_KFOnlineCICreateDialog.this.pop != null) {
                if (Lxhw_KFOnlineCICreateDialog.this.isShow) {
                    Lxhw_KFOnlineCICreateDialog.this.pop.dismiss();
                    Lxhw_KFOnlineCICreateDialog.this.isShow = false;
                    return;
                } else {
                    if (Lxhw_KFOnlineCICreateDialog.this.isShow) {
                        return;
                    }
                    Lxhw_KFOnlineCICreateDialog.this.pop.showAsDropDown(Lxhw_KFOnlineCICreateDialog.this.tw_kftype_layout);
                    Lxhw_KFOnlineCICreateDialog.this.isShow = true;
                    return;
                }
            }
            Lxhw_KFOnlineCICreateDialog.this.mListView = new ListView(Lxhw_KFOnlineCICreateDialog.this.mContext);
            Lxhw_KFOnlineCICreateDialog.this.typeAdapter = new CommonAdapter<IssueTypeListBean.Issue_type_list>(Lxhw_KFOnlineCICreateDialog.this.mContext, Util.getIdByName(Lxhw_KFOnlineCICreateDialog.this.mContext, "layout", "drhw_xkfonlinecitypelist_popup")) { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFOnlineCICreateDialog.1.1
                @Override // com.oversea.ab_firstarea.utils.CommonAdapter
                public void convert(ViewHolder viewHolder, final IssueTypeListBean.Issue_type_list issue_type_list, int i, View view) {
                    viewHolder.setText(Util.getIdByName(this.mContext, "id", "tw_kefu_type_tv"), issue_type_list.getIssue_type_name());
                    viewHolder.setOnClickListener(Util.getIdByName(this.mContext, "id", "tw_kefu_type_tv"), new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFOnlineCICreateDialog.1.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            Lxhw_KFOnlineCICreateDialog.this.pop.dismiss();
                            Lxhw_KFOnlineCICreateDialog.this.isShow = false;
                            Lxhw_KFOnlineCICreateDialog.this.tw_kf_tv.setText(issue_type_list.getIssue_type_name());
                            Lxhw_KFOnlineCICreateDialog.this.chooseDateBean = issue_type_list;
                        }
                    });
                    if (Lxhw_KFOnlineCICreateDialog.this.tw_kf_tv.getText().toString().equals(issue_type_list.getIssue_type_name())) {
                        viewHolder.setBackground(Util.getIdByName(this.mContext, "id", "tw_type_is_select"), Lxhw_KFOnlineCICreateDialog.this.getResources().getDrawable(Util.getIdByName(this.mContext, "drawable", "drhw_kfchattype_background1")));
                    } else {
                        viewHolder.setBackground(Util.getIdByName(this.mContext, "id", "tw_type_is_select"), Lxhw_KFOnlineCICreateDialog.this.getResources().getDrawable(Util.getIdByName(this.mContext, "drawable", "drhw_kfchattype_background2")));
                    }
                }
            };
            Lxhw_KFOnlineCICreateDialog.this.typeAdapter.setDatas(ManageBean.getInstance().getIssueTypeListBean().getData().getIssue_type_list());
            Lxhw_KFOnlineCICreateDialog.this.mListView.setAdapter((ListAdapter) Lxhw_KFOnlineCICreateDialog.this.typeAdapter);
            Lxhw_KFOnlineCICreateDialog.this.mListView.setDividerHeight(0);
            Lxhw_KFOnlineCICreateDialog.this.pop = new PopupWindow(Lxhw_KFOnlineCICreateDialog.this.mListView, Lxhw_KFOnlineCICreateDialog.this.tw_kftype_layout.getWidth(), Lxhw_KFOnlineCICreateDialog.this.tw_kfcontent_layout.getHeight());
            Lxhw_KFOnlineCICreateDialog.this.pop.showAsDropDown(Lxhw_KFOnlineCICreateDialog.this.tw_kftype_layout);
            Lxhw_KFOnlineCICreateDialog.this.isShow = true;
        }
    }

    public void openImage() {
        this.msg_image_url = "";
        this.image_local_uri = null;
        LLog.v_noControl("**openImage");
        Lxhw_CamearPhotoControl.getInstance().autoObtainStoragePermission(getActivity(), this.camearCallBackListener);
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, String str2) {
        dismiss();
        Lxhw_DialogManage.getInstance().enterKFOnlineCenter(getActivity());
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
