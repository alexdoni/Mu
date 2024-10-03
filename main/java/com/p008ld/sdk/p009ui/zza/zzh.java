package com.p008ld.sdk.p009ui.zza;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.OrderRecordBean;
import com.p008ld.sdk.core.bean.PublicUserInfo;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.p009ui.zzb.zzl;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;
import java.util.List;

/* compiled from: OrderRecordAdapter.java */
/* loaded from: classes2.dex */
public class zzh extends BaseAdapter {
    private List<OrderRecordBean> zza;
    private boolean zzb;
    private final Context zzc;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public zzh(Context context) {
        this.zzc = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<OrderRecordBean> list = this.zza;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.zza.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        zza zzaVar;
        if (view == null) {
            view = zzt.zza(this.zzc, "ld_account_order_item", (ViewGroup) null);
            zzaVar = new zza(view, this.zzc);
            view.setTag(zzaVar);
        } else {
            zzaVar = (zza) view.getTag();
        }
        List<OrderRecordBean> list = this.zza;
        if (list != null && i < list.size()) {
            try {
                OrderRecordBean orderRecordBean = this.zza.get(i);
                zzaVar.zzb.setText(orderRecordBean.createTime);
                zzaVar.zzc.setText(orderRecordBean.payChannelConfName);
                if (this.zzb) {
                    if (orderRecordBean.payType == 1) {
                        String zza2 = zzt.zza(this.zzc, "ld_order_coin_pay_center");
                        String zza3 = zzt.zza(this.zzc, "ld_order_coin_pay");
                        zzaVar.zza.setText(zza2 + " · " + zza3);
                        zzaVar.zzd.setText("+" + orderRecordBean.productCoin + " COIN");
                        zzaVar.zzd.setTextColor(Color.parseColor("#FF7A00"));
                    } else if (orderRecordBean.payType == 3) {
                        zzaVar.zza.setText(orderRecordBean.appName + " · " + orderRecordBean.productName);
                        zzaVar.zzd.setText("-" + orderRecordBean.productCoin + " COIN");
                        zzaVar.zzd.setTextColor(Color.parseColor("#B2000000"));
                    }
                } else {
                    zzaVar.zza.setText(orderRecordBean.appName + " · " + orderRecordBean.productName);
                    TextView textView = zzaVar.zzd;
                    StringBuilder sb = new StringBuilder("-");
                    sb.append(zzi.zza(orderRecordBean.amount, orderRecordBean.payCurrency));
                    textView.setText(sb.toString());
                    zzaVar.zzd.setTextColor(Color.parseColor("#B2000000"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    /* compiled from: OrderRecordAdapter.java */
    /* loaded from: classes2.dex */
    public class zza {
        public TextView zza;
        public TextView zzb;
        public TextView zzc;
        public TextView zzd;

        public zza(View view, Context context) {
            this.zza = (TextView) zzt.zza(context, "desc_tv_1", view);
            this.zzb = (TextView) zzt.zza(context, "desc_tv_2", view);
            this.zzc = (TextView) zzt.zza(context, "desc_tv_3", view);
            this.zzd = (TextView) zzt.zza(context, "desc_tv_4", view);
        }
    }

    public void zza(List<OrderRecordBean> list, boolean z) {
        this.zzb = z;
        this.zza = list;
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SelectItemAdapter.java */
    /* renamed from: com.ld.sdk.ui.zza.zzh$1 */
    /* loaded from: classes2.dex */
    public class ViewOnClickListenerC18821 implements View.OnClickListener {
        final /* synthetic */ int zza;

        ViewOnClickListenerC18821(int i) {
            this.zza = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (zzh.zza(zzh.this) == null || zzh.zza(zzh.this).isEmpty()) {
                return;
            }
            final String str = ((PublicUserInfo) zzh.zza(zzh.this).get(this.zza)).username;
            new zzl(zzh.zzb(zzh.this)).zza(zzt.zza(zzh.zzb(zzh.this), "ld_dialog_common_notice")).zzb(zzi.zzb(str, String.format(zzt.zza(zzh.zzb(zzh.this), "ld_delete_account_login_record_text"), str))).zza(new LDCallback1<Boolean>() { // from class: com.ld.sdk.ui.zza.zzh.1.1
                @Override // com.p008ld.sdk.internal.LDCallback1
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(Boolean bool) {
                    if (bool.booleanValue()) {
                        com.p008ld.sdk.core.zza.zza.zzf().zza(zzh.zzb(zzh.this), ((PublicUserInfo) zzh.zza(zzh.this).get(ViewOnClickListenerC18821.this.zza)).ldUserId);
                        if (zzh.zzc(zzh.this) != null) {
                            zzh.zzc(zzh.this).zza(str);
                        }
                        zzh.zza(zzh.this).remove(ViewOnClickListenerC18821.this.zza);
                        zzh.this.notifyDataSetChanged();
                    }
                }
            }).show();
        }
    }

    /* compiled from: SelectItemAdapter.java */
    /* loaded from: classes2.dex */
    public final class zzb {
        ImageView zza;
        TextView zzb;
        TextView zzc;
        ImageView zzd;

        public zzb() {
        }
    }
}
