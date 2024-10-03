package com.tencent.TMG;

/* loaded from: classes3.dex */
public abstract class ITMGAudioEffectCtrl {

    /* loaded from: classes3.dex */
    public static class ITMG_KaraokeType {
        public static final int ITME_KARAOKE_TYPE_VIGOROUS = 7;
        public static final int ITMG_KARAOKE_TYPE_DANCE = 4;
        public static final int ITMG_KARAOKE_TYPE_HEAVEN = 5;
        public static final int ITMG_KARAOKE_TYPE_LIMPID = 8;
        public static final int ITMG_KARAOKE_TYPE_ORIGINAL_SOUND = 0;
        public static final int ITMG_KARAOKE_TYPE_POP = 1;
        public static final int ITMG_KARAOKE_TYPE_RB = 3;
        public static final int ITMG_KARAOKE_TYPE_ROCK = 2;
        public static final int ITMG_KARAOKE_TYPE_TTS = 6;
    }

    /* loaded from: classes3.dex */
    public static class ITMG_VOICE_TYPE_EQUALIZER {
        public float EQUALIZER_128HZ;
        public float EQUALIZER_16KHZ;
        public float EQUALIZER_1KHZ;
        public float EQUALIZER_250HZ;
        public float EQUALIZER_2KHZ;
        public float EQUALIZER_32HZ;
        public float EQUALIZER_4KHZ;
        public float EQUALIZER_500HZ;
        public float EQUALIZER_64HZ;
        public float EQUALIZER_8KHZ;
        public float EQUALIZER_MASTER_GAIN;
    }

    /* loaded from: classes3.dex */
    public static class ITMG_VOICE_TYPE_REVERB {
        public float HARMONIC_BASS_CONTROL;
        public float HARMONIC_GAIN;
        public float HARMONIC_START_FREQUENCY;
        public float REVERB_DEPTH;
        public float REVERB_ECHO_DEPTH;
        public float REVERB_GAIN;
        public float REVERB_SIZE;
    }

    /* loaded from: classes3.dex */
    public static class ITMG_VoiceType {
        public static final int ITMG_VOICE_TYPE_CAGED_ANIMAL = 8;
        public static final int ITMG_VOICE_TYPE_DEAD_FATBOY = 4;
        public static final int ITMG_VOICE_TYPE_DIALECT = 6;
        public static final int ITMG_VOICE_TYPE_HEAVY_MACHINE = 9;
        public static final int ITMG_VOICE_TYPE_HEAVY_MENTAL = 5;
        public static final int ITMG_VOICE_TYPE_HUANG = 12;
        public static final int ITMG_VOICE_TYPE_INFLUENZA = 7;
        public static final int ITMG_VOICE_TYPE_INTANGIBLE = 3;
        public static final int ITMG_VOICE_TYPE_KINDER_GARTEN = 11;
        public static final int ITMG_VOICE_TYPE_LOLITA = 1;
        public static final int ITMG_VOICE_TYPE_ORIGINAL_SOUND = 0;
        public static final int ITMG_VOICE_TYPE_STRONG_CURRENT = 10;
        public static final int ITMG_VOICE_TYPE_UNCLE = 2;
    }

    public abstract int EnableRecordAccompany(boolean z);

    public abstract int EnableRecordLocalMic(boolean z);

    public abstract int EnableRecordRemote(boolean z);

    public abstract long GetAccompanyFileCurrentPlayedTimeByMs();

    public abstract long GetAccompanyFileCurrentPlayedTimeByMs(String str);

    public abstract long GetAccompanyFileTotalTimeByMs();

    public abstract long GetAccompanyFileTotalTimeByMs(String str);

    public abstract int GetAccompanyVolume();

    public abstract int GetEffectsVolume();

    public abstract int GetHardWareDelay();

    public abstract boolean IsAccompanyPlayEnd();

    public abstract int PauseAccompany();

    public abstract int PauseAllEffects();

    public abstract int PauseEffect(int i);

    public abstract int PauseRecord();

    public abstract int PlayEffect(int i, String str, boolean z);

    public abstract int ResumeAccompany();

    public abstract int ResumeAllEffects();

    public abstract int ResumeEffect(int i);

    public abstract int ResumeRecord();

    public abstract int SetAccompanyFileCurrentPlayedTimeByMs(long j);

    public abstract int SetAccompanyKey(int i);

    public abstract int SetAccompanyVolume(int i);

    public abstract int SetEffectsVolume(int i);

    public abstract int SetHardWareDelay(int i);

    public abstract int SetKaraokeType(int i);

    public abstract int SetKaraokeType(ITMG_VOICE_TYPE_EQUALIZER itmg_voice_type_equalizer, ITMG_VOICE_TYPE_REVERB itmg_voice_type_reverb);

    public abstract int SetVoiceType(int i);

    public abstract int StartAccompany(String str, boolean z, int i);

    public abstract int StartAccompanyDownloading(String str, boolean z, int i, int i2);

    public abstract int StartPreviewDelayTest();

    public abstract int StartRecord(String str, int i, int i2, boolean z, boolean z2, boolean z3);

    public abstract int StartRecordForHardwareDelayTest();

    public abstract int StopAccompany(int i);

    public abstract int StopAllEffects();

    public abstract int StopEffect(int i);

    public abstract int StopPreviewDelayTest();

    public abstract int StopRecord();

    public abstract int StopRecordForHardwareDelayTest();
}
