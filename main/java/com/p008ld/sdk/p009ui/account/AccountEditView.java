package com.p008ld.sdk.p009ui.account;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.PictureHelper;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class AccountEditView extends BaseAccountView {
    private FrameLayout zze;
    private EditText zzf;
    private ImageView zzg;
    private Button zzh;

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public String getTitle() {
        return "ld_account_edit_title";
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public boolean zza() {
        return true;
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public void zzb() {
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public int getTitleBgColor() {
        return Color.parseColor("#F2F3F5");
    }

    public AccountEditView(Activity activity, View.OnClickListener onClickListener) {
        super(activity);
        zza(activity, onClickListener);
    }

    private void zza(final Activity activity, View.OnClickListener onClickListener) {
        View zza = zzt.zza((Context) activity, "ld_account_edit_layout", (ViewGroup) this);
        this.zze = (FrameLayout) zzt.zza(activity, "fl_avatar", zza);
        this.zzf = (EditText) zzt.zza(activity, "et_name", zza);
        this.zzh = (Button) zzt.zza(activity, "btn_save", zza);
        this.zzg = (ImageView) zzt.zza(activity, "iv_avatar", zza);
        this.zzh.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountEditView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AccountEditView.this.zza(activity);
            }
        });
        this.zze.setOnClickListener(new ViewOnClickListenerC18162(activity));
        zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ld.sdk.ui.account.AccountEditView$2 */
    /* loaded from: classes2.dex */
    public class ViewOnClickListenerC18162 implements View.OnClickListener {
        final /* synthetic */ Activity zza;

        ViewOnClickListenerC18162(Activity activity) {
            this.zza = activity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PictureHelper pictureHelper = PictureHelper.INSTANCE;
            PictureHelper.openPictureSelector(this.zza, new OnResultCallbackListener<LocalMedia>() { // from class: com.ld.sdk.ui.account.AccountEditView.2.1
                @Override // com.luck.picture.lib.interfaces.OnResultCallbackListener
                public void onResult(ArrayList<LocalMedia> arrayList) {
                    if (arrayList != null && arrayList.size() > 0) {
                        zza.zzf().zza(arrayList.get(0).getAvailablePath(), new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.account.AccountEditView.2.1.1
                            @Override // com.p008ld.sdk.internal.LDCallback2
                            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                            public void done(String str, LDException lDException) {
                                String zza = zzi.zza((Context) ViewOnClickListenerC18162.this.zza, "ld_update_success_text");
                                if (lDException == null) {
                                    PictureHelper.loadAvatar(str, AccountEditView.this.zzg);
                                } else {
                                    zza = lDException.toString();
                                    LDLog.m573e("uploadAvatar: error->" + lDException);
                                }
                                LDUtil.toast(zza);
                            }
                        });
                    } else {
                        LDLog.m573e("openPictureSelector: onResult error");
                    }
                }

                @Override // com.luck.picture.lib.interfaces.OnResultCallbackListener
                public void onCancel() {
                    LDLog.m573e("openPictureSelector: onCancel");
                }
            });
        }
    }

    private void zzc() {
        LDUser zza = zza.zzf().zza();
        if (zza != null) {
            this.zzf.setText(zza.getNickname());
            PictureHelper.loadAvatar(zza.getHeadPortraitUrl(), this.zzg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(Activity activity) {
        String obj = this.zzf.getText().toString();
        if (zzi.zza((CharSequence) obj)) {
            LDUtil.toast(zzi.zza((Context) activity, "ld_nickname_must_not_empty"));
            return;
        }
        LDUser zza = zza.zzf().zza();
        if (zza == null || !obj.equals(zza.getNickname())) {
            zza.zzf().zza((String) null, obj, new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.account.AccountEditView.3
                @Override // com.p008ld.sdk.internal.LDCallback2
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(String str, LDException lDException) {
                    LDUtil.toast(str);
                    if (lDException != null) {
                        LDLog.m573e("updateNickName: error->" + lDException);
                    }
                }
            });
        }
    }
}
