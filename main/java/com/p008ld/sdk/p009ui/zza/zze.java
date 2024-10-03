package com.p008ld.sdk.p009ui.zza;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.ClaimGiftBagInfo;
import com.p008ld.sdk.core.bean.GiftPackage;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.PictureHelper;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.RoundCornerImageView;
import java.util.List;

/* compiled from: GiftPackageAdapter.java */
/* loaded from: classes2.dex */
public class zze extends BaseAdapter {
    private List<GiftPackage> zza;
    private final Context zzb;
    private final boolean zzc;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public zze(Context context, boolean z) {
        this.zzb = context;
        this.zzc = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<GiftPackage> list = this.zza;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.zza.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        zza zzaVar;
        if (view == null) {
            view = zzt.zza(this.zzb, "ld_gift_bag_item_layout", (ViewGroup) null);
            zzaVar = new zza(view, this.zzb);
            view.setTag(zzaVar);
        } else {
            zzaVar = (zza) view.getTag();
        }
        List<GiftPackage> list = this.zza;
        if (list != null && i < list.size()) {
            try {
                final GiftPackage giftPackage = this.zza.get(i);
                PictureHelper.loadImage(giftPackage.getPackageSltUrl(), zzaVar.zza);
                zzaVar.zzb.setText(giftPackage.getGameName());
                zzaVar.zzc.setText(giftPackage.getPackageDesc());
                if (giftPackage.getClaimMark()) {
                    if (!this.zzc) {
                        zzaVar.zzd.setVisibility(0);
                        zzaVar.zzd.setText(zzt.zza(this.zzb, "ld_gift_bag_code") + giftPackage.getPackageCode());
                    } else {
                        zzaVar.zzd.setVisibility(8);
                    }
                    zzaVar.zze.setTag("copy");
                    zzaVar.zze.setText(zzt.zza(this.zzb, "ld_gift_bag_copy"));
                } else {
                    zzaVar.zzd.setVisibility(8);
                    zzaVar.zze.setTag("claim");
                    zzaVar.zze.setText(zzt.zza(this.zzb, "ld_gift_bag_claim"));
                }
                zzaVar.zze.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zza.zze.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        String obj = view2.getTag().toString();
                        if (obj.equals("copy")) {
                            zzi.zza(zze.this.zzb, (CharSequence) giftPackage.getPackageCode());
                        } else if (obj.equals("claim")) {
                            com.p008ld.sdk.core.zza.zza.zzf().zzb(giftPackage.getId(), new LDQueryCallback<ClaimGiftBagInfo>() { // from class: com.ld.sdk.ui.zza.zze.1.1
                                @Override // com.p008ld.sdk.internal.LDCallback2
                                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                                public void done(ClaimGiftBagInfo claimGiftBagInfo, LDException lDException) {
                                    if (claimGiftBagInfo != null) {
                                        LDUtil.toast(zzt.zza(zze.this.zzb, "ld_gift_bag_claim_success"));
                                        giftPackage.setClaimMark(true);
                                        giftPackage.setPackageCode(claimGiftBagInfo.getPackage().getPackageCode());
                                        zze.this.notifyDataSetChanged();
                                        return;
                                    }
                                    if (lDException != null) {
                                        LDLog.m573e("GiftPackageAdapter -> claimGiftPackage error ：" + lDException);
                                        LDUtil.toast(lDException.toString());
                                    }
                                }
                            });
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    /* compiled from: GiftPackageAdapter.java */
    /* loaded from: classes2.dex */
    public class zza {
        public RoundCornerImageView zza;
        public TextView zzb;
        public TextView zzc;
        public TextView zzd;
        public Button zze;

        public zza(View view, Context context) {
            RoundCornerImageView roundCornerImageView = (RoundCornerImageView) zzt.zza(context, "iv_game_icon", view);
            this.zza = roundCornerImageView;
            roundCornerImageView.setCornerRadius(15);
            this.zzb = (TextView) zzt.zza(context, "tv_gift_name", view);
            this.zzc = (TextView) zzt.zza(context, "tv_gift_desc", view);
            this.zzd = (TextView) zzt.zza(context, "tv_gift_code", view);
            this.zze = (Button) zzt.zza(context, "btn_gift_claim", view);
        }
    }

    public void zza(List<GiftPackage> list) {
        this.zza = list;
        notifyDataSetChanged();
    }
}
