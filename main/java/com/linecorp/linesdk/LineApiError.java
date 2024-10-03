package com.linecorp.linesdk;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes2.dex */
public class LineApiError implements Parcelable {
    public static final Parcelable.Creator<LineApiError> CREATOR = new Parcelable.Creator<LineApiError>() { // from class: com.linecorp.linesdk.LineApiError.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineApiError createFromParcel(Parcel parcel) {
            return new LineApiError(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineApiError[] newArray(int i) {
            return new LineApiError[i];
        }
    };
    public static final LineApiError DEFAULT = new LineApiError(-1, "");
    private static final int DEFAULT_HTTP_RESPONSE_CODE = -1;
    private final int httpResponseCode;
    private final String message;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LineApiError(Exception exc) {
        this(-1, toString(exc));
    }

    public LineApiError(String str) {
        this(-1, str);
    }

    public LineApiError(int i, Exception exc) {
        this(i, toString(exc));
    }

    public LineApiError(int i, String str) {
        this.httpResponseCode = i;
        this.message = str;
    }

    private LineApiError(Parcel parcel) {
        this.httpResponseCode = parcel.readInt();
        this.message = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.httpResponseCode);
        parcel.writeString(this.message);
    }

    private static String toString(Exception exc) {
        if (exc == null) {
            return null;
        }
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public int getHttpResponseCode() {
        return this.httpResponseCode;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LineApiError lineApiError = (LineApiError) obj;
        if (this.httpResponseCode != lineApiError.httpResponseCode) {
            return false;
        }
        String str = this.message;
        String str2 = lineApiError.message;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int hashCode() {
        int i = this.httpResponseCode * 31;
        String str = this.message;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "LineApiError{httpResponseCode=" + this.httpResponseCode + ", message='" + this.message + "'}";
    }
}
