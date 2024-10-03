package com.p008ld.sdk.p009ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.p008ld.sdk.core.UserAccountMgr;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.p009ui.account.AccountBindEmailView;
import com.p008ld.sdk.p009ui.account.AccountEditView;
import com.p008ld.sdk.p009ui.account.AccountManagerView;
import com.p008ld.sdk.p009ui.account.AccountOrderView;
import com.p008ld.sdk.p009ui.account.AccountUpdatePwdView;
import com.p008ld.sdk.p009ui.account.BaseAccountView;
import com.p008ld.sdk.p009ui.account.CouponsAccountView;
import com.p008ld.sdk.p009ui.account.GiftPackageView;
import com.p008ld.sdk.p009ui.account.RechargeCoinView;
import com.p008ld.sdk.p009ui.zza.zzd;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;
import com.tencent.p014av.sdk.AVError;
import java.util.Stack;

/* compiled from: UserCenterDialog.java */
/* loaded from: classes2.dex */
public class zza extends Dialog implements View.OnClickListener {
    private Stack<BaseAccountView> zza;
    private BaseAccountView zzb;
    private RelativeLayout zzc;
    private RechargeCoinView zzd;
    private CouponsAccountView zze;
    private AccountOrderView zzf;
    private AccountManagerView zzg;
    private zzd zzh;
    private GiftPackageView zzi;
    private GridView zzj;
    private boolean zzk;
    private boolean zzl;
    private Activity zzm;
    private View zzn;
    private boolean zzo;
    private View.OnClickListener zzp;

