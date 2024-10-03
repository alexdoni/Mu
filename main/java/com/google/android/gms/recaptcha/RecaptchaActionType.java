package com.google.android.gms.recaptcha;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class RecaptchaActionType extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RecaptchaActionType> CREATOR = new zze();
    public static final String LOGIN = "login";
    public static final String OTHER = "other";
    public static final String SIGNUP = "signup";
    String zza;

    /* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Action {
    }

    public RecaptchaActionType(String str) {
        this.zza = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
