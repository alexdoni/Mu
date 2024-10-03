package com.google.android.gms.internal.recaptcha;

import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public enum zzvw implements zzri {
    NO_ERROR(0),
    UNKNOWN(1),
    CHALLENGE_EXPIRED(2),
    INVALID_REQUEST_TOKEN(3),
    INVALID_PIN(4),
    PIN_MISMATCH(5),
    ATTEMPTS_EXHAUSTED(6),
    ABORTED(10),
    UNRECOGNIZED(-1);

    private static final zzrj<zzvw> zzj = new zzrj<zzvw>() { // from class: com.google.android.gms.internal.recaptcha.zzvv
    };
    private final int zzl;

    zzvw(int i) {
        this.zzl = i;
    }

    public static zzvw zzb(int i) {
        if (i == 10) {
            return ABORTED;
        }
        switch (i) {
            case 0:
                return NO_ERROR;
            case 1:
                return UNKNOWN;
            case 2:
                return CHALLENGE_EXPIRED;
            case 3:
                return INVALID_REQUEST_TOKEN;
            case 4:
                return INVALID_PIN;
            case 5:
                return PIN_MISMATCH;
            case 6:
                return ATTEMPTS_EXHAUSTED;
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
