package com.oversea.ab_firstplatform.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/* loaded from: classes2.dex */
public class BaseCustomDialog extends Dialog {
    protected static final float ALPHA = 1.0f;

    /* loaded from: classes2.dex */
    public interface ButtonListener {
        void OnPositiveButtonClickListener(String str);
    }

    /* loaded from: classes2.dex */
    public interface OnDialogItemClickListener {
        void onDiaogItemClick(int i);
    }

    public BaseCustomDialog(Context context, int i) {
        super(context, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }
}
