package com.p008ld.sdk.p009ui.account;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.ClaimGiftBagInfo;
import com.p008ld.sdk.core.bean.GiftBagInfo;
import com.p008ld.sdk.core.bean.GiftPackage;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.p009ui.zza.zze;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.PictureHelper;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.MyRadioGroup;
import com.p008ld.sdk.widget.RBOnClickListener;
import com.p008ld.sdk.widget.RoundCornerImageView;
import java.util.List;

/* loaded from: classes2.dex */
public class GiftPackageView extends BaseAccountView {
    private ImageView zze;
    private ListView zzf;
    private zze zzg;
    private zze zzh;
    private View zzi;
    private List<GiftPackage> zzj;
    private List<GiftPackage> zzk;
    private MyRadioGroup zzl;
    private FrameLayout zzm;
    private LinearLayout zzn;
    private boolean zzo;
    private RoundCornerImageView zzp;
    private TextView zzq;
    private TextView zzr;
    private TextView zzs;
    private TextView zzt;
    private TextView zzu;
    private TextView zzv;
    private Button zzw;
    private GiftPackage zzx;

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public String getTitle() {
        return "";
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public boolean zza() {
        return false;
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public void zzb() {
    }

    public GiftPackageView(Context context) {
        super(context);
        this.zzo = true;
        zza(context);
    }

    private void zza(Context context) {
        View zza = zzt.zza(context, "ld_gift_bag_layout", (ViewGroup) this);
        this.zzm = (FrameLayout) zzt.zza(context, "fl_list", zza);
        String[] strArr = {zzt.zza(context, "ld_gift_bag_game"), zzt.zza(context, "ld_gift_bag_my")};
        MyRadioGroup myRadioGroup = (MyRadioGroup) zzt.zza(context, "rg_gift", zza);
        this.zzl = myRadioGroup;
        myRadioGroup.setTitleData(strArr, new RBOnClickListener() { // from class: com.ld.sdk.ui.account.GiftPackageView.1
            @Override // com.p008ld.sdk.widget.RBOnClickListener
            public void onClick(String str) {
                GiftPackageView.this.zzo = str.equals("0");
                GiftPackageView.this.zzc();
            }
        });
        this.zzi = zzt.zza(context, "empty_data_layout", zza);
        this.zzf = (ListView) zzt.zza(context, "recyclerView", zza);
        this.zzg = new zze(context, true);
        this.zzh = new zze(context, false);
        this.zzf.setAdapter((ListAdapter) this.zzg);
        this.zzf.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.account.GiftPackageView.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                GiftPackageView.this.zzn.setVisibility(0);
                GiftPackageView.this.zzm.setVisibility(8);
                GiftPackageView.this.zzb(GiftPackageView.this.zzo ? (GiftPackage) GiftPackageView.this.zzj.get(i) : (GiftPackage) GiftPackageView.this.zzk.get(i));
            }
        });
        zzc();
        zza(context, zza);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzc() {
        zza.zzf().zzd(new LDQueryCallback<GiftBagInfo>() { // from class: com.ld.sdk.ui.account.GiftPackageView.3
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(GiftBagInfo giftBagInfo, LDException lDException) {
                if (giftBagInfo != null) {
                    GiftPackageView.this.zzj = giftBagInfo.getNotClaimed().getList();
                    GiftPackageView.this.zzk = giftBagInfo.getClaimed().getList();
                }
                GiftPackageView giftPackageView = GiftPackageView.this;
                giftPackageView.zza((List<GiftPackage>) giftPackageView.zzj);
                String zza = zzt.zza(GiftPackageView.this.zza, "ld_gift_bag_game");
                if (GiftPackageView.this.zzj != null && !GiftPackageView.this.zzj.isEmpty()) {
                    zza = zza + "(" + GiftPackageView.this.zzj.size() + ")";
                }
                String zza2 = zzt.zza(GiftPackageView.this.zza, "ld_gift_bag_my");
                if (GiftPackageView.this.zzk != null && !GiftPackageView.this.zzk.isEmpty()) {
                    zza2 = zza2 + "(" + GiftPackageView.this.zzk.size() + ")";
                }
                GiftPackageView.this.zzl.updateTitles(new String[]{zza, zza2});
                if (GiftPackageView.this.zzo) {
                    GiftPackageView.this.zzd();
                } else {
                    GiftPackageView.this.zze();
                }
                if (lDException != null) {
                    LDLog.m573e("GiftPackageView -> queryGiftPackages error ：" + lDException);
                    LDUtil.toast(lDException.toString());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(List<GiftPackage> list) {
        zzi.zza(this.zza, list, "gameGiftSet");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzd() {
        List<GiftPackage> list = this.zzj;
        if (list != null && !list.isEmpty()) {
            this.zzf.setAdapter((ListAdapter) this.zzg);
            this.zzg.zza(this.zzj);
            this.zzi.setVisibility(4);
            this.zzf.setVisibility(0);
            return;
        }
        this.zzf.setVisibility(4);
        this.zzi.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zze() {
        List<GiftPackage> list = this.zzk;
        if (list != null && !list.isEmpty()) {
            this.zzf.setAdapter((ListAdapter) this.zzh);
            this.zzh.zza(this.zzk);
            this.zzf.setVisibility(0);
            this.zzi.setVisibility(4);
            return;
        }
        this.zzf.setVisibility(4);
        this.zzi.setVisibility(0);
    }

    private void zza(Context context, View view) {
        this.zzn = (LinearLayout) zzt.zza(context, "ll_bag_detail", view);
        this.zze = (ImageView) zzt.zza(context, "iv_detail_back", view);
        this.zzp = (RoundCornerImageView) zzt.zza(context, "iv_game_icon", view);
        this.zzq = (TextView) zzt.zza(context, "tv_gift_name", view);
        this.zzr = (TextView) zzt.zza(context, "tv_gift_expireDate", view);
        this.zzs = (TextView) zzt.zza(context, "tv_gift_code", view);
        this.zzt = (TextView) zzt.zza(context, "tv_gift_code_1", view);
        this.zzu = (TextView) zzt.zza(context, "tv_gift_content_1", view);
        this.zzv = (TextView) zzt.zza(context, "tv_gift_claim_type_1", view);
        this.zzw = (Button) zzt.zza(context, "btn_gift_claim", view);
        this.zze.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.GiftPackageView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                GiftPackageView.this.zzf();
            }
        });
        this.zzw.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.GiftPackageView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                GiftPackageView giftPackageView = GiftPackageView.this;
                giftPackageView.zza(giftPackageView.zzx);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(final GiftPackage giftPackage) {
        if (giftPackage == null) {
            return;
        }
        if (this.zzw.getText().toString().equals(zzt.zza(this.zza, "ld_gift_bag_copy"))) {
            zzi.zza(this.zza, (CharSequence) giftPackage.getPackageCode());
        } else {
            zza.zzf().zzb(giftPackage.getId(), new LDQueryCallback<ClaimGiftBagInfo>() { // from class: com.ld.sdk.ui.account.GiftPackageView.6
                @Override // com.p008ld.sdk.internal.LDCallback2
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(ClaimGiftBagInfo claimGiftBagInfo, LDException lDException) {
                    if (claimGiftBagInfo == null) {
                        if (lDException != null) {
                            LDLog.m573e("GiftPackageView -> claimGiftPackage error ：" + lDException);
                            LDUtil.toast(lDException.toString());
                            return;
                        }
                        return;
                    }
                    LDUtil.toast(zzt.zza(GiftPackageView.this.zza, "ld_gift_bag_claim_success"));
                    String packageCode = claimGiftBagInfo.getPackage().getPackageCode();
                    giftPackage.setPackageCode(packageCode);
                    giftPackage.setClaimMark(true);
                    GiftPackageView.this.zzs.setVisibility(0);
                    GiftPackageView.this.zzt.setVisibility(0);
                    GiftPackageView.this.zzt.setText(packageCode);
                    GiftPackageView.this.zzw.setText(zzt.zza(GiftPackageView.this.zza, "ld_gift_bag_copy"));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb(GiftPackage giftPackage) {
        try {
            this.zzx = giftPackage;
            if (giftPackage.getClaimMark()) {
                this.zzs.setVisibility(0);
                this.zzt.setVisibility(0);
                this.zzt.setText(giftPackage.getPackageCode());
                this.zzw.setText(zzt.zza(this.zza, "ld_gift_bag_copy"));
            } else {
                this.zzs.setVisibility(8);
                this.zzt.setVisibility(8);
                this.zzw.setText(zzt.zza(this.zza, "ld_gift_bag_claim"));
            }
            PictureHelper.loadImage(giftPackage.getPackageSltUrl(), this.zzp);
            this.zzq.setText(giftPackage.getGameName());
            if (!zzi.zza((CharSequence) giftPackage.getExpireDate())) {
                this.zzr.setText(zzt.zza(this.zza, "ld_valid_to_text") + " " + giftPackage.getExpireDate());
            }
            this.zzu.setText(giftPackage.getPackageContent());
            this.zzv.setText(giftPackage.getPackageFunction());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzf() {
        GiftPackage giftPackage;
        LinearLayout linearLayout = this.zzn;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
            this.zzm.setVisibility(0);
            if (this.zzo && (giftPackage = this.zzx) != null && giftPackage.getClaimMark()) {
                zzc();
            }
        }
    }
}
