package com.google.android.gms.recaptcha;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public class RecaptchaResultData extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RecaptchaResultData> CREATOR = new zzg();
    private final String zza;

    public RecaptchaResultData(String str) {
        this.zza = str;
    }

    public String getTokenResult() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getTokenResult(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
