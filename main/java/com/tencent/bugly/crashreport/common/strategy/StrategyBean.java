package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.proguard.C2581ap;
import java.util.Map;

/* compiled from: BUGLY */
/* loaded from: classes3.dex */
public class StrategyBean implements Parcelable {
    public static final Parcelable.Creator<StrategyBean> CREATOR = new Parcelable.Creator<StrategyBean>() { // from class: com.tencent.bugly.crashreport.common.strategy.StrategyBean.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ StrategyBean[] newArray(int i) {
            return new StrategyBean[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ StrategyBean createFromParcel(Parcel parcel) {
            return new StrategyBean(parcel);
        }
    };

    /* renamed from: a */
    public static String f868a = "https://android.bugly.qq.com/rqd/async";

    /* renamed from: b */
    public static String f869b = "https://android.bugly.qq.com/rqd/async";

    /* renamed from: c */
    public static String f870c;

    /* renamed from: d */
    public long f871d;

    /* renamed from: e */
    public long f872e;

    /* renamed from: f */
    public boolean f873f;

    /* renamed from: g */
    public boolean f874g;

    /* renamed from: h */
    public boolean f875h;

    /* renamed from: i */
    public boolean f876i;

    /* renamed from: j */
    public boolean f877j;

    /* renamed from: k */
    public boolean f878k;

    /* renamed from: l */
    public boolean f879l;

    /* renamed from: m */
    public boolean f880m;

    /* renamed from: n */
    public boolean f881n;

    /* renamed from: o */
    public long f882o;

    /* renamed from: p */
    public long f883p;

    /* renamed from: q */
    public String f884q;

    /* renamed from: r */
    public String f885r;

    /* renamed from: s */
    public String f886s;

    /* renamed from: t */
    public Map<String, String> f887t;

    /* renamed from: u */
    public int f888u;

    /* renamed from: v */
    public long f889v;

    /* renamed from: w */
    public long f890w;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public StrategyBean() {
        this.f871d = -1L;
        this.f872e = -1L;
        this.f873f = true;
        this.f874g = true;
        this.f875h = true;
        this.f876i = true;
        this.f877j = false;
        this.f878k = true;
        this.f879l = true;
        this.f880m = true;
        this.f881n = true;
        this.f883p = 30000L;
        this.f884q = f868a;
        this.f885r = f869b;
        this.f888u = 10;
        this.f889v = 300000L;
        this.f890w = -1L;
        this.f872e = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("S(@L@L@)");
        f870c = sb.toString();
        sb.setLength(0);
        sb.append("*^@K#K@!");
        this.f886s = sb.toString();
    }

    public StrategyBean(Parcel parcel) {
        this.f871d = -1L;
        this.f872e = -1L;
        boolean z = true;
        this.f873f = true;
        this.f874g = true;
        this.f875h = true;
        this.f876i = true;
        this.f877j = false;
        this.f878k = true;
        this.f879l = true;
        this.f880m = true;
        this.f881n = true;
        this.f883p = 30000L;
        this.f884q = f868a;
        this.f885r = f869b;
        this.f888u = 10;
        this.f889v = 300000L;
        this.f890w = -1L;
        try {
            f870c = "S(@L@L@)";
            this.f872e = parcel.readLong();
            this.f873f = parcel.readByte() == 1;
            this.f874g = parcel.readByte() == 1;
            this.f875h = parcel.readByte() == 1;
            this.f884q = parcel.readString();
            this.f885r = parcel.readString();
            this.f886s = parcel.readString();
            this.f887t = C2581ap.m839b(parcel);
            this.f876i = parcel.readByte() == 1;
            this.f877j = parcel.readByte() == 1;
            this.f880m = parcel.readByte() == 1;
            this.f881n = parcel.readByte() == 1;
            this.f883p = parcel.readLong();
            this.f878k = parcel.readByte() == 1;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.f879l = z;
            this.f882o = parcel.readLong();
            this.f888u = parcel.readInt();
            this.f889v = parcel.readLong();
            this.f890w = parcel.readLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f872e);
        parcel.writeByte(this.f873f ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f874g ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f875h ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f884q);
        parcel.writeString(this.f885r);
        parcel.writeString(this.f886s);
        C2581ap.m841b(parcel, this.f887t);
        parcel.writeByte(this.f876i ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f877j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f880m ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f881n ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f883p);
        parcel.writeByte(this.f878k ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f879l ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f882o);
        parcel.writeInt(this.f888u);
        parcel.writeLong(this.f889v);
        parcel.writeLong(this.f890w);
    }
}
