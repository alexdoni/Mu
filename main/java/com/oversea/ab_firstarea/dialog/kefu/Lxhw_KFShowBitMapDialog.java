package com.oversea.ab_firstarea.dialog.kefu;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_KFShowBitMapDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    ImageView bigview;
    Bundle bundle;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xkfshowbitmap_layout";
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
        Bitmap bitmap;
        this.bundle = getArguments();
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "bigview"));
        this.bigview = imageView;
        imageView.setOnClickListener(this);
        Bundle bundle = this.bundle;
        if (bundle != null && (bitmap = (Bitmap) bundle.getParcelable("bitmap")) != null) {
            this.bigview.setImageBitmap(bitmap);
        }
        setCancelable(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.bigview) {
            Lxhw_DialogManage.getInstance().removeFragment(this.mContext, "KFShowBitMapDialog");
        }
    }
}
