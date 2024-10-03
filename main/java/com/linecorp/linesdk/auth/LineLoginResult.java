package com.linecorp.linesdk.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.linecorp.linesdk.LineApiError;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.LineApiResponseCode;
import com.linecorp.linesdk.LineCredential;
import com.linecorp.linesdk.LineIdToken;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.utils.ParcelUtils;

/* loaded from: classes2.dex */
public class LineLoginResult implements Parcelable {
    public static final Parcelable.Creator<LineLoginResult> CREATOR = new Parcelable.Creator<LineLoginResult>() { // from class: com.linecorp.linesdk.auth.LineLoginResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineLoginResult createFromParcel(Parcel parcel) {
            return new LineLoginResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineLoginResult[] newArray(int i) {
            return new LineLoginResult[i];
        }
    };
    private final LineApiError errorData;
    private final Boolean friendshipStatusChanged;
    private final LineCredential lineCredential;
    private final LineIdToken lineIdToken;
    private final LineProfile lineProfile;
    private final String nonce;
    private final LineApiResponseCode responseCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private LineLoginResult(Builder builder) {
        this.responseCode = builder.responseCode;
        this.nonce = builder.nonce;
        this.lineProfile = builder.lineProfile;
        this.lineIdToken = builder.lineIdToken;
        this.friendshipStatusChanged = builder.friendshipStatusChanged;
        this.lineCredential = builder.lineCredential;
        this.errorData = builder.errorData;
    }

    private LineLoginResult(Parcel parcel) {
        this.responseCode = (LineApiResponseCode) ParcelUtils.readEnum(parcel, LineApiResponseCode.class);
        this.nonce = parcel.readString();
        this.lineProfile = (LineProfile) parcel.readParcelable(LineProfile.class.getClassLoader());
        this.lineIdToken = (LineIdToken) parcel.readParcelable(LineIdToken.class.getClassLoader());
        this.friendshipStatusChanged = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.lineCredential = (LineCredential) parcel.readParcelable(LineCredential.class.getClassLoader());
        this.errorData = (LineApiError) parcel.readParcelable(LineApiError.class.getClassLoader());
    }

    public static LineLoginResult error(LineApiResponseCode lineApiResponseCode, LineApiError lineApiError) {
        return new Builder().responseCode(lineApiResponseCode).errorData(lineApiError).build();
    }

    public static LineLoginResult error(LineApiResponse<?> lineApiResponse) {
        return error(lineApiResponse.getResponseCode(), lineApiResponse.getErrorData());
    }

    public static LineLoginResult internalError(LineApiError lineApiError) {
        return error(LineApiResponseCode.INTERNAL_ERROR, lineApiError);
    }

    public static LineLoginResult internalError(String str) {
        return internalError(new LineApiError(str));
    }

    public static LineLoginResult internalError(Exception exc) {
        return internalError(new LineApiError(exc));
    }

    public static LineLoginResult authenticationAgentError(LineApiError lineApiError) {
        return error(LineApiResponseCode.AUTHENTICATION_AGENT_ERROR, lineApiError);
    }

    public static LineLoginResult canceledError() {
        return error(LineApiResponseCode.CANCEL, LineApiError.DEFAULT);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeEnum(parcel, this.responseCode);
        parcel.writeString(this.nonce);
        parcel.writeParcelable(this.lineProfile, i);
        parcel.writeParcelable(this.lineIdToken, i);
        parcel.writeValue(this.friendshipStatusChanged);
        parcel.writeParcelable(this.lineCredential, i);
        parcel.writeParcelable(this.errorData, i);
    }

    public boolean isSuccess() {
        return this.responseCode == LineApiResponseCode.SUCCESS;
    }

    public LineApiResponseCode getResponseCode() {
        return this.responseCode;
    }

    public String getNonce() {
        return this.nonce;
    }

    public LineProfile getLineProfile() {
        return this.lineProfile;
    }

    public LineIdToken getLineIdToken() {
        return this.lineIdToken;
    }

