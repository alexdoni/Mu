package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.proguard.C2581ap;
import java.util.Map;
import java.util.UUID;

/* compiled from: BUGLY */
/* loaded from: classes3.dex */
public class CrashDetailBean implements Parcelable, Comparable<CrashDetailBean> {
    public static final Parcelable.Creator<CrashDetailBean> CREATOR = new Parcelable.Creator<CrashDetailBean>() { // from class: com.tencent.bugly.crashreport.crash.CrashDetailBean.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ CrashDetailBean[] newArray(int i) {
            return new CrashDetailBean[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ CrashDetailBean createFromParcel(Parcel parcel) {
            return new CrashDetailBean(parcel);
        }
    };

    /* renamed from: A */
    public String f891A;

    /* renamed from: B */
    public String f892B;

    /* renamed from: C */
    public long f893C;

    /* renamed from: D */
    public long f894D;

    /* renamed from: E */
    public long f895E;

    /* renamed from: F */
    public long f896F;

    /* renamed from: G */
    public long f897G;

    /* renamed from: H */
    public long f898H;

    /* renamed from: I */
    public long f899I;

    /* renamed from: J */
    public long f900J;

    /* renamed from: K */
    public long f901K;

    /* renamed from: L */
    public String f902L;

    /* renamed from: M */
    public String f903M;

    /* renamed from: N */
    public String f904N;

    /* renamed from: O */
    public String f905O;

    /* renamed from: P */
    public String f906P;

    /* renamed from: Q */
    public long f907Q;

    /* renamed from: R */
    public boolean f908R;

    /* renamed from: S */
    public Map<String, String> f909S;

    /* renamed from: T */
    public Map<String, String> f910T;

    /* renamed from: U */
    public int f911U;

    /* renamed from: V */
    public int f912V;

    /* renamed from: W */
    public Map<String, String> f913W;

    /* renamed from: X */
    public Map<String, String> f914X;

    /* renamed from: Y */
    public byte[] f915Y;

    /* renamed from: Z */
    public String f916Z;

    /* renamed from: a */
    public long f917a;

    /* renamed from: aa */
    public String f918aa;

    /* renamed from: b */
    public int f919b;

    /* renamed from: c */
    public String f920c;

    /* renamed from: d */
    public boolean f921d;

    /* renamed from: e */
    public String f922e;

    /* renamed from: f */
    public String f923f;

    /* renamed from: g */
    public String f924g;

    /* renamed from: h */
    public Map<String, PlugInBean> f925h;

    /* renamed from: i */
    public Map<String, PlugInBean> f926i;

    /* renamed from: j */
    public boolean f927j;

    /* renamed from: k */
    public boolean f928k;

    /* renamed from: l */
    public int f929l;

    /* renamed from: m */
    public String f930m;

    /* renamed from: n */
    public String f931n;

    /* renamed from: o */
    public String f932o;

    /* renamed from: p */
    public String f933p;

    /* renamed from: q */
    public String f934q;

    /* renamed from: r */
    public long f935r;

    /* renamed from: s */
    public String f936s;

    /* renamed from: t */
    public int f937t;

    /* renamed from: u */
    public String f938u;

    /* renamed from: v */
    public String f939v;

    /* renamed from: w */
    public String f940w;

    /* renamed from: x */
    public String f941x;

    /* renamed from: y */
    public byte[] f942y;

    /* renamed from: z */
    public Map<String, String> f943z;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(CrashDetailBean crashDetailBean) {
        CrashDetailBean crashDetailBean2 = crashDetailBean;
        if (crashDetailBean2 == null) {
            return 1;
        }
        long j = this.f935r - crashDetailBean2.f935r;
        if (j <= 0) {
            return j < 0 ? -1 : 0;
        }
        return 1;
    }

    public CrashDetailBean() {
        this.f917a = -1L;
        this.f919b = 0;
        this.f920c = UUID.randomUUID().toString();
        this.f921d = false;
        this.f922e = "";
        this.f923f = "";
        this.f924g = "";
        this.f925h = null;
        this.f926i = null;
        this.f927j = false;
        this.f928k = false;
        this.f929l = 0;
        this.f930m = "";
        this.f931n = "";
        this.f932o = "";
        this.f933p = "";
        this.f934q = "";
        this.f935r = -1L;
        this.f936s = null;
        this.f937t = 0;
        this.f938u = "";
        this.f939v = "";
        this.f940w = null;
        this.f941x = null;
        this.f942y = null;
        this.f943z = null;
        this.f891A = "";
        this.f892B = "";
        this.f893C = -1L;
        this.f894D = -1L;
        this.f895E = -1L;
        this.f896F = -1L;
        this.f897G = -1L;
        this.f898H = -1L;
        this.f899I = -1L;
        this.f900J = -1L;
        this.f901K = -1L;
        this.f902L = "";
        this.f903M = "";
        this.f904N = "";
        this.f905O = "";
        this.f906P = "";
        this.f907Q = -1L;
        this.f908R = false;
        this.f909S = null;
        this.f910T = null;
        this.f911U = -1;
        this.f912V = -1;
        this.f913W = null;
        this.f914X = null;
        this.f915Y = null;
        this.f916Z = null;
        this.f918aa = null;
    }

    public CrashDetailBean(Parcel parcel) {
        this.f917a = -1L;
        this.f919b = 0;
        this.f920c = UUID.randomUUID().toString();
        this.f921d = false;
        this.f922e = "";
        this.f923f = "";
        this.f924g = "";
        this.f925h = null;
        this.f926i = null;
        this.f927j = false;
        this.f928k = false;
        this.f929l = 0;
        this.f930m = "";
        this.f931n = "";
        this.f932o = "";
        this.f933p = "";
        this.f934q = "";
        this.f935r = -1L;
        this.f936s = null;
        this.f937t = 0;
        this.f938u = "";
        this.f939v = "";
        this.f940w = null;
        this.f941x = null;
        this.f942y = null;
        this.f943z = null;
        this.f891A = "";
        this.f892B = "";
        this.f893C = -1L;
        this.f894D = -1L;
        this.f895E = -1L;
        this.f896F = -1L;
        this.f897G = -1L;
        this.f898H = -1L;
        this.f899I = -1L;
        this.f900J = -1L;
        this.f901K = -1L;
        this.f902L = "";
        this.f903M = "";
        this.f904N = "";
        this.f905O = "";
        this.f906P = "";
        this.f907Q = -1L;
        this.f908R = false;
        this.f909S = null;
        this.f910T = null;
        this.f911U = -1;
        this.f912V = -1;
        this.f913W = null;
        this.f914X = null;
        this.f915Y = null;
        this.f916Z = null;
        this.f918aa = null;
        this.f919b = parcel.readInt();
        this.f920c = parcel.readString();
        this.f921d = parcel.readByte() == 1;
        this.f922e = parcel.readString();
        this.f923f = parcel.readString();
        this.f924g = parcel.readString();
        this.f927j = parcel.readByte() == 1;
        this.f928k = parcel.readByte() == 1;
        this.f929l = parcel.readInt();
        this.f930m = parcel.readString();
        this.f931n = parcel.readString();
        this.f932o = parcel.readString();
        this.f933p = parcel.readString();
        this.f934q = parcel.readString();
        this.f935r = parcel.readLong();
        this.f936s = parcel.readString();
        this.f937t = parcel.readInt();
        this.f938u = parcel.readString();
        this.f939v = parcel.readString();
        this.f940w = parcel.readString();
        this.f943z = C2581ap.m839b(parcel);
        this.f891A = parcel.readString();
        this.f892B = parcel.readString();
        this.f893C = parcel.readLong();
        this.f894D = parcel.readLong();
        this.f895E = parcel.readLong();
        this.f896F = parcel.readLong();
        this.f897G = parcel.readLong();
        this.f898H = parcel.readLong();
        this.f902L = parcel.readString();
        this.f903M = parcel.readString();
        this.f904N = parcel.readString();
        this.f905O = parcel.readString();
        this.f906P = parcel.readString();
        this.f907Q = parcel.readLong();
        this.f908R = parcel.readByte() == 1;
        this.f909S = C2581ap.m839b(parcel);
        this.f925h = C2581ap.m826a(parcel);
        this.f926i = C2581ap.m826a(parcel);
        this.f911U = parcel.readInt();
        this.f912V = parcel.readInt();
        this.f913W = C2581ap.m839b(parcel);
        this.f914X = C2581ap.m839b(parcel);
        this.f915Y = parcel.createByteArray();
        this.f942y = parcel.createByteArray();
        this.f916Z = parcel.readString();
        this.f918aa = parcel.readString();
        this.f941x = parcel.readString();
        this.f899I = parcel.readLong();
        this.f900J = parcel.readLong();
        this.f901K = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f919b);
        parcel.writeString(this.f920c);
        parcel.writeByte(this.f921d ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f922e);
        parcel.writeString(this.f923f);
        parcel.writeString(this.f924g);
        parcel.writeByte(this.f927j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f928k ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f929l);
        parcel.writeString(this.f930m);
        parcel.writeString(this.f931n);
        parcel.writeString(this.f932o);
        parcel.writeString(this.f933p);
        parcel.writeString(this.f934q);
        parcel.writeLong(this.f935r);
        parcel.writeString(this.f936s);
        parcel.writeInt(this.f937t);
        parcel.writeString(this.f938u);
        parcel.writeString(this.f939v);
        parcel.writeString(this.f940w);
        C2581ap.m841b(parcel, this.f943z);
        parcel.writeString(this.f891A);
        parcel.writeString(this.f892B);
        parcel.writeLong(this.f893C);
        parcel.writeLong(this.f894D);
        parcel.writeLong(this.f895E);
        parcel.writeLong(this.f896F);
        parcel.writeLong(this.f897G);
        parcel.writeLong(this.f898H);
        parcel.writeString(this.f902L);
        parcel.writeString(this.f903M);
        parcel.writeString(this.f904N);
        parcel.writeString(this.f905O);
        parcel.writeString(this.f906P);
        parcel.writeLong(this.f907Q);
        parcel.writeByte(this.f908R ? (byte) 1 : (byte) 0);
        C2581ap.m841b(parcel, this.f909S);
        C2581ap.m828a(parcel, this.f925h);
        C2581ap.m828a(parcel, this.f926i);
        parcel.writeInt(this.f911U);
        parcel.writeInt(this.f912V);
        C2581ap.m841b(parcel, this.f913W);
        C2581ap.m841b(parcel, this.f914X);
        parcel.writeByteArray(this.f915Y);
        parcel.writeByteArray(this.f942y);
        parcel.writeString(this.f916Z);
        parcel.writeString(this.f918aa);
        parcel.writeString(this.f941x);
        parcel.writeLong(this.f899I);
        parcel.writeLong(this.f900J);
        parcel.writeLong(this.f901K);
    }
}
