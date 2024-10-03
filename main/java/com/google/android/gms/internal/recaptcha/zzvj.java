package com.google.android.gms.internal.recaptcha;

import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public enum zzvj implements zzri {
    MOBILE_SIGNALS_UNKNOWN(0),
    BATTERY_LEVEL(1),
    FOREGROUND_VOLUME_PERCENTAGE(2),
    AVAILABLE_RAM_PERCENTAGE(3),
    APP_VERSION(4),
    APP_PERMISSIONS(5),
    SCREEN_BRIGHTNESS(6),
    ACTIVITY_NAME(7),
    UNRECOGNIZED(-1);

    private static final zzrj<zzvj> zzj = new zzrj<zzvj>() { // from class: com.google.android.gms.internal.recaptcha.zzvi
    };
    private final int zzl;

    zzvj(int i) {
        this.zzl = i;
    }

    public static zzvj zzb(int i) {
        switch (i) {
            case 0:
                return MOBILE_SIGNALS_UNKNOWN;
            case 1:
                return BATTERY_LEVEL;
            case 2:
                return FOREGROUND_VOLUME_PERCENTAGE;
            case 3:
                return AVAILABLE_RAM_PERCENTAGE;
            case 4:
                return APP_VERSION;
            case 5:
                return APP_PERMISSIONS;
            case 6:
                return SCREEN_BRIGHTNESS;
            case 7:
                return ACTIVITY_NAME;
            default:
                return null;
        }
    }

    @Override // java.lang.Enum
    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(getClass().getName());
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this != UNRECOGNIZED) {
            sb.append(" number=");
            sb.append(zza());
        }
        sb.append(" name=");
        sb.append(name());
        sb.append(Typography.greater);
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzri
    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzl;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