    public zza(Activity activity, int i) {
        super(activity, zzt.zzh(activity, "user_center_dialog"));
        this.zza = new Stack<>();
        this.zzl = false;
        this.zzo = true;
        this.zzp = new View.OnClickListener() { // from class: com.ld.sdk.ui.zza.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zza.this.zzc();
            }
        };
        this.zzm = activity;
        this.zzk = zzi.zza(activity);
        zza(activity, i, zzt.zza((Context) activity, "ld_user_center_dialog_layout", (ViewGroup) null));
    }

    private void zza(Activity activity, int i, View view) {
        zzb(activity, i, view);
        if (this.zzk) {
            zza(view);
        } else {
            ((ImageView) zzt.zza(activity, "iv_top_arrow", view)).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zza.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    UserAccountMgr.zza().zzg();
                }
            });
        }
    }

    private void zzb(Activity activity, int i, View view) {
        this.zzc = (RelativeLayout) zzt.zza(activity, "centerLayout", view);
        this.zzj = (GridView) zzt.zza(activity, "function_grid", view);
        this.zzn = zzt.zza(activity, "smegma_view", view);
        zza(view, activity);
        view.post(new Runnable() { // from class: com.ld.sdk.ui.zza.2
            @Override // java.lang.Runnable
            public void run() {
                zza.this.zzb();
                zza zzaVar = zza.this;
                zzaVar.zza((BaseAccountView) zzaVar.zzd());
            }
        });
    }

    private void zza(View view) {
        GridView gridView = (GridView) zzt.zza(this.zzm, "contact_us_gridview", view);
        final ConfigBean zze = com.p008ld.sdk.core.zza.zza.zzf().zze();
        if (zze == null || zze.sdkCustomerServiceConfigVoList == null) {
            return;
        }
        int size = zze.sdkCustomerServiceConfigVoList.size();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) gridView.getLayoutParams();
        layoutParams.width = zzt.zzb(this.zzm, "ld_dp_30") * size;
        gridView.setLayoutParams(layoutParams);
        gridView.setNumColumns(size);
        gridView.setAdapter((ListAdapter) new com.p008ld.sdk.p009ui.zza.zza(this.zzm.getApplicationContext(), zze.sdkCustomerServiceConfigVoList, "ld_contact_us_item_layout"));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.zza.3
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                zzi.zza(zza.this.zzm, zzi.zzc, zze.sdkCustomerServiceConfigVoList.get(i).url);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb() {
        zzd zzdVar = new zzd(this.zzm);
        this.zzh = zzdVar;
        this.zzj.setAdapter((ListAdapter) zzdVar);
        if (!this.zzk) {
            int count = this.zzh.getCount();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.zzj.getLayoutParams();
            layoutParams.width = zzt.zzb(this.zzm, "ld_dp_128") * count;
            this.zzj.setLayoutParams(layoutParams);
            this.zzj.setNumColumns(count);
        }
        this.zzj.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.zza.4
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                zza.this.zzh.zza(i);
                int i2 = C18767.zza[zza.this.zzh.zzb(i).ordinal()];
                if (i2 == 1) {
                    zza zzaVar = zza.this;
                    zzaVar.zza((BaseAccountView) zzaVar.zzd());
                    return;
                }
                if (i2 == 2) {
                    UserAccountMgr.zza().zze();
                    zza.this.zzb(1);
                    zza zzaVar2 = zza.this;
                    zzaVar2.zza(zzaVar2.zzg());
                    return;
                }
                if (i2 == 3) {
                    zza zzaVar3 = zza.this;
                    zzaVar3.zza((BaseAccountView) zzaVar3.zzf());
                } else {
                    if (i2 != 4) {
                        return;
                    }
                    zza zzaVar4 = zza.this;
                    zzaVar4.zza(zzaVar4.zze());
                }
            }
        });
        this.zzj.postDelayed(new Runnable() { // from class: com.ld.sdk.ui.zza.5
            @Override // java.lang.Runnable
            public void run() {
                zza.this.zzb(1);
            }
        }, 300L);
    }

    /* compiled from: UserCenterDialog.java */
    /* renamed from: com.ld.sdk.ui.zza$7 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class C18767 {
        static final /* synthetic */ int[] zza;

        static {
            int[] iArr = new int[zzd.zzb.values().length];
            zza = iArr;
            try {
                iArr[zzd.zzb.TYPE_LD_BIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                zza[zzd.zzb.TYPE_GIFT_BAG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                zza[zzd.zzb.TYPE_USER_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                zza[zzd.zzb.TYPE_COUPON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb(int i) {
        View zza;
        View childAt = this.zzj.getChildAt(i);
        if (childAt == null || (zza = zzt.zza(this.zzm, "function_hot", childAt)) == null) {
            return;
        }
        zza.setVisibility(UserAccountMgr.zza().zzd() ? 0 : 8);
    }

    public void zza() {
        Activity activity = this.zzm;
        if (activity != null && !activity.isFinishing()) {
            this.zzm.getWindow().clearFlags(2);
        }
        super.dismiss();
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        if (!this.zzl && this.zzb != null) {
            zzc();
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (this.zzo) {
            zza();
        }
    }

    private void zza(View view, Activity activity) {
        setContentView(view);
        Window window = getWindow();
        if (window != null) {
            int i = !this.zzk ? 80 : GravityCompat.START;
            window.setGravity(i);
            window.setFlags(16777216, 16777216);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setLayout(-2, -1);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            int i2 = activity.getResources().getDisplayMetrics().widthPixels;
            int i3 = activity.getResources().getDisplayMetrics().heightPixels;
            if (this.zzk) {
                attributes.width = (int) (i2 / 1.5d);
                attributes.height = -1;
                attributes.horizontalMargin = 0.0f;
            } else {
                attributes.height = (int) (i3 * 0.8d);
                attributes.width = -1;
            }
            attributes.gravity = i;
            window.setAttributes(attributes);
            window.setWindowAnimations(zzt.zzh(activity, !this.zzk ? "AnimBottom" : "AnimLeft"));
        }
        show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(BaseAccountView baseAccountView) {
        if (isShowing() && this.zzb != baseAccountView) {
            if (this.zzk && !(baseAccountView instanceof AccountUpdatePwdView)) {
                this.zza.clear();
            }
            this.zzc.removeAllViews();
            if (this.zza.size() > 0) {
                this.zza.peek().clearFocus();
            }
            baseAccountView.setBackListener(this.zzp);
            this.zza.push(baseAccountView);
            this.zzb = baseAccountView;
            baseAccountView.zzb();
            this.zzc.removeAllViews();
            this.zzc.addView(baseAccountView);
            baseAccountView.requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View zzc() {
        if (this.zzk) {
            BaseAccountView baseAccountView = this.zzb;
            if ((baseAccountView instanceof AccountUpdatePwdView) || (baseAccountView instanceof AccountBindEmailView) || (baseAccountView instanceof AccountEditView) || (baseAccountView instanceof RechargeCoinView) || ((baseAccountView instanceof AccountOrderView) && ((AccountOrderView) baseAccountView).getFromPage() == 2)) {
                zza((BaseAccountView) zzf());
                return this.zzb;
            }
        }
        if (this.zzk) {
            BaseAccountView baseAccountView2 = this.zzb;
            if ((baseAccountView2 instanceof AccountOrderView) && ((AccountOrderView) baseAccountView2).getFromPage() == 1) {
                zza((BaseAccountView) zzd());
                return this.zzb;
            }
        }
        if (this.zzk && (this.zzb instanceof GiftPackageView)) {
            zza(zzg());
            return this.zzb;
        }
        if (this.zza.size() > 1 && isShowing()) {
            this.zza.pop().clearFocus();
            BaseAccountView peek = this.zza.peek();
            int size = this.zza.size();
            for (int i = 0; i < size && (peek instanceof AccountUpdatePwdView); i++) {
                this.zza.remove(peek);
                peek = this.zza.peek();
            }
            this.zzb = peek;
            peek.zzb();
            this.zzc.removeAllViews();
            this.zzc.addView(peek);
            peek.requestFocus();
            return peek;
        }
        this.zzb = null;
        dismiss();
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        switch (intValue) {
            case 1123:
                dismiss();
                return;
            case 1124:
                this.zzn.setVisibility(0);
                return;
            case 1125:
                this.zzn.setVisibility(8);
                return;
            case 1126:
                zza(zzc(1));
                return;
            case 1127:
                zza((BaseAccountView) new AccountEditView(this.zzm, this));
                return;
            default:
                switch (intValue) {
                    case CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE /* 2000 */:
                        zza((BaseAccountView) new AccountBindEmailView(this.zzm, this));
                        return;
                    case 3000:
                        RechargeCoinView zzd = zzd();
                        zzd.zzc();
                        this.zza.clear();
                        zza((BaseAccountView) zzd);
                        return;
                    case 4000:
                        LoginActivity.startLoginActivityByPageId(this.zzm, 41);
                        return;
                    case 5000:
                        zza((BaseAccountView) new AccountUpdatePwdView(this.zzm, this));
                        return;
                    case 6000:
                    case 22000:
                        zza(zzc(2));
                        return;
                    case AVError.AV_ERR_IMSDK_TIMEOUT /* 7000 */:
                        zza(zze());
                        return;
                    case 11000:
                        zza((BaseAccountView) zzf());
                        return;
                    case 13000:
                        zzc();
                        return;
                    case 14000:
                        view.setEnabled(false);
                        this.zzl = true;
                        dismiss();
                        return;
                    case AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH /* 20000 */:
                        zza((BaseAccountView) zzd());
                        return;
                    default:
                        return;
                }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RechargeCoinView zzd() {
        if (this.zzd == null) {
            this.zzd = new RechargeCoinView(this.zzm, this);
        }
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BaseAccountView zze() {
        if (this.zze == null) {
            this.zze = new CouponsAccountView(this.zzm);
        }
        return this.zze;
    }

    private BaseAccountView zzc(int i) {
        AccountOrderView accountOrderView = new AccountOrderView(this.zzm, i, this);
        this.zzf = accountOrderView;
        return accountOrderView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AccountManagerView zzf() {
        if (this.zzg == null) {
            this.zzg = new AccountManagerView(this.zzm, this);
        }
        return this.zzg;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BaseAccountView zzg() {
        if (this.zzi == null) {
            this.zzi = new GiftPackageView(this.zzm);
        }
        return this.zzi;
    }

    public void zza(int i) {
        View view = new View(this.zzm);
        view.setTag(Integer.valueOf(i));
        onClick(view);
        zzd(i);
    }

    private void zzd(int i) {
        if (i == 7000) {
            this.zzh.zza(1);
        } else {
            if (i != 20000) {
                return;
            }
            this.zzh.zza(2);
        }
    }
}
