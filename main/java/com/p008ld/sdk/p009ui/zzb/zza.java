package com.p008ld.sdk.p009ui.zzb;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.p008ld.sdk.core.bean.LDGradeQueryInfo;
import com.p008ld.sdk.internal.LDCallback2;
import com.p008ld.sdk.p009ui.zza.zzb;
import com.p008ld.sdk.util.zzt;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CoinAreaDialog.java */
/* loaded from: classes2.dex */
public class zza extends Dialog {
    public zza(Context context, int i, List<LDGradeQueryInfo> list, LDCallback2<Integer, LDGradeQueryInfo> lDCallback2) {
        super(context, zzt.zzh(context, "KKKDialog"));
        requestWindowFeature(1);
        zza(context, i, list, lDCallback2);
    }

    public void zza(Context context, int i, final List<LDGradeQueryInfo> list, final LDCallback2<Integer, LDGradeQueryInfo> lDCallback2) {
        View zza = zzt.zza(context, "ld_coin_area_layout", (ViewGroup) null);
        ListView listView = (ListView) zzt.zza(context, "select_listview", zza);
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(list.get(i2).getCountryName());
        }
        final zzb zzbVar = new zzb(context, arrayList, "ld_coin_area_dialog_item_layout");
        listView.setAdapter((ListAdapter) zzbVar);
        zzbVar.zza(i);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.zzb.zza.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i3, long j) {
                zzbVar.zza(i3);
                lDCallback2.done(Integer.valueOf(i3), (LDGradeQueryInfo) list.get(i3));
                zza.this.dismiss();
            }
        });
        setContentView(zza);
        zza();
    }

    private void zza() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(80);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.height = (int) (getContext().getResources().getDisplayMetrics().heightPixels * 0.6d);
            attributes.width = -1;
            window.setAttributes(attributes);
            window.setWindowAnimations(zzt.zzh(getContext(), "AnimBottom"));
        }
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        show();
    }
}
