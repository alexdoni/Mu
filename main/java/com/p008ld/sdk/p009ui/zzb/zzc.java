package com.p008ld.sdk.p009ui.zzb;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;

/* compiled from: ContactServicePopWindow.java */
/* loaded from: classes2.dex */
public class zzc extends PopupWindow {
    public zzc(final Context context, boolean z) {
        View zza = zzt.zza(context, "ld_dialog_contact_service", (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) zzt.zza(context, "ll_contact", zza);
        if (z) {
            linearLayout.setBackgroundResource(zzt.zzd(context, "ld_bg_contact_up"));
        } else {
            linearLayout.setBackgroundResource(zzt.zzd(context, "ld_bg_contact_down"));
        }
        GridView gridView = (GridView) zzt.zza(context, "gridview", zza);
        final ConfigBean zze = zza.zzf().zze();
        if (zze != null && zze.sdkCustomerServiceConfigVoList != null) {
            gridView.setNumColumns(zze.sdkCustomerServiceConfigVoList.size());
            gridView.setAdapter((ListAdapter) new com.p008ld.sdk.p009ui.zza.zza(context.getApplicationContext(), zze.sdkCustomerServiceConfigVoList, "ld_dialog_contact_item_layout"));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.zzb.zzc.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    zzi.zza(context, zzi.zzc, zze.sdkCustomerServiceConfigVoList.get(i).url);
                    zzc.this.dismiss();
                }
            });
        }
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        setContentView(zza);
        setWidth(zzi.zza(context, 87.0f));
        setHeight(-2);
    }
}
