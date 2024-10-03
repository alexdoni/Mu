package com.p008ld.sdk.p009ui.zza;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.CouponBean;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CouponsAdapter.java */
/* loaded from: classes2.dex */
public class zzc extends BaseAdapter {
    private List<CouponBean> zza;
    private final Context zzb;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public zzc(Context context, List<CouponBean> list) {
        this.zzb = context;
        this.zza = list;
        if (list == null) {
            this.zza = new ArrayList();
        }
    }

    public void zza(List<CouponBean> list) {
        this.zza = list;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<CouponBean> list = this.zza;
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
            view = zzt.zza(this.zzb, "ld_account_coupons_item", (ViewGroup) null);
            zzaVar = new zza(view);
            view.setTag(zzaVar);
        } else {
            zzaVar = (zza) view.getTag();
        }
        view.setPadding(0, 0, 0, i == this.zza.size() - 1 ? zzi.zza(this.zzb, 30.0f) : 0);
        CouponBean couponBean = this.zza.get(i);
        zzaVar.zzd.setText(couponBean.couponDesc);
        if (couponBean.minDate == 0) {
            zzaVar.zze.setText(zzt.zza(this.zzb, "ld_permanently_valid_text"));
        } else {
            zzaVar.zze.setText(zzt.zza(this.zzb, "ld_valid_to_text") + "  " + zzi.zza(couponBean.minDate));
        }
        if (couponBean.couponType == 1) {
            String valueOf = String.valueOf((int) (100.0f - (Float.parseFloat(couponBean.couponRight) * 100.0f)));
            zzaVar.zzf.setText(valueOf + "%OFF");
            if (couponBean.maxNum == 0) {
                zzaVar.zzg.setVisibility(8);
            } else {
                zzaVar.zzg.setVisibility(0);
                zzaVar.zzg.setText(zzt.zza(this.zzb, "ld_maximum_deductible_text") + couponBean.maxNum);
            }
        } else {
            if (couponBean.useCondition != 0) {
                zzaVar.zzg.setText(String.format(zzt.zza(this.zzb, "ld_meet_available_text"), Long.valueOf(couponBean.useCondition)));
                zzaVar.zzg.setVisibility(0);
            } else {
                zzaVar.zzg.setVisibility(8);
                zzaVar.zzg.setText(zzt.zza(this.zzb, "ld_no_limitation_condition_text"));
            }
            zzaVar.zzf.setText(couponBean.couponRight);
        }
        if (couponBean.isNew) {
            zzaVar.zza.setVisibility(0);
        } else {
            zzaVar.zza.setVisibility(8);
        }
        if (couponBean.status == 2 || couponBean.status == 0) {
            zzaVar.zzb.setVisibility(0);
            String zza2 = zzt.zza(this.zzb, "ld_expired_text");
            String zza3 = zzt.zza(this.zzb, "ld_already_use_text");
            TextView textView = zzaVar.zzc;
            if (couponBean.status != 2) {
                zza2 = zza3;
            }
            textView.setText(zza2);
        } else {
            zzaVar.zzb.setVisibility(8);
        }
        return view;
    }

    /* compiled from: CouponsAdapter.java */
    /* loaded from: classes2.dex */
    class zza {
        public ImageView zza;
        public View zzb;
        public TextView zzc;
        public TextView zzd;
        public TextView zze;
        public TextView zzf;
        public TextView zzg;

        public zza(View view) {
            this.zzd = (TextView) zzt.zza(zzc.this.zzb, "coupon_name_tv", view);
            this.zze = (TextView) zzt.zza(zzc.this.zzb, "coupon_valid_to_tv", view);
            this.zzf = (TextView) zzt.zza(zzc.this.zzb, "discount_tv", view);
            this.zzg = (TextView) zzt.zza(zzc.this.zzb, "highest_tv", view);
            this.zza = (ImageView) zzt.zza(zzc.this.zzb, "new_coupon_img", view);
            this.zzb = zzt.zza(zzc.this.zzb, "expire_layout", view);
            this.zzc = (TextView) zzt.zza(zzc.this.zzb, "expire_tv", view);
        }
    }
}
