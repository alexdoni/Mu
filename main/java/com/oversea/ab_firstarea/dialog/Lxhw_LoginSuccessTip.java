package com.oversea.ab_firstarea.dialog;

import android.app.Activity;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.oversea.ab_firstarea.channel.ProjectType;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.xsdk.ab_firstbase.statisics.util.Util;
import com.xsdk.ab_firstbase.statisics.util.languagelib.LanguageType;

/* loaded from: classes.dex */
public class Lxhw_LoginSuccessTip {
    private Activity content;
    private CountDownTimer countDownTimer;
    private ImageView iv_tplayicon;
    private TextView uname_tv;
    private WindowManager wManager;
    private String TAG = "Lhwl_LoginSuccessTip";
    public View loginsuccessV = null;

    public Lxhw_LoginSuccessTip(Activity activity) {
        this.content = activity;
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(this.content).inflate(Util.getIdByName(this.content, "layout", "loginsuccesstip"), (ViewGroup) null);
        this.loginsuccessV = inflate;
        this.uname_tv = (TextView) inflate.findViewById(Util.getIdByName(this.content, "id", "uname_tv"));
        this.iv_tplayicon = (ImageView) this.loginsuccessV.findViewById(Util.getIdByName(this.content, "id", "iv_tplayicon"));
        if (Util.getSystemLang().equals(LanguageType.SERVER_VI) && Lxhw_AreaPlatform.getInstance().iShowViInfo) {
            this.iv_tplayicon.setImageResource(Util.getIdByName(this.content, "drawable", "tplayicon_vi"));
        }
        if (ProjectType.ZIAN.equals(ProjectType.pType)) {
            this.iv_tplayicon.setVisibility(8);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 49;
        this.content.addContentView(this.loginsuccessV, layoutParams);
        showTip(false);
        CountDownTimer countDownTimer = new CountDownTimer(3000L, 1000L) { // from class: com.oversea.ab_firstarea.dialog.Lxhw_LoginSuccessTip.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                Lxhw_LoginSuccessTip.this.removeTip();
            }
        };
        this.countDownTimer = countDownTimer;
        countDownTimer.start();
    }

    public void showTip(boolean z) {
        View view = this.loginsuccessV;
        if (view != null) {
            view.setVisibility(0);
            TextView textView = this.uname_tv;
            if (textView != null) {
                textView.setText(ManageBean.getInstance().getAccount());
            }
        }
    }

    public void removeTip() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        View view = this.loginsuccessV;
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.loginsuccessV);
            }
            this.loginsuccessV = null;
        }
    }
}
