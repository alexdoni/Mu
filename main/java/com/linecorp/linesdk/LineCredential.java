package com.linecorp.linesdk;

import android.os.Parcel;
import android.os.Parcelable;
import com.linecorp.android.security.SecurityUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class LineCredential implements Parcelable {
    public static final Parcelable.Creator<LineCredential> CREATOR = new Parcelable.Creator<LineCredential>() { // from class: com.linecorp.linesdk.LineCredential.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineCredential createFromParcel(Parcel parcel) {
            return new LineCredential(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineCredential[] newArray(int i) {
            return new LineCredential[i];
        }
    };
    private final LineAccessToken accessToken;
    private final List<Scope> scopes;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LineCredential(LineAccessToken lineAccessToken, List<Scope> list) {
        this.accessToken = lineAccessToken;
        this.scopes = list;
    }

    private LineCredential(Parcel parcel) {
        this.accessToken = (LineAccessToken) parcel.readParcelable(LineAccessToken.class.getClassLoader());
        ArrayList arrayList = new ArrayList(8);
        parcel.readStringList(arrayList);
        this.scopes = Scope.convertToScopeList(arrayList);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.accessToken, i);
        parcel.writeStringList(Scope.convertToCodeList(this.scopes));
    }

    public LineAccessToken getAccessToken() {
        return this.accessToken;
    }

    public List<Scope> getScopes() {
        return this.scopes;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LineCredential lineCredential = (LineCredential) obj;
        if (this.accessToken.equals(lineCredential.accessToken)) {
            return this.scopes.equals(lineCredential.scopes);
        }
        return false;
    }

    public int hashCode() {
        return (this.accessToken.hashCode() * 31) + this.scopes.hashCode();
    }

    public String toString() {
        return "LineCredential{accessToken=" + SecurityUtils.hideIfNotDebug(this.accessToken) + ", scopes=" + this.scopes + '}';
    }
}
