package com.linecorp.linesdk.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.linecorp.linesdk.Scope;
import com.linecorp.linesdk.utils.ParcelUtils;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public class LineAuthenticationParams implements Parcelable {
    public static final Parcelable.Creator<LineAuthenticationParams> CREATOR = new Parcelable.Creator<LineAuthenticationParams>() { // from class: com.linecorp.linesdk.auth.LineAuthenticationParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineAuthenticationParams createFromParcel(Parcel parcel) {
            return new LineAuthenticationParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LineAuthenticationParams[] newArray(int i) {
            return new LineAuthenticationParams[i];
        }
    };
    private final BotPrompt botPrompt;
    private final String nonce;
    private final List<Scope> scopes;
    private final Locale uiLocale;

    /* loaded from: classes2.dex */
    public enum BotPrompt {
        normal,
        aggressive
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private LineAuthenticationParams(Builder builder) {
        this.scopes = builder.scopes;
        this.nonce = builder.nonce;
        this.botPrompt = builder.botPrompt;
        this.uiLocale = builder.uiLocale;
    }

    private LineAuthenticationParams(Parcel parcel) {
        this.scopes = Scope.convertToScopeList(parcel.createStringArrayList());
        this.nonce = parcel.readString();
        this.botPrompt = (BotPrompt) ParcelUtils.readEnum(parcel, BotPrompt.class);
        this.uiLocale = (Locale) parcel.readSerializable();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(Scope.convertToCodeList(this.scopes));
        parcel.writeString(this.nonce);
        ParcelUtils.writeEnum(parcel, this.botPrompt);
        parcel.writeSerializable(this.uiLocale);
    }

    public List<Scope> getScopes() {
        return this.scopes;
    }

    public String getNonce() {
        return this.nonce;
    }

    public BotPrompt getBotPrompt() {
        return this.botPrompt;
    }

    public Locale getUILocale() {
        return this.uiLocale;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private BotPrompt botPrompt;
        private String nonce;
        private List<Scope> scopes;
        private Locale uiLocale;

        public Builder scopes(List<Scope> list) {
            this.scopes = list;
            return this;
        }

        public Builder nonce(String str) {
            this.nonce = str;
            return this;
        }

        public Builder botPrompt(BotPrompt botPrompt) {
            this.botPrompt = botPrompt;
            return this;
        }

        public Builder uiLocale(Locale locale) {
            this.uiLocale = locale;
            return this;
        }

        public LineAuthenticationParams build() {
            return new LineAuthenticationParams(this);
        }
    }
}
