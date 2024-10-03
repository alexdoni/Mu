package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public enum zzqx {
    DOUBLE(0, 1, zzrs.DOUBLE),
    FLOAT(1, 1, zzrs.FLOAT),
    INT64(2, 1, zzrs.LONG),
    UINT64(3, 1, zzrs.LONG),
    INT32(4, 1, zzrs.INT),
    FIXED64(5, 1, zzrs.LONG),
    FIXED32(6, 1, zzrs.INT),
    BOOL(7, 1, zzrs.BOOLEAN),
    STRING(8, 1, zzrs.STRING),
    MESSAGE(9, 1, zzrs.MESSAGE),
    BYTES(10, 1, zzrs.BYTE_STRING),
    UINT32(11, 1, zzrs.INT),
    ENUM(12, 1, zzrs.ENUM),
    SFIXED32(13, 1, zzrs.INT),
    SFIXED64(14, 1, zzrs.LONG),
    SINT32(15, 1, zzrs.INT),
    SINT64(16, 1, zzrs.LONG),
    GROUP(17, 1, zzrs.MESSAGE),
    DOUBLE_LIST(18, 2, zzrs.DOUBLE),
    FLOAT_LIST(19, 2, zzrs.FLOAT),
    INT64_LIST(20, 2, zzrs.LONG),
    UINT64_LIST(21, 2, zzrs.LONG),
    INT32_LIST(22, 2, zzrs.INT),
    FIXED64_LIST(23, 2, zzrs.LONG),
    FIXED32_LIST(24, 2, zzrs.INT),
    BOOL_LIST(25, 2, zzrs.BOOLEAN),
    STRING_LIST(26, 2, zzrs.STRING),
    MESSAGE_LIST(27, 2, zzrs.MESSAGE),
    BYTES_LIST(28, 2, zzrs.BYTE_STRING),
    UINT32_LIST(29, 2, zzrs.INT),
    ENUM_LIST(30, 2, zzrs.ENUM),
    SFIXED32_LIST(31, 2, zzrs.INT),
    SFIXED64_LIST(32, 2, zzrs.LONG),
    SINT32_LIST(33, 2, zzrs.INT),
    SINT64_LIST(34, 2, zzrs.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzrs.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzrs.FLOAT),
    INT64_LIST_PACKED(37, 3, zzrs.LONG),
    UINT64_LIST_PACKED(38, 3, zzrs.LONG),
    INT32_LIST_PACKED(39, 3, zzrs.INT),
    FIXED64_LIST_PACKED(40, 3, zzrs.LONG),
    FIXED32_LIST_PACKED(41, 3, zzrs.INT),
    BOOL_LIST_PACKED(42, 3, zzrs.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzrs.INT),
    ENUM_LIST_PACKED(44, 3, zzrs.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzrs.INT),
    SFIXED64_LIST_PACKED(46, 3, zzrs.LONG),
    SINT32_LIST_PACKED(47, 3, zzrs.INT),
    SINT64_LIST_PACKED(48, 3, zzrs.LONG),
    GROUP_LIST(49, 2, zzrs.MESSAGE),
    MAP(50, 4, zzrs.VOID);

    private static final zzqx[] zzZ;
    private final zzrs zzab;
    private final int zzac;
    private final Class<?> zzad;

    static {
        zzqx[] values = values();
        zzZ = new zzqx[values.length];
        for (zzqx zzqxVar : values) {
            zzZ[zzqxVar.zzac] = zzqxVar;
        }
    }

    zzqx(int i, int i2, zzrs zzrsVar) {
        this.zzac = i;
        this.zzab = zzrsVar;
        zzrs zzrsVar2 = zzrs.VOID;
        int i3 = i2 - 1;
        if (i3 == 1) {
            this.zzad = zzrsVar.zza();
        } else if (i3 != 3) {
            this.zzad = null;
        } else {
            this.zzad = zzrsVar.zza();
        }
        if (i2 == 1) {
            zzrsVar.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
