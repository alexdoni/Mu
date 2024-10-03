package com.oversea.ab_firstarea.dialog;

import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class Lxhw_GmtUTCDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    public static final int MSG_ONE = 1;
    private TextView gmttime;
    ImageView tw_sdk_back_iv;
    Timer timer = new Timer();
    private Handler handler = new Handler() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_GmtUTCDialog.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 1) {
                return;
            }
            Lxhw_GmtUTCDialog.this.showGmtTime();
        }
    };

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xgmtutc_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (displayMetrics.widthPixels >= displayMetrics.heightPixels) {
            getDialog().getWindow().setLayout((int) (displayMetrics.heightPixels * 0.9d), (int) (displayMetrics.heightPixels * 0.5d));
        } else {
            getDialog().getWindow().setLayout((int) (displayMetrics.widthPixels * 0.9d), (int) (displayMetrics.widthPixels * 0.5d));
        }
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.gmttime = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "gmttime"));
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_sdk_back_iv"));
        this.tw_sdk_back_iv = imageView;
        imageView.setOnClickListener(this);
        setCancelable(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.tw_sdk_back_iv) {
            delTimer();
            dismiss();
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        delTimer();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        initTimer();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStop() {
        super.onStop();
        delTimer();
    }

    public void initTimer() {
        delTimer();
        Timer timer = new Timer();
        this.timer = timer;
        timer.schedule(new TimerTask() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_GmtUTCDialog.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Message message = new Message();
                message.what = 1;
                Lxhw_GmtUTCDialog.this.handler.sendMessage(message);
            }
        }, 0L, 1000L);
    }

    public void delTimer() {
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            this.timer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showGmtTime() {
        try {
            final String gmt = Util.getGMT();
            this.mContext.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_GmtUTCDialog.3
                @Override // java.lang.Runnable
                public void run() {
                    if (Lxhw_GmtUTCDialog.this.gmttime != null) {
                        Lxhw_GmtUTCDialog.this.gmttime.setText(gmt);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
