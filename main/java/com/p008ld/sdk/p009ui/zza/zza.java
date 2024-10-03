package com.p008ld.sdk.p009ui.zza;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.util.PictureHelper;
import com.p008ld.sdk.util.zzt;
import java.util.List;

/* compiled from: ContactUsAdapter.java */
/* loaded from: classes2.dex */
public class zza extends BaseAdapter {
    private final List<ConfigBean.ContactUsBean> zza;
    private final Context zzb;
    private final String zzc;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public zza(Context context, List<ConfigBean.ContactUsBean> list, String str) {
        this.zzb = context;
        this.zza = list;
        this.zzc = str;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.zza.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.zza.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        C3259zza c3259zza;
        if (view == null) {
            view = zzt.zza(this.zzb, this.zzc, (ViewGroup) null);
            c3259zza = new C3259zza();
            c3259zza.zza = (ImageView) zzt.zza(this.zzb, "us_icon_img", view);
            view.setTag(c3259zza);
        } else {
            c3259zza = (C3259zza) view.getTag();
        }
        PictureHelper.loadImage(this.zza.get(i).iconUrl, c3259zza.zza);
        return view;
    }

    /* compiled from: ContactUsAdapter.java */
    /* renamed from: com.ld.sdk.ui.zza.zza$zza, reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    class C3259zza {
        ImageView zza;

        C3259zza() {
        }
    }
}
