package com.google.android.gms.recaptcha;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzd implements Parcelable.Creator<RecaptchaAction> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ RecaptchaAction createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        RecaptchaActionType recaptchaActionType = new RecaptchaActionType("other");
        Bundle bundle = new Bundle();
        String str = "";
        String str2 = "";
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                recaptchaActionType = (RecaptchaActionType) SafeParcelReader.createParcelable(parcel, readHeader, RecaptchaActionType.CREATOR);
            } else if (fieldId == 2) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 3) {
                bundle = SafeParcelReader.createBundle(parcel, readHeader);
            } else if (fieldId == 4) {
                str2 = SafeParcelReader.createString(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new RecaptchaAction(recaptchaActionType, str, bundle, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ RecaptchaAction[] newArray(int i) {
        return new RecaptchaAction[i];
    }
}
