package com.mu.update;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.mu.update.ConfigInfo;

/* loaded from: classes2.dex */
public class UpdateWndMgr {
    private static UpdateWndMgr instance;
    public RelativeLayout FirstActivityWnd;
    public ImageView loading_rotate;
    Activity mActivity;
    Context mContext;
    public TextView mDownDescLabel;
    Handler mHandler;
    public TextView mProcessLabel;
    public ProgressBar mProgress;
    public Button mbtnUpdateDll;
    public ImageView muLogoIcon;
    public ImageView splash;
    public RelativeLayout updateTipWnd;
    public RelativeLayout updateloadingWnd;

    public static UpdateWndMgr getInstance() {
        if (instance == null) {
            instance = new UpdateWndMgr();
        }
        return instance;
    }

    public void InitWnd(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
        Activity activity = (Activity) context;
        this.mActivity = activity;
        this.muLogoIcon = (ImageView) activity.findViewById(context.getResources().getIdentifier("mulogo", "id", this.mContext.getPackageName()));
        this.splash = (ImageView) this.mActivity.findViewById(this.mContext.getResources().getIdentifier("splash", "id", this.mContext.getPackageName()));
        this.FirstActivityWnd = (RelativeLayout) this.mActivity.findViewById(this.mContext.getResources().getIdentifier("FirstActivityWnd", "id", this.mContext.getPackageName()));
        this.updateloadingWnd = (RelativeLayout) this.mActivity.findViewById(this.mContext.getResources().getIdentifier("updateloadingWnd", "id", this.mContext.getPackageName()));
        this.mProgress = (ProgressBar) this.mActivity.findViewById(this.mContext.getResources().getIdentifier("downProgressBar", "id", this.mContext.getPackageName()));
        this.mProcessLabel = (TextView) this.mActivity.findViewById(this.mContext.getResources().getIdentifier("downProgressLabel", "id", this.mContext.getPackageName()));
        this.mDownDescLabel = (TextView) this.mActivity.findViewById(this.mContext.getResources().getIdentifier("downDescLabel", "id", this.mContext.getPackageName()));
        this.loading_rotate = (ImageView) this.mActivity.findViewById(this.mContext.getResources().getIdentifier("loading_rotate", "id", this.mContext.getPackageName()));
        this.updateTipWnd = (RelativeLayout) this.mActivity.findViewById(this.mContext.getResources().getIdentifier("updateTipWnd", "id", this.mContext.getPackageName()));
        Button button = (Button) this.mActivity.findViewById(this.mContext.getResources().getIdentifier("btnupdate", "id", this.mContext.getPackageName()));
        this.mbtnUpdateDll = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.mu.update.UpdateWndMgr.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        InitViewState();
    }

    public void InitViewState() {
        this.FirstActivityWnd.setVisibility(4);
        this.updateTipWnd.setVisibility(4);
        this.updateloadingWnd.setVisibility(4);
        this.loading_rotate.setVisibility(4);
        this.splash.setVisibility(4);
    }

