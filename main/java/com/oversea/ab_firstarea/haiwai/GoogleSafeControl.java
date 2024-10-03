package com.oversea.ab_firstarea.haiwai;

import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;

/* loaded from: classes.dex */
public class GoogleSafeControl {
    private static GoogleSafeControl instance;

    public static GoogleSafeControl getInstance() {
        if (instance == null) {
            instance = new GoogleSafeControl();
        }
        return instance;
    }

    public void checkSafe(LoginBean loginBean) {
        if (Lxhw_AreaPlatform.getInstance().google_play_integrity_switch == 1) {
            GooglePlayIntegrity.getInstance().requestIntegrityToken(loginBean);
        }
        if (Lxhw_AreaPlatform.getInstance().google_play_recaptcha_switch == 1) {
            GoogleRecaptcha.getInstance().dealRecaptcha(Lxhw_XSDK.getInstance().getContext());
        }
    }
}
