package com.oversea.ab_firstarea.dialog.kefu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.oversea.ab_firstarea.channel.ProjectType;
import com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment;
import com.oversea.ab_firstarea.utils.CommonAdapter;
import com.oversea.ab_firstarea.utils.Lxhw_TInfo;
import com.oversea.ab_firstarea.utils.ViewHolder;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.xsdk.ab_firstbase.statisics.util.DealTouchDelegate;
import com.xsdk.ab_firstbase.statisics.util.Lxhw_RechargeType;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_TInfoDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    Bundle bundle;
    private CommonAdapter<Lxhw_TInfo.Info> funAdapter;
    Button fun_btn;
    Button log_btn;
    private ListView mFunListView;
    private ListView mListView;
    TextView tile_tv;
    Button tw_clean;
    ImageView tw_sdk_back_iv;
    private CommonAdapter<Lxhw_TInfo.Info> typeAdapter;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_tinfo_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.bundle = getArguments();
        this.tile_tv = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tile_tv"));
        this.tile_tv.setText("v5.0.1 p=" + ProjectType.pType + " rt=" + Lxhw_RechargeType.getType() + " min=" + Util.getMinSdkVersion(this.mContext) + " target=" + Util.getTargetSdkVersion(this.mContext) + " versionc=" + Util.getVersionCode(this.mContext) + " recharget=" + Lxhw_RechargeType.getType() + " gid=" + Lxhw_AppInfoDecorator.getGame_id() + " sk=" + Lxhw_AppInfoDecorator.getSecret_key_app() + " pg=" + Lxhw_AppInfoDecorator.getApp_id() + " afdk=" + Lxhw_AppInfoDecorator.getAppsf_dev_key() + " fbsdid=" + Lxhw_AppInfoDecorator.getFb_senderid() + " fbaid=" + Lxhw_AppInfoDecorator.getFacebook_App_Id());
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(this);
        DealTouchDelegate.expandViewTouchDelegateCom(this.tw_sdk_back_iv, 15);
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_clean"));
        this.tw_clean = button;
        button.setOnClickListener(this);
        this.mListView = (ListView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_list_lv"));
        setCancelable(false);
        loadLogItem();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_sdk_back_iv) {
            dismiss();
            return;
        }
        if (view == this.tw_clean) {
            Lxhw_TInfo.getInstance().cleanData();
            CommonAdapter<Lxhw_TInfo.Info> commonAdapter = this.typeAdapter;
            if (commonAdapter != null) {
                commonAdapter.setDatas(Lxhw_TInfo.getInstance().getListData());
            }
            dismiss();
        }
    }

    private void loadLogItem() {
        this.mContext.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_TInfoDialog.1
            @Override // java.lang.Runnable
            public void run() {
                if (Lxhw_TInfo.getInstance().getListData() == null || Lxhw_TInfo.getInstance().getListData().size() <= 0) {
                    return;
                }
                Lxhw_TInfoDialog.this.typeAdapter = new CommonAdapter<Lxhw_TInfo.Info>(Lxhw_TInfoDialog.this.mContext, Util.getIdByName(Lxhw_TInfoDialog.this.mContext, "layout", "drhw_tinfo_item")) { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_TInfoDialog.1.1
                    @Override // com.oversea.ab_firstarea.utils.CommonAdapter
                    public void convert(ViewHolder viewHolder, final Lxhw_TInfo.Info info, int i, View view) {
                        viewHolder.setText(Util.getIdByName(this.mContext, "id", "key_tv"), info.getKey());
                        viewHolder.setText(Util.getIdByName(this.mContext, "id", "value_tv"), info.getValue() + "");
                        viewHolder.setOnClickListener(Util.getIdByName(this.mContext, "id", "value_tv"), new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.kefu.Lxhw_TInfoDialog.1.1.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                Log.i("onlineCenterDialog", "  loadItem click");
                                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), info.getValue());
                            }
                        });
                    }
                };
                Lxhw_TInfoDialog.this.mListView.setAdapter((ListAdapter) Lxhw_TInfoDialog.this.typeAdapter);
                Lxhw_TInfoDialog.this.typeAdapter.setDatas(Lxhw_TInfo.getInstance().getListData());
            }
        });
    }
}
