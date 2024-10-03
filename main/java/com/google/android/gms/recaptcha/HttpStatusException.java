package com.google.android.gms.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public class HttpStatusException extends Exception {
    private final int zza;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public HttpStatusException(java.lang.String r3, int r4) {
        /*
            r2 = this;
            java.lang.String r0 = java.lang.String.valueOf(r3)
            int r0 = r0.length()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r0 = r0 + 22
            r1.<init>(r0)
            r1.append(r3)
            java.lang.String r3 = " - status: "
            r1.append(r3)
            r1.append(r4)
            java.lang.String r3 = r1.toString()
            r2.<init>(r3)
            r2.zza = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.recaptcha.HttpStatusException.<init>(java.lang.String, int):void");
    }

    public int getHttpErrorStatus() {
        return this.zza;
    }
}
