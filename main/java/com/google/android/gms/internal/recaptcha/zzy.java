package com.google.android.gms.internal.recaptcha;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.recaptcha.RecaptchaResultData;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzy implements Parcelable.Creator<zzx> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzx createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        RecaptchaResultData recaptchaResultData = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) == 1) {
                recaptchaResultData = (RecaptchaResultData) SafeParcelReader.createParcelable(parcel, readHeader, RecaptchaResultData.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzx(recaptchaResultData);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzx[] newArray(int i) {
        return new zzx[i];
    }
}
