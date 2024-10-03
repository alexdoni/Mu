package com.luck.picture.lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.luck.picture.lib.C2140R;

/* loaded from: classes2.dex */
public class PictureCommonDialog extends Dialog implements View.OnClickListener {
    private OnDialogEventListener eventListener;

    /* loaded from: classes2.dex */
    public interface OnDialogEventListener {
        void onConfirm();
    }

    public static PictureCommonDialog showDialog(Context context, String str, String str2) {
        PictureCommonDialog pictureCommonDialog = new PictureCommonDialog(context, str, str2);
        pictureCommonDialog.show();
        return pictureCommonDialog;
    }

    public PictureCommonDialog(Context context, String str, String str2) {
        super(context, C2140R.style.Picture_Theme_Dialog);
        setContentView(C2140R.layout.ps_common_dialog);
        Button button = (Button) findViewById(C2140R.id.btn_cancel);
        Button button2 = (Button) findViewById(C2140R.id.btn_commit);
        TextView textView = (TextView) findViewById(C2140R.id.tvTitle);
        TextView textView2 = (TextView) findViewById(C2140R.id.tv_content);
        textView.setText(str);
        textView2.setText(str2);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        setDialogSize();
    }

    private void setDialogSize() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -2;
        attributes.height = -2;
        attributes.gravity = 17;
        getWindow().setWindowAnimations(C2140R.style.PictureThemeDialogWindowStyle);
        getWindow().setAttributes(attributes);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == C2140R.id.btn_cancel) {
            dismiss();
            return;
        }
        if (id == C2140R.id.btn_commit) {
            dismiss();
            OnDialogEventListener onDialogEventListener = this.eventListener;
            if (onDialogEventListener != null) {
                onDialogEventListener.onConfirm();
            }
        }
    }

    public void setOnDialogEventListener(OnDialogEventListener onDialogEventListener) {
        this.eventListener = onDialogEventListener;
    }
}
