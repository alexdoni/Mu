package com.p008ld.sdk.p009ui.zza;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.util.PictureHelper;
import com.p008ld.sdk.util.zzt;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FunctionGridAdapter.java */
/* loaded from: classes2.dex */
public class zzd extends BaseAdapter {
    private final Context zza;
    private final List<zza> zzb = new ArrayList();
    private int zzc = 0;

    /* compiled from: FunctionGridAdapter.java */
    /* loaded from: classes2.dex */
    public enum zzb {
        TYPE_LD_BIT,
        TYPE_COUPON,
        TYPE_USER_INFO,
        TYPE_GIFT_BAG
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public zzd(Context context) {
        zza();
        this.zza = context;
    }

    private void zza() {
        String[] strArr = {"ld_coin_text", "ld_gift_bag_text", "ld_account_text"};
        String[] strArr2 = {"ld_coin_icon", "ld_gift_bag_icon", "ld_account_icon"};
        ConfigBean zze = com.p008ld.sdk.core.zza.zza.zzf().zze();
        zzb[] zzbVarArr = {zzb.TYPE_LD_BIT, zzb.TYPE_GIFT_BAG, zzb.TYPE_USER_INFO};
        for (int i = 0; i < 3; i++) {
            if (i == 0 && zze.isPay == 0) {
                return;
            }
            zza zzaVar = new zza();
            zzaVar.zza = strArr[i];
            zzaVar.zzc = strArr2[i];
            zzaVar.zzb = zzbVarArr[i];
            this.zzb.add(zzaVar);
        }
    }

    public void zza(int i) {
        this.zzc = i;
        notifyDataSetChanged();
    }

    public zzb zzb(int i) {
        return this.zzb.get(i).zzb;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.zzb.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.zzb.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        zzc zzcVar;
        if (view == null) {
            view = zzt.zza(this.zza, "ld_function_item_layout", (ViewGroup) null);
            zzcVar = new zzc();
            zzcVar.zza = (TextView) zzt.zza(this.zza, "function_title", view);
            zzcVar.zzb = (ImageView) zzt.zza(this.zza, "function_icon", view);
            zzcVar.zzc = (LinearLayout) zzt.zza(this.zza, "function_layout", view);
            zzcVar.zzd = zzt.zza(this.zza, "function_hot", view);
            zzcVar.zze = (LinearLayout) zzt.zza(this.zza, "item_layout", view);
            view.setTag(zzcVar);
        } else {
            zzcVar = (zzc) view.getTag();
        }
        zzcVar.zza.setText(zzt.zza(this.zza, this.zzb.get(i).zza));
        String str = this.zzb.get(i).zzc;
        if (str.contains("http")) {
            PictureHelper.loadImage(str, zzcVar.zzb);
        } else {
            zzcVar.zzb.setImageResource(zzt.zzd(this.zza, this.zzb.get(i).zzc));
        }
        if (this.zzc == i) {
            zzcVar.zzc.setBackgroundResource(zzt.zzd(this.zza, "ld_charge_layout_bg"));
        } else {
            zzcVar.zzc.setBackgroundColor(0);
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FunctionGridAdapter.java */
    /* loaded from: classes2.dex */
    public static class zza {
        String zza;
        zzb zzb;
        String zzc;

        zza() {
        }
    }

    /* compiled from: FunctionGridAdapter.java */
    /* loaded from: classes2.dex */
    static class zzc {
        TextView zza;
        ImageView zzb;
        LinearLayout zzc;
        View zzd;
        LinearLayout zze;

        zzc() {
        }
    }
}
