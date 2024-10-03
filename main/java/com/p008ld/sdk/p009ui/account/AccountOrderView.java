package com.p008ld.sdk.p009ui.account;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.OrderRecordBean;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.p009ui.zza.zzh;
import com.p008ld.sdk.p009ui.zzb.zzc;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.PictureHelper;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.CircleImageView;
import com.p008ld.sdk.widget.MyRadioGroup;
import com.p008ld.sdk.widget.RBOnClickListener;
import java.util.List;

/* loaded from: classes2.dex */
public class AccountOrderView extends BaseAccountView {
    private TextView zzaa;
    private TextView zzab;
    private TextView zzac;
    private TextView zzad;
    private LinearLayout zzae;
    private ImageView zzaf;
    private int zze;
    private ImageView zzf;
    private ImageView zzg;
    private ListView zzh;
    private zzh zzi;
    private zzh zzj;
    private View zzk;
    private TextView zzl;
    private List<OrderRecordBean> zzm;
    private List<OrderRecordBean> zzn;
    private MyRadioGroup zzo;
    private FrameLayout zzp;
    private LinearLayout zzq;
    private boolean zzr;
    private CircleImageView zzs;
    private TextView zzt;
    private TextView zzu;
    private TextView zzv;
    private TextView zzw;
    private TextView zzx;
    private TextView zzy;
    private TextView zzz;

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

    public AccountOrderView(Context context, int i, View.OnClickListener onClickListener) {
        super(context);
        this.zzr = true;
        this.zze = i;
        zza(context);
    }

    public int getFromPage() {
        return this.zze;
    }

