package com.linecorp.linesdk.utils;

import android.os.Parcel;
import java.util.Date;

/* loaded from: classes2.dex */
public final class ParcelUtils {
    private static final long TIME_NONE = -1;

    private ParcelUtils() {
    }

    public static void writeDate(Parcel parcel, Date date) {
        parcel.writeLong(date != null ? date.getTime() : -1L);
    }

    public static Date readDate(Parcel parcel) {
        long readLong = parcel.readLong();
        if (readLong != -1) {
            return new Date(readLong);
        }
        return null;
    }

    public static <T extends Enum> void writeEnum(Parcel parcel, T t) {
        parcel.writeString(t != null ? t.name() : null);
    }

    public static <T extends Enum<T>> T readEnum(Parcel parcel, Class<T> cls) {
        String readString = parcel.readString();
        if (readString != null) {
            return (T) Enum.valueOf(cls, readString);
        }
        return null;
    }
}
