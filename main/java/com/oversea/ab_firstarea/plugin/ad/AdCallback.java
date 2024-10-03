package com.oversea.ab_firstarea.plugin.ad;

import com.oversea.ab_firstarea.net.model.RewardItemBean;

/* loaded from: classes.dex */
public interface AdCallback {
    void onAdDismissed();

    void onAdFailedToLoad(String str);

    void onUserEarnedReward(RewardItemBean rewardItemBean);
}
