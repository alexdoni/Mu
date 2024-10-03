package com.p008ld.sdk.p009ui.zza;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.PublicUserInfo;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.p009ui.zzb.zzl;
import com.p008ld.sdk.util.PictureHelper;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;
import java.util.List;

/* compiled from: SelectItemAdapter.java */
/* loaded from: classes2.dex */
public class zzj extends BaseAdapter {
    private final List<PublicUserInfo> zza;
    private final Context zzb;
    private final boolean zzc;
    private boolean zzd;
    private zza zze;

    /* compiled from: SelectItemAdapter.java */
    /* loaded from: classes2.dex */
    public interface zza {
        void zza(String str);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public zzj(Context context, List<PublicUserInfo> list) {
        this.zzb = context;
        this.zza = list;
        this.zzc = false;
        this.zzd = false;
    }

    public zzj(Context context, List<PublicUserInfo> list, boolean z) {
        this.zzd = true;
        this.zzb = context;
        this.zza = list;
        this.zzc = z;
    }

    public void zza(zza zzaVar) {
        this.zze = zzaVar;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<PublicUserInfo> list = this.zza;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.zza.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        zzb zzbVar;
        PublicUserInfo publicUserInfo = this.zza.get(i);
        if (view == null) {
            view = zzt.zza(this.zzb, "ld_login_select_item", (ViewGroup) null);
            zzbVar = new zzb();
            zzbVar.zza = (ImageView) zzt.zza(this.zzb, "iv_avatar", view);
            zzbVar.zzd = (ImageView) zzt.zza(this.zzb, "delete_account_img", view);
            zzbVar.zzb = (TextView) zzt.zza(this.zzb, "login_select_account_username", view);
            zzbVar.zzc = (TextView) zzt.zza(this.zzb, "flag_tv", view);
            view.setTag(zzbVar);
        } else {
            zzbVar = (zzb) view.getTag();
        }
        zzbVar.zzb.setText(publicUserInfo.username);
        if (i == 0) {
            zzbVar.zzb.setTextColor(Color.parseColor("#FFB31A"));
            zzbVar.zzb.getPaint().setFakeBoldText(true);
            zzbVar.zzc.setVisibility(0);
        } else {
            zzbVar.zzb.setTextColor(Color.parseColor("#999999"));
            zzbVar.zzb.getPaint().setFakeBoldText(false);
            zzbVar.zzc.setVisibility(8);
        }
        zzbVar.zza.setVisibility(this.zzd ? 8 : 0);
        PictureHelper.loadAvatar(publicUserInfo.headPortraitUrl, zzbVar.zza);
        zzbVar.zzd.setVisibility(this.zzc ? 4 : 0);
        zzbVar.zzd.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zza.zzj.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (zzj.this.zza == null || zzj.this.zza.isEmpty()) {
                    return;
                }
                final String str = ((PublicUserInfo) zzj.this.zza.get(i)).username;
                new zzl(zzj.this.zzb).zza(zzt.zza(zzj.this.zzb, "ld_dialog_common_notice")).zzb(zzi.zzb(str, String.format(zzt.zza(zzj.this.zzb, "ld_delete_account_login_record_text"), str))).zza(new LDCallback1<Boolean>() { // from class: com.ld.sdk.ui.zza.zzj.1.1
                    @Override // com.p008ld.sdk.internal.LDCallback1
                    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                    public void done(Boolean bool) {
                        if (bool.booleanValue()) {
                            com.p008ld.sdk.core.zza.zza.zzf().zza(zzj.this.zzb, ((PublicUserInfo) zzj.this.zza.get(i)).ldUserId);
                            if (zzj.this.zze != null) {
                                zzj.this.zze.zza(str);
                            }
                            zzj.this.zza.remove(i);
                            zzj.this.notifyDataSetChanged();
                        }
                    }
                }).show();
            }
        });
        return view;
    }

    /* compiled from: SelectItemAdapter.java */
    /* loaded from: classes2.dex */
    public final class zzb {
        ImageView zza;
        TextView zzb;
        TextView zzc;
        ImageView zzd;

        public zzb() {
        }
    }
}
