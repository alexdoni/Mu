package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzml {
    private static final long[][] zza = {new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{Long.MAX_VALUE, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};

    public static long zza(long j, long j2) {
        long j3 = j + j2;
        zzmm.zza(((j ^ j2) < 0) | ((j ^ j3) >= 0), "checkedAdd", j, j2);
        return j3;
    }

    public static long zzb(long j, long j2) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j) + Long.numberOfLeadingZeros(~j) + Long.numberOfLeadingZeros(1000L) + Long.numberOfLeadingZeros(-1001L);
        if (numberOfLeadingZeros > 65) {
            return j * 1000;
        }
        zzmm.zza(numberOfLeadingZeros >= 64, "checkedMultiply", j, 1000L);
        long j3 = j * 1000;
        zzmm.zza(j == 0 || j3 / j == 1000, "checkedMultiply", j, 1000L);
        return j3;
    }

    public static long zzc(long j, long j2) {
        long j3 = j - j2;
        zzmm.zza(((j ^ j2) >= 0) | ((j ^ j3) >= 0), "checkedSubtract", j, j2);
        return j3;
    }
}
