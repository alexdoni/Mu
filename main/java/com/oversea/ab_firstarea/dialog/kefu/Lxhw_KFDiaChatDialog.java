package com.oversea.ab_firstarea.dialog.kefu;

import android.content.ClipboardManager;
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
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.internal.ServerProtocol;
import com.oversea.ab_firstarea.camearAndphoto.Lxhw_CamearCallBack;
import com.oversea.ab_firstarea.camearAndphoto.Lxhw_CamearPhotoControl;
import com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dialog.adapter.Lxhw_KFDiaChatAdapter;
import com.oversea.ab_firstarea.dpresenter.PresenterSimple;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_SimplePresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_BaseView;
import com.oversea.ab_firstarea.haiwai.GooglePlayControl;
import com.oversea.ab_firstarea.net.model.IssueMsgListBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstarea.utils.CommonAdapter;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstarea.utils.osshandle.OssCallBack;
import com.oversea.ab_firstarea.utils.osshandle.OssHandel;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.loading.LoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.IsFastClickUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Lxhw_KFDiaChatDialog extends Lxhw_BaseDialogFragment implements Lxhw_BaseView<String>, View.OnClickListener {
    Bitmap bitmapP;
    Bundle bundle;
    private Lxhw_KFDiaChatAdapter diaChatAdapter;
    private IssueMsgListBean listBean;
    private ListView mListView;
    private PresenterSimple presenter;
    ListView tw_email_lv;
    private TextView tw_issue_lv;
    private ImageView tw_photo_iv;
    private EditText tw_question_et;
    ImageView tw_sdk_back_iv;
    private Button tw_submit;
    LinearLayout tw_tip_layout;
    private CommonAdapter<IssueMsgListBean.Issue_msg_list> typeAdapter;
    private String TAG = "KFDiaChatDialog";
    private String content = "";
    private String issue_id = "";
    private String state = "";
    String msg_image_url = "";
    Lxhw_CamearCallBack.CamearCallBackListener camearCallBackListener = new Lxhw_CamearCallBack.CamearCallBackListener() { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFDiaChatDialog.2
        @Override // com.oversea.ab_firstarea.camearAndphoto.Lxhw_CamearCallBack.CamearCallBackListener
        public void onCCBResult(Uri uri, Bitmap bitmap) {
            if (bitmap != null) {
                LLog.v_noControl("**onCCBResult have");
                if (ManageBean.getInstance().getaCloudStSBean().getData().getOss() != null) {
                    Lxhw_KFDiaChatDialog.this.tw_photo_iv.setImageBitmap(bitmap);
                    if (uri == null) {
                        LLog.e_noControl("**onCCBResult uri null");
                        return;
                    }
                    Lxhw_KFDiaChatDialog.this.mContext.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFDiaChatDialog.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            LoadingDialog.showDialogForLoading(Lxhw_KFDiaChatDialog.this.mContext);
                        }
                    });
                    OssHandel.getInstance().judgeIsUploadFile("customer_service/issue_msg_send", Util.getAndroidID(Lxhw_XSDK.getInstance().getContext()) + GooglePlayControl.getInstance().getGAid() + uri.getPath(), uri, Lxhw_KFDiaChatDialog.this.ossCallBack);
                    Lxhw_KFDiaChatDialog.this.msg_image_url = OssHandel.getInstance().presignUrl();
                    return;
                }
                Lxhw_AreaPlatform.getInstance().getManageSomeRequestModel().acloudsts();
                return;
            }
            LLog.e_Control("**onCCBResult no have");
        }
    };
    OssCallBack ossCallBack = new OssCallBack() { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFDiaChatDialog.3
        @Override // com.oversea.ab_firstarea.utils.osshandle.OssCallBack
        public void onChatResult(boolean z) {
            LoadingDialog.cancelDialogForLoading();
            if (z) {
                LLog.v_noControl("onChatResult图片上传成功");
                Lxhw_KFDiaChatDialog.this.submitResponse(2, "");
            } else {
                LLog.v_noControl("onChatResult图片上传失败");
            }
        }
    };

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xkfdiachat_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.msg_image_url = "";
        this.presenter = new Lxhw_SimplePresenterImpl(this.mContext, this);
        Bundle arguments = getArguments();
        this.bundle = arguments;
        if (arguments != null) {
            this.issue_id = arguments.getString("issue_id");
            this.state = this.bundle.getString(ServerProtocol.DIALOG_PARAM_STATE);
        }
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_sdk_back_iv, 15);
        TextView textView = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_issue_lv"));
        this.tw_issue_lv = textView;
        textView.setText(this.issue_id);
        this.tw_issue_lv.setOnClickListener(this);
        ImageView imageView2 = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_photo_iv"));
        this.tw_photo_iv = imageView2;
        imageView2.setOnClickListener(this);
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_submit"));
        this.tw_submit = button;
        button.setOnClickListener(this);
        this.tw_question_et = (EditText) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_question_et"));
        if (!TextUtils.isEmpty(this.state) && this.state.equals("3")) {
            this.tw_photo_iv.setVisibility(8);
            this.tw_question_et.setVisibility(8);
            this.tw_submit.setVisibility(8);
        }
        Lxhw_DialogManage.getInstance().showDialog();
        this.mListView = (ListView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_diachat_lv"));
        Lxhw_KFDiaChatAdapter lxhw_KFDiaChatAdapter = new Lxhw_KFDiaChatAdapter(this.mContext);
        this.diaChatAdapter = lxhw_KFDiaChatAdapter;
        this.mListView.setAdapter((ListAdapter) lxhw_KFDiaChatAdapter);
        getIssueResponse();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_sdk_back_iv) {
            dismiss();
            return;
        }
        if (view == this.tw_submit) {
            String obj = this.tw_question_et.getText().toString();
            if (TextUtils.isEmpty(obj)) {
                return;
            }
            if (IsFastClickUtils.isFastClick(500L)) {
                LLog.e_Control("chat 多次点击，返回...................");
                return;
            } else {
                submitResponse(1, obj);
                return;
            }
        }
        if (view == this.tw_photo_iv) {
            openImage();
            return;
        }
        if (view == this.tw_issue_lv) {
            try {
                ((ClipboardManager) getActivity().getSystemService("clipboard")).setText(this.issue_id);
                ToastUtils.toastLongShow(getActivity(), this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_copy_success")));
            } catch (Throwable th) {
                Log.e("CLIPBOARD_SERVICE", th.toString());
            }
        }
    }

    private void getIssueResponse() {
        HashMap hashMap = new HashMap();
        hashMap.put("issue_id", this.issue_id);
        this.presenter.doRequestComAddHead(AreaBaseService.CUSTOMERISSUEMSGLIST_ROUTE, AreaBaseService.CUSTOMERISSUEMSGLIST_URL, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void submitResponse(int i, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("issue_id", this.issue_id);
        if (i == 1) {
            if (TextUtils.isEmpty(str)) {
                LLog.v_noControl("submitResponse content null");
                return;
            }
            hashMap.put("msg_type", 1);
            hashMap.put("msg_text", str);
            hashMap.put("msg_image_url", "");
            this.content = str;
        } else if (i == 2) {
            if (TextUtils.isEmpty(this.msg_image_url)) {
                LLog.v_noControl("submitResponse msg_image_url  null");
                return;
            }
            hashMap.put("msg_type", 2);
            hashMap.put("msg_text", "");
            hashMap.put("msg_image_url", this.msg_image_url);
            this.content = "";
        }
        this.mContext.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_KFDiaChatDialog.1
            @Override // java.lang.Runnable
            public void run() {
                LoadingDialog.showDialogForLoading(Lxhw_KFDiaChatDialog.this.mContext);
            }
        });
        this.presenter.doRequestComAddHead(AreaBaseService.CUSTOMERISSUEMSGLISTSEND_ROUTE, AreaBaseService.CUSTOMERISSUEMSGLISTSEND_URL, hashMap);
    }

    public void openImage() {
        this.msg_image_url = "";
        Lxhw_CamearPhotoControl.getInstance().autoObtainStoragePermission(getActivity(), this.camearCallBackListener);
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, String str2) {
        try {
            LoadingDialog.cancelDialogForLoading();
            if (str.equals(AreaBaseService.CUSTOMERISSUEMSGLIST_ROUTE)) {
                IssueMsgListBean issueMsgListBean = (IssueMsgListBean) JsonUtility.jsonToObject(IssueMsgListBean.class, str2);
                this.listBean = issueMsgListBean;
                if (issueMsgListBean == null || issueMsgListBean.getData().getIssue_msg_list() == null || this.listBean.getData().getIssue_msg_list().size() <= 0) {
                    return;
                }
                this.diaChatAdapter.setData(this.listBean.getData().getIssue_msg_list());
                return;
            }
            if (str.equals(AreaBaseService.CUSTOMERISSUEMSGLISTSEND_ROUTE)) {
                IssueMsgListBean.Issue_msg_list issue_msg_list = new IssueMsgListBean.Issue_msg_list();
                if (!TextUtils.isEmpty(this.msg_image_url)) {
                    issue_msg_list.setMsg_image_url(new JSONObject(new JSONObject(str2).getString("data")).getString("msg_image_url"));
                    this.msg_image_url = "";
                    issue_msg_list.setMsg_text("");
                    this.tw_photo_iv.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_photoaccount"));
                } else {
                    this.tw_question_et.setText("");
                    issue_msg_list.setMsg_text(this.content);
                }
                this.listBean.getData().getIssue_msg_list().add(issue_msg_list);
                this.diaChatAdapter.setData(this.listBean.getData().getIssue_msg_list());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        LoadingDialog.cancelDialogForLoading();
        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        OssHandel.getInstance().release();
    }
}
