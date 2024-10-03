package com.oversea.ab_firstplatform.init;

import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;

/* loaded from: classes2.dex */
public interface Lxhw_SDKListener {
    void onADResult(boolean z, String str);

    void onAuthResult(int i);

    void onBackServiceResult(Lxhw_XUserInfo lxhw_XUserInfo);

    void onDianzanResult(boolean z);

    void onDownHeadResult(boolean z, String str);

    void onGiftGetResult(int i, String str);

    void onPingFenResult(boolean z);

    void onResult(int i, String str);

    void onShareResult(boolean z);

    void onUploadHeadResult(boolean z, String str);
}
