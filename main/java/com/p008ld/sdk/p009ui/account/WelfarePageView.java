package com.p008ld.sdk.p009ui.account;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ListView;
import com.p008ld.sdk.util.zzt;

/* loaded from: classes2.dex */
public class WelfarePageView extends BaseAccountView {
    private ListView zze;

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public String getTitle() {
        return "ld_welfare_text";
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public boolean zza() {
        return false;
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public void zzb() {
    }

    public WelfarePageView(Context context) {
        super(context);
        zza(context);
    }

    private void zza(Context context) {
        this.zze = (ListView) zzt.zza(context, "welfare_list", zzt.zza(context, "ld_welfare_layout", (ViewGroup) this));
    }
}
