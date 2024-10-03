package com.oversea.ab_firstarea.dialog.kefu;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_KFShowImageDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    Bitmap bitmap;
    Bundle bundle;
    ImageView show_iv;
    ImageView tw_sdk_back_iv;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xkfshow_img";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        getDialog().getWindow().setLayout(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        Bundle arguments = getArguments();
        this.bundle = arguments;
        if (arguments != null) {
            this.bitmap = (Bitmap) arguments.getParcelable("bitmap");
        }
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "show_iv"));
        this.show_iv = imageView;
        imageView.setOnClickListener(this);
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            this.show_iv.setImageBitmap(bitmap);
        }
        setCancelable(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.show_iv) {
            dismiss();
        }
    }
}
