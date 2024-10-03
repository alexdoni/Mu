package com.google.android.gms.internal.recaptcha;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.recaptcha.RecaptchaHandle;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzaj implements Parcelable.Creator<zzai> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzai createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        RecaptchaHandle recaptchaHandle = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) == 1) {
                recaptchaHandle = (RecaptchaHandle) SafeParcelReader.createParcelable(parcel, readHeader, RecaptchaHandle.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzai(recaptchaHandle);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzai[] newArray(int i) {
        return new zzai[i];
    }
}
