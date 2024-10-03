package com.xsdk.ab_firstbase.loading;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class WeakDialog extends Dialog {
    public WeakDialog(Context context) {
        super(context);
    }

    public WeakDialog(Context context, int i) {
        super(context, i);
    }

    protected WeakDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    @Override // android.app.Dialog
    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        super.setOnCancelListener(proxy(onCancelListener));
    }

    @Override // android.app.Dialog
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        super.setOnDismissListener(proxy(onDismissListener));
    }

    @Override // android.app.Dialog
    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        super.setOnShowListener(proxy(onShowListener));
    }

    public WeakOnCancelListener proxy(DialogInterface.OnCancelListener onCancelListener) {
        return new WeakOnCancelListener(onCancelListener);
    }

    public WeakOnDismissListener proxy(DialogInterface.OnDismissListener onDismissListener) {
        return new WeakOnDismissListener(onDismissListener);
    }

    public WeakOnShowListener proxy(DialogInterface.OnShowListener onShowListener) {
        return new WeakOnShowListener(onShowListener);
    }

    /* loaded from: classes3.dex */
    public static class WeakOnCancelListener implements DialogInterface.OnCancelListener {
        private final WeakReference<DialogInterface.OnCancelListener> mRef;

        public WeakOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.mRef = new WeakReference<>(onCancelListener);
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            DialogInterface.OnCancelListener onCancelListener = this.mRef.get();
            if (onCancelListener != null) {
                onCancelListener.onCancel(dialogInterface);
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class WeakOnDismissListener implements DialogInterface.OnDismissListener {
        private final WeakReference<DialogInterface.OnDismissListener> mRef;

        public WeakOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.mRef = new WeakReference<>(onDismissListener);
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            DialogInterface.OnDismissListener onDismissListener = this.mRef.get();
            if (onDismissListener != null) {
                onDismissListener.onDismiss(dialogInterface);
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class WeakOnShowListener implements DialogInterface.OnShowListener {
        private final WeakReference<DialogInterface.OnShowListener> mRef;

        public WeakOnShowListener(DialogInterface.OnShowListener onShowListener) {
            this.mRef = new WeakReference<>(onShowListener);
        }

        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialogInterface) {
            DialogInterface.OnShowListener onShowListener = this.mRef.get();
            if (onShowListener != null) {
                onShowListener.onShow(dialogInterface);
            }
        }
    }
}
