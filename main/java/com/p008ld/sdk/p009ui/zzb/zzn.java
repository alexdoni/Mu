package com.p008ld.sdk.p009ui.zzb;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.core.UserAccountMgr;
import com.p008ld.sdk.core.bean.CreateOrderBean;
import com.p008ld.sdk.core.bean.LdGamePayInfo;
import com.p008ld.sdk.core.bean.OrderStatusBean;
import com.p008ld.sdk.core.bean.PayConfBean;
import com.p008ld.sdk.core.bean.PayOrderBean;
import com.p008ld.sdk.core.bean.PayOrderInfo;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDPayCallback;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.p009ui.zza.zzb;
import com.p008ld.sdk.p009ui.zza.zzi;
import com.p008ld.sdk.p009ui.zzb.zzm;
import com.p008ld.sdk.track.LDTrackEvent;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzt;
import java.util.ArrayList;
import java.util.List;

/* compiled from: RechargeDialog.java */
/* loaded from: classes2.dex */
public class zzn extends Dialog {
    boolean zza;
    private zzl zzaa;
    private final Runnable zzab;
    private zzg zzac;
    private LDPayCallback zzb;
    private String zzc;
    private TextView zzd;
    private TextView zze;
    private View zzf;
    private ListView zzg;
    private ListView zzh;
    private View zzi;
    private LinearLayout zzj;
    private ImageView zzk;
    private Button zzl;
    private String zzm;
    private String zzn;
    private CreateOrderBean zzo;
    private LdGamePayInfo zzp;
    private View zzq;
    private AnimationDrawable zzr;
    private int zzs;
    private int zzt;
    private int zzu;
    private boolean zzv;
    private zzi zzw;
    private Handler zzx;
    private String zzy;
    private zzm zzz;

    public zzn(Context context, LdGamePayInfo ldGamePayInfo, LDPayCallback lDPayCallback) {
        super(context, zzt.zzh(context, "KKKDialog"));
        this.zzv = false;
        this.zzab = new Runnable() { // from class: com.ld.sdk.ui.zzb.zzn.2
            @Override // java.lang.Runnable
            public void run() {
                LDLog.m573e("RechargeDialog -> start queryOrderStatus : orderId = " + zzn.this.zzy);
                zza.zzf().zzc(zzn.this.zzy, new LDQueryCallback<OrderStatusBean>() { // from class: com.ld.sdk.ui.zzb.zzn.2.1
                    @Override // com.p008ld.sdk.internal.LDCallback2
                    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                    public void done(OrderStatusBean orderStatusBean, LDException lDException) {
                        if (zzn.this.isShowing()) {
                            if (orderStatusBean != null && (orderStatusBean.orderStatus.contains("SUCCESS") || orderStatusBean.orderStatus.contains("FAIL"))) {
                                if (!orderStatusBean.orderStatus.contains("FAIL")) {
                                    LDSdk.trackPayEvent(LDTrackEvent.PAY_FINISHED_PAY_PAGE, null, zzn.this.zzy);
                                    LDLog.m573e("RechargeDialog -> queryOrderStatus success : orderId = " + zzn.this.zzy);
                                    if (zzn.this.zzb != null) {
                                        zzn.this.zzb.onSuccess(zza.zzf().zza().getSpaceUserId(), zzn.this.zzy);
                                    }
                                } else {
                                    LDLog.m573e("RechargeDialog -> queryOrderStatus fail : orderId = " + zzn.this.zzy);
                                    LDSdk.trackPayEvent(LDTrackEvent.PAY_FINISHED_PAY_PAGE, new LDException("Top up failed"), zzn.this.zzy);
                                    LDUtil.toast("Top up failed");
                                    if (zzn.this.zzb != null) {
                                        zzn.this.zzb.onError(new LDException("Top up failed"));
                                    }
                                    if (zzn.this.zzac == null) {
                                        zzn.this.zzc();
                                    }
                                }
                                if (zzn.this.zzac != null && zzn.this.zzac.isShowing()) {
                                    zzn.this.zzac.dismiss();
                                }
                                zzn.this.dismiss();
                                return;
                            }
                            LDLog.m573e("RechargeDialog -> continue queryOrderStatus  : orderId = " + zzn.this.zzy + " , " + orderStatusBean.orderStatus);
                            zzn.this.zzx.postDelayed(zzn.this.zzab, 1500L);
                        }
                    }
                });
            }
        };
        this.zzb = lDPayCallback;
        this.zzp = ldGamePayInfo;
        zza();
        zza(context);
    }

