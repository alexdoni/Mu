package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public enum zzuh {
    DOUBLE(zzui.DOUBLE, 1),
    FLOAT(zzui.FLOAT, 5),
    INT64(zzui.LONG, 0),
    UINT64(zzui.LONG, 0),
    INT32(zzui.INT, 0),
    FIXED64(zzui.LONG, 1),
    FIXED32(zzui.INT, 5),
    BOOL(zzui.BOOLEAN, 0),
    STRING(zzui.STRING, 2),
    GROUP(zzui.MESSAGE, 3),
    MESSAGE(zzui.MESSAGE, 2),
    BYTES(zzui.BYTE_STRING, 2),
    UINT32(zzui.INT, 0),
    ENUM(zzui.ENUM, 0),
    SFIXED32(zzui.INT, 5),
    SFIXED64(zzui.LONG, 1),
    SINT32(zzui.INT, 0),
    SINT64(zzui.LONG, 0);

    private final zzui zzt;
    private final int zzu;

    zzuh(zzui zzuiVar, int i) {
        this.zzt = zzuiVar;
        this.zzu = i;
    }

    public final int zza() {
        return this.zzu;
    }

    public final zzui zzb() {
        return this.zzt;
    }
}
