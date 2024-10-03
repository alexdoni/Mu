package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: BUGLY */
/* loaded from: classes3.dex */
public class PlugInBean implements Parcelable {
    public static final Parcelable.Creator<PlugInBean> CREATOR = new Parcelable.Creator<PlugInBean>() { // from class: com.tencent.bugly.crashreport.common.info.PlugInBean.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ PlugInBean[] newArray(int i) {
            return new PlugInBean[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ PlugInBean createFromParcel(Parcel parcel) {
            return new PlugInBean(parcel);
        }
    };

    /* renamed from: a */
    public final String f865a;

    /* renamed from: b */
    public final String f866b;

    /* renamed from: c */
    public final String f867c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlugInBean(String str, String str2, String str3) {
        this.f865a = str;
        this.f866b = str2;
        this.f867c = str3;
    }

    public String toString() {
        return "plid:" + this.f865a + " plV:" + this.f866b + " plUUID:" + this.f867c;
    }

    public PlugInBean(Parcel parcel) {
        this.f865a = parcel.readString();
        this.f866b = parcel.readString();
        this.f867c = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f865a);
        parcel.writeString(this.f866b);
        parcel.writeString(this.f867c);
    }
}
