package com.p008ld.sdk.p009ui.zzb;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.facebook.internal.AnalyticsEvents;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;

/* compiled from: LDResultDialog.java */
/* loaded from: classes2.dex */
public class zzk extends Dialog {
    public TextView zza;
    public TextView zzb;
    public Button zzc;
    private ImageView zzd;
    private ImageView zze;
    private GridView zzf;
    private LDCallback1 zzg;
    private boolean zzh;

    public zzk(Context context) {
        super(context, context.getResources().getIdentifier("kkk_gift_dialog", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, context.getPackageName()));
        this.zzh = false;
        zza(context);
    }

    public void zza(final Context context) {
        View zza = zzt.zza(context, "ld_dialog_result_layout", (ViewGroup) null);
        this.zzd = (ImageView) zzt.zza(context, "iv_dialog_close", zza);
        this.zze = (ImageView) zzt.zza(context, "iv_result", zza);
        this.zza = (TextView) zzt.zza(context, "tv_title", zza);
        this.zzb = (TextView) zzt.zza(context, "tv_message", zza);
        this.zzc = (Button) zzt.zza(context, "btn_confirm", zza);
        this.zzf = (GridView) zzt.zza(context, "gridview", zza);
        final ConfigBean zze = zza.zzf().zze();
        if (zze != null && zze.sdkCustomerServiceConfigVoList != null) {
            int size = zze.sdkCustomerServiceConfigVoList.size();
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.zzf.getLayoutParams();
            layoutParams.width = zzt.zzb(context, "ld_dp_40") * size;
            this.zzf.setLayoutParams(layoutParams);
            this.zzf.setNumColumns(size);
            this.zzf.setAdapter((ListAdapter) new com.p008ld.sdk.p009ui.zza.zza(context, zze.sdkCustomerServiceConfigVoList, "ld_dialog_contact_us_item_layout"));
            this.zzf.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.zzb.zzk.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    zzi.zza(context, zzi.zzc, zze.sdkCustomerServiceConfigVoList.get(i).url);
                }
            });
        }
        this.zzd.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzk.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzk.this.dismiss();
            }
        });
        this.zzc.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzk.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzk.this.zzh = true;
                zzk.this.dismiss();
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.ld.sdk.ui.zzb.zzk.4
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (zzk.this.zzg != null) {
                    zzk.this.zzg.done(Boolean.valueOf(zzk.this.zzh));
                }
            }
        });
        setContentView(zza);
    }

    public zzk zza(String str) {
        this.zza.setText(str);
        return this;
    }

    public zzk zzb(String str) {
        this.zzb.setText(str);
        return this;
    }

    public zzk zza() {
        this.zze.setImageResource(zzt.zzd(getContext(), "ld_dialog_result_ok"));
        this.zzf.setVisibility(8);
        return this;
    }

    public zzk zzb() {
        this.zze.setImageResource(zzt.zzd(getContext(), "ld_dialog_result_error"));
        this.zzf.setVisibility(0);
        return this;
    }

    public zzk zza(LDCallback1<Boolean> lDCallback1) {
        this.zzg = lDCallback1;
        return this;
    }
}