    public void InitViewState_GOUNITY() {
        this.muLogoIcon.setScaleX(0.0f);
        this.muLogoIcon.setScaleY(0.0f);
        this.FirstActivityWnd.setVisibility(4);
        this.updateTipWnd.setVisibility(4);
        this.updateloadingWnd.setVisibility(4);
        this.loading_rotate.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void StartSplash() {
        this.muLogoIcon.setVisibility(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(3000L);
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.mu.update.UpdateWndMgr.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.muLogoIcon.startAnimation(animationSet);
        new Handler().postDelayed(new Runnable() { // from class: com.mu.update.UpdateWndMgr.3
            @Override // java.lang.Runnable
            public void run() {
                HotUpdateMgr.getInstance().mActivityState = ConfigInfo.ActivityState.splashFinish;
                UpdateWndMgr.this.mHandler.sendEmptyMessage(12);
            }
        }, 5000L);
    }

    public void ClearSplash() {
        this.muLogoIcon.setVisibility(4);
        this.FirstActivityWnd.setVisibility(0);
        ShowRotateLoading();
    }

    public void ShowRotateLoading() {
        if (HotUpdateMgr.getInstance().mActivityState != ConfigInfo.ActivityState.splashFinish) {
            return;
        }
        this.loading_rotate.setVisibility(0);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setRepeatMode(1);
        rotateAnimation.setDuration(1000L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setFillAfter(true);
        this.loading_rotate.startAnimation(rotateAnimation);
    }

    public void UpdateDownProcess(float f) {
        float round = Math.round(f * 100.0f) / 100.0f;
        if (round <= 100.0f) {
            this.mProgress.setProgress((int) round);
            this.mProcessLabel.setText(String.format("%.2f", Float.valueOf(round)) + "%");
        }
    }

    public void ClearRotateLoading() {
        this.loading_rotate.clearAnimation();
        this.loading_rotate.setVisibility(4);
    }

    public void showDownWnd() {
        this.loading_rotate.setVisibility(4);
        this.updateTipWnd.setVisibility(4);
        this.updateloadingWnd.setVisibility(0);
        this.mProgress.setProgress(0);
        this.mProcessLabel.setText("0%");
        TextView textView = this.mDownDescLabel;
        Context context = this.mContext;
        textView.setText(context.getString(context.getResources().getIdentifier("soft_updating", TypedValues.Custom.S_STRING, this.mContext.getPackageName())));
    }

    public void PropError() {
        this.loading_rotate.setVisibility(4);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        Context context = this.mContext;
        ConfigInfo.ErrorMsg = context.getString(context.getResources().getIdentifier("error_iodata", TypedValues.Custom.S_STRING, this.mContext.getPackageName()));
        builder.setMessage(ConfigInfo.ErrorMsg);
        Context context2 = this.mContext;
        builder.setPositiveButton(context2.getString(context2.getResources().getIdentifier("soft_update_cancel", TypedValues.Custom.S_STRING, this.mContext.getPackageName())), new DialogInterface.OnClickListener() { // from class: com.mu.update.UpdateWndMgr.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                System.exit(0);
            }
        });
        AlertDialog create = builder.create();
        create.setCancelable(false);
        create.show();
    }

    public void PropVersionError() {
        try {
            this.loading_rotate.setVisibility(4);
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            Context context = this.mContext;
            ConfigInfo.ErrorMsg = context.getString(context.getResources().getIdentifier("error_iodata", TypedValues.Custom.S_STRING, this.mContext.getPackageName()));
            if (HotUpdateMgr.getInstance().mUpdateState == ConfigInfo.UpdateState.LoadVersionFailed) {
                Context context2 = this.mContext;
                builder.setMessage(context2.getString(context2.getResources().getIdentifier("versionInfo_network", TypedValues.Custom.S_STRING, this.mContext.getPackageName())));
            } else {
                Context context3 = this.mContext;
                builder.setMessage(context3.getString(context3.getResources().getIdentifier("version_network", TypedValues.Custom.S_STRING, this.mContext.getPackageName())));
            }
            Context context4 = this.mContext;
            builder.setPositiveButton(context4.getString(context4.getResources().getIdentifier("confirm", TypedValues.Custom.S_STRING, this.mContext.getPackageName())), new DialogInterface.OnClickListener() { // from class: com.mu.update.UpdateWndMgr.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    HotUpdateMgr.getInstance().ResetFaile();
                    if (HotUpdateMgr.getInstance().mUpdateState == ConfigInfo.UpdateState.LoadVersionFailed) {
                        if (HotUpdateMgr.getInstance().mVersionManager != null) {
                            HotUpdateMgr.getInstance().mVersionManager.LoadVersion();
                            return;
                        }
                        return;
                    }
                    System.exit(0);
                }
            });
            AlertDialog create = builder.create();
            create.setCancelable(false);
            create.show();
        } catch (Exception unused) {
        }
    }

    public void PropConfigFileNoExitError() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            Context context = this.mContext;
            builder.setMessage(context.getString(context.getResources().getIdentifier("config_file_no_exit", TypedValues.Custom.S_STRING, this.mContext.getPackageName())));
            Context context2 = this.mContext;
            builder.setPositiveButton(context2.getString(context2.getResources().getIdentifier("confirm", TypedValues.Custom.S_STRING, this.mContext.getPackageName())), new DialogInterface.OnClickListener() { // from class: com.mu.update.UpdateWndMgr.6
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    System.exit(0);
                }
            });
            AlertDialog create = builder.create();
            create.setCancelable(false);
            create.show();
        } catch (Exception unused) {
        }
    }
}