    public Boolean getFriendshipStatusChanged() {
        Boolean bool = this.friendshipStatusChanged;
        if (bool == null) {
            return false;
        }
        return bool;
    }

    public LineCredential getLineCredential() {
        return this.lineCredential;
    }

    public LineApiError getErrorData() {
        return this.errorData;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LineLoginResult lineLoginResult = (LineLoginResult) obj;
        if (this.responseCode != lineLoginResult.responseCode) {
            return false;
        }
        String str = this.nonce;
        if (str == null ? lineLoginResult.nonce != null : !str.equals(lineLoginResult.nonce)) {
            return false;
        }
        LineProfile lineProfile = this.lineProfile;
        if (lineProfile == null ? lineLoginResult.lineProfile != null : !lineProfile.equals(lineLoginResult.lineProfile)) {
            return false;
        }
        LineIdToken lineIdToken = this.lineIdToken;
        if (lineIdToken == null ? lineLoginResult.lineIdToken != null : !lineIdToken.equals(lineLoginResult.lineIdToken)) {
            return false;
        }
        Boolean bool = this.friendshipStatusChanged;
        if (bool == null ? lineLoginResult.friendshipStatusChanged != null : !bool.equals(lineLoginResult.friendshipStatusChanged)) {
            return false;
        }
        LineCredential lineCredential = this.lineCredential;
        if (lineCredential == null ? lineLoginResult.lineCredential == null : lineCredential.equals(lineLoginResult.lineCredential)) {
            return this.errorData.equals(lineLoginResult.errorData);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.responseCode.hashCode() * 31;
        String str = this.nonce;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        LineProfile lineProfile = this.lineProfile;
        int hashCode3 = (hashCode2 + (lineProfile != null ? lineProfile.hashCode() : 0)) * 31;
        LineIdToken lineIdToken = this.lineIdToken;
        int hashCode4 = (hashCode3 + (lineIdToken != null ? lineIdToken.hashCode() : 0)) * 31;
        Boolean bool = this.friendshipStatusChanged;
        int hashCode5 = (hashCode4 + (bool != null ? bool.hashCode() : 0)) * 31;
        LineCredential lineCredential = this.lineCredential;
        return ((hashCode5 + (lineCredential != null ? lineCredential.hashCode() : 0)) * 31) + this.errorData.hashCode();
    }

    public String toString() {
        return "LineLoginResult{responseCode=" + this.responseCode + ", nonce='" + this.nonce + "', lineProfile=" + this.lineProfile + ", lineIdToken=" + this.lineIdToken + ", friendshipStatusChanged=" + this.friendshipStatusChanged + ", lineCredential=" + this.lineCredential + ", errorData=" + this.errorData + '}';
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private Boolean friendshipStatusChanged;
        private LineCredential lineCredential;
        private LineIdToken lineIdToken;
        private LineProfile lineProfile;
        private String nonce;
        private LineApiResponseCode responseCode = LineApiResponseCode.SUCCESS;
        private LineApiError errorData = LineApiError.DEFAULT;

        public Builder responseCode(LineApiResponseCode lineApiResponseCode) {
            this.responseCode = lineApiResponseCode;
            return this;
        }

        public Builder nonce(String str) {
            this.nonce = str;
            return this;
        }

        public Builder lineProfile(LineProfile lineProfile) {
            this.lineProfile = lineProfile;
            return this;
        }

        public Builder lineIdToken(LineIdToken lineIdToken) {
            this.lineIdToken = lineIdToken;
            return this;
        }

        public Builder friendshipStatusChanged(Boolean bool) {
            this.friendshipStatusChanged = bool;
            return this;
        }

        public Builder lineCredential(LineCredential lineCredential) {
            this.lineCredential = lineCredential;
            return this;
        }

        public Builder errorData(LineApiError lineApiError) {
            this.errorData = lineApiError;
            return this;
        }

        public LineLoginResult build() {
            return new LineLoginResult(this);
        }
    }
}
