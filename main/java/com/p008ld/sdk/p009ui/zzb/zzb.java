package com.p008ld.sdk.p009ui.zzb;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.p008ld.sdk.core.bean.LDGradeQueryInfo;
import com.p008ld.sdk.internal.LDCallback2;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CoinAreaPopWindow.java */
/* loaded from: classes2.dex */
public class zzb extends PopupWindow {
    public zzb(Activity activity, int i, final List<LDGradeQueryInfo> list, final LDCallback2<Integer, LDGradeQueryInfo> lDCallback2) {
        View zza = zzt.zza((Context) activity, "ld_coin_area_layout", (ViewGroup) null);
        ListView listView = (ListView) zzt.zza(activity, "select_listview", zza);
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(list.get(i2).getCountryName());
        }
        final com.p008ld.sdk.p009ui.zza.zzb zzbVar = new com.p008ld.sdk.p009ui.zza.zzb(activity, arrayList, "ld_coin_area_pop_item_layout");
        listView.setAdapter((ListAdapter) zzbVar);
        zzbVar.zza(i);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.zzb.zzb.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i3, long j) {
                zzbVar.zza(i3);
                lDCallback2.done(Integer.valueOf(i3), (LDGradeQueryInfo) list.get(i3));
                zzb.this.dismiss();
            }
        });
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        setContentView(zza);
        setWidth(zzi.zza(activity, 150.0f));
        setHeight(-2);
    }
}
