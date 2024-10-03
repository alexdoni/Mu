package com.luck.picture.lib.adapter.holder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.luck.picture.lib.C2140R;
import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.utils.DateUtils;
import com.luck.picture.lib.utils.StyleUtils;

/* loaded from: classes2.dex */
public class VideoViewHolder extends BaseRecyclerMediaHolder {
    private final TextView tvDuration;

    public VideoViewHolder(View view, SelectorConfig selectorConfig) {
        super(view, selectorConfig);
        TextView textView = (TextView) view.findViewById(C2140R.id.tv_duration);
        this.tvDuration = textView;
        SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        int adapterDurationDrawableLeft = selectMainStyle.getAdapterDurationDrawableLeft();
        if (StyleUtils.checkStyleValidity(adapterDurationDrawableLeft)) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(adapterDurationDrawableLeft, 0, 0, 0);
        }
        int adapterDurationTextSize = selectMainStyle.getAdapterDurationTextSize();
        if (StyleUtils.checkSizeValidity(adapterDurationTextSize)) {
            textView.setTextSize(adapterDurationTextSize);
        }
        int adapterDurationTextColor = selectMainStyle.getAdapterDurationTextColor();
        if (StyleUtils.checkStyleValidity(adapterDurationTextColor)) {
            textView.setTextColor(adapterDurationTextColor);
        }
        int adapterDurationBackgroundResources = selectMainStyle.getAdapterDurationBackgroundResources();
        if (StyleUtils.checkStyleValidity(adapterDurationBackgroundResources)) {
            textView.setBackgroundResource(adapterDurationBackgroundResources);
        }
        int[] adapterDurationGravity = selectMainStyle.getAdapterDurationGravity();
        if (StyleUtils.checkArrayValidity(adapterDurationGravity) && (textView.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            ((RelativeLayout.LayoutParams) textView.getLayoutParams()).removeRule(12);
            for (int i : adapterDurationGravity) {
                ((RelativeLayout.LayoutParams) this.tvDuration.getLayoutParams()).addRule(i);
            }
        }
    }

    @Override // com.luck.picture.lib.adapter.holder.BaseRecyclerMediaHolder
    public void bindData(LocalMedia localMedia, int i) {
        super.bindData(localMedia, i);
        this.tvDuration.setText(DateUtils.formatDurationTime(localMedia.getDuration()));
    }
}
