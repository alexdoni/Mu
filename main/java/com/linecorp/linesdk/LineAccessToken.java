package com.linecorp.linesdk;

import android.os.Parcel;
import android.os.Parcelable;
import com.linecorp.android.security.SecurityUtils;

/* loaded from: classes2.dex */
public class LineAccessToken implements Parcelable {
    public static final Parcelable.Creator<LineAccessToken> CREATOR = new Parcelable.Creator<LineAccessToken>() { // from class: com.linecorp.linesdk.LineAccessToken.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineAccessToken createFromParcel(Parcel parcel) {
            return new LineAccessToken(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineAccessToken[] newArray(int i) {
            return new LineAccessToken[i];
        }
    };
    private final String accessToken;
    private final long expiresInMillis;
    private final long issuedClientTimeMillis;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LineAccessToken(String str, long j, long j2) {
        this.accessToken = str;
        this.expiresInMillis = j;
        this.issuedClientTimeMillis = j2;
    }

    private LineAccessToken(Parcel parcel) {
        this.accessToken = parcel.readString();
        this.expiresInMillis = parcel.readLong();
        this.issuedClientTimeMillis = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.accessToken);
        parcel.writeLong(this.expiresInMillis);
        parcel.writeLong(this.issuedClientTimeMillis);
    }

    public String getTokenString() {
        return this.accessToken;
    }

    public long getExpiresInMillis() {
        return this.expiresInMillis;
    }

    public long getIssuedClientTimeMillis() {
        return this.issuedClientTimeMillis;
    }

    public long getEstimatedExpirationTimeMillis() {
        return getIssuedClientTimeMillis() + getExpiresInMillis();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LineAccessToken lineAccessToken = (LineAccessToken) obj;
        if (this.expiresInMillis == lineAccessToken.expiresInMillis && this.issuedClientTimeMillis == lineAccessToken.issuedClientTimeMillis) {
            return this.accessToken.equals(lineAccessToken.accessToken);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.accessToken.hashCode() * 31;
        long j = this.expiresInMillis;
        int i = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.issuedClientTimeMillis;
        return i + ((int) (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        return "LineAccessToken{accessToken='" + SecurityUtils.hideIfNotDebug(this.accessToken) + "', expiresInMillis=" + this.expiresInMillis + ", issuedClientTimeMillis=" + this.issuedClientTimeMillis + '}';
    }
}
