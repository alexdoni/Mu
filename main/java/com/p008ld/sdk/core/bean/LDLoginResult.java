package com.p008ld.sdk.core.bean;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDLoginResult.kt */
/* loaded from: classes2.dex */
public final class LDLoginResult implements Parcelable {
    private final int requestCode;
    private final String token;
    private final String userAvatar;
    private final String userId;
    private final String userName;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<LDLoginResult> CREATOR = new Parcelable.Creator<LDLoginResult>() { // from class: com.ld.sdk.core.bean.LDLoginResult$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LDLoginResult createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new LDLoginResult(source, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LDLoginResult[] newArray(int i) {
            return new LDLoginResult[i];
        }
    };

    public LDLoginResult() {
        this(null, null, null, null, 0, 31, null);
    }

    public /* synthetic */ LDLoginResult(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    public static /* synthetic */ LDLoginResult copy$default(LDLoginResult lDLoginResult, String str, String str2, String str3, String str4, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = lDLoginResult.token;
        }
        if ((i2 & 2) != 0) {
            str2 = lDLoginResult.userId;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            str3 = lDLoginResult.userName;
        }
        String str6 = str3;
        if ((i2 & 8) != 0) {
            str4 = lDLoginResult.userAvatar;
        }
        String str7 = str4;
        if ((i2 & 16) != 0) {
            i = lDLoginResult.requestCode;
        }
        return lDLoginResult.copy(str, str5, str6, str7, i);
    }

    public final String component1() {
        return this.token;
    }

    public final String component2() {
        return this.userId;
    }

    public final String component3() {
        return this.userName;
    }

    public final String component4() {
        return this.userAvatar;
    }

    public final int component5() {
        return this.requestCode;
    }

    public final LDLoginResult copy(String str, String str2, String str3, String str4, int i) {
        return new LDLoginResult(str, str2, str3, str4, i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDLoginResult)) {
            return false;
        }
        LDLoginResult lDLoginResult = (LDLoginResult) obj;
        return Intrinsics.areEqual(this.token, lDLoginResult.token) && Intrinsics.areEqual(this.userId, lDLoginResult.userId) && Intrinsics.areEqual(this.userName, lDLoginResult.userName) && Intrinsics.areEqual(this.userAvatar, lDLoginResult.userAvatar) && this.requestCode == lDLoginResult.requestCode;
    }

    public int hashCode() {
        String str = this.token;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.userId;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.userName;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.userAvatar;
        return ((hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + this.requestCode;
    }

    public String toString() {
        return "LDLoginResult(token=" + this.token + ", userId=" + this.userId + ", userName=" + this.userName + ", userAvatar=" + this.userAvatar + ", requestCode=" + this.requestCode + ')';
    }

    public LDLoginResult(String str, String str2, String str3, String str4, int i) {
        this.token = str;
        this.userId = str2;
        this.userName = str3;
        this.userAvatar = str4;
        this.requestCode = i;
    }

    public /* synthetic */ LDLoginResult(String str, String str2, String str3, String str4, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) == 0 ? str4 : "", (i2 & 16) != 0 ? 1 : i);
    }

    public final String getToken() {
        return this.token;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getUserName() {
        return this.userName;
    }

    public final String getUserAvatar() {
        return this.userAvatar;
    }

    public final int getRequestCode() {
        return this.requestCode;
    }

    private LDLoginResult(Parcel parcel) {
        this(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.token);
        parcel.writeString(this.userId);
        parcel.writeString(this.userName);
        parcel.writeString(this.userAvatar);
        parcel.writeInt(this.requestCode);
    }

    /* compiled from: LDLoginResult.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
