package com.xsdk.ab_firstbase.loading;

import android.R;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes3.dex */
public class ILoadingDialog extends DialogFragment implements DialogInterface.OnKeyListener {
    private CountDownTimer countDownTimer;

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Dialog dialog = getDialog();
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        dialog.requestWindowFeature(1);
        dialog.setCanceledOnTouchOutside(false);
        View inflate = layoutInflater.inflate(Util.getIdByName(getActivity(), "layout", "idialog_loading"), viewGroup);
        TextView textView = (TextView) inflate.findViewById(Util.getIdByName(getActivity(), "id", "tv_i_loading_dialog_hint"));
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(NotificationCompat.CATEGORY_MESSAGE)) {
            String string = arguments.getString(NotificationCompat.CATEGORY_MESSAGE);
            if (TextUtils.isEmpty(string)) {
                textView.setVisibility(8);
            } else {
                textView.setText(string);
            }
        }
        countDown();
        return inflate;
    }

    public void countDown() {
        try {
            CountDownTimer countDownTimer = new CountDownTimer(15000L, 1000L) { // from class: com.xsdk.ab_firstbase.loading.ILoadingDialog.1
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    if (ILoadingDialog.this.getActivity() == null || ILoadingDialog.this.getActivity().isFinishing()) {
                        return;
                    }
                    ILoadingDialog.this.getActivity().runOnUiThread(new Runnable() { // from class: com.xsdk.ab_firstbase.loading.ILoadingDialog.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                ILoadingDialog.this.countDownTimer.cancel();
                                ILoadingDialog.this.dismiss();
                            } catch (Throwable unused) {
                            }
                        }
                    });
                }
            };
            this.countDownTimer = countDownTimer;
            countDownTimer.start();
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        try {
            CountDownTimer countDownTimer = this.countDownTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
        } catch (Throwable th) {
            Log.i("ILoadingDialog", "onDestory e=" + th.toString());
        }
    }
}
