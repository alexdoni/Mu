package com.tencent.sharpgme.jni;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import com.luck.picture.lib.config.PictureMimeType;
import java.util.StringTokenizer;

/* loaded from: classes3.dex */
public class VivoKTVHelper {
    private static final String KEY_EXT_SPKR = "vivo_ktv_ext_speaker";
    private static final String KEY_KTV_MODE = "vivo_ktv_mode";
    private static final String KEY_MIC_SRC = "vivo_ktv_rec_source";
    private static final String KEY_MIC_TYPE = "vivo_ktv_mic_type";
    private static final String KEY_PLAY_SRC = "vivo_ktv_play_source";
    private static final String KEY_PRESET = "vivo_ktv_preset_effect";
    private static final String KEY_VOL_MIC = "vivo_ktv_volume_mic";
    private static final String TAG = "VivoKTVHelper";
    private static VivoKTVHelper mVivoKTVHelper;
    private AudioManager mAudioManager;
    private Context mContext;
    private final Object mParamLock = new Object();

    public VivoKTVHelper(Context context) {
        this.mContext = context;
        this.mAudioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
    }

    public static VivoKTVHelper getInstance(Context context) {
        if (mVivoKTVHelper == null) {
            mVivoKTVHelper = new VivoKTVHelper(context);
        }
        return mVivoKTVHelper;
    }

    public boolean isDeviceSupportKaraoke() {
        int parseInt;
        if (Build.MODEL.trim().contains("vivo")) {
            StringTokenizer stringTokenizer = new StringTokenizer(this.mAudioManager.getParameters(KEY_MIC_TYPE), "=");
            if (stringTokenizer.countTokens() == 2 && stringTokenizer.nextToken().equals(KEY_MIC_TYPE) && ((parseInt = Integer.parseInt(stringTokenizer.nextToken())) == 1 || parseInt == 0)) {
                return true;
            }
        }
        return false;
    }

    public void openKTVDevice() {
        this.mAudioManager.setParameters("vivo_ktv_mode=1");
        isDeviceSupportKaraoke();
    }

    public void closeKTVDevice() {
        this.mAudioManager.setParameters("vivo_ktv_mode=0");
    }

    public void setMicVolParam(int i) {
        synchronized (this.mParamLock) {
            if (this.mAudioManager != null) {
                this.mAudioManager.setParameters("vivo_ktv_volume_mic=" + i);
            }
        }
    }

    public void setVoiceOutParam(int i) {
        synchronized (this.mParamLock) {
            AudioManager audioManager = this.mAudioManager;
            if (audioManager != null) {
                audioManager.setParameters("vivo_ktv_rec_source=" + i);
            }
        }
    }

    public void setPreModeParam(int i) {
        synchronized (this.mParamLock) {
            AudioManager audioManager = this.mAudioManager;
            if (audioManager != null) {
                audioManager.setParameters("vivo_ktv_preset_effect=" + i);
            }
        }
    }

    public void setPlayFeedbackParam(int i) {
        synchronized (this.mParamLock) {
            AudioManager audioManager = this.mAudioManager;
            if (audioManager != null) {
                audioManager.setParameters("vivo_ktv_play_source=" + i);
            }
        }
    }

    public void setExtSpeakerParam(int i) {
        synchronized (this.mParamLock) {
            if (this.mAudioManager != null) {
                this.mAudioManager.setParameters("vivo_ktv_ext_speaker=" + i);
            }
        }
    }

    public int getExtSpeakerParam() {
        return getKTVParam(KEY_EXT_SPKR);
    }

    public int getPlayFeedbackParam() {
        return getKTVParam(KEY_PLAY_SRC);
    }

    public int getPreModeParam() {
        return getKTVParam(KEY_PRESET);
    }

    public int getMicTypeParam() {
        return getKTVParam(KEY_MIC_TYPE);
    }

    public int getVoiceOutParam() {
        return getKTVParam(KEY_MIC_SRC);
    }

    public int getMicVolParam() {
        return getKTVParam(KEY_VOL_MIC);
    }

    private int getKTVParam(String str) {
        if (!Build.MODEL.trim().contains("vivo")) {
            return 0;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(this.mAudioManager.getParameters(str), "=");
        if (stringTokenizer.countTokens() == 2 && str.equals(stringTokenizer.nextToken())) {
            return Integer.parseInt(stringTokenizer.nextToken().trim());
        }
        return 0;
    }
}
