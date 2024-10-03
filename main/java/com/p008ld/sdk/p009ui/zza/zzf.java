package com.p008ld.sdk.p009ui.zza;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.LDPayChannelInfo;
import com.p008ld.sdk.core.bean.LDSupportPayGrade;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.MyGridView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: LBRechargeCoinItemAdapter.java */
/* loaded from: classes2.dex */
public class zzf extends BaseAdapter {
    private final List<LDSupportPayGrade> zza;
    private final Context zzb;
    private int zzc;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public zzf(Context context, List<LDSupportPayGrade> list) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        this.zzc = -1;
        this.zzb = context;
        arrayList.clear();
        arrayList.addAll(list);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.zza.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.zza.get(i);
    }

    public void zza(int i) {
        this.zzc = i;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        zza zzaVar;
        if (view == null) {
            view = zzt.zza(this.zzb, "ld_lb_recharge_coin_item_layout", (ViewGroup) null);
            zzaVar = new zza();
            zzaVar.zzb = (TextView) zzt.zza(this.zzb, "tv_quantity", view);
            zzaVar.zzc = (TextView) zzt.zza(this.zzb, "tv_amount", view);
            zzaVar.zza = (TextView) zzt.zza(this.zzb, "tv_additional", view);
            zzaVar.zzd = zzt.zza(this.zzb, "item_layout", view);
            view.setTag(zzaVar);
        } else {
            zzaVar = (zza) view.getTag();
        }
        if (this.zzc == i) {
            zzaVar.zzd.setBackgroundResource(zzt.zzd(this.zzb, "ld_ldbit_item_checked_bg"));
        } else {
            zzaVar.zzd.setBackgroundResource(zzt.zzd(this.zzb, "ld_ldbit_item_normal_bg"));
        }
        LDSupportPayGrade lDSupportPayGrade = this.zza.get(i);
        zzaVar.zzb.setText(lDSupportPayGrade.getQuantityOfGive());
        zzaVar.zzc.setText(lDSupportPayGrade.getTotalAmount() + " " + lDSupportPayGrade.getCurrencyUnit());
        if (zzi.zza((CharSequence) lDSupportPayGrade.getQuantityOfRewards())) {
            zzaVar.zza.setVisibility(8);
        } else {
            zzaVar.zza.setVisibility(0);
            zzaVar.zza.setText("+" + lDSupportPayGrade.getQuantityOfRewards());
        }
        return view;
    }

    public LDSupportPayGrade zza() {
        int i = this.zzc;
        if (i == -1) {
            return null;
        }
        return this.zza.get(i);
    }

    /* compiled from: LBRechargeCoinItemAdapter.java */
    /* loaded from: classes2.dex */
    class zza {
        TextView zza;
        TextView zzb;
        TextView zzc;
        View zzd;

        zza() {
        }
    }

    /* compiled from: LBRechargeItemAdapter.java */
    /* renamed from: com.ld.sdk.ui.zza.zzf$1 */
    /* loaded from: classes2.dex */
    class ViewOnClickListenerC18781 implements View.OnClickListener {
        final /* synthetic */ int zza;

        ViewOnClickListenerC18781(int i) {
            this.zza = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (((LDPayChannelInfo) zzf.this.zza.get(this.zza)).isSelected()) {
                ((LDPayChannelInfo) zzf.this.zza.get(this.zza)).setSelected(false);
            } else {
                int size = zzf.this.zza.size();
                for (int i = 0; i < size; i++) {
                    LDPayChannelInfo lDPayChannelInfo = (LDPayChannelInfo) zzf.this.zza.get(i);
                    if (lDPayChannelInfo.isSelected()) {
                        lDPayChannelInfo.setSelected(false);
                    }
                }
                ((LDPayChannelInfo) zzf.this.zza.get(this.zza)).setSelected(true);
            }
            zzf.this.zza(this.zza);
            zzf.zza(zzf.this, -1);
            zzf zzfVar = zzf.this;
            zzfVar.zzb(zzfVar.zza);
            if (zzf.zza(zzf.this) != null) {
                zzf.zza(zzf.this).zza(this.zza, zzf.zzb(zzf.this), null);
            }
        }
    }

    /* compiled from: LBRechargeItemAdapter.java */
    /* renamed from: com.ld.sdk.ui.zza.zzf$2 */
    /* loaded from: classes2.dex */
    class C18792 implements AdapterView.OnItemClickListener {
        final /* synthetic */ zze zza;

        C18792(zze zzeVar) {
            this.zza = zzeVar;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            zzf.this.zza(i, true);
            this.zza.zza(i);
            if (zzf.zza(zzf.this) != null) {
                zzf.zza(zzf.this).zza(zzf.zzc(zzf.this), zzf.zzb(zzf.this), this.zza.zza());
            }
        }
    }

    /* compiled from: LBRechargeItemAdapter.java */
    /* loaded from: classes2.dex */
    class zzb {
        RelativeLayout zza;
        ImageView zzb;
        TextView zzc;
        ImageView zzd;
        MyGridView zze;

        zzb() {
        }
    }
}
