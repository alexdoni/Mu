package com.p008ld.sdk.p009ui.zza;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.PayConfBean;
import com.p008ld.sdk.util.PictureHelper;
import com.p008ld.sdk.util.zzt;
import java.util.List;

/* compiled from: RechargeModeAdapter.java */
/* loaded from: classes2.dex */
public class zzi extends BaseAdapter {
    private final Context zza;
    private int zzb;
    private List<PayConfBean.PayChannelProductVosBean.PayChannelConfigVosBean> zzc;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public void zza(int i) {
        this.zzb = i;
        notifyDataSetChanged();
    }

    public zzi(Context context, List<PayConfBean.PayChannelProductVosBean.PayChannelConfigVosBean> list) {
        this.zzb = -1;
        this.zzc = list;
        this.zza = context;
        if (list.size() == 1) {
            this.zzb = 0;
        }
    }

    public void zza(List<PayConfBean.PayChannelProductVosBean.PayChannelConfigVosBean> list) {
        if (list != null) {
            this.zzc = list;
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.zzc.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.zzc.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        zza zzaVar;
        if (view == null) {
            view = zzt.zza(this.zza, "ld_recharge_mode_item_layout", (ViewGroup) null);
            zzaVar = new zza();
            zzaVar.zza = (ImageView) zzt.zza(this.zza, "recharge_mode_icon", view);
            zzaVar.zzb = (TextView) zzt.zza(this.zza, "recharge_mode_name", view);
            zzaVar.zzc = (TextView) zzt.zza(this.zza, "original_price_tv", view);
            zzaVar.zzd = (TextView) zzt.zza(this.zza, "preferential_price_tv", view);
            zzaVar.zze = zzt.zza(this.zza, "root_layout", view);
            zzaVar.zzc.getPaint().setFlags(16);
            zzaVar.zzc.getPaint().setAntiAlias(true);
            view.setTag(zzaVar);
        } else {
            zzaVar = (zza) view.getTag();
        }
        PayConfBean.PayChannelProductVosBean.PayChannelConfigVosBean payChannelConfigVosBean = this.zzc.get(i);
        PictureHelper.loadImage(payChannelConfigVosBean.payChannelIcon, zzaVar.zza);
        zzaVar.zzb.setText(payChannelConfigVosBean.payChannelName);
        String str = payChannelConfigVosBean.currency;
        if (str == null || str.equals("")) {
            str = payChannelConfigVosBean.myCurrency;
        }
        if (com.p008ld.sdk.util.zzi.zza((CharSequence) payChannelConfigVosBean.discountPrice)) {
            zzaVar.zzc.setVisibility(8);
            if (payChannelConfigVosBean.gradeChannelTypeId == 0) {
                zzaVar.zzd.setText(payChannelConfigVosBean.price + " Coin");
            } else {
                zzaVar.zzd.setText(com.p008ld.sdk.util.zzi.zza(payChannelConfigVosBean.price, str));
            }
        } else {
            zzaVar.zzc.setVisibility(0);
            zzaVar.zzc.setText(com.p008ld.sdk.util.zzi.zza(payChannelConfigVosBean.price, str));
            zzaVar.zzd.setText(com.p008ld.sdk.util.zzi.zza(payChannelConfigVosBean.discountPrice, str));
        }
        if (this.zzb == i) {
            zzaVar.zze.setBackgroundResource(zzt.zzd(this.zza, "ld_select_item_selected_bg"));
        } else {
            zzaVar.zze.setBackgroundResource(zzt.zzd(this.zza, "ld_select_item_default_bg"));
        }
        return view;
    }

    public PayConfBean.PayChannelProductVosBean.PayChannelConfigVosBean zza() {
        int i = this.zzb;
        if (i == -1) {
            return null;
        }
        return this.zzc.get(i);
    }

    /* compiled from: RechargeModeAdapter.java */
    /* loaded from: classes2.dex */
    class zza {
        ImageView zza;
        TextView zzb;
        TextView zzc;
        TextView zzd;
        View zze;

        zza() {
        }
    }
}
