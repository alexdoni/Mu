package com.google.android.gms.internal.play_billing;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.tencent.p014av.sdk.AVError;

/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes2.dex */
public final class zzc extends zzh implements zze {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(IBinder iBinder) {
        super(iBinder, "com.android.vending.billing.IInAppBillingService");
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final int zza(int i, String str, String str2) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(3);
        zzo.writeString(str);
        zzo.writeString(str2);
        Parcel zzp = zzp(5, zzo);
        int readInt = zzp.readInt();
        zzp.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final int zzc(int i, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(i);
        zzo.writeString(str);
        zzo.writeString(str2);
        zzj.zzb(zzo, bundle);
        Parcel zzp = zzp(10, zzo);
        int readInt = zzp.readInt();
        zzp.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzd(int i, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(9);
        zzo.writeString(str);
        zzo.writeString(str2);
        zzj.zzb(zzo, bundle);
        Parcel zzp = zzp(TypedValues.Custom.TYPE_COLOR, zzo);
        Bundle bundle2 = (Bundle) zzj.zza(zzp, Bundle.CREATOR);
        zzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zze(int i, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(9);
        zzo.writeString(str);
        zzo.writeString(str2);
        zzj.zzb(zzo, bundle);
        Parcel zzp = zzp(12, zzo);
        Bundle bundle2 = (Bundle) zzj.zza(zzp, Bundle.CREATOR);
        zzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzf(int i, String str, String str2, String str3, String str4) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(3);
        zzo.writeString(str);
        zzo.writeString(str2);
        zzo.writeString(str3);
        zzo.writeString(null);
        Parcel zzp = zzp(3, zzo);
        Bundle bundle = (Bundle) zzj.zza(zzp, Bundle.CREATOR);
        zzp.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzg(int i, String str, String str2, String str3, String str4, Bundle bundle) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(i);
        zzo.writeString(str);
        zzo.writeString(str2);
        zzo.writeString(str3);
        zzo.writeString(null);
        zzj.zzb(zzo, bundle);
        Parcel zzp = zzp(8, zzo);
        Bundle bundle2 = (Bundle) zzj.zza(zzp, Bundle.CREATOR);
        zzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzh(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(6);
        zzo.writeString(str);
        zzo.writeString(str2);
        zzo.writeString(str3);
        zzj.zzb(zzo, bundle);
        Parcel zzp = zzp(9, zzo);
        Bundle bundle2 = (Bundle) zzj.zza(zzp, Bundle.CREATOR);
        zzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzi(int i, String str, String str2, String str3) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(3);
        zzo.writeString(str);
        zzo.writeString(str2);
        zzo.writeString(str3);
        Parcel zzp = zzp(4, zzo);
        Bundle bundle = (Bundle) zzj.zza(zzp, Bundle.CREATOR);
        zzp.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzj(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(i);
        zzo.writeString(str);
        zzo.writeString(str2);
        zzo.writeString(str3);
        zzj.zzb(zzo, bundle);
        Parcel zzp = zzp(11, zzo);
        Bundle bundle2 = (Bundle) zzj.zza(zzp, Bundle.CREATOR);
        zzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzk(int i, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(3);
        zzo.writeString(str);
        zzo.writeString(str2);
        zzj.zzb(zzo, bundle);
        Parcel zzp = zzp(2, zzo);
        Bundle bundle2 = (Bundle) zzj.zza(zzp, Bundle.CREATOR);
        zzp.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzl(int i, String str, String str2, Bundle bundle, Bundle bundle2) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(i);
        zzo.writeString(str);
        zzo.writeString(str2);
        zzj.zzb(zzo, bundle);
        zzj.zzb(zzo, bundle2);
        Parcel zzp = zzp(TypedValues.Custom.TYPE_FLOAT, zzo);
        Bundle bundle3 = (Bundle) zzj.zza(zzp, Bundle.CREATOR);
        zzp.recycle();
        return bundle3;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzm(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(8);
        zzo.writeString(str);
        zzo.writeString(str2);
        zzo.writeString("subs");
        zzj.zzb(zzo, bundle);
        Parcel zzp = zzp(801, zzo);
        Bundle bundle2 = (Bundle) zzj.zza(zzp, Bundle.CREATOR);
        zzp.recycle();
        return bundle2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing.zze
    public final void zzn(int i, String str, Bundle bundle, zzg zzgVar) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(12);
        zzo.writeString(str);
        zzj.zzb(zzo, bundle);
        zzo.writeStrongBinder(zzgVar);
        zzq(AVError.AV_ERR_ROOM_NOT_EXIST, zzo);
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final int zzr(int i, String str, String str2) throws RemoteException {
        Parcel zzo = zzo();
        zzo.writeInt(i);
        zzo.writeString(str);
        zzo.writeString(str2);
        Parcel zzp = zzp(1, zzo);
        int readInt = zzp.readInt();
        zzp.recycle();
        return readInt;
    }
}
