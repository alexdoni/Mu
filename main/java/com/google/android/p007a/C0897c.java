package com.google.android.p007a;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: Codecs.java */
/* renamed from: com.google.android.a.c */
/* loaded from: classes.dex */
public final class C0897c {
    static {
        C0897c.class.getClassLoader();
    }

    private C0897c() {
    }

    /* renamed from: a */
    public static <T extends Parcelable> T m257a(Parcel parcel, Parcelable.Creator<T> creator) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return creator.createFromParcel(parcel);
    }

    /* renamed from: b */
    public static void m258b(Parcel parcel, Parcelable parcelable) {
        parcel.writeInt(1);
        parcelable.writeToParcel(parcel, 0);
    }

    /* renamed from: c */
    public static void m259c(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 1);
        }
    }
}
