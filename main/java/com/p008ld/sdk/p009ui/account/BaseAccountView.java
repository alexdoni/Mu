package com.p008ld.sdk.p009ui.account;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.p008ld.sdk.util.zzt;

/* loaded from: classes2.dex */
public abstract class BaseAccountView extends LinearLayout implements View.OnClickListener {
    public Context zza;
    public View zzb;
    public ImageView zzc;
    public View zzd;
    private View.OnClickListener zze;
    private TextView zzf;
    private TextView zzg;
    private View zzh;
    private ImageView zzi;

    public abstract String getTitle();

    public abstract boolean zza();

    public abstract void zzb();

    public BaseAccountView(Context context) {
        super(context);
        zza(context);
    }

    public BaseAccountView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        zza(context);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        View.OnClickListener onClickListener = this.zze;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public void setBackListener(View.OnClickListener onClickListener) {
        this.zze = onClickListener;
    }

    public void setBackListener(View view) {
        view.setOnClickListener(this);
    }

    private void zza(Context context) {
        this.zza = context;
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        setOrientation(1);
        View zza = zzt.zza(context, "ld_user_center_top_layout", (ViewGroup) null);
        if (getTitle().equals("")) {
            zza.setVisibility(8);
        } else {
            this.zzf = (TextView) zzt.zza(context, "title_tv", zza);
            this.zzb = zzt.zza(context, "subtitle_layout", zza);
            this.zzg = (TextView) zzt.zza(context, "subtitle_tv", zza);
            this.zzh = zzt.zza(context, "subtitle_hot_view", zza);
            this.zzf.setText(zzt.zza(context, getTitle()));
            this.zzf.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
            this.zzf.getPaint().setStrokeWidth(0.4f);
            this.zzi = (ImageView) zzt.zza(context, "back_img", zza);
            this.zzc = (ImageView) zzt.zza(context, "iv_right", zza);
            this.zzd = zzt.zza(context, "view_line", zza);
            View zza2 = zzt.zza(context, "content_layout", zza);
            if (zza()) {
                this.zzi.setOnClickListener(this);
                this.zzi.setVisibility(0);
            } else {
                this.zzi.setVisibility(8);
            }
            zza2.setBackgroundColor(getTitleBgColor());
        }
        addView(zza);
    }

    public void setTitle(String str) {
        TextView textView = this.zzf;
        if (textView == null || str == null) {
            return;
        }
        textView.setText(str);
    }

    public void setSubtitleText(String str, int i, View.OnClickListener onClickListener) {
        TextView textView = this.zzg;
        if (textView != null) {
            textView.setVisibility(0);
            this.zzg.setText(str);
            if (getResources().getConfiguration().orientation == 1) {
                this.zzi.setVisibility(0);
            } else {
                this.zzi.setVisibility(8);
            }
            this.zzf.setVisibility(8);
            View view = this.zzb;
            if (view != null) {
                view.setTag(Integer.valueOf(i));
                this.zzb.setOnClickListener(onClickListener);
            }
        }
    }

    public void zza(View.OnClickListener onClickListener) {
        View view = new View(getContext());
        view.setTag(13000);
        view.setOnClickListener(onClickListener);
        view.performClick();
    }

    public int getTitleBgColor() {
        return Color.parseColor("#F2F3F5");
    }
}
