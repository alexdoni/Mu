package com.tencent.bugly.crashreport.biz;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.proguard.C2581ap;
import java.util.Map;

/* compiled from: BUGLY */
/* loaded from: classes3.dex */
public class UserInfoBean implements Parcelable {
    public static final Parcelable.Creator<UserInfoBean> CREATOR = new Parcelable.Creator<UserInfoBean>() { // from class: com.tencent.bugly.crashreport.biz.UserInfoBean.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ UserInfoBean[] newArray(int i) {
            return new UserInfoBean[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ UserInfoBean createFromParcel(Parcel parcel) {
            return new UserInfoBean(parcel);
        }
    };

    /* renamed from: a */
    public long f846a;

    /* renamed from: b */
    public int f847b;

    /* renamed from: c */
    public String f848c;

    /* renamed from: d */
    public String f849d;

    /* renamed from: e */
    public long f850e;

    /* renamed from: f */
    public long f851f;

    /* renamed from: g */
    public long f852g;

    /* renamed from: h */
    public long f853h;

    /* renamed from: i */
    public long f854i;

    /* renamed from: j */
    public String f855j;

    /* renamed from: k */
    public long f856k;

    /* renamed from: l */
    public boolean f857l;

    /* renamed from: m */
    public String f858m;

    /* renamed from: n */
    public String f859n;

    /* renamed from: o */
    public int f860o;

    /* renamed from: p */
    public int f861p;

    /* renamed from: q */
    public int f862q;

    /* renamed from: r */
    public Map<String, String> f863r;

    /* renamed from: s */
    public Map<String, String> f864s;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserInfoBean() {
        this.f856k = 0L;
        this.f857l = false;
        this.f858m = "unknown";
        this.f861p = -1;
        this.f862q = -1;
        this.f863r = null;
        this.f864s = null;
    }

    public UserInfoBean(Parcel parcel) {
        this.f856k = 0L;
        this.f857l = false;
        this.f858m = "unknown";
        this.f861p = -1;
        this.f862q = -1;
        this.f863r = null;
        this.f864s = null;
        this.f847b = parcel.readInt();
        this.f848c = parcel.readString();
        this.f849d = parcel.readString();
        this.f850e = parcel.readLong();
        this.f851f = parcel.readLong();
        this.f852g = parcel.readLong();
        this.f853h = parcel.readLong();
        this.f854i = parcel.readLong();
        this.f855j = parcel.readString();
        this.f856k = parcel.readLong();
        this.f857l = parcel.readByte() == 1;
        this.f858m = parcel.readString();
        this.f861p = parcel.readInt();
        this.f862q = parcel.readInt();
        this.f863r = C2581ap.m839b(parcel);
        this.f864s = C2581ap.m839b(parcel);
        this.f859n = parcel.readString();
        this.f860o = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f847b);
        parcel.writeString(this.f848c);
        parcel.writeString(this.f849d);
        parcel.writeLong(this.f850e);
        parcel.writeLong(this.f851f);
        parcel.writeLong(this.f852g);
        parcel.writeLong(this.f853h);
        parcel.writeLong(this.f854i);
        parcel.writeString(this.f855j);
        parcel.writeLong(this.f856k);
        parcel.writeByte(this.f857l ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f858m);
        parcel.writeInt(this.f861p);
        parcel.writeInt(this.f862q);
        C2581ap.m841b(parcel, this.f863r);
        C2581ap.m841b(parcel, this.f864s);
        parcel.writeString(this.f859n);
        parcel.writeInt(this.f860o);
    }
}
