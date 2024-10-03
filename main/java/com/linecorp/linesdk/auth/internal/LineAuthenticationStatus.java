package com.linecorp.linesdk.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.linecorp.linesdk.internal.OneTimePassword;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class LineAuthenticationStatus implements Parcelable {
    public static final Parcelable.Creator<LineAuthenticationStatus> CREATOR = new Parcelable.Creator<LineAuthenticationStatus>() { // from class: com.linecorp.linesdk.auth.internal.LineAuthenticationStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineAuthenticationStatus createFromParcel(Parcel parcel) {
            return new LineAuthenticationStatus(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineAuthenticationStatus[] newArray(int i) {
            return new LineAuthenticationStatus[i];
        }
    };
    private String oAuthState;
    private OneTimePassword oneTimePassword;
    private String openIdNonce;
    private String sentRedirectUri;
    private Status status;

    /* loaded from: classes2.dex */
    enum Status {
        INIT,
        STARTED,
        INTENT_RECEIVED,
        INTENT_HANDLED
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LineAuthenticationStatus() {
        this.status = Status.INIT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OneTimePassword getOneTimePassword() {
        return this.oneTimePassword;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOneTimePassword(OneTimePassword oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getSentRedirectUri() {
        return this.sentRedirectUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSentRedirectUri(String str) {
        this.sentRedirectUri = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void authenticationIntentReceived() {
        this.status = Status.INTENT_RECEIVED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void authenticationIntentHandled() {
        this.status = Status.INTENT_HANDLED;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getOAuthState() {
        return this.oAuthState;
    }

    public void setOAuthState(String str) {
        this.oAuthState = str;
    }

    public String getOpenIdNonce() {
        return this.openIdNonce;
    }

    public void setOpenIdNonce(String str) {
        this.openIdNonce = str;
    }

    public void authenticationStarted() {
        this.status = Status.STARTED;
    }

    private LineAuthenticationStatus(Parcel parcel) {
        this.status = Status.INIT;
        String readString = parcel.readString();
        String readString2 = parcel.readString();
        this.oneTimePassword = (TextUtils.isEmpty(readString) || TextUtils.isEmpty(readString2)) ? null : new OneTimePassword(readString, readString2);
        this.sentRedirectUri = parcel.readString();
        this.status = Status.values()[parcel.readByte()];
        this.oAuthState = parcel.readString();
        this.openIdNonce = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        OneTimePassword oneTimePassword = this.oneTimePassword;
        parcel.writeString(oneTimePassword == null ? null : oneTimePassword.getId());
        OneTimePassword oneTimePassword2 = this.oneTimePassword;
        parcel.writeString(oneTimePassword2 != null ? oneTimePassword2.getPassword() : null);
        parcel.writeString(this.sentRedirectUri);
        parcel.writeByte((byte) this.status.ordinal());
        parcel.writeString(this.oAuthState);
        parcel.writeString(this.openIdNonce);
    }
}
