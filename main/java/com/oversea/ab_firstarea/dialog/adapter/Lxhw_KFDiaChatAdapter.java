package com.oversea.ab_firstarea.dialog.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.net.model.IssueMsgListBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.p017xx.commom.glide.Glide;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.List;

/* loaded from: classes.dex */
public class Lxhw_KFDiaChatAdapter extends BaseAdapter {
    private Bitmap bitmap;
    private Context mContext;
    private LayoutInflater mInflater;
    private List<IssueMsgListBean.Issue_msg_list> mList;
    private String mUserHead;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public Lxhw_KFDiaChatAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<IssueMsgListBean.Issue_msg_list> list = this.mList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        List<IssueMsgListBean.Issue_msg_list> list = this.mList;
        if (list != null) {
            return list.get(i);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        final HoldViewlift holdViewlift;
        final HoldViewRight holdViewRight;
        if (this.mList.get(i).getFrom_type() == 2 || this.mList.get(i).getFrom_type() == 3) {
            if (view != null && (view.getTag() instanceof HoldViewlift)) {
                holdViewlift = (HoldViewlift) view.getTag();
            } else {
                view = this.mInflater.inflate(Util.getIdByName(this.mContext, "layout", "drhw_xkfitem_chat_lift"), viewGroup, false);
                holdViewlift = new HoldViewlift();
                holdViewlift.lTextView = (TextView) view.findViewById(getId("tw_chat_lift_tv"));
                holdViewlift.lTimeTv = (TextView) view.findViewById(getId("tw_chat_time_lift_tv"));
                holdViewlift.lImageView = (ImageView) view.findViewById(getId("tw_chat_lift_iv"));
                holdViewlift.lChatImageView = (ImageView) view.findViewById(getId("tw_content_iv"));
                holdViewlift.lChatImageView.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.adapter.Lxhw_KFDiaChatAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        try {
                            Bitmap bitmap = ((BitmapDrawable) holdViewlift.lChatImageView.getDrawable()).getBitmap();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("bitmap", bitmap);
                            Lxhw_DialogManage.getInstance().enterKFShowBitMapDialog(Lxhw_KFDiaChatAdapter.this.mContext, bundle);
                        } catch (Throwable unused) {
                        }
                    }
                });
                view.setTag(holdViewlift);
            }
            if (TextUtils.isEmpty(this.mList.get(i).getMsg_text())) {
                holdViewlift.lTextView.setVisibility(8);
                holdViewlift.lTextView.setText("");
            } else {
                holdViewlift.lTextView.setVisibility(0);
                holdViewlift.lTextView.setText(this.mList.get(i).getMsg_text());
            }
            if (!TextUtils.isEmpty(this.mList.get(i).getMsg_image_url())) {
                holdViewlift.lChatImageView.setVisibility(0);
                if (!this.mList.get(i).getMsg_image_url().startsWith("http")) {
                    Glide.with(this.mContext).load(ManageBean.getInstance().getaCloudStSBean().getData().getOss().getImage_domain() + RemoteSettings.FORWARD_SLASH_STRING + this.mList.get(i).getMsg_image_url()).into(holdViewlift.lChatImageView);
                } else {
                    Glide.with(this.mContext).load(this.mList.get(i).getMsg_image_url()).into(holdViewlift.lChatImageView);
                }
            } else {
                holdViewlift.lChatImageView.setVisibility(8);
            }
        } else {
            if (view != null && (view.getTag() instanceof HoldViewRight)) {
                holdViewRight = (HoldViewRight) view.getTag();
            } else {
                view = this.mInflater.inflate(Util.getIdByName(this.mContext, "layout", "drhw_xkfitem_chat_right"), viewGroup, false);
                holdViewRight = new HoldViewRight();
                holdViewRight.rTextView = (TextView) view.findViewById(getId("tw_chat_right_tv"));
                holdViewRight.rTimeTv = (TextView) view.findViewById(getId("tw_chat_time_right_tv"));
                holdViewRight.rImageView = (ImageView) view.findViewById(getId("tw_chat_right_iv"));
                holdViewRight.rChatImageView = (ImageView) view.findViewById(getId("tw_content_iv"));
                holdViewRight.rChatImageView.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.adapter.Lxhw_KFDiaChatAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        try {
                            Bitmap bitmap = ((BitmapDrawable) holdViewRight.rChatImageView.getDrawable()).getBitmap();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("bitmap", bitmap);
                            Lxhw_DialogManage.getInstance().enterKFShowBitMapDialog(Lxhw_KFDiaChatAdapter.this.mContext, bundle);
                        } catch (Throwable unused) {
                        }
                    }
                });
                view.setTag(holdViewRight);
            }
            if (TextUtils.isEmpty(this.mList.get(i).getMsg_image_url())) {
                holdViewRight.rChatImageView.setVisibility(8);
            } else {
                holdViewRight.rChatImageView.setVisibility(0);
                if (!this.mList.get(i).getMsg_image_url().startsWith("http")) {
                    Glide.with(this.mContext).load(ManageBean.getInstance().getaCloudStSBean().getData().getOss().getImage_domain() + RemoteSettings.FORWARD_SLASH_STRING + this.mList.get(i).getMsg_image_url()).into(holdViewRight.rChatImageView);
                } else {
                    Glide.with(this.mContext).load(this.mList.get(i).getMsg_image_url()).into(holdViewRight.rChatImageView);
                }
            }
            if (TextUtils.isEmpty(this.mList.get(i).getMsg_text())) {
                holdViewRight.rTextView.setVisibility(8);
            } else {
                holdViewRight.rTextView.setText(this.mList.get(i).getMsg_text());
                holdViewRight.rTextView.setVisibility(0);
            }
            holdViewRight.rTimeTv.setText(this.mList.get(i).getMsg_time() + "");
        }
        return view;
    }

    public void setData(List<IssueMsgListBean.Issue_msg_list> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void addData(List list) {
        this.mList.addAll(0, list);
        notifyDataSetChanged();
    }

    /* loaded from: classes.dex */
    class HoldViewRight {
        ImageView rChatImageView;
        ImageView rImageView;
        TextView rTextView;
        TextView rTimeTv;

        HoldViewRight() {
        }
    }

    /* loaded from: classes.dex */
    class HoldViewlift {
        ImageView lChatImageView;
        ImageView lImageView;
        TextView lTextView;
        TextView lTimeTv;

        HoldViewlift() {
        }
    }

    private int getId(String str) {
        return Util.getIdByName(this.mContext, "id", str);
    }
}
