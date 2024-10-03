package com.luck.picture.lib.adapter.holder;

import android.view.View;
import android.widget.TextView;
import com.luck.picture.lib.C2140R;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.utils.StyleUtils;

/* loaded from: classes2.dex */
public class CameraViewHolder extends BaseRecyclerMediaHolder {
    public CameraViewHolder(View view) {
        super(view);
        TextView textView = (TextView) view.findViewById(C2140R.id.tvCamera);
        this.selectorConfig = SelectorProviders.getInstance().getSelectorConfig();
        SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        int adapterCameraBackgroundColor = selectMainStyle.getAdapterCameraBackgroundColor();
        if (StyleUtils.checkStyleValidity(adapterCameraBackgroundColor)) {
            textView.setBackgroundColor(adapterCameraBackgroundColor);
        }
        int adapterCameraDrawableTop = selectMainStyle.getAdapterCameraDrawableTop();
        if (StyleUtils.checkStyleValidity(adapterCameraDrawableTop)) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, adapterCameraDrawableTop, 0, 0);
        }
        String string = StyleUtils.checkStyleValidity(selectMainStyle.getAdapterCameraTextResId()) ? view.getContext().getString(selectMainStyle.getAdapterCameraTextResId()) : selectMainStyle.getAdapterCameraText();
        if (StyleUtils.checkTextValidity(string)) {
            textView.setText(string);
        } else if (this.selectorConfig.chooseMode == SelectMimeType.ofAudio()) {
            textView.setText(view.getContext().getString(C2140R.string.ps_tape));
        }
        int adapterCameraTextSize = selectMainStyle.getAdapterCameraTextSize();
        if (StyleUtils.checkSizeValidity(adapterCameraTextSize)) {
            textView.setTextSize(adapterCameraTextSize);
        }
        int adapterCameraTextColor = selectMainStyle.getAdapterCameraTextColor();
        if (StyleUtils.checkStyleValidity(adapterCameraTextColor)) {
            textView.setTextColor(adapterCameraTextColor);
        }
    }
}
