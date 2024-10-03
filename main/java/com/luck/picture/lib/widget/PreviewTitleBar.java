package com.luck.picture.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.luck.picture.lib.C2140R;
import com.luck.picture.lib.style.TitleBarStyle;
import com.luck.picture.lib.utils.StyleUtils;

/* loaded from: classes2.dex */
public class PreviewTitleBar extends TitleBar {
    public PreviewTitleBar(Context context) {
        super(context);
    }

    public PreviewTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PreviewTitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.luck.picture.lib.widget.TitleBar
    public void setTitleBarStyle() {
        super.setTitleBarStyle();
        TitleBarStyle titleBarStyle = this.config.selectorStyle.getTitleBarStyle();
        if (StyleUtils.checkStyleValidity(titleBarStyle.getPreviewTitleBackgroundColor())) {
            setBackgroundColor(titleBarStyle.getPreviewTitleBackgroundColor());
        } else if (StyleUtils.checkSizeValidity(titleBarStyle.getTitleBackgroundColor())) {
            setBackgroundColor(titleBarStyle.getTitleBackgroundColor());
        }
        if (StyleUtils.checkStyleValidity(titleBarStyle.getPreviewTitleLeftBackResource())) {
            this.ivLeftBack.setImageResource(titleBarStyle.getPreviewTitleLeftBackResource());
        }
        this.rlAlbumBg.setOnClickListener(null);
        this.viewAlbumClickArea.setOnClickListener(null);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.rlAlbumBg.getLayoutParams();
        layoutParams.removeRule(17);
        layoutParams.addRule(14);
        this.rlAlbumBg.setBackgroundResource(C2140R.drawable.ps_ic_trans_1px);
        this.tvCancel.setVisibility(8);
        this.ivArrow.setVisibility(8);
        this.viewAlbumClickArea.setVisibility(8);
    }
}
