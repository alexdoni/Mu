package com.oversea.ab_firstarea.dialog;

import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.oversea.ab_firstarea.channel.ProjectType;
import com.oversea.ab_firstarea.dpresenter.PresenterSimple;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_SimplePresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_BaseView;
import com.oversea.ab_firstarea.net.model.GiftCodeGetBean;
import com.oversea.ab_firstarea.net.model.GiftRecordListBean;
import com.oversea.ab_firstarea.net.model.GiftTypeListBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstarea.utils.CommonAdapter;
import com.oversea.ab_firstarea.utils.ViewHolder;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/* loaded from: classes.dex */
public class Lxhw_GiftCenterDialog extends Lxhw_BaseDialogFragment implements Lxhw_BaseView<String> {
    private CommonAdapter<GiftTypeListBean.Gift_list> cAdapter;
    private CommonAdapter<GiftRecordListBean.Gift_code_get_record_list> cRecordAdapter;
    private TextView gift_tv;
    private View gift_view;
    private GiftTypeListBean giftlistBean;
    private TextView history_tv;
    private View history_view;
    private Boolean isChooseGiftCenter = true;
    private PresenterSimple<String> presenter;
    private ImageView tw_close_dia_iv;
    private FrameLayout tw_gift_fl;
    private ListView tw_gift_gv;
    private FrameLayout tw_history_fl;
    private ListView tw_history_gv;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xgift_center_dialog";
    }

    private void initData() {
        if (Lxhw_Platform.getInstance().userExtraData == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("server_id", Lxhw_Platform.getInstance().userExtraData.getServerId());
        hashMap.put("server_name", Lxhw_Platform.getInstance().userExtraData.getServerName());
        hashMap.put("role_id", Lxhw_Platform.getInstance().userExtraData.getRoleID());
        hashMap.put("role_name", Lxhw_Platform.getInstance().userExtraData.getRoleName());
        hashMap.put("timezone", TimeZone.getDefault().getID() == null ? "" : TimeZone.getDefault().getID());
        this.presenter.doRequestComAddHead(AreaBaseService.GIFTLIST_ROUTE, AreaBaseService.GIFTLIST_URL, hashMap);
    }

    private void initRecordData() {
        if (Lxhw_Platform.getInstance().userExtraData == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("server_id", Lxhw_Platform.getInstance().userExtraData.getServerId());
        hashMap.put("server_name", Lxhw_Platform.getInstance().userExtraData.getServerName());
        hashMap.put("role_id", Lxhw_Platform.getInstance().userExtraData.getRoleID());
        hashMap.put("role_name", Lxhw_Platform.getInstance().userExtraData.getRoleName());
        hashMap.put("timezone", TimeZone.getDefault().getID() == null ? "" : TimeZone.getDefault().getID());
        this.presenter.doRequestComAddHead(AreaBaseService.GIFTCODEGETRECORDLIST_ROUTE, AreaBaseService.GIFTCODEGETRECORDLIST_URL, hashMap);
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        if (ManageBean.getInstance().getaCloudStSBean().getData().getOss() == null) {
            Lxhw_AreaPlatform.getInstance().getManageSomeRequestModel().acloudsts();
        }
        this.presenter = new Lxhw_SimplePresenterImpl(this.mContext, this);
        initGiftData(view);
        initHistoryData(view);
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_close_dia_iv"));
        this.tw_close_dia_iv = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_GiftCenterDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Lxhw_GiftCenterDialog.this.dismissAllowingStateLoss();
            }
        });
        TextView textView = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "gift_tv"));
        this.gift_tv = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_GiftCenterDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Lxhw_GiftCenterDialog.this.chooseType(true);
            }
        });
        TextView textView2 = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "history_tv"));
        this.history_tv = textView2;
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_GiftCenterDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Lxhw_GiftCenterDialog.this.chooseType(false);
            }
        });
        this.gift_view = view.findViewById(Util.getIdByName(this.mContext, "id", "gift_view"));
        this.history_view = view.findViewById(Util.getIdByName(this.mContext, "id", "history_view"));
        this.tw_gift_fl = (FrameLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_gift_fl"));
        this.tw_history_fl = (FrameLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_history_fl"));
        initData();
        initRecordData();
        this.tw_gift_fl.setVisibility(0);
        this.tw_history_fl.setVisibility(8);
    }

    private void initGiftData(View view) {
        this.tw_gift_gv = (ListView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_gift_gv"));
        CommonAdapter<GiftTypeListBean.Gift_list> commonAdapter = new CommonAdapter<GiftTypeListBean.Gift_list>(getActivity(), Util.getIdByName(this.mContext, "layout", "drhw_xgift_center_item")) { // from class: com.oversea.ab_firstarea.dialog.Lxhw_GiftCenterDialog.4
            @Override // com.oversea.ab_firstarea.utils.CommonAdapter
            public void convert(ViewHolder viewHolder, final GiftTypeListBean.Gift_list gift_list, int i, View view2) {
                viewHolder.setText(Util.getIdByName(this.mContext, "id", "tw_tv_gift_name"), gift_list.getGift_name());
                String string = this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_youxiaoqi_time"));
                viewHolder.setText(Util.getIdByName(this.mContext, "id", "imit_usetime"), string + gift_list.getEffective_end_time());
                if (TextUtils.isEmpty(gift_list.getEffective_end_time()) || "--".equals(gift_list.getEffective_end_time())) {
                    viewHolder.setVisible(Util.getIdByName(this.mContext, "id", "imit_usetime"), false);
                } else {
                    viewHolder.setVisible(Util.getIdByName(this.mContext, "id", "imit_usetime"), true);
                }
                if (!TextUtils.isEmpty(gift_list.getGift_icon())) {
                    try {
                        if (!gift_list.getGift_icon().startsWith("http") && ManageBean.getInstance().getaCloudStSBean().getData().getOss() != null) {
                            viewHolder.setImageUrl2(Util.getIdByName(this.mContext, "id", "gift_icon_img"), ManageBean.getInstance().getaCloudStSBean().getData().getOss().getImage_domain() + RemoteSettings.FORWARD_SLASH_STRING + gift_list.getGift_icon());
                        } else {
                            viewHolder.setImageUrl2(Util.getIdByName(this.mContext, "id", "gift_icon_img"), gift_list.getGift_icon());
                        }
                    } catch (Exception unused) {
                    }
                } else {
                    if (Util.getSystemLang().equals(LanguageType.SERVER_VI) && Lxhw_AreaPlatform.getInstance().iShowViInfo) {
                        viewHolder.setBackgroundRes(Util.getIdByName(this.mContext, "id", "gift_icon_img"), Util.getIdByName(this.mContext, "drawable", "tplayicon_vi"));
                    } else {
                        viewHolder.setBackgroundRes(Util.getIdByName(this.mContext, "id", "gift_icon_img"), Util.getIdByName(this.mContext, "drawable", "tplayicon"));
                    }
                    if (ProjectType.ZIAN.equals(ProjectType.pType)) {
                        viewHolder.setInVisible(Util.getIdByName(this.mContext, "id", "gift_icon_img"), false);
                    }
                }
                if (gift_list.isGet()) {
                    viewHolder.setBackgroundRes(Util.getIdByName(this.mContext, "id", "tw_gift_center_tv"), Util.getIdByName(this.mContext, "drawable", "drhw_shape_allround_gray"));
                    viewHolder.setClickable(Util.getIdByName(this.mContext, "id", "tw_gift_center_tv"), false);
                    viewHolder.setText(Util.getIdByName(this.mContext, "id", "tw_gift_center_tv"), this.mContext.getString(Util.getIdByName(this.mContext, TypedValues.Custom.S_STRING, "tw_haveget")));
                } else {
                    viewHolder.setBackgroundRes(Util.getIdByName(this.mContext, "id", "tw_gift_center_tv"), Util.getIdByName(this.mContext, "drawable", "drhw_shape_allround_jacinth"));
                    viewHolder.setOnClickListener(Util.getIdByName(this.mContext, "id", "tw_gift_center_tv"), new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_GiftCenterDialog.4.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view3) {
                            Lxhw_GiftCenterDialog.this.getGift(gift_list.getGift_id());
                        }
                    });
                }
            }
        };
        this.cAdapter = commonAdapter;
        this.tw_gift_gv.setAdapter((ListAdapter) commonAdapter);
    }

    private void initHistoryData(View view) {
        this.tw_history_gv = (ListView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_history_gv"));
        CommonAdapter<GiftRecordListBean.Gift_code_get_record_list> commonAdapter = new CommonAdapter<GiftRecordListBean.Gift_code_get_record_list>(this.mContext, Util.getIdByName(this.mContext, "layout", "drhw_xgift_center_item")) { // from class: com.oversea.ab_firstarea.dialog.Lxhw_GiftCenterDialog.5
            @Override // com.oversea.ab_firstarea.utils.CommonAdapter
            public void convert(ViewHolder viewHolder, GiftRecordListBean.Gift_code_get_record_list gift_code_get_record_list, int i, View view2) {
                viewHolder.setText(Util.getIdByName(this.mContext, "id", "tw_tv_gift_name"), gift_code_get_record_list.getGift_name());
                viewHolder.setText(Util.getIdByName(this.mContext, "id", "act_code_tv"), gift_code_get_record_list.getGift_desc());
                if (!TextUtils.isEmpty(gift_code_get_record_list.getGift_desc())) {
                    viewHolder.setVisible(Util.getIdByName(this.mContext, "id", "act_code_tv"), true);
                } else {
                    viewHolder.setVisible(Util.getIdByName(this.mContext, "id", "act_code_tv"), false);
                }
                viewHolder.setVisible(Util.getIdByName(this.mContext, "id", "imit_usetime"), false);
                if (!TextUtils.isEmpty(gift_code_get_record_list.getGift_icon())) {
                    try {
                        if (!gift_code_get_record_list.getGift_icon().startsWith("http") && ManageBean.getInstance().getaCloudStSBean().getData().getOss() != null) {
                            viewHolder.setImageUrl2(Util.getIdByName(this.mContext, "id", "gift_icon_img"), ManageBean.getInstance().getaCloudStSBean().getData().getOss().getImage_domain() + RemoteSettings.FORWARD_SLASH_STRING + gift_code_get_record_list.getGift_icon());
                        } else {
                            viewHolder.setImageUrl2(Util.getIdByName(this.mContext, "id", "gift_icon_img"), gift_code_get_record_list.getGift_icon());
                        }
                    } catch (Exception unused) {
                    }
                } else {
                    if (Util.getSystemLang().equals(LanguageType.SERVER_VI) && Lxhw_AreaPlatform.getInstance().iShowViInfo) {
                        viewHolder.setBackgroundRes(Util.getIdByName(this.mContext, "id", "gift_icon_img"), Util.getIdByName(this.mContext, "drawable", "tplayicon_vi"));
                    } else {
                        viewHolder.setBackgroundRes(Util.getIdByName(this.mContext, "id", "gift_icon_img"), Util.getIdByName(this.mContext, "drawable", "tplayicon"));
                    }
                    if (ProjectType.ZIAN.equals(ProjectType.pType)) {
                        viewHolder.setInVisible(Util.getIdByName(this.mContext, "id", "gift_icon_img"), false);
                    }
                }
                viewHolder.setVisible(Util.getIdByName(this.mContext, "id", "tw_gift_center_tv"), false);
            }
        };
        this.cRecordAdapter = commonAdapter;
        this.tw_history_gv.setAdapter((ListAdapter) commonAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void chooseType(boolean z) {
        if (z) {
            if (this.isChooseGiftCenter.booleanValue()) {
                return;
            }
            this.isChooseGiftCenter = true;
            this.gift_tv.setTextColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "progress_color_2")));
            this.history_tv.setTextColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_dec_text_black")));
            this.gift_view.setBackgroundColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "progress_color_2")));
            this.history_view.setBackgroundColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_main_text_black")));
            this.tw_gift_fl.setVisibility(0);
            this.tw_history_fl.setVisibility(8);
            initData();
            return;
        }
        if (this.isChooseGiftCenter.booleanValue()) {
            this.isChooseGiftCenter = false;
            this.gift_tv.setTextColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_dec_text_black")));
            this.history_tv.setTextColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "progress_color_2")));
            this.gift_view.setBackgroundColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_main_text_black")));
            this.history_view.setBackgroundColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "progress_color_2")));
            this.tw_gift_fl.setVisibility(8);
            this.tw_history_fl.setVisibility(0);
            initRecordData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getGift(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("gift_id", Integer.valueOf(i));
        hashMap.put("server_id", Lxhw_Platform.getInstance().userExtraData.getServerId());
        hashMap.put("server_name", Lxhw_Platform.getInstance().userExtraData.getServerName());
        hashMap.put("role_id", Lxhw_Platform.getInstance().userExtraData.getRoleID());
        hashMap.put("role_name", Lxhw_Platform.getInstance().userExtraData.getRoleName());
        this.presenter.doRequestComAddHead(AreaBaseService.GIFTCODEGET_ROUTE, AreaBaseService.GIFTCODEGET_URL, hashMap);
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, final String str2) {
        if (str.equals(AreaBaseService.GIFTLIST_ROUTE)) {
            this.mContext.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_GiftCenterDialog.6
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Lxhw_GiftCenterDialog.this.giftlistBean = (GiftTypeListBean) JsonUtility.jsonToObject(GiftTypeListBean.class, str2);
                        if (Lxhw_GiftCenterDialog.this.giftlistBean == null || Lxhw_GiftCenterDialog.this.giftlistBean.getData().getGift_list() == null || Lxhw_GiftCenterDialog.this.giftlistBean.getData().getGift_list().size() <= 0 || Lxhw_GiftCenterDialog.this.cAdapter == null) {
                            return;
                        }
                        Lxhw_GiftCenterDialog.this.cAdapter.setDatas(Lxhw_GiftCenterDialog.this.giftlistBean.getData().getGift_list());
                    } catch (Throwable th) {
                        LLog.v_noControl(th.getMessage());
                    }
                }
            });
            return;
        }
        if (str.equals(AreaBaseService.GIFTCODEGETRECORDLIST_ROUTE)) {
            this.mContext.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_GiftCenterDialog.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        GiftRecordListBean giftRecordListBean = (GiftRecordListBean) JsonUtility.jsonToObject(GiftRecordListBean.class, str2);
                        if (giftRecordListBean == null || giftRecordListBean.getData().getGift_code_get_record_list() == null || giftRecordListBean.getData().getGift_code_get_record_list().size() <= 0 || Lxhw_GiftCenterDialog.this.cRecordAdapter == null) {
                            return;
                        }
                        Lxhw_GiftCenterDialog.this.cRecordAdapter.setDatas(giftRecordListBean.getData().getGift_code_get_record_list());
                    } catch (Throwable th) {
                        LLog.v_noControl(th.getMessage());
                    }
                }
            });
            return;
        }
        if (str.equals(AreaBaseService.GIFTCODEGET_ROUTE)) {
            try {
                GiftCodeGetBean giftCodeGetBean = (GiftCodeGetBean) JsonUtility.jsonToObject(GiftCodeGetBean.class, str2);
                if (giftCodeGetBean != null) {
                    ToastUtils.toastShow(Lxhw_XSDK.getInstance().getContext(), giftCodeGetBean.getMessage());
                    AreaSdk.getInstance().giftGetResult(giftCodeGetBean.getData().getGift_id(), giftCodeGetBean.getData().getGift_code());
                }
                GiftTypeListBean giftTypeListBean = this.giftlistBean;
                if (giftTypeListBean != null && giftTypeListBean.getData().getGift_list() != null && this.giftlistBean.getData().getGift_list().size() > 0 && giftCodeGetBean != null && giftCodeGetBean.getData().getGift_id() != 0) {
                    for (int i = 0; i < this.giftlistBean.getData().getGift_list().size(); i++) {
                        if (this.giftlistBean.getData().getGift_list().get(i).getGift_id() == giftCodeGetBean.getData().getGift_id()) {
                            this.giftlistBean.getData().getGift_list().get(i).setGet(true);
                        }
                    }
                    CommonAdapter<GiftTypeListBean.Gift_list> commonAdapter = this.cAdapter;
                    if (commonAdapter != null) {
                        commonAdapter.setDatas(this.giftlistBean.getData().getGift_list());
                        return;
                    }
                    return;
                }
                initData();
            } catch (Throwable th) {
                LLog.v_noControl(th.getMessage());
            }
        }
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        ToastUtils.toastShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
    }

    private String getLocalTime(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            Date parse = simpleDateFormat.parse(str);
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            return simpleDateFormat.format(parse);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
