package com.p008ld.sdk.p009ui.zza;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.p008ld.sdk.util.zzt;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CountryAreaAdapter.java */
/* loaded from: classes2.dex */
public class zzb extends BaseAdapter {
    private List<String> zza;
    private final Context zzb;
    private final String zzc;
    private int zzd;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public zzb(Context context, List<String> list, String str) {
        new ArrayList();
        this.zzb = context;
        this.zza = list;
        this.zzc = str;
    }

    public void zza(int i) {
        this.zzd = i;
        notifyDataSetChanged();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        zza zzaVar;
        if (view == null) {
            view = zzt.zza(this.zzb, this.zzc, (ViewGroup) null);
            zzaVar = new zza();
            zzaVar.zza = (TextView) zzt.zza(this.zzb, "country_area", view);
            zzaVar.zzb = (ImageView) zzt.zza(this.zzb, "iv_checked", view);
            zzaVar.zzc = (RelativeLayout) zzt.zza(this.zzb, "country_area_bg_layout", view);
            view.setTag(zzaVar);
        } else {
            zzaVar = (zza) view.getTag();
        }
        if (i == this.zzd) {
            zzaVar.zzc.setBackgroundResource(zzt.zzd(this.zzb, "ld_country_area_selected_bg"));
            zzaVar.zzb.setVisibility(0);
        } else {
            zzaVar.zzc.setBackgroundResource(0);
            zzaVar.zzb.setVisibility(8);
        }
        zzaVar.zza.setText(this.zza.get(i));
        return view;
    }

    /* compiled from: CountryAreaAdapter.java */
    /* loaded from: classes2.dex */
    class zza {
        TextView zza;
        ImageView zzb;
        RelativeLayout zzc;

        zza() {
        }
    }
}