    private void zza(Context context) {
        this.zza = com.p008ld.sdk.util.zzi.zza(getContext());
        View zza = zzt.zza(context, "ld_recharge_dialog_layout", (ViewGroup) null);
        this.zzc = zzt.zza(getContext(), "ld_recharge_text") + " LD COIN";
        this.zzd = (TextView) zzt.zza(context, "title_name_tv", zza);
        this.zzq = zzt.zza(context, "loading_layout", zza);
        this.zze = (TextView) zzt.zza(context, "current_country_area_text", zza);
        this.zzf = zzt.zza(context, "top_layout", zza);
        this.zzg = (ListView) zzt.zza(context, "recharge_mode_list", zza);
        this.zzj = (LinearLayout) zzt.zza(context, "ll_contact", zza);
        this.zzk = (ImageView) zzt.zza(context, "iv_contact", zza);
        this.zzl = (Button) zzt.zza(context, "charge_btn", zza);
        this.zzr = (AnimationDrawable) ((ImageView) zzt.zza(context, "create_order_loading_img", zza)).getDrawable();
        this.zzi = zzt.zza(context, "country_area_layout", zza);
        ((TextView) zzt.zza(context, "commodity_name", zza)).setText(this.zzp.tradeName);
        this.zzd.setText(this.zzc);
        zza(context, zza);
        setContentView(zza);
        zzf();
        this.zzq.postDelayed(new Runnable() { // from class: com.ld.sdk.ui.zzb.zzn.1
            @Override // java.lang.Runnable
            public void run() {
                if (zzn.this.zzv) {
                    return;
                }
                zzn.this.zza(true);
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza() {
        zza.zzf().zza(this.zzp, new LDQueryCallback<CreateOrderBean>() { // from class: com.ld.sdk.ui.zzb.zzn.8
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(CreateOrderBean createOrderBean, LDException lDException) {
                LDSdk.trackEvent(LDTrackEvent.DISPLAY_PAY_PAGE, lDException);
                zzn.this.zza(false);
                if (lDException == null) {
                    zzn.this.zzo = createOrderBean;
                    zzn.this.zzy = zzn.this.zzo.orderId + "";
                    zzn.this.zza(0);
                    return;
                }
                LDLog.m573e("RechargeDialog -> createDirectOrder error ：" + lDException);
                if (zzn.this.zzb != null) {
                    zzn.this.zzb.onError(lDException);
                }
                zzn.this.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(int i) {
        this.zzt = 0;
        this.zzs = i;
        PayConfBean payConfBean = this.zzo.payConfigVos.get(this.zzs);
        this.zzm = payConfBean.countryCode;
        this.zzn = payConfBean.currency;
        List<PayConfBean.PayChannelProductVosBean.PayChannelConfigVosBean> list = payConfBean.payChannelConfigVos;
        this.zze.setText(payConfBean.countryName);
        zza(list);
    }

    private void zza(List<PayConfBean.PayChannelProductVosBean.PayChannelConfigVosBean> list) {
        if (this.zzw == null) {
            this.zzt = 0;
            zzi zziVar = new zzi(getContext(), list);
            this.zzw = zziVar;
            this.zzg.setAdapter((ListAdapter) zziVar);
            this.zzg.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.zzb.zzn.9
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    zzn.this.zzt = i;
                    zzn.this.zzw.zza(i);
                }
            });
        }
        this.zzw.zza(this.zzt);
        this.zzw.zza(list);
    }

    private void zza(Context context, final View view) {
        zzt.zza(context, "selected_country_area_layout", view).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzn.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                zzn.this.zzd.setText(zzt.zza(zzn.this.getContext(), "ld_country_area_text"));
                if (zzn.this.zzh == null) {
                    zzn zznVar = zzn.this;
                    zznVar.zzh = (ListView) zzt.zza(zznVar.getContext(), "country_area_listview", view);
                    ArrayList arrayList = new ArrayList();
                    if (zzn.this.zzo != null && zzn.this.zzo.payConfigVos != null) {
                        List<PayConfBean> list = zzn.this.zzo.payConfigVos;
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            arrayList.add(list.get(i).countryName);
                        }
                    }
                    final zzb zzbVar = new zzb(zzn.this.getContext(), arrayList, "ld_pay_country_area_item_layout");
                    zzn.this.zzh.setAdapter((ListAdapter) zzbVar);
                    zzn.this.zzh.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.zzb.zzn.10.1
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public void onItemClick(AdapterView<?> adapterView, View view3, int i2, long j) {
                            zzbVar.zza(i2);
                            zzn.this.zza(i2);
                            zzn.this.zze();
                        }
                    });
                }
                zzn.this.zzi.setVisibility(0);
                zzn.this.zzf.setVisibility(8);
            }
        });
        zzt.zza(context, "back_pay", view).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzn.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                zzn.this.zze();
            }
        });
        zzt.zza(context, "close_pay_dialog_img", view).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzn.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (zzn.this.zzb != null) {
                    zzn.this.zzb.onCancel();
                }
                zzn.this.dismiss();
            }
        });
        this.zzl.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzn.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (com.p008ld.sdk.util.zzi.zzd()) {
                    return;
                }
                zzn.this.zzb();
            }
        });
        this.zzj.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzn.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                zzn.this.zzg();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb() {
        CreateOrderBean createOrderBean;
        if (this.zzw == null || (createOrderBean = this.zzo) == null) {
            return;
        }
        String valueOf = String.valueOf(createOrderBean.orderId);
        final PayConfBean.PayChannelProductVosBean.PayChannelConfigVosBean zza = this.zzw.zza();
        if (zza == null) {
            LDUtil.toast(zzt.zza(getContext(), "ld_please_select_pay_mode_text"));
            return;
        }
        if (zza.gradeChannelTypeId == 0) {
            LDSdk.trackPayEvent(LDTrackEvent.CLICK_PAY, null, valueOf);
            zzd();
            return;
        }
        LDSdk.trackPayEvent(LDTrackEvent.CLICK_PAY, null, valueOf);
        PayOrderInfo payOrderInfo = new PayOrderInfo();
        payOrderInfo.orderId = valueOf;
        payOrderInfo.gradeChannelTypeId = String.valueOf(zza.gradeChannelTypeId);
        payOrderInfo.regionCode = this.zzm;
        zza(true);
        zza.zzf().zza(payOrderInfo, new LDQueryCallback<PayOrderBean>() { // from class: com.ld.sdk.ui.zzb.zzn.15
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(PayOrderBean payOrderBean, LDException lDException) {
                zzn.this.zza(false);
                if (payOrderBean != null) {
                    zzn.this.zzy = String.valueOf(payOrderBean.orderId);
                    if (zza.osWeb == 1) {
                        zzn.this.zza(payOrderBean.payUrl, zza);
                    } else {
                        zzn.this.zza(payOrderBean.payUrl);
                    }
                    if (zzn.this.zzx == null) {
                        zzn.this.zzx = new Handler(Looper.getMainLooper());
                    }
                    zzn.this.zzx.postDelayed(zzn.this.zzab, 3000L);
                    return;
                }
                LDLog.m573e("RechargeDialog -> payDirectOrder error ：" + lDException);
                LDUtil.toast(lDException.toString());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzc() {
        new zzk(getContext()).zzb().zza(zzt.zza(getContext(), "ld_dialog_result_error_title")).zzb(zzt.zza(getContext(), "ld_dialog_result_error_msg")).zza(new LDCallback1<Boolean>() { // from class: com.ld.sdk.ui.zzb.zzn.3
            @Override // com.p008ld.sdk.internal.LDCallback1
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(Boolean bool) {
                LDLog.m573e("showPayErrorDialog:" + bool);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(String str, PayConfBean.PayChannelProductVosBean.PayChannelConfigVosBean payChannelConfigVosBean) {
        com.p008ld.sdk.util.zzi.zza(getContext(), com.p008ld.sdk.util.zzi.zzb, str);
        String zza = com.p008ld.sdk.util.zzi.zza(payChannelConfigVosBean.price, this.zzn);
        if (!com.p008ld.sdk.util.zzi.zza((CharSequence) payChannelConfigVosBean.discountPrice)) {
            zza = com.p008ld.sdk.util.zzi.zza(payChannelConfigVosBean.discountPrice, this.zzn);
        }
        this.zzac = new zzg(getContext(), this.zzy, zza, this.zzp.tradeName, new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzn.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (zzn.this.zzb != null) {
                    zzn.this.zzb.onCancel();
                }
                zzn.this.dismiss();
            }
        });
    }

    private void zzd() {
        if (this.zzo.coin > this.zzo.totalCoin) {
            zzl zza = new zzl(getContext()).zza(zzt.zza(getContext(), "ld_bit_balance_text")).zzb(zzt.zza(getContext(), "ld_bit_balance_hint_text")).zzc(zzt.zza(getContext(), "ld_immediate_recharge_text")).zza().zza(new LDCallback1<Boolean>() { // from class: com.ld.sdk.ui.zzb.zzn.5
                @Override // com.p008ld.sdk.internal.LDCallback1
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(Boolean bool) {
                    if (bool.booleanValue()) {
                        UserAccountMgr.zza().zza(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH, LDSdk.getFloatViewEnable(), new LDCallback1() { // from class: com.ld.sdk.ui.zzb.zzn.5.1
                            @Override // com.p008ld.sdk.internal.LDCallback1
                            public void done(Object obj) {
                                LDLog.m573e("RechargeDialog -> start createDirectOrder...");
                                zzn.this.zza();
                            }
                        });
                        if (zzn.this.zzb != null) {
                            zzn.this.zzb.onCancel();
                        }
                    }
                }
            });
            this.zzaa = zza;
            zza.show();
            return;
        }
        new zzh(getContext(), this.zzs, this.zzt, this.zzu, this.zzp, this.zzo, new LDCallback1<String>() { // from class: com.ld.sdk.ui.zzb.zzn.6
            @Override // com.p008ld.sdk.internal.LDCallback1
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(String str) {
                LDLog.m573e("LBitPayDetailDialog -> success：" + str);
                LDSdk.trackPayEvent(LDTrackEvent.PAY_FINISHED_PAY_PAGE, null, str);
                if (zzn.this.zzb != null) {
                    zzn.this.zzb.onSuccess(zza.zzf().zza().getSpaceUserId(), str);
                }
                zzn.this.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(String str) {
        this.zzz = new zzm(getContext(), str, new zzm.zzb() { // from class: com.ld.sdk.ui.zzb.zzn.7
            @Override // com.ld.sdk.ui.zzb.zzm.zzb
            public void zza() {
                LDLog.m573e("PayH5PageDialog -> success");
                zzn.this.zzq.setVisibility(0);
                zzn.this.zzq.postDelayed(new Runnable() { // from class: com.ld.sdk.ui.zzb.zzn.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (zzn.this.isShowing()) {
                            if (zzn.this.zzb != null) {
                                zzn.this.zzb.onError(new LDException(zzt.zza(zzn.this.getContext(), "ld_pay_fail_text")));
                            }
                            zzn.this.dismiss();
                        }
                    }
                }, 30000L);
            }

            @Override // com.ld.sdk.ui.zzb.zzm.zzb
            public void zzb() {
                if (zzn.this.zzb != null) {
                    zzn.this.zzb.onCancel();
                }
                zzn.this.dismiss();
            }
        });
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        AnimationDrawable animationDrawable = this.zzr;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        zzg zzgVar = this.zzac;
        if (zzgVar != null && zzgVar.isShowing()) {
            this.zzac.dismiss();
        }
        zzl zzlVar = this.zzaa;
        if (zzlVar != null && zzlVar.isShowing()) {
            this.zzaa.dismiss();
        }
        zzm zzmVar = this.zzz;
        if (zzmVar != null && zzmVar.isShowing()) {
            this.zzz.dismiss();
        }
        Handler handler = this.zzx;
        if (handler != null) {
            handler.removeCallbacks(this.zzab);
        }
        super.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zze() {
        View view = this.zzi;
        if (view != null) {
            view.setVisibility(8);
            this.zzf.setVisibility(0);
        }
        this.zzd.setText(this.zzc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(boolean z) {
        if (z) {
            this.zzr.start();
            this.zzq.setVisibility(0);
        } else {
            this.zzv = true;
            this.zzr.stop();
            this.zzq.setVisibility(8);
        }
    }

    private void zzf() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(!this.zza ? 80 : 17);
            WindowManager.LayoutParams attributes = window.getAttributes();
            int i = getContext().getResources().getDisplayMetrics().heightPixels;
            if (this.zza) {
                this.zzu = (int) (i * 0.9d);
                attributes.width = -2;
            } else if (i > 1920) {
                this.zzu = (int) (i * 0.55d);
                attributes.width = -2;
            } else {
                this.zzu = (int) (i * 0.8d);
                attributes.width = -1;
            }
            attributes.height = this.zzu;
            window.setAttributes(attributes);
            if (!this.zza) {
                window.setWindowAnimations(zzt.zzh(getContext(), "AnimBottom"));
            }
        }
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzg() {
        zzc zzcVar = new zzc(getContext(), true);
        int zza = com.p008ld.sdk.util.zzi.zza(getContext(), 100.0f);
        int zza2 = this.zzu - com.p008ld.sdk.util.zzi.zza(getContext(), 60.0f);
        if (!this.zza) {
            zza = (int) ((getContext().getResources().getDisplayMetrics().widthPixels * 0.5d) - (zzcVar.getWidth() * 0.5d));
        }
        zzcVar.showAtLocation(this.zzk, 0, zza, zza2);
    }
}
