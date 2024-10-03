package com.p008ld.sdk.p009ui.zzb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.download.zza;
import com.p008ld.sdk.download.zzc;
import com.p008ld.sdk.internal.LDDownloadCallback;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.PictureHelper;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.RoundCornerImageView;
import kotlin.Pair;

/* compiled from: GameUpdateDialog.java */
/* loaded from: classes2.dex */
public class zzf extends Dialog {
    private TextView zza;
    private TextView zzb;
    private RoundCornerImageView zzc;
    private RelativeLayout zzd;
    private TextView zze;
    private TextView zzf;
    private TextView zzg;
    private Button zzh;
    private Button zzi;
    private LinearLayout zzj;
    private TextView zzk;
    private TextView zzl;
    private TextView zzm;
    private RoundCornerImageView zzn;
    private SeekBar zzo;
    private Activity zzp;
    private ConfigBean zzq;
    private long zzr;

    public zzf(Activity activity, ConfigBean configBean) {
        super(activity, zzt.zzh(activity, "KKKDialog"));
        this.zzr = 0L;
        this.zzp = activity;
        this.zzq = configBean;
        zza();
        zzb();
    }

    private void zza() {
        View zza = zzt.zza((Context) this.zzp, "ld_app_update_layout", (ViewGroup) null);
        this.zza = (TextView) zzt.zza(this.zzp, "tv_game_name", zza);
        this.zzb = (TextView) zzt.zza(this.zzp, "tv_game_desc", zza);
        this.zzc = (RoundCornerImageView) zzt.zza(this.zzp, "iv_game_bg", zza);
        this.zzd = (RelativeLayout) zzt.zza(this.zzp, "rl_game_info", zza);
        this.zze = (TextView) zzt.zza(this.zzp, "tv_game_version", zza);
        this.zzf = (TextView) zzt.zza(this.zzp, "tv_game_size", zza);
        this.zzg = (TextView) zzt.zza(this.zzp, "tv_game_content_1", zza);
        this.zzh = (Button) zzt.zza(this.zzp, "btn_confirm", zza);
        this.zzi = (Button) zzt.zza(this.zzp, "btn_cancel", zza);
        this.zzj = (LinearLayout) zzt.zza(this.zzp, "ll_game_download", zza);
        this.zzo = (SeekBar) zzt.zza(this.zzp, "pb_download", zza);
        this.zzk = (TextView) zzt.zza(this.zzp, "tv_download_size", zza);
        this.zzl = (TextView) zzt.zza(this.zzp, "tv_download_progress", zza);
        this.zzm = (TextView) zzt.zza(this.zzp, "tv_downloading_size", zza);
        RoundCornerImageView roundCornerImageView = (RoundCornerImageView) zzt.zza(this.zzp, "iv_game_icon", zza);
        this.zzn = roundCornerImageView;
        roundCornerImageView.setCornerRadius(15);
        this.zzh.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzf.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzf zzfVar = zzf.this;
                zzfVar.zza(zzfVar.zzq);
            }
        });
        this.zzi.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzf.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (zzf.this.zzq.forcedUpdating) {
                    zzf.this.dismiss();
                    zzf.this.zzp.finish();
                } else {
                    zzf.this.dismiss();
                }
            }
        });
        this.zzo.setOnTouchListener(new View.OnTouchListener() { // from class: com.ld.sdk.ui.zzb.zzf.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.zzo.setOnSeekBarChangeListener(new zzc() { // from class: com.ld.sdk.ui.zzb.zzf.4
            @Override // com.p008ld.sdk.download.zzc, android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(final SeekBar seekBar, int i, boolean z) {
                zzf.this.zzo.post(new Runnable() { // from class: com.ld.sdk.ui.zzb.zzf.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            int progress = seekBar.getProgress();
                            zzf.this.zzl.setText(progress + "%");
                            if (progress <= 95) {
                                zzf.this.zzl.setX((seekBar.getWidth() / seekBar.getMax()) * progress);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            LDLog.logThrowable2Local(e);
                        }
                    }
                });
            }
        });
        setContentView(zza);
    }

    private void zzb() {
        try {
            boolean z = true;
            setCanceledOnTouchOutside(!this.zzq.forcedUpdating);
            if (this.zzq.forcedUpdating) {
                z = false;
            }
            setCancelable(z);
            this.zza.setText(this.zzq.gameName);
            this.zze.setText(this.zzq.newVersionName);
            this.zzf.setText(this.zzq.newPkgSize);
            this.zzg.setText(this.zzq.newVersionDesc);
            PictureHelper.loadImage(this.zzq.gameIconUrl, this.zzn);
            PictureHelper.loadImage(this.zzq.gameIconUrl, this.zzc);
            if (this.zzq.forcedUpdating) {
                this.zzi.setText(zzt.zza(this.zzp, "ld_update_exit"));
            } else {
                this.zzi.setText(zzt.zza(this.zzp, "ld_dialog_logout_cancel"));
            }
            if (zza.zza(this.zzp, this.zzq)) {
                this.zzh.setText(zzt.zza(this.zzp, "ld_update_install"));
            } else {
                this.zzh.setText(zzt.zza(this.zzp, "ld_update_update"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LDLog.logThrowable2Local(e);
        }
    }

    private void zzc() {
        this.zzd.setVisibility(8);
        this.zzj.setVisibility(0);
        this.zzb.setText(zzt.zza(this.zzp, "ld_update_downloading"));
        this.zzk.setText(String.format(zzt.zza(this.zzp, "ld_update_download_file_size"), this.zzq.newPkgSize));
        this.zzm.setText(zza("0M/" + this.zzq.newPkgSize));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String zza(String str) {
        return String.format(zzt.zza(this.zzp, "ld_update_downloading_size"), "(" + str + ")");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(final ConfigBean configBean) {
        zzc();
        zza.zza(this.zzp, configBean, new LDDownloadCallback() { // from class: com.ld.sdk.ui.zzb.zzf.5
            @Override // com.p008ld.sdk.internal.LDDownloadCallback
            public void onProgress(long j, long j2) {
                try {
                    zzf.this.zzr = j2;
                    Pair<String, Integer> zza = zza.zza(j, j2);
                    zzf.this.zzm.setText(zzf.this.zza(zza.getFirst()));
                    zzf.this.zzo.setProgress(zza.getSecond().intValue());
                } catch (Exception e) {
                    e.printStackTrace();
                    LDLog.logThrowable2Local(e);
                }
            }

            @Override // com.p008ld.sdk.internal.LDDownloadCallback
            public void onSuccess(boolean z, String str) {
                LDLog.m573e("GameUpdateDialog-> download success:" + z + " , " + str);
                try {
                    if (z) {
                        zzf.this.zzm.setText(zzf.this.zza(zzf.this.zzq.newPkgSize + RemoteSettings.FORWARD_SLASH_STRING + zzf.this.zzq.newPkgSize));
                    } else {
                        zzf.this.zzm.setText(zzf.this.zza(zza.zza(zzf.this.zzr, zzf.this.zzr).getFirst()));
                    }
                    zzf.this.zzo.setProgress(100);
                    zzi.zzc(zzf.this.zzp, str);
                    if (configBean.forcedUpdating) {
                        return;
                    }
                    zzf.this.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                    LDLog.logThrowable2Local(e);
                }
            }

            @Override // com.p008ld.sdk.internal.LDDownloadCallback
            public void onError(Throwable th) {
                LDUtil.toast(th.toString());
            }
        });
    }
}
