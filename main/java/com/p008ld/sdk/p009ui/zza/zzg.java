package com.p008ld.sdk.p009ui.zza;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.LDPayChannelInfo;
import com.p008ld.sdk.core.bean.LDSupportPayGrade;
import com.p008ld.sdk.util.PictureHelper;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.MyGridView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: LBRechargeItemAdapter.java */
/* loaded from: classes2.dex */
public class zzg extends BaseAdapter {
    private final Context zzb;
    private zza zzc;
    public List<LDPayChannelInfo> zza = new ArrayList();
    private int zzd = -1;
    private int zze = -1;

    /* compiled from: LBRechargeItemAdapter.java */
    /* loaded from: classes2.dex */
    public interface zza {
        void zza(int i, int i2, LDSupportPayGrade lDSupportPayGrade);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public zzg(Context context) {
        this.zzb = context;
    }

    public void zza(List<LDPayChannelInfo> list) {
        List<LDPayChannelInfo> list2 = this.zza;
        if (list != list2) {
            list2.clear();
            if (list != null && !list.isEmpty()) {
                this.zza.addAll(list);
            }
        } else if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList(list);
            this.zza.clear();
            this.zza.addAll(arrayList);
        } else {
            this.zza.clear();
        }
        notifyDataSetChanged();
    }

    public void zzb(List<LDPayChannelInfo> list) {
        this.zza = list;
        notifyDataSetChanged();
    }

    public void zza(int i) {
        this.zzd = i;
    }

    public void zza(int i, boolean z) {
        this.zze = i;
        if (z) {
            notifyDataSetChanged();
        }
    }

    public void zza(zza zzaVar) {
        this.zzc = zzaVar;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.zza.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.zza.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        zzb zzbVar;
        if (view == null) {
            view = zzt.zza(this.zzb, "ld_lb_recharge_item_layout", (ViewGroup) null);
            zzbVar = new zzb();
            zzbVar.zza = (RelativeLayout) zzt.zza(this.zzb, "rl_group", view);
            zzbVar.zzb = (ImageView) zzt.zza(this.zzb, "iv_icon", view);
            zzbVar.zzd = (ImageView) zzt.zza(this.zzb, "iv_unfold", view);
            zzbVar.zzc = (TextView) zzt.zza(this.zzb, "tv_name", view);
            zzbVar.zze = (MyGridView) zzt.zza(this.zzb, "grid_grade", view);
            view.setTag(zzbVar);
        } else {
            zzbVar = (zzb) view.getTag();
        }
        LDPayChannelInfo lDPayChannelInfo = this.zza.get(i);
        zzbVar.zzc.setText(lDPayChannelInfo.getName());
        PictureHelper.loadImage(lDPayChannelInfo.getIcon(), zzbVar.zzb);
        if (lDPayChannelInfo.isSelected()) {
            zzbVar.zzd.setImageResource(zzt.zzd(this.zzb, "ld_ic_packup"));
            zzbVar.zze.setVisibility(0);
        } else {
            zzbVar.zzd.setImageResource(zzt.zzd(this.zzb, "ld_ic_unfold"));
            zzbVar.zze.setVisibility(8);
        }
        zzbVar.zza.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zza.zzg.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (zzg.this.zza.get(i).isSelected()) {
                    zzg.this.zza.get(i).setSelected(false);
                } else {
                    int size = zzg.this.zza.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        LDPayChannelInfo lDPayChannelInfo2 = zzg.this.zza.get(i2);
                        if (lDPayChannelInfo2.isSelected()) {
                            lDPayChannelInfo2.setSelected(false);
                        }
                    }
                    zzg.this.zza.get(i).setSelected(true);
                }
                zzg.this.zza(i);
                zzg.this.zze = -1;
                zzg zzgVar = zzg.this;
                zzgVar.zzb(zzgVar.zza);
                if (zzg.this.zzc != null) {
                    zzg.this.zzc.zza(i, zzg.this.zze, null);
                }
            }
        });
        final zzf zzfVar = new zzf(this.zzb, lDPayChannelInfo.getSupportGrades());
        zzbVar.zze.setAdapter((ListAdapter) zzfVar);
        int i2 = this.zze;
        if (i2 != -1) {
            zzfVar.zza(i2);
        }
        zzbVar.zze.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.zza.zzg.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i3, long j) {
                zzg.this.zza(i3, true);
                zzfVar.zza(i3);
                if (zzg.this.zzc != null) {
                    zzg.this.zzc.zza(zzg.this.zzd, zzg.this.zze, zzfVar.zza());
                }
            }
        });
        return view;
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
