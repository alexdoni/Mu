package com.oversea.ab_firstarea.dialog;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.p017xx.commom.glide.Glide;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_CountDownDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    private static final int ONE_DAY = 86400000;
    private static final int ONE_HOUR = 3600000;
    private static final int ONE_MIN = 60000;
    private static final int ONE_SEC = 1000;
    private Button btnConfirm;
    private Bundle bundle;
    private CountDownTimer countDownTimer;
    private long countdownTime;
    private ImageView ivLogo;
    private TextView tvHourOne;
    private TextView tvHourTwo;
    private TextView tvMinOne;
    private TextView tvMinTwo;
    private TextView tvSecOne;
    private TextView tvSecTwo;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xcount_down_layout";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        int i;
        int i2;
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        if (displayMetrics.widthPixels >= displayMetrics.heightPixels) {
            i = (int) (displayMetrics.heightPixels * 0.95d);
            i2 = (int) (displayMetrics.heightPixels * 0.9d);
        } else {
            i = (int) (displayMetrics.widthPixels * 0.9d);
            i2 = (int) (displayMetrics.widthPixels * 0.85d);
        }
        getDialog().getWindow().setLayout(i, i2);
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        Bundle arguments = getArguments();
        this.bundle = arguments;
        if (arguments != null) {
            this.countdownTime = arguments.getLong("countdown") * 1000;
        }
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(this.mContext, ComConfig.CUSTOM_SDK_COUNTDOWN_SHOW);
        this.tvHourOne = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tv_hour_one"));
        this.tvHourTwo = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tv_hour_two"));
        this.tvMinOne = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tv_min_one"));
        this.tvMinTwo = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tv_min_two"));
        this.tvSecOne = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tv_sec_one"));
        this.tvSecTwo = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tv_sec_two"));
        Button button = (Button) view.findViewById(Util.getIdByName(this.mContext, "id", "btn_confirm"));
        this.btnConfirm = button;
        button.setOnClickListener(this);
        this.btnConfirm.setClickable(false);
        setCancelable(false);
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(this.countdownTime, 1000L) { // from class: com.oversea.ab_firstarea.dialog.Lxhw_CountDownDialog.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                Lxhw_CountDownDialog.this.setSecond(j);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                Lxhw_CountDownDialog.this.setBtnStatus();
            }
        };
        this.countDownTimer = countDownTimer2;
        countDownTimer2.start();
        this.ivLogo = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "iv_top_logo"));
        if (InitBean.getInstance().getGame_info() != null && !TextUtils.isEmpty(InitBean.getInstance().getGame_info().getAndroid_game_icon())) {
            try {
                Glide.with(this.mContext).load(InitBean.getInstance().getGame_info().getAndroid_game_icon()).error(Util.getIdByName(this.mContext, "drawable", "drhw_sdk_member_tip")).into(this.ivLogo);
            } catch (Exception unused) {
            }
        } else {
            this.ivLogo.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_sdk_member_tip"));
        }
        view.findViewById(Util.getIdByName(this.mContext, "id", "tw_close_dia_iv")).setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_CountDownDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Lxhw_AreaPlatform.getInstance().callbackSwitchAccount();
                Lxhw_CountDownDialog.this.dismissAllowingStateLoss();
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.btnConfirm) {
            dismissAllowingStateLoss();
            AreaSdk.getInstance().loginSuccess();
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBtnStatus() {
        Button button = this.btnConfirm;
        if (button != null) {
            button.setClickable(true);
            this.btnConfirm.setBackgroundResource(Util.getIdByName(this.mContext, "drawable", "drhw_shape_allround_countdown_blue"));
            this.btnConfirm.setTextColor(this.mContext.getResources().getColor(Util.getIdByName(this.mContext, TypedValues.Custom.S_COLOR, "tw_white")));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSecond(long j) {
        long j2 = j / 86400000;
        long j3 = j / 3600000;
        long j4 = 24 * j2;
        long j5 = j3 - j4;
        long j6 = j4 * 60;
        long j7 = j5 * 60;
        long j8 = ((j / 60000) - j6) - j7;
        long j9 = (((j / 1000) - (j6 * 60)) - (j7 * 60)) - (60 * j8);
        long j10 = (j2 % 100) / 10;
        long j11 = j2 % 10;
        int i = (int) ((j3 % 100) / 10);
        int i2 = (int) (j3 % 10);
        setData(this.tvHourOne, i);
        setData(this.tvHourTwo, i2);
        setData(this.tvMinOne, (int) ((j8 % 100) / 10));
        setData(this.tvMinTwo, (int) (j8 % 10));
        setData(this.tvSecOne, (int) ((j9 % 100) / 10));
        setData(this.tvSecTwo, (int) (j9 % 10));
    }

    private void setData(TextView textView, int i) {
        if (textView != null) {
            textView.setText(i + "");
        }
    }
}
