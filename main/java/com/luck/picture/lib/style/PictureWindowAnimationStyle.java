package com.luck.picture.lib.style;

import com.luck.picture.lib.C2140R;

/* loaded from: classes2.dex */
public class PictureWindowAnimationStyle {
    public int activityEnterAnimation;
    public int activityExitAnimation;
    public int activityPreviewEnterAnimation;
    public int activityPreviewExitAnimation;

    public static PictureWindowAnimationStyle ofDefaultWindowAnimationStyle() {
        return new PictureWindowAnimationStyle(C2140R.anim.ps_anim_enter, C2140R.anim.ps_anim_exit);
    }

    public PictureWindowAnimationStyle() {
    }

    public PictureWindowAnimationStyle(int i, int i2) {
        this.activityEnterAnimation = i;
        this.activityExitAnimation = i2;
        this.activityPreviewEnterAnimation = i;
        this.activityPreviewExitAnimation = i2;
    }

    public int getActivityEnterAnimation() {
        return this.activityEnterAnimation;
    }

    public void setActivityEnterAnimation(int i) {
        this.activityEnterAnimation = i;
    }

    public int getActivityExitAnimation() {
        return this.activityExitAnimation;
    }

    public void setActivityExitAnimation(int i) {
        this.activityExitAnimation = i;
    }

    public int getActivityPreviewEnterAnimation() {
        return this.activityPreviewEnterAnimation;
    }

    public void setActivityPreviewEnterAnimation(int i) {
        this.activityPreviewEnterAnimation = i;
    }

    public int getActivityPreviewExitAnimation() {
        return this.activityPreviewExitAnimation;
    }

    public void setActivityPreviewExitAnimation(int i) {
        this.activityPreviewExitAnimation = i;
    }
}
