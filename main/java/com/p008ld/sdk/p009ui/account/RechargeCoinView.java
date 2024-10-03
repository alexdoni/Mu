package com.p008ld.sdk.p009ui.account;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.core.UserAccountMgr;
import com.p008ld.sdk.core.bean.LDGradeQueryInfo;
import com.p008ld.sdk.core.bean.LDPayChannelInfo;
import com.p008ld.sdk.core.bean.LDSupportPayGrade;
import com.p008ld.sdk.core.bean.OrderStatusBean;
import com.p008ld.sdk.core.bean.PayInfo;
import com.p008ld.sdk.core.bean.PayUrlBean;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.internal.LDCallback2;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.p009ui.zza.zzg;
import com.p008ld.sdk.p009ui.zzb.zzb;
import com.p008ld.sdk.p009ui.zzb.zzk;
import com.p008ld.sdk.p009ui.zzb.zzm;
import com.p008ld.sdk.track.LDTrackEvent;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzn;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.MyListView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class RechargeCoinView extends BaseAccountView {
    LDSupportPayGrade zze;
    private zzg zzf;
    private Activity zzg;
    private View zzh;
    private TextView zzi;
    private View.OnClickListener zzj;
    private String zzk;
    private View zzl;
    private String zzm;
    private TextView zzn;
    private MyListView zzo;
    private int zzp;
    private int zzq;
    private Handler zzr;
    private String zzs;
    private boolean zzt;
    private zzm zzu;
    private final Runnable zzv;
    private boolean zzw;
    private com.p008ld.sdk.p009ui.zzb.zzg zzx;

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public String getTitle() {
        return "ld_coin_text";
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public boolean zza() {
        return false;
    }

    public RechargeCoinView(Activity activity, View.OnClickListener onClickListener) {
        super(activity);
        this.zzp = -1;
        this.zzq = 0;
        this.zze = null;
        this.zzv = new Runnable() { // from class: com.ld.sdk.ui.account.RechargeCoinView.2
            @Override // java.lang.Runnable
            public void run() {
                if (RechargeCoinView.this.zzt) {
                    LDLog.m573e("RechargeCoinView -> mRunnable : isSuccess = " + RechargeCoinView.this.zzw);
                    if (RechargeCoinView.this.zzw) {
                        View view = new View(RechargeCoinView.this.zzg);
                        view.setOnClickListener(RechargeCoinView.this.zzj);
                        view.setTag(3000);
                        view.performClick();
                        return;
                    }
                    return;
                }
                if (UserAccountMgr.zza().zzf()) {
                    LDLog.m573e("RechargeCoinView -> start queryOrderStatus : orderId = " + RechargeCoinView.this.zzs + " , " + RechargeCoinView.this.zzt);
                    zza.zzf().zzc(RechargeCoinView.this.zzs, new LDQueryCallback<OrderStatusBean>() { // from class: com.ld.sdk.ui.account.RechargeCoinView.2.1
                        @Override // com.p008ld.sdk.internal.LDCallback2
                        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                        public void done(OrderStatusBean orderStatusBean, LDException lDException) {
                            if (orderStatusBean != null && (orderStatusBean.orderStatus.contains("SUCCESS") || orderStatusBean.orderStatus.contains("FAIL"))) {
                                if (orderStatusBean.orderStatus.contains("FAIL")) {
                                    LDLog.m573e("RechargeCoinView -> queryOrderStatus fail : orderId = " + RechargeCoinView.this.zzs);
                                    LDSdk.trackPayEvent(LDTrackEvent.PAY_FINISHED_PAY_CENTER, new LDException("FAIL"), RechargeCoinView.this.zzs);
                                    LDUtil.toast("Top up failed");
                                    if (RechargeCoinView.this.zzx == null) {
                                        RechargeCoinView.this.zzf();
                                    }
                                } else {
                                    LDLog.m573e("RechargeCoinView -> queryOrderStatus success : orderId = " + RechargeCoinView.this.zzs);
                                    RechargeCoinView.this.zzw = true;
                                    LDUtil.toast(zzt.zza(RechargeCoinView.this.getContext(), "ld_recharge_success_text"));
                                    LDSdk.trackPayEvent(LDTrackEvent.PAY_FINISHED_PAY_CENTER, null, RechargeCoinView.this.zzs);
                                    RechargeCoinView.this.zzc();
                                    if (RechargeCoinView.this.zzx == null) {
                                        RechargeCoinView.this.zze();
                                    }
                                }
                                RechargeCoinView.this.zzt = true;
                                if (RechargeCoinView.this.zzx == null || !RechargeCoinView.this.zzx.isShowing()) {
                                    return;
                                }
                                RechargeCoinView.this.zzx.dismiss();
                                return;
                            }
                            LDLog.m573e("RechargeCoinView -> continue queryOrderStatus  : orderId = " + RechargeCoinView.this.zzs + " , " + orderStatusBean.orderStatus);
                            RechargeCoinView.this.zzr.postDelayed(RechargeCoinView.this.zzv, 1500L);
                        }
                    });
                }
            }
        };
        this.zzw = false;
        zza(activity, onClickListener);
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public int getTitleBgColor() {
        return Color.parseColor("#FFFFFF");
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public void zzb() {
        MyListView myListView = this.zzo;
        if (myListView != null) {
            myListView.requestFocus();
            if (this.zze != null) {
                this.zzf.zza(this.zzp, true);
            }
        }
    }

    public void zza(final Activity activity, View.OnClickListener onClickListener) {
        this.zzg = activity;
        this.zzj = onClickListener;
        this.zzd.setVisibility(8);
        View zza = zzt.zza((Context) activity, "ld_recharge_coin_layout", (ViewGroup) this);
        this.zzi = (TextView) zzt.zza(activity, "country_area_tv", zza);
        this.zzl = zzt.zza(activity, "country_area_layout", zza);
        this.zzh = zzt.zza(activity, "drop_down_layout", zza);
        this.zzn = (TextView) zzt.zza(activity, "ld_coin_tv", zza);
        this.zzo = (MyListView) zzt.zza(activity, "list_pay", zza);
        zzg zzgVar = new zzg(activity);
        this.zzf = zzgVar;
        this.zzo.setAdapter((ListAdapter) zzgVar);
        this.zzf.zza(new zzg.zza() { // from class: com.ld.sdk.ui.account.RechargeCoinView.1
            @Override // com.ld.sdk.ui.zza.zzg.zza
            public void zza(int i, int i2, LDSupportPayGrade lDSupportPayGrade) {
                if (i < 0 || i >= RechargeCoinView.this.zzf.getCount()) {
                    return;
                }
                RechargeCoinView.this.zzp = i2;
                RechargeCoinView.this.zze = lDSupportPayGrade;
            }
        });
        ((Button) zzt.zza(activity, "btn_recharge", zza)).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.RechargeCoinView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RechargeCoinView.this.zza(activity);
            }
        });
        zzd();
        zzc();
        if (this.zzc != null) {
            this.zzc.setVisibility(0);
            this.zzc.setImageResource(zzt.zzd(activity, "ld_icon_record"));
            this.zzc.setTag(1126);
            this.zzc.setOnClickListener(onClickListener);
        }
    }

    public void zzc() {
        zza.zzf().zza(true, new LDCallback1<Long>() { // from class: com.ld.sdk.ui.account.RechargeCoinView.6
            @Override // com.p008ld.sdk.internal.LDCallback1
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(Long l) {
                RechargeCoinView.this.zzn.setText(l.toString());
            }
        });
    }

    private void zzd() {
        zza.zzf().zzb(ExpandedProductParsedResult.POUND, "location", new LDQueryCallback<List<LDGradeQueryInfo>>() { // from class: com.ld.sdk.ui.account.RechargeCoinView.7
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(List<LDGradeQueryInfo> list, LDException lDException) {
                LDSdk.trackEvent(LDTrackEvent.DISPLAY_PAY_CENTER, lDException);
                if (list != null && list.size() > 0) {
                    RechargeCoinView.this.zzb(list);
                    if (list.size() > 1) {
                        RechargeCoinView.this.zzh.setVisibility(0);
                        return;
                    } else {
                        RechargeCoinView.this.zzh.setVisibility(8);
                        return;
                    }
                }
                LDLog.m573e("RechargeCoinView -> queryGrades error ：" + lDException);
                LDUtil.toast("get data error");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<LDPayChannelInfo> zza(List<LDPayChannelInfo> list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (list != null && size > 0) {
            arrayList.clear();
            for (int i = 0; i < size; i++) {
                LDPayChannelInfo lDPayChannelInfo = list.get(i);
                if (i == 0) {
                    lDPayChannelInfo.setSelected(true);
                } else {
                    lDPayChannelInfo.setSelected(false);
                }
                arrayList.add(lDPayChannelInfo);
            }
            this.zzf.zza(0);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb(final List<LDGradeQueryInfo> list) {
        LDGradeQueryInfo lDGradeQueryInfo = list.get(0);
        this.zzf.zza(zza(lDGradeQueryInfo.getPayChannels()));
        this.zzk = lDGradeQueryInfo.getCountryCode();
        this.zzi.setText(lDGradeQueryInfo.getCountryName());
        this.zzh.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.RechargeCoinView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RechargeCoinView.this.zzc((List<LDGradeQueryInfo>) list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzc(List<LDGradeQueryInfo> list) {
        if (zzi.zza(getContext())) {
            zzb zzbVar = new zzb(this.zzg, this.zzq, list, new LDCallback2<Integer, LDGradeQueryInfo>() { // from class: com.ld.sdk.ui.account.RechargeCoinView.9
                @Override // com.p008ld.sdk.internal.LDCallback2
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(Integer num, LDGradeQueryInfo lDGradeQueryInfo) {
                    RechargeCoinView.this.zzp = -1;
                    RechargeCoinView.this.zze = null;
                    RechargeCoinView.this.zzf.zza(RechargeCoinView.this.zzp, false);
                    RechargeCoinView.this.zzq = num.intValue();
                    RechargeCoinView.this.zzf.zza(RechargeCoinView.this.zza(lDGradeQueryInfo.getPayChannels()));
                    RechargeCoinView.this.zzi.setText(lDGradeQueryInfo.getCountryName());
                    RechargeCoinView.this.zzk = lDGradeQueryInfo.getCountryCode();
                }
            });
            final View view = new View(this.zzg);
            view.setOnClickListener(this.zzj);
            view.setTag(1124);
            view.performClick();
            zzbVar.showAsDropDown(this.zzl);
            zzbVar.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.ld.sdk.ui.account.RechargeCoinView.10
                @Override // android.widget.PopupWindow.OnDismissListener
                public void onDismiss() {
                    view.setTag(1125);
                    view.performClick();
                }
            });
            return;
        }
        new com.p008ld.sdk.p009ui.zzb.zza(this.zzg, this.zzq, list, new LDCallback2<Integer, LDGradeQueryInfo>() { // from class: com.ld.sdk.ui.account.RechargeCoinView.11
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(Integer num, LDGradeQueryInfo lDGradeQueryInfo) {
                RechargeCoinView.this.zzq = num.intValue();
                RechargeCoinView.this.zzf.zza(RechargeCoinView.this.zza(lDGradeQueryInfo.getPayChannels()));
                RechargeCoinView.this.zzi.setText(lDGradeQueryInfo.getCountryName());
                RechargeCoinView.this.zzk = lDGradeQueryInfo.getCountryCode();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(Activity activity) {
        if (this.zze == null) {
            LDUtil.toast(zzt.zza(activity, "ld_please_select_pay_mode_text"));
            return;
        }
        PayInfo payInfo = new PayInfo();
        payInfo.gradeChannelTypeId = this.zze.getGradeChannelTypeId();
        payInfo.regionCode = this.zzk;
        this.zzm = this.zze.getTotalAmount() + " " + this.zze.getCurrencyUnit();
        final Dialog zza = zzn.zza((Context) activity, false);
        zza.zzf().zza(payInfo, new LDQueryCallback<PayUrlBean>() { // from class: com.ld.sdk.ui.account.RechargeCoinView.12
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(PayUrlBean payUrlBean, LDException lDException) {
                zza.dismiss();
                if (lDException == null) {
                    RechargeCoinView.this.zzs = payUrlBean.orderId;
                    RechargeCoinView.this.zzt = false;
                    LDSdk.trackPayEvent(LDTrackEvent.CLICK_PAY_CENTER, null, RechargeCoinView.this.zzs);
                    if (RechargeCoinView.this.zze.getOsWeb() == 1) {
                        RechargeCoinView.this.zzb(payUrlBean.payUrl);
                    } else {
                        RechargeCoinView.this.zza(payUrlBean.payUrl);
                    }
                    if (RechargeCoinView.this.zzr == null) {
                        RechargeCoinView.this.zzr = new Handler(Looper.getMainLooper());
                    }
                    RechargeCoinView.this.zzr.postDelayed(RechargeCoinView.this.zzv, 3000L);
                    return;
                }
                LDSdk.trackPayEvent(LDTrackEvent.CLICK_PAY_CENTER, lDException, null);
                LDLog.m573e("RechargeCoinView -> rechargeLB error ：" + lDException);
                LDUtil.toast(lDException.toString());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zze() {
        new zzk(getContext()).zza().zza(zzt.zza(getContext(), "ld_success_text")).zzb(zzt.zza(getContext(), "ld_dialog_result_ok_msg")).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzf() {
        new zzk(getContext()).zzb().zza(zzt.zza(getContext(), "ld_dialog_result_error_title")).zzb(zzt.zza(getContext(), "ld_dialog_result_error_msg")).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(String str) {
        this.zzu = new zzm(this.zzg, str, new zzm.zzb() { // from class: com.ld.sdk.ui.account.RechargeCoinView.3
            @Override // com.ld.sdk.ui.zzb.zzm.zzb
            public void zza() {
                RechargeCoinView.this.zzl.postDelayed(new Runnable() { // from class: com.ld.sdk.ui.account.RechargeCoinView.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (RechargeCoinView.this.zzt) {
                            return;
                        }
                        RechargeCoinView.this.zzt = true;
                    }
                }, 30000L);
            }

            @Override // com.ld.sdk.ui.zzb.zzm.zzb
            public void zzb() {
                RechargeCoinView.this.zzt = true;
                RechargeCoinView.this.zzw = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb(String str) {
        zzi.zza(getContext(), zzi.zzb, str);
        this.zzx = new com.p008ld.sdk.p009ui.zzb.zzg(getContext(), this.zzs, this.zzm, zzt.zza(getContext(), "ld_bit_payment_text"), new View.OnClickListener() { // from class: com.ld.sdk.ui.account.RechargeCoinView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RechargeCoinView.this.zzt = true;
                RechargeCoinView.this.zzw = false;
                if (RechargeCoinView.this.zzx == null || !RechargeCoinView.this.zzx.isShowing()) {
                    return;
                }
                RechargeCoinView.this.zzx.dismiss();
            }
        });
    }
}
