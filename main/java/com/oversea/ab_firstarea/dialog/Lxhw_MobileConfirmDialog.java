package com.oversea.ab_firstarea.dialog;

import android.app.Activity;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.oversea.ab_firstarea.dpresenter.PresenterMobileConfirm;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_MobileConfirmPresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_MobileConfirmView;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.IsFastClickUtils;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Lxhw_MobileConfirmDialog extends Lxhw_BaseDialogFragment implements Lxhw_MobileConfirmView, View.OnClickListener {
    private static List<String> mAreaList;
    private TextView area_tv_id;
    private CountDownTimer countDownTimer;
    private TextView hideformterm_tv;
    private CheckBox isAngreement;
    private TelAdapter mAdapter;
    private Activity mContext;
    protected String mEmailaddress;
    protected String mEmailcode;
    private LinearLayout mLayout;
    private ListView mListView;
    private float mWith;
    private TextView platformterm_tv;
    private PopupWindow pop;
    private PresenterMobileConfirm presenter;
    private ImageView tw_close_dia_iv;
    private Button tw_confirm_btn;
    private TextView tw_get_verification_tv;
    private EditText tw_input_email_et;
    private EditText tw_input_verification_et;
    private ImageView tw_login_dropdown_icon;
    private String TAG = "MobileConfirmDialog";
    private String telArea = "";
    private JSONObject jsonArea = null;
    private boolean isShow = false;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xmobile_confirm_dialog";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.presenter = new Lxhw_MobileConfirmPresenterImpl(this.mContext, this);
        ImageView imageView = (ImageView) view.findViewById(getId("tw_sdk_back_iv"));
        this.tw_close_dia_iv = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_close_dia_iv, 15);
        this.tw_input_email_et = (EditText) view.findViewById(getId("tw_input_email_et"));
        this.tw_input_verification_et = (EditText) view.findViewById(getId("tw_input_verification_et"));
        this.tw_input_email_et.setLayerType(2, null);
        this.tw_input_verification_et.setLayerType(2, null);
        TextView textView = (TextView) view.findViewById(getId("tw_get_verification_tv"));
        this.tw_get_verification_tv = textView;
        textView.setOnClickListener(this);
        Button button = (Button) view.findViewById(getId("tw_confirm_btn"));
        this.tw_confirm_btn = button;
        button.setOnClickListener(this);
        TextView textView2 = (TextView) view.findViewById(getId("area_tv_id"));
        this.area_tv_id = textView2;
        textView2.setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "area_layout_id"));
        this.mLayout = linearLayout;
        linearLayout.setOnClickListener(this);
        ImageView imageView2 = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_login_dropdown_icon"));
        this.tw_login_dropdown_icon = imageView2;
        imageView2.setOnClickListener(this);
        CheckBox checkBox = (CheckBox) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_account_register_is_agree"));
        this.isAngreement = checkBox;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_MobileConfirmDialog.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            }
        });
        this.countDownTimer = new CountDownTimer(60000L, 1000L) { // from class: com.oversea.ab_firstarea.dialog.Lxhw_MobileConfirmDialog.2
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (Lxhw_MobileConfirmDialog.this.mContext != null) {
                    String string = Lxhw_MobileConfirmDialog.this.mContext.getString(Util.getIdByName(Lxhw_MobileConfirmDialog.this.mContext, TypedValues.Custom.S_STRING, "tw_send_second"));
                    Lxhw_MobileConfirmDialog.this.tw_get_verification_tv.setClickable(false);
                    Lxhw_MobileConfirmDialog.this.tw_get_verification_tv.setText(string + (j / 1000) + ExifInterface.LATITUDE_SOUTH);
                }
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (Lxhw_MobileConfirmDialog.this.mContext != null) {
                    Lxhw_MobileConfirmDialog.this.tw_get_verification_tv.setText(Lxhw_MobileConfirmDialog.this.mContext.getString(Util.getIdByName(Lxhw_MobileConfirmDialog.this.mContext, TypedValues.Custom.S_STRING, "tw_send_second")));
                    Lxhw_MobileConfirmDialog.this.tw_get_verification_tv.setTextColor(Lxhw_MobileConfirmDialog.this.mContext.getResources().getColor(Util.getIdByName(Lxhw_MobileConfirmDialog.this.mContext, TypedValues.Custom.S_COLOR, "tw_sdk_background_font_color")));
                    Lxhw_MobileConfirmDialog.this.tw_get_verification_tv.setClickable(true);
                }
            }
        };
        this.hideformterm_tv = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "hideformterm_tv"));
        this.platformterm_tv = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "platformterm_tv"));
        this.hideformterm_tv.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_MobileConfirmDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (InitBean.getInstance().getTerm_info() == null) {
                    Lxhw_DialogManage.getInstance().showBigTextCommon(Lxhw_MobileConfirmDialog.this.mContext, "2");
                } else if (TextUtils.isEmpty(InitBean.getInstance().getTerm_info().getTerm_privacy_url())) {
                    Lxhw_DialogManage.getInstance().showBigTextCommon(Lxhw_MobileConfirmDialog.this.mContext, "2");
                } else {
                    Lxhw_DialogManage.getInstance().showWebCommon(Lxhw_MobileConfirmDialog.this.mContext, InitBean.getInstance().getTerm_info().getTerm_privacy_url(), "");
                }
            }
        });
        this.platformterm_tv.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_MobileConfirmDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (InitBean.getInstance().getTerm_info() == null) {
                    Lxhw_DialogManage.getInstance().showBigTextCommon(Lxhw_MobileConfirmDialog.this.mContext, "1");
                } else if (TextUtils.isEmpty(InitBean.getInstance().getTerm_info().getTerm_service_url())) {
                    Lxhw_DialogManage.getInstance().showBigTextCommon(Lxhw_MobileConfirmDialog.this.mContext, "1");
                } else {
                    Lxhw_DialogManage.getInstance().showWebCommon(Lxhw_MobileConfirmDialog.this.mContext, InitBean.getInstance().getTerm_info().getTerm_service_url(), "");
                }
            }
        });
        try {
            Activity activity = this.mContext;
            this.jsonArea = new JSONObject(activity.getString(Util.getIdByName(activity, TypedValues.Custom.S_STRING, "tw_area_tel")));
            mAreaList = new ArrayList();
            JSONObject jSONObject = this.jsonArea;
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    mAreaList.add(keys.next());
                }
            }
        } catch (Throwable unused) {
        }
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, "custom_sdk_phone_show");
    }

    private int getId(String str) {
        return Util.getIdByName(this.mContext, "id", str);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String string;
        if (view == this.tw_close_dia_iv) {
            dismissDialog();
            return;
        }
        String str = "86";
        if (this.tw_get_verification_tv == view) {
            if (TextUtils.isEmpty(this.tw_input_email_et.getText())) {
                Activity activity = this.mContext;
                Toast.makeText(this.mContext, activity.getString(Util.getIdByName(activity, TypedValues.Custom.S_STRING, "tw_int_telcode")), 0).show();
                return;
            }
            if (TextUtils.isEmpty(this.telArea)) {
                Activity activity2 = this.mContext;
                Toast.makeText(this.mContext, activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "tw_tel_area_null")), 0).show();
                return;
            }
            if (IsFastClickUtils.isFastClick(Lxhw_Platform.getInstance().fastClickTime)) {
                return;
            }
            this.mEmailaddress = this.tw_input_email_et.getText().toString().trim();
            JSONObject jSONObject = this.jsonArea;
            if (jSONObject != null) {
                try {
                    string = jSONObject.getString(this.telArea);
                } catch (JSONException e) {
                    e = e;
                }
                try {
                    if (!TextUtils.isEmpty(string)) {
                        str = string;
                    }
                } catch (JSONException e2) {
                    e = e2;
                    str = string;
                    e.printStackTrace();
                    this.presenter.doBindPhoneSendCode(str, this.mEmailaddress);
                    return;
                }
            }
            this.presenter.doBindPhoneSendCode(str, this.mEmailaddress);
            return;
        }
        if (this.tw_confirm_btn == view) {
            if (!this.isAngreement.isChecked()) {
                Activity activity3 = this.mContext;
                Toast.makeText(this.mContext, activity3.getString(Util.getIdByName(activity3, TypedValues.Custom.S_STRING, "tw_aggre_auth")), 0).show();
                return;
            }
            if (TextUtils.isEmpty(this.tw_input_verification_et.getText())) {
                Activity activity4 = this.mContext;
                Toast.makeText(this.mContext, activity4.getString(Util.getIdByName(activity4, TypedValues.Custom.S_STRING, "tw_check_code_nil")), 0).show();
                return;
            }
            JSONObject jSONObject2 = this.jsonArea;
            if (jSONObject2 != null) {
                try {
                    String string2 = jSONObject2.getString(this.telArea);
                    try {
                        if (!TextUtils.isEmpty(string2)) {
                            str = string2;
                        }
                    } catch (JSONException e3) {
                        e = e3;
                        str = string2;
                        e.printStackTrace();
                        this.presenter.doBindPhone(str, this.mEmailaddress, this.tw_input_verification_et.getText().toString().trim());
                        Lxhw_DialogManage.getInstance().showDialog();
                        return;
                    }
                } catch (JSONException e4) {
                    e = e4;
                }
            }
            this.presenter.doBindPhone(str, this.mEmailaddress, this.tw_input_verification_et.getText().toString().trim());
            Lxhw_DialogManage.getInstance().showDialog();
            return;
        }
        if (this.mLayout == view) {
            clickAreaMore();
        } else if (this.tw_login_dropdown_icon == view) {
            clickAreaMore();
        }
    }

    public void startCountDown() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer == null) {
            return;
        }
        countDownTimer.start();
        this.tw_get_verification_tv.setTextColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_hint_text_black")));
    }

    public void dismissDialog() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
        dismissAllowingStateLoss();
    }

    private void clickAreaMore() {
        List<String> list = mAreaList;
        if (list == null || list.size() < 1) {
            return;
        }
        PopupWindow popupWindow = this.pop;
        if (popupWindow == null) {
            if (this.mAdapter == null) {
                this.mAdapter = new TelAdapter();
                ListView listView = new ListView(this.mContext);
                this.mListView = listView;
                listView.setDividerHeight(1);
                this.mListView.setDivider(this.mContext.getApplicationContext().getResources().getDrawable(Util.getIdByName(this.mContext, "drawable", "drhw_divider")));
                this.pop = new PopupWindow(this.mListView, this.mLayout.getWidth(), -2);
                this.mListView.setAdapter((ListAdapter) this.mAdapter);
                this.pop.showAsDropDown(this.mLayout);
                this.isShow = true;
                return;
            }
            return;
        }
        boolean z = this.isShow;
        if (z) {
            popupWindow.dismiss();
            this.isShow = false;
        } else {
            if (z) {
                return;
            }
            popupWindow.showAsDropDown(this.mLayout);
            this.isShow = true;
        }
    }

    public void adapterShut() {
        this.isShow = false;
        TelAdapter telAdapter = this.mAdapter;
        if (telAdapter != null) {
            telAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, String str2) {
        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), str2);
        AreaSdk.getInstance().userBindResult();
        dismissDialog();
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_MobileConfirmView
    public void onReqCodeSuccess() {
        startCountDown();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class TelAdapter extends BaseAdapter {
        private String TAG = "TelAdapter";
        private LayoutInflater mInflater;

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public TelAdapter() {
            this.mInflater = (LayoutInflater) Lxhw_MobileConfirmDialog.this.mContext.getSystemService("layout_inflater");
            Log.i(this.TAG, "mLoginInfoList size:" + Lxhw_MobileConfirmDialog.mAreaList.size());
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return Lxhw_MobileConfirmDialog.mAreaList.size();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            Holder holder;
            Log.i(this.TAG, "getView position:" + i);
            final String str = (String) Lxhw_MobileConfirmDialog.mAreaList.get(i);
            if (view == null) {
                view = this.mInflater.inflate(Util.getIdByName(Lxhw_MobileConfirmDialog.this.mContext, "layout", "drhw_xtel_area_popup"), (ViewGroup) null);
                holder = new Holder();
                holder.view = (TextView) view.findViewById(Util.getIdByName(Lxhw_MobileConfirmDialog.this.mContext, "id", "tw_tel_area_tv"));
                holder.image = (ImageView) view.findViewById(Util.getIdByName(Lxhw_MobileConfirmDialog.this.mContext, "id", "tw_area_is_select"));
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            if (holder != null) {
                view.setId(i);
                holder.setId(i);
                holder.view.setText(str);
                holder.view.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_MobileConfirmDialog.TelAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Lxhw_MobileConfirmDialog.this.pop.dismiss();
                        Lxhw_MobileConfirmDialog.this.telArea = str;
                        Lxhw_MobileConfirmDialog.this.area_tv_id.setText(str);
                        Lxhw_MobileConfirmDialog.this.isShow = false;
                        Lxhw_MobileConfirmDialog.this.mAdapter.notifyDataSetChanged();
                    }
                });
            }
            return view;
        }

        /* loaded from: classes.dex */
        class Holder {
            ImageView image;
            TextView view;

            Holder() {
            }

            void setId(int i) {
                this.view.setId(i);
                this.image.setId(i);
            }
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
    }
}