    public void zza(Context context) {
        View zza2 = zzt.zza(context, "ld_account_order", (ViewGroup) this);
        this.zzp = (FrameLayout) zzt.zza(context, "fl_order", zza2);
        ImageView imageView = (ImageView) zzt.zza(context, "iv_back", zza2);
        this.zzg = imageView;
        setBackListener(imageView);
        String[] strArr = {zzt.zza(context, "ld_order_ld_recharge_title"), zzt.zza(context, "ld_order_consume_title")};
        MyRadioGroup myRadioGroup = (MyRadioGroup) zzt.zza(context, "my_radiogroup", zza2);
        this.zzo = myRadioGroup;
        myRadioGroup.showLine();
        this.zzo.setTitleData(strArr, new RBOnClickListener() { // from class: com.ld.sdk.ui.account.AccountOrderView.1
            @Override // com.p008ld.sdk.widget.RBOnClickListener
            public void onClick(String str) {
                if (str.equals("0")) {
                    AccountOrderView.this.zzr = true;
                    AccountOrderView.this.zzc();
                } else if (str.equals("1")) {
                    AccountOrderView.this.zzr = false;
                    AccountOrderView.this.zzd();
                }
            }
        });
        this.zzk = zzt.zza(context, "empty_data_layout", zza2);
        this.zzl = (TextView) zzt.zza(context, "empty_desc_tv", zza2);
        this.zzh = (ListView) zzt.zza(context, "recyclerView", zza2);
        this.zzi = new zzh(context);
        this.zzj = new zzh(context);
        this.zzh.setAdapter((ListAdapter) this.zzi);
        this.zzh.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.account.AccountOrderView.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                AccountOrderView.this.zzq.setVisibility(0);
                AccountOrderView.this.zzp.setVisibility(8);
                AccountOrderView.this.zza(AccountOrderView.this.zzr ? (OrderRecordBean) AccountOrderView.this.zzm.get(i) : (OrderRecordBean) AccountOrderView.this.zzn.get(i));
            }
        });
        com.p008ld.sdk.core.zza.zza.zzf().zzb(new LDQueryCallback<List<OrderRecordBean>>() { // from class: com.ld.sdk.ui.account.AccountOrderView.3
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(List<OrderRecordBean> list, LDException lDException) {
                if (list != null && !list.isEmpty()) {
                    AccountOrderView.this.zzm = list;
                }
                AccountOrderView.this.zzc();
                if (lDException != null) {
                    LDLog.m573e("AccountOrderView -> queryLBList error ：" + lDException);
                    LDUtil.toast(lDException.toString());
                }
            }
        });
        zza(context, zza2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzc() {
        List<OrderRecordBean> list = this.zzm;
        if (list != null && !list.isEmpty()) {
            this.zzh.setAdapter((ListAdapter) this.zzi);
            this.zzi.zza(this.zzm, true);
            this.zzk.setVisibility(4);
            this.zzh.setVisibility(0);
            return;
        }
        this.zzh.setVisibility(4);
        this.zzk.setVisibility(0);
        this.zzl.setText(zzt.zza(this.zza, "ld_not_order_text"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzd() {
        if (this.zzn == null) {
            com.p008ld.sdk.core.zza.zza.zzf().zzc(new LDQueryCallback<List<OrderRecordBean>>() { // from class: com.ld.sdk.ui.account.AccountOrderView.4
                @Override // com.p008ld.sdk.internal.LDCallback2
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(List<OrderRecordBean> list, LDException lDException) {
                    if (list != null && !list.isEmpty()) {
                        AccountOrderView.this.zzn = list;
                    }
                    AccountOrderView.this.zze();
                    if (lDException != null) {
                        LDLog.m573e("AccountOrderView -> queryDirectPurchaseList error ：" + lDException);
                        LDUtil.toast(lDException.toString());
                    }
                }
            });
        } else {
            zze();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zze() {
        List<OrderRecordBean> list = this.zzn;
        if (list != null && !list.isEmpty()) {
            this.zzh.setAdapter((ListAdapter) this.zzj);
            this.zzj.zza(this.zzn, false);
            this.zzh.setVisibility(0);
            this.zzk.setVisibility(4);
            return;
        }
        this.zzl.setText(zzt.zza(this.zza, "ld_not_consumption_record_text"));
        this.zzh.setVisibility(4);
        this.zzk.setVisibility(0);
    }

    private void zza(Context context, View view) {
        this.zzq = (LinearLayout) zzt.zza(context, "ll_order_detail", view);
        this.zzf = (ImageView) zzt.zza(context, "iv_detail_back", view);
        this.zzs = (CircleImageView) zzt.zza(context, "iv_avatar", view);
        this.zzt = (TextView) zzt.zza(context, "tv_cp_name", view);
        this.zzu = (TextView) zzt.zza(context, "tv_amount", view);
        this.zzv = (TextView) zzt.zza(context, "tv_status_1", view);
        this.zzw = (TextView) zzt.zza(context, "tv_time", view);
        this.zzx = (TextView) zzt.zza(context, "tv_time_1", view);
        this.zzy = (TextView) zzt.zza(context, "tv_pay_method_1", view);
        this.zzz = (TextView) zzt.zza(context, "tv_order_number_1", view);
        this.zzaa = (TextView) zzt.zza(context, "tv_product_id_1", view);
        this.zzab = (TextView) zzt.zza(context, "tv_product_name_1", view);
        this.zzac = (TextView) zzt.zza(context, "tv_app_id_1", view);
        this.zzad = (TextView) zzt.zza(context, "tv_app_name_1", view);
        this.zzae = (LinearLayout) zzt.zza(context, "ll_contact", view);
        this.zzaf = (ImageView) zzt.zza(context, "iv_contact", view);
        if (!zzi.zza(context)) {
            this.zzae.setVisibility(0);
            this.zzae.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountOrderView.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AccountOrderView.this.zzf();
                }
            });
        }
        this.zzf.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountOrderView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AccountOrderView.this.zzg();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzf() {
        int i = this.zza.getResources().getDisplayMetrics().heightPixels;
        new zzc(getContext(), true).showAtLocation(this.zzaf, 0, this.zza.getResources().getDisplayMetrics().widthPixels, ((int) (i * 0.9d)) - zzi.zza(getContext(), 70.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(OrderRecordBean orderRecordBean) {
        try {
            if (this.zzr) {
                this.zzw.setText(zzt.zza(this.zza, "ld_pay_time_text"));
                if (orderRecordBean.payType == 1) {
                    this.zzt.setText(zzt.zza(this.zza, "ld_order_coin_pay_center"));
                    this.zzad.setText(zzt.zza(this.zza, "ld_order_coin_pay_center"));
                    this.zzu.setText("+" + orderRecordBean.productCoin + " COIN");
                    this.zzu.setTextColor(Color.parseColor("#FF7A00"));
                } else if (orderRecordBean.payType == 3) {
                    this.zzt.setText(orderRecordBean.appName);
                    this.zzad.setText(orderRecordBean.appName);
                    this.zzu.setText("-" + orderRecordBean.productCoin + " COIN");
                    this.zzu.setTextColor(Color.parseColor("#B2000000"));
                }
            } else {
                this.zzt.setText(orderRecordBean.appName);
                this.zzw.setText(zzt.zza(this.zza, "ld_consumption_time_text"));
                this.zzu.setText("-" + zzi.zza(orderRecordBean.amount, orderRecordBean.payCurrency));
                this.zzu.setTextColor(Color.parseColor("#B2000000"));
                this.zzad.setText(orderRecordBean.appName);
            }
            String str = orderRecordBean.orderStatus;
            if (str.equals("SUCCESS")) {
                str = zzt.zza(this.zza, "ld_success_text");
            } else if (str.equals("ORDER_CLOSE")) {
                str = zzt.zza(this.zza, "ld_order_close_text");
            }
            this.zzv.setText(str);
            this.zzx.setText(orderRecordBean.createTime);
            this.zzy.setText(orderRecordBean.payChannelConfName);
            this.zzz.setText(String.valueOf(orderRecordBean.f685id));
            this.zzaa.setText(String.valueOf(orderRecordBean.productId));
            this.zzab.setText(orderRecordBean.productName);
            this.zzac.setText(String.valueOf(orderRecordBean.appId));
            String str2 = orderRecordBean.appIcon;
            if (!TextUtils.isEmpty(str2) && str2.contains("http")) {
                PictureHelper.loadImage(str2, this.zzs);
            } else {
                this.zzs.setImageResource(zzt.zzd(this.zza, "ld_order_default_icon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzg() {
        LinearLayout linearLayout = this.zzq;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
            this.zzp.setVisibility(0);
        }
    }

    /* loaded from: classes2.dex */
    public class zza extends BaseAdapter {
        private List<OrderRecordBean> zzb;
        private boolean zzc;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public zza() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List<OrderRecordBean> list = this.zzb;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.zzb.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            C3258zza c3258zza;
            if (view == null) {
                view = zzt.zza(AccountOrderView.this.zza, "ld_account_order_item", (ViewGroup) null);
                c3258zza = new C3258zza(view, AccountOrderView.this.zza);
                view.setTag(c3258zza);
            } else {
                c3258zza = (C3258zza) view.getTag();
            }
            List<OrderRecordBean> list = this.zzb;
            if (list != null && i < list.size()) {
                try {
                    OrderRecordBean orderRecordBean = this.zzb.get(i);
                    c3258zza.zzb.setText(orderRecordBean.createTime);
                    c3258zza.zzc.setText(orderRecordBean.payChannelConfName);
                    if (this.zzc) {
                        if (orderRecordBean.payType == 1) {
                            String zza = zzt.zza(AccountOrderView.this.zza, "ld_order_coin_pay_center");
                            String zza2 = zzt.zza(AccountOrderView.this.zza, "ld_order_coin_pay");
                            c3258zza.zza.setText(zza + " · " + zza2);
                            c3258zza.zzd.setText("+" + orderRecordBean.productCoin + " COIN");
                            c3258zza.zzd.setTextColor(Color.parseColor("#FF7A00"));
                        } else if (orderRecordBean.payType == 3) {
                            c3258zza.zza.setText(orderRecordBean.appName + " · " + orderRecordBean.productName);
                            c3258zza.zzd.setText("-" + orderRecordBean.productCoin + " COIN");
                            c3258zza.zzd.setTextColor(Color.parseColor("#B2000000"));
                        }
                    } else {
                        c3258zza.zza.setText(orderRecordBean.appName + " · " + orderRecordBean.productName);
                        TextView textView = c3258zza.zzd;
                        StringBuilder sb = new StringBuilder("-");
                        sb.append(zzi.zza(orderRecordBean.amount, orderRecordBean.payCurrency));
                        textView.setText(sb.toString());
                        c3258zza.zzd.setTextColor(Color.parseColor("#B2000000"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return view;
        }

        /* renamed from: com.ld.sdk.ui.account.AccountOrderView$zza$zza, reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public class C3258zza {
            public TextView zza;
            public TextView zzb;
            public TextView zzc;
            public TextView zzd;

            public C3258zza(View view, Context context) {
                this.zza = (TextView) zzt.zza(context, "desc_tv_1", view);
                this.zzb = (TextView) zzt.zza(context, "desc_tv_2", view);
                this.zzc = (TextView) zzt.zza(context, "desc_tv_3", view);
                this.zzd = (TextView) zzt.zza(context, "desc_tv_4", view);
            }
        }

        public void zza(List<OrderRecordBean> list, boolean z) {
            this.zzc = z;
            this.zzb = list;
            notifyDataSetChanged();
        }
    }
}
