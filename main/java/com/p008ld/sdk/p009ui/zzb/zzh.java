package com.p008ld.sdk.p009ui.zzb;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.core.bean.CreateOrderBean;
import com.p008ld.sdk.core.bean.LdGamePayInfo;
import com.p008ld.sdk.core.bean.PayConfBean;
import com.p008ld.sdk.core.bean.PayOrderBean;
import com.p008ld.sdk.core.bean.PayOrderInfo;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.track.LDTrackEvent;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;

/* compiled from: LBitPayDetailDialog.java */
/* loaded from: classes2.dex */
public class zzh extends Dialog {
    boolean zza;
    private TextView zzb;
    private View zzc;
    private TextView zzd;
    private TextView zze;
    private TextView zzf;
    private LDCallback1<String> zzg;
    private View zzh;
    private ListView zzi;
    private TextView zzj;
    private TextView zzk;
    private long zzl;
    private String zzm;
    private long zzn;
    private long zzo;
    private String zzp;
    private Button zzq;
    private boolean zzr;
    private int zzs;
    private PayOrderInfo zzt;

    public zzh(Context context, int i, int i2, int i3, LdGamePayInfo ldGamePayInfo, CreateOrderBean createOrderBean, LDCallback1<String> lDCallback1) {
        super(context, zzt.zzh(context, "KKKDialog"));
        this.zzr = false;
        this.zzg = lDCallback1;
        this.zzs = i3;
        this.zzn = createOrderBean.coin;
        this.zzo = createOrderBean.totalCoin;
        this.zzm = ldGamePayInfo.productId;
        this.zzp = ldGamePayInfo.tradeName;
        zza(context);
        zza(createOrderBean, i, i2);
    }

    private void zza(Context context) {
        this.zza = zzi.zza(context);
        View zza = zzt.zza(context, "ld_pay_details_layout", (ViewGroup) null);
        this.zze = (TextView) zzt.zza(context, "my_ld_coins_tv", zza);
        TextView textView = (TextView) zzt.zza(context, "commodity_name_tv", zza);
        this.zzd = (TextView) zzt.zza(context, "coupon_desc_tv", zza);
        this.zzb = (TextView) zzt.zza(context, "preferential_price_tv", zza);
        this.zzf = (TextView) zzt.zza(context, "select_coupon_desc_tv", zza);
        this.zzc = zzt.zza(context, "selected_coupon_layout", zza);
        this.zzh = zzt.zza(context, "coupon_layout", zza);
        this.zzi = (ListView) zzt.zza(context, "coupon_listview", zza);
        this.zzk = (TextView) zzt.zza(context, "currency_and_price_tv", zza);
        textView.setText(this.zzp);
        this.zzj = (TextView) zzt.zza(context, "ld_coin_hint_tv", zza);
        Button button = (Button) zzt.zza(context, "charge_btn", zza);
        this.zzq = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzh.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (zzh.this.zzq.getText().toString().equals(zzt.zza(zzh.this.getContext(), "ld_immediate_pay_text"))) {
                    zzh.this.zzq.setText(zzt.zza(zzh.this.getContext(), "ld_payment_in_progress_text"));
                    zzh.this.zza();
                }
            }
        });
        zzt.zza(context, "ldbit_pay_back_img", zza).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzh.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzh.this.dismiss();
            }
        });
        zzt.zza(context, "coupon_back_img", zza).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzh.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzh.this.zza(false);
            }
        });
        setContentView(zza);
        zzb();
    }

    private void zza(CreateOrderBean createOrderBean, int i, int i2) {
        this.zze.setText(String.valueOf(this.zzo));
        this.zzl = createOrderBean.orderId;
        PayConfBean payConfBean = createOrderBean.payConfigVos.get(i);
        PayConfBean.PayChannelProductVosBean.PayChannelConfigVosBean payChannelConfigVosBean = payConfBean.payChannelConfigVos.get(i2);
        this.zzj.setText(payConfBean.coinDescription);
        this.zzk.setText(zzi.zza(payConfBean.amount, payConfBean.currency));
        this.zzb.setText(String.valueOf(this.zzn));
        PayOrderInfo payOrderInfo = new PayOrderInfo();
        this.zzt = payOrderInfo;
        payOrderInfo.orderId = String.valueOf(createOrderBean.orderId);
        this.zzt.gradeChannelTypeId = String.valueOf(payChannelConfigVosBean.gradeChannelTypeId);
        this.zzt.regionCode = payConfBean.countryCode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza() {
        zza.zzf().zza(this.zzt, new LDQueryCallback<PayOrderBean>() { // from class: com.ld.sdk.ui.zzb.zzh.4
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(PayOrderBean payOrderBean, LDException lDException) {
                if (zzh.this.isShowing()) {
                    zzh.this.zzq.setText(zzt.zza(zzh.this.getContext(), "ld_immediate_pay_text"));
                    if (payOrderBean == null) {
                        LDSdk.trackPayEvent(LDTrackEvent.PAY_FINISHED_PAY_PAGE, lDException, zzh.this.zzt.orderId);
                        LDLog.m573e("LBitPayDetailDialog -> payDirectOrder error ：" + lDException);
                        LDUtil.toast(lDException.toString());
                        return;
                    }
                    zzh.this.dismiss();
                    zzh.this.zzg.done(zzh.this.zzt.orderId);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(final boolean z) {
        float f;
        float f2;
        AnimationSet animationSet = new AnimationSet(true);
        float width = this.zzh.getWidth();
        if (z) {
            this.zzh.setVisibility(0);
            f2 = this.zzh.getWidth();
            f = 0.0f;
        } else {
            f = width;
            f2 = 0.0f;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(f2, f, 0.0f, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);
        this.zzh.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.ld.sdk.ui.zzb.zzh.5
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (z) {
                    return;
                }
                zzh.this.zzh.setVisibility(8);
            }
        });
    }

    private void zzb() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(!this.zza ? 80 : 17);
            WindowManager.LayoutParams attributes = window.getAttributes();
            if (this.zza) {
                attributes.width = -2;
            } else {
                attributes.width = -1;
            }
            attributes.height = this.zzs;
            window.setAttributes(attributes);
            if (!this.zza) {
                window.setWindowAnimations(zzt.zzh(getContext(), "AnimBottom"));
            }
        }
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        show();
    }
}
