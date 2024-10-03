package com.p008ld.sdk.p009ui.account;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.core.bean.CouponBean;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.p009ui.zza.zzc;
import com.p008ld.sdk.p009ui.zzb.zze;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzk;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.MyRadioGroup;
import com.p008ld.sdk.widget.RBOnClickListener;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public class CouponsAccountView extends BaseAccountView {
    private Context zze;
    private ListView zzf;
    private View zzg;
    private List<CouponBean> zzh;
    private List<CouponBean> zzi;
    private zzc zzj;

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public String getTitle() {
        return "";
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public boolean zza() {
        return false;
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public void zzb() {
    }

    public CouponsAccountView(Context context) {
        super(context);
        this.zze = context;
        zza(context);
    }

    public void zza(final Context context) {
        if (this.zzf == null) {
            String[] strArr = {zzt.zza(context, "ld_my_coupon_text"), zzt.zza(context, "ld_history_record_text")};
            View zza = zzt.zza(context, "ld_account_coupons_rv", (ViewGroup) this);
            this.zzf = (ListView) zzt.zza(context, "recyclerView", zza);
            ((MyRadioGroup) zzt.zza(context, "top_layout", zza)).setTitleData(strArr, new RBOnClickListener() { // from class: com.ld.sdk.ui.account.CouponsAccountView.1
                @Override // com.p008ld.sdk.widget.RBOnClickListener
                public void onClick(String str) {
                    CouponsAccountView.this.zzg.setVisibility(8);
                    if (str.equals("0")) {
                        CouponsAccountView couponsAccountView = CouponsAccountView.this;
                        couponsAccountView.setData(couponsAccountView.zzh);
                    } else if (str.equals("1")) {
                        if (CouponsAccountView.this.zzi == null) {
                            zza.zzf().zza(2, new LDQueryCallback<List<CouponBean>>() { // from class: com.ld.sdk.ui.account.CouponsAccountView.1.1
                                @Override // com.p008ld.sdk.internal.LDCallback2
                                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                                public void done(List<CouponBean> list, LDException lDException) {
                                    if (list != null && !list.isEmpty()) {
                                        CouponsAccountView.this.zzi = list;
                                    }
                                    CouponsAccountView.this.setData(CouponsAccountView.this.zzi);
                                }
                            });
                        } else {
                            CouponsAccountView couponsAccountView2 = CouponsAccountView.this;
                            couponsAccountView2.setData(couponsAccountView2.zzi);
                        }
                    }
                }
            });
            zzt.zza(context, "illustrate_tv", zza).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.CouponsAccountView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ConfigBean zze = zza.zzf().zze();
                    if (zze == null || zzi.zza((CharSequence) zze.couponUseUrl)) {
                        return;
                    }
                    new zze(context, zze.couponUseUrl);
                }
            });
            this.zzg = zzt.zza(context, "empty_data_layout", zza);
        }
        zza.zzf().zza(1, new LDQueryCallback<List<CouponBean>>() { // from class: com.ld.sdk.ui.account.CouponsAccountView.3
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(List<CouponBean> list, LDException lDException) {
                if (list != null && !list.isEmpty()) {
                    CouponsAccountView.this.zzh = list;
                    CouponsAccountView couponsAccountView = CouponsAccountView.this;
                    couponsAccountView.zza((List<CouponBean>) couponsAccountView.zzh);
                }
                CouponsAccountView couponsAccountView2 = CouponsAccountView.this;
                couponsAccountView2.setData(couponsAccountView2.zzh);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(List<CouponBean> list) {
        Set<String> zzc = zzk.zza(this.zze).zzc("couponSet");
        for (CouponBean couponBean : list) {
            couponBean.isNew = zzc == null || !zzc.contains(String.valueOf(couponBean.couponId));
        }
        zzi.zza(this.zze, this.zzh, "couponSet");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setData(List<CouponBean> list) {
        zzc zzcVar = this.zzj;
        if (zzcVar == null) {
            zzc zzcVar2 = new zzc(this.zze, list);
            this.zzj = zzcVar2;
            this.zzf.setAdapter((ListAdapter) zzcVar2);
        } else {
            zzcVar.zza(list);
        }
        this.zzf.setVisibility(0);
        if (list == null || list.isEmpty()) {
            this.zzg.setVisibility(0);
        } else {
            this.zzg.setVisibility(8);
        }
        this.zzf.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.account.CouponsAccountView.4
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            }
        });
    }
}
